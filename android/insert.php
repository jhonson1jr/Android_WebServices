<?php

if($_POST['method']=='cadastrar') {

   $usuario = $_POST['username'];
   $senha = $_POST['password'];

   require_once('db_config.php');
   //Cek npm sudah terdaftar apa belum
   $sql = "SELECT * FROM tb_usuarios WHERE usuario ='".$usuario."'";
   $check = mysqli_fetch_array(mysqli_query($conexao,$sql));
   if(isset($check)){
      $response = array('message' => "oops! Usuario ja cadastrado!",
                        'response_code' => "-1");
     echo json_encode($response);
   } else {
     $sql = "INSERT INTO tb_usuarios (usuario,senha) VALUES('$usuario','$senha')";
     if(mysqli_query($conexao,$sql)) {
      $response = array('message' => "Cadastro efetuado!",
                        'response_code' => "1");
       echo json_encode($response);
     } else {
      $response = array('message' => "oops! Houve alguma falha!",
                        'response_code' => "-1");
       echo json_encode($response);
     }
   }
   // tutup database
   mysqli_close($conexao);
} else {
  $response = array('message' => "oops! Houve alguma falha!#2",
                        'response_code' => "-1");
  echo json_encode($response);
}
?>