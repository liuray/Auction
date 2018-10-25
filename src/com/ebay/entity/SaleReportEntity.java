package com.ebay.entity;

public class SaleReportEntity {
	Double totalEarning;
	Double earnpitem ;
	Double earntyped;
	Double earntypet;
	Double earntypel;
	Double earnpseller;
	String bsi;
	String bb;
	public SaleReportEntity(Double totalEarning, Double earnpitem, Double earntyped, Double earntypet, Double earntypel,
			Double earnpseller, String bsi, String bb) {
		super();
		this.totalEarning = totalEarning;
		this.earnpitem = earnpitem;
		this.earntyped = earntyped;
		this.earntypet = earntypet;
		this.earntypel = earntypel;
		this.earnpseller = earnpseller;
		this.bsi = bsi;
		this.bb = bb;
	}
	public SaleReportEntity(){};
	public Double getTotalEarning() {
		return totalEarning;
	}
	public void setTotalEarning(Double totalEarning) {
		this.totalEarning = totalEarning;
	}
	public Double getEarnpitem() {
		return earnpitem;
	}
	public void setEarnpitem(Double earnpitem) {
		this.earnpitem = earnpitem;
	}
	public Double getEarntyped() {
		return earntyped;
	}
	public void setEarntyped(Double earntyped) {
		this.earntyped = earntyped;
	}
	public Double getEarntypet() {
		return earntypet;
	}
	public void setEarntypet(Double earntypet) {
		this.earntypet = earntypet;
	}
	public Double getEarntypel() {
		return earntypel;
	}
	public void setEarntypel(Double earntypel) {
		this.earntypel = earntypel;
	}
	public Double getEarnpseller() {
		return earnpseller;
	}
	public void setEarnpseller(Double earnpseller) {
		this.earnpseller = earnpseller;
	}
	public String getBsi() {
		return bsi;
	}
	public void setBsi(String bsi) {
		this.bsi = bsi;
	}
	public String getBb() {
		return bb;
	}
	public void setBb(String bb) {
		this.bb = bb;
	}
}
