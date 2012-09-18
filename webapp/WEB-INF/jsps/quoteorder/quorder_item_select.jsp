<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/common/taglib.jsp"%>
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript" src="${global_js_url}TabbedPanels.js"></script>
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript" src="${global_js_url}jquery/jquery.js"></script>
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>	
<s:if test="codeType == 'order'"><s:include value="../order/order_config.jsp"></s:include></s:if>
<s:else><s:include value="../quote/quote_config.jsp"></s:include></s:else>
<script>
var quorderNo = "${quorderNo}" ;
var codeType = "${codeType}" ;
</script>
<script src="${global_js_url}quoteorder/order_quotation_add.js" type="text/javascript"></script>
<script type="text/javascript" src="${global_js_url}scm/gs.util.js"></script>
</head>
<body class="content">
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
  <tr>
    <th width="28">
    <input name="checkbox"   type="checkbox" name="itemAllCheck" id="itemAllCheck"  /></th>
    <th width="50">Ctlg  No</th>
    <th> Name </th>
	<th width="85">Selling Type </th>
    <th width="45">Qty</th>
    <th width="60">Size</th>
    <th width="60">Standard Price</th>
    <th width="50">Special Price</th>
  </tr>
</table>
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" id="relationProductList">
  	<input id="mainProductId" name="mainProductId" type="hidden" value="${mainProductId}" />
  <tbody>
  <s:iterator value="itemList">
    <tr>
      <td  width="28"><div align="center">
        <input type="checkbox" value="${relateInfo}" name="itemAdd${productId}"/>
        <input type="hidden" id="uom" value="${uom}" />
		<input type="hidden" id="qtyUom" value="${qtyUom}" />
        <input type="hidden" id="discount" value=""/>
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
        <input id="productId" name="productId" type="hidden" value="${productId}" />
        <input id="parentId" name="parentId" type="hidden" value="${parentId}" />
		<input id="srcItemNo" name="srcItemNo" type="hidden" value="${itemNo}" />
      </div></td>
      <td  width="50"><div name="tdCatalogNo" id="tdCatalogNo">&nbsp;${catalogNo}</div></td>
      <td><div name="tdName" id="tdName" title="${itemName}" title2="<s:if test="%{itemName.length()>40}"><s:property value="itemName.substring(0,40)+'...'"/></s:if><s:else>${itemName }</s:else>">&nbsp;<s:if test="%{itemName.length()>40}"><s:property value="itemName.substring(0,40)+'...'"/></s:if><s:else>${itemName}</s:else></div></td>
      <td width="85"><div name="tdRelationType" id="tdRelationType">&nbsp;${relationType}</div></td>
      <td width="45"><input name="quantity" id="quantity" type="text" class="NFText" value="${quantity}" style="width:20px"  onkeyup="this.value=this.value.replace(/[^0-9]/,'');"/></td>
      <td width="60"><div name="tdSizeUom" id="tdSizeUom">&nbsp;${size} ${uom}</div></td>
      <td width="60"><div name="tdSymbolStandardPrice" id="tdSymbolStandardPrice">&nbsp;<c:if test="${! empty unitPrice}">${upSymbol}${unitPrice}</c:if></div></td>
      <td width="50"><div id="viewSpePrice" align="right" style="cursor:pointer;color:#0000FF" mce_style="cursor: pointer"><c:if test="${! empty specialPrice}">${spSymbol}${specialPrice}</c:if></div></td>
        <input id="productInfo" name="productInfo" type="hidden" value="name:${itemName},type:${type},taxStatus:${taxStatus},scheduleShip:${scheduleShip},fullDesc:${fullDesc} ,comment:${comment} ,clsId:${clsId} ,clsName:${clsName} ,sellingNote:${sellingNote} ,catalogId:${catalogId} ,catalogName: ${itemName} " />
    </tr>
       <input id="catalogNoTd" name="catalogNoTd" type="hidden" value="${catalogNo}" />
    </s:iterator>
    </tbody>
  </table>
<br/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <th width="82">Selling Notes </th>
    <td width="518">&nbsp;</td>
  </tr>
  <tr>
    <th width="82" valign="top">&nbsp;</th>
    <td>
    	<textarea name="sellingNotes" id="sellingNotes" class="content_textarea" style="width:480px;" readonly></textarea>
    </td>
  </tr>
</table>
<div align="center"><br />
    <input type="submit" name="Submit22" id="addItem" value="Add" class="style_botton" />
    <input  type="button" name="close2" id="closeRelationDialog" value="Cancel" class="style_botton"/>
</div>
</body>
</html>