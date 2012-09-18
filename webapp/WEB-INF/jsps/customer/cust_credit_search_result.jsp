<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link type="text/css"
	href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script src="${global_js_url}jquery/jquery.js" type="text/javascript"
	language="javascript"></script>
<script src="${global_js_url}jquery/jquery.dialog.all.js"
	type="text/javascript"></script>
<script src="${global_js_url}initlefttop.js" type="text/javascript"></script>
<script language="JavaScript" type="text/javascript">
	function cc(e, name) {
		var a = document.getElementsByName(name);
		for ( var i = 0; i < a.length; i++) {
			if (a[i].disabled == false)
				a[i].checked = e.checked;
		}
	}

	function del_order(name) {
		var del_order_nos = get_checked_str(name);
		var orderNos = del_order_nos.split(",");
		var noData = "orderNos=" + orderNos.join("&orderNos=");
		var statusReason = $("#statusReason").val();
		var comment = $("#comment").val();
		if (comment == "") {
			alert("Please enter the Note.");
			$("#comment").focus();
			return;
		}
		$
				.ajax({
					type : "POST",
					url : "order/order!delete.action",
					data : noData + '&statusReason=' + statusReason
							+ '&comment=' + comment,
					success : function(msg) {
						if (msg == 'success') {
							$('#delOrderDialog').dialog('close');
							alert('Delete order successfully');
						} else if (msg == 'error') {
							alert("You don't have permission to cancel order, please contact your supervisor.");
						} else {
							alert('Unknown error');
						}
						$(":checkbox").attr("checked", false);
						window.location.reload();
					},
					error : function(msg) {
						alert("Failed to cancel the order.");
					}
				});
		return false;
	}

	function get_checked_str(name) {
		var a = document.getElementsByName(name);
		var str = '';
		for ( var i = 0; i < a.length; i++) {
			if (a[i].checked) {
				str += a[i].value + ',';
			}
		}
		return str.substring(0, str.length - 1);
	}

	$(document).ready(function() {
		$("#resultTable tr:odd").find("td").addClass("list_td2");
		
		$('#delOrderDialog').dialog({
			autoOpen : false,
			height : 250,
			width : 620,
			modal : true,
			bgiframe : true,
			buttons : {}
		});


	});
</script>
</head>
<body>
	<div style="margin-right: 17px;">
		<table width="1080px" border="0" cellpadding="0" cellspacing="0"
			class="list_table">
			<tr>
				<th width="46"></th>
				<th width="60">Order No</th>
				<th width="60">Status</th>
				<th width="80">Customer No</th>
				<th width="120">Customer</th>
				<th width="">Address</th>
				<th width="85">Order Date</th>
				<th width="85">Due Date</th>
				<th width="60">Grand Total</th>
				<th width="80">Sales Manager</th>
				<th width="60">Quotation No</th>
				<th width="80">Created By</th>
			</tr>
		</table>
	</div>
	<div class="list_box"
		style="height: 340px; margin-right: 17px; width: 1095px;">
		<table id="resultTable" width="1080px;" border="0" align="center"
			cellpadding="0" cellspacing="0" class="list_table">
			<s:iterator value="orderList">
				<tr>
					<td width="46">
						<div align="left">
							<input type="radio" value="${orderNo}" name="orderNo" />
						</div></td>
					<td width="60">
						<div align="center">&nbsp; ${orderNo}</div></td>
					<td width="60">&nbsp;${status}</td>
					<td width="80">&nbsp;${custNo }</td>
					<td width="120">&nbsp; ${firstName} <c:if
							test="${! empty lastName}">
						</c:if> ${lastName}<c:if test="${! empty midName}">, </c:if> ${midName}</td>
					<td width="">&nbsp; <s:if
							test="%{addressMap[orderNo].length()>35}">
							<s:property value="addressMap[orderNo].substring(0,10)+'...'" />
						</s:if> <s:else>
							<s:property value="addressMap[orderNo]" />
						</s:else></td>
					<td width="85">&nbsp;<s:date name="orderDate"
							format="yyyy-MM-dd" />
					</td>
					<td width="85">&nbsp;<s:date name="exprDate"
							format="yyyy-MM-dd" />
					</td>
					<td width="60" align="right">&nbsp;${symbol} <c:if
							test="${symbol != '¥'}">
							<fmt:formatNumber value="${amount}" pattern="#,###,###,##0.00" />
						</c:if> <c:if test="${symbol == '¥'}">
							<fmt:formatNumber value="${amount}" pattern="#,###,###,###" />
						</c:if></td>
					<td width="80">&nbsp;${salesContact}</td>
					<c:if test="${quoteNo==0}">
						<td width="60">&nbsp;</td>
					</c:if>
					<c:if test="${quoteNo!=0}">
						<td width="60">&nbsp; ${quoteNo}</td>
					</c:if>
					<td width="80">${createdBy}</td>
				</tr>
			</s:iterator>
		</table>
	</div>
	<div class="grayr">
		<jsp:include page="/common/db_pager.jsp">
			<jsp:param value="${ctx}/customer/cust_credit_card.action"
				name="moduleURL" />
		</jsp:include>
	</div> 
</body>
</html>
