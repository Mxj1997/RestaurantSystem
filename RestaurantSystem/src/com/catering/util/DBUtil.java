package com.catering.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;
    static{
    	  Properties pro=new Properties();
    	  InputStream  is=DBUtil.class.getClassLoader().getResourceAsStream("com/catering/util/jdbc.properties");
          try {
        	  pro.load(is);
        	  driver=pro.getProperty("driver");
        	  url=pro.getProperty("url");
        	  user=pro.getProperty("user");
        	  password=pro.getProperty("password");
        	  Class.forName(driver);
        	  
		  } catch (Exception e) {
				// TODO: handle exception
		  }
    
    }
    
    
    public static Connection getConnection(){
    	    Connection conn=null;
    	    try {
				conn=DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	    
    	    return conn;
    }
   
    
    public static void closeAll(Connection conn,ResultSet rs,Statement stm){
    	 if(rs!=null){
    		 try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
    	 }
    	 
    	 if(stm!=null){
    		 try {
 				stm.close();
 			} catch (Exception e) {
 				e.printStackTrace();
 			}
    	 }
    	 
    	 
    	 if(conn!=null){
    		 try {
 				conn.close();
 			} catch (Exception e) {
 				e.printStackTrace();
 			}
    	 }
    	 
    	 
    }
    
    
    
}
