package com.ebay.entity;

public class NokiaEntity {
	String P_id;
	String N_storage_inGB;
	String N_type;
	public String getP_id() {
		return P_id;
	}
	public void setP_id(String p_id) {
		P_id = p_id;
	}
	public String getN_storage_inGB() {
		return N_storage_inGB;
	}
	public void setN_storage_inGB(String n_storage_inGB) {
		N_storage_inGB = n_storage_inGB;
	}
	public String getN_type() {
		return N_type;
	}
	public void setN_type(String n_type) {
		N_type = n_type;
	}
	public NokiaEntity(String p_id, String n_storage_inGB, String n_type) {
		super();
		P_id = p_id;
		N_storage_inGB = n_storage_inGB;
		N_type = n_type;
	}
	
	public NokiaEntity(){}
}
