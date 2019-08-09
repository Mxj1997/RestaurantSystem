package com.catering.bean;

public class Cuisine {
	private static final long serialVersionUID=1L;
	private int cid;
	private String cname;
	private double ccost;
	private double cprice;
	private String cimg;
	private String cinfo;
	private int cexist;
	private int mid;
	private String mtype;
	private int total;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public double getCcost() {
		return ccost;
	}
	public void setCcost(double ccost) {
		this.ccost = ccost;
	}
	public double getCprice() {
		return cprice;
	}
	public void setCprice(double cprice) {
		this.cprice = cprice;
	}
	public String getCimg() {
		return cimg;
	}
	public void setCimg(String cimg) {
		this.cimg = cimg;
	}
	public String getCinfo() {
		return cinfo;
	}
	public void setCinfo(String cinfo) {
		this.cinfo = cinfo;
	}
	public int getCexist() {
		return cexist;
	}
	public void setCexist(int cexist) {
		this.cexist = cexist;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMtype() {
		return mtype;
	}
	public void setMtype(String mtype) {
		this.mtype = mtype;
	}
	
}
