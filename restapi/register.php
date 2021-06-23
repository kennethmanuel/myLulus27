<?php
	error_reporting(E_ERROR | E_PARSE);
	$c = new mysqli("localhost", "root","","kelulusan_ubaya");

	if($c->connect_errno) 
	{
		$arr = array("result" => "ERROR", 
					 "message" => "Failed to Connect");
		echo json_encode($arr);
		die();
	}

	if($_POST['nrp'])
	{
		$nrp = $_POST['nrp'];
		$pin = $_POST['pin'];
		$nama = $_POST['nama'];
		$angkatan = $_POST['angkatan'];

		$sql = "INSERT INTO mahasiswa (nrp, pin, nama, angkatan) VALUES ('$nrp','$pin','$nama','$angkatan')";
		$c->query($sql);
		$arr = array("result" => "OK", 
					 "sql"	=> $sql,
					 "message" => "Data has been saved");
		echo json_encode($arr);
	}
	else
	{
		$arr = array("result" => "ERROR", 
					 "message" => "title is required");
		echo json_encode($arr);
	}	
?>