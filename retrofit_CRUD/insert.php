<?php

include('conn.php');

if($_SERVER['REQUEST_METHOD'] == 'POST'){

	$user_id = $_POST['user_id'];
	$notepad_title = $_POST['notepad_title'];
	$notepad_body = $_POST['notepad_body'];
		

	$sql = "INSERT INTO `note` (`notepad_id`,`user_id`, `notepad_title`, `notepad_body`, `notepad_time`) VALUES (NULL, '$user_id', '$notepad_title', '$notepad_body', CURRENT_TIMESTAMP)";

	if(mysqli_query($conn, $sql)){
		echo json_encode(array('kode' => $notepad_title), JSON_PRETTY_PRINT);
	}else{
		echo json_encode(array('kode' => "failed"), JSON_PRETTY_PRINT);
	};
}else{
	echo json_encode(array('kode' => "invalid request"), JSON_PRETTY_PRINT);
}



?>

