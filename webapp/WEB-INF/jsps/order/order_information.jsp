<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Information</title>
<base id="myBaseId" href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css"
	rel="stylesheet" type="text/css" />
<script src="${global_js_url}util/util.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.form.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.dialog.all.js"
	type="text/javascript"></script>
<script src="${global_js_url}quoteorder/quoteorder_sales_information.js"
	type="text/javascript"></script>
<s:include value="order_config.jsp"></s:include>
<script>

	function dd(filePath,fileName) {
		 
		$("#fileName").val(fileName);
	$("#filePath").val(filePath);
	var url = "download.action";
	$("#fileDownLoad").attr('action',url);
    $("#fileDownLoad").submit();
	}
$(function(){
    var orderNo="${order.orderNo}";
    if (orderNo != '') {//显示或修改order时promotion code的选择.
      $("#prmtCode_sel").val('${order.orderPromotion.prmtCode}');
    } else {//新增
       if ('${merckUser}'.indexOf("Merck") != -1) {
          $("#prmtCode_sel").val("MerckPromotion");
       }
    }
    if ($("#prmtCode_hidden").val() != '') {
        if ($("#prmtCode_sel option:selected").val() == '') {
            $('#txt_new_1').show();
            $("#prmtCode_manual").val($("#prmtCode_hidden").val());
            $("#prmtCode_manual").attr("disabled", true);
        }
    }
    var statusThumb = $("[name='order.status']").val();
    if (statusThumb == "NW" || statusThumb == "RV" || statusThumb == "BO" || statusThumb == "PB") {

    } else {
        if (parent.$("#AddItemTrigger") != undefined) {
            parent.$("#AddItemTrigger").attr("disabled", true);
        }
        $("form select,textarea").attr("disabled", true);
        $("form input:text").attr("readonly", true);
        $("#prmtApply").attr("disabled", true);
        $("#order_ref_sel").attr("disabled", false);
        $("#quote_ref_sel").attr("disabled", false);
        $("#prmtCode_btn").attr("disabled", true);
        $("#couponCode_add_btn").attr("disabled", true);
        $("#couponCode_delete_btn").attr("disabled", true);
    }
    if (statusThumb == "CC") {
        $("select[name='order.priority']").attr("disabled", false);
    }

	//查看Order Reference
	$("#view_order_ref_trigger").click(function() {
	    var refNoSelectId = "order_ref_sel";//下拉框的id.
	    viewOrderRefDialog(refNoSelectId, "order!edit.action?orderNo=");//调用Quote/Order公共方法.
	});
	
	
	$("#upload_btn").click( function(){
	 
		if(! $("#upload_file").val()){
			alert("Please select one file !");
			return;
		}
		$('#upload_btn').attr("disabled", true);
		var upURL = "order!uploadLicensFile.action" +"?sessId="+ $("#sessId").val() + "&docType=" + $('#pdt_docType_sel').val(); 
		//ajax form post
		var options = {
			success:function(data){
				var documentList = data;
				var tmpStr = getFileListHtml(documentList);
				$("#salesInformationItemForm").find("#fileListTable").append(tmpStr);
				var file = $("#upload_file");
				file.after(file.clone().val(""));
				file.remove();
		        $('#upload_btn').attr("disabled", false);
			},
			error: function(data){
				if(data.responseText){
					alert(data.responseText);
				}else{
					alert("Error");
				}
			},
			dataType:"json",
			resetForm:false,
			url: upURL,
			type: "post"
		};
		$("#salesInformationItemForm").ajaxForm(options);
		$("#salesInformationItemForm").submit();
	}); 
	
	
	
	//查看Quote Reference(即order_src)
	$("#view_quote_ref_trigger").click(function(){
	    var refNoSelectId = "quote_ref_sel";//下拉框的id.
	    viewQuoteRefDialog(refNoSelectId, "quote!edit.action?quoteNo=");//调用Quote/Order公共方法.
	});
	 

	//绑定弹出order status history窗口;
    $("#viewStatusHistory").click(function (){
		var url = "order!getOrderStatusHistory.action?sessOrderNo=${sessOrderNo}";		
		parent.$('#viewStatusHistoryDialog').dialog("option", "open", function() {	
			htmlStr = '<iframe src="'+ url + '" height="100%" width="100%" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			parent.$('#viewStatusHistoryDialog').html(htmlStr);
		});
		parent.$('#viewStatusHistoryDialog').dialog('open');
	});
    $("#ViewDocumentsTrriger").click(function(){
     
    	var url = "order!getOrderItemsDocuments.action?sessOrderNo=${sessOrderNo}";		
     
		parent.$('#viewOrderItemsDocumentsDialog').dialog("option", "open", function() {	
			htmlStr = '<iframe src="'+ url + '" height="100%" width="100%" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			parent.$('#viewOrderItemsDocumentsDialog').html(htmlStr);
		});
		parent.$('#viewOrderItemsDocumentsDialog').dialog('open');
    });
	
	$("#updateOrderStatus").click(function(){
		var actionList = parent.$("#actionListId");
		if(actionList.find("[value='CUST_CONFIRMED']").size() == 0){
			return;
		}
		actionList.find("[value='CUST_CONFIRMED']").attr("selected", true);
		actionList.trigger("change");
	});
});

function saveSaleInfo(){
	var src = "";
	if ($("#orderSrc_hidden").val().length > 0) {
		src = "&order.orderSrc=" + $("#orderSrc_hidden").val();
	}
    var bDisabled = $("#itemForm_orderSource").attr("disabled");
    $("#itemForm_orderSource").attr("disabled", true);//不可用;
	$.ajax({
		type: "POST",
		url:  "order!saveSaleInfo.action",
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


function deleteFile() {
	var docIds = "";
	var indexArray = new Array();
	$("#fileListTable input[type='checkbox']").each(function(){
		if(this.checked) {
			if(docIds=="") {
				docIds = docIds +this.value;
			} else {
				docIds = docIds +","+this.value;
			}
			var trObj = $(this).parent().parent();
			indexArray.push(trObj.get(0).rowIndex);
		}
	});
   if(docIds=="") {
	   alert("Please select one file at least.");
	   return;
   }
   if (confirm("Are you confirm delete these files ?")) {   
        $.ajax({
			type: "POST",
			url: "order!deleteFile.action",
			data: "sessOrderNo=" + $("#sessOrderNo").val() + "&docIds=" + docIds,
			success: function(data, textStatus) {
				alert("Success");
				for(var i =0;i<indexArray.length;i++) {
					$("#salesInformationItemForm").find("#fileListTable").get(0).deleteRow(indexArray[0]); 
				}
			},
			error: function(xhr, textStatus){
			   alert("failure");
			}
	   });   

   }
	
}

function updateUnitPrice(){
	var data = $.trim($('#itemForm_UnitPriceText').val());
    if(data==""){
        alert("Please input the 'Unit Price' value");
        return false;
    }
	$.ajax({
		type: "get",
		url:  "order_item!updateOrderUnitPrice.action",
		data: "unitPrice=" + data + "&sessOrderNo=" + $("input[name='sessOrderNo']").attr("value"),
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
</head>
<body class="content" style="background-color: #FFFFFF;">
	<form name="salesInformationItemForm" id="salesInformationItemForm"
		enctype="multipart/form-data" class="niceform">
		<input type="hidden" name="sessOrderNo" id="sessOrderNo" value="${sessOrderNo}" /> <input
			type="hidden" name="order.custNo" value="${order.custNo}" id="custNo" />
		<input type="hidden" name="order.status" value="${order.status}" /> <input
			type="hidden" name="order.statusReason" id="itemForm_statusReason"
			value="${order.statusReason}" /> <input type="hidden"
			name="order.statusNote" id="itemForm_statusNote"
			value="${order.statusNote}" />
		<table border="0" cellspacing="0" cellpadding="0"
			class="General_table" style="margin: 0px auto;">
        <tr>
            <th width="158">Unit Price</th>
            <td colspan="3">
                <input type="text" class="NFText" name="itemForm_UnitPriceText" id="itemForm_UnitPriceText" size="30"  value=""/>
                <input id="updateQuoteUnitPrice" type="button" class="style_botton" value="Update" onclick="updateUnitPrice();"/>
            </td>
        </tr>
			<tr>
				<th width="158">Status</th>
				<td colspan="3"><input type="text" readonly="readonly"
					class="NFText" name="itemForm_statusText" id="itemForm_statusText"
					size="30" value="${order.statusText}" /> <s:if
						test="purchaseOrderFlag == 0">
						<input id="updateOrderStatus" type="button" class="style_botton"
							value="Update" />
						<input name="viewStatusHistory" id="viewStatusHistory"
							type="button" class="style_botton4" value="Order Status History" />
					</s:if></td>
			</tr>
			<tr>
				<th>Source</th>
				<td><input type="hidden" id="orderSrc_hidden"
					value="${order.orderSrc}" /> <s:select cssStyle="width:182px"
						id="itemForm_orderSource" name="order.orderSrc"
						list="specDropDownList['ORIGINAL_SOURCE'].dropDownDTOs"
						listKey="id" listValue="name" value="order.orderSrc"></s:select></td>
				<th width="190">Warehouse</th>
				<td><s:select cssStyle="width:182px" id="warehouse_sel"
						name="order.warehouseId" list="warehouseList"
						listKey="warehouseId" listValue="name" value="order.warehouseId"></s:select>
				</td>
			</tr>
			<tr>
				<th>Order Reference</th>
				<td><s:if test="orderRefList != null && orderRefList.size >0">
						<s:select cssStyle="width:182px" id="order_ref_sel"
							name="order_ref_sel" list="orderRefList"></s:select>
					</s:if> <input type="button" value="View" id="view_order_ref_trigger"
					class="style_botton"
					<c:if test="${empty orderRefList}">disabled="disabled"</c:if> /></td>
				<th width="190">Quote Reference</th>
				<td><s:if test="quoteRefList != null && quoteRefList.size >0">
						<s:select cssStyle="width:182px" id="quote_ref_sel"
							name="quote_ref_sel" list="quoteRefList"></s:select>
					</s:if> <input type="button" value="View" id="view_quote_ref_trigger"
					class="style_botton"
					<c:if test="${empty quoteRefList}">disabled="disabled"</c:if> /> <input
					type="hidden" name="order.refOrderNo" id="order_refOrderNo"
					value="${order.refOrderNo}" /> <input type="hidden"
					name="order.srcQuoteNo" id="order_srcQuoteNo"
					value="${order.srcQuoteNo}" /></td>
			</tr>
			<tr>
				<th>Priority</th>
				<td><s:if test="priorityList != null && priorityList.size > 0">
						<s:select cssStyle="width:182px" id="role" name="order.priority"
							list="priorityList" listKey="value" listValue="text"
							value="order.priority"></s:select>
					</s:if></td>
				<th>View documents:</th>
				<td><input type="button" name="ViewDocuments"
					value="View documents" id="ViewDocumentsTrriger"
					class="style_botton4" /></td>
			</tr>
			<tr>
				<th>Purchase Order Number</th>
				<td><input name="itemForm_poNumber" id="itemForm_poNumber"
					disabled="disabled" value="${poNumber}" type="text" class="NFText"
					size="30" /></td>
				<th>&nbsp;</th>

				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>Sales Manager</th>
				<td><input name="itemForm_salesContact_text"
					id="itemForm_salesContact_text" type="text" class="NFText"
					size="30" value="${order.salesContactUser}" readonly="readonly" />
				</td>
				<th>Alternative Sales Manager</th>
				<td><s:select cssStyle="width:182px" id="role"
						name="order.altContact"
						list="specDropDownList['SALES_CONTACT'].dropDownDTOs" listKey="id"
						listValue="name" headerKey="-1" headerValue="Please select"
						value="order.altContact"></s:select></td>
			</tr>

			<tr>
				<th>Technical Account Manager</th>
				<td><input name="itemForm_techSupport_text"
					id="itemForm_techSupport_text" type="text" class="NFText"
					value="${order.techSupportUser}" size="30" readonly="readonly" />
				</td>
				<th nowrap="nowrap">Alternative Technical Account Manager</th>
				<td><s:select cssStyle="width:182px" id="role"
						name="order.altTechSupport"
						list="specDropDownList['TECH_SUPPORT'].dropDownDTOs" listKey="id"
						listValue="name" headerKey="-1" headerValue="Please select"
						value="order.altTechSupport"></s:select></td>
			</tr>
			<tr>
				<th>Project Manager</th>
				<td><s:if test="projectManagerList!=null">
						<s:select list="projectManagerList" listKey="userId"
							listValue="loginName" name="order.projectManager"
							cssStyle="width:182px" headerKey="-1" headerValue="Please select">
						</s:select>
					</s:if> <s:else>
						<select name="order.projectManager" style="width: 182px">
							<option value="-1">Please select</option>
						</select>
					</s:else></td>
				<th>Alternative Project Manager</th>
				<td><s:if test="altProjectManagerList!=null">
						<s:select list="altProjectManagerList" listKey="userId"
							listValue="loginName" name="order.altProjectManager"
							cssStyle="width:182px" headerKey="-1" headerValue="Please select">
						</s:select>
					</s:if> <s:else>
						<select name="order.altProjectManager" style="width: 182px">
							<option value="-1">Please select</option>
						</select>
					</s:else></td>
			</tr>
			<s:if test="purchaseOrderFlag == 0">
				<tr>
					<th valign="top">Promotion Code</th>
					<td colspan="3"><span id="promotionSpan"> <select
							name="prmtCode" id="prmtCode_sel" style="width: 182px;">
								<option value=''>None</option>
								<s:iterator
									value="specDropDownList['PROMOTION_CODE'].dropDownDTOs">
									<option value="${name}">${name}</option>
								</s:iterator>
						</select> <s:hidden name="order.orderPromotion.prmtCode"
								id="prmtCode_hidden" /> <s:hidden name="order.promotionId"
								id="promotionId" />
					</span> <span id="txt_new_1" style="display: none;"><input
							type="text" name="prmtCode_manual" id="prmtCode_manual"
							class="NFText" size="30" /> </span> <input name="prmtCode_btn"
						type="button" id="prmtCode_btn" class="style_botton" value="Apply" />
						<input name="prmtCodeLook_btn" type="button" id="prmtCodeLook_btn"
						class="style_botton2" value="View Promotion" /></td>

				</tr>
				<tr>
					<th valign="top">Gift Card</th>
					<td colspan="3"><select name="couponCode" id="couponCode"
						style="width: 182px;">
							<option value="">Please select</option>
							<s:iterator value="specDropDownList['COUPON_CODE'].dropDownDTOs">
								<option value="${value}">${name}</option>
							</s:iterator>
					</select> <input name="add" id="couponCode_add_btn" type="button"
						class="style_botton" value="Add"
						onclick="viewCoupon(${order.custNo});" /> <span id="deleteSpan">
							<input name="delete" id="couponCode_delete_btn" type="button"
							class="style_botton" value="Delete" onclick="deleteCoupon();" />
					</span> <s:hidden name="order.couponId" id="couponId" /></td>

				</tr>
			</s:if>
			<tr>
				<th valign="top">Order Type</th>
				<td><s:select cssStyle="width:182px" id="role"
						name="order.orderType" list="dropDownList['ORDER_TYPE']"
						listKey="value" listValue="text" value="order.orderType"></s:select>
				</td>
				<th>Import And Export License</th>
				<td><input type="file" class="NFText" style="height: 18px;"
					name="upload" id="upload_file" size="20" /> <input
					name="tmplUploadBtn" type="button" class="style_botton"
					value="Upload" id="upload_btn" /> <input type="button"
					name="Submit4" class="style_botton" value="Delete"
					onclick="deleteFile()" /><br /> <input id="pdt_docType_sel"
					value="ORDER_LICENCE_DOC" name="pdt_docType_sel" type="hidden" /> <input
					type="hidden" name="sessId" value="${sessId}" id="sessId" /></td>
			</tr>
			<tr>
				<th valign="top">Order Memo</th>
				<td><s:select cssStyle="width:182px" id="role"
						name="ORDER_MEMO_TEMPLATE"
						list="specDropDownList['ORDER_MEMO_TEMPLATE'].dropDownDTOs"
						listKey="id" listValue="name" headerKey=""
						headerValue="Please select"></s:select> <br /> <textarea
						name="order.orderMemo" id="itemForm_Memo" cols="" rows=""
						class="content_textarea" style="margin-top: 5px; height: 50px;">${order.orderMemo}</textarea>

				</td>
				<th></th>
				<td>
					<table id="fileListTable">
						<s:iterator value="order.documentList">
							<tr>
								<td><input type="checkbox" value="${docId}" /></td>
								<td><a href="javascript:void(0);"
									onclick="dd('${filePath}','${docName}')">${docName}</a>
								</td>
							</tr>
						</s:iterator>
					</table>
				</td>
			</tr>

		</table>
	</form>

	<form name="" id="fileDownLoad" method="post">
		<s:hidden name="filePath" id="filePath"></s:hidden>
		<s:hidden name="fileName" id="fileName"></s:hidden>
	</form>
	<iframe id="tableDownload" name="downLoadFile" src="" height="0"
		width="0" scrolling="no" style="border: 0px" frameborder="0"></iframe>
	<div id="viewCouponCodeDialog" title="View Gift Card Code"></div>
	<script type="text/javascript"> 
	function getFileListHtml(documentList){
		  var tmpStr = "";
	 	  $.each(documentList, function(key, value) {
			    tmpStr += '<tr><td>';
				tmpStr += '<input type="checkbox" value="'+key+'"/>';
				tmpStr += '</td><td>';
				tmpStr += '<a href="javascript:void(0);" onclick="dd(\''+value["filePath"]+'\',\''+value["docName"]+'\')">'+value.docName+'</a>';
				tmpStr += '</td></tr>';   
	      });
		  return tmpStr;
	}
if (parent.operation_method != "edit") {
	$('input').attr("disabled",true);
	$('button').attr("disabled",true);
	$('select').attr("disabled",true);
}
</script>
</body>
</html>