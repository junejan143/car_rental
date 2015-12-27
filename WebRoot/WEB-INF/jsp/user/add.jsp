<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>用户添加管理</title>
    <link href="<%=path %>/resource/admin/css/style.css" rel="stylesheet" type="text/css"/>
    <!-- 引入JQuery库的支持 -->
    <script type="text/javascript" src="<%=path%>/resource/admin/js/jquery.js"></script>
    <script type="text/javascript">
        function toSub(){
        	
            var account = $.trim($("#account").val());
            
            var user_name = $.trim($("#user_name").val());
            
            //1.验证 账号姓名是否为空同时需要验证长度
            if(account.length==0){
            	alert("账号不能为空");
            	$("#account").val("");
            	$("#account").focus();
            	return false;
            }
            if(account.length>10){
            	alert("账号这么长，你累不？");
            	$("#account").focus();
            	return false;
            }
            
            if(!isNumberOr_Letter(account)){
            	alert("你别瞎输入，浪费感情？");
            	$("#account").val("");
            	$("#account").focus();
            	return false;
            }
            
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
            //2.需要使用Ajax进行账号是否唯一的校验
            var param = {
            	account:account
            };
            
            $.post("sys/toCheckNameUserAction.action",param,function(data){
            	if(data.flag=="success"){
            		$("#account").focus();
            		userForm.submit();
            	}else{
            		alert(data.message);
            		return false;
            	}
            	
            	
            },"json");
        }
        
        
        
		/*
		用途：检查输入字符串是否只由英文字母和数字和下划线组成
		输入：
			s：字符串
		返回：
			如果通过验证返回true,否则返回false
		*/
		function isNumberOr_Letter(s) {//判断是否是数字或字母
			var regu = "^[0-9a-zA-Z\_]+$";
			var re = new RegExp(regu);
			if (re.test(s)) {
				return true;
			} else {
				return false;
			}
		} 


    </script>
</head>

<body>
<div class="formbody">

    <div class="formtitle"><span>用户基本信息</span></div>
	<form action="sys/addUserAction.action" method="post" name="userForm">
    <ul class="forminfo">
        <li><label>用户账号</label><input name="user.account" id="account" type="text" class="dfinput"/><i>账号不能超过10个字符</i></li>
        <li><label>用户密码</label><input name="user.password" value="123456" type="password" class="dfinput" /><i>初始化密码统一为123456</i></li>
        <li><label>用户姓名</label><input name="user.user_name" id="user_name" type="text" class="dfinput"/><i>姓名不能超过10个字符</i></li>
        <li><label>分配角色</label>
            <s:select list="roleList" listKey="role_id" listValue="role_name" headerKey="" headerValue="请选择角色" 
           id="role_id" cssClass="select_show" name="role_id"></s:select>
        </li>
        <li><label>&nbsp;</label><input  type="button" class="btn" value="确认保存" onclick="toSub();"/></li>
    </ul>
	</form>

</div>


</body>

</html>
