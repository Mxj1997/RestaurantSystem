package com.catering.bean;

public class Account {
      private static final long serialVersionUID=1L;
      private int aid;
      private String aname;
      private String apwd;
      private int atype;
      private int aexist;

	public Account() {
		super();
	}
	public Account(int aid, String aname, String apwd, int atype, int aexist) {
		super();
		this.aid = aid;
		this.aname = aname;
		this.apwd = apwd;
		this.atype = atype;
		this.aexist = aexist;
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
	public String getApwd() {
		return apwd;
	}
	public void setApwd(String apwd) {
		this.apwd = apwd;
	}
	public int getAtype() {
		return atype;
	}
	public void setAtype(int atype) {
		this.atype = atype;
	}
	public int getAexist() {
		return aexist;
	}
	public void setAexist(int aexist) {
		this.aexist = aexist;
	}
	
      
}
