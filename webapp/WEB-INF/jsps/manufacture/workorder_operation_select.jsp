<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Production WorkCenters</title>
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
		<script type="text/javascript" src="${global_js_url}show_tag.js"></script>
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
		<script type="text/javascript">
		    var baseUrl="${global_url}";	
				
            $(function() {
            	var woStauts = "${woStatus}";
            	if(woStauts=="In Production") {
            		$("#search_btn").attr("disabled",true);
            	}
            	$('#sel_operation_dlg').dialog({
					autoOpen: false,
					height: 460,
					width: 715,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            $('#sel_workcenter_dlg').dialog({
				autoOpen: false,
				height: 150,
				width: 500,
				modal: true,
				bgiframe: true,
				buttons: {
				}
			});
            
            $('#seq_input_dlg').dialog({
				autoOpen: false,
				height: 500,
				width: 500,
				modal: true,
				bgiframe: true,
				buttons: {
				}
			});
				
                $("#search_btn").click( function() {
					$('#sel_operation_dlg').dialog("option", "open", function() {	
	              		 var htmlStr = '<iframe src="operation!list.action?toPage=workorder_operation_search&selectFlg=Y&roleName=Work Order Assignment Manager" height="400" width="685" scrolling="no" style="border:0px" frameborder="0"></iframe>';
				         $('#sel_operation_dlg').html(htmlStr);
					});
					$('#sel_operation_dlg').dialog('open');
                });
                
                //Cancel按钮返回WorkOrder的编辑页面
                $("#cancel_btn").click( function() {
				    $('#confirm_btn').attr("disabled", true);
				    $('#cancel_btn').attr("disabled", true);
			        var sessId = $("#sessId").val();
			        isSaved = true;
				    window.location.href = 'workorder_entry!edit.action?referURL=select&sessId=' + sessId;
				});
				
                //创建内部Order
                $("#create_btn").click( function() {
                	$("#sel_workcenter_dlg").dialog("open");
				});				
                
                //保存完成后返回到原来的WorkOrder的编辑页面.
                $("#confirm_btn").click( function() {           
				      $('#confirm_btn').attr("disabled", true);
				      $('#cancel_btn').attr("disabled", true);
				      var sessId = $("#sessId").val();
				      var operationIdList = $("#operationIdList_hidden").val();
				      if (operationIdList == '') {
 							isSaved = true;
							window.location.href = 'workorder_entry!edit.action?referURL=select&sessId=' + sessId;	
							return;			
				      }
				      $.ajax({
							type: "POST",
							url: "workorder_operation!selectOperationForWO.action",
							data: "operationIdList=" + operationIdList + "&sessId=" + sessId,
							success: function(data, textStatus){
							     isSaved = true;
							     window.location.href = 'workorder_entry!edit.action?referURL=select&sessId=' + sessId;				
							},
							error: function(xhr, textStatus){
							   alert("failure");
					           $('#confirm_btn').attr("disabled", false);
					           $('#cancel_btn').attr("disabled", false);
							   return;
							}
					 }); 
                });                                             
            });
            
            function createInnerOrder() {
            	var workCenterId = $("#sel_workCenter").val();
            	var workOrderSessNo = $("#sessId").val();
            	$("#sel_workcenter_dlg").dialog("close");
            	$.ajax({
            		type: "POST",
					url: "workorder_operation!createInternalOrderPreOp.action",
					data: "workCenterId=" + workCenterId + "&sessId=" + workOrderSessNo,
					dataType:'json',
					success: function(data, textStatus){
						if(data.message=="seqCreate") {
							$('#seq_input_dlg').dialog("option", "open", function() {	
			              		 var htmlStr = '<iframe src="workorder_operation!inputSeqPage.action?sessId='+workOrderSessNo+'&workCenterId='+workCenterId+'" id="seqInputFrame" height="100%" width="100%" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
						         $('#seq_input_dlg').html(htmlStr);
							});
							$('#seq_input_dlg').dialog('open');
						} else {
							window.location.href = 'order/order!edit.action?referURL=select&workOrderSessNo=' + workOrderSessNo+"&purchaseOrderFlag=0&workCenterId="+workCenterId+"&operation_method=edit&defaultTab=1";
						}		
					},
					error: function(xhr, textStatus){
					   alert("failure");
					   return;
					}
            	});
            }
            
            function redirectTo() {
            	var workCenterId = $("#sel_workCenter").val();
            	var workOrderSessNo = $("#sessId").val();
            	window.location.href = 'order/order!edit.action?referURL=select&workOrderSessNo=' + workOrderSessNo+"&purchaseOrderFlag=0&workCenterId="+workCenterId+"&operation_method=edit&defaultTab=1";
            }
            
        </script>
	</head>
	<body class="content">
		<div class="scm">
			<div class="title_content">
				<div class="title">
					Add Operation
				</div>
			</div>
			<div class="input_box">
				<div class="content_box">
					<div class="invoice_title">
						<span style="cursor: pointer"
							onclick="toggle_showmore('MoreInfoItem', 'MoreInfo');"><img
								src="${global_image_url}ad.gif" width="11" height="11"
								id="MoreInfoItem" /> &nbsp;Select Operation</span>
					</div>
				</div>
				<div id="MoreInfo">
					<table width="800" border="0" cellpadding="0" cellspacing="0"
						class="General_table">
						<tr><td colspan="5" height="30px">&nbsp;</td></tr>
						<tr>
							<td width="248" valign="top">
								&nbsp;
							</td>
							<td width="95" valign="top">
								<span class="title">Operation </span>Name
							</td>
							<td width="141">
							    <input name="sessId" id="sessId" type="hidden" value="${param.sessId}" />
								<input name="operationNameList" readonly="readonly"
									class="NFText" size="40" id="operationNameList_txt" />
								<input name="operationIdList" id="operationIdList_hidden" type="hidden" />
							</td>
							<td>
								<input id="search_btn" type="button" class="style_botton"
									value="Search" />
							</td>
						</tr>
						<tr>
							<th>
								&nbsp;
							</th>
							<th colspan="2">
								<div align="left">
									Or Create an Internal Order
								</div>
							</th>
							<td width="119">
							<input name="workCenterId" id="workCenterId" type="hidden" value="${param.id}" />
								<input name="button2" type="button" value="Create"
									class="style_botton" id="create_btn" />
							</td>
						</tr>
						<tr><td colspan="5" height="40px">&nbsp;</td></tr>
						<tr>
							<th colspan="2">
								&nbsp;
							</th>
							<th colspan="2">
								<span class="button_box"> <input name="button3"
										type="button" value="Confirm" class="search_input" id="confirm_btn" />
									<input name="button" type="button" value="Cancel"
										class="search_input" id="cancel_btn" />
								</span>
							</th>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div id="sel_operation_dlg" title="Select Operation"></div>
		<div id="sel_workcenter_dlg" title="Select Internal Supplier ">
			<s:include value="sel_workcenter_dlg.jsp"></s:include>
		</div>
		<div id="seq_input_dlg" title="Sequence Input">
		</div>
	</body>
</html>