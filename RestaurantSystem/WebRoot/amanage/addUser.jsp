<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addcuisine.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link rel="stylesheet" type="text/css" href="css/style2.css" />
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/animate.css">
	<link rel="stylesheet" href="css/style.css">
	<style>
		.context{border-top:dashed 1px #ccc;padding-top:20px;margin:5px;}
		.context{text-align:center;}
		.context .left{ float:left;}
		.context .center{ display:inline;}
		.context .right{ float:right;}
		#container { margin:0 auto; width:880px; background:#fff; padding-bottom:20px; }
		h1 { font: 280% KaiTi ; margin:auto; line-height:40px;text-align:center }
		
	</style>
	<script type="text/javascript">
	function addUser(){
 	 var aname=document.form1.aname.value;
	 
	 if(aname=="")
	 {
	 	alert("请输入用户名");
		 	return false;
	 }
	 else
	 { 
		 var apwd=document.form1.cname.apwd.value; 
	 	 if(apwd=="")
	 	 {
		 	alert("密码不能为空");
		 	return false;
		 }
		 else
		 {
		 	return true;
		 	}
		}
	} 
	
	
</script>
  </head>
  
  <body">
  	<div id="container">
  	<img src="images/banner.gif" width="880px" height="120" />
  	<h1>新增人员</h1>
  		<div class="context">
			<div class="center">
				<strong>
	   			<a href="amanage" class="a_demo">返回管理</a> &nbsp;&nbsp;&nbsp;
				</strong>
			</div>
		</div>
		
  	<div class="container" style="width:880px;">
  		<div class="row">
				<div class="col-md-4 col-md-offset-4">
			  	<form onsubmit="return addUser()" action="addAmanage" method="get" name="form1" enctype="multipart/form-data" class="fh5co-form animate-box" data-animate-effect="fadeIn"
    			style="margin-top: 10px;background-color: #ebffdd;">
    				<div class="form-group">
					<div class="alert alert-success" role="alert">填写人员信息</div>
					</div>
					<div class="form-group">
							用户名：<input type="text" class="form-control" name="aname"  placeholder="用户名" autocomplete="off">
					</div>
					<div class="form-group">
							密&nbsp;&nbsp;码：<input type="password" name="apwd" class="form-control"  placeholder="用户密码" autocomplete="off">
					</div>
					<div class="form-group">
						<input type="radio" name="atype" value="1" id="ptyg"  checked="checked">
				    	<label for="ptyg">普通员工</label>
				    	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    	<input type="radio" name="atype" value="0" id="gly" >
				    	<label for="gly">管理员</label>
					</div>

   
   	<span style=" color: red;">${bug}</span>
   <div class="form-group" align="center">
  		<input type="submit" value="提交" class="btn btn-primary" />
  	</div>
  </form>
 	<br><br><br><br><br>
 	</div>
	</div>
			
	</div>
	</div>
 	<script src="js/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Placeholder -->
	<script src="js/jquery.placeholder.min.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script>
	<!-- Main JS -->
	<script src="js/main.js"></script>
  </body>
</html>
