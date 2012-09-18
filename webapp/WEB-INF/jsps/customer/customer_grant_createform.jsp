<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}scm/customerPubGrant.js"></script>
<script>
$().ready(function(){
	$('.datepicker').each(function(){
		$(this).datepicker(
		{
			dateFormat: 'yy-mm-dd',
			changeMonth: true,
			changeYear: true
		});
	});
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
<form id="newGrant" method="post" action="cust_pub_grant!saveGrant.action">
<input type="hidden" name="custNo" id="custNo" value="${custNo}" />
<input type="hidden" name="sessCustNo" id="sessCustNo" value="${sessCustNo}" />
<input type="hidden" name="grant_id" id="grant_id" value="${grantIdStr}" />
<table width="600" border="0" cellpadding="0" cellspacing="0" class="General_table">
  <tr>
    <td align="right"><span class="important">*</span>Project No</td>
    <td><input name="grant_projNum" id="grant_projNum" type="text" class="NFText" size="20" />
    </td>
    <td align="right"><span class="important">*</span>Project Name</td>

    <td><input name="grant_projTitle" id="grant_projTitle" type="text" class="NFText" size="20" /></td>
  </tr>
  <tr>
    <td align="right"><span class="important">*</span>Category</td>
    <td><input name="grant_category" id="grant_category" type="text" class="NFText" size="20" /></td>
    <td align="right">E-mail</td>
    <td><input name="grant_contactEmail" id="grant_contactEmail" type="text" class="NFText" size="20" /></td>
  </tr>

  <tr>
    <td align="right"><span class="important">*</span>Issue Date </td>
    <td>
    <input name="grant_issueDate" id="grant_issueDate" type="text" class="NFText datepicker" value="" size="20" />
     </td>
    <td align="right"><span class="important">*</span>Expiration Date </td>

    <td><input name="grant_exprDate" id="grant_exprDate" type="text" class="NFText datepicker" value="" size="20" />
    </td>
  </tr>
  
  <tr>
    <td align="right"><span class="important">*</span>PI</td>
    <td><input name="grant_piName" id="grant_piName" type="text" class="NFText" size="20" /></td>
    <td align="right"><span class="important">*</span>Organization </td>
    <td><input name="grant_orgName" id="grant_orgName" type="text" class="NFText" size="20" /></td>
  </tr>
  <tr>
    <td align="right">State </td>

    <td><select name="grant_state" id="grant_state">
      <option selected="selected"> </option>
    </select></td>
    <td align="right"><span class="important">*</span>Country</td>
    <td><select name="grant_country" id="grant_country">
    </select></td>
  </tr>
  <tr>
    <td align="right"><span class="important">*</span>Project Abstract </td>

    <td colspan=3>
	<textarea  name="grant_projAbst" id="grant_projAbst" class="content_textarea2"></textarea>
	</td>
  </tr>
  <tr>
    <td align="right">Sub Project</td>
    <td colspan=3><input name="grant_subProj" id="grant_subProj" type="text" class="NFText" size="80" /></td>
  </tr>
  <tr>
    <td align="right">Function IC </td>
    <td>
      <input name="grant_fundingIC" id="grant_fundingIC" type="text" class="NFText" size="20" />    </td>

    <td align="right"><span class="important">*</span>Amount</td>
    <td><input name="grant_amount" id="grant_amount" type="text" class="NFText" size="20" /></td>
  </tr>
  <tr>
    <td height="40" colspan="4"><div align="center">
      <br />
      <input type="button" class="style_botton" value="Save" onclick="saveGrant('${grantIdStr}');"  />
      <input type="button" class="style_botton" value="Cancel" onclick="parent.$('#editGrant').dialog('close');parent.$('#newGrant').dialog('close');"/>
      <br />

    </div></td>
  </tr>
</table>
</form>
<script language="javascript" type="text/javascript">
var baseUrl ="${ctx}/";

// init country state and city
var countryIdNames = ['grant_country'];
var countryDefaults = new Array(1);
var countryChangeHandlers = [''];

var stateIdNames = ['grant_state'];
var stateDefaults = new Array(1);
var stateChangeHandlers = [''];
</script>
<script language="javascript" type="text/javascript" src="${global_js_url}scm/gsCountryState.js"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1" type="text/javascript"></script>
<script language="javascript" type="text/javascript">
$(function(){
	$('#newGrant').validate({
		errorClass:"validate_error",
		highlight: function(element, errorClass) {
		$(element).addClass(errorClass);
		},
		unhighlight: function(element, errorClass, validClass) {
		$(element).removeClass(errorClass);
		},
			 invalidHandler: function(form, validator) {
				 $.each(validator.invalid, function(key,value){
		            alert(value);
		            $("#"+key).focus();
		            return false;
		        });
			 },
			 rules: {
			     grant_projNum: { required:true },
			     grant_projTitle: { required:true },
			     grant_category: { required:true },
			     grant_piName: { required:true },
			     grant_orgName: { required:true},
			     grant_country: { required:true },
			     grant_projAbst: { required:true },
			     grant_amount: { required:true,number:true },
			     grant_issueDate: { required:true },
			     grant_exprDate: { required:true },
			     grant_contactEmail: { email:true }
			 },
			 messages: {
			     grant_projNum: { required:"Please specify the project No" },
			     grant_projTitle: { required:"Please specify the project Name" },
			     grant_category: { required:"Please specify the category" },
			     grant_piName: { required:"Please specify the pi" },
			     grant_orgName: { required:"Please specify the organization"},
			     grant_country: { required:"Please specify the country" },
			     grant_projAbst: { required:"Please specify the project abstract" },
			     grant_amount: { required:"Please specify the amount",digits:"Please specify only digits" },
			     grant_issueDate: { required:"Please specify the issue Date" },
			     grant_exprDate: { required:"Please specify the expiration Date" },
			     grant_contactEmail: { email:"Your email address must be in the format of name@domain.com" }
			 },
			 errorPlacement: function(error, element) {
			 }
		});
});
</script>

<script language="javascript" type="text/javascript">
function saveGrant(grantIdStr)
{
	if($('#newGrant').valid() === false ) return;
	$.ajax({
		type: "POST",
		url: "cust_pub_grant!saveGrant.action?grantIdStr="+grantIdStr,
		data: $("#newGrant").serialize(),
		success: function(msg){
                self.parent.location.reload();
		},
		error: function(msg) {
			alert("Failed to save new grant. Please contact system administrator for help.");
		}
	});
}

$(document).ready(function(){
	countryDefaults[0] = '';
	stateDefaults[0] = '';
	if($('#grant_id').attr('value') != '')
	{
		var sfx = "_"+$('#grant_id').attr('value')+"_sfx";
		$(":input[id^='grant_']").each(function(i){
			this.value = self.parent.$('#'+this.id+sfx).attr('value');
			if(this.id == 'grant_issueDate' || this.id == 'grant_exprDate')
			{
				this.value = self.parent.$('#'+this.id+sfx).attr('value').substring(0,10);
			}
			if(this.id == 'grant_country')
			{
				countryDefaults[0] = self.parent.$('#'+this.id+sfx).attr('value');
			}
			
			if(this.id == 'grant_state')
			{
				//alert(self.parent.$('#'+this.id+sfx).attr('value'));
				stateDefaults[0] = self.parent.$('#'+this.id+sfx).attr('value');
			}
		});
	}
	
	// after the data is loaded, init country list
	initCountry();
});
</script>
</body>
</html>
