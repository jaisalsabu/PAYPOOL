<?php
$servername = "localhost";
$username = "id11161569_logger123";
$password = "123456";
$payid = $_POST['payid'];
$recid =$_POST['recid'];
$amount = $_POST['amount'];
$conn = mysqli_connect($servername,$username,$password,"id11161569_login");
if ($conn->connect_error) {
die("Connection failed: " . $conn->connect_error);
}
$sql = "UPDATE paypoo SET wallbal=wallbal+'$amount' WHERE Pid='$recid'";
$r=mysqli_query($conn, $sql);
if ($r == true){
$deduct="UPDATE paypoo SET wallbal=wallbal-'$amount' WHERE Pid='$payid'";
mysqli_query($conn, $deduct);
echo "PAYMENT SUCCESS";
}
else {
echo "FAILED";
}
$conn->close();
?>