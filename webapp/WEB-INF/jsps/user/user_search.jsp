<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<script type="text/javascript">var GB_ROOT_DIR = "./greybox/";</script>
<script type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/gb_scripts.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
		<script src="${global_js_url}jquery/jquery.form.js"
			type="text/javascript"></script>
<script type="text/javascript" language="javascript">
function check(){
	var paramId=document.getElementById('employeeId').value;
	var paramName=document.getElementById('employeeName').value;
	var login=document.getElementById('loginName').value;
	 if(isNaN(paramId)){
			alert("employeeId must is number !");
		}
	else {
	
	    var formStr = $("#search_form").serialize();
	    //user!userMngSch.action
		parent.frames.userInfo.location = "user!userMngSch.action?" + formStr;
		return false;
		document.userSearchForm.submit();
	}
}
$(function()
{
	$('#newButton').click(
			function()
			{
				parent.location='user!getUserPSInf.action';
			}
		);
});

</script>
</head>
<body>
<form name="search_form" action="user!userMngSch.action" id="search_form">
<table width="100%" border="0" cellpadding="0" cellspacing="0"
	class="General_table">
	<tr>
		
		<td align="right"><div id="paramId"><span>Employee ID</span></div></td>
		<td width="132"><input name="userSrchDTO.employeeId" type="text" size="20" id="employeeId" class="NFText"/></td>
		<td align="right">
		<div id="paramName"><span>Employee Name</span></td>
		<td width="131"><input name="userSrchDTO.employeeName" id="employeeName" type="text" size="20" class="NFText"/></td>
		<td align="right">
		<div id="paramLoginName"><span>Login Name</span></td>
		<td width="131"><input name="userSrchDTO.loginName" id="loginName" type="text" size="20"  class="NFText"/></td>		
		<td><input type="button" name="searchButton" value="Search" onclick="check();" class="search_input" />
			 <input type="button" name="newButton" id="newButton" value="&nbsp;&nbsp;New&nbsp;&nbsp;"  class="search_input" style="cursor:pointer" mce_style="cursor: pointer"></input>
		</td>
	</tr>
</table>
<br/>
</form>
</body>
</html>