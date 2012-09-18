<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}ajax.js"></script>

<link href="${global_css_url}tab-view.css" rel="stylesheet"
	type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}TabbedPanels.js"></script>
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
	type="text/css" />

<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
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
<script src="${global_js_url}jquery/ui/ui.datepicker.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1"
	type="text/javascript"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}scm/product/manager_task.js?v=2"></script>
<script src="${global_js_url}util/util.js" type="text/javascript"></script>
<script src="${global_js_url}/recordTime.js" type="text/javascript"></script>
<script>
var defaultTab = ${defaultTab};
var pdtServType = "Service";
$(function(){
	if(pdtServType == "Product"){
		disableTabByTitle("Break-down");
	}else if(pdtServType == "Service"){
         <s:if test="breakdownFlag==\"N\"">
    	 disableTabByTitle("Break-down");
    	</s:if>
		disableTabByTitle("Composite");
	    disableTabByTitle("More Info");
	}
	<s:if test="serviceId==null">
	disableTabByTitle("Service Details");
	disableTabByTitle("Sales");
	</s:if>

    $("#serviceClsId").mouseover(function(){ 
    	var str=$('#serviceClsId').text();
    	str = jQuery.trim(str);
    	$('#serviceClsId').attr("title",str);
    });
    
    $("#breakdownFlag").click(function(){
            if($("#breakdownFlag").attr("checked")){
                $("#breakdownFlag").attr("checked","checked");
                $("#breakdownFlag").attr("value","Y");
                 enableTabByTitle("Break-down");
            }
            else{
                $("#breakdownFlag").attr("checked",false);
                $("#breakdownFlag").attr("value","N");
                 disableTabByTitle("Break-down");
            }
        });

})
var isSaved = false;
window.onbeforeunload = function() {
	if('${approvedMethod}'!="approvedEdit"){
		if(isSaved === false){
			return 'Do you want to leave without saving data?';
		}
	}
}

function changeSpecial(){
	
	
	if($("#shipExemptFlag").attr("checked")){
		$("#specShipCharge").val('');
        $("#specShipCharge").attr("disabled","disabled");
    }
    else{
        $("#specShipCharge").attr("disabled",false);
    }
}


</script>
<script src="${global_js_url}scm/serv_dialog.js" type="text/javascript"></script>
<script src="${global_js_url}scm/service.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}tab-view.js"></script>
<style type="text/css">
<!--
.content .scm #dhtmlgoodies_tabView1 .dhtmlgoodies_aTab .General_table {
	margin: 4px 0px;
}

.content .scm #dhtmlgoodies_tabView1 .dhtmlgoodies_aTab .General_table fieldset
	{
	margin: 4px;
}
-->
</style>
</head>

<body class="content" style="background-image: none;"
	onload="recordTime()">

	<div class="scm">
		<div class="title_content">
			<div class="title">
				<s:if test="serviceId==null">
  		Service - New Service
  	</s:if>
				<s:else>
    	Service - #${name }
    </s:else>
			</div>
			<div class="input_box">
				<div class="content_box">
					<form id="baseForm">
						<table border="0" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<th width="160"><span class="important">*</span> Service
									Catalog No</th>
								<td><input name="serviceDTO.catalogNo" id="catalogNo"
									type="text" class="NFText" value="${catalogNo}" size="25"
									readonly="readonly" /> <input name="catalogNoRuleId"
									id="catalogNoRuleId" type="hidden" /> <s:if
										test="serviceId==null">
										<input name="name_a" type="button" id="name_a2"
											class="style_botton" value="Generate"
											onclick="generateCatalogNoClick();" />
										<input name="serviceDTO.categoryId" type="hidden"
											" id="categoryId" value="" />
										<div id="generateCatalogNoDialog"
											title="Service Catalog No Generation"></div>
									</s:if> <input name="psNo2" id="psNo2" type="hidden"
									value="${catalogNo}" />
								</td>
								<th width="100"><span class="important">*</span> Name</th>
								<td><s:if test="serviceId!=null">
										<s:if
											test="(approvedName!=null&&approvedName!=\"\")&&approvedMethod==\"approvedEdit\"">
											<input name="serviceDTO.name" id="psName" type="text"
												class="NFText" value="${approvedName}" readonly="readonly"
												size="25" />
										</s:if>
										<s:else>
											<input name="serviceDTO.name" id="psName" type="text"
												class="NFText" value="${name}" readonly="readonly" size="25" />
										</s:else>

									</s:if> <s:else>
										<input name="serviceDTO.name" id="psName" type="text"
											class="NFText" value="" size="25" />
									</s:else> <s:if test="catalogNo!=null&&approvedMethod!=\"approvedEdit\"">
                  		&nbsp;<input type="button" value="Modify"
											class="style_button" onclick="modifyNameClick()" />
									</s:if>
									<div id="modifyNameDialog" title="Modify Service Name"
										style="display: none;">
										<table id="whole_table" width="492" border="0" cellpadding="0"
											cellspacing="0" class="General_table"
											style="margin-left: 40px;">
											<tr>
												<th width="100" align="left">Service Name</th>
												<td width="414"><input type="text" size='35'
													class="NFText" name="approved" id="approved"
													value="${name }" /></td>
											</tr>
											<tr>
												<th height="24" colspan="2"><div align="left">
														Choose the reason to modify the service name:</div></th>
											</tr>
											<tr>
												<th colspan="2"><div align="left">
														<textarea name="approvedReason" id="approvedReason"
															cols="70" rows="2" class="content_textarea"></textarea>
													</div></th>
											</tr>
											<tr>
												<th align="right" colspan="2">
													<div align="center" style="margin: 10px;">
														<div id="cat_name" style='display: block;'>
															<input type="hidden" name="approvedType"
																id="approvedType" value="ServiceApprovedName" /> <input
																type="hidden" name="oldApproved" id="oldApproved"
																value="${name }" /> <input type="button"
																class="style_botton" value="Modify"
																id="saveApprovedTrigger" /> <input type="button"
																value="Cancel" class="style_botton"
																onclick="$('#modifyNameDialog').dialog('close');" />
														</div>
													</div>
												</th>
											</tr>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<th>Full Name</th>
								<td><input name="serviceDTO.fullName" type="text"
									class="NFText" value="${fullName}" size="25" />
								</td>
								<th>Acronym</th>
								<td><input name="serviceDTO.synonyms" type="text"
									class="NFText" value="${synonyms}" size="25" /></td>
							</tr>
							<tr>
								<th valign="top"><span class="important">*</span>
									Description</th>
								<td><input name="serviceDTO.shortDesc" type="text"
									class="NFText" value="${shortDesc}" size="76" /></td>
								<th><span class="important">*</span> Status</th>
								<td><s:if test="status==null">
										<input name="serviceDTO.status" type="text" class="NFText"
											value="INACTIVE" readonly="readonly" size="25" />
									</s:if> <s:else>
										<s:if
											test="approvedStatus!=null&&approvedMethod==\"approvedEdit\"">
											<input name="serviceDTO.status" type="text" class="NFText"
												value="${status}" readonly="readonly" size="25" />
										</s:if>
										<s:else>
											<input name="serviceDTO.status" type="text" class="NFText"
												value="${status}" readonly="readonly" size="25" />
                  		&nbsp;<input type="button" value="Modify"
												class="style_button" onclick="modifyStatusClick()" />
										</s:else>

									</s:else>
									<div id="modifyStatusDialog" title="Modify Service Status"
										style="display: none;">
										<table border="0" cellpadding="0" cellspacing="0"
											class="General_table" style="margin-left: 40px;">
											<tr>
												<th height="26"><div align="left">
														Status <select name="statusApprove" id="statusApprove">
															<s:if test="status==null">
																<option value="ACTIVE" selected="selected">ACTIVE</option>
																<option value="INACTIVE">INACTIVE</option>
																<option value="IN DEVELOP">IN DEVELOP</option>
															</s:if>
															<s:if test="status==\"ACTIVE\"">
																<option value="ACTIVE" selected="selected">ACTIVE</option>
																<option value="INACTIVE">INACTIVE</option>
																<option value="IN DEVELOP">IN DEVELOP</option>
															</s:if>
															<s:if test="status==\"INACTIVE\"">
																<option value="ACTIVE">ACTIVE</option>
																<option value="INACTIVE" selected="selected">INACTIVE</option>
																<option value="IN DEVELOP">IN DEVELOP</option>
															</s:if>
															<s:if test="status==\"IN DEVELOP\"">
																<option value="ACTIVE">ACTIVE</option>
																<option value="INACTIVE">INACTIVE</option>
																<option value="IN DEVELOP" selected="selected">IN
																	DEVELOP</option>
															</s:if>
														</select>
													</div></th>
											</tr>
											<tr>
												<th height="24" valign="top"><div align="left">Choose
														the reason to update the service status:</div></th>
											</tr>
											<tr>
												<th valign="top"><textarea name="statusReason"
														id="statusReason" cols="70" rows="2"
														class="content_textarea"></textarea></th>
											</tr>
											<tr>
												<th valign="top">
													<div align="center" style="margin: 10px;">
														<div id="cat_name" style='display: block;'>
															<input type="hidden" name="approvedStatusType"
																id="approvedStatusType" value="ServiceApprovedStatus" />
															<input type="hidden" name="oldStatusApproved"
																id="oldStatusApproved" value="${status }" /> <input
																type="button" class="style_botton" value="Modify"
																id="saveApprovedStatusTrigger" /> <input type="button"
																value="Cancel" class="style_botton"
																onclick="$('#modifyStatusDialog').dialog('close');" />
														</div>
													</div>
												</th>
											</tr>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<th valign="top">Full Description</th>
								<td><textarea name="serviceDTO.longDesc"
										class="content_textarea2">${longDesc}</textarea></td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<th>Abstract</th>
								<td><textarea name="serviceDTO.abst"
										class="content_textarea2">${abst}</textarea></td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<th>Modify Date</th>
								<td><input name="modifyDate" type="text" class="NFText"
									value="<s:date name="modifyDate" format="yyyy-MM-dd" />"
									size="25" readonly="yes" /></td>
								<th>Modified By</th>
								<td><input name="serviceDTO.modifyByText" type="text"
									class="NFText" value="${modifyByText}" size="25" readonly="yes" />
								</td>
							</tr>
							<tr>
								<th>Creation Date</th>
								<td><input name="serviceDTO.creationDate" type="text"
									class="NFText"
									value="<s:date name="creationDate" format="yyyy-MM-dd" />"
									size="25" readonly="yes" /></td>
								<th>Created By</th>
								<td><input name="serviceDTO.createdByText" type="text"
									class="NFText" value="${createdByText}" size="25"
									readonly="yes" /> <input type="hidden"
									name="serviceDTO.serviceId" id="psId" value="${serviceId}" />
									<input type="hidden" name="serviceDTO.createdBy" id="psId"
									value="${createdBy}" /> <input name="nameAppr" type="hidden"
									value="${nameAppr}" /> <input name="statusAppr" type="hidden"
									value="${statusAppr}" /> <input name="nameApprove"
									type="hidden" /> <input name="nameReason" type="hidden" /> <input
									name="statusApprove" type="hidden" /> <input
									name="statusReason" type="hidden" />
								</td>
							</tr>
							<tr>
								<th>Publish Date</th>
								<td><input name="serviceDTO.publishDate" type="text"
									class="NFText"
									value="<s:date name="publishDate" format="yyyy-MM-dd" />"
									size="25" readonly="readonly" /></td>
								<th>&nbsp;</th>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
						</table>

						<input type="hidden" id="unitCost" name="unitCost"
							value="${unitCost}" /> <input type="hidden" id="vtRatio"
							name="vtRatio" value="${vtRatio}" /> <input type="hidden"
							id="btRatio" name="btRatio" value="${btRatio}" /> <input
							type="hidden" id="sellingPriceCmsn" name="sellingPriceCmsn"
							value="${sellingPriceCmsn}" /> <input type="hidden"
							id="grossProfitCmsn" name="grossProfitCmsn"
							value="${grossProfitCmsn}" /> <input type="hidden"
							id="unitRateCmsn" name="unitRateCmsn" value="${unitRateCmsn}" />

						<input type="hidden" name="returnPoints" id="returnPoints"
							value="${returnPoints}" /> <input type="hidden"
							name="priceByPoints" id="priceByPoints" value="${priceByPoints}" />
						<input type="hidden" name="noticeSendType" id="noticeSendType"
							value="${noticeSendType}" /> <input type="hidden"
							name="noticeGenerateTime" id="noticeGenerateTime"
							value="${noticeGenerateTime}" /> <input type="hidden"
							name="customerInfo" id="customerInfo" value="${customerInfo}" />
						<s:if test="dimUom==null">
							<input type="hidden" name="serviceDTO.dimUom" value="inches" />
						</s:if>
						<s:else>
							<input type="hidden" name="serviceDTO.dimUom" value="${dimUom}" />
						</s:else>
						<input type="hidden" name="serviceDTO.inventoryId"
							value="${inventoryId}" /> <input type="hidden"
							name="serviceDTO.documentFlag" value="${documentFlag}" /> <input
							type="hidden" name="serviceDTO.versionNum" value="${versionNum}" />

						<input type="hidden" name="sessionServiceId" id="sessionServiceId"
							value="${sessionServiceId}" /> <input type="hidden"
							id="saftyStock" name="saftyStock" value="${saftyStock}" /> <input
							type="hidden" id="minOrderQty" name="minOrderQty"
							value="${minOrderQty}" /> <input type="hidden" id="leadTime"
							value="${leadTime}" /> <input type="hidden" id="url"
							value="${url}" />
					</form>
				</div>
			</div>

			<div id="dhtmlgoodies_tabView1">
				<div class="dhtmlgoodies_aTab">
					<form id="generalForm">
						<table width="970" border="0" cellpadding="0" cellspacing="0"
							class="General_table" style="margin-top: 0px;">
							<tr>
								<td width="38%">
									<fieldset>
										<legend>Service Attributes</legend>
										<table width="100%" border="0" cellpadding="0" cellspacing="0"
											class="General_table">
											<tr>
												<td width="4%"><s:if test="invoiceable==\"N\" ||invoiceable==\"n\"">
														<input type="checkbox" name="serviceDTO.invoiceable"
															value="N" checked="checked" />
													</s:if> <s:else>
														<input type="checkbox" name="serviceDTO.invoiceable"
															value="N" />
													</s:else>
												</td>
												<td width="46%">Exempt From Invoicing</td>
												<td><s:if test="taxable==\"N\" || taxable==\"n\"">
														<input type="checkbox" name="serviceDTO.taxable" value="N"
															checked="checked" />
													</s:if> <s:else>
														<input type="checkbox" name="serviceDTO.taxable" value="N" />
													</s:else>
												</td>
												<td>Non-Taxable</td>
											</tr>
											<tr>
												<td><s:if test="discountable==\"N\" ||discountable==\"n\"">
														<input type="checkbox" name="serviceDTO.discountable"
															value="N" checked="checked" />
													</s:if> <s:else>
														<input type="checkbox" name="serviceDTO.discountable"
															value="N" />
													</s:else>
												</td>
												<td>Exempt From All Discounts</td>
												<td width="4%"><s:if test="quotable==\"N\" || quotable==\"n\"">
														<input type="checkbox" name="serviceDTO.quotable"
															value="N" checked="checked" />
													</s:if> <s:else>
														<input type="checkbox" name="serviceDTO.quotable"
															value="N" />
													</s:else>
												</td>
												<td width="46%">Cannot Quote Item</td>
											</tr>
											<tr>
												<td><s:if test="lotControlFlag==\"N\" || lotControlFlag==\"n\"">
														<input type="checkbox" name="serviceDTO.lotControlFlag"
															value="N" checked="checked" />
													</s:if> <s:else>
														<input type="checkbox" name="serviceDTO.lotControlFlag"
															value="N" />
													</s:else>
												</td>
												<td>Exempt From Lot Control</td>
												<td><s:if test="sellable==\"N\" || sellable==\"n\"">
														<input type="checkbox" name="serviceDTO.sellable"
															value="N" checked="checked" />
													</s:if> <s:else>
														<input type="checkbox" name="serviceDTO.sellable"
															value="N" />
													</s:else>
												</td>
												<td>Cannot Sell Item</td>
											</tr>
											<tr>
												<td><s:if test="shippable==\"N\" || shippable==\"n\"">
														<input type="checkbox" name="serviceDTO.shippable"
															value="N" checked="checked" />
													</s:if> <s:else>
														<input type="checkbox" name="serviceDTO.shippable"
															value="N" />
													</s:else>
												</td>
												<td>Non-shippable</td>
												<td><s:if test="purchasable==\"N\" || purchasable==\"n\" ">
														<input type="checkbox" name="serviceDTO.purchasable"
															value="N" checked="checked" />
													</s:if> <s:else>
														<input type="checkbox" name="serviceDTO.purchasable"
															value="N" />
													</s:else>
												</td>
												<td>Cannot Purchase Item</td>
											</tr>
											<tr>
												<td><s:if test="returnable==\"N\" ||returnable==\"n\"">
														<input type="checkbox" name="serviceDTO.returnable"
															value="N" checked="checked" />
													</s:if> <s:else>
														<input type="checkbox" name="serviceDTO.returnable"
															value="N" />
													</s:else>
												</td>
												<td>Non-returnable</td>
												<td><s:if test="giftFlag==\"Y\" || giftFlag==\"y\"">
														<input type="checkbox" name="serviceDTO.giftFlag"
															value="Y" checked="checked" />
													</s:if> <s:else>
														<input type="checkbox" name="serviceDTO.giftFlag"
															value="Y" />
													</s:else>
												</td>
												<td>Gift Item</td>
											</tr>
											<tr>
												<td><s:if test="stockable==\"N\" || stockable==\"n\"">
														<input type="checkbox" name="serviceDTO.stockable"
															value="N" checked="checked" />
													</s:if> <s:else>
														<input type="checkbox" name="serviceDTO.stockable"
															value="N" />
													</s:else>
												</td>
												<td>Non-stockable</td>
												<td><s:if test="breakdownFlag==\"Y\" || breakdownFlag==\"y\"">
														<input type="checkbox" name="serviceDTO.breakdownFlag"
															value="Y" id="breakdownFlag" checked="checked" />
													</s:if> <s:else>
														<input type="checkbox" name="serviceDTO.breakdownFlag"
															value="N" id="breakdownFlag" />
													</s:else>
												</td>
												<td>Break-Down item</td>
											</tr>
											<tr>
												<td height="22"><s:if test="serialControlFlag==\"N\" ||serialControlFlag==\"n\"">
														<input type="checkbox" name="serviceDTO.serialControlFlag"
															value="N" checked="checked" />
													</s:if> <s:else>
														<input type="checkbox" name="serviceDTO.serialControlFlag"
															value="N" />
													</s:else>
												</td>
												<td>Non-serialized</td>
												<td><s:if test="compositeFlag==\"N\" || compositeFlag==\"n\"">
														<input type="checkbox" name="serviceDTO.compositeFlag"
															value="N" disabled="disabled" />
													</s:if> <s:else>
														<input type="checkbox" name="serviceDTO.compositeFlag"
															value="N" disabled="disabled" />
													</s:else>
												</td>
												<td>Composite item</td>
											</tr>
										</table>
									</fieldset>
									<fieldset>
										<legend>Service Codes</legend>
										<table width="100%" border="0" cellpadding="0" cellspacing="0"
											class="General_table">
											<tr>
												<th width="50%"><span class="important">*</span>
													Service Classification</th>
												<td><s:if test="serviceId==null">
														<select name="serviceDTO.serviceClsId" id="serviceClsId">
															<s:iterator value="arrDropdownList">
																<s:if test="name==\"SERVICE_CLASSIFICATION\"">
																	<s:iterator value="dropDownDTOs">
																		<s:if test="id==serviceClsId">
																			<option value="${id}" selected="selected">${name}</option>
																			<s:set name="type" value="name" />
																		</s:if>
																		<s:else>
																			<option value="${id}">${name}</option>
																		</s:else>
																	</s:iterator>
																</s:if>
															</s:iterator>
														</select>
													</s:if> <s:else>
														<select name="serviceDTO.serviceClsId" id="serviceClsId">
															<s:iterator value="arrDropdownList">
																<s:if test="name==\"SERVICE_CLASSIFICATION\"">
																	<s:iterator value="dropDownDTOs">
																		<s:if test="id==serviceClsId">
																			<option value="${id}" selected="selected">${name}</option>
																			<s:set name="type" value="name" />
																		</s:if>
																	</s:iterator>
																</s:if>
															</s:iterator>
														</select>
													</s:else>
												</td>
											</tr>
											<tr>
												<th>UPC Code</th>
												<td><input name="serviceDTO.upcCode" type="text"
													class="NFText" value="${upcCode}" size="20" /></td>
											</tr>
										</table>
									</fieldset>
									<fieldset>
										<legend>Tax Status</legend>
										<table width="100%" border="0" cellpadding="0" cellspacing="0"
											class="General_table">
											<tr>
												<th><span class="important"></span>National</th>
												<td><select name="serviceDTO.federalTaxCls">
														<s:iterator value="arrDropdownList">
															<s:if test="name==\"TAX_STATUS_NATIONAL\"">
																<s:iterator value="dropDownDTOs">
																	<s:if test="id==federalTaxCls">
																		<option value="${id}" selected="selected">${name}</option>
																	</s:if>
																	<s:else>
																		<option value="${id}">${name}</option>
																	</s:else>
																</s:iterator>
															</s:if>
														</s:iterator>
												</select>
												</td>
												<th>State</th>
												<td><select name="serviceDTO.stateTaxCls">
														<s:iterator value="arrDropdownList">
															<s:if test="name==\"TAX_STATUS_STATE\"">
																<s:iterator value="dropDownDTOs">
																	<s:if test="id==stateTaxCls">
																		<option value="${id}" selected="selected">${name}</option>
																	</s:if>
																	<s:else>
																		<option value="${id}">${name}</option>
																	</s:else>
																</s:iterator>
															</s:if>
														</s:iterator>
												</select>
												</td>
											</tr>
											<tr>
												<th>Country</th>
												<td colspan="3"><select name="serviceDTO.countryTaxCls">
														<s:iterator value="arrDropdownList">
															<s:if test="name==\"TAX_STATUS_COUNTRY\"">
																<s:iterator value="dropDownDTOs">
																	<s:if test="id==countryTaxCls">
																		<option value="${id}" selected="selected">${name}</option>
																	</s:if>
																	<s:else>
																		<option value="${id}">${name}</option>
																	</s:else>
																</s:iterator>
															</s:if>
														</s:iterator>
												</select>
												</td>
											</tr>
										</table>
									</fieldset>
									<fieldset>
										<legend>Cross-Sell,UP-Sell,Substitute,Promote Items</legend>
										<div id="generalCrossDialog"></div>
										<div id="crossCtgNoSrchDialog" title="Catalog item"></div>
										<table width="100%" border="0" cellpadding="0" cellspacing="0"
											class="General_table">
											<tr>
												<td width="50%">
													<div align="right" id="crossContainer">
														<select name="cross" id="cross" onchange="selectCross()"
															style="width: 200px;">
															<s:iterator value="dropDownDTOList">
																<option value="${id}">${name}</option>
															</s:iterator>
															<option value='' selected="selected">Add Relate
																Selling Item</option>
														</select> <span id="catalogNoSpan"> <s:iterator
																value="dropDownDTOList">
																<input type="hidden" name="crossCatalogNo"
																	value="${rltCatalogNo}" />
															</s:iterator> </span>
													</div>
												</td>
												<td>
													<div id="tbl" style='display: block;'>
														<input name="add" type="button" id='addCross'
															class="style_botton" value="Add"
															onclick="generalCrossClick();" />
													</div>
													<div id="tb2" style='display: none;'>

														<input name="edit" type="button" id='editCross'
															class="style_botton" value="Edit"
															onclick="generalCrossClick();" />

													</div>
												</td>
											</tr>
										</table>
									</fieldset></td>
								<td valign="top"><fieldset>
										<legend>Size</legend>
										<table width="100%" border="0" cellpadding="0" cellspacing="0"
											class="General_table">
											<tr>
												<th width="110">Length</th>
												<td width="155"><input name="serviceDTO.length"
													type="text" class="NFText" size="8" value="${length}" /> <s:if
														test="dimUom==null">
	                  (inches)
	                  </s:if> <s:else>
	                  	(${dimUom})
	                  </s:else>
												</td>
												<th>Width</th>
												<td width="155"><input name="serviceDTO.width"
													type="text" class="NFText" size="8" value="${width}" /> <s:if
														test="dimUom==null">
	                  (inches)
	                  </s:if> <s:else>
	                  	(${dimUom})
	                  </s:else>
												</td>
											</tr>
											<tr>
												<th>Height</th>
												<td><input name="serviceDTO.height" type="text"
													class="NFText" size="8" value="${height}" /> <s:if
														test="dimUom==null">
	                  (inches)
	                  </s:if> <s:else>
	                  	(${dimUom})
	                  </s:else></td>
												<th><span class="important">*</span> Quantity UOM</th>
												<td><input name="serviceDTO.qtyUom" type="text"
													class="NFText" size="8" value="${qtyUom}" /></td>
											</tr>
											<tr>
												<th>Size</th>
												<td><input name="serviceDTO.size" type="text"
													class="NFText" size="8" value="${size}" /> <strong>UOM</strong>
													<input name="serviceDTO.uom" type="text" class="NFText"
													size="1" value="${uom}" /></td>
												<th>Alternate Size</th>
												<td><input name="serviceDTO.altSize" type="text"
													class="NFText" size="8" value="${altSize}" /> <strong>UOM</strong>
													<input name="serviceDTO.altUom" type="text" class="NFText"
													size="1" value="${altUom}" /></td>
											</tr>
										</table>
									</fieldset>
									<fieldset>
										<legend>Storage</legend>
										<table width="100%" border="0" cellpadding="0" cellspacing="0"
											class="General_table">
											<tr>
												<th width="110">Service Form</th>
												<td width="155"><input
													name="serviceDTO.storageCondition.form" type="text"
													class="NFText" size="20" value="${storageCondition.form }" />
												</td>
												<th><span class="important">*</span> Light Protected</th>
												<td width="155"><select
													name="serviceDTO.storageCondition.lightProtection">
														<s:if test="storageCondition.lightProtection==\"Y\"">
															<option selected="selected" value="Y">Yes</option>
															<option value="N">No</option>
														</s:if>
														<s:else>
															<option value="Y">Yes</option>
															<option selected="selected" value="N">No</option>
														</s:else>
												</select>
												</td>
											</tr>
											<tr>
												<th><span class="important">*</span> Temperature</th>
												<td><select
													name="serviceDTO.storageCondition.temperature">
														<option value="">Please select</option>
														<option value="25.0"
															<s:if test="storageCondition.temperature==\"25.0\"">selected="selected" </s:if>>25
														</option>
														<option value="4.0"
															<s:if test="storageCondition.temperature==\"4.0\"">selected="selected" </s:if>>4
														</option>
														<option value="-20.0"
															<s:if test="storageCondition.temperature==\"-20.0\"">selected="selected" </s:if>>-20
														</option>
														<option value="-80.0"
															<s:if test="storageCondition.temperature==\"-80.0\"">selected="selected" </s:if>>-80
														</option>
														<option value="-196.0"
															<s:if test="storageCondition.temperature==\"-196.0\"">selected="selected" </s:if>>-196
														</option>
												</select> ℃ <%--    <input name="serviceDTO.storageCondition.temperature" type="text" class="NFText" size="20" value="${storageCondition.temperature }" />℃ --%>
												</td>
												<th><span class="important">*</span> Humidity</th>
												<td><input name="serviceDTO.storageCondition.humidity"
													type="text" class="NFText" size="20"
													value="${storageCondition.humidity }" /></td>
											</tr>
											<tr>
												<th>Container</th>
												<td><input name="serviceDTO.storageCondition.container"
													type="text" class="NFText" size="20"
													value="${storageCondition.container }" /></td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<th>Other Requirement</th>
												<td colspan="3"><input
													name="serviceDTO.storageCondition.comment" type="text"
													class="NFText" size="73"
													value="${storageCondition.comment }" /></td>
											</tr>
										</table>
									</fieldset>
									<fieldset>
										<legend>Shipping</legend>
										<table width="100%" border="0" cellpadding="0" cellspacing="0"
											class="General_table">
											<tr>
												<th colspan="2">Service Form</th>
												<td width="111"><input
													name="serviceDTO.shipCondition.form" type="text"
													class="NFText" size="16" value="${shipCondition.form }" />
												</td>
												<th width="142"><span class="important">*</span>
													Temperature</th>
												<td width="145"><select
													name="serviceDTO.shipCondition.temperature">
														<option value="">Please select</option>

														<option value="25.0"
															<s:if test="shipCondition.temperature==\"25.0\"">selected="selected" </s:if>>25
														</option>
														<option value="4.0"
															<s:if test="shipCondition.temperature==\"4.0\"">selected="selected" </s:if>>4
														</option>
														<option value="-20.0"
															<s:if test="shipCondition.temperature==\"-20.0\"">selected="selected" </s:if>>-20
														</option>
														<option value="-80.0"
															<s:if test="shipCondition.temperature==\"-80.0\"">selected="selected" </s:if>>-80
														</option>
														<option value="-196.0"
															<s:if test="shipCondition.temperature==\"-196.0\"">selected="selected" </s:if>>-196
														</option>
												</select>℃ <%--      <input name="serviceDTO.shipCondition.temperature" type="text" class="NFText" size="16" value="${shipCondition.temperature}" />℃ --%>
												</td>
											</tr>
											<tr>
												<th colspan="2"><span class="important">*</span>
													Domestic Ship Weight</th>
												<td>
													<%-- <input
													name="serviceDTO.shipCondition.domShipWeight" type="text"
													class="NFText" size="11"
													value="${shipCondition.domShipWeight}" /> <s:if
														test="shipCondition.shipWeightUom==null">
    		(lbs)
    	</s:if> <s:else>
    		(${shipCondition.shipWeightUom})
    	</s:else>--%> <select name="serviceDTO.shipCondition.domShipWeight">
														<option value="">Please select ....</option>
														<option value="1.0"
															<s:if test="shipCondition.domShipWeight==\"1.0\"">selected="selected" </s:if>>
															1
															<s:if test="shipCondition.shipWeightUom==null">
    		(lbs)
    	</s:if>
															<s:else>
    		(${shipCondition.shipWeightUom})
    	</s:else>
														</option>
														<option value="3.0"
															<s:if test="shipCondition.domShipWeight==\"3.0\"">selected="selected" </s:if>>
															3
															<s:if test="shipCondition.shipWeightUom==null">
    		(lbs)
    	</s:if>
															<s:else>
    		(${shipCondition.shipWeightUom})
    	</s:else>
														</option>
														<option value="5.0"
															<s:if test="shipCondition.domShipWeight==\"5.0\"">selected="selected" </s:if>>
															5
															<s:if test="shipCondition.shipWeightUom==null">
    		(lbs)
    	</s:if>
															<s:else>
    		(${shipCondition.shipWeightUom})
    	</s:else>
														</option>
														<option value="10.0"
															<s:if test="shipCondition.domShipWeight==\"10.0\"">selected="selected" </s:if>>
															10
															<s:if test="shipCondition.shipWeightUom==null">
    		(lbs)
    	</s:if>
															<s:else>
    		(${shipCondition.shipWeightUom})
    	</s:else>
														</option>
														<option value="15.0"
															<s:if test="shipCondition.domShipWeight==\"15.0\"">selected="selected" </s:if>>
															15
															<s:if test="shipCondition.shipWeightUom==null">
    		(lbs)
    	</s:if>
															<s:else>
    		(${shipCondition.shipWeightUom})
    	</s:else>
														</option>
														
														<option value="25.0"
															<s:if test="shipCondition.domShipWeight==\"25.0\"">selected="selected" </s:if>>
															25
															<s:if test="shipCondition.shipWeightUom==null">
    		(lbs)
    	</s:if>
															<s:else>
    		(${shipCondition.shipWeightUom})
    	</s:else>
														</option>
												</select> <!-- 				<input
													name="productDTO.shipCondition.intlShipWeight" type="text"
													class="NFText" size="11"
												value="${shipCondition.intlShipWeight}" />  
													<s:if
														test="shipCondition.shipWeightUom==null">
    		(lbs)
    	</s:if> <s:else>
    		(${shipCondition.shipWeightUom})
    	</s:else>	 --></td>
												<th><span class="important">*</span> Int'I Ship Weight</th>
												<td>
													<%-- <input
													name="serviceDTO.shipCondition.intlShipWeight" type="text"
													class="NFText" size="11"
													value="${shipCondition.intlShipWeight}" /> <s:if
														test="shipCondition.shipWeightUom==null">
    		(lbs)
    	</s:if> <s:else>
    		(${shipCondition.shipWeightUom})
    	</s:else>
    	
    	 --%> <select name="serviceDTO.shipCondition.intlShipWeight">
														<option value="">Please select ....</option>
														<option value="1.0"
															<s:if test="shipCondition.intlShipWeight==\"1.0\"">selected="selected" </s:if>>
															1
															<s:if test="shipCondition.shipWeightUom==null">
    		(lbs)
    	</s:if>
															<s:else>
    		(${shipCondition.shipWeightUom})
    	</s:else>
														</option>
														<option value="3.0"
															<s:if test="shipCondition.intlShipWeight==\"3.0\"">selected="selected" </s:if>>
															3
															<s:if test="shipCondition.shipWeightUom==null">
    		(lbs)
    	</s:if>
															<s:else>
    		(${shipCondition.shipWeightUom})
    	</s:else>
														</option>
														<option value="5.0"
															<s:if test="shipCondition.intlShipWeight==\"5.0\"">selected="selected" </s:if>>
															5
															<s:if test="shipCondition.shipWeightUom==null">
    		(lbs)
    	</s:if>
															<s:else>
    		(${shipCondition.shipWeightUom})
    	</s:else>
														</option>
														<option value="10.0"
															<s:if test="shipCondition.intlShipWeight==\"10.0\"">selected="selected" </s:if>>
															10
															<s:if test="shipCondition.shipWeightUom==null">
    		(lbs)
    	</s:if>
															<s:else>
    		(${shipCondition.shipWeightUom})
    	</s:else>
														</option>
														<option value="15.0"
															<s:if test="shipCondition.intlShipWeight==\"15.0\"">selected="selected" </s:if>>
															15
															<s:if test="shipCondition.shipWeightUom==null">
    		(lbs)
    	</s:if>
															<s:else>
    		(${shipCondition.shipWeightUom})
    	</s:else>
														</option>
															<option value="25.0"
															<s:if test="shipCondition.intlShipWeight==\"25.0\"">selected="selected" </s:if>>
															25
															<s:if test="shipCondition.shipWeightUom==null">
    		(lbs)
    	</s:if>
															<s:else>
    		(${shipCondition.shipWeightUom})
    	</s:else>
														</option>
												</select> <!-- 				<input
													name="productDTO.shipCondition.intlShipWeight" type="text"
													class="NFText" size="11"
												value="${shipCondition.intlShipWeight}" />  
													<s:if
														test="shipCondition.shipWeightUom==null">
    		(lbs)
    	</s:if> <s:else>
    		(${shipCondition.shipWeightUom})
    	</s:else>	 --></td>
											</tr>
											<tr>
												<th colspan="2">Container</th>
												<td><input name="serviceDTO.shipCondition.container"
													type="text" class="NFText" size="16"
													value="${shipCondition.container}" /></td>
												<th><span class="important">*</span> Package Type</th>
												<td><s:select
														name="serviceDTO.shipCondition.shipPkgType"
														list="pbOptionItemList" listValue="text" listKey="value"
														value="shipCondition.shipPkgType" /></td>
											</tr>
											<tr>
												<td colspan="3">
													<div align="center">
														<s:if test="shipCondition.shipExemptFlag == \"N\"">
															<input type="checkbox" id="shipExemptFlag"
																name="serviceDTO.shipCondition.shipExemptFlag" value="N"
																checked="checked" onclick="changeSpecial()" />
														</s:if>
														<s:else>
															<input type="checkbox" id="shipExemptFlag"
																name="serviceDTO.shipCondition.shipExemptFlag" value="N"
																onclick="changeSpecial()" />
														</s:else>
														Exempt From Shipping Charges
													</div></td>
												<th>Special Shipping Charge</th>
												<td>
													<!--<input name="serviceDTO.shipCondition.specShipCharge" type="text" class="NFText" size="16" value="${shipCondition.specShipCharge}"/>
    	--> <s:if test="shipCondition.shipExemptFlag == \"N\"">
														<input id="specShipCharge"
															name="serviceDTO.shipCondition.specShipCharge"
															type="text" class="NFText" size="16"
															value="${shipCondition.specShipCharge}"
															disabled="disabled" />
													</s:if> <s:else>
														<input id="specShipCharge"
															name="serviceDTO.shipCondition.specShipCharge"
															type="text" class="NFText" size="16" />
													</s:else>
												</td>
											</tr>
											<tr>
												<td width="70"><div align="right">
														<s:if test="shipCondition.urgentFlag == \"Y\"">
															<input type="checkbox"
																name="serviceDTO.shipCondition.urgentFlag" value="Y"
																checked="checked" />
														</s:if>
														<s:else>
															<input type="checkbox"
																name="serviceDTO.shipCondition.urgentFlag" value="Y" />
														</s:else>
													</div></td>
												<td width="76">Send Urgent</td>
												<td>&nbsp;</td>
												<th>&nbsp;</th>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<th colspan="2">Other Requirement</th>
												<td colspan="3"><input
													name="serviceDTO.shipCondition.comment" type="text"
													class="NFText" size="73" value="${shipCondition.comment}" />
												</td>
											</tr>
										</table>
									</fieldset>
								</td>
							</tr>
							<tr>
								<td height="24" colspan="2"><table border="0"
										cellspacing="0" cellpadding="0">
										<tr>
											<th width="80">Selling Note</th>
											<td><input name="serviceDTO.sellingNote" type="text"
												class="NFText" size="100" value="${sellingNote}" /></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>


						<input type="hidden" name="serviceDTO.shipCondition.createdBy"
							value="${shipCondition.createdBy}" /> <input type="hidden"
							name="serviceDTO.storageCondition.createdBy"
							value="${storageCondition.createdBy}" />
						<s:if test="serviceId==null">
							<input type="hidden" name="serviceDTO.shipCondition.creationDate"
								value="" />
							<input type="hidden"
								name="serviceDTO.storageCondition.creationDate" value="" />
						</s:if>
						<s:else>
							<input type="hidden" name="serviceDTO.shipCondition.creationDate"
								value="${shipCondition.creationDate}" />
							<input type="hidden"
								name="serviceDTO.storageCondition.creationDate"
								value="${storageCondition.creationDate}" />
						</s:else>
						<s:if test="shipCondition.shipWeightUom==null">
							<input type="hidden"
								name="serviceDTO.shipCondition.shipWeightUom" value="lbs" />
						</s:if>
						<s:else>
							<input type="hidden"
								name="serviceDTO.shipCondition.shipWeightUom"
								value="${shipCondition.shipWeightUom}" />
						</s:else>
					</form>
				</div>
				<div class="dhtmlgoodies_aTab">
					<iframe name="detailIframe" id="detailIframe"
						src="${ctx}/serv/serv!serviceDetails.action?type=<s:property value="#type"/>&id=${serviceId}&sessionServiceId=${sessionServiceId}"
						width="100%" height="420" frameborder="0" scrolling="yes"></iframe>
				</div>
				<div class="dhtmlgoodies_aTab">
					<iframe id="inventroyIframe" width="100%" height="430"
						frameborder="0" scrolling="no"
						src="serv/serv_inventory!list.action?psId=${id}&type=${type}&sessionServiceId=${sessionServiceId}"></iframe>
					<div id="shipAreaAddDialog" title="Add Restricted Shipping Area"></div>
					<div id="shipAreaEditDialog" title="Edit Restricted Shipping Area"
						style="background: #EEF4FD;"></div>
				</div>
				<div class="dhtmlgoodies_aTab">
					<iframe id="breakdownList_iframe"
						src="serv/breakdown!list.action?psId=${id}&type=${type}&sessionServiceId=${sessionServiceId}"
						width="100%" height="430" frameborder="0" scrolling="no"></iframe>
					<div id="breakdownDialog"
						title="Add New Break-down Item Intermediates"></div>
				</div>

				<div class="dhtmlgoodies_aTab">
					<iframe id="compositeList_iframe" width="100%" height="430"
						frameborder="0" scrolling="no"></iframe>
					<div id="compositeDialog" title="Add New Composite Item Components"></div>
				</div>
				<div class="dhtmlgoodies_aTab">
					<iframe id="supplierIframe" width="100%" height="430"
						frameborder="0" scrolling="no"></iframe>
					<div id="supplierEditDialog"></div>
					<div id="supplierPickerDialog"></div>
				</div>

				<div class="dhtmlgoodies_aTab">
					<iframe id="priceIframe" name="priceIframe" width="100%"
						height="430" frameborder="0" scrolling="no"></iframe>
					<div id="specialPriceDialog" title="Special Price"></div>
				</div>

				<div class="dhtmlgoodies_aTab">
					<div id="dhtmlgoodies_tabView2">
						<div class="dhtmlgoodies_aTab">&nbsp;</div>
						<div class="dhtmlgoodies_aTab">&nbsp;</div>
						<div id="priceChangeHistDialogd" title="Price Change History"></div>
						<div id="priceChangeHistDialogd2" title="Price Change History"></div>
						<div class="dhtmlgoodies_aTab"></div>
					</div>

				</div>

				<div class="dhtmlgoodies_aTab">
					<iframe id="miscIframe" width="100%" height="430" frameborder="0"
						scrolling="no"></iframe>
					<div id="miscPickerDialog" title="Supplier for Royalty Payment"></div>
				</div>

				<div class="dhtmlgoodies_aTab">
					<iframe id="salesIframe" width="100%" height="430" frameborder="0"
						scrolling="no"></iframe>
				</div>

			</div>
			<script type="text/javascript">
initTabs('dhtmlgoodies_tabView1',Array('General','Service Details','Inventory','Break-down','Composite','Suppliers','Pricing','More Info','Misc','Sales'),0,998,420);
function tabClick(){
	var index = activeTabIndex['dhtmlgoodies_tabView1'];
	var tmpFlag = false;
    if(index == 6){
        tmpFlag = window.frames["priceIframe"].changePrice();
        showTab('dhtmlgoodies_tabView1',${defaultTab});
	}else{
		tmpFlag = true;
	}
	if(tmpFlag == false){
		return;
	}
	var idArray = this.id.split('_');	
	showTab(this.parentNode.parentNode.id,idArray[idArray.length-1].replace(/[^0-9]/gi,''));	
 
}


</script>
			<div class="button_box">
				<s:if test="approvedMethod==\"approvedEdit\"">
					<div align="center">
						<input type="hidden" name="requestIdApproved"
							id="requestIdApproved" value="${requestId}" /> <input
							type="hidden" name="approvedMethod" id="approvedMethod"
							value="${approvedMethod}" /> <input id="approveBtn"
							type="button" class="search_input" value="Approve" /> <input
							id="rejectBtn" type="button" class="search_input" value="Reject" />
						<input id="host" type="button" class="search_input" value="Cancel"
							onclick="javascript:history.back(-1);" />
					</div>
				</s:if>
				<s:else>
					<saveButton:saveBtn parameter="${operation_method}"
						disabledBtn='<input type="submit" value="Save" id="SaveAllTrigger" class="search_input" disabled="disabled" />'
						saveBtn='<input type="submit" value="Save" id="SaveAllTrigger" class="search_input" onclick="pdtServSaveAll(\'serv\')" />' />
					<input type="button" value="Cancel" id="cancelAllTrigger"
						class="search_input" />
				</s:else>
			</div>
		</div>
	</div>
	<div class="scm">
		<div id="subStepAddDialog" title="Add New Step"></div>
	</div>
	<div style="display: none;" id="rejectDialog">
		<table id="whole_table" width="408" border="0" cellpadding="0"
			cellspacing="0" class="General_table" style="margin-left: 50px;">
			<tr>
				<td width="364" height="18" style="font-size: 12px">Enter
					comments to reject the change request:</td>
			</tr>
			<tr>
				<td height="63"><textarea id="rejectReason" name="rejectReason"
						class="content_textarea2"></textarea>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<input id="rejectDialogConfirm" type="button" class="style_botton"
							value="Confirm" /> <input id="rejectDialogCancel" type="button"
							class="style_botton" value="Cancel" />
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>