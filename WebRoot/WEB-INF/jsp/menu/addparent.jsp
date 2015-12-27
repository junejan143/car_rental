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
    <title>菜单管理</title>
    <link href="<%=path %>/resource/admin/css/style.css" rel="stylesheet" type="text/css"/>
    <!-- 引入JQuery支持的库 -->
    <script type="text/javascript" src="<%=path %>/resource/admin/js/jquery.js"></script>
    <script type="text/javascript">
        function toSub(){
        	var menu_name = $.trim($("#menu_name").val());
            
            if(menu_name.length==0){
                alert("类型不能为空");
                $("#menu_name").focus();
                return false;
            }
            if(menu_name.length>10){
                alert("输入的内容过长");
                $("#menu_name").focus();
                return false;
            }
            var postion = $.trim($("#postion").val());
            
            if(postion.length==0){
                alert("位置不能为空");
                $("#postion").focus();
                return false;
            }
            if(postion.length>10){
                alert("输入的内容过长");
                $("#postion").focus();
                return false;
            }
            //使用Ajax进行校验
            $.post("sys/toCheckNameMenuAction.action",{menu_name:menu_name},function(data){
                    if(data.flag=="success"){
                    	//提交
                        menuForm.submit();
                    }else{
                         alert(data.message);
                         $("#menu_name").focus();
                         return false;
                    }
                    
                });
        }

    </script>
</head>

<body>

    

<div class="formbody">

    <div class="formtitle"><span>父菜单基本信息添加</span></div>
    <form action="sys/addParentMenuAction.action" method="post" name="menuForm" enctype="multipart/form-data">
    <ul class="forminfo">
        <li><label>菜单名称</label><input name="menu.menu_name" id="menu_name" type="text" class="dfinput"/><i>菜单名称不能超过10个字符</i></li>
        <li><label>菜单位置</label><input name="menu.postion" id="postion" type="text" class="dfinput" value="LEFT"/></li>
        <li><label>&nbsp;</label><input  type="button" class="btn" value="确认保存" onclick="toSub()"/></li>
    </ul>
    </form>

</div>


</body>

</html>