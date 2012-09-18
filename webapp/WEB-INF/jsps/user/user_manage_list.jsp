<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>User list</title>
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
</head>

<body class="content">
<script type="text/javascript">
		<c:if test="${request.sign == 1}">
			alert(" You search's user not exist !");
		</c:if>
</script>
	<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="list_table">
			<tr>
				<th style="width: 70px">Employee ID</th>
				<th style="width: 90px">Employee Name</th>
				<th style="width: 93px">Login Name</th>
				<th style="width: 50px">Status</th>
				<th style="width: 90px">Job Function</th>
				<th style="width: 90px">Department</th>
				<th style="width: 178px">E-mail</th>
				<th style="width: 81px">Phone</th>
				<th style="width: 70px">Effective Date</th>
				<th style="width: 70px">Expiration Date</th>
				<th></th>
			</tr>
		<tr>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="list_table">
			<c:set var="rowcount" value="1"></c:set>
			<s:iterator value="pageUserDTO.result">
              <c:if test="${rowcount mod 2 == 0}">
                <c:set var="tdclass" value=" class='list_td2'"></c:set>
              </c:if>	
             <c:if test="${rowcount mod 2 == 1}">
                <c:set var="tdclass" value=""></c:set>
              </c:if>
				<tr align="center">
					<td style="width: 70px" align="center" ${tdclass}>&nbsp;<a href="user!getUserPSInf.action?employeeId=${employee.employeeId}"
						target="mainFrame" >${employee.employeeId}</a></td>
					<td style="width: 90px" ${tdclass}>&nbsp;${firstName}&nbsp;${lastName}</td>
					<td style="width: 93px" ${tdclass}>&nbsp;${loginName}</td>
					<td style="width: 50px" ${tdclass}>&nbsp;${status}</td>
					<td style="width: 90px" ${tdclass}>&nbsp;${employee.jobFunction}</td>
					<td style="width: 90px" ${tdclass}>&nbsp;${employee.department}</td>
					<td style="width: 178px" ${tdclass}>&nbsp;${employee.email}</td>
					<td style="width: 81px" ${tdclass}>&nbsp;${employee.phone}</td>
					<td style="width: 70px" ${tdclass}>&nbsp;${effFrom}</td>
					<td style="width: 70px" ${tdclass}>&nbsp;${effTo}</td>
					<td ${tdclass}>&nbsp;</td>
				</tr><c:set var="rowcount" value="${rowcount+1}"></c:set>
			</s:iterator>
		</table>
		</tr>
		<div class="grayr">
			<jsp:include page="/common/db_pager.jsp">
				<jsp:param value="${ctx}/user!manageList.action" name="moduleURL" />
			</jsp:include>
		</div>
</table>
</body>
</html>