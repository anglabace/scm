<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Return Order</title>
<base id="myBaseId" href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"  />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<s:include value="order_config.jsp"></s:include>
<script language="javascript" src="${global_js_url}jquery/jquery.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.form.js"></script>
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="${global_js_url}scm/gs.util.js" language="javascript" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js" type="text/javascript"></script>
<script src="${global_js_url}quoteorder/order_quotation_return.js" type="text/javascript"></script>
</head>
<body class="content">
<div class="scm">
<div class="title_content">
<div class="input_box">
<div class="content_box">
<form name="returnOrderForm" id="returnOrderForm">
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
  <tr>
    <th>RMA #</th>
    <td colspan="5">
      <s:select name="selectReturnOrder" id="selectReturnOrder" cssStyle="width:160px;" list="returnList" listKey="rmaNo" listValue="'Unprocessed RMA #'+rmaNo"  headerKey="-1" headerValue="New RMA" value="orderReturn.rmaNo" ></s:select>
    </td>
  </tr>
  <tr>
    <th>Entered By</th>
    <td><input name="createUser" id="createUser" type="text" class="NFText" value="${orderReturn.createUser}" size="20" readonly="readonly" /></td>
    <th>Entry Date</th>
    <td><input name="creationDate" id="creationDate" type="text" class="NFText" value='<s:date name="orderReturn.creationDate" format="yyyy-MM-dd" />' size="18" readonly="readonly" /></td>
    <th>&nbsp;&nbsp;Expiration Date</th>
    <td>
    	<input name="exprDate" id="exprDate" type="text" class="ui-datepicker" style="width:125px;;border:1px solid #A7AAB9;padding: 0px 3px;margin:0px 2px" value='<s:date name="orderReturn.exprDate" format="yyyy-MM-dd" />' size="18" readonly="readonly" />
    </td>
  </tr>
  <tr>
    <th><span class="red_font">*</span>Shipping Refund</th>
    <td><input name="shipRefund" id="shipRefund" type="text" class="NFText" value="${orderReturn.shipRefund}" size="20" /></td>
    <th><span class="red_font">*</span>Total Refund </th>
    <td>
    	<input name="totalRefund" id="totalRefund" type="text" class="NFText" value="${orderReturn.totalRefund}" readonly="readonly" size="20" />
    </td>
    <th colspan="2">&nbsp;</th>
  </tr>
  <tr>
    <th>Processed By</th>
    <td><input name="processUser" id="processUser" type="text" class="NFText" size="20" value="${orderReturn.processUser}" readonly="readonly" /></td>
    <th>Process Date</th>
    <td>
    	<input name="processedDate" id="processedDate" type="text" class="pickdate NFText" style="width:125px;" value='<s:date name="orderReturn.processedDate" format="yyyy-MM-dd" />' size="18" readonly="readonly" />
    </td>
    <th colspan="2">&nbsp;</th>
  </tr>
  <tr>
    <th valign="top">Return Note</th>
    <td colspan="5">
      <textarea name="note" id="note" class="content_textarea">${orderReturn.note}</textarea>
    </td>
  </tr>
</table>
  <table width="780" border="0" cellpadding="0" cellspacing="0" class="list_table">
    <tr>
      <th width="46">
        <input name="allItemCheck" id="allItemCheck" type="checkbox"  />
        <img id="delItemList" name="delItemList" src="images/file_delete.gif" alt="Delete" width="16" height="16" border="0" style="cursor:pointer" mce_style="cursor: pointer"/></th>
      <th width="40">Item No </th>
      <th width="40">Cat No </th>
      <th>Name</th>
      <th width="40">Tax</th>
      <th width="45">Unit Price </th>
      <th width="35">Disc</th>
      <th width="50">Shipped Qty </th>
      <th width="60">Shipped Size </th>
      <th width="45">Return Qty </th>
      <th width="60">Return Size</th>
      <th width="50">Reason</th>
      <th width="55">Exchange Item </th>
      <th width="61">Refund</th>
    </tr>
   </table>
   <div id="itemTableDiv" class="frame_box6" style="width: 800px;">
	<table width="780" border="0" cellpadding="0" cellspacing="0" id="returnOrderItemList" name="returnOrderItemList" class="list_table3">	
    <s:iterator value="orderReturnItemMap">
	    <tr itemId='${key}'>
	      <td width="46">
	      	<input type="checkbox" value="${key}" name="itemId"/>
	      </td>
	      <td width="40" align="center">
	      	<span id="itemNo">${value.itemNo}</span>
	      </td>
	      <td width="40">${value.catalogNo}</td>
	      <td>
	      	<s:property value="value.name.substring(0, 10)+'...'" />
	      </td>
	      <td width="40">${value.tax}</td>
	      <td width="45"><div align="right">${value.unitPrice}</div></td>
	      <td width="35">${value.discount}</td>
	      <td width="50"><span id="shippedQty">${value.shippedQty}</span></td>
	      <td width="60"><span id="shipSize">${value.shipSize}</span>${value.shipUom}</td>
	      <td width="45"><span style="width:100%;display:inline-block" id="returnQty">${value.returnQty}</span></td>
	      <td width="60"><span style="display:inline-block;width:40px;" id="returnSize">${value.returnSize}</span>${value.shipUom}</td>
	      <td width="50">
			<s:select name="returnReason" id="" cssStyle="width:46px;" 
				list="#{'DIF':'DIF','TDI':'TDI','DAT':'DAT','NOL':'NOL','DEF':'DEF','PAR':'PAR','UNA':'UNA','OTH':'OTH' }" value="value.returnReason"></s:select>	     	 
	      </td>
	      <td width="55" align="center">
	      	<s:checkbox name="exchangeFlag" id="" value='value.exchangeFlag == "Y"' fieldValue="Y"></s:checkbox>
		  </td>
	      <td width="61">
	      	<span align="right" id="refund">${value.refund}</span>
	      </td>
	    </tr>
    </s:iterator>
  </table>
  </div>
</form>
<p align="center">
	<input type="button" name="returnProcess" id="returnProcess" class="style_botton" value="Process" style="display:none" />
	<input type="button" name="returnSave" id="returnSave" class="style_botton" value="Save" />
	<input type="button" name="returnDel" id="returnDel" class="style_botton" value="Delete" style="display:none"/>
	<input type="button" name="returnCancel" id="returnCancel" class="style_botton" value="Cancel"/>
</p>
</div>
</div>
</div>
</div>
</body>
</html>
