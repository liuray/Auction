package com.ebay.entity;

public class MBidEntity {
	String Buyer_id;
	String Auction_id;
	Double bid_price;
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
	public Double getBid_price() {
		return bid_price;
	}
	public void setBid_price(Double bid_price) {
		this.bid_price = bid_price;
	}
	public MBidEntity(String buyer_id, String auction_id, Double bid_price) {
		super();
		Buyer_id = buyer_id;
		Auction_id = auction_id;
		this.bid_price = bid_price;
	}
	public MBidEntity(){}
}
