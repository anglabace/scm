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
});

//update source in session
function saveMailLog(){
	if (! $('#email_subject').val()){
		alert("Please enter the Subject."); 
		$("#email_subject").focus(); 
		return;
	}
	if (! $('#content').val()){
		alert("Please enter the Content."); 
		$("#content").focus(); 
		return;
	}
	if (! $('#receipt').val()){
		alert("Please enter the Recei."); 
		$("#receipt").focus(); 
		return;
	}
	var action = "workorder_proc!saveMail.action";
	var form = $("#instruction_form");
	var options = {
		success:function(data) {
			alert("Success.");
			parent.$('#instruction_dlg').dialog('close');
			parent.window.location.reload();
		}, 
		error: function(){
			parent.$('#instruction_dlg').dialog('close');
		}, 
		resetForm:false, 
		url:action,
		type:"POST",
		async:false
	};
	form.ajaxSubmit(options);
//	form.submit();



}
	
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
							Content
						</th>
						<td valign="top">
							<textarea name="workOrderNote.content" id="content"  rows="20" cols="80">
							This is a non-reply email. Please don't reply to it.<br><br>

							Dear ${workOrder.custName} :<br><br>

							Thanks for your order. The following is your account information:<br><br>

							Account Number: ${workOrder.custNo}<br>
							Email: ${workOrder.email}<br>
							Password: ${workOrder.password}<br>

							Please log on to https://www.genscript.com/ssl-bin/myaccount to<br> 
							check your orders.<br><br>

							Here is the immunization schedule of your GenScript<br> 
							order id: ${workOrder.srcSoNo}_${workOrder.soItemNo}<br><br>

							Immunization Schedule:<br>
							Please check your attachment<br><br>
							
							* Your order will be delivered in one shipment after the work<br> 
							on all items is completed. Contact your account manager <br>
							if you require separate shipping. You will be charged an additional <br>
							fee for each shipment.<br><br>  

							Your success is our joy! With every publication or reference of <br>
							yours that cites our custom or catalog antibody products, <br>
							you win a $100 discount coupon towards any GenScript order. <br>
							Win this coupon also for a good gel picture demonstrating <br>
							the binding of our antibody to your antigen of interest. <br>
							This reference and image will be stored in our database <br>
							and may be used on our website or sent to specific customers with<br> 
							your permission. 
							
							</textarea>

						</td>
					</tr>
					<tr>
					<th>Attachment</th>
					<td><a href="download.action?filePath=${wordDoc.filePath}&fileName=${wordDoc.docName}">${wordDoc.docName}</a>
					<s:hidden name="wordDoc.docName"/>
					<s:hidden name="wordDoc.filePath"/>
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
									onclick="parent.$('#instruction_dlg').dialog('close');" />
							</div>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<script type="text/javascript">
				
			</script>
	</body>
</html>
