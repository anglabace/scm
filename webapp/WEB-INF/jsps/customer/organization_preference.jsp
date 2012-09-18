<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Production Resources</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" language="javascript"
			src="${global_js_url}jquery/jquery.js"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
		<script src="${global_js_url}jquery/jquery.validate.js?v=1"
			type="text/javascript"></script>
		<script type="text/javascript" src="${global_js_url}show_tag.js"></script>
		<script type="text/javascript">
		   var baseUrl="${global_url}";
		   
		   function getMyChanged() {
		     alert($("#changed_hid").val());
		   }
		   
           $(function() {            
                   if ('${preference.printedFlag}' == 'Y') {
                      $("#flag_check").get(0).checked = true;
                   }
           
	               $("input[type=text]").change(function() {
	                  $("#changed_hid").val("Y");
	               })
	               $("select").change(function() {
	                  $("#changed_hid").val("Y");
	               })        
	               $("#flag_check").click (function() {
	                    $("#changed_hid").val("Y");
		                if ($("#flag_check").get(0).checked) {
		                    $("#printedFlag_hid").val("Y");
		                } else {
		                    $("#printedFlag_hid").val("N");
		                }
	               });
            });
        </script>
	</head>
	<body style="margin: 0px; padding: 0px;">
		<div class="input_box" style="overflow: hidden; height: 3px;">
			&nbsp;
		</div>
		<div class="input_box" style="border: 1px solid #ccc;">
			<form method="get" id="preference_form">
				<input type="hidden" id="changed_hid" value="N" />
				<input type="hidden" name="preference.id" value="${preference.id}" />
				<input type="hidden" name="preference.orgId"
					value="${preference.orgId}" />
				<table border="0" cellpadding="0" cellspacing="0"
					class="General_table">
					<tr>
						<th width="138">
							Customer Contact Name
						</th>
						<td width="385">
							<input name="preference.custContactName"
								value="${preference.custContactName}" type="text" class="NFText"
								size="35" />
						</td>
						<th width="138">
							Representative
						</th>
						<td width="116">
							<s:select name="preference.representative"
								list="#{'Sales':'Sales', 'CustomerService':'Customer Service', 'PurchasingAgent':'Purchasing Agent'}"
								cssStyle="width:207px;" value="preference.representative"></s:select>
						</td>
					</tr>
					<tr>
						<th valign="top">
							Invoicing Rule
						</th>
						<td>
							<s:select name="preference.invoiceRule"
								list="#{'AfterDelivery':'After Delivery', 'Immediate':'Immediate', 'AfterOrderdelivered':'After Order delivered', 'CustomerScheduleafterDelivery':'Customer Schedule after Delivery'}"
								cssStyle="width:207px;" value="preference.invoiceRule"></s:select>
						</td>
						<th>
							Invoice Schedule
						</th>
						<td>
							<s:select name="preference.invoiceSchedule"
								list="#{'Monthly':'Monthly'}" cssStyle="width:207px;"
								value="preference.invoiceSchedule"></s:select>
						</td>
					</tr>
					<tr>
						<th valign="top">
							Shipping Rule
						</th>
						<td>
							<s:select name="preference.shipRule"
								list="#{'Availability':'Availability', 'CompleteLine':'Complete Line', 'Manual':'Manual', 'CompleteOrder':'Complete Order', 'AfterReceipt':'After Receipt'}"
								cssStyle="width:207px;" value="preference.shipRule"></s:select>
						</td>
						<th>
							Shipping Schema
						</th>
						<td>
							<s:select name="preference.shippingSchema"
								list="{'Delivery','Pickup','Shipper'}"
								cssStyle="width:207px;" value="preference.shippingSchema"></s:select>
						</td>
					</tr>
					<tr>
						<th valign="top">
							Price Catalog
						</th>
						<td>
							 <s:select id="orderPriceCatalog" name="preference.orderPriceCatalog" list="catalogList" listKey="catalogId" listValue="catalogName"></s:select>
						</td>
						<th>
							Flat Discount Percentage
						</th>
						<td>
							<input name="preference.flatDiscPercent" onkeyup="this.value=value.replace(/[^\d]/g,'')" type="text" class="NFText2"
								value="${preference.flatDiscPercent}" size="35" />
						</td>
					</tr>
					<tr>
						<th valign="top">
							Payment Method
						</th>
						<td>
							<s:select name="preference.paymentMethod"
								list="#{'Cash':'Cash', 'DirectDebit':'Direct Debit', 'CreditCard':'Credit Card', 'OnCredit':'On Credit', 'Check':'Check', 'DirectDeposit':'Direct Deposit'}"
								cssStyle="width:207px;" value="preference.paymentMethod"></s:select>
						</td>
						<th>
							Payment Term
						</th>
						<td>
							<s:select name="preference.orderPaymentTerm"
								list="#{'15Days':'15 Days', 'Net30':'2%10 Net 30', '30DaysNet':'30 Days Net', 'Immediate-in30days':'50% Immediate - 50% in 30 days', 'Immediate':'Immediate'}"
								cssStyle="width:207px;" value="preference.orderPaymentTerm"></s:select>
						</td>
					</tr>
					<tr>
						<th valign="top">
							Return Policy
						</th>
						<td>
							<s:select name="preference.returnPolicy"
								list="#{'30 Days':'30Days'}"
								cssStyle="width:207px;" value="preference.returnPolicy"></s:select>
						</td>
						<th>
							&nbsp;
						</th>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<th valign="top">
							Order Reference
						</th>
						<td>
							<input name="preference.orderReference" value="${preference.orderReference}" type="text" class="NFText" size="35" />
						</td>
						<th>
							Order Description
						</th>
						<td>
							<input name="preference.orderDescription" value="${preference.orderDescription}" type="text" class="NFText" size="35" />
						</td>
					</tr>
					<tr>
						<th valign="top">
							Invoice Print Format
						</th>
						<td>
							<s:select name="preference.invoicePrintFormat"
								list="{'PDF'}"
								cssStyle="width:207px;" value="preference.invoicePrintFormat"></s:select>
						</td>
						<th>
							Order Print Format
						</th>
						<td>
							<input name="preference.orderPrintFormat" type="text" class="NFText" value="${preference.orderPrintFormat}"
								size="35" />
						</td>
					</tr>
					<tr>
						<th valign="top">
							&nbsp;
						</th>
						<td>
						    <c:set var="flag" value="N"></c:set>
						    <c:if test="${! empty preference.printedFlag}">
						       <c:set var="flag" value="${preference.printedFlag}"></c:set>
						    </c:if>
						    <input type="hidden" name="preference.printedFlag" value="${flag}" id="printedFlag_hid" />
							<input type="checkbox" value="Y" id="flag_check" />
							<label for="flag_check">Discount Printed on Invoice and Order</label>
						</td>
						<th>
							&nbsp;
						</th>
						<td>
							&nbsp;
						</td>
					</tr>
				</table>
			</form>
			<div class="input_box" style="overflow: hidden; height: 12px;">
				&nbsp;
			</div>
		</div>
	</body>
</html>