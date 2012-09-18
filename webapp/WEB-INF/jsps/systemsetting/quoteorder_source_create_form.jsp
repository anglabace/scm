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
    $('.ui-datepicker').each(function(){
		$(this).datepicker(
			{
				dateFormat: 'yy-mm-dd',
				changeMonth: true,
				changeYear: true
			});
	});

	$('#searchPS').dialog({
		autoOpen: false,
		height: 350,
		width: 640,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
			var htmlStr = '<iframe id="srchPdtSvc_iframe" src="pdt_serv_srch!settingsSourcePdtList.action" height="300" width="610" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			$('#searchPS').html(htmlStr); 
		}
	});

	// validate signup form on keyup and submit
	$("#source_form").validate({ 
		invalidHandler: function(form, validator) { 
			$.each(validator.invalid, function(key,value){ 
				alert(value); 
				$("#"+key).focus(); 
				return false; 
			}); 
		}, 
		rules: { 
			debug:true,
			code: { required:true }, 
			name: { required:true }, 
			description: { required:true }, 
			deptNo: { required:true }, 
			catalogId: { required:true }, 
			campaignCode: { required:true }
		}, 
		messages: { 
			code: "Please enter code", 
			name:  "Please enter sname", 
			description:  "Please enter description",
			deptNo:  "Please enter department number", 
			catalogId:  "Please select catalog",
			campaignCode:  "Please enter camapign code"
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
	

</script>

<style type="text/css">
<!--
body{margin:0px 0px 10px 6px ;width:720px;}
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


<form id="source_form">
<input type="hidden" id="id" name="id" value="${id}" />
<input type="hidden" id="sourceId" name="sourceId" value="${sourceId}" />
<input type="hidden" id="opType" name="opType" value="${opType}" />
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
  <tr>
	<th width="110"><span class="important">*</span>Code</th>
	<td width="310">
	<input name="code" id="code" type="text" class="NFText" size="20" <c:if test="${opType == 'update'}">readonly</c:if> value="${code}" /></td>
	<th width="120"><span class="important">*</span>Name</th>
	<td><input name="name" id="name" type="text" class="NFText" size="20" value="${name}" /></td>
  </tr>
  <tr>
	<th valign="top"><span class="important">*</span>Description</th>
	<td>
	  <textarea name="description" id="description" class="content_textarea2" style="width:300px;">${description}</textarea>
	</td>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
  </tr>
  <tr>
	<th><span class="important">*</span>Department No </th>
	<td><input id="deptNo" name="deptNo" type="text" class="NFText" size="20" value="${deptNo}" /></td>
	<th>Product/Service</th>
	<td><input name="catalogNo" id="catalogNo" type="text" class="NFText" size="20" value="${catalogNo}"/>
	<a href="javascript:void(0)" onclick="javascript:showSearchBox()" class="thickbox" title="Search Product/Service"><img src="${global_image_url}search.gif" width="16" height="16" border="0" align="absmiddle" /></a>
	</td>
  </tr>
  <tr>
	<th><span class="important">*</span>Campaign Code </th>
	<td><input id="campaignCode" name="campaignCode" type="text" class="NFText" size="20" value="${campaignCode}" /></td>
	<th><span class="important">*</span>Catalog </th>
	<td><select id="catalogId" name="catalogId" >
	<s:iterator value="specDropDownist" >
	<option value="">None</option>
	<s:if test="name=='CATALOG'">
	<s:iterator value="dropDownDTOs">
		 <option value="${id}" label="${name }" <c:if test="${catalogId == id}">selected="${id}"</c:if> >${name }</option>
		 </s:iterator>
        	</s:if>
        	</s:iterator>
	</select></td>
  </tr>
</table>
<div class="invoice_title" style="padding-top:0px;"><img style="cursor:pointer;" onclick="toggleShowMore_img(this,'Cost');" src="${global_image_url}ad.gif" width="11" height="11" /> Cost</div>
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table" id="Cost" style="display:block;" >
    <tr>
      <th width="110">Insertion/List Cost </th>
      <td width="310"><input id="listCost" name="listCost" type="text" class="NFText" size="20" value="<c:if test="${empty listCost}">0.00</c:if>${listCost}"/></td>
      <th width="120" >Postage Cost </th>
      <td><input id="postageCost" name="postageCost" type="text" class="NFText" size="20" value="<c:if test="${empty postageCost}">0.00</c:if>${postageCost}"/></td>
    </tr>
    <tr>
      <th>Printing Cost </th>
      <td><input id="printingCost" name="printingCost" type="text" class="NFText" size="20" value="<c:if test="${empty printingCost}">0.00</c:if>${printingCost}"/></td>
      <th>Mailing Cost </th>
      <td><input id="mailingCost" name="mailingCost" type="text" class="NFText" size="20" value="<c:if test="${empty mailingCost}">0.00</c:if>${mailingCost}"/></td>
    </tr>
    <tr>
      <th>Total Cost </th>
      <td><input id="total_cost" name="total_cost" readonly="readonly" type="text" class="NFText2" value="${totalCost}" size="20" /></td>
      <th width="120">&nbsp;</th>
      <td>&nbsp;</td>
    </tr>
  </table>
  <div class="invoice_title"><img style="cursor:pointer;" onclick="toggleShowMore_img(this, 'Adver');" src="${global_image_url}ad.gif" width="11" height="11" /> Advertisement</div>
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table" id="Adver" style="display:block;">
    <tr>
      <th width="110">Effective Date </th>
      <td width="310"><input name="adEffFrom" type="text" class="ui-datepicker" style="width:125px;" value="<s:date name="adEffFrom" format="yyyy-MM-dd"/>" size="18"/></td>
      <th width="120">Expiration Date </th>
      <td><input name="adEffTo" type="text" class="ui-datepicker" style="width:125px;" value="<s:date name="adEffTo" format="yyyy-MM-dd"/>" size="18"/></td>
    </tr>
    <tr>
      <th>Description </th>
      <td>
      <textarea name="adDescription" class="content_textarea2" style="width:300px;">${adDescription}</textarea></td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <th>&nbsp;</th>
      <td colspan="3">(e.g.,Size/Color/Position of Ad, Size of Mailing or Circulation...) </td>
    </tr>
    <tr>
      <th>List Publisher </th>
      <td colspan="3"><input name="adPublisher" type="text" class="NFText" size="20" value="${adPublisher}" /></td>
    </tr>
    <tr>
      <th>Ad Rate </th>
      <td><input name="adRate" type="text" class="NFText" size="20" value="${adRate}" onblur="check_number_source(this)" /></td>
      <th>Number of Insertion </th>
      <td><input name="adInsCount" type="text" class="NFText" size="20" value="${adInsCount}" onblur="check_number_source(this)" /></td>
    </tr>
    <tr>
      <th>URL Referrer </th>
      <td colspan="3"><input name="urlRefer" type="text" class="NFText" size="20" value="${urlRefer}" /></td>
    </tr>
    <tr>
      <td height="35" colspan="4" valign="bottom" class="tab_title"><strong class="blue_price">Optional Sales Message to appear on the Invoice: </strong></td>
    </tr>
    <tr>
      <th width="110">&nbsp;</th>
      <td colspan="3"><input name="invoiceMsg" type="text" class="NFText"  style="width:300px;" value="${invoiceMsg}" /></td>
    </tr>
    <tr>
      <th>Comments:</th>
      <td colspan="3"><textarea name="comment" class="content_textarea2" style="width:300px;">${comment}</textarea></td>
    </tr>
  </table>
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td><div align="center">
      <saveButton:saveBtn parameter="${operation_method}"
		disabledBtn='<input type="button" class="style_botton" value="  Save  " disabled="disabled"  />'
		saveBtn='<input type="button" class="style_botton" value="  Save  " onclick="update_source();" id="source_add_button"  />'
	  /> 
        <input type="button" value="Cancel" class="style_botton" onclick="javascript:parent.$('#source_<c:choose><c:when test="${empty opType == 'add'}">add</c:when><c:otherwise>edit</c:otherwise></c:choose>_dialog').dialog('close');"/>
        <!-- <input name="Submit3" type="submit" class="style_botton4"  onclick="window.location.href='sales_statistics.html'" value="View Sales Statistics"/> -->
      </div></td>
    </tr>
  </table> 
</form>



<!-- poped serach product service dialog -->
<div id="searchPS" title="Search Product/Service"></div>
</body>  
</html>
