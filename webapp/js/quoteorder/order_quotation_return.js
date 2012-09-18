$.fn.formClear = function(){
	var _f = $(this) ;
	$( ':text , textarea' , _f ).each(function(){
			$(this).val( '' ) ;
	});
}

function returnOrderInit(){
	if( $('#selectReturnOrder').val() != "-1" ){
		$('#returnProcess').show() ;
		$('#returnDel').show() ;
	}
}

function calculateSTRefund(){
	var shipRefund = 0 ;
	var totalRefund = 0 ;
	var refund = 0 ;
	$( '#returnOrderItemList tr' ).each(function(){
			refund += parseFloat( $(this).find('#refund').attr( 'innerHTML' ) ) ;
		}
	) ;
	
	oNoobj = new Number( refund ) ;
	$( '#totalRefund' ).val( oNoobj.toFixed( 2 ) ) ;
}


function returnTdClick( thisObj ){
	var tableTd = thisObj ;
	if( tableTd.html().search( 'INPUT' ) != -1 || tableTd.html().search( 'input' ) != -1 ){
		return ;
	}
	
	var trObj = thisObj.parent("td").parent("tr") ;
	
	var shippedQty = trObj.find( '#shippedQty' ).html() ;
	var shipSize = trObj.find( '#shipSize' ).html() ;
	
	if( thisObj.attr( 'id' ) == "returnQty" ){
		trObj.find( '#returnSize' ).html( shipSize ) ;
	}else{
		trObj.find( '#returnQty' ).html( shippedQty ) ;
	}
	
	var inputName = tableTd.attr( 'id' )+"Input";
	var tdVal = tableTd.html().replace( '&nbsp;' , '' );
	tableTd.html( '<input name="'+inputName+'" id="'+inputName+'" type="text" class="NFText" value="'+tdVal+'" size="1"/>');
	tableTd.find( 'input' ).change(function(){
		var qtyInput = $(this) ;
		
		if( inputName == "returnQtyInput" ){
			var returnQty = $(this).val() ;
			var returnSize = trObj.find( '#returnSize' ).html() ;
			if( !checkReturnNum( returnQty , shippedQty , "Return Qty can't more than Shipped Qty" ) ){
				$(this).val( shippedQty ) ;
				return ;
			}
			qtyInput.parent('span').html( returnQty ) ;
		}else{
			var returnQty = trObj.find( '#returnQty' ).html() ;
			var returnSize = $(this).val() ;
			if( !checkReturnNum( returnSize , shipSize , "Return Size can't more than Shipped Size" ) ){
				$(this).val( shipSize ) ;
				return ;
			}
			qtyInput.parent('span').html( returnSize ) ;
		}

		var itemNo = trObj.find( '#itemNo' ).html() ;
		calculateRefund( returnQty , returnSize , itemNo , trObj ) ;
		trObj.find( '[name="exchangeFlag"]' ).attr( 'checked' , false ) ;
	}) ;
}

function updateReturnItemList( trObj ){
	if( trObj == "" ){
		return ;
	}
	var returnQty = trObj.find( '#returnQty' ).html() ;
	var returnSize = trObj.find( '#returnSize' ).html() ;
	var returnReason = trObj.find( '[name="returnReason"]' ).val() ;
	var exchangeFlag = "" ;
	if( trObj.find( '[name="exchangeFlag"]' ).attr( 'checked' ) == true ){
		exchangeFlag = "Y" ;	
	}else{
		exchangeFlag = "" ;
	}
	
	var refund = trObj.find( '#refund' ).html() ;
	var itemId = trObj.find( '[name="itemId"]' ).val() ;
	var selectVal = $( '#selectReturnOrder' ).val() ;
	var paramVal = "&returnQty="+returnQty+"&returnSize="+returnSize+"&returnReason="+returnReason+"&exchangeFlag="+exchangeFlag+"&refund="+refund ;
	$.ajax({
		type: "POST",
		url: orderquoteObj.url("updateReturnItem"),
		data: "rmaNo="+selectVal+"&itemId="+itemId+paramVal ,
		async: false,
		success: function( data ){
			//
		},
		error:function(){
			alert("Failed to modify the item.");
			//刷新页面
			window.location.reload();
		}
	}) ;
	calculateSTRefund() ;
}

function checkReturnNum( inputVal , compareVal , comment ){
	if( inputVal > compareVal ){
		alert( comment ) ;
		return false ;
	}
	return true ;
}

function calculateRefund( returnQty , returnSize , itemNo , trObj ){
	$.ajax({
		type: "POST",
		url: orderquoteObj.url("calReturnRefund"),
		data: "itemNo="+itemNo+"&returnQty="+returnQty+"&returnSize="+returnSize ,
		async: false ,
		success: function( data ){
			var refund = data ;
			trObj.find('#refund').html( refund ) ;
			updateReturnItemList( trObj ) ;
		}
	});
}

function saveReturnOrder( saveType ){
	calculateSTRefund();
	if( !checkForm() ){
		return ;
	}
	var selectVal = $( '#selectReturnOrder' ).val() ;
	//New return order		
	var paramVal = "&exprDate="+$('#exprDate').val() +"&shipRefund="+$('#shipRefund').val() +"&totalRefund="+$('#totalRefund').val() +"&note="+$('#note').val()+"&saveType="+saveType ;
	if( saveType == "process" ){
		$.ajax({
			type: "POST",
			url: orderquoteObj.url("saveReturn"),
			data: "rmaNo="+selectVal+paramVal ,
			dataType:"json" ,
			async: false ,
			success: function( data ){
				if ( data.status == "SUCCESS" ){
					alert ("Process sucessfully.");
					parent.document.getElementById("itemListIframe").src = orderquoteObj.url("getItemList");
					parent.document.getElementById("totalIframe").src = orderquoteObj.url("showTotal");					
				}else{
					alert(data.msg);
				}
				window.parent.$( '#returnOrderDialog' ).dialog( 'close' ) ;
			},
			error:function(){
				alert("System error! Please contact system administrator for help.");
			}
		}) ;
	}else{
		var itemIdList = getItemIdListParam();
		$.ajax({
			type: "POST",
			url: orderquoteObj.url("saveReturn"),
			data: "rmaNo="+selectVal+paramVal+itemIdList,
			async: false ,
			dataType:"json",
			success: function( data ){
				if ( data.status == "SUCCESS" ){
					if( saveType == "del" ){
						alert ("RMA #"+data.rmaNo+" delete sucessfully.");
					}else{
						alert ("RMA #"+data.rmaNo+" has been created.");
					}
					window.parent.$( '#returnOrderDialog' ).dialog( 'close' ) ;	
				}else{
					alert(data.msg);
				}
			},
			error:function(){
				alert("System error! Please contact system administrator for help.");
			}
		}) ;
	}
}

function getItemIdListParam(){
	var itemIdList = [];
	$("[name='itemId']").each(function(i, n){
		itemIdList.push("&itemIdList="+$(n).val());
	});
	return itemIdList.join("");
}

function checkForm(){
	var msg = "" ;
	if( $('#shipRefund').val() == "" ){
		msg += "Please input Shipping Refund\r\n" ;
	}
	if( $('#totalRefund').val() == "" ){
		msg += "Please input Total Refund\r\n" ;
	}
	if( msg != "" ){
		alert( msg ) ;
		return false ;
	}
	return true ;
}

$(function(){
	returnOrderInit() ;

	$( '#selectReturnOrder' ).change(function(){
		window.location.href = orderquoteObj.url("showReturn")+"&rmaNo="+$(this).val();
	}) ;
	
	$('#returnCancel').click(function(){
		window.parent.$( '#returnOrderDialog' ).dialog( 'close' ) ;
	}) ;

	$( '#delItemList' ).click(function(){
		$( '[name="itemId"]' ).each(function(){
			if($(this).attr( "checked" ) == true ){
				$("#returnOrderItemList tr[itemId='"+$(this).val()+"']").remove();
			}
		});
		calculateSTRefund() ;
	});
	
	$("#allItemCheck").click(function(){
		$( '[name="itemId"]' ).attr("checked", $(this).attr("checked"));
	});

	$( '[name="returnReason"]' ).live( 'change' ,function(){
		trObj = $(this).parent( 'td' ).parent( 'tr' ) ;
		updateReturnItemList( trObj ) ;
	}) ;
	
	$( '[id*="returnSize"]' ).live( 'click' ,function(){
			returnTdClick( $(this) ) ;
	});
	
	$( '[id*="returnQty"]' ).live( 'click' ,function(){
			returnTdClick( $(this) ) ;
	}) ;
	
	$( '[name="exchangeFlag"]' ).live( 'click' ,function(){
		trObj = $(this).parent( 'td' ).parent( 'tr' ) ;
		trObj.find( '#refund' ).html(0) ;
		if( trObj.find( '#returnQtyInput' ).val() != undefined ){
			trObj.find( '#returnQty' ).html( trObj.find( '#returnQtyInput' ).val() ) ;
		}
		if( trObj.find( '#returnSizeInput' ).val() != undefined ){
			trObj.find( '#returnSize' ).html( trObj.find( '#returnSizeInput' ).val() ) ;
		}
		if( !$(this).attr( 'checked' ) ){
			var returnQty = trObj.find( '#returnQty' ).html() ;
			var returnSize = trObj.find( '#returnSize' ).html() ;
			var itemNo = trObj.find( '#itemNo' ).html() ;
			calculateRefund( returnQty , returnSize , itemNo , trObj ) ;
			return ;
		}
		updateReturnItemList( trObj ) ;
	});

	$( '#returnProcess' ).click( function(){
		saveReturnOrder( 'process' ) ;
	}) ;
	
	$( '#returnSave' ).click( function(){
		saveReturnOrder( 'modify' ) ;
	}) ;
	
	$( '#returnDel' ).click( function(){
		saveReturnOrder( 'del' ) ;
	});
	
});
