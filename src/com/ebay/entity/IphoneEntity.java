package com.ebay.entity;

public class IphoneEntity {
	String P_id;
	int I_storage_inGB;
	String I_type;
	public String getP_id() {
		return P_id;
	}
	public void setP_id(String p_id) {
		P_id = p_id;
	}
	public int getI_storage_inGB() {
		return I_storage_inGB;
	}
	public void setI_storage_inGB(int i_storage_inGB) {
		I_storage_inGB = i_storage_inGB;
	}
	public String getI_type() {
		return I_type;
	}
	public void setI_type(String i_type) {
		I_type = i_type;
	}
	public IphoneEntity(String p_id, int i_storage_inGB, String i_type) {
		super();
		P_id = p_id;
		I_storage_inGB = i_storage_inGB;
		I_type = i_type;
	}
	
	public IphoneEntity(){}
}
