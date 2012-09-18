//××××××××××××××××××
//tab中的顺序
var CC = 0 ;
var PO = 1 ;
//
var TABLEDIVID = "dhtmlgoodies_tabView2";
//××××××××××××××××××

function initDisplay() {
	if ($('#paymentListTable tr').length != 0) {
		$('#paymentListBody').show();
	} else {
		$('#noPlaymentListBody').show();
	}
	changeTerm( $("#poPaymentTerm").val() ) ;
}
//显示编辑tab
function editTabPayment( sessVoucherId ){
	var paymentType = "" ;
	var currentTrObj = $("#paymentListTable tr[sessVoucherId='"+sessVoucherId+"']");
	var paymentType = currentTrObj.find( '#paymentType' ).html() ;
	var payInfo = currentTrObj.find( '#payInfo' ).val() ;
	switch( paymentType ){
		case "CC":
			showTab( TABLEDIVID , CC );
			initCC(sessVoucherId, payInfo);
			break ;
		case "PO":
			showTab( TABLEDIVID , PO );
			initPO(sessVoucherId, payInfo);
			break ;
		default: 
			showTab( TABLEDIVID , CC );
			initCC(sessVoucherId, payInfo);
	}
}

//info格式为"id1":"val1","id2":"val2",...
//containerDiv为保护此id的div容器
function autoSetVals(containerDiv, info){
	var eleArr = info.split( "," );
	var tmpArr = [];
	var tmpId;
	var tmpVal;
	if( eleArr.constructor == Array ){
		for( var i = 0 ; i < eleArr.length ; i++ ){
			tmpArr = eleArr[i].split( ':' ) ;
			tmpId = tmpArr[0];
			tmpVal = tmpArr[1];
			setValToScreen(containerDiv, tmpId, tmpVal);
		}
	}
}
//根据id，和包含其的div容器，赋值。
function setValToScreen( containerObj , eleId , eleVal ){
	if( eleId == ""){
		return false ;
	}
	if( eleId == "poPaymentTerm" ){
	//初始化term下拉框对应相关的数据。
		changeTerm( eleVal ) ;
	}else if(eleId == "ccExprDate"){
		var yearMonth = $.trim(eleVal.substring(0, 7));
		$("#ccExprDateYear").val(yearMonth.substring(0, 4));
		$("#ccExprDateMonth").val(yearMonth.substring(5, 7));
	}
	
	eleVal = eleVal.replace( '&nbsp;' , '' ) ;
	containerObj.find( '#'+eleId ).val( eleVal ) ;		
}
//显示编辑CC(Credit Card)
function initCC(sessVoucherId, payInfo){
	var containerDiv = $("#ccDiv");
	autoSetVals(containerDiv, payInfo);
	containerDiv.find("#sessVoucherId").val(sessVoucherId);
	containerDiv.find( '#buttonDiv1' ).show() ;
	containerDiv.find( '#buttonDiv2' ).hide() ;
}
//显示编辑PO
function initPO(sessVoucherId, payInfo){
	var containerDiv = $("#poDiv");
	var docSpan = $("#"+sessVoucherId+"_payDocument");
	var filePath = docSpan.find("[name='filePath']").val();
	var docName = docSpan.find("[name='docName']").val();
	var poId = docSpan.find("[name='poId']").val();
	var docId = docSpan.find("[name='docId']").val();
	if(docName && docName != "null"){
		$("#fileSpan").attr("style","display:none");
		$("#removeSpan").attr("style","display:inline");
		$("#poDocument").attr("value","");
		$("#poId").val(poId);
		$("#docId").val(docId);
		$("#docName").val(docName);
		$("#poHref").attr("href","download.action?filePath="+filePath+"&fileName="+docName);
		$("#poHref").html(docName);
	}else{
		$("#fileSpan").attr("style","display:inline");
		$("#removeSpan").attr("style","display:none");
	}
	autoSetVals(containerDiv, payInfo);
	containerDiv.find("#sessVoucherId").val(sessVoucherId);
	containerDiv.find( '#buttonDiv1' ).show() ;
	containerDiv.find( '#buttonDiv2' ).hide() ;
}
//清空form
function clearForm( thisObj ){
	var currentDivId = thisObj.parent().parent().attr( 'id' ).replace( 'ButtonDiv' , '' )+"Div" ;
	var currentFormObj = $( '#'+currentDivId+' :input') ;
	var eleIdVal = "" ;
	
	$("#removeSpan").attr("style","display:none");
	$("#fileSpan").attr("style","display:inline");
	
	currentFormObj.each(function(){
			eleIdVal = $(this).attr( 'id' ) ;
			if( eleIdVal != "authorizeButton" && eleIdVal != "newPayment" && eleIdVal != "delPayment" && eleIdVal != "cancelPayment" && eleIdVal != "modPayment"  && eleIdVal != "addPayment"  && eleIdVal != "cancelAddPayment" && eleIdVal != "paymentType" && eleIdVal != "orderNo" && eleIdVal != "paymentTermInfoStr" && eleIdVal != "removeCardsAccount" && eleIdVal != "newCardsAccount" && eleIdVal != "removePoDoc" ){
				$(this).attr( 'value' , '' ) ;
			}
	}) ;
}

function changeTerm( selectVal ){
	var selObj = $("#poPaymentTerm");
	var optionObj = selObj.find("option[value='"+selectVal+"']");
	var dueDays = optionObj.attr("dueDays");
	var netDays = optionObj.attr("netDays");
	$( '#poPaymentTerm_duedays' ).val( dueDays ) ;
	$( '#poPaymentTerm_netdays' ).val( netDays ) ;
}

function updatePayment(formId){
	$("#ccExprDate").val($("#ccExprDateYear").val()+"-"+$("#ccExprDateMonth").val()+"-01");
	if($("#"+formId).valid() == false){
		return;
	}
	var formObj = $("#"+formId);
	//ajax form post
	var options = {
		success:function(data){
			if(data == "SUCCESS"){
				alert( 'Operate succeed') ;
			}else{
				if(data)
					alert(data);
				else
					alert("System error! Please contact system administrator for help.");
			}
			//refresh the list iframe
			parent.document.getElementById("paymentListIframe").src = parent.$("#paymentListIframe").attr("src");
		},
		error: function(){
			alert("System error! Please contact system administrator for help.");
		},
		resetForm:false,
		url:orderquoteObj.url("savePayment"),
		type:"POST",
		async:false,
		dataType: "text"
	};
	formObj.ajaxForm(options);
	formObj.submit();
}

function deletePayment(sessVoucherId){
	$.ajax({
	    type: "get",
	    url: orderquoteObj.url("delPayment"),
	    data:"sessVoucherId="+sessVoucherId,
	    async: false ,
	    success: function( data ){
			if(data == "SUCCESS"){
				alert( 'Delete succeed') ;
			}else{
				if(data)
					alert(data);
				else
					alert("System error! Please contact system administrator for help.");
			}
			
		//refresh the list iframe
			parent.document.getElementById("paymentListIframe").src = parent.$("#paymentListIframe").attr("src");
		},
		error: function(){
			alert("error");
		}
	 });
}

function removePoDoc(){
	if(!confirm("Are you sure to delete the po document?")){
		return;
	}
	var sessVoucherId = $("#poForm").find("#sessVoucherId").val();
	$.ajax({
		type: "POST",
	    url: orderquoteObj.url("deletePoDoc"),
	    data: "sessVoucherId="+sessVoucherId,
	    async: false ,
	    success: function( data ){
			if(data == "SUCCESS"){
				 //alert( 'Operate succeed') ;
				var trObj = $("#paymentListTable tr[sessVoucherId='"+sessVoucherId+"']");
				trObj.find("[name='filePath']").val("");
				trObj.find("[name='docName']").val("");
				$("#removeSpan").attr("style","display:none");
				$("#fileSpan").attr("style","display:inline");
			}else{
				if(data)
					alert(data);
				else
					alert("System error! Please contact system administrator for help.");
					//refresh the list iframe
					parent.document.getElementById("paymentListIframe").src = parent.$("#paymentListIframe").attr("src");
			}
		},
		error: function(){
			alert("error");
		}
	});
}

$(document).ready( function() {
	$("#paymentListTable tr:even td").addClass("list_td2");
	initDisplay();
	//编辑
	$( '[id="descClick"]' ).click(function(){
		var sessVoucherId = $(this).attr("sessVoucherId");
		editTabPayment( sessVoucherId) ;
	});	
	//新增payment
	$('[id="addPayment"]').click(function(){
		updatePayment($(this).attr("formId"));
	});
	//清空form
	$('[id="cancelAddPayment"]').click(function(){
		clearForm( $(this) ) ;
	}) ;
	//修改payment
	$('[id="modPayment"]').click(function(){
		updatePayment($(this).attr("formId"));
	}) ;
	//修改payment中的cancel
	$('[id="cancelPayment"]').click(function(){
		var formId = $(this).attr("formId");
		var sessVoucherId = $("#"+formId).find("#sessVoucherId").val();
		editTabPayment(sessVoucherId);
	});
	//删除payment
	$('[id="delPayment"]').click(function(){
		var formId = $(this).attr("formId");
		var sessVoucherId = $("#"+formId).find("#sessVoucherId").val();
		deletePayment(sessVoucherId);
	});
	//修改视图中的新增按钮事件
	$('[id="newPayment"]').click(function(){
		clearForm( $(this) ) ;
		$(this).parent().next().show() ;
		$(this).parent().hide() ;
		$("#removeSpan").attr("style","display:none");
		$("#fileSpan").attr("style","display:inline");
	});
	//删除PO中的附件
	$('#removePoDoc').click(function(){
		removePoDoc();
	 });
	//切换payment term	
	$( '#poPaymentTerm' ).change(function(){
			changeTerm( $(this).val() ) ;
	});
	//
	$("#poDueDate").click(function(){
		//控制日历的高度
		if($("#ui-datepicker-div").get(0)){		
			$("#ui-datepicker-div").css("top", "5px");
			$("#ui-datepicker-div").css("z-index", "102");
		}
	});
	
	//Cards in Account
	$( '#cardList' ).change(function(){
			var ccInfo = $(this).val() ;
			if( ccInfo == "" ){
				return ;
			}
			var tmpArr1 = ccInfo.split(",") ;
			var tmpArr2;
			for(  var i = 0 ; i < tmpArr1.length ; i++ ){
				tmpArr2 = tmpArr1[i].split(":") ;
				if( tmpArr2[0] == "id" ){
					continue ;
				}else if(tmpArr2[0] == "ccExprDate"){
					var yearMonth = $.trim(tmpArr2[1].substring(0, 7));
					$("#ccExprDateYear").val(yearMonth.substring(0, 4));
					$("#ccExprDateMonth").val(yearMonth.substring(5, 7));
					$("#"+tmpArr2[0] ).val( ) ;
				}else{
					$( "#"+tmpArr2[0] ).val( $.trim(tmpArr2[1]) ) ;
				}
			}
	});
	//日期
	$('.ui-datepicker').each(function(){
		$(this).datepicker({
					dateFormat: 'yy-mm-dd',
					changeMonth: true,
					changeYear: true
		});
	});
	//form验证
	$('#ccForm').validate({
		errorClass:"validate_error",
		highlight: function(element, errorClass) {
			$(element).addClass(errorClass);
	    },
	    unhighlight: function(element, errorClass, validClass) {
	        $(element).removeClass(errorClass);
	    },
	    invalidHandler: function(form, validator) {
			 $.each(validator.invalid, function(key,value){
	            alert(value);
	            return false;
	        });
		 },
		 rules: {
			 ccNo: { required:true },
			 ccHolder: { required:true }
		 },
		 messages: {
			 ccNo: { required:"Card Number is required" },
			 ccHolder: { required:"Name on the Card is required" }
		 },
		 errorPlacement: function(error, element) {
		 }
	});
	
	$('#poForm').validate({
		errorClass:"validate_error",
		highlight: function(element, errorClass) {
			$(element).addClass(errorClass);
	    },
	    unhighlight: function(element, errorClass, validClass) {
	        $(element).removeClass(errorClass);
	    },
	    invalidHandler: function(form, validator) {
			 $.each(validator.invalid, function(key,value){
	            alert(value);
	            return false;
	        });
		 },
		 rules: {
			 poNumber: { required:true },
			 poAmount:{required:true, number:true, max:99999999.999499}
		 },
		 messages: {
			 poNumber: { required:"P.O. Number is required" },
			 poAmount:{required:"P.O. Amount is required.", number:"P.O. Amount should be a number.", max:"P.O. Amount should be Less than 99999999.9995."}
		 },
		 errorPlacement: function(error, element) {
		 }
	});
	
});