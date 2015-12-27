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
       function toSub(){
             var start_date = $("#start_date").val();
             var receivable_price = $("#receivable_price").val();
             var real_price = $("#real_price").val();
             if(start_date==0){
                 alert("租赁日期不能为空，请填写租赁日期");
                 $("#start_date").focus();
                 return false;
             }
             if(real_price<receivable_price){
            	 return false;
             }
             updateForm.submit();
       }

       function toUpdate(){
    	 
    	   var start_date = $("#start_date").val();
    	   var real_date = $("#real_date").val();
    	   var days_number = $("#days_number");
    	   var receivable_price = $("#receivable_price");
    	   var rent_price = $("#rent_price").val();
    	   var nums = DateUtil.dateDiff('d',DateUtil.strToDate(start_date),DateUtil.strToDate(real_date));
    	   if(nums==0){
    		   nums=1;
    	   }
    	   days_number.val(nums);
    	   var discount = $("#discount").val();
    	   var price;
    	   if(discount==null){
    	    	price = rent_price*nums
    	   }else{
    		   price = rent_price*nums*discount/10
    	   }
    	   receivable_price.val(price);
       }
    </script>
 </head>

 <body>
    
    <form action="sys/updateBackRentAction.action" method="post" name="updateForm" enctype="multipart/form-data">
        <table border="1">
            <tbody>
            <tr>
            <td style="width: 30"><label>客户姓名</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input type="text" class="dfinput" style="width: 150" readonly="readonly" value="<s:property value="rentCar.customer.cus_name"/> "/></td>
            <td style="width: 30"><label>车牌号</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input type="text" value="<s:property value="rentCar.carInfo.car_code"/>"  class="dfinput" style="width: 150" readonly="readonly"/>
            </tr> 
            
            <tr>
            <td style="width: 30"><label>租赁日期</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input type="text" id="start_date" value="20<s:property value="rentCar.start_date"/>" class="dfinput" style="width: 150" readonly="readonly"> 
            <td style="width: 30"><label>应还日期</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input type="text" value="20<s:property value="rentCar.end_date"/>" class="dfinput" style="width: 150" readonly="readonly">
            </tr> 
            
            <tr>
             <td style="width: 30"><label>归还日期</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input class="Wdate" type="text" id="real_date" name="rentCar.real_date" onchange="toUpdate()" onClick="WdatePicker({minDate:'%y-%M-{%d}',maxDate:'%y-%M-{%d}'})" ></td>
            <td><label>租赁天数</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input  id="days_number" name="rentCar.days_number" type="text" class="dfinput" style="width: 150" readonly="readonly"/><i></i></td>
            </tr> 
            <tr>
            <td><label>租赁金额</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input  id="rent_price" type="text" value="<s:property value="rentCar.rent_price"/>" class="dfinput" style="width: 150" readonly="readonly"/></td>
            <td><label>应收金额</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input   id="receivable_price" name="rentCar.receivable_price" type="text" class="dfinput" style="width: 150" readonly="readonly"/>
            </tr> 
            <tr>
            <td><label>实际金额</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input name="rentCar.real_price" id="real_price" type="text" class="dfinput" style="width: 150" /></td>
           <td><label>意见</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input name="rentCar.rent_desc" type="text" class="dfinput" style="width: 150" /></td>
            </tr> 
            
            </tbody>
        </table>
        <li><label>&nbsp;</label><input  type="button" class="btn" value="确认保存" onclick="toSub()"/></li>
        <input type="hidden" name="rentCar.rent_id" value="<s:property value="rentCar.rent_id"/>">
        <input type="hidden" name="car_id" value="<s:property value="rentCar.carInfo.car_id"/>">
        <input type="hidden" id="discount" value="<s:property value="rentCar.customer.memberType.discount"/>">

     </form>
    </div>


</body>

</html>