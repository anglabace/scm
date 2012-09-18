<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Service price</title>
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript"
	src="${global_js_url}tab-view.js"></script>
<link href="${global_css_url}tab-view.css" rel="stylesheet"
	type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}TabbedPanels.js"></script>
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
	type="text/css" />

<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script
	src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js"
	type="text/javascript"></script>
<link type="text/css"
	href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script src="${global_js_url}jquery/ui/ui.core.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1"
	type="text/javascript"></script>
<script type="text/javascript" language="javascript">
	$(document)
			.ready(
					function() {
						$("#priceChangeHistDialogTrigger")
								.click(
										function() {
											var returnType = $("#returnType")
													.val();
											var url = "${ctx}/serv/service_pricing!getPriceChangeHist2seviceIdandbreakdown.action?returnType="
													+ returnType
													+ "&sessionServiceId=${sessionServiceId}";
											var htmlStr = '<iframe src="' + url + '" height="180" width="560" scrolling="no" style="border:0px" frameborder="0"></iframe>';
											parent.$("#priceChangeHistDialogd")
													.dialog({
														autoOpen : false,
														height : 300,
														width : 600,
														modal : true,
														bgiframe : true,
														buttons : {

														}
													});
											parent
													.$(
															'#priceChangeHistDialogd')
													.dialog(
															"option",
															"open",
															function() {
																parent
																		.$(
																				'#priceChangeHistDialogd')
																		.html(
																				htmlStr);
															});
											parent.$('#priceChangeHistDialogd')
													.dialog('open');
										});

						$("#priceChangeHistDialog")
								.click(
										function() {

											var url = "${ctx}/serv/service_pricing!getPriceChangeHist2substeps.action?sessionServiceId=${sessionServiceId}";
											var htmlStr = '<iframe src="' + url + '" height="180" width="560" scrolling="no" style="border:0px" frameborder="0"></iframe>';
											parent.$("#priceChangeHistDialogd")
													.dialog({
														autoOpen : false,
														height : 300,
														width : 600,
														modal : true,
														bgiframe : true,
														buttons : {

														}
													});
											parent
													.$(
															'#priceChangeHistDialogd')
													.dialog(
															"option",
															"open",
															function() {
																parent
																		.$(
																				'#priceChangeHistDialogd')
																		.html(
																				htmlStr);
															});
											parent.$('#priceChangeHistDialogd')
													.dialog('open');
										});
					});

	function radioChange(openId, closeId) {
	}

	function servicePriceCatalogChange() {
		var tmpFlag = false
		tmpFlag = changePrice();
		if (tmpFlag == false) {
			return;
		}

		var sessionServiceId = $("#sessionServiceId").val();
		var catalogId = $("#catalogId option:selected").val();
		var catalogNo = $("#catalogNo option:selected").val();
		var returnType = $("#returnType").val();
		var serviceIdDetail = $("#serviceId option:selected").val();
		$
				.ajax({
					type : "POST",
					url : "serv/service_pricing!searchServicePriceByCatalogId.action?serviceIdDetail="
							+ serviceIdDetail
							+ "&returnType="
							+ returnType
							+ "&catalogId="
							+ catalogId
							+ "&catalogNo="
							+ catalogNo
							+ "&sessionServiceId="
							+ sessionServiceId,
					dataType : "json",
					success : function(msg) {
						if (msg.message == "success") {
							$("#curency").val(msg.currencyCode);
							$("#catalogView")
									.attr(
											"onClick",
											"parent.window.location.href='${ctx }/product/catalog!input.action?id="
													+ msg.id
													+ "&callBackName=catalogCreationForm&operation_method=edit'");
							$("#serviceCategoryId").empty();
							$("#serviceCCategoryId").empty();
							$("#serviceBCategoryId").empty();
							var html = "";
							var selected = "";
							$("[name='priceAppr']").val(msg.priceAppr);
							for ( var i = 0; i < msg.serviceCategoryOfPriceList.length; i++) {
								var category = msg.serviceCategoryOfPriceList[i];
								if (msg.servicePrice != null) {
									if (msg.servicePrice.categoryId == category.categoryId) {
										selected = "selected=selected";
									} else {
										selected = "";
									}
								}
								html += '<option value="' + category.categoryId + '"' + selected + '>'
										+ category.categoryNo + '</option>';
							}
							$("#serviceCategoryId").append(html);
							html = "";
							for ( var i = 0; i < msg.serviceCCategoryOfPriceList.length; i++) {
								var category = msg.serviceCCategoryOfPriceList[i];
								if (msg.ccategoryId == category.categoryId) {
									selected = "selected=selected";
								} else {
									selected = "";
								}
								html += '<option value="' + category.categoryId + '"' + selected + '>'
										+ category.categoryNo + '</option>';
							}
							$("#serviceCCategoryId").append(html);
							html = "";
							for ( var i = 0; i < msg.serviceBCategoryOfPriceList.length; i++) {
								var category = msg.serviceBCategoryOfPriceList[i];
								if (msg.bcategoryId == category.categoryId) {
									selected = "selected=selected";
								} else {
									selected = "";
								}
								html += '<option value="' + category.categoryId + '"' + selected + '>'
										+ category.categoryNo + '</option>';
							}
							$("#serviceBCategoryId").append(html);
							if (msg.servicePrice == null) {
								$("#limitPrice").val('');
								$("#standardPrice").val('');
								$("#priceId").val('');
								$("#groupId option[value='']").attr("selected",
										true);
							} else {
								if (msg.servicePrice.limitPrice != null
										&& msg.servicePrice.limitPrice != ''
										&& msg.servicePrice.limitPrice != 'null') {
									$("#limitPrice").val(
											msg.servicePrice.limitPrice);
								} else {
									$("#limitPrice").val('');
								}
								if (msg.servicePrice.standardPrice != null
										&& msg.servicePrice.standardPrice != ''
										&& msg.servicePrice.standardPrice != 'null') {
									$("#standardPrice").attr("disabled", false);
									$("#groupId").attr("disabled", true);
									$(":radio[value='standardPrice']").attr(
											"checked", true);
									$("#standardPrice").val(
											msg.servicePrice.standardPrice);
								} else {
									$("#standardPrice").val('');
								}
								if (msg.servicePrice.priceRuleGroup != null
										&& msg.servicePrice.priceRuleGroup != ''
										&& msg.servicePrice.priceRuleGroup != 'null') {
									$("#standardPrice").attr("disabled", true);
									$("#groupId").attr("disabled", false);
									$(":radio[value='groupId']").attr(
											"checked", true);
									$(
											"#groupId option[value='"
													+ msg.servicePrice.priceRuleGroup
													+ "']").attr("selected",
											true);
								} else {
									$("#groupId option[value='']").attr(
											"selected", true);
								}
								$("#priceId").val(msg.servicePrice.priceId);
							}
						}
					},
					error : function(msg) {
						alert("System error! Please contact system administrator for help.");
					}
				});
	}

	function servicePriceChange() {
		var tmpFlag = false
		tmpFlag = changePrice();
		if (tmpFlag == false) {
			return;
		}
		var sessionServiceId = $("#sessionServiceId").val();
		var catalogId = $("#catalogId option:selected").val();
		var serviceCategoryId = $("#serviceCategoryId").val();
		var catalogNo = $("#catalogNo option:selected").val();
		var returnType = $("#returnType").val();
		var serviceIdDetail = $("#serviceId option:selected").val();

		$
				.ajax({
					type : "POST",
					url : "serv/service_pricing!searchServicePriceByCategoryIdServiceId.action?serviceCategoryId="
							+ serviceCategoryId
							+ "&serviceIdDetail="
							+ serviceIdDetail
							+ "&returnType="
							+ returnType
							+ "&catalogId="
							+ catalogId
							+ "&catalogNo="
							+ catalogNo
							+ "&sessionServiceId="
							+ sessionServiceId,
					dataType : "json",
					success : function(msg) {
						if (msg.message == "success") {
							$("#curency").val(msg.currencyCode);
							$("#catalogView")
									.attr(
											"onClick",
											"parent.window.location.href='${ctx }/product/catalog!input.action?id="
													+ msg.id
													+ "&callBackName=catalogCreationForm&operation_method=edit'");
							$("[name='priceAppr']").val(msg.priceAppr);
							if (msg.servicePrice == null) {
								$("#limitPrice").val('');
								$("#standardPrice").val('');
								$("#priceId").val('');
								$("#groupId option[value='']").attr("selected",
										true);
							} else {
								if (msg.servicePrice.limitPrice != null
										&& msg.servicePrice.limitPrice != ''
										&& msg.servicePrice.limitPrice != 'null') {
									$("#limitPrice").val(
											msg.servicePrice.limitPrice);
								} else {
									$("#limitPrice").val('');
								}
								if (msg.servicePrice.standardPrice != null
										&& msg.servicePrice.standardPrice != ''
										&& msg.servicePrice.standardPrice != 'null') {
									$("#standardPrice").attr("disabled", false);
									$("#groupId").attr("disabled", true);
									$(":radio[value='standardPrice']").attr(
											"checked", true);
									$("#standardPrice").val(
											msg.servicePrice.standardPrice);
								} else {
									$("#standardPrice").val('');
								}
								if (msg.group != null && msg.group != ''
										&& msg.group != 'null') {
									$("#groupId").empty();
									var html = "<option value=''></option>";
									for ( var i = 0; i < msg.group.length; i++) {
										var group = msg.group[i];
										html += '<option value=' + group.groupId + '>'
												+ group.groupName + '</option>';
									}
									$("#groupId").append(html);
								}
								if (msg.servicePrice.priceRuleGroup != null
										&& msg.servicePrice.priceRuleGroup != ''
										&& msg.servicePrice.priceRuleGroup != 'null') {
									$("#standardPrice").attr("disabled", true);
									$("#groupId").attr("disabled", false);
									$(":radio[value='groupId']").attr(
											"checked", true);
									$(
											"#groupId option[value='"
													+ msg.servicePrice.priceRuleGroup
													+ "']").attr("selected",
											true);
								} else {
									$("#groupId option[value='']").attr(
											"selected", true);
								}
								$("#priceId").val(msg.servicePrice.priceId);
							}
						}
					},
					error : function(msg) {
						alert("System error! Please contact system administrator for help.");
					}
				});
	}

	function subStepPriceCatalogIdChange() {
		var sessionServiceId = $("#sessionServiceId").val();
		var catalogId = $("#catalogId option:selected").val();
		var stepId = $("#stepId option:selected").val();
		$
				.ajax({
					type : "POST",
					url : "serv/service_pricing!searchSubStepPrice.action?sessionServiceId="
							+ sessionServiceId
							+ "&catalogId="
							+ catalogId
							+ "&stepId=" + stepId,
					dataType : "json",
					success : function(msg) {
						if (msg.message == "success") {
							$("#curency").val(msg.currencyCode);
							$("#catalogView")
									.attr(
											"onClick",
											"parent.window.location.href='${ctx }/product/catalog!input.action?id="
													+ msg.id
													+ "&callBackName=catalogCreationForm&operation_method=edit'");
							$("[name='priceAppr']").val(msg.priceAppr);
							if (msg.subStepPrice == null) {
								$("#limitPrice").val('');
								$("#standardPrice").val('');
								$("#priceId").val('');
								$("#groupId option[value='']").attr("selected",
										true);
							} else {
								if (msg.subStepPrice.limitPrice != null
										&& msg.subStepPrice.limitPrice != ''
										&& msg.subStepPrice.limitPrice != 'null') {
									$("#limitPrice").val(
											msg.subStepPrice.limitPrice);
								} else {
									$("#limitPrice").val('');
								}
								if (msg.subStepPrice.retailPrice != null
										&& msg.subStepPrice.retailPrice != ''
										&& msg.subStepPrice.retailPrice != 'null') {
									$("#standardPrice").attr("disabled", false);
									$("#groupId").attr("disabled", true);
									$(":radio[value='standardPrice']").attr(
											"checked", true);
									$("#standardPrice").val(
											msg.subStepPrice.retailPrice);
								} else {
									$("#standardPrice").val('');
								}
								if (msg.subStepPrice.priceRuleGroup != null
										&& msg.subStepPrice.priceRuleGroup != ''
										&& msg.subStepPrice.priceRuleGroup != 'null') {
									$("#standardPrice").attr("disabled", true);
									$("#groupId").attr("disabled", false);
									$(":radio[value='groupId']").attr(
											"checked", true);
									$(
											"#groupId option[value='"
													+ msg.subStepPrice.priceRuleGroup
													+ "']").attr("selected",
											true);
								} else {
									$("#groupId option[value='']").attr(
											"selected", true);
								}
								$("#priceId").val(msg.subStepPrice.priceId);
							}
						}
					},
					error : function(msg) {

					}
				});
	}
	function subStepPriceChange() {
		var tmpFlag = false
		tmpFlag = changePrice();
		if (tmpFlag == false) {
			return;
		}
		var sessionServiceId = $("#sessionServiceId").val();
		var catalogId = $("#catalogId option:selected").val();
		var stepId = $("#stepId option:selected").val();
		$
				.ajax({
					type : "POST",
					url : "serv/service_pricing!searchSubStepPrice.action?sessionServiceId="
							+ sessionServiceId
							+ "&catalogId="
							+ catalogId
							+ "&stepId=" + stepId,
					dataType : "json",
					success : function(msg) {
						if (msg.message == "success") {
							$("#curency").val(msg.currencyCode);
							$("#catalogView")
									.attr(
											"onClick",
											"parent.window.location.href='${ctx }/product/catalog!input.action?id="
													+ msg.id
													+ "&callBackName=catalogCreationForm&operation_method=edit'");
							$("[name='priceAppr']").val(msg.priceAppr);
							if (msg.subStepPrice == null) {
								$("#limitPrice").val('');
								$("#standardPrice").val('');
								$("#priceId").val('');
								$("#groupId option[value='']").attr("selected",
										true);
							} else {
								if (msg.subStepPrice.limitPrice != null
										&& msg.subStepPrice.limitPrice != ''
										&& msg.subStepPrice.limitPrice != 'null') {
									$("#limitPrice").val(
											msg.subStepPrice.limitPrice);
								} else {
									$("#limitPrice").val('');
								}

								if (msg.subStepPrice.retailPrice != null
										&& msg.subStepPrice.retailPrice != ''
										&& msg.subStepPrice.retailPrice != 'null') {
									$("#standardPrice").attr("disabled", false);
									$("#groupId").attr("disabled", true);
									$(":radio[value='standardPrice']").attr(
											"checked", true);
									$("#standardPrice").val(
											msg.subStepPrice.retailPrice);
								} else {
									$("#standardPrice").val('');
								}
								if (msg.subStepPrice.priceRuleGroup != null
										&& msg.subStepPrice.priceRuleGroup != ''
										&& msg.subStepPrice.priceRuleGroup != 'null') {
									$("#standardPrice").attr("disabled", true);
									$("#groupId").attr("disabled", false);
									$(":radio[value='groupId']").attr(
											"checked", true);
									$(
											"#groupId option[value='"
													+ msg.subStepPrice.priceRuleGroup
													+ "']").attr("selected",
											true);
								} else {
									$("#groupId option[value='']").attr(
											"selected", true);
								}
								$("#priceId").val(msg.subStepPrice.priceId);
							}
						}
					},
					error : function(msg) {

					}
				});
	}

	function changePrice() {
		var returnType = $("#returnType").val();
		if (returnType == "breakDown" || returnType == "serviceId") {
			servicePriceSaveToSession();
		} else {
			serviceSubStepPriceSaveToSession();
		}
		return true;
	}

	function servicePriceSaveToSession() {
		var sessionServiceId = $("#sessionServiceId").val();
		var catalogId = $("#catalogIdInput").val();
		$("#catalogIdInput").val($("#catalogId option:selected").val());
		if (catalogId == '') {
			return false;
		}
		var category = $("#serviceCategoryId option:selected").val();
		if (category == '' || category == null) {
			return false;
		}
		var catalogNo = $("#catalogNoInput").val();
		$("#catalogNoInput").val($("#catalogNo option:selected").val());
		var returnType = $("#returnType").val();
		var serviceIdDetail = $("#serviceIdInput").val();
		$("#serviceIdInput").val($("#serviceId option:selected").val());
		if (catalogNo == '' && serviceIdDetail == '') {
			return false;
		}
		var url = "serv/service_pricing!saveServicePriceToSession.action?sessionServiceId="
				+ sessionServiceId
				+ "&servicePrice.catalogId="
				+ catalogId
				+ "&catalogNo="
				+ catalogNo
				+ "&returnType="
				+ returnType
				+ "&serviceIdDetail=" + serviceIdDetail;
		url += "&servicePrice.categoryId=" + category
		var limitPrice = $("#limitPrice").val();
		limitPrice = $.trim(limitPrice);
		if (limitPrice == '') {
			return false;
		}
		if (isNaN(limitPrice)) {
			return false;
		}
		url += "&servicePrice.limitPrice=" + limitPrice;
		var checkBox = $(":radio:checked").val();
		if (checkBox == "groupId") {
			var groupId = $("#groupId option:selected").val();
			url += "&servicePrice.priceRuleGroup=" + groupId;
		} else if (checkBox == "standardPrice") {
			var standardPrice = $("#standardPrice").val();
			url += "&servicePrice.standardPrice=" + standardPrice;
		} else {
			return false;
		}
		$
				.ajax({
					type : "POST",
					url : url,
					dataType : "json",
					success : function(msg) {
						if (msg.message == "success") {
							return true;
						}
					},
					error : function(msg) {
						alert("System error! Please contact system administrator for help.");
						return false;
					}
				});
		return true;
	}

	function serviceSubStepPriceSaveToSession() {
		var sessionServiceId = $("#sessionServiceId").val();
		var catalogId = $("#catalogIdInput").val();
		$("#catalogIdInput").val($("#catalogId option:selected").val());
		if (catalogId == '') {
			return false;
		}
		var stepId = $("#stepIdInput").val();
		$("#stepIdInput").val($("#stepId option:selected").val());
		if (stepId == '') {
			return false;
		}
		var url = "serv/service_pricing!saveServiceSubStepPriceToSession.action?sessionServiceId="
				+ sessionServiceId
				+ "&subStepPrice.catalogId="
				+ catalogId
				+ "&subStepPrice.stepId=" + stepId;
		var limitPrice = $("#limitPrice").val();
		limitPrice = $.trim(limitPrice);
		if (limitPrice == '') {
			return 'false';
		}
		if (isNaN(limitPrice)) {
			return false;
		}
		url += "&subStepPrice.limitPrice=" + limitPrice;
		var checkBox = $(":radio:checked").val();

		if (checkBox == "groupId") {
			var groupId = $("#groupId option:selected").val();
			url += "&subStepPrice.priceRuleGroup=" + groupId;
		} else if (checkBox == "standardPrice") {
			var standardPrice = $("#standardPrice").val();
			url += "&subStepPrice.retailPrice=" + standardPrice;
		} else {
			return false;
		}
		$
				.ajax({
					type : "POST",
					url : url,
					dataType : "json",
					success : function(msg) {
						if (msg.message == "success") {
							return true;
						}
					},
					error : function(msg) {
						alert("System error! Please contact system administrator for help.");
						return false;
					}
				});
		return true;
	}

	function modifyPriceClick() {
	 

		if ($("[name='priceAppr']").val() == "true") {
			alert("The price have been modified.");
			return;
		}
		if ($("[name='priceGroupAppr']").val() == "true") {
			alert("The price have been modified.");
			return;
		}
		$("#modifyPriceDialog").dialog({
			autoOpen : false,
			height : 300,
			width : 600,
			modal : true,
			bgiframe : true,
			buttons : {}
		});
		$('#modifyPriceDialog').dialog("option", "open", function() {
		});
		$('#modifyPriceDialog').dialog('open');
		$('#priceApproved').val($('#standardPrice').val());
		var oldPriceApproved = $('#standardPrice').val();
		if (!oldPriceApproved) {
			oldPriceApproved = $('#groupId option:selected').val();
		}
		$('#oldPriceApproved').val(oldPriceApproved);
	}

	function savePriceApprovedTrigger() {
	 
		var priceId = $('#priceId').val();
		
		var priceApproved = $("#priceApproved").val();
	 
		priceApproved = $.trim(priceApproved);
		var groupApproved = $("#priceGroupApproved").val();
		if (priceId == null || priceId == '' || isNaN(priceId)) {
			$("#modifyPriceDialog").dialog("close");
			return;
		}
		var approvedPriceReason = $("#approvedPriceReason").val();
		approvedPriceReason = $.trim(approvedPriceReason);
		var approvedPriceGroupReason = $("#approvedPriceGroupReason").val();
		approvedPriceGroupReason = $.trim(approvedPriceGroupReason);
		var approvedPriceType = $("#approvedPriceType").val();
		var oldPriceApproved = $("#oldPriceApproved").val();
		var oldPriceGroupApproved = $("#oldPriceGroupApproved").val();
		if (oldPriceApproved == priceApproved) {
			if (oldPriceGroupApproved == groupApproved) {
				alert("Please modify the price to continue your operation.");
				return;
			}
		}

		if (!oldPriceApproved) {
			oldPriceApproved = oldPriceGroupApproved;
		}
		if (oldPriceApproved == null || oldPriceApproved == '') {
			oldPriceApproved = '0';
		}
		if (!priceApproved && !groupApproved) {
			alert("Please enter the price.");
			return;
		}
		if (!approvedPriceReason && !approvedPriceGroupReason) {
			alert("Please enter the reason.");
			return;
		}
		if (!approvedPriceReason) {
			approvedPriceReason = approvedPriceGroupReason;
		}
		if (isNaN(priceApproved)) {
			alert("Please enter the correct price.");
			return;
		}
		var returnType = $("#returnType").val();
		var approvedPriceType = "";
		if (returnType == "breakDown" || returnType == "serviceId") {
			approvedPriceType = "price";
			priceId = "priceId=" + priceId;
		} else {
			approvedPriceType = "subPrice";
			priceId = "subPriceId=" + priceId;
		}
		var sessionServiceId = $('#sessionServiceId').val();
		$
				.ajax({
					url : "serv/service_pricing!priceApprovedSaveSession.action",
					type : "get",
					data : "oldPriceApproved=" + oldPriceApproved + "&"
							+ priceId + "&groupApproved=" + groupApproved
							+ "&approved=" + priceApproved + "&approvedReason="
							+ approvedPriceReason + "&approvedType="
							+ approvedPriceType + "&sessionServiceId="
							+ sessionServiceId,
					dataType : "json",
					success : function(data) {
						if (data.message == "success") {
							alert("The modification is ready to be submitted for the evaluation and will be applied only after it’s approved.");
						} else {
							alert("System error! Please contact system administrator for help.");

						}

					},
					error : function(data) {
						alert("System error! Please contact system administrator for help.");
					},
					async : false
				});
		if (priceApproved) {
			$("#modifyPriceDialog").dialog("close");
		}
		if (groupApproved) {
			$("#modifyPriceGroupDialog").dialog("close");
		}
	}

	function modifyRuleGroupPriceClick() {
		if ($("[name='priceAppr']").val() == "true") {
			alert("The price have been modified.");
			return;
		}
		if ($("[name='priceGroupAppr']").val() == "true") {
			alert("The price have been modified.");
			return;
		}
		$("#modifyPriceGroupDialog").dialog({
			autoOpen : false,
			height : 300,
			width : 600,
			modal : true,
			bgiframe : true,
			buttons : {}
		});
		$('#modifyPriceGroupDialog').dialog("option", "open", function() {

		});
		$('#modifyPriceGroupDialog').dialog('open');
		$('#oldPriceGroupApproved').val($('#groupId option:selected').val());

	}

	function change_category(categoryLevel) {
		var catalogId = $("#catalogId option:selected").val();
		var categoryId;
		if (categoryLevel == 2) {
			categoryId = $("#serviceBCategoryId option:selected").val();
		}
		if (categoryLevel == 3) {
			categoryId = $("#serviceCCategoryId option:selected").val();
		}
		if (catalogId == null || catalogId == '') {
			alert("The catalog id is not null.");
			return false;
		}
		if (categoryId == null || categoryId == '') {
			alert("The category id is not null.");
			return false;
		}
		var reqUrl = "${ctx}/serv/service_pricing!getCategoryByCatalogAndCategory.action?catalogId="
				+ catalogId
				+ "&categoryId="
				+ categoryId
				+ "&categoryLevel="
				+ categoryLevel;

		$
				.ajax({
					type : "POST",
					dataType : "json",
					url : reqUrl,
					success : function(data) {
						var html = "";
						var htmlp = "";
						if (data.serviceCCategoryOfPriceList != null) {
							for ( var i = 0; i < data.serviceCCategoryOfPriceList.length; i++) {
								html += "<option value=" + data.serviceCCategoryOfPriceList[i].categoryId + ">"
										+ data.serviceCCategoryOfPriceList[i].categoryNo
										+ "</option>";
							}
						}
						if (data.serviceCategoryOfPriceList != null) {
							for ( var i = 0; i < data.serviceCategoryOfPriceList.length; i++) {
								htmlp += "<option value=" + data.serviceCategoryOfPriceList[i].categoryId + ">"
										+ data.serviceCategoryOfPriceList[i].categoryNo
										+ "</option>";
							}
						}
						if (categoryLevel == 2) {
							$("#serviceCCategoryId").html(html);
							categoryLevel++;
						}
						if (categoryLevel == 3) {
							$("#serviceCategoryId").html(htmlp);
						}
					},
					error : function(xhr, textStatus) {
						alert("Failed to access the  web server. Please contact system administrator for help.!");
						if (textStatus == 'timeout') {

						}

						if (textStatus == 'parsererror') {
							tmp = xhr.responseText.split('{', 2);
							alert(tmp[0]);
						}
					}
				});
	}
</script>
</head>

<body>
	<form id="priceForm">
		<table border="0" cellpadding="0" cellspacing="0"
			class="General_table">
			<tr>
				<th><span class="important">*</span>VT Ratio</th>
				<td><input type="text" size="22" class="NFText2" id="vtRatio"
					name="serviceDTO.vtRatio" value="${vtRatio}" /></td>
				<td>&nbsp;</td>
				<th><span class="important">*</span>BT Ratio</th>
				<td><input type="text" size="22" class="NFText2" id="btRatio"
					name="serviceDTO.btRatio" value="${btRatio}" /></td>
				<td>&nbsp;         <%--  <input name="d" type="text"		value="${returnType } "/>  --%></td>
			</tr>
			<s:if test="returnType == \"breakDown\"||returnType == \"serviceId\"">
				<tr>
					<th width="210">Price Catalog</th>
					<td width="139"><s:select list="catalog" name="catalogId"
							id="catalogId" listKey="catalogId" listValue="catalogName"
							onchange="servicePriceCatalogChange()"></s:select> <input
						type="hidden" name="catalogIdInput" id="catalogIdInput" value="" />
						<script type="text/javascript">
							$("#catalogIdInput").val(
									$("#catalogId option:selected").val());
						</script>
					</td>
					<td width="109"><input name="catalogView" id="catalogView"
						type="submit" class="style_botton" value="View"
						onclick="parent.window.location.href='${ctx }/product/catalog!input.action?id=${id}&callBackName=catalogCreationForm&operation_method=edit'" />
					</td>
					<th width="166">Currency</th>
					<td width="148"><input name="curency" id="curency"
						value="${currencyCode }" type="text" disabled="disabled" />
					</td>
					<td width="264">&nbsp;</td>
				</tr>
				<tr>
					<th>Business Unit</th>
					<td colspan="2"><s:select list="serviceBCategoryOfPriceList"
							id="serviceBCategoryId" listKey="categoryId"
							listValue="categoryNo" value="bcategoryId"
							onchange="change_category(2)"></s:select>
					</td>
					<th>&nbsp;</th>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<th>Category</th>
					<td colspan="2"><s:select list="serviceCCategoryOfPriceList"
							id="serviceCCategoryId" listKey="categoryId"
							listValue="categoryNo" value="ccategoryId"
							onchange="change_category(3)"></s:select>
					</td>
					<th>&nbsp;</th>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<th>Service Line</th>
					<td colspan="2"><s:select list="serviceCategoryOfPriceList"
							name="serviceCategoryId" id="serviceCategoryId"
							listKey="categoryId" listValue="categoryNo"
							value="servicePrice.categoryId"></s:select>
					</td>
					<th>&nbsp;</th>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<th>Services Detail</th>
					<td colspan="2"><s:if test="returnType==\"serviceId\"">
							<select name="serviceId" id="serviceId"
								onchange="servicePriceChange()">
								<option value="${serviceId}">${serviceType}</option>
							</select>
							<input type="hidden" name="serviceIdInput" id="serviceIdInput"
								value="" />
							<script type="text/javascript">
								$("#serviceIdInput").val(
										$("#serviceId option:selected").val());
							</script>
						</s:if> <s:else>
							<s:select list="breakDown" name="catalogNo" id="catalogNo"
								listKey="intmdCatalogNo" listValue="intmdCatalogNo"
								onchange="servicePriceChange()"></s:select>
							<input type="hidden" name="catalogNoInput" id="catalogNoInput"
								value="" />
							<script type="text/javascript">
								$("#catalogNoInput").val(
										$("#catalogNo option:selected").val());
							</script>
						</s:else>
					</td>
					<th>&nbsp;</th>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
          
					<s:if test="servicePrice.standardPrice!=null">
						<th><input name="radiobuttonA" type="radio"
							value="standardPrice" checked="checked"
							onclick="radioChange('standardPrice','groupId')"
							disabled="disabled" /> Standard Retail Price</th>
						<td colspan="2"><input name="standardPrice"
							id="standardPrice" type="text" class="NFText2"
							value="${servicePrice.standardPrice}" size="20"
							readonly="readonly" /> <input type="button" value="Modify"
							class="style_button" onclick="modifyPriceClick()" />
						</td>
					</s:if>
					<s:else>
						<th><input name="radiobuttonA" type="radio"
							value="standardPrice"
							onclick="radioChange('standardPrice','groupId')"
							disabled="disabled" /> Standard Retail Price</th>
						<td colspan="2"><input name="standardPrice"
							id="standardPrice" type="text" class="NFText2"
							value="${servicePrice.standardPrice}" size="20"
							disabled="disabled" readonly="readonly" /> <input type="button"
							value="Modify" class="style_button" onclick="modifyPriceClick()" />
						</td>
					</s:else>

					<th>Price Limit</th>
					<td><s:if test="servicePrice.limitPrice==null">
							<input name="limitPrice" id="limitPrice" type="text"
								class="NFText2" size="20" value="0" />
						</s:if> <s:else>
							<input name="limitPrice" id="limitPrice" type="text"
								class="NFText2" size="20" value="${servicePrice.limitPrice }" />
						</s:else>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>

					<s:if test="servicePrice.priceRuleGroup!=null">
						<th><input name="radiobuttonA" type="radio" value="groupId"
							checked="checked"
							onclick="radioChange('groupId','standardPrice')"
							disabled="disabled" /> Price rule group</th>
						<td colspan="2"><s:select list="group" name="groupId"
								id="groupId" listKey="groupId" listValue="groupName"
								value="servicePrice.priceRuleGroup" headerKey="" headerValue=""
								onchange="this.selectedIndex=1 " disabled="true"></s:select> <input
							type="button" value="Modify" class="style_button"
							onclick="modifyRuleGroupPriceClick()" />
						</td>
					</s:if>
					<s:else>
						<th><input name="radiobuttonA" type="radio" value="groupId"
							onclick="radioChange('groupId','standardPrice')"
							disabled="disabled" /> Price rule group</th>
						<td colspan="2"><s:select list="group" name="groupId"
								id="groupId" listKey="groupId" listValue="groupName"
								value="servicePrice.priceRuleGroup" headerKey="" headerValue=""
								onchange="this.selectedIndex=1 " disabled="true"></s:select> <input
							type="button" value="Modify" class="style_button"
							onclick="modifyRuleGroupPriceClick()" />
						</td>
					</s:else>

					<th>&nbsp;</th>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="6" align="center"><input type="button"
						value="Price Change History" id="priceChangeHistDialogTrigger"
						class="style_botton3" />
					</td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<th width="210">Price Catalog</th>
					<td width="139"><s:select list="catalog" name="catalogId"
							id="catalogId" listKey="catalogId" listValue="catalogName"
							value="subStepPrice.catalogId" onchange="subStepPriceChange()"></s:select>
						<input type="hidden" name="catalogIdInput" id="catalogIdInput"
						value="" /> <script type="text/javascript">
							$("#catalogIdInput").val(
									$("#catalogId option:selected").val());
						</script>
					</td>
					<td width="109"><input name="catalogView" id="catalogView"
						type="submit" class="style_botton" value="View"
						onclick="parent.window.location.href='${ctx }/product/catalog!input.action?id=${id}&callBackName=catalogCreationForm&operation_method=edit'" />
					</td>
					<th width="166">Currency</th>
					<td width="148"><input name="curency" id="curency"
						value="${currencyCode }" type="text" disabled="disabled" />
					</td>
					<td width="264">&nbsp;</td>
				</tr>
				<tr>
					<th>Services Detail</th>
					<td colspan="2"><s:select list="subSteps" name="stepId"
							id="stepId" listKey="stepId" listValue="name"
							onchange="subStepPriceChange()"></s:select> <input type="hidden"
						name="stepIdInput" id="stepIdInput" value="" /> <script
							type="text/javascript">
							$("#stepIdInput").val(
									$("#stepId option:selected").val());
						</script>
					</td>
					<th>Price Limit</th>
					<td><s:if test="subStepPrice.limitPrice==null">
							<input name="limitPrice" id="limitPrice" type="text"
								class="NFText2" size="20" value="0" />
						</s:if> <s:else>
							<input name="limitPrice" id="limitPrice" type="text"
								class="NFText2" size="20" value="${subStepPrice.limitPrice }" />
						</s:else>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>


					<th><input name="radiobuttonA" type="radio"
						value="standardPrice" checked="checked"
						onclick="radioChange('standardPrice','groupId')"
						disabled="disabled" /> Standard Retail Price</th>
					<td colspan="2"><input name="standardPrice" id="standardPrice"
						type="text" class="NFText2" value="${subStepPrice.retailPrice}"
						size="20" readonly="readonly" /> <input type="button"
						value="Modify" class="style_button" onclick="modifyPriceClick()" />
					</td>


					<th>&nbsp;</th>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td colspan="6" align="center"><input name="Submit7222"
						type="button" class="style_botton3" value="Price Change History"
						id="priceChangeHistDialog" /></td>
				</tr>
			</s:else>

			<input type="hidden" name="priceAppr" id="priceAppr"
				value="${priceAppr}" />
			<s:if test="servicePrice.priceId!=null">
				<input type="hidden" id="priceId" value="${servicePrice.priceId}" />
			</s:if>
			<s:elseif test="subStepPrice.priceId!=null">
				<input type="hidden" id="priceId" value="${subStepPrice.priceId}" />
			</s:elseif>
			<s:else>
				<input type="hidden" id="priceId" value="" />
			</s:else>
			<input type="hidden" name="sessionServiceId" id="sessionServiceId"
				value="${sessionServiceId}" />
			<input type="hidden" name="returnType" id="returnType"
				value="${returnType }" />

		</table>
		<div class="dhtmlgoodies_aTab">
			<table width="970" border="0" cellpadding="0" cellspacing="0"
				class="General_table">

				<tr>
					<td>
						<div align="center">
							<span class="blue_price">Special Price List </span>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<table width="940" border="0" cellpadding="0" cellspacing="0"
							class="list_table">

							<tr>
								<th width="46">
									<div align="left">
										<input name="checkbox13222" type="checkbox" /> <a
											href="javascript:void(0)"
											onclick="openiframe('delete_reason_price.html',620,300,200)"><img
											src="images/file_delete.gif" alt="Delete" width="16"
											height="16" border="0" /> </a>
									</div>
								</th>
								<th width="48">Seq No</th>
								<th width="70">Unit Price</th>
								<th width="70">Discount</th>
								<th width="70">RFM Rating</th>
								<th width="180">Source Key</th>
								<th width="56">Min Qty</th>
								<th width="100">Catalog ID</th>
								<th width="80">Start Date</th>
								<th width="80">End Date</th>
								<th>Order Total</th>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<table width="940" border="0" cellpadding="0" cellspacing="0"
				class="list_table">

			</table>
			<div id="modifyPriceDialog" title="Modify Price"
				style="display: none;">
				<table id="whole_table" width="492" border="0" cellpadding="0"
					cellspacing="0" class="General_table" style="margin-left: 40px;">
					<tr>
						<th width="150" align="left">Standard Retail Price</th>
						<td width="414"><input type="text" size='35' class="NFText"
							name="priceApproved" id="priceApproved" value="" /></td>
					</tr>
					<tr>
						<th height="24" colspan="2">
							<div align="left">Choose the reason to modify the price:</div>
						</th>
					</tr>
					<tr>
						<th colspan="2">
							<div align="left">
								<textarea name="approvedPriceReason" id="approvedPriceReason"
									cols="70" rows="2" class="content_textarea"></textarea>
							</div>
						</th>
					</tr>
					<tr>
						<th align="right" colspan="2">
							<div align="center" style="margin: 10px;">
								<div id="cat_name" style='display: block;'>
									<input type="hidden" name="approvedPriceType"
										id="approvedPriceType" value="ServiceApprovedPrice" /> <input
										type="hidden" name="oldPriceApproved" id="oldPriceApproved"
										value="" /> <input type="button" class="style_botton"
										value="Modify" id="savePriceApprovedTrigger"
										onclick="savePriceApprovedTrigger()" /> <input type="button"
										value="Cancel" class="style_botton"
										onclick="$('#modifyPriceDialog').dialog('close');" />
								</div>
							</div>
						</th>
					</tr>
				</table>
			</div>
			<div id="modifyPriceGroupDialog" title="Modify Price"
				style="display: none;">
				<table id="whole_table" width="492" border="0" cellpadding="0"
					cellspacing="0" class="General_table" style="margin-left: 40px;">
					<tr>
						<th width="150" align="left">Price rule group</th>
						<td width="414"><s:select list="group"
								name="priceGroupApproved" id="priceGroupApproved"
								listKey="groupId" listValue="groupName"
								value="servicePrice.priceRuleGroup" headerKey="" headerValue=""
								onchange="this.selectedIndex=1 " disabled=""></s:select></td>
					</tr>
					<tr>
						<th height="24" colspan="2">
							<div align="left">Choose the reason to modify the price
								rule group:</div>
						</th>
					</tr>
					<tr>
						<th colspan="2">
							<div align="left">
								<textarea name="approvedPriceGroupReason"
									id="approvedPriceGroupReason" cols="70" rows="2"
									class="content_textarea"></textarea>
							</div>
						</th>
					</tr>
					<tr>
						<th align="right" colspan="2">
							<div align="center" style="margin: 10px;">
								<div id="cat_name" style='display: block;'>
									<input type="hidden" name="approvedPriceGroupType"
										id="approvedPriceGroupType" value="ServiceApprovedPriceGroup" />
									<input type="hidden" name="oldPriceGroupApproved"
										id="oldPriceGroupApproved" value="" /> <input type="button"
										class="style_botton" value="Modify"
										id="savePriceGroupApprovedTrigger"
										onclick="savePriceApprovedTrigger()" /> <input type="button"
										value="Cancel" class="style_botton"
										onclick="$('#modifyPriceGroupDialog').dialog('close');" />
								</div>
							</div>
						</th>
					</tr>
				</table>
	</form>
	</div>
	</div>
</body>
</html>
