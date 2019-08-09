package com.catering.bean;

import java.math.BigDecimal;

public class OrderItem {
	private int fid;			//����id
	private Cuisine cuisine;	//�˵���
	private int inum;			//����
	private double sum;			//С��
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
	//ÿ����Ʒ�������޸ģ������¼���С�Ƽ۸�
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
