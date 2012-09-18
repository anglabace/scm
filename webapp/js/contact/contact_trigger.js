$(document).ready(function(){
//file begin
	
	//intrest
	$('#appInterestAddTrigger').click(function(e){
		var interestSel = $('#appInterest_sel').get(0);
		var interestOpt = interestSel.options[interestSel.selectedIndex];
		var newInterest = '<li><input  type="checkbox" value="'+ interestOpt.value + ':' + interestOpt.text +'" name="appInterest"/>'+ interestOpt.text +'</li>';
		if(interestOpt.value == '')	return;			
		var existFlag = false;
		$('#appInterestContainer :input').each(function(){
			tmpArr = this.value.split(':', 2);
			areaId = tmpArr[0];
			if(areaId == interestOpt.value)
			{
				existFlag = true;
			}
		});
		if(existFlag === false) {
			$('#appInterestContainer').append(newInterest);
		} else {
			alert("'"+interestOpt.text+"' has been added already.");
	    }
		return;
   });
	$('#editTrigger').click(function(){
		$("#orgName1").attr("readonly", false);
	});
	
	// auto reload organization location when organization is changed
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
						var orgname = $("#orgName1").val();
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
												$("#orgName1").val("");
												$("#orgName1").focus();
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
												alert(data.error);
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
											alert("Failed to access the web server. Please contact system administrator for help.");
											if (textStatus == 'timeout') {
											}

											if (textStatus == 'parsererror') {
												tmp = xhr.responseText.split(
														'{', 2);
												alert(tmp[0]);
											}
										}
									});

							// set variable
							orgNameBeforeChange = $('#orgName').val();
							orgIdBeforeChange = $('#orgId').val();
						}

						// it is defind in template 'customer_create_form.tpl'.

					});
	
	
	
   	$('#disciplineInterestAddTrigger').click(function(){
		var interestSel = $('#disciplineInterest').get(0);
		var interestOpt = interestSel.options[interestSel.selectedIndex];
		var newInterest = '<li><input  type="checkbox" checked="true" value="'+ interestOpt.value + ':' + interestOpt.text + '" name="decInterest"/>'+ interestOpt.text +'</li>';
		if(interestOpt.value == '')	return;			
		var existFlag = false;
		$('#disciplineInterestContainer :input').each(function(){
			tmpArr = this.value.split(':', 2);
			areaId = tmpArr[0];
			if(areaId == interestOpt.value)
			{
				existFlag = true;
			}
		});
		if(existFlag === false) {
			$('#disciplineInterestContainer').append(newInterest);
		} else {
			alert("'"+interestOpt.text+"' has been added already.");
		}
	});	
	
//file end
});