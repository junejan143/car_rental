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
    <title>汽车品牌管理</title>
    <link href="<%=path %>/resource/admin/css/style.css" rel="stylesheet" type="text/css"/>
    <!-- 引入JQuery支持的库 -->
    <script type="text/javascript" src="<%=path %>/resource/admin/js/jquery.js"></script>
    <!-- 引入上传图片预览JQuery支持的库 -->
    <script type="text/javascript" src="<%=path %>/resource/imagePreview/jquery.js"></script>
    <script type="text/javascript" src="<%=path %>/resource/imagePreview/imagePreview.js"></script>
    <script type="text/javascript">
        function toSub(){
        	var role_name = $.trim($("#role_name").val());
        	
        	if(role_name.length==0){
        		alert("类型不能为空");
        		$("#role_name").val($("#old_role_name").val());
        		$("#role_name").focus();
        		return false;
        	}
        	if(role_name.length>10){
                alert("输入的内容过长");
                $("#role_name").val($("#old_role_name").val());
                $("#role_name").focus();
                return false;
            }
            //使用Ajax进行校验
            if(role_name!=$("#old_role_name").val()){
            	$.post("sys/toCheckNameRoleAction.action",{role_name:role_name},function(data){
                    if(data.flag=="success"){
                    	roleForm.submit();
                    }else{
                    	 alert(data.message);
                    	 $("#type_name").focus();
                    	 return false;
                    }
                    
                });
            }else{
            	 //提交
                roleForm.submit();
            }
        }

    </script>
</head>

<body>

	

<div class="formbody">

    <div class="formtitle"><span>角色基本信息|更新</span></div>
	<form action="sys/updateRoleAction.action" method="post" name="roleForm" enctype="multipart/form-data">
    <ul class="forminfo">
        <li><label>角色名称</label>
        <s:textfield name="role.role_name" id="role_name" cssClass="dfinput"></s:textfield><i>角色名称不能超过10个字符</i></li>
        <li><label>角色描述</label>
        <s:textfield name="role.role_desc" id="role_desc" cssClass="dfinput"></s:textfield></li>
        <li><label>角色头像</label><cite>
	        <input type="file" name="photo" onchange="preview(this,'preview','imghead',200,100)">
        </cite></li>
        <li>
            <div id="imagePreview" >图片预览
            <div id="preview">
            <img alt="<s:property value="role.role_name"/>" id="imghead" width=200 height=100 title="<s:property value="role.role_name"/>" 
            src="<%=path %>/upload/role/<s:property value="role.role_photo" default="default.png"/>" />
            </div>
            </div>
        </li>
        <li><label>&nbsp;</label><input  type="button" class="btn" value="确认更新" onclick="toSub()"/></li>
    </ul>
    <input type="hidden" name="role.role_id" value="<s:property value="role.role_id"/>">
    <input type="hidden" id="old_role_name" value="<s:property value="role.role_name"/>">
    
	</form>

</div>


</body>

</html>