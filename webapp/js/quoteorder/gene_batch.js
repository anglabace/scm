/**
 * Created by zhanghuibin
 * User: Administrator
 * Date: 8/15/11
 * Time: 2:26 PM
 *基因批量添加的所有公用JS
 */
var itemIds = "";
$(document).ready(function() {
    $("#searchEnzymeTrigger5").click(function() {
        $("#searchEnzymeDialog").dialog("option", "sequenceId", "sequence5");
        $("#searchEnzymeDialog").dialog("open");
    });

    $("#searchEnzymeTrigger3").click(function() {
        $("#searchEnzymeDialog").dialog("option", "sequenceId", "sequence3");
        $("#searchEnzymeDialog").dialog("open");
    });

    //confirm email update/display dialog
    $('#searchEnzymeDialog').dialog({
        autoOpen: false,
        height: 500,
        width: 300,
        modal: true,
        bgiframe: true,
        sequenceId:"",
        sequence:"",
        buttons: {
        },
        open: function() {
            if ($('#searchEnzymeDialog').find("iframe").size() == 0) {
                var htmlStr = '<iframe src=' + batchUrlObj.searchEnzymeUrl + ' height="450" width="270" scrolling="no" style="border:0px" frameborder="0"></iframe>';
                $('#searchEnzymeDialog').html(htmlStr);
            }
        },
        close: function() {
            var sequenceId = $("#searchEnzymeDialog").dialog("option", "sequenceId");
            var sequence = $("#searchEnzymeDialog").dialog("option", "sequence");
            $("#" + sequenceId).val(sequence);
        }
    });

    //$("#itemDetailIframe").refreshItem("", itemIds, "")
//alert(parent.document.getElementById("itemDetailIframe"))
//保存
    $("#addServiceBtn").click(function() {
        $.funkyUI({
			showDialog:false
		});
        //解决IE时序问题
        setTimeout(saveBatchGene, 20);
    });
});

function saveBatchGene(){
    if (!$('#geneSynthesisForm').valid()) {
            $.unfunkyUI();
            return false;
        }
        if (!dataValidate()) {
            $.unfunkyUI();
            return false;
        }
        var flag = true;
        if (itemIds == "") {
            flag = createQuoteItem();
        }
        if (!flag) {
            $.unfunkyUI();
            return false;
        }
        //计算要生成的item
        var tmpFlag = false;
        var cloneData = "";
        $.ajax({
            url: batchUrlObj.batchSaveUrl + "?" + sessNoName +"="+ orderquoteObj.sessNoValue + "&itemId=" + itemIds + "&plasmidPrepFlag=" + $("#plasmidPrepFlag").val(),
            data:addFormNameToSerializeGeneData("geneSynthesis", $("#geneSynthesisForm").formSerialize()),
            success:function(data) {
                var ids = data.ids.substring(1, data.ids.length);
                var itemData = data.data;
                //设置itemId
                /*for (var i = 0; i < ids.split(",").length; i++) {
                    refreshItem(itemData[i], ids.split(",")[i], "");
                }*/
                //tmpFlag = true;
                //$("#geneSynthesisForm").refresh_changed_form();
                //add by zhanghuibin 选择选中item
                var trNum = window.parent.$('#itemListIframe').contents().find('#itemTable').find("tr").length;
                if (trNum == undefined || trNum == 0) {
                    parent.window.orderquoteObj.originQty = 0;
                } else {
                    parent.window.orderquoteObj.originQty = trNum - 1;
                }
                parent.window.orderquoteObj.triggerLast = "1";
                parent.$("#itemListIframe").attr("src", batchUrlObj.itemList + "?" + sessNoName + "=" + orderquoteObj.sessNoValue);
                parent.$('#itemAddBatch').dialog('close');
            },
            error: function() {
                alert("System error! Please contact system administrator for help.");
            },
            type:"POST",
            async:false,
            dataType:"json"
        });
        $.unfunkyUI();
}

 //删除cloning
function delBatchCloning(){
    if ($("#cloningFlag:checked").val()) {
        if(parent.batchCloningId=="") return;
        $.ajax({
            url:batchUrlObj.custCloningSaveUrl,
            data:sessNoName+"=" + orderquoteObj.sessNoValue + "&itemId=" + parent.batchCloningId + "&removeFlag=1",
            success:function(data) {
                //refreshItem(data, tmpId, "");
                //$("#cloningForm").refresh_changed_form();
                parent.batchCloningId = "";
            },
            error: function() {
                alert("System error! Please contact system administrator for help.");
            },
            type:"POST",
            async:false,
            dataType:"json"
        });
    }
}

function showCloning(){
    if (createQuoteItem() && saveGeneSynthesis()) {
        parent.showBatchCloning(orderquoteObj.sessNoValue, itemIds);
    }else{
       $("#cloningFlag").attr("checked", true);
    }
}

function showDirectCloning(){
    delBatchCloning();
    showCloning();
}

function createQuoteItem(){
    var flag = true;
    //创建sessionQuoteNo 以及 itemId
    if(itemIds != ""){
        return true;
    }
    $.ajax({
		url:batchUrlObj.createItemUrl ,
        type:"get",
        dataType:"json",
		data:"custNo=" + batchUrlObj.batchCustNo + "&"+sessNoName+"="+orderquoteObj.sessNoValue + "&itemNum=1&catalogNo=SC1010",
		success:function(data){
			itemIds = data.itemNOs;
		},
		error: function(){
			alert("System error! Please contact system administrator for help.");
            flag=false;
		},
		async:false
	});
    return flag;
}

// validate signup form on keyup and submit
	$("#geneSynthesisForm").validate({
		errorClass:"validate_error",
		highlight: function(element, errorClass) {
			$(element).addClass(errorClass);
	    },
	    unhighlight: function(element, errorClass, validClass) {
	        $(element).removeClass(errorClass);
	    },
		invalidHandler: function(form, validator) {
	        $.each(validator.invalid, function(key,value){
	        	        	var tmpIndex = getTabIndexBy("Gene Synthesis");
	        	if(tmpIndex != -1){
	        		showTab('dhtmlgoodies_tabView1', tmpIndex);
	        	}
	            alert(value);
	            return false;
	        });
		},
		rules: {
			sequenceContent: {required:true}
		},
		messages: {
			sequenceContent: {required:"Please enter the 'sequence' "}
		},
		errorPlacement: function(error, element) {
		}
	});

function saveGeneSynthesis(){
    if(!$('#geneSynthesisForm').valid()){
		return false;
	}
    if(!dataValidate()){
        return false;
    }
    var flag = true;
    $.ajax({
		url:batchUrlObj.geneSynSaveUrl + "?"+sessNoName+"="+batchUrlObj.sessNO + "&itemId="+itemIds ,
        type:"post",
        dataType:"json",
		data:addFormNameToSerializeGeneData("geneSynthesis", $("#geneSynthesisForm").formSerialize()),
		success:function(data){

		},
		error: function(){
			alert("System error! Please contact system administrator for help.");
            flag=false;
		},
		async:false
	});
    return flag;
}

function dataValidate() {
    var seqContent = $("#sequenceContent").val();
    var seqCont =  seqContent.split(">");
    if(seqContent.indexOf(">")<0){
        alert("Invalid character Sequence");
        return false;
    }
    for (var i = 1; i < seqCont.length; i++) {
        seqCont[i] = seqCont[i].replace(/\r|\n|\r\n/i, "@@");
        var geneName = seqCont[i].split("@@")[0];
        var squ = seqCont[i].substring(seqCont[i].indexOf("@@")+2, seqCont[i].length);
        squ = squ.replace(/\r|\n|\r\n|\s/g, "");
        var reFirstCheck = /^([AGTCagtc])*$/;
        var firstCheck = reFirstCheck.test(squ);
        if (!firstCheck) {
            alert("Invalid character Sequence, when you select  the Sequence type is 'DNA',the Sequence must match 'AGTCagtc'");
            return false;
        }
    }
    return true;
}

 function addFormNameToSerializeGeneData(formName, str) {
	if (!formName) {
		return str;
	}
	var tmpArr = str.split("&");
	var rtArr = [];
	var tmpStr = '';
	for ( var i = 0; i < tmpArr.length; i++) {
		tmpStr = tmpArr[i];
		if (tmpStr.substring(0, 11) == "__checkbox_") {
			continue;
		} else if (tmpStr.substring(0, 5) == "_tmp_") {
			continue;
		}else if(tmpStr.indexOf("plasmidPreparation") >= 0){
            rtArr.push(tmpArr[i]);
            continue;
        }
		rtArr.push(formName + "." + tmpArr[i]);
	}
	return rtArr.join("&");
}

function refreshItem(itemInfo, itemId, removeFlag){
	if(itemId){
		var parentId = itemInfo.parentId;
		if (itemInfo.preParentId != undefined && itemInfo.preParentId != "") {
			parentId = itemInfo.preParentId;
		}
		var itemTrs = parent.$("#itemListIframe").contents().find("#itemTable tr");
		if(removeFlag){
			itemTrs.each(function(i, n){
				if($(this).attr("itemId") == itemId){
					$(this).remove();
				}
			});
			parent.window.frames["itemListIframe"].setArrowImage();
			parent.window.frames["itemListIframe"].reSetItemNo();
			return;
		}
		var findFlag = 0;
		itemTrs.each(function(i, n){
			if($(this).attr("itemId") == itemId){
				findFlag = 1;
				updateItemHtmlData( $(this), itemId, itemInfo);
			}
		});
		//
		if(findFlag == 0){
			itemTrs.each(function(i, n){
				if($(this).attr("itemId") == parentId){
					findFlag = 1;
					$(this).after(getItemHtml(itemId));
					updateItemHtmlData( $(this).next(), itemId, itemInfo);
				}
			});
		}
		if(findFlag == 0){
			itemTrs.each(function(i, n){
				if($(this).attr("itemId") == parentId){
					findFlag = 1;
					$(this).after(getItemHtml(itemId));
					updateItemHtmlData( $(this).next(), itemId, itemInfo);
				}
			});
		}
		if(findFlag == 0){
			findFlag = 1;
			var lastTr = parent.$("#itemListIframe").contents().find("#itemTable tr:last");
            if (lastTr.html() == null) {
                parent.$("#itemListIframe").contents().find("#itemTable").html(getItemHtml(itemId));
                updateItemHtmlData( parent.$("#itemListIframe").contents().find("#itemTable tr:last"), itemId, itemInfo);
            } else {
                $(lastTr).after(getItemHtml(itemId));
                updateItemHtmlData( $(lastTr).next(), itemId, itemInfo);
            }
	    }
		parent.window.frames["itemListIframe"].setArrowImage();
		parent.window.frames["itemListIframe"].reSetItemNo();
		if(findFlag == 0){
			alert("Failed to add the new item.");
		}
	}
}

function updateItemHtmlData(trObj, itemId, itemInfo){
	var precision = 2;
	if("JPY" == itemInfo.currencyCode){
		precision = 0;
	}
	trObj.find("[name='amount']").val(itemInfo.amount);
	trObj.find("[name='unitPrice']").val(itemInfo.unitPrice);
	trObj.find("[name='upSymbol']").val(itemInfo.upSymbol);
	trObj.find("[name='discount']").val(itemInfo.discount);
	trObj.find("[name='tax']").val(itemInfo.tax);
	trObj.find("[name='tdItemNo']").html(itemInfo.itemNo);
	var catalogNoShort = "";
	if(itemInfo.catalogNo.length > 9){
		catalogNoShort = itemInfo.catalogNo.substr(0, 9)+"...";
	}else{
		catalogNoShort = itemInfo.catalogNo;
	}
	trObj.find("[name='tdCatalogNo']").html(catalogNoShort);
	trObj.find("[name='catalogNoTd']").val(itemInfo.catalogNo);
	var tmpPreImg = trObj.find("[name='tdNameShort']").find("img");
	var nameShort = "";
	if(itemInfo.name.length > 40){
		nameShort = itemInfo.nameShow.substr(0, 40)+"...";
	}else{
		nameShort = itemInfo.nameShow;
	}
	trObj.find("[name='tdNameShort']").html(nameShort);
	trObj.find("[name='tdNameShort']").prepend(tmpPreImg);
	trObj.find("[name='changeStatus']").html(itemInfo.status);
	trObj.find("[name='qtyTd']").html(itemInfo.quantity);
	trObj.find("[name='tdQtyUom']").html(itemInfo.qtyUom);
	trObj.find("[name='type']").val(itemInfo.type);
	trObj.find("[name='parentId']").val(itemInfo.parentId);

	trObj.find("[name='tdSizeQtyUom']").html(itemInfo.size);
	if(itemInfo.sizeUom){
		trObj.find("[name='tdSizeQtyUom']").append(itemInfo.sizeUom);
	}
	if(parseFloat(itemInfo.amount) < 0){
		trObj.find("[name='tdSymbolAmount']").html("TBD");
	}else{
		trObj.find("[name='tdSymbolAmount']").html(itemInfo.amount.toFixed(precision));
	}
	if(parseFloat(itemInfo.unitPrice) < 0){
		trObj.find("[name='tdSymbolUnitPrice']").val("TBD");
	}else{
		trObj.find("[name='tdSymbolUnitPrice']").html(itemInfo.unitPrice.toFixed(precision));
	}
	trObj.find("[name='tdDiscount']").html(itemInfo.discount.toFixed(precision));
	if(parseFloat(itemInfo.cost) < 0){
		trObj.find("[name='tdCost']").html("TBD");
	}else{
		trObj.find("[name='tdCost']").html(itemInfo.cost.toFixed(precision));
	}
	trObj.find("[name='tdTax']").html(itemInfo.tax.toFixed(precision));
	if(itemInfo.upSymbol && parseFloat(itemInfo.unitPrice) > 0 ){
		trObj.find("[name='tdSymbolAmount']").prepend(itemInfo.upSymbol);
		trObj.find("[name='tdSymbolUnitPrice']").prepend(itemInfo.upSymbol);
	}
	if(itemInfo.upSymbol && parseFloat(itemInfo.cost) > 0 ){
		trObj.find("[name='tdCost']").prepend(itemInfo.upSymbol);
	}
	if(itemInfo.upSymbol){
		trObj.find("[name='tdDiscount']").prepend(itemInfo.upSymbol);
		trObj.find("[name='tdTax']").prepend(itemInfo.upSymbol);
	}
}

function getItemHtml(itemId){
	var tmpStr = "";
	tmpStr += '<tr itemId="'+itemId+'">' ;
    tmpStr += '<td width="65" >' ;
    tmpStr += '	<input type="checkbox" value="'+itemId+'" name="itemId"/>';
    tmpStr += '<input id="amount" name="amount" type="hidden" value="">' ;
    tmpStr += '<input id="unitPrice" name="unitPrice" type="hidden" value="">';
	tmpStr += '<input id="upSymbol" name="upSymbol" type="hidden" value="">' ;
    tmpStr += '<input id="discount" name="discount" type="hidden" value="">';
	tmpStr += '<input id="tax" name="tax" type="hidden" value="">' ;
	tmpStr += '<input id="type" name="type" type="hidden" value="">' ;
	tmpStr += '<input id="parentId" name="parentId" type="hidden" value="">' ;
    tmpStr += '</td>' ;
    tmpStr += '<td width="60"  align="center"><div name="tdItemNo" id="tdItemNo">' ;
    tmpStr += '</div></td>' ;
    tmpStr += '<td width="60"  align="center"><div name="tdCatalogNo" id="tdCatalogNo"></div><input id="catalogNoTd" name="catalogNoTd" type="hidden" value=""></td>' ;
    tmpStr += '<td width="250" ><div name="tdNameShort" id="tdNameShort" title=""></div></td>' ;
    tmpStr += '<td width="40"  align="center">' ;
    tmpStr += '<div name="changeStatus" id="changeStatus"></div>' ;
    tmpStr += '</td>' ;
    tmpStr += '<td width="45"  align="center">' ;
    tmpStr += '<div style="width:100%" id="qtyTd" name="qtyTd"></div>' ;
    tmpStr += '</td>' ;
    tmpStr += '<td width="40" >' ;
    tmpStr += '<div align="center" name="tdQtyUom" id="tdQtyUom">&nbsp;</div>' ;
    tmpStr += '</td>' ;
    tmpStr += '<td width="60" align="center">' ;
    tmpStr += '<div name="tdSizeQtyUom" id="tdSizeQtyUom">&nbsp;</div>' ;
    tmpStr += '  </td>' ;
    tmpStr += '<td width="60" align="right">' ;
    tmpStr += '<div name="tdSymbolAmount" id="tdSymbolAmount">&nbsp;' ;
    tmpStr += '</div></td>' ;
    tmpStr += '<td width="60" align="right">' ;
    tmpStr += '<div name="tdCost" id="tdCost">&nbsp;' ;
    tmpStr += '</div></td>' ;
    tmpStr += '<td width="60" align="right">' ;
    tmpStr += '<div name="tdSymbolUnitPrice" id="tdSymbolUnitPrice">&nbsp;</div>' ;
    tmpStr += '</td>' ;
    tmpStr += '<td width="60"  align="right">' ;
    tmpStr += '<div name="tdDiscount" id="tdDiscount">&nbsp;</div>' ;
    tmpStr += '</td>' ;
    tmpStr += '<td align="right">' ;
    tmpStr += '<div name="tdTax" id="tdTax" >&nbsp;</div>' ;
    tmpStr += '</td>' ;
    tmpStr += '</tr>' ;
    return tmpStr;
}

function quoteWeightChange(){
    var prepWeightSel = $("#prepWeightSel").val();
    if ($(this).val() == "0") {
        $("#prepDiv").css("display","");
        $("#prepWtUom").attr("value", "mg");
    } else {
        $("#prepWeightStr").attr("value",prepWeightSel);
        $("#prepDiv").css("display","none");
    }
}

//////////////////////////////////////batch cloning
//上传附件
function uploadServiceFile(itemId, serviceName, formId, refType, btnObj){
	if(!$(btnObj).parent().parent().find(":file").val() ){
		alert("Please select one file.");
		return;
	}
	var form = $("#"+formId);
	var tmpId = itemId;
	//ajax form post
	var options = {
		success:function(data){
			var documentList = data;
			var tmpStr = getFileListHtml(documentList);
			$(form).find("#fileListTable").append(tmpStr);
			$(form).find(":file").val("");
		},
		error: function(data){
			if(data.responseText){
				alert(data.responseText);
			}else{
				alert("System error! Please contact system administrator for help.");
			}
		},
		dataType:"json",
		resetForm:false,
		url:cloningUrlObj.uploadfile + "?"+ sessNoName + "=" + cloningUrlObj.cloningSessNo +"&itemId="+cloningUrlObj.itemId+"&serviceName="+serviceName+"&refType="+refType,
		type:"POST"
	};
	form.ajaxForm(options);
	form.submit();
}

function closeCloning(){
     saveBatchCloning();
     window.parent.$('#batchCloning').dialog( 'close');
 }

function addFormNameToSerializeCloningData(formName, str) {
	if (!formName) {
		return str;
	}
	var tmpArr = str.split("&");
	var rtArr = [];
	var tmpStr = '';
	for ( var i = 0; i < tmpArr.length; i++) {
		tmpStr = tmpArr[i];
		if (tmpStr.substring(0, 11) == "__checkbox_") {
			continue;
		} else if (tmpStr.substring(0, 5) == "_tmp_") {
			continue;
		}else if(tmpStr.indexOf("upload") >= 0){
            continue;
        }
		rtArr.push(formName + "." + tmpArr[i]);
	}
	return rtArr.join("&");
}

//保存cloning ,itemId 传人"" 为创建一个Item
 function saveBatchCloning(){
	var cloneData = "";
	$.ajax({
		url:cloningUrlObj.saveBatchCloning + "?"+sessNoName+"="+ cloningUrlObj.cloningSessNo +"&itemId=&parentId=" + cloningUrlObj.cloningParentId,
		data:addFormNameToSerializeCloningData("custCloning", $("#cloningForm").formSerialize()),
		success:function(data){
            for(var p in data){
                parent.batchCloningId = p;
                break;
            }
			//refreshItem(data, tmpId, "");
			//$("#cloningForm").refresh_changed_form();
		},
		error: function(){
			alert("System error! Please contact system administrator for help.");
		},
		type:"POST",
		async:false,
		dataType:"json"
	});
 }

//add by zhanghuibin
function vectorSizeValid(e) {
    var key = window.event ? e.keyCode : e.which;
    if(key == 8 || key == 46) return true;
    var keychar = String.fromCharCode(key);
    var reg = /\d|-/;
    var result = reg.test(keychar);
    if(!result)
    {
        return false;
    }
    else
    {
        return true;
    }
}
