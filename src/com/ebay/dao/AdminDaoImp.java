package com.ebay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.ebay.entity.AdminEntity;

public class AdminDaoImp implements AdminDao {
	ApplicationDAO applicationDao = new ApplicationDAO();

	public LinkedList<AdminEntity> getAdmin(String email, String password)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		LinkedList<AdminEntity> admins = new LinkedList<AdminEntity>();

		String getSql = "select * from Admin where Admin.UserID in"
				+ " (select u.UserID from User as u where u.email = '" + email + "' and u.password = '" + password
				+ "')";

		Connection dataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConnection.prepareStatement(getSql);

		int length = 0;
		ResultSet rs = pS.executeQuery();
		while (rs.next()) {
			length++;
			admins.add(new AdminEntity(rs.getString("UserID"), rs.getString("position")));
		}
		pS.close();
		dataBasesConnection.close();

		return admins;
	}

	public void insertAdmin(AdminEntity admin)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		String insertsql = "insert into Admin" + " (UserID, position) " + "values(?, ?)";
		Connection dataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConnection.prepareStatement(insertsql);
		pS.setString(1, admin.getUserID());
		pS.setString(2, admin.getPosition());
		System.out.println(" representative added");

		pS.executeUpdate();

		pS.close();
		dataBasesConnection.close();
	}
}
