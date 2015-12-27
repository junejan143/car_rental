<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
		<link href="<%=path %>/resource/admin/css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=path %>/resource/admin/js/jquery.js"></script>
		<!-- 引入artDailog支持的库 -->
	    <link rel="stylesheet" href="<%=path %>/resource/admin/artDialog/css/ui-dialog.css">
	    <script language="JavaScript" src="<%=path %>/resource/admin/artDialog/dist/dialog-plus.js"></script>
	   <style type="text/css">
	        .noShow{
	           display:none;
	        }
	    
	    </style>
    <script type="text/javascript">
        function toAddParentDialog(){
            //测试artDialog是否成功
            //成功需要注意jquery的版本必须是1.7+以上
            var d = top.dialog({
                id:"rightFrame",
                width:700,
                height:500,
                title: '菜单管理|添加父菜单',
                url:'sys/toAddParentMenuAction.action',//可以是一个访问路径Action|Servlet等或者jsp页面资源
                onclose: function () {
                if (this.returnValue=="success") {
                   window.location.reload();
                }

            }
            });
            d.showModal();
        }
       
        /**父节点删除方法*/
        function toDeleteParent(menu_id){
            $.post("sys/toCheckChildMenuAction.action", { menu_id: menu_id },
            function(data){
                if(data.flag=="success"){
                    //测试artDialog是否成功
                    //成功需要注意jquery的版本必须是1.7+以上
                    var d = top.dialog({
	                    title: '菜单管理 | 父节点注销',
	                    content: '您确定要<font color="red" style="font-size:22px;">彻底删除</font>该菜单吗？',
	                    okValue: '确定',
	                    ok: function () {
					                    
	                    window.location.href="sys/deleteParentMenuAction.action?menu_id="+menu_id;
	                    },
	                    cancelValue: '取消',
	                    cancel: function () {}
                    });
                    d.showModal();
                }else{
	               alert("不能删除该节点");
                    return false;
                }
                
            }, "json");
        }

        /**父节点修改方法*/
        function toUpdateParent(menu_id){
            //测试artDialog是否成功
            //成功需要注意jquery的版本必须是1.7+以上
            var d = top.dialog({
                id:"rightFrame",
                width:700,
                height:500,
                title: '菜单管理 | 父节点更新',
                url:'sys/toUpdateParentMenuAction.action?menu_id='+menu_id,//可以是一个访问路径Action|Servlet等或者jsp页面资源
                onclose: function () {
                   if (this.returnValue=="success") {
                       window.location.reload();
                    }
                }
            });
            d.showModal();
        }
        
         /**显示子节点信息*/
        var j = 0;
        function showChild(i){
            var child = $("#hidden_child"+i+"");
            child.toggle();
         
        }
        
        /**子节点的编辑和删除方法*/
        function toShowChild(obj,child_menu_id){
            var d = dialog({
                align: 'top right',
                content: '<a style="text-decoration: underline;" href="javascript:void(0)" onclick="toUpdateChild(\''+child_menu_id+'\')">编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a style="text-decoration: underline;" href="javascript:void(0)" onclick="toDeleteChild(\''+child_menu_id+'\')">删除</a>',
                quickClose: true// 点击空白处快速关闭
            });
            d.show(obj);
        }
        /**子节点删除方法*/
        function toDeleteChild(menu_id){
            //测试artDialog是否成功
            //成功需要注意jquery的版本必须是1.7+以上
             var d = top.dialog({
                title: '菜单管理 | 子节点注销',
                content: '您确定要<font color="red" style="font-size:22px;">彻底删除</font>该菜单吗？',
                okValue: '确定',
                ok: function () {
                     window.location.href="sys/deleteChildMenuAction.action?menu_id="+menu_id;
                },
                cancelValue: '取消',
                cancel: function () {}
            });
            d.showModal();
           
        }
        /**子节点修改方法*/
        function toUpdateChild(menu_id){
            //测试artDialog是否成功
            //成功需要注意jquery的版本必须是1.7+以上
            var d = top.dialog({
                id:"rightFrame",
                width:700,
                height:500,
                title: '菜单管理 | 子节点更新',
                url:'sys/toUpdateChildMenuAction.action?menu_id='+menu_id,//可以是一个访问路径Action|Servlet等或者jsp页面资源
                onclose: function () {
	               if (this.returnValue=="success") {
	                   window.location.reload();
	                }
                }
            });
            d.showModal();
        }
       
         function toAddChildDialog(){
            //测试artDialog是否成功
            //成功需要注意jquery的版本必须是1.7+以上
            var d = top.dialog({
                id:"rightFrame",
                width:700,
                height:500,
                title: '菜单管理|添加子菜单',
                url:'sys/toAddChildMenuAction.action',//可以是一个访问路径Action|Servlet等或者jsp页面资源
                onclose: function () {
                if (this.returnValue=="success") {
                   window.location.reload();
                }

            }
            });
            d.showModal();
        }

    </script>

	</head>

	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>
					<a href="#">首页</a>
				</li>
				<li>
					<a href="#">系统设置</a>
				</li>
			</ul>
		</div>
	    <div class="tools">
	        <ul class="toolbar">
	            <li class="click" onclick="toAddParentDialog()"><span><img src="<%=path %>/resource/admin/images/t01.png"/></span>添加父菜单</li>
	        </ul>
	         <ul class="toolbar">
                <li class="click" onclick="toAddChildDialog()"><span><img src="<%=path %>/resource/admin/images/t01.png"/></span>添加子菜单</li>
            </ul>
	    </div>
		<table class="tablelist">
			<thead>
				<tr>
					
					<th>
						编号
					</th>
					<th>
						菜单名称
					</th>
					<th>
                                                             图标
                    </th>
					<th>
						操作
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#parentNoteList" var="parent" status="st">
					<tr>
						<td>
							<s:property value="#st.count" />
						</td>
						<td>
							<s:property value="#parent.menu_name"/>
						</td>
						<td>
							<s:property value="#parent.icon" default="暂无"/>
						</td>

						<td>
							<a href="javascript:void(0)" class="tablelink" onclick="showChild('<s:property value="#st.count" />')">查看二级节点</a>
							<a href="javascript:void(0)" class="tablelink" onclick="toUpdateParent('<s:property value="#parent.menu_id" />')">编辑</a>
							<a href="javascript:void(0)" class="tablelink" onclick="toDeleteParent('<s:property value="#parent.menu_id" />')">删除</a>
						</td>
					</tr>
					<tr id="hidden_child<s:property value='#st.count'/>" class="noShow">
						<s:if test="#parent.childList.size>0"> 
						  <td colspan="4"/>
						      <strong>
								  <s:iterator value="#parent.childList" var="child">
								     <a href="javascript:void(0)" onclick="toShowChild(this,'<s:property value="#child.menu_id"/>')" style="text-decoration: underline;"><s:property value="#child.menu_name"/></a>&nbsp;&nbsp;|&nbsp;&nbsp;
		                          </s:iterator>
	                          </strong>
						  </td>
						</s:if>
						<s:else>
	                       <td colspan="4">
	                           <font color="red">无任何子节点可以进行删除</font>
	                       </td>
                        </s:else>
					</tr>
				
				</s:iterator>
			</tbody>
			
		</table>
		<script type="text/javascript">
            $('.tablelist tbody tr:odd').addClass('odd');
        </script>

	</body>

</html>
