<?php

include('conn.php');

$queryResult = $conn->query("SELECT * FROM note ORDER BY `note`.`notepad_time` DESC");
$result=array();

while ($fetchData = $queryResult->fetch_assoc()) {
	$result[]=$fetchData;
}

echo json_encode(array('kode'=>"1",'notepad' => $result ), JSON_PRETTY_PRINT);
?>

