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
		String action=request.getParameter("action");//��ȡ��action��ֵ���ж��ǵ�½�����˳�
		HttpSession session=request.getSession();
		AccountDao accountDao=new AccountDao();
	    if("login".equals(action)){
	    	
	    	/**
	    	 * �û���  aname=admin
	    	 * ����     apwd=0000
	    	 * ����Ա atype=0
	    	 */
	    	
	    	String aname=request.getParameter("aname");
	    	String apwd=request.getParameter("apwd");
	    	int atype=Integer.parseInt(request.getParameter("atype"));//��������ǹ���Ա������ͨ�û�
	    	Account account=accountDao.showAccount(aname, apwd, atype);  
	        if(account!=null){
	        	  session.setAttribute("account", account);//����½���û���Ϣ��ŵ�session�
	        	  session.setMaxInactiveInterval(60*60*60*24);//������Чʱ��
	        	  response.sendRedirect("deskServlet");//��ת������ҳ��
	        }else{
	        	request.setAttribute("bug", "�˺ź��������");
	        	request.getRequestDispatcher("login.jsp").forward(request, response);//�����������ת�ص�½ҳ��
	        	
	        }
	    }else if("exit".equals(action)){
	         session.removeAttribute("account");
	         response.sendRedirect("login.jsp");//������˳���½����ת�ص�½ҳ��
	    }
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		   this.doGet(request, response);
	}

}
