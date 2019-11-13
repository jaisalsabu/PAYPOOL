<?php 
   $servername = "localhost";
  $username = "id11161569_logger123";
  $password = "123456";
  $name=$_POST['name'];
  $conn =mysqli_connect($servername,$username,$password,"id11161569_login") or die('Unable to Connect');

if (!$conn) {

die("Connection failed: " . mysqli_connect_error());

}
else
{
$stmt = $conn->prepare("SELECT * FROM paypoo where Pname='$name'");
$stmt->execute();
$result = $stmt->get_result();
$outp = $result->fetch_all(MYSQLI_ASSOC);
echo json_encode($outp);

}
mysqli_close($conn);
?>
