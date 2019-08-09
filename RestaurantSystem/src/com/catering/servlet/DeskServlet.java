package com.catering.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.catering.bean.Account;
import com.catering.bean.Desk;
import com.catering.bean.PageBean;
import com.catering.dao.DeskDao;
import com.catering.util.StringUtil;

public class DeskServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    Account account=(Account)request.getSession().getAttribute("account");
	        if(account!=null){
	        	  
	        	  int pageNo=1;
	        	  int pageSize=8;
	        	  if(StringUtil.isNotEmpty(request.getParameter("pageNo"))){
	        		  pageNo=Integer.parseInt(request.getParameter("pageNo"));
	        	  }
	        	  if(StringUtil.isNotEmpty(request.getParameter("pageSize"))){
	        		  
	        		  pageSize=Integer.parseInt(request.getParameter("pageSize"));
	        	  }
	        	  //type��ʾ�������ͣ�1Ϊȫ����2Ϊ�ڲͣ�3Ϊ��λ
                  int type=1;
                  if(StringUtil.isNotEmpty(request.getParameter("type"))){
	        		   type=Integer.parseInt(request.getParameter("type"));
	        	  }
                  
                  DeskDao deskDao=new DeskDao();
                   // ��ѯ����    ����������deskList����                1        8         1  
                  List<Desk> deskList=deskDao.pageDesk(pageNo, pageSize, type);
                  //����pageBean���� ����Desk
                  PageBean<Desk>  pBean=new PageBean<Desk>(); 
                  pBean.setData(deskList);//���ݱ���������
                  pBean.setPageNo(pageNo);//����ҳ��
                  pBean.setPageSize(pageSize);//����ÿҳ�ļ�¼��
                  pBean.setTotal(deskDao.getDeskCount(type));
                  request.setAttribute("type", type);
                  request.setAttribute("pBean", pBean);
	              request.getRequestDispatcher("index.jsp").forward(request, response);//ת����index.jspҳ��

	        }else{
	        	request.setAttribute("bug", "�˺ź��������");
	        	request.getRequestDispatcher("login.jsp").forward(request, response);	
	        	
	        }
	
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        this.doGet(request, response);
	     
	}

}
