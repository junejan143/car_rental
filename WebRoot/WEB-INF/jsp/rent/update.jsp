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
    <link href="<%=path %>/resource/admin/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=path %>/resource/admin/js/jquery.js"></script>
    <script type="text/javascript" src="<%=path %>/resource/admin/js/DateUtil.js"></script>
    <script language="javascript" type="text/javascript" src="<%=path %>/resource/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
       function toCheck(){
    	   var id_card = $.trim($("#id_card").val());
    	   if(id_card.length==0){
                alert("身份证号为空");
                $("#id_card").focus();
                return false;
            }
    	   $.post("sys/getCustomerRentAction.action",{id_card:id_card},function(data){
    		   
    		  if(!data){
    		      alert("该身份身份证号，无法查询，请重新录入该人员");
    		      return false;
    		  }else{
    			  $("#cus_name").val(data.cus_name);
    			  $("#cus_sex").val(data.cus_sex);
    			  $("#cus_driver_code").val(data.cus_driver_code);
    			  $("#memberType").val(data.memberType.type_name);
    			  $("#cus_tel").val(data.cus_tel);
    			  $("#cus_address").val(data.cus_address);
    			  $("#gua_name").val(data.gua_name);
    			  $("#gua_tel").val(data.gua_tel);
    			  $("#cus_id").val(data.cus_id);
    		  }
    		   
    	   });
       }
       
       function toSub(){
    	     var start_date = $("#start_date").val();
    	     if(start_date==0){
    	    	 alert("租赁日期不能为空，请填写租赁日期");
    	    	  $("#start_date").focus();
                 return false;
    	     }
    	     updateForm.submit();
       }

    </script>
 </head>

 <body>
    
    <form action="sys/updateRentAction.action" method="post" name="updateForm" enctype="multipart/form-data">
        <table border="1">
            <tbody>
            <tr>
            <td colspan="4"><input type="text" name="id_card" id="id_card" class="dfinput"><input type="button" value="检测身份证号" class="btn" onclick="toCheck()"></td>
            </tr>
           
            <tr>
            <td style="width: 30"><label>预定人姓名</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input id="cus_name" name="carInfo.reserve_user_name" type="text" class="dfinput" style="width: 150" readonly="readonly"/></td>
            <td style="width: 30"><label>性别</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input  id="cus_sex" type="text" class="dfinput" style="width: 150" readonly="readonly"/><i><font color="red"></font></i></td>
            </tr> 
            
            <tr>
            <td style="width: 30"><label>预定日期</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input class="Wdate" type="text" id="reserve_date" name="carInfo.reserve_date" onClick="WdatePicker({minDate:'%y-%M-{%d}',maxDate:'%y-%M-{%d+10}'})"></td>
            <td style="width: 30"><label></label>&nbsp;&nbsp;
            </td><td style="width: 250">
            </tr> 
            
            <tr>
            <td><label>驾驶证号</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input  id="cus_driver_code"  type="text" class="dfinput" style="width: 150" readonly="readonly"/><i></i></td>
            <td><label>客户类型</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input  id="memberType"  type="text" class="dfinput" style="width: 150" readonly="readonly"/><i><font color="red"></font></i></td>
            </tr> 
            <tr>
            <td><label>电话</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input  id="cus_tel" name="carInfo.reserve_tel" type="text" class="dfinput" style="width: 150" readonly="readonly"/></td>
            <td><label>家庭住址</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input   id="cus_address" type="text" class="dfinput" style="width: 150" readonly="readonly"/><i><font color="red"></font></i></td>
            </tr> 
            <tr>
            <td><label>担保人名称</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input  id="gua_name" type="text" class="dfinput" style="width: 150" readonly="readonly"/></td>
           <td><label>担保人电话</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input  id="gua_tel" type="text" class="dfinput" style="width: 150" readonly="readonly"/></td>
            </tr> 
             <tr>
            <td style="width: 30"><label>车牌号码</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input id="car_code" name="carInfo.car_code" value="<s:property value="carInfo.car_code"/>" type="text" class="dfinput" style="width: 150" readonly="readonly"/><i><font color="red"></font></i></td>
            <td style="width: 30"><label>车辆名称</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input id="car_name"  value="<s:property value="carInfo.car_name"/>" type="text" class="dfinput" style="width: 150" readonly="readonly"/><i><font color="red"></font></i></td>
            </tr> 
            </tbody>
        </table>
        <li><label>&nbsp;</label><input  type="button" class="btn" value="确认保存" onclick="toSub()"/></li>
        <input type="hidden" name="carInfo.car_id" value="<s:property value="carInfo.car_id"/>">

     </form>
    </div>


</body>

</html>