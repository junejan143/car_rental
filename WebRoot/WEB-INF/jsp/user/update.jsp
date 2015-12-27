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
    <title>用户更新管理</title>
    <link href="<%=path %>/resource/admin/css/style.css" rel="stylesheet" type="text/css"/>
    <!-- 引入JQuery库的支持 -->
    <script type="text/javascript" src="<%=path%>/resource/admin/js/jquery.js"></script>
    <script type="text/javascript">
        function toSub(){
            var user_name = $.trim($("#user_name").val());
            
            if(user_name.length==0){
            	alert("姓名不能为空");
            	$("#user_name").val("");
            	$("#user_name").focus();
            	return false;
            }
            if(user_name.length>10){
            	alert("姓名这么长，你累不？");
            	$("#user_name").focus();
            	return false;
            }
            userForm.submit();
            
        }

    </script>
</head>

<body>
<div class="formbody">

    <div class="formtitle"><span>用户基本信息</span></div>
	<form action="sys/updateUserAction.action" method="post" name="userForm">
    <ul class="forminfo">
        <li><label>用户账号</label><input name="user.account" id="account" type="text" class="dfinput" readonly="readonly" value="<s:property value="user.account"/>" style="background: #ccc;"/><i>账号不能超过10个字符</i></li>
        <li><label>用户姓名</label><input name="user.user_name" id="user_name" type="text" class="dfinput" value="<s:property value="user.user_name"/>"/><i>姓名不能超过10个字符</i></li>
        <li><label>分配角色</label>
            <s:select list="roleList" listKey="role_id" listValue="role_name" headerKey="" headerValue="请选择角色" 
                id="role_id" cssClass="select_show" name="role_id" value="user.role.role_id" ></s:select>
        </li>
        
        <li><label>&nbsp;</label><input  type="button" class="btn" value="确认更新" onclick="toSub();"/></li>
    </ul>
    
    <!-- 需要传递用户的主键ID -->
    <input type="hidden" name="user.user_id" value="<s:property value="user.user_id"/>">
	</form>

</div>


</body>

</html>
