package com.catering.bean;

import java.io.Serializable;

public class Desk implements Serializable{
    private static final long serialVersionUID=1L;
    private int deid;
    private String dname;
    private int dstate;
    private int dexist;
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
	public int getDstate() {
		return dstate;
	}
	public void setDstate(int dstate) {
		this.dstate = dstate;
	}
	public int getDexist() {
		return dexist;
	}
	public void setDexist(int dexist) {
		this.dexist = dexist;
	}
	public Desk() {
		super();
	}
	public Desk(int deid, String dname, int dstate, int dexist) {
		super();
		this.deid = deid;
		this.dname = dname;
		this.dstate = dstate;
		this.dexist = dexist;
	}
	
    
    
    
}
