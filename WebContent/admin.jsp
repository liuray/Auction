<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ebay.dao.ApplicationDAO"%>
<%@ page import="com.ebay.dao.UserDaoImp"%>
<%@ page import="com.ebay.dao.FaqDaoImp"%>

<%@ page import="com.ebay.entity.UserEntity"%>
<%@ page import="com.ebay.entity.FaqEntity"%>
<%@ page import="com.ebay.entity.AdminEntity"%>
<%@ page import="com.ebay.entity.SaleReportEntity"%>
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


	<%
		UserDaoImp userDaoImp = new UserDaoImp();
		ApplicationDAO dao = new ApplicationDAO();
		FaqDaoImp faqDaoImp = new FaqDaoImp();

		LinkedList<FaqEntity> q_list = faqDaoImp.getQuestion();
		if (q_list.size() == 0) {
			System.out.println("No question");
		}
	%>
	<h1>
		<font color="white">Welcome Admin ${adminName}</font>
	</h1>

	<div align="right" style="width: 807px; height: 88px;">
		<a id="logout" href="<%=request.getContextPath()%>/logout.jsp">logout</a>
	</div>
	<br>

	<%
		// only boss(Admin) can see this
		AdminEntity admin = (AdminEntity) request.getSession().getAttribute("admin");
		String position = admin.getPosition();
		if (position.equals("boss")) {
	%>
	<center>
		<br> <a id="signUp"
			href="<%=request.getContextPath()%>/adminSignUp.jsp">Sign up a
			customer representative account</a>
	</center>
	<br>
	<%
		}
	%>
	<center>
		<br> <a id="rAuction"
			href="<%=request.getContextPath()%>/removeAuction.jsp"> manage
			live auctions</a>
	</center>
	<br>
	<center>
		<input type="button" onclick="saleReport()" value="sale report" />
	</center>
	<%
		SaleReportEntity report = dao.generateReport();
		Double totalEarning = report.getTotalEarning();
		Double earnpitem = report.getEarnpitem();
		Double earntyped = report.getEarntyped();
		Double earntypet = report.getEarntypet();
		Double earntypel = report.getEarntypel();
		Double earnpseller = report.getEarnpseller();
		String bsi = report.getBsi();
		String bb = report.getBb();
	%>
	<center>
		<div id="reportDIV" style="display: none">
			<table>
				<tr>
					<td><font color="#ffffff">Total earning</font></td>
					<td><font color="#ffffff"><%=totalEarning%></font></td>
				</tr>

				<tr>
					<td><font color="#ffffff">Earn per item</font></td>
					<td><font color="#ffffff"><%=earnpitem%></font></td>
				</tr>

				<tr>
					<td><font color="#ffffff">Nokia Earning</font></td>
					<td><font color="#ffffff"><%=earntyped%></font></td>
				</tr>

				<tr>
					<td><font color="#ffffff">Iphone Earning</font></td>
					<td><font color="#ffffff"><%=earntypet%></font></td>
				</tr>

				<tr>
					<td><font color="#ffffff">Sumsung Earning</font></td>
					<td><font color="#ffffff"><%=earntypel%></font></td>
				</tr>

				<tr>
					<td><font color="#ffffff">Earn per seller</font></td>
					<td><font color="#ffffff"><%=earnpseller%></font></td>
				</tr>

				<tr>
					<td><font color="#ffffff">Best Seller Item</font></td>
					<td><font color="#ffffff"><%=bsi%></font></td>
				</tr>

				<tr>
					<td><font color="#ffffff">Best Buyer</font></td>
					<td><font color="#ffffff"><%=bb%></font></td>
				</tr>
			</table>
		</div>
	</center>
<br>
<br>
	<%
		
	%>
	<font color="white">
		<center>
			<table>
				<tr>
					<th>Customer type</th>
					<th>Customer firstName</th>
					<th>Question</th>
					<th>answer</th>
				</tr>
				<%
					for (int i = 0; i < q_list.size(); i++) {
						FaqEntity q = q_list.get(i);
						String B_id = q.getB_id();
						String S_id = q.getS_id();
						if (B_id.equals("buyer")) {
							// a seller question
							UserEntity seller = userDaoImp.searchUser(S_id).peek();
				%>
				<tr>
					<td>Seller</td>
					<td><%=seller.getFirstName()%></td>
					<td><%=q.getQuestion()%></td>
					<td>
						<div id="ans<%=i%>">
							<input type="button" name="answer" value="submit response"
								onclick="answer<%=i%>()" /><br>
						</div>
						<div id="<%=i%>" style="display: none">
							<form method="post" action="ans" onsubmit="return valid<%=i%>()"
								name="form<%=i%>">
								<input type="hidden" name="questionID" value="<%=q.getQ_id()%>">
								<textarea id="text<%=i%>" name="ans"></textarea>
								<br> <input type="submit" value="submit" />
							</form>
						</div> <script type="text/javascript">
		function answer<%=i%>(){
			document.getElementById('ans<%=i%>').style.display = "none";
			document.getElementById('<%=i%>').style.display = "block";
		}
		
		function valid<%=i%>(){
			var text = document.getElementById('text<%=i%>').value;
			if(text == ""){
				alert("answer cannot be empty");
				return false;
			}
			return true;
		}
	</script>
					</td>
				</tr>
				<%
					} else if (S_id.equals("seller")) {
							// a buyer question
							UserEntity buyer = userDaoImp.searchUser(B_id).peek();
				%>
				<tr>
					<td>Buyer</td>
					<td><%=buyer.getFirstName()%></td>
					<td><%=q.getQuestion()%></td>
					<td>
						<div id="ans<%=i%>">
							<input type="button" name="answer" value="submit response"
								onclick="answer<%=i%>()" /><br>
						</div>
						<div id="<%=i%>" style="display: none">
							<form method="post" action="ans" onsubmit="return valid<%=i%>()"
								name="form<%=i%>">
								<input type="hidden" name="questionID" value="<%=q.getQ_id()%>">
								<textarea id="text<%=i%>" name="ans"></textarea>
								<br> <input type="submit" value="submit" />
							</form>
						</div> <script type="text/javascript">
		function answer<%=i%>(){
			document.getElementById('ans<%=i%>').style.display = "none";
			document.getElementById('<%=i%>').style.display = "block";
		}
		
		function valid<%=i%>(){
			var text = document.getElementById('text<%=i%>').value;
			if(text == ""){
				alert("answer cannot be empty");
				return false;
			}
			return true;
		}
	</script>
					</td>
				</tr>
				<%
					}
					}
				%>
			</table>
		</center>
	</font>
	<script type="text/javascript">
window.onload = function(){
	var logout = document.getElementById('logout');
	logout.onclick = killSession;	
}
function killSession(){
	request.getSession().invalidate();
}

function saleReport(){
	document.getElementById('reportDIV').style.display = "block";
}
</script>
</body>
</html>