<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript" src="${global_js_url}TabbedPanels.js"></script>
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript" src="${global_js_url}jquery/jquery.js"></script>
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>

<s:if test="codeType == 'order'"><s:include value="../order/order_config.jsp"></s:include></s:if>
<s:else><s:include value="../quote/quote_config.jsp"></s:include></s:else>

<script>
var quorderNo = "${quorderNo}" ;
</script>
<script src="${global_js_url}quoteorder/order_quotation_add.js" type="text/javascript"></script>
<script type="text/javascript" src="${global_js_url}scm/gs.util.js"></script>
<script type="text/javascript">
function init(searchClass){
	 if(searchClass == "SERVICE_ITEM"){
		 $("#serviceSplitTr").show();
	 }
	 if(searchClass == "SERVICE_TEMPLATE_ITEM"){
	 	$("#serviceSplit").attr("checked", false);
	 }
}

function changeSearchClass(searchClass){
	$("div[id*='propertySelect']").hide(); 
	$("[name='propertyName']").attr("disabled", true);

	$("[name='propertyValue1']").attr("disabled", true);
	$("[id*='propertyValue1']").hide();
	if(searchClass == "PRODUCT_ITEM"){
		$("div[id='propertySelect1']").show();
		$("div[id='propertySelect1'] select").attr("disabled", false);
		$("div[id='propertyValue1Inp']").show();
		$("div[id='propertyValue1Inp']>input").attr("disabled", false);
		$("#input1").show();
		$("#input2").val("").hide();
		$("#input3").val("").hide();
		$("#input4").val("").hide();
		$("#productPropertyValue1Sel").hide();
		$("#servicePropertyValue1Sel").hide();
		if($("#searchOperator").find("option[value='LIKE']").text()==""){
			$("#searchOperator").append("<option value='LIKE'>LIKE</option>");
		}
	}else if(searchClass == "SERVICE_ITEM"){
		$("div[id='propertySelect1']").show();
		$("div[id='propertySelect1'] select").attr("disabled", false);
		$("div[id='propertyValue2Inp']").show();
		$("div[id='propertyValue2Inp']>input").attr("disabled", false);
		$("#input2").show();
		$("#input1").val("").hide();
		$("#input3").val("").hide();
		$("#input4").val("").hide();
		$("#productPropertyValue1Sel").hide();
		$("#servicePropertyValue1Sel").hide();
		if($("#searchOperator").find("option[value='LIKE']").text()==""){
			$("#searchOperator").append("<option value='LIKE'>LIKE</option>");
		}
	}else if(searchClass == "ORDER_ITEM"){
		$("div[id='propertySelect4']").show();
		$("div[id='propertySelect4'] select").attr("disabled", false);
		$("div[id='propertyValue3Inp']").show();
		$("div[id='propertyValue3Inp']>input").attr("disabled", false);
		$("#input3").show();
		$("#input1").val("").hide();
		$("#input2").val("").hide();
		$("#input4").val("").hide();
		$("#productPropertyValue1Sel").hide();
		$("#servicePropertyValue1Sel").hide();
		$("#searchOperator option[value='LIKE']").remove();
	}else if(searchClass == "QUOTE_ITEM"){
		$("div[id='propertySelect3']").show();
		$("div[id='propertySelect3'] select").attr("disabled", false);
		$("div[id='propertyValue4Inp']").show();
		$("div[id='propertyValue4Inp']>input").attr("disabled", false);
		$("#input4").show();
		$("#input1").val("").hide();
		$("#input2").val("").hide();
		$("#input3").val("").hide();
		$("#productPropertyValue1Sel").hide();
		$("#servicePropertyValue1Sel").hide();
		$("#searchOperator option[value='LIKE']").remove();
	}else if(searchClass == "TEMPLATE_ITEM"){
		$("div[id='propertySelect2']").show();
		$("div[id='propertySelect2'] select").attr("disabled", false);
		$("#productPropertyValue1Sel").show();
		$("#productPropertyValue1Sel").attr("disabled", false);
		$("#servicePropertyValue1Sel").hide();
		$("#input3").val("").hide();
		$("#input1").val("").hide();
		$("#input2").val("").hide();
		$("#input4").val("").hide();
		$("#searchOperator option[value='LIKE']").remove();
	}else if(searchClass == "SERVICE_TEMPLATE_ITEM"){
		$("div[id='propertySelect2']").show();
		$("div[id='propertySelect2'] select").attr("disabled", false);
		$("#servicePropertyValue1Sel").show();
		$("#servicePropertyValue1Sel").attr("disabled", false);
		$("#productPropertyValue1Sel").hide();
		$("#input3").val("").hide();
		$("#input1").val("").hide();
		$("#input2").val("").hide();
		$("#input4").val("").hide();
		$("#searchOperator option[value='LIKE']").remove();
	}else{
		$("div[id='propertySelect1']").show();
		$("div[id='propertySelect1'] select").attr("disabled", false);
	}

	if(searchClass != "TEMPLATE_ITEM"){
		//$("#propertyValue1Inp").attr("value", "");
		$("#propertyValue1Inp").attr("style", "display:block;");
		$("#propertyValue1Inp").attr("disabled", false);
		
	}
	$("#searchOperator[value=''] option").attr("selected",true);
}
</script>
</head>
<body class="content" style="background-image:none;">
<form method="get" action="qu_order!showProductItemPriceForm.action" target="_self">
<s:if test="erpCheckStockException != null && erpCheckStockException != ''">
<script type="text/javascript">
var confirmStatus = window.confirm("${erpCheckStockException}\n Do you want to continue?");
if (confirmStatus == false) {
	if (window.parent.$('#itemLookupDialog')) {
		window.parent.$('#itemLookupDialog').dialog('close');
	}
}
</script>
</s:if>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<input type="hidden" name="searchType" value="search" />
	<input type="hidden" name="custNo" value="${custNo}" />
	<input type="hidden" name="quorderNo" value="${quorderNo}" />
	<input type="hidden" name="catalogNoList" value="${catalogNoList}" />
	<input type="hidden" name="codeType" value="${codeType}" />
  <tr>
    <td>&nbsp;</td>
    <th>Lookup On </th>
    <th>Condition</th>
    <th>Value</th>
    <th>&nbsp;</th>
  </tr>
  <tr>
    <td><select name="searchClass"  id='searchClass' onchange="changeSearchClass(this.value);">
		<s:iterator value="itemSearchTypes" >
			<option value="${key}" label="${value}" <c:if test="${searchClass == key}">selected="${value}"</c:if>>${value}</option>
		</s:iterator>
    </select></td>
    <td>
   <div id="propertySelect1" style='display:block'>
		<select name="propertyName" disabled="disabled">
		<option value="categoryName">Category Name</option>
		    <option value="catalogNo">Catalog No</option>
		    <option value="name">Name</option>
		</select>
   </div>
   <div id="propertySelect2" style='display:none'>
		<select name="propertyName" disabled="disabled">
      		<option value="name">Template Name</option>
        </select>
   </div>
   <div id="propertySelect3" style='display:none'>
		<select name="propertyName" disabled="disabled">
      		<option value="quoteNo">Quote No</option>
        </select>
   </div>
   <div id="propertySelect4" style='display:none'>
		<select name="propertyName" disabled="disabled">
      		<option value="orderNo">Order No</option>
        </select>
   </div>
   </td>
    <td>
	<select name="searchOperator" id="searchOperator">
	  <option value="LIKE">LIKE</option>
	  <option value="EQ">=</option>
	  <option value="GT">&gt;</option>
	  <option value="GE">&gt;=</option>
	  <option value="LT">&lt;</option>
	  <option value="LE">&lt;=</option>
	  <option value="NE">!=</option>
	</select>
    </td>
    <td>
      <select name="propertyValue1" id="productPropertyValue1Sel" style="display:none;" >
      	<s:iterator value="productTemplateList" >
      		<option value="${name}" label="${name}" <c:if test="${propertyValue1 == name}">selected="${name}"</c:if>>${name}</option>
      	</s:iterator>
      </select>
	<select name="propertyValue1" id="servicePropertyValue1Sel" style="display:none;" >
      	<s:iterator value="serviceTemplateList" >
      		<option value="${name}" label="${name}" <c:if test="${propertyValue1 == name}">selected="${name}"</c:if>>${name}</option>
      	</s:iterator>
      </select>
      <div id="propertyValue1Inp" style='display:none'>
      	<input name="propertyValue1" id="input1" type="text" class="NFText" size="20" value="${propertyValue1}" disabled="disabled"/>
      </div>
      <div id="propertyValue2Inp" style='display:none'>
      	<input name="propertyValue1" id="input2" type="text" class="NFText" size="20" value="${propertyValue1}" disabled="disabled"/>
      </div>
      <div id="propertyValue3Inp" style='display:none'>
      	<input name="propertyValue1" id="input3" type="text" class="NFText" size="20" value="${propertyValue1}" disabled="disabled"/>
      </div>
      <div id="propertyValue4Inp" style='display:none'>
      	<input name="propertyValue1" id="input4" type="text" class="NFText" size="20" value="${propertyValue1}" disabled="disabled"/>
      </div>
      
    </td>
    <td><input name="Submit3" type="submit" class="style_botton" value="Search" /></td>
  </tr>

</table>
</form>

  <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td colspan="4" style="padding-top:10px;">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
      <tr>
        <th width="28"> <input name="checkbox" name="itemAllCheck" id="itemAllCheck" type="checkbox"  /></th>
        <s:if test="searchClass == 'TEMPLATE_ITEM' || searchClass == 'ORDER_ITEM' || searchClass == 'QUOTE_ITEM'">
        <th width="55">Item No </th>
        </s:if>
        <th width="65">Ctlg No</th>
        <th>Name</th>
        <th width="45">Qty</th>
		<th width="60">Size</th>
        <th width="50">Standard Price</th>
        <th width="60">Special Price</th>
        <th width="40">Quantity in stock</th>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="4" style="padding-bottom:20px; "><div  style="width:100%; height:100%;">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" id="productList">
		<s:iterator value="itemBeanDTOList">
        <tr>
          <td  width="28">
          	<div align="center">
              &nbsp;<input type="checkbox" value="<c:choose><c:when test="${empty serviceId}">${productId}</c:when><c:otherwise>${serviceId}</c:otherwise></c:choose>@@@${name}@@@${catalogNo}" name="itemAdd<c:choose><c:when test="${empty serviceId}">${productId}</c:when><c:otherwise>${serviceId}</c:otherwise></c:choose>"/>
		        <input type="hidden" id="uom" value="${uom}" />
		        <input type="hidden" id="qtyUom" value="${qtyUom}" />
		        <input type="hidden" id="discount" value="" />
		        <input type="hidden" id="tax" value="${tax}" />
		        <input id="unitPrice" name="unitPrice" type="hidden" value="${unitPrice}" />
		        <input id="catalogPrice" name="catalogPrice" type="hidden" value="${catalogPrice}" />
		        <input id="specialPrice" name="specialPrice" type="hidden" value="${specialPrice}" />
		        <input id="upSymbol" name="upSymbol" type="hidden" value="${upSymbol}" />
		        <input id="cpSymbol" name="cpSymbol" type="hidden" value="${cpSymbol}" />
		        <input id="spSymbol" name="spSymbol" type="hidden" value="${spSymbol}" />
		        <input id="upPrecision" name="upPrecision" type="hidden" value="${upPrecision}" />
		        <input id="cpPrecision" name="cpPrecision" type="hidden" value="${cpPrecision}" />
		        <input id="spPrecision" name="spPrecision" type="hidden" value="${spPrecision}" />
		        <input id="mainProductId" name="mainProductId" type="hidden" value="<c:choose><c:when test="${empty serviceId}">${productId}</c:when><c:otherwise>${serviceId}</c:otherwise></c:choose>" />
				<input id="srcQuoteNo" name="srcQuoteNo" type="hidden" value="${srcQuoteNo}" />
				<input id="srcOrderNo" name="srcOrderNo" type="hidden" value="${srcOrderNo}" />
				<input id="srcItemNo" name="srcItemNo" type="hidden" value="${itemNo}" />
				<input id="templateType" name="templateType" type="hidden" value="${templateType}" />
          </div></td>
        <s:if test="searchClass == 'TEMPLATE_ITEM' || searchClass == 'ORDER_ITEM' || searchClass == 'QUOTE_ITEM'">
          <td  width="55"><div align="center" id="tdItemNo" name="tdItemNo">&nbsp;${itemNo}</div></td>
        </s:if>
          <td  width="65"><div name="tdCatalogNo" id="tdCatalogNo">&nbsp;${catalogNo}</div></td>
         <s:if test="searchClass == 'TEMPLATE_ITEM'">
          <td><div name="tdName" id="tdName" title="${productName}">&nbsp;<s:if test="%{productName.length()>40}">
						<s:property value="productName.substring(0,40)+'...'"/>
					</s:if>
					<s:else><s:property value="productName"/></s:else></div></td>
          </s:if>
		  <s:if test="searchClass == 'SERVICE_TEMPLATE_ITEM'">
          <td><div name="tdName" id="tdName" title="${serviceName}">&nbsp;<s:if test="%{serviceName.length()>40}">
						<s:property value="serviceName.substring(0,40)+'...'"/>
					</s:if>
					<s:else><s:property value="serviceName"/></s:else></div></td>
          </s:if>
          <s:if test="searchClass != 'TEMPLATE_ITEM' && searchClass != 'SERVICE_TEMPLATE_ITEM'">
          <td><div name="tdName" id="tdName" title="${name}" title2="<s:if test="%{name.length()>40}"><s:property value="name.substring(0,40)+'...'"/></s:if><s:else>${name }</s:else>" >&nbsp;<s:if test="%{name.length()>60}"><s:property value="name.substring(0,60)+'...'"/></s:if><s:else>${name}</s:else></div></td>
          </s:if>
          <td width="45">&nbsp;<input name="quantity" id="quantity" type="text" class="NFText" value="${quantity}" size="1"  onkeyup="this.value=this.value.replace(/[^0-9]/,'');"/> </td>
          <td width="60"><div name="tdSizeUom" id="tdSizeUom">&nbsp;${size} ${uom}</div></td>
          <td width="50"><div name="tdSymbolStandardPrice" id="tdSymbolStandardPrice">&nbsp;<c:if test="${! empty unitPrice}">${upSymbol}${unitPrice}</c:if></div></td>
          <td width="60"><c:if test="${! empty specialPrice}"><div id="viewSpePrice" align="right" style="cursor:pointer;color:#0000FF" mce_style="cursor: pointer">&nbsp;${spSymbol}${specialPrice}</div></c:if></td>
          <td width="44"><c:if test="${! empty unitInStock}"><div id="viewunitInStock" align="right" >&nbsp;${unitInStock}</div></c:if></td>
          <s:if test="searchClass == 'SERVICE_ITEM'">
          	<input id="productInfo" name="productInfo" type="hidden" value="name:${name},type:${type},taxStatus:${taxStatus},scheduleShip:${scheduleShip},fullDesc:${fullDesc},comment:${comment},clsId:${clsId},clsName:${clsName},sellingNote:${sellingNote},catalogId:${catalogId},catalogName:${catalogName}" />
          </s:if>
          <s:else>
          <input id="productInfo" name="productInfo" type="hidden" value="name:${name},type:${type},taxStatus:${taxStatus},scheduleShip:${scheduleShip},fullDesc:${fullDesc},comment:${comment},clsId:${clsId},clsName:${clsName},sellingNote:${sellingNote},catalogId:${upCatalogId},catalogName:${upCatalogName}" />
          </s:else>
        </tr>
        <input id="catalogNoTd" name="catalogNoTd" type="hidden" value="${catalogNo}" />
		</s:iterator>
      </table>
    </div>
	<div class="grayr">
		<jsp:include page="/common/db_pager.jsp">
					<jsp:param value="${ctx}/qu_order!showProductItemPriceForm.action"
						name="moduleURL" />
		</jsp:include>
	</div>
	</td>
  </tr>
  <tr style="display: none;" id="serviceSplitTr">
  	<td colspan="4">
  	<div align="left">
  		<input type="checkbox" id="serviceSplit" checked="checked" />
  		Split the item quantities into multiple lines and each line includes one unit only. 
  	</div>
  	</td>
  </tr>
  <tr>
    <td colspan="4">
      <div align="center">
      	<input name="itemSelect" type="button" id="itemSelect" class="style_botton"  value="Select" />
        <input type="button" name="closeDialog" id="closeDialog" value="Cancel" class="style_botton" />
      </div>
    </td>
  </tr>
</table>
<script>
var selStr = "${searchClass}";
if (selStr == "") {
	selStr = "SERVICE_ITEM";
}
init("${searchClass}");
changeSearchClass(selStr);
$("#searchOperator").find("option[value='${searchOperator}']").attr("selected", true);
$("[name='propertyName']").find("option[value='${propertyName}']").attr("selected", true);
</script>
</body>
</html>