//初始化输入框是否可以编辑
function checkItemInfo(){
	var status = $("#status").val();
	$("#itemForm").find("[readonly]").bind("focus", function(){ $(this).trigger("blur"); });
	if(status == 'CM' || status == 'PB' || status == 'BO' || (orderquoteObj.type == 'quote' && status == 'RG') ){
		$("#comment").attr("readonly", false);
		$("#trackingNo").attr("readonly", false);
		$('#longDesc').attr("readonly", false);
		$("#comment").unbind("focus");
		$("#longDesc").unbind("focus");
		$("#trackingNo").unbind("focus");
		if(orderquoteObj.type == 'quote' && $("#tempStatusStr").val()=='CW') {
			$("#storageIdWarehouseId").attr("disabled", true);
		} else {
			$("#storageIdWarehouseId").attr("disabled", false);
		}
		$("#shipSchedule1").attr("readonly", true);
		if (orderquoteObj.type == 'quote' && $("#shipSchedule1").length>0) {
			if ($("#tempStatusStr").val()=='CW' || $("#tempStatusStr").val()=='CL' 
				|| $("#tempStatusStr").val()=='CN') {
				$("#updateQuoteScheduleDeliveryDialogTrigger").attr("disabled", true);
			} else {
				$("#updateQuoteScheduleDeliveryDialogTrigger").attr("disabled", false);
			}
		}
		if (orderquoteObj.type == 'order' && $("#shipSchedule1").length>0) {
			$("#updateOrderScheduleDeliveryDialogTrigger").attr("disabled", false);
		}
		if(orderquoteObj.type == 'quote' && ($("#tempStatusStr").val()=='CW' 
			|| $("#tempStatusStr").val()=='CO' || $("#tempStatusStr").val()=='VD' 
				|| $("#tempStatusStr").val()=='OH')) {
			$("#shipMethod").attr("disabled", true);
		} else {
			$("#shipMethod").attr("disabled", false);
		}
		var rt = true;
	}else{
		$("#comment").attr("readonly", true);
		$("#trackingNo").attr("readonly", true);
		$('#longDesc').attr("readonly", true);
		$("#comment").bind("focus", function(){ $(this).trigger("blur"); });
		$("#trackingNo").bind("focus", function(){$(this).trigger("blur"); });
		$("#longDesc").bind("focus", function(){$(this).trigger("blur"); });
		$("#storageIdWarehouseId").attr("disabled", true);
		$("#shipSchedule1").attr("disabled", true);
		$("#updateQuoteScheduleDeliveryDialogTrigger").attr("disabled", true);
		$("#updateOrderScheduleDeliveryDialogTrigger").attr("disabled", true);
		$("#shipMethod").attr("disabled", true);
		$("#shippingRoute").attr("disabled", true);
		var rt = false;
	}
	var packShipDate = $("#packTrackingNo").val();
	if (packShipDate != undefined && packShipDate != "") {
		$("#shipDate").val(packShipDate);
	}
	return rt;
}
//切换Pick location
function changePickLocation(){
	var rtFlag = false;
	var itemId = $("#itemId").val();
	var storageIdWarehouseId = $(this);
	var old_storageId = $("#storageId").val();
	var storageId = storageIdWarehouseId.val();
	$.ajax({
		type:"get",
		url:orderquoteObj.url("getItemOtherInfo"),
		data:orderquoteObj.sessNoName+"="+orderquoteObj.sessNoValue+"&itemId="+itemId+"&storageId="+storageId,
		dataType:"json",
		success:function(data){
			$("#status").val(data["itemStatus"]);
			$("#statusText").val(data["itemStatusText"]);
			$("#otherInfo").val(data["otherInfo"]);
			$("#storageId").val(storageId);
			parent.window.frames["itemListIframe"].updateStatus(itemId, data["itemStatus"]);
		},
		error:function(){
			alert("System error! Please contact system administrator for help.");
			//充值下拉列表
			$("#storageIdWarehouseId").find("option[value='"+old_storageId+"']").attr("selected", true);
		},
		async:false
	});
	setOtherInfoColor();
}

function setOtherInfoColor(){
	if($('#otherInfo').val() == "Not in stock"){
		$('#otherInfo').get(0).style.color = "red";
	}else{
		$('#otherInfo').get(0).style.color = "";
	}
}

function updateItemStatus(){
	var itemId = $("#itemId").val();
	if(itemId == "" || parseInt(itemId) != itemId){
		return;
	}
		var itemStatus = $("#status").val();
	$.ajax({
		type:"get",
		url:orderquoteObj.url("getItemStatusList"),
		data:orderquoteObj.sessNoName+"="+orderquoteObj.sessNoValue+"&status="+itemStatus,
		dataType:"json",
		success:function(itemStatus){
			parent.$("#updateStatus").html("");
			var tmpStatus = "";
			for(var i in itemStatus){
				if(tmpStatus == ""){
					tmpStatus = itemStatus[i].code;
				}
				parent.$("#updateStatus").append('<option value="'+itemStatus[i].code+'">'+itemStatus[i].name+'</option>');
			}
			if(tmpStatus != ""){
				parent.changeUpdateStatus(tmpStatus);
			}
			if(parent.$("#updateStatus").html() == ""){
				$("#updateStatusDialogTrigger").attr("disabled", true);
			}else{
				$("#updateStatusDialogTrigger").attr("disabled", false);
			}
		},
		error:function(){
			alert("System error! Please contact system administrator for help.");
		},
		async:false
	});
	if($("#updateStatusDialogTrigger").attr("disabled") == true){
		return;
	}
	var itemNo = $("#itemNoSpan").html();
	parent.$('#updateItemStatusDialog').dialog("option", "title", "Modify Status For Order Or Quotation item #"+itemNo);
	parent.$('#updateItemStatusDialog').dialog("open");
}

function updateOrderScheduleDelivery () {
	var itemId = $("#itemId").val();
	if(itemId == undefined || itemId == ""){
		return;
	}
	var url = "order_item!showItemScheduleShipment.action?"+orderquoteObj.sessNoName+"="+orderquoteObj.sessNoValue+"&itemId="+itemId;
	var htmlStr = '<iframe src="'+url+'" height="100%" width="100%" allowTransparency="true" frameborder="0" scrolling="No"></iframe>';
	parent.$('#updateOrderScheduleDeliveryDialog').html(htmlStr);
	parent.$("#updateOrderScheduleDeliveryDialog").dialog("open");
}

function updateQuoteScheduleDelivery () {
	var itemId = $("#itemId").val();
	if(itemId == undefined || itemId == ""){
		return;
	}
	var url = "quote_item!showItemScheduleShipment.action?"+orderquoteObj.sessNoName+"="+orderquoteObj.sessNoValue+"&itemId="+itemId;
	var htmlStr = '<iframe src="'+url+'" height="100%" width="100%" allowTransparency="true" frameborder="0" scrolling="No"></iframe>';
	parent.$('#updateQuoteScheduleDeliveryDialog').html(htmlStr);
	parent.$("#updateQuoteScheduleDeliveryDialog").dialog("open");
}

//更新item detail信息到session中去
function updateItemDetail(){
	var itemId = $("#itemId").val();
	if(itemId == ""){
		return;
	}
	if ($("#shipSchedule1").length>0) {
		var shipSchedule1 = $("#shipSchedule1").val();
		if (shipSchedule1 != undefined) {
			shipSchedule1 = shipSchedule1.replace(/\s/g,"");
			if (shipSchedule1 != "" && !(/^[0-9]*$/).test(shipSchedule1)) {
				alert("Schedule Shipment must be number.");
				return;
			}
			$("#shipSchedule1").val(shipSchedule1);
		}
	}
	var form = $("#itemForm");
	var options = {
		success:function(data){
//			alert(data);
		},
		error: function(){
			alert("System error! Please contact system administrator for help.");
		},
		resetForm:false,
		url:orderquoteObj.url("saveItem")+"?"+orderquoteObj.sessNoName+"="+orderquoteObj.sessNoValue,
		type:"POST",
		async: false
	};
	form.ajaxForm(options);
	form.submit();
}

$(document).ready(function(){
	//初始化输入框是否可编辑
	checkItemInfo();
	 //切换Pick location 事件
	$("#storageIdWarehouseId").change(changePickLocation);
	//设置otherInfo颜色
	setOtherInfoColor();
	//update item status
	$("#updateStatusDialogTrigger").click(function(){
		updateItemStatus();
	});
	$('#itemStatusHistoryDialogTrigger').click(function(){
		var itemId = $("#itemId").val();
		if(!itemId){
			alert("Please select the item to continue your operation.");
			return;
		}
		parent.$('#itemStatusHistoryDialog').dialog('option', 'itemId', itemId);
		parent.$('#itemStatusHistoryDialog').dialog('open' );
	});
	$('#allItemDetailsDialogTrigger').click(function(){
		if(orderquoteObj.type == "order"){
			var tmp = "Order";
		}else{
			var tmp = "Quotation";
		}
		if(parseInt(orderquoteObj.sessNoValue) != orderquoteObj.sessNoValue){
			var title = "All Line Items Detail in New "+tmp;
		}else{
			var title = "All Line Items Detail in "+tmp+" #"+orderquoteObj.sessNoValue;
		}
		parent.$('#allItemDetailsDialog').dialog('option' , 'title', title);
		parent.$('#allItemDetailsDialog').dialog('open' );
	});
	
	$("#itemMoreInfoTrigger").click(function(){
		parent.$("#itemMoreInfoDialog").dialog("option", "catalogId", $("#catalogId").val());
		parent.$("#itemMoreInfoDialog").dialog("open");
	});
	
	$("#updateOrderScheduleDeliveryDialogTrigger").click(function(){
		updateOrderScheduleDelivery();
	});
	
	$("#updateQuoteScheduleDeliveryDialogTrigger").click(function(){
		updateQuoteScheduleDelivery();
	});
	
	$("#packTrackingNo").change(function () {
		var packShipDate = $("#packTrackingNo").val();
		if (packShipDate != undefined && packShipDate != "") {
			$("#shipDate").val(packShipDate);
		}
	});
	
	$("#showPackage").click(function () {
		var packShipDate = $("#packTrackingNo").val();
		if (packShipDate != undefined) {
			var shipmentNo = $("#packTrackingNo").find("option:selected").val();
			if (shipmentNo != undefined && shipmentNo != "") {
				window.open("shipments!getShipInfo.action?shipmentNo="+shipmentNo+"&show_tab=Packages&operation_method=view");
			}
		}
	});
});