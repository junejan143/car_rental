<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>欢迎登录RBAC后台管理系统</title>
	<link href="<%=path %>/resource/admin/css/style.css" rel="stylesheet" type="text/css" />
	<script language="JavaScript" src="<%=path %>/resource/admin/js/jquery.js"></script>
	<script src="<%=path %>/resource/admin/js/cloud.js" type="text/javascript"></script>

	<script language="javascript">
		$(function(){
		    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
			$(window).resize(function(){  
		    	$('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
		    })  
		});  
		
		function toSub(){
			//通过ID选择器获取值
			var account = $.trim($("#account").val());
			
			var password = $.trim($("#password").val());
			
			
			if(account.length==0){
				alert("用户名不能为空，请重新输入!");
				$("#account").focus();
				return false;
			}
			
			if(!isNumberOr_Letter(account)){
				alert("账号必须是数字下划线或者字母组成");
				$("#account").focus();
				return false;
			}
			if(password.length==0){
				alert("密码不能为空，请重新输入!");
				$("#password").focus();
				return false;
			}
			
			loginForm.submit();
		}
		
		/*
		用途：检查输入字符串是否只由英文字母和数字和下划线组成
		输入：
			s：字符串
		返回：
			如果通过验证返回true,否则返回false
		*/
		function isNumberOr_Letter(s) {//判断是否是数字或字母
			var regu = "^[0-9a-zA-Z\_]+$";
			var re = new RegExp(regu);
			if (re.test(s)) {
				return true;
			} else {
				return false;
			}
		} 

	</script> 

</head>

<body style="background-color:#1c77ac; background-image:url(resource/admin/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">

    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>

	<div class="logintop">
		<span>欢迎登录RBAC后台管理界面平台</span>
		<ul>
			<li>
				<a href="#">回首页</a>
			</li>
		
		</ul>
	</div>
	
	<form action="sys/loginLoginAction.action" method="post" name="loginForm">
	<div class="loginbody">

		<span class="systemlogo"></span>

		<div class="loginbox">

			<ul>
				<li>
			        <s:textfield name="user.account" id="account" cssClass="loginuser" placeholder="输入您的账号"></s:textfield>
				</li>
				<li>
				    <s:password name="user.password" id="password" cssClass="loginpwd" placeholder="输入您的密码"></s:password>
				</li>
				<li>
					<input type="button" class="loginbtn" value="登录"	onclick="toSub()" />
					<!--  
					<label>
						<input name="" type="checkbox" value="" checked="checked" />
						记住密码
					</label>
					-->
					<label>
						<font color="red">${message}</font><!-- 什么是EL表达式 -->
					</label>
				</li>
			</ul>


		</div>

	</div>
	</form>



		<div class="loginbm">版权所有  2015  <a href="http://www.cchsxt.com">www.ccshxt.com</a>  仅供学习交流，勿用于任何商业用途</div>
	
    
</body>

</html>
