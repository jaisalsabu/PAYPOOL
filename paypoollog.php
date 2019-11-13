<?php
$servername = "localhost";
$username = "id11161569_logger123";
$password = "123456";
$name1=$_POST['nameo'];
$password1=$_POST['passwordo'];
$con= new mysqli($servername,$username,$password,"id11161569_login") or die('Unable to Connect');
$sql="SELECT * FROM paypoo WHERE Pname='$name1' and Password='$password1'";
$result = mysqli_query($con,$sql) or die('Could not query');
if ($result->num_rows > 0) {
// output data of each row
echo "success";
} 
else 
{
echo "not success";
}
$con->close();
?>
