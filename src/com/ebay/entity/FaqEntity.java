package com.ebay.entity;

public class FaqEntity {
	String Q_id;
	String B_id;
	String S_id;
	String Admin_id;
	String question;
	String ans;
	public String getQ_id() {
		return Q_id;
	}
	public void setQ_id(String q_id) {
		Q_id = q_id;
	}
	public String getB_id() {
		return B_id;
	}
	public void setB_id(String b_id) {
		B_id = b_id;
	}
	public String getS_id() {
		return S_id;
	}
	public void setS_id(String s_id) {
		S_id = s_id;
	}
	public String getAdmin_id() {
		return Admin_id;
	}
	public void setAdmin_id(String admin_id) {
		Admin_id = admin_id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAns() {
		return ans;
	}
	public void setAns(String ans) {
		this.ans = ans;
	}
	public FaqEntity(String q_id, String b_id, String s_id, String admin_id, String question, String ans) {
		super();
		Q_id = q_id;
		B_id = b_id;
		S_id = s_id;
		Admin_id = admin_id;
		this.question = question;
		this.ans = ans;
	}
	
	public FaqEntity(){}
}
