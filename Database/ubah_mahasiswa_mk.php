<?php
    error_reporting(E_ERROR|E_PARSE);
    $c = new mysqli("localhost","root","","kelulusan_ubaya");

    if($c -> connect_errno){
        $arr = array(
            "result" => "ERROR",
            "message" => "Failed to connect"
        );
        die(json_encode($arr));
    }

    //check the existence of POST
    if($_POST['submit']){
        $nrp = $_POST['nrp'];
        $kode_mk = $_POST['kode'];
        $semester = $_POST['semester'];
        $tahun_ambil = (int)$_POST['tahun'];
        $nisbi = $_POST['nisbi'];

        $sql = "UPDATE mahasiswa_ambil_mk SET semester=$semester, tahun_ambil=$tahun_ambil, nisbi=$nisbi WHERE nrp=$nrp AND kode_mk=$kode_mk";
        if ($stmt = $c->prepare($sql)){
            $stmt->execute();
            //diasumsikan berhasil
            $arr = array(
                "result" => "OK",
                "message" => "Data has been inserted!"
            );
            echo json_encode($arr);
        } else {
            $arr = array(
                "result" => "ERROR",
                "message" => "Database error!"
            );
            die(json_encode($arr));
        } 
        
    }else {
        $arr = array(
            "result" => "ERROR",
            "message" => "There's no data."
        );
        die(json_encode($arr));
    }

?>