//获得一个字符串中的浮点数
function getFloat(str){
	if(!str){
		return 0;	
	}
	var newStrs = [];
	var regularStr = "0123456789.";
	for(var i=0; i<str.length; i++){
		if(regularStr.indexOf(str[i]) > -1){
			newStrs.push(str[i]);
		}
	}
	return newStrs.join("");
}

function clearTotalAll () {
	clearShippingTotalForm();
	clearAboutAccount();
}

//清空shipping form
function clearShippingTotalForm(){
	$("#packageTotal").val("");
	$("#billableWeightTotal").val("");
	$("#zone").val("");
	$("#costTotal").val("");
	$("#shipAccount").val("");
	$("#shipAccountFlag").attr("checked", false);
}
//清空billing total form
function clearTotal(){
	$("#shipAmt").val("");
	$("#total").val("");
	$("#handlingFee").val("");
	$("#subtotal").val("");
	$("#discount").val("");
	$("#couponValue").val("");
	$("#tax").val("");
	$("#totalPayments").val("");
	$("#prePaymentNum").text(0);
	$("#prePaymentSymbol").text( "" );
}
//
function clearAboutAccount(){
	$("#costTotal").val("");
	clearTotal();
}
//初始化total页面
function initTotal(){
	initShippingTotal();
	//shipAccount
	if($("#shipAccount").val() != ""){
		$("#shipAccountFlag").attr("checked", true);
		$("#shipAccount").attr("readonly", false);
	}else{
		$("#shipAccountFlag").attr("checked", false);
		$("#shipAccount").attr("readonly", true);
	}
	//新建不能生成shipping
	if(parseInt(orderquoteObj.sessNoValue) == orderquoteObj.sessNoValue){
		$("#calculateShipping").attr("disabled", false);
	}else{
		$("#calculateShipping").attr("disabled", true);
		$("#calculateTotal").attr("disabled", true);
		$("#replace_btn").attr("disabled", true);
	}
	//不能编辑状态
	var quorderStatus = $("#quorderStatus").val();
	if( quorderStatus == "NW" || quorderStatus == "RV"|| quorderStatus == "OP" || quorderStatus == "BO" || quorderStatus == "PB" ){
		$("#changeShipViaSel").attr("disabled", false);
		$("#paymentNoteSpan").show();
	}else{
		$("#changeShipViaSel").attr("disabled", true);
		$("#shipAccountFlag").attr("disabled", true);
		$("#toCurrency").attr("disabled", true);
		$("#calculateShipping").attr("disabled", true);
		$("#calculateTotal").attr("disabled", true);
		$("#paymentNoteSpan").hide();
	}
}

//显示 shipping total
function initShippingTotal(){
	$.ajax({
	    type: "get",
	    url: orderquoteObj.url("getShippingTotal"),
	    async: true,
		dataType:"json" ,
	    success: function(data){
			if(hasException(data)){
				return;
			}else if(data == null){
				return;
			}else{
				for(var o in data){
					if(data[o] == null){
						data[o] = "";
					}
				}
			}
			$("#packageTotal").val(data["packageTotal"]);
			$("#billableWeightTotal").val(data["billableWeightTotal"]);
			$("#zone").val(data["zone"]);
			//costTotal
			var costTotal = data["costTotalShow"];
			if(data["currencySymbol"]){
				$("#costTotal").val(data["currencySymbol"]+costTotal);
				$("#symbol").val(data["currencySymbol"]);
			}else{
				$("#costTotal").val(costTotal);
			}
			$( '#costTotalVal' ).val(costTotal) ;
			/*
			$("#shipAccount").val(data["shipAccount"]);
			if(data["shipAccount"]){
				$("#shipAccountFlag").attr("checked", true);
				$("#shipAccount").attr("readonly", false);
			}else{
				$("#shipAccountFlag").attr("checked", false);
				$("#shipAccount").attr("readonly", true);
			}*/
			if(data["shipVia"]){
				$("#shipAccountFlag").attr("disabled", false);
			}else{
				$("#shipAccountFlag").attr("disabled", true);
			}
			$("#changeShipViaSel").find("option[value='"+data["shipVia"]+"']").attr("selected", true);
			$("#changeShipViaSel").attr("_h", data["shipVia"]);
		},
		error: function(){
			alert("System error! Please contact system administrator for help.");
		}
	});
}

function calculateShippingClick(shipFlag) {
	if($("#shipAccountFlag").attr("checked") && (!$("#shipAccount").val())){
		alert("Please enter the Account No");
		return;
	}
	var shipAccount = $("#shipAccount").val();	
	if($("#shipAccountFlag").attr("checked")){
		var shFlag = 1;
	}else{
		var shFlag = 0;
	}
	
	var ajaxSuccessFlag = 1;
	$.ajax({
	    type: "post",
	    url: orderquoteObj.url("calShipping"),
	    data: "shipAccount="+shipAccount+"&shipAccountFlag="+shFlag+"&costTotal="+$("#costTotal").val()+"&shipFlag="+shipFlag,
	    dataType:"json" ,
	    async: false ,
	    success: function( data ){
			if (hasException(data)) {
				ajaxSuccessFlag = 0;
			} else if( data.SUCCESS == "SUCCESS" ){
	    		initShippingTotal();
	    	} else {
	    		ajaxSuccessFlag = 0;
	    		alert( data ) ;
	    	}
		},
		error: function(){
			ajaxSuccessFlag = 0;
			alert("System error! Please contact system administrator for help.");
		}				
	});
	return ajaxSuccessFlag;
}

function changeShipAccount(){
	var shipAccount = $("#shipAccount").attr("value");
	if(!$("#shipAccount").attr("value")){
		$("#shipAccountFlag").attr("checked",false);
		$(this).attr("readonly", true);
	}
	$.ajax({
		url:orderquoteObj.url("changeShipAccount"),
		data:"shipAccount="+shipAccount,
		success:function(data){
			if(data == "SUCCESS"){
				if(shipAccount == ""){
					clearTotal();
				}else{
					clearAboutAccount();
				}
			}else{
				alert("System error! Please contact system administrator for help.");
			}
		},
		error:function(data){
			alert("System error! Please contact system administrator for help.");
		},
		async:false
	});
}

function changeShipMethod(){
	var selObj = $("#changeShipViaSel");
	var _h = selObj.attr("_h");
	if(confirm('Apply this ship method to all items?')){ 
		 var shipMethod = selObj.val();
		 $.ajax({
			 url:orderquoteObj.url("changeShipMethod"),
			 data:"shipMethod="+shipMethod,
			 success:function(data){
			 	if(data == "SUCCESS"){
			 		$("#changeShipViaSel").attr("_h", $("#changeShipViaSel").val());
			 		parent.$("#itemDetailIframe").contents().find("#shipMethod").find("option[value='"+shipMethod+"']").attr("selected", true);
			 		$("#shipAccountFlag").attr("disabled", false);
			 		clearShippingTotalForm();
			 		clearTotal();
			 	}else{
			 		 //back to the primitive data
			 		selObj.find("option[value='"+_h+"']").attr("selected", true);
			 		if(data)
			 			alert(data);
			 		else
			 			alert("System error! Please contact system administrator for help.");
			 	}
		 	 },
			 error:function(data){
		 		 //back to the primitive data
		 		selObj.find("option[value='"+_h+"']").attr("selected", true);
		 		 alert("Failed to change the ship Method ");
		 	 },
			 async:false
		 });
	}else{
		 //back to the primitive data
		 selObj.find("option[value='"+_h+"']").attr("selected", true);
	}
}

function calAllItemPrice(){
	var flag = false;
	$.ajax({
		type: "POST",
		url: orderquoteObj.url("calAllItemPrice"),
		async: false ,
		dataType:"json",
		error:function( jsonData ){
			alert( jsonData["responseText"] ) ;
			flag = false;
		},
		success:function( data ) {
			if(data.message == "Success"){
				flag = true;
			}else{
				alert(data.message);
			}
			parent.document.getElementById("itemListIframe").src = orderquoteObj.url("getItemList");
		}
	});
	return flag ;
}

function autoCalTotalBeforeSave(){
		return calculateTotalClick("N");
}

function calculateTotalClick(alertFlag){
//	if( $("#costTotal").val() == "" ){
		if(!calculateShippingClick(alertFlag)){
			return false;
		}
//	}
	//更新item的price
	var ret = calAllItemPrice() ;
	if( !ret ){
		return false;
	}	
	$.ajax({
	    type: "POST",
	    url: orderquoteObj.url("calBill"),
	    async: false ,
		dataType:"json" ,
	    success: function( jsonData ){
	    	if(jsonData != undefined && jsonData !="" && jsonData != null ){
	    		$( '#subtotal' ).val( jsonData.symbol+jsonData.quorderSubtotalShow ) ;
	    		$( '#discount' ).val( jsonData.symbol+jsonData.quorderDiscountShow ) ;
	    		$( '#shipAmt' ).val( jsonData.symbol+jsonData.quorderShipAmtShow ) ;
	    		$( '#tax' ).val( jsonData.symbol+jsonData.quorderTaxShow ) ;
	    		$( '#total' ).val( jsonData.symbol+jsonData.quorderTotalShow ) ;
	    		if (jsonData.handlingFee != null && jsonData.handlingFee != "" 
	    			&& jsonData.handlingFee != undefined && jsonData.handlingFee != "null") {
	    			$( '#handlingFee' ).val( jsonData.symbol+jsonData.handlingFee ) ;
	    		} else {
	    			$( '#handlingFee' ).val("") ;
	    		}
	    		$( '#totalPayments' ).val( jsonData.symbol+jsonData.quorderTotalPaymentsShow ) ;
	    		$( '#couponValue').val(jsonData.symbol+jsonData.quorderTotalCouponValueShow);
	    		$( '#totalType option[value="quorder"]' ).attr( 'selected' , true ) ;
	    		//prepayment
		    	$("#prePaymentSymbol").text( jsonData.symbol ) ;
	    		$("#prePaymentNum").text( jsonData.prePayment) ;
	    		if(alertFlag == "Y"){
	    			//alert("Calculate Total successfully.");
	    		}
	    	}else{
	    		if(alertFlag == "Y"){
	    			alert( "fail to calculate Total" ) ;
	    		}
	    		return false;
	    	}
		},
		error: function(){
			alert("System error! Please contact system administrator for help.");
			return false;
		}			
	}) ;
}

function replaceOrder(){
	if(orderquoteObj.type == "quote"){
		return;
	}
	 //check return item
	$.ajax({
	    type: "POST",
	    url: orderquoteObj.url("replaceOrder"),
	    async: false ,
	    success: function(data){
			if (data == "SUCCESS"){
				alert ("Replace to order "+orderquoteObj.sessNoValue+" sucessfully.");
				parent.window.location.href = orderquoteObj.url("edit")+orderquoteObj.sessNoValue;
			}else{
				if(data)
					alert(data);
				else
					alert("System error! Please contact system administrator for help.");
			}
		},
		error: function(){
			alert("System error! Please contact system administrator for help.");
		}				
	});
}

$(document).ready(function(){
	initTotal();
	$('#calculateShipping').click(function(){
		if(calculateShippingClick("Y")){
			//alert("Succeed");
		}
	});
	
	$("#shipAccount").change(function(){
		if($(this).val()){
			clearAboutAccount();
		}else{
			clearTotal();
		}
		changeShipAccount();
	});
	
	$("#changeShipViaSel").change(function(){
		changeShipMethod();
	});
	
	$("#ViewPaymentsTrriger").click(function(){
		$('#orderTotalPaymentsDialogView').dialog('open');
		
	}); 
	$('#orderTotalPaymentsDialogView').dialog({
		autoOpen: false,
		height: 600,
		width: 800,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
			var url= orderquoteObj.url("caltotalPaymentslist"); 
			//alert(url+"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			var htmlStr = '<iframe src="'+url+'" height="500" width="780" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
			$('#orderTotalPaymentsDialogView').html(htmlStr);
		}
	});
	
	$("#shipAccountFlag").click(function(){
		if(!$(this).attr("checked")){
			var tmpAccount = $("#shipAccount").val();
			$("#shipAccount").val("");
			$("#shipAccount").trigger("change");
			$(this).attr("checked",false);
			if(tmpAccount){
				clearAboutAccount();
			}
			parent.$("#topShipAccount").val("");
			$("#shipAccount").attr("readonly", true);
		}else{
			$("#shipAccount").attr("readonly", false);
		}
	});
	
	$('#calculateTotal').click(function(){
		calculateTotalClick("Y") ;
	}) ;
	
	$("#costTotal2").change(function(){
		var costTotal2 = $(this).val();
		var costTotal2F = "";
		if(costTotal2 != ""){
			costTotal2F = getFloat(costTotal2);
		}
		$.ajax({
			type: "POST",
		    url: orderquoteObj.url("changeCost"),
		    data:"customizedCost="+costTotal2F,
		    async: false,
		    success: function( data ){
				
			},
			error: function(data){
				alert("System error! Please contact system administrator for help.");
			}
		});
	});
	
	$('#replace_btn').click(function(){
		replaceOrder();
	});
	
	$( '#toCurrency' ).change(function(){
		var lastCurrency = $(this).attr("_h");
		var toCurrency = $(this).val() ;
		
		var costTotalVal = $("#costTotalVal").val();
		var shipAccount = $("#shipAccount").val();	
		
		$.ajax({
		    type: "POST",
		    url: orderquoteObj.url("changeCurrency"),
		    data:"toCurrency="+toCurrency,
		    async: false ,
		    success: function( data ){
				if(data == "SUCCESS"){
					//重新计算Shipping fee
					calculateShippingClick("Y");
					//更新Item列表的Discount
					calAllItemPrice();
				    //刷新item list
					parent.document.getElementById("itemListIframe").src = orderquoteObj.url("getItemList");
					//重新加载shipping信息。
					if (parent.document.getElementById("totalIframe").getAttribute("src") != null) {
						parent.document.getElementById("totalIframe").src = orderquoteObj.url("showTotal");
					} else if (parent.document.getElementById("totalIframe").getAttribute("_src") != null) {
						parent.document.getElementById("totalIframe")._src = orderquoteObj.url("showTotal");
					}
				}else{
					if(data)
						alert(data);
					else
						alert("System error! Please contact system administrator for help.");
					$(this).find("option[value='"+lastCurrency+"']").attr("selected", true);
				}
			},
			error: function(){
				alert("System error! Please contact system administrator for help.");
				$(this).find("option[value='"+lastCurrency+"']").attr("selected", true);
			}
		});
	}) ;
	
});