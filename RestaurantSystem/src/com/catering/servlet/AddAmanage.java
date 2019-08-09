package com.catering.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.catering.bean.Account;
import com.catering.dao.AccountDao;

public class AddAmanage extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String aname=request.getParameter("aname");
	    	String apwd=request.getParameter("apwd");
	    	int atype=Integer.parseInt(request.getParameter("atype"));
	    	Account account=new Account();
	    	AccountDao accountDao=new AccountDao();
	    	account.setAname(aname);
	    	account.setApwd(apwd);
	    	account.setAtype(atype);
	    	account.setAexist(1);
	    	if(accountDao.findByNamePwd(aname,apwd)){
	    		request.setAttribute("bug", "该用户已存在，添加失败");
	    		request.getRequestDispatcher("amanage/addUser.jsp").forward(request, response);
	    	}
	    	else
	    	{
	    		if(accountDao.addUser(account)){
		    		response.sendRedirect("amanage");
	    		}
	    	}
	}

}
