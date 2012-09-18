var ctId = $("[name='ctId']").val();
function modifyCatalogNameClick() {
	if($("[name='nameAppr']").val()=="true"){
		alert("The catalog name have been modified.");return;
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

function modifyCatalogStatusClick() {
	if($("[name='statusAppr']").val()=="true"){
		alert("The catalog status have been modified.");return;
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
function rolloverTab()
{
	if(this.className.indexOf('tabInactive')>=0){
		this.className='inactiveTabOver';
		var img = this.getElementsByTagName('IMG')[0];
		if(img.src.indexOf('tab_')<=0)img = this.getElementsByTagName('IMG')[1];
		img.src = 'images/tab_over2.gif';
	}
}
$(function(){
	$('#saveAllTrigger').click(function(){
		if( $('#catalogTopForm').valid() === false)
		{
			return false;	
		}
		var cataId = $("[id='cataId1']").val();
		$("#cataId1").attr("value",$.trim(cataId));
		cataId = $.trim(cataId);
		if(cataId==null||cataId==''){
			alert('Please enter the Catalog ID.');return flase;
		}
		if(cataId.split("&").length>1){
			alert("Plsease don't input the letter '&'.");
			return flase;
		}
		if(cataId.split("=").length>1){
			alert("Plsease don't input the letter '='.");
			return flase;
		}
		if(cataId.split("?").length>1){
			alert("Plsease don't input the letter '?'.");
			return flase;
		}
		if(cataId.split("@").length>1){
			alert("Plsease don't input the letter '@'.");
			return flase;
		}
		if(cataId.split("^").length>1){
			alert("Plsease don't input the letter '^'.");
			return flase;
		}
		if(cataId.split("%").length>1){
			alert("Plsease don't input the letter '%'.");
			return flase;
		}
		if(cataId.split("$").length>1){
			alert("Plsease don't input the letter '$'.");
			return flase;
		}
		if(cataId.split("#").length>1){
			alert("Plsease don't input the letter '#'.");
			return flase;
		}
		var catalogName = $("[id='catalogName']").val();
		$("#catalogName").attr("value",$.trim(catalogName));
		catalogName = $.trim(catalogName);
		if(catalogName==null||catalogName==''){
			alert('Please enter the Catalog Name.');return flase;
		}
		var formStr = '';
		formStr += $('#catalogTopForm').serialize();
		//formStr += "&" + $('#contactPersonalForm').serialize();		
		// ajax submit all the serialized data;
		var defaultTab = activeTabIndex['dhtmlgoodies_tabView1'];
		$.ajax({
			type: "POST",
			url: "product/catalog!SaveCopyCtlgAct.action",
			data: formStr,
			dataType: 'json',
			success: function(data, textStatus){
				if(hasException(data))
				{
					isSaved = true;
					$('#saveAllTrigger').attr("disabled", false);	
					window.location.reload();
				}else{
				  //alert(data.message);
				  isSaved = true;
				  location.href = "product/catalog!input.action?callBackName=catalogCreationForm&id="+data.id+"&defaultTab="+defaultTab;
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
				$('#saveAllTrigger').attr("disabled", false);
			}
		});
	});
	
	function copyChoiceCtlg() {
		var objSeq = $('[name="catalogSeq"]:checkbox:checked');
		var lenSeq = objSeq.length;
		if(!lenSeq) {
			alert("Please select one item to continue your operation.");return;
		}
		if(lenSeq>1) {
			alert("Please select only one catalog.");return;
		}
		return objSeq.val();
	}
	
	$("#copyCtlgDialogTrigger").click(function(){
		var ctlgId = copyChoiceCtlg();
		if(!ctlgId) {return;}
		$("#copyCtlgDialog").dialog({
			autoOpen: false,
			height: 340,
			width: 660,
			modal: true,
			bgiframe: true,
			buttons: {}
		});
		/*$.ajax({
			type: "POST",
			url: "product/catalog!catalogGetData.action",
			data: "ctlgId=" + ctlgId,
			dataType: 'json',
			async:false,
			success:function(data){
				var type = data.type;
				var defaultFlag = data.defaultFlag;
				var currencyCode = data.currencyCode;
				var priceLimit = data.priceLimit;
				var publishZone = data.publishZone;
				var oldId = data.id;*/
				$('#copyCtlgDialog').dialog("option", "open", function(){
					//var pmtStr = "type="+type+"&defaultFlag="+defaultFlag+"&currencyCode="+currencyCode+"&priceLimit="+priceLimit+"&publishZone="+publishZone+"&oldId="+oldId;
					var htmlStr = '<iframe src="product/catalog!input.action?id='+ctlgId+'&callBackName=catlogCopyNew" height="280" width="620" scrolling="no" style="border:0px" frameborder="0"></iframe>';
					$('#copyCtlgDialog').html(htmlStr);
				});
				$('#copyCtlgDialog').dialog('open');
			/*},
			error:function(){
				alert("Copy error!");
			}
		});*/
		
	});
	
	$("#cancelCopyCtlgTrigger").click(function(){
		parent.$("#copyCtlgDialog").dialog('close');
	})
	
	$("#cancelAllTrigger").click(function(){
		if(confirm("Are you sure to continue?")){
			window.location.reload();
		}
	});
	
	$('#saveCopyNewTrigger').click(function(){
		if( $('#copyNewCtlgForm').valid() === false)
		{
			return false;	
		}
		var formStr = '';
		formStr += $('#copyNewCtlgForm').serialize();
		//formStr += "&" + $('#contactPersonalForm').serialize();		
		// ajax submit all the serialized data;
		$.ajax({
			type: "POST",
			url: "product/catalog!saveNewCatalog.action",
			data: formStr,
			dataType: 'json',
			success: function(data, textStatus){
				if(hasException(data))
				{
					$('#saveCopyNewTrigger').attr("disabled", false);	
				}else{
				    isSaved = true;
				    parent.location.href = "product/catalog.action";
				}
			},
			error: function(xhr, textStatus){
				if(textStatus == 'timeout')
				{
					//alert("Timeout!");
					$('#saveCopyNewTrigger').attr("disabled", false);	
				}
				
				if(textStatus == 'parsererror')
				{
					tmp = xhr.responseText.split('{', 2);
					$('#saveCopyNewTrigger').attr("disabled", false);	
					alert(tmp[0]);
				}
			}
		});
	});
	
	$("#deleteCatalogTrigger").click(function(){
		var objSeq=$('[name="catalogSeq"]:checkbox:checked');
		var lenSeq = objSeq.length;
		if(!lenSeq) {
			alert("Please select one item to continue your operation.");return;
		}
		if(!confirm("Are you sure to delete?")){
			return;
		}
		var delStr="";
		for(i=0;i<lenSeq;i++){
			delStr += $('[name="catalogSeq"]:checkbox:checked').get(i).value + "<;>";
		}
		if(delStr){delStr = delStr.slice(0,-3);}

		$.ajax({
			type: "POST",
			url: "product/catalog!delCatalogList.action",
			data: 'delStr=' + delStr,
			dataType: 'json',
			success: function(msg){
				if(msg.message == 'success'){
					
					window.location.reload();
				}else if(hasException(msg)){
					$('#deleteCatalogTrigger').attr("disabled", false);	
				}else {
					alert('System error! Please contact system administrator for help.');
				}
			},
			error: function(msg){
				alert("Failed to remove the catalog. Please contact system administrator for help.");
			}
		});
		return false;			
	});
	
	$("#saveApprovedTrigger").click(function(){
		var approved = $("#approved").val();
		approved = $.trim(approved);
		var approvedReason = $("#approvedReason").val();
		approvedReason = $.trim(approvedReason);
		var approvedType = $("#approvedType").val();
		var oldApproved = $("#oldApproved").val();
		if(oldApproved==approved){
			alert("Please modify the name to continue your operation.");return;
		}
		if(!approved){
			alert("Please enter the name.");return;
		}
		if(approved.length>50){
			alert("The length of the Category Name  is out of maximum limit - max length 50.");return;
		}
		if(!approvedReason){
			alert("Please enter the reason.");return;
		}
		var sessionCatalogId = $("#sessionCatalogId").val();
		$.ajax({
			url:"product/catalog!catalogApprovedSaveSession.action",
			type:"get",
			data:"approved="+approved+"&approvedReason="+approvedReason+"&approvedType="+approvedType+"&sessionCatalogId="+sessionCatalogId,
			dataType:"json",
			success:function(data){
				if(hasException(data)){
					$('#saveApprovedTrigger').attr("disabled", false);	
				}else{
					if(data.message == "success"){
						alert("The modification is ready to be submitted for the evaluation and will be applied only after it’s approved.");
					}else{
						
						alert("System error! Please contact system administrator for help.");
						
					}
				}
			},
			error:function(data){
				
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
		var sessionCatalogId = $("#sessionCatalogId").val();
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
			url:"product/catalog!catalogApprovedSaveSession.action",
			type:"get",
			data:"approved="+approved+"&approvedReason="+approvedReason+"&approvedType="+approvedType+"&sessionCatalogId="+sessionCatalogId,
			dataType:"json",
			success:function(data){
				if(hasException(data)){
					$('#saveApprovedStatusTrigger').attr("disabled", false);	
				}else{
					if(data.message == "success"){
						alert("The modification is ready to be submitted for the evaluation and will be applied only after it’s approved.");
					}else{
						alert("System error! Please contact system administrator for help.");
					}
				}
			},
			error:function(data){
				alert("System error! Please contact system administrator for help.");
			},
			async:false
		});
		$("#modifyCatStatusDialog").dialog("close");
	});

});
