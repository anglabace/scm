<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script>
$(document).ready(function(){
	$("#allItemsForm").find(":text, :textarea").bind("focus", function(){ $(this).trigger("blur"); });
	$("tr[_h='onlyQuoteTr']").show();
});
</script>
</head>  

<body>
<form id="allItemsForm" name="allItemsForm">
<input type="hidden" id="codeType" value="${codeType}" />
<s:iterator value="itemMap">
<input type="hidden" value="${value.quoteItemId}" name="quoteItemId[]" />
<span class="blue_price">Item #${value.itemNo}: </span>
<table  border="0" cellpadding="0" cellspacing="0" class="General_table">
  <tr>
    <th > Item Status </th>
    <td>
		<input name="${value.quoteItemId}_status" type="text" class="NFText" readonly="readonly" value="${value.statusText}" size="34" />
    </td>
    <th rowspan="2" valign="top">Description </th>
    <td colspan="2" rowspan="2" valign="top">
    	<textarea name="${value.quoteItemId}_description" class="content_textarea2" style="width: 250PX;" readonly="readonly">${value.shortDesc}</textarea>
    </td>
  </tr>
  <tr>
    <th>Other Info </th>
    <td>
    	<input name="${value.quoteItemId}_otherInfo" type="text" class="NFText" readonly="readonly" value="${value.otherInfo}" size="34" />
    </td>
  </tr>
  <tr>
    <th>Catalog</th>
    <td>
    	<input name="${value.quoteItemId}_catalogNo" type="text" class="NFText" value="${value.catalogText}" size="34" readonly="readonly" />
    </td>
        <th rowspan="2" valign="top">Full Description</th>
    <td colspan="2" rowspan="2" valign="top">
    	<textarea  name="${value.quoteItemId}_longDesc" class="content_textarea2" style="width: 250PX;">${value.longDesc}</textarea>
    </td>
  </tr>
  <tr>
    <th>Type</th>
    <td>
    	<input name="${value.quoteItemId}_type" type="text" value="${value.typeText}" class="NFText" size="34" readonly="readonly" />	
	</td>
  </tr>
  <tr>
    <th>Tax Status </th>
    <td>
			<s:if test='value.taxStatus == "Y"'>
				<input type="text" value="Taxable Item" readonly="readonly" class="NFText" size="34" name="${value.quoteItemId}_taxStatus"/>
			</s:if>				
			<s:else>
				<input type="text" value="Non-Taxable" readonly="readonly" class="NFText" size="34" name="${value.quoteItemId}_taxStatus"/>
			</s:else>	
    </td>
    <th rowspan="2" valign="top">Comment </th>
    <td colspan="2" rowspan="2" valign="top">
    	<textarea  name="${value.quoteItemId}_comment" class="content_textarea2" style="width: 250PX;">${value.comment}</textarea>
    </td>
  </tr>
  <tr>
    <th>Pick Location</th>
    <td colspan="3">
    	<s:set id="storageIdWarehouseId" value='value.storageId+"-"+value.warehouseId'></s:set>
	    <input name="" type="text" value='${locationMap[storageIdWarehouseId]}' class="NFText" size="34" readonly="readonly" />
    </td>
  </tr>
  <tr>
    <th>Schedule Shipment</th>
    <td>
		<input name="" type="text" class="NFText" value='${value.shipSchedule}' size="28" />Days
    </td>
    <th>Ship Via </th>
     <td>
    	<s:if test="value.shipMethod">1
	    	<input name="${value.quoteItemId}_shipMethod" type="text" class="NFText2" 
	    	value="${shipMethodMap[value.shipMethod]}" size="15" />
    	</s:if>
    	<s:else>
    		<input name="${value.quoteItemId}_shipMethod" type="text" class="NFText2" 
    		value='${request.firstMethodName}' size="15" />
    	</s:else>
    </td>
  </tr>
  	<tr _h="onlyQuoteTr" style="display:none;">
		<th>Shipping Routes</th>
		<td colspan="1">
		  <select name="select12" style="width:200px;">
            <option>Vendor to Nanjing Shipping Center</option>
          </select>
		</td>
		<td>&nbsp;</td>
	</tr>
	<tr _h="onlyQuoteTr" style="display:none;">
		<th>Tracking No</th>
		<td>
			<input id="itemForm_trackingNo" name="_trackingNo" type="text" class="NFText" value="" size="34" />
		</td>
		
		<th>Shipped</th>
		<td>
			<input name="" type="text" class="ui-datepicker NFText" style="width:125px;" value="     -   -  " size="20"/>
		</td>
	</tr>
  <tr>
    <th valign="top">Selling Note</th>
    <td colspan="4">
    	<textarea name="${value.quoteItemId}_note" class="content_textarea" style="color: #990000;">${value.sellingNote}</textarea>
    </td>
  </tr>
</table>
<br/>
</s:iterator>
<p align="center">
	<input id="allItemsCloseBtn" type="button" name="close" value="Close" class="style_botton" onclick="parent.$('#allItemDetailsDialog').dialog('close');" />  
</p>
</form>
</body>
</html>
    