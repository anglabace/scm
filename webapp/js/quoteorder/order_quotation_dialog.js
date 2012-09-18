Date.format = 'yyyy-mm-dd';
$(document).ready(function(){
	$('#cancelDialog').dialog({
		autoOpen: false,
		height: 250,
		width: 620,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
	//confirmStatusDialog
	$('#confirmStatusDialog').dialog({
		autoOpen: false,
		height: 340,
		width: 620,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
	
	$('#instruction_dlg').dialog({
		autoOpen: false,
		height: 420,
		width: 600,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open:function(){
			var searchNoteType = $('#instruction_dlg').dialog("option", "searchNoteType");
            var tmp = $("#tmpAdd").val();
			$('#only_email').val("");
			if(searchNoteType == "CUST_CONFIRM_EMAIL" || searchNoteType == "ORDER_CHANGE_NOTIFICATION"){
				$('#instruction_dlg').dialog("option", "title", "Send Email");
			}else if(searchNoteType == "CREATE_DUE_DATE" && tmp=="tt"){
				$('#instruction_dlg').dialog("option", "title", "Order Instruction Management");
				$('#only_email').val("Y");
			}else if(searchNoteType == "CREATE_DUE_DATE"){
				$('#instruction_dlg').dialog("option", "title", "Add Order Change Notification");
				$('#only_email').val("Y");
			}else{
				$('#instruction_dlg').dialog("option", "title", "Add Instruction/Note");
			}
			var status = $("#quorderStatus").val();
			var url = orderquoteObj.url("editNote") + "&searchNoteType="+searchNoteType+"&tmpStr="+tmp+"&status="+status;
			var htmlStr = '<iframe src="'+url+'" height="340" width="580" scrolling="auto" style="border:0px;" frameborder="0"></iframe>';
			$('#instruction_dlg').html(htmlStr);
		}
	});

	$('#instruction_update_dlg').dialog({
		autoOpen: false,
		height: 400,
		width: 600,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open:function(){
			var params = $('#instruction_update_dlg').dialog("option", "params");
			var url = orderquoteObj.url("editNote") + params;
	        var htmlStr = '<iframe src="'+url+'" height="350" width="570" scrolling="auto" style="border:0px;" frameborder="0"></iframe>';
			$('#instruction_update_dlg').html(htmlStr);
		}
	});
	
	//cancel order dialog
	$('#cancel_'+orderquoteObj.type+'_dlg').dialog({
		autoOpen: false,
		height: 260,
		width: 600,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
			var htmlStr = '<iframe src="'+orderquoteObj.type+'/'+orderquoteObj.type+'/showCancelAct?custNo=' + custNo + '&'+noName+'='+ noValue+ '" height="180" width="570" scrolling="no" style="border:0px" frameborder="0"></iframe>';

			$('#cancel_'+orderquoteObj.type+'_dlg').html(htmlStr);
		}
	});
	
	//cancel order dialog
	$('#printDialog').dialog({
		autoOpen: false,
		height: 560,
		width: 650,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
	
	$('#printOrderQuDialog').dialog({
		autoOpen: false,
		height: 500,
		width: 860,
		modal: false,
		bgiframe: true,
		buttons: {
		}
	});
	$('#TxtOrderQuDialog').dialog({
		autoOpen: false,
		height: 300,
		width: 460,
		modal: false,
		bgiframe: true,
		buttons: {
		}
	});	
	$('#DocumentOrderQuDialog').dialog({
		autoOpen: false,
		height: 300,
		width: 460,
		modal: false,
		bgiframe: true,
		buttons: {
		}
	});	
	
	$('#AllitemDetailDialog').dialog({
		autoOpen: false,
		height: 600,
		width: 700,
		modal: false,
		bgiframe: true,
		buttons: {
		
		}
	});
	
	$('#batchGeneOrderDialog').dialog({
		autoOpen: false,
		height: 560,
		width: 650,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});

	$( '#itemLookupDialog' )
	.dialog({
			autoOpen: false ,
			height: 500 ,
			width: 800 ,
			modal: true ,
			//Div top display
			bgiframe: true 
	}) ;
	
	$( '#orfCloneListDialog' )
	.dialog({
			autoOpen: false ,
			height: 240 ,
			width: 600 ,
			modal: true ,
			//Div top display
			bgiframe: true 
	}) ;
	
	$( '#itemSelectDialog' )
	.dialog({
			autoOpen: false ,
			height: 420 ,
			width: 680 ,
			modal: true ,
			//Div top display
			bgiframe: true 
	}) ;

	//save order template
	$('#saveTmplDialog').dialog({
		autoOpen: false ,
			height: 420 ,
			width: 680 ,
			modal: true ,
			//Div top display
			bgiframe: true 

	});
	
	//save order template
	$('#reopenDialog').dialog({
		autoOpen: false,
		height: 260,
		width: 600,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
		}
	});
	
	$( '#returnOrderDialog' )
	.dialog({
			autoOpen: false ,
			height: 500 ,
			width: 865 ,
			modal: true ,
			//Div top display
			bgiframe: true 
	}) ;
	
	$( '#changeStatusDialog' )
		.dialog({
				autoOpen: false ,
				height: 250 ,
				width: 580 ,
				modal: true ,
				//Div top display
				bgiframe: true 
	}) ;
	$( '#viewSpePriceDialog' )
		.dialog({
				autoOpen: false ,
				height: 370 ,
				width: 520 ,
				modal: true ,
				//Div top display
				bgiframe: true 
	}) ;
	//Item status history
	$('#itemStatusHistoryDialog').dialog({
		autoOpen: false,
		height: 300,
		width: 580,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		itemId:'',
		open: function(){
			var itemId = $(this).dialog('option', 'itemId');
			var url = orderquoteObj.url("getItemStatusHist")+"&itemId="+itemId;
			var htmlStr = '<iframe src="'+url+'" height="250" width="550" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
			$('#itemStatusHistoryDialog').html(htmlStr);
		}
	});
	
	//All items detail
	$('#allItemDetailsDialog').dialog({
		autoOpen: false,
		height: 450,
		width: 780,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
			//var itemIdNos = getItemIdNoList();
			//var url = "quorder/quorderItem/getItemDetailListAct?quorderNo="+noValue+"&codeType="+orderquoteObj.type+"&itemIdNos="+itemIdNos;
			var url = orderquoteObj.url("getItemDetailList");
			var htmlStr = '<iframe src="'+url+'" height="400" width="750" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
			$('#allItemDetailsDialog').html(htmlStr);
		}
	});
    
	$('#allItemDetailsDialogTrigger')
	.click(function(){
		if(orderquoteObj.type == "order"){
			var tmp = "Order";
		}else{
			var tmp = "Quotation";
		}
		if(parseInt(noValue) != noValue){
			var title = "All Line Items Detail in New "+tmp;
		}else{
			var title = "All Line Items Detail in "+tmp+" #"+noValue;
		}
		$('#allItemDetailsDialog').dialog('option' , 'title', title);
		$('#allItemDetailsDialog').dialog('open' );
	});
	
	//update status
	$('#updateItemStatusDialog').dialog({
		autoOpen: false,
		height: 250,
		width: 580,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
		}
	});
     $("#updateStatusDialogTrigger")
     .click(function(){
    	if($("#quorderItemId").val() == ""){
    		return;
    	}
 		var itemStatus = $("#_status").val();
			$.ajax({
			type:"get",
			url:"quorder/quorderItem/getQuorderItemStatusAct",
			data:"quorderNo="+noValue+"&itemStatus="+itemStatus+"&codeType="+orderquoteObj.type,
			dataType:"json",
			success:function(itemStatus){
				$("#updateStatus").html("");
				var tmpStatus = "";
				for(var is in itemStatus){
					if(tmpStatus == ""){
						tmpStatus = is;
					}
					$("#updateStatus").append('<option value="'+is+'">'+itemStatus[is]+'</option>');
				}
				if(tmpStatus != "")
				changeUpdateStatus(tmpStatus);
				if($("#updateStatus").html() == ""){
					$("#updateStatusDialogTrigger").attr("disabled", false);
				}else{
					$("#updateStatusDialogTrigger").attr("disabled", false);
				}
			},
			error:function(){
				alert(" System error! Please contact system administrator for help.");
			},
			async:false
		});
		if($("#updateStatusDialogTrigger").attr("disabled") == true){
			return;
		}
    	var itemNo = $("#itemNoSpan").html();
    	$('#updateItemStatusDialog').dialog("option", "title", "Modify Status For Order Or Quotation item #"+itemNo);
    	$('#updateItemStatusDialog').dialog('open');
     });
     
	$( '#updateMainStatusDialog' )
	.dialog({
			autoOpen: false ,
			height: 260 ,
			width: 560 ,
			modal: true ,
			//Div top display
			bgiframe: true
	}) ;
	
	$( '#viewStatusHistoryDialog' )
	.dialog({
			autoOpen: false ,
			height: 300 ,
			width: 550 ,
			modal: true ,
			//Div top display
			bgiframe: true 
	}) ;
	$( '#viewOrderItemsDocumentsDialog' )
	.dialog({
			autoOpen: false ,
			height: 300 ,
			width: 550 ,
			modal: true ,
			//Div top display
			bgiframe: true ,
			buttons: {
				close: function(event, ui) { 
					$("#viewOrderItemsDocumentsDialog").dialog('close');
				}
			}
	}) ;
     
	$( '#createCardsAccountDialog' )
	.dialog({
			autoOpen: false ,
			height: 280 ,
			width: 500 ,
			modal: true ,
			//Div top display
			bgiframe: true 
	}) ;
	
	$( '#packageModifyDialog' )
	.dialog({
			autoOpen: false ,
			height: 600 ,
			width: 800 ,
			modal: true ,
			//Div top display
			bgiframe: true 
	}) ;
	
 	$("#saveTmplBtn").click(
 		function(e){
 				var tmp_overrideFlag = "";
 				var overrideFlag=$("#overrideFlag").val();
 				if(overrideFlag){
 					tmp_overrideFlag = overrideFlag;
 				}
 				var curtMyTemplateCount = parseInt($("#curtMyTemplateCount").val());
 				var maxMyTemplateCount = parseInt($("#maxMyTemplateCount").val());
 				var quorderTmplName = $("#quorderTmplName").val();
 				 var bConfirm = true;		  		 
				// $("#quorderTmplName").val(trim($("#quorderTmplName").val()));
				 $("#templateListTable tr").each( function() {	
				     if (trim($(this).find("td:eq(1)").text()) == $("#quorderTmplName").val()) {
				        if (confirm("The template's name cannot be repeat. Are you sure to override?")) {
				           $("#overrideFlag").val("Y");
				        } else {
				           bConfirm = false;
				           return false;//只是跳出each循环，没有实际返回.
				        }	
				     }			 
				 });
				 //返回这个函数体的标识位， 因为在each块中不能直接退出函数体.
				 if (! bConfirm) {
				    return;
				 }
				 
				  if ($("#maxMyTemplateCount").val()<=$("#templateListTable tr").length) {
				    if ($("#overrideFlag").val()=='N') {
				      alert("Your order template's count is more than the maxium " + $("#maxMyTemplateCount").val() + "; Please delete one order template first. ");
				      return false;
				    }
				 }	
				 
 				var tmpType=orderquoteObj.type;
 				var tmpData;
 				if(tmpType=='order'){
 				tmpData="orderNo="+orderquoteObj.sessNoValue+"&tmplName="+quorderTmplName+"&overrideFlag="+tmp_overrideFlag+"&custNo="+orderquoteObj.custNo;
 				}else{
 				tmpData="quoteNo="+orderquoteObj.sessNoValue+"&tmplName="+quorderTmplName+"&overrideFlag="+tmp_overrideFlag+"&custNo="+orderquoteObj.custNo;
 				}
 				$.ajax({
 				    type: "POST",
 				    url: orderquoteObj.url("saveTemplate"),
 				    data: tmpData,
 					success:function(data)
 					{
	 					   if(data["repeatFlag"] == 1){
	 							if(confirm(data["message"])){
	 								$("#saveTmplBtn").trigger("click", ["Y"]);
	 								return;
	 							}else{
	 								return;
	 							}
	 						}
	 						var curtMyTemplateCount = data.curtMyTemplateCount;
	 						var maxMyTemplateCount = data.maxMyTemplateCount;
	 						$("#curtMyTemplateCount").val(curtMyTemplateCount);
	 						$("#maxMyTemplateCount").val(maxMyTemplateCount);
	 						alert("The template is saved.");
	 						$('#saveTmplDialog').dialog('close');
 					},
 					async: false,
 					dataType: 'json',
 					error:function(data){
 						alert("The templates saved are over the maximum number six.Please remove any-one of them  to continue your operation.");
 					}
 				    });
 		});
 		
// 		itemMoreInfoDialog
 		$('#itemMoreInfoDialog').dialog({
 			autoOpen: false,
 			height: 325,
 			width: 580,
 			modal: true,
 			bgiframe: true,
 			buttons: {
 			},
 			open: function(){
 				var catalogId = $("#itemMoreInfoDialog").dialog("option", "catalogId");
 				var url = "qu_order!showCatalogDetail.action?catalogId="+catalogId;
 				var htmlStr = '<iframe src="'+url+'" height="275" width="550" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
 				$('#itemMoreInfoDialog').html(htmlStr);
 			}
 		});
 
 		//view customer
 		// $("#viewCustomerDialog").dialog({
 			//autoOpen: false,
 			//height: 750,
 			//width: 950,
 			//modal: true,
 		//	bgiframe: true,
 		//	buttons: {
 		//	},
 			//open: function(){
 			//	var custNo = $("#viewCustomerDialog").dialog("option", "custNo");
 			//	var url = "customer/customer!edit.action?custNo="+custNo+"&operation_method=view";
 			//	var htmlStr = '<iframe src="'+url+'" height="700" width="930" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
 			//	$('#viewCustomerDialog').html(htmlStr);
 		//	}
 		//}); 
 		$("#viewCustomerTrigger").click(function(){
 		window.open("customer/customer!edit.action?custNo="+orderquoteObj.custNo+"&operation_method=view");
 			//$("#viewCustomerDialog").dialog("option", "custNo", $(this).attr("custNo"));
 			//$("#viewCustomerDialog").dialog("open");
 		});
 		
 		//confirm email update/display dialog
 		$('#searchEnzymeDialog').dialog({
 			autoOpen: false,
 			height: 500,
 			width: 300,
 			modal: true,
 			bgiframe: true,
 			sequenceId:"",
 			sequence:"",
 			buttons: {
 			},
 			open: function(){
 				if($('#searchEnzymeDialog').find("iframe").size() == 0){
 					var htmlStr = '<iframe src="qu_order_more!searchEnzyme.action" height="450" width="270" scrolling="no" style="border:0px" frameborder="0"></iframe>';
 					$('#searchEnzymeDialog').html(htmlStr);
 				}
 			},
 			close: function(){
 				var sequenceId = $("#searchEnzymeDialog").dialog("option", "sequenceId");
 				var sequence = $("#searchEnzymeDialog").dialog("option", "sequence");
 				$("#moreDetailIframe").contents().find("#"+sequenceId).val(sequence);
 			}
 		});
 		//peptide sequence select page
    	$('#seqSelectDlg').dialog({
			autoOpen: false,
			height: 700,
			width: 820,
			modal: true,
			bgiframe: true,
			buttons: {
			},
			open:function(){
				var htmlStr = '<iframe src="quoteorder/qu_order_more!viewSequenceList.action" height="650" width="790" style="border:0px" frameborder="0"></iframe>';
				$('#seqSelectDlg').html(htmlStr);
			}
		});
    	
    	$( '#updateOrderScheduleDeliveryDialog' )
    	.dialog({
    			autoOpen: false ,
    			height: 300 ,
    			width: 600 ,
    			modal: true ,
    			//Div top display
    			bgiframe: true,
    			buttons: {
     			},
     			open: function(){
     			}
    	}) ;
    	
    	$( '#updateQuoteScheduleDeliveryDialog' )
    	.dialog({
    			autoOpen: false ,
    			height: 300 ,
    			width: 600 ,
    			modal: true ,
    			//Div top display
    			bgiframe: true,
    			buttons: {
     			},
     			open: function(){
     			}
    	}) ;
});

