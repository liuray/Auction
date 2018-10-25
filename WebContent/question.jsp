<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ebay.dao.ApplicationDAO" %>
<%@ page import="com.ebay.entity.UserEntity" %>
<%@ page import="java.util.LinkedList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/home.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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

<h1>Contact to Customer Representative: </h1><br>
<div align="right" style="width: 683px; "><a id="logout" href="<%=request.getContextPath() %>/logout.jsp">logout</a></div>
<big><big><font color = "white">send message to customer representative ${User.firstName }:<br></font></big></big>

<form method="post" action="FAQ" onsubmit="return valid()">
<center>
<textarea id="q" name="question" style="width:300px;height:150px;"></textarea>
</center>
<center>
<br>
<input type="submit" id="submit" value="Submit ">

</center>
</form>

<script type="text/javascript">
	windown.onload = function(){
		var logout = document.getElementById('logout');
		logout.onclick = killSession;
	}
	
	function killSession(){
		request.getSession().invalidate();
	}
	
	function valid(){
		var q = document.getElementById('q').value;
		if(q == ""){
			alert("Please Type Answer!!!");
			return false;
		}
		alert("Your Question Has Been Submited!!! ");
		return true;
	}
</script>
</body>
</html>