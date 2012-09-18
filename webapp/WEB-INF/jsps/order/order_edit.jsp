<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>Order Management</title>
<base id="myBaseId" href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"  />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script src="${global_js_url}jquery/jquery.js" type="text/javascript" language="javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.form.js"></script>
<script src="${global_js_url}jquery/jquery.validate.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.dialog.all.js" type="text/javascript"></script>
<script src="${global_js_url}scm/config.js" type="text/javascript"></script>
<script src="${global_js_url}/recordTime.js" type="text/javascript"></script>
<script type="text/javascript" src="${global_js_url}scm/orgPicker.js"></script>
<s:include value="order_config.jsp"></s:include>
<script src="${global_js_url}initlefttop.js" type="text/javascript"></script>
<script src="${global_js_url}jquery.funkyUI.js" type="text/javascript"></script>
<script>
var operation_method = "${operation_method}";
//页面初次edit进入标志
var editInitFlag = true;
if (operation_method != "edit") {
	$('input').attr("disabled",true);
	$('button').attr("disabled",true);
	$('select').attr("disabled",true);
}
var isSaved = false;
window.onbeforeunload = function() {
	if(isSaved === false){
		return 'Do you want to leave without saving data?';
	}
}
var prefShipMthd;
$(document).ready(function(){
	prefShipMthd = $("#prefShipMthd").val();
	var orderNo = '${order.orderNo}';
	var isSalesManager = "${isSalesManager}";
	var quorderStatus = $("#quorderStatus").val();
	var shippedFlag = '${shippedFlag}';
	if (orderNo == '') {//新增时以下操作不允许.   
	   $("#actionListId option[value=REPEAT_NEW_ORDER]").remove();
	   $("#actionListId option[value=cancel_order]").remove();
	   $("#actionListId option[value=REOPEN]").remove();
	   $("#actionListId option[value=RETURN_ORDER]").remove();
	}
	if(quorderStatus != "SH" && quorderStatus != "VS" && quorderStatus != "PS"){
		 $("#actionListId option[value=RETURN_ORDER]").remove();
	}
	if(quorderStatus != "CC" && quorderStatus != "VC" && quorderStatus != "IP" && quorderStatus != "PS"){
		if (quorderStatus == "CN" && isSalesManager == 'true') {
			
		} else {
			 $("#actionListId option[value=REOPEN]").remove();
		}
	}
	if (isSalesManager != 'true') {
		$("#actionListId option[value=cancel_order]").remove();
	} else {
		if (quorderStatus != "NW" && quorderStatus != "RV" 
			&& quorderStatus != "CC" && quorderStatus != "VC"
			&& quorderStatus != "BO" && quorderStatus != "PB") {
			$("#actionListId option[value=cancel_order]").remove();
		}
	}
	if (quorderStatus != "NW" && quorderStatus != "RV") {
		$("#actionListId option[value=CUST_CONFIRMED]").remove();
	}
	if(quorderStatus == "CN" ){
		$("#actionListId option[value=REPEAT_NEW_ORDER]").remove();
		$("#actionListId option[value=cancel_order]").remove();
		//var instructTabIndex = window.getTabIndexBy("Instructions/Notes");
		//changeTabClass(instructTabIndex, 'document', 'tabDisable');
	}
	if(shippedFlag == 1){
		$("#actionListId option[value=REOPEN]").remove();
	}
	$("#purchaseOrderDialog").dialog({
		autoOpen: false,
		height: 350,
		width: 580,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
			var url = "purchase/cust_purchase_order!input.action?orderNoStr=${orderNo}&operation_method=edit";
			var htmlStr = '<iframe id="newPurcharseOrderIframe" src="'+url+'" height="300" width="540" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
			$('#purchaseOrderDialog').html(htmlStr);
		}
	});
	$("#genPurchaseOrderTrigger").click(function(){
		$("#purchaseOrderDialog").dialog("open");
		
	});

	$("#salesOrderDialog").dialog({
		autoOpen: false,
		height: 350,
		width: 580,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
			var url = "purchase/cust_processing_order!input.action?orderNoStr=${purchaseOrderNo}&operation_method=edit";
			var htmlStr = '<iframe id="newPurcharseOrderIframe" src="'+url+'" height="300" width="540" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
			$('#salesOrderDialog').html(htmlStr);
		}
	});
	$("#genSalesOrderTrigger").click(function(){
		$("#salesOrderDialog").dialog("open");
		
	});
	$('#promotion_edit_dialog').dialog({
		autoOpen: false,
		height: 510,
		width: 800,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
});

function del_order(name){
	var noData = "orderNos=${sessOrderNo}";
	var statusReason = $("#statusReason").val();
	var comment = $("#comment").val();
	if(comment == ""){
		alert("Please enter the Note.");
		$("#comment").focus();
		return;
	}
	$.ajax({
		type: "POST",
		url: "order/order!delete.action",
		data: noData+'&statusReason='+statusReason+'&comment='+comment,
		success: function(msg){
			if(msg == 'success'){
				$('#cancelDialog').dialog('close');
				alert('Cancel order successfully');
				window.location = "order/order_search!search.action";
			}else if(msg == 'error'){
				alert("You don't have permission to cancel order, please contact your supervisor.");	
			}else {
				alert('Unknown error');
			}
		},
		error: function(msg){
			alert("Error: Failed to cancel the order.");
		}
	});
	return false;
}

function changeOrderQuoteStatus(quorderStatus){
	if( quorderStatus == "NW" || quorderStatus == "RV"|| quorderStatus == "OP" || quorderStatus == "BO" || quorderStatus == "PB"){
		orderquoteObj.editFlag = true;
	}else{
		orderquoteObj.editFlag = false;
	}
}
changeOrderQuoteStatus("${order.status}");


var custNo = '${order.custNo}';
//function initLeft () {
	//parent.parent.leftFrame.changeTopLink("Order Processing");
//}
</script>
</head>
<body class="content" onload="recordTime();rtnPreLeftTop('Order Processing');">
<s:if test="order != null && order.orderNo != null && searchCust != null">
<input type="hidden" value="${order.orderNo}" id="hiddenOrderNo"/>
<input type="hidden" id="hiddenBusEmail" value="${busEmail}"/>
<input type="hidden" value="${order.amount}" id="hiddenAccountNumber"/>
<input type="hidden" value="${searchCust.firstName} ${searchCust.midName} ${searchCust.lastName}" id="hiddenCustName"/>
<input type="hidden" value="${searchQuote.quoteNo}" id="hiddenQuoteNo"/>
</s:if>
<div class="scm">
<div class="title_content">
<input type="hidden" id="custNo_hidden" value="${order.custNo}" />
<input type="hidden" id="keyInfoChanged" name="keyInfoChanged" value="${order.keyInfoChanged}" />
<input type="hidden" id="purchaseOrderNo" value="${purchaseOrderNo}" />
<input type="hidden" id="quorderStatus" name="quorderStatus" value="${order.status}" />
<input type="hidden" id="internalOrderFlag" value="${internalOrderFlag}" />
<s:if test="purchaseOrderNo != null">
	<div class="title">Purchase Order #${purchaseOrderNo} - 
	<s:if test="order.status == 'CN'"><font color="Red">Canceled</font></s:if>
	 &nbsp;&nbsp;&nbsp;${fullName} (Customer #${custNo})
		<input type="hidden" value="${orderNo}" name="orderNo" id="orderNo" />
	</div>
</s:if>
<s:elseif test="orderNo == 0 && purchaseOrderNo == null">
	<div class="title">New Order &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${fullName} (Customer #${custNo})
		<input type="hidden" value="${orderNo}" name="orderNo" id="orderNo" />
	</div>
</s:elseif>
<s:else>
	<div class="title">Order #${orderNo} - 
	<s:if test="order.status == 'CN'"><font color="Red">Canceled</font></s:if>
	 &nbsp;&nbsp;&nbsp;${fullName} (Customer #
	 <s:if test="salesOrderFlag == 1">
	 	${billAccCode}
	 </s:if>
	 <s:else>
	 <c:if test="${custNo > 0}">
     	<c:if test='${param.lookCust==null}'><a href="customer/customer!edit.action?custNo=${custNo}&operation_method=view" target="_blank">${custNo}</a></c:if>
     	<c:if test='${param.lookCust!=null&&param.lookCust=="N"}'>
     	${custNo}
     	</c:if>
     </c:if>
     <c:if test="${custNo <= 0}">${custNo}</c:if>
     </s:else>
     )
		<input type="hidden" value="${orderNo}" name="orderNo" id="orderNo" />
	</div>
</s:else>
</div>
<input type="hidden" id="tmpAdd" name="tmpAdd" />
<div class="input_box">
<div class="content_box">
	   	<iframe name="itemListIframe" id="itemListIframe" src="order_item!list.action?sessOrderNo=${sessOrderNo}" width="1000" style="overflow-y:hidden;" frameborder="0" scrolling="auto"></iframe>
</div>
</div>
</div>
<s:if test="purchaseOrderFlag == 0 && internalOrderFlag != 1">
	<div class="new_item" style="text-align:center">
	<s:if test='operation_method == "edit"'>
		<input type="button" name="AddItemTrigger" id="AddItemTrigger" value="Add New Item" class="search_input" />&nbsp;&nbsp;
	</s:if>
	<s:else>
		<input type="button" name="AddItemTrigger" value="Add New Item" class="search_input" />&nbsp;&nbsp;
	</s:else>
		<input type="hidden" id="prefShipMthd" name="prefShipMthd" value="${prefShipMthd}" />&nbsp;&nbsp;
        <input type="button" name="AddBatchItemTrigger" id="AddBatchItemTrigger" value="Add Batch Item" class="search_input"/>&nbsp;&nbsp;
	</div>
	
<form name="form1" id="form1">
<saveButton:saveBtn parameter="${operation_method}"
	disabledBtn='<select name="menu1" id="actionListId" style="width: 250px;" disabled="disabled"><option selected="selected">Select Operation</option></select>'
	saveBtn='
	<select name="menu1" id="actionListId" style="width: 250px;">
		<option selected="selected">Select Operation</option>
		<option value="CUST_CONFIRMED">Customer Confirm</option>
		<option value="CREATE_DUE_DATE" title="Add Order Change Notification">Add Order Change Notification</option>
		<option value="PRINT_ORDER">Create Order Document</option>
		<option value="cancel_order" title="Cancel Order">Cancel Order</option>
		<option value="REOPEN" title="Reopen Order">Reopen Order</option>
		<option value="Allitemdetail">All Item Detail</option> 
		<option value="RETURN_ORDER" title="Return Order">Return Order</option>
		<option value="REPEAT_NEW_ORDER" title="Repeat as New Order">Repeat as New Order</option>
		<option value="SAVE_TMPL" title="Save My Template">Save as My Template</option>
	</select>
'
/>	
</form>
</s:if><s:elseif test="internalOrderFlag == 1">
	<div class="new_item" style="text-align:center">
	<s:if test='operation_method == "edit"'>
		<input type="button" name="AddItemTrigger" id="AddItemTrigger" value="Add New Item" class="search_input" />&nbsp;&nbsp;
	</s:if>
	<s:else>
		<input type="button" name="AddItemTrigger" value="Add New Item" class="search_input" />&nbsp;&nbsp;
	</s:else>
		<input type="hidden" id="prefShipMthd" name="prefShipMthd" value="${prefShipMthd}" />&nbsp;&nbsp;
        <input type="button" name="AddBatchItemTrigger" id="AddBatchItemTrigger" value="Add Batch Item" class="search_input"/>&nbsp;&nbsp;
	</div>
	
<form name="form1" id="form1">
<saveButton:saveBtn parameter="${operation_method}"
	disabledBtn='<select name="menu1" id="actionListId" style="width: 250px;" disabled="disabled"><option selected="selected">Select Operation</option></select>'
	saveBtn='
	<select name="menu1" id="actionListId" style="width: 250px;">
		<option selected="selected">Select Operation</option>
		<option value="CUST_CONFIRMED">Customer Confirm</option>
		<option value="CREATE_DUE_DATE" title="Add Order Change Notification">Add Order Change Notification</option>
		<option value="PRINT_ORDER">Create Order Document</option>
		<option value="REOPEN" title="Reopen Order">Reopen Order</option>
		<option value="Allitemdetail">All Item Detail</option> 
	</select>
'
/>	
</form>
</s:elseif>
<div id="dhtmlgoodies_tabView1">
<div class="dhtmlgoodies_aTab">
	<iframe name="itemDetailIframe" id="itemDetailIframe" src="" height="380" width="950" style="" frameborder="0" scrolling="no"></iframe>
</div>
<div id="updateItemStatusDialog" style="display:none;">
	<s:include value="../quoteorder/quoteorder_item_update_status.jsp"></s:include>
</div>
<div id="itemStatusHistoryDialog" title="Item Status History" style="display:none;"></div>
<div id="allItemDetailsDialog" title="" style="display:none;"></div>
<div id="itemMoreInfoDialog" title="Catalog Info" style="display:none;"></div>

<div class="dhtmlgoodies_aTab">
	<iframe id="moreDetailIframe" name="moreDetailIframe" width="100%" height="390" frameborder="0"></iframe>
</div>

<s:if test="purchaseOrderFlag == 0">
<div class="dhtmlgoodies_aTab">
	<iframe id="addrIframe" width="970px" height="350px" frameborder="0"></iframe>
	<div id="changeAddressDialog" title="Select Address"></div>
	<div id="orgDialogWindow" title="Organization Lookup"></div>
</div>
<div class="dhtmlgoodies_aTab">
    <%--shiptoAddrFlag 为3的由JS去加载--%>
	<iframe id="multiAddrIframe" _src='<s:if test="shiptoAddrFlag != 3">quorder/quorderAddress/showMultiAddrAct?quorderNo=${orderNo}&itemId=${item.orderItemId}&codeType=order</s:if>' width="970px" height="350px" frameborder="0"></iframe>
	<div id="changeAddressDialog2" title="Select Address"></div>
</div>
</s:if>
<div class="dhtmlgoodies_aTab" id="salesIFrame">
	<iframe id="salesInformationIframe" name="salesInformationIframe" src="order!getInformation.action?sessOrderNo=${sessOrderNo}&purchaseOrderFlag=${purchaseOrderFlag}"
		_src="order!getInformation.action?sessOrderNo=${sessOrderNo}"
		width="100%" height="340" frameborder="0"></iframe></div>

<!-- Instruction/Notes pane begin  -->
<div class="dhtmlgoodies_aTab" id="instructionTab">
	<iframe name="instructionIframe" id="instructionIframe"
		_src="order_note!list.action?sessOrderNo=${sessOrderNo}&custNo=${order.custNo}" height="300" width="984" scrolling="no" style="border: 0px"
		frameborder="0"></iframe>
</div>

<s:if test="purchaseOrderFlag == 0">
<!-- Packing pane begin  -->
<div class="dhtmlgoodies_aTab" id="packingTab">
	<iframe id="packagingIframe" _src="order_package!list.action?custNo=${custNo}&sessOrderNo=${orderNo}" height="350" width="984" scrolling="no" style="border:0px" frameborder="0"></iframe>
	<div id="packageModifyDialog" title=""></div>
</div>
<!-- Packing pane end  --> 
<div class="dhtmlgoodies_aTab">
<iframe id="totalIframe" name="totalIframe" height="425" src="" width="984" scrolling="no" style="border:0px" frameborder="0"></iframe>
</div>
</s:if>
<div class="button_box">
<s:if test="purchaseOrderFlag == 0">
<form enctype="multipart/form-data" method="post" id="orderSaveAll">
<saveButton:saveBtn parameter="${operation_method}"
disabledBtn='<input type="button" value="Save" class="search_input" style="background-image: url(images/input_searchbg2.jpg); width: 120px;" disabled="disabled" />'
saveBtn='<input type="button" value="Save" class="search_input" style="background-image: url(images/input_searchbg2.jpg); width: 120px;" onclick="saveTimeOut();" />'
/> 
<c:if test="${param.lookCust==null}">
<input name="button2" type="button" value="Cancel" id="cancel_btn" class="search_input"
	style="background-image: url(images/input_searchbg2.jpg); width: 120px;" />
<input id="viewCustomerTrigger" type="button" value="View Customer" class="search_input"
	style="background-image: url(images/input_searchbg2.jpg); width: 120px;" custNo="${custNo}" />
</c:if>

</form>
</s:if>
<s:else>
<!--
<s:if test="purchaseOrderFlag == 1 && salesOrderFlag == 0"> 
	<input class="search_input3" type="button" name="genPurchaseOrderTrigger" id="genPurchaseOrderTrigger" value="Generate Purchase Order"/>
	</s:if>
	<s:if test="salesOrderFlag == 1">
		<input class="search_input3" type="button" name="genSalesOrderTrigger" id="genSalesOrderTrigger" value="Generate Sales Order"/>
	</s:if>
	  -->
	<input class="search_input" type="button" name="backPurchaseOrderTrigger" id="backPurchaseOrderTrigger" value="Back" onclick="window.history.go(-1);"/>
</s:else>
</div>
</div>
<div id="itemLookupDialog" title="Item Lookup">
</div>
<div id="itemSelectDialog">
</div>
<div id="changeStatusDialog">
</div>
<div id="updateStatusDialog">
</div>
<div id="updateMainStatusDialog">
</div>
<div id="viewStatusHistoryDialog" title="Order Status History">
</div>
<div id="viewOrderItemsDocumentsDialog" title="View production documents Dialog">
</div>
<div id="viewSpePriceDialog" title="Special Price">
</div>
<div id="viewCustomerDialog" title="${fullName} (Customer #${custNo})">
</div>
<div id="searchEnzymeDialog" title="Search Enzyme">
</div>
<div id="orfCloneListDialog" title="Search Orf Clone">
</div>

<div id="purchaseOrderDialog" title="Create Purchase Order"></div>
<div id="salesOrderDialog" title="Create Sales Order"></div>
<!-- poped instruction dialog -->
<div id="instruction_dlg" title="Add Instruction/Note"> </div> 

<!-- poped instruction display dialog -->
<div id="instruction_update_dlg" title="Instruction/Note"> </div> 

<!-- poped cancel order dialog -->
<div id="cancelDialog" title="Cancel Order" style="display: none;">
	<s:include value="order_update_status.jsp"></s:include>
</div>
<!-- poped confirm order dialog -->
<div id="confirmStatusDialog" title="Confirm Order" style="display: none;">
	<s:include value="order_confirm_status.jsp"></s:include>
</div>
<!-- Update Schedule Shipment -->
<div id="updateOrderScheduleDeliveryDialog" title="Update Order Schedule Delivery"></div>
<!-- poped sequence select dialog -->
<div id="seqSelectDlg" title="Select" style="display: none;"></div> 

<!-- poped cancel order dialog -->
<div id="reopenDialog" title="Reopen Order"></div>
<!-- print order dialog -->
<div id="printDialog" title="Print Order"> </div>
<div id="returnOrderDialog" title="Return Order"></div> 
<div id="createCardsAccountDialog" title="Cards in Account"> </div>

<!-- saveOrderTmplDialog -->
<div id="saveTmplDialog" title="Save as My Template" style="overflow:no;overflow-x:hidden;overflow-y:hidden;" >
     
</div>
<%--add by zhanghuibin--%>
<div id="batchCloning" title="Batch Quotation"></div>
<div id="itemAddBatch" title="Batch Service"></div>
<div id="selectBatch" title="Select Batch Service"></div>
<input id="itemMaxNo" name="itemMaxNo" type="hidden" value="${itemMaxNo}" />
<input type="hidden" name="selectCheckBoxValue" id="selectCheckBoxValue" value="" />
<input type="hidden" name="totalAmount" id="totalAmount" value="0" />
<input type="hidden" name="totalDiscount" id="totalDiscount" value="0" />
<input type="hidden" name="totalTax" id="totalTax" value="0" />
<input type="hidden" name="shipAmt" id="shipAmt" value="${shipAmt}" />
<input type="hidden" id="now_page" value="${now_page}" />
<input type="hidden" id="projectSupport" value="${projectSupport}" />
<input type="hidden" id="mail_type" />
<input type="hidden" id="only_email" />
<input type="hidden" id="email_edit_type" />
<input type="hidden" id="email_all_data" />
<input type="hidden" id="email_temp_id" />
<%--add by zhanghuibin--%>
<input type="hidden" id="shiptoAddrFlag" value="${shiptoAddrFlag}"/>
<input type="hidden" id="fullName" name="fullName" value="${fullName}" />

<div id="viewOrderRefDialog" title="View Order"></div>
<div id="viewQuoteRefDialog" title="View Quote"></div>
<div id="vendor_search_dlg" title="Vendor Search"></div>
<div id="AllitemDetailDialog" title="All item Detail Dialog"></div>
<div id="customer_search_dlg" title="Customer Search"></div>
<div id="batchGeneOrderDialog" title="Batch Gene Order"></div>
<div id="printOrderQuDialog" title="Print Order"></div>
<input type="hidden" id="confirmStatus" value="${confirmStatus}" />
<input type="hidden" id="confirmStatusText" value="${confirmStatusText}" />
<input type="hidden" id="lookFromWoFlag" value="${lookFromWoFlag}"/>

<script src="${global_js_url}scm/gs.util.js" type="text/javascript"></script>
<script src="${global_js_url}quoteorder/order_quotation_dialog.js" type="text/javascript"></script>
<script src="${global_js_url}quoteorder/order_quotation_top.js" type="text/javascript"></script>

<c:if test="${param.workOrderSessNo!=null}"><%-- 创建内部订单 create internal order--%>
<input type="hidden" id="workOrderSessNo_hidden" value="${param.workOrderSessNo}" />
<input type="hidden" id="wo_path_hidden" value="${ctx}/workorder_entry!edit.action?referURL=select&sessId=${param.workOrderSessNo}" />
	<script type="text/javascript">
		initTabs('dhtmlgoodies_tabView1',Array('Item Detail','More Detail','Addresses','Multi-Ship/Gift Msg','Sales Information','Instructions/Notes','Packaging','Order Total'),${defaultTab},998,400);
		//disableTabByTitle('Addresses');
		disableTabByTitle('Multi-Ship/Gift Msg');
		disableTabByTitle('Packaging');
	</script>
</c:if>
<c:if test="${param.workOrderSessNo==null}">
	<s:if test="purchaseOrderFlag == 0">
	<script type="text/javascript">
	initTabs('dhtmlgoodies_tabView1',Array('Item Detail','More Detail','Addresses','Multi-Ship/Gift Msg','Sales Information','Instructions/Notes','Packaging','Order Total'),${defaultTab},998,400);
	</script>
	</s:if>
	<s:else>
	<script type="text/javascript">
	initTabs('dhtmlgoodies_tabView1',Array('Item Detail','More Detail','Sales Information','Instructions/Notes'),${defaultTab},998,375);
	</script>
	</s:else>
</c:if>
<s:if test="lookFromWoFlag==1">
	<script type="text/javascript">
		disableTabByTitle('Addresses');
		disableTabByTitle('Order Total');
	</script>
</s:if>

<div id="promotion_edit_dialog"  title="View promotion"> </div>
</body>
<script language="javascript" type="text/javascript">
//add by zhanghuibin
var batchCloningId = "";
        $('#AddBatchItemTrigger').click( function() {
        	var tmpFlag = updateMoreInfo();
			if(tmpFlag == false){
				var moreDetailIndex = getTabIndexBy("More Detail");
		       	if(moreDetailIndex != -1){
		       		showTab('dhtmlgoodies_tabView1', moreDetailIndex);
		       	}
		        unBlock();
				return;
			}
			$('#selectBatch').dialog( 'open' ) ;
			$( '#selectBatch' ).attr( 'innerHTML' , '<iframe  src="order_more!showBatchType.action" allowTransparency="true" width="100%" height="100%" frameborder="0" scrolling="no" ></iframe>' ) ;
		}) ;

    $( '#itemAddBatch' ).dialog({
			autoOpen: false ,
			height: 600 ,
			width: 820 ,
			modal: true ,
			//Div top display
			bgiframe: true
	}) ;

     $( '#selectBatch' ).dialog({
			autoOpen: false ,
			height: 200 ,
			width: 500 ,
			modal: true ,
			//Div top display
			bgiframe: true
	}) ;

    function showAddBatch(){
        $('#selectBatch').dialog( 'close') ;
        $('#itemAddBatch').dialog( 'open' ) ;
        $( '#itemAddBatch' ).attr( 'innerHTML' , '<iframe id="orderBatch"  src="order_more!showBachItem.action?custNo='+orderquoteObj.custNo+'&sessOrderNo='+orderquoteObj.sessNoValue+'" allowTransparency="true" width="100%" height="100%" frameborder="0" scrolling="Yes" ></iframe>' ) ;
    }

  $( '#batchCloning').dialog({
			autoOpen: false ,
			height: 465 ,
			width: 820 ,
			modal: true ,
			//Div top display
             close : function(event, ui){
                 if (batchCloningId == "") {
                     $("#orderBatch").contents().find("#cloningFlag").attr("checked", true);
                 }
            },
			bgiframe: true
	}) ;
 function showBatchCloning(sessionNo, itemIds){
     $('#batchCloning').dialog( 'open' ) ;
     $( '#batchCloning' ).attr( 'innerHTML' , '<iframe  src="order_more!showBatchCloningStrategy.action?sessOrderNo='+sessionNo+'&itemIds='+itemIds + '" allowTransparency="true" width="100%" height="100%" frameborder="0" scrolling="Yes" ></iframe>' ) ;

 }
</script>
</html>