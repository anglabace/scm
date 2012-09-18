<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
	$(document)
			.ready(
					function() {
						if ($("#topCount").val() == '') {
							$("#topCount").val(5);
						}
						$("#genSalesStaticsChartTrigger")
								.click(
										function() {
											var custNo = $("#custNo").val();
											if (!custNo) {
												alert("Please enter the Customer Number.");
												return;
											}
											var periodType = $("#periodType")
													.val();
											var radioValue = $(
													'input[name="radiobutton"]:checked')
													.val();
											var url = "customer/cust_sales_statics!showSalesStatics.action?custNo="
													+ custNo;
											url += "&periodType=" + periodType;

											if (radioValue == 1) {
												url += "&salesPeriod="
														+ $("#salesPeriod")
																.val();
											} else {
												var fromDate = $("#fromDate")
														.val();
												var toDate = $("#toDate").val();
												if (!fromDate) {
													alert("Please enter the From Date.");
													return;
												}
												if (!toDate) {
													alert("Please enter the To Date.");
													return;
												}
												url += "&fromDate=" + fromDate;
												url += "&toDate=" + toDate;
											}
											$
													.ajax({
														type : "POST",
														dataType : 'json',
														url : url,
														success : function(msg) {
															$("#showContent")
																	.html(
																			msg.showContent);
															img_html = "<img src='../images/temp/" + msg.picName + ".png' />";
															$('#report_img')
																	.html(
																			img_html);
														},
														error : function(msg) {
															alert("System error! Please contact system administrator for help.");
														}
													});
										});

						$("#salesOrderedTrigger")
								.click(
										function() {
											var custNo = $("#custNo").val();
											var type = $("#type").val();
											if (!custNo) {
												alert("Please enter the Customer Number.");
												return;
											}
											var url = "customer/cust_sales_statics!showSalesOrdered.action?custNo="
													+ custNo;
											url += "&type=" + type;
											url += "&top="
													+ $("#topCount").val();
											$
													.ajax({
														type : "POST",
														dataType : 'json',
														url : url,
														success : function(msg) {
															var tableTr = "";
															$(
																	"#salesOrderedTable")
																	.empty();
															if (msg != null
																	&& msg != "") {
																for (i = 0; i < msg.length; i++) {
																	tableTr += "<tr>";

																	if (i > 0
																			&& (i + 1) % 2 == 0) {
																		tableTr += "<td width='120' class='list_td2'>"
																				+ msg[i][0]
																				+ "</td>";
																		tableTr += "<td width='80' class='list_td2'><div align='center'>"
																				+ msg[i][1]
																				+ "</div></td>";
																	} else {
																		tableTr += "<td width='120'>"
																				+ msg[i][0]
																				+ "</td>";
																		tableTr += "<td width='80'><div align='center'>"
																				+ msg[i][1]
																				+ "</div></td>";
																	}

																	tableTr += "</tr>";
																}
															}
															$(
																	"#salesOrderedTable")
																	.append(
																			tableTr);
														},
														error : function(msg) {
															alert("System error! Please contact system administrator for help.");
														}
													});
										});

						$("#salesOrdersByTrigger")
								.click(
										function() {
											var custNo = $("#custNo").val();
											var type = $("#ordersByType").val();
											if (type == 'salesId') {
												$("#ordersByTitle").html(
														"Sales ID");
											} else if (type == 'sourceId') {
												$("#ordersByTitle").html(
														"Source ID");
											} else {
												$("#ordersByTitle").html(
														"Catalog No");
											}
											if (!custNo) {
												alert("Please enter the Customer Number.");
												return;
											}
											var url = "customer/cust_sales_statics!showOrdersBy.action?custNo="
													+ custNo;
											url += "&type=" + type;
											$
													.ajax({
														type : "POST",
														dataType : 'json',
														url : url,
														success : function(msg) {
															var tableTr = "";
															$(
																	"#salesOrdersByTable")
																	.empty();
															if (msg != null
																	&& msg != "") {
																for (i = 0; i < msg.length; i++) {
																	tableTr += "<tr>";

																	if (i > 0
																			&& (i + 1) % 2 == 0) {
																		tableTr += "<td width='120' class='list_td2'>"
																				+ msg[i][0]
																				+ "</td>";
																		tableTr += "<td width='80' class='list_td2'><div align='center'>"
																				+ msg[i][1]
																				+ "</div></td>";
																	} else {
																		tableTr += "<td width='120'>"
																				+ msg[i][0]
																				+ "</td>";
																		tableTr += "<td width='80'><div align='center'>"
																				+ msg[i][1]
																				+ "</div></td>";
																	}

																	tableTr += "</tr>";
																}
															}
															$(
																	"#salesOrdersByTable")
																	.append(
																			tableTr);
														},
														error : function(msg) {
															alert("System error! Please contact system administrator for help.");
														}
													});
										});
						//document.ready  end
					});
</script>
</head>
<body>
<input type="hidden" id="custNo" value="${custNo}" />
<table width="976" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="713">
		<table width="712" border="0" cellpadding="0" cellspacing="0"
			class="General_table table_new">
			<tr>
				<td width="103" class="blue_price"><strong>Sales
				Period</strong></td>
				<th width="99">Period Type</th>
				<td colspan="4"><select name="periodType" id="periodType"
					style="width: 100px">
					<option value="1">Days</option>
					<option value="7">Weeks</option>
					<option value="30">Months</option>
					<option value="90">Quarters</option>
					<option value="365">Years</option>
				</select></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<th>Web Analysis</th>
				<td width="54"><input type="radio" name="radiobutton" value="0" />
				From</td>
				<td width="161"><input name="fromDate" id="fromDate" value=""
					type="text" class="ui-datepicker-birth" style="width: 125px;"
					size="16" /></td>
				<td width="31">
				<div align="right">To</div>
				</td>
				<td width="264"><input name="toDate" id="toDate" value=""
					type="text" class="ui-datepicker-birth" style="width: 125px;"
					size="16" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><input name="radiobutton" type="radio" value="1"
					checked="checked" /> For</td>

				<td><select name="salesPeriod" id="salesPeriod"
					style="width: 120px;">
					<option value="lastWeek">Last Week</option>
					<option value="thisWeek">This Week</option>
					<option value="lastMonth">Last Month</option>
					<option value="thisMonth">This Month</option>
					<option value="lastQuarter">Last Quarter</option>
					<option value="thisQuarter">This Quarter</option>
					<option value="last6Months">Last 6 Months</option>
					<option value="lastYear">Last Year</option>
					<option value="thisYear">This Year</option>
				</select></td>
				<td colspan="2">&nbsp;</td>
			</tr>

			<tr>
				<td colspan="6">
				<div class="botton_box"><input
					name="genSalesStaticsChartTrigger" id="genSalesStaticsChartTrigger"
					type="button" class="style_botton2" value="Generate Chart" /></div>
				</td>
			</tr>

			<tr>
				<th colspan="6">
				<div align="center" id="showContent"></div>
				</th>
			</tr>

		</table>
		<div id="report_img" align="center"><img
			src="${global_image_url}statics_chart_tpl.png" width="545"
			height="204" /></div>
		<td width="253" class="order_search" valign="top"
			style="padding-left: 17px;">
		<form>
		<table width="232" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="232" style="padding: 0px;">
				<div style="padding-bottom: 6px;"><input type="button"
					id="salesOrderedTrigger" name="salesOrderedTrigger" value="View"
					style="width: 45px;" /> Top <input id="topCount" name="topCount"
					type="text" class="NFText" size="3" style="width: 10px;" /> Most
				Ordered <select name="type" id="type" size="1" style="width: 65px">
					<option value="PRODUCT">Products</option>
					<option value="SERVICE">Services</option>
				</select></div>
				<table width="211" border="0" cellpadding="0" cellspacing="0"
					class="list_table">
					<tr>
						<th width="120">
						<div align="center">Name</div>
						</th>

						<th width="80">
						<div align="center">Quantity</div>
						</th>
					</tr>
				</table>
				<div style="height: 70px; width: 228px; overflow: scroll;"
					class="list_box">
				<table id="salesOrderedTable" width="210" border="0" align="left"
					cellpadding="0" cellspacing="0" class="list_table2">

				</table>
				</div>
				</td>
			</tr>
		</table>
		<table width="207" border="0" cellpadding="0" cellspacing="0"
			class="General_table">
			<tr>

				<td><input id="salesOrdersByTrigger" type="button"
					name="salesOrdersByTrigger" value="View" style="width: 45px;" />&nbsp;&nbsp;#
				of Orders by <select id="ordersByType" name="ordersByType"
					style="width: 90px">
					<option value="catalogNo">Catalog No</option>
					<option value="salesId">Sales ID</option>
					<option value="sourceId">Source ID</option>
				</select></td>
			</tr>
			<tr>

				<td style="padding: 0px;">
				<table width="211" border="0" cellpadding="0" cellspacing="0"
					class="list_table">
					<tr>
						<th width="120">
						<div align="center" id="ordersByTitle">Catalog No</div>
						</th>
						<th>
						<div align="center"># of Orders</div>
						</th>
					</tr>
				</table>

				<div style="height: 70px; width: 228px; overflow: scroll;"
					class="list_box">
				<table id="salesOrdersByTable" width="210" border="0" align="left"
					cellpadding="0" cellspacing="0" class="list_table2">

				</table>
				</div>
				</td>

			</tr>
		</table>
		</form>
		</td>
	</tr>
</table>
</body>
</html>
