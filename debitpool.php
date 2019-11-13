<?php
$servername = "localhost";
$username = "id11161569_logger123";
$password = "123456";
$payid = $_POST['payid'];
$amount = $_POST['amount'];
$con = mysqli_connect($servername,$username,$password,"id11161569_login") or die('Unable to Connect');
$sql ="UPDATE paypoo SET wallbal=wallbal-'$amount' WHERE Pid='$payid'";
if(mysqli_query($con,$sql))
{
echo"Sucessfully debited";
}
else
{
echo "error";
}

$con->close();
?>