package com.catering.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageBean<T> implements Serializable  {
	private static final long serialVersionUID=1L;
	private int pageNo;//ҳ��    1
	private int pageSize;//ҳ����    8
	private List<T> data=new ArrayList<T>(); //��ҳ���ݼ���    deskList
	private int total;//�ܼ�¼����
	
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
	 * ������ҳ��
	 * @return
	 */
	public int getCount(){
		return (this.total+this.pageSize-1)/this.pageSize;
	}
	/**
	 * ������ҳҳ��
	 * @return
	 */
	public int getFirst(){
		return 1;
	}
	
	/**
	 *����ĩҳҳ��
	 * @return
	 */
	public int getLast(){
		return this.getCount();
		
	}
	
	/**
	 * ������һҳҳ��
	 * @return
	 */
	public int getUp(){
		return this.pageNo==1?1:this.pageNo-1;
		
	}
	
	/**
	 * ������һҳҳ��
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
