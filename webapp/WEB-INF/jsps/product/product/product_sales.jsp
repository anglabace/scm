<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css"
	rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}scm/product/product_sales.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$().ready(function() {
		$('.ui-datepicker-birth').each(function() {
			$(this).datepicker({
				dateFormat : 'yy-mm-dd',
				changeMonth : true,
				changeYear : true
			});
		});
		$('#fromDate').datepicker();
		$('#toDate').datepicker();
		var hobbys = "${contact.personal.hobby}";
		var hobby = hobbys.split(":");
		if (hobby.length == 4) {
			for ( var i = 0; i < hobby.length; i++) {
				if (hobby[i] == 1) {
					document.getElementById("ids" + i).checked = true;
				} else {
					document.getElementById("ids" + i).value = 0;
				}
			}
		}
	});

	function searchProductSales() {
		var catalogNo = $("#catalogNo").val();
		if (!catalogNo) {
			alert("Please enter the Category No.");
			return;
		}
		var periodType = $("#periodType").val();
		var salesPeriodBasedOn = $("#salesPeriodBasedOn").val();
		var radioValue = $('input[name="radiobutton"]:checked').val();
		var url = "product/product!showProductSales.action?catalogNo="
				+ catalogNo + "&isFalse=yes";
		url += "&periodType=" + periodType;
		url += "&salesPeriodBasedOn=" + salesPeriodBasedOn;

		if (radioValue == 1) {
			url += "&salesPeriod=" + $("#salesPeriod").val();
		} else {
			var fromDate = $("#fromDate").val();
			var toDate = $("#toDate").val();
			if (!fromDate) {
				alert("Please enter the correct date range.");
				return;
			}
			if (!toDate) {
				alert("Please enter the correct date range.");
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
						$("#grossUitsSales").val("$" + msg.grossUitsSales);
						$("#unitsReturned").val(msg.unitsReturned);
						$("#totalSales").val("$" + msg.totalSales);
						$("#totalProfit").val("$" + msg.totalProfit);
						$("#margin").val(msg.margin);
						alert(msg.picName);
						img_html = "<img src='../images/temp/" + msg.picName + ".png' />";
						$('#report_img').html(img_html);
					},
					error : function(msg) {
						alert("Failed to generate the chart and statistics report.Please contact system administrator for help.");
					}
				});

	}

	function searchSalesRanking() {
		var catalogNo = $("#catalogNo").val();
		if (!catalogNo) {
			alert("Please enter the Catalog No.");
			return;
		}
		var url = "product/product!showSalesRanking.action?catalogNo="
				+ catalogNo;
		url += "&lastDays=" + $("#lastDays").val();
		url += "&top=" + $("#topCount").val();
		$.ajax({
			type : "POST",
			dataType : 'json',
			url : url,
			success : function(msg) {
				var tableTr = "";
				$("#salersTable").empty();
				if (msg != null && msg != "") {
					if (msg.length < 1) {
						alert("We didn't get any datas.");
					}
					for (i = 0; i < msg.length; i++) {
						tableTr += "<tr>";
						for (j = 0; j < msg[i].length; j++) {
							if (i > 0 && (i + 1) % 2 == 0) {
								tableTr += "<td width='200' class='list_td2'>"
										+ msg[i][j] + "</td>";
							} else {
								tableTr += "<td width='200'>" + msg[i][j]
										+ "</td>";
							}
						}
						tableTr += "</tr>";
					}
				} else {
					alert("We didn't get any datas.");
				}
				$("#salersTable").append(tableTr);
			},
			error : function(msg) {
				alert("Failed to generate the report.");
			}
		});

	}
</script>
</head>

<body class="content">
	<input type="hidden" id="id" value="${id}" />
	<input type="hidden" id="catalogNo" value="${catalogNo}" />
	<div class="scm" style="background: #ffffff;">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="General_table" style="margin-top: 0px;">
			<tr>
				<td>
					<fieldset>
						<table border="0" align="center" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<th rowspan="3" valign="top">Sales Period</th>
								<td><input type="radio" name="radiobutton" value="0" />
								</td>
								<th>From</th>
								<td><input name="fromDate" id="fromDate" value=""
									type="text" class="ui-datepicker-birth" style="width: 125px;"
									size="18" /></td>
								<td colspan="2"><span class="blue_price">Sales
										Statistics for Selected Period </span>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<th>To</th>
								<td><input name="toDate" id="toDate" value="" type="text"
									class="ui-datepicker-birth" style="width: 125px;" size="18" />
								</td>
								<th>Unit Sold</th>
								<td><input name="grossUitsSales" id="grossUitsSales"
									type="text" class="NFText2"
									value="$${productSaleDTO.grossUitsSales }" size="15" />
								</td>
							</tr>
							<tr>
								<td><input name="radiobutton" type="radio" value="1"
									checked="checked" />
									<div align="center" class="blue_price"></div>
								</td>
								<td>&nbsp;</td>
								<td><select name="salesPeriod" id="salesPeriod">
										<option value="lastWeek">Last Week</option>
										<option value="thisWeek">This Week</option>
										<option value="lastMonth">Last Month</option>
										<option value="thisMonth">This Month</option>
										<option value="lastQuarter">Last Quarter</option>
										<option value="thisQuarter">This Quarter</option>
										<option value="last6Months">Last 6 Months</option>
										<option value="lastYear">Last Year</option>
										<option value="thisYear">This Year</option>
								</select>
								</td>
								<th>Units Returned</th>
								<td><input name="unitsReturned" id="unitsReturned"
									type="text" class="NFText2"
									value="${productSaleDTO.unitsReturned }" size="15" />
								</td>
							</tr>
							<tr>
								<th colspan="4">Period Type <select name="periodType"
									id="periodType">
										<option value="1">Days</option>
										<option value="7">Weeks</option>
										<option value="30">Months</option>
										<option value="90">Quarters</option>
										<option value="365">Years</option>
								</select>
								</th>
								<th>Total Sales</th>
								<td><input name="totalSales" id="totalSales" type="text"
									class="NFText2" value="$${productSaleDTO.totalSales }"
									size="15" />
								</td>
							</tr>
							<tr>
								<th colspan="4">&nbsp;</th>
								<th>Total Profit</th>
								<td><input name="totalProfit" id="totalProfit" type="text"
									class="NFText2" value="$${productSaleDTO.totalProfit}"
									size="15" />
								</td>
							</tr>
							<tr>
								<th colspan="4">Sales Period Based On <select
									name="salesPeriodBasedOn" id="salesPeriodBasedOn">
										<option value="netSales">Net Sales</option>
										<option value="grossSales">Gross Sales</option>
										<option value="lossOnReturn">Loss on Return</option>
										<option value="netUnitsSold">Net Units Sold</option>
										<option value="grossUnitsSold">Gross Units Sold</option>
										<option value="unitsReturned">Units Returned</option>
								</select>
								</th>
								<th>Profit Margin</th>
								<td><input name="margin" id="margin" type="text"
									class="NFText2" value="${productSaleDTO.margin}" size="15" />
								</td>
							</tr>
							<tr>
								<td colspan="4"></td>
								<th>&nbsp;</th>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td colspan="6"><div align="center">
										<input name="Submit42" type="button" class="style_botton3"
											value="Generate Chart / Statistics"
											onclick="searchProductSales()" />
									</div>
								</td>
							</tr>
						</table>
					</fieldset></td>
				<td width="42%" valign="top">
					<fieldset>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<td colspan="2">The Top <input id="topCount"
									name="topCount" type="text" class="NFText" value="5" size="8" />
									Sales Person(s) Selling this Product</td>
							</tr>
							<tr>
								<td width="73%">for <select id="lastDays" name="lastDays">
										<option value="lastWeek">Last Week</option>
										<option value="lastMonth">Last Month</option>
										<option value="last3Months">Last 3 Months</option>
										<option value="last6Months">Last 6 Months</option>
										<option value="lastYear">Last Year</option>
								</select></td>
								<td width="27%"><input id="salersSearch" type="button"
									class="style_botton" value="Search"
									onclick="searchSalesRanking()" /></td>
							</tr>
							<tr>
								<td colspan="2">
									<table width="400" border="0" cellpadding="0" cellspacing="0"
										class="list_table">
										<tr>
											<th width="200"><div align="center">Sales</div>
											</th>
											<th width="200"><div align="center">Quantities
													Sold</div>
											</th>
										</tr>
									</table>
									<div style="height: 102px; width: 417px; overflow: scroll;"
										class="list_box">
										<table id="salersTable" width="400" border="0" align="left"
											cellpadding="0" cellspacing="0" class="list_table2">

										</table>
									</div></td>
							</tr>
						</table>
					</fieldset></td>
			</tr>
			<tr>
				<td height="200" colspan="2"><div id="report_img"
						align="center">
						<img src="images/product_service_chart_tpl.png" />
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>