package com.catering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.catering.util.DBUtil;

public class FormDao {
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	/**
	 * 创建订单的方法，根据餐桌id 操作人id 创建订单并拿到fid
	 * @param did
	 * @param aid
	 * @return
	 */
	public int addForm(int did,int aid){
		int fid=0;

		String sql="insert into order_form (f_id,d_id,a_id) values (formid.nextval,?,?)";
		conn=DBUtil.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, did);
			pst.setInt(2,aid);
			int rows=pst.executeUpdate();
			if(rows>0){
				sql="select formId.currval from dual";
				pst=conn.prepareStatement(sql);
				rs=pst.executeQuery();
				if(rs.next()){
					fid=rs.getInt(1);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return fid;
	}
	
	/**
	 * 结账，插入订单信息
	 * @param fstate
	 * @param sum
	 * @param fid
	 * @return
	 */
	public boolean settleForm(int fstate,double sum,int fid){
		boolean flag=false;

		String sql="update order_form set f_date2=sysdate,f_state=?,f_income=? where f_id=?";
		conn=DBUtil.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, fstate);
			pst.setDouble(2,sum);
			pst.setInt(3,fid);
			int rows=pst.executeUpdate();
			if(rows>0){
				flag=true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		
		return flag;
	}
	/**
	 * 获取总收益
	 * @return income
	 */
	public int getIncome(String date){
		int income=0;
		String sql="select sum(f_income) SUM from order_form where to_char(f_date2,'yyyymmdd') = ?";
		try {
			 conn= DBUtil.getConnection();
			 pst=conn.prepareStatement(sql);
			 pst.setString(1, date);
			 rs=pst.executeQuery();
			 if(rs.next()){
				 income=rs.getInt(1);
			 }
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return income;
	}
}
