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
    <title> | </title>
    <script type="text/javascript" src="<%=path %>/resource/fusioncharts/FusionCharts.js"></script><!--引入主类库-->
    <!-- 引入Jquery的支持库，使用Ajax技术 -->
    <script type="text/javascript" src="<%=path %>/resource/fusioncharts/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        $(function(){
            toShow();
            
        });
        function toShow(){
            var name = $("#sel").val();
            $.post("sys/showRoleChartAnalysisAction.action",function(data){
                var chart = new FusionCharts("resource/fusioncharts/"+name, "ChartId", "650", "300", "0", "0");
                //创建一个FusionCharts对象，第一个参数为swf文件的路径，第二个为id用来标识这个对象，第三个为宽度，第四个为高度
                //chart.setDataURL("data/data.xml");//xml文件
                //  chart.setDataXML(xmldata);
                chart.setJSONData(data);
                chart.render("mzl");//将FusionCharts对象填充到指定的div标签处：render（div）
            });
        }
        
        function showData(role_id){
            var d = top.dialog({
                width:1000,
                height:550,
                title: '查看详细',
                url:'sys/toShowRoleAnalysisAction.action?role_id='+role_id,//可以是一个访问路径Action|Servlet等或者jsp页面资源
                onclose: function () {
                if (this.returnValue) {
                    alert(this.returnValue);
                }

            }
            });
            d.showModal();
        }
    </script>

</head>
<body>
<select id="sel" onchange="toShow()">
    <option value="Pie3D.swf">3D饼状图</option>
    <option value="Column2D.swf">2D柱状图</option>
    <option value="Doughnut2D.swf">2D环状图</option>
</select>
<div id="mzl">在这加载图形</div><!--定义一个div用于存放图形-->
</script>
</body>

</html>