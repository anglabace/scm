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
			<s:include value="workOrder_config.jsp"></s:include>
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
					Work Order<s:if test="workOrder.orderNo!=null">-<s:property value="workOrder.orderNo"/></s:if>
				</div>
			</div>

			<div class="input_box">
				<div class="content_box">
					<form id="save_form" method="post" class="niceform"
						enctype="multipart/form-data">
						<s:hidden name="workOrder.creationDate"></s:hidden>
						<s:hidden name="workOrder.createdBy"></s:hidden>
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
												<input type="hidden" name="workOrder.orderNo" id="workOrderNo" value="${workOrder.orderNo}"/>
												<input name="workOrder.altOrderNo" id="altOrderNo" type="text"
													class="NFText" value="${workOrder.altOrderNo}"
													readonly="readonly" disabled="disabled" size="35" />
												<input type="hidden" name="sessId" value="${sessId}"
													id="sessId" />
											</td>
											<th width="150">
												Work Order Type
											</th>
											<td colspan="2">
												<s:select id="type_sel" name="workOrder.type" 
													disabled="true"
													cssStyle="width: 207px;"
													list="dropDownMap['WORK_ORDER_TYPE']" listKey="value"
													listValue="text" value="workOrder.type"></s:select>
											</td>
										</tr>
										<tr>
											<th valign="top">
												Source
											</th>
											<td>
												<s:select id="source_sel" name="workOrder.source"
													disabled="true"
													cssStyle="width: 207px;"
													list="dropDownMap['WORK_ORDER_SOURCE']" listKey="value"
													listValue="text" value="workOrder.source"></s:select>
											</td>
											<th>
												Status
											</th>
											<td>
												<s:select id="status_sel" name="workOrder.status"
													disabled="true"
													cssStyle="width: 207px;"
													list="dropDownMap['WORK_ORDER_STATUS']" listKey="value"
													listValue="text" value="workOrder.status"></s:select>
											</td>
											<td>
												<input type="button" name="Submit42" class="style_botton"
													value="Modify" disabled="disabled"/>
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
													<input readonly="readonly" name="workOrder.soItemNo"
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
													disabled="true"
													list="workCenterList" listKey="id" listValue="name"
													value="workOrder.workCenterId" headerKey=""
													headerValue="Please select..." cssStyle="width: 207px;"></s:select>
											</td>
											<th>
												Work Center Supervisor
											</th>
											<td colspan="2">
												<s:select id="center_super_sel"
													disabled="true"
													name="workOrder.workCenterSpvs"
													list="workOrder.workCenterSuperList" listKey="value"
													listValue="name" value="workOrder.workCenterSpvs"
													cssStyle="width: 207px;"></s:select>
											</td>
										</tr>
										<tr>
											<th>
												Work Group
											</th>
											<td>
												<s:textfield readonly="true" id="workGroupNames" name="workOrder.workGroupNames" cssClass="NFText" size="35"/>
												<s:hidden name="workOrder.workGroupIds" id="workGroupIds"></s:hidden>
												<s:hidden name="processFlag" id="processFlag"></s:hidden>
												<input type="button" name="Submit8" class="style_botton" value="Assign" onclick="assignWorkGroup()"/>
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
											<s:if test="qaGroupList!=null">
												<s:select id="qc_group_sel" name="workOrder.qcGroup"
													disabled="true"
													list="qaGroupList" listKey="id" listValue="groupName"
													value="workOrder.qcGroup" headerKey=""
													headerValue="Please select..." cssStyle="width: 207px;"></s:select>
											</s:if>
											<s:else>
											<select id="qc_group_sel" name="workOrder.qcGroup" disabled="disabled">
											 <option value="">Please select...</option>
											</select>
											</s:else>
											</td>
											<th>
												QC Clerk
											</th>
											<td colspan="2">
											<s:if test="qcClerkList!=null">
											<s:select id="qc_clerk_sel" name="workOrder.qcClerk"
													disabled="true"
													list="qcClerkList" listKey="userId" listValue="superName"
													value="workOrder.qcClerk" cssStyle="width: 207px;"></s:select>
											</s:if>
											<s:else>
												<select id="qc_clerk_sel" name="workOrder.qcClerk" disabled="disabled">
													<option value="">Please select...</option>
												</select>
											</s:else>
											</td>
										</tr>
										
										<tr>
											<th>
												Priority
											</th>
											<td>
												<s:select id="workOrder_priority" name="workOrder.priority"
													disabled="true"
													cssStyle="width: 207px;"
													list="dropDownMap['WORK_ORDER_PRIORITY']" listKey="value"
													listValue="text" value="workOrder.priority"></s:select>
											</td>
											<th>
												Warehouse
											</th>
											<td colspan="2">
												<s:select id="warehouse_sel" name="workOrder.warehouseId"
													disabled="true"
													list="warehouseList" listKey="warehouseId" listValue="name"
													value="workOrder.warehouseId" cssStyle="width: 207px;"></s:select>
											</td>
										</tr>
										<tr>
											<th>
												Description
											</th>
											<td>
												<input name="workOrder.description" disabled="disabled"
													value="${workOrder.description}" type="text" class="NFText"
													size="60" />
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
													disabled="disabled"
													class="NFText" style="width: 200px;" readonly="readonly"
													value="<s:date name="workOrder.modifyDate" format="yyyy-MM-dd"/>" />
											</td>
											<th>
												Modified By
											</th>
											<td colspan="2">
												<input name="modifyUser" type="text" class="NFText"
													disabled="disabled"
													readonly="readonly" style="width: 200px;"
													value="${workOrder.modifyUser}" />
											</td>
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
													<input name="workOrder.catalogNo" readonly="readonly"
														disabled="disabled"
														value="${workOrder.catalogNo}" id="catalogNo_txt"
														type="text" class="NFText" size="35" />
													<!-- <img src="images/search.gif" style="cursor: pointer"
														width="16" height="16" /> -->
												</td>
												<th width="204">
													Storage Location
												</th>
												<td colspan="2">
													<input name="workOrder.storageLocation"
														disabled="disabled"
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
														disabled="disabled"
														value="${workOrder.quantity}" id="quantity_txt"
														type="text" class="NFText" size="35" />
												</td>
												<th>
													Quantity UOM
												</th>
												<td>
													<input name="workOrder.qtyUom" value="${workOrder.qtyUom}"
														disabled="disabled"
														id="qtyUom_txt" type="text" class="NFText" size="35" />
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
														disabled="disabled"
														id="size_txt" type="text" class="NFText" size="35" />
												</td>
												<th>
													Size UOM
												</th>
												<td>
													<input name="workOrder.sizeUom"
														disabled="disabled"
														value="${workOrder.sizeUom}" id="sizeUom_txt" type="text"
														class="NFText" size="35" />
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
													<select name="select10" disabled="disabled" style="width: 207px;">
													</select>
												</td>
												<td>
													<input type="button" disabled="disabled" name="Submit2" class="style_botton"
														value="Apply" />
												</td>
												<th>
													Routing
												</th>
												<td>
												<s:if test="routeList!=null">
													<s:select id="route_sel" name="workOrder.standardRoutine"
														disabled="true"
														list="routeList" listKey="id" listValue="name"
														value="workOrder.standardRoutine" cssStyle="width: 207px;"></s:select>
												</s:if>
												<s:else>
													<select name="workOrder.standardRoutine" id="route_sel" disabled="disabled">
														<option value="">Please select...</option>
													</select>
												</s:else>
												</td>
												<td>
													<input type="button" name="Submit5" class="style_botton"
														disabled="disabled"
														value="Apply" id="routing_apply_btn" />
												</td>
											</tr>
											<tr>
												<th>
													Quantity Manufactured
												</th>
												<td colspan="2">
													<input name="workOrder.qtyCompleted"
														disabled="disabled"
														value="${workOrder.qtyCompleted}" type="text"
														class="NFText" size="35" />
												</td>
												<th>
													Size Manufactured
												</th>
												<td colspan="2">
													<input name="workOrder.sizeCompleted"
														disabled="disabled"
														value="${workOrder.sizeCompleted}" type="text"
														class="NFText" size="35" />
												</td>
											</tr>
			
											<tr>
												<th>
													Product/Service QC Status
												</th>
												<td colspan="2">
													<s:select list="{'Failed', 'Passed','Partial'}" headerKey=""
														disabled="true"
														headerValue="Please select..." name="workOrder.productQc"
														value="workOrder.productQc" cssStyle="width:207px"></s:select>
												</td>
												<th>
													Product/Service QC Date
												</th>
												<td colspan="2">
													<input name="workOrder.productQcDate" type="text"  class="NFText" size="35" readonly="readonly" value="<s:date name="workOrder.productQcDate" format="yyyy-MM-dd"/>" />
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
													<s:select list="{'Failed', 'Passed','Partial'}" headerKey=""
														disabled="true"
														headerValue="Please select..." name="workOrder.documentQc"
														value="workOrder.documentQc" cssStyle="width:207px"></s:select>
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
											<input name="tmplUploadBtn" type="button" class="style_botton" value="Upload" id="upload_btn" disabled="disabled"/>
											<input type="button" name="Submit4" class="style_botton" value="Delete" onclick="deleteFile()" disabled="disabled"/></td>
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
                                            <s:if test="workOrder.orderNo!=null && workOrder.clsId == 1">
                                            <tr>
                                                <th>
													&nbsp;
												</th>
                                                <Td><input name="operation_table" type="button" class="style_botton1" value="View operation tab" id="operation_table" onclick="tableList_downLoad('op');"/></Td>
                                                <Td colspan="2"><input name="qc_table" type="button" class="style_botton1" value="View QC tab" id="qc_table" onclick="tableList_downLoad('qc');"/></Td>
                                            </tr>
                                            </s:if>
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
														id="scheduleStart_date" type="text"
														disabled="disabled"
														class="NFText ui-datepicker" style="width: 200px;"
														value="<s:date name="workOrder.scheduleStart" format="yyyy-MM-dd"/>" />
												</td>
												<th width="276">
													Scheduled Complete Date
												</th>
												<td width="130">
													<input name="workOrder.scheduleEnd" id="scheduleEnd_date"
														disabled="disabled"
														type="text" class="NFText ui-datepicker"
														style="width: 200px;"
														value="<s:date name="workOrder.scheduleEnd" format="yyyy-MM-dd"/>" />
												</td>
											</tr>
											<tr>
												<th>
													Actual Start Date
												</th>
												<td>
													<input name="workOrder.actualStart" id="actualStart_date"
														disabled="disabled"
														type="text" class="NFText ui-datepicker"
														style="width: 200px;"
														value="<s:date name="workOrder.actualStart" format="yyyy-MM-dd"/>" />
												</td>
												<th>
													Actual Complete Date
												</th>
												<td>
													<input name="workOrder.actualEnd" id="actualEnd_date"
														type="text" class="NFText ui-datepicker"
														style="width: 200px;" disabled="disabled"
														value="<s:date name="workOrder.actualEnd" format="yyyy-MM-dd"/>" />
												</td>
											</tr>
											<tr>
												<th>
													Customized Start Date
												</th>
												<td>
													<input name="workOrder.customStart" id="customStart_date"
														disabled="disabled"
														type="text" class="NFText ui-datepicker"
														style="width: 200px;"
														value="<s:date name="workOrder.customStart" format="yyyy-MM-dd"/>" />
												</td>
												<th>
													Customized Complete Date
												</th>
												<td>
													<input name="workOrder.customEnd" id="customEnd_date"
														type="text" class="NFText ui-datepicker"
														style="width: 200px;" disabled="disabled"
														value="<s:date name="workOrder.customEnd" format="yyyy-MM-dd"/>" />
												</td>
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
									
										<script type="text/javascript"> 
										    $(function() {
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
										           
										          
										    });
										</script>
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
		<div id="ShowDetailDialog" title="Show Detail Dialog"></div>
		<div id="sel_order_dlg" title="Select Order"></div>
		<div id="sel_product_dlg" title="Select Product/Service"></div>
		<div id="sel_operation_dlg" title="Select Operation"></div>
		<div id="instruction_dlg" title="Send supply email"> </div> 
		<div id="work_group_assign_dlg" title="Assign Work Group"></div>
		<div id="dhtmlgoodies_tabView1">
			<div class="dhtmlgoodies_aTab">
				<iframe width="100%" height="310px" id="operation_list_frame"
					name="operation_list_frame" scrolling="no" frameborder="0"
					src="workorder_operation!getOperationTaskList.action?sessId=${sessId}&antibodyFlg=${antibodyFlg}"></iframe>
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

	function assignWorkGroup() {
		var centerId = $("#work_center_sel").val();
		if(centerId==null||centerId=="") {
			alert("Please select a work center first.");
			return;
		}
		var workGroupNames = $("#workGroupNames").val();
		var workGroupIds = $("#workGroupIds").val();
		 $('#work_group_assign_dlg').dialog("option", "open", function() {		    
	            var htmlStr = '<iframe src="workorder_entry!showWorkGroupAssign.action?centerId='+centerId+'&workGroupNames='+workGroupNames+'&workGroupIds='+workGroupIds+'" height="100%" width="100%" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
	            $('#work_group_assign_dlg').html(htmlStr);
		    });
		    $('#work_group_assign_dlg').dialog('open');
	}

 	function createLotNo () {
 	 	var workOrderNo = $("#workOrderNo").val();
 	 	var orderOrderNo = $('#order_orderNo_text').val();
 	 	var soItemSel = $("#so_item_sel").val();
 	 	
		$.ajax({
			type: "POST",
			url: global_url + "workorder_entry!createLotNo.action",
			data: "id="+workOrderNo+"&itemNo="+soItemSel+"&orderOrderNo="+orderOrderNo,
			dataType: 'json',
			success: function(data, textStatus){
				if(data.hasException){
		 	        alert("Program throw exception,create failure!");			
				}else{
					if (data.lotNo != undefined && data.lotNo != "" && data.lotNo != null && data.lotNo != "null") {
						$("#lotNo").val(data.lotNo);
						$("#hidden_lotNo").val(data.lotNo);
				   	} else if (data.rtnMessage != undefined) {
				   		alert("Create lotNo failure!"+data.rtnMessage);
					} else {
						alert("Create lotNo failure!");
					}	
				}
			},
			error: function(xhr, textStatus){
			   alert("Error, Create lotNo failure!");
			   return;
			}
		});
 	}
 	
 	function downLoadFile(filePath,fileName) {
 		isSaved = true;
		$("#fileName").val(fileName);
		$("#filePath").val(filePath);
		var url = "download.action";
		$("#fileDownLoad").attr('action',url);
        $("#fileDownLoad").submit();
 	}
</script>

		<div class="button_box">
			<saveButton:saveBtn parameter="${operation_method}"
				disabledBtn='<input type="button" name="Submit123" value="Save" class="search_input" disabled="disabled"/>'
				saveBtn='<input type="button" id="save_task_btn" name="Submit123" value="Save" class="search_input" />' />
			<input type="submit" name="Submit124" value="Cancel"
				class="search_input"
				onclick='window.location="workorder_proc!searchTask.action";' />
		</div>
    <iframe id="tableDownload" name="downLoadFile" src="" height="0" width="0" scrolling="no" style="border:0px" frameborder="0"></iframe>
	</body>
</html>