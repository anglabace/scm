<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base id="myBaseId" href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<div>
		<table border="0" cellpadding="0" cellspacing="0"
			class="Customer_table">
			<tr>
				<td width="740"><div class="invoice_title">My Points
						History</div>
				</td>
			</tr>
		</table>
	</div>
	<div>
		<table width="720" border="0" cellspacing="0" cellpadding="0"
			class="list_table">
			<tr>
				<th width="189">Order Id</th>
				<th width="180">Points change</th>
				<th width="180">Customer No</th>
				<th width="100">Coupon Id</th>  
				<th width="100">Comments</th>
				<th>Date</th>
			</tr>
			<c:if test="${!empty amazonhistoryPage.result}">
				<c:forEach items="${amazonhistoryPage.result}"
					var="amazonHistoryDTO">
					<tr>
						<td width="189" align="center">${amazonHistoryDTO.orderNo}</td>
						<td width="180" align="center">${amazonHistoryDTO.points}</td>
						<td width="180" align="center">${amazonHistoryDTO.custNo}</td>
						<td width="180" align="center">${amazonHistoryDTO.couponId}</td> 
						<td width="180" align="center">${amazonHistoryDTO.comments}</td>
						<td align="center">${amazonHistoryDTO.issueDate}</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
	</div>
	<div class="grayr">
		<jsp:include page="/common/db_pager.jsp">
			<jsp:param
				value="${ctx}/customer/customer!showAmazonPointHistory.action"
				name="moduleURL" />
		</jsp:include>
	</div>

</body>
</html>
