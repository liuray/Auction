package com.ebay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.ebay.entity.SellerEntity;

public class SellerDaoImp implements SellerDao {
	ApplicationDAO applicationDao = new ApplicationDAO();

	public LinkedList<SellerEntity> searchSeller(String UserID)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		LinkedList<SellerEntity> list = new LinkedList<SellerEntity>();
		String selectSql = "select * from Seller as s where s.UserID = '" + UserID + "'";
		Connection dataBasesConne = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConne.prepareStatement(selectSql);
		int resLength = 0;
		ResultSet rs = pS.executeQuery();
		while (rs.next()) {
			resLength++;
			list.add(new SellerEntity(rs.getString("UserID"), rs.getString("Company_name")));
		}
		pS.close();
		dataBasesConne.close();

		return list;
	}

	public LinkedList<SellerEntity> getSeller(String email, String password)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		LinkedList<SellerEntity> listt = new LinkedList<SellerEntity>();

		String selectSql = "select * from Seller where Seller.UserID in "
				+ "(select u.UserID from User u where u.email = '" + email + "' and u.password = '" + password + "')";

		Connection dataBasesConne = applicationDao.getConnection();
		PreparedStatement ps = dataBasesConne.prepareStatement(selectSql);

		int length = 0;
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			length++;
			listt.add(new SellerEntity(rs.getString("UserID"), rs.getString("Company_name")));
		}
		ps.close();
		dataBasesConne.close();

		return listt;
	}

	public void insertSeller(SellerEntity seller)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		String insertSql = "insert into Seller" + " (UserID, Company_name) " + "values(?, ?)";
		Connection dataBasesConne = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConne.prepareStatement(insertSql);

		pS.setString(1, seller.getUserID());
		pS.setString(2, seller.getCompany_name());
		System.out.println("Seller added");

		pS.executeUpdate();

		pS.close();
		dataBasesConne.close();
	}
}
