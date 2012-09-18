<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base id="myBaseId" href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Contact Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet"
	type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css"
	rel="stylesheet" type="text/css" />
<!-- public javascript library -->


<script type="text/javascript" src="${global_js_url}ajax.js"></script>
<script type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script type="text/javascript" src="${global_js_url}show_tag.js"></script>
<script type="text/javascript" src="${global_js_url}select.js"></script>
<script type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script type="text/javascript" src="${global_js_url}gs.util.js"></script>
<script type="text/javascript" src="${global_js_url}scm/config.js"></script>
<script src="${global_js_url}/recordTime.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript">
	var baseUrl = "${global_url}";
	function toggleShowMore_img2(imgId, divID) {
		var oId = document.getElementById(divID);
		var obj = document.getElementById(imgId);
		if (obj.src.indexOf('ad.gif') > 0) {
			obj.src = "${global_url}images/ar.gif";
			oId.style.display = "none";
		} else {
			obj.src = "${global_url}images/ad.gif";
			oId.style.display = "block";
		}
	}
</script>
</head>
<body class="content" onload="recordTime()">
	<div class="scm">
		<div class="input_box, content_box">
			<form id="contactTopForm" class="contactForm">
				<!-- summary start -->
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<div class="invoice_title">
								<span style="cursor: pointer"
									onclick="toggle_showmore('Contact_InfoItem', 'Contact_Info');"><img
									src="${global_image_url}/ad.gif" width="11" height="11"
									id="Contact_InfoItem" /> &nbsp;General Information</span>
							</div>
							<div id="Contact_Info" style="display: block;">
								<table width="99%" border="0" cellpadding="0" cellspacing="0"
									class="Customer_table">
									<tr>
										<td width="6%"><span class="disable_font" align="right">Contact
												No</span></td>
										<td colspan="3"><input type="hidden" id="sessContactNo"
											name="sessContactNo" value="${sessContactNo }" /> <input
											name="contact.contactNo" id="contactNo"
											value="${contact.contactNo}" type="text" class="NFText3"
											size="40" readonly /></td>
										<td width="7%" align="right">Alternate No</td>
										<td colspan="4"><input name="contact.altNo" id="altNo"
											value="${contact.altNo}" type="text" size="40" class="NFText" />
										</td>

									</tr>
									<tr>
										<td align="right">Name</td>
										<td width="1%">
											<div class="select">
												<s:select id="namePfx" name="contact.namePfx"
													list="namePfxList" listKey="value" listValue="value"
													headerKey="" headerValue="" cssStyle="width:75px;"
													value="contact.namePfx"></s:select>
											</div>
										</td>
										<td width="6%" align="right"><span class="important">*</span>First</td>
										<td width="20%"><input name="contact.firstName"
											type="text" id="firstName" value="${contact.firstName}"
											size="16" class="NFText" /></td>
										<td align="right">Middle</td>
										<td width="25%"><input name="contact.midName" type="text"
											id="midName" value="${contact.midName}" size="40"
											class="NFText" /></td>
										<td width="8%" align="right"><span class="important">*</span>Last</td>
										<td width="27%"><input name="contact.lastName"
											type="text" value="${contact.lastName}" id="lastName"
											size="20" class="NFText" />
										<s:select id="nameSfx" name="contact.nameSfx"
												list="nameSfxList" listKey="value" listValue="value"
												headerKey="" headerValue="" cssStyle="width:80px;"
												value="contact.nameSfx"></s:select></td>

									</tr>
									<tr>
										<td align="right">Title</td>
										<td colspan="3"><input name="contact.title" type="text"
											id="title" value="${contact.title}" size="40" class="NFText" />
										</td>
										<td align="right">Supervisor</td>
										<td><input type="text" name="supervisor" size="40"
											class="NFText" value="${contact.supervisor}" /></td>
										<td align="right"><span class="important">*</span>Organization
										</td>
										<td colspan=""><input name="orgName1" type="text"
											id="orgName1" value="${contact.organization.name}" size="40"
											class="NFText" readonly /> <img id="org_1Trigger"
											src="${global_image_url}/search.gif" width="16" height="16"
											align="absmiddle" style="cursor: pointer" /></td>
									</tr>
									<tr>
										<td align="right"><span class="important">*</span>Address
										</td>
										<td colspan="3"><input name="contact.addrLine1"
											type="text" value="${contact.addrLine1}" id="address_1"
											size="40" class="NFText" /></td>
										<td>&nbsp;</td>
										<td><input name="contact.addrLine2" type="text"
											id="address_2" value="${contact.addrLine2}" size="40"
											class="NFText" /></td>
										<td>&nbsp;</td>
										<td colspan="2"><input name="contact.addrLine3"
											type="text" value="${contact.addrLine3}" id="address_3"
											size="40" class="NFText" /></td>
									</tr>
									<tr>
										<td height="23" colspan="8"><table width="93%" border="0"
												cellpadding="0" cellspacing="0">
												<tr>
													<td width="5%" align="right"><span class="important">*</span>City</td>
													<td colspan="3"><input name="contact.city"
														value="${contact.city}" type="text" id="city" size="40"
														class="NFText" /></td>
													<td width="8%" align="right"><span class="important">*</span>State</td>
													<td width="14%"><span> <input
															name="contact.state" type="text" class="NFText"
															value="${contact.state}" id="state" size="18" />
													</span></td>
													<td width="9%" align="right"><span class="important">*</span>Zip</td>
													<td width="9%" align="left"><input
														name="contact.zipCode" type="text"
														value="${contact.zipCode}" class="NFText" id="zip"
														size="10" /></td>
													<td width="11%" align="right"><span class="important">*</span>Country
													</td>
													<td width="17%"><select name="contact.country"
														id="country">
															<option value="US">USA</option>
													</select></td>
												</tr>
											</table></td>
									</tr>

									<tr>
										<td valign="top" align="right">Notes</td>
										<td colspan="8"><input name="contact.note" type="text"
											value="${contact.note}" class="NFText" id="note" size="120" />
										</td>
									</tr>
								</table>
						</td>
						<s:iterator value="noteList" id="contactNoteDTO">
							<c:if test="${custNoteDTO.type == 'SALES_NOTES'}">
								<script type="text/javascript">
									$("#topSalesNotesDescription").val(
											"${contactNoteDTO.description}");
								</script>
							</c:if>
							<c:if test="${custNoteDTO.type == 'PRODUCTION'}">
								<script type="text/javascript">
									$("#topPrdInstDescription").val(
											"${contactNoteDTO.description}");
								</script>
							</c:if>
							<c:if test="${custNoteDTO.type == 'SHIPMENT'}">
								<script type="text/javascript">
									$("#topShipInstDescription").val(
											"${contactNoteDTO.description}");
								</script>
							</c:if>
							<c:if test="${custNoteDTO.type == 'ACCOUNTING'}">
								<script type="text/javascript">
									$("#topAccInstDescription").val(
											"${contactNoteDTO.description}");
								</script>
							</c:if>
						</s:iterator>
					</tr>
				</table>
		</div>
		</td>
		</tr>
		</table>
		<!-- summary end -->
		</form>
	</div>


	<div id="dhtmlgoodies_tabView1">
		<!-- tabs start -->
		<!-- general start -->
		<div class="dhtmlgoodies_aTab">
			<form id="contactGeneralForm" class="contactForm">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">

					<tr>
						<td>
							<div class="general_box" style="height: 39px; padding: 8px;">
								<table width="396" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td valign="top">Role</td>
										<td valign="top">Department Function</td>
									</tr>
									<tr>
										<td>
											<div align="center">
												<s:select cssStyle="width:150px" id="role"
													name="contact.jobRoleId"
													list="specDropDownList['CUSTOMER_ROLE'].dropDownDTOs"
													listKey="id" listValue="name" headerKey=""
													headerValue="Select role" value="contact.jobRoleId"></s:select>
											</div>
										</td>
										<td valign="top">
											<div align="center">
												<s:select cssStyle="width:223px" id="dept_func"
													name="contact.deptId"
													list="specDropDownList['DEPARTMENT_FUNCTION'].dropDownDTOs"
													listKey="id" listValue="name" headerKey=""
													headerValue="Select department function"
													value="contact.deptId"></s:select>
											</div>
										</td>
									</tr>
								</table>
							</div>
						</td>

						<td width="385" colspan="2">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
										<div style="height: 39px; padding: 8px;">
											<div align="center">
												Sales Manager <br />
												<s:if test="salesManagerList!=null">
													<s:select name="contact.salesContact" list="salesManagerList"
														listKey="salesId" listValue="resourceName"
														id="salesContact" headerKey=""
														headerValue="Please select..." cssStyle="width:90px">
													</s:select>
												</s:if>
												<s:else>
													<select name="contact.salesContact" id="salesContact"
														disabled="disabled">
														<option value="">Please select...</option>
													</select>
												</s:else>
											</div>
										</div>
									</td>
									<td>
										<div style="height: 39px; padding: 8px;">
											<div align="center">
												Tech Account Manager <br />
												<s:if test="techManagerList!=null">
													<s:select name="contact.techSupport" list="techManagerList"
														listKey="salesId" listValue="resourceName"
														id="techSupport" headerKey=""
														headerValue="Please select..." cssStyle="width:90px">
													</s:select>
												</s:if>
												<s:else>
													<select name="contact.techSupport" id="techSupport"
														disabled="disabled">
														<option value="">Please select...</option>
													</select>
												</s:else>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td valign="top">
							<div class="general_box"
								style="height: 162px; padding-left: 10px;">
								<table height="145" border="0" cellpadding="0" cellspacing="0"
									class="General_table">
									<tr>
										<td><span class="important">*</span>Business Phone</td>
										<td>
											<div align="left">
												<input name="contact.busPhone" id="bussPhone"
													value="${empty contact.busPhone ? '' :  contact.busPhone}"
													type="text" class="NFText" size="12" /> Ext <input
													name="contact.busPhoneExt" id="bussPhoneExt"
													value="${contact.busPhoneExt}" type="text" size="6"
													class="NFText" />
											</div>
										</td>
										<td><input id="morePhoneDialogTrigger" type="button"
											class="style_botton2" value="More Phones" /></td>

									</tr>
									<tr>
										<td align="right">Fax</td>
										<td colspan="2">
											<div align="left">
												<input name="contact.fax" id="fax" value="${contact.fax}"
													type="text" class="NFText" size="12" /> Ext <input
													name="contact.faxExt" type="text" value="${contact.faxExt}"
													size="6" id="faxExt" class="NFText" />
											</div>
										</td>
									</tr>

									<tr>
										<td><span class="important">*</span>Business Email</td>
										<td><input name="contact.busEmail" id="bussEmail"
											value="${contact.busEmail}" type="text" class="NFText"
											size="29" /></td>
										<td><input type="button" id="moreEmailDialogTrigger"
											value="More Emails " class="style_botton2" /></td>
									</tr>
									<tr>

										<td rowspan="2" valign="top">Best Time To Call</td>
										<td colspan="2"><s:select name="callTimeFrom"
												list="#request.callTimeList" listKey="value"
												listValue="value" cssStyle="width:60px;" headerKey=" "
												headerValue="" value="#request.callTimeFrom"></s:select> <s:select
												name="callTimeFromPm" cssStyle="width:59px;"
												list="{'AM', 'PM'}" headerKey=" " headerValue=""
												value="#request.callTimeFromAPM" /> <img
											src="${global_image_url}/range.jpg" /> <s:select
												name="callTimeTo" list="#request.callTimeList"
												listKey="value" listValue="value" cssStyle="width:60px;"
												headerKey=" " headerValue="" value="#request.callTimeTo"></s:select>
											<s:select name="callTimeToPm" cssStyle="width:59px;"
												list="{'AM', 'PM'}" headerKey=" " headerValue=""
												value="#request.callTimeToAPM"></s:select></td>
									</tr>
									<tr>
										<td colspan="2"><s:select cssStyle="width:282px"
												name="contact.bstCallTmzn"
												list="specDropDownList['TIME_ZONE'].dropDownDTOs"
												listKey="id" listValue="name" headerKey=""
												headerValue="Select Time Zone" value="contact.bstCallTmzn"></s:select>
										</td>
									</tr>
									<tr>
										<td align="right">Web</td>
										<td colspan="2"><input name="contact.web" type="text"
											value="${contact.web}" class="NFText" size="50" /></td>
									</tr>
								</table>
							</div>
						</td>
						<td rowspan="2" valign="top">
							<div class="general_box" style="height: 253px;">
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									class="General_table">
									<s:if test="contact != null && contact.donotCode != null">
										<tr>
											<td width="19%"><s:checkbox title="donotCode" name=""
													value="contact.donotCode.substring(0, 1) == 1"></s:checkbox>
											</td>
											<td width="81%">Fraudulent</td>
										</tr>
										<tr>
											<td><s:checkbox title="donotCode" name=""
													value="contact.donotCode.substring(2, 3) == 1"></s:checkbox>
											</td>
											<td>Do Not Mail</td>
										</tr>
										<tr>
											<td><s:checkbox title="donotCode" name=""
													value="contact.donotCode.substring(4, 5) == 1"></s:checkbox>
											</td>
											<td>Do Not Email</td>
										</tr>
										<tr>
											<td><s:checkbox title="donotCode" name=""
													value="contact.donotCode.substring(6, 7) == 1"></s:checkbox>
											</td>
											<td>Do Not Call</td>
										</tr>
										<tr>
											<td><s:checkbox title="donotCode" name=""
													value="contact.donotCode.substring(8, 9) == 1"></s:checkbox>
											</td>
											<td>Do Not Fax</td>
										</tr>
									</s:if>
								</table>
							</div>
						</td>
						<td rowspan="2" valign="top">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
										<div class="general_box" style="height: 42px; padding: 6px;">
											<table width="100%" border="0" cellpadding="0"
												cellspacing="0">
												<tr>
													<td width="33%">Interest Rating</td>
													<td># of Contacts</td>
													<td widtd="33%">&nbsp;</td>
												</tr>
												<tr>
													<td valign="top"><s:select
															name="contact.interestRating"
															list="{1,2,3,4,5,6,7,8,9,10}" cssStyle="width:90px;"
															value="contact.interestRating"></s:select></td>
													<td>
														<div align="center">
															<input id="numOfContacts" value="${contact.contactCount}"
																type="text" class="NFText" size="14" readonly />
														</div>
													</td>
													<td>&nbsp;</td>
												</tr>
											</table>
										</div>
									</td>

								</tr>
								<tr>
									<td>
										<div class="general_box" style="height: 180px; padding: 6px;">
											<table width="100%" height="180" border="0" cellpadding="0"
												cellspacing="0">
												<tr>
													<td colspan="2" valign="bottom">
														<div align="left">Status</div>
													</td>
													<td width="118" valign="bottom">
														<div align="center">Date Created</div>
													</td>

												</tr>
												<tr>
													<td width="118">
														<div align="center">
															<input type="text" name="contact.status"
																id="contactStatus" class="NFText"
																value="${contact.status}" size="14" readonly />
														</div>
													</td>
													<td>
														<!-- update status dialog start, please refer to 'update status dialog' at the bottom -->

														<input type="button" id="updateStatusTrigger"
														class="style_botton2" value="Update" />
													</td>
													<td>
														<div align="center">
															<input id="dateCreated"
																value="<s:date name="contact.creationDate" format="yyyy-MM-dd" />"
																type="text" class="NFText" size="14" readonly />
														</div>
													</td>
												</tr>

												<tr>
													<td colspan="2">Source</td>
													<td>Last Activity</td>
												</tr>
												<tr>
													<td>
														<div align="center">
															<s:select cssStyle="width:110px" name="contact.source"
																id="sourceId"
																list="specDropDownList['ORIGINAL_SOURCE'].dropDownDTOs"
																listKey="id" listValue="name" headerKey=""
																headerValue="Select Source" value="contact.source"></s:select>
														</div>
													</td>
													<td><input type="button" id="viewSourceDialogTrigger"
														class="style_botton2" value="View Detail" /></td>

													<td>
														<div align="center">
															<input id="lastActivity"
																value="<s:date name="contact.lastActivity" format="yyyy-MM-dd" />"
																type="text" class="NFText" size="14" readonly />
														</div>
													</td>
												</tr>

												<tr>
													<td colspan="2">
														<div align="center">
															Modify Date
															<div align="center">
																<input id="modifyDate" type="text"
																	value="<s:date name="contact.modifyDate" format="yyyy-MM-dd" />"
																	class="NFText" size="14" readonly="readonly" />
															</div>
														</div>
													</td>
													<td>
														<div align="center">
															Modify By <input type="hidden" class="NFText"
																value="${contact.modifiedBy}" size="14" /> <input
																id="modifiedBy" name="modifiedBy" size="14"
																class="NFText" type="text" readonly="readonly"
																value="${modifyByUsername}">
														</div>
													</td>
												</tr>
												<tr>
													<td height="40" colspan="3">
														<div align="center">
															<c:if test="${not empty param.contactNo}">
																<input id="contactActvDialogTrigger" type="button"
																	class="style_botton3" value="View Contact Activities" />
															</c:if>

														</div>
													</td>
												</tr>
											</table>
										</div>
									</td>

								</tr>
								<tr>
									<td></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td valign="top">
							<div>
								<div align="center">
									Comments <br />
									<textarea name="contact.comment" id="comments"
										class="content_textarea2" style="width: 368px;">${contact.comment}</textarea>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- general end -->


		<div class="dhtmlgoodies_aTab">
			<!-- organization start -->
			<div style="width: 100%; height: 320px; overflow: auto">
				<form id="contactOrgForm" class="customerForm">

					<table width="950" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<div class="invoice_title" style="padding-top: 0px;">
									<span style="cursor: pointer"
										onclick="toggleShowMore_img2('OrganizaItem', 'Organiza');"><img
										src="images/ad.gif" width="11" height="11" id="OrganizaItem" />Organization</span>
								</div>
								<div id="Organiza" style="display: block;">
									<table width="100%" border="0" cellpadding="0" cellspacing="0"
										class="General_table">
										<tr>
											<td width="21%" align="right"><span class="important">*</span>Organization
											</td>
											<td width="29%"><input name="contact.organization.orgId"
												id="orgId" type="hidden"
												value="${contact.organization.orgId}" /> <input
												name="contact.organization.name" id="orgName"
												value="${contact.organization.name}" type="text"
												class="NFText" size="35" readonly /> <span
												style="cursor: pointer"><img id="org_2Trigger"
													src="images/search.gif" width="16" height="16"
													align="absmiddle" /> <!--       <img id="editTrigger"
										src="images/b_edit.jpg" width="16" height="16"
										align="absmiddle" /> --> </span></td>
											<td width="12%" align="right">Description</td>
											<td><input id="orgDescription"
												name="contact.organization.description" type="text"
												value="${contact.organization.description}" class="NFText"
												size="35" readonly="readonly" /></td>
										</tr>
										<tr>
											<td align="right">Category</td>
											<td><s:select id="categoryId"
													name="contact.organization.categoryId"
													value="contact.organization.categoryId"
													cssStyle="width:250px"
													list="specDropDownList['ORGANIZATION_CATEGORY'].dropDownDTOs"
													listKey="id" listValue="name" headerKey=""
													headerValue="Select Category" disabled="true"></s:select></td>
											<td align="right"><span class="important">*</span>Type</td>
											<td><s:select name="contact.organization.typeId"
													value="contact.organization.typeId" id="orgType"
													list="specDropDownList['ORGANIZATION_TYPE'].dropDownDTOs"
													listKey="id" listValue="name" headerKey=""
													headerValue="Select Type" disabled="true"></s:select></td>
										</tr>

										<tr>
											<td align="right">Language Code</td>
											<td><s:select id="orgLangCode"
													name="contact.organization.langCode"
													list="specDropDownList['LANGUAGE_CODE'].dropDownDTOs"
													listKey="id" listValue="name" headerKey="" headerValue=""
													value="contact.organization.langCode" disabled="true"></s:select>
											</td>
											<td align="right">Web</td>
											<td><input id="orgWeb" name="contact.organization.web"
												type="text" value="${contact.organization.web}"
												class="NFText" size="35" readonly="readonly" /></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td><input type="checkbox"
												name="contact.organization.activeFlag" value="Y"
												${contact.organization.activeFlag== 'Y'? "checked='checked'
												" : ""} id="orgActive" readonly="readonly" /> ACTIVE</td>
											<td>&nbsp;</td>

											<td>&nbsp;</td>
										</tr>
									</table>
								</div>
								<div class="invoice_title">
									<span style="cursor: pointer"
										onclick="toggleShowMore_img2('Organization2Item', 'Organization2');"><img
										src="images/ar.gif" width="11" height="11"
										id="Organization2Item" />Division</span>
								</div>
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									class="General_table" id="Organization2" style="display: none;">
									<tr>
										<td width="21%" align="right">Division</td>
										<td width="29%"><input type="hidden"
											name="contact.division.divisionId" id="divId"
											value="${contact.division.divisionId}" /> <input
											name="contact.division.name" id="divName"
											value="${contact.division.name}" type="text" class="NFText"
											size="35" readonly="readonly" /> <span
											style="cursor: pointer"><img id="divDialogTrigger"
												src="images/search.gif" width="16" height="16"
												align="absmiddle" /> </span></td>
										<td width="12%" align="right">Description</td>
										<td><input name="contact.division.description"
											value="${contact.division.description}" type="text"
											class="NFText" size="35" readonly="readonly" /></td>
									</tr>
									<tr>
										<td align="right">Supervisor</td>
										<td colspan="3"><input name="contact.division.supervisor"
											value="${contact.division.supervisor}" type="text"
											class="NFText" size="35" readonly="readonly" /></td>
									</tr>
									<tr>
										<td align="right">Language Code</td>
										<td colspan="3"><s:select
												name="contact.division.langCode"
												list="specDropDownList['LANGUAGE_CODE'].dropDownDTOs"
												listKey="id" listValue="name" headerKey="" headerValue=""
												value="contact.division.langCode" disabled="true"></s:select>
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td colspan="3"><input type="checkbox"
											name="contact.division.activeFlag" value="Y"
											${contact.division.activeFlag== 'Y'? "checked='checked'
											" : ""} id="divActive" readonly="readonly" /> ACTIVE</td>
									</tr>
								</table>
								<div class="invoice_title">
									<span style="cursor: pointer"
										onclick="toggleShowMore_img2('Organization3Item', 'Organization3');"><img
										src="images/ar.gif" width="11" height="11"
										id="Organization3Item" />Department</span>
								</div>
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									class="General_table" id="Organization3" style="display: none;">
									<tr>
										<td width="21%" align="right">Department</td>
										<td width="29%"><input type="hidden"
											name="contact.department.deptId" id="deptId"
											value="${contact.department.deptId}" /> <input
											name="contact.department.name" id="deptName"
											value="${contact.department.name}" type="text" class="NFText"
											size="35" readonly="yes" /> <span style="cursor: pointer"><img
												id="deptDialogTrigger" src="images/search.gif" width="16"
												height="16" align="absmiddle" /> </span></td>
										<td width="12%" align="right">Description</td>
										<td><input name="contact.department.description"
											value="${contact.department.description}" type="text"
											class="NFText" size="35" readonly="readonly" /></td>
									</tr>
									<tr>
										<td align="right">Department Function</td>
										<td><s:select cssStyle="width:210px"
												name="contact.department.deptFuncId" id="deptDeptFunc"
												list="specDropDownList['DEPARTMENT_FUNCTION'].dropDownDTOs"
												listKey="id" listValue="name" headerKey="" headerValue=""
												value="contact.department.deptFuncId" disabled="true"></s:select>
										</td>
										<td align="right">Supervisor</td>
										<td><input name="contact.department.supervisor"
											type="text" class="NFText" size="35"
											value="${contact.department.supervisor}" readonly="readonly" />
										</td>
									</tr>
									<tr>
										<td align="right">Office</td>
										<td><input name="contact.department.office" type="text"
											class="NFText" size="35" value="${contact.department.office}"
											readonly="readonly" /></td>
										<td align="right">Lab</td>
										<td><input name="contact.department.lab" type="text"
											class="NFText" size="35" value="${contact.department.lab}"
											readonly="readonly" /></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><input type="checkbox"
											name="contact.department.activeFlag" value="Y"
											${contact.department.activeFlag== 'Y'? "checked='checked'
											" : ""} id="deptActive" readonly="readonly" /> ACTIVE</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</table>
								<div class="invoice_title">
									<span style="cursor: pointer"
										onclick="toggleShowMore_img2('Organization4Item', 'Organization4');"><img
										src="images/ar.gif" width="11" height="11"
										id="Organization4Item" />Location</span>
								</div>
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									class="General_table" id="Organization4" style="display: none;">
									<tr>
										<td width="21%" align="right">Location Type</td>
										<td width="29%"><select id="locType"
											style="width: 209px;">
												<option value="orgLocTable">Organization</option>
												<option value="divLocTable">Division</option>
												<option value="deptLocTable">Department</option>
										</select></td>
										<td><span id="sameAsOrgLocDiv" style='display: none'>
												<input type="radio" name="sameas" id="sameAsOrgLoc"
												readonly="readonly" />Same as the organization
										</span> <span id="sameAsDivLocDiv" style='display: none'> <input
												type="radio" name="sameas" id="sameAsDivLoc"
												readonly="readonly" />Same as the division
										</span></td>
									</tr>
									<tr>
										<td colspan="3">
											<!-- organization location start -->
											<table id="orgLocTable" width="100%" border="0"
												cellpadding="0" cellspacing="0" style="margin: 0px"
												class="General_table">
												<tr>
													<td width="21%" align="right">Phone</td>
													<td width="29%">
														<div align="left">
															<input name="contact.organization.phone" id="orgLocPhone"
																type="text" value="${contact.organization.phone}"
																class="NFText" size="17" readonly="readonly" /> Ext <input
																name="contact.organization.phoneExt" id="orgLocPhoneExt"
																value="${contact.organization.phoneExt}" type="text"
																class="NFText" size="6" readonly="readonly" />
														</div>
													</td>
													<td width="12%" align="right">Alt</td>
													<td><div align="left">
															<input name="contact.organization.altPhone"
																id="orgLocAltPhone"
																value="${contact.organization.altPhone}" type="text"
																class="NFText" size="17" readonly="readonly" /> Ext <input
																name="contact.organization.altPhoneExt"
																id="orgLocAltPhoneExt"
																value="${contact.organization.altPhoneExt}" type="text"
																class="NFText" size="6" readonly="readonly" />
														</div></td>
												</tr>
												<tr>
													<td align="right">Fax</td>
													<td><div align="left">
															<input name="contact.organization.fax" id="orgLocFax"
																value="${contact.organization.fax}" type="text"
																class="NFText" size="17" readonly="readonly" /> Ext <input
																name="contact.organization.faxExt" id="orgLocFaxExt"
																value="${contact.organization.faxExt}" type="text"
																size="6" class="NFText" readonly="readonly" />
														</div></td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td rowspan="2" valign="top" align="right">Address</td>

													<td><input name="contact.organization.addrLine1"
														id="orgLocAddr1" value="${contact.organization.addrLine1}"
														type="text" class="NFText" size="35" readonly="readonly" />
													</td>
													<td align="right">City</td>
													<td><input name="contact.organization.city"
														id="orgLocCity" value="${contact.organization.city}"
														type="text" class="NFText" size="35" readonly="readonly" />
													</td>
												</tr>
												<tr>
													<td><input name="contact.organization.addrLine2"
														id="orgLocAddr2" value="${contact.organization.addrLine2}"
														type="text" class="NFText" size="35" readonly="readonly" />
													</td>
													<td align="right">Zip Code</td>
													<td><input name="contact.organization.zipCode"
														id="orgLocZip" value="${contact.organization.zipCode}"
														type="text" class="NFText" size="35" readonly="readonly" />
													</td>

												</tr>
												<tr>
													<td>&nbsp;</td>
													<td><input name="contact.organization.addrLine3"
														id="orgLocAddr3" value="${contact.organization.addrLine3}"
														type="text" class="NFText" size="35" readonly="readonly" />
													</td>
													<td align="right">State</td>
													<td><select name="organization.state" id="orgLocState"
														style="width: 209px;" disabled="disabled">
													</select></td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td align="right">Country</td>
													<td><select name="contact.organization.country"
														id="orgLocCountry" style="width: 209px;"
														disabled="disabled">
															<option value="">United States</option>
													</select></td>
												</tr>
											</table> <!-- organization location end --> <!-- division location start -->
											<table id="divLocTable" style="display: none" width="100%"
												border="0" cellpadding="0" cellspacing="0"
												class="General_table" style="margin:0px">
												<tr>
													<td width="21%" align="right">Phone</td>
													<td width="29%">
														<div align="left">
															<input name="contact.division.phone" id="divLocPhone"
																type="text" value="${contact.division.phone}"
																class="NFText" size="17" /> Ext <input
																name="contact.division.phoneExt" id="divLocPhoneExt"
																value="${contact.division.phoneExt}" type="text"
																class="NFText" size="6" />
														</div>
													</td>
													<td width="12%">Alt</td>
													<td>
														<div align="left">
															<input name="contact.division.altPhone"
																id="divLocAltPhone" value="${contact.division.altPhone}"
																type="text" class="NFText" size="17" /> Ext <input
																name="contact.division.altPhoneExt"
																id="divLocAltPhoneExt"
																value="${contact.division.altPhoneExt}" type="text"
																class="NFText" size="6" />
														</div>
													</td>
												</tr>
												<tr>
													<td>Fax</td>
													<td><div align="left">
															<input name="contact.division.fax" id="divLocFax"
																value="${contact.division.fax}" type="text"
																class="NFText" size="17" /> Ext <input
																name="contact.division.faxExt" id="divLocFaxExt"
																value="${contact.division.faxExt}" type="text" size="6"
																class="NFText" />
														</div></td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td rowspan="2" valign="top">Address</td>
													<td><input name="contact.division.addrLine1"
														id="divLocAddr1" value="${contact.division.addrLine1}"
														type="text" class="NFText" size="35" /></td>
													<td>City</td>
													<td><input name="contact.division.city"
														id="divLocCity" value="${contact.division.city}"
														type="text" class="NFText" size="35" /></td>
												</tr>
												<tr>
													<td><input name="contact.division.addrLine2"
														id="divLocAddr2" value="${contact.division.addrLine2}"
														type="text" class="NFText" size="35" /></td>
													<td>Zip Code</td>
													<td><input name="contact.division.zipCode"
														id="divLocZip" value="${contact.division.zipCode}"
														type="text" class="NFText" size="35" /></td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td><input name="contact.division.addrLine3"
														id="divLocAddr3" value="${contact.division.addrLine3}"
														type="text" class="NFText" size="35" /></td>
													<td>State</td>
													<td><input type="hidden" name="contact.division.state" />
														<input name="divLocState" type="text" class="NFText"
														value="${contact.division.state}" id="divLocState"
														size="18" /></td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>Country</td>
													<td><select name="contact.division.country"
														id="divLocCountry" style="width: 209px;">
															<option value="">United States</option>
													</select></td>
												</tr>
											</table> <!-- division location end --> <!-- department location start -->
											<table id="deptLocTable" style="display: none" width="100%"
												border="0" cellpadding="0" cellspacing="0"
												class="General_table" style="margin:0px">
												<tr>
													<td width="21%">Phone</td>
													<td width="29%">
														<div align="left">
															<input name="contact.department.phone" id="deptLocPhone"
																type="text" class="NFText" size="17"
																value="${contact.department.phone}" /> Ext <input
																name="contact.department.phoneExt" id="deptLocPhoneExt"
																type="text" class="NFText" size="6"
																value="${contact.department.phoneExt}" />
														</div>
													</td>
													<td width="12%">Alt</td>
													<td>
														<div align="left">
															<input name="contact.department.altPhone"
																id="deptLocAltPhone" type="text" class="NFText"
																size="17" value="${contact.department.altPhone}" /> Ext
															<input name="contact.department.altPhoneExt"
																id="deptLocAltPhoneExt" type="text" class="NFText"
																size="6" value="${contact.department.altPhoneExt}" />
														</div>
													</td>
												</tr>
												<tr>
													<td>Fax</td>
													<td>
														<div align="left">
															<input name="contact.department.fax" id="deptLocFax"
																type="text" class="NFText" size="17"
																value="${contact.department.fax}" /> Ext <input
																name="contact.department.faxExt" id="deptLocFaxExt"
																type="text" size="6" class="NFText"
																value="${contact.department.faxExt}" />
														</div>
													</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td rowspan="2" valign="top">Address</td>
													<td><input name="contact.department.addrLine1"
														id="deptLocAddr1" type="text" class="NFText" size="35"
														value="${contact.department.addrLine1}" /></td>
													<td>City</td>
													<td><input name="contact.department.city"
														id="deptLocCity" type="text" class="NFText" size="35"
														value="${contact.department.city}" /></td>
												</tr>
												<tr>
													<td><input name="contact.department.addrLine2"
														id="deptLocAddr2" type="text" class="NFText" size="35"
														value="${contact.department.addrLine2}" /></td>
													<td>Zip Code</td>
													<td><input name="contact.department.zipCode"
														id="deptLocZip" type="text" class="NFText" size="35"
														value="${contact.department.zipCode}" /></td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td><input name="contact.department.addrLine3"
														id="deptLocAddr3" type="text" class="NFText" size="35"
														value="${contact.department.addrLine3}" /></td>
													<td>State</td>
													<td><input type="hidden"
														name="contact.department.state" /> <input
														name="deptLocState" type="text" class="NFText"
														value="${contact.department.state}" id="deptLocState"
														size="18" /></td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>Country</td>
													<td><select name="contact.department.country"
														id="deptLocCountry" style="width: 209px;">
															<option value="">United States</option>
													</select></td>
												</tr>
											</table> <!-- department location end -->
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

				</form>
			</div>
			<!-- organization end -->
		</div>

		<!-- interest start -->
		<div class="dhtmlgoodies_aTab">
			<div style="width: 100%; height: 320px; overflow: auto">
				<form id="contactInterestForm">
					<div class="invoice_title" style="padding-top: 0px;">
						<span style="cursor: pointer"
							onclick="toggle_showmore('DisciplinesItem', 'Disciplines');"><img
							src="images/ad.gif" width="11" height="11" id="DisciplinesItem" />Disciplines
							&amp; Research</span>
					</div>
					<div id="Disciplines" style="display: block;">
						<table width="470" border="0" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<td colspan="2"><s:select name="disciplineInterest"
										id="disciplineInterest"
										list="specDropDownList['DECIPLINE_INTEREST'].dropDownDTOs"
										listKey="id" listValue="name" headerKey="" headerValue=""
										cssStyle="width:250px;"></s:select> <input type="button"
									id="disciplineInterestAddTrigger" class="style_botton"
									value="Add" /> <input type="button"
									id="disciplineInterestDelTrigger" class="style_botton"
									value="Remove" /></td>
							</tr>
						</table>
						<div class="interest">
							<ul id="disciplineInterestContainer">
								<input type="hidden" name="del_dr_interestId"
									id="del_dr_interestId" value="" />
								<!-- discipline interest list here -->
								<s:iterator value="contact.interestList">
									<s:if test="type == 'DR'">
										<li><input value="${areaId}:${name}"
											name="src_decInterest:${interestId}" type="checkbox" />${name}</li>
									</s:if>
								</s:iterator>
							</ul>
						</div>
					</div>
					<div class="invoice_title" style="padding-top: 0px;">
						<span style="cursor: pointer"
							onclick="toggle_showmore('ApplicationsItem', 'Applications');"><img
							src="images/ad.gif" width="11" height="11" id="ApplicationsItem" />Applications
							&amp; Techniques</span>
					</div>
					<div id="Applications" style="display: block;">
						<table width="470" border="0" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<td colspan="2"><s:select name="appInterest_sel"
										id="appInterest_sel"
										list="specDropDownList['APPLICATION_INTEREST'].dropDownDTOs"
										listKey="id" listValue="name" headerKey="" headerValue=""
										cssStyle="width:250px;"></s:select> <input type="button"
									id="appInterestAddTrigger" class="style_botton" value=" Add" />
									<input type="button" id="appInterestDelTrigger"
									class="style_botton" value="Remove" /></td>
							</tr>
						</table>
						<div class="interest">
							<ul id="appInterestContainer">
								<input type="hidden" name="del_at_interestId"
									id="del_at_interestId" value="" />
								<s:iterator value="contact.interestList">
									<s:if test="type == 'AT'">
										<li><input value="${areaId}:${name}"
											name="src_appInterest:${interestId}" type="checkbox" />${name}</li>
									</s:if>
								</s:iterator>
							</ul>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- interest end -->

		<!-- grant start -->
		<div class="dhtmlgoodies_aTab">
			<iframe id="pubIframe" width="100%" height="360" frameborder="0"
				scrolling="no"></iframe>
		</div>
		<!-- grant end -->


		<!-- address start -->
		<div class="dhtmlgoodies_aTab">
			<iframe id="addrIframe" name="addrIframe" width="100%" height="300"
				frameborder="0" scrolling="yes"></iframe>
			<p align="center">
				<input type="button" value=" Add" id="addContactAddrDialogTrigger" />
				<input type="hidden" id="editContactAddrDialogTrigger" /> <input
					type="hidden" id="selectAddrDialogTrigger" />
			</p>
			<div id="addContactAddrDialog" title="Add Address"></div>
			<div id="editContactAddrDialog" title="Edit Address"></div>
			<div id="selectAddrDialog" title="Select Address"></div>
		</div>
		<!-- address end -->

		<!-- Contacts start -->
		<div class="dhtmlgoodies_aTab">
			<iframe id="contactIframe" width="100%" height="340" frameborder="0"
				scrolling="yes"></iframe>
			<input type="hidden" id="addContactCntctDialogTrigger" /> <input
				type="hidden" id="editContactCntctDialogTrigger" />
			<div id="addContactCntctDialog" title="Add Contact"></div>
			<div id="editContactCntctDialog" title="Edit Contact"></div>

		</div>
		<!-- Contacts end -->

		<div class="dhtmlgoodies_aTab">
			<!-- personal info start -->
			<form id="contactPersonalForm" class="contactForm">
				<table width="566" height="250" border="0" cellpadding="0"
					cellspacing="0" class="General_table" style="margin-left: 20px">
					<tr>
						<td width="134" align="right">Gender</td>
						<td width="201"><select name="contact.personal.gender"
							id="contact.personal.gender">
								<s:if test="contact.personal.gender==\"Male\"">
									<option value="Male" selected="selected">Male</option>
									<option value="Female">Female</option>
								</s:if>
								<s:else>
									<option value="Male">Male</option>
									<option value="Female" selected="selected">Female</option>
								</s:else>
						</select></td>

						<td width="103" align="right">Date of Birth</td>
						<td width="128"><input name="birth" id="birth"
							value="<s:date name='contact.personal.birthDate' format='yyyy-MM-dd' />"
							type="text" class="ui-datepicker-birth" style="width: 80px"
							size="20" /></td>
					</tr>
					<tr>
						<td valign="top" align="right">Ethnics/Race</td>
						<td colspan="3" valign="top"><select
							name="contact.personal.race" id="contact.personal.race">
								<s:if test="contact.personal.race==\"African American\"">
									<option value="African American" selected="selected">African
										American</option>
								</s:if>
								<s:else>
									<option value="African American">African American</option>
								</s:else>
								<s:if test="contact.personal.race==\"Caucasian\"">
									<option value="Caucasian" selected="selected">Caucasian</option>
								</s:if>
								<s:else>
									<option value="Caucasian">Caucasian</option>
								</s:else>
								<s:if test="contact.personal.race==\"Chinese\"">
									<option value="Chinese" selected="selected">Chinese</option>
								</s:if>
								<s:else>
									<option value="Chinese">Chinese</option>
								</s:else>
								<s:if test="contact.personal.race==\"Hispanic\"">
									<option value="Hispanic" selected="selected">Hispanic</option>
								</s:if>
								<s:else>
									<option value="Hispanic">Hispanic</option>
								</s:else>
								<s:if test="contact.personal.race==\"Indian\"">
									<option value="Indian" selected="selected">Indian</option>
								</s:if>
								<s:else>
									<option value="Indian">Indian</option>
								</s:else>
								<s:if test="contact.personal.race==\"Japanese\"">
									<option value="Japanese" selected="selected">Japanese</option>
								</s:if>
								<s:else>
									<option value="Japanese">Japanese</option>
								</s:else>
								<s:if test="contact.personal.race==\"Korean\"">
									<option value="Korean" selected="selected">Korean</option>
								</s:if>
								<s:else>
									<option value="Korean">Korean</option>
								</s:else>
								<s:if test="contact.personal.race==\"Other\"">
									<option value="Other" selected="selected">Other</option>
								</s:if>
								<s:else>
									<option value="Other">Other</option>
								</s:else>
								<s:if test="contact.personal.race==\"Unkown\"">
									<option value="Unkown">Unkown</option>
								</s:if>
								<s:else>
									<option value="Unkown">Unkown</option>
								</s:else>
						</select> <label></label></td>
					</tr>
					<tr>
						<td valign="top" align="right">Contact Hobby</td>

						<td valign="top"><input id="ids0" type="checkbox" value="1" />
							Basketball<br /> <input type="checkbox" id="ids1" value="1" />
							Fishing <br /> <input type="checkbox" id="ids2" value="1" />
							Golf<br /> <input type="checkbox" id="ids3" value="1" /> Tennis</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- personal info end -->
	</div>
	<!-- tabs end -->

	<div class="button_box">
		<saveButton:saveBtn parameter="${operation_method}"
			disabledBtn='<input type="submit" id="saveAllTrigger" value="Save" class="search_input" disabled="disabled" />'
			saveBtn='<input type="submit" id="saveAllTrigger" value="Save" class="search_input" onclick="isSaved=true;" />' />
		<input type="button" id="cancelAllTrigger" value="Cancel"
			class="search_input" onclick="history.go(-1);" />
	</div>

	</div>
	<!-- end of input_box class -->

	<!-- org/div/dept picker container start -->
	<div id="orgDialogWindow" title="Organization Lookup"
		style="visible: hidden"></div>
	<div id="divDialogWindow" title="Division Lookup"
		style="visible: hidden"></div>
	<div id="deptDialogWindow" title="Department Lookup"
		style="visible: hidden"></div>
	<!-- org/div/dept picker container end -->

	<!-- more phones start -->
	<div id="morePhoneDialog" title="More Phones">
		<form id="morePhoneDialogForm">
			<table border="0" cellpadding="0" cellspacing="0"
				class="General_table" style="margin: auto;">
				<tr>
					<td align="right">Business Alternative</td>
					<td><input type="text" name="contact.altPhone"
						id="bussAltPhone"
						value="${empty contact.altPhone ? ' - - ' :  contact.altPhone}"
						size="30" class="NFText" /></td>
					<td width="20" align="right">Ext</td>
					<td><input type="text" name="contact.altPhoneExt"
						id="bussAltPhoneExt" value="${contact.altPhoneExt}" class="NFText"
						size="10" /></td>
				</tr>
				<tr>
					<td align="right">Mobile</td>
					<td colspan="3"><input type="text" name="contact.mobile"
						id="bussMobile" value="${contact.mobile}" size="30" class="NFText" />
					</td>
				</tr>
				<tr>
					<td align="right">Mobile Alternate</td>
					<td colspan="3"><input type="text" name="contact.altMobile"
						id="bussMobileAlt" value="${contact.altMobile}" size="30"
						class="NFText" /></td>
				</tr>
				<tr>
					<td align="right">Home</td>
					<td colspan="3"><input type="text" name="contact.homePhone"
						id="bussHomePhone"
						value="${empty contact.homePhone ? ' - - ' :  contact.homePhone}"
						size="30" class="NFText" /></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- more phones end -->

	<!-- more email settings start -->
	<div id="moreEmailDialog" title="More Emails">
		<form id="moreEmailDialogForm">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="General_table" style="margin: auto;">
				<tr>
					<td align="right">Business Email Alternative</td>
					<td><input type="text" class="NFText"
						name="contact.altBusEmail" id="altBusEmail"
						value="${contact.altBusEmail}" size="40" /></td>
				</tr>
				<tr>
					<td align="right">Personal Email</td>
					<td><input type="text" class="NFText"
						name="contact.personalEmail" id="personalEmail"
						value="${contact.personalEmail}" size="40" /></td>
				</tr>
				<tr>
					<td align="right">Personal Email Alternative</td>
					<td><input type="text" class="NFText"
						name="contact.altPersonalEmail" id="altPersonalEmail"
						value="${contact.altPersonalEmail}" size="40" /></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- more email settings end -->
	<!-- update status dialog start -->
	<div id="updateStatusDialog" title="Update Contact Status">
		<table border="0" cellpadding="0" cellspacing="0"
			class="General_table" style="margin-left: 40px;">
			<tr>
				<td height="26">
					<div align="left">
						Status <select id="contactStatus2">
							<option label="ACTIVE" value="ACTIVE" selected="selected">
								ACTIVE</option>
							<option label="INACTIVE" value="INACTIVE">INACTIVE</option>
							<option label="SUSPENDED" value="SUSPENDED">SUSPENDED</option>

						</select>
					</div>
				</td>
			</tr>
			<tr>
				<td height="24" valign="top">
					<div align="left">Choose the reason to update the Customer
						Status:</div>
				</td>
			</tr>
			<tr>
				<td valign="top"><textarea name="contact.statusReason"
						cols="70" rows="2" class="content_textarea"></textarea></td>

			</tr>
		</table>
	</div>
	<!-- update status dialog end -->
	<!-- contact activities dialog container -->
	<div id="contactActvDialog" title="Contact Activities"
		style="visible: hidden"></div>

	<!-- view source dialog -->
	<div id="viewSourceDialog" title="View All Original Sources"
		style="visible: hidden"></div>

	<div id="newPublicationDialog" title="Add New Publication"
		style="visible: hidden"></div>

	<div id="newGrantDialog" title="Add New Grant" style="visible: hidden"></div>

	<div id="editPublicationDialog" title="Edit Publication"
		style="visible: hidden"></div>

	<div id="editGrantDialog" title="Edit Grant" style="visible: hidden"></div>


	<script type="text/javascript">
		initTabs('dhtmlgoodies_tabView1', Array('General', 'Organization',
				'Area of Interest', 'Grants & Pubs', 'Addresses', 'Contacts',
				'Personal Information'), 0, 998, 350);

		// publication and grant
		$('#tabTabdhtmlgoodies_tabView1_3')
				.click(
						function() {
							if ($('#pubIframe').attr('src') == undefined
									|| $('#pubIframe').attr('src') == '') {
								$('#pubIframe')
										.attr('src',
												"contact_pub!index.action?sessContactNo=${sessContactNo}");
							}
						});

		// load iframe for address
		$('#tabTabdhtmlgoodies_tabView1_4')
				.click(
						function() {
							if ($('#addrIframe').attr('src') == undefined
									|| $('#addrIframe').attr('src') == '') {
								$('#addrIframe')
										.attr('src',
												"contact_address!list.action?sessContactNo=${sessContactNo}");
							}
						});

		// load iframe for contact
		$('#tabTabdhtmlgoodies_tabView1_5')
				.click(
						function() {
							if ($('#contactIframe').attr('src') == undefined
									|| $('#contactIframe').attr('src') == '') {
								$('#contactIframe')
										.attr('src',
												"contact_contact!list.action?sessContactNo=${sessContactNo}");
							}
						});

		$('#tabTabdhtmlgoodies_tabView1_${param.defaultTab}').trigger('click');
	</script>

	<script language="javascript">
		var result_frame = parent.document.getElementById('srchCustAct_iframe');
		if (result_frame != null) {
			result_frame.height = 650;
		}
		var orgNameBeforeChange = "GenScript";
		var divNameBeforeChange = "";
		var deptNameBeforeChange = "";
		var orgIdBeforeChange = "2";
		var divIdBeforeChange = "";
		var deptIdBeforeChange = "";

		// country state city default value initialization
		var countryIdNames = [ 'country', 'orgLocCountry', 'divLocCountry',
				'deptLocCountry' ];
		//var countryDefaults = ['${contact.country}','${contact.organization.country}','${contact.division.country}','${contact.department.country}'];
		var countryDefaults = [
				'${contact.country?contact.country:"US"}',
				'${contact.organization.country?contact.organization.country:"US"}',
				'${contact.division.country?contact.division.country:"US"}',
				'${contact.department.country?contact.department.country:"US"}' ];
		var countryChangeHandlers = [ '', '', '', '' ];

		var stateIdNames = [ 'state', 'orgLocState', 'divLocState',
				'deptLocState' ];
		var stateDefaults = [ '${contact.state}',
				'${contact.organization.state}', '${contact.division.state}',
				'${contact.department.state}' ];
		var stateChangeHandlers = [ '', '', '', '' ];

		// 编辑时territory的默认值.
		var defaultSalesTerritory = "${contact.salesTerritory}";
		var defaultSalesGroup = "${contact.salesGroup}";
		var defaultSalesContact = "${contact.salesContact}";
		var defaultTechSupport = "${contact.techSupport}";
		var contactNo = "${contactNo}";
		var sessContactNo = "${sessContactNo}";
	</script>
	<script type="text/javascript"
		src="${global_js_url}scm/gsCountryState.js?v=3"></script>
	<script type="text/javascript" src="${global_js_url}scm/contact.js"></script>
	<script
		src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js"
		type="text/javascript"></script>
	<script src="${global_js_url}jquery/ui/ui.core.js"
		type="text/javascript"></script>
	<script src="${global_js_url}jquery/ui/ui.draggable.js"
		type="text/javascript"></script>
	<script src="${global_js_url}jquery/ui/ui.resizable.js"
		type="text/javascript"></script>
	<script src="${global_js_url}jquery/ui/ui.dialog.js"
		type="text/javascript"></script>
	<script src="${global_js_url}jquery/jquery.validate.js"
		type="text/javascript"></script>

	<script type="text/javascript" src="${global_js_url}scm/contact.js"></script>
	<script type="text/javascript"
		src="${global_js_url}contact/scm_contact.js"></script>
	<script type="text/javascript" src="${global_js_url}util/util.js"></script>
	<script type="text/javascript"
		src="${global_js_url}contact/contact_trigger.js"></script>
	<script type="text/javascript"
		src="${global_js_url}contact/contact_valid.js"></script>
	<script type="text/javascript" src="${global_js_url}scm/orgPicker.js"></script>
	<script type="text/javascript" src="${global_js_url}scm/divPicker.js"></script>
	<script type="text/javascript" src="${global_js_url}scm/deptPicker.js"></script>
	<script src="${global_js_url}jquery/ui/ui.datepicker.js"
		type="text/javascript"></script>
	<script>
		var isSaved = false;
		window.onbeforeunload = function() {
			if (isSaved === false) {
				return 'Do you want to leave without saving data?';
			}
		}
		$().ready(function() {
			$('.ui-datepicker-birth').each(function() {
				$(this).datepicker({
					dateFormat : 'yy-mm-dd',
					changeMonth : true,
					changeYear : true
				});
			});
			$('#birth').datepicker();
			var hobbys = "${contact.personal.hobby}";
			var hobby = hobbys.split(":");
			if (hobby.length == 4) {
				for ( var i = 0; i < hobby.length; i++) {
					if (hobby[i] == 1) {
						document.getElementById("ids" + i).checked = true;
					} else {
						document.getElementById("ids" + i).value = 0;
					}
				}
			}
		});
	</script>

</body>
</html>

