<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Work Order -- Production Centers</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}tab-view.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="${global_js_url}jquery/jquery.js"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
		<script src="${global_js_url}jquery/jquery.validate.js?v=1"
			type="text/javascript"></script>
		<script type="text/javascript" src="${global_js_url}tab-view.js"></script>
		<script
			src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js"
			type="text/javascript"></script>
		<script src="${global_js_url}jquery/jquery.form.js"
			type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.core.js"
			type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.draggable.js"
			type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.resizable.js"
			type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.dialog.js"
			type="text/javascript"></script>
		<script type="text/javascript" src="${global_js_url}show_tag.js"></script>
		<script src="${global_js_url}jquery/ui/ui.datepicker.js"
			type="text/javascript"></script>
		<script src="${global_js_url}workorder/entry.js"
			type="text/javascript"></script>
	    <script language="javascript" type="text/javascript"
	        src="${global_js_url}newwindow.js"></script>
		<script type="text/javascript">
            var baseUrl="${global_url}";			
         </script>
		<style type="text/css">
<!--
.content .scm #dhtmlgoodies_tabView1 .dhtmlgoodies_aTab .General_table fieldset
	{
	margin: 4px;
}

.disp {
	display: none;
	margin-left: 40px;
}
-->
</style>

	</head>
	<body class="content">
		<div class="scm">
			<div class="title_content">
				<div class="title">
					<s:if test="workOrder.orderNo!=null">Work Order-<s:property value="workOrder.orderNo"/></s:if>
					<s:else>Create Work Order</s:else>
				</div>
			</div>
             <div id="ShowDetailDialog" title=" Show Detail Dialog"></div>
			<div class="input_box">
				<div class="content_box">
					<form id="save_form" method="post" class="niceform"
						enctype="multipart/form-data">
						<s:hidden name="workOrder.parentOrderNo" id="parentWorkOrderNo"></s:hidden>
						<s:hidden name="workOrder.creationDate"></s:hidden>
						<s:hidden name="workOrder.createdBy"></s:hidden>
						<s:hidden name="operStatus" id="operStatus"></s:hidden>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									<table border="0" cellpadding="0" cellspacing="0"
										class="General_table">
										<tr>
											<th width="254">
												Work Order No
											</th>
											<td width="316">
												<input name="workOrder.altOrderNo" id="altOrderNo" type="text"
													class="NFText" value="${workOrder.altOrderNo}"
													readonly="readonly" size="35" />
												<input name="workOrder.orderNo" id="workOrderNo" type="hidden"
													 value="${workOrder.orderNo}"/>
												<input type="hidden" name="sessId" value="${sessId}"
													id="sessId" />
											</td>
											<th width="150">
												Work Order Type
											</th>
											<td colspan="2">
											<s:if test="operStatus=='QC'">
												<s:select id="type_sel" name="workOrder.type"
													cssStyle="width: 207px;"
													list="dropDownMap['WORK_ORDER_TYPE']" listKey="value"
													listValue="text" value="workOrder.type" disabled="true"></s:select>
												<s:hidden name="workOrder.type"></s:hidden>
											</s:if>
											<s:else>
												<s:select id="type_sel" name="workOrder.type"
													cssStyle="width: 207px;"
													list="dropDownMap['WORK_ORDER_TYPE']" listKey="value"
													listValue="text" value="workOrder.type"></s:select>
											</s:else>
												
											</td>
										</tr>
										<tr>
											<th valign="top">
												Source
											</th>
											<td>
											<s:if test="operStatus=='QC'">
												<s:select id="source_sel" name="workOrder.source"
													cssStyle="width: 207px;"
													list="dropDownMap['WORK_ORDER_SOURCE']" listKey="value"
													listValue="text" value="workOrder.source" disabled="true"></s:select>
													<s:hidden name="workOrder.source"></s:hidden>
											</s:if>
											<s:else>
												<s:select id="source_sel" name="workOrder.source"
													cssStyle="width: 207px;"
													list="dropDownMap['WORK_ORDER_SOURCE']" listKey="value"
													listValue="text" value="workOrder.source"></s:select>
											</s:else>
												
											</td>
											<th>
												Status
											</th>
											<td>
											<s:if test="operStatus=='QC'">
												<s:select id="status_sel" name="workOrder.status"
													cssStyle="width: 207px;"
													list="dropDownMap['WORK_ORDER_STATUS']" listKey="value"
													listValue="text" value="workOrder.status" disabled="true"></s:select>
													<s:hidden name="workOrder.status"/>
											</s:if>
											<s:elseif test="workOrder.orderNo==null">
												<s:select id="status_sel" name="workOrder.status"
													cssStyle="width: 207px;"
													list="dropDownMap['WORK_ORDER_STATUS']" listKey="value"
													listValue="text" value="workOrder.status" disabled="true"></s:select>
													<s:hidden name="workOrder.status"/>
											</s:elseif>
											<s:else>
												<s:select id="status_sel" name="workOrder.status"
													cssStyle="width: 207px;"
													list="dropDownMap['WORK_ORDER_STATUS']" listKey="value"
													listValue="text" value="workOrder.status" disabled="true"></s:select>
													<s:hidden name="workOrder.status" id="hidden_status"/>
													<s:hidden name="workOrder.reason" id="hidden_reason"/>
													<s:if test="workOrder.orderNo==null"><input type="button" name="Submit42" class="style_botton"
													value="Modify" onclick="showModifyStatusDialog();" disabled="disabled"/></s:if>
													<s:else><input type="button" name="Submit42" class="style_botton"
													value="Modify" onclick="showModifyStatusDialog();"/></s:else>
													
											</s:else>
												
											</td>
											<td>
												
											</td>
										</tr>
										<tr>
											<th valign="top">
												Sales Order No
											</th>
											<td>
												<input readonly="readonly" name="workOrder.srcSoNo"
													id="order_orderNo_text" class="NFText" size="35"
													value="${workOrder.srcSoNo}" />
													<input id="ShowDetailTrigger" name="ShowDetailTrigger" value="View" class="style_botton"/>
											</td>
											<th>
												&nbsp;
											</th>
											<td colspan="2">
												&nbsp;
											</td>
										</tr>
										<tr>
											<th valign="top">
												China Sales Order No
											</th>
											<td>
												<input readonly="readonly" name="workOrder.soNo"
													id="order_chinaOrderNo_text" class="NFText" size="35"
													value="${workOrder.soNo}" />
												<input type="hidden" name="workOrder.companyId"
													value="${workOrder.companyId}" id="companyId_hidden" />
												<s:if test="workOrder.orderNo==null">
													<img src="images/search.gif" style="cursor: pointer"
													width="16" height="16" id="sel_chinaOrder_btn" />
												</s:if>
												
											 
											</td>
											<th>
												Item No
											</th>
											<td colspan="2">
												<s:if test="workOrder.orderNo!=null">
													<input readonly="readonly" id="so_item_sel" name="workOrder.soItemNo"
													 class="NFText" size="35"
													value="${workOrder.soItemNo}"/>
												</s:if>
												<s:elseif test="dropdownItemList!=null">
													<s:select id="so_item_sel" name="workOrder.soItemNo"
													list="dropdownItemList" listKey="value" listValue="name"
													value="workOrder.soItemNo" cssStyle="width: 207px;"></s:select>
												</s:elseif>
												<s:else>
													<select id="so_item_sel" name="workOrder.soItemNo">
														<option value=""></option>
													</select>
												</s:else>
												
												<input type="hidden" name="workOrder.itemType"
													value="${workOrder.itemType}" id="itemType_hidden" />
												<input type="hidden" name="workOrder.clsId"
													value="${workOrder.clsId}" id="clsId_hidden" />
												<input type="hidden" name="workOrder.itemName"
													value="${workOrder.itemName}" id="itemName_hidden" />
											</td>
											
										</tr>
										<tr>
											<th>
												Work Center
											</th>
											<td>
												<s:select id="work_center_sel" name="workOrder.workCenterId"
													list="workCenterList" listKey="id" listValue="name"
													value="workOrder.workCenterId" headerKey=""
													headerValue="Please select..." cssStyle="width: 207px;" disabled="true"></s:select>
													<s:hidden name="workOrder.workCenterId"></s:hidden>
												
											</td>
											<th>
												Work Center Supervisor
											</th>
											<td colspan="2">
											<s:if test="operStatus=='QC'">
											<s:select id="center_super_sel"
													name="workOrder.workCenterSpvs"
													list="workOrder.workCenterSuperList" listKey="value"
													listValue="name" value="workOrder.workCenterSpvs"
													cssStyle="width: 207px;" disabled="true"></s:select>
													<s:hidden name="workOrder.workCenterSpvs"></s:hidden>
											</s:if>
											<s:else>
												<s:select id="center_super_sel"
													name="workOrder.workCenterSpvs"
													list="workOrder.workCenterSuperList" listKey="value"
													listValue="name" value="workOrder.workCenterSpvs"
													cssStyle="width: 207px;"></s:select>
											</s:else>
												
											</td>
										</tr>
										<tr>
											<th>
												Work Group
											</th>
											<td>
												<s:textfield readonly="true" id="workGroupNames" name="workOrder.workGroupNames" cssClass="NFText" size="35"/>
												<s:hidden name="workOrder.workGroupIds" id="workGroupIds"></s:hidden>
											</td>
											<th>
												
											</th>
											<td colspan="2">
												
											</td>
										</tr>
										<tr>
											<th>
												QC Group
											</th>
											<td>
											<s:if test="operStatus=='QC'">
												<s:if test="qaGroupList!=null">
													<s:select id="qc_group_sel" name="workOrder.qcGroup"
													list="qaGroupList" listKey="id" listValue="groupName"
													value="workOrder.qcGroup" headerKey=""
													headerValue="Please select..." cssStyle="width: 207px;" disabled="true"></s:select>
												</s:if>
										        <s:else>
										        	<select id="qc_group_sel" name="workOrder.qcGroup" style="width: 207px;" disabled="disabled">
										        		<option value="">Please select...</option>
										        	</select>
										        </s:else>
													<s:hidden name="workOrder.qcGroup"/>
											</s:if>
											<s:else>
												<s:if test="qaGroupList!=null">
												<s:select id="qc_group_sel" name="workOrder.qcGroup"
													list="qaGroupList" listKey="id" listValue="groupName"
													value="workOrder.qcGroup" headerKey=""
													headerValue="Please select..." cssStyle="width: 207px;"></s:select>
												</s:if>
												<s:else>
													<select id="qc_group_sel" name="workOrder.qcGroup" style="width: 207px;">
														<option value="">Please select...</option>
													</select> 
												</s:else>
											</s:else>
												
											</td>
											<th>
												QC Clerk
											</th>
											<td colspan="2">
											<s:if test="operStatus=='QC'">
											<s:if test="qcClerkList!=null">
												<s:select id="qc_clerk_sel" name="workOrder.qcClerk"
													list="qcClerkList" listKey="userId" listValue="superName"
													value="workOrder.qcClerk" cssStyle="width: 207px;" disabled="true"></s:select>
											</s:if>
											<s:else>
												<select id="qc_clerk_sel" name="workOrder.qcClerk" style="width: 207px;" disabled="disabled">
													<option value="">Please select...</option>
												</select>
											</s:else>	
													<s:hidden name="workOrder.qcClerk"></s:hidden>
											</s:if>
											<s:else>
											<s:if test="qcClerkList!=null">
											<s:select id="qc_clerk_sel" name="workOrder.qcClerk"
													list="qcClerkList" listKey="userId" listValue="superName"
													value="workOrder.qcClerk" cssStyle="width: 207px;"></s:select>
											</s:if>
											<s:else>
												<select id="qc_clerk_sel" name="workOrder.qcClerk" style="width: 207px;">
													<option value="">Please select...</option>
												</select>
											</s:else>	
											</s:else>
												
											</td>
										</tr>
		
										<tr>
											<th>
												Priority
											</th>
											<td>
											<s:select id="workOrder_priority" name="workOrder.priority"
												cssStyle="width: 207px;"
												list="dropDownMap['WORK_ORDER_PRIORITY']" listKey="value"
												listValue="text" value="workOrder.priority" disabled="true"></s:select>
												<s:hidden name="workOrder.priority" id="hidden_priority"></s:hidden>
												
											</td>
											<th>
												Warehouse
											</th>
											<td colspan="2">
											<s:if test="operStatus=='QC'">
												<s:select id="warehouse_sel" name="workOrder.warehouseId"
													list="warehouseList" listKey="warehouseId" listValue="name"
													value="workOrder.warehouseId" cssStyle="width: 207px;" disabled="true"></s:select>
													<s:hidden name="workOrder.warehouseId"></s:hidden>
											</s:if>
											<s:else>
												<s:select id="warehouse_sel" name="workOrder.warehouseId"
													list="warehouseList" listKey="warehouseId" listValue="name"
													value="workOrder.warehouseId" cssStyle="width: 207px;"></s:select>
											</s:else>
												
											</td>
										</tr>
										<tr>
											<th>
												Description
											</th>
											<td>
											<s:if test="operStatus=='QC'">
												<input name="workOrder.description"
													value="${workOrder.description}" type="text" class="NFText"
													size="60" readonly="readonly"/>
											</s:if>
											<s:else>
												<input name="workOrder.description"
													value="${workOrder.description}" type="text" class="NFText"
													size="60" />
											</s:else>
												
											</td>
											<th>
												&nbsp;
											</th>
											<td colspan="2">
												&nbsp;
											</td>
										</tr>
										<tr>
											<th>
												Modified Date
											</th>
											<td>
												<input name="workOrder.modifyDate" type="text"
													class="NFText" style="width: 200px;" readonly="readonly"
													value="<s:date name="workOrder.modifyDate" format="yyyy-MM-dd"/>" />
											</td>
											<th>
												Modified By
											</th>
											<td colspan="2">
												<input name="modifyUser" type="text" class="NFText"
													readonly="readonly" style="width: 200px;"
													value="${workOrder.modifyUser}" />
											</td>
										</tr>
										 <tr>
							              <th><span class="important">Production Notes</span></th>
							              <td colspan="2"><input name="textfield11" type="text" class="NFText" size="35" />
						                  <input type="button" name="Submit7" class="style_botton3" value="View Production Instruction" onclick="view_production_ins();"/></td>
							              <td>&nbsp;</td>
						                </tr>
										<tr>
											<th>
												&nbsp;
											</th>
											<td>
												&nbsp;
											</td>
											<th>
												&nbsp;
											</th>
											<td colspan="2">
												&nbsp;
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<div class="invoice_title">
										<span style="cursor: pointer"
											onclick="toggle_showmore('Goods_InfoItem', 'Goods_Info');"><img
												src="${global_image_url}ad.gif" width="11" height="11"
												id="Goods_InfoItem" /> &nbsp;Product/Service Information</span>
									</div>
									<div id="Goods_Info" class="disp" style="display: block;">
										<table border="0" cellpadding="0" cellspacing="0"
											class="General_table">
											<tr>
												<th width="210">
													Product/Service
												</th>
												<td colspan="2">
													<input name="workOrder.catalogNoDesc" readonly="readonly" value="${workOrder.catalogNoDesc}" id="catalogNo_txt2"
													type="text" class="NFText" size="25" />
													<s:hidden name="workOrder.catalogNo" id="catalogNo_txt"></s:hidden>
													<!-- <img src="images/search.gif" style="cursor: pointer"
														width="16" height="16" id="sel_product_btn"/> -->
												    <input type="button" name="Submit5" class="style_botton"
														value="View" id="view_product_btn" />
												</td>
												<th width="204">
													Storage Location
												</th>
												<td colspan="2">
													<input name="workOrder.storageLocation"
														value="${workOrder.storageLocation}" type="text"
														class="NFText" size="35" />
												</td>
											</tr>

											<tr>
												<th>
													Quantity
												</th>
												<td colspan="2">
													<input name="workOrder.quantity"
														value="${workOrder.quantity}" id="quantity_txt"
														type="text" class="NFText" size="35" readonly="readonly"/>
												</td>
												<th>
													Quantity UOM
												</th>
												<td>
													<input name="workOrder.qtyUom" value="${workOrder.qtyUom}"
														id="qtyUom_txt" type="text" class="NFText" size="35" readonly="readonly"/>
												</td>
												<td>
													&nbsp;
												</td>
											</tr>
											<tr>
												<th>
													Size
												</th>
												<td colspan="2">
													<input name="workOrder.size" value="${workOrder.size}"
														id="size_txt" type="text" class="NFText" size="35" readonly="readonly"/>
												</td>
												<th>
													Size UOM
												</th>
												<td>
													<input name="workOrder.sizeUom"
														value="${workOrder.sizeUom}" id="sizeUom_txt" type="text"
														class="NFText" size="35" readonly="readonly"/>
												</td>
												<td>
													&nbsp;
												</td>
											</tr>
											<tr>
												<th>
													BOM
												</th>
												<td>
													<select name="select10" style="width: 207px;" disabled="disabled">
													</select>
												</td>
												<td>
													<input type="button" name="Submit2" class="style_botton"
														value="Apply" disabled="disabled"/>
												</td>
												<th>
													Routing
												</th>
												<td>
												<s:if test="operStatus=='QC'">
												<s:if test="routeList!=null">
													<s:select id="route_sel" name="workOrder.standardRoutine"
														list="routeList" listKey="id" listValue="name"
														value="workOrder.standardRoutine" cssStyle="width: 207px;" disabled="true"></s:select>
												</s:if>
												<s:else>
													<select name="workOrder.standardRoutine" id="route_sel" disabled="disabled">
														<option value="">Please select...</option>
													</select>
												</s:else>
												</s:if>
												<s:else>
												<s:if test="routeList!=null">
													<s:select id="route_sel" name="workOrder.standardRoutine"
														list="routeList" listKey="id" listValue="name"
														value="workOrder.standardRoutine" cssStyle="width: 207px;"></s:select>
												</s:if>
												<s:else>
													<select name="workOrder.standardRoutine" id="route_sel">
														<option value="">Please select...</option>
													</select>
												</s:else>
												</s:else>
												</td>
												<td>
												<s:if test="operStatus=='QC'">
												<input type="button" name="Submit5" class="style_botton"
														value="Apply" id="routing_apply_btn" disabled="disabled"/>
												</s:if>
												<s:else>
													<input type="button" name="Submit5" class="style_botton"
														value="Apply" id="routing_apply_btn" />
												</s:else>	
												</td>
											</tr>
											<tr>
												<th>
													Quantity Manufactured
												</th>
												<td colspan="2">
													<input name="workOrder.qtyCompleted"
														value="${workOrder.qtyCompleted}" type="text"
														class="NFText" size="35" />
												</td>
												<th>
													Size Manufactured
												</th>
												<td colspan="2">
													<input name="workOrder.sizeCompleted"
														value="${workOrder.sizeCompleted}" type="text"
														class="NFText" size="35" />
												</td>
											</tr>
											<tr>
												<th>
													Lot No
												</th>
												<td>
													<s:select id="workOrder_lot" list="workOrder.workOrderLotList" listKey="id" listValue="lotNo">
													</s:select>
												</td>
												<td>
												<span id="createLotSpan"><input type="button" name="Submit3" class="style_botton"
														value="Create" onclick="showCreateLotNoDialog()" /></span>
												<span id="editLotSpan">
												<input type="button" name="Submit3" class="style_botton"
														value="View/Edit" onclick="showCreateLotNoDialog()" />
												</span>	
													
												</td>
												<th>
													&nbsp;
												</th>
												<td colspan="2">
													&nbsp;
												</td>
											</tr>
											<tr>
												<th>
													Product/Service QC Status
												</th>
												<td colspan="2">
												<s:if test="operStatus=='QC'">
													<s:select list="{'Failed', 'Passed','Partial'}" headerKey=""
														headerValue="Please select..." name="workOrder.productQc"
														value="workOrder.productQc" cssStyle="width:207px"  ></s:select>
												</s:if>
												<s:else>
												<s:select list="{'Failed', 'Passed','Partial'}" headerKey=""
														headerValue="Please select..." name="workOrder.productQc"
														value="workOrder.productQc" cssStyle="width:207px"  disabled="true"></s:select>
												</s:else>
												</td>
												<th>
													Product/Service QC Date
												</th>
												<td colspan="2">
													<input name="workOrder.productQcDate" type="text"  class="NFText" size="35" readonly="readonly" value="<s:date name="workOrder.productQcDate" format="yyyy-MM-dd"/>" />
												</td>
											</tr>
												<tr>
													<th>Comments</th>
													<td colspan="2">
													<s:textarea name="workOrder.comment" cssClass="content_textarea2">
													</s:textarea>
													</td>
												</tr>
										</table>
									</div>
									<div class="invoice_title">
										<span style="cursor: pointer"
											onclick="toggle_showmore('Goods_doc_InfoItem', 'Goods_doc_Info');"><img
												src="${global_image_url}ar.gif" width="11" height="11"
												id="Goods_doc_InfoItem" /> &nbsp;Production Documents</span>
									</div>
									<div id="Goods_doc_Info" class="disp" style="display: none;">
										<table border="0" cellpadding="0" cellspacing="0"
											class="General_table">
											<s:if test="antibodyFlg==1">
												<tr>
											    <th>Experimental Data Type</th>
											    <td>
											    <select name="workOrder.experimentDataType" style="width:207px;" id="select3">
											      <option value="Antiboy">Antiboy</option>
											    </select>
											    </td>
											    <th>&nbsp;</th>
											    <td>&nbsp;</td>
											  </tr>
											  <tr>
											    <th>Host Name</th>
											    <td>
											    <select name="workOrder.hostName" style="width:207px;" id="select4">
											      <option value="Chicken">Chicken</option>
											      <option value="Goat">Goat</option>
											      <option value="Mouse">Mouse</option>
											      <option value="Rabbit" selected="selected">Rabbit</option>
											      <option value="Rat">Rat</option>
											    </select>
											    </td>
											    <th>Host Amount</th>
											    <td><input name="workOrder.hostAmount" value="${workOrder.hostAmount}" type="text" class="NFText" value="2" size="35" /></td>
											  </tr>
											  <tr>
											    <th>Host No</th>
											    <td colspan="2"><input name="workOrder.hostNo" value="${workOrder.hostNo}" type="text" class="NFText" value="4566,4567,456" size="35" />      (e.g.,&quot;4566,4567,4568&quot;)</td>
											    <td>&nbsp;</td>
										</tr>
											</s:if>
											
																						
											<tr>
												<th width="210">
													Production Documents QC Status
												</th>
												<td>
												<s:if test="operStatus=='QC'">
													<s:select list="{'Failed', 'Passed','Partial'}" headerKey=""
														headerValue="Please select..." name="workOrder.documentQc"
														value="workOrder.documentQc" cssStyle="width:207px"></s:select>
												</s:if>
												<s:else>
												<s:select list="{'Failed', 'Passed','Partial'}" headerKey=""
														headerValue="Please select..." name="workOrder.documentQc"
														value="workOrder.documentQc" cssStyle="width:207px" disabled="true"></s:select>
												</s:else>
												</td>
												<th width="276">
													Production Documents QC Date
												</th>
												<td>
													<input name="workOrder.documentQcDate" type="text"  class="NFText" size="35" readonly="readonly" value="<s:date name="workOrder.documentQcDate" format="yyyy-MM-dd"/>" />
												</td>
											</tr>
											<tr>
										    <th>Type</th>
										    <td colspan="2">
										    	<s:select name="manuDoc" id="pdt_docType_sel"
														cssStyle="width:160px" list="pdtDocTypeList"
														listValue="text" listKey="value" headerKey=""
														headerValue="Please select..." />
										    </td>
										    <th>&nbsp;</th>
										    </tr>
										 	
										  <tr>
										    <th>File</th>
										    <td colspan="2">
              								<input name="upload" type="file" id="upload_file" class="type-file-file"/>
              								
											<input name="tmplUploadBtn" type="button" class="style_botton" value="Upload" id="upload_btn" />
											<input type="button" name="Submit4" class="style_botton" value="Delete" onclick="deleteFile()"/></td>
										    <td>&nbsp;</td>
										    </tr>
											<tr>
												<th>
													&nbsp;
												</th>
												<td colspan="3">
													<table id="fileListTable">
														<s:iterator value="workOrder.documentList">
															<tr>
																<td>
																	<input type="checkbox" value="${docId}"/>
																</td>
																<td>
																	<a href="javascript:void(0);" onclick="downLoadFile('${filePath}','${docName}')">${docName}</a>
																</td>
																
																<td>
																	Uploaded By<input name="textfield32255" value="${modifyUser}" type="text" class="NFText" size="20" readonly="readonly"/>
																</td>
																<td>
																	Uploaded Date<input name="textfield3224" type="text" class="NFText" size="20" readonly="readonly" value="<s:date name="modifyDate" format="yyyy-MM-dd"/>" />
																</td>
															</tr>
														</s:iterator>
													</table>
												</td>
											</tr>
										</table>
									</div>
									<div class="invoice_title">
										<span style="cursor: pointer"
											onclick="toggle_showmore('WorkTime_InfoItem', 'WorkTime_Info');"><img
												src="${global_image_url}ar.gif" width="11" height="11"
												id="WorkTime_InfoItem" /> &nbsp;Work Time</span>
									</div>
									<div id="WorkTime_Info" class="disp" style="display: none">
										<table border="0" cellpadding="0" cellspacing="0"
											class="General_table">
											<tr>
												<th width="210">
													Scheduled Start Date
												</th>
												<td>
													<input name="workOrder.scheduleStart"
														id="scheduleStart_date" type="text" readonly="readonly" size=35  class="NFText"
														value="<s:date name="workOrder.scheduleStart" format="yyyy-MM-dd"/>" />
												</td>
												<th width="276">
													Scheduled Complete Date
												</th>
												<td width="130">
													<input name="workOrder.scheduleEnd" id="scheduleEnd_date"
														type="text" readonly="readonly" size=35    class="NFText"
														value="<s:date name="workOrder.scheduleEnd" format="yyyy-MM-dd"/>" />
													
												</td>
												<td><input type="button" name="Submit3" class="style_botton"
														value="Refresh" id="refresh" onclick="refresh22();" />
														<s:hidden name="workOrder.scheduleChangeFlag" id="scheduleChangeFlag"></s:hidden>
														</td>
											</tr>
											<tr>
												<th>
													Actual Start Date
												</th>
												<td>
													<input name="workOrder.actualStart" id="actualStart_date"
														type="text" readonly="readonly" size=35    class="NFText"
														value="<s:date name="workOrder.actualStart" format="yyyy-MM-dd"/>" />
												</td>
												<th>
													Actual Complete Date
												</th>
												<td>
													<input name="workOrder.actualEnd" id="actualEnd_date"
														type="text" readonly="readonly" size=35    class="NFText"
														value="<s:date name="workOrder.actualEnd" format="yyyy-MM-dd"/>" />
												</td>
											</tr>
											
											<tr>
												<th>
													Customized Start Date
												</th>
												<td>
													<input name="workOrder.customStart" id="customStart_date"
														readonly="readonly" size=35 
														type="text" class="NFText ui-datepicker"
														value="<s:date name="workOrder.customStart" format="yyyy-MM-dd"/>" />
														<input type="hidden" id="customStartDate_hidden" value="<s:date name="workOrder.customStart" format="yyyy-MM-dd"/>"/>
												</td>
												<th>
													Customized Complete Date
												</th>
												<td>
													<input name="workOrder.customEnd" id="customEnd_date"
														type="text" class="NFText ui-datepicker" readonly="readonly" size=35 
														value="<s:date name="workOrder.customEnd" format="yyyy-MM-dd"/>" />
														<input type="hidden" id="customEndDate_hidden" value="<s:date name="workOrder.customEnd" format="yyyy-MM-dd"/>"/>
														
												</td>
												<td><input type="button" name="Submit223" value="Date Changed Log" class="style_botton2" id="change_log" /></td>
											</tr>
										</table>
									</div>
									<div class="invoice_title">
										<span style="cursor: pointer"
											onclick="toggle_showmore('Contacts_InfoItem', 'Contacts_Info');"><img
												src="${global_image_url}ar.gif" width="11" height="11"
												id="Contacts_InfoItem" /> &nbsp;Contacts</span>
									</div>
									<div id="Contacts_Info" class="disp" style="display: none">
										<table border="0" cellpadding="0" cellspacing="0"
											class="General_table">
											<tr>
												<th width="188">
													Project Manager
												</th>
												<td width="255">
													<input name="projectSupport"
														value="${workOrder.projectSupport}"
														id="order_projectSupport_text" readonly="readonly"
														type="text" class="NFText" size="35" />
												</td>
												<th width="200">
													Tech Account Manager
												</th>
												<td>
													<input name="techSupport"
														value="${workOrder.techSupport}"
														id="order_techSupport_text" readonly="readonly"
														type="text" class="NFText" size="35" />
												</td>											
											</tr>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<form name="" id="fileDownLoad" method="post">
			<s:hidden name="filePath" id="filePath"></s:hidden>
			<s:hidden name="fileName" id="fileName"></s:hidden>
		</form>
		<s:hidden name="plateId" id="plateId"></s:hidden>
		<div id="create_lot_no_dlg" title="Create Lot No" style="visible: hidden">
			<s:include value="create_lot_no.jsp"></s:include>
		</div>
		<div id="sel_order_dlg" title="Select Order"></div>
		<div id="change_work_order_status" title="Modify Work Order Status" style="visible: hidden">
			<s:include value="change_work_order_status.jsp"></s:include>
		</div>
		<div id="sel_product_dlg" title="Select Product/Service"></div>
		<div id="sel_operation_dlg" title="Select Operation"></div>
		<div id="show_product_dlg" title="Product/Service Information"></div>
		<div id="production_instruction_dlg" title="View production instruction"></div>
		<div id="instruction_update_dlg" title="Instruction/Notes"></div>
		<div id="innerOrder_operation_dlg" title="View Operation"></div>
		<div id="update_request_log_dlg" title="Records causes"></div>
		<div id="customized_date_Log_dlg" title="Customized Date Changed Log"></div>
		<div id="dhtmlgoodies_tabView1">
			<div class="dhtmlgoodies_aTab">
			<iframe width="100%" height="310px" id="operation_list_frame"
				name="operation_list_frame" scrolling="no" frameborder="0"
				src="workorder_operation!getOperationList.action?sessId=${sessId}&antibodyFlg=${antibodyFlg}"></iframe>
			</div>
		</div>
		<script type="text/javascript"> 
	var global_url = "${global_url}" ;
	initTabs('dhtmlgoodies_tabView1',Array('Operations'),0,1350,320); 
	var isSaved = false;
	window.onbeforeunload = function() {
		if(isSaved === false){
			return 'Do you want to leave without saving data?';
		}
	}
	fileinput("upload_file","textfield");

    $(function() {
    	if($("#workOrder_lot").val()==null||$("#workOrder_lot").val()=="") {
    		$("#createLotSpan").show();
    		$("#editLotSpan").hide();
    	} else {
    		$("#createLotSpan").hide();
    		$("#editLotSpan").show();
    	}
    	if($("#lotNo").val()==null||$("#lotNo").val()=="") {
    		$("#showCreate").show();
    		$("#showRemove").hide();
    	} else {
    		$("#showRemove").hide();
    		$("#showCreate").show();
    	}
    	if($("#status_sel").val()=="Canceled") {
    		$("#routing_apply_btn").attr("disabled",true);
    	}
    	
    	 $("select").each(function(){
 			var changeWidth=false;
 	   		var len = this.offsetWidth;
 	   		if(len!=0) {
	 	   		this.style.width = 'auto';
	 	   		if(len<this.offsetWidth) {
	 	   			changeWidth = true;
	 	   		}
	 	   		this.style.width=len+"px";
	 	   		$(this).mousedown(function(){
	 	   			if(changeWidth) {
	 	   				this.style.width = 'auto';
	 	   			}
	 	   			});
	 	   		$(this).blur(function() {this.style.width = len+"px";});
	 	   		$(this).change(function(){this.style.width = len+"px";});
 	   		}
 	   		
 	   	});
    	 
    	 $("#customStart_date").change(function(){
    		 if($("#workOrderNo").val()!=null&&$("#customStart_date").val()!=$("#customStartDate_hidden").val()) {
            	 var url = "${global_url}/system/update_request_log!initDialog.action?updateRequestLog.objectEntity=WorkOrder&updateRequestLog.field=customStartDate&updateRequestLog.objectId="
            			 +$("#workOrderNo").val()+"&updateRequestLog.originalValue="
 						+$("#customStartDate_hidden").val()+"&updateRequestLog.newValue="+$("#customStart_date").val()+"&changeType=customStart_date";
            	 $('#update_request_log_dlg').dialog("option", "open", function() {
             		 var htmlStr = '<iframe src="'+url+'" id="updateRequestFrame"  height="100%" width="100%" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
             		 $('#update_request_log_dlg').html(htmlStr);
            	});
            	$('#update_request_log_dlg').dialog('open');
            	$("#customStart_date").val($("#customStartDate_hidden").val());
             }
    	 });
    	 
    	 $("#customEnd_date").change(function(){
    		 if($("#workOrderNo").val()!=""&&$("#customEnd_date").val()!=$("#customEndDate_hidden").val()) {
               	 var url = "${global_url}/system/update_request_log!initDialog.action?updateRequestLog.objectEntity=WorkOrder&updateRequestLog.field=customEndDate&updateRequestLog.objectId="
               			 +$("#workOrderNo").val()+"&updateRequestLog.originalValue="
	     						+$("#customEndDate_hidden").val()+"&updateRequestLog.newValue="+$("#customEnd_date").val()+"&changeType=customEnd_date";
               	 $('#update_request_log_dlg').dialog("option", "open", function() {
                		 var htmlStr = '<iframe src="'+url+'" id="selectUserFrame"  height="100%" width="100%" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
                		 $('#update_request_log_dlg').html(htmlStr);
               	});
               	$('#update_request_log_dlg').dialog('open');
               	$("#customEnd_date").val($("#customEndDate_hidden").val());
    		 }
    		 
    	 });
    	 
    	 $("#change_log").click(function(){
         	if(!isNaN($("#workOrderNo").val())) {
         		var url = "workorder_operation!customDateChangeLog.action?id="+$("#workOrderNo").val();
	          	 $('#customized_date_Log_dlg').dialog("option", "open", function() {
	           		 var htmlStr = '<iframe src="'+url+'" id="selectUserFrame"  height="100%" width="100%" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
	           		 $('#customized_date_Log_dlg').html(htmlStr);
	          	});
	          	$('#customized_date_Log_dlg').dialog('open');
         	}
         	
         });

        $('#ShowDetailDialog').dialog({
                 	autoOpen: false,
					height: 700,
					width: 1000,
					modal: true,
					bgiframe: true,
                    resize:'auto',
                   buttons: {
                	   "Close": function() {
                           $(this).dialog('close');
                  		}
                   }
               });
           $("#ShowDetailTrigger").click (function() {
               var ids=$("#order_orderNo_text").val();
               if(ids!=null && ids !=""){
                 $('#ShowDetailDialog').dialog("option", "open", function() {
             		 var htmlStr = '<iframe src="${global_url}/order/order!edit.action?orderNo='+ids+'&operation_method=view&lookCust=N&lookFromWoFlag=1" id="selectUserFrame"  height="100%" width="1000" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
             		 $('#ShowDetailDialog').html(htmlStr);
            	});
            	 $('#ShowDetailDialog').dialog('open');
                   return true;
               }else{
                   alert("Please select an sales order no!");
                   return false;
               }
           });
           
           $('#update_request_log_dlg').dialog({
       		autoOpen: false,
       		height: 250,
       		width: 620,
       		modal: true,
       		bgiframe: true,
       		buttons: {
       		}
       	});
       	
       	$("#customized_date_Log_dlg").dialog({
       		autoOpen: false,
       		height: 350,
       		width: 620,
       		modal: true,
       		bgiframe: true,
       		buttons: {
       		}
       	});
           
          
    });
	window.onunload = function() {
	   if (isSaved) {//其它内部操作， 例： New一个新的Operation.
	      return;
	   }
	   $.ajax({
			type: "POST",
			url: global_url + "workorder_entry!cancelSave.action",
			data: "sessId=${sessId}",
			success: function(data, textStatus){
				
			},
			error: function(xhr, textStatus){
			   alert("failure");
			   return;
			}
		});
	}

	
	function lotNoSel() {
		if($("#lotNo").val()=="") {
			$("#showCreate").show();
			$("#showRemove").hide();
			$("#lotDesc").attr("value","");
			$("#quantity").attr("value","");
			$("#departmentCode").get(0).selectedIndex=0;
			$("#workGroupCode").get(0).selectedIndex=0;
			$("#storageTemp").get(0).selectedIndex=0;
			$("#shipTemp").get(0).selectedIndex=0;
			$("#creationDate").attr("value","");
			$("#createdByName").attr("value","");
			$("#create_lot_no_dlg").attr("title","Create Lot No");
		} else {
			$("#create_lot_no_dlg").attr("title","Edit Lot No");
			$("#showCreate").hide();
			$("#showRemove").show();
			$.ajax({
				type:"get",
				url:global_url+"workorder_entry!lotNoChange.action?workOrderLotSessId="+$("#lotNo").val()+"&sessId="+$("#sessId").val(),
				dataType:'json',
				success:function(data,textStatus) {
					$("#lotDesc").attr("value",data.workOrderLot.lotDesc);
					$("#quantity").attr("value",data.workOrderLot.quantity);
					$("#departmentCode").get(0).value=data.workOrderLot.departmentCode;
					$("#workGroupCode").get(0).value=data.workOrderLot.workGroupCode;
					$("#storageTemp").get(0).value=data.workOrderLot.storageTemperature;
					$("#shipTemp").get(0).value=data.workOrderLot.shipTemperature;
					$("#creationDate").attr("value",data.workOrderLot.creationDateStr);
					$("#createdByName").attr("value",data.workOrderLot.createdByName);
				},
				error:function(xhr, textStatus) {
					alert("failure!");
				}
				
			});
		}
	}
 	function createLot () {
 		var formData = "sessId="+$("#sessId").val()+"&workOrderLot.departmentCode="+$("#departmentCode").val()
 						+"&workOrderLot.workGroupCode="+$("#workGroupCode").val()+"&workOrderLot.lotDesc="+$("#lotDesc").val()
 						+"&workOrderLot.quantity="+$("#quantity").val()
 						+"&workOrderLot.storageTemperature="+$("#storageTemp").val()+"&workOrderLot.shipTemperature="+$("#shipTemp").val()
 						+"&workOrderLotSessId="+$("#lotNo").val();
		$.ajax({
			type: "POST",
			url: global_url + "workorder_entry!createLotNo.action",
			data:formData,
			dataType: 'json',
			success: function(data, textStatus){
				if(data.hasException){
		 	        alert("Program throw exception,create failure!");			
				}else{
					if($("#lotNo").val()!="") {
						$("#lotNo").get(0).value=data.key;
						$("#lotNo").trigger("change");
					}else if (data.message=="true") {
						$("#lotNo").get(0).options.add(new Option(data.lotNo,data.key));
						$("#lotNo").get(0).value=data.key;
						$("#creationDate").val(data.creationDate);
						$("#createdByName").val(data.createdByName);
						$("#lotNo").trigger("change");
				   	} else {
						alert("failure!");
					}	
				}
			},
			error: function(xhr, textStatus){
			   alert("Error, failure!");
			   return;
			}
		});
 	}
 	
 	function removeLot() {
 		var formData = "sessId="+$("#sessId").val()+"&workOrderLotSessId="+$("#lotNo").val();
 		$.ajax({
			type: "POST",
			url: global_url + "workorder_entry!delWorkOrderLot.action",
			data:formData,
			dataType: 'json',
			success: function(data, textStatus){
				var index=$("#lotNo").get(0).selectedIndex;
				$("#lotNo").get(0).remove(index);
				$("#lotNo").trigger("change");
			},
			error: function(xhr, textStatus){
			   alert("Error, failure!");
			   return;
			}
		});
 	}

 	
 	function confirmLotNo() {
 		$.ajax({
			type:"POST",
			url:global_url+"workorder_entry!confirmLotNo.action?sessId="+$("#sessId").val(),
			dataType:'json',
			success:function(data,textStatus) {
				$("#workOrder_lot").empty();
				$("#lotNo option").each(function(){
					if(this.value!="") {
						$("#workOrder_lot").get(0).options.add(new Option(this.text,this.value));
					}
				});
				if($("#workOrder_lot").get(0).options.length!=0) {
					$("#editLotSpan").show();
					$("#createLotSpan").hide();
				}
		 		$("#create_lot_no_dlg").dialog("close");
			},
			error:function(xhr, textStatus) {
				 alert("Error, failure!");
			}
			
		});
 		
 	}
 	function showCreateLotNoDialog() {
 		$.ajax({
 			type:"POST",
 			url:global_url+"workorder_entry!clearWorkOrderLotTemp.action?sessId="+$("#sessId").val(),
 			dataType:"json",
 			success:function(data,textStatus) {
 				$("#lotNo").empty();
				$("#lotNo").get(0).options.add(new Option("Add Lot No",""),0);
				$("#workOrder_lot option").each(function(){
					if(this.value!="") {
						$("#lotNo").get(0).options.add(new Option(this.text,this.value));
					}
				});
				if($("#workOrder_lot option").length>0) {
					$("#lotNo").val($("#workOrder_lot").val());
				}
 				$("#lotNo").trigger("change");
 				$("#create_lot_no_dlg").dialog("open");
 			},
 			error:function(xhr, textStatus) {
 				alert("Error");
 				return;
 			}
 		});
 		
 	}
 	function showModifyStatusDialog() {
 		if($("#staus_sel").val()=="Canceled") {
 			alert("The Work order is Canceled");
 			return;
 		}
 		$("#change_work_order_status").dialog("open");
 	}
 	
 	function modifyStatus() {
 		var reason = $("#reason").val();
 		if($("#status_sel").val()==$("#status_sel_dlg").val()) {
 			$("#change_work_order_status").dialog("close");
 			return;
 		}
 		if($("#status_sel").val()=="In Production"&&$("#status_sel_dlg").val()=="New") {
 			if(!confirm('Are you sure to change the status backward?')) {
 				$("#change_work_order_status").dialog("close");
 			}
 		}
 		if($("#status_sel").val()=="Production Complete"&&($("#status_sel_dlg").val()=="New"||$("#status_sel_dlg").val()=="In Production")) {
 			if(!confirm('Are you sure to change the status backward?')) {
 				$("#change_work_order_status").dialog("close");
 			}
 		}
 		if(reason=="") {
 			alert("Please enter the reason.");
 			return;
 		}
		$("#status_sel").val($("#status_sel_dlg").val());
		$("#hidden_status").attr("value",$("#status_sel_dlg").val());
 		$("#hidden_reason").attr("value",$("#reason").val());
 		$("#change_work_order_status").dialog("close");
 	}
 	
 	function downLoadFile(filePath,fileName) {
 		isSaved = true;
		$("#fileName").val(fileName);
		$("#filePath").val(filePath);
		var url = "download.action";
		$("#fileDownLoad").attr('action',url);
        $("#fileDownLoad").submit();
 	}
 	
 	function refresh22() {
 		$("#refresh").attr("disabled","disabled");
 		$.ajax({
			type: "POST",
			url: "workorder_entry!refreshTime.action",
			data:"sessWorkOrderNo="+$("#sessId").val(),
			dataType: 'json',
			success: function(data, textStatus){
				$("#scheduleEnd_date").val(data.ScheduleEnd);
				$("#scheduleChangeFlag").attr("value","1");
				$("#refresh").attr("disabled","");
			},
			error: function(xhr, textStatus){
			   alert("failure!");
			   $("#refresh").attr("disabled","");
			   return;
			}
		});
 	}
</script>

		<div class="button_box">
			<saveButton:saveBtn parameter="${operation_method}"
				disabledBtn='<input type="button" name="Submit123" value="Save" class="search_input" disabled="disabled"/>'
				saveBtn='<input type="button" id="save_btn" name="Submit123" value="Save" class="search_input" />' />
			<input type="submit" name="Submit124" value="Cancel"
				class="search_input"
				<s:if test="plateId==null&&(operStatus==null||operStatus=='')">
				onclick="window.location = 'workorder_proc!search.action';"
				</s:if>
				<s:elseif test="operStatus=='QC'">
				onclick="window.location = 'workorder_qc!search.action';"
				</s:elseif>
				<s:else>onclick='javascript:history.go(-1);'</s:else>
				 />
		</div>
	</body>
</html>