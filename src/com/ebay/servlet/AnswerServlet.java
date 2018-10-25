package com.ebay.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebay.dao.ApplicationDAO;
import com.ebay.dao.FaqDaoImp;
import com.ebay.entity.AdminEntity;

/**
 * Servlet implementation class AnsServlet
 */
public class AnswerServlet extends HttpServlet {
	FaqDaoImp faqDaoImp = new FaqDaoImp();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		String answer = request.getParameter("ans");
		String questionID = request.getParameter("questionID");
		
		AdminEntity adminEntity = (AdminEntity) request.getSession().getAttribute("admin");
		String position = adminEntity.getPosition();
		String adminID = adminEntity.getUserID();
		if(!position.equals("boss")){
			// this is a customer representative
			try {
				faqDaoImp.UpdateFaqForCR(adminID, answer, questionID);
				request.getRequestDispatcher("/admin.jsp").forward(request, response);
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
		}else{
			try {
				faqDaoImp.UpdateFaq(answer, questionID);
				//request.setAttribute("", );
				request.getRequestDispatcher("/admin.jsp").forward(request, response);
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
