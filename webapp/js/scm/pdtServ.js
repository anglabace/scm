
$(function(){
	var pdtId = $("#psId").val();
	var psId = 	$("#psId").val();
	$("#cancelAllTrigger").click(function(){
		if(confirm("Are you sure to continue clear data?")){
			window.location.reload();
		}
	});
	$("#tabTabdhtmlgoodies_tabView1_2").click(function(){
		if($('#inventroyIframe').attr('src') == undefined || $('#inventroyIframe').attr('src') == '')
		{
			$('#inventroyIframe').attr('src', "pdtServ/pdtServInventory/showInventoryAct?psId="+psId+"&type="+pdtServType);
		}
	});
	
	$("#tabTabdhtmlgoodies_tabView1_3").click(function(){
		if($('#breakdownList_iframe').attr('src') == undefined || $('#breakdownList_iframe').attr('src') == '')
		{
			$('#breakdownList_iframe').attr('src', "pdtServ/pdtServBreakdown/showBreakdownListAct?psId="+psId+"&type="+pdtServType);
		}
	});
	$("#tabTabdhtmlgoodies_tabView1_4").click(function(){
		if($('#compositeList_iframe').attr('src') == undefined || $('#compositeList_iframe').attr('src') == '')
		{
			$('#compositeList_iframe').attr('src', "product/productComposite/showCompositeListAct?pdtId="+pdtId);
		}
	});
	$("#tabTabdhtmlgoodies_tabView1_5").click(function(){
		if($('#supplierIframe').attr('src') == undefined || $('#supplierIframe').attr('src') == '')
		{
			$('#supplierIframe').attr('src', "pdtServ/pdtServSupplier/showSupplierAct?psId="+psId+"&type="+pdtServType);
		}
	});
	$("#tabTabdhtmlgoodies_tabView1_6").click(function(){
		if($('#priceIframe').attr('src') == undefined || $('#priceIframe').attr('src') == '')
		{
			$('#priceIframe').attr('src', "product/productPrice/showPriceAct?pdtId="+pdtId);
		}
	});
	$("#tabTabdhtmlgoodies_tabView1_8").click(function(){
		if($('#miscIframe').attr('src') == undefined || $('#miscIframe').attr('src') == '')
		{
			$('#miscIframe').attr('src', "pdtServ/pdtServMisc/showMiscAct?psId="+psId+"&psNo="+$("[name='psNo']").val()+"&type="+pdtServType);
		}
	});
	$("#tabTabdhtmlgoodies_tabView1_9").click(function(){
		if($('#salesIframe').attr('src') == undefined || $('#salesIframe').attr('src') == '')
		{
			$('#salesIframe').attr('src', "product/product!/showSalesAct?pdtId="+pdtId);
		}
	});

	$("#tabTabdhtmlgoodies_tabView1_"+defaultTab).trigger("click");
	
	$("#baseForm").validate({
		invalidHandler: function(form, validator) {
	        $.each(validator.invalid, function(key,value){
	            alert(value);
	            $("[name='"+key+"']").focus();
	            return false;
	        });
		},
		rules: {
			psNo: {required:true},
			psName: {required:true},
			psDescription: {required:true}
		},
		messages: {
			psNo: {
				required: "Please specify your Catalog No"
			},
			psName: {
				required: "Please specify Name"
			},
			psDescription: {
				required: "Please specify Description"
			}
		},
		errorPlacement: function(error, element) {}		
	});

    
	$("#generalForm").validate({
		invalidHandler: function(form, validator) {
	        $.each(validator.invalid, function(key,value){
	            alert(value);
	            $("[name='"+key+"']").focus();
	            return false;
	        });
		},
		rules: {
			psQtyUom: {required:true},
			ps_storage_temperature: {required:true},
			ps_storage_humidity: {required:true},
			psShipTemperature: {required:true},
			psDomShipWeight: {required:true},
			psShipIntlShipWeight: {required:true},
			psFederalTaxCls: {required:true}
		},
		messages: {
			psQtyUom: {
				required: "Please specify Quantity Uom"
			},
			ps_storage_temperature: {
				required: "Please specify Storage Temperature"
			},
			ps_storage_humidity: {
				required: "Please specify Storage Humidity"
			},
			psShipTemperature: {
				required: "please specify Shipping Temperature"
			},
			psDomShipWeight: {
				required: "please specify Shipping Domestic Ship Weight"
			},
			psShipIntlShipWeight: {
				required: "please specify Shipping  Int'l Ship Weight"
			},
			psFederalTaxCls: {
				required: "please specify National Tax Status"
			}
		},
		errorPlacement: function(error, element) {}		
	});
	$('[name="psShipExemptFlag"]').click( function(){
		if($(this).attr("checked")){
			$('[name="psSpecShipCharge"]').val("");
			$('[name="psSpecShipCharge"]').attr("disabled",true);
		}else{
			$('[name="psSpecShipCharge"]').attr("disabled",false);
		}
	});
});

function selectCross(){

    var crossSelect = $("#cross option:selected").val();
	if(crossSelect == '')
	{
		$("#tbl").css("display","block");
		$("#tb2").css("display","none");
	}
	else
	{
		$("#tb2").css("display","block");
		$("#tbl").css("display","none");
	}
}

function generalCrossClick(){
	var psId = 	$("#psId").val();
	$('#generalCrossDialog').dialog({
		autoOpen: false,
		height: 450,
		width: 720,
		modal: true,
		bgiframe: true,
		buttons: {}
	});
	var crossSelect = $("#cross option:selected").val();
	//alert(crossSelect);
	if(crossSelect){
		var cross = $("#cross option:selected").text();
		$("#generalCrossDialog").dialog("option","title",cross);
	}else{
		$("#generalCrossDialog").dialog("option","title","Add Related Selling Item");
	}
	$('#generalCrossDialog').dialog("option","open",function(){
		var htmlStr = '<iframe name="crossDialog" id="crossDialog" src="product/product!showCrossCreateFormAct.action?relationId=' + crossSelect +'&id=' + psId + '&type=Product" height="400" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
		$('#generalCrossDialog').html(htmlStr);
	});
	$('#generalCrossDialog').dialog('open');
}

function modifyNameClick() {
	if($("[name='nameAppr']").val()){
		alert("The product name have been modified.");return;
	}
	$("#modifyNameDialog").dialog({
		autoOpen: false,
		height: 300,
		width: 600,
		modal: true,
		bgiframe: true,
		buttons: {}
	});	
	$('#modifyNameDialog').dialog("option", "open",function(){
		var name = $("[name='psName']").val();
		var htmlStr = '<iframe src="pdtServ/pdtServ/modifyNameAct?name='+name+'" height="240" width="570" scrolling="no" style="border:0px" frameborder="0"></iframe>';
		$('#modifyNameDialog').html(htmlStr);
	});
	$('#modifyNameDialog').dialog('open');
}
function modifyStatusClick() {
	if($("[name='statusAppr']").val()){
		alert("The product status have been modified.");return;
	}
	$("#modifyStatusDialog").dialog({
		autoOpen: false,
		height: 300,
		width: 600,
		modal: true,
		bgiframe: true,
		buttons: {}
	});	
	$('#modifyStatusDialog').dialog("option", "open",function() {
		var status = $("[name='psStatus']").val();
		var htmlStr = '<iframe src="pdtServ/pdtServ/modifyStatusAct?status='+status+'" height="240" width="570" scrolling="no" style="border:0px" frameborder="0"></iframe>';
		$('#modifyStatusDialog').html(htmlStr);
	});
	$('#modifyStatusDialog').dialog('open');	
}

//misc iframe checkbox control end
function pdtServSaveAll(type){
	if( $('#baseForm').valid() === false) {
		return false;	
	}
	//if( $('#generalForm').valid() === false) {
	//	return false;	
	//}
	var prefWarehouse = $("#prefWarehouse", $("#inventroyIframe").contents().find("body")).val();//added by zyl 2010-5-5
	if(!prefWarehouse && $("#inventroyIframe").attr("src")) {
		$("#tabTabdhtmlgoodies_tabView1_2").trigger("click");
		alert("Please specify Always use warehouse");
		$("#prefWarehouse", $("#inventroyIframe").contents().find("body")).focus();
		return;
	}
	var prefStorage = $("#prefStorage", $("#inventroyIframe").contents().find("body")).val();//added by zyl 2010-5-5
	if(!prefStorage && $("#inventroyIframe").attr("src")){
		$("#tabTabdhtmlgoodies_tabView1_2").trigger("click");
		alert("Please specify Storage Location");
		$("#prefStorage", $("#inventroyIframe").contents().find("body")).focus();
		return;
	}
	var altWarehouseFlag = $("#altWarehouseFlag", $("#inventroyIframe").contents().find("body")).attr("checked");
	var leadTime = $("#leadTime", $("#supplierIframe").contents().find("body")).val();
	if(!leadTime && $("#supplierIframe").attr("src")){
		$("#tabTabdhtmlgoodies_tabView1_5").trigger("click");
		alert("Please specify Lead Times(Days)");
		$("#leadTime", $("#supplierIframe").contents().find("body")).focus();
		return;
	}
	var saftyStock = $("#saftyStock", $("#supplierIframe").contents().find("body")).val();
	if(!saftyStock && $("#saftyStock", $("#supplierIframe").contents().find("body")).attr("disabled") == false){
		$("#tabTabdhtmlgoodies_tabView1_5").trigger("click");
		alert("Please specify Re-Order When Stock Is Below");
		$("#saftyStock", $("#supplierIframe").contents().find("body")).focus();
		return;
	}
	var minOrderQty = $("#minOrderQty", $("#supplierIframe").contents().find("body")).val();
	var unitCost = $("#unitCost", $("#supplierIframe").contents().find("body")).val();
	if(!unitCost && $("#unitCost", $("#supplierIframe").contents().find("body")).attr("disabled") == false){
		$("#tabTabdhtmlgoodies_tabView1_5").trigger("click");
		alert("Please specify Current Unit Cost Basis");
		$("#unitCost", $("#supplierIframe").contents().find("body")).focus();
		return;
	}
	if(altWarehouseFlag == true){
		altWarehouseFlag = "Y";
	}else{
		altWarehouseFlag = "N";
	}
	var formStr = '';
	formStr += $('#baseForm').serialize();
	formStr += "&" + $('#generalForm').serialize();
	var detail_form = $("#detailIframe").contents().find("#detail_form");
	formStr += "&" + detail_form.serialize();
	
	if($("#miscIframe").attr("src")){
		var objMisc = document.getElementById("miscIframe").contentWindow;
		formStr += "&" + objMisc.$('#miscForm').serialize();
	}
	if($("#inventroyIframe").attr("src")){
		formStr += "&prefWarehouse="+prefWarehouse;
		formStr += "&altWarehouseFlag="+altWarehouseFlag;
		formStr += "&prefStorage="+prefStorage;
	}
        
	if($("#supplierIframe").attr("src")){
		formStr += "&leadTime="+leadTime;
		formStr += "&saftyStock="+saftyStock;
		formStr += "&minOrderQty="+minOrderQty;
		formStr += "&unitCost="+unitCost;
	}
	// Intermediate -->
	if($("#breakdownList_iframe").attr('src')){
		var trObj = $("#breakdownList_iframe").contents().find("#breakdownListTable").find("tr");
		//var trlen = objBreakdown.$("#breakdownListTable" + " tr").length;
		var imdStrs = "";
		trObj.each(function(){
			var seqStr = $(this).find("span[id^='seqNo_']").text();
			imdStrs += seqStr + "<->";
			
			var idStr = $(this).find("[name='bdid']").attr("value");
			imdStrs += idStr + "<->";
			
			var catalogNoStr = $(this).find("span[id^='catalogNo_']").text();
			imdStrs += catalogNoStr + "<->";

			var itemStr = $(this).find("span[id^='item_']").text();
			imdStrs += itemStr + "<->";

			var leadTimeStr = $(this).find("span[id^='leadTime_']").text();
			imdStrs += leadTimeStr + "<->";

			var symbolStr = $(this).find("span[id^='symbol_']").text();
			imdStrs += symbolStr + "<->";

//			priceStr = $(this).find("span[id^='price_']").html();
//			imdStrs += priceStr + "<->";
			var text_quantity = $(this).find("[name='text_quantity']");	
			quantityStr = $(this).find("span[id^='quantity_']").text();
			if(!quantityStr){
				quantityStr = text_quantity.val();
			}
			imdStrs += quantityStr + "<->";
			requiredFlagStr = $(this).find("[name='requiredFlag']").attr("checked")?"Y":"N";
			imdStrs += requiredFlagStr + "<=>";
		});
		imdStrs =imdStrs.substring(0,imdStrs.length-3);
		formStr += "&imdStr=" + imdStrs;
	}
	// Intermediate end
	//composite -->
	/*if($("#compositeList_iframe").attr('src')){
		var objComposite = document.getElementById("compositeList_iframe").contentWindow;
		
		trObj = objComposite.$("#compositeListTable").find("tr");

		var cmpsStrs = "";
		trObj.each(function(){
			var seqStr = $(this).find("span[id^='seqNo_']").html();
			cmpsStrs += seqStr + "<->";
			
			var idStr = $(this).find("[name='cmpsId']").attr("value");
			cmpsStrs += idStr + "<->";
			
			var catalogNoStr = $(this).find("span[id^='catalogNo_']").html();
			cmpsStrs += catalogNoStr + "<->";

			var itemStr = $(this).find("span[id^='item_']").html();
			cmpsStrs += itemStr + "<->";

			var leadTimeStr = $(this).find("span[id^='leadTime_']").html();
			cmpsStrs += leadTimeStr + "<->";

			var symbolStr = $(this).find("span[id^='symbol_']").html();
			cmpsStrs += symbolStr + "<->";

			var priceStr = $(this).find("span[id^='price_']").html();
			cmpsStrs += priceStr + "<->";
			
			var text_quantity = $(this).find("[name='text_quantity']");	
			var quantityStr = $(this).find("span[id^='quantity_']").text();
			if(!quantityStr){
				quantityStr = text_quantity.val();
			}
			cmpsStrs += quantityStr + "<=>";
		});
		cmpsStrs =cmpsStrs.substring(0,cmpsStrs.length-3);
		formStr += "&cmpsStr=" + cmpsStrs;
	}*/
	//composite end
	defaultTab = activeTabIndex['dhtmlgoodies_tabView1'];
	var type2 = pdtServType.toLowerCase();
	//alert(formStr);
	$.ajax({
		type: "POST",
		url: type+"/"+type+"!save.action",
		data: formStr,
		dataType: 'json',
		success: function(data, textStatus){
			if(hasException(data)){
				$('#SaveAllTrigger').attr("disabled", false);	
			}else{
				alert("The "+pdtServType+" is saved successfully!");
				isSaved = true;
				if(type=="product"){
					location.href = "product/product!input.action?id="+data.id+"&defaultTab="+defaultTab;
				}else{
					location.href = "serv/service!input.action?id="+data.id+"&defaultTab="+defaultTab;
				}
			}
		},
		error: function(xhr, textStatus){
			alert("Accessing web server encounter error!");
			if(textStatus == 'timeout')
			{
				alert("Timeout!");
			}
			
			if(textStatus == 'parsererror')
			{
				tmp = xhr.responseText.split('{', 2);
				alert(tmp[0]);
				//document.write(tmp[0]);
			}
		}
	});
}
