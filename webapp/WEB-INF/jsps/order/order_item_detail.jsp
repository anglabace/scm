<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"  />
<s:include value="order_config.jsp"></s:include>
<title>scm item detail</title>
</head>
<body>
<div class="blue_price">Item No:&nbsp;<span class="item_no" id="itemNoSpan">${itemNo}</span></div>
<form name="itemForm" id="itemForm">
	<input type="hidden" value="${itemId}" name="itemId" id="itemId" />
	<input type="hidden" value="" id="itemCheckedStr" />
	<input type="hidden" value="0" name="tab0_changed" id="tab0_changed" />
	<input type="hidden" id="initItemDetailId" />
	<table border="0" cellpadding="0" cellspacing="0" class="General_table" style="">
		<tr>
			<th>Item Status</th>
			<td>
				<input id="statusText" type="text" class="NFText" readonly="readonly" size="34" name="statusText" value="${statusText}" />
				<input id="status" type="hidden" id="" name="status" value="${status}"/>
				<input type="hidden" id="statusReason" name="statusReason" value="${statusReason}"/>
				<input type="hidden" id="statusNote" name="statusNote" value="${statusNote}"/>
			</td>
			<td>
			<s:if test="purchaseOrderFlag == 0">
				<s:if test='status != "CC" || (status == "CC" && ("CC"==dbStatus || "RV"==dbStatus))'>
					<input id="updateStatusDialogTrigger" type="button" class="style_botton" value="Update" />
				</s:if><s:else>
					<input disabled="disabled" type="button" class="style_botton" value="Update" />
				</s:else>
				<input type="button" class="style_botton4" value="Item Status History" id="itemStatusHistoryDialogTrigger" /> 
			</s:if>
			</td>
			<th rowspan="2" valign="top" width="110px;">Description</th>
			<td colspan="2" rowspan="2" valign="top">
				<textarea id="" name="shortDesc" class="content_textarea2" style="width: 250PX;" readonly="readonly">${shortDesc}</textarea>
			</td>
		</tr>
		<tr>
			<th>Other Info</th>
			<td>
				<input id="otherInfo" name="otherInfo" type="text" class="NFText" readonly="readonly" value="${otherInfo}" size="34" />
			</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<th>Catalog</th>
			<td>
				<input id="" name="" type="text" class="NFText" value="${catalogText}" size="34" readonly="readonly"/>
				<input id="catalogId" type="hidden" value="${catalogId}">
			</td>
			<td>
				<input id="itemMoreInfoTrigger" type="button" class="style_botton4" value="More Info" />
			</td>
			<th rowspan="2" valign="top">Full Description</th>
			<td colspan="2" rowspan="2" valign="top">
				<textarea id="longDesc" name="longDesc" class="content_textarea2" style="width: 250PX;">${longDesc}</textarea>
			</td>
		</tr>
		<tr>
			<th>Type</th>
			<td>
				<input id="" type="text" name="" value="${typeText}" class="NFText" size="34" readonly="readonly" />
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
				<textarea id="comment" name="comment" class="content_textarea2" style="width: 250PX;">${comment}</textarea>
			</td>
		</tr>
		<tr>
			<th>Pick Location</th>
			<td colspan="">
				<input type="hidden" id="warehouseId" name="warehouseId" value="${warehouseId}" />
				<input type="hidden" id="storageId" name="storageId" value="${storageId}" />
				<s:select id="storageIdWarehouseId" cssStyle="width: 200px;" list="pickLocationList" listKey="id" listValue="name" value="storageId"></s:select>
			</td>
			<td>&nbsp;</td>
		</tr>
		<tr><th>Scheduled Delivery</th>
			<td><input id="shipSchedule1" name="shipSchedule" type="text" class="NFText" 
				size="34"  value="${shipSchedule}" readonly="readonly"/>Days
			</td>
			<td>
				<input id="updateOrderScheduleDeliveryDialogTrigger" type="button" class="style_botton" value="Modify" />
			</td>
			<th>Schduled Shipment Date</th>
			<td>
				<input id="targetDate" type="text" style="width: 250px;" class="NFText"
					value="<s:date name="targetDate" format="yyyy-MM-dd"/>" disabled="disabled"/>
			</td>
		</tr>
		<tr>
			<th>Shipping Routes</th>
			<td>
	          <s:select id="shippingRoute" name="shippingRoute" list="dropDownMap['SHIPPING_ROUTE']" listKey="value" listValue="text" headerKey="" headerValue="Please select" value="shippingRoute" cssStyle="width: 200px;" ></s:select>
			</td>
			<td>&nbsp;</td>
			<th>Ship Via</th>
			<td>
				<s:select id="shipMethod" name="shipMethod" list="specDropDownList['SHIP_METHOD'].dropDownDTOs" listKey="id" listValue="name" value="shipMethod" cssStyle="width: 260px;"></s:select>
			</td>
		</tr>
		<tr>
			<th>Tracking No</th>
			<td><s:if test="shipPackageList != null && shipPackageList.size>0">
				<select id="packTrackingNo" style="width: 200px;">
				<s:iterator value="shipPackageList">
					<option value="${shipments.shipmentId}" pkgId="${packageId}">${trackingNo}</option>
				</s:iterator>
				</select>
				</s:if><s:else>
				<select id="trackingNo" style="width: 200px;">
					<option>Not Billed</option>
				</select>
				</s:else>
			</td>
			<td>&nbsp;<input type="button" value="Track Package" class="style_botton2" id="showPackage"></td>
			<th>Shipped</th>
			<td>
				<input id="shipDate" type="text" class="NFText" style="width:250px;" value='<s:date name="shipDate" format="yyyy-MM-dd HH:mm:SS"/>' size="20" readonly="readonly" />
			</td>
		</tr>
		<tr>
			<th valign="top">Selling Note</th>
			<td colspan="2">
				<textarea id="" name="sellingNote" class="content_textarea" style="color: #990000;width:325px;" readonly="readonly">${sellingNote}</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="6"><br />
				<div align="center">
					<input id="allItemDetailsDialogTrigger" type="button" class="style_botton2" value="All Items Detail" /> 
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
<script type="text/javascript">
if (parent.operation_method != "edit") {
	$('input').attr("disabled",true);
	$('button').attr("disabled",true);
	$('select').attr("disabled",true);
}
</script>
</html>