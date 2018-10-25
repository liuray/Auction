package com.ebay.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebay.dao.ApplicationDAO;
import com.ebay.dao.BuyerDao;
import com.ebay.dao.BuyerDaoImp;
import com.ebay.dao.FaqDaoImp;
import com.ebay.entity.FaqEntity;
import com.ebay.entity.UserEntity;

/**
 * Servlet implementation class FaqServlet
 */
public class FaqServlet extends HttpServlet {
	FaqDaoImp faqDaoImp = new FaqDaoImp();

	BuyerDaoImp buyerDao = new BuyerDaoImp();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		FaqEntity faq = new FaqEntity();
		ApplicationDAO dao = new ApplicationDAO();
		String question = request.getParameter("question");
		UserEntity User = (UserEntity)request.getSession().getAttribute("User");
		if(User == null){
			System.out.println("no user");
		}
		String UserID = User.getUserID();
		faq.setQuestion(question);
		
		String Q_id = UUID.randomUUID().toString();
		faq.setQ_id(Q_id);
		try {
			if(buyerDao.searchBuyer(UserID).size() != 0){
				// this is a buyer
				System.out.println(UserID);
				faq.setB_id(UserID);
				faq.setS_id("seller");
				// set admin_id to default first, which is 1 
				faq.setAdmin_id("boss");
				faq.setAns("Not answered");
				try {
					faqDaoImp.insertFAQ(faq);
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
			}else{
				System.out.println(UserID);
				// this is a seller
				faq.setS_id(UserID);
				faq.setB_id("buyer");
				// set admin_id to default first, which is 1 
				faq.setAdmin_id("boss");
				faq.setAns("Not answered");
				try {
					faqDaoImp.insertFAQ(faq);
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
				request.getRequestDispatcher("/sellerPage.jsp").forward(request, response);
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
