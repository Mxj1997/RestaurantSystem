package com.catering.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.catering.bean.Account;
import com.catering.bean.OrderItem;
import com.catering.dao.DeskDao;
import com.catering.dao.FormDao;
import com.catering.dao.ItemDao;

public class OrderServlet extends HttpServlet {

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
			/*���
			cid=${clist.cid }&pageNo=${pBean.pageNo}&deid=${deid}&cname=${mp.cname}
			&ctype=${mp.ctype}&price1=${mp.price1}&price2=${mp.price2}&action=click"
*/			
		request.setCharacterEncoding("UTF-8");
			String action=request.getParameter("action");
			HttpSession session=request.getSession();
			FormDao formDao=new FormDao();
			ItemDao itemDao=new ItemDao();
			DeskDao deskDao=new DeskDao();
			if("click".equals(action)){
				int deid=Integer.parseInt(request.getParameter("deid"));//��ȡ����ID
				int state=deskDao.showState(deid);						//��ȡ����״̬
				int cid=Integer.parseInt(request.getParameter("cid"));	//��ȡ���˵�ID
				Account account=(Account)session.getAttribute("account");//��session���ȡ�û���Ϣ
				int aid=account.getAid();	//��ȡ���û�ID
				if(state==0){
					//����״̬
					int fid=formDao.addForm(deid, aid);//���һ��������Ϣ�����ض���ID��
					if(fid!=0){
						if(itemDao.addItem(fid, cid)){
							//����������
							if(deskDao.updateDeskState(deid, fid)){
								//���ò�����d_state����Ϊ����ID
								request.setAttribute("deid", deid);
								request.getRequestDispatcher("pageMenu").forward(request, response);
							}
						}
					}
				}
				else{

					//����״̬
					Map<Integer,OrderItem> map=new HashMap<Integer, OrderItem>();
					OrderItem item=map.get(cid);
					if(item==null){
						if(itemDao.addItem(state, cid)){
							request.setAttribute("deid", deid);
							request.getRequestDispatcher("pageMenu").forward(request, response);
						}
					}
					else{
						int sum=item.getInum()+1;
						if(itemDao.updateItemNum(state, cid, sum)){
							request.setAttribute("deid", deid);
							request.getRequestDispatcher("pageMenu").forward(request, response);
						}
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
