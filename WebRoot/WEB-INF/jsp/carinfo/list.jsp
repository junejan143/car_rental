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
    <title>无标题文档</title>
    <link href="<%=path%>/resource/admin/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=path%>/resource/admin/js/jquery.js"></script>
    <!-- 引入放大镜的jQuery库 -->
    <link rel="stylesheet" href="<%=path%>/resource/image-zoom/css/jqzoom.css" type="text/css">
	<script src="<%=path%>/resource/image-zoom/js/jquery-1.3.2.min.js" type="text/javascript"></script>
	<script src="<%=path%>/resource/image-zoom/js/jqzoom.pack.1.0.1.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(function() {
	    var options =
	    {
	        zoomWidth: 250, //放大镜的宽度
	        zoomHeight: 250,//放大镜的高度
	        zoomType:'reverse'
	    };
	    $(".jqzoom").jqzoom(options);
	});
	</script>
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
                title: '车辆管理 | 添加',
                url:'sys/toAddCarInfoAction.action',//可以是一个访问路径Action|Servlet等或者jsp页面资源
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
     function toUpdate(car_id){
            //成功需要注意jquery的版本必须是1.7+以上
            var d = top.dialog({
                width:700,
                height:400,
                title: '车辆管理 | 更新',
                url:'sys/toUpdateCarInfoAction.action?car_id='+car_id,//可以是一个访问路径Action|Servlet等或者jsp页面资源
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
    var j = 0 ;
	 function add(){
		//获取当前页
        var page_now = $("#pageNow").val();
        //获取总页数
        var page_total = $("#totalPage").val();
        //获取每页显示条数
        var page_size = $("#page_size").val();
        var i = page_size;
        page_size = page_size+i;
		$.post("sys/findListCarInfoAction.action",{page_size:page_size},function(data){
			j++;
			for(var i=4*j;i<4*j+5;i++){
				var str = "";
				if(data.datas[i].car_status==1){
					str = "<td><strong><font color='green'>可租</font></strong></td>";
				}else if(data.datas[i].car_status==2){
					str = "<td><strong><font color='red'>预定</font></strong></td>";
				}else if(data.datas[i].car_status==3){
					str = "<td><strong><font color='red'>维修</font></strong></td>";
				}else if(data.datas[i].car_status==4){
					str = "<td><strong><font color='red'>报废</font></strong></td>";
				}else{
					str = "<td><strong><font color='red'>租赁</font></strong></td>";
				}
				
				
            	$("#show").append("<tr><td>"+(i+1)+"</td>" +
            	"<td>"+data.datas[i].car_name+"</td>" +
            	"<td>"+data.datas[i].car_code+"</td>" +
            	str+
            	"<td>"+data.datas[i].car_color+"</td>" +
            	"<td>"+data.datas[i].buy_price+"</td>" +
            	"<td>"+data.datas[i].carType.type_name+"</td>" +
            	"<td>" +
            	"<a href="+'<%=path%>'+"/upload/carinfo/"+data.datas[3].photo+" class='jqzoom' >" +
            	"<img  style='border: 1px solid #666;'  width='200' height='130' src="+'<%=path%>'+"/upload/carinfo/"+data.datas[3].photo+">" +
            	"</a></td>" +
            	"<td>" +
            	"<a href=\"javascript:void(0)\" onclick=\"toUpdate("+data.datas[i].car_id+")\" class=\"tablelink\">编辑</a>" +
            	"</tr>")
            }
            
             
            
	     },"json");
	}
    
    
</script>

</head>

<body>

    <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li>汽车分页操作</li>
    </ul>
    </div>
        <div class="tools">
            <form action="sys/findCarInfoAction.action" method="post" name="searchForm">
                <ul class="seachform">
                <li><label>车辆名称</label>
                <s:textfield name="query.car_name" cssClass="scinput"></s:textfield>
                <li><label>车辆类型</label>  
                <s:select list="carTypeList" listKey="type_id" listValue="type_name" cssClass="select_show" name="query.type_id" headerKey="" headerValue="请选择汽车类型"></s:select>
                </li>
                 <li><label>车辆状态</label>  
                <s:select list="#{'1':'可租','2':'预定','3':'维修','4':'报废 ','5':'租赁'}" cssClass="select_show" name="query.car_status" headerKey="" headerValue="请选择汽车状态"></s:select>
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



        <table class="tablelist" id="show">
        <thead>
        <tr>
        <th>编号</th>
        <th>名称</th>
        <th>车牌号</th>
        <th>状态</th>
        <th>颜色</th>
        <th>购买价格</th>
        <th>型号</th>
        <th>图片</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody >
          <div style="text-align:center;clear:both;">
               <script src="/gg_bd_ad_720x90.js" type="text/javascript"></script>
               <script src="/follow.js" type="text/javascript"></script>
          </div>
          <s:iterator value="pageBean.datas" var="carInfo" status="st">
            <tr>
                <td><s:property value="#st.count"/> </td>
                <td><s:property value="#carInfo.car_name"/></td>
                <td><s:property value="#carInfo.car_code"/></td>
                <td>
                   <s:if test="#carInfo.car_status==1"><strong><font color='green'>可租</font></strong></s:if>
                   <s:elseif test="#carInfo.car_status==2"><strong><font color='red'>预定</font></strong></s:elseif> 
                   <s:elseif test="#carInfo.car_status==3"><strong><font color='red'>维修</font></strong></s:elseif> 
                   <s:elseif test="#carInfo.car_status==4"><strong><font color='red'>报废</font></strong></s:elseif> 
                   <s:else><strong><font color='red'>租赁</font></strong></s:else>
                </td>
                <td><s:property value="#carInfo.car_color"/></td>
                <td><s:property value="#carInfo.buy_price"/></td>
                <td><s:property value="#carInfo.carType.type_name" default="<font color='red'>未录入型号</font>" escape="false"/></td>
              
	            <td>
	               <a href="<%=path %>/upload/carinfo/<s:property value="#carInfo.photo" default="default.png"/>"
	                class="jqzoom" style="" title="<s:property value="#carInfo.photo"/>"> 
	               <img title="<s:property value="#carInfo.photo" />" 
	               style="border: 1px solid #666;"
	               width="200" height="130"
	               src="<%=path %>/upload/carinfo/<s:property value="#carInfo.photo" default="default.png"/>" />
	               </a> 
	            </td>
                <td><a href="javascript:void(0)" onclick="toUpdate('<s:property value="#carInfo.car_id"/>')" class="tablelink">编辑</a></td>
            </tr> 
         </s:iterator>
        </tbody>
    </table>
    
     <div class="pagin">
        <div class="message">共<i class="blue"><s:property value="pageBean.totalCount"/></i>条记录，当前显示第&nbsp;<i class="blue"><s:property value="pageBean.pageNow"/>&nbsp;/&nbsp;<s:property value="pageBean.totalPage"/></i>页
        <s:select list="#{'15':'每页显示15条','10':'每页显示10条','4':'每页显示4条'}" id="page_size" onchange="toPage('6')" cssStyle="border: 1px solid #6CB2E3;" value="pageBean.pageSize"></s:select>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           <input type="button" value="加载更多" onclick="add()" style="border: 1px solid #ccc;height: 30px;width: 60px;background: #066CAC;color: white;font-weight: bold;">
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