package com.ebay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


import com.ebay.entity.BidEntity;
import com.ebay.entity.MBidEntity;

public class BidDaoImp {
	
	ApplicationDAO applicationDao = new ApplicationDAO();

	public LinkedList<BidEntity> searchBid(String aid) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		LinkedList<BidEntity> listt = new LinkedList<BidEntity>();
		String chooseSql = "select * from Bid as b where b.Auction_id = '"+aid+"'";
		Connection dataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConnection.prepareStatement(chooseSql);
		int length = 0;
		ResultSet rs = pS.executeQuery();
		while(rs.next()){
			listt.add(new BidEntity(rs.getString("Buyer_id"), rs.getString("Auction_id"), rs.getDouble("Current_price")));
		}
		pS.close();
		dataBasesConnection.close();
		
		return listt;
	}
	
	
	
	public LinkedList<BidEntity> searchBidByBuyerID(String BuyerID) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		LinkedList<BidEntity> listt = new LinkedList<BidEntity>();
		String chooseSql = "select * from Bid as b where b.Buyer_id = '"+BuyerID+"'";
		Connection dataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConnection.prepareStatement(chooseSql);
		int resLength = 0;
		ResultSet rs = pS.executeQuery();
		while(rs.next()){
			listt.add(new BidEntity(rs.getString("Buyer_id"), rs.getString("Auction_id"), rs.getDouble("Current_price")));
		}
		pS.close();
		dataBasesConnection.close();
		
		return listt;
	}
	
	public void UpdateBid(String bid, String aid, Double price) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		String Updatesql = "call bid_update ('"+aid+"', '"+bid+"', "+price+")";
		Connection dataBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = dataBasesConnection.prepareStatement(Updatesql);
		pS.execute();
		System.out.println(bid);
		System.out.println(aid);
		System.out.println("Bid updated");
		pS.close();
		dataBasesConnection.close();
	}
	
	public void UpdateMBid(String bid, String aid, Double price) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		String updateSql = "update Manually_bid set bid_price = '"+price+"' where Buyer_id = '"+bid+"' and Auction_id = '"+aid+"'";
		Connection DateBasesConnection = applicationDao.getConnection();
		PreparedStatement pS = DateBasesConnection.prepareStatement(updateSql);
		pS.executeUpdate();
		System.out.println("Manually bid updated");
		pS.close();
		DateBasesConnection.close();
	}
	
	
	
	
	
	public void insertBid(BidEntity bid) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		String sql = "call bid_insert ('"+bid.getAuction_id()+"', '"+bid.getBuyer_id()+"', "+bid.getCurrent_price()+")";
		Connection dbConnection = applicationDao.getConnection();
		PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
		System.out.println("Bid added");

		preparedStatement.execute();
		
		preparedStatement.close();
		dbConnection.close();
	}
	
	public void insertMBid(MBidEntity mbid) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		String insertsql = "insert into Manually_bid"
						+ " (Buyer_id, Auction_id, bid_price)"
						+ " values(?,?,?)";
		Connection dbConnection = applicationDao.getConnection();
		PreparedStatement preparedStatement = dbConnection.prepareStatement(insertsql);
		
		preparedStatement.setString(1, mbid.getBuyer_id());
		preparedStatement.setString(2, mbid.getAuction_id());
		preparedStatement.setDouble(3, mbid.getBid_price());
		System.out.println("Manually Bid added");
		
		preparedStatement.executeUpdate();
		
		preparedStatement.close();
		dbConnection.close();
	}
	
	
	
	public void deleteBid(String bid) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{

		String deleteSql="delete from Bid where Buyer_id = '"+bid+"'";

		Connection dataBasesConnection = applicationDao.getConnection();

		PreparedStatement pS = dataBasesConnection.prepareStatement(deleteSql);

		pS.execute();

		pS.close();

		dataBasesConnection.close();

	}
	
}
