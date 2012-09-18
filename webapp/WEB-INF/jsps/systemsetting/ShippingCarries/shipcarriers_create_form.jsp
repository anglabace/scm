<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="greybox/gb_styles.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
	type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}TabbedPanels.js"></script>

<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<link href="${global_css_url}tab-view.css" rel="stylesheet"
	type="text/css" />
<link type="text/css"
	href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script language="javascript" type="text/javascript"
	src="${global_js_url}show_tag.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}select.js"></script>
<script type="text/javascript" src="${global_js_url}scm/config.js"></script>
<script type="text/javascript" src="${global_js_url}util/util.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}scm/gs.util.js"></script>
<script src="${global_js_url}recordTime.js" type="text/javascript"></script>
<script src="${global_js_url}initlefttop.js" type="text/javascript"></script>
<script
	src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js"
	type="text/javascript"></script>
<link type="text/css"
	href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script src="${global_js_url}jquery/ui/ui.core.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js"
	type="text/javascript"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.form.js"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js"
	type="text/javascript"></script>

<script src="${global_js_url}scm/gsCountryState.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${global_js_url}scm/setting_shipping_carrieres.js"></script>
<script language="javascript" type="text/javascript">

var countryIdNames = ['country'];
var countryDefaults = ['${customerDetail.country != null?customerDetail.country:"US"}'];
var countryChangeHandlers = [''];
var stateIdNames = ['state'];
var stateDefaults = ['${shipCarriers.state}'];
var stateChangeHandlers = ['']; 
$(document).ready(function(){  
    $('tr:odd >td').addClass('list_td2');
    $('.ui-datepicker').each(function(){
		$(this).datepicker(
				{
					dateFormat: 'yy-mm-dd',
					changeMonth: true,
					changeYear: true
				});
	});
    initCountry();
});
</script>
<script language="JavaScript" type="text/javascript">
        function checkSave() {
            if ($('#savedFlag').val() != 'Y') {
                return 'Unsaved data will be losted, are you sure to continue?';
            }
        }
        window.onbeforeunload = checkSave;
    </script>
</head>
<body class="content">

	<div class="scm">
		<div class="title_content">
			<div class="title">
				<s:if test="opType == 'add'">New Shipping Carrier </s:if>
				<s:else>Edit - ${shipCarriers.name}-(${shipCarriers.carrierCode})</s:else>
			</div>
		</div>
		<div class="input_box">
			<form id="carrierMainForm">
				<input type="hidden" id="carrierId" name="shipCarriers.carrierId"
					value="<s:if test="opType == 'add'"></s:if><s:else>${shipCarriers.carrierId}</s:else>" />
				<input type="hidden" id="opType" name="opType" value="${opType}" />
				<input type="hidden" id="savedFlag" />
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="General_table">
					<tr>
						<th width="140">Shipping Carrier Code</th>
						<td width="424"><input id="carrierCode"
							name="shipCarriers.carrierCode" type="text" class="NFText"
							value="${shipCarriers.carrierCode}" size="20"
							<s:if test="opType == 'edit'">readonly</s:if> />
						</td>
						<th width="92">Name</th>
						<td width="346"><input id="name" name="shipCarriers.name"
							type="text" class="NFText" value="${shipCarriers.name}" size="20" />
						</td>
					</tr>
					<tr>
						<th valign="top">Description</th>
						<td><textarea name="shipCarriers.description"
								class="content_textarea2">${shipCarriers.description}</textarea>
						</td>
						<th>Status</th>
						<td valign="top"><s:select cssStyle="width:131px"
								name="shipCarriers.status" list="statusMap" listKey="key"
								listValue="value" value="shipCarriers.status"></s:select></td>
					</tr>
					<tr>
						<th>Modified Date</th>
						<td><input name="shipCarriers.modifyDate" type="text"
							class="NFText"
							value="<s:date name="shipCarriers.modifyDate" format="yyyy-MM-dd"/>"
							size="20" readonly="readonly" />
						</td>

						<th>Modified By</th>
						<td><input name=" modifieddBy" type="text"
							class="NFText" value="${modifyByUsername}" size="20"
							readonly="readonly" />
							<input name="shipCarriers.modifiedBy" type="hidden"
							class="NFText" value="${shipCarriers.modifiedBy}" size="20"
							readonly="readonly" />
						</td>
					</tr>
				</table>
			</form>
		</div>

		<div id="dhtmlgoodies_tabView1">
			<div class="dhtmlgoodies_aTab">
				<div class="general_box" style="height: 390px;">
					<form id="generalForm">
						<table border="0" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<th width="205">&nbsp;</th>
								<td>&nbsp;</td>
								<th width="223">&nbsp;</th>
								<td width="223">&nbsp;</td>
							</tr>
							<tr>
								<th>Rate Effective Date</th>
								<td valign="top"><input name="shipCarriers.rateDate"
									value="<s:date name="shipCarriers.rateDate" format="yyyy-MM-dd"/>"
									type="text" class="NFText ui-datepicker" size="19"
									readonly="readonly" id="rateDate" /></td>
								<th>&nbsp;</th>
								<td valign="top">&nbsp;</td>
							</tr>
							<tr>
								<th valign="top">Insurance(Per $100)</th>
								<td valign="top"><input name="shipCarriers.insurance"
									type="text" class="NFText" id="insurance"
									value="${shipCarriers.insurance}" size="32" /></td>
								<th>Additional Handling</th>
								<td valign="top"><input
									name="shipCarriers.additionalHandling" type="text"
									class="NFText" id="additionalHandling"
									value="${shipCarriers.additionalHandling}" size="32" /></td>
							</tr>
							<tr>
								<th>Hazardous Material</th>
								<td valign="top"><input
									name="shipCarriers.hazardousMaterial" type="text"
									class="NFText" id="hazardousMaterial"
									value="${shipCarriers.hazardousMaterial}" size="32" /></td>
								<th>Large Package Surcharge</th>
								<td valign="top"><input
									name="shipCarriers.packageSurcharge" type="text" class="NFText"
									id="packageSurcharge" value="${shipCarriers.packageSurcharge}"
									size="32" /></td>
							</tr>
							<tr>
								<th>Residential Delivery Surcharge</th>
								<td><input name="shipCarriers.residentialSurcharge"
									type="text" class="NFText" id="residentialSurcharge"
									value="${shipCarriers.residentialSurcharge}" size="32" /></td>
								<th>Commercial Delivery Surcharge</th>
								<td><input name="shipCarriers.commercialSurcharge"
									type="text" class="NFText" size="32" id="commercialSurcharge"
									value="${shipCarriers.commercialSurcharge }" /></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="dhtmlgoodies_aTab">
				<div class="general_box" style="height: 390px;">
					<form id="internationalForm">
						<table border="0" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<th width="205">&nbsp;</th>
								<td colspan="2">&nbsp;</td>
								<th width="223">&nbsp;</th>
								<td width="223">&nbsp;</td>
							</tr>

							<tr>
								<th valign="top">Insurance(Per $100)</th>
								<td colspan="2" valign="top"><input
									name="shipCarriers.intlInsurance" type="text" class="NFText"
									id="intlInsurance" value="${shipCarriers.intlInsurance }"
									size="32" /></td>
								<th>Additional Handling</th>
								<td valign="top"><input
									name="shipCarriers.intlAdditionalHandling" type="text"
									class="NFText" value="${shipCarriers.intlAdditionalHandling}"
									size="32" id="intlAdditionalHandling" /></td>
							</tr>

							<tr>
								<th>Hazardous Material</th>
								<td colspan="2" valign="top"><input
									name="shipCarriers.intlHazardousMaterial" type="text"
									class="NFText" id="intlHazardousMaterial"
									value="${shipCarriers.intlHazardousMaterial}" size="32" />
								</td>
								<th>Large Package Surcharge</th>
								<td valign="top"><input
									name="shipCarriers.intlPackageSurcharge" type="text"
									class="NFText" id="intlPackageSurcharge"
									value="${shipCarriers.intlPackageSurcharge }" size="32" /></td>
							</tr>
							<tr>

								<th>Residential Delivery Surcharge</th>
								<td colspan="2"><input
									name="shipCarriers.intlResidentialSurcharge" type="text"
									class="NFText" id="intlResidentialSurcharge"
									value="${shipCarriers.intlResidentialSurcharge }" size="32" />
								</td>
								<th>Commercial Delivery Surcharge</th>
								<td><input name="shipCarriers.intlCommercialSurcharge"
									type="text" class="NFText" size="32"
									id="intlCommercialSurcharge"
									value="${shipCarriers.intlCommercialSurcharge }" /></td>
							</tr>
							<tr>
								<th>&nbsp;</th>
								<td><input type="checkbox" name="intlFlag1" id="intlFlag1"
									value="1" ${intlFlag1==1? "checked='checked'
									":""}
									 readonly="readonly" /></td>
								<td width="150">Certificate of Origin Required</td>
								<th>&nbsp;</th>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<th>&nbsp;</th>
								<td><input type="checkbox" name="intlFlag2" id="intlFlag2"
									value="1" ${intlFlag2==1? "checked='checked'
									":""}
									 readonly="readonly" /></td>
								<td>Commercial Invoice Required</td>
								<th>&nbsp;</th>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<th>&nbsp;</th>
								<td><input type="checkbox" name="intlFlag3" id="intlFlag3"
									value="1" value="1" ${intlFlag3==1? "checked='checked'
									":""}
									 readonly="readonly" /></td>
								<td>Export Declaration Required</td>
								<th>&nbsp;</th>
								<td>&nbsp;</td>
							</tr>
						</table>
					</form>
				</div>
			</div>

			<div class="dhtmlgoodies_aTab">
				<div class="general_box" style="height: 390px;">
					<form id="billinginfoForm">
					<input type="hidden" name="billid" id="billid" value="${billid }" />
						<table border="0" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<th width="205">&nbsp;</th>
								<td colspan="3">&nbsp;</td>
								<th width="223">&nbsp;</th>
								<td width="223">&nbsp;</td>
							</tr>
							<tr>
								<th>Billing Type</th>
								<td colspan="3" valign="top"><select id="billType"
									style="width: 155px;" name="shipCarriers.billType">
										<option value="billType1"
											<s:if test="shipCarriers.billType=='billType1'">selected="true"</s:if>>billType1</option>
										<option value="billType2"
											<s:if test="shipCarriers.billType=='billType2'">selected="true"</s:if>>billType2</option>
										<option value="billType3"
											<s:if test="shipCarriers.billType=='billType3'">selected="true"</s:if>>billType3</option>
										<option value="billType4"
											<s:if test="shipCarriers.billType=='billType4'">selected="true"</s:if>>billType4</option>
										<option value="billType5"
											<s:if test="shipCarriers.billType=='billType5'">selected="true"</s:if>>billType5</option>
										<option value="billType6"
											<s:if test="shipCarriers.billType=='billType6'">selected="true"</s:if>>billType6</option>
										<option value="billType7"
											<s:if test="shipCarriers.billType=='billType7'">selected="true"</s:if>>billType7</option>
								</select>
								</td>
								<th>Status</th>
								<td valign="top"><s:select cssStyle="width:131px"
										name="shipCarriers.billStatus" list="billStatusMap"
										listKey="key" listValue="value" value="billStatus"></s:select>
								</td>
							</tr>

							<tr>
								<th>Account No</th>

								<td colspan="3" valign="top"><input id="accountNo"
									name="shipCarriers.accountNo" type="text" class="NFText"
									size="33" value="${shipCarriers.accountNo }" />
								</td>
								<th>Account Password</th>
								<td valign="top"><input name="shipCarriers.accountPwd"
									type="password" value="${shipCarriers.accountPwd }"
									class="NFText" size="33" />
								</td>
							</tr>

							<tr>
								<th valign="top">Phone</th>

								<td width="50" valign="top"><input
									name="shipCarriers.phone" type="text"
									value="${shipCarriers.phone }" class="NFText" id="phone"
									size="12" />
								</td>
								<th valign="top">Ext</th>
								<td valign="top"><input name="shipCarriers.phoneExt"
									type="text" class="NFText" id="phoneExt" size="8"
									value="${shipCarriers.phoneExt }" />
								</td>
								<th>&nbsp;</th>
								<td valign="top">&nbsp;</td>
							</tr>
							<tr>
								<th>Address</th>

								<td colspan="3" valign="top"><input
									name="shipCarriers.addrLine1" value="${shipCarriers.addrLine1}"
									type="text" class="NFText" id="addrLine1" size="33" />
								</td>
								<th>&nbsp;</th>
								<td valign="top">&nbsp;</td>
							</tr>
							<tr>
								<th>&nbsp;</th>
								<td colspan="3"><input name="shipCarriers.addrLine2"
									type="text" value="${shipCarriers.addrLine2}" class="NFText"
									id="addrLine2" size="33" />
								</td>
								<th>&nbsp;</th>
								<td>&nbsp;</td>

							</tr>
							<tr>
								<th>&nbsp;</th>
								<td colspan="3"><input name="shipCarriers.addrLine3"
									type="text" value="${shipCarriers.addrLine3}" class="NFText"
									id="addrLine3" size="33" />
								</td>
								<th>&nbsp;</th>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<th>City</th>

								<td colspan="3"><input name="shipCarriers.city" type="text"
									value="${shipCarriers.city}" class="NFText" id="city" size="33" />
								</td>
								<th>Zip Code</th>
								<td><input name="shipCarriers.zipCode" type="text"
									class="NFText" size="33" value="${shipCarriers.zipCode}"
									id="zipCode" />
								</td>
							</tr>
							<tr>
								<th>State</th>
								<td colspan="3"><input name="shipCarriers.state" type="text"
									class="NFText" value="${shipCarriers.state}" id="state" size="18" />
								</td>

								<th>Country</th>
								<td><select name="shipCarriers.country" id="country">
										<option value="">United States</option>
								</select>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="button_box">
		<saveButton:saveBtn parameter="${operation_method}"
			disabledBtn='<input type="submit" id="saveAllTrigger" value="Save" class="search_input" disabled="disabled" />'
			saveBtn='<input type="submit" id="saveAllTrigger" value="Save" class="search_input" />' />
		<input type="button" name="Submit124" value="Cancel"
			class="search_input" onclick="cancelAll()" />
	</div>
	<script type="text/javascript">
    initTabs('dhtmlgoodies_tabView1', Array('General','International','Billing Info'), 0, 1000, 395);
</script>
</body>
</html>
