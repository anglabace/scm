
$(function(){
	var categoryType = $("#categoryType").val();
	$('#saveAllTrigger').click(function(){
		if( $('#categoryTopForm').valid() === false) {
			return false;	
		}								
		var formStr = '';
		var urlStr;
		var hrefStr;
		formStr += $('#categoryTopForm').serialize();
		if(categoryType=="product"){
			urlStr = "product/product_category!productCategorySaveAct.action";
		}
		if(categoryType=="service"){
			urlStr = "serv/service_category!serviceCategorySaveAct.action";
		}
		var defaultTab = activeTabIndex['dhtmlgoodies_tabView1'];
		hrefStr = categoryType.toLowerCase()+"CategoryEditFormAct";

		var objRefernce = $("#referenceTable").find("tr");
    	var referStrs = "";
    	objRefernce.each(function(){
			var reference = $(this).find("[id^='reference']").val();
			if(reference!=null&&reference!=""&&reference!=undefined){
				var id = $(this).find("[id^='referenceId']").val();
				if(id!=null&&id!=""&&id!=undefined){
					referStrs += id+"<,>";
				}else{
					referStrs += "<,>";
				}
				referStrs += reference + "<,>";
				var url = $(this).find("[id^='referenceUrl']").val();
				referStrs += url+"<;>";
			}
		});
    	formStr +="&referenceList="+referStrs;
		$.ajax({
			type: "POST",
			url: urlStr,
			data: formStr,
			dataType: 'json',
			success: function(data, textStatus){
				if(hasException(data))
				{
					isSaved = true;
					$('#saveAllTrigger').attr("disabled", false);	
					window.location.reload();
				}else{
					alert(data.message);
					isSaved = true;
					if(data.back=='product'){
						location.href = "product/product_category!input.action?dodo=first&callBackName=categoryCreationForm&categoryId="+data.id;
					}else{
						location.href = "serv/service_category!input.action?dodo=first&callBackName=categoryCreationForm&categoryId="+data.id+"&defaultTab="+defaultTab;
					}
				}
			},
			error: function(xhr, textStatus){
				if(textStatus == 'timeout')
				{
					//alert("Timeout!");
				}
				
				if(textStatus == 'parsererror')
				{
					tmp = xhr.responseText.split('{', 2);
					alert(tmp[0]);
				}
			}
		});
	});
	$("#cancelAllTrigger").click(function(){
		if(confirm("Are you sure to continue?")){
			window.location.reload();
		}
	});
	$("#categoryTopForm").validate({
		invalidHandler: function(form, validator) {
	        $.each(validator.invalid, function(key,value){
	            alert(value);
	            $("[name='"+key+"']").focus();
	            return false;
	        });
    	},
		rules: {
    		"serviceCategoryDTO.categoryNo": {required:true, minlength:1, maxlength:60},
    		"serviceCategoryDTO.name": {required:true, minlength:1, maxlength:50}
		},
		messages: {
			"serviceCategoryDTO.categoryNo": {
				required: "Please enter the Catalog Id",
				maxlength: "The length of the Catalog Id  is out of maximum limit - max length 60."
			},
			"serviceCategoryDTO.name": {
				required: "Please enter the Name",
				maxlength: "The length of the Category Name is out of maximum limit - max length 50."
			}
		},
		errorPlacement: function(error, element) {}		
	});
	
	$("#saveApprovedTrigger").click(function(){
		var approved = $("#approved").val();
		approved = $.trim(approved);
		var approvedReason = $("#approvedReason").val();
		approvedReason = $.trim(approvedReason);
		var approvedType = $("#approvedType").val();
		var sessionCategoryId = $("#sessionCategoryId").val();
		var oldApproved = $("#oldApproved").val();
		if(oldApproved==approved){
			alert("Please modify the name to continue your operation.");return;
		}
		if(!approved){
			alert("Please enter the name.");return;
		}
		if(!approvedReason){
			alert("Please enter the reason.");return;
		}
		if(approved.length>50){
			alert("The length of the Category Name  is out of maximum limit - max length 50.");return;
		}
		$.ajax({
			url:"serv/service_category!serviceCategoryApprovedSaveSession.action",
			type:"get",
			data:"approved="+approved+"&approvedReason="+approvedReason+"&approvedType="+approvedType+"&sessionCategoryId="+sessionCategoryId,
			dataType:"json",
			success:function(data){
				if(hasException(data)){
					$('#saveApprovedTrigger').attr("disabled", false);	
				}else{
					if(data.message == "success"){
						alert("The modification is ready to be submitted for the evaluation and will be applied only after it’s approved.");
					}else{
						if(data){
							alert(data.message);
						}else{
							alert("System error! Please contact system administrator for help.");
						}
					}
				}
			},
			error:function(data){
				if(data)
					alert(data.message);
				else
					alert("System error! Please contact system administrator for help.");
			},
			async:false
		});
		$("#modifyCatNameDialog").dialog("close");
	});
	
	$("#saveApprovedStatusTrigger").click(function(){
		var approved = $("#statusApprove").val();
		approved = $.trim(approved);
		var approvedReason = $("#statusReason").val();
		approvedReason = $.trim(approvedReason);
		var approvedType = $("#approvedStatusType").val();
		var oldApproved = $("#oldStatusApproved").val();
		var sessionCategoryId = $("#sessionCategoryId").val();
		if(oldApproved==approved){
			alert("Please modify the status to continue your operation.");return;
		}
		if(!approved){
			alert("Please status the reason.");return;
		}
		if(!approvedReason){
			alert("Please enter the reason.");return;
		}
		$.ajax({
			url:"serv/service_category!serviceCategoryApprovedSaveSession.action",
			type:"get",
			data:"approved="+approved+"&approvedReason="+approvedReason+"&approvedType="+approvedType+"&sessionCategoryId="+sessionCategoryId,
			dataType:"json",
			success:function(data){
				if(hasException(data)){
					$('#saveApprovedStatusTrigger').attr("disabled", false);	
				}else{
					if(data.message == "success"){
						alert("The modification is ready to be submitted for the evaluation and will be applied only after it’s approved.");
					}else{
						if(data){
							alert(data.message);
						}else{
							alert("System error! Please contact system administrator for help.");
						}
					}
				}
			},
			error:function(data){
				if(data)
					alert(data.message);
				else
					alert("System error! Please contact system administrator for help.");
			},
			async:false
		});
		$("#modifyCatStatusDialog").dialog("close");
	});
});

function modifyCategoryNameClick() {
	if($("[name='nameAppr']").val()=="true"){
		alert("The category name have been modified.");return;
	}
	$("#modifyCatNameDialog").dialog({
		autoOpen: false,
		height: 300,
		width: 600,
		modal: true,
		bgiframe: true,
		buttons: {}
	});	
	$('#modifyCatNameDialog').dialog("option", "open",function(){
	});
	$('#modifyCatNameDialog').dialog('open');
}

function modifyCategoryStatusClick() {
	if($("[name='statusAppr']").val()=="true"){
		alert("The category status have been modified.");return;
	}
	$("#modifyCatStatusDialog").dialog({
		autoOpen: false,
		height: 300,
		width: 600,
		modal: true,
		bgiframe: true,
		buttons: {}
	});	
	$('#modifyCatStatusDialog').dialog("option", "open",function() {
		
	});
	$('#modifyCatStatusDialog').dialog('open');	
}