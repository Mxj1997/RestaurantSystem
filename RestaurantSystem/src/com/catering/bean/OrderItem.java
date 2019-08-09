package com.catering.bean;

import java.math.BigDecimal;

public class OrderItem {
	private int fid;			//订单id
	private Cuisine cuisine;	//菜单类
	private int inum;			//数量
	private double sum;			//小计
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public Cuisine getCuisine() {
		return cuisine;
	}
	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}
	public int getInum() {
		return inum;
	}
	//每当商品数量被修改，会重新计算小计价格
	public void setInum(int inum) {
		this.inum = inum;
		double s1=this.inum*cuisine.getCprice();
		BigDecimal bd=new BigDecimal(s1);
		this.sum=bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	
}
