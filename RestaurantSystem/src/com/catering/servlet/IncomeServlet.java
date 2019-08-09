package com.catering.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.catering.bean.Cuisine;
import com.catering.bean.PageBean;
import com.catering.dao.CuisineDao;
import com.catering.dao.FormDao;
import com.catering.dao.ItemDao;
import com.catering.util.StringUtil;

public class IncomeServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			String action=request.getParameter("action");
			ItemDao itemDao = new ItemDao();
			FormDao formDao=new FormDao();
			CuisineDao cuisineDao=new CuisineDao();
			if(!StringUtil.isNotEmpty(action)){ 	//初始化
				cuisineDao.resetTotal();
			}
			int total=0,pageNo=1,pageSize=8;
			if(request.getParameter("pageNo")!=null){
				pageNo=Integer.parseInt(request.getParameter("pageNo"));
			}
			if(request.getParameter("pageSize")!=null){
				pageSize=Integer.parseInt(request.getParameter("pageSize"));
			}
			List<Cuisine> clist=cuisineDao.findAllByPage(pageNo, pageSize);
			PageBean<Cuisine> pBean=new PageBean<Cuisine>();
			pBean.setData(clist);
			pBean.setPageNo(pageNo);
			pBean.setPageSize(pageSize);
			pBean.setTotal(cuisineDao.getCount());
			if(!StringUtil.isNotEmpty(action)){ 
				request.setAttribute("pBean",pBean);
				request.getRequestDispatcher("statistics/statistics.jsp").forward(request, response);
			}else if("total".equals(action)){     //查询该菜品总数				
				int cid=Integer.parseInt(request.getParameter("cid"));
				total=itemDao.getTotal(cid);
				cuisineDao.updateTotal(total,cid);
				request.getRequestDispatcher("incomeServlet?action=totalover").forward(request, response);
			}else if("income".equals(action)){     //查询收益	
				String date=request.getParameter("date");
				int income=formDao.getIncome(date);				
				request.setAttribute("income", income);
				request.setAttribute("date", date);
				request.setAttribute("pBean",pBean);
				request.getRequestDispatcher("statistics/statistics.jsp").forward(request, response);
			}else if("totalover".equals(action)){ 
				request.setAttribute("pBean",pBean);
				request.getRequestDispatcher("statistics/statistics.jsp").forward(request, response);
			}
			
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doGet(request, response);

	}

}
