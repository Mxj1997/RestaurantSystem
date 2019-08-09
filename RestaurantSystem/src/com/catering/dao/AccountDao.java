package com.catering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.catering.bean.Account;
import com.catering.util.DBUtil;

public class AccountDao {
	Connection conn=null;

	PreparedStatement pst=null;
	ResultSet rs=null;
	
	/**
	 * 搜寻用户是否存在
	 * @param user
	 * @param pass
	 * @param type
	 * @return
	 */
    public Account showAccount(String user,String pass,int type){
    	Account at=null;
    	String sql="select * from acct where a_name=? and a_pwd=? and a_type=? and a_exist=1";
    	conn=DBUtil.getConnection();
    	try {
			pst= conn.prepareStatement(sql);
			pst.setString(1, user);
			pst.setString(2, pass);
			pst.setInt(3, type);
			rs=pst.executeQuery();
			if(rs.next()){
				at=new Account();
				at.setAid(rs.getInt(1));
				at.setAname(rs.getString(2));
				at.setApwd(rs.getString(3));
				at.setAtype(rs.getInt(4));
				at.setAexist(rs.getInt(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
       return at;
    }
    
    /**
     * 查询所有用户
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<Account> findAllAccount(int pageNo,int pageSize){
    	List<Account> list=new ArrayList<Account>();
		conn=DBUtil.getConnection();
		String sql="select * from acct";
		try{
			pst=conn.prepareStatement(sql);
			int start=(pageNo-1)*pageSize+1;
			int end=pageNo*pageSize;
			rs=pst.executeQuery();
			while(rs.next())
			{

				Account account=new Account();
				account.setAid(rs.getInt(1));
				account.setAname(rs.getString(2));
				account.setApwd(rs.getString(3));
				account.setAtype(rs.getInt(4));
				account.setAexist(rs.getInt(5));
				list.add(account);
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
     * 获得总的人员数
     * @return
     */
    public int getCount(){
    	int count=0;
		conn=DBUtil.getConnection();
		String sql="select count(*) from acct";
		try{
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			if(rs.next())
			{

				count=rs.getInt(1);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return count;
    }
    
    /**
     * 通过用户名和密码查找
     * @param account
     * @return
     */
    public boolean findByNamePwd(String aname,String apwd){
    	boolean flag=false;
    	String sql="select count(*) from acct where a_name=? and a_pwd=?";
    	conn=DBUtil.getConnection();
    	try {
    		pst=conn.prepareStatement(sql);
    		pst.setString(1,aname);
			pst.setString(2,apwd);
			rs=pst.executeQuery();
			if(rs.next()){
				int rows=rs.getInt(1);
				if(rows>0){
					flag=true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
    	return flag;
    }
    
    /**
     * 通过ID查找用户密码
     * @param account
     * @return
     */
    public String findPwdByAid(Account account){
    	String pwd="";
    	String sql="select a_pwd from acct where a_id=?";
    	conn=DBUtil.getConnection();
    	try {
			pst= conn.prepareStatement(sql);
			pst.setInt(1, account.getAid());
			rs=pst.executeQuery();
			if(rs.next()){
				pwd=rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
    	return pwd;
    }
    
    /**
     * 添加用户
     * @param account
     * @return
     */
    public boolean addUser(Account account){
    	boolean flag=false;
    	String sql="insert into acct values (acctid.nextval,?,?,?,?)";
    	conn=DBUtil.getConnection();
    	try {
			pst= conn.prepareStatement(sql);
			pst.setString(1, account.getAname());
			pst.setString(2, account.getApwd());
			pst.setInt(3, account.getAtype());
			pst.setInt(4, account.getAexist());
			int rows=pst.executeUpdate();
			if(rows>0){
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
    	return flag;
    }
    
    /**
     * 修改密码
     * @param account
     * @return
     */
    public boolean updatePwd(Account account){
    	boolean flag=false;

    	String sql="update acct set a_pwd=? where a_id=?";
    	conn=DBUtil.getConnection();
    	try {
			pst= conn.prepareStatement(sql);
			pst.setString(1, account.getApwd());
			pst.setInt(2, account.getAid());
			int rows=pst.executeUpdate();
			if(rows>0){
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
    	return flag;
    }
    
    /**
     * 启用/禁用用户
     * @param account
     * @return
     */
    public boolean setAble(Account account){
    	boolean flag=false;

    	String sql="update acct set a_exist=? where a_id=?";
    	conn=DBUtil.getConnection();
    	try {
			pst= conn.prepareStatement(sql);
			pst.setInt(1, account.getAexist());
			pst.setInt(2, account.getAid());
			int rows=pst.executeUpdate();
			if(rows>0){
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
    	return flag;
    }

    /**
     * 修改用户权限
     * @param account
     * @return
     */
    public boolean setType(Account account){
    	boolean flag=false;
    	String sql="update acct set a_type=? where a_id=?";
    	conn=DBUtil.getConnection();
    	try {
			pst= conn.prepareStatement(sql);
			pst.setInt(1, account.getAtype());
			pst.setInt(2, account.getAid());
			int rows=pst.executeUpdate();
			if(rows>0){
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
    	return flag;
    }
}
