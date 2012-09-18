<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<script language="javascript" type="text/javascript"
	src="${global_js_url}util/json_util.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}select.js"></script>
<form action="customer/customer/saveCustAcctAct" method="POST"
	id="customerAcctForm">
	<table id="accountingTable" width="100%" border="0" cellpadding="0"
		cellspacing="0" class="General_table" style="margin-top: -10px;">
		<tr>
			<td nowrap>
				<fieldset style="margin: 5px; height: 170px;">
					<legend>Balances</legend>
					<table border="0" cellspacing="0" cellpadding="0"
						style="margin-top: 10px;">
						<tr>
							<td>
								<div align="right">Current Balance</div>
							</td>
							<td><input id="cust_acct_curblnc" type="text"
								value="${customerDetail.custInfoStat.currentBalance}"
								class="NFText2" size="20" readonly="readonly" /></td>
						</tr>
						<tr>
							<td>
								<div align="right">Deferred Balance</div>
							</td>
							<td><input id="cust_acct_defblnc" type="text"
								value="${customerDetail.custInfoStat.deferedBalance}"
								class="NFText2" size="20" readonly="readonly" /></td>
						</tr>
						<tr>
							<td>
								<div align="right">Interest Charge</div>
							</td>
							<td><input id="cust_acct_intrstchg" type="text"
								value="${customerDetail.custInfoStat.interestCharge}"
								class="NFText2" size="20" readonly="readonly" /></td>
						</tr>
						<tr>
							<td>
								<div align="right">Gross Spent</div>
							</td>
							<td><input id="cust_acct_grspt" type="text"
								value="${customerDetail.custInfoStat.grossSpent}"
								class="NFText2" size="20" readonly="readonly" /></td>
						</tr>
						<tr>
							<td>
								<div align="right">Gross Profit</div>
							</td>
							<td><input id="cust_acct_grsprft" type="text"
								value="${customerDetail.custInfoStat.grossProfit}"
								class="NFText2" size="20" readonly="readonly" /></td>
						</tr>
					</table>
				</fieldset>
				<fieldset style="margin: 5px; height: 165px;">
					<legend>Statements/Invoice Prefferederences </legend>
					<table width="65%" border="0" cellpadding="0" cellspacing="0"
						style="margin: 5px 0px 0px 10px;">
						<tr>
							<td colspan="2">Contact Prefferederence</td>
						</tr>
						<tr>
							<td width="12%">&nbsp;</td>
							<td width="88%"><s:select name="prefContactType"
									list="dropDownList['CONTACT_METHOD']"
									value="customerDetail.prefContactType" listKey="value"
									listValue="value"></s:select></td>
						</tr>
						<tr>
							<td colspan="2">Email Format Prefferederence</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><s:select name="prefEmailFmt"
									list="dropDownList['EMAIL_FORMAT']"
									value="customerDetail.prefEmailFmt" listKey="value"
									listValue="value"></s:select></td>
						</tr>
					</table>
				</fieldset>
			</td>

			<td valign="top" width="390px;">
				<fieldset style="margin: 5px; height: 170px; padding: 0;">
					<legend>Credit Information </legend>
					<table height="120" border="0" cellspacing="0" cellpadding="0"
						style="">
						<tr>
							<td>&nbsp;</td>
							<td><s:checkbox name=""
									value='customerDetail.custInfoStat.badCreditRisk == "Y"'
									fieldValue="Y" onclick="return false;"></s:checkbox> Customer
								is Bad Credit Risk</td>
						</tr>
						<tr>
							<td width="123" align="right">Credit Limit</td>
							<td> 
								<select name="crRatingIds" onchange="doSomething()"
								id="cust_acct_crdtlimit">
									<s:iterator value="arrDropdownList">
										<s:if test="name==\"CREDIT_LIMIT\"">
											<s:iterator value="dropDownDTOs">
												<s:if test="id==customerDetail.crRatingId">
													<option value="${id}" selected="selected">${name}</option>
												</s:if>
												<s:else>
													<option value="${id}">${name}</option>
												</s:else>
											</s:iterator>
										</s:if>
									</s:iterator>
									<s:if test="customerDetail.crRatingId==99">
										<option value="99" selected="selected">other</option>
									</s:if>
									<s:else>
										<option value="99">other</option>
									</s:else>
							</select>
								<div id="CreditLimitDiv" style='display: none;'>
									<input name="creditLimit" type="text" id="creditLimit"
										class="NFText" size="8" value="${customerDetail.creditLimit}">
								</div>
							</td>
						</tr>
						<tr>
							<td align="right">Terms</td>
							<td><input type="hidden" id="termDefault"
								value="${customerDetail.prefPaymentTerm}" /> <select
								name="prefPaymentTerm" id="cust_acct_term_perc"
								style="text-align: left; width: 97px;" onchange="initTerm();" />
								</select> <input id="cust_acct_term_day" type="text" class="NFText2"
								size="1" readonly="readonly" />Days,Net <input
								id="cust_acct_term_net" type="text" class="NFText" size="1"
								readonly="readonly" /></td>
						</tr>
						<tr>
							<td align="right">Average Days to Pay</td>
							<td><input id="cust_acct_aveday_to_pay"
								value="${customerDetail.custInfoStat.avgPayDay}" type="text"
								class="NFText" size="15" readonly="readonly" /></td>
						</tr>
						<tr>
							<td align="right">Payment Performance</td>
							<td><input id="cust_acct_pay_perform"
								value="${customerDetail.custInfoStat.paymentPerf}" type="text"
								class="NFText" size="30" readonly="readonly" /></td>
						</tr>

						<tr>
							<td align="right"><s:checkbox name="ccpayFlag"
									value='customerDetail.ccpayFlag=="Y"' fieldValue="Y"
									id="cust_ccpay_flag"></s:checkbox> Credit Cards</td>
							<td><s:select name="" id="cardList" list="cardList"
									listKey="'id:'+id+',ccType:'+cardType+',ccExprDate:'+exprDate+',ccNo:'+cardNo+',ccCvc:'+cvc+',ccHolder:'+cardHolder"
									listValue="cardType+' '+cardNo" headerKey=""
									headerValue="Add New Credit Card"></s:select> <input
								type="button" value="New" class="style_botton"
								id="credit_card_add_btn" /> <input type="button" value="Edit"
								class="style_botton" id="credit_card_edit_btn"
								style="display: none;" /> <input type="button" value="Remove"
								class="style_botton" id="credit_card_remove_btn"
								style="display: none;" /></td>
						</tr>
					</table>
				</fieldset>

				<fieldset style="margin: 5px; height: 165px;">
					<legend>Order Prefferederences </legend>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td width="47%" align="right">Preffered Pay Method</td>
								<td width="21%"><s:select name="prefPaymentMthd"
										list="dropDownList['PAYMENT_METHOD']"
										value="customerDetail.prefPaymentMthd" listKey="value"
										listValue="value" cssStyle="width: 60px;"></s:select></td>
								<td width="32%"><s:checkbox name="acknlgReqFlag"
										fieldValue="Y" value='customerDetail.acknlgReqFlag == "Y"'
										checked="true"></s:checkbox> Order Confirmation</td>
							</tr>
							<tr>
								<td align="right">Preffered Pay Currency</td>
								<td><s:select cssStyle="width: 60px;"
										name="paymentCurrency"
										list="specDropDownList['CURRENCY'].dropDownDTOs"
										listKey="name" listValue="name"
										value="customerDetail.paymentCurrency" headerKey="USD"
										headerValue="USD"></s:select></td>
								<td><s:checkbox name="discountFlag" fieldValue="Y"
										value='customerDetail.discountFlag == "Y"' checked="true"></s:checkbox>Order
									Discount&nbsp;</td>
							</tr>
							<tr>
								<td align="right">Preffered Ship Method</td>
								<td><select name="prefShipMthd"
									onmouseover="FixWidth(this)" style="width: 60px;"
									id="prefShipMthdD">
										<option value="">Please select...</option>
										<s:iterator
											value="specDropDownList['SHIP_METHOD'].dropDownDTOs">
											<c:if
												test="${ !empty customerDetail.prefShipMthd && id == customerDetail.prefShipMthd }">
												<option value="${id}" selected="selected">${name}</option>
											</c:if>
											<c:if
												test="${ empty customerDetail.prefShipMthd || id != customerDetail.prefShipMthd }">
												<option value="${id}">${name}</option>
											</c:if>
										</s:iterator>
								</select></td>
								<td><s:checkbox name="overShipFlag" fieldValue="Y"
										value='customerDetail.overShipFlag == "Y"' checked="true"></s:checkbox>
									Over Shipment&nbsp;</td>
							</tr>
							<tr>
								<td align="right">Preffered Ship From Location</td>
								<td><s:select name="prefShipFromLoc"
										cssStyle="width: 60px;"
										list="#{'US':'US','CN':'China','Ep':'Europe','JP':'Japan'}"
										value="customerDetail.prefShipFromLoc"></s:select></td>
								<td><s:checkbox name="partialShipFlag" fieldValue="Y"
										value='customerDetail.partialShipFlag == "Y"' checked="true"></s:checkbox>
									Partial Shipment&nbsp;</td>
							</tr>
							<tr>
								<td>Billing Account Code</td>
								<td><input name="billAccountCode" class="NFText" size="8"
									readonly="readonly" type="text"
									value="${customerDetail.billAccountCode }" /></td>
								<td><s:checkbox name="substitutionFlag" fieldValue="Y"
										value='customerDetail.substitutionFlag == "Y"'></s:checkbox>
									Substitution</td>
							</tr>
						</tbody>
					</table>

				</fieldset>
			</td>

			<td valign="top" nowrap>
				<fieldset style="margin: 5px; height: 170px;">
					<legend>Points/Rewards</legend>
					<table width="100%" height="100" border="0" cellspacing="0"
						cellpadding="0" style="margin: 25px auto 5px auto;">
						<tr>
							<th>Available Points</th>
							<td><input name="pointsAvailable"
								value='<c:if test="${customerDetail.pointsAvailable != null}">${customerDetail.pointsAvailable}</c:if><c:if test="${customerDetail.pointsAvailable == null}">0</c:if>'
								type="text" class="NFText" size="8" readonly="readonly" /></td>
							<td><input type="button" id="credit_cards_anlu2"
								class="style_botton2" value="Redeem Point" /></td>
						</tr>
						<tr>
							<td height="20" colspan="3"><s:checkbox name="pointsFlag"
									fieldValue="1" value='"Y" == customerDetail.pointsFlag'></s:checkbox>
								Do Not Allow Customer Points</td>
						</tr>
						<tr>
							<td height="40" colspan="3">
								<div class="botton_box">
									<input type="button" id="coupon_cards_btn" name="Submit14"
										class="style_botton3" value="Print/View Points History" />
								</div>
							</td>
						</tr>
					</table>
				</fieldset>
				<fieldset style="margin: 5px; height: 165px;">
					<legend>Tax Status</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						height="88" style="margin-top: 10px;">
						<tr>
							<td>&nbsp;</td>
							<td><s:checkbox name="taxExemptFlag" id="taxExemptFlag"
									fieldValue="Y" value='customerDetail.taxExemptFlag == "Y"'></s:checkbox>
								Tax Exempt</td>
						</tr>
						<tr>
							<td align="right">Tax ID</td>
							<td><input name="taxId" type="text" id="taxId"
								class="NFText" size="20" value="${customerDetail.taxId}" /></td>
						</tr>
						<tr>
							<td align="right">Alternate Tax ID</td>
							<td><input name="altTaxId" type="text" class="NFText"
								size="20" value="${customerDetail.altTaxId}" /></td>
						</tr>
					</table>
				</fieldset>
			</td>
		</tr>
	</table>
</form>
<!-- poped instruction insert or edit dialog -->
<div id="accountingInstructionDialog" title="Instruction/Note"></div>
<div id="accountingInstructionDialog2" title="Instruction/Note"></div>
<div>
	<input type="hidden" id="showRedeemPointDialogTrigger" />
	<div id="show_Redeem_Point_Dialog"
		title="EzCouponTM Points: ${customerDetail.pointsAvailable}"></div>
	<input type="hidden" id="showRedeemPointHistoryDialogTrigger" />
	<div id="show_Redeem_Point_History_Dialog"
		title="EzCouponTM Points: ${customerDetail.pointsAvailable}"></div>
</div>
<script>
	function doSomething() {
		var str = $("#cust_acct_crdtlimit").val();
		if (str == '99') {
			document.getElementById("creditLimit").value="";
			document.getElementById("CreditLimitDiv").style.display = "block";
		}else{
			document.getElementById("CreditLimitDiv").style.display = "none";
		}
	}
	
	$(document)
	.ready(
			function() {
				var str = $("#cust_acct_crdtlimit").val();
				if (str == '99') { 
					document.getElementById("CreditLimitDiv").style.display = "block";
				}
			});
	var termListJson;
	$.ajax({
		type : "GET",
		url : "basedata/base_data!getTermList.action",
		dataType : "json",
		success : function(msg) {
			termListJson = msg;
			$("#cust_acct_term_perc").empty();
			termSel = $("#cust_acct_term_perc").get(0);
			termSel.selectedIndex = 0;
			for (i = 0; i < termListJson.length; i++) {
				tmpOpt = document.createElement("OPTION");
				tmpOpt.value = termListJson[i].termId;
				tmpOpt.text = termListJson[i].name;

				termSel[termSel.options.length] = tmpOpt;
				if (termListJson[i].termId == $('#termDefault').attr('value')) {
					termSel.selectedIndex = i;
				}
			}
			initTerm();
		}
	});

	$(function() {
		$("#credit_cards_anlu2")
				.click(
						function() {
							var detail = "${customerDetail.custNo}";
							if (detail == null || detail == "") {
								return;
							} else {
								var href = 'customer!showRedeemPoint.action?custNo=${customerDetail.custNo}';
								$('#showRedeemPointDialogTrigger').val(href);
								$('#showRedeemPointDialogTrigger').click();
							}
						});

		$("#coupon_cards_btn")
				.click(
						function() {
							var detail = "${customerDetail.custNo}";
							if (detail == null || detail == "") {
								return;
							} else {
								var href = 'customer!showAllPointHistory.action?custNo=${customerDetail.custNo}';
								$('#showRedeemPointHistoryDialogTrigger').val(
										href);
								$('#showRedeemPointHistoryDialogTrigger')
										.click();
							}
						});

		$("#showRedeemPointDialogTrigger").click(function() {
			$('#show_Redeem_Point_Dialog').dialog('open');
		});

		$("#showRedeemPointHistoryDialogTrigger").click(function() {
			$('#show_Redeem_Point_History_Dialog').dialog('open');
		});

		$('#show_Redeem_Point_Dialog')
				.dialog(
						{
							autoOpen : false,
							height : 600,
							width : 650,
							modal : true,
							bgiframe : true,
							buttons : {},
							open : function() {
								var url = $("#showRedeemPointDialogTrigger")
										.val();
								var htmlStr = '<iframe src="'
										+ url
										+ '"  allowTransparency="true" width="100%" height="100%" frameborder="0" scrolling="No"></iframe>';
								$('#show_Redeem_Point_Dialog').html(htmlStr);
							}
						});

		$('#show_Redeem_Point_History_Dialog')
				.dialog(
						{
							autoOpen : false,
							height : 800,
							width : 800,
							modal : true,
							bgiframe : true,
							buttons : {},
							open : function() {
								var url = $(
										"#showRedeemPointHistoryDialogTrigger")
										.val();
								var htmlStr = '<iframe src="'
										+ url
										+ '"  allowTransparency="true" width="100%" height="98%" frameborder="0" scrolling="No"></iframe>';
								$('#show_Redeem_Point_History_Dialog').html(
										htmlStr);
							}
						});
	});

	function closeDialogAndRefeash() {
		$('#show_Redeem_Point_Dialog').dialog('close');
		window.location = "customer!edit.action?custNo=${custNo}&operation_method=edit";
	}

	function initTerm() {
		var termSel = $('#cust_acct_term_perc').get(0);
		var selIndex = termSel.selectedIndex;
		$('#cust_acct_term_day').attr('value', termListJson[selIndex].dueDays);
		$('#cust_acct_term_net').attr('value', termListJson[selIndex].netDays);
	}
</script>