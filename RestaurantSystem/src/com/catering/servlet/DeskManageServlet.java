package com.catering.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.catering.bean.Cuisine;
import com.catering.bean.Desk;
import com.catering.bean.PageBean;
import com.catering.dao.DeskDao;
import com.catering.util.StringUtil;

public class DeskManageServlet extends HttpServlet {

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

		request.setCharacterEncoding("UTF-8");
		request.getParameter("action");
		String action=request.getParameter("action");
		DeskDao dao=new DeskDao();

		int pageNo=1,pageSize=10;
		if(request.getParameter("pageNo")!=null){
			pageNo=Integer.parseInt(request.getParameter("pageNo"));
		}
		if(request.getParameter("pageSize")!=null){
			pageSize=Integer.parseInt(request.getParameter("pageSize"));
		}
		if(!StringUtil.isNotEmpty(action)){
			//action为null或""
			List<Desk> desks=dao.findDeskByPage(pageNo, pageSize, true);
			PageBean<Desk> deskpb=new PageBean<Desk>();
			deskpb.setData(desks);
			deskpb.setPageNo(pageNo);
			deskpb.setPageSize(pageSize);
			deskpb.setTotal(dao.getCount(true));//获得总条数
			request.setAttribute("deskpb",deskpb);
			request.getRequestDispatcher("dmanage/deskM.jsp").forward(request, response);
		}else if("update".equals(action)){
			int deid=Integer.parseInt(request.getParameter("deid"));
			Desk desk=dao.findDeskById(deid);
			request.setAttribute("desk", desk);
			request.getRequestDispatcher("dmanage/deskUpdate.jsp").forward(request, response);
		}else if("updateover".equals(action)){
			int deid=Integer.parseInt(request.getParameter("deid"));
			String dname=request.getParameter("dname");
			if(dao.updateDeskName(deid, dname)){
				response.sendRedirect("dmanage/success.jsp");
			}
		}
		else if("ban".equals(action)){
			int deid=Integer.parseInt(request.getParameter("deid"));
			if(dao.banDesk(deid, 0)){
				List<Desk> desks=dao.findDeskByPage(pageNo, pageSize, true);
				PageBean<Desk> deskpb=new PageBean<Desk>();
				deskpb.setData(desks);
				deskpb.setPageNo(pageNo);
				deskpb.setPageSize(pageSize);
				deskpb.setTotal(dao.getCount(true));//获得总条数
				request.setAttribute("deskpb",deskpb);
				request.getRequestDispatcher("dmanage/deskM.jsp").forward(request, response);
			}
		}
		else if("use".equals(action)){
			int deid=Integer.parseInt(request.getParameter("deid"));
			if(dao.banDesk(deid, 1)){
				List<Desk> desks=dao.findDeskByPage(pageNo, pageSize, true);
				PageBean<Desk> deskpb=new PageBean<Desk>();
				deskpb.setData(desks);
				deskpb.setPageNo(pageNo);
				deskpb.setPageSize(pageSize);
				deskpb.setTotal(dao.getCount(true));//获得总条数
				request.setAttribute("deskpb",deskpb);
				request.getRequestDispatcher("dmanage/deskM.jsp").forward(request, response);
			}
		}else if("add".equals(action)){
			response.sendRedirect("dmanage/deskAdd.jsp");
		}else if("addover".equals(action)){
			String dname=request.getParameter("dname");
			Desk desk=new Desk();
			desk.setDname(dname);
			desk.setDstate(0);
			desk.setDexist(1);
			int rows=dao.findDeskByName(dname);
			if(rows==0){
				if(dao.addDesk(desk)){
					response.sendRedirect("dmanage/success.jsp");
				}
			}
		}
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

			this.doGet(request, response);
	}

}
