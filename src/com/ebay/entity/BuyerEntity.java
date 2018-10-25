package com.ebay.entity;

public class BuyerEntity {
	String UserID;
	String payment_method;
	
	
	
	public BuyerEntity(String userID, String payment_method) {
		super();
		UserID = userID;
		this.payment_method = payment_method;
	}



	public String getUserID() {
		return UserID;
	}



	public void setUserID(String userID) {
		UserID = userID;
	}



	public String getPayment_method() {
		return payment_method;
	}



	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}



	public BuyerEntity(){
	}
}
