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
    <script type="text/javascript">
        function toSub(){
        	/**客户姓名验证*/
            var cus_name = $.trim($("#cus_name").val());
            
            if(cus_name.length==0){
                alert("客户姓名不能为空");
                $("#cus_name").focus();
                return false;
            }
            if(cus_name.length>10){
                alert("姓名没有这么长的");
                $("#cus_name").focus();
                return false;
            }
            /**担保人姓名验证*/
            var gua_name = $.trim($("#gua_name").val());
            
            if(gua_name.length==0){
                alert("担保人姓名不能为空");
                $("#gua_name").focus();
                return false;
            }
            if(gua_name.length>10){
                alert("姓名没有这么长的");
                $("#gua_name").focus();
                return false;
            }
            /**验证客户身份证号*/
             var cus_id_card = $.trim($("#cus_id_card").val());
            
             if(cus_id_card.length==0){
            	 alert("客户身份证号必填");
            	 $("#cus_id_card").focus();
            	 return false;
             }
             /**验证担保人身份证号*/
             var gua_id_card = $.trim($("#gua_id_card").val());
             
             if(gua_id_card==0){
                 alert("担保人身份证号必填");
                 $("#gua_id_card").focus();
                 return false;
             }
             /**验证驾驶证号是否正确*/
             var cus_driver_code = $.trim($("#cus_driver_code").val());
            
             if(cus_driver_code.length==0){
                 alert("驾驶证号必填");
                 $("#cus_driver_code").focus();
                 return false;
             }
             /**验证客户和担保人的关系*/
             var relation = $.trim($("#relation").val());
            
             if(relation.length==0){
                 alert("客户和担保人关系必填");
                 $("#relation").focus();
                 return false;
             }
            //使用Ajax进行校验
           
            $.post("sys/toCheckCardCustomerAction.action",{cusIdCard:cus_id_card},function(data){
                    if(data.flag=="success"){
                         //提交
                         addForm.submit();
                    }else{
                         alert(data.message);
                         $("#cus_id_card").focus();
                         return false;
                    }
              });
        }


    </script>
 </head>

 <body>
    
    <form action="sys/addCustomerAction.action" method="post" name="addForm" enctype="multipart/form-data">
	    <table border="1">
	        
	        <tbody>
	        <tr>
	        <td style="width: 50"><label>客户姓名</label>&nbsp;&nbsp;
	        </td><td style="width: 250">
	        <input name="customer.cus_name" id="cus_name" type="text" class="dfinput" style="width: 150"/><i><font color="red"><b>*</b></font></i></td>
	        <td style="width: 50"><label>客户性别</label>&nbsp;&nbsp;
	        </td><td style="width: 250">
	        <s:select list="#{'男':'男','女':'女','未知':'未知'}" cssClass="select_show" name="customer.cus_sex" headerKey="" headerValue="请选择性别"></s:select>
	        </tr> 
	        <tr>
	        <td style="width: 50"><label>客户电话号</label>&nbsp;&nbsp;
	        </td><td style="width: 250">
	        <input name="customer.cus_tel" id="cus_tel" type="text" class="dfinput" style="width: 150"/></td>
	        <td style="width: 50"><label>客户家庭地址</label>&nbsp;&nbsp;
	        </td><td style="width: 250">
	        <input name="customer.cus_address" type="text" class="dfinput" style="width: 150"/></td>
	    
	        </tr> 
	        <tr>
	        <td style="width: 50"><label>客户工作地址</label>&nbsp;&nbsp;
	        </td><td style="width: 250">
	        <input name="customer.cus_work_address" id="cus_work_address" type="text" class="dfinput" style="width: 150"/><i></i></td>
	        <td style="width: 50"><label>客户身份证号</label>&nbsp;&nbsp;
	        </td><td style="width: 250">
	        <input name="customer.cus_id_card" id="cus_id_card" type="text" class="dfinput" style="width: 150"/><i><font color="red"><b>*</b></font></i></td>
	    
	        </tr> 
	        <tr>
	        <td style="width: 50"><label>客户驾驶证号</label>&nbsp;&nbsp;
	        </td><td style="width: 250">
	        <input name="customer.cus_driver_code" id="cus_driver_code" type="text" class="dfinput" style="width: 150"/><i><font color="red"><b>*</b></font></i></td>
	        <td style="width: 50"><label>担保人姓名</label>&nbsp;&nbsp;
	        </td><td style="width: 250">
	        <input name="customer.gua_name" id="gua_name" type="text" class="dfinput" style="width: 150"/><i><font color="red"><b>*</b></font></i></td>
	        </tr> 
	        <tr>
	        <td style="width: 50"><label>担保人性别</label>&nbsp;&nbsp;
	        </td><td style="width: 250">
	        <s:select list="#{'男':'男','女':'女','未知':'未知'}" cssClass="select_show" name="customer.gua_sex" headerKey="" headerValue="请选择性别"></s:select>
	        <td style="width: 50"><label>担保人电话</label>&nbsp;&nbsp;
	        </td><td style="width: 250">
	        <input name="customer.gua_tel" id="gua_tel" type="text" class="dfinput" style="width: 150"/></td>
	    
	        </tr> 
	        
	        <tr>
	        <td style="width: 50"><label>担保人身份证号</label>&nbsp;&nbsp;
	        </td><td style="width: 250">
	        <input name="customer.gua_id_card" id="gua_id_card" type="text" class="dfinput" style="width: 150"/><i><font color="red"><b>*</b></font></i></td>
	        <td style="width: 50"><label>担保人家庭住址</label>&nbsp;&nbsp;
	        </td><td style="width: 250">
	        <input name="customer.gua_address" id="gua_address" type="text" class="dfinput" style="width: 150"/></td>
	    
	        </tr> 
	        
	        <tr>
	        <td style="width: 50"><label>担保人单位住址</label>&nbsp;&nbsp;
	        </td><td style="width: 250">
	        <input name="customer.gua_work_address" id="gua_work_address" type="text" class="dfinput" style="width: 150"/></td>
	        <td style="width: 50"><label>与担保人关系</label>&nbsp;&nbsp;
	        </td><td style="width: 250">
	        <input name="customer.relation" id="relation" type="text" class="dfinput" style="width: 150"/><i><font color="red"><b>*</b></font></i></td>
	    
	        </tr> 
	        
	        <tr>
	        <td style="width: 50"><label>会员类型</label>&nbsp;&nbsp;
	        </td><td colspan="3">
	        <s:select list="memberTypesList" listKey="type_id" listValue="type_name" cssClass="select_show" name="type_id" id="type_id" headerKey="" headerValue="请选择会员类型"></s:select>
	        <i><font color="red"><b>带*的为必填项</b></font></i>
	        </td>
	        </tr> 
	        
	        </tbody>
	    </table>
        <li><label>&nbsp;</label><input  type="button" class="btn" value="确认保存" onclick="toSub()"/></li>
     </form>
    </div>


</body>

</html>
    