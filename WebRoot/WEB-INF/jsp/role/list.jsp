<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
                title: '角色管理|添加',
                url:'sys/toAddRoleAction.action',//可以是一个访问路径Action|Servlet等或者jsp页面资源
                onclose: function () {
	                if (this.returnValue=="success") {
                         window.location.reload();
                    }
               }
            });
            d.showModal();
        }
        
        /***
         * 修改操作
         * @memberOf {TypeName} 
         * @return {TypeName} 
         */
         
         function toUpdate(role_id){
        	  var d = top.dialog({
                width:800,
                height:500,
                title: '角色管理 | 更新',
                url:'sys/toUpdateRoleAction.action?role_id='+role_id,//可以是一个访问路径Action|Servlet等或者jsp页面资源
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
        
         function toAuthorize(role_id){
        	 var d = top.dialog({
                width:800,
                height:500,
                title: '角色管理 | 授权',
                url:'sys/toAuthorizeRoleAction.action?role_id='+role_id,//可以是一个访问路径Action|Servlet等或者jsp页面资源
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
         
          function toDelete(role_id){
             var d = top.dialog({
                title: '角色管理 | 注销',
                content: '您确定要<font color="red" style="font-size:22px;">彻底删除</font>该角色吗？',
                okValue: '确定',
                ok: function () {
                    
                    window.location.href="sys/deleteRoleAction.action?role_id="+role_id;
                },
                cancelValue: '取消',
                cancel: function () {}
            });
            d.showModal();
        }
          
        //变更状态方法
        function toChangeStatus(role_id,role_status){
        	if(role_status==1){
        		role_status=2;
        	}else{
        		role_status=1;
        	}
            var d = top.dialog({
            title: '提示',
            content: '您确定要变更角色吗?',
            okValue: '确定',
            ok: function () {
                //使用Ajax进行变更    
                $.post("sys/changStatusRoleAction.action",{role_id:role_id,role_status:role_status},function(data){
                    //alert(data.flag)
                    if(data.flag=="success"){
                        window.location.reload();
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
        <li>角色管理</li>
    </ul>
</div>

<div class="rightinfo">

    <div class="tools">
        <ul class="toolbar">

            <li class="click" style="cursor: pointer;" onclick="toAddDialog()"><span><img src="<%=path %>/resource/admin/images/t01.png"/></span>新建角色</li>
        </ul>

    </div>


			<ul class="imglist">
			<s:iterator value="roleList" var="role">
					<li>
						<span><img 
						<s:if test="#role.role_status==2">class="gray"</s:if>
						title="${role.role_desc}" src="<%=path %>/upload/role/${role.role_photo}" />
						</span>
						<h2 >
							<s:property value="#role.role_name"/> 
						</h2>
						<p>
							<a href="javascript:void(0)" onclick="toUpdate('<s:property value="#role.role_id"/>')">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" onclick="toAuthorize('<s:property value="#role.role_id"/>')">授权</a>
						</p>
						<p>
							<s:if test="%{#role.role_id!=#session.session_user.role.role_id}">
								<a href="javascript:void(0)" onclick="toDelete('${role.role_id}')">注销</a>&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="javascript:void(0)" onclick="toChangeStatus('${role.role_id}','${role.role_status}')" >变更</a>
							</s:if>
						</p>
				
					</li>
				</s:iterator>
			</ul>


		</div>


</body>

</html>