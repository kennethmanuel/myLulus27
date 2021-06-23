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

    $c -> set_charset("UTF8"); 
    //check the existence of POST
    if($_POST['submit']){
        $nrp = $_POST['nrp'];
        $pin = $_POST['pin'];

        $sql = "SELECT * FROM mahasiswa WHERE nrp=$nrp AND pin=$pin";
        $result = $c -> query($sql);

        if($result -> num_rows > 0){
            while($obj = $result -> fetch_object()){
                $array[] = $obj;
            }
            echo json_encode(array(
                'result' => 'OK',
                'message' => $array
            ));
        } else {
            echo json_encode(array(
                "result" => "ERROR",
                "message" => 'No data found'
            ));
            die();
        }
    } else {
        echo json_encode(array(
            'result' => 'ERROR',
            'message' => 'Database is not okay!'
        ));
        die();
    }
?>