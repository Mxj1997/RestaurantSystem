<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/style2.css">

	
	   <script type="text/javascript">
	   
	        function kong(id){
	             window.location.href="pageMenu?deid="+id;
	        }
	        
	        function man(id,state){
	              window.location.href="fromServlet?deid="+id+"&fid="+state;
	        }
	        function showAdd(){
	            alert("更多功能敬请期待");
	        }
	        function exit(){
	              if(confirm("你确定要退出吗?")){
	                     window.location.href="LoginMenuServlet?action=exit";
	              }
	        }
	   </script>
	   
	   <style type="text/css">
	        body,td,th{
                         font-size: 12px;
                         line-height: 18px;
	                  }
	        .contain{
	               width: 950px;
	               text-align: left;
	               background: #FEFEF6;
	        } 
	        
	        .tab{
	            width: 437px;
	            height: 292px;
	            padding: 4px;
	            border: solid 1px #C3C3C3;
	            text-align: center;
	            margin: 10px;
	            
	        }
	        .tit{
	            width: 437px;
	            text-align: center;
	            display: block;
	            background: #EBEBEB;
	            line-height: 20px;
	            color: #FF0000;
	        }
	        
	        body{
	             background-color: #EBEBEB;
	        }
	        
	        .tb1{
	             background:url(images/daohang2.jpg)
	             font-size: 13px;
	        }
	        
	        body{
	              
	               font-size: 13px;
	        }
	        
	        .lg{
	           width: 950px; height: 800px; margin: 100px auto;
	        }
	   </style>
  </head>
  
  <body>
        <center>
             <div class="contain">
                     <table width="950" height="120" border="0" cellpadding="0" cellspacing="0">
                           <tr>
                               <td>
                                    <img src="images/banner.gif" width="950" height="120"> 
                               </td>
                           </tr>
                     </table>
                     
  <table width="950" height="30" border="0" cellpadding="0" cellspacing="0" class="tb1" style="border-top: 1px">
          <tr>
                <td align="center">
                        <strong>
                        <c:choose>
                               <c:when test="${account.atype eq 0 }">
                                      <a href="cuisine" class="a_demo">菜单管理</a>&nbsp;&nbsp;&nbsp;
                                      <a href="dmanage" class="a_demo">餐桌管理</a>&nbsp;&nbsp;&nbsp;
                                      <a href="amanage" class="a_demo">人员管理</a>&nbsp;&nbsp;&nbsp;
                                      <a href="tmanage" class="a_demo">类型管理</a>&nbsp;&nbsp;&nbsp;
                                      <a href="incomeServlet" class="a_demo">统计查询</a>&nbsp;&nbsp;&nbsp;
                                      <a href="javascript:showAdd()" class="a_demo">更多功能</a>&nbsp;&nbsp;&nbsp;
                                      <a href="javascript:exit()" class="a_demo">退出登录</a>&nbsp;&nbsp;&nbsp;
                               </c:when>
                               
                               <c:otherwise>
                                      <a href="amanage?action=updatePwd&aid=${account.aid }" class="a_demo">修改密码</a>&nbsp;&nbsp;&nbsp;
                                      <a href="javascript:exit()" class="a_demo">退出登录</a>
                                 
                               </c:otherwise>
                        </c:choose> 
                     </strong>      
                </td>
          </tr>
  </table>
  
  
  <table width="950" height="60" border="0" cellpadding="0" cellspacing="0" class="tb1" style="border-top: 1px">
          <tr>
                <td align="center">
                     <a href="deskServlet?type=1">查询全部餐桌</a>
                     <a href="deskServlet?type=2" >查询在餐餐桌</a>
                     <a href="deskServlet?type=3" >查询空位餐桌</a>
                </td>
          </tr> 
  </table>
  
<table width="950" height="750" border="0" cellpadding="0" cellspacing="0" style="border-top: 1px" class="tb1">
  <tr> 
   <c:forEach items="${pBean.data }" var="desk" varStatus="status">
         <td width="300" height="305" align="center" valign="top">
		<c:choose>
		      <c:when test="${desk.dstate eq 0 }">
		               <div class="tab">
		                       <a href="javascript:kong(${desk.deid })">
		                              <img src="images/kongzuo.jpg" width="300" height="285">
		                       </a>      
		               </div>        
		      </c:when>
		      
		      <c:otherwise>
		              <div class="tab">
		                     <a href="javascript:man(${desk.deid },${desk.dstate })">
		                           <img alt="" src="images/manzuo.jpg" width="300" height="285">
		                     </a>     
		              </div>
		      </c:otherwise>
		
		</c:choose>
                 
        <div class="tit" >${desk.dname }</div> 
     </td> 
         <c:if test="${status.count%2 eq 0}">
              <tr></tr>
         </c:if>
   </c:forEach>
  </tr>   
</table>
  
<table width="950" height="50" border="0" cellpadding="0" cellspacing="0" style="border-top: 1px; margin-top: 10px">
      <tr>
          <td align="center">
                   <a href="deskservlet?pageNo=${pBean.first}&type=${type}" class="a_demo">首页</a>&nbsp;&nbsp;&nbsp;
                   <a href="deskservlet?pageNo=${pBean.up}&type=${type}" class="a_demo">上一页</a>&nbsp;&nbsp;&nbsp;
                                                   当前第${pBean.pageNo }页,共计${pBean.count }页&nbsp;&nbsp;&nbsp;
                   <a href="deskservlet?pageNo=${pBean.down}&type=${type}" class="a_demo">下一页</a>&nbsp;&nbsp;&nbsp;
                   <a href="deskservlet?pageNo=${pBean.last}&type=${type}" class="a_demo">尾页</a>&nbsp;&nbsp;&nbsp;
          </td>
      </tr>
</table>
</div>
</center> 
  </body>
</html>
