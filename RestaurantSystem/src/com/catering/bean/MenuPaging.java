package com.catering.bean;

public class MenuPaging {
	private int price1;		//价格
	private int price2;		//价格
	private String cname;	//名称
	private String ctype;	//类型
	public MenuPaging() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MenuPaging(int price1, int price2, String cname, String ctype) {
		super();
		this.price1 = price1;
		this.price2 = price2;
		this.cname = cname;
		this.ctype = ctype;
	}
	public int getPrice1() {
		return price1;
	}
	public void setPrice1(int price1) {
		this.price1 = price1;
	}
	public int getPrice2() {
		return price2;
	}
	public void setPrice2(int price2) {
		this.price2 = price2;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
}
