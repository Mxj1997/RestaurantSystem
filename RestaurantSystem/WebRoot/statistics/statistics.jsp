<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'cuisine.jsp' starting page</title>
    
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
		#content { margin-top:0px; height:78% ;  position: relative;}
		p.sig { margin:0 auto; width:680px; padding:1em 0; }
		form { margin:1em 0; padding:.2em 20px; background:#eee; }
	</style>
	<script type="text/javascript">
	function showadd(){
		alert("更多功能敬请期待");
	}
	function getTotal(id){
			if(confirm("确定查询该菜品数量吗?")){
				window.location.href="incomeServlet?action=total&cid="+id;
			}
		}
		
	function getIncome(){
		var date=document.getElementById("date").value;
		if(date==null||date==""){
	 	   alert("请输入日期！");
	    }else{
	    	document.form.action="incomeServlet?action=income&date="+date;
			document.form.submit();
	    }
	}
	function exit(){
          if(confirm("你确定要退出吗?")){
                 window.location.href="LoginMenuServlet?action=exit";
          }
    }
	</script>
	

  </head>
  
  <body>
  <div id="container">
	<img src="images/banner.gif" width="880px" height="120px" />
	  <h1>统计查询</h1>
	  <div id="content">
	  	<div class="context">
	  			<div class="center">
				<strong>
	   			<c:choose>
	  			<c:when test="${account.atype eq 0 }">
				<a href="cuisine" class="a_demo">菜品管理</a> &nbsp;&nbsp;&nbsp;
				<a href="amanage" class="a_demo">账号管理</a> &nbsp;&nbsp;&nbsp;
				<a href="dmanage" class="a_demo">餐桌管理</a> &nbsp;&nbsp;&nbsp;
				<a href="tmanage" class="a_demo">类型管理</a>&nbsp;&nbsp;&nbsp;
				<a href="javascript:showadd()" class="a_demo">更多功能</a> &nbsp;&nbsp;&nbsp;
				</c:when>
				<c:otherwise>
                       <a href="amanage?action=updatePwd&aid=${account.aid }" class="a_demo">修改密码</a>&nbsp;&nbsp;&nbsp;
                       <a href="javascript:exit()" class="a_demo">退出登录</a>                                 
                </c:otherwise>
	    		</c:choose>
	    		</strong>
			    </div>
	  	
				<div class="right" ><a href="deskServlet" class="a_demo">返回主界面</a>
				</div>			
		</div>
			
		  <table border="1" style="margin-bottom: 85px;">
		  <thead>
		  <tr>
		        <th>图片</th>
		        <th>菜名</th> 
		        <th>实价</th>
		        <th>类型</th>
		        <th>状态</th>
		        <th>已销售数量</th>
		        <th>操作</th>
		  </tr>
		  </thead>
		  <c:forEach items="${pBean.data}" var="cu">
		  
		  <tr>
		    <td><img  src="images/${cu.cimg}" width="120" height="80"></td>
		    <td>${cu.cname}</td>
		    <td>${cu.cprice}</td>
		    <td>${cu.mtype}</td>
		    <td>${cu.cexist eq 1?'在用':'停用' }</td>
		  	<td>${cu.total}</td>
		  	<td><a href="javascript:getTotal(${cu.cid})" class="a_demo">查询已售总数</a></td>
		  </tr>
		  
		  </c:forEach>  
		  <tr>
		  <form method="post" name="form" enctype="multipart/form-data" class="fh5co-form animate-box" data-animate-effect="fadeIn"
    			style="margin-top: 10px;background-color: #ebffdd;">
		 	  <td>查询日期：	<input type="text" class="form-control" id="date" value="${date}" placeholder="例：20180801" autocomplete="off">	  </td>
			  <td><input type="button" value="查询收益"  onclick="getIncome()" class="btn btn-primary">
			  </td>
			</form>
			  <td><h4><strong>该天收益为：￥${income}</strong></h4></td>
			  
		  </tr>
		  </table>
	   	
	   	<table height="50" border="0" cellpadding="0" cellspacing="0" style="border-top:1px #C3C3C3 solid; position:fixed;bottom:0px; width: 880;">
  		<tr>
  		<td align="center" style="text-align:center;">
    		<a href="IncomeServlet?pageNo=${pBean.first}">首页</a>&nbsp;&nbsp;&nbsp;
    		<a href="IncomeServlet?pageNo=${pBean.up}">上一页</a>&nbsp;&nbsp;&nbsp;
    		当前第${pBean.pageNo}页，共计${pBean.count}页&nbsp;&nbsp;&nbsp;
    		<a href="IncomeServlet?pageNo=${pBean.down}">下一页</a>&nbsp;&nbsp;&nbsp;
    		<a href="IncomeServlet?pageNo=${pBean.last}">末页</a>&nbsp;&nbsp;&nbsp;
    	</td>
			</tr>
		</table>
	   		
	</div>
</div>
</body>
</html>
