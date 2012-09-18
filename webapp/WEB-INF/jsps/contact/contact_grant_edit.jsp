<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/taglib.jsp"%>
<%@ page contentType="text/html; charset=utf-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base id="myBaseId" href="${global_url}" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Contact Grants edit</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
		<script type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
		<script type="text/javascript" src="${global_js_url}scm/config.js"></script>
		<script type="text/javascript" src="${global_js_url}jquery/ui/ui.datepicker.js" ></script>
		<script type="text/javascript" src="${global_js_url}scm/contact_pubGrant.js"></script>
<script type="text/javascript">
var baseUrl = "${global_url}";
$().ready(function(){
	$('.ui-datepicker').each(function(){
		$(this).datepicker(
		{
			dateFormat: 'yy-mm-dd',
			changeMonth: true,
			changeYear: true
		});
	});
	$('#grant_exprDate').datepicker();
	$('#grant_issueDate').datepicker();
});
</script>
<style type="text/css"> 
<!--
body {
	margin-left:10px;margin-top:10px;
}
-->
</style>

	</head>
	<body>
		<form id="newGrant" method="post" action="contact_grant!save.action">&nbsp; 
			<input type="hidden" name="grantKey" id="grantKey"
				value="${grantKey}" />
			<input type="hidden" name="contactGrant.grantId" id="grant_id"
				value="${contactGrant.grantId}" />
			<table width="600" border="0" cellpadding="0" cellspacing="0"
				class="General_table">
				<tr>
					<td align="right">
						<span style="color: #FF0000;">*</span>Project No
					</td>
					<td>
						<input name="contactGrant.projectNo" id="grant_projNum" type="text"
							class="NFText" size="20" maxlength="30" value="${contactGrant.projectNo}"/>
					</td>
					<td align="right">
						<span style="color: #FF0000;">*</span>Project Name
					</td>

					<td>
						<input name="contactGrant.projectName" id="grant_projTitle" type="text"
							class="NFText" size="20" maxlength="50" value="${contactGrant.projectName}"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span style="color: #FF0000;">*</span>Category
					</td>
					<td>
						<input name="contactGrant.category" id="grant_category" type="text"
							class="NFText" size="20" maxlength="50" value="${contactGrant.category}"/>
					</td>
					<td align="right">
						E-mail
					</td>
					<td>
						<input name="contactGrant.email" id="grant_contactEmail" type="text"
							class="NFText" size="20" maxlength="50" value="${contactGrant.email}"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span style="color: #FF0000;">*</span>Issue Date
					</td>
					<td>
						<input name="issueDate" id="grant_issueDate" type="text"
							class="NFText ui-datepicker" size="20" maxlength="20"
							style="width: 130px;" value='<s:date name="contactGrant.issueDate" format="yyyy-MM-dd" />'/>
					</td>
					<td align="right">
						<span style="color: #FF0000;">*</span>Expiration Date
					</td>

					<td>
						<input name="exprDate" id="grant_exprDate" type="text"
							class="NFText ui-datepicker" readonly="readonly" size="20"
							maxlength="20" style="width: 130px;" value='<s:date name="contactGrant.exprDate" format="yyyy-MM-dd" />'/>
					</td>

				</tr>
				<tr>
					<td align="right">
						<span style="color: #FF0000;">*</span>PI
					</td>
					<td>
						<input name="contactGrant.pi" id="grant_piName" type="text"
							class="NFText" size="20" maxlength="50" value="${contactGrant.pi}"/>
					</td>
					<td align="right">
						<span style="color: #FF0000;">*</span>Org
					</td>
					<td>
						<input name="contactGrant.organization" id="grant_orgName" type="text"
							class="NFText" size="20" maxlength="50" value="${contactGrant.organization}"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						State
					</td>

					<td><input name="contactGrant.state" type="text"
															class="NFText" value="${contactGrant.state}" id="state"
															size="18" />
					</td>
					<td align="right">
						<span style="color: #FF0000;">*</span>Country
					</td>
					<td>
						<select name="contactGrant.country" id="grant_country"></select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span style="color: #FF0000;">*</span>Project Abstract
					</td>

					<td colspan=3>
						<textarea name="contactGrant.abst" id="grant_projAbst"
							class="content_textarea2">${contactGrant.abst}</textarea>
					</td>
				</tr>
				<tr>
					<td align="right">
						Sub Project
					</td>
					<td colspan=3>
						<input name="contactGrant.subProjectNo" id="grant_subProj" type="text"
							class="NFText" size="80" maxlength="30" value="${contactGrant.subProjectNo}"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						Function IC
					</td>
					<td>
						<input name="contactGrant.fundingIc" id="grant_fundingIC" type="text"
							class="NFText" size="20" maxlength="50" value="${contactGrant.fundingIc}"/>
					</td>
					<td align="right">
						<span style="color: #FF0000;">*</span>Amount
					</td>
					<td>
						<input name="contactGrant.amount" id="grant_amount" type="text"
							class="NFText" size="20"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
							maxlength="12" value="${contactGrant.amount}"/>
					</td>
				</tr>
				<tr>
					<td height="40" colspan="4">
						<div align="center">
							<br />
							<input type="button" class="style_botton" value="Save"
								onclick="saveGrant();" />
							<input type="button" class="style_botton" value="Cancel"
								onclick="parent.$('#newGrantDialog').dialog('close');parent.$('#editGrantDialog').dialog('close');" />
							<br />

						</div>
					</td>
				</tr>
			</table>
		</form>
		<script language="javascript">
function saveGrant() {
	if(!document.getElementById("grant_projNum").value){
		alert("Please enter the Project No.");
		document.getElementById("grant_projNum").focus();
		return false;
	}

    if(!isNaN(document.getElementById("grant_projNum"))){
        alert("Please enter a valid projectNo!");
        document.getElementById("grant_projNum").focus();
        return false;
    }
    if(!document.getElementById("grant_projTitle").value){
		alert("please enter the Project Name.");
		document.getElementById("grant_projTitle").focus();
		return false;
	}if(!document.getElementById("grant_category").value){
		alert("Please enter the Category.");
		document.getElementById("grant_category").focus();
		return false;
	}if(!document.getElementById("grant_piName").value){
		alert("Please enter the PI.");
		document.getElementById("grant_piName").focus();
		return false;
	}if(!document.getElementById("grant_orgName").value){
		alert("Please enter the organization.");
        document.getElementById("grant_orgName").focus();
		return false;
	}if(!document.getElementById("grant_country").value){
		alert("Please enter the Country.");document.getElementById("grant_country").focus();
		return false;
	}if(!document.getElementById("grant_projAbst").value){
		alert("Please enter the Project Abstract.");
		document.getElementById("grant_projAbst").focus();
		return false;
	}if(!document.getElementById("grant_amount").value){
		alert("Please enter the Amount.");
		document.getElementById("grant_amount").focus();
		return false;
	}
     if(isNaN(document.getElementById("grant_amount").value)){
		alert("failure! Amount should be number !");
		document.getElementById("grant_amount").focus();
		return false;
	}
    if(!document.getElementById("grant_issueDate").value){
		alert("Please enter the Issue Date.");
		document.getElementById("grant_issueDate").focus();
		return;
	}if(!document.getElementById("grant_exprDate").value){
		alert("Please enter the Expiration Date.");
		document.getElementById("grant_exprDate").focus();
		return;
	}if(!datebj(document.getElementById("grant_issueDate").value,document.getElementById("grant_exprDate").value)){
		alert("Please enter the correct Ssue Date.");
		return;
	}
	if(document.getElementById("grant_contactEmail").value && !checkmail(document.getElementById("grant_contactEmail").value)){
		alert("Please enter the correct Email.");
		document.getElementById("grant_contactEmail").focus();
		
		return;
	}
	$.ajax({
		type: "POST",
		url: "contact_grant!save.action?sessContactNo=${sessContactNo}",
		data: $("#newGrant").serialize(),
		success: function(msg){
		    var grantKey = '${grantKey}';
		    if (grantKey == '') {//新增返回第一页;
		        //alert("add: " + parent.document.getElementById('grant_list_frame').src);		        
			    parent.document.getElementById('grant_list_frame').src = parent.document.getElementById('grant_list_frame').src;
		    } else {//编辑返回当前页
		        //alert("edit: " + parent.frames["grant_list_frame"].document.URL);
			    parent.document.getElementById('grant_list_frame').src = parent.frames["grant_list_frame"].document.URL;
			}
			parent.$('#newGrantDialog').dialog('close');
			parent.$('#editGrantDialog').dialog('close');
		},
		error: function(msg) {
			alert("System error! Please contact system administrator for help.");
		}
	});
}


// country state default value initialization
//${contactGrant.country}
var countryIdNames = ['grant_country'];
var countryDefaults = ['${contactGrant.country!=null ? contactGrant.country :"US" }'];
var countryChangeHandlers = [''];

var stateIdNames = ['state'];
var stateDefaults = ['${contactGrant.state}'];
var stateChangeHandlers = [''];
$(document).ready(function(){
	initCountry();
    document.getElementById("state").value=stateDefaults;
});
</script>
<script type="text/javascript" src="${global_js_url}scm/gsCountryState.js?v=3"></script>
	</body>
</html>
