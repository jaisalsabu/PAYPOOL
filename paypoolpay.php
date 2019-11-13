<?php
$Amount=$_POST['amount'];
$Details=$_POST['details'];
$Payerid=$_POST['payerid'];
$Reciverid=$_POST['reciverid'];


$servername = "localhost";
$username = "id11161569_logger123";
$password = "123456";
$con = mysqli_connect($servername,$username,$password,"id11161569_login") or die('Unable to Connect');
$sql = "INSERT INTO paymentlog (Payerid,Reciverid,Details,Amount) VALUES ('$Payerid','$Reciverid','$Details','$Amount')";

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