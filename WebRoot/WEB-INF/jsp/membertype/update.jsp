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
    <title>会员类型管理</title>
    <link href="<%=path %>/resource/admin/css/style.css" rel="stylesheet" type="text/css"/>
    <!-- 引入JQuery支持的库 -->
    <script type="text/javascript" src="<%=path %>/resource/admin/js/jquery.js"></script>
    <!-- 引入上传图片预览JQuery支持的库 -->
    <script type="text/javascript" src="<%=path %>/resource/imagePreview/jquery.js"></script>
    <script type="text/javascript" src="<%=path %>/resource/imagePreview/imagePreview.js"></script>
    <script type="text/javascript">
        function toSub(){
        	var type_name = $.trim($("#type_name").val());
        	
        	if(type_name.length==0){
        		alert("类型不能为空");
        		$("#type_name").val($("#old_type_name").val());
        		$("#type_name").focus();
        		return false;
        	}
        	if(type_name.length>10){
                alert("输入的内容过长");
                $("#type_name").val($("#old_type_name").val());
                $("#type_name").focus();
                return false;
            }
            //使用Ajax进行校验
            if(type_name!=$("#old_type_name").val()){
            	$.post("sys/toCheckNameMemberTypeAction.action",{type_name:type_name},function(data){
                    if(data.flag=="success"){
                    	typeForm.submit();
                    }else{
                    	 alert(data.message);
                    	 $("#type_name").focus();
                    	 return false;
                    }
                    
                });
            }else{
            	 //提交
                typeForm.submit();
            }
        }

    </script>
</head>

<body>

	

<div class="formbody">

    <div class="formtitle"><span>会员类型基本信息|更新</span></div>
	<form action="sys/updateMemberTypeAction.action" method="post" name="typeForm" enctype="multipart/form-data">
    <ul class="forminfo">
        <li><label>会员类型名称</label>
        <s:textfield name="memberType.type_name" id="type_name" cssClass="dfinput"></s:textfield><i>会员类型不能超过10个字符</i></li>
        <li><label>会员类型折扣</label>
        <s:textfield name="memberType.discount" id="discount" cssClass="dfinput"></s:textfield>
        <li><label>会员类型头像</label><cite>
	        <input type="file" name="photo" onchange="preview(this,'preview','imghead',200,100)">
        </cite></li>
        <li>
            <div id="imagePreview" >图片预览
            <div id="preview">
            <img alt="<s:property value="memberType.type_name"/>" id="imghead" width=200 height=100 title="<s:property value="memberType.type_name"/>" 
            src="<%=path %>/upload/membertype/<s:property value="memberType.photo" default="default.png"/>" />
            </div>
            </div>
        </li>
        <li><label>&nbsp;</label><input  type="button" class="btn" value="确认更新" onclick="toSub()"/></li>
    </ul>
    <input type="hidden" name="memberType.type_id" value="<s:property value="memberType.type_id"/>">
    <input type="hidden" id="old_type_name" value="<s:property value="memberType.type_name"/>">
    
	</form>

</div>


</body>

</html>