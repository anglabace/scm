<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="/common/taglib.jsp"%>
	<head>
		<base id="myBaseId" href="${global_url}" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Contact publications edit</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="${global_js_url}scm/config.js"></script>
		<script type="text/javascript" src="${global_js_url}/jquery/jquery.js"></script>
		<script type="text/javascript"
			src="${global_js_url}scm/contact_pubGrant.js"></script>
		<style type="text/css">
<!--
body {
	margin-left: 10px;
	margin-top: 10px;
}
-->
</style>
	</head>
	<body>
		<form id="newPub" method="post"
			action="contact_pub!save.action">
			<input type="hidden" name="sessContactNo" id="sessContactNo" value="${sessContactNo}" />
			<input type="hidden" name="pubKey" id="pubKey" value="${pubKey}" />
			<input type="hidden" name="contactPub.id" id="pub_id" value="${contactPub.id}" />
			<table width="650" border="0" cellpadding="0" cellspacing="0"
				class="General_table">
				<tr>
					<td align="right">
						<span style="color: #FF0000;">*</span>Title
					</td>
					<td>
						<input name="contactPub.title" id="pub_title" type="text" class="NFText"
							size="20" maxlength="100" value="${contactPub.title}" />
					</td>
					<td align="right">
						<span style="color: #FF0000;">*</span>Crspg Authors
					</td>
					<td>
						<input name="contactPub.coAuthor" id="pub_coAuthor" type="text"
							class="NFText" size="20" maxlength="30" value="${contactPub.coAuthor}" />
					</td>
				</tr>

				<tr>
					<td align="right">
						E-mail
					</td>
					<td colspan="3">
						<input name="contactPub.email" id="pub_email" type="text" class="NFText"
							size="20" maxlength="50" value="${contactPub.email}" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<span style="color: #FF0000;">*</span>Publication Name
					</td>
					<td>
						<input name="contactPub.publicationName" id="pub_publicationName"
							type="text" class="NFText" size="20" maxlength="50" value="${contactPub.publicationName}" />
					</td>
					<td align="right">
						<span style="color: #FF0000;">*</span>URL
					</td>

					<td>
						<input name="contactPub.url" id="pub_url" type="text" class="NFText"
							size="20" maxlength="100" value="${contactPub.url}" />
					</td>
				</tr>
				<tr>
					<td valign="top" align="right">
						<span style="color: #FF0000;">*</span>Abstract
					</td>
					<td colspan="3">
						<label>
							<textarea name="contactPub.abst" id="pub_abst" class="content_textarea2">${contactPub.abst}</textarea>
						</label>
					</td>

				</tr>
				<tr>
					<td valign="top" align="right">
						<span style="color: #FF0000;">*</span>Related Area
					</td>
					<td colspan="3">
						<textarea name="contactPub.relatedArea" id="pub_relatedArea"
							class="content_textarea2">${contactPub.relatedArea}</textarea>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span style="color: #FF0000;">*</span>Keywords
					</td>

					<td colspan="3">
						<input name="contactPub.keyWords" id="pub_keyWords" type="text"
							class="NFText" size="50" maxlength="100" value="${contactPub.keyWords}" />
					</td>
				</tr>
				<tr>
					<td height="40" colspan="4">
						<div align="center">
							<br />
							<input type="button" class="style_botton" value="Save"
								onclick="savePub();" />
							<input type="button" class="style_botton" value="Cancel"
								onclick="parent.$('#newPublicationDialog').dialog('close');parent.$('#editPublicationDialog').dialog('close');" />
							<br />
						</div>
					</td>

				</tr>
			</table>
		</form>
		<script language="javascript"> 
function savePub()
{
	if(!document.getElementById("pub_title").value){
		alert("Please enter the Title.");
		document.getElementById("pub_title").focus();
		return;
	}if(!document.getElementById("pub_coAuthor").value){
		alert("please enter the Grspg Authors.");
		document.getElementById("pub_coAuthor").focus();
		return;
	}if(!document.getElementById("pub_publicationName").value){
		alert("Please enter the Publication Name.");
		document.getElementById("pub_publicationName").focus();
		return;
	}if(!IsURL(document.getElementById("pub_url").value)){
		alert("Please enter the correct URL.");
		document.getElementById("pub_url").focus();
		return;
	}if(!document.getElementById("pub_abst").value){
		alert("Please enter The Abstract.");
		document.getElementById("pub_abst").focus();
		return;
	}if(!document.getElementById("pub_relatedArea").value){
		alert("Please enter the Related Area.");
		document.getElementById("pub_relatedArea").focus();
		return;
	}if(!document.getElementById("pub_keyWords").value){
		alert("Please enter the Keywords.");
		document.getElementById("pub_keyWords").focus();
		return;
	}if(document.getElementById("pub_email").value && !checkmail(document.getElementById("pub_email").value)){
		alert("Please enter the correct Email.");
		document.getElementById("pub_email").focus();
		return;
	}
	$.ajax({
		type: "POST",
		url: "contact_pub!save.action",
		data: $("#newPub").serialize(),
		success: function(msg){
		    var pubKey = '${pubKey}';
		    if (pubKey == '') {//新增返回第一页;
			    parent.document.getElementById('pub_list_frame').src = parent.document.getElementById('pub_list_frame').src;
		    } else {//编辑返回当前页
			    parent.document.getElementById('pub_list_frame').src = parent.frames["pub_list_frame"].document.URL;
			}
			parent.$('#newPublicationDialog').dialog('close');
			parent.$('#editPublicationDialog').dialog('close');
			//self.parent.tb_remove();
		},
		error: function(msg) {
			alert("System error! Please contact system administrator for help.");
		}
	});
}

</script>
	</body>
</html>
