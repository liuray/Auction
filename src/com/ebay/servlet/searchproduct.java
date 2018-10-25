package com.ebay.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebay.dao.ApplicationDAO;
import com.ebay.entity.AuctionEntity;
import com.ebay.entity.NokiaEntity;
import com.ebay.entity.SumsungEntity;
import com.ebay.entity.ProductEntity;
import com.ebay.entity.SellerEntity;
import com.ebay.entity.IphoneEntity;

/**
 * Servlet implementation class searchproduct
 */
@WebServlet("/searchproduct")
public class searchproduct extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		String productType=request.getParameter("sel");
		String productName=request.getParameter("product");
		String emptyprice;
		String largemin;
		
		if(request.getParameter("price-min").isEmpty()||request.getParameter("price-max").isEmpty()) {
			emptyprice="true";
			request.setAttribute("emptyprice", emptyprice);
			request.getRequestDispatcher("/search.jsp").forward(request,response);
		} else {
		double minprice=Double.parseDouble(request.getParameter("price-min"));
		double maxprice=Double.parseDouble(request.getParameter("price-max"));
		if(minprice>maxprice) {
			largemin="true";
			request.setAttribute("largemin", largemin);
			request.getRequestDispatcher("/search.jsp").forward(request,response);
		}
		
		ApplicationDAO dao = new ApplicationDAO();
		String searchType = null;
		if(productType.equals("Nokia")) {
			searchType="P_Nokia";
		} else if(productType.equals("Iphone")) {
			searchType="P_Iphone";
		} else if (productType.equals("Sumsung")) {
			searchType="P_Sumsung";
		} else {
			System.exit(-1);
		}
		try {
			Connection dbConnection=dao.getConnection();
			String searchsql="select * from Product p where p.P_id in (select P_id from "+searchType+")"
							+ " and p.P_name = '"+productName+"' ";
			PreparedStatement preparedStatement = dbConnection.prepareStatement(searchsql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs==null) {
				// if no product is found.
				String fail = "true";
				request.setAttribute("fail", fail);
				request.getRequestDispatcher("/search.jsp").forward(request,response);
			} else {
				// Find current time
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date current = new Date();
				dateFormat.format(current);
				
				LinkedList<String> pids = new LinkedList<String>();
				LinkedList<AuctionEntity> auctions=new LinkedList<AuctionEntity>();
				LinkedList<SellerEntity> sellers=new LinkedList<SellerEntity>();
				LinkedList<ProductEntity> products=new LinkedList<ProductEntity>();
				
				// all products with name mac
				while(rs.next()) {
					// look into table Auction with a given P_id
					String getTime="select * from Auction a where a.P_id = '"+rs.getString("P_id")+"'";
					preparedStatement = dbConnection.prepareStatement(getTime);
					ResultSet rs1=preparedStatement.executeQuery();
					rs1.next();
					Timestamp timestamp = rs1.getTimestamp("end_date");
					Date enddate = new java.util.Date(timestamp.getTime());
					Date enddatesql=new java.sql.Date(enddate.getTime());
					Double stprice=rs1.getDouble("start_price");
					if(current.before(enddate)&&minprice<=stprice&&maxprice>=stprice) {
						pids.add(rs.getString("P_id"));
						Timestamp timestamp1 = rs1.getTimestamp("post_date");
						Date postdate = new java.util.Date(timestamp1.getTime());
						Date postdatesql=new java.sql.Date(postdate.getTime());
						auctions.add(new AuctionEntity(rs1.getString("A_id"),rs1.getString("S_id"),rs1.getString("P_id"),(java.sql.Date)postdatesql, (java.sql.Date)enddatesql,rs1.getDouble("start_price")));
					}
				}
				if(pids.size()==0) {
					if(minprice<=maxprice) {
					String fail = "true";
					request.setAttribute("fail", fail);
					request.getRequestDispatcher("/search.jsp").forward(request,response);}
				} else {
					if(productType.equals("Sumsung")) {
						LinkedList<SumsungEntity> Sumsungs = new LinkedList<SumsungEntity>();
						for (int k=0;k<pids.size();k++) {
							String getinfo="select * from P_Sumsung a where a.P_id = '"+pids.get(k)+"'";
							preparedStatement = dbConnection.prepareStatement(getinfo);
							ResultSet info=preparedStatement.executeQuery();
							info.next();
							Sumsungs.add(new SumsungEntity(pids.get(k),info.getInt("S_storage_inGB"),info.getString("S_type")));
						}
						for (int i=0;i<pids.size();i++) {
							String getproducts="select * from Product a where a.P_id = '"+pids.get(i)+"'";
							preparedStatement = dbConnection.prepareStatement(getproducts);
							ResultSet info=preparedStatement.executeQuery();
							info.next();
							products.add(new ProductEntity(pids.get(i),info.getString("P_description"),info.getDouble("P_price"),info.getString("P_name")));
						}
						for (int j=0;j<pids.size();j++) {
							String getproducts="select * from Seller a where a.UserID = '"+auctions.get(j).getS_id()+"'";
							preparedStatement = dbConnection.prepareStatement(getproducts);
							ResultSet info=preparedStatement.executeQuery();
							info.next();
							sellers.add(new SellerEntity(info.getString("UserID"),info.getString("Company_name")));
						}
						request.setAttribute("result", Sumsungs);
						request.setAttribute("sellers", sellers);
						request.setAttribute("auctions", auctions);
						request.setAttribute("products",products);
						request.getRequestDispatcher("/search.jsp").forward(request,response);
					} else if(productType.equals("Iphone")) {
						LinkedList<IphoneEntity> Iphones = new LinkedList<IphoneEntity>();
						for (int k=0;k<pids.size();k++) {
							String getinfo="select * from P_Iphone a where a.P_id = '"+pids.get(k)+"'";
							preparedStatement = dbConnection.prepareStatement(getinfo);
							ResultSet info=preparedStatement.executeQuery();
							info.next();
							Iphones.add(new IphoneEntity(pids.get(k),info.getInt("I_storage_inGB"),info.getString("I_type")));
						}
						for (int i=0;i<pids.size();i++) {
							String getproducts="select * from Product a where a.P_id = '"+pids.get(i)+"'";
							preparedStatement = dbConnection.prepareStatement(getproducts);
							ResultSet info=preparedStatement.executeQuery();
							info.next();
							products.add(new ProductEntity(pids.get(i),info.getString("P_description"),info.getDouble("P_price"),info.getString("P_name")));
						}
						for (int j=0;j<pids.size();j++) {
							String getproducts="select * from Seller a where a.UserID = '"+auctions.get(j).getS_id()+"'";
							preparedStatement = dbConnection.prepareStatement(getproducts);
							ResultSet info=preparedStatement.executeQuery();
							info.next();
							sellers.add(new SellerEntity(info.getString("UserID"),info.getString("Company_name")));
						}
						request.setAttribute("result", Iphones);
						request.setAttribute("sellers", sellers);
						request.setAttribute("auctions", auctions);
						request.setAttribute("products",products);
						request.getRequestDispatcher("/search.jsp").forward(request,response);
					} else if(productType.equals("Nokia")) {
						LinkedList<NokiaEntity> Nokias = new LinkedList<NokiaEntity>();
						for (int k=0;k<pids.size();k++) {
							String getinfo="select * from P_Nokia a where a.P_id = '"+pids.get(k)+"'";
							preparedStatement = dbConnection.prepareStatement(getinfo);
							ResultSet info=preparedStatement.executeQuery();
							info.next();
							Nokias.add(new NokiaEntity(pids.get(k),info.getString("N_storage_inGB"),info.getString("N_type")));
						}
						for (int i=0;i<pids.size();i++) {
							String getproducts="select * from Product a where a.P_id = '"+pids.get(i)+"'";
							preparedStatement = dbConnection.prepareStatement(getproducts);
							ResultSet info=preparedStatement.executeQuery();
							info.next();
							products.add(new ProductEntity(pids.get(i),info.getString("P_description"),info.getDouble("P_price"),info.getString("P_name")));
						}
						for (int j=0;j<pids.size();j++) {
							String getproducts="select * from Seller a where a.UserID = '"+auctions.get(j).getS_id()+"'";
							preparedStatement = dbConnection.prepareStatement(getproducts);
							ResultSet info=preparedStatement.executeQuery();
							info.next();
							sellers.add(new SellerEntity(info.getString("UserID"),info.getString("Company_name")));
						}
						request.setAttribute("result", Nokias);
						request.setAttribute("sellers", sellers);
						request.setAttribute("auctions", auctions);
						request.setAttribute("products",products);
						request.getRequestDispatcher("/search.jsp").forward(request,response);
					} else {
					System.exit(-1);
					}
				}
			}
			preparedStatement.close();
			dbConnection.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	}
}