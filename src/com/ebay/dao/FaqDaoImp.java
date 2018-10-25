package com.ebay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.ebay.entity.FaqEntity;

public class FaqDaoImp implements FaqDao {
	ApplicationDAO applicationDao = new ApplicationDAO();

	public LinkedList<FaqEntity> searchFaq(String q_id)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		LinkedList<FaqEntity> listt = new LinkedList<FaqEntity>();
		String chooseSql = "select * from FAQ as q where q.Q_id = '" + q_id + "'";
		Connection dataBaseConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBaseConnection.prepareStatement(chooseSql);
		int length = 0;
		ResultSet rs = pS.executeQuery();
		while (rs.next()) {
			length++;
			listt.add(new FaqEntity(rs.getString("Q_id"), rs.getString("B_id"), rs.getString("S_id"),
					rs.getString("Admin_id"), rs.getString("question"), rs.getString("ans")));
		}
		pS.close();
		dataBaseConnection.close();
		return listt;
	}

	public void UpdateFaq(String ans, String q_id)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		String updataSql = "update FAQ set ans = '" + ans + "' where Q_id = '" + q_id + "'";
		Connection dataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConnection.prepareStatement(updataSql);
		pS.executeUpdate();
		pS.close();
		dataBasesConnection.close();
	}

	public void UpdateFaqForCR(String adminID, String ans, String q_id)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		String sql = "update FAQ set ans = '" + ans + "', Admin_id = '" + adminID + "' where Q_id = '" + q_id + "'";
		Connection DataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = DataBasesConnection.prepareStatement(sql);
		pS.executeUpdate();
		pS.close();
		DataBasesConnection.close();
	}

	public LinkedList<FaqEntity> getQuestion()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		LinkedList<FaqEntity> listt = new LinkedList<FaqEntity>();
		String chooseSql = "select * from FAQ as f " + " where f.Q_id in (" + " select Q_id from FAQ as f2"
				+ " where f2.ans = 'Not answered') ";
		Connection dataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConnection.prepareStatement(chooseSql);
		int length = 0;
		ResultSet rs = pS.executeQuery();
		while (rs.next()) {
			length++;
			listt.add(new FaqEntity(rs.getString("Q_id"), rs.getString("B_id"), rs.getString("S_id"),
					rs.getString("Admin_id"), rs.getString("question"), rs.getString("ans")));
		}
		pS.close();
		dataBasesConnection.close();

		return listt;
	}

	public void insertFAQ(FaqEntity faq)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		String insertsql = "insert into FAQ" + " (Q_id, B_id, S_id, Admin_id, question, ans)" + " values(?,?,?,?,?,?)";
		Connection dateBaseConnection = applicationDao.getConnection();
		PreparedStatement pS = dateBaseConnection.prepareStatement(insertsql);

		pS.setString(1, faq.getQ_id());
		pS.setString(2, faq.getB_id());
		pS.setString(3, faq.getS_id());
		pS.setString(4, faq.getAdmin_id());
		pS.setString(5, faq.getQuestion());
		pS.setString(6, faq.getAns());
		pS.executeUpdate();
		System.out.println("Question added");
		pS.close();
		dateBaseConnection.close();
	}
}
