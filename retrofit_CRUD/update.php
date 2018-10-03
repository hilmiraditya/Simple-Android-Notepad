<?php

include('conn.php');

if($_SERVER['REQUEST_METHOD'] == 'POST'){

	$notepad_id = $_POST['notepad_id'];
	$notepad_title = $_POST['notepad_title'];
	$notepad_body = $_POST['notepad_body'];
	$notepad_time = $_POST['notepad_time'];

	$sql = "UPDATE `note` SET `notepad_title` = '$notepad_title', `notepad_body` = '$notepad_body', `notepad_time` = CURRENT_TIMESTAMP WHERE `note`.`notepad_id` = '$notepad_id'";

	if(mysqli_query($conn, $sql)){
		echo json_encode(array('kode'=>'1'), JSON_PRETTY_PRINT);
	}else{
		echo json_encode(array('kode'=>'0'), JSON_PRETTY_PRINT);
	};
}else{
	echo json_encode(array('kode'=>'request not valid'), JSON_PRETTY_PRINT);
}



?>

