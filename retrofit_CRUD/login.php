<?php

include('conn.php');

if($_SERVER['REQUEST_METHOD'] == 'POST'){

	$username = $_POST['username'];
	$password = $_POST['password'];
		

	$sql = "SELECT * FROM user WHERE `user`.`username` = '$username' AND  `user`.`password` = '$password'";
	$request =  mysqli_query($conn, $sql);
	$array = mysqli_fetch_assoc($request);
	$count = mysqli_num_rows($request);

	if($count == 1){
		echo json_encode(array('kode' => $array['user_id']), JSON_PRETTY_PRINT);
	}


}else{
	echo json_encode(array('kode' => "invalid request"), JSON_PRETTY_PRINT);
}



?>

