package com.ebay.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebay.dao.ApplicationDAO;
import com.ebay.dao.AuctionDaoImp;
import com.ebay.dao.ProductDaoImp;
import com.ebay.entity.AuctionEntity;
import com.ebay.entity.NokiaEntity;
import com.ebay.entity.SumsungEntity;
import com.ebay.entity.ProductEntity;
import com.ebay.entity.SandP;
import com.ebay.entity.IphoneEntity;
import com.ebay.entity.UserEntity;

/**
 * Servlet implementation class AuctionServlet
 */
public class AuctionServlet extends HttpServlet {
	ProductDaoImp productDaoImp = new ProductDaoImp();
	AuctionDaoImp auctionDaoImp = new AuctionDaoImp();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		ProductEntity product = new ProductEntity();
		String productName = request.getParameter("product_name");
		String productDescription = request.getParameter("productDescription");
		
		String Productprice = request.getParameter("price");
		double price = Double.parseDouble(Productprice);
		
		String P_id = UUID.randomUUID().toString();
		product.setP_description(productDescription);
		product.setP_id(P_id);
		product.setP_name(productName);
		product.setP_price(price);
		
		ApplicationDAO dao = new ApplicationDAO();
		try {
			productDaoImp.insertProduct(product);
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
		
		UserEntity Seller = (UserEntity)request.getSession().getAttribute("Seller");
		String S_id = Seller.getUserID(); 
		
		SandP suppereeee = new SandP();
		suppereeee.setP_id(P_id);
		suppereeee.setS_id(S_id);
		try {
			dao.insertSandP(suppereeee);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String type = request.getParameter("product_type");
		if(type.equals("Iphone")){
			String i_storage = request.getParameter("i_storage");
			int storage = Integer.parseInt(i_storage);
			String i_type = request.getParameter("i_type");
			
			IphoneEntity Iphone = new IphoneEntity();
			Iphone.setP_id(P_id);
			Iphone.setI_storage_inGB(storage);
			Iphone.setI_type(i_type);
			try {
				productDaoImp.insertIphone(Iphone);
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
			
		}else if(type.equals("Nokia")){
			String n_storage = request.getParameter("n_storage");
			String n_type = request.getParameter("n_type");
			
			NokiaEntity Nokia = new NokiaEntity();
			Nokia.setP_id(P_id);
			Nokia.setN_storage_inGB(n_storage);
			Nokia.setN_type(n_type);
			
			try {
				productDaoImp.insertNokia(Nokia);
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
			String s_storage = request.getParameter("s_storage");
			int sto = Integer.parseInt(s_storage);
			String s_type = request.getParameter("s_type");
			
			SumsungEntity Sumsung = new SumsungEntity();
			Sumsung.setP_id(P_id);
			Sumsung.setS_storage_inGB(sto);
			Sumsung.setS_type(s_type);
			
			try {
				productDaoImp.insertSumsung(Sumsung);
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
		// Get current time
		DateFormat currentDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date currentTime = new Date();
		System.out.println(currentDateFormat.format(currentTime));
		java.sql.Date post_date = new java.sql.Date(currentTime.getTime());
		
		//get data from jsp
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String end_date = year + "-" + month + "-" + day;
		System.out.println(end_date);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		AuctionEntity auction = new AuctionEntity();
		try {
			Date endDate = format.parse(end_date);
			java.sql.Date EndDate = new java.sql.Date(endDate.getTime());
			auction.setEnd_date(EndDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		String A_id = UUID.randomUUID().toString();
		

		auction.setA_id(A_id);
		auction.setP_id(P_id);
		auction.setS_id(S_id);
		auction.setStart_price(price);
		auction.setPost_date(post_date);
		
		try {
			auctionDaoImp.insertAuction(auction);
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
}
