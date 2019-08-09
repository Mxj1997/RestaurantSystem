package com.catering.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageBean<T> implements Serializable  {
	private static final long serialVersionUID=1L;
	private int pageNo;//页号    1
	private int pageSize;//页条数    8
	private List<T> data=new ArrayList<T>(); //单页数据集合    deskList
	private int total;//总记录条数
	
	public PageBean() {
		super();
	}
	public PageBean(int pageNo, int pageSize, List<T> data, int total) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.data = data;
		this.total = total;
	}
	/**
	 * 返回总页数
	 * @return
	 */
	public int getCount(){
		return (this.total+this.pageSize-1)/this.pageSize;
	}
	/**
	 * 返回首页页号
	 * @return
	 */
	public int getFirst(){
		return 1;
	}
	
	/**
	 *返回末页页号
	 * @return
	 */
	public int getLast(){
		return this.getCount();
		
	}
	
	/**
	 * 返回上一页页号
	 * @return
	 */
	public int getUp(){
		return this.pageNo==1?1:this.pageNo-1;
		
	}
	
	/**
	 * 返回下一页页号
	 * @return
	 */
	public int getDown(){
		return this.pageNo==this.getCount()?this.pageNo:this.pageNo+1;
	}
	
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
	
	
	
}
