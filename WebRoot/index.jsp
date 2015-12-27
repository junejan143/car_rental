<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>凌云15期用户权限管理系统 |简单版</title>
  </head>
  
  <body>
   <%
   request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
   %>
  </body>
</html>
