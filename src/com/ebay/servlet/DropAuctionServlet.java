package com.ebay.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebay.dao.ApplicationDAO;
import com.ebay.dao.AuctionDaoImp;
import com.ebay.dao.ProductDaoImp;

/**
 * Servlet implementation class DropAuctionServlet
 */
public class DropAuctionServlet extends HttpServlet {
	ProductDaoImp productDaoImp = new ProductDaoImp();
	AuctionDaoImp auctionDaoImp = new AuctionDaoImp();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String aid = request.getParameter("aid");
		String sid = request.getParameter("sid");
		String pid = request.getParameter("pid");
		ApplicationDAO dao = new ApplicationDAO();	
		try {
			auctionDaoImp.deleteAuction(aid, sid, pid);
			productDaoImp.deleteProduct(pid);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/sellerPage.jsp").forward(request, response);;
	}
}
