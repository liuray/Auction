package com.ebay.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebay.dao.ApplicationDAO;
import com.ebay.dao.BidDaoImp;
import com.ebay.entity.UserEntity;

/**
 * Servlet implementation class UpdateBidServlet
 */
public class UpdateBidServlet extends HttpServlet {
	
	BidDaoImp bidDaoImp = new BidDaoImp();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String newPrice = request.getParameter("updatePrice");
		Double new_price = Double.parseDouble(newPrice);
		UserEntity buyer = (UserEntity) request.getSession().getAttribute("Buyer");
		String bid = buyer.getUserID();
		String aid = request.getParameter("thisAuction");
		ApplicationDAO dao = new ApplicationDAO();
		try {
			bidDaoImp.UpdateBid(bid, aid, new_price);
			bidDaoImp.UpdateMBid(bid, aid, new_price);
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
		request.getRequestDispatcher("/buyer.jsp").forward(request, response);
	}

}
