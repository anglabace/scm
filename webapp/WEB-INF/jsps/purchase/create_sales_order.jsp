<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script type="text/javascript" language="javascript"
			src="${global_js_url}jquery/jquery.js"></script>
			<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
       <script src="${global_js_url}jquery/jquery.validate.js?v=1" type="text/javascript"></script>
		<script type="text/javascript">
		 $(function() {
			 $('.ui-datepicker').each(function(){
					$(this).datepicker(
						{
							dateFormat: 'yy-mm-dd',
							changeMonth: true,
							changeYear: true
						});
				});
			//验证form的逻辑
			   $('#createPurchaseOrder_form').validate({
					errorClass:"validate_error",
					highlight: function(element, errorClass) {
						$(element).addClass(errorClass);
				    },
				    unhighlight: function(element, errorClass, validClass) {
				        $(element).removeClass(errorClass);
				    },
					invalidHandler: function(form, validator) {
						 $.each(validator.invalid, function(key,value){
				            alert(value);
				            $(this).find("name=[" + key + "]").focus();
				            return false;
				        });
					 },
					 rules: {
						 "warehouseId": { required:true},
					 	"currency": { required:true}
					 },
					 messages: {
						 "warehouseId": { required : "Please enter the warehouse name !" },
						 "currency": { required : "Please enter the currency code !" }
					 },
					 errorPlacement: function(error, element) {
					 }
				});
			   $(function() {            
	            	parent.$('#customer_search_dlg').dialog({
						autoOpen: false,
						height: 500,
						width: 660,
						modal: true,
						bgiframe: true,
						buttons: {
						}
					});
	            });
			   
				//绑定保存按钮事件.            
              $("#save_btn").click (function() {
                 if ($('#createPurchaseOrder_form').valid()  === false ) {
                    return false;
                 }
                 var formStr = $('#createPurchaseOrder_form').serialize();
                 $('#save_btn').attr("disabled", true);
                 $.ajax({
						type: "POST",
						url: "cust_processing_order!save.action",
						data: formStr,
						dataType: 'json',
						success: function(data, textStatus){
							if(hasException(data)){//有错误信息.
				 	           $('#save_btn').attr("disabled", false);				
							}else{                              
							  alert(data.message);
							  if(data.WO!=null&&data.WO!=''&&data.WO!='undefind') {
								  alert(data.WO);
							  }
							  parent.$('#createPurchaseOrder_form').dialog('close');
							  parent.location = parent.location;
							}
						},
						error: function(xhr, textStatus){
						   alert("failure");
				 	       $('#save_btn').attr("disabled", false);
						}
					});                
              });//end of {$("#save_btn").click};               
          
          });
		 function openSearchDlg() {
				parent.$('#customer_search_dlg').dialog("option", "open", function() {	
			         var htmlStr = '<iframe src="customer_picker.action" height="450" width="620" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			         parent.$('#customer_search_dlg').html(htmlStr);
				});
				parent.$('#customer_search_dlg').dialog('open');

			}	
      </script>
</head>
<body>

    
 <form method="get" id="createPurchaseOrder_form">
   <table border="0" align="center" cellpadding="0" cellspacing="0" class="General_table">
   <input name="orderNoStr" type="hidden" value="${orderNoStr}"/>
     <tr>
    <th>&nbsp;</th>
    <td>&nbsp;</td>
    <th>&nbsp;</th>
    <td>&nbsp;</td>
    </tr>

   
  <tr>
    <th>Customer No</th>
    <td align="left">
   <input name="custNo" id="custNo" type="text" class="NFText" size="20" value="${custNo}" readonly="readonly"/>
   <a href="javascript:void(0);" onclick="openSearchDlg();"><img
									src="${global_image_url}search.gif" width="16" height="16" border="0px" />
							</a></td>
    <th>Customer Name</th>
    <td align="left">
    <input name="custName" id="custName" type="text" class="NFText" size="20"  readonly="readonly"/></td>
    </tr>
  <tr>
    <th>Priority</th>
    <td align="left"><s:select list="{'Urgent', 'High', 'Medium', 'Low', 'Minor'}" value="priority" name="priority" style="width:132px;"></s:select>
   </td>
   <th>Deliver to Customer</th>
    <td align="left">
    <s:select cssStyle="width:132px" name="warehouseId" list="specDropDownList['WAREHOUSE'].dropDownDTOs" listKey="id" listValue="name" headerKey="" headerValue="Select Warehouse" value="warehouseId"></s:select></td>
    </tr>
    <tr>
    <th>Expected Date</th>
    <td align="left">

      <input name="expectedDate" type="text" class="ui-datepicker" size="18" value="<s:date name="expectedDate" format="yyyy-MM-dd"/>" style="width:125px;"/>
    </td>
    </tr>
      <tr>
    <th>Total Cost</th>
    <td align="left"><input name="subTotal" type="text" class="NFText" size="20" value="${subTotal}"/></td>
    <th>Currency</th>
    <td align="left"><s:select cssStyle="width:132px" name="currency" list="specDropDownList['CURRENCY'].dropDownDTOs" listKey="name" listValue="name" headerKey="" headerValue="Select Currency" value="currency"></s:select></td>
    </tr>
     <tr>
    <th>Order Date</th>
    <td align="left"><input name="orderDate" type="text" class="ui-datepicker" value="<s:date name="orderDate" format="yyyy-MM-dd"/>" size="18" style="width:126px;" readonly="readonly"/></td>
    <th>Ordered By</th>

    <td align="left"><input name="orderedBy" type="text" class="NFText" value="${orderedBy}" size="20" readonly="readonly"/></td>
    </tr>
  <tr>
    <th>Comment</th>
    <td colspan="3" align="left">
      <input name="comment" type="text" class="NFText" size="79" value="${comment}"/>
   </td>
    </tr>
    </table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
						<div class="botton_box">
						<saveButton:saveBtn parameter="${operation_method}"
							disabledBtn='<input type="button" class="style_botton" value="Save" disabled="disabled"/>'
							saveBtn='<input name="Submit" id="save_btn" type="button" class="style_botton" value="Save" />'
						/> 
							<input type="button" name="Submit2" value="Cancel"
								class="style_botton"
								onclick="parent.$('#salesOrderDialog').dialog('close');" />
						</div>
					</td>
    </tr>
   </table>
   
   <br /></td>
  </tr>

</table>
</form>

  
<script>

</script>
</body>
</html>
