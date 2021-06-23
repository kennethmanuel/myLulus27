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

        $sql = "INSERT INTO mahasiswa_ambil_mk VALUES (?,?,?,?,?)";
        if ($stmt = $c->prepare($sql)){
            $stmt->bind_param('sssis', $nrp, $kode_mk, $semester, $tahun_ambil, $nisbi);
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