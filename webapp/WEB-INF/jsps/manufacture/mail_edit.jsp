<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base id="myBaseId" href="${global_url}" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Order Instruction Management</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
			<s:include value="workOrder_config.jsp"></s:include>
			<script src="${global_js_url}util/util.js" type="text/javascript"></script>
		<script src="${global_js_url}scm/config.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/jquery.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.datepicker.js"
			type="text/javascript"></script>
		<script src="${global_js_url}jquery/jquery.form.js"
			type="text/javascript"></script>
		<script src="${global_js_url}jquery/jquery.validate.js"
			type="text/javascript"></script>
		<script src="${global_js_url}scm/gs.util.js" type="text/javascript"></script>
		<script src="${global_js_url}quoteorder/quoteorder_note_edit.js"
			type="text/javascript"></script>
		<script language="javascript"> 
//js variables
var emptyDate = "    -  -  "; 
$(function(){
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
	if($("#content_select").val()==""&&$("#content_select option").length>1) {
		$("#content_select")[0].selectedIndex =1;
		$("#content_select").trigger("change");
	}
	
});
	
</script>

<style type="text/css">
<!--
body {
	margin: 0px auto;
	width: 570px;
	overflow-x: hidden;

}

.General_table {
	margin: 0px;
}
#instruction_form {
	width: 570px;
	overflow-x: hidden;
}
  
-->
</style>
	</head>
	<body>
		<form id="instruction_form" method="post"
			enctype="multipart/form-data">
			&nbsp;
			<input type="hidden" name="uploaded_count"  id="uploaded_count" />
			<input type="hidden" name="docDelIndexs" id="docDelIndexs" />
			<s:hidden id="workOrderNoteId" name="workOrderNote.id"></s:hidden>
			<s:hidden id="sessWorkOrderNo" name="sessWorkOrderNo"/>
			<s:hidden id="docFlag" name="workOrderNote.docFlag"/>
			<s:hidden name="workOrderNote.type"></s:hidden>
			<div id="mail_div">
				<table width="600" border="0" cellpadding="0" cellspacing="0"
					class="General_table">
					<tr>
						<th valign="top">
							To
						</th>
						<td colspan="3">
							<textarea name="workOrderNote.recipient" id="receipt"
								class="content_textarea2">${workOrderNote.recipient}</textarea>
						</td>
					</tr>
					<tr>
						<th>
							<span class="important">*</span>Subject
						</th>
						<td colspan="3">
							<input name="workOrderNote.subject" value="${workOrderNote.subject}"
								id="email_subject" type="text" class="NFText" size="76" />
						</td>
					</tr>
					<tr>
						<th width="99" valign="top">
							<span class="important">*</span>Template
						</th>
						<td valign="top">
							<s:select list="mailTmplList" listKey="subject_content" listValue="functionName"  name="content_select" id='content_select'
								headerKey="" headerValue="Please select template..." onchange="change_content_tmpl(this)">
							</s:select>

					</tr>
					<tr>
					   <th width="99" valign="top">
							Content
						</th>
						<td valign="top">
							<textarea name="workOrderNote.content" id="content" class="content_textarea2">${workOrderNote.content}</textarea>

						</td>
					</tr>
					<tr>
					<th>Word Document</th>
					<td>
						&nbsp;
					</td>
					</tr>
					<s:iterator value="wordDocList">
					<tr>
						<td colspan="2" align="center"><a href="download.action?filePath=${filePath}&fileName=${docName}">${docName}</a></td>
						<input type="hidden" name="docNameArray" value="${docName}"/>
						<input type="hidden" name="filePathArray" value="${filePath}"/>
					</tr>
					</s:iterator>
				</table>
			</div>
			<div id="file_div">
				<table width="600" border="0" cellpadding="0" cellspacing="0"
					class="General_table">
					<tr>
						<th valign="top" width="99">
							Attachment
						</th>
						<td>
							<div id="uploaded_files"></div>
							<input type="button" id="btn_add_file"
								value="  Add Attachments  " onclick="add_file()" />
						</td>
					</tr>
				</table>
			</div>

			<div>
				<table width="600" border="0" cellpadding="0" cellspacing="0"
					class="General_table">
					<tr>
						<td colspan="2">
							<div align="center">
								<br />
								<input type="button" class="style_botton" value="Save&Send"
										onclick="saveMailLog()" />
								&nbsp;&nbsp;
								<input type="button" name="cancel" value="Cancel"
									class="style_botton"
									onclick="parent.$('#instruction_dlg').dialog('close');parent.$('#instruction_update_dlg').dialog('close');parent.window.location.reload();" />
							</div>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<script type="text/javascript">
				function change_content_tmpl(tmplObj) {
					var array =  tmplObj.options[tmplObj.selectedIndex].value.split("::");
					$("#email_subject").val(array[0]);
				     $('#content').val(array[1]);
				}
			</script>
	</body>
</html>
