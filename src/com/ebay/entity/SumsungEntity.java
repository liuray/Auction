package com.ebay.entity;

public class SumsungEntity {
	String P_id;
	int S_storage_inGB;
	String S_type;
	
	public String getP_id() {
		return P_id;
	}
	public void setP_id(String p_id) {
		P_id = p_id;
	}
	public int getS_storage_inGB() {
		return S_storage_inGB;
	}
	public void setS_storage_inGB(int s_storage_inGB) {
		S_storage_inGB = s_storage_inGB;
	}
	public String getS_type() {
		return S_type;
	}
	public void setS_type(String s_type) {
		S_type = s_type;
	}
	public SumsungEntity(String p_id, int s_storage_inGB, String s_type) {
		super();
		P_id = p_id;
		S_storage_inGB = s_storage_inGB;
		S_type = s_type;
	}
	
	public SumsungEntity(){}
}
