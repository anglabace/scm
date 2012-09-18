<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Reopen Quote Information</title>
		<base id="myBaseId" href="${global_url}" />
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<script src="${global_js_url}jquery/jquery.js" type="text/javascript"></script>
	</head>
	<body>
		<table border="0" align="center" cellpadding="0" class="General_table" id="open_tbl" cellspacing="0" style="margin: 20px auto 0px auto;">
			<tr>
			    <th colspan="2">
			    	<div align="left">Choose the reason to reopen the quote</div>
			    </th>
			</tr>
			<tr>
				<th width="4" valign="top">
					&nbsp;
				</th>
				<td>
					<select id="reopenReason" style="width: 250px;">
						<option label="Discussed Reopen" value="Discussed Reopen">
							Discussed Reopen
						</option>
						<option label="Negotiate Price" value="Negotiate Price">
							Negotiate Price
						</option>
						<option label="Wrong Close" value="Wrong Close">
							Wrong Close
						</option>

					</select>

				</td>
			</tr>
			<tr>
				<th valign="top">
					Note:
				</th>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<th valign="top">
					&nbsp;
				</th>
				<td>
					<textarea id="statusNote" name="comment" class="content_textarea2"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<br />
						<input type="button" id="reopenBtn" class="style_botton"
							value="Confirm" />
						<input type="button" name="Cancel" value="Cancel"
							class="style_botton"
							onclick="parent.$('#reopenDialog').dialog('close');" />
					</div>

				</td>
			</tr>
		</table>
<script type="text/javascript">
$(document).ready(function() {
	$("#reopenBtn").click( function(){
			var status = 'OP';
			var statusReason = $("#reopenReason").val();
			var statusNote = $("#statusNote").val();
			if(statusNote == ""){
				alert("Please enter the Note.");
				return;
			}
			$.ajax({
				   type: "POST",
				   url: "quote_extra!reopen.action",
				   data: "sessQuoteNo=${sessQuoteNo}&quoteStatus="+status+"&statusReason="+statusReason+"&statusNote="+statusNote,
				   success:function(data) {
						alert("The quote is reopened.");
						parent.$('#reopenDialog').dialog('close');
						parent.window.location = parent.window.location;
					},
					error: function(msg) {
			            alert("Failed to reopen the quote.");
		            },
					async: false
		    });
 	});
});
</script>
	</body>
</html>
