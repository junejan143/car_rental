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
    <title>汽车品牌管理</title>
    <link href="<%=path %>/resource/admin/css/style.css" rel="stylesheet" type="text/css"/>
    <!-- 引入JQuery支持的库 -->
    <script type="text/javascript" src="<%=path %>/resource/admin/js/jquery.js"></script>
    <script type="text/javascript">
        function toSub(){
        	var type_name = $.trim($("#type_name").val());
        	
        	if(type_name.length==0){
                alert("类型不能为空");
                $("#type_name").focus();
                return false;
            }
            if(type_name.length>10){
                alert("输入的内容过长");
                $("#type_name").focus();
                return false;
            }
        	
            //使用Ajax进行校验
           
            $.post("sys/toCheckNameCarTypeAction.action",{type_name:type_name},function(data){
                    if(data.flag=="success"){
                         //提交
                    	typeForm.submit();
                    }else{
                         alert(data.message);
                         $("#type_name").focus();
                         return false;
                    }
              });
        }

    </script>
</head>

<body>

	

<div class="formbody">

    <div class="formtitle"><span>品牌基本信息</span></div>
	<form action="sys/addCarTypeAction.action" method="post" name="typeForm" enctype="multipart/form-data">
    <ul class="forminfo">
        <li><label>品牌名称</label><input name="carType.type_name" id="type_name" type="text" class="dfinput"/><i>品牌名称不能超过10个字符</i></li>
        <li><label>品牌LOGO</label><cite>
	        <input type="file" name="photo">
        </cite></li>
        <li><label>&nbsp;</label><input  type="button" class="btn" value="确认保存" onclick="toSub()"/></li>
    </ul>
	</form>

</div>


</body>

</html>