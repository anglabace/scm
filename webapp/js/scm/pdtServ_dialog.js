$(document).ready(function(){
	var psId = 	$("#psId").val();
         var sessionPSID= $("#sessionPSID").val();      
	// ship area add
	$('#shipAreaAddDialog').dialog({
		autoOpen: false,
		height: 360,
		width: 600,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
			//var pdtId = globalCfgObj.pdtId;
			var url = "product/product/inventory!input.action?psId="+psId+"&type="+pdtServType+"&sessionPSID="+sessionPSID;
			var htmlStr = '<iframe src="'+url+'" height="310" width="570" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			$('#shipAreaAddDialog').html(htmlStr);
                        
		},
		close: function(obj){
		}
	});
	//ship area edit
	$('#shipAreaEditDialog').dialog({
		autoOpen: false,
		height: 350,
		width: 600,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		shipInfo: "",
		open: function(){
			//var pdtId = globalCfgObj.pdtId;
			var shipInfo = $('#shipAreaEditDialog').dialog("option","shipInfo");
			var url = "product/product/inventory!edit.action?psId="+psId+"&shipInfo="+shipInfo+"&type="+pdtServType+"&sessionPSID="+sessionPSID;
			var htmlStr = '<iframe src="'+url+'" height="300" width="570" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			$('#shipAreaEditDialog').html(htmlStr);
		},
		close: function(obj){
		}
	});
	
	//supplier edit
	$('#supplierEditDialog').dialog({
		autoOpen: false,
		height: 360,
		width: 600,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: ""
	});
	
	//price edit
	$('#productPriceEditDialog').dialog({
		autoOpen: false,
		height: 250,
		width: 550,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: ""
	});
	
	//price Add
	$('#productPriceAddDialog').dialog({
		autoOpen: false,
		height: 250,
		width: 550,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: ""
	});
	
	$('#specialPriceEditDialog').dialog({
		autoOpen: false,
		height: 430,
		width: 600,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: ""
	});
	
	$('#specialPriceAddDialog').dialog({
		autoOpen: false,
		height: 430,
		width: 600,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: ""
	});
	
	// price change history dialog
	$('#priceChangeHistDialog').dialog({
		autoOpen: false,
		height: 250,
		width: 600,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: ""
	});
	
	//supplier picker
	$('#supplierPickerDialog').dialog({
		autoOpen: false,
		height: 400,
		width: 700,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: ""
	});
	
});