<?php
    //disable error/warning in PHP jika ada echo line kecuali file JSON
    error_reporting(E_ERROR|E_PARSE);
    $c = new mysqli("localhost", "root", "", "kelulusan_ubaya");

    if($c -> connect_errno){
        //generate json (berisi associative array (ada key -> value))
        echo json_encode(array(
            "result" => "ERROR",
            "message" => "Failed to connect DB"
        ));
        die();
    }

    $c -> set_charset("UTF8"); //kalau hasil kosong
    if($_POST['submit']){
        $nrp = $_POST['nrp'];
        $sql = "SELECT * FROM mahasiswa_ambil_mk WHERE nrp=$nrp";
        $result = $c -> query($sql);
        $array = array();
    
        if($result -> num_rows > 0){
            while($obj = $result -> fetch_object()){
                $array[] = $obj;
            }
            echo json_encode(array(
                'result' => 'OK',
                'data' => $array
            ));
        }
        else{
            echo json_encode(array(
                "result" => "ERROR",
                "message" => 'No data found'
            ));
            die();
        }
    }  
?>