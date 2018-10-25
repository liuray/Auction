<%@page import="com.ebay.dao.UserDaoImp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ebay.dao.ApplicationDAO"%>
<%@ page import="com.*"%>
<%@ page import="com.ebay.entity.UserEntity"%>
<%@ page import="com.ebay.entity.FaqEntity"%>
<%@ page import="com.ebay.entity.BidEntity"%>
<%@ page import="com.ebay.dao.BidDaoImp"%>
<%@ page import="com.ebay.dao.ProductDaoImp"%>
<%@ page import="com.ebay.dao.AdminDaoImp"%>
<%@ page import="com.ebay.dao.AuctionDaoImp"%>


<%@ page import="com.ebay.entity.AuctionEntity"%>
<%@ page import="com.ebay.entity.ProductEntity"%>
<%@ page import="java.util.UUID"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.LinkedList"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
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
	<h1>YABE BUYER PAGE ${Buyer.firstName } ${Buyer.lastName }!</h1>
	<div align="right" style="width: 684px; height: 80px;">
		<a id="logout" href="<%=request.getContextPath()%>/logout.jsp">logout</a>
	</div>
	<center>
		<h3>
			<a href="<%=request.getContextPath()%>/question.jsp">Contact us</a>
		</h3>
		<br>
		<h3>
			<a href="<%=request.getContextPath()%>/search.jsp">SearchAction</a>
		</h3>
		<br>
	</center>
	<center>
		<h3>
			<input type="button" value="My YABY Bidding" onclick="showHistory()" />
		</h3>
	</center>

	<div id="d1" style="display: none">
		<%
			BidDaoImp bidDaoImp = new BidDaoImp();
			ProductDaoImp productDaoImp = new ProductDaoImp();
			AdminDaoImp adminDaoImp = new AdminDaoImp();
			ApplicationDAO dao = new ApplicationDAO();
			UserDaoImp userDaoImp = new UserDaoImp();
			AuctionDaoImp auc = new AuctionDaoImp();
			// Both new and old buyer has these infomation at this page
			UserEntity Buyer = (UserEntity) request.getSession().getAttribute("Buyer");
			String BuyerID = Buyer.getUserID();
			// get all bids that this buyer gets involved
			LinkedList<BidEntity> bids = bidDaoImp.searchBidByBuyerID(BuyerID);
			// Some buyer hasn't bidded anything yet.
			if (bids.size() != 0) {
				//We want to know the auctions this buyer participated
				Map<AuctionEntity, Double> OutOfDate = new HashMap<AuctionEntity, Double>();
				Map<AuctionEntity, Double> UpToDate = new HashMap<AuctionEntity, Double>();
				for (int i = 0; i < bids.size(); i++) {
					String auctionID = bids.get(i).getAuction_id();
					Double myPrice = bids.get(i).getCurrent_price();

					AuctionEntity auction = auc.searchAuctionById(auctionID).peek();
					Date today = new Date();
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					dateFormat.format(today);
					Date end_date = (java.util.Date) auction.getEnd_date();
					if (end_date.before(today)) {
						//out of date
						OutOfDate.put(auction, myPrice);
					} else {
						UpToDate.put(auction, myPrice);
					}
				}
				// Now we have all auctions that this buyer participated
		%>
		<input type="button" value="Late Auctions" onclick="showOutAs()" /><br>
		<div id="d2" style="display: none">
			<%
				if (!OutOfDate.isEmpty()) {
						Iterator it = OutOfDate.entrySet().iterator();
						while (it.hasNext()) {
							Map.Entry pair = (Map.Entry) it.next();
							AuctionEntity a = (AuctionEntity) pair.getKey();
							Double p = (Double) pair.getValue(); //data
							Date post_date = (java.util.Date) a.getPost_date(); //data
							Date end_date = (java.util.Date) a.getEnd_date(); //data
							Double start_price = a.getStart_price(); //data
							String sellerID = a.getS_id();
							UserEntity seller = userDaoImp.searchUser(sellerID).peek();
							String seller_firstName = seller.getFirstName(); //data
							String pid = a.getP_id();
							ProductEntity product = productDaoImp.searchProduct(pid).peek();
							String product_name = product.getP_name(); //data
							String product_desp = product.getP_description(); //data
			%>
			<table>
				<tr>
					<td><font color="white">Product Name: </font></td>
					<td><%=product_name%></td>
				</tr>
				<tr>
					<td><font color="white">Seller Name: </font></td>
					<td><%=seller_firstName%></td>
				</tr>
				<tr>
					<td><font color="white">Product Description: </font></td>
					<td><%=product_desp%></td>
				</tr>
				<tr>
					<td><font color="white">Auction start date: </font></td>
					<td><%=post_date%></td>
				</tr>
				<tr>
					<td><font color="white">Auction end date: </font></td>
					<td><%=end_date%></td>
				</tr>
				<tr>
					<td><font color="white">Auction start price: </font></td>
					<td><%=start_price%></td>
				</tr>
				<tr>
					<td><font color="white">My price: </font></td>
					<td><%=p%></td>
				</tr>
			</table>
			<br>
			<%
				}
					}
			%>
		</div>
		<br> <input type="button" value="Live Auctions"
			onclick="showUpAs()" /><br>
		<div id="d3" style="display: none">
			<%
				if (!UpToDate.isEmpty()) {
						Iterator it = UpToDate.entrySet().iterator();
						while (it.hasNext()) {
							Map.Entry pair = (Map.Entry) it.next();
							AuctionEntity a = (AuctionEntity) pair.getKey();
							String aid = a.getA_id();
							System.out.println(aid);
							LinkedList<BidEntity> bs = bidDaoImp.searchBid(aid);
							Double highest_price = bs.peek().getCurrent_price();
							for (int j = 0; j < bs.size(); j++) {
								if (highest_price < bs.get(j).getCurrent_price()) {
									highest_price = bs.get(j).getCurrent_price();
								}
							} // data
							Double p = (Double) pair.getValue(); //data
							Date post_date = (java.util.Date) a.getPost_date(); //data
							Date end_date = (java.util.Date) a.getEnd_date(); //data
							Double start_price = a.getStart_price(); //data
							String sellerID = a.getS_id();
							UserEntity seller = userDaoImp.searchUser(sellerID).peek();
							String seller_firstName = seller.getFirstName(); //data
							String pid = a.getP_id();
							ProductEntity product = productDaoImp.searchProduct(pid).peek();
							String product_name = product.getP_name(); //data
							String product_desp = product.getP_description(); //data
			%>
			<font color="white">
				<table frame="box">
					<tr>
						<td><font color="white">AuctionID: </font></td>
						<td><%=aid%></td>
					</tr>
					<tr>
						<td><font color="white">Product Name: </font></td>
						<td><%=product_name%></td>
					</tr>
					<tr>
						<td><font color="white">Seller Name: </font></td>
						<td><%=seller_firstName%></td>
					</tr>
					<tr>
						<td><font color="white">Product Description: </font></td>
						<td><%=product_desp%></td>
					</tr>
					<tr>
						<td><font color="white">Auction start date: </font></td>
						<td><%=post_date%></td>
					</tr>
					<tr>
						<td><font color="white">Auction end date: </font></td>
						<td><%=end_date%></td>
					</tr>
					<tr>
						<td><font color="white">Auction start price: </font></td>
						<td><%=start_price%></td>
					</tr>
					<tr>
						<td><font color="white">Current highest price: </font></td>
						<td><%=highest_price%></td>
					</tr>
					<tr>
						<td><font color="white">My price: </font></td>
						<td><%=p%> <%
 	if (highest_price > p) {
 %> <br>
						<font color="white">WARNING! You are not the current
								highest bidder</font><br> Increase Bid :
							<form name="updateForm" action="updateBid" method="post"
								onsubmit="return valid()">
								<input id="newPrice" type="text" name="updatePrice"
									onkeypress="return isNumberKey(event)" /> <input type="hidden"
									value="<%=highest_price%>" id="highestPrice" /> <input
									type="hidden" value="<%=aid%>" name="thisAuction" /> <input
									type="submit" value="submit" />
							</form> <%
 	}
 %></td>
					</tr>
				</table>
			</font> <br>
			<%
				}
					}
			%>
		</div>
		<br>
		<%
			}
		%>
	</div>

	<%
		if (request.getSession().getAttribute("qs") != null) {
			LinkedList<FaqEntity> qs = (LinkedList<FaqEntity>) request.getSession().getAttribute("qs");
			if (qs.size() != 0) {
				// ansered questions exist
	%>
	Solved questions
	<br>&nbsp;
	<br>
	<%
		for (int i = 0; i < qs.size(); i++) {
	%>
	<center>
		<font color="red">
			<div>
				<font color="white">Send Message to Custom Representative: </font><%=qs.get(i).getQuestion()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font
					color="white">Your Feedback:</font><%=qs.get(i).getAns()%>
			</div>
			<br>
		</font><font color="white"> No Response yet. </font>
	</center>
	<%
		}
			} else {
	%>

	<%
		}
		}
	%>
	<script type="text/javascript">
		windown.onload = function() {
			var logout = document.getElementById('logout');
			logout.onclick = killSession;
		}

		function killSession() {
			request.getSession().invalidate();
		}

		function showHistory() {
			document.getElementById('d1').style.display = "block";
		}

		function showOutAs() {
			document.getElementById('d2').style.display = "block";
		}

		function showUpAs() {
			document.getElementById('d3').style.display = "block";
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
			var price = document.forms["updateForm"]["updatePrice"].value;
			var highest_price = document.getElementById('highestPrice').value;
			if (price == "") {
				alert("Oops, that's not a match! Please check if any empty field");
				return false;
			} else if (Number(price) < Number(highest_price)) {
				alert("You can noly increasing you bidding");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>