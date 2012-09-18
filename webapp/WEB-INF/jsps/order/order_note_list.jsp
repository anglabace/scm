<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="/common/taglib.jsp"%>
	<head>
		<base id="myBaseId" href="${global_url}" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Order Instruction Management</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
		<script src="${global_js_url}scm/config.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/jquery.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/jquery.dialog.all.js" type="text/javascript"></script>
		<script type="text/javascript"
			src="${global_js_url}jquery/jquery.form.js"></script>
		<script src="${global_js_url}jquery/jquery.validate.js"
			type="text/javascript"></script>
		<script type="text/javascript"> 
function show_edit(noteKey, searchNoteType){
	if (parent.operation_method == "edit") {
		var params = '&noteKey='+noteKey + '&searchNoteType=' + searchNoteType+'&tmpStr=tt';
		parent.$('#instruction_update_dlg').dialog("option","params", params);
		parent.$('#instruction_update_dlg').dialog('open'); 
	}
}
function initStatus(){
	if (parent.window.orderquoteObj.editFlag == true||$("#status").val()=="CC") {
		$("#searchInstructionBtn").attr("disabled", false);
		$("#instructionDialogBtn").attr("disabled", false);
	} else {
		$("#instructionDialogBtn").attr("disabled", true);
		$("#searchInstructionBtn").attr("disabled", true);
	}
}
$(document).ready( function() {
	initStatus();
    if (parent.operation_method != "edit") {
    	$('input').attr("disabled",true);
    	$('button').attr("disabled",true);
    	$('select').attr("disabled",true);
    }
});
var descriptionArray = new Array();
</script>

	</head>
	<body>
		<table border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="top">
					View Instruction/Notes with Type :
				</td>
				<td>
					<select id="notetype_sel" style="width: 250px;" id='notetype_sel'>
						<option value=''>
							All Instruction/Notes
						</option>
						<option value='CUST_CONFIRM_EMAIL'>
							Customer Confirmation Emails
						</option>
						<option value='SALES_NOTES'>
							Sales Notes
						</option>
						<option value='SHIPMENT'>
							Shipment Notes
						</option>
						<option value='PRODUCTION'>
							Production Notes
						</option>
						<option value='ACCOUNTING'>
							Accounting Notes
						</option>
						<option value="CROSS_SELLING">
									Cross Selling Notes
						</option>
					</select>
				</td>
				<td>
					<input type="button" id="searchInstructionBtn" class="style_botton" value="Search" />
				</td>
				<td>
					<input type="button" id="instructionDialogBtn" value="Add" class="style_botton" />
				</td>
			</tr>
		</table>
		<br />
		<div id="tb1" style="display: block;">
			<table width="960" border="0" cellpadding="0" cellspacing="0"
				class="list_table">
				<tr>
					<th width="70">
						Date
					</th>
					<th width="150">
						Type
					</th>
					<c:if test="${empty searchNoteType}">
						<th width="80">
							Source
						</th>
						<th width="65">
							Status
						</th>
						<th width="200">
							Subject
						</th>
					</c:if>
					<c:if
						test="${searchNoteType=='SALES_NOTES' or searchNoteType=='SHIPMENT' or searchNoteType=='PRODUCTION' or searchNoteType=='ACCOUNTING' or searchNoteType=='CROSS_SELLING'}">
						<th width="80">
							Source
						</th>
					</c:if>
					<s:if test="searchNoteType!=null&&searchNoteType!=''&&searchNoteType !='CUST_CONFIRM_EMAIL'">
						<th width="65">
							Status
						</th>
					</s:if>
					<c:if test="${searchNoteType=='CUST_CONFIRM_EMAIL' or searchNoteType=='VENDOR_CONFIRM_EMAIL' or searchNoteType=='FOLLOWUP_EMAIL'}">
						<th width="200">
							Subject
						</th>
					</c:if>
					<th>
						Description/Content
					</th>
					<th width="80">
						Created By
					</th>
				</tr>
			</table>
			<div class="frame_box3" style="height: 250px">
				<table width="960" border="0" cellpadding="0" cellspacing="0"
					class="list_table">
					<s:iterator value="noteListMap" step="index">
						<tr>
							<td width="70">
								<div align="center">
									<s:date name="value.instructionDate" format="yyyy-MM-dd" />
								</div>
							</td>
							<td width="150" align="center">
								${value.type}
							</td>
							<c:if test="${empty searchNoteType}">
								<td width="80">
									${value.source}
								</td>
								<td width="65">
									${value.status}
								</td>
								<td width="200">
									${value.subject}
								</td>
							</c:if>
							<c:if
								test="${searchNoteType=='SALES_NOTES' or searchNoteType=='SHIPMENT' or searchNoteType=='PRODUCTION' or searchNoteType=='ACCOUNTING' or searchNoteType=='CROSS_SELLING'}">
								<td width="80">
									${value.source}
								</td>
							</c:if>
							<s:if test="searchNoteType!=null&&searchNoteType!=''&&searchNoteType != 'CUST_CONFIRM_EMAIL'">
								<td width="65">
									${value.status}
								</td>
							</s:if>
							<c:if
								test="${searchNoteType=='CUST_CONFIRM_EMAIL' or searchNoteType=='VENDOR_CONFIRM_EMAIL' or searchNoteType=='FOLLOWUP_EMAIL'}">
								<td width="200">
									${value.subject}
								</td>
							</c:if>

							<td>
								<c:if test="${! empty value.documentList || ! empty value.custNoteDocumentList}">
									<img src="${ctx}/images/file_icon.png" />&nbsp; </c:if>
								<a href="javascript:void(0)"
									onclick="show_edit('${key}', '${value.type}')">
									<c:if test="${! empty value.content}">
                                        <s:property value="value.content" escape="false"/>
                                    </c:if>
                                    <c:if test="${! empty value.description}">
                                        <s:property value="value.description" escape="false"/>
                                    </c:if>
								</a>
							</td>

							<td width="80">
								${value.createUser}
							</td>
						</tr>
					</s:iterator>
				</table>
				<input type="hidden" id="now_page" value="<!-- {$now_page} -->" />
				<input type="hidden" id="mail_type" />
				<s:hidden name="status" id="status"></s:hidden>
			</div>
			<script type="text/javascript">
				$(document).ready(function(){
					
					//初始化页面: 提交页面后， 回显时选中note_type.
					$("#notetype_sel option[value='${searchNoteType}']").attr( 'selected' , true ) ;

				    //绑定search事件
					$("#searchInstructionBtn").click(function() {
						var search_type = $('#notetype_sel').val();
						var srcURL = "order_note!list.action?sessOrderNo=${sessOrderNo}&custNo=${custNo}";
						window.location = srcURL + "&searchNoteType="+search_type;
					});
					
					//绑定Add按钮弹出instruction新增窗口;
				    $("#instructionDialogBtn").click(function (){
				    	parent.$("#tmpAdd").val('tt');
						parent.$('#instruction_dlg').dialog('open'); 
					});

				});
			</script>
	</body>
</html>
