package com.ebay.entity;

public class BidEntity {
	String Buyer_id;
	String Auction_id;
	Double Current_price; 
	

	public BidEntity(String buyer_id, String auction_id, Double current_price) {
		super();
		Buyer_id = buyer_id;
		Auction_id = auction_id;
		Current_price = current_price;
	}


	public String getBuyer_id() {
		return Buyer_id;
	}


	public void setBuyer_id(String buyer_id) {
		Buyer_id = buyer_id;
	}


	public String getAuction_id() {
		return Auction_id;
	}


	public void setAuction_id(String auction_id) {
		Auction_id = auction_id;
	}


	public Double getCurrent_price() {
		return Current_price;
	}


	public void setCurrent_price(Double current_price) {
		Current_price = current_price;
	}


	public BidEntity(){}
}
