package com.catering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.catering.bean.Desk;
import com.catering.util.DBUtil;

public class DeskDao {
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	public int showState(int deid){
		int state=0;
		String sql="select d_state from desk where d_id=?";
		conn=DBUtil.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, deid);
			rs=pst.executeQuery();
			if(rs.next()){
				state=rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return state;
	}
	/**
	 * 分页查询长度的
	 * @param type
	 * @return
	 */
	public int getDeskCount(int type){
		   int count=0;
		   String sql="";
		   //d_exist=0,停用,d_exist=1,在用;d_tate=0,空座,d_state=1,满座
		   sql="select count(*) from desk where d_exist=1";
		   if(type==2){
			   sql=sql+"and d_state<>0";
			   
		   }
		   if(type==3){
			   sql=sql+"and d_state=0";   
		   }
		   
		   try {
			 conn= DBUtil.getConnection();
			 pst=conn.prepareStatement(sql);
			 rs=pst.executeQuery();
			 if(rs.next()){
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
	  * 菜单页面查看桌子状态
	  * 1全部 2.在餐  3. 空桌
	  * @param pageNo
	  * @param pageSize
	  * @param type
	  * @return
	  */
     public List<Desk> pageDesk(int pageNo,int pageSize,int type){
    	 //创建List集合   实现类是ArrayList      
    	 List<Desk> list=new ArrayList<Desk>();
    	    conn=DBUtil.getConnection();
    	    String sql="select * from (select d.*,row_number() over (order by d_name) rn "
            +"from desk d where d_exist=1";
           
    	    if(type==2){
    	    	 sql=sql+"and d_state<>0";
    	    }
    	    if(type==3){
    	    	sql=sql+"and d_state=0";
    	    }
    	    sql=sql+") where rn between ? and ?";
    	    
    	    try {
				pst=conn.prepareStatement(sql);
				int start=(pageNo-1)*pageSize+1;  // 1
				int end=pageNo*pageSize;     //8
				pst.setInt(1, start);
				pst.setInt(2, end);
				rs=pst.executeQuery();
				while(rs.next()){
					 Desk desk=new Desk();
					 desk.setDeid(rs.getInt(1));
					 desk.setDname(rs.getString(2));
					 desk.setDstate(rs.getInt(3));
					 desk.setDexist(rs.getInt(4));
					 
					 list.add(desk);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				DBUtil.closeAll(conn, rs, pst);
			}
    	    
    	    return list;
     }   
     
     public List<Desk> findDeskByPage(int pageNo,int pageSize,boolean manage){
    	 List<Desk> list=new ArrayList<Desk>();
    	 String sql="select * from(select d.*,row_number() over (order by d_id) rn from desk d)where rn between ? and ?";
    	 if(!manage)
    		 sql="select * from(select d.*,row_number() over (order by d_id) rn from desk d where d_exist =1)where rn between ? and ?";
     	    conn=DBUtil.getConnection();
    	    try {
				pst=conn.prepareStatement(sql);
				int start=(pageNo-1)*pageSize+1;  // 1
				int end=pageNo*pageSize;     //10
				pst.setInt(1, start);
				pst.setInt(2, end);
				rs=pst.executeQuery();
				while(rs.next()){
					 Desk desk=new Desk();
					 desk.setDeid(rs.getInt(1));
					 desk.setDname(rs.getString(2));
					 desk.setDstate(rs.getInt(3));
					 desk.setDexist(rs.getInt(4));
					 
					 list.add(desk);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				DBUtil.closeAll(conn, rs, pst);
			}
    	 
    	 return list;
     }
     
     public int getCount(boolean manage){
    	 int count=0;
    	 String sql="";
    	 if(manage){
    		 sql="select count(*) from desk";
    	 }
    	 else{
    		 sql="select count(*) from desk where d_exist=1";
    	 }
    	 conn=DBUtil.getConnection();
    	 try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			if(rs.next()){
				count=rs.getInt(1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
    	 return count;
     }
     
     
     public Desk findDeskById(int deid){
    	 Desk desk=null;
    	 String sql="select * from desk where d_id=?";
    	 conn=DBUtil.getConnection();
 	    try {
				pst=conn.prepareStatement(sql);
				pst.setInt(1, deid);
				rs=pst.executeQuery();
				while(rs.next()){
					 desk=new Desk();
					 desk.setDeid(rs.getInt(1));
					 desk.setDname(rs.getString(2));
					 desk.setDstate(rs.getInt(3));
					 desk.setDexist(rs.getInt(4));
				}
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				DBUtil.closeAll(conn, rs, pst);
			}
 	    return desk;
     }
     
     public boolean updateDeskName(int deid,String dname){
    	 boolean flag=false;
    	 String sql="update desk set d_name=? where d_id=?";
    	 conn=DBUtil.getConnection();
 	    try {
				pst=conn.prepareStatement(sql);
				pst.setString(1, dname);
				pst.setInt(2, deid);
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
     
     public boolean banDesk(int deid,int exist){
    	 boolean flag=false;

    	 String sql="update desk set d_exist=? where d_id=?";
    	 conn=DBUtil.getConnection();
 	    try {
				pst=conn.prepareStatement(sql);
				pst.setInt(1, exist);
				pst.setInt(2, deid);
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
     
     public boolean addDesk(Desk desk){
    	 boolean flag=false;
    	 String sql="insert into desk values (deskid.nextval,?,?,?)";
    	 conn=DBUtil.getConnection();
 	    try {
				pst=conn.prepareStatement(sql);
				pst.setString(1,desk.getDname());
				pst.setInt(2,desk.getDstate());
				pst.setInt(3, desk.getDexist());
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
     

     public int findDeskByName(String dname){
    	 int rows=0;
    	 String sql="select count(*) from desk where d_name=?";
    	 conn=DBUtil.getConnection();
 	    try {
				pst=conn.prepareStatement(sql);
				pst.setString(1, dname);
				rs=pst.executeQuery();
				if(rs.next()){
					rows=rs.getInt(1);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				DBUtil.closeAll(conn, rs, pst);
			}
 	    return rows;
     }
     
     /**
      * 修改餐桌状态
      * @param deid
      * @param state
      */
     public boolean updateDeskState(int deid,int state){
    	 boolean flag=false;
    	 String sql="update desk set d_state=? where d_id=?";
    	 conn=DBUtil.getConnection();
 	    try {
				pst=conn.prepareStatement(sql);
				pst.setInt(1,state);
				pst.setInt(2,deid);
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
     
     
     
}
