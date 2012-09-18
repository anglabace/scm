<!-- {get_spec_selects value="CATALOG"} -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Add/Update Source</title>
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

<script language="javascript" type="text/javascript" src="${global_js_url}scm/setting_quoteorder_source.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}scm/setting_quoteorder.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}scm/gs.util.js"></script>

<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" /> 
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script language="javascript" type="text/javascript">
$(document).ready(function(){
// validate signup form on keyup and submit
	$("#rate_form").validate({ 
		invalidHandler: function(form, validator) { 
			$.each(validator.invalid, function(key,value){ 
				alert(value); 
				$("#"+key).focus(); 
				return false; 
			}); 
		}, 
		rules: { 
			"rateDetail.zoneCode": { required:true }, 
			"rateDetail.weightFrom": { required:true }, 
			"rateDetail.weightTo": { required:true }, 
			"rateDetail.charge": { required:true }
		}, 
		messages: {
			"rateDetail.zoneCode":  "Please enter zone code",
			"rateDetail.weightFrom": "Please enter from weight", 
			"rateDetail.weightTo":  "Please enter to weight", 
			"rateDetail.charge":  "Please enter shipping charge"
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
	
//update rate in session
function update_rate(){
	if($("#rate_form").valid() == false) {
		return false;
	} 

	var op_type = $("#opType").val(); 
	var action = 'shipping_method!saveSessionRate.action';
	var form = $("#rate_form"); 
	var page_no = 1;
	var options = {
		success:function(data) {
			if(data == "SUCCESS"){
				//reload rate frame
				rateForm = parent.document.getElementById('list_rate_form');
				parent.document.getElementById('pageNo').value = 1;
				rateForm.submit();
					
				//close dialog
				if (op_type == 'add'){
					parent.$('#rate_add_dialog').dialog('close'); 
				}else{
					parent.$('#rate_edit_dialog').dialog('close'); 
				}
			}else{
				alert(data); 
			}
		}, 
		error: function(){
			alert('error...'); 
		}, 
		resetForm:false, 
		url:action,
		type:"POST" 
	}; 
	form.ajaxForm(options);
	form.submit();
} 
</script>

<style type="text/css">
<!--
body{width:460px; margin:0px auto}
-->
</style>
</head>

<body>


<form id="rate_form">
<input type="hidden" id="rateId" name="rateId" value="<s:if test="opType == 'add'">${idStr}</s:if><s:else>${rateDetail.idStr}</s:else>" />
<input type="hidden" id="opType" name="opType" value="${opType}" />
<input type="hidden" id="id" name="id" value="${id}" />
<input type="hidden" id="warehouseId" name="warehouseId" value="${warehouseId}" />
<table width="100%" border="0"  cellpadding="0" cellspacing="0" class="General_table"> 
 
  <tr> 
    <td colspan="4"> 
 
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table"> 
        <tr> 
          <th height="30"><span class="important">*</span>Zone</th> 
          <td colspan="3"><input id="rateDetail.zoneCode" name="rateDetail.zoneCode" type="text" class="NFText" size="12" value="${rateDetail.zoneCode}" /></td> 
        </tr> 
		 <tr> 
          <th height="30"><span class="important">*</span>From Weight </th> 
          <td><input id="rateDetail.weightFrom" name="rateDetail.weightFrom" type="text" class="NFText" size="12" value="${rateDetail.weightFrom}" onblur="check_number(this)" /></td> 
          <th><span class="important">*</span>To Weight</th> 
          <td><input id="rateDetail.weightTo" name="rateDetail.weightTo" type="text" class="NFText" size="12" value="${rateDetail.weightTo}" onblur="check_number(this)" /></td> 
        </tr> 
        <tr> 
          <th height="30"><span class="important">*</span>Shipping Charge</th> 
          <td colspan="3"><input id="rateDetail.charge" name="rateDetail.charge" type="text" class="NFText" size="12" value="${rateDetail.charge}" onblur="check_number(this)"/></td> 
        </tr> 
 
             <tr> 
          <th height="30">Note</th> 
          <td colspan="3"><input name="rateDetail.note" type="text" class="NFText" size="56" value="${rateDetail.note}" /></td> 
        </tr> 
 
      </table> 
   </td> 
  </tr> 
  <tr> 
    <td colspan="4"><div class="botton_box"> 
      <input  type="button" value="Add" class="style_botton" onclick="update_rate();"  /> 
      <input  type="button" value="Cancel" class="style_botton" onclick="javascript: parent.$('#rate_<s:if test="opType == 'add'">add</s:if><s:else>edit</s:else>_dialog').dialog('close');" /> 
    </div></td> 
  </tr> 
</table> 
</form>
</body>  
</html>
