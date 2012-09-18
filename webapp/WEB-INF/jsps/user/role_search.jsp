<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<%@ include file="/common/taglib.jsp"%>
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
	type="text/css" />
<link href="${global_css_url}greybox/gb_styles.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}TabbedPanels.js"></script>
<script type="text/javascript">
	var GB_ROOT_DIR = "./greybox/";
</script>
<script type="text/javascript"
	src="${global_js_url}greybox/AJS.js"></script>
<script type="text/javascript"
	src="${global_js_url}greybox/AJS_fx.js"></script>
<script type="text/javascript"
	src="${global_js_url}greybox/gb_scripts.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script type="text/javascript" language="javascript">
function check(){
	var paramId=document.getElementById('roleId').value;
	var paramName=document.getElementById('roleName').value;
	if(isNaN(paramId)){
		alert(" roleId must be number !");
		}
	else {
		document.roleSearchForm.submit();
	}
}
$(function()
		{
			$('#newButton').click(
					function()
					{
						parent.location='user!getRolePSInf.action';
					}
				);
		});
</script>
</head>
<body>
<form name="roleSearchForm" id="roleSearchForm" action="user!roleMngSch.action" target="roleInfo">
<table width="758" border="0" cellpadding="0" cellspacing="0"
	class="General_table">
	<tr>
		<td width="67" height="32" align="right"><div id="paramId">Role ID</td>
		<td width="162"><input name="roleSrchDTO.roleId" id="roleId" type="text" value="" size="25" class="NFText" /></td>
		<td width="91" align="right"><div id="paramName">Role Name</td>
		<td width="161"><input name="roleSrchDTO.roleName" id="roleName" type="text" size="25"  class="NFText"/></td>
		<td>
		<input type="button" id="searchButton" name="searchButton" value="Search" onclick="check()"  class="search_input"/>
		 <input type="button" id="newButton" name="newButton" value="&nbsp;&nbsp;New&nbsp;&nbsp;" class="search_input" style="cursor:pointer" mce_style="cursor: pointer" />
		 </td>
	</tr>	
</table>
<br/>
</form>
</body>
</html>