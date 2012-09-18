<!-- {get_spec_selects value="COUNTRY_NAME,CUSTOMER_ROLE,ORIGINAL_SOURCE,SHIP_METHOD,TERRITORY,CATEGORY,RFM_VALUE"} -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Add/Update Promotion</title>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}show_tag.js"></script>

<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.form.js" language="javascript" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js" language="javascript" type="text/javascript"></script>

<script language="javascript" type="text/javascript" src="${global_js_url}scm/gs.util.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}scm/setting_quoteorder.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}scm/setting_quoteorder_promo.js"></script>
<script src="${global_js_url}scm/Date.js" type="text/javascript"></script>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" /> 
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script language="javascript" type="text/javascript">
$(document).ready(function(){
    $('.ui-datepicker').each(function(){
		$(this).datepicker(
				{
					dateFormat: 'yy-mm-dd',
					changeMonth: true,
					changeYear: true
				});
	});

	// validate signup form on keyup and submit
	$("#coupon_edit_form").validate({ 
		invalidHandler: function(form, validator) { 
			$.each(validator.invalid, function(key,value){ 
				alert(value); 
				$("#"+key).focus(); 
				return false; 
			}); 
		}, 
		rules: { 
			couponCode: { required:true, maxlength:20, number:true, min:0}, 
			couponValue: { required:true, maxlength:11, number:true, min:0}
		}, 
		messages: { 
			couponCode: "Please enter gift card code, and the code should be is number and less than 20 digits", 
			couponValue: "Please enter gift card price, and the price should be greater than 0 and less than 11 digits"
		}, 
		errorClass:"validate_error",
		highlight: function(element, errorClass) {
		$(element).addClass(errorClass);
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).removeClass(errorClass);
		},
		errorPlacement: function(error, element) {

		}
	});	
	
	
});

function update_coupon(){
	if($("#coupon_edit_form").valid() == false) {
		return false;
	}
	<s:if test="opType != 'update'">
		var couponCode = $("#coupon_code").val();
		var reqUrl = "quote_order_coupon!checkCouponCode.action?couponCode="+couponCode;
		$.ajax({
			type: "POST",
			url: reqUrl,
			success: function(message){
				if (message != undefined) {
					if (message == 'success') {
						$("#coupon_edit_form").action = "quote_order_coupon!save.action";
						$("#coupon_edit_form").submit();
						return;
					} else if (message == 'failure') {
						alert("You enter the code in the database already exists, please re-enter another one.");
						return;
					}
				} 
				alert("System procedures, please contact the administrator!");	
			},
			error: function(xhr, textStatus){
				alert("System procedures, please contact the administrator!");
			}
		}); 
	</s:if>
	<s:else>
		$("#coupon_edit_form").action = "quote_order_coupon!save.action";
		$("#coupon_edit_form").submit();
	</s:else>
}

</script>

<style type="text/css">
<!--
body{margin:0px 0px 10px 6px ;width:480px;}
.General_table .invoice_title {
	padding:6px 0px;
	margin:0px;
	font-weight:bold;
	padding-left:5px;
}
.General_table .Indent {
	padding-left: 15px;
}
.General_table  .General_table {
	margin: 6px 0px;
}
-->
</style>
</head>

<body>
<s:if test="message == 'Success'">
	<script>alert("Save Success!");</script>
</s:if><s:elseif test="message == 'Failure'">
	<script>alert("Save Failure!");</script>
</s:elseif>
<form id="coupon_edit_form" action="quote_order_coupon!save.action" method="post">
<s:if test="id != null">
<input type="hidden" id="id" name="id" value="${id}" />
</s:if>
<s:if test="coupon != null && coupon.id != null">
<s:hidden name="coupon.id"/>
<input type="hidden" name="couponCode" value="${coupon.code}"/>
</s:if>
<input type="hidden" id="opType" name="opType" value="${opType}" />
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
      <tr>
        <th width="30%">Gift Card Code</th>
        <td><s:if test="coupon != null && coupon.id != null">
        	<input name="couponCode" id="coupon_code" value="${couponCode}" type="text" disabled="disabled" readonly="readonly" class="NFText" size="20" />
        	</s:if>
        	<s:else>
        	<input name="couponCode" id="coupon_code" value="${couponCode}" type="text" class="NFText" size="20" />
        	</s:else>
        </td>
        </tr>
      <tr>
        <th>Gift Card Name</th>
        <td><input name="couponName" id="coupon_name" value="${couponName}" type="text" class="NFText" size="20" /></td>
      </tr>
      <tr>
        <th>Price</th>
        <td>$<input name="couponValue" id="coupon_value" value="${couponValue}" type="text" class="NFText" size="20" id="txt_a"/>
        </td>
      </tr>
 
      <tr>
        <th>Description</th>
        <td><textarea name="couponComments" id="coupon_comments" class="content_textarea2" style="width:250px"><s:property value="couponComments"/></textarea></td>
      </tr>
</table>
</form>
<table width="100%" cellspacing="0" cellpadding="0" border="0">
  <tbody><tr>
      <td><div align="center">
        <input type="button" class="style_botton" value="  Save  " onclick="update_coupon(this, 'coupon_form', 'quote_order_coupon!save.action');" id="coupon_add_button"  />
        <input type="button" value="Cancel" class="style_botton" onclick="javascript:parent.$('#coupon_<c:choose><c:when test="${opType == 'add'}">add</c:when><c:otherwise>edit</c:otherwise></c:choose>_dialog').dialog('close');" />
      </div></td>
  </tr>
</tbody></table>

</body>  
</html>
