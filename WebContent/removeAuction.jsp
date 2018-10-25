<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ebay.dao.ApplicationDAO"%>
<%@ page import="com.ebay.dao.UserDaoImp"%>
<%@ page import="com.ebay.dao.BidDaoImp"%>
<%@ page import="com.ebay.dao.ProductDaoImp"%>
<%@ page import="com.ebay.dao.AuctionDaoImp"%>

<%@ page import="com.ebay.entity.AuctionEntity"%>
<%@ page import="com.ebay.entity.ProductEntity"%>
<%@ page import="com.ebay.entity.UserEntity"%>
<%@ page import="com.ebay.entity.BidEntity"%>
<%@ page import="java.util.LinkedList"%>
<%@ page import="java.util.Date"%>
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
		<forn color="white">Auctions Management</forn>
	</h1>
	<div align="right" style="width: 685px;">
		<a id="logout" href="<%=request.getContextPath()%>/logout.jsp">logout</a>
	</div>
	</br>
	<div align="center" style="width: 694px; height: 1028px;">
		<font color="white"> Live Auctions: </font> <font
			color="white">
			<table>
				<%
					UserDaoImp userDaoImp = new UserDaoImp();
					ApplicationDAO dao = new ApplicationDAO();
					BidDaoImp bidDaoImp = new BidDaoImp();
					ProductDaoImp productDaoImp = new ProductDaoImp();
					AuctionDaoImp auctionDaoImp = new AuctionDaoImp();
					LinkedList<AuctionEntity> as = auctionDaoImp.getAllUpToDateAuctions();
					if (as.size() != 0) {
						for (int i = 0; i < as.size(); i++) {
							String AuctionID = as.get(i).getA_id(); // 1
							String SellerID = as.get(i).getS_id(); // 2
							String ProductID = as.get(i).getP_id(); // 3
							Date postDate = (java.util.Date) as.get(i).getPost_date(); // 4
							Date endDate = (java.util.Date) as.get(i).getEnd_date(); // 5
							Double startPrice = as.get(i).getStart_price(); // 6

							String type = null; //13
							if (productDaoImp.searchSumsung(ProductID).size() != 0) {
								type = "Sumsung";
							} else if (productDaoImp.searchNokia(ProductID).size() != 0) {
								type = "Nokia";
							} else if (productDaoImp.searchIphone(ProductID).size() != 0) {
								type = "Iphone";
							}

							UserEntity seller = userDaoImp.searchUser(SellerID).peek();
							String sellerName = seller.getFirstName(); // 7

							ProductEntity product = productDaoImp.searchProduct(ProductID).peek();
							String productName = product.getP_name(); // 8
							String productDesc = product.getP_description(); // 9
							LinkedList<BidEntity> bs = bidDaoImp.searchBid(AuctionID);
							if (bs.size() != 0) {
								BidEntity bid = bs.peek();
								Double highestPrice = bid.getCurrent_price(); // 10
								String winnerID = bid.getBuyer_id();
								for (int j = 0; j < bs.size(); j++) {
									bid = bs.get(j);
									if (highestPrice < bid.getCurrent_price()) {
										highestPrice = bid.getCurrent_price();
										winnerID = bid.getBuyer_id(); // 11
									}
								}
								String winnerName = userDaoImp.searchUser(winnerID).peek().getFirstName(); // 12
				%>
				<tr>
					<td><font color="white">
							<table frame="box">
								<tr>
									<td><font color="white">AuctionID: </font></td>
									<td><%=AuctionID%></td>
								</tr>
								<tr>
									<td><font color="white">Seller ID: </font></td>
									<td><%=SellerID%></td>
								</tr>
								<tr>
									<td><font color="white">Seller: </font></td>
									<td><%=sellerName%></td>
								</tr>
								<tr>
									<td><font color="white">start date: </font></td>
									<td><%=postDate%></td>
								</tr>
								<tr>
									<td><font color="white"> end date: </font></td>
									<td><%=endDate%></td>
								</tr>
								<tr>
									<td><font color="white"> start price: </font></td>
									<td><%=startPrice%></td>
								</tr>
								<tr>
									<td><font color="white">Product ID: </font></td>
									<td><%=ProductID%></td>
								</tr>
								<tr>
									<td><font color="white"> type: </font></td>
									<td><%=type%></td>
								</tr>
								<tr>
									<td><font color="white"> Name: </font></td>
									<td><%=productName%></td>
								</tr>
								<tr>
									<td><font color="white"> Description: </font></td>
									<td><%=productDesc%></td>
								</tr>
								<tr>
									<td><font color="white">Highest bid price: </font></td>
									<td><%=highestPrice%></td>
								</tr>
								<tr>
									<td><font color="white">Current winner ID: </font></td>
									<td><%=winnerID%></td>
								</tr>
								<tr>
									<td><font color="white">Current winner: </font></td>
									<td><%=winnerName%></td>
								</tr>
								<tr>
									<td>
										<form name="form<%=i%>" action="rauction" method="post">
											<input type="hidden" name="AuctionID" value="<%=AuctionID%>" />
											<input type="hidden" name="SellerID" value="<%=SellerID%>" />
											<input type="hidden" name="ProductID" value="<%=ProductID%>" />
											<input type="hidden" name="type" value="<%=type%>" />
											<center>
												<input type="submit" value="remove" />
											</center>
										</form>
									</td>
								</tr>
								<tr>
									<td>
										<table>

											</font>
											<%
												LinkedList<BidEntity> allBids = bidDaoImp.searchBid(AuctionID);
															if (allBids.size() != 0) {
																for (int w = 0; w < allBids.size(); w++) {
																	String buyerid = allBids.get(w).getBuyer_id();
																	Double bidprice = allBids.get(w).getCurrent_price();
											%>
											<tr>
												<td><font color="white">Buyer id: </font></td>
												<td><%=buyerid%></td>
											</tr>
											<tr>
												<td><font color="white">Buyer's price: </font></td>
												<td><%=bidprice%></td>
											</tr>
											<tr>
												<td>
													<form action="RemoveBid" method="post">
														<input type="hidden" value="<%=buyerid%>" name="buyerid">
														<center>
															<input type="submit" value="remove this bid">
														</center>
													</form>
												</td>
											</tr>
											<%
												}
															} else {
											%>
											<tr>
												<td><font color="white">No bid yet</font></td>
											</tr>
											<%
												}
											%>
										</table>
									</td>
								</tr>
							</table></td>
				</tr>
				<%
					}
						}
					}
				%>
			</table>
	</div>
	<script type="text/javascript">
		window.onload = function() {
			var logout = document.getElementById('logout');
			logout.onclick = killSession;
		}
		function killSession() {
			request.getSession().invalidate();
		}
	</script>
</body>
</html>