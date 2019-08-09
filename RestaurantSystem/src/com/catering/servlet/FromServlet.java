package com.catering.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.catering.bean.OrderForm;
import com.catering.bean.OrderItem;
import com.catering.dao.DeskDao;
import com.catering.dao.ItemDao;

public class FromServlet extends HttpServlet {

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

		DeskDao deskDao=new DeskDao();
		ItemDao itemDao=new ItemDao();
		OrderForm orderForm=new OrderForm();
		//获取满座的id
		int deid=Integer.parseInt(request.getParameter("deid"));
		//查找到桌子的状态
		int fid=deskDao.showState(deid);
		//查询餐桌的名称
		String dname=deskDao.findDeskById(deid).getDname();
		Map<Integer,OrderItem> item=itemDao.showItems(fid);
		double sum=0;
		Iterator<OrderItem> it=item.values().iterator();
		while(it.hasNext()){
			sum+=it.next().getSum();
		}
		
		orderForm.setTotal(sum);
		orderForm.setItems(item);
		orderForm.setDeid(deid);
		request.setAttribute("dname", dname);
		request.setAttribute("orderForm", orderForm);
		request.getRequestDispatcher("form.jsp").forward(request, response);
		
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
