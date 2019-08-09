package com.catering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.catering.bean.Cuisine;
import com.catering.util.DBUtil;

public class MenuXun {

	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	/**
	 * 添加菜品
	 * @param cuisine
	 * @return
	 */
	public int addfood(Cuisine cuisine){
		int cid=0;
		String sql="insert into cuisine values(cuisineid.nextval,?,?,?,?,'',?,1,0)";
		conn=DBUtil.getConnection();
		try{
			pst=conn.prepareStatement(sql);
			pst.setString(1, cuisine.getCname());
			pst.setDouble(2,cuisine.getCcost());
			pst.setDouble(3,cuisine.getCprice());
			pst.setString(4, cuisine.getCimg());
			pst.setInt(5, cuisine.getMid());
			int rows=pst.executeUpdate();
			if(rows>0){
				String sqll="select cuisineid.currval from dual";//dual是伪表
				pst=conn.prepareStatement(sqll);
				rs=pst.executeQuery();
				while(rs.next()){
					cid=rs.getInt(1);
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return cid;
	}
	
	/**
	 * 修改菜品图片
	 * @param cuisine
	 * @return
	 */
	public boolean updateImage(Cuisine cuisine){
		boolean flag=false;
		String sql="update cuisine set c_img=? where c_id=?";
		conn=DBUtil.getConnection();
		try{
			pst=conn.prepareStatement(sql);
			pst.setString(1, cuisine.getCimg());
			pst.setInt(2, cuisine.getCid());
			int rows=pst.executeUpdate();
			if(rows>0){
				flag=true;			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return flag;
	}
}
