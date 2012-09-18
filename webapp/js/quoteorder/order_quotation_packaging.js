function checkAll(obj, name){
	if(obj.checked == true){
		$("[name='"+name+"']").attr("checked", true);
	}else{
		$("[name='"+name+"']").attr("checked", false);
	}
}

$(document).ready(function(){
	$("#viewPackages").click(function(){
		window.location.href = orderquoteObj.url("showPackageList");
	});
	
	$("#viewPackagesForItems").click(function(){
   		var itemIds = [];
   		parent.$("#itemListIframe").contents().find("[name='itemId']").each(function(i, n){
   			if($(n).attr("checked") == true){
   				itemIds.push("&itemIdList="+$(n).val());
   			}
   		});
		var itemIdListParam = itemIds.join("");
		if(itemIdListParam == ""){
			alert("Please select the item to continue your operation.");
			return;
		}
		window.location.href = orderquoteObj.url("showPackageList")+itemIdListParam;
	});
	
	$("#packageList").click(function(e){
		if(e.target.id == "packageIdLink" && parent.operation_method == "edit"){
			var tmpObj = e.target;
			var packageId = $(tmpObj).attr("packageId");
			var title = $(tmpObj).attr("title");
			var quorderNo = $("#quorderNo").val();
			var packageDialog = parent.$( '#packageModifyDialog' ) ;
			var url = orderquoteObj.url("showPackageEdit")+'&sessPackageId='+packageId;
			packageDialog.html( '<iframe src="'+url+'" height="550" width="770" scrolling="auto" style="border:0px" frameborder="0"></iframe>' ) ;
			packageDialog.dialog('option' , 'title', title);
			packageDialog.dialog( "open" ) ;
		}
	});
});