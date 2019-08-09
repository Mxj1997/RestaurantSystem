package com.catering.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.catering.dao.DeskDao;
import com.catering.dao.FormDao;
import com.catering.dao.ItemDao;
import com.catering.util.StringUtil;

public class SettleServlet extends HttpServlet {

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
			//cid="+id+"&num="+num+"&action=update&deid="+deid
			String action=request.getParameter("action");
			FormDao formDao=new FormDao();
			ItemDao itemDao=new ItemDao();
			DeskDao deskDao=new DeskDao();
			if("update".equals(action)){
				int deid=Integer.parseInt(request.getParameter("deid"));
				int fid=deskDao.showState(deid);
				//菜单编号
				int cid=Integer.parseInt(request.getParameter("cid"));
				//菜数量
				int num=Integer.parseInt(request.getParameter("num"));
				if(itemDao.updateItemNum(fid, cid, num)){
					request.setAttribute("deid", deid);
					request.getRequestDispatcher("fromServlet").forward(request, response);
				}
			}else if("settle".equals(action)){
				int deid=Integer.parseInt(request.getParameter("deid"));
				int fid=deskDao.showState(deid);
				double incom=Double.parseDouble(request.getParameter("incom"));
				int fstate=1;
				if(formDao.settleForm(fstate, incom, fid)){
					int dstate=0;
					deskDao.updateDeskState(deid, dstate);
					response.sendRedirect("deskServlet");
				}
			}
			//cid="+cid+"&deid="+deid+"&action=deleteItem
			else if("deleteItem".equals(action)){
				int deid=Integer.parseInt(request.getParameter("deid"));
				int fid=deskDao.showState(deid);
				int cid=Integer.parseInt(request.getParameter("cid"));
				if(itemDao.deleteItem(fid, cid)){
					request.setAttribute("deid", deid);
					request.getRequestDispatcher("fromServlet").forward(request, response);
				}
			}
			//deid="+id+"&action=cancel
			else if("cancel".equals(action)){
				int deid=Integer.parseInt(request.getParameter("deid"));
				int fid=deskDao.showState(deid);
				double sum=0;	//实际的结账收入
				int dstate=0;//状态
				int fstate=0;
				if(formDao.settleForm(fstate, sum, fid)){
					deskDao.updateDeskState(deid, dstate);
					response.sendRedirect("deskServlet");
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
