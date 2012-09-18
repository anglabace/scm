<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>User list</title>
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}gb_styles.css" rel="stylesheet" type="text/css" media="all" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<script type="text/javascript">
    var GB_ROOT_DIR = "./greybox/";
</script>
<script type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/gb_scripts.js"></script>
</head>
<body class="content">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td style="padding-right: 17px;"></td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="list_table">
			<tr>
				<th style="width: 70px">Employee ID</th>
				<th style="width: 90px">Employee Name</th>
				<th style="width: 50px">Status</th>
				<th>&nbsp;</th>
			</tr>
		</table>
		<div class="list_box" style="height: 340px;">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="list_table">
			<c:set var="rowcount" value="1"></c:set>
			<s:iterator value="pageUserDTOList.getResult()">
			<c:if test="${rowcount mod 2 == 0}">
                <c:set var="tdclass" value=" class='list_td2'"></c:set>
              </c:if>	
             <c:if test="${rowcount mod 2 == 1}">
                <c:set var="tdclass" value=""></c:set>
              </c:if>
				<tr>
					<td style="width: 70px" align="center" ${tdclass}>&nbsp;<a
						href="user!getUserPSInf.action?employeeId=${employee.employeeId}"
						target="mainFrame">${employee.employeeId}</a></td>
					<td style="width: 90px" ${tdclass}>&nbsp;${employee.employeeName}</td>
					<td style="width: 50px" ${tdclass}>&nbsp;${userListItem.status}</td>
					<td ${tdclass}>&nbsp;</td>
				</tr>
				</tr><c:set var="rowcount" value="${rowcount+1}"></c:set>
			</s:iterator>
		</table>
		</div>
		</td>
	</tr>
</table>
<div class="grayr">
	<jsp:include page="/common/db_pager.jsp">
	  <jsp:param value="${ctx}/user!getSearchUserList.action" name="moduleURL"/>
	</jsp:include>	
</div>
</body>
</html>