<?php

include('conn.php');

if (isset($_POST['user_id'])) {
	$user_id = $_POST['user_id'];

	$queryResult = $conn->query("SELECT * FROM note WHERE `note`.`user_id` = '$user_id' ORDER BY `note`.`notepad_time` DESC");
	$result=array();

	while ($fetchData = $queryResult->fetch_assoc()) {
		$result[]=$fetchData;
	}

	echo json_encode(array('kode'=>"1",'notepad' => $result ), JSON_PRETTY_PRINT);
}
?>

