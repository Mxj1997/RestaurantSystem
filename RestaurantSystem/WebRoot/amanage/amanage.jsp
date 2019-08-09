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
	
		function useA(aid,pageno){
			if(confirm("确定要恢复该用户吗?")){
				//跳转AccountServlet 启用账号
				window.location.href="amanage?action=use&aid="+aid+"&pageNo="+pageno;
			}
		}
		
		function banA(aid,pageno,atype,uid){
			if(aid==uid){
				alert("无权禁用自己");
			}
			else if(atype==0&&uid!=1){
				alert("您的权限不够");
			}
			else{
				if(confirm("确定要禁用该用户?")){
					//跳转AccountManageServlet 启用账号
					window.location.href="amanage?action=ban&aid="+aid+"&pageNo="+pageno;
				}
			}
		}
		function showadd(){
			alert("更多功能敬请期待");
		}
		
		function updatetype(aid,type,uid,uname){
			if(aid==uid){
				alert("无权限修改自己");
			}
			else if(type==0&&uid!=1){
				alert("权限登记不够");
				location.reload();
			}
			else if(confirm("确定要修改吗？"))
			{		var atype=0;
					atype= type==0?1:0;
					window.location.href="amanage?action=updateType&aid="+aid+"&atype="+atype;
			}
		}
	
	</script>
	

  </head>
  
  <div id="container">
  <img src="images/banner.gif" width="880px" height="120" />
  <h1>人员管理</h1>
  <div id="content">
  	<div class="context">
			<div class="left" ><a href="amanage?action=add" class="a_demo">添加人员</a> </div>
			<div class="center">
				<strong>
	   			<c:choose>
	  			<c:when test="${account.atype eq 0 }">
				<a href="cuisine" class="a_demo">菜单管理</a> &nbsp;&nbsp;&nbsp;
				<a href="dmanage" class="a_demo">餐桌管理</a> &nbsp;&nbsp;&nbsp;
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
			<div class="right" ><a href="deskServlet" class="a_demo">返回主界面</a></div>
			</div>
   
  <table border="1" style="margin-bottom: 85px;">
  <thead>
  <tr>
        <th>人员ID</th>
        <th>账号</th> 
        <th>密码</th>
        <th>类型</th>
        <th>状态</th>
        <th>操作</th>
  </tr>
  </thead>
  
  <c:forEach items="${pBean.data}" var="au">
  
  <tr>
    <td>${au.aid}</td>
    <td>${au.aname}</td>
    <td>${au.apwd }</td>
    <td>
    	<select name="atype" onchange="updatetype(${au.aid},${au.atype},${uid},'${uname}')">
    		<option value="0" ${au.atype eq 0?'selected':""}>管理员</option>
    		<option value="1" ${au.atype eq 1?'selected':""}>前台</option>
    	</select>
    </td> 
    <td>${au.aexist eq 1?'可用':'不可用' }</td>
  <td>
  	<c:choose>
	  <c:when test="${au.aexist eq 1 }">
	  	<a href="javascript:banA(${au.aid},${pBean.pageNo},${au.atype},${uid}) " class="a_demo">禁用</a>
	  </c:when>
	  <c:otherwise>
	  	<a href="javascript:useA(${au.aid},${pBean.pageNo})" class="a_demo">恢复</a>
	  </c:otherwise>
	  </c:choose>
  </tr>
  </c:forEach>  
  </table>
  
  <!-- 分页 -->
    <!--<table width="950" height="50" border="0" cellpadding="0" cellspacing="0" style="border-top:1px #C3C3C3 solid;position: absolute;bottom:0; ">
  		<tr>
  		<td align="center" style="text-align:center;">
    		<a href="cuisine?pageNo=${pBean.first}">首页</a>&nbsp;&nbsp;&nbsp;
    		<a href="cuisine?pageNo=${pBean.up}">上一页</a>&nbsp;&nbsp;&nbsp;
    		当前第${pBean.pageNo}页，共计${pBean.count}页&nbsp;&nbsp;&nbsp;
    		<a href="cuisine?pageNo=${pBean.down}">下一页</a>&nbsp;&nbsp;&nbsp;
    		<a href="cuisine?pageNo=${pBean.last}">末页</a>&nbsp;&nbsp;&nbsp;
    	</td>
			</tr>
		</table>-->
   
   <table height="50" border="0" cellpadding="0" cellspacing="0" style="border-top:1px #C3C3C3 solid; position:fixed;bottom:0px; width: 880;">
  		<tr>
  		<td align="center" style="text-align:center;">
    		<a href="cuisine?pageNo=${pBean.first}">首页</a>&nbsp;&nbsp;&nbsp;
    		<a href="cuisine?pageNo=${pBean.up}">上一页</a>&nbsp;&nbsp;&nbsp;
    		当前第${pBean.pageNo}页，共计${pBean.count}页&nbsp;&nbsp;&nbsp;
    		<a href="cuisine?pageNo=${pBean.down}">下一页</a>&nbsp;&nbsp;&nbsp;
    		<a href="cuisine?pageNo=${pBean.last}">末页</a>&nbsp;&nbsp;&nbsp;
    	</td>
			</tr>
		</table>
		</div>
  </div>
  </body>
</html>
