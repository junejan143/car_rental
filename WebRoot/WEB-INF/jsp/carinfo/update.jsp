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
    <!-- 引入上传图片预览JQuery支持的库 -->
    <script type="text/javascript" src="<%=path %>/resource/imagePreview/jquery.js"></script>
    <script type="text/javascript" src="<%=path %>/resource/imagePreview/imagePreview.js"></script>
    <script type="text/javascript">
        function toSub(){
            /**车牌号码验证*/
            var car_code = $.trim($("#car_code").val());
            
            if(car_code.length==0){
                alert("车牌号码不能为空");
                $("#car_code").focus();
                return false;
            }
            if(car_code.length>10){
                alert("车牌号码太长了");
                $("#car_code").focus();
                return false;
            }
            /**车辆名称验证*/
            var car_name = $.trim($("#car_name").val());
            
            if(car_name.length==0){
                alert("车辆名称为必填项");
                $("#car_name").focus();
                return false;
            }
            if(car_name.length>10){
                alert("还是短点的名字好，请换成短点的名字，谢谢合作！！！");
                $("#car_name").focus();
                return false;
            }
            /**验证租金*/
             var rent_price = $.trim($("#rent_price").val());
            
             if(rent_price.length==0){
                 alert("租金为必填哦！！！");
                 $("#rent_price").focus();
                 return false;
             }
             
             if(!isMoney(rent_price)){
                 alert("这是金额哦！！！");
                 $("#rent_price").focus();
                 return false;
             }


             /**验证保证金*/
             var deposit = $.trim($("#deposit").val());
             
             if(deposit==0){
                 alert("这是金额哦！！！");
                 $("#deposit").focus();
                 return false;
             }
             if(!isMoney(deposit)){
                 alert("这是金额哦！！！");
                 $("#deposit").focus();
                 return false;
             }
             /**验证登记人*/
             var reserve_user_name = $.trim($("#reserve_user_name").val());
            
             if(reserve_user_name.length==0){
                 alert("填上你的名字哦");
                 $("#reserve_user_name").focus();
                 return false;
             }
            
            updateForm.submit();
        }
        /*
        用途：检查输入字符串是否符合金额格式
        格式定义为带小数的正数，小数点后最多三位
        输入：
        s：字符串
        返回：
        如果通过验证返回true,否则返回false
        */
        function isMoney(s) {
        var regu = "^[0-9]+[\.][0-9]{0,3}$";
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
    
    <form action="sys/updateCarInfoAction.action" method="post" name="updateForm" enctype="multipart/form-data">
        <table border="1">
            <tbody>
            <tr>
            <td style="width: 30"><label>车牌号码</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input name="carInfo.car_code" readonly="readonly" id="car_code" value="<s:property value="carInfo.car_code"/>" type="text" class="dfinput" style="width: 150"/><i><font color="red"><b>*</b></font></i></td>
            <td style="width: 30"><label>车辆名称</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input name="carInfo.car_name" id="car_name" value="<s:property value="carInfo.car_name"/>" type="text" class="dfinput" style="width: 150"/><i><font color="red"><b>*</b></font></i></td>
            </tr> 
            <tr>
            <td style="width: 30"><label>租借金额</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input name="carInfo.rent_price" id="rent_price" value="<s:property value="carInfo.rent_price"/>" type="text" class="dfinput" style="width: 150"/><i><font color="red"><b>*</b></font></i></td>
            <td style="width: 30"><label>车辆颜色</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input name="carInfo.car_color" value="<s:property value="carInfo.car_color"/>" type="text" class="dfinput" style="width: 150"/></td>
            </tr> 
            <tr>
            <td><label>购买价格</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input name="carInfo.buy_price" id="buy_price" value="<s:property value="carInfo.buy_price"/>" type="text" class="dfinput" style="width: 150"/><i></i></td>
            <td><label>车辆保证金</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input name="carInfo.deposit" id="deposit" value="<s:property value="carInfo.deposit"/>" type="text" class="dfinput" style="width: 150"/><i><font color="red"><b>*</b></font></i></td>
            </tr> 
            <tr>
            <td><label>公里数</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input name="carInfo.km" value="<s:property value="carInfo.km"/>" type="text" class="dfinput" style="width: 150"/></td>
            <td><label>登记人</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input name="carInfo.create_user_name" value="<s:property value="carInfo.create_user_name"/>" id="reserve_user_name" type="text" class="dfinput" style="width: 150"/><i><font color="red"><b>*</b></font></i></td>
            </tr> 
            <tr>
            <td><label>车辆描述</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input name="carInfo.car_desc" value="<s:property value="carInfo.car_desc"/>" type="text" class="dfinput" style="width: 150"/></td>
            <td><label>车辆图片</label>&nbsp;&nbsp;
            </td><td style="width: 250">
            <input name="photo" type="file" class="dfinput" style="width: 150 ;height: 20" onchange="preview(this,'preview','imghead',150,100)"/>
                <div id="imagePreview" >
                <div id="preview">
                <img alt="<s:property value="carInfo.car_name"/>" id="imghead" width="150" height="100" src="<%=path %>/upload/carinfo/<s:property value="carInfo.photo" default="default.png"/>">
	            </div>
	            </div>
            </td>
            </tr> 
            <tr>
            <td><label>车辆类型</label>&nbsp;&nbsp;
            </td><td colspan="3">
            <s:select list="carTypeList" listKey="type_id" listValue="type_name" cssClass="select_show" name="type_id" value="carInfo.carType.type_id" headerKey="" headerValue="请选择汽车类型"></s:select>
            <i><font color="red"><b>带*的为必填项</b></font></i>
            </td>
            </tr> 
            
            </tbody>
        </table>
        <li><label>&nbsp;</label><input  type="button" class="btn" value="确认保存" onclick="toSub()"/></li>
        <input type="hidden" name="carInfo.car_id" value="<s:property value="carInfo.car_id"/>">
     </form>
    </div>


</body>

</html>