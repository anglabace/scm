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
			src="${global_js_url}jquery/jquery.dialog.all.js"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
		<script src="${global_js_url}/recordTime.js" type="text/javascript"></script>
		<script type="text/javascript">
            $(function() {
                var flagVal = '${workCenter.defaultFlag}';
                if (flagVal == 'Y') {
                  $("#flag").get(0).checked = true;
                } else {
                  $("#flag").get(0).checked = false;
                }         

                //Generate Batch Work Orders
                $("#GenerateBatchWorkOrdersDialog").dialog({
                	autoOpen: false,
					height: 300,
					width: 300,
					modal: true,
					bgiframe: true,
					buttons: {
					}
                });
                $("#generateBatchWorkOrders").click( function() {
					$('#GenerateBatchWorkOrdersDialog').dialog("option", "open", function() {	
	              		 var htmlStr = '<iframe src="workorder_entry!GenerateBatchWorkOrders.action" height="100%" width="250" scrolling="no" style="border:0px" frameborder="0"></iframe>';
				         $('#GenerateBatchWorkOrdersDialog').html(htmlStr);
					});
					$('#GenerateBatchWorkOrdersDialog').dialog('open');
                });  
                $("#GenerateBatchWorkOrdersSearchDialog").dialog({
                	autoOpen: false,
					height: 400,
					width: 640,
					modal: true,
					bgiframe: true,
					buttons: {
					}
                });        
            
            });
        </script>
	</head>
	<body class="content" onload="recordTime();">
		<div class="scm">
			<div class="title_content">
				<div class="title">
					${workCenter.name} Center
				</div>
			</div>
			<div class="input_box">
				<div class="content_box">
						<input type="hidden" name="workCenter.id" id="workCenterId" value="${workCenter.id}" />
						<table border="0" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<th width="160">
								Work Center	Name
								</th>
								<td>
									<input name="workCenter.name" value="${workCenter.name}"
										type="text" class="NFText" size="76" readonly="readonly"/>
								</td>
								<th width="150">
									Status
								</th>
								<td>
									<s:select list="{'ACTIVE', 'INACTIVE'}"
										value="workCenter.status" name="workCenter.status" disabled="true"></s:select>
								</td>
							</tr>
							<tr>
								<th valign="top">
									Description
								</th>
								<td>
									<input name="workCenter.description" type="text"
										value="${workCenter.description}" class="NFText" size="76" readonly="readonly"/>
								</td>
								<th>

								</th>
								<td>
									<input type="checkbox" id="flag" name="workCenter.defaultFlag"
										value="Y" disabled="disabled"/>
									<label for="flag">
										Set as Default
									</label>
								</td>
							</tr>
							<tr>
								<th>
									Supervisor
								</th>
								<td>
									<input name="workCenter.superName" type="text"
										value="${workCenter.superName}" class="NFText" size="38" readonly="readonly"/>
								</td>
								<th>
									Storage Warehouse
								</th>
								<td>
									<input name="workCenter.warehouseName" type="text"
										value="${workCenter.warehouseName}" class="NFText" size="38" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<th>
									Comment
								</th>
								<td>
									<textarea name="workCenter.comment" class="content_textarea2" disabled="disabled">${workCenter.comment}</textarea>
								</td>
								<td>
									&nbsp;
								</td>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<th>
									Modified Date
								</th>
								<td>
									<input name="textfield3224" type="text" class="NFText"
										size="20" readonly="readonly"
										value="<s:date name="workCenter.modifyDate" format="yyyy-MM-dd"/>" />
								</td>
								<th>
									Modified By
								</th>
								<td>
									<input name="textfield3322" type="text" class="NFText"
										readonly="readonly" size="20" value="${workCenter.modifyUser}" />
								</td>
							</tr>
						</table>
				</div>
			</div>
			<div class="button_box">
				<input type="button" id="generateBatchWorkOrders" name="Submit123" value="Generate Batch Work Orders"
					class="search_input3"/>
				<input type="button" name="Submit123" value="Create Work Order"
					class="search_input2"
					onclick="window.location.href='workorder_entry!add.action?id=${workCenter.id}'" />
				<input type="submit" name="Submit124" value="Cancel"
					class="search_input" <s:if test="operation_method=='edit'">
								  onclick="window.location = 'workorder_entry!list.action';";
					  		 </s:if>
							   <s:else> 
								  onclick="window.history.go(-1);"
							  </s:else>/>
			</div>
		</div>
		<div id="GenerateBatchWorkOrdersDialog" title="Generate Batch Work Orders"></div>
		<div id="GenerateBatchWorkOrdersSearchDialog" title="Generate Batch Work Orders Search"></div>
	</body>
</html>