<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
   <title>信息提示中心</title>
    <script type="text/javascript" src="../js/jquery.js"></script>
    <link rel="stylesheet" href="../artDialog/css/ui-dialog.css">
    <script src="../artDialog/dist/dialog-plus.js"></script>
    <script type="text/javascript">
        function winClose(flag){

            var dialog = top.dialog.get(window);
            dialog.close(flag);
            dialog.remove();
        }

    </script>

</head>
<body>
    <div align="center">
        <h2><%=request.getAttribute("message") %></h2>
        
        <input type="button" value="关闭窗口" onclick="winClose('<%=request.getAttribute("flag")!=null?request.getAttribute("flag").toString():"" %>')">
    </div>
</body>
</html>
