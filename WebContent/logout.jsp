<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
<style type="text/css">
	@font-face {
		font-family: halloween;
		src: url('fonts/bloodcrow.ttf');
	}
	h1 {
		font-family: halloween;
		color: red
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body style="background-color:black;">

<center> <h1> <font size="7" color="white"> You Have Successfully Log Out!!!</font></h1> </center>

<center><a href="<%=request.getContextPath()%>/home.jsp">Go back to home page</a> </center>
</body>
</html>