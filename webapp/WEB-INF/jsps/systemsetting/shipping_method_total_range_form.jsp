<%-- 
    Document   : shipping_method_total_range_form
    Created on : 2010-10-12, 15:58:43
    Author     : jinsite
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
	$("#total_range_form").validate({
		invalidHandler: function(form, validator) {
			$.each(validator.invalid, function(key,value){
				alert(value);
				$("#"+key).focus();
				return false;
			});
		},
		rules: {
			totalFrom: { required:true },
			totalTo: { required:true }
		},
		messages: {
			totalFrom:  "Please enter from total",
			totalTo:  "Please select to total"
		},
		errorPlacement: function(error, element) {

		}
	});
});

//update total range in session
function update_total_range(){
	if($("#total_range_form").valid() == false) {
		return false;
	}
	if (parseFloat($('#totalFrom').val()) > parseFloat($('#totalTo').val())){
		alert ("From total should be smaller than to total!");
		$('#totalFrom').focus();
		return false;
	}
	if (! $('#charge').val() && ! $('#chargePct').val()){
		alert ("Please enter charge or charge percentage!");
		$('#charge').focus();
		return false;
	}

	var op_type = $("#op_type").val();
	var action = baseUrl+'system/shipping_method!saveTotalRange.action';
	var form = $("#total_range_form");
	var page_no = 1;
	var options = {
		success:function(data) {
			//if(data == "SUCCESS"){
				var totalRangeFrame = parent.parent.document.getElementById('total_range_frame');
				totalRangeFrame.src = totalRangeFrame.src;
				//close dialog
				if (op_type == 'add'){
					parent.$('#total_range_add_dialog').dialog('close');
				}else{
					parent.$('#total_range_edit_dialog').dialog('close');
				}
			//}else{
				//alert(data);
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


<form id="total_range_form">
<input type="hidden" id="id" name="id" value="${id}" />
<input type="hidden" id="range_id" name="range_id" value="${range_id}" />
<input type="hidden" id="op_type" name="op_type" value="<s:if test="#request.op_type eq \"edit\"">edit</s:if><s:else>add</s:else>" />

<table width="100%" border="0"  cellpadding="0" cellspacing="0" class="General_table">

  <tr>
    <td colspan="4">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
		 <tr>
          <th width="23%" height="30"><span class="important">*</span>From Total </th>
          <td width="30%"><input id="totalFrom" name="totalFrom" type="text" class="NFText" size="12" value="${shipRateTotalRange.totalFrom}" onblur="check_number(this)" /></td>
          <th width="20%"><span class="important">*</span>To Total</th>
          <td width="27%"><input id="totalTo" name="totalTo" type="text" class="NFText" size="12" value="${shipRateTotalRange.totalTo}" onblur="check_number(this)" /></td>
        </tr>
		 <tr>
          <th height="30"><span class="important">*</span>Charge</th>
          <td><input id="charge" name="charge" type="text" class="NFText" size="12" value="${shipRateTotalRange.charge}" onblur="check_number(this)" /></td>
          <th height="30"><span class="important">*</span>Charge Percentage</th>
          <td><input id="chargePct" name="chargePct" type="text" class="NFText" size="12" value="${shipRateTotalRange.chargePct}" onblur="check_number(this)" />%</td>
        </tr>
      </table>
   </td>
  </tr>
  <tr>
    <td colspan="4"><div class="botton_box">
            <input  type="button" value="<s:if test="#request.op_type eq \"edit\"">Edit</s:if><s:else>Add</s:else>" class="style_botton" onclick="update_total_range();"  />
      <input  type="button" value="Cancel" class="style_botton" onclick="javascript: parent.$('#total_range_<s:if test="op_type==\"add\"">add</s:if><s:else>edit</s:else>_dialog').dialog('close');" />
    </div></td>
  </tr>
</table>

</form>
</body>
</html>
