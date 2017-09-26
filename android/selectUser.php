<?php 
//seleciona os usuarios
require_once('db_config.php');
//consulta
$sql = 'select * from tb_usuarios order by usuario;';
//executando

$response = array(); //variavel q armazena a resposta do servidor

//preenche o array com os dados da consulta:
if ($resultado = mysqli_query($conexao,$sql))
{
	while ($linha = mysqli_fetch_assoc($resultado)) {
		array_push($response, array(
						'id' => $linha["id"],
						'usuario' => $linha["usuario"],
						'senha' => $linha["senha"]
					  ));
	}
}

echo json_encode(array('response_code' => 1, 'resultado' => $response));

mysqli_close($conexao);
?>