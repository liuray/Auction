package com.ebay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.ebay.entity.BuyerEntity;

public class BuyerDaoImp implements BuyerDao {
	ApplicationDAO applicationDao = new ApplicationDAO();

	public LinkedList<BuyerEntity> searchBuyer(String UserID)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		LinkedList<BuyerEntity> listt = new LinkedList<BuyerEntity>();
		String chooseSql = "select * from Buyer as b where b.UserID = '" + UserID + "'";
		Connection DataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = DataBasesConnection.prepareStatement(chooseSql);
		int length = 0;
		ResultSet rs = pS.executeQuery();
		while (rs.next()) {
			length++;
			listt.add(new BuyerEntity(rs.getString("UserID"), rs.getString("payment_method")));
		}
		pS.close();
		DataBasesConnection.close();

		return listt;
	}

	public void insertBuyer(BuyerEntity buyer)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
 
		String putSql = "insert into Buyer" + " (UserID, payment_method) " + "values(?, ?)";
		Connection dataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConnection.prepareStatement(putSql);
		pS.setString(1, buyer.getUserID());
		pS.setString(2, buyer.getPayment_method());
		System.out.println("Buyer added");

		pS.executeUpdate();

		pS.close();
		dataBasesConnection.close();
	}
	public LinkedList<BuyerEntity> getBuyer(String email, String password) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		LinkedList<BuyerEntity> listt = new LinkedList<BuyerEntity>();
		
		String chooseSql = "select * from Buyer where Buyer.UserID = "
				+ "(select u.UserID from User u where u.Email = '"+email+"' and u.password = '"+password+"')";
		
		Connection dataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConnection.prepareStatement(chooseSql);
		
		int length = 0;
		ResultSet rs = pS.executeQuery();
		while(rs.next()){
			length++;
			listt.add(new BuyerEntity(rs.getString("UserID"), rs.getString("payment_method")));
		}
		pS.close();
		dataBasesConnection.close();
		
		return listt;
	}
	

}
