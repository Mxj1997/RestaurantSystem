package com.catering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.catering.bean.Cuisine;
import com.catering.bean.OrderItem;
import com.catering.util.DBUtil;

public class ItemDao {
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	public Map<Integer,OrderItem> showItems(int fid){
		//创建map集合，以键值对形式存放元素
		Map<Integer,OrderItem> map=new HashMap<Integer,OrderItem>();
		conn=DBUtil.getConnection();
		String sql="select c.c_id,c.c_name,c.c_price,i.i_num from "
					+" order_form f,Cuisine c,order_item i "
					+"where f.f_id=i.f_id and i.c_id=c.c_id and i.f_id=?";
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, fid);
			rs=pst.executeQuery();
			while(rs.next()){
				Cuisine cuisine=new Cuisine();
				OrderItem item=new OrderItem();
				cuisine.setCid(rs.getInt(1));
				cuisine.setCname(rs.getString(2));
				cuisine.setCprice(rs.getDouble(3));
				item.setCuisine(cuisine);
				item.setFid(fid);
				item.setInum(rs.getInt(4));
				item.setSum(rs.getDouble(3)*rs.getInt(4));
				//map以菜品的id做键，订单项作为值
				map.put(rs.getInt(1), item);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return map;
	}
	//创建订单项方法
	public boolean addItem(int fid,int cid){
		boolean flag=false;
		conn=DBUtil.getConnection();
		String sql="insert into order_item values (?,?,?)";
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, fid);
			pst.setInt(2, cid);
			pst.setInt(3, 1);
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
	 * 根据订单id菜品id修改订单项中的菜品数
	 * @param fid 订单id
	 * @param cid 菜品id
	 * @param num 菜品数量
	 * @return 是否成功
	 */
	public boolean updateItemNum(int fid,int cid,int num){
		boolean flag=false;
		String sql="update order_item set i_num=? where f_id=? and c_id=?";
		conn=DBUtil.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, num);
			pst.setInt(2, fid);
			pst.setInt(3, cid);
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
	 * 删除菜品
	 * @param fid
	 * @param cid
	 * @return
	 */
	public boolean deleteItem(int fid,int cid){
		boolean flag=false;
		String sql="delete from order_item where f_id=? and c_id=?";
		conn=DBUtil.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, fid);
			pst.setInt(2, cid);
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
	 * 根据菜品id 查询已定菜品总数量
	 * @param cid
	 * @return total
	 */
	public int getTotal(int cid){
		int total=0;
		String sql="select sum(i_num) SUM from order_item  where c_id=?";
		try {
			 conn= DBUtil.getConnection();
			 pst=conn.prepareStatement(sql);
			 pst.setInt(1, cid);
			 rs=pst.executeQuery();
			 if(rs.next()){
				 total=rs.getInt(1);
			 }
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return total;
	}
	
}
