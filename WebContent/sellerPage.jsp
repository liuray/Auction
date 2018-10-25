<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.LinkedList"%>
<%@ page import="com.ebay.entity.FaqEntity"%>
<%@ page import="com.ebay.entity.AuctionEntity"%>
<%@ page import="com.ebay.entity.ProductEntity"%>
<%@ page import="com.ebay.dao.ApplicationDAO"%>
<%@ page import="com.ebay.entity.BidEntity"%>
<%@ page import="com.ebay.entity.UserEntity"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.ebay.dao.UserDaoImp"%>
<%@ page import="com.ebay.dao.BidDaoImp"%>
<%@ page import="com.ebay.dao.ProductDaoImp"%>
<%@ page import="com.ebay.dao.AuctionDaoImp"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/home.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/DatePicker/WdatePicker.js"></script>
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
	<h1>Seller Page: ${Seller.firstName }, ${Seller.lastName }!</h1>
	<div align="right" style="width: 685px;">
		<a id="logout" href="<%=request.getContextPath()%>/logout.jsp">logout</a>
	</div>
	<br>
	<big>
		<center>
			<table>
				<tr>
					<td><font color="white">Contact Us: </font></td>
					<td><a id="faq"
						href="<%=request.getContextPath()%>/question.jsp">Representative</a></td>
				</tr>
				<tr>
					<td><font color="white">Sell Item : </font></td>
					<td><a style="color: white; cursor: pointer" id="auction">Create Auction</a></td>
				</tr>
				<tr>
					<td><font color="white">Check Auctions : </font></td>
					<td><a style="color: white; cursor: pointer" id="checkAuction">Bidding Status</a></td>
				</tr>
			</table>
		</center>
	</big>
	<div id="d2" style="display: none">
		<%
			UserDaoImp userDaoImp = new UserDaoImp();
			BidDaoImp bidDaoImp = new BidDaoImp();
			ProductDaoImp productDaoImp = new ProductDaoImp();
			AuctionDaoImp auc = new AuctionDaoImp();
			ApplicationDAO dao = new ApplicationDAO();
			UserEntity user = (UserEntity) request.getSession().getAttribute("Seller");
			String UserID = user.getUserID();
			LinkedList<AuctionEntity> as = auc.searchAuction(UserID);
			LinkedList<AuctionEntity> out_date_as = new LinkedList<AuctionEntity>();
			LinkedList<AuctionEntity> upto_date_as = new LinkedList<AuctionEntity>();
			if (as.size() != 0) {
				for (int i = 0; i < as.size(); i++) {
					Date end_date = (java.util.Date) as.get(i).getEnd_date();
					Date now_date = new Date();
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					dateFormat.format(now_date);
					if (end_date.before(now_date)) {
						// this auction is out of date
						out_date_as.add(as.get(i));
					} else {
						upto_date_as.add(as.get(i));
					}
				}
				request.getSession().setAttribute("out_date_as", out_date_as);
				request.getSession().setAttribute("out_date_as", upto_date_as);
			}
		%>
		<input type="button" value="Late auctions" onclick="showOutAs()" />
		<div id="d3" style="display: none">
			<font color="white">Your past autions</font><br>
			<%
				if (out_date_as.size() != 0) {
					for (int j = 0; j < out_date_as.size(); j++) {
						String pid = out_date_as.get(j).getP_id();
						String aid = out_date_as.get(j).getA_id();
						LinkedList<BidEntity> bids = bidDaoImp.searchBid(aid);
						ProductEntity product = productDaoImp.searchProduct(pid).peek();

						if (bids.size() == 0) {
							// no one bid this auction
			%>
			<div style="width: 691px; height: 354px;">
				<center>
					<table frame="box">
						<tr>
							<td><font color="white">start date:</font><font> <font>&nbsp;&nbsp;&nbsp;</font><%=out_date_as.get(j).getPost_date()%></font></td>
						</tr>
						<tr>
							<td><font color="white">end date:</font><font> <font>&nbsp;&nbsp;&nbsp;</font><%=out_date_as.get(j).getEnd_date()%></font></td>
						</tr>
						<tr>
							<td><font color="white">start price:</font><font> <font>&nbsp;&nbsp;&nbsp;</font><%=out_date_as.get(j).getStart_price()%></font></td>
						</tr>
						<tr>
							<td><font color="white">winner:</font><font> <font>&nbsp;&nbsp;&nbsp;</font>
									Auction has not end yet
							</font></td>
						</tr>
						<tr>
							<td><font color="white">MAX bidding price:</font><font>
									<font>&nbsp;&nbsp;&nbsp;</font><%=out_date_as.get(j).getStart_price()%></font></td>
						</tr>
					</table>
				</center>
			</div>
			<br>
			<%
				} else {
							// some buyers get involved, needs to find the max 
							Double max = bids.peek().getCurrent_price();
							String winnerID = bids.peek().getBuyer_id();
							for (int k = 0; k < bids.size(); k++) {
								if (max < bids.get(k).getCurrent_price()) {
									max = bids.get(k).getCurrent_price();
									winnerID = bids.get(k).getBuyer_id();
								}
							}
							String winnerFirstName = userDaoImp.searchUser(winnerID).peek().getFirstName();
			%>
			<div>
				<center>
					<table frame="box">
						<tr>
							<td><font color="white">start date:</font><font>
									&nbsp;&nbsp;&nbsp;<%=out_date_as.get(j).getPost_date()%></font></td>
						</tr>
						<tr>
							<td><font color="white">end date:</font><font>
									&nbsp;&nbsp;&nbsp;<%=out_date_as.get(j).getEnd_date()%></font></td>
						</tr>
						<tr>
							<td><font color="white">start price:</font><font>
									&nbsp;&nbsp;&nbsp;<%=out_date_as.get(j).getStart_price()%></font></td>
						</tr>
						<tr>
							<td><font color="white">winner:</font><font>
									&nbsp;&nbsp;&nbsp;<%=winnerFirstName%></font></td>
						</tr>
						<tr>
							<td><font color="white">MAX bidding price:</font><font>
									&nbsp;&nbsp;&nbsp;<%=max%></font></td>
						</tr>
					</table>
				</center>
			</div>
			<br>
			<%
				}
					}
				}
			%>
		</div>
		<center>
			<input type="button" value="Live auctions" onclick="showUpAs()" />

			<div id="d4" style="display: none">
				<font color="white">Your live Auctions</font>
				<%
					if (upto_date_as.size() != 0) {
						for (int j = 0; j < upto_date_as.size(); j++) {
							String Upid = upto_date_as.get(j).getP_id();
							String Uaid = upto_date_as.get(j).getA_id();
							LinkedList<BidEntity> Ubids = bidDaoImp.searchBid(Uaid);
							ProductEntity product = productDaoImp.searchProduct(Upid).peek();

							if (Ubids.size() == 0) {
								// no one bid this auction
				%>
				<div>
					<center>
						<font color="white">
							<table frame="box">
								<tr>
									<td><font color="white">post date:</font><font> <font>&nbsp;&nbsp;&nbsp;</font><%=upto_date_as.get(j).getPost_date()%></font></td>
								</tr>
								<tr>
									<td><font color="white">end date:</font><font> <font>&nbsp;&nbsp;&nbsp;</font><%=upto_date_as.get(j).getEnd_date()%></font></td>
								</tr>
								<tr>
									<td><font color="white">start price:</font><font> <font>&nbsp;&nbsp;&nbsp;</font><%=upto_date_as.get(j).getStart_price()%></font></td>
								</tr>
								<tr>
									<td><font color="white">Current winner:</font><font>
											<font>&nbsp;&nbsp;&nbsp;</font>Auction has not end yet
									</font></td>
								</tr>
								<tr>
									<td><font color="white">MAX bidding price:</font><font>
											<font>&nbsp;&nbsp;&nbsp;</font><%=upto_date_as.get(j).getStart_price()%></font></td>
								</tr>
								<tr>
									<td>
										<form id="dropForm" action="drop" method="post">
											<input type="hidden" value="<%=Uaid%>" name="aid"> <input
												type="hidden" value="<%=Upid%>" name="pid"> <input
												type="hidden" value="<%=UserID%>" name="sid"> <input
												type="submit" value="Abondon auction" />
										</form>
									</td>
								</tr>
							</table>
						</font>
					</center>
				</div>
				<br>
				<%
					} else {
								// some buyers get involved, needs to find the max 
								Double Umax = Ubids.peek().getCurrent_price();
								String UwinnerID = Ubids.peek().getBuyer_id();
								for (int k = 0; k < Ubids.size(); k++) {
									if (Umax < Ubids.get(k).getCurrent_price()) {
										Umax = Ubids.get(k).getCurrent_price();
										UwinnerID = Ubids.get(k).getBuyer_id();
									}
								}
								String UwinnerFirstName = userDaoImp.searchUser(UwinnerID).peek().getFirstName();
				%>
				<div>
					<font color="white">
						<table frame="box">
							<tr>
								<td><font color="white">start date:</font><font>
										&nbsp;&nbsp;&nbsp;<%=upto_date_as.get(j).getPost_date()%></font></td>
							</tr>
							<tr>
								<td><font color="white">end date:</font><font>
										&nbsp;&nbsp;&nbsp;<%=upto_date_as.get(j).getEnd_date()%></font></td>
							</tr>
							<tr>
								<td><font color="white">start price:</font><font>
										&nbsp;&nbsp;&nbsp;<%=upto_date_as.get(j).getStart_price()%></font></td>
							</tr>
							<tr>
								<td><font color="white">Current winner:</font><font>
										&nbsp;&nbsp;&nbsp;<%=UwinnerFirstName%></font></td>
							</tr>
							<tr>
								<td><font color="white">MAX bidding price:</font><font>
										&nbsp;&nbsp;&nbsp;<%=Umax%></font></td>
							</tr>
						</table>
					</font>
				</div>
				<br>
				<%
					}
						}
					}
				%>
			</div>
	</div>
	<br>
	<center>
		<font color="white">
			<div id="d1" style="display: none">

				<form method="post" name="auction_form" onsubmit="return valid()"
					action="auction">
					<center>
						<table>
							<tr>
								<td><font color="white">Product Information:</font></td>
							</tr>
							<tr>
								<td><font color="white"> Name : </font></td>
								<td><input type="text" name="product_name" /></td>
							</tr>
							<tr>
								<td><font color="white"> Type :</font></td>
								<td><input type="radio" name="product_type" value="Sumsung"
									id="Sumsung" />Sumsung<br> <input type="radio"
									name="product_type" value="Nokia" id="Nokia" />Nokia<br>
									<input type="radio" name="product_type" value="Iphone"
									id="Iphone" />Iphone<br></td>
							</tr>
						</table>
					</center>

					
					<div style="display: none" id="l">
					
					<center>
					<table>
					<tr><td><font color="white">
					Memory: </font></td><td><input type="text" name="s_storage"
							onkeypress="return isNumberKey(event)" /></td>
					</tr>
					<tr>
				<td><font color="white">
						 Type: </font></td><td><input type="text" name="s_type" /><br></td>
					</tr>
					</table>
					</center>
					
					</div>
					
					<div style="display: none" id="d">
						<center>
							<table>
								<tr>
									<td><font color="white"> Memory: </font></td>
									<td><input type="text" name="n_storage" /></td>
								</tr>
								<tr>
									<td><font color="white"> Type: </font></td>
									<td><input type="text" name="n_type" /></td>
								</tr>
							</table>
						</center>

					</div>
					
					<div style="display: none" id="t">
					<center>
					<table>
					<tr><td><font color="white">
					Memory: </font></td><td><input type="text" name="i_storage"
							onkeypress="return isNumberKey(event)" /></td>
					</tr>
					<tr>
					<td><font color="white"> Type:</font></td><td> <input
							type="text" name="i_type" /></td>
					</table>
					</center>
						
					</div>
					<center>
					<table>
					<tr><td><font color="white"> 
					 Description:</font></td><td>
					<textarea style="width: 150px; height: 150px;"
						name="productDescription" /></textarea></td>
					</tr>
					<tr>
					<font color="white"> Auction End Date:</font>
					</tr>
					<tr><td><font color="white">
					Year:</font></td><td> <input type="text" name="year"
						onkeypress="return isNumberKey(event)"></td>
					</tr>
					
					<tr>
					<td><font color="white">
					 Month: </font></td><td><input
						type="text" name="month" onkeypress="return isNumberKey(event)"></td>
					</tr>
					<tr><td><font color="white">
					Day: </font></td><td><input type="text" name="day"
						onkeypress="return isNumberKey(event)"></td>
					</tr>
					
					<tr>
					
					
					<td><font color="white">
					 start
					price: </font></td><td><input type="text" name="price"
						onkeypress="return isNumberKey(event)" /></td>
					</tr>
					</table>
					</center>
					
					 <input
						type="submit" value="submit">

				</form>
			</div>
		</font>
	</center>
	<%
		if (request.getSession().getAttribute("qs") != null) {
			LinkedList<FaqEntity> qs = (LinkedList<FaqEntity>) request.getSession().getAttribute("qs");
			if (qs.size() != 0) {
				// ansered questions exist
	%>
	Feedback
	<br>&nbsp;
	<br>
	<%
		for (int i = 0; i < qs.size(); i++) {
	%>
	<div>
		<center style="width: 649px;">
			<font color="white">Your question: </font><font color="red"><%=qs.get(i).getQuestion()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font
				color="white">Feedback:</font><%=qs.get(i).getAns()%>
		</center>
	</div>
	<br>
	<%
		}
			} else {
	%>
	<center>
		<font color="white"> No response yet. </font>
	</center>
	<%
		}
		}
	%>
	<br>

	<script type="text/javascript">
		window.onload = function() {
			var a = document.getElementById('auction');
			a.onclick = initAuction;

			var l = document.getElementById('Sumsung');
			var d = document.getElementById('Nokia');
			var t = document.getElementById('Iphone');

			l.onclick = Sumsung;
			d.onclick = Nokia;
			t.onclick = Iphone;

			var checkAuction = document.getElementById('checkAuction');
			checkAuction.onclick = check;

			var logout = document.getElementById('logout');
			logout.onclick = killSession;

			var h = document.getElementById('checkAuction');
			h.onclick = history;
		}

		function history() {
			document.getElementById('d2').style.display = "block";
		}

		function initAuction() {
			document.getElementById('d1').style.display = "block";
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
			var p_name = document.forms["auction_form"]["product_name"].value;
			var p_type = document.forms["auction_form"]["product_type"].value;
			var p_desp = document.forms["auction_form"]["productDescription"].value;
			var p_date = document.forms["auction_form"]["end_date"].value;
			var p_price = document.forms["auction_form"]["price"].value;

			if (p_name == null || p_name == "", p_type == null || p_type == "",
					p.desp == null || p_desp == "", p_date == null
							|| p_date == "", p_price == null) {
				alert("Please fill all required field");
				return false;
			}
			return true;
		}

		function Sumsung() {
			document.getElementById('t').style.display = "none";
			document.getElementById('d').style.display = "none";
			document.getElementById('l').style.display = "block";
		}

		function Nokia() {
			document.getElementById('t').style.display = "none";
			document.getElementById('l').style.display = "none";
			document.getElementById('d').style.display = "block";
		}

		function Iphone() {
			document.getElementById('l').style.display = "none";
			document.getElementById('d').style.display = "none";
			document.getElementById('t').style.display = "block";
		}

		function check() {

		}

		function killSession() {
			request.getSession().invalidate();
		}

		function showOutAs() {
			document.getElementById('d3').style.display = "block";
		}

		function showUpAs() {
			document.getElementById('d4').style.display = "block";
		}
	</script>
</body>
</html>