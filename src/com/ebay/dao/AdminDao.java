package com.ebay.dao;

import java.sql.SQLException;
import java.util.LinkedList;

import com.ebay.entity.AdminEntity;

public interface AdminDao {
	public LinkedList<AdminEntity> getAdmin(String email, String password) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;
		public void insertAdmin(AdminEntity admin) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;

}
