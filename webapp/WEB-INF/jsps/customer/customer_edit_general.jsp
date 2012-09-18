<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<style type="text/css">
.tab td {
	margin: 0px;
	padding: 0px;
	color: #333333;
	align: "right";
	font: 11px/18px Arial, Helvetica, sans-serif;
}
</style> 
<form id="customerGeneralForm" class="customerForm">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<div class="general_box" style="height: 78px; padding: 2px;">
					<table width="396" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="79" height="28" align="right">Priority Level</td>
							<td width="112"><s:select id="priorityLvl"
									name="priorityLvl" list="dropDownList['CUST_PRIORITY_LEVEL']"
									listKey="value" listValue="value" headerKey=""
									headerValue="Please select" cssStyle="width:100px"
									value="customerDetail.priorityLvl"></s:select>
							</td>
							<td width="30" align="right">Role</td>
							<td width="175"><s:select name="custRoleId" id="role"
									cssStyle="width:171px;"
									list="specDropDownList['CUSTOMER_ROLE'].dropDownDTOs"
									listKey="id" listValue="name" headerKey=""
									headerValue="Select Role" value="customerDetail.custRoleId"></s:select>
							</td>
						</tr>
						<tr>
							<td width="10" height="28px;">&nbsp;</td>
							<td colspan="1" align="right">
								Department Function
							</td>
							<td colspan="2" width="210px">
									<s:if test='customerDetail.department== null'>
										<s:select id="dept_func" cssStyle="width:200px;"
											list="specDropDownList['DEPARTMENT_FUNCTION'].dropDownDTOs"
											listKey="id" listValue="name" headerKey=""
											headerValue="Select Department Function"
											value="customerDetail.department.deptFuncId" disabled="true"></s:select>
									</s:if>
									<s:else>
										<s:select id="dept_func" cssStyle="width:200px;"
											list="specDropDownList['DEPARTMENT_FUNCTION'].dropDownDTOs"
											listKey="id" listValue="name" headerKey=""
											headerValue="Select Department Function"
											value="customerDetail.department.deptFuncId"></s:select>
									</s:else>

							</td>
						</tr>
					</table>
				</div>
			</td>
			<td width="544" colspan="3">
				<div class="general_box"
					style="height: 78px; padding: 2px; width: 534px;">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<div align="center">
									<span class="important">*</span>Sales Manager<br />
									<s:if test="salesManagerList!=null">
										<s:select name="salesContact" list="salesManagerList"
											listKey="salesId" listValue="resourceName" id="salesContact"
											headerKey="" headerValue="Please select..."
											cssStyle="width:90px">
										</s:select> 
									</s:if>
									<s:else>
										<select name="salesContact" id="salesContact">
											<option value="">Please select...</option>
										</select> 
									</s:else>
								</div>
							</td>
							<td>
								<div align="center">
									<span class="important">*</span>Tech Account Manager <br />
									<s:if test="techManagerList!=null">
										<s:select name="techSupport" list="techManagerList"
											listKey="salesId" listValue="resourceName" id="techSupport"
											headerKey="" headerValue="Please select..."
											cssStyle="width:90px">
										</s:select> 
									</s:if>
									<s:else>
										<select name="techSupport" id="techSupport">
											<option value="">Please select...</option>
										</select> 
									</s:else>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td valign="top">
				<div class="general_box" style="height: 162px; padding-left: 10px;">
					<table height="145" border="0" cellpadding="0" cellspacing="0"
						class="General_table">
						<tr>
							<td align="right"><span class="important">*</span>Business
								Phone</td>
							<td>
								<div align="left">
									<input name="busPhone" id="bussPhone"
										value="${customerDetail.busPhone}" type="text" class="NFText"
										size="12" /> Ext <input name="busPhoneExt" id="bussPhoneExt"
										value="${customerDetail.busPhoneExt}" type="text" size="6"
										class="NFText" />
								</div>
							</td>
							<td><input id="morePhoneDialogTrigger" type="button"
								class="style_botton2" value="More Phones" />
							</td>
						</tr>
						<tr>
							<td align="right">Fax</td>
							<td colspan="2">
								<div align="left">
									<input name="fax" id="fax" value="${customerDetail.fax}"
										type="text" class="NFText" size="12" /> Ext <input
										name="faxExt" type="text" value="${customerDetail.faxExt}"
										size="6" id="faxExt" class="NFText" />
								</div>
							</td>
						</tr>

						<tr>
							<td align="right"><span class="important">*</span>Business	Email</td>
							<td><input name="busEmail" id="bussEmail"
								value="${customerDetail.busEmail}" type="text" class="NFText"
								size="29" /></td>
							<td><input type="button" id="moreEmailDialogTrigger"
								value="More Emails " class="style_botton2" />
							</td>
						</tr>
						<tr>
							<td rowspan="2" valign="top" align="right">Best Time To Call</td>
							<td colspan="2"><s:select id="callTimeFrom"
									_name="callTimeFrom" name="" list="callTimeList"
									listKey="value" listValue="value" cssStyle="width:60px;"
									headerKey="" headerValue="" value="#request.callTimeFrom"></s:select>
								<s:select id="callTimeFromPm" _name="callTimeFromPm" name=""
									list="amPmList" listKey="value" listValue="value"
									cssStyle="width:59px;" value="#request.callTimeFromAPM"></s:select>
								<img src="images/range.jpg" /> <s:select id="callTimeTo"
									_name="callTimeTo" name="" list="callTimeList" listKey="value"
									listValue="value" cssStyle="width:60px;" headerKey=""
									headerValue="" value="#request.callTimeTo"></s:select> <s:select
									id="callTimeToPm" _name="callTimeToPm" name="" list="amPmList"
									listKey="value" listValue="value" cssStyle="width:59px;"
									value="#request.callTimeToAPM"></s:select>
							</td>
						</tr>

						<tr>
							<td colspan="2"><s:select cssStyle="width:282px"
									name="bstCallTmzn"
									list="specDropDownList['TIME_ZONE'].dropDownDTOs" listKey="id"
									listValue="name" headerKey="" headerValue="Select Time Zone"
									value="customerDetail.bstCallTmzn"></s:select>
							</td>
						</tr>
						<tr>
							<td align="right">Web</td>
							<td colspan="2"><input name="web" type="text"
								value="${customerDetail.web}" class="NFText" size="50" />
							</td>
						</tr>
					</table>
				</div>
			</td>

			<td rowspan="3" valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td colspan="2">
							<div class="general_box" style="height: 78px; padding: 6px;">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td align="center">Purchasing Capability</td>
										<td align="center">Last Order</td>
										<td align="center">Last Quote</td>
										<td align="center"># of Contacts</td>
									</tr>
									<tr>
										<td valign="top">
											<div align="center">
												<input name="purchasingCpbl" id="purchasingCpbl"
													value="${customerDetail.purchasingCpbl}" type="text"
													class="NFText" value="" size="14" />
											</div>
										</td>
										<td align="center">
											<div align="center">
												<input id="last_order" type="text" class="NFText"
													value="${customerDetail.custInfoStat.lastOrder}" size="14"
													readonly="readonly" />
											</div>
										</td>
										<td>
											<div align="center">
												<input id="last_quote" type="text" class="NFText"
													value="${customerDetail.custInfoStat.lastQuote}" size="14"
													readonly="readonly" />
											</div>
										</td>
										<td>
											<div align="center">
												<input id="cntctnum" type="text" class="NFText"
													value="${customerDetail.custInfoStat.contactCount}"
													size="14" readonly="readonly" />
											</div>
										</td>
									</tr>
									<tr>
										<td align="center">RFM Rating</td>
										<td align="center"># of Orders</td>
										<td align="center"># of Quotes</td>
										<td></td>
									</tr>
									<tr>
										<td>
											<div align="center">
												<s:hidden id="rfm_rt" name="rfmRatingId"></s:hidden>
												<input type="text" class="NFText"
													value="${customerDetail.rfmRatingCd}" size="14"
													readonly="readonly" />
											</div>
										</td>
										<td height="27">
											<div align="center">
												<input id="ordernum" type="text" class="NFText"
													value="${customerDetail.custInfoStat.orderCount}" size="14"
													readonly="readonly" />
											</div>
										</td>
										<td>
											<div align="center">
												<input id="quotenum" type="text" class="NFText"
													value="${customerDetail.custInfoStat.quoteCount}" size="14"
													readonly="readonly" />
											</div>
										</td>
										<td></td>
									</tr>
								</table>
							</div>
						</td>

					</tr>

					<tr>
						<td>
							<div class="general_box"
								style="height: 110px; padding-top: 10px;">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<s:if
										test="customerDetail != null && customerDetail.donotCode != null">
										<tr>
											<td width="19%"><s:checkbox title="donotCode" name=""
													value='customerDetail.donotCode.substring(0, 1) == "1"'></s:checkbox>
											</td>
											<td width="81%">Fraudulent</td>
										</tr>
										<tr>
											<td><s:checkbox title="donotCode" name=""
													value='customerDetail.donotCode.substring(2, 3) == "1"'></s:checkbox>
											</td>
											<td>Do Not Mail</td>
										</tr>
										<tr>
											<td><s:checkbox title="donotCode" name=""
													value='customerDetail.donotCode.substring(4, 5) == "1"'></s:checkbox>
											</td>
											<td>Do Not Email</td>
										</tr>
										<tr>
											<td><s:checkbox title="donotCode" name=""
													value='customerDetail.donotCode.substring(6, 7) == "1"'></s:checkbox>
											</td>
											<td>Do Not Call</td>
										</tr>
										<tr>
											<td><s:checkbox title="donotCode" name=""
													value='customerDetail.donotCode.substring(8, 9) == "1"'></s:checkbox>
											</td>
											<td>Do Not Fax</td>
										</tr>
									</s:if>
								</table>
							</div>
							<div class="general_box"
								style="height: 21px; padding-right: 0px;">
								<table class="" width="100%" border="0" cellpadding="0"
									cellspacing="0">
									<tbody>
										<tr>
											<td><s:checkbox name="greenAccFlag"
													value='customerDetail.greenAccFlag == "Y"' fieldValue="Y"></s:checkbox>
											</td>
											<td>Green Account</td>
										</tr>
									</tbody>
								</table>
							</div>
						</td>

						<td>
							<div class="general_box" style="height: 150px; padding: 2px;">
								<table width="100%" height="125" border="0" cellpadding="0"
									cellspacing="0">
									<tr>
										<td colspan="2" valign="bottom">
											<div align="left" style="margin-left: 35px;">Status</div>
										</td>
										<td width="118" valign="bottom">
											<div align="center">Date Created</div>
										</td>
									</tr>
									<tr>
										<td width="118">
											<div align="center">
												<s:if
													test='customerDetail == null or customerDetail.status == "" or customerDetail.status == null'>
													<input type="text" name="status" id="customerStatus"
														class="NFText" value="ACTIVE" size="14"
														readonly="readonly" />
												</s:if>
												<s:else>
													<input type="text" name="status" id="customerStatus"
														class="NFText" value="${customerDetail.status}" size="14"
														readonly="readonly" />
												</s:else>
											</div>
										</td>
										<td>
											<!-- update status dialog start, Please refer to 'update status dialog' section -->
											<input type="button" id="updateStatusTrigger"
											class="style_botton2" value="Update" />
										</td>
										<td>
											<div align="center">
												<input id="dateCreated"
													value='<s:date name="customerDetail.creationDate" format="yyyy-MM-dd" />'
													type="text" class="NFText" size="14" readonly="readonly" />
											</div>
										</td>
									</tr>

									<tr>
										<td colspan="2">
											<div align="left" style="margin-left: 35px;">Source</div>
										</td>
										<td align="center">Last Activity</td>
									</tr>
									<tr>
										<td>
											<div align="center">
												<s:select cssStyle="width:110px" name="source"
													list="specDropDownList['ORIGINAL_SOURCE'].dropDownDTOs"
													listKey="id" listValue="name" headerKey=""
													headerValue="Select Source" value="customerDetail.source"
													id="sourceId"></s:select>
											</div>
										</td>
										<td><input type="button" id="viewSourceDialogTrigger"
											class="style_botton2" value="View Detail" />
										</td>
										<td>
											<div align="center">
												<input id="lastActivity"
													value='<s:date name="customerDetail.lastActivity" format="yyyy-MM-dd" />'
													type="text" class="NFText" size="14" readonly="readonly" />
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div align="center">Modify Date</div></td>
										<td><div align="center">Modify By</div></td>
										<td>
									</tr>
									<tr>
										<td><input id="modifyDate" type="text"
											value="<s:date name="customerDetail.modifyDate" format="yyyy-MM-dd" />"
											class="NFText" size="14" readonly="readonly" /></td>
										<td><div align="center"><%-- <input type="hidden" class="NFText" name="modifiedBy" id="modifiedBy" 
											value="${customerDetail.modifiedBy}" size="14"  /> --%>
											<input  size="14" class="NFText" type="text" readonly="readonly" value="${modifyByUsername}">
											</div>
										</td>
									</tr>
									<tr>
										<td height="40" colspan="3">
											<div align="center">
											<!-- <a href="customer!openaccount.action" target="_blank">  --><input id="customerAccountHomeTrigger" type="button"
													class="style_botton3" value="Customer's Account Home" /> <!-- </a> -->
													&nbsp;&nbsp;&nbsp;&nbsp;
											<input id="customerActvDialogTrigger" type="button"
													class="style_botton3" value="View Customer's Activities" />
											</div>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td valign="top">
				<div>
					<div align="center">
						Comments<br />
						<textarea name="comment" id="comments" class="content_textarea2"
							style="width: 368px;">${customerDetail.comment}</textarea>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<table width="97%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="40%" align="right">Instructions & Notes</td>
						<td width="45%"><select id="instruction_slt"
							style="width: 138px;">
								<option value="">Add Instruction or Notes</option>
								<s:if test="noteList != null && noteList.size > 0">
									<s:iterator value="noteList" id="oneNote">
										<%--#${oneNote.name}--%>
										<option selected="selected" value="${oneNote.id}">${oneNote.type}-instr-account</option>
									</s:iterator>
								</s:if>
						</select>

							<div style="display: none;" id="noteHideFileList"></div>
						</td>
						<td><s:if test="noteList != null && noteList.size > 0">
								<input type="button" id="instruction_btn" class="style_botton2"
									value="View/Edit"
									onclick="javascript:$('#accountingInstructionDialog').dialog('open');" />
							</s:if> <s:else>
								<input type="button" id="instruction_btn" class="style_botton2"
									value="  Add  "
									onclick="javascript:$('#accountingInstructionDialog').dialog('open');" />
							</s:else>
						</td>
					</tr>
					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>
<br />