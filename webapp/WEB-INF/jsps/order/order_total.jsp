<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<base id="myBaseId" href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />

<link href="${global_css_url}tab-view.css" rel="stylesheet"
	type="text/css" />
<script src="${global_js_url}jquery/jquery.js" type="text/javascript"
	language="javascript"></script>
<link type="text/css"
	href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}show_tag.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}select.js"></script>
<script type="text/javascript" src="${global_js_url}util/util.js"></script>
<script src="${global_js_url}initlefttop.js" type="text/javascript"></script>

<script src="${global_js_url}jquery/jquery.dialog.all.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js"
	type="text/javascript"></script>
<s:include value="order_config.jsp"></s:include>
<script language="javascript" type="text/javascript"
	src="${global_js_url}util/util.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}quoteorder/order_quotation_total.js"></script>
</head>
<body class="content" style="background-image: none;">
	<table width="960" height="209" border="0" cellpadding="0"
		cellspacing="0" class="list_table2">
		<tr>
			<th width="376">Payment Information</th>
			<th>Shipping</th>
			<th>Billing &amp; Totals</th>
		</tr>
		<tr>
			<td valign="top" class="line_Notes"><iframe
					id="paymentListIframe"
					src="order_payment!list.action?sessOrderNo=${sessOrderNo}"
					scrolling="no" width="370" height="226" style="border: 0px;"
					frameborder="no"></iframe> <span
				style="padding-left: 10px; color: #FF0000; font-size: 11px; display: none;"
				id="paymentNoteSpan"> <span id="prePaymentSymbol">${billTotal.symbol}</span>
					<span id="prePaymentNum">${billTotal.prePayment}</span> Prepayment
					is required </span>
			</td>

			<td valign="top" class="line_Notes">
				<table width="320" border="0" cellpadding="0" cellspacing="0"
					class="pay_table">
					<tr>
						<th width="103">Shipping Type</th>
						<td width="217"><s:select id="shippingType"
								cssStyle="width: 207px;" name="shippingType"
								list="dropDownMap['SHIPPING_TYPE']" listKey="value"
								listValue="value" value="shippingType"></s:select>
						</td>
					</tr>
					<tr>
						<th width="103">Shipping Rule</th>
						<td><s:select id="shippingRule" cssStyle="width: 207px;"
								name="shippingRule" list="dropDownMap['SHIPPING_RULE']"
								listKey="value" listValue="value" value="shippingRule"></s:select>
						</td>
					</tr>
					<tr>
						<th width="103">Ship Via</th>
						<td width="217"><s:select id="changeShipViaSel"
								cssStyle="width:207px"
								list="specDropDownMap['SHIP_METHOD'].dropDownDTOs" listKey="id"
								listValue="name" headerKey="" headerValue="Please select"
								name="shipMethod"></s:select>
						</td>
					</tr>
				</table>

				<div id="" style="margin-top: -6px;">
					<table width="320" border="0" cellpadding="0" cellspacing="0"
						class="pay_table">
						<tr>
							<th width="103">Packages</th>
							<td width="217"><input id="packageTotal" type="text"
								class="NFText2" size="35" readonly="readonly" value="" /></td>
						</tr>
						<tr>
							<th>Weight</th>
							<td><input id="billableWeightTotal" type="text"
								class="NFText2" size="35" readonly="readonly" value="" /></td>
						</tr>
						<tr>
							<th>Zone</th>
							<td><input id="zone" type="text" class="NFText2" size="35"
								readonly="readonly" value="" /></td>
						</tr>
						<tr>
							<th>Cost</th>
							<td><input id="costTotal" type="text" class="NFText2"
								size="35" readonly="readonly" value="" /> <input
								id="costTotalVal" type="hidden" class="NFText2" size="35"
								readonly="readonly" value="" /></td>
						</tr>
						<tr>
							<th>Customized Cost</th>
							<td><s:if test='shipAmtChanged == "Y"'>
									<input id="costTotal2" type="text" class="NFText2" size="35"
										value="${shipAmt}" />
								</s:if> <s:else>
									<input id="costTotal2" type="text" class="NFText2" size="35"
										value="" />
								</s:else>
							</td>
						</tr>
					</table>
				</div>

				<table width="320" border="0" cellpadding="0" cellspacing="0"
					class="pay_table">
					<tr>
						<td align="right" width="103"><input type="checkbox"
							id="shipAccountFlag" /></td>
						<td align="left">
							<div align="left">Customer's Shipping Account</div>
						</td>
					</tr>
					<tr>
						<th width="103">Account No</th>
						<td><input id="shipAccount" type="text" class="NFText"
							value="${shippingAccount}" _v="" size="36" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" height="30"><input id="ifCal"
							value="${ifCal}" type="hidden" /> <input
							name="calculateShipping" id="calculateShipping"
							class="style_botton2" type="button" value="Calculate Shipping" />
						</td>
					</tr>
				</table>
			</td>
			<td valign="top" class="line_Notes"><table border="0"
					align="center" cellpadding="0" cellspacing="0" class="pay_table">
					<tr>
						<th>View</th>
						<td><select name="totalType" id="totalType">
								<option value="quorder">Total Order</option>
								<option value="billed">Total Billed</option>
								<option value="unbilled">Total Unbilled</option>
								<option value="refund">Total Refund</option>
						</select>
						</td>
					</tr>
					<tr>
						<th>Currency</th>
						<td><s:select name="toCurrency" id="toCurrency"
								list="specDropDownMap['CURRENCY'].dropDownDTOs" listKey="name"
								listValue="name" value="toCurrency" _h="toCurrency"></s:select>
							<input name="fromCurrency" id="fromCurrency" type="hidden"
							value="${fromCurrency}" />
						</td>
					</tr>
					<tr>
						<th>Subtotal</th>
						<td><input name="subtotal" id="subtotal" type="text"
							class="NFText2"
							value="${billTotal.symbol}${billTotal.quorderSubtotal}" size="20"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<th>Discount</th>
						<td><input name="discount" id="discount" type="text"
							class="NFText2"
							value="${billTotal.symbol}${billTotal.quorderDiscount}" size="20"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<th>Gift Card</th>
						<td><input name="" id="couponValue" type="text"
							class="NFText2"
							value="${billTotal.symbol}${billTotal.quorderTotalCouponValue}"
							size="20" readonly="readonly" /></td>
					</tr>
					<tr>
						<th>Shipping&Handling</th>
						<td><input name="shipAmt" id="shipAmt" type="text"
							class="NFText2"
							value="${billTotal.symbol}${billTotal.quorderShipAmt}" size="20"
							readonly="readonly" />
						</td>
					</tr>
					<tr>
						<th>Handling</th>
						<td><s:if
								test="billTotal == null || billTotal.handlingFee == null || billTotal.handlingFee == ''">
								<input id="handlingFee" type="text" class="NFText2" value=""
									size="20" readonly="readonly" />
							</s:if> <s:else>
								<input id="handlingFee" type="text" class="NFText2"
									value="${billTotal.symbol}${billTotal.handlingFee}" size="20"
									readonly="readonly" />
							</s:else>
						</td>
					</tr>
					<tr>
						<th class="Total_td3"
							style="border-bottom: 1px solid #990000; padding-bottom: 8px;">Taxes</th>
						<td class="Total_td3"
							style="border-bottom: 1px solid #990000; padding-bottom: 8px;"><input
							name="tax" id="tax" type="text" class="NFText2"
							value="${billTotal.symbol}${billTotal.quorderTax}" size="20"
							readonly="readonly" />
						</td>
					</tr>
					<tr>
						<th class="Total_td">Total</th>
						<td class="Total_td"><input name="total" id="total"
							type="text" class="NFText2" style="color: #990000;"
							value="${billTotal.symbol}${billTotal.quorderTotal}" size="20"
							readonly="readonly" />
						</td>
					</tr>
					<tr>
						<th class="total_td2">Total Payments</th>
						<td class="total_td2"><input name="totalPayments"
							id="totalPayments" type="text" class="NFText2"
							style="color: #003399;"
							value="${billTotal.symbol}${billTotal.quorderTotalPayments}"
							size="20" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" height="30"><input
							name="calculateTotal" id="calculateTotal" class="style_botton2"
							type="button" value="Calculate Total" /> <input
							class="style_botton2" type="button" value="View Payments"
							id="ViewPaymentsTrriger" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<input type="hidden" id="quorderStatus" value="${mainStatus}" />
		<input name="symbol" id="symbol" value="${symbol}" type="hidden" />
	</table>

	<div title="view total payments list" id="orderTotalPaymentsDialogView" />
	<table width="960" border="0" cellpadding="0" cellspacing="0"
		class="list_table" style="margin-top: 0px;">
		<tr>
			<th width="50%">Returns</th>
			<th>Refunds</th>
		</tr>
	</table>
	<div class="frame_box3" style="height: 70px; margin-bottom: 5px;">
		<table width="960" border="0" cellpadding="0" cellspacing="0"
			class="list_table">
			<tr>
				<td valign="top" width="50%"><s:iterator value="returnItemList"
						id="item">
						<s:date name="creationDate" format="yyyy-MM-dd" />: ${item.returnQty} of 
		      	  <span title="${item.name}"> <s:if
								test="name.length() > 45">
								<s:property value="name.substring(0, 45)" />...
			      	  </s:if> <s:else>${item.name}</s:else> </span>(Cat #${item.catalogNo}) returned <br />
					</s:iterator>
				</td>
				<td valign="top">None</td>
			</tr>
		</table>
	</div>
	<div align="center">
		<input id="replace_btn" type="button" class="style_botton2"
			value="Replace Order" /> <input name="Submit5" type="button"
			class="style_botton2" value="Refund" /> <input id="isNew"
			type="hidden" value="${isNew}" />
	</div>
</body>
</html>