package com.ebay.dao;

import java.sql.SQLException;
import java.util.LinkedList;

import com.ebay.entity.UserEntity;

public interface UserDao {
	public void insertUser(UserEntity user)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException;

	public LinkedList<UserEntity> searchUser(String UserID)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;

	public LinkedList<UserEntity> getUser(String email, String password)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException;
}
