<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@include  file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_url}stylesheet/scm.css" rel="stylesheet" type="text/css" />
<link href="${global_url}stylesheet/table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_url}js/show_tag.js"></script>
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script   language="JavaScript" type="text/javascript">  
function   cc(e)  
{  
	var   a   =   document.getElementsByName("mm33");  
	for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;  
} 
$().ready(function(){
	$('.ui-datepicker').each(function(){
		$(this).datepicker(
		{
			dateFormat: 'yy-mm-dd',
			changeMonth: true,
			changeYear: true
		});
	});
	$('#btntxt').datepicker();  
	$('#btntxt2').datepicker();
});
</script>
</head>
<body class="content" style="background-image:none;">
<div class="scm">
<div class="title_content">
  <div class="title"> Exception Logs </div>
</div>
  <div class="search_box">
<div class="search_order">&nbsp;</div>
<form method="get" action="exception!list.action" target="exceptionList_iframe">
<input type="hidden" name="search_type" value="search" />
<table border="0" cellpadding="0" cellspacing="0" class="General_table">
  <tr>
    <td align="right">Exception Code </td>
    <td><input name="excode" type="text" class="NFText" size="20" /></td>
    <td align="right">Exception Name </td>
    <td><input name="exname" type="text" class="NFText" size="20" /></td>
    <td align="right">Interface Name </td>
    <td><input name="iname" type="text" class="NFText" size="20" /></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="right">Severity</td>
    <td>
      <select name="severity">
        <option value="ERROR">ERROR</option>
        <option value="WARNING">WARNING</option>
        <option value="INFO">INFO</option>
      </select>
    </td>
    <td align="right">Start Date </td>
    <td><input name="startdate" type="text" class="NFText ui-datepicker" value="" size="18" id="btntxt" style="width:130px;" /></td>
    <td align="right">End Date </td>
    <td><input name="enddate" type="text" class="NFText ui-datepicker" value="" size="18" id="btntxt2" style="width:130px;" /></td>
    <td><input type="submit" value="Search" class="search_input" /></td>
  </tr>
</table>
</form>
<br/>
</div>
<iframe id="exceptionList_iframe" name="exceptionList_iframe" src="exception!list.action" width="100%" height="580" frameborder="0" scrolling="no" ></iframe>
</div>	
</body>
</html>
