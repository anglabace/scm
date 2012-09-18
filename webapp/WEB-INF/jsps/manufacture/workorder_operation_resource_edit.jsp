<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Production Resources</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="${global_js_url}jquery/jquery.js"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
		<script src="${global_js_url}jquery/jquery.validate.js?v=1"
			type="text/javascript"></script>
		<script type="text/javascript" src="${global_js_url}show_tag.js"></script>
		<script src="${global_js_url}jquery/ui/ui.datepicker.js"
			type="text/javascript"></script>
		<script type="text/javascript">
	        var baseUrl="${global_url}";	
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
            	
                $('.ui-datepicker').each(function(){
					$(this).datepicker(
					{
					dateFormat: 'yy-mm-dd',
					changeMonth: true,
					changeYear: true
					});
				});
				
                 //绑定保存按钮事件.
                $("#save_btn").click (function() {
                    var formStr = $('#resource_form').serialize();
                    $('#save_btn').attr("disabled", true);
                   $.ajax({
						type: "POST",
						url: "workorder_operation!saveResource.action",
						data: formStr,
						dataType: 'json',
						success: function(data, textStatus){
							if(hasException(data)){//有错误信息.
				 	           $('#save_btn').attr("disabled", false);				
							}else{                              
							  //alert(data.message);
							  parent.$('#edit_res_dlg').dialog('close');
							  var url = parent.$("#resource_list_frame").attr("src");
							  parent.frames["resource_list_frame"].location = url;
							}
						},
						error: function(xhr, textStatus){
						   alert("failure");
				 	       $('#save_btn').attr("disabled", false);
						   return;
						}
					});
                
                });//end of {$("#save_btn").click};             
            
            });
        </script>
	</head>
	<body>
		<div>
			<form method="get" id="resource_form" class="niceform">
				<div class="input_box">
					<div class="content_box">
						<input type="hidden" name="woResource.resource.resourceId"
							value="${woResource.resource.resourceId}" />
						<input type="hidden" name="sessWOPKey" id="sessWOPKey"
							value="${param.sessWOPKey}" />
						<input type="hidden" name="sessResKey" id="sessResKey"
							value="${param.sessResKey}" />
						<input type="hidden" name="woResource.woOperationId"
							id="woOperationId" value="${woResource.woOperationId}" />
						<input type="hidden" name="woResource.seqNo" id="seqNo"
							value="${woResource.seqNo}" />



						<table border="0" cellpadding="5" cellspacing="0"
							class="General_table" id="edit_tbl">
							<tr>
								<th width="160">
									Resource No
								</th>
								<td>
									<input readonly="readonly" id="res_resourceNo" type="text"
										class="NFText" value="${woResource.resource.resourceNo}"
										size="20" />
								</td>
								<th width="150">
									Status
								</th>
								<td>
									<s:select list="{'ACTIVE', 'INACTIVE'}"
										value="woResource.resource.status" disabled="true"></s:select>
								</td>
							</tr>
							<tr>
								<th>
									Name
								</th>
								<td>
									<input id="res_name" type="text" class="NFText"
										name="woResource.resource.name"
										value="${woResource.resource.name}" disabled="true" size="20" />
								</td>
								<th>
									Resource Group
								</th>
								<td>
									<s:select id="resource_group"
										list="dropDownMap['RESOURCE_GROUP']" listKey="value"
										listValue="text" value="woResource.resource.group" disabled="true"></s:select>
								</td>
							</tr>
							<tr>
								<th valign="top">
									Description
								</th>
								<td>
									<input type="text" class="NFText" size="20"
										value="${woResource.resource.description}" disabled="true" />
								</td>
								<th>
									Location
								</th>
								<td>
									<input name="woResource.location" type="text" class="NFText2"
										value="${woResource.location}" size="20" />
								</td>
							</tr>
							<tr>
								<th valign="top">
									Per Item Quantity
								</th>
								<td>
									<input name="woResource.perItemQty" type="text" class="NFText2"
										value="${woResource.perItemQty}" size="20" />
								</td>
								<th>
									UOM
								</th>
								<td>
									<s:select id="resource_uom" list="dropDownMap['RESOURCE_UOM']"
										listKey="value" listValue="text"
										value="woResource.resource.uom" disabled="true"></s:select>
								</td>
							</tr>
							<tr>
								<th valign="top">
									Quantity Issued
								</th>
								<td>
									<input name="woResource.qtyIssued" type="text" class="NFText2"
										value="${woResource.qtyIssued}" size="20" />
								</td>
								<th>
									Quantity Used
								</th>
								<td>
									<input name="woResource.qtyUsed" type="text" class="NFText2"
										value="${woResource.qtyUsed}" size="20" />
								</td>
							</tr>


							<tr>
								<th valign="top">
									Cost
								</th>
								<td>
									<input type="text" class="NFText2"
										value="${woResource.resource.cost}" size="20" readonly="readonly" />
								</td>
								<th>
									Cost Basis
								</th>
								<td>
									<s:select id="resource_costBasis"
										list="dropDownMap['RESOURCE_COST_BASIS']" listKey="value"
										listValue="text" value="woResource.resource.costBasis" disabled="true"></s:select>
								</td>
							</tr>
							<tr>
								<th>
									Currency
								</th>
								<td>
									<s:select list="#request.currencyList" listKey="currencyCode"
										listValue="currencyCode" value="woResource.resource.currency" disabled="true"></s:select>
								</td>
								<th>

								</th>
								<td>

								</td>

							</tr>
							<tr>
								<th>
									Modified Date
								</th>
								<td>
									<input name="textfield322" type="text" class="NFText"
										value="<s:date name="woResource.resource.modifyDate" format="yyyy-MM-dd"/>"
										size="20" readonly="readonly" />
								</td>
								<th>
									Modified By
								</th>
								<td>
									<input name="textfield332" type="text" class="NFText"
										value="${woResource.resource.modifyUser}" readonly="readonly"
										size="20" />
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="invoice_title">
					<span style="cursor: pointer"
						onclick="toggle_showmore('WorkTime_InfoItem', 'WorkTime_Info');"><img
							src="${global_image_url}ad.gif" width="11" height="11"
							id="WorkTime_InfoItem" /> &nbsp;Work Time</span>
				</div>
				<div id="WorkTime_Info" class="disp" style="display: ''">
					<table border="0" cellpadding="0" cellspacing="0"
						class="General_table">
						<tr>
							<th width="160">
								Scheduled Start Date
							</th>
							<td>
								<input name="woResource.scheduleStartDate"
									id="scheduleStart_date" type="text"
									class="NFText ui-datepicker" style="width: 120px;"
									value="<s:date name="woResource.scheduleStartDate" format="yyyy-MM-dd"/>" />
							</td>
							<th width="155">
								Scheduled Complete Date
							</th>
							<td width="130">
								<input name="woResource.scheduleCompleteDate"
									id="scheduleEnd_date" type="text" class="NFText ui-datepicker"
									style="width: 120px;"
									value="<s:date name="woResource.scheduleCompleteDate" format="yyyy-MM-dd"/>" />
							</td>
						</tr>
						<tr>
							<th>
								Actual Start Date
							</th>
							<td>
								<input name="woResource.actualStartDate" id="actualStart_date"
									type="text" class="NFText ui-datepicker" style="width: 120px;"
									value="<s:date name="woResource.actualStartDate" format="yyyy-MM-dd"/>" />
							</td>
							<th>
								Actual Complete Date
							</th>
							<td>
								<input name="woResource.actualCompleteDate" id="actualEnd_date"
									type="text" class="NFText ui-datepicker" style="width: 120px;"
									value="<s:date name="woResource.actualCompleteDate" format="yyyy-MM-dd"/>" />
							</td>
						</tr>
					</table>
				</div>
			</form>
			<div style="text-align: center; margin-top: 30px;">
				<input type="button" name="Submit123" value="Save"
					class="search_input" id="save_btn" />
				<input type="button" name="Submit124" value="Cancel"
					class="search_input"
					onclick="parent.$('#edit_res_dlg').dialog('close');" />
			</div>

		</div>
	</body>
</html>