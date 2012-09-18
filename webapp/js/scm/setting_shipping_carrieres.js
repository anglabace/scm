$(function() {
	$('#carrierMainForm').validate({
		invalidHandler : function(form, validator) {
			$.each(validator.invalid, function(key, value) {
				alert(value);
				$(this).find("[name='" + key + "']").focus();
				return false;
			});
		},
		rules : {
			'shipCarrier.carrierCode' : {
				required : true
			},
			'shipCarrier.name' : {
				required : true
			}
		},
		messages : {
			'shipCarrier.carrierCode' : {
				required : "Please enter the Carrier Code "
			},
			'shipCarrier.name' : {
				required : "Please enter the Name."
			}
		},
		errorPlacement : function(error, element) {
		}
	});

	$('#generalForm')
			.validate(
					{
						invalidHandler : function(form, validator) {
							$.each(validator.invalid, function(key, value) {
								alert(value);
								$(this).find("[name='" + key + "']").focus();
								return false;
							});
						},
						rules : {
							'shipCarrier.rateDate' : {
								required : true
							},
							'shipCarrier.insurance' : {
								digits : true,
								required : true
							},
							'shipCarrier.additionalHandling' : {
								digits : true,
								required : true
							},
							'shipCarrier.hazardousMaterial' : {
								digits : true,
								required : true
							},
							'shipCarrier.packageSurcharge' : {
								digits : true,
								required : true
							},
							'shipCarrier.residentialSurcharge' : {
								digits : true,
								required : true
							},
							'shipCarrier.commercialSurcharge' : {
								digits : true,
								required : true
							}
						},
						messages : {
							'shipCarrier.rateDate' : {
								required : "Please enter the rateDate."

							},
							'shipCarrier.insurance' : {
								required : "Please enter the insurance .",
								digits : "The  insurance in 'General' tab can only contain digit!"
							},
							'shipCarrier.additionalHandling' : {
								required : "Please enter the additionalHandling .",
								digits : "The  additionalHandling in 'General' tab can only contain digit!"
							},
							'shipCarrier.hazardousMaterial' : {
								required : "Please enter the hazardousMaterial .",
								digits : "The hazardousMaterial in 'General' tab can only contain  digit"
							},
							'shipCarrier.packageSurcharge' : {
								required : "Please enter the packageSurcharge .",
								digits : "The packageSurcharge in 'General' tab can only contain digit!"
							},
							'shipCarrier.residentialSurcharge' : {
								required : "Please enter the residentialSurcharge .",
								digits : "TheresidentialSurcharge in 'General' tab can only contain digit!"
							},
							'shipCarrier.commercialSurcharge' : {
								required : "Please enter the commercialSurcharge .",
								digits : "The commercialSurcharge in 'General' tab can only contain digit!"
							}
						},
						errorPlacement : function(error, element) {
						}
					});

	$('#billinginfoForm')
			.validate(
					{
						invalidHandler : function(form, validator) {
							$.each(validator.invalid, function(key, value) {
								alert(value);
								$(this).find("[name='" + key + "']").focus();
								return false;
							});
						},
						rules : {
							'shipCarrier.billType' : {
								required : true
							},
							'shipCarrier.billStatus' : {
								required : true
							},
							'shipCarrier.accountNo' : {
								required : true
							},
							'shipCarrier.accountPwd' : {
								required : true,
								digits : true
							},
							'shipCarrier.phone' : {
								required : true,
								isPhone : true
							},
							'shipCarrier.phoneExt' : {
								digits : true
							},
							'shipCarrier.addrLine1' : {
								digits : true
							},
							'shipCarrier.addrLine2' : {
								digits : true
							},
							'shipCarrier.addrLine3' : {
								digits : true
							},
							'shipCarrier.city' : {
								required : true
							},
							'shipCarrier.zipCode' : {
								required : true
							},
							'shipCarrier.state' : {
								required : true
							}
						},
						messages : {
							'shipCarrier.billType' : {
								required : "Please enter the billType ."
							},
							'shipCarrier.billStatus' : {
								required : "Please enter the billStatus ."
							},
							'shipCarrier.accountNo' : {
								required : "Please enter the accountNo ."
							},
							'shipCarrier.accountPwd' : {
								required : "Please enter the accountPwd .",
								digits : "The accountPwd in 'Billing Info' tab can only contain digit!"
							},
							'shipCarrier.phone' : {
								required : "Please enter the phone .",
								isPhone : "The phone in 'Billing Info' tab can only contain digit and it is phone!!"
							},
							'shipCarrier.phoneExt' : {
								digits : "The phoneExt in 'Billing Info' tab can only contain digit!"
							},
							'shipCarrier.addrLine1' : {
								required : "Please enter the addrLine1 ."
							},
							'shipCarrier.addrLine2' : {
								required : "Please enter the addrLine2 ."
							},
							'shipCarrier.addrLine3' : {
								required : "Please enter the addrLine3 ."
							},
							'shipCarrier.city' : {
								required : "Please enter the city ."
							},
							'shipCarrier.zipCode' : {
								required : "Please enter the zipCode ."
							},
							'shipCarrier.state' : {
								required : "Please enter the  state ."
							}
						},
						errorPlacement : function(error, element) {
						}
					});

	$('#internationalForm')
			.validate(
					{
						invalidHandler : function(form, validator) {
							$.each(validator.invalid, function(key, value) {
								alert(value);
								$(this).find("[name='" + key + "']").focus();
								return false;
							});
						},
						rules : {
							'shipCarrier.intlInsurance' : {
								required : true,
								digits : true
							},
							'shipCarrier.intlAdditionalHandling' : {
								required : true,
								digits : true
							},
							'shipCarrier.intlHazardousMaterial' : {
								required : true,
								digits : true
							},
							'shipCarrier.intlPackageSurcharge' : {
								required : true,
								digits : true
							},
							'shipCarrier.intlResidentialSurcharge' : {
								required : true,
								digits : true
							},
							'shipCarrier.intlCommercialSurcharge' : {
								required : true,
								digits : true
							}
						},
						messages : {
							'shipCarrier.intlInsurance' : {
								required : "Please enter the intlInsurance .",
								digits : "The intlInsurance in 'International' tab can only contain digit!"
							},
							'shipCarrier.intlAdditionalHandling' : {
								required : "Please enter the intlAdditionalHandling .",
								digits : "The intlAdditionalHandling in 'International' tab can only contain digit!"
							},
							'shipCarrier.intlHazardousMaterial' : {
								required : "Please enter the intlHazardousMaterial .",
								digits : "The intlHazardousMaterial in 'International' tab can only contain digit!"
							},
							'shipCarrier.intlPackageSurcharge' : {
								required : "Please enter the intlPackageSurcharge .",
								digits : "The intlPackageSurcharge in 'International' tab can only contain digit!"
							},
							'shipCarrier.intlResidentialSurcharge' : {
								required : "Please enter the intlResidentialSurcharge .",
								digits : "The intlResidentialSurcharge in 'International' tab can only contain digit!"
							},
							'shipCarrier.intlCommercialSurcharge' : {
								required : "Please enter the intlCommercialSurcharge .",
								digits : "The intlCommercialSurcharge in 'International' tab can only contain digit!"
							}
						},
						errorPlacement : function(error, element) {
						}
					});

	$('#saveAllTrigger')
			.click(
					function() {
						if ($('#carrierMainForm').valid() === false
								|| $('#generalForm').valid() === false
								|| $('#internationalForm').valid() === false
								|| $('#billinginfoForm').valid() === false) {
							return false;
						}
						$(this).attr("disabled", true);
						var formStr = '';
						var urlStr = "";					
						urlStr = "shipping_carriers!save.action";
						var hrefStr;
						if (!$('#carrierCode').val()) {
							alert("Shipping Carrier code should't be empty!");
							$('#carrierCode').focus();
							return;
						}
						if (!$('#name').val()) {
							alert("Shipping Carrier name should't be empty!");
							$('#name').focus();
							return;
						}
						formStr += $('#carrierMainForm').serialize() + "&";
						formStr += $('#generalForm').serialize() + "&";
						formStr += $('#internationalForm').serialize() + "&";
						formStr += $('#billinginfoForm').serialize() + "&";
						defaultTab = activeTabIndex['dhtmlgoodies_tabView1'];
					 
						$
								.ajax({
									type : "POST",
									url : urlStr,
									data : formStr.substring(0,
											formStr.length - 1),
									dataType : 'json',
									success : function(data, textStatus) {
										if (hasException(data)) {
											$('#saveAllTrigger').attr(
													"disabled", false);
										} else {
											alert(data.message);
											location.href = "shipping_carriers!input.action?carrierId="
													+ data.carrierId
													+ "&opType=edit&&defaultTab="
													+ defaultTab
													+ "&rand="
													+ Math.random();
										}
									},
									error : function(xhr, textStatus) {
										alert("System error! Please contact system administrator for help.");
										$('#saveAllTrigger').attr("disabled",
												false);
										return;
									}
								});
					});
});

function cancelAll() {
	if (confirm("Are you sure to cancel all the changes?")) {
		$('#savedFlag').val('Y');
		window.location.reload();
	}

}
