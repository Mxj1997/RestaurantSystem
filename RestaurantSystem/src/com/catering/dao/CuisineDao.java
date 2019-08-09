package com.catering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.catering.bean.Cuisine;
import com.catering.bean.MenuPaging;
import com.catering.util.DBUtil;
import com.catering.util.StringUtil;

public class CuisineDao {
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	/**
	 * 查找所需页号的所有菜
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Cuisine> findAllByPage(int pageNo,int pageSize){
		List<Cuisine> list=new ArrayList<Cuisine>();
		conn=DBUtil.getConnection();
		String sql="select * from (select c.*,m.m_type,row_number() over(order by c_id) rn "
		+"from cuisine c left join menuType m on c.m_id=m.m_id) "
		+"where rn between ? and ?";
		try{
			pst=conn.prepareStatement(sql);
			int start=(pageNo-1)*pageSize+1;
			int end=pageNo*pageSize;
			pst.setInt(1, start);
			pst.setInt(2,end);
			rs=pst.executeQuery();
			while(rs.next())
			{

				Cuisine cuisine=new Cuisine();
				cuisine.setCid(rs.getInt(1));
				cuisine.setCname(rs.getString(2));
				cuisine.setCcost(rs.getDouble(3));
				cuisine.setCprice(rs.getDouble(4));
				cuisine.setCimg(rs.getString(5));
				cuisine.setCinfo(rs.getString(6));
				cuisine.setMid(rs.getInt(7));
				cuisine.setCexist(rs.getInt(8));
				cuisine.setTotal(rs.getInt(9));				
				cuisine.setMtype(rs.getString(10));	
				list.add(cuisine);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		finally{
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
		String sql="select count(*) from cuisine";
		conn=DBUtil.getConnection();
		try{
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			if(rs.next()){
				count=rs.getInt(1);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return count;
	}
	
	/**
	 * 判断菜品是否重名
	 * @param cname
	 * @return
	 */
	public boolean repecname(String cname){
		boolean flag=false;
		String sql="select count(*) from cuisine where c_name=?";
		conn=DBUtil.getConnection();
		try{
			pst=conn.prepareStatement(sql);
			pst.setString(1,cname);
			rs=pst.executeQuery();
			while(rs.next()){
				if(rs.getInt(1)>0){
					flag=true;
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}

		return flag;
	}
	
	/**
	 * 按i修改菜品状态
	 * @param cid
	 * @param exist
	 * @return
	 */
	public boolean deleteCuisine(int cid,int exist){
		boolean flag=false;
		String sql="update cuisine set c_exist=? where c_id=?";
		conn=DBUtil.getConnection();
		try{
			pst=conn.prepareStatement(sql);
			pst.setInt(1,exist);
			pst.setInt(2,cid);
			int rows=pst.executeUpdate();
			if(rows>0){
				flag=true;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return flag;
	}
	
	/**
	 * 通过id查找菜品
	 * @param cid
	 * @return
	 */
	public Cuisine findCuiById(int cid){
		Cuisine cuisine=null;
		String sql="select * from cuisine where c_id=?";
		conn=DBUtil.getConnection();
		try{
			pst=conn.prepareStatement(sql);
			pst.setInt(1,cid);
			rs=pst.executeQuery();
			if(rs.next()){
				cuisine=new Cuisine();
				cuisine.setCid(rs.getInt(1));
				cuisine.setCname(rs.getString(2));
				cuisine.setCcost(rs.getDouble(3));
				cuisine.setCprice(rs.getDouble(4));
				cuisine.setCimg(rs.getString(5));
				cuisine.setCinfo(rs.getString(6));
				cuisine.setMid(rs.getInt(7));
				cuisine.setCexist(rs.getInt(8));
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return cuisine;
	}
	
	/**
	 * 修改菜品
	 * @param cuisine
	 * @return
	 */
	public boolean updateCuisine(Cuisine cuisine){
		boolean flag=false;
		String sql="update cuisine set c_price=?,m_id=? where c_id=?";
		conn=DBUtil.getConnection();
		try{
			pst=conn.prepareStatement(sql);
			pst.setDouble(1,cuisine.getCprice());
			pst.setInt(2,cuisine.getMid());
			pst.setInt(3,cuisine.getCid());
			int rows=pst.executeUpdate();
			if(rows>0){
				flag=true;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return flag;
	}
	
	public List<Cuisine> pageCuisine(int pageNo,int pageSize,MenuPaging mp){
		List<Cuisine> list=new ArrayList<Cuisine>();
		conn=DBUtil.getConnection();
		String sql="select * from (select c.*,m.m_type,row_number() over (order by c_id) rn from cuisine c,menutype m where c.c_exist=1 and c.m_id=m.m_id ";
		if(mp.getPrice1()>0 && mp.getPrice2()==0){
			sql+="and c.cprice="+mp.getPrice1();
		}
		if(mp.getPrice1()==0 && mp.getPrice2()>0){
			sql+="and c.cprice="+mp.getPrice2();
		}
		if(mp.getPrice1()>0&&mp.getPrice2()>0&& mp.getPrice1()<mp.getPrice2()){
			sql+="and c.c_price between "+mp.getPrice1()+" and "+mp.getPrice2();
		}
		if(mp.getPrice1()>0&&mp.getPrice2()>0&& mp.getPrice1()>mp.getPrice2()){
			sql+="and c.c_price between "+mp.getPrice2()+" and "+mp.getPrice1();
		}
		if(StringUtil.isNotEmpty(mp.getCname())){
			sql+="and c.c_name like '%"+mp.getCname()+"%'";
		}
		if(StringUtil.isNotEmpty(mp.getCtype())){
			sql+="and m.m_type like '%"+mp.getCtype()+"%'";
		}
		String sql2=sql+") where rn between ? and ?";
		try {
			pst=conn.prepareStatement(sql2);
			int start=(pageNo-1)*pageSize+1;
			int end=pageNo*pageSize;
			pst.setInt(1, start);
			pst.setInt(2, end);
			rs=pst.executeQuery();
			while(rs.next()){
				Cuisine cuisine=new Cuisine();
				cuisine=new Cuisine();
				cuisine.setCid(rs.getInt(1));
				cuisine.setCname(rs.getString(2));
				cuisine.setCcost(rs.getDouble(3));
				cuisine.setCprice(rs.getDouble(4));
				cuisine.setCimg(rs.getString(5));
				cuisine.setCinfo(rs.getString(6));
				cuisine.setMid(rs.getInt(7));
				cuisine.setCexist(rs.getInt(8));
				list.add(cuisine);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return list;
	}
	
	
	public int getCount(MenuPaging mp){
		int count=0;
		conn=DBUtil.getConnection();
		String sql="select count(*) from cuisine c,menutype m where c_exist=1 and c.m_id=m.m_id ";
		if(mp.getPrice1()>0 && mp.getPrice2()==0){
			sql+="and c.cprice="+mp.getPrice1();
		}
		if(mp.getPrice1()==0 && mp.getPrice2()>0){
			sql+="and c.cprice="+mp.getPrice2();
		}
		if(mp.getPrice1()>0&&mp.getPrice2()>0&& mp.getPrice1()<mp.getPrice2()){
			sql+="and c.c_price between "+mp.getPrice1()+" and "+mp.getPrice2();
		}
		if(mp.getPrice1()>0&&mp.getPrice2()>0&& mp.getPrice1()>mp.getPrice2()){
			sql+="and c.c_price between "+mp.getPrice2()+" and "+mp.getPrice1();
		}
		if(StringUtil.isNotEmpty(mp.getCname())){
			sql+="and c.c_name like '%"+mp.getCname()+"%'";
		}
		if(StringUtil.isNotEmpty(mp.getCtype())){
			sql+="and m.m_type like '%"+mp.getCtype()+"%'";
		}
		try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return count;
	}
	   /**
	    * 修改total
	    * @return
	    */
	   public boolean updateTotal(int total,int cid){
			boolean flag=false;
			String sql="update cuisine set c_total=? where c_id=?";
			conn=DBUtil.getConnection();
			try{
				pst=conn.prepareStatement(sql);
				pst.setInt(1,total);
				pst.setInt(2,cid);
				int rows=pst.executeUpdate();
				if(rows>0){
					flag=true;
				}
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				DBUtil.closeAll(conn, rs, pst);
			}
			return flag;
		}
	   /**
	    * 初始total
	    * @return
	    */
	   public boolean resetTotal(){
			boolean flag=false;
			String sql="update cuisine set c_total=0";
			conn=DBUtil.getConnection();
			try{
				pst=conn.prepareStatement(sql);
				int rows=pst.executeUpdate();
				if(rows>0){
					flag=true;
				}
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				DBUtil.closeAll(conn, rs, pst);
			}
			return flag;
		}
}
