//清空item 的 discount
function clearDiscount(prmtCode){
	prmtCode = escape(prmtCode);
	var postParam = "quorderNo="+orderquoteObj.sessNoValue+"&codeType="+orderquoteObj.type+"&prmtCode="+prmtCode ;
	var ajaxUrl = "qu_order!clearDiscount.action" ;
	$.ajax({
		type: "POST",
		url: ajaxUrl ,
		dataType: "text" ,
		data: postParam ,
		async: false ,
		error:function(){
			alert( "The request send fail!" ) ;
		},
		success:function(msg) {
			//刷新item list 页面
			parent.document.getElementById("itemListIframe").src = orderquoteObj.url("getItemList");
		}
	});
}
//清空添加的Gift
function clearGift(prmtCode) {
	prmtCode = escape(prmtCode);
	var postParam = "quorderNo="+orderquoteObj.sessNoValue+"&codeType="+orderquoteObj.type+"&prmtCode="+prmtCode ;
	var ajaxUrl = "qu_order!clearGift.action" ;
	$.ajax({
		type: "POST",
		url: ajaxUrl ,
		dataType: "text" ,
		data: postParam ,
		async: false ,
		error:function(){
			alert( "The request send fail!" ) ;
		},
		success:function(msg) {
			//刷新item list 页面
			parent.document.getElementById("itemListIframe").src = orderquoteObj.url("getItemList");
		}
	});
}
//清空coupon
function clearCoupon(couponId) {
	var flag = false;
	var postParam = "quorderNo="+orderquoteObj.sessNoValue+"&codeType="+orderquoteObj.type+"&couponId="+couponId ;
	var ajaxUrl = "qu_order!clearCoupon.action" ;
	$.ajax({
		type: "POST",
		url: ajaxUrl ,
		dataType: "text" ,
		data: postParam ,
		async: false ,
		error:function(){
			alert( "The request send fail!" ) ;
		},
		success:function(msg) {
			//刷新item list 页面
			flag = true;
		}
	});
}
function calAllItemPrice(prmtCode){
	prmtCode = escape(prmtCode);
	var flag = false;
	$.ajax({
		type: "POST",
		url: orderquoteObj.url("calAllItemPrice")+"&prmtCode="+prmtCode,
		dataType: "json",
		async: false ,
		success: function(data, textStatus){
			if(hasException(data)){	
				flag = false;
			}else{
				if(data.message.toLowerCase()=='success') {
					flag = true;
				} 
				alert(data.message) ;
				parent.document.getElementById("itemListIframe").src = orderquoteObj.url("getItemList");
			}
		},
		error: function(xhr, textStatus){
			flag = false;
		    alert("System error! Please contact system administrator for help.");
		}
	});
	return flag ;
}

function calAllItemPrice2(){
	var flag = false;
	$.ajax({
		type: "POST",
		url: orderquoteObj.url("calAllItemPrice"),
		dataType: "json",
		async: false ,
		success: function(data, textStatus){
			if(hasException(data)){	
				flag = false;
			}else{
				if(data.message.toLowerCase()=='success') {
					flag = true;
				} 
			}
		},
		error: function(xhr, textStatus){
			flag = false;
		    alert("System error! Please contact system administrator for help.");
		}
	});
	return flag ;
}

function sourceSelectChage(){
	 $("#orderSrc_hidden").val($("#itemForm_orderSource").val());
    if ($("#itemForm_orderSource").val() == '') {
	    $("#prmtCode_sel").get(0).options.length = 0;
		$("#prmtCode_sel").get(0).options.add(new Option("None", ""));
		$('#txt_new_1').hide();
       return;
    }
    $.ajax({
			type: "POST" ,
			url: orderquoteObj.ajaxUrls.getPrmtListBySource,
			dataType: "json" ,
			async:false,
			data: "sourceId=" + $("#itemForm_orderSource").val(),
			error:function() {
				alert( "The request send fail!" ) ;
			},
			success: function(jsonObj) {
			  $("#prmtCode_sel").get(0).options.length = 0;
			  $("#prmtCode_sel").get(0).options.add(new Option("None", ""));
			  $.each(jsonObj, function(name, value) {
		            $("#prmtCode_sel").get(0).options.add(new Option(value, value));
		      });
		      $("#prmtCode_sel").find("option[value='"+$("#prmtCode_hidden").val()+"']").attr("selected", true);
			  $('#txt_new_1').hide();
		   }
	});
}
$(function(){	
    //初始化页面时加载数据;
	if($("#prmtCode_hidden").val() != ''){
		$("#prmtCode_sel").attr("disabled",true);
		$("#prmtCode_btn").attr("value","Remove");
		$("#itemForm_orderSource").attr("disabled",true);
	}

	if(!$("#itemForm_prmtCode").attr("value") && $("#hdnPrmtCode").val()) {		
		$("#itemForm_prmtCode").attr("disabled",true);
		$("[name='prmtCode']").attr("disabled",true);
		$("#prmtApply").attr("value","Remove");
		$("#txt_new_1").css("display","inline");
		$("#itemForm_prmtCode option:last").attr("selected",true);
		$("[name='prmtCode']").val($("#hdnPrmtCode").val());
	}	
	if($("#couponCode").val() == ''||$("#couponCode").val()==null) {
		$("#deleteSpan").hide();
	}
	refresh_changed_form();
	
	function refresh_changed_form () {
		_f = document;
		 $(':text, :password, textarea', _f).each(function() {  
		        $(this).attr('_value', $(this).val());  
		    });
		    $(':checkbox, :radio', _f).each(function() {         
		        var _v = this.checked ? 'on' : 'off';         
		        $(this).attr('_value', _v);         
		    });
		       
		    $('select', _f).each(function() { 
		        if (this.options.length > 0 && this.selectedIndex != -1) {	          
		          $(this).attr('_value', this.options[this.selectedIndex].value);       
		        } else {
		        }
		    });            
	}
	
	function is_form_changed() {   
		var f = document;
	    var changed = false;         
	    $(':text, :password, textarea', f).each(function() {         
	        var _v = $(this).attr('_value');         
	        if(typeof(_v) == 'undefined')   _v = '';         
	        if(_v != $(this).val()) changed = true;         
	    });         
	       
	    $(':checkbox, :radio', f).each(function() {         
	        var _v = this.checked ? 'on' : 'off';       
	        if(_v != $(this).attr('_value')) changed = true;         
	    });
	        
	    $('select', f).each(function() {         
	        var _v = $(this).attr('_value');         
	        if(typeof(_v) == 'undefined')   _v = '';         
	        if(_v != this.options[this.selectedIndex].value) changed = true;       
	    });         
	    return changed;         
	}
	
	$( '#itemForm_MemoSelect' ).change(
		function()
		{
			$( '#itemForm_Memo' ).attr( 'value' , $( '#itemForm_MemoSelect' ).val()  ) ;
		}
	) ;
	/**Source 下拉框选项发生变化时触发 **/
	$("#itemForm_orderSource").change( function() {
	     $("#orderSrc_hidden").val($("#itemForm_orderSource").val());
	     if ($("#itemForm_orderSource").val() == '') {
		    $("#prmtCode_sel").get(0).options.length = 0;
			$("#prmtCode_sel").get(0).options.add(new Option("None", ""));
			$('#txt_new_1').hide();
	        return;
	     }
	     $.ajax({
				type: "POST" ,
				url: orderquoteObj.ajaxUrls.getPrmtListBySource,
				dataType: "json" ,
				async:false,
				data: "sourceId=" + $("#itemForm_orderSource").val(),
				error:function() {
					alert( "The request send fail!" ) ;
				},
				success: function(jsonObj) {
				  $("#prmtCode_sel").get(0).options.length = 0;
				  $("#prmtCode_sel").get(0).options.add(new Option("None", ""));
				  $.each(jsonObj, function(name, value) {
			            $("#prmtCode_sel").get(0).options.add(new Option(value, value));
			      });
				  $('#txt_new_1').hide();
			   }
		});
	}); 
	
	//绑定Promotion Code下拉改变选中值的事件
	$("#prmtCode_sel").change(function(){
		$("#prmtCode_btn").attr("value","Apply");
		if ($("#prmtCode_sel option:selected").text()=='Other'){
		    $("#prmtCode_manual").val("");
			$('#txt_new_1').show();
		} else {
			$('#txt_new_1').hide();
		}
	});

	   //绑定Promotion Code->Apply(Remove) 按钮事件
	$('#prmtCode_btn').click(function(){
		var prmtCode = "";
		var prmtCode_hidden = $("#prmtCode_hidden").val();
		var prmtCodeMan = $("#prmtCode_manual").val();
		var prmtCodeText = $("#prmtCode_sel option:selected").text();
		var prmtCodeSel =$("#prmtCode_sel option:selected").val();
		if( $('#prmtCode_btn').attr('value') != "Apply" ) {//Promotion Code Remove 操作
			 sourceSelectChage();
             $("#itemForm_orderSource").attr("disabled", false);
			 $('#prmtCode_btn').attr( 'value', 'Apply' ) ;
			 $('#prmtCode_hidden').val("");
			 $("#prmtCode_sel").attr("disabled",false);
			 $('#txt_new_1').hide();
			 $("#prmtCode_manual").val("");
			 $("#prmtCode_manual").attr("disabled",false);
			 $( '#prmtCode_sel option:eq(0)').attr( "selected" , true ) ;						
			 clearDiscount(prmtCode_hidden) ;
			 clearGift(prmtCode_hidden);
		} else {
		    //select选中的值为 'NULL'
			if(prmtCodeSel == ''){
				alert("Please select one the promotion code.");
				return;
			} else if(prmtCodeText == "Other"){//select选中的值为 'Other'
				prmtCode = prmtCodeMan;
				if($.trim(prmtCode) == '') {
					//alert("Please Enter Promotion Code.");
					return;
				}
				$.ajax({
					type: "POST" ,
					url: orderquoteObj.ajaxUrls.isPromotionExist,
					dataType: "text" ,
					async:false,
					data: "prmtCode=" + prmtCode,
					error:function() {
						alert( "The request send fail!" ) ;
					},
					success: function(msg) {//返回值为'Y'或为'N'
						if(msg == 'N'){
							alert("The promotion code doesn't exist.");
							return;
						}
						$('#prmtCode_hidden').val(prmtCode);
					}
				});
			} else {
				
				prmtCode = prmtCodeSel;
				$('#prmtCode_hidden').val(prmtCode);
			}
			if( ! calAllItemPrice(prmtCode) ){
				$(this).attr( 'value', 'Apply' ) ;
				$('#prmtCode_hidden').val("");
				$("#prmtCode_sel").attr("disabled",false);
				$('#prmtCode_manual').attr("disabled",false);
				$('#txt_new_1').css("display","none");
				$( '#prmtCode_sel option:eq(0)' ).attr( "selected" , true ) ;
				clearDiscount(prmtCode) ;
				clearGift(prmtCode);
			}  else {
				$("#prmtCode_sel").attr("disabled",true);
				$('#prmtCode_btn').attr( 'value', 'Remove' ) ;
		        $("#itemForm_orderSource").attr("disabled",true);
			}			
		}
	}); //end of '$('#prmtCode_btn').click';
	
	$("#prmtCodeLook_btn").click(function(){
		var prmtCodeSel =$("#prmtCode_sel option:selected").val();
		if(prmtCodeSel == ''){
			alert("Please select one the promotion code.");
			return;
		}
		prmtCodeSel = escape(prmtCodeSel);
		var href = '/scm/quote_order_promotion!input.action?prmtCode_view='+prmtCodeSel + '&operation_method=view';
		parent.$('#promotion_edit_dialog').dialog("option","open",function(){
			var htmlStr = '<iframe id="promotion_edit_iframe" src="'+href+'" height="460" width="750" scrolling="yes" style="border:0px" frameborder="0"></iframe>';
			parent.$('#promotion_edit_dialog').html(htmlStr);
		});
		parent.$('#promotion_edit_dialog').dialog("open");
	});
	
	 //绑定Coupon Code->Apply(Remove) 按钮事件
	 //end of '$('#couponCode_btn').click';
	
	
	
}) ;

//Order, Quote共用的Quote Reference.
function viewQuoteRefDialog (refNoSelectId,  url) {
	parent.$("#viewQuoteRefDialog").dialog({
		autoOpen: false,
		height: 750,
		width: 950,
		modal: true,
		bgiframe: true,
		buttons: {}
	});
	var refNo = $("#" + refNoSelectId).val();
	var urlStr = url + refNo + "&operation_method=view";
	parent.$('#viewQuoteRefDialog').dialog("option", "open", function() {	
		var htmlStr = '<iframe src="'+urlStr + '" height="700" width="930" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
		parent.$('#viewQuoteRefDialog').html(htmlStr);
	});
	parent.$('#viewQuoteRefDialog').dialog('open');
}

//Order, Quote共用的Order Reference.
function viewOrderRefDialog (refNoSelectId,  url) {
	parent.$("#viewOrderRefDialog").dialog({
		autoOpen: false,
		height: 750,
		width: 950,
		modal: true,
		bgiframe: true,
		buttons: {}
	});
	var refNo = $("#" + refNoSelectId).val();
	var urlStr = url + refNo + "&operation_method=view";
	parent.$('#viewOrderRefDialog').dialog("option", "open", function() {	
		var htmlStr = '<iframe src="'+urlStr + '" height="700" width="930" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
		parent.$('#viewOrderRefDialog').html(htmlStr);
	});
	parent.$('#viewOrderRefDialog').dialog('open');
}

//Order, Quote共用的查看status history的窗口.
function viewStatusHistory(url) {
	parent.$("#viewStatusHistoryDialog").dialog({
		autoOpen: false,
		height: 320,
		width: 560,
		modal: true,
		bgiframe: true,
		open: function(){
			htmlStr = '<iframe _src="'+ url + '" height="100%" width="100%" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			parent.$('#viewStatusHistoryDialog').html(htmlStr);
		}
	});
}
//Order, Quote共用的查看CouponCode的窗口.
function viewCoupon(custNo) {
	$("#viewCouponCodeDialog").dialog({
		autoOpen: false,
		height: 350,
		width: 470,
		modal: true,
		bgiframe: true,
		buttons: {}
	});
	var urlStr = "qu_order!viewCoupon.action?filter_EQI_custNo="+custNo;
	$('#viewCouponCodeDialog').dialog("option", "open", function() {	
		var htmlStr = '<iframe src="'+urlStr + '" height="300" width="450" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
		$('#viewCouponCodeDialog').html(htmlStr);
	});
	$('#viewCouponCodeDialog').dialog('open');
}

function deleteCoupon() {
	if($("#couponCode").val()==""||$("#couponCode").val()==null) {
		alert("Please enter the gift card number.");
		return;
	} 
	var couponCodeSelVal = $("#couponCode").val();
	var couponId = couponCodeSelVal;
	var couponIdArray = $("#couponId").val().split(",");
	var flag = false;
	for(var i=0;i<couponIdArray.length;i++) {
		if(couponIdArray[i]==couponId) {
			couponIdArray.splice(i,1);
			flag = true;
		}
	}
	if(!flag) {
		return;
	}
	if(!applyCouponCode(couponIdArray.toString()))  {
		alert("Failed to remove the gift card.");
		return;
	} else {
		$('#couponCode option:selected').remove(); 
		$("#couponId").attr("value",couponIdArray.toString());
	}
	if($("#couponCode").val()==""||$("#couponCode").val()==null) {
		$("#deleteSpan").hide();
	}
	
}

//apply couponCode
function applyCouponCode(couponIds){
	var param="&couponId="+couponIds;
	var flag = false;
	$.ajax({
		type: "POST",
		url: orderquoteObj.url("applyCouponCode")+param,
		dataType: "json",
		async: false ,
		success: function(data, textStatus){
			if(hasException(data)){	
				flag = false;
				alert("Add gift card failure");
			}else{
				if(data.message.toLowerCase()=='success') {
					flag = true;
				} 
				alert(data.message) ;
			}
		},
		error: function(xhr, textStatus){
			flag = false;
			alert("Add gift card failure");
		}
	});
	return flag ;
}