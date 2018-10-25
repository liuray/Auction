package com.ebay.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebay.dao.ApplicationDAO;
import com.ebay.dao.BuyerDaoImp;
import com.ebay.dao.SellerDaoImp;
import com.ebay.dao.UserDaoImp;
import com.ebay.entity.BuyerEntity;
import com.ebay.entity.SellerEntity;
import com.ebay.entity.UserEntity;

/**
 * Servlet implementation class SignUpServlet
 */
public class SignUpServlet extends HttpServlet {
	UserDaoImp userDaoImp = new UserDaoImp();
	BuyerDaoImp buyerDaoImp = new BuyerDaoImp();
	SellerDaoImp sellerDapImp =  new SellerDaoImp();
	public static boolean isNumeric(String str){
		for(char c : str.toCharArray()){
			if(!Character.isDigit(c)){
				return false;
			}
		}
		return true;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ApplicationDAO dao = new ApplicationDAO();
		UserEntity user = new UserEntity();
		
		request.setCharacterEncoding("utf-8");
		
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String passWord = request.getParameter("password");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("streetAddr");
		String identity = request.getParameter("identity");
		
		if(lastName == "" || firstName == "" || passWord == "" || email == "" 
				|| phone == "" || address == "" || identity == ""){
			String hasNull = "true";
			request.setAttribute("hasNull", hasNull);
			request.getRequestDispatcher("signUp.jsp").forward(request, response);		
		}else if(!isNumeric(phone)){
			String PhoneNum = "PhoneNum";
			request.setAttribute("PhoneNum", PhoneNum);
			request.getRequestDispatcher("signUp.jsp").forward(request, response);
		}
		else{
		
			int phoneNumber = Integer.parseInt(phone);
			
			user.setLastName(lastName);
			user.setFirstName(firstName);
			user.setPassword(passWord);
			user.setEmail(email);
			user.setPhone(phoneNumber);
			user.setStreetAddr(address);
			
			
			String userID = UUID.randomUUID().toString();
			user.setUserID(userID);
			
			try {
				userDaoImp.insertUser(user);
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
			
			if(identity.equals("buyer")){
				String paymentMethod = request.getParameter("payment_method");
				BuyerEntity buyer = new BuyerEntity(userID, paymentMethod);
				try {
					buyerDaoImp.insertBuyer(buyer);
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
				request.getSession().setAttribute("Buyer", user);
				request.getRequestDispatcher("/buyer.jsp").forward(request, response);
			}else if(identity.equals("seller")){
				String companyName = request.getParameter("company_name");
				SellerEntity seller = new SellerEntity(userID, companyName);
				try {
					sellerDapImp.insertSeller(seller);
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
				request.getSession().setAttribute("Seller", user);
				request.getRequestDispatcher("/sellerPage.jsp").forward(request, response);
			}
		}
	}
}
