$(document).ready(function(){
	var psId = $("#psId").val();
	//ship area add trigger
	$("#supplierAddTrigger").click(function(){
		parent.$('#supplierEditDialog').attr("title", "Add New Supplier");	
		parent.$('#supplierEditDialog').dialog("option","open", function(){
			var url = "product/produt!showEditSuppler.action?id="+psId+"&type="+type;
			var htmlStr = '<iframe src="'+url+'" height="300" width="570" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			parent.$('#supplierEditDialog').html(htmlStr);
		});	
		parent.$('#supplierEditDialog').dialog("option", "title", "Add New Supplier");	
		parent.$('#supplierEditDialog').dialog("open");	
	});
	//ship area edit trigger
	$("#supplierEditTrigger").click(function(){
		var vendorName = $(this).attr("vendorName");
		var supplierId = $(this).attr("supplierId");
		parent.$('#supplierEditDialog').attr("title", "Supplier-"+vendorName);
		parent.$('#supplierEditDialog').dialog("option", "open",function(){
			var supplierInfo = $("#"+supplierId+"_supplierInfo").val();
			var url = "product/produt!showEditSuppler.action?id="+psId+"&type="+type+"&supplierId="+supplierId+"&supplierInfo="+supplierInfo;
			var htmlStr = '<iframe src="'+url+'" height="300" width="570" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			parent.$('#supplierEditDialog').html(htmlStr);
		});
		parent.$('#supplierEditDialog').dialog("option", "title", "Supplier-"+vendorName);
		parent.$('#supplierEditDialog').dialog("open");
	});
});