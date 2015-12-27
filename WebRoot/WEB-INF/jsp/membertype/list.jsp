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
    <!-- 引入artDailog支持的库 -->
    <link rel="stylesheet" href="<%=path %>/resource/admin/artDialog/css/ui-dialog.css">
    <script src="<%=path %>/resource/admin/artDialog/dist/dialog-plus.js"></script>
     <style type="text/css">
       .gray { 
         -webkit-filter: grayscale(100%);
         -moz-filter: grayscale(100%);
         -ms-filter: grayscale(100%);
         -o-filter: grayscale(100%);  
         filter: grayscale(100%);  
         filter: gray;
       }
    </style>
    <script language="javascript">
        $(function () {
            //导航切换
            $(".imglist li").click(function () {
                $(".imglist li.selected").removeClass("selected")
                $(this).addClass("selected");
            })
        })
    </script>

    <!-- 关于功能测试代码 -->
    <script type="text/javascript">
        function toAddDialog(){
            //测试artDialog是否成功
            //成功需要注意jquery的版本必须是1.7+以上
            var d = top.dialog({
                id:"rightFrame",
                width:700,
                height:500,
                title: '会员类型管理|添加',
                url:'sys/toAddMemberTypeAction.action',//可以是一个访问路径Action|Servlet等或者jsp页面资源
                onclose: function () {
	                if (this.returnValue=="success") {
	                   window.location.reload();
	                }

                }
            });
            d.showModal();
        }
        function toUpdateDialog(type_id){
        	 var d = top.dialog({
                id:"rightFrame",
                width:700,
                height:500,
                title: '会员类型管理|更新',
                url:'sys/toUpdateMemberTypeAction.action?type_id='+type_id,//可以是一个访问路径Action|Servlet等或者jsp页面资源
                onclose: function () {
	                if (this.returnValue=="success") {
	                   window.location.reload();
	                }
               }
            });
            d.showModal();
        }

        //变更状态方法
        function toChangeStatusDialog(type_id,i){
              
        	window.location.href="sys/toChangeStatusMemberTypeAction.action?type_id="+type_id+"&status="+i;
        
        }
        
        function toDeleteDialog(type_id){
        	 var d = top.dialog({
                title: '会员类型管理 | 注销',
                content: '您确定要<font color="red" style="font-size:22px;">彻底删除</font>该会员类型吗？',
                okValue: '确定',
                ok: function () {
                    
                    window.location.href="sys/deleteMemberTypeAction.action?type_id="+type_id;
                },
                cancelValue: '取消',
                cancel: function () {}
            });
            d.showModal();
        	
        }


    </script>

</head>


<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li>会员类型管理</li>
    </ul>
</div>

<div class="rightinfo">

    <div class="tools">
        <ul class="toolbar">
            
            <li class="click" onclick="toAddDialog()"><span><img src="<%=path %>/resource/admin/images/t01.png"/></span>新建会员类型</li>
        </ul>

    </div>


    <ul class="imglist">
        <s:iterator value="list" var="memberType">

					<li value="<s:property value="#memberType.type_name"/>">
						<span><img
								<s:if test="#memberType.type_status==2">class="gray"</s:if>
								src="<%=path %>/upload/membertype/<s:property value="#memberType.photo" default="default.png"/>" />
						</span>

						<h2>
							${memberType.type_name}&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">${memberType.discount}</font>折
						</h2>
						<p>
							<a href="javascript:void(0)" onclick="toUpdateDialog('<s:property value="#memberType.type_id"/>')">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" onclick="toChangeStatusDialog('<s:property value="#memberType.type_id"/>','2')">变更</a>
						</p>

						<p>
							<a href="javascript:void(0)" onclick="toDeleteDialog('<s:property value="#memberType.type_id"/>')">注销</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" onclick="toChangeStatusDialog('<s:property value="#memberType.type_id"/>','1')">授权</a>
						</p>
					</li>

				</s:iterator>
    </ul>


</div>


</body>

</html>
