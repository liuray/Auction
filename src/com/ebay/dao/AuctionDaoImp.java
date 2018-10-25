package com.ebay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import com.ebay.entity.AuctionEntity;

public class AuctionDaoImp implements AuctionDao{
	ApplicationDAO applicationDao = new ApplicationDAO();

	public LinkedList<AuctionEntity> searchAuction(String UserID) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		LinkedList<AuctionEntity> as = new LinkedList<AuctionEntity>();
		String insterSqlll = null;
		insterSqlll = "select * from Auction as a where a.S_id = '"+UserID+"'";		
		Connection dataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConnection.prepareStatement(insterSqlll);
		int length = 0;
		ResultSet rs = pS.executeQuery();
		while(rs.next()){
			length++;
			as.add(new AuctionEntity(rs.getString("A_id"), rs.getString("S_id"), rs.getString("P_id"),
					rs.getDate("post_date"), rs.getDate("end_date"), rs.getDouble("start_price")));
		}
		pS.close();
		dataBasesConnection.close();
		
		return as;
	}
	
	public LinkedList<AuctionEntity> searchAllAuction() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		LinkedList<AuctionEntity> listt = new LinkedList<AuctionEntity>();
		String selectSql = null;
		selectSql = "select * from Auction ";		
		Connection dataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConnection.prepareStatement(selectSql);
		int length = 0;
		ResultSet rs = pS.executeQuery();
		while(rs.next()){
			length++;
			listt.add(new AuctionEntity(rs.getString("A_id"), rs.getString("S_id"), rs.getString("P_id"),
					rs.getDate("post_date"), rs.getDate("end_date"), rs.getDouble("start_price")));
		}
		pS.close();
		dataBasesConnection.close();
		
		return listt;
	}
	
	public LinkedList<AuctionEntity> getAllUpToDateAuctions() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		LinkedList<AuctionEntity> listt = new LinkedList<AuctionEntity>();
		String chooseSql = "select * from Auction";
		Connection dataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConnection.prepareStatement(chooseSql);
		int length = 0;
		ResultSet rs = pS.executeQuery();
		while(rs.next()){
			length++;
			Date end_date = (java.util.Date)rs.getDate("end_date");
			Date now = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			dateFormat.format(now);
			dateFormat.format(end_date);
			if(end_date.after(now)){
				listt.add(new AuctionEntity(rs.getString("A_id"), rs.getString("S_id"), rs.getString("P_id"),
						rs.getDate("post_date"), rs.getDate("end_date"), rs.getDouble("start_price")));
			}
		}
		return listt;
	}
	
	public LinkedList<AuctionEntity> searchAuctionById(String auctionID) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		LinkedList<AuctionEntity> as = new LinkedList<AuctionEntity>();
		String chooseSql = null;
		chooseSql = "select * from Auction as a where a.A_id = '"+auctionID+"'";		
		Connection dataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConnection.prepareStatement(chooseSql);
		int length = 0;
		ResultSet rs = pS.executeQuery();
		while(rs.next()){
			length++;
			as.add(new AuctionEntity(rs.getString("A_id"), rs.getString("S_id"), rs.getString("P_id"),
					rs.getDate("post_date"), rs.getDate("end_date"), rs.getDouble("start_price")));
		}
		pS.close();
		dataBasesConnection.close();
		
		return as;
	}
	
	public void insertAuction(AuctionEntity auction) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		String insertsql = "insert into Auction"
						+ " (A_id, S_id, P_id, post_date, end_date, start_price)"
						+ " values(?,?,?,?,?,?)";
		Connection dataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConnection.prepareStatement(insertsql);
		
		pS.setString(1, auction.getA_id());
		pS.setString(2, auction.getS_id());
		pS.setString(3, auction.getP_id());
		pS.setDate(4, auction.getPost_date());
		pS.setDate(5, auction.getEnd_date());
		pS.setDouble(6, auction.getStart_price());
		System.out.println("Auction added");
		
		pS.executeUpdate();
		
		pS.close();
		dataBasesConnection.close();
	}
	
	
	

	
	public void deleteAuction(String aid, String sid, String pid) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		String deleteSql = " delete from Auction where A_id = '"+aid+"' and S_id = '"+sid+"'";
		Connection dataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConnection.prepareStatement(deleteSql);
		pS.execute();
		pS.close();
		dataBasesConnection.close();
	}
}
