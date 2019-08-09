package com.catering.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.catering.bean.Account;
import com.catering.dao.AccountDao;

public class LoginMenuServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//login
		//exit
		String action=request.getParameter("action");//获取到action的值，判断是登陆还是退出
		HttpSession session=request.getSession();
		AccountDao accountDao=new AccountDao();
	    if("login".equals(action)){
	    	
	    	/**
	    	 * 用户名  aname=admin
	    	 * 密码     apwd=0000
	    	 * 管理员 atype=0
	    	 */
	    	
	    	String aname=request.getParameter("aname");
	    	String apwd=request.getParameter("apwd");
	    	int atype=Integer.parseInt(request.getParameter("atype"));//获得类型是管理员还是普通用户
	    	Account account=accountDao.showAccount(aname, apwd, atype);  
	        if(account!=null){
	        	  session.setAttribute("account", account);//将登陆的用户信息存放到session里。
	        	  session.setMaxInactiveInterval(60*60*60*24);//设置有效时间
	        	  response.sendRedirect("deskServlet");//跳转到处理页面
	        }else{
	        	request.setAttribute("bug", "账号和密码错误");
	        	request.getRequestDispatcher("login.jsp").forward(request, response);//如果错误则跳转回登陆页面
	        	
	        }
	    }else if("exit".equals(action)){
	         session.removeAttribute("account");
	         response.sendRedirect("login.jsp");//如果是退出登陆则跳转回登陆页面
	    }
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		   this.doGet(request, response);
	}

}
