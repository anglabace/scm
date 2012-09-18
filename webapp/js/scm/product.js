$(function() {
	var psId = $("#psId").val();
	var catalogNo = $("#psNo2").val();
	var unitCost = $("#unitCost").val();
	var saftyStock = $("#saftyStock").val();
	var minOrderQty = $("#minOrderQty").val();
	var leadTime = $("#leadTime").val();
	var sessionProductId = $("#sessionProductId").val();
	$('#supplierIframe').attr(
			'src',
			"product/product!productOfSupplierList.action?id=" + psId
					+ "&type=" + pdtServType + "&catalogNo=" + catalogNo
					+ "&leadTime=" + leadTime + "&minOrderQty=" + minOrderQty
					+ "&saftyStock=" + saftyStock + "&unitCost=" + unitCost
					+ "&sessionProductId=" + sessionProductId);
	$("#cancelAllTrigger").click(function() {
		if (confirm("Are you sure to continue clear data?")) {
			window.location.reload();
		}
	});
	$("#tabTabdhtmlgoodies_tabView1_2").click(
			function() {
				if ($('#inventroyIframe').attr('src') == undefined
						|| $('#inventroyIframe').attr('src') == '') {
					$('#inventroyIframe').attr(
							'src',
							"product/product!list.action?psId=" + psId
									+ "&type=" + pdtServType + "&sessionPSID="
									+ sessionProductId);
				}
			});

	$("#tabTabdhtmlgoodies_tabView1_3").click(
			function() {
				if ($('#breakdownList_iframe').attr('src') == undefined
						|| $('#breakdownList_iframe').attr('src') == '') {
					$('#breakdownList_iframe').attr(
							'src',
							"product/breakdown!list.action?psId=" + psId
									+ "&sessionPSID=" + sessionPSID);
				}
			});
	$("#tabTabdhtmlgoodies_tabView1_4").click(
			function() {
				if ($('#compositeList_iframe').attr('src') == undefined
						|| $('#compositeList_iframe').attr('src') == '') {
					$('#compositeList_iframe').attr(
							'src',
							"product/component!list.action?psId=" + psId
									+ "&sessionPSID=" + sessionProductId);
				}
			});
	$("#tabTabdhtmlgoodies_tabView1_5").click(
			function() {
				if ($('#supplierIframe').attr('src') == undefined
						|| $('#supplierIframe').attr('src') == '') {
					$('#supplierIframe').attr(
							'src',
							"product/product!productOfSupplierList.action?id="
									+ psId + "&type=" + pdtServType
									+ "&catalogNo=" + catalogNo + "&leadTime="
									+ leadTime + "&minOrderQty=" + minOrderQty
									+ "&saftyStock=" + saftyStock
									+ "&unitCost=" + unitCost
									+ "&sessionProductId=" + sessionProductId);
				}
			});

	var btRatio = $("#btRatio").val();
	var vtRatio = $("#vtRatio").val();
	// alert(btRatio + ">>>" + vtRatio);
	$("#tabTabdhtmlgoodies_tabView1_6").click(
			function() {
				if ($('#priceIframe').attr('src') == undefined
						|| $('#priceIframe').attr('src') == '') {
					$('#priceIframe').attr(
							'src',
							"product/product_pricing.action?sessionProductId="
									+ sessionProductId + "&catalogNo="
									+ catalogNo + "&vtRatio=" + vtRatio
									+ "&btRatio=" + btRatio);
				}
			});
	$("#tabTabdhtmlgoodies_tabView1_7").click(
			function() {
				if ($('#moreInfoIframe').attr('src') == undefined
						|| $('#moreInfoIframe').attr('src') == '') {
					$('#moreInfoIframe').attr(
							'src',
							"product/product!showMoreInfo.action?catalogNo="
									+ catalogNo + "&id=" + psId
									+ "&sessionProductId=" + sessionProductId
									+ "&type=" + pdtServType);
				}
			});
	$("#tabTabdhtmlgoodies_tabView1_8").click(
			function() {
				if ($('#miscIframe').attr('src') == undefined
						|| $('#miscIframe').attr('src') == '') {
					$('#miscIframe').attr(
							'src',
							"product/product!showMiscAct.action?id=" + psId
									+ "&catalogNo=" + catalogNo + "&type="
									+ pdtServType);
				}
			});
	$("#tabTabdhtmlgoodies_tabView1_9").click(
			function() {
				if ($('#salesIframe').attr('src') == undefined
						|| $('#salesIframe').attr('src') == '') {
					$('#salesIframe').attr(
							'src',
							"product/product!showProductSales.action?id="
									+ psId + "&catalogNo=" + catalogNo
									+ "&isFalse=no");
				}
			});
	if (defaultTab != null)
		$("#tabTabdhtmlgoodies_tabView1_" + defaultTab).trigger("click");

	$("#baseForm").validate({
		errorClass : "validate_error",
		highlight : function(element, errorClass) {
			$(element).addClass(errorClass);
		},
		unhighlight : function(element, errorClass, validClass) {
			$(element).removeClass(errorClass);
		},
		invalidHandler : function(form, validator) {
			$.each(validator.invalid, function(key, value) {
				alert(value);
				$("[name='" + key + "']").focus();
				return false;
			});
		},
		rules : {
			"productDTO.catalogNo" : {
				required : true
			},
			"productDTO.name" : {
				required : true
			},
			"productDTO.shortDesc" : {
				required : true
			},
			"productDTO.retestDays" : {
				number : true
			},
			"productDTO.expirationDays" : {
				number : true
			}
		},
		messages : {
			"productDTO.catalogNo" : {
				required : "Please enter the Catalog No"
			},
			"productDTO.name" : {
				required : "Please enter the Name"
			},
			"productDTO.shortDesc" : {
				required : "Please enter the Description" 
			},
			"productDTO.retestDays" : {
				number : "This 'Retest Days' must be a digit!"
			},
			"productDTO.expirationDays" : {
				number : "This 'Expiration Days' must be a digit!"
			}
		},
		errorPlacement : function(error, element) {
		}
	});
	 
	$("#generalForm").validate({
		errorClass:"validate_error",
		highlight: function(element, errorClass) {
		$(element).addClass(errorClass);
		},
		unhighlight: function(element, errorClass, validClass) {
		$(element).removeClass(errorClass);
		},
		invalidHandler: function(form, validator) {
	        $.each(validator.invalid, function(key,value){
	            alert(value);
	            $("[name='"+key+"']").focus();
	            return false;
	        });
		},
		rules: {
			"productDTO.qtyUom": {required:true},
			"productDTO.storageCondition.temperature": {required:true,number:true},
			"productDTO.storageCondition.humidity": {required:true,number:true},
			"productDTO.shipCondition.temperature": {required:true,number:true},
			"productDTO.shipCondition.domShipWeight": {required:true,number:true},
			"productDTO.shipCondition.intlShipWeight": {required:true,number:true},
		/*	"productDTO.federalTaxCls": {required:true},*/
			"productDTO.length": {number:true},
			"productDTO.width": {number:true},
			"productDTO.height": {number:true},
			"productDTO.size": {number:true},
			"productDTO.altSize": {number:true},
			"productDTO.shipCondition.specShipCharge": {number:true}
		},
		messages: {
			"productDTO.qtyUom": {
				required: "Please specify Quantity Uom"
			},
			"productDTO.storageCondition.temperature": {
				required: "Please specify Strorage Temperature",number: "This 'Temperature' must be a digit!"
			},
			"productDTO.storageCondition.humidity": {
				required: "Please specify Storage Humidity",number: "This 'Humidity' must be a digit!"
			},
			"productDTO.shipCondition.temperature": {
				required: "please specify Shipping Temperature",number: "This 'Temperature' must be a digit!"
			},
			"productDTO.shipCondition.domShipWeight": {
				required: "please specify Shipping Domestic Ship Weight",number: "This 'Domestic Ship Weight' must be a digit!"
			},
			"productDTO.shipCondition.intlShipWeight": {
				required: "please specify Shipping  Int'l Ship Weight",number: "This 'Int'l Ship Weight' must be a digit!"
			}/*,
			"productDTO.federalTaxCls": {
				required: "please specify National Tax Status"
			}*/,
			"productDTO.length": {
				number: "This 'length' must be a digit!"
			},
			"productDTO.width": {
				number: "This 'width' must be a digit!"
			},
			"productDTO.height": {
				number: "This 'height' must be a digit!"
			},
			"productDTO.size": {
				number: "This 'size' must be a digit!"
			},
			"productDTO.altSize": {
				number: "This 'altSize' must be a digit!"
			},
			"productDTO.shipCondition.specShipCharge": {
				number: "This 'Special Shipping Charge' must be a digit!"
			}
		},
		errorPlacement: function(error, element) {}		
	});
	
	
	$('[name="psShipExemptFlag"]').click(function() {
		if ($(this).attr("checked")) {
			$('[name="psSpecShipCharge"]').val("");
			$('[name="psSpecShipCharge"]').attr("disabled", true);
		} else {
			$('[name="psSpecShipCharge"]').attr("disabled", false);
		}
	});

	$("#saveApprovedTrigger")
			.click(
					function() {
						var approved = $("#approved").val();
						approved = $.trim(approved);
						var approvedReason = $("#approvedReason").val();
						approvedReason = $.trim(approvedReason);
						var approvedType = $("#approvedType").val();
						var catalogId = $("#catalogId").val();
						var oldApproved = $("#oldApproved").val();
						if (oldApproved == approved) {
							alert("Please modify the name to continue your operation.");
							return;
						}
						if (!approved) {
							alert("Please enter the name.");
							return;
						}
						if (!approvedReason) {
							alert("Please enter the reason.");
							return;
						}
						if (approved.length > 50) {
							alert("The length of the Category Name  is out of maximum limit - max length 50.");
							return;
						}
						$
								.ajax({
									url : "product/product!productApprovedSaveSession.action",
									type : "get",
									data : "approved=" + approved
											+ "&approvedReason="
											+ approvedReason + "&approvedType="
											+ approvedType
											+ "&sessionProductId="
											+ sessionProductId + "&catalogId="
											+ catalogId,
									dataType : "json",
									success : function(data) {
										if (hasException(data)) {
											$('#saveApprovedTrigger').attr(
													"disabled", false);
										} else {
											if (data.message == "success") {
												alert("The modification is ready to be submitted for the evaluation and will be applied only after it’s approved.");
											} else {
												if (data) {
													alert(data.message);
												} else {
													alert("System error! Please contact system administrator for help.");
												}
											}
										}
									},
									error : function(data) {
										if (data)
											alert(data.message);
										else
											alert("System error! Please contact system administrator for help.");
									},
									async : false
								});
						$("#modifyNameDialog").dialog("close");
					});

	$("#saveApprovedStatusTrigger")
			.click(
					function() {
						var approved = $("#statusApprove").val();
						approved = $.trim(approved);
						var approvedReason = $("#statusReason").val();
						approvedReason = $.trim(approvedReason);
						var approvedType = $("#approvedStatusType").val();
						var oldApproved = $("#oldStatusApproved").val();
						if (oldApproved == approved) {
							alert("Please modify the status to continue your operation.");
							return;
						}
						if (!approvedReason) {
							alert("Please enter the reason.");
							return;
						}
						$
								.ajax({
									url : "product/product!productApprovedSaveSession.action",
									type : "get",
									data : "approved=" + approved
											+ "&approvedReason="
											+ approvedReason + "&approvedType="
											+ approvedType
											+ "&sessionProductId="
											+ sessionProductId,
									dataType : "json",
									success : function(data) {
										if (hasException(data)) {
											$('#saveApprovedStatusTrigger')
													.attr("disabled", false);
										} else {
											if (data.message == "success") {
												alert("The modification is ready to be submitted for the evaluation and will be applied only after it’s approved.");
											} else {
												if (data) {
													alert(data.message);
												} else {
													alert("System error! Please contact system administrator for help.");
												}
											}
										}
									},
									error : function(data) {
										if (data)
											alert(data.message);
										else
											alert("System error! Please contact system administrator for help.");
									},
									async : false
								});
						$("#modifyStatusDialog").dialog("close");
					});
});

function selectCross() {

	var crossSelect = $("#cross option:selected").val();
	if (crossSelect == '') {
		$("#tbl").css("display", "block");
		$("#tb2").css("display", "none");
	} else {
		$("#tb2").css("display", "block");
		$("#tbl").css("display", "none");
	}
}

function generalCrossClick() {
	var sessionProductId = $("#sessionProductId").val();
	var psId = $("#psId").val();
	$('#generalCrossDialog').dialog({
		autoOpen : false,
		height : 450,
		width : 720,
		modal : true,
		bgiframe : true,
		buttons : {}
	});
	var crossSelect = $("#cross option:selected").val();
	var url = "";
	if (crossSelect == null || crossSelect == '') {
		url = "product/product!showCrossCreateFormAct.action?id=" + psId
				+ "&type=Product" + "&sessionProductId=" + sessionProductId
	} else {
		url = "product/product!showCrossCreateFormAct.action?relationId="
				+ crossSelect + "&id=" + psId
				+ "&type=Product&sessionProductId=" + sessionProductId;
	}
	if (crossSelect) {
		var cross = $("#cross option:selected").text();
		$("#generalCrossDialog").dialog("option", "title", cross);
	} else {
		$("#generalCrossDialog").dialog("option", "title",
				"Add Related Selling Item");
	}
	$('#generalCrossDialog')
			.dialog(
					"option",
					"open",
					function() {
						var htmlStr = '<iframe name="crossDialog" id="crossDialog" src="'
								+ url
								+ '" height="400" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
						$('#generalCrossDialog').html(htmlStr);
					});
	$('#generalCrossDialog').dialog('open');
}

function modifyNameClick() {
	if ($("[name='nameAppr']").val() == "true") {
		alert("The product name have been modified.");
		return;
	}
	$("#modifyNameDialog").dialog({
		autoOpen : false,
		height : 300,
		width : 600,
		modal : true,
		bgiframe : true,
		buttons : {}
	});
	$('#modifyNameDialog').dialog("option", "open", function() {

	});
	$('#modifyNameDialog').dialog('open');
}
function modifyStatusClick() {
	if ($("[name='statusAppr']").val() == "true") {
		alert("The product status have been modified.");
		return;
	}
	$("#modifyStatusDialog").dialog({
		autoOpen : false,
		height : 300,
		width : 600,
		modal : true,
		bgiframe : true,
		buttons : {}
	});
	$('#modifyStatusDialog').dialog("option", "open", function() {

	});
	$('#modifyStatusDialog').dialog('open');
}

function generateCatalogNoClick() {
	$("#generateCatalogNoDialog").dialog({
		autoOpen : false,
		height : 300,
		width : 600,
		modal : true,
		bgiframe : true,
		buttons : {}
	});
	$('#generateCatalogNoDialog')
			.dialog(
					"option",
					"open",
					function() {
						var html = '<iframe name="catalogNoListIframe" id="catalogNoListIframe" src="${ctx}/product/product!searchCatalogNoRules.action?type=PRODUCT" width="100%" height="200" frameborder="0" scrolling="no"></iframe>';
						$('#generateCatalogNoDialog').html(html);
					});
	$('#generateCatalogNoDialog').dialog('open');
}

// misc iframe checkbox control end
function pdtServSaveAll(type) {
	if ($('#baseForm').valid() == false) {
		return false;
	}
	if ($('#generalForm').valid() == false) {
		return false;
	}
	/*
	 * alert($("#priceIframe").contents().find('priceForm').valid()); if
	 * ($("#priceIframe").contents().find('priceForm').valid() == false) {
	 * return false; }
	 */
	if ($('#expirationDays').val() != "" && $('#retestDays').val() != "") {
		alert("Two fields only allow to fill in one,Retest Days and Expiration Days!!!!");
		return false;
	}
	var prefWarehouse = $("#prefWarehouse",
			$("#inventroyIframe").contents().find("body")).val();// added by
	// zyl
	// 2010-5-5
	if (!prefWarehouse && $("#inventroyIframe").attr("src")) {
		$("#tabTabdhtmlgoodies_tabView1_2").trigger("click");
		alert("Please enter the Always use warehouse");
		$("#prefWarehouse", $("#inventroyIframe").contents().find("body"))
				.focus();
		return;
	}
	var prefStorage = $("#prefStorage",
			$("#inventroyIframe").contents().find("body")).val();// added by
	// zyl
	// 2010-5-5
	if (!prefStorage && $("#inventroyIframe").attr("src")) {
		$("#tabTabdhtmlgoodies_tabView1_2").trigger("click");
		alert("Please enter the Storage Location");
		$("#prefStorage", $("#inventroyIframe").contents().find("body"))
				.focus();
		return;
	}
	var altWarehouseFlag = $("#altWarehouseFlag",
			$("#inventroyIframe").contents().find("body")).attr("checked");
	var leadTime = $("#leadTime", $("#supplierIframe").contents().find("body"))
			.val();
	if (!leadTime && $("#supplierIframe").attr("src")) {
		$("#tabTabdhtmlgoodies_tabView1_5").trigger("click");
		alert("Please enter the Lead Times(Days)");
		$("#leadTime", $("#supplierIframe").contents().find("body")).focus();
		return;
	}
	var saftyStock = $("#saftyStock",
			$("#supplierIframe").contents().find("body")).val();
	if (!saftyStock
			&& $("#saftyStock", $("#supplierIframe").contents().find("body"))
					.attr("disabled") == false) {
		$("#tabTabdhtmlgoodies_tabView1_5").trigger("click");
		alert("Please enter the Re-Order When Stock Is Below");
		$("#saftyStock", $("#supplierIframe").contents().find("body")).focus();
		return;
	}
	var minOrderQty = $("#minOrderQty",
			$("#supplierIframe").contents().find("body")).val();
	var unitCost = $("#unitCost", $("#supplierIframe").contents().find("body"))
			.val();
	
	if (!unitCost
			&& $("#unitCost", $("#supplierIframe").contents().find("body"))
					.attr("disabled") == false) {
		$("#tabTabdhtmlgoodies_tabView1_5").trigger("click");
		alert("Please enter the Current Unit Cost Basis");
		$("#unitCost", $("#supplierIframe").contents().find("body")).focus();
		return;
	}
	if(unitCost!=null || unitCost!=""){
		formStr += "&productDTO.unitCost=" + unitCost;
	}
	if (altWarehouseFlag == true) {
		altWarehouseFlag = "Y";
	} else {
		altWarehouseFlag = "N";
	}
	var formStr = '';
	formStr += $('#baseForm').serialize();
	// --------------add by zhougang0---------------

	if ($("#priceIframe").attr("src")) {
		var objPrice = document.getElementById("priceIframe").contentWindow;
		if (objPrice.$('#priceForm').valid() === false) {
			return false;
		} else {
			formStr += "&" + objPrice.$('#priceForm').serialize();
		}
	} else {
		var btRatio = $("#btRatio").val();
		var vtRatio = $("#vtRatio").val();
	 
		if (btRatio != null || vtRatio != "" || btRatio != null
				|| btRatio != "") {
			var all = parseFloat(btRatio) + parseFloat(vtRatio);
			if (parseFloat(btRatio) + parseFloat(vtRatio) != 1) {
				alert("Please enter the right digit for VT Ration and  BT Ration!  "); 
				return false;
			}
		}
		if (vtRatio == null || vtRatio == "") { 
			
			alert("Please enter VT Ration for this product!");
			return false;
		} else {
			formStr += "&productDTO.vtRatio=" + vtRatio;
		}
		if (btRatio == null || btRatio == "") {
			alert("Please enter BT Ration for this product!");
			return false;
		} else {
			formStr += "&productDTO.btRatio=" + btRatio;
		}
	}

	// ------------------------end ----------
	formStr += "&" + $('#generalForm').serialize();
	var detail_form = $("#detailIframe").contents().find("#detailForm");
	formStr += "&" + detail_form.serialize();
	var pdtExtInfo_form = $("#moreInfoIframe").contents().find("#extendedInfo");
	formStr += "&" + pdtExtInfo_form.serialize();

	if ($("#miscIframe").attr("src")) {
		var objMisc = document.getElementById("miscIframe").contentWindow;
		if (objMisc.$('#miscForm').valid() === false) {
			return false;
		}
		formStr += "&" + objMisc.$('#miscForm').serialize();
	} else {
		var sellingPriceCmsn = $("#sellingPriceCmsn").val();
		//alert(sellingPriceCmsn);
		if (!(isNaN(sellingPriceCmsn) || sellingPriceCmsn != null || sellingPriceCmsn != "")) {
			formStr += "&productDTO.sellingPriceCmsn=" + sellingPriceCmsn;
		}

		var grossProfitCmsn = $("#grossProfitCmsn").val();
		//alert(grossProfitCmsn);
		if (!(isNaN(grossProfitCmsn) || grossProfitCmsn != null || grossProfitCmsn != "")) {
			formStr += "&productDTO.grossProfitCmsn=" + grossProfitCmsn;
		}

		var unitRateCmsn = $("#unitRateCmsn").val();
	//	alert(unitRateCmsn);
		if (!(isNaN(unitRateCmsn) || unitRateCmsn != null || unitRateCmsn != "")) {
			formStr += "&productDTO.unitRateCmsn=" + unitRateCmsn;
		}

		formStr += "&productDTO.returnPoints=" + $("#returnPoints").val();
		formStr += "&productDTO.priceByPoints=" + $("#priceByPoints").val();
		formStr += "&productDTO.noticeSendType=" + $("#noticeSendType").val();
		formStr += "&productDTO.noticeGenerateTime="
				+ $("#noticeGenerateTime").val();
		formStr += "&productDTO.customerInfo=" + $("#customerInfo").val();
	}
	if ($("#inventroyIframe").attr("src")) {
		formStr += "&productDTO.prefWarehouse=" + prefWarehouse;
		formStr += "&productDTO.altWarehouseFlag=" + altWarehouseFlag;
		formStr += "&productDTO.prefStorage=" + prefStorage;
	}

	if ($("#supplierIframe").attr("src")) {
		if (document.getElementById("supplierIframe").contentWindow.$(
				'#supplierForm').valid() === false) {
			return false;
		}
		formStr += "&productDTO.leadTime=" + leadTime;
		formStr += "&productDTO.saftyStock=" + saftyStock;
		formStr += "&productDTO.minOrderQty=" + minOrderQty;
		formStr += "&productDTO.unitCost=" + unitCost;
	} else {
		leadTime = $("#leadTime").val();
		formStr += "&productDTO.leadTime=" + leadTime;
		saftyStock = $("#saftyStock").val();
		formStr += "&productDTO.saftyStock=" + saftyStock;
		minOrderQty = $("#minOrderQty").val();
		formStr += "&productDTO.minOrderQty=" + minOrderQty;
		unitCost = $("#unitCost").val();
		if (isNaN(unitCost) || unitCost == null || unitCost == "") {
			unitCost = 0;
		}
		formStr += "&productDTO.unitCost=" + unitCost;
	}
	/*
	 * // Intermediate --> if($("#breakdownList_iframe").attr('src')){ var trObj =
	 * $("#breakdownList_iframe").contents().find("#breakdownListTable").find("tr");
	 * //var trlen = objBreakdown.$("#breakdownListTable" + " tr").length; var
	 * imdStrs = ""; trObj.each(function(){ var seqStr =
	 * $(this).find("span[id^='seqNo_']").text(); imdStrs += seqStr + "<->";
	 * 
	 * var idStr = $(this).find("[name='bdid']").attr("value"); imdStrs += idStr + "<->";
	 * 
	 * var catalogNoStr = $(this).find("span[id^='catalogNo_']").text(); imdStrs +=
	 * catalogNoStr + "<->";
	 * 
	 * var itemStr = $(this).find("span[id^='item_']").text(); imdStrs +=
	 * itemStr + "<->";
	 * 
	 * var leadTimeStr = $(this).find("span[id^='leadTime_']").text(); imdStrs +=
	 * leadTimeStr + "<->";
	 * 
	 * var symbolStr = $(this).find("span[id^='symbol_']").text(); imdStrs +=
	 * symbolStr + "<->"; var intmdKeyword =
	 * $(this).find("[name='intmdKeyword_']").val(); imdStrs += intmdKeyword + "<->"; //
	 * priceStr = $(this).find("span[id^='price_']").html(); // imdStrs +=
	 * priceStr + "<->"; var text_quantity =
	 * $(this).find("[name='text_quantity']"); quantityStr =
	 * $(this).find("span[id^='quantity_']").text(); if(!quantityStr){
	 * quantityStr = text_quantity.val(); } imdStrs += quantityStr + "<->";
	 * requiredFlagStr =
	 * $(this).find("[name='requiredFlag']").attr("checked")?"Y":"N"; imdStrs +=
	 * requiredFlagStr + "<=>"; }); imdStrs
	 * =imdStrs.substring(0,imdStrs.length-3); formStr += "&imdStr=" + imdStrs; }
	 */
	// Intermediate end
	// composite -->
	if ($("#moreInfoIframe").attr('src')) {
		var objRefernce = $("#moreInfoIframe").contents().find(
				"#referenceTable").find("tr");
		var referStrs = "";
		objRefernce.each(function() {
			var productReference = $(this).find("[id^='productReference']")
					.val();
			if (productReference != null && productReference != ""
					&& productReference != undefined) {
				var id = $(this).find("[id^='productReferenceId']").val();
				if (id != null && id != "" && id != undefined) {
					referStrs += id + "<,>";
				} else {
					referStrs += "<,>";
				}
				referStrs += productReference + "<,>";
				var url = $(this).find("[id^='productReferenceUrl']").val();
				referStrs += url + "<;>";
			}
		});
		formStr += "&referenceList=" + referStrs;
	}
	if ($("#compositeList_iframe").attr('src')) {
		var objComposite = document.getElementById("compositeList_iframe").contentWindow;

		trObj = objComposite.$("#compositeListTable").find("tr");

		var cmpsStrs = "";
		trObj.each(function() {
			var seqStr = $(this).find("span[id^='seqNo_']").html();
			cmpsStrs += seqStr + "<->";

			var idStr = $(this).find("[name='cmpsId']").attr("value");
			cmpsStrs += idStr + "<->";

			// var catalogNoStr = $(this).find("span[id^='catalogNo_']").html();
			// cmpsStrs += catalogNoStr + "<->";

			// var itemStr = $(this).find("span[id^='item_']").html();
			// cmpsStrs += itemStr + "<->";

			// var leadTimeStr = $(this).find("span[id^='leadTime_']").html();
			// cmpsStrs += leadTimeStr + "<->";

			// var symbolStr = $(this).find("span[id^='symbol_']").html();
			// cmpsStrs += symbolStr + "<->";

			// var priceStr = $(this).find("span[id^='price_']").html();
			// cmpsStrs += priceStr + "<->";

			var text_quantity = $(this).find("[name='text_quantity']");
			var quantityStr = $(this).find("span[id^='quantity_']").text();
			if (!quantityStr) {
				if (text_quantity.val()) {
					quantityStr = text_quantity.val();
				} else {
					alert("Please enter the quantity");
					$(this).find("[name='text_quantity']").focus();
					return;
				}
			}
			cmpsStrs += quantityStr + "<=>";
			// cmpsStrs += "<=>";
		});
		cmpsStrs = cmpsStrs.substring(0, cmpsStrs.length - 3);
		formStr += "&cmpsStr=" + cmpsStrs;
	}
	var sizeStr = $("#size").val();
	formStr += "&sizeStr=" + sizeStr;
	// composite end
	//alert(formStr);
	defaultTab = activeTabIndex['dhtmlgoodies_tabView1'];
	var type2 = pdtServType.toLowerCase();
	$
			.ajax({
				type : "POST",
				url : "product/product!save.action",
				data : formStr,
				dataType : 'json',
				success : function(data, textStatus) {
					if (hasException(data)) {
						$('#SaveAllTrigger').attr("disabled", false);
					} else {
						  alert("The "+pdtServType+" is saved successfully!");
						isSaved = true;
						location.href = "product/product!input.action?type="
								+ data.type + "&id=" + data.id + "&defaultTab="
								+ defaultTab;
					}
				},
				error : function(xhr, textStatus) {
					alert("System error! Please contact system administrator for help.");
					if (textStatus == 'timeout') {
						// alert("Timeout!");
					}

					if (textStatus == 'parsererror') {
						tmp = xhr.responseText.split('{', 2);
						alert(tmp[0]);
						// document.write(tmp[0]);
					}
				}
			});
}
