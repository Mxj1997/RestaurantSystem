package com.catering.bean;

public class MenuType {
	private static final long serialVersionUID=1L;
	private int mid;
	private String mtype;
	private int mexist;
	private int mstate;//是否有菜用这个类型
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
	public int getMexist() {
		return mexist;
	}
	public void setMexist(int mexist) {
		this.mexist = mexist;
	}
	public int getMstate() {
		return mstate;
	}
	public void setMstate(int mstate) {
		this.mstate = mstate;
	}
}
