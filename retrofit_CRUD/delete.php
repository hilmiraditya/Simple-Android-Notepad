<?php

include('conn.php');

if($_SERVER['REQUEST_METHOD'] == 'POST'){

	$notepad_id = $_POST['notepad_id'];

	$sql = "DELETE FROM note WHERE `notepad_id` = '$notepad_id' ";

	if(mysqli_query($conn, $sql)){
		echo json_encode(array('kode' => "1",'flag'=>'deleted'), JSON_PRETTY_PRINT);
	}else{
		echo json_encode(array('kode' => 0,'flag'=>'failed'), JSON_PRETTY_PRINT);
	};
}else{
	echo json_encode(array('kode' => 404,'flag'=>'request not valid'), JSON_PRETTY_PRINT);
}



?>

