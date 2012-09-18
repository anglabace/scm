<%-- 
    Document   : shipping_method_weight_range_form
    Created on : 2010-10-12, 16:17:51
    Author     : jinsite
--%>

<%@page contentType="text/html" pageEncoding="GBK"%>
<%@include  file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base id="myBaseId" href="${global_url}" />
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

<script language="javascript" type="text/javascript" src="${global_js_url}scm/gs.util.js"></script>

<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script language="javascript" type="text/javascript">

var baseUrl = "${global_url}";

$(document).ready(function(){
// validate signup form on keyup and submit
	$("#weight_range_form").validate({
		invalidHandler: function(form, validator) {
			$.each(validator.invalid, function(key,value){
				alert(value);
				$("#"+key).focus();
				return false;
			});
		},
		rules: {
			weightFrom: { required:true },
			weightTo: { required:true },
			charge: { required:true }
		},
		messages: {
			weightFrom:  "Please enter from weigth",
			weightTo: "Please enter to weight",
			charge:  "Please enter shipping charge"
		},
		errorPlacement: function(error, element) {

		}
	});
});

//update weight_range in session
function update_weight_range(){
	if($("#weight_range_form").valid() == false) {
		return false;
	}
	if (parseFloat($('#weightFrom').val()) > parseFloat($('#weightTo').val())){
		alert ("From weight should be smaller than to weight!");
		$('#weightFrom').focus();
		return false;
	}

	var op_type = $("#op_type").val();
	var action = baseUrl+'system/shipping_method!saveWeightRange.action';
	var form = $("#weight_range_form");
	var page_no = 1;
	var options = {
		success:function(data) {
			//if(data == "SUCCESS"){
				//reload weight_range_list frame
				var weightRangeFrame = parent.parent.document.getElementById('weight_range_frame');
				weightRangeFrame.src = weightRangeFrame.src;

				//close dialog
				if (op_type == 'add'){
					parent.$('#weight_range_add_dialog').dialog('close');
				}else{
					parent.$('#weight_range_edit_dialog').dialog('close');
				}
			//}else{
			//	alert(data);
			//}
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


<form id="weight_range_form">
    <input type="hidden" id="id" name="id" value="${id}" />
    <input type="hidden" id="weight_id" name="weight_id" value="${weight_id}" />
    <input type="hidden" id="op_type" name="op_type" value="<s:if test="#request.op_type eq \"edit\"">edit</s:if><s:else>add</s:else>" />

<table width="100%" border="0"  cellpadding="0" cellspacing="0" class="General_table">

  <tr>
    <td colspan="4">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
		 <tr>
          <th width="23%" height="30"><span class="important">*</span>From Weight </th>
          <td width="30%"><input id="weightFrom" name="weightFrom" type="text" class="NFText" size="12" value="${shipRateWeightRange.weightFrom}" onblur="check_number(this)" /></td>
          <th width="20%"><span class="important">*</span>To Weight</th>
          <td width="27%"><input id="weightTo" name="weightTo" type="text" class="NFText" size="12" value="${shipRateWeightRange.weightTo}" onblur="check_number(this)" /></td>
        </tr>
		 <tr>
          <th height="30"><span class="important">*</span>Shipping Charge</th>
          <td colspan="3"><input id="charge" name="charge" type="text" class="NFText" size="12" value="${shipRateWeightRange.charge}" /></td>
        </tr>
		 <tr>
		   <th height="60">&nbsp;</th>
		   <td colspan="3">&nbsp;</td>
		 </tr>
      </table>
   </td>
  </tr>
  <tr>
    <td colspan="4"><div class="botton_box">
      <input  type="button" value="<s:if test="#request.op_type eq \"edit\"">Edit</s:if><s:else>Add</s:else>" class="style_botton" onclick="update_weight_range();" />
      <input  type="button" value="Cancel" class="style_botton" onclick="javascript: parent.$('#weight_range_<s:if test="op_type==\"add\"">add</s:if><s:else>edit</s:else>_dialog').dialog('close');" />
    </div></td>
  </tr>
</table>

</form>
</body>
</html>
