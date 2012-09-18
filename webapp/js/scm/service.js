$(function() {
	var pdtId = $("#psId").val();
	var psId = $("#psId").val();
	var catalogNo = $("#psNo2").val();
	var leadTime = $("#leadTime").val();
	var sessionServiceId = $("#sessionServiceId").val();
	$('#supplierIframe').attr(
			'src',
			"serv/serv!serviceOfSupplierList.action?id=" + psId + "&type="
					+ pdtServType + "&catalogNo=" + catalogNo + "&leadTime="
					+ leadTime + "&sessionServiceId=" + sessionServiceId);

	$("#cancelAllTrigger").click(function() {
		if (confirm("Are you sure to continue clear data?")) {
			window.location.reload();
		}
	});
	// alert("test");
	$("#tabTabdhtmlgoodies_tabView1_2").click(
			function() {
				if ($('#inventroyIframe').attr('src') == undefined
						|| $('#inventroyIframe').attr('src') == '') {
					$('#inventroyIframe').attr(
							'src',
							"serv/serv_inventory!list.action?psId=" + psId
									+ "&type=" + pdtServType
									+ "&sessionServiceId=" + sessionServiceId);
				}
			});

	$("#tabTabdhtmlgoodies_tabView1_3").click(
			function() {
				if ($('#breakdownList_iframe').attr('src') == undefined
						|| $('#breakdownList_iframe').attr('src') == '') {
					$('#breakdownList_iframe').attr(
							'src',
							"serv/breakdown!list.action?psId=" + psId
									+ "&type=" + pdtServType
									+ "&sessionServiceId=" + sessionServiceId);
				}
			});
	$("#tabTabdhtmlgoodies_tabView1_4").click(
			function() {
				if ($('#compositeList_iframe').attr('src') == undefined
						|| $('#compositeList_iframe').attr('src') == '') {
					$('#compositeList_iframe').attr(
							'src',
							"product/productComposite/showCompositeListAct?pdtId="
									+ pdtId);
				}
			});
	$("#tabTabdhtmlgoodies_tabView1_5").click(
			function() {
				if ($('#supplierIframe').attr('src') == undefined
						|| $('#supplierIframe').attr('src') == '') {
					$('#supplierIframe').attr(
							'src',
							"serv/serv!serviceOfSupplierList.action?id=" + psId
									+ "&type=" + pdtServType + "&catalogNo="
									+ catalogNo + "&leadTime=" + leadTime
									+ "&sessionServiceId=" + sessionServiceId);
				}
			});
	var serviceType = $("#serviceClsId option:selected").text();
	var clsId = $("#serviceClsId option:selected").val();

	var btRatio = $("#btRatio").val();
	var vtRatio = $("#vtRatio").val(); 
 
	$("#tabTabdhtmlgoodies_tabView1_6").click(
			function() {
				if ($('#priceIframe').attr('src') == undefined
						|| $('#priceIframe').attr('src') == '') {
					$('#priceIframe').attr(
							'src',
							"serv/service_pricing!searchServicePrice.action?serviceId="
									+ psId + "&serviceType=" + serviceType
									+ "&clsId=" + clsId + "&sessionServiceId="
									+ sessionServiceId + "&vtRatio=" + vtRatio
									+ "&btRatio=" + btRatio);
				}
			});

	$("#tabTabdhtmlgoodies_tabView1_8").click(
			function() {
				if ($('#miscIframe').attr('src') == undefined
						|| $('#miscIframe').attr('src') == '') {
					$('#miscIframe').attr(
							'src',
							"serv/serv!showMiscAct.action?id=" + psId
									+ "&catalogNo=" + catalogNo + "&type="
									+ pdtServType);
				}
			});
	$("#tabTabdhtmlgoodies_tabView1_9").click(
			function() {
				if ($('#salesIframe').attr('src') == undefined
						|| $('#salesIframe').attr('src') == '') {
					$('#salesIframe')
							.attr(
									'src',
									"serv/serv!showServiceSales.action?id="
											+ pdtId + "&catalogNo=" + catalogNo
											+ "&isFalse=no");
				}
			});

	$("#tabTabdhtmlgoodies_tabView1_" + defaultTab).trigger("click");

	$("#baseForm").validate({
		invalidHandler : function(form, validator) {
			$.each(validator.invalid, function(key, value) {
				alert(value);
				$("[name='" + key + "']").focus();
				return false;
			});
		},
		rules : {
			"serviceDTO.catalogNo" : {
				required : true
			},
			"serviceDTO.name" : {
				required : true
			},
			"serviceDTO.shortDesc" : {
				required : true
			}
		},
		messages : {
			"serviceDTO.catalogNo" : {
				required : "Please enter the Catalog No"
			},
			"serviceDTO.name" : {
				required : "Please enter the Name"
			},
			"serviceDTO.shortDesc" : {
				required : "Please enter the Description"
			}
		},
		errorPlacement : function(error, element) {
		}
	});
	$("#priceForm").validate({
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
			"serviceDTO.vtRatio" : {
				required : true,
				number : true
			},
			"serviceDTO.btRatio" : {
				required : true,
				number : true
			}
		},
		messages : {
			"serviceDTO.vtRatio" : {
				required : "Please enter the vtRatio",
				number : "This 'vtRatio' must be a digit!"
			},
			"serviceDTO.btRatio" : {
				required : "Please enter the btRatio",
				number : "This 'btRatio' must be a digit!"
			}
		},
		errorPlacement : function(error, element) {
		}
	});

	$("#generalForm").validate({
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
			"serviceDTO.qtyUom" : {
				required : true
			},
			"serviceDTO.storageCondition.temperature" : {
				required : true,
				number : true
			},
			"serviceDTO.storageCondition.humidity" : {
				required : true,
				number : true
			},
			"serviceDTO.shipCondition.temperature" : {
				required : true,
				number : true
			},
			"serviceDTO.shipCondition.domShipWeight" : {
				required : true,
				number : true
			},
			"serviceDTO.shipCondition.intlShipWeight" : {
				required : true,
				number : true
			}, 
			"serviceDTO.length" : {
				number : true
			},
			"serviceDTO.width" : {
				number : true
			},
			"serviceDTO.height" : {
				number : true
			},
			"serviceDTO.size" : {
				number : true
			},
			"serviceDTO.altSize" : {
				number : true
			},
			"serviceDTO.shipCondition.specShipCharge" : {
				number : true
			}
		},
		messages : {
			"serviceDTO.qtyUom" : {
				required : "Please enter the Quantity Uom"
			},
			"serviceDTO.storageCondition.temperature" : {
				required : "Please enter the Storage Temperature",
				number : "This 'Temperature' must be a digit!"
			},
			"serviceDTO.storageCondition.humidity" : {
				required : "Please enter the Storage Humidity",
				number : "This 'Humidity' must be a digit!"
			},
			"serviceDTO.shipCondition.temperature" : {
				required : "Please enter the Shipping Temperature",
				number : "This 'Temperature' must be a digit!"
			},
			"serviceDTO.shipCondition.domShipWeight" : {
				required : "Please enter the Shipping Domestic Ship Weight",
				number : "This 'Domestic Ship Weight' must be a digit!"
			},
			"serviceDTO.shipCondition.intlShipWeight" : {
				required : "Please enter the Shipping  Int'l Ship Weight",
				number : "This 'Int'l Ship Weight' must be a digit!"
			},
			/*
			 * "serviceDTO.federalTaxCls": { required: "Please enter the
			 * National Tax Status" },
			 */
			"serviceDTO.length" : {
				number : "This 'length' must be a digit!"
			},
			"serviceDTO.width" : {
				number : "This 'width' must be a digit!"
			},
			"serviceDTO.height" : {
				number : "This 'height' must be a digit!"
			},
			"serviceDTO.size" : {
				number : "This 'size' must be a digit!"
			},
			"serviceDTO.altSize" : {
				number : "This 'altSize' must be a digit!"
			},
			"serviceDTO.shipCondition.specShipCharge" : {
				number : "This 'Special Shipping Charge' must be a digit!"
			}
		},
		errorPlacement : function(error, element) {
		}
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
									url : "serv/serv!serviceApprovedSaveSession.action",
									type : "get",
									data : "approved=" + approved
											+ "&approvedReason="
											+ approvedReason + "&approvedType="
											+ approvedType
											+ "&sessionServiceId="
											+ sessionServiceId,
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
									url : "serv/serv!serviceApprovedSaveSession.action",
									type : "get",
									data : "approved=" + approved
											+ "&approvedReason="
											+ approvedReason + "&approvedType="
											+ approvedType
											+ "&sessionServiceId="
											+ sessionServiceId,
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
	var sessionServiceId = $("#sessionServiceId").val();
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
						var htmlStr = '<iframe name="crossDialog" id="crossDialog" src="serv/serv!showCrossCreateFormAct.action?relationId='
								+ crossSelect
								+ '&id='
								+ psId
								+ '&type=Service&sessionServiceId='
								+ sessionServiceId
								+ '" height="400" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
						$('#generalCrossDialog').html(htmlStr);
					});
	$('#generalCrossDialog').dialog('open');
}

function modifyNameClick() {
	if ($("[name='nameAppr']").val() == "true") {
		alert("The service name have been modified.");
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
		alert("The service status have been modified.");
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
						var html = '<iframe name="catalogNoListIframe" id="catalogNoListIframe" src="${ctx}/product/product!searchCatalogNoRules.action?type=SERVICE" width="100%" height="200" frameborder="0" scrolling="no"></iframe>';
						$('#generateCatalogNoDialog').html(html);
					});
	$('#generateCatalogNoDialog').dialog('open');
}

// misc iframe checkbox control end
function pdtServSaveAll(type) {
	var index = activeTabIndex['dhtmlgoodies_tabView1'];
	var tmpFlag = false;
	if (index == 6) {
		tmpFlag = window.frames["priceIframe"].changePrice();
	}
	if ($('#baseForm').valid() === false) {
		return false;
	}
	if ($('#generalForm').valid() === false) {
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
			$("#inventroyIframe").contents().find("body")).attr("checked") == true ? 'Y'
			: 'N';
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

	var formStr = '';
	formStr += $('#baseForm').serialize();
	if ($("#priceIframe").attr("src")) {
		var objPrice = document.getElementById("priceIframe").contentWindow;
		if (objPrice.$('#priceForm').valid() === false) {
			return false;
		}
		formStr += "&" + objPrice.$('#priceForm').serialize();
	} else {
		var btRatio = $("#btRatio").val();
		var vtRatio = $("#vtRatio").val();
	//	alert(vtRatio);		
	//	alert(btRatio);
		if (vtRatio != null || vtRatio != "" || btRatio != null
				|| btRatio != "") {
			var all = parseFloat(btRatio) + parseFloat(vtRatio);
			if (parseFloat(btRatio) + parseFloat(vtRatio) != 1) {
				alert("Please enter the right digit for VT Ration and  BT Ration!  ");
				return false;
			}
		}
		if (vtRatio == null || vtRatio == "") {
			alert("Please enter VT Ration for this service!");
			return false;
		} else {
			formStr += "&serviceDTO.vtRatio=" + vtRatio;
		}
		if (btRatio == null || btRatio == "") {
			alert("Please enter BT Ration for this service!");
			return false;
		} else {
			formStr += "&serviceDTO.btRatio=" + btRatio;
		}
	} 
	formStr += "&" + $('#generalForm').serialize(); 
	if ($("#miscIframe").attr("src")) {
		var objMisc = document.getElementById("miscIframe").contentWindow;
		if (objMisc.$('#miscForm').valid() === false) {
			return false;
		}
		formStr += "&" + objMisc.$('#miscForm').serialize();
	} else {
		formStr += "&serviceDTO.sellingPriceCmsn="
				+ $("#sellingPriceCmsn").val();
		formStr += "&serviceDTO.grossProfitCmsn=" + $("#grossProfitCmsn").val();
		var unitRateCmsn = $("#unitRateCmsn").val();
		if (isNaN(unitRateCmsn) || unitRateCmsn == null || unitRateCmsn == "") {
			unitRateCmsn = 0;
		}
		formStr += "&serviceDTO.unitRateCmsn=" + unitRateCmsn;
		formStr += "&serviceDTO.returnPoints=" + $("#returnPoints").val();
		formStr += "&serviceDTO.priceByPoints=" + $("#priceByPoints").val();
		formStr += "&serviceDTO.noticeSendType=" + $("#noticeSendType").val();
		formStr += "&serviceDTO.noticeGenerateTime="
				+ $("#noticeGenerateTime").val();
		formStr += "&serviceDTO.customerInfo=" + $("#customerInfo").val();
		formStr += "&serviceDTO.url=" + $("#url").val();
	}
	if ($("#inventroyIframe").attr("src")) {
		formStr += "&serviceDTO.prefWarehouse=" + prefWarehouse;
		formStr += "&serviceDTO.altWarehouseFlag=" + altWarehouseFlag;
		formStr += "&serviceDTO.prefStorage=" + prefStorage;
	}
	if ($("#supplierIframe").attr("src")) {
		formStr += "&serviceDTO.leadTime=" + leadTime;
	} else {
		leadTime = $("#leadTime").val();
		formStr += "&serviceDTO.leadTime=" + leadTime;
	}
	// Intermediate -->
	if ($("#breakdownList_iframe").attr('src')) {
		var trObj = $("#breakdownList_iframe").contents().find(
				"#breakdownListTable").find("tr"); 
		var imdStrs = "";
		trObj.each(function() {
			var seqStr = $(this).find("span[id^='seqNo_']").text();
			imdStrs += seqStr + "<->";

			var idStr = $(this).find("[name='bdid']").attr("value");
			imdStrs += idStr + "<->";

			var catalogNoStr = $(this).find("span[id^='catalogNo_']").text();
			imdStrs += catalogNoStr + "<->";

			var itemStr = $(this).find("span[id^='item_']").text();
			imdStrs += itemStr + "<->";

			var leadTimeStr = $(this).find("span[id^='leadTime_']").text();
			imdStrs += leadTimeStr + "<->";

			var symbolStr = $(this).find("span[id^='symbol_']").text();
			imdStrs += symbolStr + "<->";

			var intmdKeyword = $(this).find("[id^='intmdKeyword_']").val();
			imdStrs += intmdKeyword + "<->";
 
			var text_quantity = $(this).find("[name='text_quantity']");
			quantityStr = $(this).find("span[id^='quantity_']").text();
			if (!quantityStr) {
				quantityStr = text_quantity.val();
			}
			imdStrs += quantityStr + "<->";
			requiredFlagStr = $(this).find("[name='requiredFlag']").attr(
					"checked") ? "Y" : "N";
			imdStrs += requiredFlagStr + "<=>";
		});
		imdStrs = imdStrs.substring(0, imdStrs.length - 3);
		formStr += "&imdStr=" + imdStrs;
	}

	// Intermediate end
	// composite -->
	/*
	 * if($("#compositeList_iframe").attr('src')){ var objComposite =
	 * document.getElementById("compositeList_iframe").contentWindow;
	 * 
	 * trObj = objComposite.$("#compositeListTable").find("tr");
	 * 
	 * var cmpsStrs = ""; trObj.each(function(){ var seqStr =
	 * $(this).find("span[id^='seqNo_']").html(); cmpsStrs += seqStr + "<->";
	 * 
	 * var idStr = $(this).find("[name='cmpsId']").attr("value"); cmpsStrs +=
	 * idStr + "<->";
	 * 
	 * var catalogNoStr = $(this).find("span[id^='catalogNo_']").html();
	 * cmpsStrs += catalogNoStr + "<->";
	 * 
	 * var itemStr = $(this).find("span[id^='item_']").html(); cmpsStrs +=
	 * itemStr + "<->";
	 * 
	 * var leadTimeStr = $(this).find("span[id^='leadTime_']").html(); cmpsStrs +=
	 * leadTimeStr + "<->";
	 * 
	 * var symbolStr = $(this).find("span[id^='symbol_']").html(); cmpsStrs +=
	 * symbolStr + "<->";
	 * 
	 * var priceStr = $(this).find("span[id^='price_']").html(); cmpsStrs +=
	 * priceStr + "<->";
	 * 
	 * var text_quantity = $(this).find("[name='text_quantity']"); var
	 * quantityStr = $(this).find("span[id^='quantity_']").text();
	 * if(!quantityStr){ quantityStr = text_quantity.val(); } cmpsStrs +=
	 * quantityStr + "<=>"; }); cmpsStrs
	 * =cmpsStrs.substring(0,cmpsStrs.length-3); formStr += "&cmpsStr=" +
	 * cmpsStrs; }
	 */
	// composite end
 
	defaultTab = activeTabIndex['dhtmlgoodies_tabView1'];
	var type2 = pdtServType.toLowerCase();
	$
			.ajax({
				type : "POST",
				url : "serv/serv!save.action",
				data : formStr,
				dataType : 'json',
				success : function(data, textStatus) {
					if (hasException(data)) {
						$('#SaveAllTrigger').attr("disabled", false);
					} else {
						alert("The " + pdtServType + " is saved successfully!");
						isSaved = true;
						location.href = "serv/serv!input.action?id=" + data.id
								+ "&defaultTab=" + defaultTab;
					}
				},
				error : function(xhr, textStatus) {
					alert("System error! Please contact system administrator for help.");
					if (textStatus == 'timeout') {
						alert("Timeout!");
					}

					if (textStatus == 'parsererror') {
						tmp = xhr.responseText.split('{', 2);
						alert(tmp[0]); 
					}
				}
			});
}
