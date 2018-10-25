package com.ebay.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebay.dao.AdminDaoImp;
import com.ebay.dao.ApplicationDAO;
import com.ebay.dao.UserDaoImp;
import com.ebay.entity.AdminEntity;
import com.ebay.entity.UserEntity;

/**
 * Servlet implementation class AdminRegisterServlet
 */
public class AdminRegisterServlet extends HttpServlet {
	
	UserDaoImp userDaoImp = new UserDaoImp();
	AdminDaoImp adminDaoImp = new AdminDaoImp();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String passWord = request.getParameter("password");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String Address = request.getParameter("streetAddr");
	
		
		Integer phoneNum = Integer.parseInt(phone);
		
		
		UserEntity userEntity = new UserEntity();
		String UserID = UUID.randomUUID().toString();
		userEntity.setUserID(UserID);
		userEntity.setFirstName(firstName);
		userEntity.setLastName(lastName);
	
		userEntity.setEmail(email);
		userEntity.setPassword(passWord);
		userEntity.setPhone(phoneNum);
		userEntity.setStreetAddr(Address);
		
		
		AdminEntity adminEntity = new AdminEntity();
		adminEntity.setUserID(UserID);
		adminEntity.setPosition("Customer representative");
		
		try {
			userDaoImp.insertUser(userEntity);
			adminDaoImp.insertAdmin(adminEntity);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/admin.jsp").forward(request, response);
	}
}
