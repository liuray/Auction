package com.ebay.entity;

public class ProductEntity {
	String P_id;
	String P_description;
	double P_price;
	String P_name;
	
	public String getP_id() {
		return P_id;
	}
	public void setP_id(String p_id) {
		P_id = p_id;
	}
	public String getP_description() {
		return P_description;
	}
	public void setP_description(String p_description) {
		P_description = p_description;
	}
	public double getP_price() {
		return P_price;
	}
	public void setP_price(double p_price) {
		P_price = p_price;
	}
	public String getP_name() {
		return P_name;
	}
	public void setP_name(String p_name) {
		P_name = p_name;
	}
	public ProductEntity(String p_id, String p_description, double p_price, String p_name) {
		super();
		P_id = p_id;
		P_description = p_description;
		P_price = p_price;
		P_name = p_name;
	}
	
	public ProductEntity(){}
}
