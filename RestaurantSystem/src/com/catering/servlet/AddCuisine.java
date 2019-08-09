package com.catering.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.catering.bean.Cuisine;
import com.catering.bean.MenuType;
import com.catering.dao.CuisineDao;
import com.catering.dao.MenuTypeDao;
import com.catering.dao.MenuXun;
import com.catering.util.StringUtil;
import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;



public class AddCuisine extends HttpServlet {

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
		//request.getRequestDispatcher("cuisine").forward(request, response);
		//��ȡ��ӵĲ�Ʒ����
		String cname=new String(request.getParameter("cname").getBytes("ISO-8859-1"),"UTF-8");//��ò�Ʒ����
		CuisineDao dao1=new CuisineDao();
		MenuTypeDao dao2=new MenuTypeDao();
		double ccost=0;
		if(StringUtil.isNotEmpty(request.getParameter("ccost"))){
			ccost=Double.parseDouble(request.getParameter("ccost"));
		}
		double cprice=Double.parseDouble(request.getParameter("cprice"));//��ò˼۸�
		int mid=Integer.parseInt(request.getParameter("mid"));//��ò�����valueֵ
		//���ͼƬ·��
		String cimg=request.getParameter("cimg");
		//�жϲμӵĲ�Ʒ�Ƿ��������������������addcuisine.jsp
		if(dao1.repecname(cname)){
			List<MenuType> list=dao2.showAllType();
			request.setAttribute("mlist", list);
			request.setAttribute("cmsg", "�����Ѵ���");
			request.setAttribute("rcname",cname);
			request.getRequestDispatcher("cmanage/addcuisine.jsp").forward(request, response);
		}
		
		MenuXun dao=new MenuXun();
		Cuisine cuisine=new Cuisine();
		cuisine.setCname(cname);
		cuisine.setCcost(ccost);
		cuisine.setCprice(cprice);
		cuisine.setMid(mid);
		cuisine.setCimg(cimg);

		int cid=dao.addfood(cuisine);
		if(cid>0){
			SmartUpload su=new SmartUpload();
			su.initialize(this.getServletConfig(), request, response);
			//su.initialize(this.getServletConfig(),request,response);
			try{
				su.upload();
				//su.upload();
			}catch (Exception e) {
				// TODO: handle exception
			}
			Files files=su.getFiles();
			//Files files=su.getFiles();
			for (int i=0;i<files.getCount();i++){
				File file=files.getFile(i);
				String path=this.getServletContext().getRealPath("images");
				String filename=file.getFileName();
				String jiequ=filename.substring(filename.lastIndexOf("."));
				String newFileName=cid+jiequ;
				path=path+"/"+newFileName;
				try {
					file.saveAs(path);
				} catch (Exception e) {
					// TODO: handle exception
				}
				cuisine.setCid(cid);
				cuisine.setCimg(newFileName);

				if(dao.updateImage(cuisine))
					response.sendRedirect("cuisine");
			}
		}
		
	}

}
