package com.catering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.catering.bean.MenuType;
import com.catering.util.DBUtil;

public class MenuTypeDao {
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	/**
	 * 查询所有菜的类型
	 * @return
	 */
	public List<MenuType> showAllType(){
		List<MenuType> list=new ArrayList<MenuType>();
		String sql="select * from menutype where m_exist=1";
		conn=DBUtil.getConnection();
		try{
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				MenuType mt=new MenuType();
				mt.setMid(rs.getInt(1));
				mt.setMtype(rs.getString(2));
				mt.setMexist(rs.getInt(3));
				list.add(mt);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return list;
	}
	public List<MenuType> findAllByPage(int pageNo,int pageSize){
		List<MenuType> list=new ArrayList<MenuType>();
		String sql="select * from (select t.*,row_number() over (order by m_id) rn"
		+" from (select m.m_id,m.m_type,m.m_exist,count(c.c_id) c_state from"
		+" menutype m left join cuisine c on c.m_id=m.m_id and c.c_exist=1 group by"
		+" m.m_id,m.m_type,m.m_exist) t) where rn between ? and ?";
		
		//为了能显示，这边暂时改了SQL语句
		conn=DBUtil.getConnection();//
		try{
			pst=conn.prepareStatement(sql);
			int start=(pageNo-1)*pageSize+1;
			int end=pageNo*pageSize;
			pst.setInt(1, start);
			pst.setInt(2,end);
			rs=pst.executeQuery();
			while(rs.next()){

				MenuType mt=new MenuType();
				mt.setMid(rs.getInt(1));
				mt.setMtype(rs.getString(2));
				mt.setMexist(rs.getInt(3));
				mt.setMstate(rs.getInt(4));
				list.add(mt);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return list;
	}
	
	/**
	 * 获得总条数
	 * @return
	 */
	public int getCount(){
		int count=0;
		String sql="select count(*) from menutype";

		conn=DBUtil.getConnection();
		try{
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return count;
	}
	
	public boolean addType(MenuType mt){
		boolean flag=false;
		String sql="insert into menutype values (menutypeid.nextval,?,?)";

		conn=DBUtil.getConnection();
		try{
			pst=conn.prepareStatement(sql);
			pst.setString(1,mt.getMtype());
			pst.setInt(2, mt.getMexist());
			int rows=pst.executeUpdate();
			if(rows>0){
				flag=true;
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return flag;
	}
	
	public boolean existType(int mid,int exist){
		boolean flag=false;
		String sql="update menutype set m_exist=? where m_id=?";

		conn=DBUtil.getConnection();
		try{
			pst=conn.prepareStatement(sql);
			pst.setInt(1,exist);
			pst.setInt(2, mid);
			int rows=pst.executeUpdate();
			if(rows>0){
				flag=true;
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return flag;
		
	}
	
	public boolean updateType(int mid,String type){
		boolean flag=false;
		String sql="update menutype set m_type=? where m_id=?";

		conn=DBUtil.getConnection();
		try{
			pst=conn.prepareStatement(sql);
			pst.setString(1,type);
			pst.setInt(2, mid);
			int rows=pst.executeUpdate();
			if(rows>0){
				flag=true;
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return flag;
		
	}
}
