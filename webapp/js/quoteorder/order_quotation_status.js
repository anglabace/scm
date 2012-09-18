$(function(){
	
	$('#updateOrderStatusConfirm').click(function(){
			var itemStatus = "" ;
			var statusNote = $( '#itemForm_StatusNote' ).val() ;
			if( statusNote == "" ){
				alert( "Please input note" ) ;
			}else{
				if($("#orderReasonTr1").get(0).style.display == "none"){
					var statusReason = "";	
				}else{
					var statusReason = $("#itemForm_StatusReason").val();
				}
				window.parent.$("#salesInformationIframe").contents().find( "#itemForm_statusText" ).attr( 'value' , $("#itemForm_Status").find("option:selected").text() ) ;
				window.parent.$("#salesInformationIframe").contents().find( "#itemForm_status" ).attr( 'value' , $("#itemForm_Status").find("option:selected").val() ) ;
				window.parent.$("#salesInformationIframe").contents().find( "#itemForm_statusReason" ).attr( 'value' , statusReason ) ;
				window.parent.$("#salesInformationIframe").contents().find( "#itemForm_statusNote" ).attr( 'value' , statusNote ) ;
				window.parent.$( '#updateMainStatusDialog' ).dialog( 'close' )  ;
			}
	});

	$('#updateStatusCancel').click(function(){
			window.parent.$( '#updateMainStatusDialog' ).dialog( 'close' )  ;
	});
	
	function orderStatusReaInit(){
		var orderStatus = $("#itemForm_Status").val();
		if(orderStatus == "CN" || orderStatus == "VD" || orderStatus == "OH" || orderStatus =="CO"){
			$("#orderReasonTr1").show();
			$("#orderReasonTr2").show();
		}else{
			$("#orderReasonTr1").hide();
			$("#orderReasonTr2").hide();
		}
	}
	
	$("#itemForm_Status").change(function(){
		orderStatusReaInit();
	});
	orderStatusReaInit();
}) ;