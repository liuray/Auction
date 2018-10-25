package com.ebay.dao;

import java.sql.SQLException;
import java.util.LinkedList;

import com.ebay.entity.BuyerEntity;

public interface BuyerDao {
	public LinkedList<BuyerEntity> searchBuyer(String UserID)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;

	public void insertBuyer(BuyerEntity buyer)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;

	public LinkedList<BuyerEntity> getBuyer(String email, String password)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;
}
