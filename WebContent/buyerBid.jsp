<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ebay.dao.ApplicationDAO"%>
<%@ page import="com.ebay.dao.UserDaoImp"%>
<%@ page import="com.ebay.dao.BidDaoImp"%>
<%@ page import="com.ebay.dao.ProductDaoImp"%>

<%@ page import="com.ebay.entity.UserEntity"%>
<%@ page import="com.ebay.entity.ProductEntity"%>
<%@ page import="com.ebay.entity.SumsungEntity"%>
<%@ page import="com.ebay.entity.NokiaEntity"%>
<%@ page import="com.ebay.entity.IphoneEntity"%>
<%@ page import="com.ebay.entity.BidEntity"%>
<%@ page import="java.util.LinkedList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<body style="background-color: black;">

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
	<div align="center">

		<h1>
			<font color="white">Start Bid ${Buyer.firstName }
		</h1>
	</div>
	<div align="right" style="width: 674px;">
		<a id="logout" href="<%=request.getContextPath()%>/logout.jsp">logout</a>
	</div>
	The product you chose:
	<%
		String sid = request.getParameter("sid");
		String pid = request.getParameter("pid");
		String aid = request.getParameter("aid");
		UserDaoImp userDaoImp = new UserDaoImp();
		BidDaoImp bidDaoImp = new BidDaoImp();
		ProductDaoImp productDaoImp = new ProductDaoImp();

		ApplicationDAO dao = new ApplicationDAO();
		UserEntity seller = userDaoImp.searchUser(sid).peek();
		ProductEntity product = productDaoImp.searchProduct(pid).peek();

		String type = request.getParameter("type");
		if (type.equals("Sumsung")) {
			SumsungEntity Sumsung = productDaoImp.searchSumsung(pid).peek();
	%>
	<div align="center">
		<table frame="box">
			<tr>
				<td style="color: #ffffff">Product name</td>
				<td>&nbsp;&nbsp;&nbsp; <%=product.getP_name()%></td>
			</tr>
			<tr>
				<td style="color: #ffffff">Seller name</td>
				<td>&nbsp;&nbsp;&nbsp; <%=seller.getFirstName()%></td>
			</tr>
			<tr>
				<td style="color: #ffffff">Storage</td>
				<td>&nbsp;&nbsp;&nbsp; <%=Sumsung.getS_storage_inGB()%></td>
			</tr>
			<tr>
				<td style="color: #ffffff">Type</td>
				<td>&nbsp;&nbsp;&nbsp; <%=Sumsung.getS_type()%></td>
			</tr>
			<tr>
				<td style="color: #ffffff">Description</td>
				<td>&nbsp;&nbsp;&nbsp; <%=product.getP_description()%></td>
			</tr>
			<tr>
				<td style="color: #ffffff">Start price</td>
				<td>&nbsp;&nbsp;&nbsp; <%=product.getP_price()%></td>
			</tr>
		</table>
	</div>
	<br>
	<%
		LinkedList<BidEntity> bs = bidDaoImp.searchBid(aid);
			Double highest_price = null;
			if (bs.size() == 0) {
				// No one bid yet
				highest_price = productDaoImp.searchProduct(pid).peek().getP_price();
	%>
	<div align="center">
		<p style="color: #ffffff">
			Current highest price:
			<%=highest_price%></p>
	</div>
	<%
		} else {
				// Somebody bid already
				LinkedList<Double> priceList = new LinkedList<Double>();
				highest_price = bs.peek().getCurrent_price();
				for (int i = 1; i < bs.size(); i++) {
					Double price = bs.get(i).getCurrent_price();
					if (price >= highest_price) {
						highest_price = price;
					}
				}
	%>
	<p style="color: #ffffff">
		Current highest price:
		<%=highest_price%></p>
	<%
		}
	%>
	<input type="button" value="manually bid" id="manBid">
	<div id="autoB" style="display: none">
		<form name="auto_form" action="auto" method="post"
			onsubmit="return valid()">
			Give a base price: (higher than current highest price):<br> <input
				name="basePrice" id="basePrice" type="text"
				onkeypress="return isNumberKey(event)" /><br> Increment amount:<br>
			<input name="increm" id="increm" type="text"
				onkeypress="return isNumberKey(event)"><br> Set a
			ceiling amount:<br> <input name="ceiling" id="ceiling"
				type="text" onkeypress="return isNumberKey(event)" /><br> <input
				name="auction_id" type="hidden" value="<%=aid%>"> <input
				type="submit" value="submit">
		</form>
	</div>

	<div id="menu" style="display: none">
		Your Bid price:
		<form name="man_form" action="bid" method="post"
			onsubmit="return valid()">
			<input id="input_price" type="text" value="<%=highest_price%>"
				name="manumPrice" onkeypress="return isNumberKey(event)" /><br>
			<input id="highest_price" type="hidden" value="<%=highest_price%>" />
			<input name="auctionID" type="hidden" value="<%=aid%>" /> <input
				type="submit" value="submit">
		</form>
	</div>
	<%
		} else if (type.equals("Nokia")) {
			NokiaEntity Nokia = productDaoImp.searchNokia(pid).peek();
	%>

	<div>
		<table frame="box">
			<tr>
				<td style="color: #ffffff">Product name</td>
				<td>&nbsp;&nbsp;&nbsp; <%=product.getP_name()%></td>
			</tr>
			<tr>
				<td style="color: #ffffff">Seller name</td>
				<td>&nbsp;&nbsp;&nbsp; <%=seller.getFirstName()%></td>
			</tr>
			<tr>
				<td style="color: #ffffff">Storage</td>
				<td>&nbsp;&nbsp;&nbsp; <%=Nokia.getN_storage_inGB()%>
				</td>
			</tr>
			<tr>
				<td style="color: #ffffff">Type</td>
				<td>&nbsp;&nbsp;&nbsp; <%=Nokia.getN_type()%></td>
			</tr>
			<tr>
				<td style="color: #ffffff">Description</td>
				<td>&nbsp;&nbsp;&nbsp; <%=product.getP_description()%></td>
			</tr>
			<tr>
				<td style="color: #ffffff">Start price</td>
				<td>&nbsp;&nbsp;&nbsp; <%=product.getP_price()%></td>
			</tr>
		</table>
	</div>
	<br>
	<%
		LinkedList<BidEntity> bs = bidDaoImp.searchBid(aid);
			Double highest_price = null;
			if (bs.size() == 0) {
				// No one bid yet
				highest_price = productDaoImp.searchProduct(pid).peek().getP_price();
	%>
	<p style="color: #ffffff">
		Current highest price:
		<%=highest_price%></p>
	<%
		} else {
				// Somebody bid already
				LinkedList<Double> priceList = new LinkedList<Double>();
				highest_price = bs.peek().getCurrent_price();
				for (int i = 1; i < bs.size(); i++) {
					Double price = bs.get(i).getCurrent_price();
					if (price >= highest_price) {
						highest_price = price;
					}
				}
	%>
	<p style="color: #ffffff">
		Current highest price:
		<%=highest_price%></p>
	<%
		}
	%>
	<input type="button" value="manually bid" id="manBid">
	<div id="autoB" style="display: none">
		<form name="auto_form" action="auto" method="post"
			onsubmit="return valid()">
			Give a base price: (higher than current highest price):<br> <input
				name="basePrice" id="basePrice" type="text"
				onkeypress="return isNumberKey(event)" /><br> Increment amount:<br>
			<input name="increm" id="increm" type="text"
				onkeypress="return isNumberKey(event)"><br> Set a
			ceiling amount:<br> <input name="ceiling" id="ceiling"
				type="text" onkeypress="return isNumberKey(event)" /><br> <input
				name="auction_id" type="hidden" value="<%=aid%>"> <input
				type="submit" value="submit">
		</form>
	</div>

	<div id="menu" style="display: none">
		Your Bid price:
		<form name="man_form" action="bid" method="post"
			onsubmit="return valid()">
			<input id="input_price" type="text" value="<%=highest_price%>"
				name="manumPrice" onkeypress="return isNumberKey(event)" /><br>
			<input id="highest_price" type="hidden" value="<%=highest_price%>">
			<input name="auctionID" type="hidden" value="<%=aid%>"> <input
				type="submit" value="submit">
		</form>
	</div>
	<%
		} else if (type.equals("Iphone")) {
			IphoneEntity Iphone = productDaoImp.searchIphone(pid).peek();
	%>
	<div>
		<table frame="box">
			<tr>
				<td style="color: #ffffff">Product name</td>
				<td>&nbsp;&nbsp;&nbsp; <%=product.getP_name()%></td>
			</tr>
			<tr>
				<td style="color: #ffffff">Seller name</td>
				<td>&nbsp;&nbsp;&nbsp; <%=seller.getFirstName()%></td>
			</tr>
			<tr>
				<td style="color: #ffffff">Type</td>
				<td>&nbsp;&nbsp;&nbsp; <%=Iphone.getI_type()%></td>
			</tr>
			<tr>
				<td style="color: #ffffff">Storage</td>
				<td>&nbsp;&nbsp;&nbsp; <%=Iphone.getI_storage_inGB()%></td>
			</tr>
			<tr>
				<td style="color: #ffffff">Description</td>
				<td>&nbsp;&nbsp;&nbsp; <%=product.getP_description()%></td>
			</tr>
			<tr>
				<td style="color: #ffffff">Start price</td>
				<td>&nbsp;&nbsp;&nbsp; <%=product.getP_price()%></td>
			</tr>
		</table>
	</div>
	<br>
	<%
		LinkedList<BidEntity> bs = bidDaoImp.searchBid(aid);
			Double highest_price = null;
			if (bs.size() == 0) {
				// No one bid yet
				highest_price = productDaoImp.searchProduct(pid).peek().getP_price();
	%>
	<p style="color: #ffffff">
		Current highest price:
		<%=highest_price%></p>
	<%
		} else {
				// Somebody bid already
				LinkedList<Double> priceList = new LinkedList<Double>();
				highest_price = bs.peek().getCurrent_price();
				for (int i = 1; i < bs.size(); i++) {
					Double price = bs.get(i).getCurrent_price();
					if (price >= highest_price) {
						highest_price = price;
					}
				}
	%>
	<p style="color: #ffffff">
		Current highest price:
		<%=highest_price%></p>
	<%
		}
	%>
	<input type="button" value="manually bid" id="manBid">
	<div id="autoB" style="display: none">
		<form name="auto_form" action="auto" method="post"
			onsubmit="return valid2()">
			Give a base price: (higher than current highest price):<br> <input
				name="basePrice" id="basePrice" type="text"
				onkeypress="return isNumberKey(event)" /><br> Increment amount:<br>
			<input name="increm" id="increm" type="text"
				onkeypress="return isNumberKey(event)"><br> Set a
			ceiling amount:<br> <input name="ceiling" id="ceiling"
				type="text" onkeypress="return isNumberKey(event)" /><br> <input
				name="auction_id" type="hidden" value="<%=aid%>"> <input
				type="submit" value="submit">
		</form>
	</div>

	<div id="menu" style="display: none">
		Your Bid price:
		<form name="man_form" action="bid" method="post"
			onsubmit="return valid()">
			<input id="input_price" type="text" value="<%=highest_price%>"
				name="manumPrice" onkeypress="return isNumberKey(event)" /><br>
			<input id="highest_price" type="hidden" value="<%=highest_price%>">
			<input name="auctionID" type="hidden" value="<%=aid%>"> <input
				type="submit" value="submit">
		</form>
	</div>
	<%
		}
	%>
	<script type="text/javascript">
		window.onload = function() {
			var logout = document.getElementById('logout');
			logout.onclick = killSession;

			var manBid = document.getElementById('manBid');
			manBid.onclick = man;

			var autoBid = document.getElementById('autoBid');
			autoBid.onclick = auto;
		}

		function killSession() {
			request.getSession().invalidate();
		}

		function man() {
			document.getElementById('menu').style.display = "block";
		}

		function auto() {
			document.getElementById('autoB').style.display = "block";
		}

		function isNumberKey(evt) {
			var charCode = (evt.which) ? evt.which : evt.keyCode;
			if (charCode > 31
					&& (charCode != 46 && (charCode > 57 || charCode < 48))) {
				return false;
			}
			return true;
		}

		function valid() {
			var price = document.forms[man_form][manumPrice].value;
			var highest_price = document.getElementById('highest_price').value;
			if (price == "") {
				alert("Please fill all required field");
				return false;
			}
			if (Number(price) < Number(highest_price)) {
				alert("Your bid price cannot be lower than the highest price");
				return false;
			}
			return true;
		}

		function valid2() {
			var Bprice = document.forms["auto_form"]["basePrice"].value;
			var increm = document.forms["auto_form"]["increm"].value;
			var ceiling = document.forms["auto_form"]["ceiling"].value;
			var highest_price = document.getElementById('highest_price').value;
			if (Bprice == null || Bprice == "", increm == null || increm == "",
					ceiling == null || ceiling == "") {
				alert("Please fill all required field");
				return false;
			} else if (Number(Bprice) < Number(highest_price)) {
				alert("Your base price cannot be lower than the highest price");
				return false;
			} else if (Number(increm) <= 0) {
				alert("Your increment amount cannot be negative or 0");
			} else if (Number(Bprice) >= Number(ceiling)) {
				alert("Your ceiling amount must be higher than base price");
			}
			return true;
		}
	</script>
</body>
</html>