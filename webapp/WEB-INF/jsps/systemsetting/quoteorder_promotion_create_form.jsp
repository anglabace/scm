<!-- {get_spec_selects value="COUNTRY_NAME,CUSTOMER_ROLE,ORIGINAL_SOURCE,SHIP_METHOD,TERRITORY,CATEGORY,RFM_VALUE"} -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Add/Update Promotion</title>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}show_tag.js"></script>

<script src="${global_js_url}jquery/ui/ui.datepicker.js"
	type="text/javascript"></script>
<script
	src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.form.js"
	language="javascript" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js"
	language="javascript" type="text/javascript"></script>

<script language="javascript" type="text/javascript"
	src="${global_js_url}scm/gs.util.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}scm/setting_quoteorder.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}scm/setting_quoteorder_promo.js"></script>
<script src="${global_js_url}scm/Date.js" type="text/javascript"></script>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet"
	type="text/css" />
<link type="text/css"
	href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script language="javascript" type="text/javascript">

$(document).ready(function(){
	<s:if test="operation_method=='view'">
		$("#promotion_add_button").attr("disabled",true);
	</s:if>
    $('.ui-datepicker').each(function(){
		$(this).datepicker(
				{
					dateFormat: 'yy-mm-dd',
					changeMonth: true,
					changeYear: true
				});
	});

    parent.$('#customer_search_dlg').dialog({
						autoOpen: false,
						height: 500,
						width: 660,
						modal: true,
						bgiframe: true,
						buttons: {
						}
					});
    parent.$('#selectCategoryDialog').dialog({
		autoOpen: false,
		height: 480,
		width: 360,
		modal: true,
		bgiframe: true,
		buttons: {}
	}); 
	parent.$('#giftCatalogDialog').dialog({
		autoOpen: false,
		height: 480,
		width: 360,
		modal: true,
		bgiframe: true,
		buttons: {}
	}); 
	parent.$('#categoryCatalogDialog').dialog({
		autoOpen: false,
		height: 480,
		width: 400,
		modal: true,
		bgiframe: true,
		buttons: {}
	});
	// validate signup form on keyup and submit
	$("#promotion_form").validate({ 
		invalidHandler: function(form, validator) { 
			$.each(validator.invalid, function(key,value){ 
				alert(value); 
				$("#"+key).focus(); 
				return false; 
			}); 
		}, 
		rules: { 
			prmtCode: { required:true }, 
			description: { required:true }, 
			orderEffFrom: { required:true }, 
			orderEffTo: { required:true } 
		}, 
		messages: { 
			prmtCode: "Please enter promotion code", 
			description:  "Please enter description",
			orderEffFrom: "Please enter start date", 
			orderEffTo:  "Please enter end date"
		}, 
		errorClass:"validate_error",
		highlight: function(element, errorClass) {
		$(element).addClass(errorClass);
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).removeClass(errorClass);
		},
		errorPlacement: function(error, element) {

		}
	});	
	
	
});

function selectCatalogDlg(){
	parent.$('#giftCatalogDialog').dialog("option", "open",function(){
		var htmlStr = '<iframe src="${ctx}/basedata/gift_catalog_picker.action" height="400" width="320" scrolling="no" style="border:0px" frameborder="0"></iframe>';
		parent.$('#giftCatalogDialog').html(htmlStr);
	});	
	parent.$('#giftCatalogDialog').dialog('open');	
}
function selectCategoryDlg(s){
	parent.$('#selectCategoryDialog').dialog("option", "open",function(){
		var htmlStr = '<iframe src="${ctx}/basedata/category_picker!index.action?type='+s+'" height="400" width="340" scrolling="no" style="border:0px" frameborder="0"></iframe>';
		parent.$('#selectCategoryDialog').html(htmlStr);
	});	
	parent.$('#selectCategoryDialog').dialog('open');	
}
function selectCustomerDlg(){
	parent.$('#customer_search_dlg').dialog("option", "open",function(){
		var htmlStr = '<iframe src="${ctx}/basedata/customer_picker.action" height="450" width="620" scrolling="no" style="border:0px" frameborder="0"></iframe>';
		parent.$('#customer_search_dlg').html(htmlStr);
	});	
	parent.$('#customer_search_dlg').dialog('open');	
}
function selectCategoryCatalogDlg(){
	categoryType = document.getElementById('orderItemCategoryType').value;
	categoryNo = document.getElementById('orderItemCategory').value;
	//alert(categoryNo);
	if(categoryType==null)
		return;
	else if(categoryType == 'PRODUCT'){
		parent.$('#categoryCatalogDialog').dialog("option", "open",function(){
		var htmlStr = '<iframe src="${ctx}/basedata/category_picker!getProductCatalogNoList.action?categoryNo='+categoryNo+'" height="400" width="360" scrolling="no" style="border:0px" frameborder="0"></iframe>';
		parent.$('#categoryCatalogDialog').html(htmlStr);
	});
	}else if(categoryType == 'SERVICE'){
		parent.$('#categoryCatalogDialog').dialog("option", "open",function(){
		var htmlStr = '<iframe src="${ctx}/basedata/category_picker!getServiceCatalogNoList.action?categoryNo='+categoryNo+'" height="400" width="360" scrolling="no" style="border:0px" frameborder="0"></iframe>';
		parent.$('#categoryCatalogDialog').html(htmlStr);
	});
	}	
	parent.$('#categoryCatalogDialog').dialog('open');	
}
</script>

<style type="text/css">
<!--
body {
	margin: 0px 0px 10px 6px;
	width: 720px;
}

.General_table .invoice_title {
	padding: 6px 0px;
	margin: 0px;
	font-weight: bold;
	padding-left: 5px;
}

.General_table .Indent {
	padding-left: 15px;
}

.General_table  .General_table {
	margin: 6px 0px;
}
-->
</style>
</head>

<body>

<form id="promotion_form"><input type="hidden" id="id" name="id"
	value="${id}" /> <input type="hidden" id="opType" name="opType"
	value="${opType}" />
		<input type="hidden" id="discAmountPercentType" name="discAmountPercentType"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0"
	class="General_table">
	<tr>
		<th width="22%"><span class="important">*</span>Code</th>
		<td width="28%"><input id="prmtCode" name="prmtCode" type="text"
			class="NFText" <c:if test="${opType == 'update'}">readonly</c:if>
			size="30" value="${prmtCode}"/></td>
		<th width="22%"><span class="important">*</span>Description</th>
		<td width="28%"><input id="description" name="description"
			type="text" class="NFText" size="30" value="${description}" /></td>
	</tr>
	<tr>
		<th>Apply this promotion</th>
		<td><s:select id="applyType" name="applyType"
			list="dropDownList['PROMOTION_APPLY_TYPE']" listKey="value"
			listValue="value" 
			cssStyle="width:240px" value="applyType"></s:select></td>
		<th>Source Key</th>
		<td><s:select cssStyle="width:183px"
			name="orderSource"
			list="specDropDownList['ORIGINAL_SOURCE'].dropDownDTOs" listKey="id"
			listValue="name" headerKey="" headerValue="Select Source"
			value="orderSource"></s:select></td>
	</tr>

	<tr>
		<th>&nbsp;</th>
		<td colspan="3"><s:checkbox id="cumulateFlag" name="cumulateFlag"
			value='cumulateFlag == "Y"' fieldValue="Y"></s:checkbox> Apply this
		promotion in addition to order discount</td>
	</tr>
</table>
<div class="invoice_title"><img style="cursor: pointer;"
	src="${global_image_url}/ad.gif" width="11" height="11"
	onclick="toggleShowMore_img(this, 'Price');" /> Pricing</div>

<div id="Price" style="display: block;">
<table width="100%" border="0" cellpadding="0" cellspacing="0"
	class="General_table">
	<tr>
		<td width="4%"><s:checkbox title="discType" name=""
			value='discType != null && discType.substring(0, 1) == "1"' onclick="clear4t1(this)"></s:checkbox>
		</td>
		<td width="96%">&nbsp;&nbsp; <input id="discPercent"
			name="discPercent" type="text" class="NFText2" value="${discPercent}"
			size="5" onblur="check_number(this)" /> % discount to all products
		and services for orders totaling over $ <input id="orderTotalMin1"
			name="orderTotalMin1" type="text" class="NFText2"
			value="${orderTotalMin1}" size="5" onblur="check_number(this)" /></td>
	</tr>
	<tr>
		<td><s:checkbox title="discType" name=""
			value='discType != null && discType.substring(2, 3) == "1"' onclick="clear4t2(this)"></s:checkbox></td>
		<td>$ <input id="discAmount" name="discAmount" type="text"
			class="NFText2" value="${discAmount}" size="5"
			onblur="check_number(this)" /> off orders over $ <input
			id="orderTotalMin2" name="orderTotalMin2" type="text" class="NFText2"
			value="${orderTotalMin2}" size="5" onblur="check_number(this)" /></td>
	</tr>
	<tr>
		<td><s:checkbox title="discType" name=""
			value='discType != null && discType.substring(4, 5) == "1"' onclick="clear4t3(this)"></s:checkbox></td>
		<td>Automatically add <input id="discProdQty" name="discProdQty"
			type="text" class="NFText2" size="5" value="${discProdQty}"
			onblur="check_number(this)" /> qty of product or service <input
			id="discProd" name="discProd" type="text" class="NFText2" size="5"
			value="${discProd}" readonly="readonly"/><a href="javascript:void(0);"
			onclick="selectCatalogDlg();"><img src="images/search.gif" id="discCatalogImg"
			height="16" width="16" /></a> into the order</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>when minimum order of $ <input id="orderTotalMin3"
			name="orderTotalMin3" type="text" class="NFText2"
			value="${orderTotalMin3}" size="5" onblur="check_number(this)" /></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>Product or services notation <input id="discNotation"
			name="discNotation" type="text" class="NFText" size="40"
			value="${discNotation}" /></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td><label> <input id="chk_special_sell" type="checkbox"
			<c:if test="${specialSell == 'Y'}">checked</c:if> /> Apply a special
		selling price to this product or services </label></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>
		<table width="365" border="0" cellpadding="0" cellspacing="0"
			style="margin-left: 15px;">
			<tr>
				<td width="150"><input type="radio" name="rdo_specical_sell"
					<c:if test="${! empty discPrice}">checked</c:if>
					<c:if test="${specialSell == 'N'}">disabled</c:if> /> Sell at $ <input
					id="discPrice" name="discPrice" type="text" class="NFText2"
					size="5" value="${discPrice}"
					<c:if test="${specialSell == 'N'}">disabled</c:if>
					onblur="check_number(this)"
					onchange="toggle_special(this, 'price')" /></td>
				<td><input type="radio" name="rdo_specical_sell"
					<c:if test="${! empty specialDiscPercent}">checked</c:if>
					<c:if test="${specialSell == 'N'}">disabled</c:if> /> <input
					id="specialDiscPercent" name="specialDiscPercent" type="text"
					class="NFText2" size="5"
					value="<c:if test="${discType == '3'}">${discPercent}</c:if>"
					<c:if test="${specialSell == 'N'}">disabled</c:if>
					onblur="check_number(this)"
					onchange="toggle_special(this, 'percent')" /> % discount</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>

		<td><s:checkbox title="discType" name=""
			value='discType != null && discType.substring(6, 7) == "1"' onclick="clear4t4(this)"></s:checkbox></td>
		<td><input name="rdo_button" type="radio"
			<c:if test="${! empty discCatePercent}">checked</c:if>
			<c:if test="discType != null && discType.substring(6, 7) == '0'">disabled</c:if> /><input
			name="discCatePercent" id="discCatePercent" class="NFText2"
			value="${discCatePercent}" size="5" type="text" onblur="check_number(this)" /> % discount to
		category <input name="discCategory" id="discCategory1" class="NFText2"
			size="5"
			value="<c:if test="${! empty discCatePercent}">${discCategory}</c:if>"
			type="text" readonly="readonly"></input> <a href="javascript:void(0);" id="discCategoryImg1"
			onclick="selectCategoryDlg('1');"><img src="images/search.gif" 
			height="16" width="16" /></a> orders totaling over $ <input
			name="discOrderTotal" id="discOrderTotal1" class="NFText2"
			value="<c:if test="${! empty discCatePercent}">${discOrderTotal}</c:if>"
			<c:if test="discType != null && discType.substring(6, 7) == '0'">disabled</c:if> size="5"
			type="text" onblur="check_number(this)"></input></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td><input name="rdo_button" type="radio"
			<c:if test="${! empty discCateAmount}">checked</c:if>
			<c:if test="discType != null && discType.substring(6, 7) == '0'">disabled</c:if> />$ <input
			name="discCateAmount" id="discCateAmount" class="NFText2"
			value="${discCateAmount}" size="5" type="text" onblur="check_number(this)"/> off orders over $ <input
			name="discOrderTotal" id="discOrderTotal2" class="NFText2"
			value="<c:if test="${! empty discCateAmount}">${discOrderTotal}</c:if>"
			size="5" type="text" onblur="check_number(this)"/> to category <input name="discCategory"
			id="discCategory2" class="NFText2" size="5"
			value="<c:if test="${! empty discCateAmount}">${discCategory}</c:if>"
			type="text" readonly="readonly"/><a href="javascript:void(0);" id="discCategoryImg2"
			onclick="selectCategoryDlg('2');"><img src="images/search.gif" 
			height="16" width="16" /></a><input type="hidden" id="discCategoryType" name="discCategoryType" value="${discCategoryType}"></input></td>
	</tr>
</table>
</div>
<div class="invoice_title"><img src="${global_image_url}ar.gif"
	onclick="toggleShowMore_img(this, 'Customer');" width="11" height="11"
	style="cursor: pointer;" /> Customer</div>
<table width="100%" border="0" cellpadding="0" cellspacing="0"
	class="General_table" id="Customer" style="display: none;">
	<tr>
		<th width="22%">Customer No</th>
		<td width="28%"><input id="custNo" name="custNo" type="text"
			class="NFText" size="15" value="<c:if test="${! empty custNo}">${custNo}</c:if>"
			onblur="check_number(this)" /><a href="javascript:void(0);" id="customerPickTrriger"
			onclick="selectCustomerDlg();"><img src="images/search.gif" 
			height="16" width="16" /></a></td>
		<th width="22%">RFM Value</th>
		<td width="28%"><s:select cssStyle="width:133px" name="rfmValue"
			list="specDropDownList['RFM_VALUE'].dropDownDTOs" listKey="id"
			listValue="name" headerKey="" headerValue="Select RFM Value"
			value="rfmValue"></s:select></td>
		</tr>

			<tr>
				<th width="22%">Priority Level</th>
				<td width="28%"><s:select id="custPriorityLvl"
					name="custPriorityLvl" list="dropDownList['CUST_PRIORITY_LEVEL']"
					listKey="value" listValue="value" headerKey=""
					headerValue="Select Level" cssStyle="width:133px"
					value="custPriorityLvl"></s:select>
				<th width="22%">Role</th>
				<td width="28%"><s:select cssStyle="width:133px"
					name="custJobRole"
					list="specDropDownList['CUSTOMER_ROLE'].dropDownDTOs" listKey="id"
					listValue="name" headerKey="" headerValue="Select Role"
					value="custJobRole"></s:select></td>
				</tr>
				<tr>
					<th>Country</th>

					<td><s:select cssStyle="width:133px" name="custCountry"
						list="specDropDownList['COUNTRY_NAME'].dropDownDTOs" listKey="id"
						listValue="name" headerKey="" headerValue="Select Country"
						value="custCountry"></s:select></td>
					<th>Territory</th>
					<td><s:select cssStyle="width:133px" name="custSalesTerritory"
						list="specDropDownList['TERRITORY'].dropDownDTOs" listKey="id"
						listValue="name" headerKey="" headerValue="Select Territory"
						value="custSalesTerritory"></s:select></td>
				</tr>
				<tr>
          <td>&nbsp;</td>
          <td colspan="2"><input type="checkbox" name="checkbox2" value="checkbox" disabled="disabled"/>
            Send Promotion Code To Customer</td>
          <td>&nbsp;</td>
    
          </tr>
				
</table>
<div class="invoice_title"><img
	onclick="toggleShowMore_img(this, 'Order');"
	src="${global_image_url}/ad.gif" width="11" height="11"
	style="cursor: pointer;" /> Order & Quote</div>
<table width="100%" border="0" cellpadding="0" cellspacing="0"
	class="General_table" id="Order" style="display: block;">
	<tr>
		<th>Category</th>
		<td><input id="orderItemCategory" name="orderItemCategory" type="text"
			class="NFText" size="15" value="${orderItemCategory}" readonly="readonly"/> <a href="javascript:void(0);" id="discCategoryImg1"
			onclick="selectCategoryDlg('3');"><img src="images/search.gif" 
			height="16" width="16" /></a><input type="hidden" id="orderItemCategoryType" name="orderItemCategoryType" value="${orderItemCategoryType}"></input></td>
		<th width="22%">Catalog No</th>
		<td width="28%"><input id="orderCatalogNo" name="orderCatalogNo"
			type="text" class="NFText" size="15" value="${orderCatalogNo}" readonly="readonly"/><a href="javascript:void(0);" id="discCatalogImg1"
			onclick="selectCategoryCatalogDlg();"><img src="images/search.gif" 
			height="16" width="16" /></a></td>
		
	</tr>
	<tr>
		<th><span class="important">*</span>Start Date</th>
		<td><input id="orderEffFrom" name="orderEffFrom" type="text"
			class="ui-datepicker" style="width: 125px;"
			value="<s:date name="orderEffFrom" format="yyyy-MM-dd"/>" size="18" /></td>
		<th><span class="important">*</span>End Date</th>
		<td><input id="orderEffTo" name="orderEffTo" type="text"
			class="ui-datepicker" style="width: 125px;"
			value="<s:date name="orderEffTo" format="yyyy-MM-dd"/>" size="18" /></td>
	</tr>
	<tr>
		<th colspan="4">
		<div align="left">Optional Message to appear on the Invoice</div>
		</th>
	</tr>
	<tr>
		<th>&nbsp;</th>
		<td colspan="3"><input id="invoiceMsg" name="invoiceMsg"
			type="text" class="NFText" size="65" value="${invoiceMsg}" /></td>
	</tr>
</table>

<div class="invoice_title"><img style="cursor: pointer;"
	onclick="toggleShowMore_img(this, 'Shipping');"
	src="${global_image_url}/ar.gif" width="11" height="11" /> Shipping</div>

<table width="100%" border="0" cellpadding="0" cellspacing="0"
	class="General_table" id="Shipping" style="display: none;">
	<tr>
		<td width="4%" valign="top"><input type="checkbox"
			name="shipDiscFlag" id="shipDiscFlag" value="Y"
			<c:if test="${shipDiscFlag == 'Y'}">checked</c:if> /></td>
		<td width="96%">Charge a flat amount of $ <input
			name="shipAmount" id="shipAmount"
			<c:if test="${shipDiscFlag != 'Y'}">disabled</c:if> type="text"
			class="NFText" size="20" value="${shipAmount}"
			onblur="check_number(this)" /> using <s:select
			cssStyle="width:110px" name="shipMethod"
			list="specDropDownList['SHIP_METHOD'].dropDownDTOs" listKey="id"
			listValue="name" headerKey="" headerValue="Select Shipping"
			value="shipMethod"></s:select> as a shipping method, on order <br />
		over $ <input name="shipOrderTotal" id="shipOrderTotal"
			<c:if test="${shipDiscFlag != 'Y'}">disabled</c:if> type="text"
			class="NFText" size="20" style="margin-top: 2px;"
			value="${shipOrderTotal}" onblur="check_number(this)" /></td>
	</tr>

</table>

</form>
<table width="100%" cellspacing="0" cellpadding="0" border="0">
	<tbody>
		<tr>
			<td>
			<div align="center"><c:if test="${editable == 'Y'}"><input type="button" class="style_botton"
				value="  Save  "
				onclick="update_promotion(this, 'promotion_form', 'quote_order_promotion!save.action');"
				id="promotion_add_button" /> </c:if><input type="button" value="Cancel"
				class="style_botton"
				onclick="javascript:parent.$('#promotion_<c:choose><c:when test="${opType == 'add'}">add</c:when><c:otherwise>edit</c:otherwise></c:choose>_dialog').dialog('close');" />
			<!-- <input name="Submit3" type="submit" class="style_botton4"  onclick="window.location.href='sales_statistics.html'" value="View Sales Statistics"/> -->
			</div>
			</td>
		</tr>
	</tbody>
</table>

</body>
</html>
