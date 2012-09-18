<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}scm/config.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<link type="text/css"
	href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script language="JavaScript" type="text/javascript">
	function cc(e)

	{

		var a = document.getElementsByName("pl2");

		for ( var i = 0; i < a.length; i++)
			a[i].checked = e.checked;

	}

	function dd(e)

	{

		var a = document.getElementsByName("as");

		for ( var i = 0; i < a.length; i++)
			a[i].checked = e.checked;

	}
</script>




<script type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>

<script type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>

<script type="text/javascript"
	src="${global_js_url}greybox/gb_scripts.js"></script>

<link href="${global_js_url}greybox/gb_styles.css" rel="stylesheet"
	type="text/css" media="all" />



<style type="text/css">
<!--
.hidlayer {
	font-size: 12px;
	height: 370px;
	width: 666px;
	position: absolute;
	z-index: 9999;
	left: 20%;
	top: 20%;
	display: none;
}
-->
</style>



<script>
	function openwin1()

	{

		document.getElementById("hidlayer").style.display = "block";

	}

	function openwin2()

	{

		document.getElementById("hidlayer1").style.display = "block";

	}

	function closelay()

	{

		document.getElementById("hidlayer").style.display = "none";

	}

	function closelay2()

	{

		document.getElementById("hidlayer1").style.display = "none";

	}

	function closelay3()

	{

		document.getElementById("hidlayer3").style.display = "none";

	}
</script>
</head>

<body class="content" style="background-image: none;">
	<div id="frame12" style="display: none;" class="hidlayer1">
		<iframe id="hidkuan" name="hidkuan" src="kuang.html" width="668"
			height="425" frameborder="0" allowtransparency="true"></iframe>
	</div>
	<div class="hidlayer" id="hidlayer">
		<iframe id="hidfra" src="paystatus.html" width="666" height="870"
			frameborder="0"></iframe>
	</div>
	<div class="scm">
		<div class="title_content">
			<div class="title">Transaction History</div>
		</div>
		<div class="input_box">
			<table width="998" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td><table border="0" cellpadding="0" cellspacing="0"
							class="General_table"
							style="margin-top: 0px; margin-left: 0px; margin-right: 0px;">
							<tr>
								<th>Total Credit Card Payment</th>
								<td><input name="totalPayment" type="text" class="NFText"
									value="${totalRefund }" size="20" />
								</td>
								<th width="180">Total Credit Card Refund</th>
								<td><input name="totalRefund" type="text" class="NFText"
									value="${totalPayment}" size="20" />
								</td>
							</tr>

						</table>
					</td>
				</tr>
				<tr>
					<td><div style="margin-right: 17px;">

							<table width="981" border="0" cellspacing="0" cellpadding="0"
								class="list_table">
								<tr>
									<th width="113" height="22">Transaction Date</th>

									<th width="70">Order No</th>

									<th width="124">Transaction Type</th>

									<th width="80">Amount</th>

									<th width="86">Status</th>

									<th width="255">Transaction Message</th>

									<th width="76">Card Holder</th>

									<th width="106">Credit Card No</th>

									<th width="70">Charger</th>

								</tr>

							</table>

						</div>
					</td>

				</tr>

				<tr>

					<td><div class="list_box" style="height: 300px;">

							<table width="981" border="0" cellspacing="0" cellpadding="0"
								class="list_table2">
								<s:iterator value="arInvoicePaymentpage.result">
									<tr>
										<td width="113">${transactionDate }</td>

										<td width="72">${orderNo }</td>

										<td width="124">${transactionType }</td>

										<td width="82">${amount }</td>

										<td width="84">${status }</td>

										<td width="250">${description }</td>

										<td width="77">${ccCardHolder }</td>

										<td width="106" align="right">${accountNo }</td>

										<td width="72" align="right">${balance}</td>
									</tr>
								</s:iterator>
							</table>

						</div>
					</td>

				</tr>

				<tr>
					<td><div class="grayr"></div>
					</td>
				</tr>
			</table>

		</div>

		<div id="dhtmlgoodies_tabView1"></div>

	</div>

	<div class="button_box"> 
		<input type="button" name="Submit123" value="Back"
			class="search_input"
			onclick="window.location.href='${ctx}/customer/cust_credit_card!cardChanger.action'" /> 
	</div>

</body>

</html>