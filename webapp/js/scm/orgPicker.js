// init org dialog window
$(function() {
	$("#orgDialogWindow")
			.dialog(
					{
						bgiframe : true,
						autoOpen : false,
						height : 420,
						width : 670,
						modal : true,
						beforeclose : function() {
							// this is an organization picker hook,
							// you can set the jQuery data in the "call" page
							if (!dataHolderWin) {
								return false;
							}

							if (dataHolderWin.jQuery.data(
									dataHolderWin.document.body,
									'orgPickerData')) {
								var orgArray = null;
								var elemIdAdds = null;
								var orgOtherInfo = null;
								if (dataHolderWin.jQuery.data(
										dataHolderWin.document.body,
										'isGetAddr') != 'undefind'
										&& dataHolderWin.jQuery.data(
												dataHolderWin.document.body,
												'isGetAddr') == 'true'
										&& dataHolderWin.jQuery.data(
												dataHolderWin.document.body,
												'isGetOrgOtherInfo') == 'undefind') {
									// 带出org的地址
									orgArray = dataHolderWin.jQuery.data(
											dataHolderWin.document.body,
											'orgPickerData').split('::', 5);
									elemIdAdds = dataHolderWin.jQuery.data(
											dataHolderWin.document.body,
											'orgAddr').split(':');
									dataHolderWin.jQuery.data(
											dataHolderWin.document.body,
											'orgAddr', '');
								} else if (dataHolderWin.jQuery.data(
										dataHolderWin.document.body,
										'isGetOrgOtherInfo') != 'undefind'
										&& dataHolderWin.jQuery.data(
												dataHolderWin.document.body,
												'isGetOrgOtherInfo') == 'true'
										&& dataHolderWin.jQuery.data(
												dataHolderWin.document.body,
												'isGetAddr') == 'undefind') {

									orgArray = dataHolderWin.jQuery.data(
											dataHolderWin.document.body,
											'orgPickerData').split('::', 8);
									orgOtherInfo = dataHolderWin.jQuery.data(
											dataHolderWin.document.body,
											'orgOtherInfo').split(':');
									dataHolderWin.jQuery.data(
											dataHolderWin.document.body,
											'orgOtherInfo', '');
								} else if (dataHolderWin.jQuery.data(
										dataHolderWin.document.body,
										'isGetAddr') != 'undefind'
										&& dataHolderWin.jQuery.data(
												dataHolderWin.document.body,
												'isGetAddr') == 'true'
										&& dataHolderWin.jQuery.data(
												dataHolderWin.document.body,
												'isGetOrgOtherInfo') != 'undefind'
										&& dataHolderWin.jQuery.data(
												dataHolderWin.document.body,
												'isGetOrgOtherInfo') == 'true') {
									orgArray = dataHolderWin.jQuery.data(
											dataHolderWin.document.body,
											'orgPickerData').split('::', 13);
									elemIdAdds = dataHolderWin.jQuery.data(
											dataHolderWin.document.body,
											'orgAddr').split(':');
									orgOtherInfo = dataHolderWin.jQuery.data(
											dataHolderWin.document.body,
											'orgOtherInfo').split(':');
									dataHolderWin.jQuery.data(
											dataHolderWin.document.body,
											'orgAddr', '');
									dataHolderWin.jQuery.data(
											dataHolderWin.document.body,
											'orgOtherInfo', '');

								} else {
									orgArray = dataHolderWin.jQuery.data(
											dataHolderWin.document.body,
											'orgPickerData').split('::', 2);
								}
								var orgid = orgArray[0];
								var orgname = orgArray[1];
								var orgLoc = dataHolderWin.jQuery.data(
										dataHolderWin.document.body, 'orgLoc');
								var elemIdOrgIds = null;
								var orgId_Str = dataHolderWin.jQuery
										.data(dataHolderWin.document.body,
												'orgIdStr');
								if (orgId_Str != undefined) {
									elemIdOrgIds = orgId_Str.split(':');
								}
								var elemIdOrgNames = dataHolderWin.jQuery.data(
										dataHolderWin.document.body,
										'orgNameStr').split(':');

								if (!orgLoc) {
									alert("The organization location window is not loaded. (orgPicker.js)");
									return false;
								}
								
								//更换country和state  add by Zhang Yong 2011-10-20
								orgLoc.$('#country').attr('value', orgArray[11]);
								orgLoc.$("#country").trigger("change");
								orgLoc.$('#state').attr('value', orgArray[12]);
								orgLoc.$("#state").find("option[value='"+orgArray[12]+"']").attr("selected", true);

								// set element id value
								if (elemIdOrgIds != null) {
									for (i = 0; i < elemIdOrgIds.length; i++) {
										if (orgLoc.$('#' + elemIdOrgIds[i])
												.get(0)) {
											orgLoc.$('#' + elemIdOrgIds[i])
													.attr('value', orgid);
										}
									}
								}

								// set element name value
								setFocus = false;
								focusElemId = '';
								for (i = 0; i < elemIdOrgNames.length; i++) {
									if (orgLoc.$('#' + elemIdOrgNames[i])) {
										orgLoc.$('#' + elemIdOrgNames[i]).attr(
												'value', orgname);
										if (setFocus === false) {
											orgLoc.$('#' + elemIdOrgNames[i])
													.focus();
											focusElemId = elemIdOrgNames[i];
											setFocus = true;
										}
									}
								}

								orgLoc.$('#' + focusElemId).trigger('blur');
								if (elemIdAdds && elemIdAdds != "") {
									for (i = 0; i < elemIdAdds.length; i++) {
										if (orgLoc.$('#' + elemIdAdds[i])) {
											orgLoc.$('#' + elemIdAdds[i]).attr(
													'value', orgArray[2 + i]);
										}
									}
								}
								if (orgOtherInfo && orgOtherInfo != "") {
									for (i = 0; i < orgOtherInfo.length; i++) {
										if (orgLoc.$('#' + orgOtherInfo[i])) {
											if (elemIdAdds) {
												orgLoc
														.$(
																'#'
																		+ orgOtherInfo[i])
														.attr('value',
																orgArray[5 + i]);
											} else {
												orgLoc
														.$(
																'#'
																		+ orgOtherInfo[i])
														.attr('value',
																orgArray[2 + i]);
											}

										}
									}
								}
								dataHolderWin.jQuery.data(
										dataHolderWin.document.body,
										'orgPickerData', '');
								dataHolderWin.jQuery.data(
										dataHolderWin.document.body, 'orgLoc',
										'');
								dataHolderWin.jQuery.data(
										dataHolderWin.document.body,
										'orgIdStr', '');
								dataHolderWin.jQuery.data(
										dataHolderWin.document.body,
										'orgNameStr', '');
							}
						},
						close : function() {
							$('#orgDialogWindow').html('');
						},
						open : function() {
							if (!dataHolderWin) {
								return false;
							}
							var param = '';
							if (dataHolderWin.jQuery.data(
									dataHolderWin.document.body, 'disableNew')
									&& dataHolderWin.jQuery.data(
											dataHolderWin.document.body,
											'disableNew') == 1) {
								param = "?disableNew=1";
								dataHolderWin.jQuery.data(
										dataHolderWin.document.body,
										'disableNew', 0);
							}
							var htmlStr = '<iframe name="orgDialog" id="orgDialog" src="basedata/org_picker.action'
									+ param
									+ '" height="350" width="650" scrolling="no" style="border:0px" frameborder="0"></iframe>';
							$('#orgDialogWindow').html(htmlStr);
						}
					});

});