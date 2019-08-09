<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'deskM.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/style2.css" />
	<link href="tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />
	<script type="text/javascript" src="tablecloth/tablecloth.js"></script>
	<style>
		.context{border-top:dashed 1px #ccc;padding-top:20px;margin:5px;}
		.context{text-align:center;}
		.context .left{ float:left;}
		.context .center{ display:inline;}
		.context .right{ float:right;}
		body { margin:0; padding:0; background:#f1f1f1; font:70% Arial, Helvetica, sans-serif; color:#555; line-height:150%; text-align:left; }
		a { text-decoration:none; color:#057fac; }
		a:hover { text-decoration:none; color:#f1590e; }
		h1 { font: 400% KaiTi ; margin:auto; line-height:40px;text-align:center }
		#container { margin:0 auto; width:880px; background:#fff; padding-bottom:20px; }
		#content { margin-top:0px; height:77%;  position: relative; }
		p.sig { margin:0 auto; width:680px; padding:1em 0; }
		form { margin:1em 0; padding:.2em 20px; background:#eee; }
	</style>
	<script type="text/javascript">
		function banD(deid,dstate,pageno){
			if(dstate != 0 ){
				alert("有顾客正在使用该餐桌，无法停用！");
			}else{
				if(confirm("确定要停用这张餐桌吗?")){
					//跳转DeskManageServlet 停用餐桌
					window.location.href="dmanage?action=ban&deid="+deid+"&pageNo="+pageno;
				}
			}	
		}
		
		function useD(deid,pageno){
			if(confirm("确定要启用这张餐桌吗?")){
				//跳转DeskManageServlet 启用餐桌
				window.location.href="dmanage?action=use&deid="+deid+"&pageNo="+pageno;
			}
		}
		
		function showadd(){
			alert("更多功能敬请期待");
		}
	</script>
  </head>
  
  <body>
  	<div id="container">
  	<img src="images/banner.gif" width="880px" height="120" />
    <h1>餐桌管理</h1>
    <div id="content" >
    	<div class="context">
    	<div class="left"><a href="dmanage?action=add" class="a_demo">添加餐桌</a> </div>
			<div class="center">
				<strong>
	   			<c:choose>
	  			<c:when test="${account.atype eq 0 }">
				<a href="cuisine" class="a_demo">菜品管理</a> &nbsp;&nbsp;&nbsp;
				<a href="amanage" class="a_demo">账号管理</a> &nbsp;&nbsp;&nbsp;
				<a href="tmanage" class="a_demo">类型管理</a> &nbsp;&nbsp;&nbsp;
				<a href="incomeServlet" class="a_demo">统计查询</a> &nbsp;&nbsp;&nbsp;
				<a href="javascript:showadd()" class="a_demo">更多功能</a> &nbsp;&nbsp;&nbsp;
				</c:when>
				<c:otherwise>
				<a href="amanage?action=updatePwd&aid=${account.aid }" class="a_demo">修改密码</a> &nbsp;&nbsp;&nbsp;
				</c:otherwise>
	    		</c:choose>
	    		</strong>
			</div>
			<div class="right"><a href="deskServlet" class="a_demo">返回主页面</a></div>
			</div>
    
    <table border="1" style="margin-bottom: 90px;">
    <thead>
	  <tr>
	  	<th>餐桌ID</th>
        <th>餐桌名</th>
        <th>餐桌状态</th>
        <th>是否停用</th>
        <th>操作</th>
      </tr>
	  </thead>
	  
	  <c:forEach items="${deskpb.data }" var="desk">
	  <tr>
	  <td>${desk.deid }</td>
	  <td>${desk.dname }</td>
	  <td>${desk.dstate eq 0?'空桌':desk.dstate}
	      ${desk.dstate==0?'':'号订单使用中' }
	  </td>
	  <td>${desk.dexist eq 1?'在用':'停用' }</td>
	  <td>
	  <!-- 调用deskManageServlet 修改餐桌 -->
	  <a href="dmanage?action=update&deid=${desk.deid}" class="a_demo">修改餐桌名</a> | 
	  <c:choose>
	  <c:when test="${desk.dexist eq 1}">
	      <a href="javascript:banD(${desk.deid },${desk.dstate },${deskpb.pageNo } )" class="a_demo">停用该餐桌</a>
	  </c:when>
	  <c:otherwise>
	       <a href="javascript:useD(${desk.deid },${deskpb.pageNo })" class="a_demo">启用该餐桌</a></c:otherwise>
	  </c:choose>
	  </td>
	  </tr>
	  </c:forEach>
	  </table>
	  <!-- 分页 -->
	  <table width="950" height="50" border="0" cellpadding="0" cellspacing="0" style="border-top:1px #C3C3C3 solid; position:fixed;bottom:0px; width: 880;">
  		<tr>
  		<td align="center" style="text-align:center;">
	  		<a href="dmanage?pageNo=${deskpb.first }">首页</a>&nbsp;&nbsp;&nbsp;
    		<a href="dmanage?pageNo=${deskpb.up }">上一页</a>&nbsp;&nbsp;&nbsp;
    		当前第${deskpb.pageNo }页，共计${deskpb.count }页&nbsp;&nbsp;&nbsp;
    		<a href="dmanage?pageNo=${deskpb.down }">下一页</a>&nbsp;&nbsp;&nbsp;
    		<a href="dmanage?pageNo=${deskpb.last }">末页</a>&nbsp;&nbsp;&nbsp;
	  	</td>
	  	</tr>
	  </table>
	  </div>
  	</div>
  </body>
</html>
