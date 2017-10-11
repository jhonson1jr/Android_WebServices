<?php //Php Script to insert data into MySQL database
 error_reporting(0); 
include("db_config.php"); 
// array for JSON response 
$response = array(); 
if( !(empty($_POST['item_name']))) { 
	$item_name=$_POST['item_name'];
	$result = mysql_query("INSERT INTO tb_usuarios(id,usuario, senha) VALUES('','$usuario','$senha')"); 
	if($result>0){
           $response["success"] = 1;
         }    
     else{
           $response["success"] = 0;
         }
     // echoing JSON response
     echo json_encode($response);
}
 
?>