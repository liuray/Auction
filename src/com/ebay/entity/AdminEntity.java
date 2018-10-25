package com.ebay.entity;

public class AdminEntity {
	String UserID;
	String position;

	public AdminEntity(String userID, String position) {
		super();
		UserID = userID;
		this.position = position;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public AdminEntity() {
	}
}
