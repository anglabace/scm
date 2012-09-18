//item list 初始化
function initItemList() {
	//默认触发第一行 //新增
	if($("#itemTable tr").length == 0){
		parent.document.getElementById("itemDetailIframe").src = orderquoteObj.url("getItemDetail");
	}else{
		//编辑
		if(parent.window.orderquoteObj.triggerLast == "1"){
			parent.window.orderquoteObj.triggerLast = 0;
			var trNum = parseInt(parent.window.orderquoteObj.originQty+1);
			$( '#itemTable tr:nth-child('+trNum+')' ).click();
			$("#itemTableDiv").animate({scrollTop: $("#itemTableDiv").get(0).scrollHeight});
		}else{
			$( '#itemTable tr:first-child' ).click();
		}
	}
	setArrowImage();
	
	//是否从work order那边查看order的
	if(parent.$("#lookFromWoFlag").val()==1) {
		$("#itemTable").find("[name='tdSymbolAmount']").each(function(){
			$(this).parent().html("XX");
		});
//		$("#itemTable").find("[name='tdCost']").each(function(){
//			$(this).parent().html("XX");
//		});
		$("#itemTable").find("[name='tdSymbolUnitPrice']").each(function(){
			$(this).parent().html("XX");
		});
		$("#itemTable").find("[name='tdDiscount']").each(function(){
			$(this).parent().html("XX");
		});
		$("#itemTable").find("[name='tdTax']").each(function(){
			$(this).parent().html("XX");
		});
	}
//	setInitRow();
    $(window).ready(function(){
        $.unfunkyUI({topWin : window.parent});
    });
}

function setInitRow() {
	var compareVal = "";
	$('#itemTable tr').each(
			function() {
				compareVal = $(this).find("[name*='changeStatus']").html()
						.replace("&nbsp;", "").toString();
				if (compareVal == 'Canceled' || compareVal == 'CN') {
					$(this).addClass('tr_del');
					$(this).find("[name='itemId']").attr('disabled', 'true');
				}
			});
}

function validateInteger(sDouble)
{
	var re = /^\d+$/;
	return re.test(sDouble);
}

function validateUnitPrice(sDouble)
{
	var re = /^\d+((\.?\d+)|(\d*))/;
	return re.test(sDouble);
}

//改变quantity
function changeQty(itemId) {
	var itemTr = $("#itemTable").find("tr[itemId='" + itemId + "']");
	var qtyInput = $(itemTr).find("[name='qtyTd']").find("input");
	var upSymbol = $(itemTr).find('[name="upSymbol"]').val();
	var upPrecision = $(itemTr).find('[name="upPrecision"]').val();
	
	if (qtyInput.attr('value') == "" || !validateInteger(qtyInput.attr('value'))) {
		alert("Please enter the item quantity.");
		return;
	}
	if (qtyInput.attr('value') <= 0) {
		alert("The item quantity must be greater than zero.");
		return;
	}
	if(qtyInput.attr('value') >1000000) {
		alert("The item quantity must be lower than 1000000.");
		return;
	}
	var quantity = qtyInput.attr('value');
	var unitPrice = $(itemTr).find('[name="unitPrice"]').val();
	var tdAmount = quantity * unitPrice;
	var oNoobj = new Number(tdAmount);
	tdAmount = oNoobj.toFixed(upPrecision);
	if(tdAmount>9999999) {
		alert("The item amount is too long.");
		return;
	}
	// 更新session
	$.ajax( {
		type : "POST",
		url : orderquoteObj.url("updateItemQty"),
		dataType : "json",
		data :  "itemId="+itemId+"&quantity="+quantity+"&amount="+tdAmount,
		async : false,
		error : function() {
			alert("System error! Please contact system administrator for help.");
		},
		success : function(data) {
			if(hasException(data)){
				
			}else{
				if(data.message!=null) {
					alert(data.message);
					return;
				}
//				$(itemTr).find("[name='qtyTd']").html(quantity);
//				$(itemTr).find('[name="tdSymbolAmount"]').html(
//						"&nbsp;" + upSymbol + tdAmount);
//				$(itemTr).find('[name="amount"]').val(tdAmount);
//				//更新discount
//				for ( var o in data) {
//					var tmpUpSymbol = $("#itemTable").find("tr[itemId='" + o + "']").find("[name='upSymbol']").val();
//					$("#itemTable").find("tr[itemId='" + o + "']").find(
//							"[name='tdDiscount']").html(tmpUpSymbol+data[o]);
//				}
			}
			parent.document.getElementById("itemListIframe").contentWindow.location.href = orderquoteObj.url("getItemList");
			parent.document.getElementById("totalIframe").src = orderquoteObj.url("showTotal");
		}
	});

	if (parent.document.getElementById("totalIframe") != undefined) {
		parent.frames["totalIframe"].clearShippingTotalForm();
		parent.frames["totalIframe"].clearTotal();
	}
}

//改变UnitPrice
function changeUnitPrice(itemId) {
	var itemTr = $("#itemTable").find("tr[itemId='" + itemId + "']");
	var unitPriceInput = $(itemTr).find("[name='tdSymbolUnitPrice']").find("input");
	var upSymbol = $(itemTr).find('[name="upSymbol"]').val();
	var upPrecision = $(itemTr).find('[name="upPrecision"]').val();
	var sym = unitPriceInput.attr('value').substring(0,1);
	var unitPrice = unitPriceInput.attr('value').substring(1,unitPriceInput.attr('value').length);
	if (sym != "$") {
		unitPrice = unitPriceInput.attr('value');
	}
	if (unitPrice == "" || !validateUnitPrice(unitPrice)) {
		alert("Please enter valid the item quantity.");
		return;
	}
	var quantity = $(itemTr).find('[name="qtyTd"]').html().replace("&nbsp;","");
	var tdAmount = quantity * unitPrice;
	var oNoobj = new Number(tdAmount);
	tdAmount = oNoobj.toFixed(upPrecision);
	if(tdAmount>9999999) {
		alert("The item amount is too long.");
		return;
	}
	// 更新session
	var prmtCode = $("#prmtCode").val();
	$.ajax( {
		type : "POST",
		url : orderquoteObj.url("updateItemPrice"),
		dataType : "json",
		data :  "prmtCode=" + prmtCode+"&itemId="+itemId+"&quantity="+quantity+"&amount="+tdAmount+"&unitPrice="+unitPrice,
		async : false,
		error : function() {
			alert("System error! Please contact system administrator for help.");
		},
		success : function(data) {
			if(hasException(data)){
				return;
			} else if (data != undefined && data.message != undefined) {
				alert(data.message);
				return;
			}
			if (data != undefined) {
				if (data.unitPrice != undefined && data.unitPrice != "") {
					$(itemTr).find("[name='tdSymbolUnitPrice']").html("&nbsp;" + upSymbol + data.unitPrice);
				}
				if (data.amount != undefined && data.amount != "") {
					$(itemTr).find('[name="tdSymbolAmount"]').html("&nbsp;" + upSymbol + data.amount);
					$(itemTr).find('[name="amount"]').val(data.amount);
				}
				if (data.cost != undefined && data.cost != "") {
					$(itemTr).find("[name='tdCost']").html("&nbsp;$" + data.cost);
				}
				if (data.discountMap != undefined) {
					//更新discount
					for ( var o in data.discountMap) {
						var tmpUpSymbol = $("#itemTable").find("tr[itemId='" + o + "']").find("[name='upSymbol']").val();
						$("#itemTable").find("tr[itemId='" + o + "']").find(
								"[name='tdDiscount']").html(tmpUpSymbol+data.discountMap[o]);
					}
				}
			}
		}
	});
	if (parent.document.getElementById("totalIframe") != undefined) {
		parent.frames["totalIframe"].clearShippingTotalForm();
		parent.frames["totalIframe"].clearTotal();
	}
}

//改变Cost
function changeCost(itemId) {
	var itemTr = $("#itemTable").find("tr[itemId='" + itemId + "']");
	var costInput = $(itemTr).find("[name='tdCost']").find("input");
	var upSymbol = $(itemTr).find('[name="upSymbol"]').val();
	var upPrecision = $(itemTr).find('[name="upPrecision"]').val();
	var sym = costInput.attr('value').substring(0,1);
	var cost = costInput.attr('value').substring(1,costInput.attr('value').length);
	if (sym != "$") {
		cost = costInput.attr('value');
	}
	if (cost == "" || !validateUnitPrice(cost)) {
		alert("Please enter the valid cost.");
		return;
	}
	// 更新session
	$.ajax( {
		type : "POST",
		url : orderquoteObj.url("updateItemCost"),
		dataType : "json",
		data :  "itemId="+itemId+"&cost="+cost,
		async : false,
		error : function() {
			alert("System error! Please contact system administrator for help.");
		},
		success : function(data) {
			if (data != undefined) {
				if(hasException(data)){
					return;
				} else if (data.message != undefined) {
					alert(data.message);
					return;
				}
				if (data.cost != undefined && data.cost != "") {
					$(itemTr).find("[name='tdCost']").html("&nbsp;$" + data.cost);
				}
				if (data.unitPrice != undefined && data.unitPrice != "") {
					$(itemTr).find("[name='tdSymbolUnitPrice']").html("&nbsp;$" + data.unitPrice);
				}
				if (data.amount != undefined && data.amount != "") {
					$(itemTr).find("[name='tdSymbolAmount']").html("&nbsp;$" + data.amount);
				}
			}
		}
	});
	if (parent.document.getElementById("totalIframe") != undefined) {
		parent.frames["totalIframe"].clearShippingTotalForm();
		parent.frames["totalIframe"].clearTotal();
	}
}

function updateStatus(itemId, status){
	
	$("#itemTable").find("tr[itemId='" + itemId + "']").find("[name='changeStatus']").html(status);
	if(status == "CN"){
		setCancelRow(itemId);
	}else{
		setEnableRow(itemId);
	}
	parent.document.getElementById("salesInformationIframe").src = parent.$("#salesInformationIframe").attr("_src");
	//刷新address
	parent.document.getElementById("addrIframe").src = orderquoteObj.url("showAddress");
}

//qty td click function
function qtyTdClick(obj) {
    if(!parent.window.orderquoteObj.editFlag) return;
	var thisObj = $(obj);
	//1.if row status was CN and not do qty click
	if (thisObj.parent("td").parent("tr").find('[name="changeStatus"]').html() == "CN") {
		return;
	}
	//2.if it's service type
	if (thisObj.parent("td").parent("tr").find('[name="type"]').val() != "PRODUCT") {
		return;
	}
	//3.if input element exist
	var tableTdQty = thisObj;
	var quantity = tableTdQty.html().replace('&nbsp;', '');
	if (tableTdQty.html().search('INPUT') != -1
			|| tableTdQty.html().search('input') != -1) {
		return;
	}
	//display input element
	tableTdQty
			.html(
					'<input name="qty1" id="qty1" type="text" class="NFText" value="' + quantity + '" size="1" />')
			.find('input').change( function() {
				changeQty(thisObj.parent("td").parent("tr").attr("itemId"));
			});
}

//UnitPrice td click function
function tdSymbolUnitPriceClick(obj) {
    if(!parent.window.orderquoteObj.editFlag) return;
	var thisObj = $(obj);
	//1.if row status was CN and not do qty click
	if (thisObj.parent("td").parent("tr").find('[name="changeStatus"]').html() == "CN") {
		return;
	}
	//2.if it's service type
	if (thisObj.parent("td").parent("tr").find('[name="type"]').val() != "SERVICE") {
		return;
	}
	if (thisObj.parent("td").parent("tr").find('[name="custType"]') != undefined 
			&& thisObj.parent("td").parent("tr").find('[name="custType"]').val() == "Internal") {
		return;
	}
	//3.if input element exist
	var tableTdQty = thisObj;
	var unitPrice = tableTdQty.html().replace('&nbsp;', '');
	if (tableTdQty.html().search('INPUT') != -1
			|| tableTdQty.html().search('input') != -1) {
		return;
	}
	//display input element
	tableTdQty
			.html('<input name="unitPrice" id="unitPrice" type="text" class="NFText" value="' 
					+ unitPrice + '" size="1"/>')
			.find('input').change( function() {
				changeUnitPrice(thisObj.parent("td").parent("tr").attr("itemId"));
			});
}

//Cost td click function
function tdCostClick(obj) {
    if(!parent.window.orderquoteObj.editFlag) return;
	var thisObj = $(obj);
	//1.if row status was CN and not do qty click
	if (thisObj.parent("td").parent("tr").find('[name="changeStatus"]').html() == "CN") {
		return;
	}
	//2.if it's service type
	if (thisObj.parent("td").parent("tr").find('[name="type"]').val() != "SERVICE") {
		return;
	}
	//3.if input element exist
	var tableTdCost = thisObj;
	var cost = tableTdCost.html().replace('&nbsp;', '');
	if (tableTdCost.html().search('INPUT') != -1
			|| tableTdCost.html().search('input') != -1) {
		return;
	}
	//display input element
	tableTdCost
			.html('<input name="cost" id="cost" type="text" class="NFText" value="' 
					+ cost + '" size="1"/>')
			.find('input').change( function() {
				changeCost(thisObj.parent("td").parent("tr").attr("itemId"));
			});
}

//item切换
function itemTrClick( trObj ){
	var hasClassTrClick = trObj.hasClass("tr_click");
	var itemId = trObj.attr("itemId");
    clickItemId = itemId;
	if(!hasClassTrClick){
		var tmpFlag = selectOneItem(itemId);
		if(tmpFlag == false){
			return;
		}
	}
	if(preTRObj != undefined && preTRObj != null){
	    preTRObj.removeClass("tr_click");
    }
    preTRObj = trObj;
	trObj.addClass("tr_click");
	//处理SERVICE 类型item,
	var tmpChecked = trObj.find(":checkbox").attr("checked");
	$("#itemTable").find("tr").each(function(){
		if($(this).find("[name='parentId']").val() == itemId){
			var catalogNo = $(this).find("[name='catalogNoTd']").val();
			if(catalogNo == 'SC1208' || catalogNo == 'SC1177' || catalogNo == 'SC1487' || catalogNo == 'SC1507'){
				
			}else{
				$(this).find(":checkbox").attr("checked", tmpChecked);
			}
			
		}
	});
}

//setup already delete items
function setCancelRow( itemId ){
	var row = $("#itemTable").find("tr[itemId='" + itemId + "']");
	var qty = row.find( '#qty1' ).val() ;
	var unitPrice = row.find( '#unitPrice' ).val() ;
	
	if( qty != undefined ){
		row.find( '#qtyTd' ).html( qty ) ;
	}
	if( unitPrice != undefined ){
		row.find( '#tdSymbolUnitPrice' ).html( unitPrice ) ;
	}
	row.find( "[name='itemId']" ).attr( 'disabled' , 'true' ) ;
	row.find( "#changeStatus" ).html( "CN" );
	row.addClass( "tr_del" ) ;
}

function setEnableRow( itemId ){
	var row = $("#itemTable").find("tr[itemId='" + itemId + "']");
	row.removeClass( "tr_del" ) ;
	row.find( "[name='itemId']" ).removeAttr("disabled") ;
}

function selectOneItem(itemId){
    //add by zhanghuibin 清空所有的用于form比较的隐藏域
    $("input[id$='_initData']").each(function(){
        $(this).attr("value", "");
    });
	var tmpFlag = true;
	if(parent.$("#itemDetailIframe").contents().find("#itemId").get(0)){
        //modify by zhanghuibin 初次进入页面的时候需执行更新动作,主要用来取得一些初始数据
        parent.window.frames["itemDetailIframe"].updateItemDetail();
		tmpFlag = parent.window.updateMoreInfo();
		if(!tmpFlag){
			var moreTmpIndex = parent.window.getTabIndexBy("More Detail");
	       	if(moreTmpIndex != -1){
	       		parent.window.showTab('dhtmlgoodies_tabView1', moreTmpIndex);
	       	}
			return tmpFlag;
		}
	}
	parent.document.getElementById("itemDetailIframe").src = orderquoteObj.url("getItemDetail")+"&itemId="+itemId;
    //由于页面初始会加载multiAddrIframe，所以这里不需要初始加载
	if(parent.document.getElementById("multiAddrIframe") != null && parent.document.getElementById("shiptoAddrFlag") != null && parent.document.getElementById("shiptoAddrFlag").value=="3" ){
        parent.document.getElementById("multiAddrIframe").src = orderquoteObj.url("showMultiAddress")+"&itemId="+itemId;
    }
	//刷新More Detail
	parent.document.getElementById("moreDetailIframe").contentWindow.location.href = orderquoteObj.url("showMore")+"&itemId="+itemId;
	if($("#itemTable").find("tr[itemId='" + itemId + "']").find("[name='type']").val() == "SERVICE"){
		parent.window.enableTabByTitle("More Detail");
	}else{
		var index = parent.window.activeTabIndex['dhtmlgoodies_tabView1'];
		if(index == 1){
			parent.window.showTab("dhtmlgoodies_tabView1", 0);
		}
		parent.window.disableTabByTitle("More Detail");
	}
    //初次进入edit标志
    parent.editInitFlag = false;
	return tmpFlag;
}

function trUpMove( rowSelf , rowTarget ){
	//The deleted row can't move
	if( $( '#itemTable .tr_click' ).find( "[name='itemId']" ).attr( 'disabled' ) == true ){
		return ;
	}
	//find current row
	if( rowTarget.hasClass( 'tr_del' ) ){
		trUpMove( rowSelf , rowTarget.prev() ) ;
		return ;
	}
	if( rowTarget.html() == null ){
		return ;
	}
	exchangeRow( rowTarget , rowSelf ) ;
}

function trDownMove( rowSelf , rowTarget ){
	//The deleted row can't move
	if( $( '#itemTable .tr_click' ).find( "[name='itemId']" ).attr( 'disabled' ) == true ){
		return ;
	}
	if( rowTarget.hasClass( 'tr_del' ) ){
		trDownMove( rowSelf , rowTarget.next() ) ;
		return ;
	}
	if( rowTarget.html() == null ){
		return ;
	}
	exchangeRow( rowTarget , rowSelf ) ;
}

function exchangeRow( rowTarget , rowSelf ){
	var itemIdFrom = rowSelf.attr("itemId");
	var itemIdTo = rowTarget.attr("itemId");
	var exchangeFlag = true;
	$.ajax({
		url:orderquoteObj.url("exchangeItem"),
		data:"itemIdFrom="+itemIdFrom+"&itemIdTo="+itemIdTo,
		success:function(data){
			if(data != "SUCCESS"){
				alert("System error! Please contact system administrator for help.");
				exchangeFlag = false;
			}
		},
		error:function(){
			alert("System error! Please contact system administrator for help.");
		},
		async:false
	});
	if(exchangeFlag == false){
		return;
	}
	if (rowTarget.attr("tagName") == "TR" && rowSelf.attr("tagName") == "TR" &&  rowTarget.children("td").length == rowSelf.children("td").length){
		var checkBoxStatus = "";
		checkBoxStatus = rowSelf.find( '[name="itemId"]' ).attr( 'checked' ) ;
		for (i=0;i<rowTarget.children("td").length;i++){
			//item no need't sort
			if( i == 1 ){
				continue ;
			}
			temp=rowTarget.children("td").eq(i).html();
			rowTarget.children("td").eq(i).html(rowSelf.children("td").eq(i).html());
			rowSelf.children("td").eq(i).html(temp);
			rowTarget.find( '[name="itemId"]' ).attr( 'checked' , checkBoxStatus ) ;
		}
		rowTarget.addClass( 'tr_click' ) ;
		rowSelf.removeClass( 'tr_click' ) ;
		rowTarget.attr("itemId", itemIdFrom);
		rowSelf.attr("itemId", itemIdTo);
		parent.$("#itemDetailIframe").contents().find("#itemNoSpan").html(rowTarget.find("[name='tdItemNo']").html());
	}else{
		alert("System error! Please contact system administrator for help.");
		parent.document.getElementById("itemListIframe").src = orderquoteObj.url("getItemList");
	}
}

function deleteItems(){
	var delIds = [];
	var oldItemIds = "" ;
	var newItemIds = "" ;
	var rowNumber = "" ;
	var amount =0 ;
	$( '[name="itemId"]' ).each(
		function(){
			if( $(this).attr('checked')== true ){
				delIds.push($(this).val());
			}
		}
	);
	var delItemIdStr = delIds.toString();
	if( delItemIdStr != "" ){
		if( !window.confirm( "Are you sure delete these items?" )   ){
			return ;
		}
	}else{
		alert("Please select the item to continue your operation.");
		return;
	}
	$( '#allCheck' ).attr( 'checked' , false ) ;
	//刷新页面
	$.ajax({
		type: "POST",
		url: orderquoteObj.url("delItem") ,
		data: orderquoteObj.sessNoName+"="+orderquoteObj.sessNoValue+"&delItemIdStr="+delItemIdStr ,
		dataType: "text" ,
		async: false ,
		error:function(){
			alert( "System error! Please contact system administrator for help." ) ;
		},
		success: function( msg ){
			//alert("Delete successfully.")
			//刷新salesInformationIframe
			if(!parent.window.frames["salesInformationIframe"].calAllItemPrice2()) {
				alert("Failed to apply the promotion for it does not meet the conditions.");
			}
			parent.document.getElementById("salesInformationIframe").src = parent.$("#salesInformationIframe").attr("_src");
			parent.document.getElementById("addrIframe").src = orderquoteObj.url("showAddress");
			//refresh the total iframe
			parent.document.getElementById("totalIframe").src = orderquoteObj.url("showTotal");
			parent.document.getElementById("moreDetailIframe").src = '';
			//refreshItemList();
	}});
	parent.document.getElementById("itemListIframe").src = orderquoteObj.url("getItemList");
	var index = parent.window.activeTabIndex['dhtmlgoodies_tabView1'];
	if(index == 1){
		parent.window.showTab("dhtmlgoodies_tabView1", 0);
	}
	parent.window.disableTabByTitle("More Detail");

	parent.window.frames["salesInformationIframe"].sourceSelectChage();
}

function refreshItemList() {
    //add by zhanghuibin 选择选中item
    var trNum = window.parent.$('#itemListIframe').contents().find('#itemTable').find("tr").length;
    if (trNum == undefined || trNum == 0) {
        parent.window.orderquoteObj.originQty = 0;
    } else {
        parent.window.orderquoteObj.originQty = trNum - 1;
    }
    parent.window.orderquoteObj.triggerLast = "1";
    parent.$("#itemListIframe").attr("src", orderquoteObj.url("getItemList"));
}

function initStatus(){
	if (parent.window.orderquoteObj.editFlag) {
		$("#deleteItem").bind("click", deleteItems);
		/*$('[name="qtyTd"]').bind("click", qtyTdClick);
		$('[name="tdSymbolUnitPrice"]').bind("click", tdSymbolUnitPriceClick);
		$('[name="tdCost"]').bind("click", tdCostClick);*/
	} else {
		$("#deleteItem").unbind("click");
		/*$('[name="qtyTd"]').unbind("click");
		$('[name="tdSymbolUnitPrice"]').unbind("click");
		$('[name="tdCost"]').unbind("click", tdCostClick); */
		parent.$("#AddItemTrigger").attr("disabled", true);
		parent.$("#AddBatchItemTrigger").attr("disabled", true);
	}
}

$(document).ready( function() {
	parent.$("#itemDetailIframe").contents().find("#itemId").remove();//为了防止selectOneItem时候默认对当前item保存。
	
	$('#allCheck').click( function() {
		if ($(this).attr('checked') === true) {
			$(':check [name="itemId"]').attr('checked', true);
		} else {
			$(':check [name="itemId"]').attr('checked', false);
			$(this).attr('checked', false);
		}
	});
	// tr点击事件
	$( '#itemTable tr' ).live( 'click' ,function(){
		itemTrClick( $(this) ) ;
	});
	//如果点击的不是每行的第一列，将选中该行的checkBox，并把其它未选中的row中的CheckBox清空。
	//如果是每行的第列将不做清空CheckBox操作
	$( '#itemTable tr td' ).live( 'click' ,function(){
		if( $(this).find('[name="itemId"]').val() == undefined && !$(this).parent().hasClass( 'tr_del' ) ){
			$( '[name="itemId"]' ).attr( "checked" , false ) ;
			$(this).parent( 'tr' ).find( '[name="itemId"]' ).attr( "checked" , true );
		}
	});
	//鼠标移动事件
	$( '#itemTable tr' ).live( 'mouseover' ,function(){
		$(this).addClass("tr_hover");   
	});
	$( '#itemTable tr' ).live( 'mouseout' ,function(){
		$(this).removeClass("tr_hover"); 
	});
	//item上下移动事件
	$( '#upTableTd' ).click(function(){
		rowUp = $( '#itemTable .tr_click' ).prev() ;
		rowSelect = $( '#itemTable .tr_click' ) ;
		trUpMove( rowSelect , rowUp ) ;
	});
	$('#downTableTd' ).click(function(){
		rowDown = $('#itemTable .tr_click' ).next() ;
		rowSelect = $( '#itemTable .tr_click' ) ;
		trDownMove( rowSelect , rowDown ) ;
	});
	initItemList();
	initStatus();
});
// **************************************************************************************************************
// **************************************************************************************************************
function setArrowImage() {
	var itemTableObj = $("#itemTable");
	var i = 0;
    var compareVal = "";
	$(itemTableObj).find("tr").each(
					function() {
						++i;
						var tmpParentId = $(this).find("[name='parentId']").val();
						if (tmpParentId && tmpParentId != 0 && tmpParentId != "null") {
							$(this).find("[name='tdNameShort']").find("img").remove();
							var ppId = $("#itemTable tr[itemId='"+tmpParentId+"']").find("[name='parentId']").val();
							var tmpId = $(this).attr("itemId");
							var lastFlag = isLastSubService($(this),tmpParentId, tmpId);
							// 子子服务
							if (ppId) {
								var imgSrc = "images/arrow_001_2.gif";
								var plastFlag = isLastSubService($(this),ppId, tmpParentId);
								var pppId = $("#itemTable tr[itemId='"+ppId+"']").find("[name='parentId']").val();
								if (pppId) {
									var imgSrc = "images/arrow_001.gif";
									if (!lastFlag) {
										if (!plastFlag) {
											var tdClass = "list_td2_new1_3";
										} else {
											var tdClass = "list_td2_new1_3";
										}
									} else {
										if (!plastFlag) {
											var tdClass = "list_td2_new1_3";
										} else {
											var tdClass = "list_td2_new1_end_3";
										}
									}
								} else {
									if (!lastFlag) {
										if (!plastFlag) {
											var tdClass = "list_td2_new2_3";
										} else {
											var tdClass = "list_td2_new2_2";
										}
									} else {
										if (!plastFlag) {
											var tdClass = "list_td2_new2_4";
										} else {
											var tdClass = "list_td2_new2_end_2";
										}
									}
								}
							} else {
								var imgSrc = "images/arrow_001.gif";
								if (!lastFlag) {
									var tdClass = "list_td2_new2";
								} else {
									var tdClass = "list_td2_new2_end";
								}
							}
							$(this).find("[name='tdNameShort']").prepend("<img src='" + imgSrc + "'></img>");
							$(this).find("[name='tdNameShort']").parent().removeClass();
							$(this).find("[name='tdNameShort']").parent().addClass(tdClass);
						}

                        //modify by zhanghuibin
                        compareVal = $(this).find("[name*='changeStatus']").html().replace("&nbsp;", "").toString();
                        if (compareVal == 'Canceled' || compareVal == 'CN') {
                            $(this).addClass('tr_del');
                            $(this).find("[name='itemId']").attr('disabled', 'true');
                        }
					});
}

//modify by zhanghuibin
function isLastSubService(itemTRObj, parentId, itemId) {
    var tmpId = "";
    var tmpParentId = itemTRObj.next().find("[name='parentId']").val();
    if(tmpParentId == undefined || tmpParentId == ""){
        return true;
    }else if(tmpParentId != parentId){
        return true;
    }else{
        return false;
    }
    /*if (tmpParentId == parentId) {
        tmpId = itemTRObj.next().attr("itemId");
    }
    if (tmpId == itemId) {
        return true;
    } else {
        return false;
    }*/
}

function reSetItemNo(){
	var itemNo = 1 ;
	$("#itemTable").find( "tr" ).each(function(){
		$(this).find("[name='tdItemNo']").html( itemNo++ ) ;
	}) ;
}