<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>User list</title>
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"> var GB_ROOT_DIR = "${global_js_url}greybox/";</script>
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}greybox/gb_scripts.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<link href="${global_js_url}greybox/gb_styles.css"   rel="stylesheet" type="text/css" media="all" />
<link href="${global_css_url}stylesheet/tab-view.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form id="roleDataForm" name="roleDataForm">
<table border="0" cellpadding="0" cellspacing="0" class="General_table">

	<tr>
		<th width="93">Role ID</th>
		<td width="422"><input name="roleId" id="roleId" type="text" class="NFText" size="50" value="${roleDto.roleId}" readonly /></td>
	</tr>
	<tr>
		<th>Name</th>
		<td><input name="roleName" id="roleName" type="text" class="NFText" size="73" value="${roleDto.roleName}" /></td>
	</tr>
	<tr>
		<th valign="top">Description</th>
		<td><textarea name="description" id="description" type="text" class="content_textarea2">${roleDto.description}</textarea></td>
	</tr>
</table>
</form>
</body>
</html>