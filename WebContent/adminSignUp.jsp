<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/home.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="css/home.css">

<style>
div {
	border: 15px solid transparent;
	width: 300px;
	padding: 10px 20px;
}

#round {
	border-image:
		url(images/single-line-border-clipart-Border_Lineart_8_by_inspyretash_stock.jpg)
		30 30 round;
}

a:link {
	color: red;
	background-color: transparent;
	text-decoration: none;
	font-size: 20px;
}

a:visited {
	color: red;
	background-color: transparent;
	text-decoration: none;
}

a:hover {
	color: white;
	background-color: transparent;
	text-decoration: underline;
}

a:active {
	color: yellow;
	background-color: transparent;
	text-decoration: underline;
}
</style>

</head>

<body>
	<h1><font color="white">create a representative account</font></h1>
	<div align="right" style="width: 670px;">
		<a id="logout" href="<%=request.getContextPath()%>/logout.jsp">logout</a>
	</div>
	<form name="adminForm" action="admin" method="post"
		onsubmit="return valid()"><center>
		<table >
			<tr>
				<td><font color="white">First Name: </font></td>
				<td><input type="text" name="firstName" /></td>
			</tr>
			<tr>
				<td><font color="white">Last Name: </font></td>
				<td><input type="text" name="lastName"></td>
			</tr>
			<tr>
				<td><font color="white">Password: </font></td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td><font color="white">E-mail: </font></td>
				<td><input type="text" name="email" /></td>
			</tr>
			<tr>
				<td><font color="white">Phone Number: </font></td>
				<td><input type="text" name="phone"
					onkeypress="return isNumberKey(event)" /></td>
			</tr>
			<tr>
				<td><font color="white">Address: </font></td>
				<td><input type="text" name="streetAddr" /></td>
			</tr>
			

		</table>
		</center>
		<center>
		<br>
			<input type="submit" value="submit" />
		</center>
	</form>

	<script type="text/javascript">
		function isNumberKey(evt) {
			var charCode = (evt.which) ? evt.which : evt.keyCode;
			if (charCode > 31
					&& (charCode != 46 && (charCode > 57 || charCode < 48))) {
				return false;
			}
			return true;
		}

		function valid() {
			var FirstName = document.forms["adminForm"]["firstName"].value;
			var LastName = document.forms["adminForm"]["lastName"].value;
			var password = document.forms["adminForm"]["password"].value;
			var email = document.forms["adminForm"]["email"].value;
			var phone = document.forms["adminForm"]["phone"].value;
			var streetAddr = document.forms["adminForm"]["streetAddr"].value;
			

			if (FirstName == null || FirstName == "", LastName == null
					|| LastName == "", password == null || password == "",
					email == null || email == "", phone == null || phone == "",
					streetAddr == null || streetAddr == "") {
				alert("Please Fill all Information!!");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>