$('#saveAllTrigger')
		.click(
				function() {
					if ($('#contactTopForm').valid() === false
							|| $('#contactGeneralForm').valid() === false
							|| $('#morePhoneDialogForm').valid() === false
							|| $('#moreEmailDialogForm').valid() === false
							|| $('#contactOrgForm').valid() === false) {
						return false;
					}

					var bussemail = $('#contactGeneralForm').find("#bussEmail")
							.val();
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

					var zipCode = $("#contactTopForm").find("#zip").val();
					var country = $("#contactTopForm").find("#country").val();
					var state= $("#contactTopForm").find("#state").val();
					if (country == "US" && state == "CA") {
						if (zipCode != null && zipCode != "") { 
							if (!(parseInt(zipCode) >= 90000
									&& parseInt(zipCode) <= 97000)) {
								alert("Please Enter the zip code scope about the 90000 to 97000 . ");
								return false;
							}
						}
					}

					$(this).attr("disabled", true);
					var formStr = '';
					formStr += $('#contactTopForm').serialize();
					formStr += "&" + $('#contactGeneralForm').serialize();
					formStr += "&" + $('#contactPersonalForm').serialize();
					var hobbys = '';
					for ( var i = 0; i < 4; i++) {
						if (document.getElementById("ids" + i).checked) {
							hobbys += '1:';
						} else {
							hobbys += '0:';
						}
					}
					formStr += "&contact.personal.hobby="
							+ hobbys.substring(0, hobbys.length - 1);
					$('#morePhoneDialog :input[name!=""]').each(function() {
						formStr += "&" + this.name + "=" + this.value;
					});

					$('#moreEmailDialog :input[name!=""]').each(function() {
						formStr += "&" + this.name + "=" + this.value;
					});

					$('#updateStatusDialog :input[name!=""]').each(function() {
						formStr += "&" + this.name + "=" + this.value;
					});
					formStr += "&contact.donotCode=" + donotCodeStr();

					var orgState = $('#contactOrgForm').find(
							"[name='contact.organization.state']");
					orgState.attr("value", $('#contactOrgForm').find(
							"[name='orgLocState']").val());

					var divState = $('#contactOrgForm').find(
							"[name='contact.division.state']");
					divState.attr("value", $('#contactOrgForm').find(
							"[name='divLocState']").val());

					var deptState = $('#contactOrgForm').find(
							"[name='contact.department.state']");
					deptState.attr("value", $('#contactOrgForm').find(
							"[name='deptLocState']").val());

					formStr += "&" + $('#contactOrgForm').serialize();
					// serialize interest form
					$('#disciplineInterestContainer :input').each(
							function() {
								formStr += '&' + $(this).attr('name') + '='
										+ $(this).attr('value');
							});
					$('#appInterestContainer :input').each(
							function() {
								formStr += '&' + $(this).attr('name') + '='
										+ $(this).attr('value');
							});
					if ($('#orgActive').attr("checked") != true) {
						formStr += "&" + "contact.organization.activeFlag=N";
					}
					if ($('#divActive').attr("checked") != true) {
						formStr += "&" + "contact.division.activeFlag=N";
					}
					if ($('#deptActive').attr("checked") != true) {
						formStr += "&" + "contact.department.activeFlag=N";
					}
					// organization
					formStr += "&contact.organization.state="
							+ $('#contactOrgForm').find("#orgLocState").val();// 为disable属性，故单独获取
					formStr += "&contact.organization.country="
							+ $('#contactOrgForm').find("#orgLocCountry").val();// 为disable属性，故单独获取

					// set default tab
					defaultTab = activeTabIndex['dhtmlgoodies_tabView1'];
					// ajax submit all the serialized data;
					$
							.ajax({
								type : "POST",
								url : "contact/contact!save.action",
								data : formStr,
								dataType : 'json',
								success : function(data, textStatus) {
									if (hasException(data)) {
										$('#saveAllTrigger').attr("disabled",
												false);
									} else {
										alert(data.message);
										location.href = "contact/contact!edit.action?contactNo="
												+ data.contactNo
												+ "&defaultTab="
												+ defaultTab
												+ "&rand=" + Math.random();
									}
								},
								error : function(xhr, textStatus) {
									alert("System error! Please contact system administrator for help.");
									$('#saveAllTrigger')
											.attr("disabled", false);
									return;
								}
							});
				});// end of $('#saveAllTrigger').click;

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