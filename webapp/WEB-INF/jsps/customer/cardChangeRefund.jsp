
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Card charge Refund</title>
<base href="${global_url}" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}openwin.css" rel="stylesheet"
	type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">
	var GB_ROOT_DIR = "./greybox/";
</script>
<style type="text/css">
<!--
.content .scm #dhtmlgoodies_tabView1 .dhtmlgoodies_aTab .General_table fieldset
	{
	margin: 4px;
}
-->
</style>
<script language="javascript" type="text/javascript">
	$(document)
			.ready(
					function() {
						$('#ConfirmTrriger')
								.click(
										function() {
											var orderNO = $("#orderNO").val();
											var year = $("#year").val();
											var month = $("#month").val();
											var ccType = $("#ccType").val();
											if (ccType == "" || ccType == null) {
												alert("Please select the Card Type!");
												document.getElementById(
														"ccType").focus();
												return;
											}
											var ccCardHolder = $(
													"#ccCardHolder").val();
											if (ccCardHolder == ""
													|| ccCardHolder == null) {
												alert("Please enter the Name on the Card!");
												return;
											}
											var ccCvc = $("#ccCvc").val();
											if (ccCvc == "" || ccCvc == null) {
												alert("Please enter the CVC!");
												document
														.getElementById("ccCvc")
														.focus();
												return;
											}
											var amount = $("#amount").val();
											var strP = /^\d+$/;
											if (amount != null && amount != "") {
												if (!strP.test(amount)) {
													alert("Please enter the Digit for Amount!");
													document.getElementById(
															"amount").focus();
													return;
												}
											}

											var accountNo = $("#accountNo")
													.val();
											if (accountNo == ""
													|| accountNo == null) {
												alert("Please enter the accountNo!");
												document.getElementById(
														"accountNo").focus();
												return;
											}
											if (year == "" || month == "") {
												alert("Please select the year and month!");
												return;
											}
											var transactionTypevalue = $(
													"#transactionType").val();
											if (transactionTypevalue == ""
													|| transactionTypevalue == null) {
												alert("Please select the transaction Type!");
												document.getElementById(
														"transactionType")
														.focus();
												return;
											}
											if (orderNO == null
													|| orderNO == "") {
												alert("Please Check the Data!");
												document.getElementById(
														"orderNO").focus();
												return;
											}
											var balanceval = $("#balance")
													.val();
											if (balanceval != ""
													&& balanceval != null
													&& amount != null
													&& amount != "") {
												if (strP.test(amount)) {
													if (amount > balanceval) {
														alert("Please Enter the smaller digital for amount ");
														return;
													}else if(amount==balanceval){
														alert("Please Enter the digit for amount ");
														return;
													}

												}
											}
											$
													.ajax({
														type : "POST",
														url : "customer/cust_credit_card!saveTotransaction.action",
														data : $("#MainForm")
																.serialize(),
														dataType : 'json',
														success : function(msg) {
															alert(msg.message);
															//	alert(msg.over);
															if (msg.message == 'Save success!') {
																window.location.href = "customer/cust_credit_card!transactionListByOrdernNo.action?orderNo="
																		+ orderNO;
																return true;
															} else {
																alert("The order#"
																		+ orderNO
																		+ " is not exist.");
																return false;
															}
														},
														error : function(msg) {
															alert("Failed to view the order. Please contact system administrator for help.");
														}
													});
										});
					});
</script>
</head>

<body class="content">

	<div id="frame12" style="display: none;" class="hidlayer1">

		<iframe id="hidkuan" name="hidkuan" src="kuang.html" width="668"
			height="425" frameborder="0" allowtransparency="true"></iframe>

	</div>

	<div class="scm">

		<div class="title_content">

			<div class="title">Charge or Refund Credit Card</div>

		</div>

		<div class="input_box">

			<div class="content_box">
				<form class="niceform" id="MainForm">
					<input type="hidden" name="currency" value="${currency }" /> <input
						type="hidden" value="Standard" name="arInvoicePayment.paymentType" />
					<input type="hidden" value="Completed"
						name="arInvoicePayment.status" />
					<table border="0" cellpadding="0" cellspacing="0"
						class="General_table">

						<tr>

							<th>Order No</th>

							<td><input name="orderNO" value="${orderNo}" type="text"
								id="orderNO" class="NFText" size="35" readonly="readonly" />
							</td>

							<th>&nbsp;</th>

							<td>&nbsp;</td>

						</tr>

						<tr>

							<th>Billing Address</th>

							<td><textarea name="billingAddress" readonly="readonly"
									class="content_textarea2">${billingAddress}</textarea></td>

							<th>&nbsp;</th>

							<td>&nbsp;</td>

						</tr>
						<tr>

							<th><span class="important">*</span>Card Type</th>

							<td><s:if test="cardtype==null || cardtype ==''">
									<s:select id="ccType" name="arInvoicePayment.ccType"
										list="dropDownList['CREDIT_CARD_TYPE']" listKey="value"
										listValue="value" headerKey="" headerValue="Please select"
										cssStyle="width:100px"></s:select>
								</s:if> <s:else>
									<s:select id="ccType" name="arInvoicePayment.ccType"
										list="dropDownList['CREDIT_CARD_TYPE']" listKey="value"
										listValue="value" headerKey="" headerValue="Please select"
										cssStyle="width:100px" value="cardtype"></s:select>
								</s:else></td>

							<th><span class="important">*</span>Name on the Card</th>
							<td><input name="arInvoicePayment.ccCardHolder"
								id="ccCardHolder" type="text" class="NFText" size="35"
								value="${cardholderName }" />
							</td>

						</tr>

						<tr>

							<th width="160"><span class="important">*</span>Card Number</th>

							<td><input name="arInvoicePayment.accountNo" type="text"
								id="accountNo" class="NFText" size="35"
								value="${customerCardNo }" />
							</td>

							<th width="150">CVC</th>

							<td><input name="arInvoicePayment.ccCvc" type="text"
								id="ccCvc" class="NFText" size="35" value="${cvcs }" />
							</td>
						</tr>
						<tr>
							<th valign="top"><span class="important">*</span>Expiration
								Date</th>

							<td>Year:<select id="year" name="year" style="width: 81px">
									<option value=""
										<s:if test="empimeryear=='' || empimeryear ==null ">selected</s:if>>
									</option>
									<option value="2011"
										<s:if test="empimeryear=='2011' ">selected</s:if>>2011</option>
									<option value="2012"
										<s:if test="empimeryear=='2012' ">selected</s:if>>2012</option>
									<option value="2013"
										<s:if test="empimeryear=='2013' ">selected</s:if>>2013</option>
									<option value="2014"
										<s:if test="empimeryear=='2014' ">selected</s:if>>2014</option>
									<option value="2015"
										<s:if test="empimeryear=='2015' ">selected</s:if>>2015</option>
									<option value="2016"
										<s:if test="empimeryear=='2016' ">selected</s:if>>2016</option>
									<option value="2017"
										<s:if test="empimeryear=='2017' ">selected</s:if>>2017</option>
									<option value="2018"
										<s:if test="empimeryear=='2018' ">selected</s:if>>2018</option>
									<option value="2019"
										<s:if test="empimeryear=='2019' ">selected</s:if>>2019</option>
									<option value="2020"
										<s:if test="empimeryear=='2020' ">selected</s:if>>2020</option>
							</select> Month:<select id="month" name="month" style="width: 81px">
									<option value=""
										<s:if test="empimermonth=='' || empimermonth ==null ">selected</s:if>></option>
									<option value="01"
										<s:if test="empimermonth=='01' ">selected</s:if>>1</option>
									<option value="02"
										<s:if test="empimermonth=='02' ">selected</s:if>>2</option>
									<option value="03"
										<s:if test="empimermonth=='03'  ">selected</s:if>>3</option>
									<option value="04"
										<s:if test="empimermonth=='04' ">selected</s:if>>4</option>
									<option value="05"
										<s:if test="empimermonth=='05' ">selected</s:if>>5</option>
									<option value="06"
										<s:if test="empimermonth=='06'  ">selected</s:if>>6</option>
									<option value="07"
										<s:if test="empimermonth=='07' ">selected</s:if>>7</option>
									<option value="08"
										<s:if test="empimermonth=='08'  ">selected</s:if>>8</option>
									<option value="09"
										<s:if test="empimermonth=='09' ">selected</s:if>>9</option>
									<option value="10"
										<s:if test="empimermonth=='10'  ">selected</s:if>>10</option>
									<option value="11"
										<s:if test="empimermonth=='11'  ">selected</s:if>>11</option>
									<option value="12"
										<s:if test="empimermonth=='12'  ">selected</s:if>>12</option>
							</select></td>
							<th>&nbsp;</th>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<th valign="top">Order Total</th>

							<td><input name="orderTotal" type="text" class="NFText"
								size="35" readonly="yes" value="${orderTotal }" /></td>

							<th>Total Payments</th>

							<td><input name="totalPayments" type="text" class="NFText"
								value="${totalPayments}" size="35" readonly="yes" /></td>

						</tr>

						<tr>

							<th valign="top">Balance</th>

							<td><input name="arInvoicePayment.balance" type="text"
								id="balance" class="NFText" value="${balance }" size="35"
								readonly="yes" />
							</td>

							<th>&nbsp;</th>

							<td>&nbsp;</td>

						</tr>

						<tr>

							<th valign="top">Transaction Type</th>

							<td><s:select id="transactionType"
									name="arInvoicePayment.transactionType"
									list="dropDownList['AR_TRANSACTION_TYPE']" listKey="value"
									listValue="value" headerKey="" headerValue="Please select"
									cssStyle="width:100px"></s:select>
							</td>

							<th>&nbsp;</th>

							<td>&nbsp;</td>

						</tr>

						<tr>

							<th valign="top">Transaction Amount</th>

							<td><input name="arInvoicePayment.amount" type="text"
								class="NFText" " size="35" id="amount" /></td>

							<th>&nbsp;</th>

							<td>&nbsp;</td>

						</tr>

						<tr>

							<th>Comment</th>

							<td><textarea name="arInvoicePayment.description"
									class="content_textarea2"> </textarea>
							</td>

							<td>&nbsp;</td>

							<td>&nbsp;</td>

						</tr>

						<tr>

							<th>&nbsp;</th>

							<td>&nbsp;</td>

							<th>&nbsp;</th>

							<td>&nbsp;</td>

						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="button_box">
			<input type="button" name="Submit123" value="Confirm"
				class="search_input" id="ConfirmTrriger" /> <input type="submit"
				name="Submit124" value="Cancel" class="search_input"
				onclick="window.history.go(-1)" />

		</div>

	</div>

</body>

</html>