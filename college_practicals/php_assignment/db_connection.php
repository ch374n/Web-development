<html>
	<head>
		<title>Php Assignment</title>
	</head>	
	<body>
		<table>
			<tr>
				<th>Username</th>
				<th>password</th>
			</tr>
			<?php 
				$HOST = "localhost";
				$USERNAME = "root";
				$PASSWORD = "";

				$connection = mysqli_connect($HOST, $USERNAME, $PASSWORD);

				$sql = "CREATE DATABASE IF NOT EXISTS users_info";
				mysqli_query($connection, $sql);

				$sql = "USE users_info";

				mysqli_query($connection, $sql);

				$sql = "CREATE TABLE IF NOT EXISTS users(username TEXT, password TEXT)";

				mysqli_query($connection, $sql);

				$username = $_GET["username"];
				$password = $_GET["password"];

				$sql = "INSERT INTO users VALUES('".$username."', '".$password."')";

				mysqli_query($connection, $sql);

				$sql = "SELECT * FROM users";

				$result_set = mysqli_query($connection, $sql);

				
				while($row = mysqli_fetch_assoc($result_set)) {
					echo "<tr>".
					     "<td>{$row['username']}</td>".
					     "<td>{$row['password']}</td>".
					     "</tr>";					
				}
				
				

			 ?>
			
		</table>
		<a href="index.html">GO BACK</a>
	</body>
</html>
