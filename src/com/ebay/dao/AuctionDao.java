package com.ebay.dao;

import java.sql.SQLException;
import java.util.LinkedList;

import com.ebay.entity.AuctionEntity;

public interface AuctionDao {
	public LinkedList<AuctionEntity> searchAuction(String UserID) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;
		public LinkedList<AuctionEntity> searchAllAuction() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;
			public LinkedList<AuctionEntity> getAllUpToDateAuctions() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;
				public LinkedList<AuctionEntity> searchAuctionById(String auctionID) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;
					public void insertAuction(AuctionEntity auction) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;
						public void deleteAuction(String aid, String sid, String pid) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException;

}
