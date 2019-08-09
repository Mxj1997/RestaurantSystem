package com.catering.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.catering.bean.Cuisine;
import com.catering.bean.MenuPaging;
import com.catering.bean.MenuType;
import com.catering.bean.PageBean;
import com.catering.dao.CuisineDao;
import com.catering.dao.DeskDao;
import com.catering.dao.MenuTypeDao;
import com.catering.util.StringUtil;

public class PageMenuServlet extends HttpServlet {

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

			int deid=Integer.parseInt(request.getParameter("deid"));//²Í×Àid
			DeskDao deskDao=new DeskDao();
			CuisineDao cuisineDao=new CuisineDao();
			
			int pageNo=1;
			int pageSize=9;
			if(StringUtil.isNotEmpty(request.getParameter("pageNo"))){
				pageNo=Integer.parseInt(request.getParameter("pageNo"));
			}
			if(StringUtil.isNotEmpty(request.getParameter("pageSize"))){
		  
				pageSize=Integer.parseInt(request.getParameter("pageSize"));
			}
			int price1=0;
			int price2=0;
			if(StringUtil.isNotEmpty(request.getParameter("price1"))){
				price1=Integer.parseInt(request.getParameter("price1"));
			}
			if(StringUtil.isNotEmpty(request.getParameter("price2"))){
				price2=Integer.parseInt(request.getParameter("price2"));
			}
			String cname=request.getParameter("cname");
			String ctype=request.getParameter("ctype");
			MenuPaging menuPaging=new MenuPaging(price1, price2, cname, ctype);
			List<Cuisine> clist=cuisineDao.pageCuisine(pageNo, pageSize, menuPaging);
			PageBean<Cuisine> pBean=new PageBean<Cuisine>();
			pBean.setData(clist);
			pBean.setPageNo(pageNo);
			pBean.setPageSize(pageSize);
			pBean.setTotal(cuisineDao.getCount(menuPaging));
			deid=Integer.parseInt(request.getParameter("deid"));
			int dstate=deskDao.showState(deid);	//»ñµÃ²Í×À×´Ì¬(¶©µ¥ID)
			MenuTypeDao menuTypeDao=new MenuTypeDao();
			List<MenuType> menuTypeList=menuTypeDao.showAllType();
					
					
			request.setAttribute("mplist", menuTypeList);
			request.setAttribute("mp", menuPaging);
			request.setAttribute("pBean", pBean);
			request.setAttribute("deid", deid);
			request.setAttribute("dstate", dstate);
			
			request.getRequestDispatcher("dishes.jsp").forward(request, response);

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
