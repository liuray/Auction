package com.ebay.entity;

public class SandP {
	String S_id;
	String P_id;
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
	public SandP(String s_id, String p_id) {
		super();
		S_id = s_id;
		P_id = p_id;
	}
	
	public SandP(){}
}
