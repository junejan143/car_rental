<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>无标题文档</title>
		<link href="<%=path%>/resource/admin/css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=path%>/resource/admin/js/jquery.js"></script>

		<script language="javascript">
			$(function() {
				$('.error').css( {
					'position' : 'absolute',
					'left' : ($(window).width() - 490) / 2
				});
				$(window).resize(function() {
					$('.error').css( {
						'position' : 'absolute',
						'left' : ($(window).width() - 490) / 2
					});
				})
			});
		</script>


	</head>


	<body style="background: #edf6fa;">

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>
					500错误提示
				</li>
			</ul>
		</div>

		<div class="error500">

			<h2>
				非常遗憾，您访问的页面存在错误，错误信息为<s:debug></s:debug>
			</h2>
			<p>
				看到这个提示，就自认倒霉吧!
			</p>
			<div class="reindex">
				<a href="<%=path %>/index.jsp" target="_parent">返回首页</a>
			</div>

		</div>


	</body>

</html>

