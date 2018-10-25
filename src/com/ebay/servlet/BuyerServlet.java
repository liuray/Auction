package com.ebay.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebay.dao.ApplicationDAO;
import com.ebay.dao.BidDaoImp;
import com.ebay.entity.BidEntity;
import com.ebay.entity.MBidEntity;
import com.ebay.entity.UserEntity;

/**
 * Servlet implementation class BidServlet
 */
public class BuyerServlet extends HttpServlet {
	
	BidDaoImp bidDaoImp = new BidDaoImp();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String manumPrice = request.getParameter("manumPrice");
		Double bidPrice = Double.parseDouble(manumPrice);
		
		UserEntity buyer = (UserEntity)request.getSession().getAttribute("Buyer");
		String BuyerID = buyer.getUserID();
		
		String auctionID = request.getParameter("auctionID");
		
		
		BidEntity bid = new BidEntity();
		bid.setBuyer_id(BuyerID);
		bid.setAuction_id(auctionID);
		bid.setCurrent_price(bidPrice);
		
		MBidEntity manmunBid = new MBidEntity();
		manmunBid.setBuyer_id(BuyerID);
		manmunBid.setAuction_id(auctionID);
		manmunBid.setBid_price(bidPrice);
		
		ApplicationDAO dao = new ApplicationDAO();
		
		try {
			LinkedList<BidEntity> bs = bidDaoImp.searchBidByBuyerID(BuyerID);
			if(bs.size() != 0){
				boolean exist = false;
				String aid = null;
				for(int i = 0; i < bs.size(); i++){
					if(bs.get(i).getAuction_id().equals(auctionID)){
						aid = bs.get(i).getAuction_id();
						exist = true;
						break;
					}
				}
				if(exist == true){
					bidDaoImp.UpdateBid(BuyerID, aid, bidPrice);
					bidDaoImp.UpdateMBid(BuyerID, aid, bidPrice);
				}else{
					// user update
					bidDaoImp.insertBid(bid);
					bidDaoImp.insertMBid(manmunBid);
				}
			}else{
				// use insert
				bidDaoImp.insertBid(bid);
				bidDaoImp.insertMBid(manmunBid);
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		request.getRequestDispatcher("/buyer.jsp").forward(request, response);
	}
}
