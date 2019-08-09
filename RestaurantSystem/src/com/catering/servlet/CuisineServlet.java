package com.catering.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.catering.bean.Cuisine;
import com.catering.bean.MenuType;
import com.catering.bean.PageBean;
import com.catering.dao.CuisineDao;
import com.catering.dao.MenuTypeDao;
import com.catering.util.StringUtil;

public class CuisineServlet extends HttpServlet {

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
		//设置编码
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");//当前action为null
		MenuTypeDao dao1=new MenuTypeDao();
		CuisineDao cuisineDao=new CuisineDao();
		int pageNo=1,pageSize=8;
		if(request.getParameter("pageNo")!=null){
			pageNo=Integer.parseInt(request.getParameter("pageNo"));
		}
		if(request.getParameter("pageSize")!=null){
			pageSize=Integer.parseInt(request.getParameter("pageSize"));
		}
		if(!StringUtil.isNotEmpty(action)){
			//action为null或""
			List<Cuisine> clist=cuisineDao.findAllByPage(pageNo, pageSize);//根据页号查找记录
			PageBean<Cuisine> pBean=new PageBean<Cuisine>();
			pBean.setData(clist);
			pBean.setPageNo(pageNo);
			pBean.setPageSize(pageSize);
			pBean.setTotal(cuisineDao.getCount());//获得总条数
			request.setAttribute("pBean",pBean);
			request.getRequestDispatcher("cmanage/cuisine.jsp").forward(request, response);
		}else if("add".equals(action)){
			//action为"add"时
			List<MenuType> list=dao1.showAllType();
			request.setAttribute("mlist", list);
			request.getRequestDispatcher("cmanage/addcuisine.jsp").forward(request, response);
		}
		else if("shangjia".equals(action)){

			int cid=Integer.parseInt(request.getParameter("cid"));
			if(cuisineDao.deleteCuisine(cid, 1)){
				List<Cuisine> clist=cuisineDao.findAllByPage(pageNo, pageSize);
				PageBean<Cuisine> pBean=new PageBean<Cuisine>();
				pBean.setData(clist);
				pBean.setPageNo(pageNo);
				pBean.setPageSize(pageSize);
				pBean.setTotal(cuisineDao.getCount());
				request.setAttribute("pBean", pBean);
				request.getRequestDispatcher("cmanage/cuisine.jsp").forward(request, response);
			}
		}
		else if("xiajia".equals(action)){
			int cid=Integer.parseInt(request.getParameter("cid"));
			if(cuisineDao.deleteCuisine(cid, 0)){
				List<Cuisine> clist=cuisineDao.findAllByPage(pageNo, pageSize);
				PageBean<Cuisine> pBean=new PageBean<Cuisine>();
				pBean.setData(clist);
				pBean.setPageNo(pageNo);
				pBean.setPageSize(pageSize);
				pBean.setTotal(cuisineDao.getCount());
				request.setAttribute("pBean", pBean);
				request.getRequestDispatcher("cmanage/cuisine.jsp").forward(request, response);
			}
		}
		else if("update".equals(action)){
			int cid=Integer.parseInt(request.getParameter("cid"));
			Cuisine cu=cuisineDao.findCuiById(cid);
			List<MenuType> list=dao1.showAllType();
			request.setAttribute("mlist", list);
			request.setAttribute("cu", cu);
			request.getRequestDispatcher("cmanage/updatecuisine.jsp").forward(request, response);
		}
		else if("updateover".equals(action)){
			int cid=Integer.parseInt(request.getParameter("cid"));
			double cprice=Double.parseDouble(request.getParameter("cprice"));
			int mid=Integer.parseInt(request.getParameter("type"));
			Cuisine cu=new Cuisine();
			cu.setCid(cid);
			cu.setCprice(cprice);
			cu.setMid(mid);
			if(cuisineDao.updateCuisine(cu)){
				response.sendRedirect("cmanage/success.jsp");
			}
			
		}
	}

}
