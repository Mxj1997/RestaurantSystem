package com.catering.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class OrderForm implements Serializable{
	private int fid;			//����id
	private int deid;			//����id
	private String dname;		//������
	private Timestamp fdate;	//����ʱ��
	private Timestamp fdate2;	//����ʱ��
	private int fstate;			//����״̬
	private double total;		//�ܼ�
	private int aid;			//�µ���
	private String aname;		//�µ����˺�
	private double fincome;		//ʵ������
	private double fsum;		//�ݴ�Ӧ��
	
	private Map<Integer,OrderItem> items=new HashMap<Integer,OrderItem>();

	public int getFid() {
		return fid;
	}

	public Map<Integer, OrderItem> getItems() {
		return items;
	}

	public void setItems(Map<Integer, OrderItem> items) {
		this.items = items;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public int getDeid() {
		return deid;
	}

	public void setDeid(int deid) {
		this.deid = deid;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public Timestamp getFdate() {
		return fdate;
	}

	public void setFdate(Timestamp fdate) {
		this.fdate = fdate;
	}

	public Timestamp getFdate2() {
		return fdate2;
	}

	public void setFdate2(Timestamp fdate2) {
		this.fdate2 = fdate2;
	}

	public int getFstate() {
		return fstate;
	}

	public void setFstate(int fstate) {
		this.fstate = fstate;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public double getFincome() {
		return fincome;
	}

	public void setFincome(double fincome) {
		this.fincome = fincome;
	}

	public double getFsum() {
		return fsum;
	}

	public void setFsum(double fsum) {
		this.fsum = fsum;
	}
}
