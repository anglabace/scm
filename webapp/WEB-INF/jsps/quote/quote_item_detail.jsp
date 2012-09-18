<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"  />
<s:include value="quote_config.jsp"></s:include>
<title>scm item detail</title>
</head>  
<body>
<div class="blue_price">Item No:&nbsp;<span class="item_no" id="itemNoSpan" name="quoteItem.itemNo">${quoteItem.itemNo}</span></div>
<form name="itemForm" id="itemForm">
	<input type="hidden" value="${itemId}" name="itemId" id="itemId" />
	<input type="hidden" value="" id="itemCheckedStr" />
	<input type="hidden" value="0" name="tab0_changed" id="tab0_changed" />
	<input type="hidden" id="initItemDetailId" />
	<s:hidden name="tempStatusStr"  id="tempStatusStr"/>
	<table border="0" cellpadding="0" cellspacing="0" class="General_table" style="">
		<tr>
			<th>Item Status</th>
			<td>
				<input id="statusText" type="text" class="NFText" readonly="readonly" size="34" name="quoteItem.statusText" value="${quoteItem.statusText}" />
				<input id="status" type="hidden" name="quoteItem.status" value="${quoteItem.status}"/>
				<input type="hidden" id="statusReason" name="quoteItem.statusReason" value="${quoteItem.statusReason}"/>
				<input type="hidden" id="statusNote" name="quoteItem.statusNote" value="${quoteItem.statusNote}"/>
			</td>
			<td>
		<s:if test="tempStatusStr == 'CO' || tempStatusStr == 'CW' || tempStatusStr == 'VD' || tempStatusStr == 'OH'">
				<input id="updateStatusDialogTrigger" type="button" class="style_botton" value="Update" disabled="disabled"/>
				<input type="button" class="style_botton4" value="Item Status History" id="itemStatusHistoryDialogTrigger" /> 
		</s:if>
		<s:else>
				<input id="updateStatusDialogTrigger" type="button" class="style_botton" value="Update" />
				<input type="button" class="style_botton4" value="Item Status History" id="itemStatusHistoryDialogTrigger" /> 
		</s:else>
			</td>
			<th rowspan="2" valign="top" width="110px;">Description</th>
			<td colspan="2" rowspan="2" valign="top">
				<textarea id="" name="quoteItem.shortDesc" class="content_textarea2" style="width: 250PX;" readonly="readonly">${quoteItem.shortDesc}</textarea>
			</td>
		</tr>
		<tr>
			<th>Other Info</th>
			<td>
				<input id="otherInfo" name="quoteItem.otherInfo" type="text" class="NFText" readonly="readonly" value="${quoteItem.otherInfo}" size="34" />
			</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<th>Catalog</th>
			<td>
				<input id="" name="quoteItem.catalogText" type="text" class="NFText" value="${quoteItem.catalogText}" size="34" readonly="readonly"/>
				<input id="catalogId" type="hidden" value="${quoteItem.catalogId}">
			</td>
			<td>
			<s:if test="tempStatusStr == 'CO'">
				<input id="itemMoreInfoTrigger" type="button" class="style_botton4" value="More Info" disabled="disabled"/>
		</s:if>
		<s:else>
			<input id="itemMoreInfoTrigger" type="button" class="style_botton4" value="More Info" />
		</s:else>
				
			</td>
			<th rowspan="2" valign="top">Full Description</th>
			<td colspan="2" rowspan="2" valign="top">
				<textarea id="longDesc" name="quoteItem.longDesc" class="content_textarea2" style="width: 250PX;">${quoteItem.longDesc}</textarea>
			</td>
		</tr>
		<tr>
			<th>Type</th>
			<td>
				<input id="" type="text" name="" value="${quoteItem.typeText}" class="NFText" size="34" readonly="readonly" />
			</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<th>Tax Status</th>
			<td>
				<s:if test='taxStatus == "Y"'>
					<input type="text" id="" name="" value="Taxable Item" readonly="readonly" class="NFText" size="34" />
				</s:if>
				<s:else>
					<input type="text" id="" name="" value="Non-Taxable" readonly="readonly" class="NFText" size="34" />
				</s:else>
			</td>
			<td>&nbsp;</td>
			<th rowspan="2" valign="top">Comment</th>
			<td colspan="2" rowspan="2" valign="top">
				<textarea id="comment" name="quoteItem.comment" class="content_textarea2" style="width: 250PX;">${quoteItem.comment}</textarea>
			</td>
		</tr>
		<tr>
		<th>Pick Location</th>
			<td colspan="">
				<input type="hidden" id="warehouseId" name="quoteItem.warehouseId" value="${warehouseId}" />
				<input type="hidden" id="storageId" name="quoteItem.storageId" value="${quoteItem.storageId}" />
				<s:select id="storageIdWarehouseId" cssStyle="width: 203px;" list="pickLocationList" listKey="id" listValue="name" value="quoteItem.storageId"></s:select>
				
			</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<th>Scheduled Delivery</th>
			<td><input id="shipSchedule1"  name="shipSchedule1" type="text" class="NFText" 
				size="34"  value="${quoteItem.shipSchedule}" readonly="readonly"/>Days
			</td>
			<td>
				<input id="updateQuoteScheduleDeliveryDialogTrigger" type="button" class="style_botton" value="Modify" />
			</td>
			<th>Ship Via</th>
			<td>
				<s:select id="shipMethod" name="quoteItem.shipMethod" list="specDropDownList['SHIP_METHOD'].dropDownDTOs" listKey="id" listValue="name" value="quoteItem.shipMethod" cssStyle="width: 260px;"></s:select>
			</td>
		</tr>
		<tr>
			<th>Shipping Routes</th>
			<td colspan="1">
	          <s:select id="shippingRoute" name="quoteItem.shippingRoute" list="dropDownMap['SHIPPING_ROUTE']" listKey="value" listValue="text" headerKey="" headerValue="Please select" value="quoteItem.shippingRoute" cssStyle="width: 203px;"></s:select>
			</td>
			<td>&nbsp;</td>
		</tr>
		<tr style="display:none;">
			<th>Tracking No</th>
			<td>
				<input id="trackingNo" name="trackingNo" type="text" class="NFText" value="${trackingNo}" size="34" />
			</td>
			<td>&nbsp;<input type="button" value="Track Package" class="style_botton2" name="Submit132"></td>
			<th>Shipped</th>
			<td>
				<input name="" type="text" class="ui-datepicker NFText" style="width:125px;" value="     -   -  " size="20"/>
			</td>
		</tr>
		<tr>
			<th valign="top">Selling Note</th>
			<td colspan="2">
				<textarea id="" name="quoteItem.sellingNote" class="content_textarea" style="color: #990000;width:325px;" readonly="readonly">${quoteItem.sellingNote}</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="6"><br />
				<div align="center">
				<s:if test="tempStatusStr == 'CO'">
				<input id="allItemDetailsDialogTrigger" type="button" class="style_botton2" value="All Items Detail" disabled="disabled"/> 
		</s:if>
		<s:else>
			<input id="allItemDetailsDialogTrigger" type="button" class="style_botton2" value="All Items Detail" /> 
		</s:else>
					
				</div>
			</td>
		</tr>
	</table>
</form>
</body>
<script src="${global_js_url}jquery/jquery.js" type="text/javascript" language="javascript"></script>
<script src="${global_js_url}jquery/jquery.form.js" type="text/javascript" language="javascript"></script>
<script src="${global_js_url}util/util.js" type="text/javascript"></script>
<script src="${global_js_url}quoteorder/order_quotation_item_detail.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script src="${global_js_url}scm/gs.util.js" type="text/javascript"></script>
<script type="text/javascript">
if (parent.operation_method != "edit") {
	$('input').attr("disabled",true);
	$('button').attr("disabled",true);
	$('select').attr("disabled",true);
}
</script>
</html>