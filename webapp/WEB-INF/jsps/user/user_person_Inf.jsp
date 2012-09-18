<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Quotation Management</title>
        <base id="myBaseId" href="${global_url}" />
        <link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
        <link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
        <link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"  />
        <link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />

        <script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>
        <script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
        <script language="javascript" type="text/javascript" src="${global_js_url}expland.js"></script>
        <script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}gb_style.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript">
	var GB_ROOT_DIR = "${global_js_url}greybox/";
</script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}TabbedPanels.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}greybox/AJS.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}greybox/AJS_fx.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}greybox/gb_scripts.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>

</head>

<body>

<form id="userDataForm"  name="userDataForm" >
<table width="750" border="0" cellpadding="0" cellspacing="0"
	class="General_table" style="margin: 0px auto;">
	<tr>
		<th>Employee ID</th>
		<td><input name="employeeId" id="employeeId" type="text"
			class="NFText" size="25" value="${userDto.employee.employeeId}" /></td>
		<td>
		   <s:select list="{'ACTIVE', 'INACTIVE'}" value="userDto.status" name="status"></s:select>	
		</td>
	</tr>
	<tr>
		<th>Employee Name</th>
		<td colspan="3"><input name="employeeName" id="employeeName"
			type="text" class="NFText" size="25" value="${userDto.employee.employeeName}" /></td>
	</tr>
	<tr>
		<th>Login Name</th>
		<td colspan="3"><input name="loginName" id="loginName"
			type="text" class="NFText" size="25" value="${userDto.loginName}"  /></td>
	</tr>
	<tr>
		<th>Last Activity Date</th>
		<td colspan="3"><input name="loginDate" id="loginDate"
			type="text" class="NFText" size="25"
			value="${lastLoginDate}" readonly="readonly" /> <input type="button"
			name="Submit2" value="View Login History"
			onclick="return GB_show( 'Login History' , 'user/user_managerment/userHistoryList?userId=${userDto.userId}' , 360 , 400 ) ;" />
		</td>
	</tr>
	<tr>
		<th>Effective Date</th>
		<td><input name="effFrom" id="effFrom" type="text" class="NFText"
			size="25" value="${userDto.effFrom}" /></td>
		<th>Expiration Date</th>
		<td><input name="effTo" id="effTo" type="text" class="NFText"
			size="25" value="${userDto.effTo}" /></td>
	</tr>
	<tr>
		<th>Hire Date</th>
		<td colspan="3"><input name="hireDate" id="hireDate" type="text"
			class="NFText" size="25" value="${userDto.employee.hireDate}" /></td>
	</tr>
	<tr>
		<th>SSN/ID</th>
		<td colspan="3"><input name="sid" id="sid" type="text"
			class="NFText" size="25" value="${userDto.employee.sid}" /></td>
	</tr>
	<tr>
		<th>Location</th>
		<td colspan="3"><input name="location" id="location" type="text"
			class="NFText" size="80" value="${userDto.employee.location}" /></td>
	</tr>
	<tr>
		<th>Department</th>
		<td colspan="3"><input name="department" id="department"
			type="text" class="NFText" size="80" value="${userDto.employee.department}" /></td>
	</tr>
	<tr>
		<th>Tel No</th>
		<th colspan="3">
		<div align="left"><input name="phone" id="phone" type="text"
			class="NFText" size="10" value="${userDto.employee.phone}" /> Ext <input
			name="phoneExt" id="phoneExt" type="text" class="NFText" size="3"
			value="${userDto.employee.phoneExt}" /></div>
		</th>
	</tr>
	<tr>
		<th>E-mail Address</th>
		<td colspan="3"><input name="email" id="email" type="text"
			class="NFText" size="80" value="${userDto.employee.email}" /></td>
	</tr>
	<tr>
		<th>Address</th>
		<td colspan="3"><input name="address" id="address" type="text"
			class="NFText" size="80" value="${userDto.employee.address}" /></td>
	</tr>
	<input type="hidden" id="userId" name="userId" value="${userDto.userId}" ></input>
</table>
	</form>
</body>
</html>