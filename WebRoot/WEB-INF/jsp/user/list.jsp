<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>无标题文档</title>
	<link href="<%=path%>/resource/admin/css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=path%>/resource/admin/js/jquery.js"></script>
	
<script type="text/javascript">
	function toPage(type){
		//获取当前页
		var page_now = $("#pageNow").val();
		//获取总页数
		var page_total = $("#totalPage").val();
		//获取每页显示条数
		var page_size = $("#page_size").val();
		
		switch(type){
			case "1":
				$("#pageNow").val(1);
				//window.location.href="sys/findUserAction.action?pageBean.pageNow=1&pageBean.pageSize="+page_size;;
				break;
			case "2":
				if(page_now<=1){
					alert("抱歉，没有上一页");
					return false;
				}else{
					$("#pageNow").val((parseInt(page_now)-1));
					//window.location.href="sys/findUserAction.action?pageBean.pageNow="+(parseInt(page_now)-1)+"&pageBean.pageSize="+page_size;;
				}
				break;
			case "3":
				if(parseInt(page_now)>=parseInt(page_total)){
					alert("抱歉，没有下一页");
					return false;
				}else{
					$("#pageNow").val((parseInt(page_now)+1));
					//window.location.href="sys/findUserAction.action?pageBean.pageNow="+(parseInt(page_now)+1)+"&pageBean.pageSize="+page_size;;
				}
				break;
			case "4":
				$("#pageNow").val(page_total);
				//window.location.href="sys/findUserAction.action?pageBean.pageNow="+page_total+"&pageBean.pageSize="+page_size;;
				break;
			case "5":
				//第一步判断输入是正整数
				var go = $("#go").val();
				if(!isNumber(go)){
					alert("请输入合法数据!")
					$("#go").val("");
					$("#go").focus();
					return false;
				}else{
					if(parseInt(go)>parseInt(page_total)||go==0){
						alert("输入的数据不合法，请重新输入!")
						$("#go").val("");
						$("#go").focus();
						return false;
					}else{
						$("#pageNow").val(go);
						//window.location.href="sys/findUserAction.action?pageBean.pageNow="+go+"&pageBean.pageSize="+page_size;;
					}
				}
				
				break;
			case "6":
				$("#pageNow").val(1);
				//window.location.href="sys/findUserAction.action?pageBean.pageNow=1&pageBean.pageSize="+page_size;
				break;
		
		}
		$("#pageSize").val(page_size);
	       searchForm.submit();
	}
	
	function toSearch(){
		$("#pageNow").val(1);
		searchForm.submit();
	}
	
	/*
	用途：检查输入字符串是否符合正整数格式
	输入：
	s：字符串
	返回：
	如果通过验证返回true,否则返回false
	*/
	function isNumber(s) {
		var regu = "^[0-9]+$";
		var re = new RegExp(regu);
		if (s.search(re) != -1) {
			return true;
		} else {
			return false;
		}
	} 

	/**
	 * 跳转到用户添加页面
	 */
	 function toAdd(){
            //成功需要注意jquery的版本必须是1.7+以上
            var d = top.dialog({
                width:700,
                height:400,
                title: '用户管理 | 添加',
                url:'sys/toAddUserAction.action',//可以是一个访问路径Action|Servlet等或者jsp页面资源
                onclose: function () {
	                if (this.returnValue) {
	                    if(this.returnValue=="success"){
	                    	window.location.reload();
	                    }
	                }
            	}
            });
            d.showModal();
        }
	/**
	 * 跳转到用户更新页面
	 */
	 function toUpdate(user_id){
            //成功需要注意jquery的版本必须是1.7+以上
            var d = top.dialog({
                width:700,
                height:400,
                title: '用户管理 | 更新',
                url:'sys/toUpdateUserAction.action?user_id='+user_id,//可以是一个访问路径Action|Servlet等或者jsp页面资源
                onclose: function () {
	                if (this.returnValue) {
		                if(this.returnValue=="success"){
		                    window.location.reload();
		                }
	                }
            	}
            });
            d.showModal();
        }
	
	/***
	 * 修改用户状态
	 */
	 function toChange(obj,user_id){
		var txt = $.trim($(obj).text());
		//变成一行代码 使用条件运算
		if(txt=="可用"){
			txt = "<b><font color='red'>禁用</font></b>";
		}else{
			txt = "<b><font color='green'>可用</font></b>";
		}
		
		var d = top.dialog({
		    title: '提示',
		    content: '您确定要'+txt+'吗?',
		    okValue: '确定',
		    ok: function () {
		    	//使用Ajax进行变更    
		    	$.post("sys/changStatusUserAction.action",{user_id:user_id},function(data){
		    		//alert(data.flag)
		    		if(data.flag=="success"){
		    			//window.location.reload();
		    			$(obj).html(txt);
		    		}
		    		return false;
		    	},"json")
		        
		    },
		    cancelValue: '取消',
		    cancel: function () {}
		});
		//d.show();
		d.showModal();
	 }
	
</script>

</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li>用户分页操作</li>
    </ul>
    </div>
        <div class="tools">
            <form action="sys/findUserAction.action" method="post" name="searchForm">
	            <ul class="seachform">
	            <li><label>姓名</label>
	            <s:textfield name="query.user_name" cssClass="scinput"></s:textfield>
		        <li><label>性别</label>  
		        <s:select list="#{'男':'男','女':'女','未知':'未知'}" cssClass="select_show" name="query.sex" headerKey="" headerValue="请选择性别"></s:select>
	            </li>
	            
	            <li><label>&nbsp;</label><input type="button" class="scbtn" value="查询" onclick="toSearch()"/></li>
	           </ul>
	           <ul class="toolbar1">
	                <li class="click" onclick="toAdd()" style="cursor: pointer;">
	                    <span><img src="<%=path%>/resource/admin/images/t01.png" />
	                    </span>添加
	                </li>
	            </ul>
	            <!-- 隐藏域 -->
			    <input type="hidden" name="pageBean.pageNow" id="pageNow" value="<s:property value="pageBean.pageNow"/>">
			    <input type="hidden" name="pageBean.totalPage" id="totalPage" value="<s:property value="pageBean.totalPage"/>">
			    <input type="hidden" name="pageBean.pageSize" id="pageSize" value="<s:property value="pageBean.pageSize"/>">
            </form>
       </div>



		<table class="tablelist">
    	<thead>
    	<tr>
        <th>编号</i></th>
        <th>姓名</th>
        <th>账号</th>
        <th>状态</th>
        <th>日期</th>
        <th>拥有角色</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
       <s:iterator value="pageBean.datas" var="user" status="st">
	        <tr>
		        <td><s:property value="#st.count"/> </td>
		        <td><s:property value="#user.user_name"/></td>
		        <td><s:property value="#user.account"/></td>
		        <td>
		        <a href="javascript:void(0)" title="点击修改" style="text-decoration: underline;" onclick="toChange(this,'<s:property value="#user.user_id"/>')">
			       <s:if test="#user.account_status==1"><strong><font color='green'>可用</font></strong></s:if>
			        <s:else><strong><font color='red'>禁用</font></strong></s:else>
				</a></td>
		        <td><s:date name="#user.create_date" format="yyyy-MM-dd"/></td>
		        <td><s:property value="#user.role.role_name" default="<font color='red'>暂无角色</font>" escape="false"/></td>
		        
		        <td><a href="javascript:void(0)" onclick="toUpdate('<s:property value="#user.user_id"/>')" class="tablelink">编辑</a></td>
	        </tr> 
         </s:iterator>
        </tbody>
    </table>
    
     <div class="pagin">
    	<div class="message">共<i class="blue"><s:property value="pageBean.totalCount"/></i>条记录，当前显示第&nbsp;<i class="blue"><s:property value="pageBean.pageNow"/>&nbsp;/&nbsp;<s:property value="pageBean.totalPage"/></i>页
    	<s:select list="#{'15':'每页显示15条','10':'每页显示10条','5':'每页显示5条'}" id="page_size" onchange="toPage('6')" cssStyle="border: 1px solid #6CB2E3;" value="pageBean.pageSize"></s:select>
    	
    	</div>
        <ul class="paginList">
        <li class="paginItem"><a href="javascript:void(0);" onclick="toPage('1')">首页</a></li>
        <li class="paginItem"><a href="javascript:void(0);" onclick="toPage('2')">上一页</a></li>
        <li class="paginItem"><a href="javascript:void(0);" onclick="toPage('3')">下一页</a></li>
        <li class="paginItem"><a href="javascript:void(0);" onclick="toPage('4')">尾页</a></li>
        
         <li class="paginItem">
         <input type="text" id="go" style="border: 1px solid #ccc;height: 30px;width: 30px;font-size: 15px;margin-left: 3px;"/>
         <input type="button" value="GO" onclick="toPage('5')" style="border: 1px solid #ccc;height: 30px;width: 30px;background: #066CAC;color: white;font-weight: bold;">
         </li>
        </ul>
    </div>

    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
    
    
    
    
    </div>


</body>

</html>

