function addFormNameToSerializeData(formName, str) {
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
		}
		rtArr.push(formName + "." + tmpArr[i]);
	}
	return rtArr.join("&");
}

function donotCodeStr() {
	var tmpArr = [];
	$("[title='donotCode']").each(function(i, n) {
		if ($(n).attr("checked")) {
			tmpArr.push(1);
		} else {
			tmpArr.push(0);
		}
	});
	return tmpArr.join(";");
}

$(function() {
	// more phones set dialog and trigger
	$('#morePhoneDialog').dialog({
		autoOpen : false,
		height : 200,
		width : 620,
		modal : true,
		bgiframe : true,
		buttons : {
			"Confirm" : function() {
				if ($('#morePhoneDialogForm').valid() == false) {
					return false;
				}

				$(this).dialog('close');
			},
			"Cancel" : function() {
				if ($('#morePhoneDialogForm').valid() == false) {
					return false;
				}
				$(this).dialog('close');
			}
		}
	});

	$("#morePhoneDialogTrigger").click(function() {
		$('#morePhoneDialog').dialog('open');
	});
	// more emails set dialog and trigger
	$('#moreEmailDialog').dialog({
		autoOpen : false,
		height : 170,
		width : 620,
		modal : true,
		bgiframe : true,
		buttons : {
			"Confirm" : function() {
				if ($('#moreEmailDialogForm').valid() == false) {
					return false;
				}
				$(this).dialog('close');
			},
			"Cancel" : function() {
				if ($('#moreEmailDialogForm').valid() == false) {
					return false;
				}
				$(this).dialog('close');
			}
		}
	});

	$("#moreEmailDialogTrigger").click(function() {
		$('#moreEmailDialog').dialog('open');
	});

	// customer activities dialog and trigger
	$('#customerActvDialog')
			.dialog(
					{
						autoOpen : false,
						height : 550,
						width : 650,
						modal : true,
						bgiframe : true,
						buttons : {
							"Close" : function() {
								$(this).dialog('close');
							}
						},
						open : function() {
							var htmlStr = '<iframe src="customer/customer!viewActivitys.action?custNo='
									+ $("#custNo").val()
									+ '&sessCustNo='
									+ $("#sessCustNo").val()
									+ '"  height="430" width="620" scrolling="no" style="border:0px" frameborder="0"></iframe>';
							$('#customerActvDialog').html(htmlStr);
						}
					});

	$("#customerActvDialogTrigger").click(function() {
		$('#customerActvDialog').dialog('open');
	});

	// ****************************************************************************************************************************************
	// 信用卡管理begin
	// ****************************************************************************************************************************************
	$('#customerCreditDialog').dialog({
		autoOpen : false,
		height : 300,
		width : 450,
		modal : true,
		bgiframe : true,
		buttons : {},
		open : function() {
		}
	});

	$("#credit_card_add_btn")
			.click(
					function() {
						if ($("#custInt").val() == "") {
							alert("Please save the changes to continue your operation.");
							return;
						}
						// 清空数据
						var crTable = $("#creditCardEditTb");
						crTable.find("[name='ccType']").find("option:first")
								.attr("selected", true);
						crTable.find("[name='ccNo']").val("");
						crTable.find("[name='ccId']").val("");
						crTable.find("[name='ccHolder']").val("");
						crTable.find("[name='ccCvc']").val("");
						crTable.find("#ccExprDateSpan").hide();
						crTable.find("[name='ccExprDate']").show();
						crTable.find("[name='ccExprDate']").val("");
						$('#customerCreditDialog').dialog('option', "title",
								"New Credit Information");
						$('#customerCreditDialog').dialog('open');
					});

	$("#credit_card_edit_btn").click(
			function() {
				// 初始化数据
				var data = $("#cardList").val();
			//	alert(data);
				var card = [];
				var tmpArr = data.split(",");
				var tmpArr2 = [];
				for ( var i = 0; i < tmpArr.length; i++) {
					tmpArr2 = tmpArr[i].split(":");
					var name = tmpArr2[0];
					if (name == "ccExprDate") {
						if (tmpArr2[1]) {
							tmpArr2[1] = tmpArr2[1].substr(0, 10);
						}
					}
					//alert(tmpArr2[1]);
					card[name] = tmpArr2[1];
				}
				var crTable = $("#creditCardEditTb");
				crTable.find("[name='ccType']").find(
						"option[value='" + card["ccType"] + "']").attr(
						"selected", true);
				crTable.find("[name='ccNo']").val(card["ccNo"]);
				crTable.find("[name='ccId']").val(card["id"]);
				crTable.find("[name='ccHolder']").val(card["ccHolder"]);
				crTable.find("[name='ccCvc']").val(card["ccCvc"]);
				crTable.find("#ccExprDateSpan").show();
				crTable.find("#ccExprDateSpan").html(card["ccExprDate"]);
			//	alert(card["ccExprDate"]);
			//	crTable.find("[name='ccExprDate']").val(card["ccExprDate"]);
				 var date=card["ccExprDate"];
				 var strs =date.split("-");
				 var dat=strs[2];
				 var month=strs[1];
				 var year=strs[0];
			//	  alert(dat);
			///	 alert(month);
			//	 alert(year); 
				 crTable.find("[name='month']").val(month);
				 crTable.find("[name='year']").val(year);
			//	crTable.find("[name='ccExprDate']").hide();
				$('#customerCreditDialog').dialog('option', "title",
						"Edit Credit Information");
				$('#customerCreditDialog').dialog('open');
			});

	$("#credit_card_remove_btn")
			.click(
					function() {
						var cardData = $("#cardList").val();
						if (cardData == "") {
							alert("System error! Please contact system administrator for help.");
							return;
						}

						var tmpArr = cardData.split(",");
						var tmpArr2 = tmpArr[0].split(":");
						var ccId = tmpArr2[1];
						$
								.ajax({
									type : "post",
									url : "customer!removeCredit.action?creditCardId="
											+ ccId,
									dataType : "text",
									success : function(data) {
										if (data == "SUCCESS") {
											$("#cardList").find(
													"option:selected").remove();
											$("#cardList").trigger("change");
										} else {
											if (data)
												alert(data);
											else
												alert("System error! Please contact system administrator for help.");
										}
									},
									error : function() {
										alert("System error! Please contact system administrator for help.");
									},
									async : false
								});

					});

	$("#cardList").change(function() {
		var cardData = $(this).val();
		if (cardData == "") {
			$("#credit_card_edit_btn").hide();
			$("#credit_card_remove_btn").hide();
			$("#credit_card_add_btn").show();
		} else {
			$("#credit_card_add_btn").hide();
			$("#credit_card_edit_btn").show();
			$("#credit_card_remove_btn").show();
		}
	});

	$("#credtModifyBtn")
			.click(
					function() {
						var custNo = $("#custInt").val();
						if (!custNo) {
							return;
						}
						var crTable = $("#creditCardEditTb");
						var ccType = crTable.find("[name='ccType']").val();
						var ccNo = crTable.find("[name='ccNo']").val();
						var ccId = crTable.find("[name='ccId']").val();
						var ccHolder = crTable.find("[name='ccHolder']").val();
						var ccCvc = crTable.find("[name='ccCvc']").val();
						var year = document.getElementById("year").value;
						var month = document.getElementById("month").value; 
						var ccExprDate = year + "-" + month + "-" + "01"; 
						if (!ccNo) {
							alert("Please enter the Credit Card Number.");
							return;
						}
						if (!ccHolder) {
							alert("Please enter the Name.");
							return;
						}
						if (year == "" || month == "") {
							alert("Please enter the Expiration Date.");
							return;
						}
						var dataStr = "";
						dataStr += "&creditCard.cardType=" + ccType;
						dataStr += "&creditCard.cardNo=" + ccNo;
						dataStr += "&creditCard.id=" + ccId;
						dataStr += "&creditCard.cardHolder=" + ccHolder;
						dataStr += "&creditCard.cvc=" + ccCvc;
						dataStr += "&creditCard.exprDate=" + ccExprDate;
						dataStr += "&creditCard.custNo=" + custNo;
						$
								.ajax({
									type : "post",
									url : "customer!saveCredit.action",
									data : dataStr,
									dataType : "json",
									success : function(data) {
										var newCcId = data.ccId;
										var optionVal = 'id:' + newCcId
												+ ',ccType:' + ccType
												+ ',ccExprDate:' + ccExprDate
												+ ',ccNo:' + ccNo + ',ccCvc:'
												+ ccCvc + ',ccHolder:'
												+ ccHolder;
										// 新增
										if (!ccId) {
											$("#cardList").append(
													'<option value="'
															+ optionVal + '">'
															+ ccType + " "
															+ ccNo
															+ '</option>');
										} else {
											$("#cardList").find(
													"option:selected").val(
													optionVal);
											$("#cardList").find(
													"option:selected").html(
													ccType + " " + ccNo);
										}
									},
									error : function() {
										alert("System error! Please contact system administrator for help.");
									},
									async : false
								});
						$('#customerCreditDialog').dialog('close');
					});
	// 日期选择后转移焦点，否则再次点击日期不弹出div bug.
	$("#creditCardEditTb").find("[name='ccExprDate']").change(function() {
		$("#credtModifyBtn").focus();
	});
	// ****************************************************************************************************************************************
	// 信用卡管理end
	// ****************************************************************************************************************************************
	// view orginal source detail dialog and trigger
	$("#viewSourceDialogTrigger").click(function() {
		var sourceId = $("#sourceId").val();
		if (sourceId == "") {
			alert("Please select one source !");
			return false;
		} else {
			$('#viewSourceDialog').dialog('open');
		}
	});
	$('#viewSourceDialog')
			.dialog(
					{
						autoOpen : false,
						height : 600,
						width : 790,
						modal : true,
						bgiframe : true,
						buttons : {
							"Confirm" : function() {
								$(this).dialog('close');
							}
						},
						open : function() {
							var sourceId = $("#sourceId").val();
							var htmlStr = '<iframe src="quote_order_source!input.action?id='
									+ sourceId
									+ '&operation_method=read"  height="540" width="760" scrolling="no" style="border:0px" frameborder="0"></iframe>';
							$('#viewSourceDialog').html(htmlStr);
						}
					});

	// set organization picker trigger
	$("#org_1Trigger")
			.click(
					function() {
						dataHolderWin.jQuery.data(dataHolderWin.document.body,
								'disableNew', 1);
						$('#orgDialogWindow').dialog('open');
						dataHolderWin.jQuery.data(dataHolderWin.document.body,
								'isGetAddr', "true");
						dataHolderWin.jQuery.data(dataHolderWin.document.body,
								'isGetOrgOtherInfo', "true");
						dataHolderWin.jQuery.data(dataHolderWin.document.body,
								'orgAddr', "address_1:address_2:address_3");
						dataHolderWin.jQuery
								.data(dataHolderWin.document.body,
										'orgOtherInfo',
										'orgDescription:categoryId:orgType:orgLangCode:orgWeb:orgActive');
						dataHolderWin.jQuery.data(dataHolderWin.document.body,
								'orgLoc', self);
						dataHolderWin.jQuery.data(dataHolderWin.document.body,
								'orgIdStr', 'orgId');
						dataHolderWin.jQuery.data(dataHolderWin.document.body,
								'orgNameStr', 'orgName1:orgName');
					});

	// --------------------------------
	// auto reload organization location when organization is changed
	function cleanOrgLocInfo() {
		$('#orgLocPhone').attr('value', '');
		$('#orgLocPhoneExt').attr('value', '');
		$('#orgLocAltPhone').attr('value', '');
		$('#orgLocAltPhoneExt').attr('value', '');
		$('#orgLocFax').attr('value', '');
		$('#orgLocFaxExt').attr('value', '');
		$('#orgLocAddr1').attr('value', '');
		$('#orgLocCity').attr('value', '');
		$('#orgLocAddr2').attr('value', '');
		$('#orgLocZip').attr('value', '');
		$('#orgLocAddr3').attr('value', '');
		$('#orgLocState').attr('value', '');
		$('#orgLocCountry').attr('value', 'US');
	}

	function cleanDivInfo() {
		$("#divId").attr("value", '');
		$("#divDescription").attr("value", '');
		$("#divLangCode").attr("value", '');
		$("#divName").attr("value", '');
		$("#divSupervisor").attr("value", '');
	}

	function cleanDivLocInfo() {
		$('#divLocPhone').attr('value', '');
		$('#divLocPhoneExt').attr('value', '');
		$('#divLocAltPhone').attr('value', '');
		$('#divLocAltPhoneExt').attr('value', '');
		$('#divLocFax').attr('value', '');
		$('#divLocFaxExt').attr('value', '');
		$('#divLocAddr1').attr('value', '');
		$('#divLocCity').attr('value', '');
		$('#divLocAddr2').attr('value', '');
		$('#divLocZip').attr('value', '');
		$('#divLocAddr3').attr('value', '');
		$('#divLocState').attr('value', '');
		$('#divLocCountry').attr('value', 'US');
	}

	function cleanDeptInfo() {
		$("#deptId").attr('value', '');
		$("#deptDeptFunc").attr('value', '');
		$("#deptDescription").attr('value', '');
		$("#deptLab").attr('value', '');
		$("#deptName").attr('value', '');
		$("#deptOffice").attr('value', '');
		$("#deptSupervisor").attr('value', '');
	}

	function cleanDeptLocInfo() {
		$('#deptLocPhone').attr('value', '');
		$('#deptLocPhoneExt').attr('value', '');
		$('#deptLocAltPhone').attr('value', '');
		$('#deptLocAltPhoneExt').attr('value', '');
		$('#deptLocFax').attr('value', '');
		$('#deptLocFaxExt').attr('value', '');
		$('#deptLocAddr1').attr('value', '');
		$('#deptLocCity').attr('value', '');
		$('#deptLocAddr2').attr('value', '');
		$('#deptLocZip').attr('value', '');
		$('#deptLocAddr3').attr('value', '');
		$('#deptLocState').attr('value', '');
		$('#deptLocCountry').attr('value', 'US');
	}

	function setObjProp() {
		var prop = arguments[0];
		if (prop == null || prop == undefined) {
			prop = "";
		}
		return prop;
	}

	// --------------------add by zhou gang 2011 06 16---
	$("#editTrigger").click(function() {
		$("#orgName1").attr("readonly", false);
		// cleanOrgLocInfo();
	});
	$("#editTrigger2").click(function() {
		$("#orgName").attr("readonly", false);
		// cleanOrgLocInfo();
	});

	$('#orgName1')
			.blur(
					function() {
						if ($('#orgId').val() == '') {
							if ($('#orgName').val() != orgNameBeforeChange) {
								cleanOrgLocInfo();
								cleanDivInfo();
								cleanDivLocInfo();
								cleanDeptInfo();
								cleanDeptLocInfo();
								orgNameBeforeChange = $('#orgName').val();
								orgIdBeforeChange = $('#orgId').val();
							}
							return;
						}

						
						
						
						
						if ($('#orgName').val() != orgNameBeforeChange
								|| $('#orgId').val() != orgIdBeforeChange) {
							// reload location
							$
									.ajax({
										type : "POST",
										url : baseUrl
												+ "basedata/get_loc_json!getOrgLocJson.action?orgId="
												+ $('#orgId').val(),
										dataType : 'json',
										success : function(data, textStatus) { 
											if (data.error) {
												//alert(data.error);
											} else {
												$('#orgLocPhone').attr('value',setObjProp(data.phone));
												$('#orgLocPhoneExt').attr('value',setObjProp(data.phoneExt));
												$('#orgLocAltPhone').attr('value',setObjProp(data.altPhone));
												$('#orgLocAltPhoneExt').attr('value',setObjProp(data.altPhoneExt));
												$('#orgLocFax').attr('value',setObjProp(data.fax));
												$('#orgLocFaxExt').attr('value',setObjProp(data.faxExt));
												$('#orgLocAddr1').attr('value',setObjProp(data.addrLine1));
												$('#orgLocCity').attr('value',setObjProp(data.city));
												$('#orgLocAddr2').attr('value',setObjProp(data.addrLine2));
												$('#orgLocZip').attr('value',setObjProp(data.zipCode));
												$('#orgLocAddr3').attr('value',setObjProp(data.addrLine3));
												$('#orgLocState').attr('value',setObjProp(data.state));
												$('#orgDescription').attr('value',setObjProp(data.description));
												$('#orgWeb').attr('value',setObjProp(data.web));
												$('#orgLocCountry').attr('value',setObjProp(data.country) ? setObjProp(data.country):'US');
												$('#categoryId option[value="'+ data.categoryId+ '"]').attr("selected", true);
												$('#orgType option[value="'+ data.typeId+ '"]').attr("selected", true);
												$('#orgLangCode option[value="'+ data.langCode+ '"]').attr("selected", true);
												if (data.activeFlag == "Y") {$("#orgActive").attr("checked", true);
												} else {
													$("#orgActive").attr("checked", false);
												}
												// customer top
												$("#customerTopForm").find("[name='addrLine1']").attr("value",setObjProp(data.addrLine1));
												$("#customerTopForm").find("[name='addrLine2']").attr("value",setObjProp(data.addrLine2));
												$("#customerTopForm").find("[name='addrLine3']").attr("value",setObjProp(data.addrLine3));
												$("#customerTopForm").find("[name='city']").attr("value",setObjProp(data.city));
												$("#customerTopForm").find("[name='zipCode']").attr("value",setObjProp(data.zipCode));
												$("#customerTopForm").find("#state option[value='"+ setObjProp(data.state)+ "']").attr("selected", true);
												$("#customerTopForm").find("#country option[value='"+ setObjProp(data.country)+ "']").attr("selected", true);

												cleanDivInfo();
												cleanDivLocInfo();
												cleanDeptInfo();
												cleanDeptLocInfo();
											}
										},
										error : function(xhr, textStatus) {
										/*	alert("Failed to access the web server. Please contact system administrator for help.");
											if (textStatus == 'timeout') {
											}

											if (textStatus == 'parsererror') {
												tmp = xhr.responseText.split(
														'{', 2);
												alert(tmp[0]);
											}*/
										}
									});

							// set variable
							orgNameBeforeChange = $('#orgName').val();
							orgIdBeforeChange = $('#orgId').val();
						}

						// it is defind in template 'customer_create_form.tpl'.

					});

	$('#divName')
			.blur(
					function() {
						if ($('#divId').val() == '') {
							if ($('#divName').val() != divNameBeforeChange) {
								cleanDivLocInfo();
								cleanDeptInfo();
								cleanDeptLocInfo();
								divNameBeforeChange = $('#divName').val();
								divIdBeforeChange = $('#divId').val();
							}
							return;
						}

						// it is defind in template 'customer_create_form.tpl'.
						if ($('#divName').val() != divNameBeforeChange
								|| $('#divId').val() != divIdBeforeChange) {
							// reload location
							$
									.ajax({
										type : "POST",
										url : baseUrl
												+ "basedata/get_loc_json!getDivLocJson.action?divId="
												+ $('#divId').val(),
										dataType : 'json',
										success : function(data, textStatus) {
											if (data.error) {
												//alert(data.error);
											} else {
												// set
												$('#divLocPhone').attr('value',
														setObjProp(data.phone));
												$('#divLocPhoneExt')
														.attr(
																'value',
																setObjProp(data.phoneExt));
												$('#divLocAltPhone')
														.attr(
																'value',
																setObjProp(data.altPhone));
												$('#divLocAltPhoneExt')
														.attr(
																'value',
																setObjProp(data.altPhoneExt));
												$('#divLocFax').attr('value',
														setObjProp(data.fax));
												$('#divLocFaxExt')
														.attr(
																'value',
																setObjProp(data.faxExt));
												$('#divLocAddr1')
														.attr(
																'value',
																setObjProp(data.addrLine1));
												$('#divLocCity').attr('value',
														setObjProp(data.city));
												$('#divLocAddr2')
														.attr(
																'value',
																setObjProp(data.addrLine2));
												$('#divLocZip')
														.attr(
																'value',
																setObjProp(data.zipCode));
												$('#divLocAddr3')
														.attr(
																'value',
																setObjProp(data.addrLine3));
												$('#divLocState').attr('value',
														setObjProp(data.state));
												$('#divLocCountry')
														.attr(
																'value',
																setObjProp(data.country) ? setObjProp(data.country)
																		: 'US');
												cleanDeptInfo();
												cleanDeptLocInfo();
											}
										},
										error : function(xhr, textStatus) {
											/*alert("Failed to access the web server. Please contact system administrator for help.");
											if (textStatus == 'timeout') {
											}

											if (textStatus == 'parsererror') {
												tmp = xhr.responseText.split(
														'{', 2);
												alert(tmp[0]);
											}*/
										}
									});

							// set variable
							divNameBeforeChange = $('#divName').val();
							divIdBeforeChange = $('#divId').val();
						}
					});
	$("#orgName")
			.blur(
					function() {

						var orgname = $("#orgName").val();
						if (orgname != "") {
							$
									.ajax({
										type : "POST",
										url : "basedata/org_picker!checkOrg.action?orgName="
												+ orgname,
										dataType : "json",
										success : function(data, textStatus) {
											if (data.message != 'ok') {
												alert(data.message);
												$("#orgName").val("");
												$("#orgName").focus();
												return false;
											}
										}

									});
						}
						if ($('#orgName').val() != orgNameBeforeChange
								|| $('#orgId').val() != orgIdBeforeChange) {
							// reload location
							$
									.ajax({
										type : "POST",
										url : baseUrl
												+ "basedata/get_loc_json!getOrgLocJson.action?orgId="
												+ $('#orgId').val(),
										dataType : 'json',
										success : function(data, textStatus) {
											if (data.error) {
												//alert(data.error);
											} else {
												// set
												$('#orgLocPhone').attr('value',
														setObjProp(data.phone));
												$('#orgLocPhoneExt')
														.attr(
																'value',
																setObjProp(data.phoneExt));
												$('#orgLocAltPhone')
														.attr(
																'value',
																setObjProp(data.altPhone));
												$('#orgLocAltPhoneExt')
														.attr(
																'value',
																setObjProp(data.altPhoneExt));
												$('#orgLocFax').attr('value',
														setObjProp(data.fax));
												$('#orgLocFaxExt')
														.attr(
																'value',
																setObjProp(data.faxExt));
												$('#orgLocAddr1')
														.attr(
																'value',
																setObjProp(data.addrLine1));
												$('#orgLocCity').attr('value',
														setObjProp(data.city));
												$('#orgLocAddr2')
														.attr(
																'value',
																setObjProp(data.addrLine2));
												$('#orgLocZip')
														.attr(
																'value',
																setObjProp(data.zipCode));
												$('#orgLocAddr3')
														.attr(
																'value',
																setObjProp(data.addrLine3));
												$('#orgLocState').attr('value',
														setObjProp(data.state));
												$('#orgDescription')
														.attr(
																'value',
																setObjProp(data.description));
												$('#orgWeb').attr('value',
														setObjProp(data.web));
												$('#orgLocCountry')
														.attr(
																'value',
																setObjProp(data.country) ? setObjProp(data.country)
																		: 'US');
												$(
														'#categoryId option[value="'
																+ data.categoryId
																+ '"]').attr(
														"selected", true);
												$(
														'#orgType option[value="'
																+ data.typeId
																+ '"]').attr(
														"selected", true);
												$(
														'#orgLangCode option[value="'
																+ data.langCode
																+ '"]').attr(
														"selected", true);
												if (data.activeFlag == "Y") {
													$("#orgActive").attr(
															"checked", true);
												} else {
													$("#orgActive").attr(
															"checked", false);
												}
												// customer top
												$("#customerTopForm")
														.find(
																"[name='addrLine1']")
														.attr(
																"value",
																setObjProp(data.addrLine1));
												$("#customerTopForm")
														.find(
																"[name='addrLine2']")
														.attr(
																"value",
																setObjProp(data.addrLine2));
												$("#customerTopForm")
														.find(
																"[name='addrLine3']")
														.attr(
																"value",
																setObjProp(data.addrLine3));
												$("#customerTopForm").find(
														"[name='city']").attr(
														"value",
														setObjProp(data.city));
												$("#customerTopForm")
														.find(
																"[name='zipCode']")
														.attr(
																"value",
																setObjProp(data.zipCode));
												$("#customerTopForm")
														.find(
																"#state option[value='"
																		+ setObjProp(data.state)
																		+ "']")
														.attr("selected", true);
												$("#customerTopForm")
														.find(
																"#country option[value='"
																		+ setObjProp(data.country)
																		+ "']")
														.attr("selected", true);

												cleanDivInfo();
												cleanDivLocInfo();
												cleanDeptInfo();
												cleanDeptLocInfo();
											}
										},
										error : function(xhr, textStatus) {
											/*alert("Failed to access the web server. Please contact system administrator for help.");
											if (textStatus == 'timeout') {
											}

											if (textStatus == 'parsererror') {
												tmp = xhr.responseText.split(
														'{', 2);
												alert(tmp[0]);
											}*/
										}
									});

							// set variable
							orgNameBeforeChange = $('#orgName').val();
							orgIdBeforeChange = $('#orgId').val();
						}

					});

	$('#deptName')
			.blur(
					function() {
						if ($('#deptId').val() == '') {
							if ($('#deptName').val() != deptNameBeforeChange) {
								cleanDeptLocInfo();
								deptNameBeforeChange = $('#deptName').val();
								deptIdBeforeChange = $('#deptId').val();
							}
							return;
						}
						// it is defind in template 'customer_create_form.tpl'.
						if ($('#deptName').val() != deptNameBeforeChange
								|| $('#deptId').val() != deptIdBeforeChange) {
							// reload location
							$
									.ajax({
										type : "POST",
										url : baseUrl
												+ "basedata/get_loc_json!getDeptLocJson.action?deptId="
												+ $('#deptId').val(),
										dataType : 'json',
										success : function(data, textStatus) {
											if (data.error) {
											//d	alert(data.error);
											} else {
												// set
												$('#deptLocPhone').attr(
														'value',
														setObjProp(data.phone));
												$('#deptLocPhoneExt')
														.attr(
																'value',
																setObjProp(data.phoneExt));
												$('#deptLocAltPhone')
														.attr(
																'value',
																setObjProp(data.altPhone));
												$('#deptLocAltPhoneExt')
														.attr(
																'value',
																setObjProp(data.altPhoneExt));
												$('#deptLocFax').attr('value',
														setObjProp(data.fax));
												$('#deptLocFaxExt')
														.attr(
																'value',
																setObjProp(data.faxExt));
												$('#deptLocAddr1')
														.attr(
																'value',
																setObjProp(data.addrLine1));
												$('#deptLocCity').attr('value',
														setObjProp(data.city));
												$('#deptLocAddr2')
														.attr(
																'value',
																setObjProp(data.addrLine2));
												$('#deptLocZip')
														.attr(
																'value',
																setObjProp(data.zipCode));
												$('#deptLocAddr3')
														.attr(
																'value',
																setObjProp(data.addrLine3));
												$('#deptLocState').attr(
														'value',
														setObjProp(data.state));
												$('#deptLocCountry')
														.attr(
																'value',
																setObjProp(data.country));
											}
										},
										error : function(xhr, textStatus) {
											/*alert("Failed to access the web server. Please contact system administrator for help.");
											if (textStatus == 'timeout') {
											}

											if (textStatus == 'parsererror') {
												tmp = xhr.responseText.split(
														'{', 2);
												alert(tmp[0]);
											}*/
										}
									});

							// set variable
							deptNameBeforeChange = $('#deptName').val();
							deptIdBeforeChange = $('#deptId').val();
						}
					});

	$("#org_2Trigger")
			.click(
					function() {
						dataHolderWin.jQuery.data(dataHolderWin.document.body,
								'disableNew', 1);
						dataHolderWin.jQuery.data(dataHolderWin.document.body,
								'isGetAddr', "true");
						dataHolderWin.jQuery.data(dataHolderWin.document.body,
								'isGetOrgOtherInfo', "true");
						dataHolderWin.jQuery.data(dataHolderWin.document.body,
								'orgAddr', "address_1:address_2:address_3");
						dataHolderWin.jQuery
								.data(dataHolderWin.document.body,
										'orgOtherInfo',
										'orgDescription:categoryId:orgType:orgLangCode:orgWeb:orgActive');
						dataHolderWin.jQuery.data(dataHolderWin.document.body,
								'orgLoc', self);
						dataHolderWin.jQuery.data(dataHolderWin.document.body,
								'orgIdStr', 'orgId');
						dataHolderWin.jQuery.data(dataHolderWin.document.body,
								'orgNameStr', 'orgName1:orgName');
						dataHolderWin.$('#orgDialogWindow').dialog('open');
					});

	// set division picker trigger
	$("#divDialogTrigger").click(
			function() {
				if ($('#orgName').val() == '') {
					alert("Please enter the organization. ");
					$('#orgName1').focus();
					return;
				}
				dataHolderWin.$('#divDialogWindow').dialog('open');
				dataHolderWin.jQuery.data(dataHolderWin.document.body,
						'divLoc', self);
				dataHolderWin.jQuery.data(dataHolderWin.document.body,
						'divIdStr', 'divId');
				dataHolderWin.jQuery.data(dataHolderWin.document.body,
						'divNameStr', 'divName');
			});

	// set department picker trigger
	$("#deptDialogTrigger").click(
			function() {
				if ($('#orgName').val() == '') {
					alert("Please enter the organization. ");
					$('#orgName1').focus();
					return;
				}
				dataHolderWin.$('#deptDialogWindow').dialog('open');
				dataHolderWin.jQuery.data(dataHolderWin.document.body,
						'deptLoc', self);
				dataHolderWin.jQuery.data(dataHolderWin.document.body,
						'deptIdStr', 'deptId');
				dataHolderWin.jQuery.data(dataHolderWin.document.body,
						'deptNameStr', 'deptName');
			});

	/**
	 * ************************* trigger active flag for organization division
	 * and dept **********
	 */

	// set update status dialog window
	$('#updateStatusDialog').dialog(
			{
				autoOpen : false,
				height : 240,
				width : 550,
				modal : true,
				bgiframe : true,
				buttons : {
					"Confirm" : function() {
						// set parameters
						$('#customerStatus').attr('value',
								$('#customerStatus2').attr('value'));
						$(this).dialog('close');
					}
				},
				close : function() {
					$('#customerStatus2').attr('value',
							$('#customerStatus').attr('value'));
				}
			});

	// syncronize customer status in dialog and display page;
	$('#customerStatus').change(
			function() {
				$('#customerStatus2').attr('value',
						$('#customerStatus').attr('value'));
			});

	// set update status dialog trigger
	$("#updateStatusTrigger").click(function() {
		$('#updateStatusDialog').dialog("open");
	});

	// syncronize department functions
	$('#dept_func').change(function() {
		$('#deptDeptFunc').attr('value', $(this).attr('value'));
		// $('#deptDeptFunc2').attr('value', $(this).attr('value'));

	});

	$('#deptDeptFunc').change(function() {
		$('#dept_func').attr('value', $(this).attr('value'));
	});

	// set location toggle trigger
	$('#locType').change(function() {
		if ($(this).attr('value') == 'divLocTable') {
			if ($('#divName').attr('value') == '') {
				alert("Please enter the division.");
				$('#locType').attr('value', "orgLocTable");

				$('#sameAsOrgLocDiv').css('display', 'none');
				$('#sameAsDivLocDiv').css('display', 'none');

				$('#divLocTable').css('display', 'none');
				$('#deptLocTable').css('display', 'none');
				$('#orgLocTable').css('display', '');

				$('#divName').focus();
				return false;
			}
		}

		if ($(this).attr('value') == 'deptLocTable') {
			if ($('#deptName').attr('value') == '') {
				alert("Please enter the department.");
				$('#locType').attr('value', "orgLocTable");
				$('#sameAsOrgLocDiv').css('display', 'none');
				$('#sameAsDivLocDiv').css('display', 'none');

				$('#divLocTable').css('display', 'none');
				$('#deptLocTable').css('display', 'none');
				$('#orgLocTable').css('display', '');

				$('#deptName').focus();
				return false;
			}
		}

		// start the page flow
		$('#orgLocTable').css('display', 'none');
		$('#divLocTable').css('display', 'none');
		$('#deptLocTable').css('display', 'none');
		$('#' + $(this).attr('value')).css('display', '');

		$('#sameAsOrgLocDiv').css('display', 'none');
		$('#sameAsDivLocDiv').css('display', 'none');

		$('#sameAsOrgLoc').attr('checked', '');
		$('#sameAsDivLoc').attr('checked', '');

		if ($(this).attr('value') == 'divLocTable') {
			$('#sameAsOrgLocDiv').css('display', '');
		}

		if ($(this).attr('value') == 'deptLocTable') {
			$('#sameAsOrgLocDiv').css('display', '');
			$('#sameAsDivLocDiv').css('display', '');
		}
	});

	// same as organization location radio button
	$('#sameAsOrgLoc').click(
			function() {
				if ($('#locType').attr('value') == 'divLocTable') {
					$('#divLocTable :input').each(
							function() {
								idName = this.id.substring(3);
								if (idName != '' && this.type == 'text')
									$('#div' + idName).attr('value',
											$('#org' + idName).attr('value'));
							});

					// you can find what is the meaning of '3' in the bottom of
					// customer_create_form.tpl
					countryDefaults[2] = $('#orgLocCountry').attr('value');
					stateDefaults[2] = $('#orgLocState').attr('value');
					initCountry('divLocCountry');
				} else {
					if ($('#locType').attr('value') == 'deptLocTable') {
						$('#deptLocTable :input').each(
								function() {
									idName = this.id.substring(4);
									if (idName != '' && this.type == 'text')
										$('#dept' + idName).attr(
												'value',
												$('#org' + idName)
														.attr('value'));
								});

						// you can find what is the meaning of '3' in the bottom
						// of customer_create_form.tpl
						countryDefaults[3] = $('#orgLocCountry').attr('value');
						stateDefaults[3] = $('#orgLocState').attr('value');
						initCountry('deptLocCountry');
					}
				}
			});

	// same as division location radio button
	$('#sameAsDivLoc').click(
			function() {
				if ($('#locType').attr('value') == 'deptLocTable') {
					$('#deptLocTable :input').each(
							function() {
								idName = this.id.substring(4);
								if (idName != '' && this.type == 'text')
									$('#dept' + idName).attr('value',
											$('#div' + idName).attr('value'));
							});

					// you can find what is the meaning of '3' in the bottom of
					// customer_create_form.tpl
					countryDefaults[3] = $('#divLocCountry').attr('value');
					stateDefaults[3] = $('#divLocState').attr('value');
					initCountry('deptLocCountry');
				}
			});

	// add/edit customer's address set dialog and trigger
	$('#addCustomerAddrDialog')
			.dialog(
					{
						autoOpen : false,
						height : 470,
						width : 760,
						modal : true,
						bgiframe : true,
						buttons : {},
						open : function() {
							var custNo = $("#custNo").val();
							if (custNo == null || custNo == ""
									|| custNo == undefined) {
								custNo = 0;
							}
							var custInt = $("#sessCustNo").val();
							var defaultFlags = '';
							if (document.getElementById('addrIframe')
									&& document.getElementById('addrIframe').contentWindow.document
											.getElementById('defaultFlags')) {
								defaultFlags = document
										.getElementById('addrIframe').contentWindow.document
										.getElementById('defaultFlags').value;
							}
							var htmlStr = '<iframe src="cust_address!showAddrCreateForm.action?custNo='
									+ custNo
									+ '&sessCustNo='
									+ custInt
									+ '&defaultFlags='
									+ defaultFlags
									+ '" height="410" width="720" scrolling="no" style="border:0px" frameborder="0"></iframe>';

							$('#addCustomerAddrDialog').html(htmlStr);
						}
					});

	$("#addCustomerAddrDialogTrigger").click(function() {
		$('#addCustomerAddrDialog').dialog('open');
	});

	// add/edit customer's address set dialog and trigger
	$('#addCustomerSpclPrcDialog')
			.dialog(
					{
						autoOpen : false,
						height : 450,
						width : 600,
						modal : true,
						bgiframe : true,
						buttons : {},
						open : function() {
							var custNo = $("#custNo").val();
							var sessCustNo = $("#sessCustNo").val();
							var listType = $("#addCustomerSpclPrcDialog")
									.dialog("option", "listType");
							var priceCatalogId = $("#addCustomerSpclPrcDialog")
									.dialog("option", "priceCatalogId");
							var htmlStr = '<iframe id="priceCreateFormIframe" src="customer/cust_spcl_prc!input.action?custNo='
									+ custNo
									+ '&sessCustNo='
									+ sessCustNo
									+ '&listType='
									+ listType
									+ '&priceCatalogId='
									+ priceCatalogId
									+ '" height="380" width="550" scrolling="no" style="border:0px" frameborder="0"></iframe>';
							$('#addCustomerSpclPrcDialog').html(htmlStr);
						}
					});

	// -----add by zhougang 2011 5 10---------
	$('#SeeCustomerSpclDialog')
			.dialog(
					{
						autoOpen : false,
						height : 450,
						width : 600,
						modal : true,
						bgiframe : true,
						buttons : {},
						open : function() {
							var custNo = $("#custNo").val();
							var sessCustNo = $("#sessCustNo").val();
							var listType = $("#SeeCustomerSpclDialog").dialog(
									"option", "listType");
							var priceCatalogNO = $("#SeeCustomerSpclDialog")
									.dialog("option", "catalogNO");
							var priceCatalogId = $("#SeeCustomerSpclDialog")
									.dialog("option", "priceCatalogId");
							var htmlStr = '<iframe id="priceCreateFormIframe" src="customer/cust_spcl_prc!showSpecialPrice_2.action?custNo='
									+ custNo
									+ '&sessCustNo='
									+ sessCustNo
									+ '&listType='
									+ listType
									+ '&priceCatalogNO='
									+ priceCatalogNO
									+ '&priceCatalogId='
									+ priceCatalogId
									+ '" height="380" width="550" scrolling="no" style="border:0px" frameborder="0"></iframe>';
							$('#SeeCustomerSpclDialog').html(htmlStr);
						}
					});
	// ------------------------------------------
	// add/edit customer's address set dialog and trigger
	$('#catalogSearchDialog').dialog({
		autoOpen : false,
		height : 400,
		width : 550,
		modal : true,
		bgiframe : true,
		buttons : {}
	});

	$('#webBehaviorDialog')
			.dialog(
					{
						autoOpen : false,
						height : 570,
						width : 860,
						modal : true,
						bgiframe : true,
						buttons : {},
						open : function() {
							var custNo = $("#custNo").val();
							var htmlStr = '<iframe src="customer_web_behavior.action?custNo='
									+ custNo
									+ '" height="520" width="830" scrolling="no" style="border:0px" frameborder="0"></iframe>';

							$('#webBehaviorDialog').html(htmlStr);
						}
					});

	$("#webBehaviorDialogTrigger").click(function() {
		$('#webBehaviorDialog').dialog('open');
	});

	// more info case dialog
	$('#caseDialog')
			.dialog(
					{
						autoOpen : false,
						height : 500,
						width : 720,
						modal : true,
						bgiframe : true,
						buttons : {},
						open : function() {
							var custNo = $("#custInt").val();
							var sessCustNo = $("#custNo").val();
							var htmlStr = '<iframe id="customer_case_iframe" src="cust_case!list.action?custNo='
									+ custNo
									+ '&sessCustNo='
									+ sessCustNo
									+ '" height="440" width="700" scrolling="no" style="border:0px" frameborder="0"></iframe>';
							$('#caseDialog').html(htmlStr);
						}
					});

	$("#caseDialogTrigger").click(function() {
		$('#caseDialog').dialog('open');
	});

	$('#newCase')
			.dialog(
					{
						autoOpen : false,
						height : 510,
						width : 700,
						modal : true,
						bgiframe : true,
						open : function() {
							var htmlStr = '<iframe id="TB_iframeContent" src="cust_case!input.action?custNo='
									+ custNo
									+ '&sessCustNo='
									+ sessCustNo
									+ '" height="480" width="700"  scrolling="no" style="border:0px" frameborder="0"></iframe>';
							$('#newCase').html(htmlStr);
						}
					});

	$('#editCaseDialog').dialog({
		autoOpen : false,
		height : 510,
		width : 670,
		modal : true,
		bgiframe : true,
		buttons : {}
	});

	// personal information dialog
	$('#personalInfoDialog')
			.dialog(
					{
						autoOpen : false,
						height : 280,
						width : 620,
						modal : true,
						bgiframe : true,
						buttons : {},
						open : function() {
							var custNo = $("#custNo").val();
							var custInt = $("#custInt").val();
							var htmlStr = '<iframe src="customer/customer_info!show.action?custNo='
									+ custInt
									+ '&sessCustNo='
									+ custNo
									+ '" height="230" width="570" scrolling="no" style="border:0px" frameborder="0"></iframe>';

							$('#personalInfoDialog').html(htmlStr);
						}
					});

	$("#personalInfoDialogTrigger").click(function() {
		$('#personalInfoDialog').dialog('open');
	});

	// area of interest dialog
	$('#areaInterestDialog')
			.dialog(
					{
						autoOpen : false,
						height : 550,
						width : 550,
						modal : true,
						bgiframe : true,
						buttons : {},
						open : function() {
							var custNo = $("#custNo").val();
							var custInt = $("#custInt").val();
							var htmlStr = '<iframe src="cust_area_interest!viewCustAreaInterest.action?custNo='
									+ custInt
									+ '&sessCustNo='
									+ custNo
									+ '" height="500" width="500" scrolling="no" style="border:0px" frameborder="0"></iframe>';

							$('#areaInterestDialog').html(htmlStr);
						}
					});

	$("#areaInterestDialogTrigger").click(function() {
		$('#areaInterestDialog').dialog('open');
	});

	// grant and publication dialog
	$('#grantPublicationDialog')
			.dialog(
					{
						autoOpen : false,
						height : 500,
						width : 800,
						modal : true,
						bgiframe : true,
						buttons : {},
						open : function() {
							var custNo = $("#custNo").val();
							var custInt = $("#custInt").val();
							var htmlStr = '<iframe src="cust_pub_grant!listPubGrant.action?custNo='
									+ custInt
									+ '&sessCustNo='
									+ custNo
									+ '" height="450" width="750" scrolling="no" style="border:0px" frameborder="0"></iframe>';

							$('#grantPublicationDialog').html(htmlStr);
						}
					});

	$("#grantPublicationDialogTrigger").click(function() {
		$('#grantPublicationDialog').dialog('open');
	});

	// edit customer address dialog;
	$('#editCustomerAddrDialog')
			.dialog(
					{
						autoOpen : false,
						height : 470,
						width : 760,
						modal : true,
						bgiframe : true,
						buttons : {},
						open : function() {
							var url = $("#editCustomerAddrDialogTrigger").val();
							var htmlStr = '<iframe src="'
									+ url
									+ '" height="410" width="720" scrolling="no" style="border:0px" frameborder="0"></iframe>';
							$('#editCustomerAddrDialog').html(htmlStr);
						}
					});

	$("#editCustomerAddrDialogTrigger").click(function() {
		$('#editCustomerAddrDialog').dialog('open');
	});

	// add/edit customer's contacts set dialog and trigger
	$('#addCustomerCntctDialog')
			.dialog(
					{
						autoOpen : false,
						height : 450,
						width : 760,
						modal : true,
						bgiframe : true,
						buttons : {},
						open : function() {
							var url = $("#addCustomerCntctDialogTrigger").val();
							var htmlStr = '<iframe src="'
									+ url
									+ '" height="400" width="720" scrolling="no" style="border:0px" frameborder="0"></iframe>';
							$('#addCustomerCntctDialog').html(htmlStr);
						}
					});

	$("#addCustomerCntctDialogTrigger").click(function() {
		$('#addCustomerCntctDialog').dialog('open');
	});

	$('#editCustomerCntctDialog')
			.dialog(
					{
						autoOpen : false,
						height : 470,
						width : 760,
						modal : true,
						bgiframe : true,
						buttons : {},
						open : function() {
							var url = $("#editCustomerCntctDialogTrigger")
									.val();
							var htmlStr = '<iframe src="'
									+ url
									+ '" height="410" width="720" scrolling="no" style="border:0px" frameborder="0"></iframe>';
							$('#editCustomerCntctDialog').html(htmlStr);
						}
					});

	$("#editCustomerCntctDialogTrigger").click(function() {
		$('#editCustomerCntctDialog').dialog('open');
	});

	// Select address
	$('#selectAddrDialog')
			.dialog(
					{
						autoOpen : false,
						height : 470,
						width : 760,
						modal : true,
						bgiframe : true,
						buttons : {},
						open : function() {
							var url = $("#selectAddrDialogTrigger").val();
							var htmlStr = '<iframe src="'
									+ url
									+ '" height="420" width="730" scrolling="no" style="border:0px" frameborder="0"></iframe>';
							$('#selectAddrDialog').html(htmlStr);
						}
					});

	$("#selectAddrDialogTrigger").click(function() {
		$('#selectAddrDialog').dialog('open');
	});

	// accounting notes drop down list
	$('#instruction_slt').change(function() {
		var slt = this;
		var tmpValue = slt.options[slt.selectedIndex].value;
		if (tmpValue == "") {
			$('#instruction_btn').val("  Add  ");
		} else {
			$('#instruction_btn').val("View/Edit");
		}
	});

	// accounting dialog
	$('#accountingInstructionDialog')
			.dialog(
					{
						autoOpen : false,
						height : 320,
						width : 600,
						modal : true,
						bgiframe : true,
						buttons : {},
						open : function() {
							var custNo = $("#custInt").val();
							var sessCustNo = $("#sessCustNo").val();
							var noteId = $("#instruction_slt").val();
							var htmlStr = '<iframe src="customer/customer!showInstruction.action?custNo='
									+ custNo
									+ '&sessCustNo='
									+ sessCustNo
									+ '&sessNoteId='
									+ noteId
									+ '" height="300" width="570" scrolling="yes" style="border:0px" frameborder="0"></iframe>';
							$('#accountingInstructionDialog').html(htmlStr);
						}
					});
});

/*******************************************************************************
 * * init territory list according to country;
 ******************************************************************************/
function initTerritory() {
	var defaultSalesContact = $("#salesContact").val();
	var defaultTechSupport = $("#techSupport").val();
	var salesManagerList = null;
	var techManagerList = null;
	var salesContactArray = [];
	var techSupportArray = [];
	var paramUrl = "basedata/get_territory_by_country!getSalesManagerAndtechSupport.action?sessCustNo="
			+ sessCustNo + "&custNo=" + custNo + "&type=customer";
	$
			.ajax({
				type : "GET",
				async : false,
				url : baseUrl + paramUrl,
				dataType : "json",
				// data: $('#'+searchtableid).serialize(),
				success : function(msg) {
					salesManagerList = msg.salesManagerList;
					techManagerList = msg.techManagerList;
					$('#salesContact').empty();
					$('#techSupport').empty();
					if (salesManagerList == null && techManagerList == null)
						return;

					// transverse the json object and get all array
					if (salesManagerList != null) {
						for (i = 0; i < salesManagerList.length; i++) {
							// init sales territory array
							salesContactArray[salesContactArray.length] = [
									salesManagerList[i].salesId,
									salesManagerList[i].resourceName ];
						}
					}
					if (techManagerList != null) {
						for (j = 0; j < techManagerList.length; j++) {
							techSupportArray[techSupportArray.length] = [
									techManagerList[j].salesId,
									techManagerList[j].resourceName ];
						}

					}

					var salesContactSel = $('#salesContact').get(0);
					var selectId = -1;
					for (i = 0; i < salesContactArray.length; i++) {
						tmpOpt = document.createElement("OPTION");
						tmpOpt.value = salesContactArray[i][0];
						tmpOpt.text = salesContactArray[i][1];
						salesContactSel[salesContactSel.options.length] = tmpOpt;
						if (defaultSalesContact != ""
								& tmpOpt.value == defaultSalesContact
								&& selectId == -1) {
							selectId = i;
						}
					}
					if (selectId != -1) {
						salesContactSel.selectedIndex = selectId;
					} else {
						salesContactSel.selectedIndex = 0;
					}

					var techSupportSel = $('#techSupport').get(0);
					var selectId2 = -1;
					for (i = 0; i < techSupportArray.length; i++) {
						tmpOpt = document.createElement("OPTION");
						tmpOpt.value = techSupportArray[i][0];
						tmpOpt.text = techSupportArray[i][1];
						techSupportSel[techSupportSel.options.length] = tmpOpt;
						if (defaultTechSupport != ""
								&& tmpOpt.value == defaultTechSupport
								&& selectId2 == -1) {
							selectId2 = i;
						}
					}
					if (selectId2 != -1) {
						techSupportSel.selectedIndex = selectId2;
					} else {
						techSupportSel.selectedIndex = 0;
					}

					// refresh
				},
				error : function(msg) {
					// alert( "get territory list failed!!" );
					// refresh my search drop down list
				}
			});
}

function initOrgTab() {
	if ($("#orgLangCode").val() == "") {
		$("#orgLangCode option[value='EN']").attr("selected", true);
	}
}

function salesTerrChange() {
	var salesTerr = $("#salesTerr").val();
	if (salesTerr != null && salesTerr != "" && salesTerr != undefined) {
		$
				.ajax({
					type : "GET",
					async : false,
					url : baseUrl
							+ "basedata/get_territory_by_country!searchSalesByTerritory.action?salesTerrId="
							+ salesTerr + "&defSalesContactId="
							+ defaultSalesContact + "&defaultTechSupportId="
							+ defaultTechSupport + "&defaultSalesGroup="
							+ defaultSalesGroup,
					dataType : "json",
					success : function(data) {
						$('#salesContact').empty();
						$('#techSupport').empty();
						$('#salesGroup').empty();
						var salesMap = data;
						var scListBySearch = salesMap.salesContactListBySearch;
						var tsListBySearch = salesMap.techSupportListBySearch;
						var sgListBySearch = salesMap.salesGroupBySearch;
						if (salesMap == null)
							return;

						var salesContactMap = new Array();
						var salesContactSel = $('#salesContact').get(0);
						if (scListBySearch != undefined) {
							for (i = 0; i < scListBySearch.length; i++) {
								tmpOpt = document.createElement("OPTION");
								tmpOpt.value = scListBySearch[i].salesId;
								tmpOpt.text = scListBySearch[i].resourceName;
								var hasElement = false;
								for (j = 0; j < salesContactMap.length; j++) {
									if (salesContactMap[j] == tmpOpt.text) {
										hasElement = true;
										break;
									}
								}
								if (!hasElement) {
									salesContactSel[salesContactSel.options.length] = tmpOpt;
								}
								salesContactMap[i] = tmpOpt.text;
								if (defaultSalesContact == tmpOpt.value) {
									salesContactSel.selectedIndex = i;
								}
							}
						}

						var techSupportMap = new Array();
						var techSupportSel = $('#techSupport').get(0);
						if (tsListBySearch != undefined) {
							for (i = 0; i < tsListBySearch.length; i++) {
								tmpOpt = document.createElement("OPTION");
								tmpOpt.value = tsListBySearch[i].salesId;
								tmpOpt.text = tsListBySearch[i].resourceName;
								var hasElement = false;
								for (j = 0; j < techSupportMap.length; j++) {
									if (techSupportMap[j] == tmpOpt.text) {
										hasElement = true;
										break;
									}
								}
								if (!hasElement) {
									techSupportSel[techSupportSel.options.length] = tmpOpt;
								}
								techSupportMap[i] = tmpOpt.text;
								if (defaultTechSupport == tmpOpt.value) {
									techSupportSel.selectedIndex = i;
								}
							}
						}

						var salesGroupSel = $('#salesGroup').get(0);
						if (sgListBySearch != undefined) {
							tmpOpt = document.createElement("OPTION");
							tmpOpt.value = sgListBySearch.groupId;
							tmpOpt.text = sgListBySearch.groupCode;
							salesGroupSel[salesGroupSel.options.length] = tmpOpt;
							if (defaultSalesGroup == tmpOpt.value) {
								salesGroupSel.selectedIndex = 0;
							}
						}
					},
					error : function(msg) {
						alert("get Sales Manager list failed!!");
					}
				});
	}
}

// onload init all country dropdown list and state city
$(document).ready(function() {
	// init country dropdown menu
	initCountry();
	// initTerritory();
	initOrgTab();
	if ($("#cardList").get(0)) {
		$("#cardList").get(0).selectedIndex = 0;// 初始化
	}
	$('.datepicker').each(function() {
		$(this).datepicker({
			dateFormat : 'yy-mm-dd',
			changeMonth : true,
			changeYear : true
		});
	});
	// init credit checkbox
	if ($("#custInt").val() == "") {
		$("#cust_ccpay_flag").attr("disabled", true);
		$("#credit_card_add_btn").hide();
	} else {
		initCcpayFlag();
	}
	/***************************************************************************
	 * $("#salesContact").change(function () { var salesContact =
	 * $("#salesContact").val(); $.ajax({ type: "GET", async: false, url:
	 * baseUrl +
	 * "basedata/get_territory_by_country!searchGroupBySalesId.action?defSalesContactId="+salesContact,
	 * dataType: "json", success: function(data){ $('#salesGroup').empty(); var
	 * sgListBySearch = data; if(sgListBySearch == null) return;
	 * 
	 * var salesGroupSel = $('#salesGroup').get(0); if (sgListBySearch !=
	 * undefined) { tmpOpt = document.createElement("OPTION"); tmpOpt.value =
	 * sgListBySearch.groupId; tmpOpt.text = sgListBySearch.groupCode;
	 * salesGroupSel[salesGroupSel.options.length]=tmpOpt; if(defaultSalesGroup ==
	 * tmpOpt.value) { salesGroupSel.selectedIndex = 0; } } }, error:
	 * function(msg){ alert( "get Sales Manager , Tech Account Manager and Sales
	 * Group list failed!!" ); } }); });
	 * 
	 * $("#techSupport").change(function () { var salesContact =
	 * $("#salesContact").val(); if (salesContact == null || salesContact == "" ||
	 * salesContact == undefined) { var techSupport = $("#techSupport").val();
	 * $.ajax({ type: "GET", async: false, url: baseUrl +
	 * "basedata/get_territory_by_country!searchGroupBySalesId.action?defSalesContactId="+techSupport,
	 * dataType: "json", success: function(data){ $('#salesGroup').empty(); var
	 * sgListBySearch = data; if(sgListBySearch == null) return;
	 * 
	 * var salesGroupSel = $('#salesGroup').get(0); if (sgListBySearch !=
	 * undefined) { tmpOpt = document.createElement("OPTION"); tmpOpt.value =
	 * sgListBySearch.groupId; tmpOpt.text = sgListBySearch.groupCode;
	 * salesGroupSel[salesGroupSel.options.length]=tmpOpt; if(defaultSalesGroup ==
	 * tmpOpt.value) { salesGroupSel.selectedIndex = 0; } } }, error:
	 * function(msg){ alert( "get Sales Group list failed!!" ); } }); } });
	 **************************************************************************/
});
