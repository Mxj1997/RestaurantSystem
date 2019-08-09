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
	        	  //type表示餐桌类型，1为全部，2为在餐，3为空位
                  int type=1;
                  if(StringUtil.isNotEmpty(request.getParameter("type"))){
	        		   type=Integer.parseInt(request.getParameter("type"));
	        	  }
                  
                  DeskDao deskDao=new DeskDao();
                   // 查询餐桌    餐桌保存在deskList集合                1        8         1  
                  List<Desk> deskList=deskDao.pageDesk(pageNo, pageSize, type);
                  //创建pageBean对象 泛型Desk
                  PageBean<Desk>  pBean=new PageBean<Desk>(); 
                  pBean.setData(deskList);//数据保存在这里
                  pBean.setPageNo(pageNo);//保存页号
                  pBean.setPageSize(pageSize);//保存每页的记录数
                  pBean.setTotal(deskDao.getDeskCount(type));
                  request.setAttribute("type", type);
                  request.setAttribute("pBean", pBean);
	              request.getRequestDispatcher("index.jsp").forward(request, response);//转发到index.jsp页面

	        }else{
	        	request.setAttribute("bug", "账号和密码错误");
	        	request.getRequestDispatcher("login.jsp").forward(request, response);	
	        	
	        }
	
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        this.doGet(request, response);
	     
	}

}
