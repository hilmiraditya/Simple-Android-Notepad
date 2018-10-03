<?php

include('conn.php');

if($_SERVER['REQUEST_METHOD'] == 'POST'){

	$username = $_POST['username'];
	$password = $_POST['password'];
		

	$sql = "INSERT INTO `user` (`user_id`, `username`, `password`) VALUES (NULL, '$username', '$password')";

	if(mysqli_query($conn, $sql)){
		echo json_encode(array('kode' => $username), JSON_PRETTY_PRINT);
	}else{
		echo json_encode(array('kode' => "failed"), JSON_PRETTY_PRINT);
	};
}else{
	echo json_encode(array('kode' => "invalid request"), JSON_PRETTY_PRINT);
}



?>

