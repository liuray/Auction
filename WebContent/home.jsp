<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

	<center>
		<div id="round">
			<h1>CS336 Project: YABE</h1>
		</div>
	</center>
	</br>

	<form name="login" method="post" action="check">
		<center>
			<table>
				<tr>

					<td><font color="white"> E-mail: </font></td>

					<td><input type="text" name="userEmail"></td>
				</tr>
				<tr>
					<td><font color="white"> Password:</font></td>
					<td><input type="password" name="userpPassword"></td>
				</tr>
			</table>
		</center>

		<p align="center">
			<input type="submit" value="login">
		</p>
		<p>
		<center>
			<font color="red"> <a
				href="<%=request.getContextPath()%>/signUp.jsp">Sign up</a>
			</font>
			</p>

			<%
				if (request.getAttribute("done") != null) {
			%>
			<script>
				alert("Your username or password is wrong !!!!");
			</script>
			<%
				}
			%>
		
</body>
</html>