<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<form id="customerTopForm" class="customerForm">
	<!-- summary start -->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<div class="input_box">
					<div class="content_box">
						<div class="invoice_title">
							<img src="images/ad.gif" width="11" height="11"
								onclick="toggleShowMore_img(this, 'Customer_Info');"
								id="Customer_InfoItem" style="cursor: pointer;" />&nbsp;General
							Information
						</div>

						<div id="Customer_Info" style="display: block;">
							<table border="0" cellpadding="0" cellspacing="0"
								class="Customer_table">
								<tr>
									<td align="right">Customer No</td>
									<td colspan="3"><input type="hidden" id="custNo" name=""
										value="${customerDetail.custNo}" /> <input type="hidden"
										id="custInt" name="" value="${customerDetail.custNo}" /> <input
										name="" id="custNoDisplay" value="${customerDetail.custNo}"
										type="text" class="NFText3" size="40" readonly="readonly" />
									</td>
									<td align="right">Alternate No</td>
									<td colspan="4"><input name="altNo" id="altNo"
										value="${customerDetail.altNo}" type="text" size="40"
										class="NFText" />
									</td>
								</tr>
								<tr>
									<td align="right">Name</td>
									<td>
										<div class="select">
											<div>
												<input type="hidden" name="sessCustNo" id="sessCustNo"
													value="${sessCustNo}" />
												<s:select id="namePfx" name="namePfx" list="namePfxList"
													listKey="value" listValue="value" cssStyle="width:75px;"
													headerKey="" headerValue="" value="customerDetail.namePfx"></s:select>
											</div>
										</div></td>
									<th width="36"><span class="important">*</span>First</th>
									<td align="left"><input name="firstName" type="text"
										id="firstName" value="${customerDetail.firstName}"
										class="NFText" style="width: 95px" />
									</td>
									<td align="right">Middle</td>
									<td><input name="midName" type="text" id="midName"
										value="${customerDetail.midName}" size="40" class="NFText" />
									</td>
									<td align="right"><span class="important">*</span>Last</td>
									<td><input name="lastName" type="text"
										value="${customerDetail.lastName}" id="lastName" size="20"
										class="NFText" /> <s:select id="nameSfx" name="nameSfx"
											headerKey="" headerValue="" list="nameSfxList"
											listKey="value" listValue="value" cssStyle="width:93px;"
											value="customerDetail.nameSfx"></s:select>
									</td>
								</tr>
								<tr>
									<td align="right">Title</td>
									<td colspan="3"><input name="title" type="text" id="title"
										value="${customerDetail.title}" size="40" class="NFText" />
									</td>
									<td align="right">Supervisor</td>
									<td><input type="text" name="supervisor" size="40"
										class="NFText" value="${customerDetail.supervisor}" /></td>
									<td align="right"><span class="important">*</span>Organization</td>
									<td colspan="">
									
									<input name="_tmp_orgName" type="text"
										id="orgName1" value="${customerDetail.organization.name}"
										size="40" class="NFText" readonly="readonly" /> <img
										id="org_1Trigger" src="images/search.gif" width="16"
										height="16" align="absmiddle" />
									</td>
								</tr>

								<tr>
									<td align="right"><span class="important">*</span>Address</td>
									<td colspan="3">
									
									<input name="addrLine1" type="text"
										value="${customerDetail.addrLine1} " id="address_1" size="40"
										class="NFText" /></td>
									<td>&nbsp;</td>
									<td><input name="addrLine2" type="text" id="address_2"
										value="${customerDetail.addrLine2}" size="40" class="NFText" />
									</td>
									<td>&nbsp;</td>
									<td colspan="2"><input name="addrLine3" type="text"
										value="${customerDetail.addrLine3}" id="address_3" size="40"
										class="NFText" /></td>
								</tr>

								<tr>
									<td align="right"><span class="important">*</span>City</td>
									<td colspan="3"><input name="city"
										value="${customerDetail.city}" type="text" id="city" size="40"
										class="NFText" /></td>
									<td align="right"><span class="important">*</span>State</td>
									<td align="right">
										<div align="left">
											<span><input name="state" type="text" class="NFText"
												value="${customerDetail.state}" id="state" size="18" /> </span> <span
												class="important">*</span>Zip&nbsp; <input name="zipCode"
												type="text" value="${customerDetail.zipCode}" class="NFText"
												id="zip" size="7" />

										</div></td>
									<td align="right"><span class="important">*</span>Country</td>
									<td colspan="2"><select name="country" id="country"
										onchange="docheng()">
											<option value="US">USA</option>
									</select></td>
								</tr>
								<tr>
									<td align="right" valign="top">Notes</td>
									<td colspan="8"><input name="note" type="text"
										value="${customerDetail.note}" class="NFText" id="note"
										size="120" /> <input name="custType" type="hidden"
										value="${customerDetail.custType}" />
									</td>
								</tr>
								<tr>
									<td colspan="10" style="padding: 0px;">
										<table border="0" cellpadding="0" cellspacing="0"
											class="Customer_table" style="margin: 0px;">
											<tr>
												<th width="62">Sales Notes</th>
												<td width="365"><input id="topSalesNotesDescription"
													type="text" readonly="readonly"
													value="${topSalesNotesDescription}" size="65"
													class="NFText3" style="width: 320px;" /></td>
												<th width="148">Production Notes</th>
												<td><input id="topPrdInstDescription" type="text"
													readonly="readonly" value="${topPrdInstDescription}"
													size="65" class="NFText3" style="width: 316px;" />
												</td>
											</tr>
										</table></td>
								</tr>
								<tr>
									<td colspan="10" style="padding: 0px;">
										<table border="0" cellpadding="0" cellspacing="0"
											class="Customer_table" style="margin: 0px;">
											<tr>
												<th width="62">Ship Notes</th>
												<td width="365"><input id="topShipInstDescription"
													type="text" readonly="readonly"
													value="${topShipInstDescription}" size="65" class="NFText3"
													style="width: 320px;" /></td>
												<th width="148">Accounting Notes</th>
												<td><input id="topAccInstDescription" type="text"
													readonly="readonly" value="${topAccInstDescription}"
													size="65" class="NFText3" style="width: 316px;" />
												</td>
											</tr>
										</table></td>
									<s:iterator value="noteList" id="custNoteDTO">
										<c:if test="${custNoteDTO.type == 'SALES'}">
											<script type="text/javascript">
												$("#topSalesNotesDescription")
														.val(
																"${custNoteDTO.description}");
											</script>
										</c:if>
										<c:if test="${custNoteDTO.type == 'PRODUCTION'}">
											<script type="text/javascript">
												$("#topPrdInstDescription")
														.val(
																"${custNoteDTO.description}");
											</script>
										</c:if>
										<c:if test="${custNoteDTO.type == 'SHIPMENT'}">
											<script type="text/javascript">
												$("#topShipInstDescription")
														.val(
																"${custNoteDTO.description}");
											</script>
										</c:if>
										<c:if test="${custNoteDTO.type == 'ACCOUNTING'}">
											<script type="text/javascript">
												$("#topAccInstDescription")
														.val(
																"${custNoteDTO.description}");
											</script>
										</c:if>
									</s:iterator>
								</tr>
								<tr>
									<td colspan="10" align="center">&nbsp;</td>
								</tr>

							</table>
						</div>
			</td>
		</tr>
	</table>

	<!-- summary end -->
</form>
</div>
<script type="text/javascript">
	function docheng() {
		var county = $("#country").val();
		
	}
</script>