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
   <title>角色管理 | 授权</title>
	<link rel="stylesheet" type="text/css" href="<%=path %>/resource/admin/css/main.css" />
	<style type="text/css">
	.demo{width:450px; margin:10px auto}
	    .select_side{float:left; width:200px}
	    select{width:180px; height:200px}
	    .select_opt{float:left; width:40px; height:15%; margin-top:80px}
	    .select_opt p{width:26px; height:26px; margin-top:6px; background:url(<%=path %>/resource/admin/images/arr.gif) no-repeat; cursor:pointer; text-indent:-999em}
	    .select_opt p#toright{background-position:2px 0}
	    .select_opt p#toleft{background-position:2px -22px}
	    .sub_btn{clear:both; height:42px; line-height:42px; padding-top:10px; text-align:center}
	</style>
	<script type="text/javascript" src="<%=path %>/resource/admin/js/jquery.js"></script>
	<script type="text/javascript">
	$(function(){
	    var leftSel = $("#selectL");
	    var rightSel = $("#selectR");
	    $("#toright").bind("click",function(){      
	        leftSel.find("option:selected").each(function(){
	            $(this).remove().appendTo(rightSel);
	        });
	    });
	    $("#toleft").bind("click",function(){       
	        rightSel.find("option:selected").each(function(){
	            $(this).remove().appendTo(leftSel);
	        });
	    });
	    leftSel.dblclick(function(){
	        $(this).find("option:selected").each(function(){
	            $(this).remove().appendTo(rightSel);
	        });
	    });
	    rightSel.dblclick(function(){
	        $(this).find("option:selected").each(function(){
	            $(this).remove().appendTo(leftSel);
	        });
	    });
	    $("#sub").click(function(){
	        var selVal = [];
	        rightSel.find("option").each(function(){
	            selVal.push(this.value);
	        });
	        selVals = selVal.join(",");
	        //selVals = rightSel.val();
	        if(selVals==""){
	            alert("没有选择任何项！");
	        }
	    });
	});

	function toSub(){
		
		$("#selectR option").prop("selected",true);
		
		authorizeForm.submit();
	}

</script>
</head>

<body>
<div id="main">
  <h2 class="top_title" align="center">针对 <s:property value="role.role_name"/> 进行授权</h2>
  <form action="sys/updateAuthorizeRoleAction.action" method="post" name="authorizeForm">
	  <div class="demo">
	     <div class="select_side">
	     <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;未拥有的菜单</p>
	        <s:select list="#unSelectedMenuList" listKey="menu_id" listValue="menu_name" id="selectL" multiple="true"></s:select>
	     </div>
	     <div class="select_opt">
	        <p id="toright" title="添加">&gt;</p>
	        <p id="toleft" title="移除">&lt;</p>
	     </div>
	     <div class="select_side">
	     <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;拥有的菜单</p>
	        <s:select list="#selectedMenuList" listKey="menu_id" listValue="menu_name" id="selectR" name="selectedMenuIds" multiple="true"></s:select>
	     </div>
	     <div class="sub_btn"><input type="button" id="sub" value="授权" style="width: 150px;height: 35px;font-size: 15px;" onclick="toSub()"/></div>
	  </div>
	  <input type="hidden" name="role_id" value="<s:property value="role.role_id"/>" />
  </form>
  
  
</div>
</body>
</html>
