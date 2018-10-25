package com.ebay.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebay.dao.AdminDaoImp;
import com.ebay.dao.AnsDaoImp;
import com.ebay.dao.BuyerDaoImp;
import com.ebay.dao.SellerDaoImp;
import com.ebay.dao.UserDaoImp;
import com.ebay.entity.AdminEntity;
import com.ebay.entity.FaqEntity;
import com.ebay.entity.UserEntity;


public class HomeServlet extends HttpServlet {
	UserDaoImp  userDaoImp = new UserDaoImp();
	BuyerDaoImp buyerDaoImp = new BuyerDaoImp();
	AnsDaoImp ansDao = new AnsDaoImp();
	SellerDaoImp sellerDaoImp = new SellerDaoImp();
	AdminDaoImp adminDaoImp = new AdminDaoImp();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		String email = request.getParameter("userEmail");
		String password = request.getParameter("userpPassword");
		
	LinkedList<UserEntity> list = new LinkedList<UserEntity>();
		
		// get the corresponding user according to email and password
		try {
			list = userDaoImp.getUser(email, password);
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
		
		if(list.size() == 0){
			// either user name or password is wrong or this user doesn't exist
			String fail = "true";
			request.setAttribute("done", fail);
			request.getRequestDispatcher("/home.jsp").forward(request,response);
		}else{
			// user information is confirmed
			request.getSession().setAttribute("User", list.peek());
			try {
				if(buyerDaoImp.getBuyer(email, password).size() != 0){
					// check answered questions for this buyer
					String UserID = buyerDaoImp.getBuyer(email, password).peek().getUserID();
					LinkedList<FaqEntity> qs = ansDao.searchAns(UserID);
					request.getSession().setAttribute("qs", qs);
					request.getSession().setAttribute("Buyer", userDaoImp.getUser(email, password).peek());
					request.getRequestDispatcher("/buyer.jsp").forward(request, response);
				}else if(sellerDaoImp.getSeller(email, password).size() != 0){
					// check answered questions for this seller
					String UserID = sellerDaoImp.getSeller(email, password).peek().getUserID();
					LinkedList<FaqEntity> qs = ansDao.searchAns(UserID);
					// check auctions of this seller
					request.getSession().setAttribute("qs", qs);
					request.getSession().setAttribute("Seller", userDaoImp.getUser(email, password).peek());
					request.getRequestDispatcher("/sellerPage.jsp").forward(request, response);
				}else{
					LinkedList<AdminEntity> admins = adminDaoImp.getAdmin(email, password);
					AdminEntity admin = admins.peek();
					String adminName = list.peek().getFirstName();
					request.getSession().setAttribute("adminName", adminName);
					request.getSession().setAttribute("admin", admin);
					request.getRequestDispatcher("/admin.jsp").forward(request, response);
				}
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
		}
	}
}
