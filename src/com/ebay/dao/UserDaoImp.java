package com.ebay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.ebay.entity.UserEntity;

public class UserDaoImp implements UserDao {
	
	ApplicationDAO applicationDao = new ApplicationDAO();

	public void insertUser(UserEntity user) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
		String insertSql = "insert into User"
						 + " (UserID, lastName, firstName, password, email, phone, streetAddr) "
						 + "values(?, ?, ?, ?, ?, ?, ?)";

		Connection dataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConnection.prepareStatement(insertSql);
		pS.setString(1, user.getUserID());
		pS.setString(2, user.getLastName());
		pS.setString(3, user.getFirstName());
		pS.setString(4, user.getPassword());
		pS.setString(5, user.getEmail());
		pS.setInt(6, user.getPhone());
		pS.setString(7, user.getStreetAddr());
		
		
		System.out.println("User added");
		
		pS.executeUpdate();
		
		pS.close();
		dataBasesConnection.close();
	}

	
	public LinkedList<UserEntity> searchUser(String UserID) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		LinkedList<UserEntity> listt = new LinkedList<UserEntity>();
		String selectSql = "select * from User as u where u.UserID = '"+UserID+"'";
		Connection databaseConnection = applicationDao.getConnection();
		PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
		int legnth = 0;
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			legnth++;
			listt.add(new UserEntity(rs.getString("UserID"), rs.getString("lastName"), rs.getString("firstName"), 
					rs.getString("password"), rs.getString("email"), rs.getInt("phone"), 
					rs.getString("streetAddr")));
		}
		preparedStatement.close();
		databaseConnection.close();
		
		return listt;
	}
	
	
	public LinkedList<UserEntity> getUser(String email, String password) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
		LinkedList<UserEntity> list = new LinkedList<UserEntity>();
		
		String selectSql = "select * from User as u where u.email = '"+email+"' and u.password = '"+password+"'";
		
		Connection dateBaseConnection = applicationDao.getConnection();
		PreparedStatement pS = dateBaseConnection.prepareStatement(selectSql);
		
		int resLength = 0;
		ResultSet rs = pS.executeQuery();
		
		while(rs.next()){
			resLength++;
			list.add(new UserEntity(rs.getString("UserID"), rs.getString("lastName"), rs.getString("firstName"), 
									rs.getString("password"), rs.getString("email"), rs.getInt("phone"), 
									rs.getString("streetAddr")));
		}
		
		pS.close();
		dateBaseConnection.close();
		
		return list;
	}


}
