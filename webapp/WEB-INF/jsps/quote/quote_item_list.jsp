<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />

<script src="${global_js_url}jquery/jquery.js" type="text/javascript" language="javascript"></script>
<script src="${global_js_url}util/util.js" type="text/javascript"></script>
<s:include value="quote_config.jsp"></s:include>
<script src="${global_js_url}quoteorder/order_quotation_item_list.js" type="text/javascript"></script>
<script src="${global_js_url}jquery.funkyUI.js" type="text/javascript"></script>
<script type="text/javascript">
if (parent.operation_method != "edit") {
	$('input').attr("disabled",true);
	$('button').attr("disabled",true);
	$('select').attr("disabled",true);
}
/*
* add by zhanghuibin
* 增加变量：
* preTRObj ： 上次点击的tr对象
* clickItemId： 当前点击的Item的Id
* */
var preTRObj;
var clickItemId;
</script>
<title>scm item list</title>
</head>
<body>
<input type="hidden" id="status" value="${quote.status}" />
<input type="hidden" id="prmtCode" value="${quote.quotePromotion.prmtCode}" />
<input type="hidden" id="sessQuoteNo" value="${sessQuoteNo}" />
<table width="980"  border="0" cellpadding="0" cellspacing="0" class="list_table">
     <tr>
     	<th width="65">
   		<div class="quote_dele">
             <input name="allCheck" id="allCheck"  type="checkbox"/>
             <img src="images/file_delete.gif" name="deleteItem" id="deleteItem" alt="Delete" width="16" height="16" border="0" style="cursor:pointer" mce_style="cursor: pointer" />
          </div>
          <div id="upTableTd" class="down_up"><img src="images/up.jpg" width="8" height="8" title="up" style="cursor:pointer" mce_style="cursor: pointer"/></div> 
          <div id="downTableTd" class="down_up2"><img src="images/down.jpg" width="8" height="7" title="down" style="cursor:pointer" mce_style="cursor: pointer"/></div> 
       </th>
       <th width="60">Item No</th>
       <th width="60">Catalog No </th>
       <th width="250">Name</th>
       <th width="40">Status</th>
       <th width="45">Qty</th>
       <th width="40">UOM </th>
       <th width="60">Size </th>
       <th width="60">Amount</th>
       <th width="60">Cost</th>
       <th width="60">Unit Price </th>
       <th width="60">Discount</th>
       <th>Tax</th>
     </tr>
</table>
<div id="itemTableDiv" class="frame_box5" style="width:1000px; height:120px; overflow:scroll;overflow-x:hidden;background-color: #EEF2FD;">
	<table id="itemTable" width="980"  border="0" cellpadding="0" cellspacing="0" class="list_table_new">		
		<s:iterator value="itemListResultMap" status="itemStatus">
         <s:if test="#itemStatus.even == true">
         	<tr itemId="${key}" class="row_even">
         </s:if>
         <s:else>
         	<tr itemId="${key}" class="row_odd">
         </s:else>
           <td width="65">
            <input type="checkbox" value="${key}" name="itemId" id="" />
            <c:if test="${value.virusSeqFlag =='1'}">
            <img width="16" height="14" style="padding-top:3px;" src="${global_url}images/xiaochong.gif">
            </c:if>
            <input name="amount" type="hidden" value="${value.amount}" />
            <input name="unitPrice" type="hidden" value="${value.unitPrice}" />
            <input name="tax" type="hidden" value="${value.tax}" />
            <input name="discount" type="hidden" value="${value.discount}" />
            <input name="upSymbol" type="hidden" value="${value.upSymbol}" />
            <input name="upPrecision" type="hidden" value="${value.upPrecision}" />
            <input name="type" type="hidden" value="${value.type}" />
            <input name="parentId" type="hidden" value="${value.parentId}" />
           </td>
           <td width="60"  align="center">
           	<div name="tdItemNo">${value.itemNo}</div>
           </td>
           <td width="60" align="center">
           	   <div name="tdCatalogNo" title="${value.catalogNo}">${value.catalogNo}</div>
           	   <input id="catalogNoTd" name="catalogNoTd" type="hidden" value="${value.catalogNo}">
           </td>
           <td width="250">
            <div name="tdNameShort" title='${value.nameShow}'>
            	<s:if test="value.nameShow.length()>40">
            		<s:property value="value.nameShow.substring(0, 40)"/>...
            	</s:if>
            	<s:else>${value.nameShow}</s:else>
            </div>
           </td>
           <td width="40" align="center">
           	<div name="changeStatus">${value.status}</div>
           </td>
           <td width="45" align="center">
            <div style="width:100%" name="qtyTd" onclick="qtyTdClick(this);">&nbsp;${value.quantity}</div>
           </td>
           <td width="40" align="center">
           	<div name="tdQtyUom">&nbsp;${value.qtyUom}</div>
           </td>
           <td width="60" align="center">
           	<div name="tdSizeQtyUom">&nbsp;${value.size} ${value.sizeUom}</div>
           </td>
           <s:if test="value.tbdFlag == 1">
           	   <td width="60" align="right">
	           	<div name="tdSymbolAmount">&nbsp;TBD</div>
	           </td>
	           <td width="60" align="right">
	           	<div name="tdCost" onclick="tdCostClick(this);">&nbsp;TBD</div>
	           </td>
	           <td width="60"  align="right">
	           	<div name="tdSymbolUnitPrice" onclick="tdSymbolUnitPriceClick(this);">&nbsp;TBD</div>
	           </td>
           </s:if>
           <s:else>
	           <td width="60" align="right">
	           	<div name="tdSymbolAmount">&nbsp;${value.upSymbol}${value.amount}</div>
	           </td>
	           <td width="60" align="right">
	           	<div name="tdCost" onclick="tdCostClick(this);">&nbsp;$${value.cost}</div>
	           </td>
	           <td width="60"  align="right">
	           	<div name="tdSymbolUnitPrice" onclick="tdSymbolUnitPriceClick(this);">&nbsp;${value.upSymbol}${value.unitPrice}</div>
	           </td>
           </s:else>
           <td width="60" align="right">
           	<div name="tdDiscount">&nbsp;${value.upSymbol}${value.discount}</div>
           </td>
           <td align="right">
           	<div name="tdTax">&nbsp;${value.upSymbol}${value.tax}</div>
           </td>
         </tr>
       </s:iterator>
      </table>
</div>
</body>
</html>