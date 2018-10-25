package com.ebay.dao;

import java.sql.SQLException;
import java.util.LinkedList;

import com.ebay.entity.SellerEntity;

public interface SellerDao {
	public LinkedList<SellerEntity> searchSeller(String UserID)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;

	public LinkedList<SellerEntity> getSeller(String email, String password)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;

	public void insertSeller(SellerEntity seller)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;

}
