<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
	<h1>
		<center><font color="white">
		Sign up Information:</font></center>
	</h1>
	<div align="right" style="width: 680px; height: 94px;">
		<a id="logout" href="<%=request.getContextPath()%>/logout.jsp">logout</a>
	</div>
	</br>
	<form name="signUp" method="post" action="add">
		<center>
			<table>
				<tr>
					<td><font color="white">
					Last Name:</font></td>
					<td><input type="text" name="lastName"></br></td>
				</tr>
				<tr>
					<td><font color="white">
					First Name:</font></td>
					<td><input type="text" name="firstName"></td>
				</tr>

				<tr>
					<td><font color="white">
					Password:</font></td>
					<td><input type="password" name="password"></br></td>
				</tr>
				<tr>
					<td><font color="white">
					E-mail:</font></td>
					<td><input type="text" name="email"></br></td>
				</tr>
				<tr>
					<td><font color="white">
					Phone Number:</font></td>
					<td><input type="text" name="phone"></td>

					</br>
				</tr>
				<tr>
					<td><font color="white">
					Address:</font></td>
					<td><input type="text" name="streetAddr"></br></td>
				</tr>
				<tr>

					<td><font color="white">
					Type:</font></td>
					<td><font color="white">
					<input type="radio" name="identity" value="buyer"
						id="buyer">Buyer</br> <input type="radio" name="identity"
						value="seller" id="seller">Seller</font></td>
				</tr>

			</table>
		</center>


		

<center> 

			<div id="buyerClickOn" style="display: none">
				
					<table>
						<tr>
							<td><font color="white">
							Cash or credit card? </font><br>
							</td>
							<td><font color="white">
							<input type="radio" name="payment_method" value="visa"
								id="visa" checked>Card</br> <input type="radio"
								name="payment_method" value="paypal" id="paypal">Cash</font></td>
						</tr>
					</table>
				
			</div>
</center>
<center>
			<div id="sellerClickOn" style="display: none">
				
					<table>
						<tr>
							<td><font color="white">
							working for:</font></td>
							<td><input id="company_name" name="company_name"><br></td>
						</tr>
					</table>
				
			</div>
			</center>
		</font>
		<center>
			<input type="submit" value="submit" id="sub">
		</center>
	</form>





	<script type="text/javascript">
		window.onload = function() {
			var b = document.getElementById('buyer');
			var s = document.getElementById('seller');
			//		var sub = document.getElementById('sub');

			b.onclick = buyer;
			s.onclick = seller;
			//		sub.onclick = finalCheck;

			var logout = document.getElementById('logout');
			logout.onclick = killSession;
		}

		function buyer() {
			document.getElementById('sellerClickOn').style.display = "none";
			document.getElementById('buyerClickOn').style.display = "block";
		}

		function seller() {
			document.getElementById('buyerClickOn').style.display = "none";
			document.getElementById('sellerClickOn').style.display = "block";
		}

		function killSession() {
			request.getSession().invalidate();
		}
	</script>
</body>
</html>