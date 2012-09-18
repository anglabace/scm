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

<%--<s:if test="codeType == 'order'"><s:include value="../order/order_config.jsp"></s:include></s:if>--%>
<%--<s:else><s:include value="../quote/quote_config.jsp"></s:include></s:else>--%>

<script>
var quorderNo = "${quorderNo}" ;
</script>
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
<form method="post" action="warehousing_shipment!showProductItemPrice.action" target="_self">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<input type="hidden" name="searchType" value="search" />
	<input type="hidden" name="custNo" value="${custNo}" />
	<input type="hidden" name="quorderNo" value="${quorderNo}" />
	<input type="hidden" id="catalogNoList" name="catalogNoList" value="${catalogNoList}" />
	<input type="hidden" name="codeType" value="${codeType}" />
  <tr>
    <td>&nbsp;</td>
    <th>Lookup On </th>
    <th>Condition</th>
    <th>Value</th>
    <th>&nbsp;</th>
  </tr>
  <tr>
    <td>
        <%--<select name="searchClass"  id='searchClass' onchange="changeSearchClass(this.value);">
		<s:iterator value="itemSearchTypes" >
			<option value="${key}" label="${value}" <c:if test="${searchClass == key}">selected="${value}"</c:if>>${value}</option>
		</s:iterator>
    </select>--%>
      <s:select list="#{'SERVICE_ITEM':'Service Item'}" headerValue="Product Item" headerKey="PRODUCT_ITEM" onchange="changeSearchClass(this.value);"
      name="searchClass" id="searchClass"/>
    </td>
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
        <th width="28"> <input name="checkbox" name="itemAllCheck" id="itemAllCheck" type="checkbox"/></th>
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
              &nbsp;<input type="checkbox" value="${catalogNo}" name="itemAdd"/>
		        <input name="unitPrice" id="unitPrice${catalogNo}" type="hidden" value="${unitPrice}" />
		        <input name="clsId" id="clsId${catalogNo}" type="hidden" value="${clsId}" />
          </div></td>
          <td  width="65"><div name="tdCatalogNo" id="tdCatalogNo">&nbsp;${catalogNo}</div></td>
            <td>
                <div name="tdName" id="tdName" title="${name}"
                     title2="<s:if test="%{name.length()>40}"><s:property value="name.substring(0,40)+'...'"/></s:if><s:else>${name }</s:else>">
                    &nbsp;<s:if test="%{name.length()>60}">
                    <s:property value="name.substring(0,60)+'...'"/>
                </s:if><s:else>${name}</s:else></div>
            </td>
          <td width="45">&nbsp;1 </td>
          <td width="60"><div name="tdSizeUom" id="tdSizeUom">&nbsp;${size} ${uom}</div></td>
          <td width="50"><div name="tdSymbolStandardPrice" id="tdSymbolStandardPrice">&nbsp;<c:if test="${! empty unitPrice}">${upSymbol}${unitPrice}</c:if></div></td>
          <td width="60"><c:if test="${! empty specialPrice}"><div align="right" style="cursor:pointer;color:#0000FF" mce_style="cursor: pointer">&nbsp;${spSymbol}${specialPrice}</div></c:if></td>
          <td width="44"><c:if test="${! empty unitInStock}"><div align="right" >&nbsp;${unitInStock}</div></c:if></td>
          <s:if test="searchClass == 'SERVICE_ITEM'">
          	<input name="productInfo" type="hidden" value="name:${name},type:${type},taxStatus:${taxStatus},scheduleShip:${scheduleShip},fullDesc:${fullDesc},comment:${comment},clsId:${clsId},clsName:${clsName},sellingNote:${sellingNote},catalogId:${catalogId},catalogName:${catalogName}" />
          </s:if>
          <s:else>
          <input name="productInfo" type="hidden" value="name:${name},type:${type},taxStatus:${taxStatus},scheduleShip:${scheduleShip},fullDesc:${fullDesc},comment:${comment},clsId:${clsId},clsName:${clsName},sellingNote:${sellingNote},catalogId:${upCatalogId},catalogName:${upCatalogName}" />
          </s:else>
        </tr>
        <input name="catalogNoTd" type="hidden" value="${catalogNo}" />
		</s:iterator>
      </table>
    </div>
	<div class="grayr">
		<jsp:include page="/common/db_pager.jsp">
					<jsp:param value="${ctx}/warehousing_shipment!showProductItemPrice.action"
						name="moduleURL" />
		</jsp:include>
	</div>
	</td>
  </tr>
  <%--<tr style="display: none;" id="serviceSplitTr">
  	<td colspan="4">
  	<div align="left">
  		<input type="checkbox" id="serviceSplit" checked="checked" />
  		Split the item quantities into multiple lines and each line includes one unit only.
  	</div>
  	</td>
  </tr>--%>
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
    $('#itemAllCheck').click(
                            function() {
                                if ($(this).attr('checked') === true) {
                                    $(':check [name="itemAdd"]').attr('checked', true);
                                }
                                else {
                                    $(':check [name="itemAdd"]').attr('checked', false);
                                    $(this).attr('checked', false);
                                }
                            }
            );
var selectData = new Array();
var selStr = "${searchClass}";
if (selStr == "") {
	selStr = "SERVICE_ITEM";
}
init("${searchClass}");
changeSearchClass(selStr);
$("#searchOperator").find("option[value='${searchOperator}']").attr("selected", true);
$("[name='propertyName']").find("option[value='${propertyName}']").attr("selected", true);

$('#closeDialog').click(function(){
				var itemSelectDialogObj = window.parent.$('#itemLookupDialog') ;
				itemSelectDialogObj.dialog( 'close' ) ;
			}
		);

$('#itemSelect').click(function() {
    var itemSelectCheckBoxs = $(":checkbox[name*='itemAdd'][checked=true]");
//                alert(itemSelectCheckBoxs.size())
    if (itemSelectCheckBoxs.size() != 0) {
        var catalogIds = "";
        itemSelectCheckBoxs.each(function() {
            catalogIds += "'" + $(this).val() + "'" + ","
        });
        $("#catalogNoList").attr("value", catalogIds.substring(0, catalogIds.length - 1));
        $.ajax({
            type:"get",
            url:"warehousing_shipment!getPSData.action?catalogNoList=" + $("#catalogNoList").val() + "&searchClass=" + $("#searchClass").val(),
            dataType:"json",
            success:function(data) {
                if (data == "None") {
                    alert("System error! Please contact system administrator for help.");
                } else {
                    for (var i = 0; i < data.length; i++) {
                        selectData[i] = {
                            catalogNo : data[i].catalogNo,
                            price : $("#unitPrice" + data[i].catalogNo).val(),
                            clsId : data[i].clsId
                        };
                    }
                    parent.setReturnValue(selectData);
                    var itemSelectDialogObj = window.parent.$('#new_ps_dlg');
                    itemSelectDialogObj.dialog('close');

                }
            },
            error:function(data) {
            },
            async:false
        });
    } else {
        alert('Please select items!');
    }
}
        )
</script>
</body>
</html>