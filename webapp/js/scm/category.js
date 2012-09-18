
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
		hrefStr = categoryType.toLowerCase()+"CategoryEditFormAct";
		$.ajax({
			type: "POST",
			url: urlStr,
			data: formStr,
			dataType: 'json',
			success: function(data, textStatus){
				if(hasException(data))
				{
					$('#saveAllTrigger').attr("disabled", false);	
				}else{
					
					isSaved = true;
					if(data.back=='product'){
						location.href = "product/product_category!input.action?callBackName=categoryCreationForm&categoryId="+data.id;
					}else{
						location.href = "serv/service_category!input.action?callBackName=categoryCreationForm&categoryId="+data.id;
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
    		ctgNo: {required:true, minlength:1, maxlength:20},
    		ctgName: {required:true, minlength:1, maxlength:50}
		},
		messages: {
			ctgNo: {
				required: "Please enter the Catalog no",
				maxlength: "The length of the Catalog no  is out of maximum limit - max length 20."
			},
			ctgName: {
				required: "Please enter the Name",
				maxlength: "The length of the Category Name is out of maximum limit - max length 50."
			}
		},
		errorPlacement: function(error, element) {}		
	});
	
});

function modifyCategoryNameClick() {
	if($("[name='nameAppr']").val()){
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
		var name = $("[name='ctgName']").val();
		var htmlStr = '<iframe src="category/category/modifyCatNameAct?name='+name+'" height="240" width="570" scrolling="no" style="border:0px" frameborder="0"></iframe>';
		$('#modifyCatNameDialog').html(htmlStr);
	});
	$('#modifyCatNameDialog').dialog('open');
}

function modifyCategoryStatusClick() {
	if($("[name='statusAppr']").val()){
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
		var status = $("[name='ctgStatus']").val();
		var htmlStr = '<iframe src="category/category/modifyCatStatusAct?status='+status+'" height="240" width="570" scrolling="no" style="border:0px" frameborder="0"></iframe>';
		$('#modifyCatStatusDialog').html(htmlStr);
	});
	$('#modifyCatStatusDialog').dialog('open');	
}