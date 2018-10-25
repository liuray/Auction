package com.ebay.entity;

import java.sql.Date;

public class AuctionEntity {
	String A_id;
	String S_id;
	String P_id;
	Date post_date;
	Date end_date;
	double start_price;
	
	
	
	public AuctionEntity(String a_id, String s_id, String p_id, Date post_date, Date end_date, double start_price) {
		super();
		A_id = a_id;
		S_id = s_id;
		P_id = p_id;
		this.post_date = post_date;
		this.end_date = end_date;
		this.start_price = start_price;
	}



	public String getA_id() {
		return A_id;
	}



	public void setA_id(String a_id) {
		A_id = a_id;
	}



	public String getS_id() {
		return S_id;
	}



	public void setS_id(String s_id) {
		S_id = s_id;
	}



	public String getP_id() {
		return P_id;
	}



	public void setP_id(String p_id) {
		P_id = p_id;
	}



	public Date getPost_date() {
		return post_date;
	}



	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}



	public Date getEnd_date() {
		return end_date;
	}



	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}



	public double getStart_price() {
		return start_price;
	}



	public void setStart_price(double start_price) {
		this.start_price = start_price;
	}



	public AuctionEntity(){}

}