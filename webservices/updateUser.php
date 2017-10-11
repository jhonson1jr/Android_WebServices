<?php

//seleciona os usuarios
require_once('db_config.php');

$id = $_POST['id'];
$usuario = $_POST['username'];
$senha = $_POST['password'];

$sql = 'update tb_usuarios set usuario = "'.$usuario.'", senha = "'.$senha.'" where id='.$id.';';

//preenche o array com os dados da consulta:
if (mysqli_query($conexao,$sql))
{
	$response = array(
						'response_code' => 1,
						'message' => "Dados atualizados."
					  );
}else{
	$response = array(
						'response_code' => -1,
						'message' => "Houve alguma falha."
					  );	
}
echo json_encode($response);
mysqli_close($conexao);
?>