package com.ebay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.ebay.entity.FaqEntity;

public class AnsDaoImp implements AnswersDao{
	ApplicationDAO applicationDao = new ApplicationDAO();
	BuyerDaoImp buyerDapImp = new BuyerDaoImp();
	public LinkedList<FaqEntity> searchAns(String UserID) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		LinkedList<FaqEntity> qs = new LinkedList<FaqEntity>();
		String insterSqllllll = null;
		if(buyerDapImp.searchBuyer(UserID).size() == 0){
			// seller
			insterSqllllll = "select * from FAQ as q where q.S_id = '"+UserID+"' "
														+ "and q.ans != 'Not answered'";
		}else{
			// buyer
			insterSqllllll = "select * from FAQ as q where q.B_id = '"+UserID+"' "
														+ "and q.ans != 'Not answered'";
		}
		Connection dataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConnection.prepareStatement(insterSqllllll);
		int length = 0;
		ResultSet rs = pS.executeQuery();
		while(rs.next()){
			length++;
			qs.add(new FaqEntity(rs.getString("Q_id"), rs.getString("B_id"), rs.getString("S_id"), rs.getString("Admin_id"),
					rs.getString("question"), rs.getString("ans")));
		}
		pS.close();
		dataBasesConnection.close();
		return qs;
	}
}
