package com.ebay.entity;

public class UserEntity {
	private String UserID;
	private String lastName;
	private String firstName;
	private String password;
	private String email;
	private int phone;
	private String streetAddr;
	
	
	

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStreetAddr() {
		return streetAddr;
	}
	public void setStreetAddr(String streetAddr) {
		this.streetAddr = streetAddr;
	}
	
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public UserEntity(){

	}
	public UserEntity(String userID, String lastName, String firstName, String password, String email, int phone,
			String streetAddr) {
		super();
		UserID = userID;
		this.lastName = lastName;
		this.firstName = firstName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.streetAddr = streetAddr;
		
	}
	
}
