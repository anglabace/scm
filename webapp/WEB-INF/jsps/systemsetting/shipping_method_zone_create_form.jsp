<!-- {get_spec_selects value="COUNTRY_NAME"} -->
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

<script language="javascript" type="text/javascript" src="${global_js_url}scm/gs.util.js"></script>

<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" /> 
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script language="javascript" type="text/javascript">

$(document).ready(function(){
// validate signup form on keyup and submit
	$("#zone_form").validate({ 
		invalidHandler: function(form, validator) { 
			$.each(validator.invalid, function(key,value){ 
				alert(value); 
				$("#"+key).focus(); 
				return false; 
			}); 
		}, 
		rules: { 
			"zoneDetail.zoneCode": { required:true },
			"zoneDetail.country": { required:true }, 
			"zoneDetail.zipFrom": { required:true }, 
			"zoneDetail.zipTo": { required:true }
		}, 
		messages: { 
			"zoneDetail.zoneCode":  "Please enter zone code",
			"zoneDetail.country": "Please select country", 
			"zoneDetail.zipFrom":  "Please enter from zip", 
			"zoneDetail.zipTo":  "Please enter to zip"
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
	
//update zone in session
function update_zone(){
	if($("#zone_form").valid() == false) {
		return false;
	} 

	var op_type = $("#opType").val(); 
	var id = $("#zoneId").val();
	var action = 'shipping_method!saveSessionZone.action';
	var form = $("#zone_form"); 
	var page_no = 1;
	var options = {
		success:function(data) {
			if(data == "SUCCESS"){
				//reload zone_list frame
				zoneForm = parent.document.getElementById('list_zone_form');
				parent.document.getElementById('pageNo').value = 1;
				zoneForm.submit();
					
				//close dialog
				if (op_type == 'add'){
					parent.$('#zone_add_dialog').dialog('close'); 
				}else{
					parent.$('#zone_edit_dialog').dialog('close'); 
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


<form id="zone_form">
<input type="hidden" id="zoneId" name="zoneId" value="<s:if test="opType == 'add'">${idStr}</s:if><s:else>${zoneDetail.idStr}</s:else>" />
<input type="hidden" id="opType" name="opType" value="${opType}" />
<input type="hidden" id="warehouseId" name="warehouseId" value="${warehouseId}" />
<input type="hidden" id="id" name="id" value="${id}" />
<table width="100%" border="0"  cellpadding="0" cellspacing="0" class="General_table"> 
 
  <tr> 
    <td colspan="4"> 
 
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table"> 
        <tr> 
          <th height="30"><span class="important">*</span>Zone</th> 
          <td colspan="3"><input id="zoneDetail.zoneCode" name="zoneDetail.zoneCode" type="text" class="NFText" size="12" value="${zoneDetail.zoneCode}" /></td> 
        </tr> 
        <tr> 
          <th height="30"><span class="important">*</span>Country</th> 
          <td colspan="3">
			<s:select cssStyle="width:150px" name="zoneDetail.country" list="specDropDownList['COUNTRY_NAME'].dropDownDTOs" listKey="id" listValue="name" headerKey="" headerValue="Select Country" value="zoneDetail.country"></s:select>
          </td> 
        </tr> 
             <tr> 
          <th height="30"><span class="important">*</span>From Zip </th> 
          <td><input id="zipFrom" name="zoneDetail.zipFrom" type="text" class="NFText" size="12" value="${zoneDetail.zipFrom}" onblur="check_number(this)" /></td> 
          <th><span class="important">*</span>To Zip</th> 
          <td><input id="zipTo" name="zoneDetail.zipTo" type="text" class="NFText" size="12" value="${zoneDetail.zipTo}" onblur="check_number(this)" /></td>
        </tr> 
             <tr> 
          <th height="30">Note</th> 
          <td colspan="3"><input id="zoneDetail.note" name="zoneDetail.note" type="text" class="NFText" size="58" value="${zoneDetail.note}" /></td> 
        </tr> 
 
      </table> 
   </td> 
  </tr> 
  <tr> 
    <td colspan="4"><div class="botton_box"> 
      <input  type="button" value="Add" class="style_botton" onclick="update_zone();"  /> 
      <input  type="button" value="Cancel" class="style_botton" onclick="javascript: parent.$('#zone_<s:if test="opType == 'add'">add</s:if><s:else>edit</s:else>_dialog').dialog('close');" /> 
    </div></td> 
  </tr> 
</table> 

</form>
</body>  
</html>
