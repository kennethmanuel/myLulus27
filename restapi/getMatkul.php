<?php
	error_reporting(E_ERROR | E_PARSE);
	$c = new mysqli("localhost", "root", "","kelulusan_ubaya");

	if($c->connect_errno)
	{
		$arr = array("result" => "ERROR", 
					 "message" => "Failed to Connect");
		echo json_encode($arr);
		die();

	$sql = "SELECT * FROM mk";
	$result = $c->query($sql);
	
	if ($result->num_rows > 0) {
		$array = array();
		while ($obj = $result -> fetch_object()) {
			$array[] = $obj;
		}
		echo json_encode(array('result' => 'OK', 'data' => $array));			
	} else {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'No data found'));
		die();
	}
?>