// check the integrity of organization location
function checkOrgLocIntegrity() {
	isIntegrated = false;
	if ($('#orgLocPhone').attr('value') != ''
			|| $('#orgLocPhoneExt').attr('value') != ''
			|| $('#orgLocAltPhone').attr('value') != ''
			|| $('#orgLocAltPhoneExt').attr('value') != ''
			|| $('#orgLocFax').attr('value') != ''
			|| $('#orgLocFaxExt').attr('value') != ''
			|| $('#orgLocAddr1').attr('value') != ''
			|| $('#orgLocCity').attr('value') != ''
			|| $('#orgLocAddr2').attr('value') != ''
			|| $('#orgLocZip').attr('value') != ''
			|| $('#orgLocAddr3').attr('value') != ''
			|| $('#orgLocState').attr('value') != '')
		isIntegrated = true; // should be integrity

	return isIntegrated;
}

// check the integrity of division location
function checkDivLocIntegrity() {
	isIntegrated = false;
	if ($('#divLocPhone').attr('value') != ''
			|| $('#divLocPhoneExt').attr('value') != ''
			|| $('#divLocAltPhone').attr('value') != ''
			|| $('#divLocAltPhoneExt').attr('value') != ''
			|| $('#divLocFax').attr('value') != ''
			|| $('#divLocFaxExt').attr('value') != ''
			|| $('#divLocAddr1').attr('value') != ''
			|| $('#divLocCity').attr('value') != ''
			|| $('#divLocAddr2').attr('value') != ''
			|| $('#divLocZip').attr('value') != ''
			|| $('#divLocAddr3').attr('value') != ''
			|| $('#divLocState').attr('value') != '')
		isIntegrated = true; // should be integrity

	return isIntegrated;
}

// check the integrity of department location
function checkDeptLocIntegrity() {
	isIntegrated = false;
	if ($('#deptLocPhone').attr('value') != ''
			|| $('#deptLocPhoneExt').attr('value') != ''
			|| $('#deptLocAltPhone').attr('value') != ''
			|| $('#deptLocAltPhoneExt').attr('value') != ''
			|| $('#deptLocFax').attr('value') != ''
			|| $('#deptLocFaxExt').attr('value') != ''
			|| $('#deptLocAddr1').attr('value') != ''
			|| $('#deptLocCity').attr('value') != ''
			|| $('#deptLocAddr2').attr('value') != ''
			|| $('#deptLocZip').attr('value') != ''
			|| $('#deptLocAddr3').attr('value') != ''
			|| $('#deptLocState').attr('value') != '')
		isIntegrated = true; // should be integrity

	return isIntegrated;
}

function checkBestTimeToCall(callTimeFromId, callTimeFromPmId, callTimeToId,
		callTimeToPmId) {
	var callTimeFrom = $("#" + callTimeFromId).val();
	var callTimeFromPm = $("#" + callTimeFromPmId).val();
	var callTimeTo = $("#" + callTimeToId).val();
	var callTimeToPm = $("#" + callTimeToPmId).val();
	// 0点特殊处理
	if (callTimeTo == "00:00" && callTimeToPm == "AM") {
		callTimeTo = "12:00";
		callTimeToPm = "PM";
	}
	var rt = true;
	if (callTimeFromPm == "PM" && callTimeToPm == "AM") {
		rt = false;
	}
	if (callTimeFrom != "" && callTimeTo != "") {
		if ((callTimeFromPm == "AM" && callTimeToPm == "AM")
				|| (callTimeFromPm == "PM" && callTimeToPm == "PM")) {
			if (callTimeFrom >= callTimeTo) {
				rt = false;
			}
		}
	}
	if (rt == false) {
		alert("Please select the correct time range.");
	}
	return rt;
}

$(document)
		.ready(
				function() {
					// file begin
					/***********************************************************
					 * ** validate the customer save form element
					 **********************************************************/
					// set phone dialog form validation
					$('#morePhoneDialogForm')
							.validate(
									{
										errorClass : "validate_error",
										highlight : function(element,
												errorClass) {
											$(element).addClass(errorClass);
										},
										unhighlight : function(element,
												errorClass, validClass) {
											$(element).removeClass(errorClass);
										},
										invalidHandler : function(form,
												validator) {
											$.each(validator.invalid, function(
													key, value) {
												alert(value);
												return false;
											});
										},
										rules : {
											altPhone : {
												isPhone : true
											},
											altPhoneExt : {
												digits : true
											},
											mobile : {
												isPhone : true
											},
											altMobile : {
												isPhone : true
											},
											homePhone : {
												isPhone : true
											}
										},
										messages : {
											altPhone : {
												isPhone : "Business Alternative can only contain - and digits"
											},
											altPhoneExt : {
												digits : "Business Alternative Ext can only contain digits"
											},
											mobile : {
												isPhone : "Mobile can only contain - and digits"
											},
											altMobile : {
												isPhone : "Mobile Alternate can only contain - and digits"
											},
											homePhone : {
												isPhone : "Home phone can only contain - and digits"
											}
										},
										errorPlacement : function(error,
												element) {
										}
									});
					// set email dialog form validation
					$('#moreEmailDialogForm')
							.validate(
									{
										errorClass : "validate_error",
										highlight : function(element,
												errorClass) {
											$(element).addClass(errorClass);
										},
										unhighlight : function(element,
												errorClass, validClass) {
											$(element).removeClass(errorClass);
										},
										invalidHandler : function(form,
												validator) {
											$.each(validator.invalid, function(
													key, value) {
												alert(value);
												$("#" + key).focus();
												return false;
											});
										},
										rules : {
											altBusEmail : {
												email : true
											},
											personalEmail : {
												email : true
											},
											altPersonalEmail : {
												email : true
											}
										},
										messages : {
											altBusEmail : {
												email : "Bussiness Email Alternative must be in the format of name@domain.com"
											},
											personalEmail : {
												email : "Personal Email must be in the format of name@domain.com"
											},
											altPersonalEmail : {
												email : "Personal Email Alternative must be in the format of name@domain.com"
											}
										},
										errorPlacement : function(error,
												element) {
										}
									});
					// init form validation
					jQuery.validator.addMethod("isPhone", function(value,
							element, param) {
						var tmpVal = value.replace(/ /g, "");
						return this.optional(element)
								|| /^([\d-])*$/.test(tmpVal);
					}, "Please enter a valid phone");

					$('#customerTopForm')
							.validate(
									{
										errorClass : "validate_error",
										highlight : function(element,
												errorClass) {
											$(element).addClass(errorClass);
										},
										unhighlight : function(element,
												errorClass, validClass) {
											$(element).removeClass(errorClass);
										},
										invalidHandler : function(form,
												validator) {
											$.each(validator.invalid, function(
													key, value) {
												alert(value);
												$("#" + key).focus();
												return false;
											});
										},
										rules : {

											addrLine1 : {
												required : true, 
												maxlength : 50
											},
											addrLine2 : { 
												maxlength : 50
											},
											addrLine3 : { 
												maxlength : 50
											},
											country : {
												required : true
											},
											state : {
												required : true, 
												maxlength : 30
											},
											zipCode : {
												required : true, 
												maxlength : 10
											},
											city : {
												required : true, 
												maxlength : 50
											},
											_tmp_orgName : {
												required : true//, 
											//	maxlength : 50
											}
										},
										messages : {
											addrLine1 : {
												required : "Please enter the address .",
 												maxlength : "The address in 'General Information' - Max length: 50 characters"
											},
											addrLine2 : {
 												maxlength : "The address in 'General Information' - Max length:  50 characters"
											},
											addrLine3 : {
 												maxlength : "The address in 'General Information' - Max length: 50 characters"
											},
											country : "Please enter the country.",
											state : {
												required : "Please enter the state.",
 												maxlength : "The state in 'General Information' - Max length: 50 characters"
											},
											zipCode : {
												required : "Please enter the zip.",
 												maxlength : "The zip in 'General Information' - Max length: 10 characters"
											},
											city : {
												required : "Please enter the city.",
 												maxlength : "The city in 'General Information' - Max length: 50 characters"
											},
											_tmp_orgName : {
												required : "Please enter the organization."//,
 											//	maxlength : "The orgName in 'General Information' - Max length: 50 characters"
											}
										},
										errorPlacement : function(error,
												element) {
										}
									});

					$('#customerGeneralForm')
							.validate(
									{
										errorClass : "validate_error",
										highlight : function(element,
												errorClass) {
											$(element).addClass(errorClass);
										},
										unhighlight : function(element,
												errorClass, validClass) {
											$(element).removeClass(errorClass);
										},
										invalidHandler : function(form,
												validator) {
											$.each(validator.invalid, function(
													key, value) {
												alert(value);
												$("#" + key).focus();
												return false;
											});
										},
										rules : {
											busPhone : {
											//digits : true,
												required : true,
 												maxlength : 20
											},
											busPhoneExt : {
												digits : true
											},
											fax : { 
												maxlength : 20
											},
											busEmail : {
												//email : true,
 												maxlength : 50
											},
											faxExt : {
												digits : true
											},
											purchasingCpbl : {
												digits : true
											}
										},
										messages : {
											busPhone : {
												//digits : "The Business Phone  in 'General' tab can only contain digit!",
												required : "Please enter the bussiness phone.",
 												maxlength : "the Bussiness phone in 'General Information' - Max length: 20"

											},
											busPhoneExt : {
												digits : "The Business Phone Ext in 'General' tab can only contain digit!"
											},
											fax : { 
  												maxlength : "The Fax in 'General Information' - Max length: 20"
											},
											busEmail : {
											//	email : "Please enter a Email format.",
 												maxlength : "The Business	Email in 'General Information' - Max length: 50 characters "
											},
											faxExt : {
												digits : "The Fax Extension in 'General' tab can only contain digit!"
											},
											purchasingCpbl : {
												digits : "The Purchasing Capability in 'General' tab can only contain digit!"
											}
										},
										errorPlacement : function(error,
												element) {
										}
									});

					// organization form validation start
					$('#customerOrgForm')
							.validate(
									{
										errorClass : "validate_error",
										highlight : function(element,
												errorClass) {
											$(element).addClass(errorClass);
										},
										unhighlight : function(element,
												errorClass, validClass) {
											$(element).removeClass(errorClass);
										},
										invalidHandler : function(form,
												validator) {
											$.each(validator.invalid, function(
													key, value) {
												alert(value);
												$(this).find(
														"[name='" + key + "']")
														.focus();
												return false;
											});
										},
										rules : {
											"organization.name" : {
												required : true,
												minlength : 1
											}

										},
										messages : {
											"organization.name" : {
												required : "Please enter the organization."
											}
										},
										errorPlacement : function(error,
												element) {
										}
									});

				});