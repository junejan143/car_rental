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
    <title>信息管理系统界面</title>
    <link href="<%=path %>/resource/admin/css/style.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
        * { padding:0; margin:0; }
        html, body { height:100%; border:none 0; }
        #rightFrame { width:83%; height:100%; border:none 0; }
    </style>
    <script language="JavaScript" src="<%=path %>/resource/admin/js/jquery.js"></script>
    <!-- 引入artDailog支持的库 -->
    <link rel="stylesheet" href="<%=path %>/resource/admin/artDialog/css/ui-dialog.css">
    <script language="JavaScript" src="<%=path %>/resource/admin/artDialog/dist/dialog-plus.js"></script>
    <script type="text/javascript" src="<%=path %>/resource/dtree/wtree.js"></script>
    <link href="<%=path %>/resource/dtree/dtree.css" rel="stylesheet" type="text/css"></link>
    <script type="text/javascript">
        $(function(){
            //顶部导航切换
            $(".nav li a").click(function(){
                $(".nav li a.selected").removeClass("selected")
                $(this).addClass("selected");
            })
        })
    </script>
    <script type="text/javascript">
        $(function(){
            //导航切换
            $(".menuson li").click(function(){
                $(".menuson li.active").removeClass("active")
                $(this).addClass("active");
            });

            $('.title').click(function(){
                var $ul = $(this).next('ul');
                $('dd').find('ul').slideUp();
                if($ul.is(':visible')){
                    $(this).next('ul').slideUp();
                }else{
                    $(this).next('ul').slideDown();
                }
            });
        })
        
        function toLoginOut(){
        	var d = dialog({
			    title: '系统注销提示',
			    content: '您确定要退出系统吗？',
			    okValue: '确定',
			    ok: function () {
			        window.top.location.href="sys/loginOutLoginAction.action"
			    },
			    cancelValue: '取消',
			    cancel: function () {}
			});
			d.showModal();
        }
    </script>


</head>
<body >
    <!--头部导航信息  开始-->
    <div style="background:url(<%=path %>/resource/admin/images/topbg.gif) repeat-x;height:88px;">

    <div class="topleft">
        <a href="main.html" target="_parent"><img src="<%=path %>/resource/admin/images/logo.png" title="系统首页" /></a>
    </div>
    <!-- 
    <ul class="nav">
        <li><a href="default.html" target="rightFrame" class="selected"><img src="<%=path %>/resource/admin/images/icon01.png" title="工作台" /><h2>工作台</h2></a></li>
        <li><a href="imgtable.html" target="rightFrame"><img src="<%=path %>/resource/admin/images/icon02.png" title="模型管理" /><h2>模型管理</h2></a></li>
        <li><a href="imglist.html"  target="rightFrame"><img src="<%=path %>/resource/admin/images/icon03.png" title="模块设计" /><h2>模块设计</h2></a></li>
        <li><a href="tools.html"  target="rightFrame"><img src="<%=path %>/resource/admin/images/icon04.png" title="常用工具" /><h2>常用工具</h2></a></li>
        <li><a href="computer.html" target="rightFrame"><img src="<%=path %>/resource/admin/images/icon05.png" title="文件管理" /><h2>文件管理</h2></a></li>
        <li><a href="tab.html"  target="rightFrame"><img src="<%=path %>/resource/admin/images/icon06.png" title="系统设置" /><h2>系统设置</h2></a></li>
    </ul>
    -->
    <div class="topright">
        <ul>
            <li><span><img src="<%=path %>/resource/admin/images/help.png" title="帮助"  class="helpimg"/></span><a href="#">修改口令</a></li>
            <li><a href="#">关于</a></li>
            <li><a href="javascript:void(0)" onclick="toLoginOut()">退出</a></li>
        </ul>

        <div class="user">
            <span><s:property value="#session.session_user.user_name"/> </span>
            <i><s:property value="@com.shxt.base.utils.Lunar@getDate()"/></i>
        </div>

    </div>

    </div>

    <!--头部导航信息  结束-->

    <!--左侧导航信息  开始-->
    <div style="background:#f0f9fd;width:187px;float: left;">
        <div class="lefttop"><span></span>导航信息管理</div>

        <dl class="leftmenu">
         <!--  -->
            <s:iterator value="#parentLeftMenuList" var="parent">
            <dd>
                <div class="title">
                    <span><img src="<%=path %>/resource/admin/images/leftico01.png" /></span><s:property value="#parent.menu_name"/>
                </div>
                <ul class="menuson">
                    <s:iterator value="childLeftMenuList" var="child">
                        <s:if test="#parent.menu_id==#child.parent_id">
                            <li><cite></cite><a href="<s:property value="#child.url"/>" target="<s:property value="#child.target"/>"><s:property value="#child.menu_name"/></a><i></i></li>
                        </s:if>
                    </s:iterator>
                </ul>
            </dd>
           </s:iterator>
            
          <!-- 
          <div id="systree"></div>
         <script type="text/javascript">
                /*实例化dTree对象方法参数说明
                new dTree(objName, imgDir);
                objName：dTree实例对象名称, 
                imgDir：图标目录，相对于根目录
                */
                var d = new dTree('d','resource/dtree/images/system/menu/');
                d.config.folderLinks=true;
                /*添加节点方法完整参数说明
                add(id, pid, name, url, title, target, open, icon, iconOpen)
                id: 节点id（必须唯一，不能重复）, 
                pid: 父级节点id, 
                name: 节点名称 ,
                url: 连接地址,
                title: 工具提示文本,
                target: 打开方式/窗口,
                open: 打开（是：true/否：false）,
                icon: 节点图标 ,
                iconOpen: 节点打开时图标
                */
                d.config.useCookies=false;
                d.config.check=true;
            
                d.add(0,-1,'系统菜单',"javascript:;",'提示');
            
                //先遍历父节点
                
                <s:iterator value="#parentLeftMenuList" var="parent" status="st">
                    d.add(<s:property value="#parent.menu_id"/>,0,'<s:property value="#parent.menu_name"/>',"javascript:void(0);",'<s:property value="#parent.menu_name"/>');
                </s:iterator>
                //遍历子节点
                
                <s:iterator value="#childLeftMenuList" var="child">
                    d.add(<s:property value="#child.menu_id"/>,<s:property value="#child.parent_id"/>,'<s:property value="#child.menu_name"/>','<s:property value="#child.url"/>','<s:property value="#child.menu_name"/>','<s:property value="#child.target"/>');
                </s:iterator>

                 
            document.getElementById('systree').innerHTML = d;
          </script>
           -->
        </dl>
    </div>
    <!--左侧导航信息  结束-->

    <!-- 使用iframe加载右侧内容 -->
    <iframe id="rightFrame" name="rightFrame" src="sys/findUserAction.action" ></iframe>
    <script type="text/javascript">

        window.dialog = dialog;
    </script>


</body>
</html>