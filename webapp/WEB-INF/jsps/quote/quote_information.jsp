<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Information</title>
<base id="myBaseId" href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"  />
<script src="${global_js_url}util/util.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.form.js" type="text/javascript" ></script>
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<script src="${global_js_url}quoteorder/quoteorder_sales_information.js" type="text/javascript"></script>
<s:include value="quote_config.jsp"></s:include>
<script type="text/javascript">
$(function(){
    var quoteNo="${quote.quoteNo}";
    if (quoteNo != '') {//显示或修改quote时promotion code的选择.
      $("#prmtCode_sel").val('${quote.quotePromotion.prmtCode}');
    } else {//新增quote
       if ('${merckUser}'.indexOf("Merck") != -1) {
          $("#prmtCode_sel").val("MerckPromotion");
       }
    }
    if($("#prmtCode_hidden").val() != ''){
		if($("#prmtCode_sel option:selected").val()=='') {
			$('#txt_new_1').show();
			$("#prmtCode_manual").val($("#prmtCode_hidden").val());
			$("#prmtCode_manual").attr("disabled",true);
		} 
	}
	var statusThumb = $("[name='quote.status']").val();
    if(statusThumb == "NW" || statusThumb == "OP" || statusThumb == "RV" || statusThumb == "BO" || statusThumb == "PB"){
	 }else{
		if (parent.$("#AddItemTrigger") != undefined) {
			parent.$("#AddItemTrigger").attr("disabled",true);
		}
		$("form select,textarea").attr("disabled",true);
		$("form input:text").attr("readonly",true);
		$("#quote_ref_sel").attr("disabled",false);
		$("#prmtCode_btn").attr("disabled", true);
		$("#couponCode_add_btn").attr("disabled",true);
		$("#couponCode_delete_btn").attr("disabled",true);
	 }

	//查看quote Reference
	$("#view_quote_ref_trigger").click(function() {
	    var refNoSelectId = "quote_ref_sel";//下拉框的id.
	    viewQuoteRefDialog(refNoSelectId, "quote!edit.action?quoteNo=");//调用Order/Quote公共方法.
	});
	
	//查看Order Reference(即quote_src)
	$("#view_order_ref_trigger").click(function(){
	    var refNoSelectId = "order_ref_sel";//下拉框的id.
	    viewQuoteRefDialog(refNoSelectId, "order!edit.action?orderNo=");//调用Order/Quote公共方法.
	});
	 

	//绑定弹出order status history窗口;
    $("#viewStatusHistory").click(function (){
		var url = "quote!getQuoteStatusHistory.action?sessQuoteNo=${sessQuoteNo}";		
		parent.$('#viewStatusHistoryDialog').dialog("option", "open", function() {	
			htmlStr = '<iframe src="'+ url + '" height="100%" width="100%" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			parent.$('#viewStatusHistoryDialog').html(htmlStr);
		});
		parent.$('#viewStatusHistoryDialog').dialog('open');
	});
	$("#updateQuoteStatus").click(function(){
			parent.$("#confirmStatusDialog").dialog("open");
	});
});
function saveSaleInfo(){
	var src = "";
	if ($("#orderSrc_hidden").val().length > 0) {
		src = "&quote.quoteSrc=" + $("#orderSrc_hidden").val();
	}
	var bDisabled = $("#itemForm_orderSource").attr("disabled");
	 $("#itemForm_orderSource").attr("disabled", true);//不可用;
	$.ajax({
		type: "POST",
		url:  "quote!saveSaleInfo.action",
		data: $('#salesInformationItemForm').serialize() + src,
		success: function(data, textStatus){
			  //alert(data);
			  //window.location = window.location;

		},
		async:false,
		error: function(xhr, textStatus){
		   alert("System error! Please contact system administrator for help.");
		   return;
		}
   });
    $("#itemForm_orderSource").attr("disabled", bDisabled);//恢复当前值.
}
</script>
</head>
<body class="content" style="background-color:#FFFFFF;">
<form name="salesInformationItemForm" id="salesInformationItemForm">
<input type="hidden" name="sessQuoteNo"  value="${sessQuoteNo}"  />
<input type="hidden" name="quote.custNo" id="custNo" value="${quote.custNo}" id="custNo" />
<input type="hidden" name="quote.status" value="${quote.status}" />
<input type="hidden" name="quote.statusReason" id="itemForm_statusReason" value="${quote.statusReason}"  />
<input type="hidden" name="quote.statusNote" id="itemForm_statusNote" value="${quote.statusNote}" />
<table border="0" cellspacing="0" cellpadding="0" class="General_table" style="margin: 0px auto;" width="100%">
	<tr>
        <th width="158">Unit Price</th>
		<td colspan="3">
            <input type="text" class="NFText" name="itemForm_UnitPriceText" id="itemForm_UnitPriceText" size="30" value="" />
            <input id="updateQuoteUnitPrice" type="button" class="style_botton" value="Update" onclick="updateUnitPrice();"/>
        </td>
    </tr>
    <tr>
		<th width="158">Status</th>
		<td colspan="3">
			<input type="text" readonly="readonly" class="NFText" name="itemForm_statusText" id="itemForm_statusText" size="30" value="${quote.statusText}" />
			<s:if test="quote.statusText=='Close without order'||quote.statusText=='Closed without an order'||quote.statusText=='Closed with an order'||quote.statusText=='Void'">
			<input id="updateQuoteStatus" type="button" class="style_botton" value="Update" disabled/>
			<input name="viewStatusHistory" id="viewStatusHistory" type="button" class="style_botton4" value="Quote Status History" />
			</s:if>
			<s:else>
			<input id="updateQuoteStatus" type="button" class="style_botton" value="Update" />
			<input name="viewStatusHistory" id="viewStatusHistory" type="button" class="style_botton4" value="Quote Status History" />
			</s:else>
		</td>
	</tr>
	<tr>
		<th>Source</th>
		<td>
		    <input type="hidden" id="orderSrc_hidden" value="${quote.quoteSrc}" />
			<s:select cssStyle="width:182px" id="itemForm_orderSource"
				name="quote.quoteSrc"
				list="specDropDownList['ORIGINAL_SOURCE'].dropDownDTOs"
				listKey="id" listValue="name" value="quote.quoteSrc"></s:select>
		</td>
		
	</tr>
	<tr>
		<th>Quote Reference</th>
		<td width="248">
		<s:if test="quoteRefList != null && quoteRefList.size >0">
			<s:select cssStyle="width:182px" id="quote_ref_sel"
				name="quote_ref_sel"
				list="quoteRefList"></s:select>
		</s:if>		
			<input type="button" value="View" id="view_quote_ref_trigger" name="view_quote_ref_trigger" class="style_botton" <c:if test="${empty quoteRefList}">disabled="disabled"</c:if> />
			
		</td>
		<th width="190">Order No Converted </th>
		<td width="249">
		<s:if test="orderRefList != null && orderRefList.size >0">
			<s:select cssStyle="width:182px" id="order_ref_sel"
				name="order_ref_sel"
				list="orderRefList"></s:select>
		</s:if>
			<input type="button" value="View" id="view_order_ref_trigger" class="style_botton"  <c:if test="${empty orderRefList}">disabled="disabled"</c:if> />
				
            
			<input type="hidden" name="quote.refQuoteNo" id="quote_refQuoteNo" value="${quote.refQuoteNo}" />
			<input type="hidden" name="quote.orderNo" id="quote_orderNo" value="${quote.orderNo}" />
		</td>
	</tr>
   <tr>
		<th>Priority</th>
		<td colspan="3">
		<s:if test="priorityList != null && priorityList.size > 0">
			<s:select cssStyle="width:182px" id="role"
				name="quote.priority"
				list="priorityList"
				listKey="value" listValue="text" value="quote.priority"></s:select>
		</s:if>
		</td>
	</tr>
	<tr>
		<th>Purchase Order Number</th>
		<td>
		<input name="itemForm_poNumber" id="itemForm_poNumber" value=""  disabled="disabled" type="text" class="NFText" size="30" readonly="readonly"/>
		</td>
		<th>&nbsp;</th>

		<td>&nbsp;</td>
	</tr>
	<tr>
		<th>Sales Manager</th>
		<td><input name="itemForm_salesContact_text" id="itemForm_salesContact_text" type="text" class="NFText" size="30"	value="${quote.salesContactUser}" readonly="readonly"/>		
		</td>
		<th>Alternative Sales Manager</th>
		<td>
			<s:select cssStyle="width:182px" id="role"
				name="quote.altContact"
				list="specDropDownList['SALES_CONTACT'].dropDownDTOs"
				listKey="id" listValue="name" headerKey=""
				headerValue="Please select" value="quote.altContact"></s:select>

		</td>
	</tr>

	<tr>
		<th >Technical Account Manager</th>
		<td >
			<input name="itemForm_techSupport_text" id="itemForm_techSupport_text" type="text" class="NFText" value="${quote.techSupportUser}" size="30" readonly="readonly"/>
		</td>
		<th nowrap="nowrap">Alternative Technical Account Manager</th>
		<td >
			<s:select cssStyle="width:182px" id="role"
				name="quote.altTechSupport"
				list="specDropDownList['TECH_SUPPORT'].dropDownDTOs"
				listKey="id" listValue="name" headerKey=""
				headerValue="Please select" value="quote.altTechSupport"></s:select>
		</td>
	</tr>
	<tr>
          <th>Project Manager</th>
          <td>
          <s:if test="projectManagerList!=null">
          <s:select 
          			list="projectManagerList" 
          			listKey="userId" 
          			listValue="loginName" 
          			name="quote.projectManager"  
          			cssStyle="width:182px"
          			headerKey="-1"
          			headerValue="Please select">
          </s:select>
          </s:if>
          <s:else>
          <select name="projectManager" style="width:182px">
          	<option value="-1">Please select</option>
          </select>
          </s:else>
          </td>
          <th>Alternative  Project Manager</th>
          <td>
          <s:if test="altProjectManagerList!=null">
          <s:select 
          			list="altProjectManagerList" 
          			listKey="userId" 
          			listValue="loginName" 
          			name="quote.altProjectManager"  
          			cssStyle="width:182px"
          			headerKey="-1"
          			headerValue="Please select">
          </s:select>
          </s:if>
          <s:else>
          <select name="altProjectManager" style="width:182px">
          	<option value="-1">Please select</option>
          </select>
          </s:else>
		  </td>
        </tr>
	<tr>
		<th valign="top">Promotion Code</th>
		<td colspan="3">
		<span id="promotionSpan">
		<select name="prmtCode" id="prmtCode_sel" style="width: 182px;">
				<option value=''>None</option>
				<s:iterator value="specDropDownList['PROMOTION_CODE'].dropDownDTOs">
				   <option value="${name}">${name}</option>
				</s:iterator>
		</select>
		<s:hidden  name="quote.quotePromotion.prmtCode" id="prmtCode_hidden"/>
		<s:hidden  name="quote.promotionId" id="promotionId"/>
		</span>
		<span id="txt_new_1" style="display:none;"><input type="text" name="prmtCode_manual" id="prmtCode_manual" class="NFText" size="30" /></span>	
		<s:if test="tempStatus == 'CO' || tempStatus == 'CW' || tempStatus == 'VD' || tempStatus == 'OH'">  
		<input name="prmtCode_btn" type="button" id="prmtCode_btn" class="style_botton" value="Apply" readonly="readonly"/>
		</s:if>
		<s:else>
		<input name="prmtCode_btn" type="button" id="prmtCode_btn" class="style_botton" value="Apply" />
		<input name="prmtCodeLook_btn" type="button" id="prmtCodeLook_btn" class="style_botton2" value="View Promotion" />
		
		</s:else>
		</td>
	</tr>
	<tr>
       	<th valign="top">Gift Card</th>
		<td colspan="3">
		<select name="couponCode" id="couponCode" style="width: 182px;">
				<option>Add Gift Card</option>
				<s:iterator value="specDropDownList['COUPON_CODE'].dropDownDTOs">
				   <option value="${value}">${name}</option>
				</s:iterator>
		</select>
        <input name="add" id="couponCode_add_btn" type="button" class="style_botton" value="Add"  onclick="viewCoupon(${quote.custNo});"/>
        <span id="deleteSpan">
            <input name="delete" id="couponCode_delete_btn" type="button" class="style_botton" value="Delete" onclick="deleteCoupon();"/>
        </span>
		<s:hidden name="quote.couponId" id="couponId"/>
		</td>
		
	</tr>
	<tr>
		<th valign="top">Quotation  Type</th>
		<td colspan="3">
			<s:select cssStyle="width:182px" id="role"
				name="quote.quoteType"
				list="dropDownList['QUOTE_TYPE']" listKey="value" listValue="text" value="quote.quoteType"></s:select>
		</td>
	</tr>
	<tr>
		<th valign="top">Quotation  Memo</th>
		<td colspan="3">
			<s:select cssStyle="width:182px" id="role"
				name="QUOTE_MEMO_TEMPLATE"
				list="specDropDownList['QUOTE_MEMO_TEMPLATE'].dropDownDTOs"
				listKey="id" listValue="name" headerKey=""
				headerValue="Please select"></s:select>
				
		<br />
		<textarea name="quote.quoteMemo" id="itemForm_Memo" cols="" rows="" class="content_textarea" style="margin-top: 5px; height: 50px;">${quote.quoteMemo}</textarea>
		</td>
	</tr>
</table>
</form>
<div id="viewCouponCodeDialog" title="View Gift Card Code"></div>
<script type="text/javascript">
if (parent.operation_method != "edit") {
	$('input').attr("disabled",true);
	$('button').attr("disabled",true);
	$('select').attr("disabled",true);
}

function updateUnitPrice(){
	var data = $.trim($('#itemForm_UnitPriceText').val());
    if(data==""){
        alert("Please input the 'Unit Price' value");
        return false;
    }
	$.ajax({
		type: "get",
		url:  "quote_item!updateQuoteUnitPrice.action",
		data: "unitPrice=" + data + "&sessQuoteNo=" + $("input[name='sessQuoteNo']").attr("value"),
		success: function(data, textStatus){
            if (data.message == "ok") {
                parent.frames['itemListIframe'].location.reload();
            } else if(data.message == "fail"){
                alert("System error! Please contact system administrator for help.");
                return false;
            }else{
                alert(data.message);
                return false;
            }
		},
        dataType:"json",
		async:false,
		error: function(xhr, textStatus){
		   alert("System error! Please contact system administrator for help.");
		}
   });
}
</script>
</body>
</html>