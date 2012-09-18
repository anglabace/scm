$(function() {

	$('#morePhoneDialog').dialog({
		autoOpen : false,
		height : 200,
		width : 620,
		modal : true,
		bgiframe : true,
		buttons : {
			"Confirm" : function() {
				if ($('#morePhoneDialogForm').valid() === false) {
					return false;
				}

				$(this).dialog('close');
			},
			"Cancel" : function() {
				if ($('#morePhoneDialogForm').valid() === false) {
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
				if ($('#moreEmailDialogForm').valid() === false) {
					return false;
				}
				$(this).dialog('close');
			},
			"Cancel" : function() {
				if ($('#moreEmailDialogForm').valid() === false) {
					return false;
				}
				$(this).dialog('close');
			}
		}
	});

	$("#moreEmailDialogTrigger").click(function() {
		$('#moreEmailDialog').dialog('open');
	});

	// contact activities dialog and trigger
	$('#contactActvDialog')
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
							var contactNo = $("#contactNo").val();
							var htmlStr = '<iframe src="contact!getContactActivity.action?contactNo='
									+ contactNo
									+ '" height="430" width="620" scrolling="no" style="border:0px" frameborder="0"></iframe>';
							$('#contactActvDialog').html(htmlStr);
						}
					});

	$("#contactActvDialogTrigger").click(function() {
		$('#contactActvDialog').dialog('open');
	});

	// view orginal source detail dialog and trigger
	$('#viewSourceDialog')
			.dialog(
					{
						autoOpen : false,
						height : 360,
						width : 540,
						modal : true,
						bgiframe : true,
						buttons : {
							"Confirm" : function() {
								$(this).dialog('close');
							}
						},
						open : function() {
							var sourceId = $("#sourceId").val();
							var htmlStr = '<iframe src="base_data!getSourceList.action?sourceId='
									+ sourceId
									+ '"  height="250" width="500" scrolling="no" style="border:0px" frameborder="0"></iframe>';
							$('#viewSourceDialog').html(htmlStr);
						}
					});

	$("#viewSourceDialogTrigger").click(function() {
		$('#viewSourceDialog').dialog('open');
	});

	// set organization picker trigger
	$("#org_1Trigger").click(
			function() {
				$('#orgDialogWindow').dialog('open');
				dataHolderWin.jQuery.data(dataHolderWin.document.body,
						'orgLoc', self);
				dataHolderWin.jQuery.data(dataHolderWin.document.body,
						'orgIdStr', 'orgId');
				dataHolderWin.jQuery.data(dataHolderWin.document.body,
						'orgNameStr', 'orgName1:orgName');
			});

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
		if (prop === null || prop === undefined) {
			prop = "";
		}
		return prop;
	}

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

						// it is defind in template 'contact_create_form.tpl'.
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
											//	alert(data.error);
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

												// contact top
												$("#contactTopForm")
														.find(
																"[id='address_1']")
														.attr(
																"value",
																setObjProp(data.addrLine1));
												$("#contactTopForm")
														.find(
																"[id='address_2']")
														.attr(
																"value",
																setObjProp(data.addrLine2));
												$("#contactTopForm")
														.find(
																"[id='address_3']")
														.attr(
																"value",
																setObjProp(data.addrLine3));
												$("#contactTopForm").find(
														"[id='city']").attr(
														"value",
														setObjProp(data.city));
												$("#contactTopForm")
														.find("[id='zip']")
														.attr(
																"value",
																setObjProp(data.zipCode));
												$("#contactTopForm")
														.find(
																"#state option[value='"
																		+ setObjProp(data.state)
																		+ "']")
														.attr("selected", true);
												$("#contactTopForm")
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
						// it is defind in template 'contact_create_form.tpl'.
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
												// set location
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
							divNameBeforeChange = $('#divName').val();
							divIdBeforeChange = $('#divId').val();
						}
					});
	/*
	 * <img id="editTrigger" src="images/b_edit.jpg" width="16" height="16"
	 * align="absmiddle" />
	 */
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
						// it is defind in template 'contact_create_form.tpl'.
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
												//alert(data.error);
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
																setObjProp(data.country) ? setObjProp(data.country)
																		: 'US');
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

	$("#org_2Trigger").click(
			function() {
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
					alert("Please enter the organization.");
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
					alert("Please enter the organization.");
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
						$('#contactStatus').attr('value',
								$('#contactStatus2').attr('value'));
						$(this).dialog('close');
					}
				},
				close : function() {
					$('#contactStatus2').attr('value',
							$('#contactStatus').attr('value'));
				}
			});

	// syncronize contact status in dialog and display page;
	$('#contactStatus').change(function() {
		$('#contactStatus2').attr('value', $('#contactStatus').attr('value'));
	});

	// set update status dialog trigger
	$("#updateStatusTrigger").click(function() {
		$('#updateStatusDialog').dialog("open");
	});

	// syncronize department functions
	$('#dept_func').change(function() {
		$('#deptDeptFunc').attr('value', $(this).attr('value'));
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
					// contact_create_form.tpl
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
						// of contact_create_form.tpl
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
					// contact_create_form.tpl
					countryDefaults[3] = $('#divLocCountry').attr('value');
					stateDefaults[3] = $('#divLocState').attr('value');
					initCountry('deptLocCountry');
				}
			});

	/**
	 * ******************** set area of interest add/remove event
	 * ****************************
	 */

	$('#disciplineInterestDelTrigger')
			.click(
					function() {
						$('#disciplineInterestContainer :input')
								.each(
										function() {
											if (this.checked) {
												var delIdList = $(
														'#del_dr_interestId')
														.val();
												var input_name = $(this).attr(
														"name");
												if (input_name.indexOf("src") != -1) {
													$('#del_dr_interestId')
															.attr(
																	'value',
																	delIdList
																			+ ","
																			+ input_name
																					.split(':')[1]);
												}
												$(this).parent().remove();
											}
										});
					});

	$('#appInterestDelTrigger')
			.click(
					function() {
						$('#appInterestContainer :input')
								.each(
										function() {
											if (this.checked) {
												var delIdList = $(
														'#del_at_interestId')
														.val();
												var input_name = $(this).attr(
														"name");
												if (input_name.indexOf("src") != -1) {
													$('#del_at_interestId')
															.attr(
																	'value',
																	delIdList
																			+ ","
																			+ input_name
																					.split(':')[1]);
												}
												$(this).parent().remove();
											}
										});
					});

	// add/edit contact's address set dialog and trigger
	$('#addContactAddrDialog')
			.dialog(
					{
						autoOpen : false,
						height : 600,
						width : 730,
						modal : true,
						bgiframe : true,
						buttons : {},
						open : function() {
							var contactNo = $("#contactNo").val();
							var sessContactNo = $("#sessContactNo").val();
							var defaultFlags = '';
							if (document.getElementById('addrIframe')
									&& document.getElementById('addrIframe').contentWindow.document
											.getElementById('defaultFlags')) {
								defaultFlags = document
										.getElementById('addrIframe').contentWindow.document
										.getElementById('defaultFlags').value;
							}
							var htmlStr = '<iframe src="contact/contact_address!add.action?contactNo='
									+ contactNo
									+ '&sessContactNo='
									+ sessContactNo
									+ '&defaultFlags='
									+ defaultFlags
									+ '" height="590" width="720" scrolling="no" style="border:0px" frameborder="0"></iframe>';

							$('#addContactAddrDialog').html(htmlStr);
						}
					});

	$("#addContactAddrDialogTrigger").click(function() {
		$('#addContactAddrDialog').dialog('open');
	});

	$('#editContactAddrDialog')
			.dialog(
					{
						autoOpen : false,
						height : 600,
						width : 730,
						modal : true,
						bgiframe : true,
						buttons : {},
						open : function() {
							var url = $("#editContactAddrDialogTrigger").val();
							var htmlStr = '<iframe src="'
									+ url
									+ '" height="590" width="720" scrolling="no" style="border:0px" frameborder="0"></iframe>';
							$('#editContactAddrDialog').html(htmlStr);
						}
					});

	$("#editContactAddrDialogTrigger").click(function() {
		$('#editContactAddrDialog').dialog('open');
	});

	// add/edit contact's contacts set dialog and trigger
	$('#addContactCntctDialog')
			.dialog(
					{
						autoOpen : false,
						height : 450,
						width : 760,
						modal : true,
						bgiframe : true,
						buttons : {},
						open : function() {
							var url = $("#addContactCntctDialogTrigger").val();
							var htmlStr = '<iframe src="'
									+ url
									+ '" height="400" width="720" scrolling="no" style="border:0px" frameborder="0"></iframe>';
							$('#addContactCntctDialog').html(htmlStr);
						}
					});

	$("#addContactCntctDialogTrigger").click(function() {
		$('#addContactCntctDialog').dialog('open');
	});

	$('#editContactCntctDialog')
			.dialog(
					{
						autoOpen : false,
						height : 470,
						width : 760,
						modal : true,
						bgiframe : true,
						buttons : {},
						open : function() {
							var url = $("#editContactCntctDialogTrigger").val();
							var htmlStr = '<iframe src="'
									+ url
									+ '" height="410" width="720" scrolling="no" style="border:0px" frameborder="0"></iframe>';
							$('#editContactCntctDialog').html(htmlStr);
						}
					});

	$("#editContactCntctDialogTrigger").click(function() {
		$('#editContactCntctDialog').dialog('open');
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
	/***************************************************************************
	 * ** validate the contact save form element
	 **************************************************************************/
	// init form validation
	jQuery.validator.addMethod("isPhone", function(value, element, param) {
		var tmpVal = value.replace(/ /g, "");
		return this.optional(element) || /^([\d-])*$/.test(tmpVal);
	}, "Please enter a valid phone");

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
			+ sessContactNo + "&custNo=" + contactNo + "&type=contact";
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

	/**
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
	 */
});

function initOrgTab() {
	if ($("#orgLangCode").val() == "") {
		$("#orgLangCode option[value='EN']").attr("selected", true);
	}
}