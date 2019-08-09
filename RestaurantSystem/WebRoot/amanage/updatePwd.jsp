<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updatecuisine.jsp' starting page</title>
    
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
	<!--<link href="tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />-->
	<!--<script type="text/javascript" src="tablecloth/tablecloth.js"></script>-->
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
	
		function chec(pwd){
			var oldpwd=document.getElementById("oldpwd").value;
			var newpwd=document.getElementById("newpwd").value;
			var secpwd=document.getElementById("secpwd").value;
			if(oldpwd==""||newpwd==""||secpwd==""){
				alert("请输入完整密码");
			}
			else if(pwd!=oldpwd){
				alert("旧密码错误");
				return false;
			}
			else if(newpwd!=secpwd){
				alert("新密码不一致,请重新输入");
				return false;
			}
			else{
				return true;
			}
		}
	</script>
  </head>
  
  <body>
  	<div id="container">
  	<img src="images/banner.gif" width="880px" height="120" />
  	<h1>密码修改</h1>
  		<div class="context">
			<div class="center">
				<strong>
	   			<a href="deskServlet" class="a_demo">返回主页</a> &nbsp;&nbsp;&nbsp;
				</strong>
			</div>
		</div>
  	
    <div class="container" style="width:880px;">
    	<div class="row">
				<div class="col-md-4 col-md-offset-4">
    		<form onsubmit="return chec(${account.apwd})" action="amanage" method="post" class="fh5co-form animate-box" data-animate-effect="fadeIn"
    			style="margin-top: 10px;background-color: #ebffdd;">
    			<div class="form-group">
					<div class="alert alert-success" role="alert">修改密码</div>
				</div>
    			<input type="hidden" name="action" value="updatepwdover">
    			<input type="hidden" name="aid" value="${account.aid }">
					<div class="form-group">
							<label class="sr-only"></label>旧&nbsp;密&nbsp;码：
							<input type="password" name="oldpwd" id="oldpwd" class="form-control" value="">
					</div>
					<div class="form-group">
							
							<label class="sr-only"></label>新&nbsp;密&nbsp;码：
							<input type="password" name="newpwd" id="newpwd" class="form-control" value="">
					</div>
					<div class="form-group">
							
							<label class="sr-only"></label>再&nbsp;确&nbsp;认：
							<input type="password" name="secpwd" id="secpwd" class="form-control" value="">
					</div>
					
    		<div class="form-group" align="center">
					<input type ="submit" value="保存修改" class="btn btn-primary">&nbsp;&nbsp;&nbsp;&nbsp;
					<input type ="reset" value="重置" class="btn btn-primary">
			</div>
    </form>
    
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
