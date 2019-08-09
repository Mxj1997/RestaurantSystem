package com.catering.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.catering.bean.Account;
import com.catering.bean.PageBean;
import com.catering.dao.AccountDao;
import com.catering.util.StringUtil;

public class Amanage extends HttpServlet {

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
		String action=request.getParameter("action");//当前action为null
		HttpSession session=request.getSession();
		Account user=(Account)session.getAttribute("account");
		AccountDao accountDao=new AccountDao();
		Account account=new Account();
		int pageNo=1,pageSize=8;
		if(request.getParameter("pageNo")!=null){
			pageNo=Integer.parseInt(request.getParameter("pageNo"));
		}
		if(request.getParameter("pageSize")!=null){
			pageSize=Integer.parseInt(request.getParameter("pageSize"));
		}
		if(!StringUtil.isNotEmpty(action)){
			//action为null或""
			List<Account> alist=accountDao.findAllAccount(pageNo, pageSize);//根据页号查找人员
			PageBean<Account> pBean=new PageBean<Account>();
			pBean.setData(alist);
			pBean.setPageNo(pageNo);
			pBean.setPageSize(pageSize);
			pBean.setTotal(accountDao.getCount());//获得总条数
			request.setAttribute("pBean",pBean);
			request.setAttribute("uid",user.getAid());
			request.setAttribute("uname",user.getAname());
			request.getRequestDispatcher("amanage/amanage.jsp").forward(request, response);
		}
		else if("add".equals(action)){
			response.sendRedirect("amanage/addUser.jsp");
		}
		//updatePwd&aid=${account.aid }
		else if("updatePwd".equals(action)){
			int aid=Integer.parseInt(request.getParameter("aid"));
			account.setAid(aid);
			account.setApwd(accountDao.findPwdByAid(account));
			if(account.getApwd()!=""){
				request.setAttribute("account", account);
				request.getRequestDispatcher("amanage/updatePwd.jsp").forward(request, response);
			}
			
		}
		else if("updatepwdover".equals(action)){
			int aid=Integer.parseInt(request.getParameter("aid"));
			String newpwd=request.getParameter("newpwd");
			account.setAid(aid);
			account.setApwd(newpwd);
			if(accountDao.updatePwd(account)){
				List<Account> alist=accountDao.findAllAccount(pageNo, pageSize);//根据页号查找人员
				PageBean<Account> pBean=new PageBean<Account>();
				pBean.setData(alist);
				pBean.setPageNo(pageNo);
				pBean.setPageSize(pageSize);
				pBean.setTotal(accountDao.getCount());//获得总条数
				request.setAttribute("pBean",pBean);
				request.setAttribute("uid",user.getAid());
				request.setAttribute("uname",user.getAname());
				request.getRequestDispatcher("amanage/amanage.jsp").forward(request, response);
			}
			
		}
		//action=use&aid="+aid+"&pageNo="+pageno
		else if("use".equals(action)){
			int aid=Integer.parseInt(request.getParameter("aid"));
			account.setAid(aid);
			account.setAexist(1);
			if(accountDao.setAble(account)){
				List<Account> alist=accountDao.findAllAccount(pageNo, pageSize);//根据页号查找人员
				PageBean<Account> pBean=new PageBean<Account>();
				pBean.setData(alist);
				pBean.setPageNo(pageNo);
				pBean.setPageSize(pageSize);
				pBean.setTotal(accountDao.getCount());//获得总条数
				request.setAttribute("pBean",pBean);
				request.setAttribute("uid",user.getAid());
				request.setAttribute("uname",user.getAname());
				request.getRequestDispatcher("amanage/amanage.jsp").forward(request, response);
			}
		}
		//action=ban&aid="+aid+"&pageNo="+pageno
		else if("ban".equals(action)){
			int aid=Integer.parseInt(request.getParameter("aid"));
			account.setAid(aid);
			account.setAexist(0);
			if(accountDao.setAble(account)){
				List<Account> alist=accountDao.findAllAccount(pageNo, pageSize);//根据页号查找人员
				PageBean<Account> pBean=new PageBean<Account>();
				pBean.setData(alist);
				pBean.setPageNo(pageNo);
				pBean.setPageSize(pageSize);
				pBean.setTotal(accountDao.getCount());//获得总条数
				request.setAttribute("pBean",pBean);
				request.setAttribute("uid",user.getAid());
				request.setAttribute("uname",user.getAname());
				request.getRequestDispatcher("amanage/amanage.jsp").forward(request, response);
			}
		}
		else if("updateType".equals(action)){
			int aid=Integer.parseInt(request.getParameter("aid"));
			int atype=Integer.parseInt(request.getParameter("atype"));
			account.setAid(aid);
			account.setAtype(atype);
			if(accountDao.setType(account)){
				List<Account> alist=accountDao.findAllAccount(pageNo, pageSize);//根据页号查找人员
				PageBean<Account> pBean=new PageBean<Account>();
				pBean.setData(alist);
				pBean.setPageNo(pageNo);
				pBean.setPageSize(pageSize);
				pBean.setTotal(accountDao.getCount());//获得总条数
				request.setAttribute("pBean",pBean);
				request.setAttribute("uid",user.getAid());
				request.setAttribute("uname",user.getAname());
				request.getRequestDispatcher("amanage/amanage.jsp").forward(request, response);
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
