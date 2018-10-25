package com.ebay.entity;

public class SellerEntity {
	String UserID;
	String Company_name;
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getCompany_name() {
		return Company_name;
	}
	public void setCompany_name(String company_name) {
		Company_name = company_name;
	}
	
	public SellerEntity(String userID, String company_name) {
		super();
		UserID = userID;
		Company_name = company_name;
	}
	public SellerEntity(){}
}
