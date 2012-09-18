function checkCustomerAcctForm() {
	var customerAcctForm = $("#customerAcctForm");
	var taxExemptFlag = customerAcctForm.find("#taxExemptFlag").attr("checked");
	var taxId = customerAcctForm.find("#taxId").val();
	var creditLimit = customerAcctForm.find("#creditLimit").val();
	var cust_acct_crdtlimit = parseInt(customerAcctForm.find(
			"#cust_acct_crdtlimit").val());
	if (cust_acct_crdtlimit == 99) {
		if (creditLimit == "" || creditLimit == null) {
			alert("Please enter the Credit Limit!");
			return false;
		} else {
			if (isNaN(creditLimit)) {
				alert('Please enter a Digit for the Credit Limit! ');
				customerAcctForm.find("#creditLimit").focus();
				return false;
			}
		}
	}
	if (taxExemptFlag
			&& (taxId === undefined || TrimString(taxId) === "" || TrimString(taxId) === undefined)) {
		alert("Please enter the Tax ID of Accounting Location.");
		return false;
	}
	return true;
}

$(document)
		.ready(
				function() {
					$('#customerAccountHomeTrigger')
							.click(
									function() {
										var custno = $("#custNo").val();
										var firstName = $("#firstName").val();
										var lastName = $("#lastName").val();
										var email = $("#bussEmail").val();
										var cookie_name = "GSACCOUNTCookie";
										var url = "customer/customer!openaccount.action?cookie_name="
												+ cookie_name
												+ "&custno="
												+ custno
												+ "&firstName="
												+ firstName
												+ "&lastName="
												+ lastName + "&email=" + email;
										window.open(url);

									});

					// file begin
					$('#saveAllTrigger')
							.click(
									function() {
										// validate all the form data from page
										// || $('#customerAcctForm').valid() ===
										// false 去掉此验证 zouyulu 2010-08-31
										if ($('#customerTopForm').valid() === false
												|| $('#customerGeneralForm')
														.valid() === false
												|| $('#customerOrgForm')
														.valid() === false
												|| checkBestTimeToCall(
														"callTimeFrom",
														"callTimeFromPm",
														"callTimeTo",
														"callTimeToPm") == false
												|| !checkCustomerAcctForm())
											return false;

										// 对名称长度的判断、zskang 2011 12 1
										var zipCode = $("#customerTopForm").find("#zip").val();
										var country = $("#customerTopForm").find("#country").val(); 
										var state = $("#customerTopForm").find("#state").val(); 
										
								 
										if (country == "US" && state == "CA") {
											if (zipCode != null && zipCode != "") { 
												if (!(parseInt(zipCode) >= 90000
														&& parseInt(zipCode) <= 97000)) {
													alert("Please Enter the zip code scope about the 90000 to 97000 . ");
													return false;
												}
											}
										}
										var firstName = $("#customerTopForm")
												.find("#firstName").val();
										var Middle = $("#customerTopForm")
												.find("#midName").val();
										var lastName = $("#customerTopForm")
												.find("#lastName").val();
										if (firstName == "") {
											alert("Please enter the first name. ");
											return false;
										}
										if (lastName == "") {
											alert("Please enter the last name. ");
											return false;
										}
										if (Middle.length > 0) {
											var totallength = firstName.length
													+ Middle.length
													+ lastName.length;
											if (totallength > 50) {
												alert("the Name in 'General Information' - Max length: 50");
												return false;
											}
										}

										var bussemail = $(
												'#customerGeneralForm').find(
												"#bussEmail").val();

										var strs = bussemail.split("@");
										if (strs.length >= 3) {
											alert("You can input only one email.");
											return false;
										} else {
											var search_str = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
											if (!search_str.test(bussemail)) {
												alert('Please enter a valid email address');
												return false;
											}
										}

										var formStr = '';
										if (parseInt($("#custNo").val()) == $(
												"#custNo").val()) {
											formStr += "customerDetail.custNo="
													+ $("#custNo").val();
										}
										formStr += "&sessCustNo="
												+ $("#sessCustNo").val();
										formStr += "&"
												+ addFormNameToSerializeData(
														"customerDetail",
														$('#customerTopForm')
																.serialize());
										formStr += "&"
												+ addFormNameToSerializeData(
														"customerDetail",
														$(
																'#customerGeneralForm')
																.serialize());
										$('#morePhoneDialog :input[name!=""]')
												.each(
														function() {
															formStr += "&customerDetail."
																	+ this.name
																	+ "="
																	+ this.value;
														});
										var customerAcctForm = $("#customerAcctForm");
										var cust_acct_crdtlimit = customerAcctForm
												.find("#cust_acct_crdtlimit")
												.val();
										var creditLimit = customerAcctForm.find("#creditLimit").val();
										 
										if (cust_acct_crdtlimit != null
												&& cust_acct_crdtlimit != "") {
											formStr += "&crRatingIds="
													+ parseInt(cust_acct_crdtlimit);
										}
										if(cust_acct_crdtlimit==99){
											formStr += "&creditLimit="
												+ parseInt(creditLimit);
										}
										// default check box value
										if ($("#greenAccFlag").attr("checked") == false) {
											formStr += "&customerDetail.greenAccFlag=N";
										}
										if ($("#orgActive").attr("checked") == false) {
											formStr += "&customerDetail.organization.activeFlag=N";
										}
										if ($("#division_activeFlag").attr(
												"checked") == false) {
											formStr += "&customerDetail.division.activeFlag=N";
										}
										if ($("#department_activeFlag").attr(
												"checked") == false) {
											formStr += "&customerDetail.department.activeFlag=N";
										}
										//
										$('#moreEmailDialog :input[name!=""]')
												.each(
														function() {
															formStr += "&customerDetail."
																	+ this.name
																	+ "="
																	+ this.value;
														});

										$(
												'#updateStatusDialog :input[name!=""]')
												.each(
														function() {
															formStr += "&customerDetail."
																	+ this.name
																	+ "="
																	+ this.value;
														});
										// call time handle
										formStr += "&callTimeFrom="
												+ $("[_name='callTimeFrom']")
														.val();
										formStr += "&callTimeFromPm="
												+ $("[_name='callTimeFromPm']")
														.val();
										formStr += "&callTimeTo="
												+ $("[_name='callTimeTo']")
														.val();
										formStr += "&callTimeToPm="
												+ $("[_name='callTimeToPm']")
														.val();
										// organization
										formStr += "&"
												+ addFormNameToSerializeData(
														"customerDetail",
														$('#customerOrgForm')
																.serialize());
										formStr += "&customerDetail.department.deptFuncId="
												+ $('#customerOrgForm').find(
														"#deptDeptFunc").val(); // disable
										// 属性
										// 故单独获取
										// alert($("#deptDeptFunc").val());
										formStr += "&customerDetail.organization.state="
												+ $('#customerOrgForm').find(
														"#orgLocState").val();// 为disable属性，故单独获取
										// alert($("#orgLocState").val());
										formStr += "&customerDetail.organization.country="
												+ $('#customerOrgForm').find(
														"#orgLocCountry").val();// 为disable属性，故单独获取

										var country = $("#orgLocCountry").val();
										var shipMethod = $('#customerAcctForm')
												.find("#prefShipMthdD").val();
										// customer accounting form
										formStr += "&"
												+ addFormNameToSerializeData(
														"customerDetail",
														$('#customerAcctForm')
																.serialize());

										// special price form
										var specialDiscount = $(
												"#specialDiscount",
												$("#priceIframe").contents())
												.val();
										var discEffFrom = $("#discEffFrom",
												$("#priceIframe").contents())
												.val();
										var discEffTo = $("#discEffTo",
												$("#priceIframe").contents())
												.val();
										if (discEffTo < discEffFrom) {
											alert("Please select a valid end date.");
											return;
										}
										var priceCatalogId = $(
												"#priceCatalogId",
												$("#priceIframe").contents())
												.val();
										formStr += "&customerDetail.specialDiscount="
												+ specialDiscount;
										formStr += "&customerDetail.discEffFrom="
												+ discEffFrom;
										formStr += "&customerDetail.discEffTo="
												+ discEffTo;
										formStr += "&customerDetail.priceCatalogId="
												+ priceCatalogId;
										// set default tab
										// special name handle
										formStr += "&customerDetail.donotCode="
												+ donotCodeStr();

										defaultTab = activeTabIndex['dhtmlgoodies_tabView1'];
										// ajax submit all the serialized data;
										$(this).attr("disabled", true);

										$
												.ajax({
													type : "POST",
													url : baseUrl
															+ "customer/customer!save.action",
													data : formStr,
													dataType : 'json',
													success : function(data,
															textStatus) {
														if (hasException(data)) {
															$('#saveAllTrigger')
																	.attr(
																			"disabled",
																			false);
														} else {
															if (data.message == "The customer busEmail has been used by other customer.") {
																alert(data.message);
																$(
																		'#saveAllTrigger')
																		.attr(
																				"disabled",
																				false);
																return;
															}
															if (data.message == "Please select one tech account manager.") {
																alert(data.message);
																$(
																		'#saveAllTrigger')
																		.attr(
																				"disabled",
																				false);
																return;
															}
															alert(data.message);
															location.href = baseUrl
																	+ "customer/customer!edit.action?custNo="
																	+ data.custNo
																	+ "&defaultTab="
																	+ defaultTab
																	+ "&rand="
																	+ Math
																			.random();
														}
													},
													error : function(xhr,
															textStatus) {
														$('#saveAllTrigger')
																.attr(
																		"disabled",
																		false);
														alert("Failed to access the web server. Please contact system administrator for help.");
														if (textStatus == 'timeout') {
														}

														if (textStatus == 'parsererror') {
															tmp = xhr.responseText
																	.split('{',
																			2);
															alert(tmp[0]);
														}
													}
												});
									});

					$("#cust_ccpay_flag").click(function() {
						initCcpayFlag();
					});
					// file end
				});