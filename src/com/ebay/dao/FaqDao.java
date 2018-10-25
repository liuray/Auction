package com.ebay.dao;

import java.sql.SQLException;
import java.util.LinkedList;

import com.ebay.entity.FaqEntity;

public interface FaqDao {
	public LinkedList<FaqEntity> searchFaq(String q_id)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;

	public void UpdateFaq(String ans, String q_id)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;

	public void UpdateFaqForCR(String adminID, String ans, String q_id)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;

}
