$(function() {
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

	$('#contactTopForm')
			.validate(
					{
						invalidHandler : function(form, validator) {
							$.each(validator.invalid, function(key, value) {
								alert(value);
								// $("#"+key).focus();
								$(this).find("[name='" + key + "']").focus();
								return false;
							});
						},
						rules : {
							'contact.firstName' : {
								required : true,
								maxlength : 50
							},
							'contact.lastName' : {
								required : true,
								maxlength : 50
							},
							'contact.addrLine1' : {
								required : true,
								maxlength : 50
							},
							'contact.addrLine2' : {
								maxlength : 50
							},
							'contact.addrLine3' : {
								maxlength : 50
							},
							'contact.country' : {
								required : true
							},
							state : {
								required : true,
								maxlength : 50
							},
							'contact.zipCode' : {
								digits : true,
								required : true,
								maxlength : 10
							},
							'contact.city' : {
								required : true,
								maxlength : 50
							},
							orgName1 : {
								required : true//,
								//maxlength : 50

							}
						},
						messages : {
							'contact.firstName' : {
								required : "Please enter the first name.",
								maxlength : "First Name in 'General Information' - max length 50"
							},
							'contact.lastName' : {
								required : "Please enter the last name.",
 								maxlength : "Last Name in 'General Information' - max length: 50"
							},
							'contact.addrLine1' : {
								required : "Please enter the address Line 1.",
								maxlength : "Address Line 1 in 'General Information' - Max length: 50"
							},
							'contact.addrLine2' : {
								maxlength : "Address Line 2 in 'General Information' - Max length: 50"
							},
							'contact.addrLine3' : {
								maxlength : "Address Line 3 in 'General Information' - Max length: 50"
							},
							'contact.country' : "Please enter the country.",
						   	state : {
								required : "Please enter the state.",
								maxlength : "The phone number should be no more than 50 characters."
							},
							'contact.zipCode' : {
								digits:"Please enter the digit for the zip..",
								required : "Please enter the zip.",
								maxlength : "Zip in 'General Information' - Min length: 10 characters"
							},
							'contact.city' : {
								required : "Please enter the city.",
								maxlength : "the City in 'General Information' - Max length: 50"
							},
							orgName1 : {
								required : "Please enter the organization."//,
								//maxlength : "the organization name   - Max length: 50 characters"
							}
						},
						errorPlacement : function(error, element) {
						}
					});

	$('#contactGeneralForm')
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
							'contact.busEmail' : {
								required : true,
								maxlength : 50
							},
							'contact.busPhone' : {
								required : true,
								maxlength : 50
							},
							'contact.busPhoneExt' : {
								digits : true
							},
							'contact.fax' : {
								digits : true,
								maxlength : 20
							},
							'contact.faxExt' : {
								digits : true
							}
						},
						messages : {
							'contact.busEmail' : {
								required : "Please enter the bussiness email.",
								maxlength : "the bussiness email  - Max length: 50 characters"
							},
							'contact.busPhone' : {
								required : "Please enter the bussiness phone .",
								isPhone : "The Business Phone in 'General' tab can only contain - and digit"
							},
							'contact.busPhoneExt' : {
								digits : "The Business Phone Ext in 'General' tab can only contain digit!"
							},
							'contact.fax' : {
								digits : "The Fax in 'General' tab can only contain digit!",
								maxlength : "the Fax  - Max length: 20 characters"
							},
							'contact.faxExt' : {
								digits : "The Fax Extension in 'General' tab can only contain digit!"
							}
						},
						errorPlacement : function(error, element) {
						}
					});

	$('#morePhoneDialogForm').validate({
		invalidHandler : function(form, validator) {
			$.each(validator.invalid, function(key, value) {
				alert(value);
				$(this).find("[name='" + key + "']").focus();
				return false;
			});
		},
		rules : {
			'contact.altPhone' : {
				isPhone : true
			},
			'contact.altPhoneExt' : {
				digits : true
			},
			'contact.mobile' : {
				isPhone : true
			},
			'contact.altMobile' : {
				isPhone : true
			},
			'contact.homePhone' : {
				isPhone : true
			}
		},
		messages : {
			'contact.altPhone' : {
				isPhone : "Business Alternative can only contain - and digits"
			},
			'contact.altPhoneExt' : {
				digits : "Business Alternative Ext can only contain digits"
			},
			'contact.mobile' : {
				isPhone : "Mobile can only contain - and digits"
			},
			'contact.altMobile' : {
				isPhone : "Mobile Alternate can only contain - and digits"
			},
			'contact.homePhone' : {
				isPhone : "Home phone can only contain - and digits"
			}
		},
		errorPlacement : function(error, element) {
		}
	});

	$('#moreEmailDialogForm')
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
							'contact.altBusEmail' : {
								email : true
							},
							'contact.personalEmail' : {
								email : true
							},
							'contact.altPersonalEmail' : {
								email : true
							}
						},
						messages : {
							'contact.altBusEmail' : {
								email : "Bussiness Email Alternative must be in the format of name@domain.com"
							},
							'contact.personalEmail' : {
								email : "Personal Email must be in the format of name@domain.com"
							},
							'contact.altPersonalEmail' : {
								email : "Personal Email Alternative must be in the format of name@domain.com"
							}
						},
						errorPlacement : function(error, element) {
						}
					});

	$('#contactOrgForm')
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
							// 'contact.organization.orgName': {
							// required:true,minlength:1,maxlength:50 },
							'contact.organization.typeId' : {
								required : true
							},
							'contact.organization.phone' : {
								isPhone : true
							},
							'contact.organization.altPhone' : {
								isPhone : true
							},
							'contact.organization.fax' : {
								isPhone : true
							},
							// 'contact.organization.fax.e',
							'contact.organization.addrLine1' : {
								required : function(element) {
									return checkOrgLocIntegrity();
								}
							},
							'contact.organization.country' : {
								required : function(element) {
									return checkOrgLocIntegrity();
								}
							},
							'orgLocState' : {
								required : function(element) {
									return checkOrgLocIntegrity();
								}
							},
							'contact.organization.city' : {
								required : function(element) {
									return checkOrgLocIntegrity();
								}
							},
							'contact.organization.zipCode' : {
								required : function(element) {
									return checkOrgLocIntegrity();
								},
								minlength : 5
							},
							'contact.division.phone' : {
								isPhone : true
							},
							'contact.division.altPhone' : {
								isPhone : true
							},
							'contact.division.fax' : {
								isPhone : true
							},
							'contact.division.addrLine1' : {
								required : function(element) {
									return checkDivLocIntegrity();
								}
							},
							'contact.division.country' : {
								required : function(element) {
									return checkDivLocIntegrity();
								}
							},
							'divLocState' : {
								required : function(element) {
									return checkDivLocIntegrity();
								}
							},
							'contact.division.city' : {
								required : function(element) {
									return checkDivLocIntegrity();
								}
							},
							'contact.division.zipCode' : {
								required : function(element) {
									return checkDivLocIntegrity();
								},
								minlength : 5
							},
							'contact.department.phone' : {
								isPhone : true
							},
							'contact.department.altPhone' : {
								isPhone : true
							},
							'contact.department.fax' : {
								isPhone : true
							},
							'contact.department.addrLine1' : {
								required : function(element) {
									return checkDeptLocIntegrity();
								}
							},
							'contact.department.country' : {
								required : function(element) {
									return checkDeptLocIntegrity();
								}
							},
							'deptLocState' : {
								required : function(element) {
									return checkDeptLocIntegrity();
								}
							},
							'contact.department.city' : {
								required : function(element) {
									return checkDeptLocIntegrity();
								}
							},
							'contact.department.zipCode' : {
								required : function(element) {
									return checkDeptLocIntegrity();
								},
								minlength : 5
							}
						},
						messages : {
							// 'contact.organization.orgName': {
							// required:"Please enter the organization." },
							'contact.organization.typeId' : {
								required : "Please enter the organization type."
							},
							'contact.organization.phone' : {
								isPhone : "The phone of organization location in 'organization' tab can only contain - and digits"
							},
							'contact.organization.altPhone' : {
								isPhone : "The alt phone of organization Location in 'organization' tab can only contain - and digits"
							},
							'contact.organization.fax' : {
								isPhone : "The fax of organization location in 'organization' tab can only contain - and digits"
							},
							'contact.organization.addrLine1' : {
								required : "Please enter the address line 1."
							},
							'contact.organization.country' : {
								required : "Please enter the country of organization Location."
							},
							orgLocState : {
								required : "Please enter the state of organization Location."
							},
							'contact.organization.city' : {
								required : "Please enter the city of organization Location."
							},
							'contact.organization.zipCode' : {
								required : "Please enter the zip of organization Location.",
								minlength : "The zip of organization location in 'organization' tab - min length 5"
							},
							'contact.division.phone' : {
								isPhone : "The phone of division Location in 'organization' tab can only contain - and digits"
							},
							'contact.division.altPhone' : {
								isPhone : "The alt phone of division location in 'organization' tab can only contain - and digits"
							},
							'contact.division.fax' : {
								isPhone : "The fax of division location in 'organization' tab can only contain - and digits"
							},
							'contact.division.addrLine1' : {
								required : "Please enter the address line 1 of division location."
							},
							'contact.division.country' : {
								required : "Please enter the country of division location."
							},
							divLocState : {
								required : "Please enter the state of division location."
							},
							'contact.division.city' : {
								required : "Please enter the city of division location."
							},
							'contact.division.zipCode' : {
								required : "Please enter the zip of division location.",
								minlength : "The zip of division location in 'organization' tab - min length 5"
							},
							'contact.department.phone' : {
								isPhone : "The phone of department location in 'organization' tab can only contain - and digits"
							},
							'contact.department.altPhone' : {
								isPhone : "The alt phone of department location in 'organization' tab can only contain - and digits"
							},
							'contact.department.fax' : {
								isPhone : "The Fax of department location in 'organization' tab can only contain - and digits"
							},
							'contact.department.addrLine1' : {
								required : "Please enter the address line 1 of department Location."
							},
							'contact.department.country' : {
								required : "Please enter the country of department Location."
							},
							deptLocState : {
								required : "Please enter the state of department Location."
							},
							'contact.department.city' : {
								required : "Please enter the city of department Location."
							},
							'contact.department.zipCode' : {
								required : "Please enter the zip of department Location.",
								minlength : "The Zip of Department Location in 'organization' tab - min length 5"
							}
						},
						errorPlacement : function(error, element) {
						}
					});
});
