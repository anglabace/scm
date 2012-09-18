	function shipAreaEdit(shipInfo){
		$("#shipAreaEditTrigger").val(shipInfo);
		$("#shipAreaEditTrigger").trigger("click");
	}

	function initInventory(){
		var prefWarehouse = $("#init_prefWarehouse").val();
		var prefStorage = $("#init_prefStorage").val();
		changePrefWarehouse(prefWarehouse, prefStorage);
	}

	function changePrefWarehouse(prefWarehouse, prefStorage){
		$("#prefWarehouse option").each(function(i){
			if($(this).val() == prefWarehouse){
				$(this).attr("selected", true);
				var jsonStorageList = $(this).attr("_h");
				var storageList = eval(jsonStorageList);
				$("#prefStorage").html("");
				for(var o in storageList){
					var selectedStr = '';
					if(prefStorage == storageList[o].storageId){
						selectedStr = 'selected';
					}
					var tmpStr = "<option value='"+storageList[o].storageId+"' "+selectedStr+">"+storageList[o].name+"</option>";
					$("#prefStorage").append(tmpStr);
				}
			}
		});
	}

	function checkAllRS(ckAObj, ckName){
		if(ckAObj.checked){
			$("[name='"+ckName+"']").attr("checked", true);	
		}else{
			$("[name='"+ckName+"']").attr("checked", false);
		}
	}
$(document).ready(function(){
	initInventory();
    //css
	$('tr:odd >td').addClass('list_td2');
    
	//ship area add trigger
	$("#shipAreaAddTrigger").click(function(){
		parent.$('#shipAreaAddDialog').dialog("open");	
	});
	//ship area edit trigger
	$("#shipAreaEditTrigger").click(function(){
		parent.$('#shipAreaEditDialog').dialog("option","shipInfo", $(this).val());
		parent.$('#shipAreaEditDialog').dialog("open");
	});
	
	//restrict area delte
	$("#deleteShipAreaImg").click(function(){
		var tmpArr = [];
		$(":checked[name='rsId']").each(function(i, n){
			tmpArr.push($(n).val());
		});
		var delIdStr = tmpArr.toString();
		if(delIdStr == ""){
			alert("Please select one item to continue your operation.");
			return;
		}
		if(!confirm("Are you sure to delete?")){
			return;
		}
		var psId = $("#psId").val();
		var sessionServiceId=$("#sessionServiceId").val();
		$.ajax({
			type:"get",
			url:"serv/serv_inventory!delete.action?psId="+psId+"&delIdStr="+delIdStr+"&type="+parent.pdtServType+"&sessionServiceId="+sessionServiceId,
			data:"",
			dataType:"text",
			success:function(){
				window.location.reload();
			},
			error:function(){
				alert("Failed to remove the restricted shipping area. Please contact system administrator for help.");
			},
			async:false
		});
	});


	
});