function initEdit(){
	// 新增时候不能操作select
	if(parseInt(orderquoteObj.sessNoValue) != orderquoteObj.sessNoValue ){
		$("#actionListId").attr("disabled", true);
	}
	// 初始化tab
	if($("#dhtmlgoodies_tabView1").attr("id")){
		var defaultTab = activeTabIndex['dhtmlgoodies_tabView1'];
		var tmpTab = getTabByIndex(defaultTab, '');
		$(tmpTab).trigger("click");
		$("#addrIframe").attr("_src", orderquoteObj.url("showAddress"));
		if(document.getElementById("addrIframe")!=null){
			document.getElementById("addrIframe").src = $("#addrIframe").attr("_src");
		}
		if(document.getElementById("totalIframe")!=null){
			document.getElementById("totalIframe").src = orderquoteObj.url("showTotal");
		}
	}
}
 //不同tab进行切换
function tabClick(){
	var index = activeTabIndex['dhtmlgoodies_tabView1'];
	var tmpFlag = true;
	if(index == 1){
		tmpFlag = updateTabContent(index);
	}
//	if(index == 0){	
//		tmpFlag = updateTabContent(index);
//	}else if(index == 4 || index == 1){
//		tmpFlag = updateTabContent(index);
//	}
	if(tmpFlag == false){
		return;
	}
	var idArray = this.id.split('_');
	var nowIndex = idArray[idArray.length-1].replace(/[^0-9]/gi,'');
	var packagingIndex = getTabIndexByTitle("Packaging")[1];
	var instructionIndex = getTabIndexByTitle("Instructions/Notes")[1];
	var addrIndex = getTabIndexByTitle("Addresses")[1];
	var addrMultiIndex = getTabIndexByTitle("Multi-Ship/Gift Msg")[1];
	var salesInfoIndex = getTabIndexByTitle("Sales Information")[1];
	if(orderquoteObj.type == "order"){
		var totalIndex = getTabIndexByTitle("Order Total")[1];
	}else{
		var totalIndex = getTabIndexByTitle("Quotation Total")[1];
	}
	// Check if Multi-ship/Gift Msg Tab enable
	if(nowIndex == addrIndex){
		document.getElementById("addrIframe").src = orderquoteObj.url("showAddress");
	}else if(nowIndex == addrMultiIndex){
		if($('#changeMultiAddr', $('#addrIframe').contents().find("body")).val() == 1){
			return;
		}
		var itemId = $("#itemDetailIframe").contents().find("#itemId").val();
		document.getElementById("multiAddrIframe").src = orderquoteObj.url("showMultiAddress")+"&itemId="+itemId;
	}else if( nowIndex == packagingIndex){
		if($('#haveShipTo', $('#addrIframe').contents().find("body")).val() == 0){
			return;
		}
	}else if(nowIndex == salesInfoIndex){
		if($('#salesInformationIframe').attr('src') == undefined || $('#salesInformationIframe').attr('src') == '') {
			$('#salesInformationIframe').attr('src', $("#salesInformationIframe").attr("_src"));
		}
	}
	if(packagingIndex == nowIndex){
		document.getElementById("packagingIframe").src = $("#packagingIframe").attr("_src");
	}else if(totalIndex == nowIndex){
		// document.getElementById("totalIframe").src =
		// orderquoteObj.url("showTotal")
	}else if(instructionIndex == nowIndex)
// if($("#quorderStatus").val() == "CN" ){
// return;
// }
		document.getElementById("instructionIframe").src = $("#instructionIframe").attr("_src");
	showTab(this.parentNode.parentNode.id,idArray[idArray.length-1].replace(/[^0-9]/gi,''));
}
// 更新item detail
function updateItemDetail(){
	if(window.frames["itemDetailIframe"].updateItemDetail){
		window.frames["itemDetailIframe"].updateItemDetail();
	}
}
// 更新sales infomation
function updateSalesInfo(){
	if(window.frames["salesInformationIframe"].saveSaleInfo){
		window.frames["salesInformationIframe"].saveSaleInfo();
	}
	
}
function updateStdPeptide(){
	if(window.frames["moreDetailIframe"].saveStdPeptide){
		window.frames["moreDetailIframe"].saveStdPeptide();
	}
}

function updateMoreInfo(){
	$("#saveMoreDetailTrigger", $("#moreDetailIframe").contents().find("body")).trigger("click");
	if($("#validateFlag", $("#moreDetailIframe").contents().find("body")).val() == "0"){
		return false;
	}else{
		return true;
	}
}

function changeUpdateStatus(status){
	if(status == "CN" || status == "OH" || status == "VD"){
		$("#reasonTr1").show();
		$("#reasonTr2").show();
	}else{
		$("#reasonTr1").hide();
		$("#reasonTr2").hide();
	}
}

function updateTabContent(tabIndex){
	var tmpFlag = true;
	if(tabIndex == 0) {
		tmpFlag = updateItemDetail();
	} else if(tabIndex == 1){
		tmpFlag = updateMoreInfo();
	}
//	if(tabIndex == 0)
//		tmpFlag = updateItemDetail();
//	else if(tabIndex == 1){
//		tmpFlag = updateMoreInfo();
//	}else if(tabIndex == 4){
//		tmpFlag = updateSalesInfo();	
//	}
	return tmpFlag;
}

/**
 * 把刚刚新增的order加入到Wo Operation中.
 * 
 * @author wangsf
 * @param orderNo
 *            创建Order成功返回的orderNo
 */
function addToWorkOrderOperation(orderNo) {
    var paramData = "";
    paramData = "sessId=" + $("#workOrderSessNo_hidden").val();
    paramData = paramData + "&orderNo=" + orderNo;
	$.ajax({
	    type: "POST",
	    url:  "workorder_operation!selectOrderForWO.action",
	    data: paramData,
		success:function(data){
	       isSaved = true;
		   window.location = $("#wo_path_hidden").val();
		}
	});
	                
}

//add by zhanghuibin
function Block(){
		$.funkyUI({
			showDialog:false
		});
}

function unBlock(){
   /* $.unfunkyUI({
        showDialog:false
    });*/

    //$(window.self).unblock();
   $.unfunkyUI();
}

function saveTimeOut(){
   if(window.frames["totalIframe"].document.readyState != 'loaded' && window.frames["totalIframe"].document.readyState != 'complete'){
        alert("Page is loading, please wait a minute.");
        return false;
    }
    Block();
   setTimeout(save, 20);
}

//order 保存
function save() {
    var internalOrder = false;
    if (! $("#wo_path_hidden").val()) {// 为空
        internalOrder = false;
    } else {// 创建的是内部订单
        internalOrder = true;
    }
	updateItemDetail();
	var defaultTab = activeTabIndex['dhtmlgoodies_tabView1'];
	updateSalesInfo();
	updateTargetDate();
	var tmpFlag = updateMoreInfo();
	if(tmpFlag == false){
		var moreDetailIndex = getTabIndexBy("More Detail");
       	if(moreDetailIndex != -1){
       		showTab('dhtmlgoodies_tabView1', moreDetailIndex);
       	}
        unBlock();
		return;
	}
	// totalIframe相关参数
	if(parseInt(orderquoteObj.sessNoValue) == orderquoteObj.sessNoValue){
		// 新增时候不进行计算
		window.frames["totalIframe"].autoCalTotalBeforeSave();
	}
	var paramData = "";
	var shippingType = $("#totalIframe").contents().find('#shippingType').val() ;
	paramData += "&shippingType="+shippingType;
	var shippingRule = $("#totalIframe").contents().find('#shippingRule').val() ;
	paramData += "&shippingRule="+shippingRule;
    paramData += "&keyInfoChanged=" + $("#keyInfoChanged").val();
	// shipAccount
	var shippingAccount = $("#totalIframe").contents().find('#shipAccount').val() ;
	paramData += "&shippingAccount="+shippingAccount;
	$.ajax({
	    type: "POST",
	    url: orderquoteObj.url("save")+"?"+orderquoteObj.sessNoName+"="+orderquoteObj.sessNoValue+"&defaultTab="+defaultTab,
	    dataType:"json",
	    data:paramData,
		success:function(data){
			if(hasException(data)){
				// $('#saveAllTrigger').attr("disabled", false);
                unBlock();
			}else{
				 alert(data.message);
                unBlock();
				 if (internalOrder) {
				    // 把刚刚新增的order加入到Wo Operation中.
				    addToWorkOrderOperation(data.no);
	             } else {
                	isSaved = true;	
				    location.href = orderquoteObj.url("edit")+data.no+"&defaultTab="+defaultTab+"&rand="+Math.random()+"&operation_method=edit";
				 }
			}
		},
		async: false
	});
//    unBlock();
	return true;
}
// 检查confirm之前的条件
function checkConfirmItems(status, statusText){
	var retFlag = false;
	var internalOrderFlag = $("#internalOrderFlag").val();
	$.ajax({
		url:orderquoteObj.url("checkConfirm"),
		data:"status="+status+"&statusText="+statusText+"&internalOrderFlag="+internalOrderFlag,
		async:false,
		success:function(data){
			if(data == "SUCCESS"){
				retFlag = true;
			}else{
				if(data)
					alert(data);
				else
					alert("System error! Please contact system administrator for help.");
			}
		},
		error:function(data){
			alert(data);
		}
	});
	return retFlag;
}

function confirmItems(status, statusText){
	var selectedIds = [];
	$("#itemListIframe").contents().find("[name='itemId']").each(function(i, n){
		if($(n).parent().parent().find("[name='changeStatus']").html() != "CN")
		selectedIds.push($(n).val());
	});
    var orderStatus = $("#quorderStatus").val();
    var paramStr = "";
    if(orderStatus == "RV" || orderStatus == "OP"){
        var item_update_reason = $("#item_update_reason");
        var order_update_reason = $("#order_update_reason");
        if(item_update_reason != undefined && $.trim(item_update_reason.val())==""){
            alert("Please input the 'Item Update Reason'.");
            return false;
        }else if(order_update_reason != undefined && $.trim(order_update_reason.val()) == ""){
            alert("Please input the 'Order Update Reason'.");
            return false;
        }else{
            paramStr = "&update_reason_sel=" + $("#update_reason_sel").val() +"&item_update_reason=" + item_update_reason.val() + "&order_update_reason=" + order_update_reason.val();
        }
    }
	var orderItemIdStr = selectedIds.toString();
	if(orderItemIdStr == ""){
		alert("No item is added");
		return;
	}
	// 检查confirm条件
	if( checkConfirmItems(status, statusText) == false){
		return;
	}
	var calResult = window.frames["totalIframe"].autoCalTotalBeforeSave();
	if (calResult != undefined && calResult == false) {
		return;
	}
	if(!confirm("Are you sure to confirm? Please check bill-to address and ship-to address is right or not.")){
		return;
	}
	// 先生成targetDate
	updateTargetDate();
	$.ajax({
		url:orderquoteObj.url("confirm"),
		data:"status="+status+"&statusText="+statusText + paramStr,
		async:false,
		success:function(data){
			if(data == "SUCCESS" || data == "ErpCheckStockException"){
				if (data == "ErpCheckStockException") {
					alert("Could not retrieve inventory data.");
				}
				$("#confirmStatusDialog").dialog("close");
				changeOrderQuoteStatus(status);
				// 刷新iframe
				document.getElementById("itemListIframe").src = orderquoteObj.url("getItemList");
				document.getElementById("salesInformationIframe").src = $("#salesInformationIframe").attr("src");
				if (document.getElementById("totalIframe").getAttribute("src") != null) {
					document.getElementById("totalIframe").src = orderquoteObj.url("showTotal");
				} else if (document.getElementById("totalIframe").getAttribute("_src") != null) {
					document.getElementById("totalIframe")._src = orderquoteObj.url("showTotal");
				}
			} else{
				if(data)
					alert(data);
				else
					alert("System error! Please contact system administrator for help.");
			}
		},
		error:function(data){
			alert(data);
		}
	});
}

function updateTargetDate(){
	var rtFlag = false;
	$.ajax({
		url:orderquoteObj.url("updateTargetDate"),
		async:false,
		success:function(data){
			if(data == "SUCCESS"){
				rtFlag = true;
			}else{
				if(data)
					alert(data);
				else
					alert("System error! Please contact system administrator for help.");
			}
		},
		error:function(data){
			alert(data);
		}
	});
	return rtFlag;
}

$(document).ready(function(){
    // 取消Cancel按钮事件
    $("#cancel_btn").click( function() {
        if (! $("#wo_path_hidden").val()) {// 为空
          window.location.reload();
        } else {
          window.location = $("#wo_path_hidden").val();
        }
    
    });
    // 更新order转态为CC
    $("#infoUpdateStatusConfirm").click(function(){
    	confirmItems("CC", "Customer Confirmed");
	});
    
	$("#actionListId").change(function(){
		$("#tmpAdd").val('ff');
		var slt = this;
		var item = slt.options[slt.selectedIndex].value;
		if (item == "GEN_ORDER_CFM_EMAIL" || item == "GEN_FOLLOWUP_EMAIL"){
			$('#only_email').val("Y");
			$('#instruction_dlg').dialog('open');
		}else if(item == "PRINT_"+orderquoteObj.type.toUpperCase()){// 打印Quote
																	// order
	       var url = orderquoteObj.ajaxUrls.print;
	       $("#printOrderQuDialog").dialog('option','open',function(){
				var htmlStr = '<iframe src="'+url+ '" height="100%" width="840" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
				$('#printOrderQuDialog').html(htmlStr);
			});
	       $("#printOrderQuDialog").dialog("open");
	       isSaved = true;
		} else if(item=="Allitemdetail"){ 
			   isSaved = true; 
			   var url2 = orderquoteObj.ajaxUrls.Allitemdetail;			 
			   $("#AllitemDetailDialog").dialog('option','open',function(){
					var htmlStr = '<iframe src="'+url2+ '" height="100%" width="100%" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
					$('#AllitemDetailDialog').html(htmlStr);
				});
			   
		       $("#AllitemDetailDialog").dialog("open");  
		       
		} else if(item=="PRINT_TXT"){
		      isSaved = true; 
		      var url2 = orderquoteObj.ajaxUrls.PrintTxt;
		      $("#TxtOrderQuDialog").dialog('option','open',function(){
					var htmlStr = '<iframe src="'+url2+ '" height="300" width="440" scrolling="auto" style="border:0px" frameborder="0"></iframe>';

					$('#TxtOrderQuDialog').html(htmlStr);
				});
		       $("#TxtOrderQuDialog").dialog("open");  
		       isSaved = false;
		       $("#TxtOrderQuDialog").dialog("close");  
		}else if(item=="PRINT_DOCUMENT"){
		      isSaved = true; 
		      var url2 = orderquoteObj.ajaxUrls.PrintDocument;
		      $("#DocumentOrderQuDialog").dialog('option','open',function(){
					var htmlStr = '<iframe src="'+url2+ '" height="300" width="440" scrolling="auto" style="border:0px" frameborder="0"></iframe>';

					$('#DocumentOrderQuDialog').html(htmlStr);
				});
		       $("#DocumentOrderQuDialog").dialog("open");  
		       isSaved = false;
		       $("#DocumentOrderQuDialog").dialog("close");  
		}else if(item == "cancel_"+orderquoteObj.type){
			 $('#cancelDialog').dialog('open');
		}else if(item == "SAVE_TMPL"){
			$('#saveTmplDialog').dialog('open');
			 var htmlStr = '<iframe src="' + orderquoteObj.url("getTemplateList") + '" height="100%" width="100%" scrolling="no" style="border:0px" frameborder="0"></iframe>';			
			$('#saveTmplDialog').html(htmlStr);
		}else if(item == "REPEAT_NEW_"+orderquoteObj.type.toUpperCase()){
			if (confirm("Proceed to repeate this "+ orderquoteObj.type +"? Not saved data will be lost.")){
				$.ajax({
					type: "POST",
					url : orderquoteObj.ajaxUrls.repeat,
					success:function(data)
					{
						if(data=='null') {
							alert("Failed to Repeate this "+orderquoteObj.type);
							return;
						}
						if (! isNaN(data)){
							// success
							alert ("New "+orderquoteObj.type+" "+data+" created.");
							url = orderquoteObj.ajaxUrls.edit + data+"&operation_method=edit";
							window.location.href = url+"&custNo="+orderquoteObj.custNo;
						}else{
							alert(data);
						}
						return;
					},
					async: false
					});
			}else{
				return;
			}
		}else if(item == "RETURN_"+orderquoteObj.type.toUpperCase()){
			$('#returnOrderDialog').dialog('open'); 
			var htmlStr = '<iframe src="'+orderquoteObj.url("showReturn")+'" height="440" width="840" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			$('#returnOrderDialog').html(htmlStr);
		}else if(item == "REOPEN"){
			$('#reopenDialog').dialog('open'); 
		    var htmlStr = '<iframe src="' + orderquoteObj.ajaxUrls.reopenOrder + '" height="100%" width="100%" scrolling="no" style="border:0px" frameborder="0"></iframe>';			
			$('#reopenDialog').html(htmlStr);
			// alert(htmlStr);
		}else if(item == "CUST_CONFIRMED"){
			$("#confirmStatusDialog").dialog("open");
			// confirmItems("CC", "Customer Confirmed");
		}else if(item == "CREATE_DUE_DATE"){
			// 先生成targetDate
// updateTargetDate();
			$('#instruction_dlg').dialog('option', "searchNoteType", item);
			$('#instruction_dlg').dialog('open'); 
		} else if(item == "BATCH_GENE_"+orderquoteObj.type.toUpperCase()){
			$('#batchGeneOrderDialog').dialog('open'); 
			var htmlStr = '<iframe src="'+orderquoteObj.url("showReturn")+'" height="440" width="840" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			$('#batchGeneOrderDialog').html(htmlStr);
		}
		slt.selectedIndex = 0;// added by zyl 2009-04-22
	});
	
	$("#searchInstructionBtn").click(function(){
		var search_type = $('#instruction_slt').val();
		document.getElementById('instructionIframe').src = 
			document.getElementById('instructionIframe').src+"&search_type="+search_type;
	});
	
	/** *********yulu mod************** */
	    $("#updateStatusConfirm").click(function(){
	    	var itemId = $("#itemDetailIframe").contents().find("#itemId").val();
	    	var fromVal = $("#itemDetailIframe").contents().find("#status").val();
	   		var status = $("#updateStatus").val();
	   		var statusText = $("#updateStatus option:selected").html();
	   		var statusNote = $("#updateNote").val();
	   		if(statusNote == ""){
	   			alert("Please enter the Note.");
	   			return;
	   		}
	   		if($("#updateStatusReason").parents().find("#reasonTr2").get(0).style.display == "none"){
	   			var statusReason = "";
	   		}else{
	   			var statusReason = $("#updateStatusReason").val();
	   		}
    		$.ajax({
    				type:"get",
    				url:orderquoteObj.url("updateItemStatus"),
    				data:orderquoteObj.sessNoName+"="+orderquoteObj.sessNoValue+"&itemId="+itemId+"&status="+status
    					+"&statusText="+statusText+"&statusNote="+statusNote+"&statusReason="+statusReason,
    				dataType:"json",
    				success:function(data){
						for(var tmpId in data){
						   	window.frames["itemListIframe"].updateStatus(tmpId, data[tmpId]["itemStatus"]);
						}
    				},
    				error:function(){
// alert("Fail to get the right status");
    				},
    				async:false
    		});
    		document.getElementById("itemDetailIframe").src = orderquoteObj.url("getItemDetail")+"&itemId="+itemId;
	   		$('#updateItemStatusDialog').dialog('close');
	   	});
	    
	    $("#updateStatusCancel").click(function(){
	    	$('#updateItemStatusDialog').dialog('close');
	    });
	    
	    $("#updateStatus").click(function(){
	    			changeUpdateStatus(this.value);
	    });
	    
	    $("#templateListDelImg").click(function(){
	    	var tmpIds = [];
	    	$("#templateListTable").find("[name='templateId'][checked]").each(function(i, n){
	    		tmpIds.push($(n).val());
	    	});
	    	var tmplIdStr = tmpIds.toString();
	    	if(!tmplIdStr){
	    		alert("Please select the item to continue your operation.");
	    		return;
	    	}else{
	    		if(!confirm("Are you sure to delete?")){
	    			return;
	    		}
	    	}
    		$.ajax({
    			url:"quote!delTemplate.action?tmplIdStr="+tmplIdStr+"&codeType="+orderquoteObj.type,
    			type:"get",
    			success:function(data){
    				if(data == "1"){
    					for(var i=0; i<tmpIds.length; i++){
    						$("#templateListTable").find("[name='templateId'][value='"+tmpIds[i]+"']").parent().parent().parent().remove();
    					}
    				}else{
    					if(data)
    						alert(data);
    					else
    						alert("Failed to delete the template.");
    				}
    			},
    			error:function(){
    				alert("Failed to delete the template.");
    			}
    		});
	    });

	    $("#templateListCheckAll").click(function(){
	    	if($(this).attr("checked")){
	    		$("#templateListTable").find("[name='templateId']").attr("checked", true);
	    	}else{
	    		$("#templateListTable").find("[name='templateId']").attr("checked", false);
	    	}
	    });
		
		function isNum(str) {
			str = str.replace(/\s/g,""); 
				alert( str ) ;
			if(str.length > 0)
			{
				if(isNaN(str))
				{   
					return false;
				}
			}
		}
		
		$( '#AddItemTrigger' ).click( function() {
			var tmpFlag = updateMoreInfo();
			if(tmpFlag == false){
				var moreDetailIndex = getTabIndexBy("More Detail");
		       	if(moreDetailIndex != -1){
		       		showTab('dhtmlgoodies_tabView1', moreDetailIndex);
		       	}
		        unBlock();
				return;
			}
			$('#itemLookupDialog').dialog( 'open' ) ;
			$( '#itemLookupDialog' ).attr( 'innerHTML' , '<iframe  src="qu_order!showProductItemPriceForm.action?custNo=' + orderquoteObj.custNo + '&quorderNo='+orderquoteObj.sessNoValue+'&catalogNoList=&codeType='+orderquoteObj.type+'" allowTransparency="true" width="100%" height="100%" frameborder="0" scrolling="Yes" ></iframe>' ) ;
		}) ;

		$( '#closeDialog' ).click( function() {
			window.parent.$( '#itemLookupDialog' ).dialog( 'close' )  ;
		});

		$( ':checkbox[name*="itemAdd"]' ).click(function(){
				$('#sellingNotes').attr( "value" , this.value ) ;
		}) ;

		$('#closeDialog').click(function(){
				var itemSelectDialogObj = window.parent.$('#itemSelectDialog') ;
				itemSelectDialogObj.dialog( 'close' ) ;
		});

		$( '#btn_convert2order' ).click(function(){  
			var quorderStatus = $("#quorderStatus").val();
			var fullName=$("#fullName").val();
			if(quorderStatus != "NW" && quorderStatus != "RV" && quorderStatus != "OP"){
				alert("The quote with status '"+quorderStatus+"' is not allowed to be converted to order.");
				return;
				
			}
			if(parseInt(orderquoteObj.sessNoValue) != orderquoteObj.sessNoValue){
				// 新建不允许convert
				alert("This quotation is not allowed to be converted to an order. Please check if the quotation status is right.");
				return;
			}
			
			if (confirm("Proceed to convert to order?")){				
				$.ajax({
					  type: "GET",
					  url: "quote!convertToOrder.action",
					  data: "quoteNo="+orderquoteObj.sessNoValue,
					  success: function(data){
						  if(hasException(data)){
							  
						  }else if (! isNaN(data.quoteNo)){
							// success
							alert ("Convert to order "+data.quoteNo+" sucessfully.");
							window.location.href='order!edit.action?orderNo='+data.quoteNo+'&custNo='+orderquoteObj.custNo+'&fullName='+fullName+"&operation_method=edit";
						}else{
							alert (data); 
							return;
						} 
					  },
					  dataType: "json",
					  async: false 
				});
			}
		}) ;
		initEdit();
	}) ;