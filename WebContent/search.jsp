<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ebay.dao.ApplicationDAO"%>
<%@ page import="com.*"%>
<%@ page import="com.ebay.entity.SumsungEntity"%>
<%@ page import="com.ebay.entity.IphoneEntity"%>
<%@ page import="com.ebay.entity.NokiaEntity"%>
<%@ page import="com.ebay.entity.SellerEntity"%>
<%@ page import="com.ebay.entity.AuctionEntity"%>
<%@ page import="com.ebay.entity.ProductEntity"%>
<%@ page import="java.util.LinkedList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/home.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Product</title>
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
<h1>Please Type Your Product Information</h1>
</head>

<body>
	<div align="right" style="width: 680px; height: 93px;">
		<a id="logout" href="<%=request.getContextPath()%>/logout.jsp">logout</a>
	</div>
	<center>
		<table>
			<tr>
				<td><font color="white"> Item name :</font></td>
				<td><br>
					<form name="typein" method="post" action="search">
						<select id="select" name="sel">
							<option value="Sumsung">Sumsung
							<option value="Iphone">Iphone
							<option value="Nokia">Nokia
						</select> <br> <br></td>
			</tr>

			<tr>
				<td><font color="white"> <label for="price-min">lowest:</label></font></td>

				<td><br> <input type="text" name="price-min"
					onkeypress="return isNumberKey(event)" /><br> <br></td>
			</tr>

			<tr>
				<td><font color="white"> <label for="price-max">Highest: </label></font> </font></td>
				<td><br> <input type="text" name="price-max"
					onkeypress="return isNumberKey(event)" /><br> <br></td>
			</tr>
			<tr>
				<td><font color="white"> item Name:</font></td>
				<td><br> <input type="text" name="product"> <br>
				<br></td>

			</tr>
		</table>
	</center>


	<center>
		<input type="submit" value="search">
		</p>
	</center>
	</form>

	<%
		if (request.getAttribute("emptyprice") != null) {
	%>
	<script>
		alert("Imformation Canot Empty!")
	</script>

	<%
		} else if (request.getAttribute("largemin") != null) {
	%>
	<script>
		alert("Minimum Price Cannot Higher Than Maximum Price!");
	</script>

	<%
		} else if (request.getAttribute("fail") != null) {
	%>

	<script>
		alert("Cannot Find Product!!");
	</script>
	<%
		} else {
			String protype = request.getParameter("sel");
			if (protype != null) {
				LinkedList<SellerEntity> sellers = (LinkedList<SellerEntity>) request.getAttribute("sellers");
				LinkedList<AuctionEntity> auctions = (LinkedList<AuctionEntity>) request.getAttribute("auctions");
				LinkedList<ProductEntity> products = (LinkedList<ProductEntity>) request.getAttribute("products");
				if (protype.equals("Sumsung")) {
					LinkedList<SumsungEntity> Sumsungs = (LinkedList<SumsungEntity>) request.getAttribute("result");
					int counter = 0;
					while (!Sumsungs.isEmpty()) {
						SellerEntity sellptr = new SellerEntity();
						AuctionEntity aucptr = new AuctionEntity();
						ProductEntity proptr = new ProductEntity();
						SumsungEntity ptr = new SumsungEntity();
						ptr = Sumsungs.pollFirst();
						sellptr = sellers.pollFirst();
						aucptr = auctions.pollFirst();
						proptr = products.pollFirst();
						String proname = proptr.getP_name();
						String description = proptr.getP_description();
						Double startprice = aucptr.getStart_price();
						String seller = sellptr.getCompany_name();
						int sto = ptr.getS_storage_inGB();
						String cpu = ptr.getS_type();
	%>

	<center>
		<table>
			<tr>
				<td><font color="white">item name: </font></td>
				<td><font color="red">&nbsp;&nbsp;&nbsp; <%=proname%></font></td>
			</tr>
			<tr>
				<td><font color="white"> Seller :</font></td>
				<td><font color="red">&nbsp;&nbsp;&nbsp; <%=seller%></font></td>
			</tr>
			<tr>
				<td><font color="white">Memory :</font></td>
				<td><font color="red">&nbsp;&nbsp;&nbsp; <%=sto%></font></td>
			</tr>
			<tr>
				<td><font color="white">Type :</font></td>
				<td><font color="red">&nbsp;&nbsp;&nbsp; <%=cpu%></font></td>
			</tr>
			<tr>
				<td><font color="white">Description: </font></td>
				<td><font color="red">&nbsp;&nbsp;&nbsp; <%=description%></font></td>
			</tr>
			<tr>
				<td><font color="white">start Price: </font></td>
				<td><font color="red">&nbsp;&nbsp;&nbsp; <%=startprice%></font></td>
			</tr>
		</table>
	</center>
	<br>
	<center>
		<a
			href="buyerBid.jsp?sid=<%=sellptr.getUserID()%>&pid=<%=proptr.getP_id()%>&type=Sumsung&aid=<%=aucptr.getA_id()%>">Bidding</a>
	</center>
	<br>
	<%
		counter++;
					}
				} else if (protype.equals("Iphone")) {
					LinkedList<IphoneEntity> Iphones = (LinkedList<IphoneEntity>) request.getAttribute("result");
					int counter = 0;
					while (!Iphones.isEmpty()) {
						IphoneEntity ptr = new IphoneEntity();
						SellerEntity sellptr = new SellerEntity();
						AuctionEntity aucptr = new AuctionEntity();
						ProductEntity proptr = new ProductEntity();
						ptr = Iphones.pollFirst();
						sellptr = sellers.pollFirst();
						aucptr = auctions.pollFirst();
						proptr = products.pollFirst();
						String proname = proptr.getP_name();
						String description = proptr.getP_description();
						Double startprice = aucptr.getStart_price();
						String seller = sellptr.getCompany_name();
						String os = ptr.getI_type();
						int storage = ptr.getI_storage_inGB();
	%>

	<center>
		<table>
			<tr>
				<td><font color="white">item name: </font></td>
				<td><font color="red">&nbsp;&nbsp;&nbsp; <%=proname%></font></td>
			</tr>
			<tr>
				<td><font color="white"> Seller :</font></td>
				<td><font color="red">&nbsp;&nbsp;&nbsp; <%=seller%></font></td>
			</tr>
			<tr>
				<td><font color="white">Memory :</font></td>
				<td><font color="red">&nbsp;&nbsp;&nbsp; <%=os%></font></td>
			</tr>
			<tr>
				<td><font color="white">Type :</font></td>
				<td><font color="red">&nbsp;&nbsp;&nbsp; <%=storage%></font></td>
			</tr>
			<tr>
				<td><font color="white">Description : </font></td>
				<td><font color="red">&nbsp;&nbsp;&nbsp; <%=description%></font></td>
			</tr>
			<tr>
				<td><font color="white">start Price: </font></td>
				<td><font color="red">&nbsp;&nbsp;&nbsp; <%=startprice%></font></td>
			</tr>
		</table>
	</center>
	<center>
		<br>
		<a
			href="buyerBid.jsp?sid=<%=sellptr.getUserID()%>&pid=<%=proptr.getP_id()%>&type=Iphone&aid=<%=aucptr.getA_id()%>">Bidding
			</a>
	</center>
	<br>
	<%
		counter++;
					}
				} else {
					LinkedList<NokiaEntity> Nokias = (LinkedList<NokiaEntity>) request.getAttribute("result");
					int counter = 0;
					while (!Nokias.isEmpty()) {
						SellerEntity sellptr = new SellerEntity();
						AuctionEntity aucptr = new AuctionEntity();
						ProductEntity proptr = new ProductEntity();
						NokiaEntity ptr = new NokiaEntity();
						ptr = Nokias.pollFirst();
						sellptr = sellers.pollFirst();
						aucptr = auctions.pollFirst();
						proptr = products.pollFirst();
						String proname = proptr.getP_name();
						String description = proptr.getP_description();
						Double startprice = aucptr.getStart_price();
						String seller = sellptr.getCompany_name();
						String gpu = ptr.getN_storage_inGB();
						String nit = ptr.getN_type();
	%>
	<center>
		<table>
			<tr>
				<td><font color="white">item name: </font></td>
				<td><font color="red">&nbsp;&nbsp;&nbsp; <%=proname%></font></td>
			</tr>
			<tr>
				<td><font color="white"> Seller :</font></td>
				<td><font color="red">&nbsp;&nbsp;&nbsp; <%=seller%></font></td>
			</tr>
			<tr>
				<td><font color="white">Memory :</font></td>
				<td><font color="red">&nbsp;&nbsp;&nbsp; <%=gpu%></font></td>
			</tr>
			<tr>
				<td><font color="white">Type :</font></td>
				<td><font color="red">&nbsp;&nbsp;&nbsp; <%=nit%></font></td>
			</tr>
			<tr>
				<td><font color="white">Description: </font></td>
				<td><font color="red">&nbsp;&nbsp;&nbsp; <%=description%></font></td>
			</tr>
			<tr>
				<td><font color="white">start Price: </font></td>
				<td><font color="red">&nbsp;&nbsp;&nbsp; <%=startprice%></font></td>
			</tr>
		</table>
	</center>
	<center>
		<br> <a
			href="buyerBid.jsp?sid=<%=sellptr.getUserID()%>&pid=<%=proptr.getP_id()%>&type=Nokia&aid=<%=aucptr.getA_id()%>">Bidding
			</a>
	</center>
	<br>
	<%
		counter++;
					}
				}
			}
		}
	%>
	<script type="text/javascript">
		window.onload = function() {
			var logout = document.getElementById('logout');
			logout.onclick = killSession;
		}

		function isNumberKey(evt) {
			var charCode = (evt.which) ? evt.which : evt.keyCode;
			if (charCode > 31
					&& (charCode != 46 && (charCode > 57 || charCode < 48))) {
				return false;
			}
			return true;
		}

		function killSession() {
			request.getSession().invalidate();
		}
	</script>
</body>
</html>