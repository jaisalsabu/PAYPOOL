<?php
$Name=$_POST['name'];
$Phone=$_POST['phone'];
$Password=$_POST['password'];
$servername = "localhost";
$username = "id11161569_logger123";
$password = "123456";
$con = mysqli_connect($servername,$username,$password,"id11161569_login") or die('Unable to Connect');
	$n=10; 
function getName($n) { 
    $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'; 
    $randomString = ''; 
  
    for ($i = 0; $i < $n; $i++) { 
        $index = rand(0, strlen($characters) - 1); 
        $randomString .= $characters[$index]; 
    } 
  
    return $randomString; 
} 
  
$str=getName($n); 
$sql = "INSERT INTO paypoo (Pname,Pid,Phone,Password) VALUES ('$Name','$str','$Phone','$Password')";

if(mysqli_query($con,$sql))
{
echo "Successfully Uploaded";
}
else
{
echo "Error";
}

$con->close();
?>