<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}thickbox/thickbox.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}thickbox/thickbox-compressed.js"></script>
<script>
$(document).ready(function(){
	$('.ui-datepicker').each(function(){
		$(this).datepicker(
		{
			dateFormat: 'yy-mm-dd',
			changeMonth: true,
			changeYear: true
		});
	});
	$('#fromdate').datepicker();  
	$('#todate').datepicker();
});
</script>
<style type="text/css">
<!--
body {
	margin-left:10px; margin-top:10px;
}
-->
</style>
</head>
<body>
<form action="cust_case!search.action" method="get" target="caseList_iframe">
<input type="hidden" name="sessCustNo" value="${sessCustNo}" />
<input type="hidden" name="custNo" value="${custNo}" />
<table width="680" border="0" cellpadding="0" cellspacing="0" class="General_table">
  <tr>
    <th>View Customer Case With </th>
    <td><div align="right">Type</div></td>
    <td>
       <s:select id="type" name="type" list="dropDownList['CASE_TYPE']" listKey="value" listValue="value"></s:select>
    </td>
    <td><div align="right">Status</div></td>
    <td>
      <s:select id="status" name="status" list="dropDownList['CASE_STATUS']" listKey="value" listValue="value" headerKey="" headerValue=""></s:select>
    </td>
  </tr>
  <tr>
  	<td></td>
    <td><div align="right">Open Date From </div></td>
    <td>
    	<input name="fromDate" id="fromDate" type="text" class="NFText ui-datepicker" value="" size="10" id="btntxt" style="width:125px;" readonly="readonly" />
    </td>
    <td><div align="right">To</div></td>
    <td>
    	<input name="toDate" id="toDate" type="text" class="NFText ui-datepicker" value="" size="10" id="btntxt2" style="width:125px;" readonly="readonly" />
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td height="30" colspan="4">
      <div align="center">
        <input type="submit" class="style_botton" value="Search" />    
      </div>
    </td>
  </tr>
</table>
</form>
<input type="hidden" id="custNo" value="${custNo}"/>
<input type="hidden" id="sessCustNo" value="${sessCustNo }" />
<iframe id="caseList_iframe" name="caseList_iframe" src="cust_case!search.action?custNo=${custNo}&sessCustNo=${sessCustNo}" width="680" height="260" frameborder="0" scrolling="no" ></iframe>

<table width="680" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
     
      <div align="center">
        <input type="button" name=""  class="style_botton" value="New" onclick="newCase()"/>
        <input type="button" name="" value="Close" class="style_botton" onclick="javascript:parent.$('#caseDialog').dialog('close');"  />
      </div></td>
  </tr>
  
</table>
<script type="text/javascript">
		var custNo = $("#custNo").val();
		var sessCustNo = $("#sessCustNo").val();
	$(document).ready(function(){
		
	});


function newCase(){
	parent.$('#newCase').dialog('open');
}
</script>
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js?v=1" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js?v=1" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js?v=1" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js?v=1" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js?v=1" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js?v=1" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1" type="text/javascript"></script>

<script type="text/javascript" src="${global_js_url}customer/customer_validate.js"></script>
<script type="text/javascript" src="${global_js_url}customer/customer_trigger.js"></script>
<script type="text/javascript" src="${global_js_url}scm/orgPicker.js"></script>
<script type="text/javascript" src="${global_js_url}scm/divPicker.js"></script>
<script type="text/javascript" src="${global_js_url}scm/deptPicker.js"></script>
</body>
</html>