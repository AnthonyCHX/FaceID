<?php

$host='localhost';
$username='id5068369_root';
$pwd='root123';
$db="id5068369_logintable";

$con=mysqli_connect($host,$username,$pwd,$db) or die('Unable to connect');

if(mysqli_connect_error($con))
{
    echo "Failed to Connect to Database ".mysqli_connect_error();
}

$nom=$_POST['nom'];
$prenom=$_POST['prenom'];
$age=$_POST['age'];
$images='https://wad97.000webhostapp.com/uploads/'.$_FILES['url']['name'];
$file_path='uploads/'.$_FILES['url']['name'];
echo($images);
echo($file_path);
 move_uploaded_file($_FILES['url']['tmp_name'],$file_path);
$sql="INSERT INTO images(url,nom,prenom,age) VALUES('$images','$nom','$prenom', $age)";

$result=mysqli_query($con,$sql);

if($result)
{
    echo ('Successfully test');
}else
{
    echo('Not saved Successfully');
}
mysqli_close($con);

?>