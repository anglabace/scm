<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_css_url}jquery/ui.all.css" rel="stylesheet" />
		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css" />
<!--<script src="js/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="js/ui.core.js" type="text/javascript"></script>-->

<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript"> 
$(function(){
	$('.pickdate').each(function(){
		$(this).datepicker(
			{
				dateFormat: 'yy-mm-dd',
				changeMonth: true,
				changeYear: true,
				yearRange: '-100:+0'
			});
	});
});
function show_list(){
	var param = "?";
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	if($("#today").attr("checked")) {
		param = param+"today=true&id="+parent.$("#workCenterId").val();
	} else {
		if(checkDate(fromDate,toDate)) {
			alert("From date must earlier than to date!");
			return;
		}
		param = param+"fromDate="+fromDate+"&toDate="+toDate+"&id="+parent.$("#workCenterId").val();
	}
	parent.$('#GenerateBatchWorkOrdersSearchDialog').dialog("option","open", function(){
		var url = "workorder_entry!showOrderItemList.action"+ param;
		var htmlStr = '<iframe src="'+url+'" height="100%" width="580" scrolling="no" style="border:0px;" frameborder="0"></iframe>';
		parent.$('#GenerateBatchWorkOrdersSearchDialog').html(htmlStr);
	});
	parent.$('#GenerateBatchWorkOrdersSearchDialog').dialog('open'); 
}

function checkDate(fromDate,toDate) {
	var startDate=new Date(fromDate.replace(/-/g,"/"));
	var endDate = new Date(toDate.replace(/-/g,"/"));
	if(startDate>endDate) {
		return true;
	}
	return false;
}

</script>

</head>

<body>

<table width="250" border="0"  cellpadding="0" cellspacing="0" class="General_table">
          <tr>
            <th><input name="radio" type="radio" id="today" value="radio" checked="checked" /></th>
            <th>Today</th>
            <td>&nbsp;</td>
          </tr>
          
          <tr>
            <th><input type="radio" name="radio" id="custom" value="radio" /></th>
            <th width="48">From</th>
            <td><input name="fromDate" id="fromDate" type="text" class="pickdate NFText" style="width:125px;" value="     -   -  " size="20"/></td>
          </tr>
          <tr>
            <th>&nbsp;</th>
            <th width="48">To</th>
            <td><input name="toDate" id="toDate" type="text" class="pickdate NFText" style="width:125px;" value="     -   -  " size="20"/></td>
          </tr>
           <tr>
            <td height="80" colspan="3">&nbsp;</td>
          </tr>
          <tr>
            <td colspan="3"><div class="botton_box">
              <input type="button" name="Submit62"class="style_botton"  value="Search" onclick="show_list();"/>
              <input type="button" name="Submit622" value="Cancel"  class="style_botton" onclick="parent.$('#GenerateBatchWorkOrdersDialog').dialog('close');" />
            </div></td>
          </tr>
</table>
</body>
</html>