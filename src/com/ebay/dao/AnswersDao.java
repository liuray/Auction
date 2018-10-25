package com.ebay.dao;

import java.sql.SQLException;
import java.util.LinkedList;

import com.ebay.entity.FaqEntity;

public interface AnswersDao {
	public LinkedList<FaqEntity> searchAns(String UserID) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;
}
