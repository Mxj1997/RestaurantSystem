package com.catering.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.catering.bean.MenuType;
import com.catering.bean.PageBean;
import com.catering.dao.MenuTypeDao;
import com.catering.util.StringUtil;

public class TmanageServlet extends HttpServlet {

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

		request.setCharacterEncoding("UTF-8");
		String action=request.getParameter("action");
		MenuTypeDao dao=new MenuTypeDao();
		int pageNo=1;
		int pageSize=8;
		if(request.getParameter("pageNo")!=null){
			pageNo=Integer.parseInt(request.getParameter("pageNo"));
		}
		if(request.getParameter("pageSize")!=null){
			pageSize=Integer.parseInt(request.getParameter("pageSize"));
		}
		
		if(!StringUtil.isNotEmpty(action)){
			//actin为null或""
			List<MenuType> mlist=dao.findAllByPage(pageNo, pageSize);
			PageBean<MenuType> mtpb=new PageBean<MenuType>();
			mtpb.setData(mlist);
			mtpb.setTotal(dao.getCount());
			mtpb.setPageNo(pageNo);
			mtpb.setPageSize(pageSize);
			request.setAttribute("mtpb", mtpb);
			request.getRequestDispatcher("tmanage/mtypeM.jsp").forward(request, response);
		}else if("add".equals(action)){
			//action为"add"
			response.sendRedirect("tmanage/mtypeAdd.jsp");
		}else if("addover".equals(action)){
			//action为"addover"
			String mname=request.getParameter("mtype");
			MenuType mt= new MenuType();
			mt.setMtype(mname);
			//0表示停用，1表示在用
			mt.setMexist(1);
			if(dao.addType(mt));{
				response.sendRedirect("tmanage/success.jsp");
			}
		}else if("ban".equals(action)){
			int mid=Integer.parseInt(request.getParameter("mid"));
			if(dao.existType(mid, 0)){
				List<MenuType> mlist=dao.findAllByPage(pageNo, pageSize);
				PageBean<MenuType> mtpb=new PageBean<MenuType>();
				mtpb.setData(mlist);
				mtpb.setPageNo(pageNo);
				mtpb.setPageSize(pageSize);
				mtpb.setTotal(dao.getCount());
				request.setAttribute("mtpb", mtpb);
				request.getRequestDispatcher("tmanage/mtypeM.jsp").forward(request, response);
			}
		}else if("use".equals(action)){
			int mid=Integer.parseInt(request.getParameter("mid"));
			if(dao.existType(mid, 1)){
				List<MenuType> mlist=dao.findAllByPage(pageNo, pageSize);
				PageBean<MenuType> mtpb=new PageBean<MenuType>();
				mtpb.setData(mlist);
				mtpb.setPageNo(pageNo);
				mtpb.setPageSize(pageSize);
				mtpb.setTotal(dao.getCount());
				request.setAttribute("mtpb", mtpb);
				request.getRequestDispatcher("tmanage/mtypeM.jsp").forward(request, response);
			}
		}
		else if("updateover".equals(action)){

			int mid=Integer.parseInt(request.getParameter("mid"));
			String mtype=new String(request.getParameter("mtype").getBytes("ISO-8859-1"),"UTF-8");
			MenuType mt= new MenuType();
			mt.setMid(mid);
			mt.setMtype(mtype);
			if(dao.updateType(mid, mtype)){
				response.sendRedirect("tmanage/success.jsp");
			}
		}
		
	}

}
