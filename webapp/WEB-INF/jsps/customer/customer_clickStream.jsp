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
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
</head>
<body>
<table width="740" border="0" cellpadding="0" cellspacing="0"
	class="list_table" style="TABLE-LAYOUT: fixed; word-break: break-all" >
	<c:set var="rowcount" value="1"></c:set>
	<s:iterator value="pageAnalysisDTO.result">
		<c:if test="${rowcount mod 2 == 0}">
			<c:set var="tdclass" value=" class='list_td2'"></c:set>
		</c:if>
		<c:if test="${rowcount mod 2 == 1}">
			<c:set var="tdclass" value=""></c:set>
		</c:if>
		<tr align="center">
			<td width="60" ${tdclass}>&nbsp;<s:date name="visitTime" format="yyyy-MM-dd"></s:date> </td>
			<td width="70" ${tdclass} style= "white-space:nowrap;overflow:hidden;text-overflow: ellipsis; ">&nbsp;${staySeconds}</td>
			<td width="100" ${tdclass} style= "white-space:nowrap;overflow:hidden;text-overflow: ellipsis; ">&nbsp;${visitPage}</td>
			<td width="100" ${tdclass} style= "white-space:nowrap;overflow:hidden;text-overflow: ellipsis; ">&nbsp;${visitPage}</td>
			<td width="100" ${tdclass} style= "white-space:nowrap;overflow:hidden;text-overflow: ellipsis; ">&nbsp;${refer}</td>
			<td width="80" ${tdclass} style= "white-space:nowrap;overflow:hidden;text-overflow: ellipsis; ">&nbsp;${searchTerm}</td>
			<td width="60" ${tdclass} style= "white-space:nowrap;overflow:hidden;text-overflow: ellipsis; ">&nbsp;${visit.country}</td>
			<td width="50" ${tdclass} style= "white-space:nowrap;overflow:hidden;text-overflow: ellipsis; ">&nbsp;${visit.zip}</td>
			<td width="50" ${tdclass} style= "white-space:nowrap;overflow:hidden;text-overflow: ellipsis; ">&nbsp;${visit.isp}</td>
			<td width="50" ${tdclass} style= "white-space:nowrap;overflow:hidden;text-overflow: ellipsis; ">&nbsp;${visit.os}</td>
			<td width="50" ${tdclass} style= "white-space:nowrap;overflow:hidden;text-overflow: ellipsis; ">&nbsp;${visit.browser}</td>
			<td width="70" ${tdclass} style= "white-space:nowrap;overflow:hidden;text-overflow: ellipsis; ">&nbsp;${visit.institution}</td>
		</tr>
		<c:set var="rowcount" value="${rowcount+1}"></c:set>
	</s:iterator>
</table>
<div style="margin-left: 300px;" id="click_indicator"></div>
<div class="grayr" id="click_pager">
	<jsp:include page="/common/db_pager.jsp">
			<jsp:param value="${ctx}/customer_web_behavior!getClickStream.action" name="moduleURL" />
	</jsp:include>	
</div>
</body>
</html>