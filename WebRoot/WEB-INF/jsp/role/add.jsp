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
    <title>角色管理</title>
    <link href="<%=path %>/resource/admin/css/style.css" rel="stylesheet" type="text/css"/>
    <!-- 引入JQuery支持的库 -->
    <script type="text/javascript" src="<%=path %>/resource/admin/js/jquery.js"></script>
    <script type="text/javascript">
        function toSub(){
           
            var role_name = $.trim($("#role_name").val());
            
            if(role_name.length==0){
                alert("角色不能为空");
                $("#role_name").focus();
                return false;
            }
            if(role_name.length>10){
                alert("输入的内容过长");
                $("#role_name").focus();
                return false;
            }
             //使用Ajax进行校验
            $.post("sys/toCheckNameRoleAction.action",{role_name:role_name},function(data){
                    if(data.flag=="success"){
                         //提交
                    	typeForm.submit();
                    }else{
                         alert(data.message);
                         $("#role_name").focus();
                         return false;
                    }
                    
                });
           
        }

    </script>
</head>

<body>

	

<div class="formbody">

    <div class="formtitle"><span>角色基本信息</span></div>
	<form action="sys/addRoleAction.action" method="post" name="typeForm" enctype="multipart/form-data">
    <ul class="forminfo">
        <li><label>角色名称</label><input name="role.role_name" id="role_name" type="text" class="dfinput"/><i>角色类型名称不能超过10个字符</i></li>
        <li><label>角色描述</label><input name="role.role_desc" id="role_desc" type="text" class="dfinput"/>
	    <li><label>角色头像</label><cite>
	        <input type="file" name="photo">
        </cite></li>
        <li><label>&nbsp;</label><input  type="button" class="btn" value="确认保存" onclick="toSub()"/></li>
    </ul>
	</form>

</div>


</body>

</html>