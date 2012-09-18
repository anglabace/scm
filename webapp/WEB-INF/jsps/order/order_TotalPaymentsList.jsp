<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.genscript.gsscm.accounting.entity.ArInvoicePayment,java.util.List;"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<s:include value="order_config.jsp"></s:include>
<script src="${global_js_url}jquery/jquery.js" type="text/javascript"
	language="javascript"></script>
<script>
	$(document).ready(function() {

	});
</script>

</head>
<body class="content" style="background-image: none;">
	<table width="750" border="0" cellpadding="0" cellspacing="0"
		class="list_table">
		<tr>
			<th width="64">ID</th>
			<th width="104">Customer No</th>
			<th width="104">Transaction Date</th>
			<th>Transaction Type</th>
			<th width="104">Amount</th>
			<th>Balance</th>
		</tr>
	</table>
	<div class="frame_box3" style="height:700px">
		<table width="750" border="0" cellpadding="0" cellspacing="0"
			class="list_table">

			<%
				List artlist = (List<ArInvoicePayment>) request
						.getAttribute("Paymentlist");
				if (artlist != null && !"".equals(artlist)) {
					for (int i = 0; i < artlist.size(); i++) {
						Object ai[] = (Object[]) artlist.get(i);
			%>

			<tr>
				<td  width="64"><%=i%></td>
				<td width="104"><%=ai[1]%></td>
				<td width="104"><%=ai[2]%></td>
				<td><%=ai[3]%></td>
				<td width="104"><%=ai[4]%></td>
				<td><%=ai[5]%></td>
			</tr>
			<%
				}
				}
			%>
			<tr>
				<td>total Payment:</td>
				<td></td>
				<td></td>
				<td></td>
				<td>${totalpayments}</td>
				<td></td>
			</tr>
		</table>
	</div> 
</body>
</html>