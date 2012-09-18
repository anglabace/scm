<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>

<style type="text/css">
<!--
body {
	margin-left:10px;margin-top:10px;  
}
-->
</style>
</head>

<body>
<form id="newPub" method="post" action="cust_pub_grant!savePub.action" >
<input type="hidden" name="custNo" id="custNo" value="${custNo}" />
<input type="hidden" name="sessCustNo" id="sessCustNo" value="${sessCustNo}" />
<input type="hidden" name="pub_id" id="pub_id" value="${pubIdStr}" />
<table width="600" border="0" cellpadding="0" cellspacing="0" class="General_table">
  <tr>
    <td align="right"><span class="important">*</span>Title</td>
    <c:set var="pubtitle" value="0"></c:set>
    <!-- 
    <c:if test="${pubtitle ==0}">
    <td><input name="pub_title" id="pub_title" type="text" class="NFText" size="20" />    </td>
    </c:if>
     -->
    <c:if test="${pubtitle ==1}">
    <td><input name="pub_title" id="pub_title" type="text" class="NFText" size="20" />    </td>
    </c:if>
    <td><input name="pub_title" id="pub_title" type="text" class="NFText" size="20" />    </td>
    <td align="right"><span class="important">*</span>Grspg Authors </td>
    <td><input name="pub_coAuthor" id="pub_coAuthor" type="text" class="NFText" size="20" /></td>
  </tr>

  <tr>
    <td align="right">E-mail</td>
    <td colspan="3"><input name="pub_email" id="pub_email" type="text" class="NFText" size="20" /></td>
  </tr>
  <tr>
    <td align="right"><span class="important">*</span>Publication Name </td>
    <td><input name="pub_publicationName" id="pub_publicationName" type="text" class="NFText" size="20" /></td>
    <td align="right"><span class="important">*</span>URL </td>

    <td><input name="pub_url" id="pub_url" type="text" class="NFText" size="20" /></td>
  </tr>
  <tr>
    <td valign="top" align="right"><span class="important">*</span>Abstract </td>
    <td colspan="3"><label>
      <textarea name="pub_abst"  id="pub_abst" class="content_textarea2"></textarea>
    </label></td>

  </tr>
  <tr>
    <td valign="top" align="right"><span class="important">*</span>Related Area </td>
    <td colspan="3"><textarea name="pub_relatedArea" id="pub_relatedArea" class="content_textarea2"></textarea></td>
  </tr>
  <tr>
    <td align="right"><span class="important">*</span>Keywords </td>

    <td colspan="3"><input name="pub_keyWords" id="pub_keyWords" type="text" class="NFText" size="50" /></td>
  </tr>
  <tr>
    <td height="40" colspan="4"><div align="center">
      <br />
      <input type="button" class="style_botton" value="Save" onclick="savePub('${pubIdStr}');" />
      <input type="button" class="style_botton"  value="Cancel" onclick="parent.$('#editPublication').dialog('close');parent.$('#newPublication').dialog('close');" />
      <br />
    </div></td>

  </tr>
</table>
</form>
<script src="${global_js_url}jquery/jquery.validate.js?v=1" type="text/javascript"></script>

<script language="javascript" type="text/javascript">
$(function(){
	jQuery.validator.addMethod("isUrl", function(value, element, param) {
		  var tmpVal = value.replace(/ /g,"");
		  var regexp = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
		  return this.optional(element) || regexp.test(tmpVal);       
		}, "Please enter a valid url");
		
	$('#newPub').validate({
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
			     pub_title: { required:true },
			     pub_coAuthor: { required:true },
			     pub_publicationName: { required:true },
			     pub_url: { required:true,isUrl:true },
			     pub_abst: { required:true},
			     pub_relatedArea: { required:true },
			     pub_keyWords: { required:true },
			     pub_email: {email:true}
			 },
			 messages: {
			     pub_title: { required:"Please specify the title" },
			     pub_coAuthor: { required:"Please specify the Grspg Authors" },
			     pub_publicationName: { required:"Please specify the Publication Name" },
			     pub_url: { required:"Please specify the URL",isUrl:"Url format: http://www.genscript.com/" },
			     pub_abst: { required:"Please specify the Abstract"},
			     pub_relatedArea: { required:"Please specify the Related Area" },
			     pub_keyWords: { required:"Please specify the Keywords" },
			     pub_email: { email:"Your email address must be in the format of name@domain.com" }
			 },
			 errorPlacement: function(error, element) {
			 }
		});
});
</script>
<script language="javascript">
function savePub(pubIdStr)
{
	if($('#newPub').valid() === false ) return;
	$.ajax({
		type: "POST",
		url: "cust_pub_grant!savePub.action?pubIdStr="+pubIdStr,
		data: $("#newPub").serialize(),
		success: function(msg){
			self.parent.location.reload();
			//self.parent.tb_remove();
		},
		error: function(msg) {
			alert("System error! Please contact system administrator for help.");
		}
	});
}

$(document).ready(function(){
	if($("#pub_id").attr("value") == '')
	{
		$("#pub_url").val('http://');
	}
	if($("#pub_id").attr("value") != '')
	{
		var sfx = "_"+$("#pub_id").attr("value")+"_pubsfx";
		$(":input[id^='pub_']").each(function(i){
			this.value = self.parent.$('#'+this.id+sfx).attr("value");
		});
	}
});



</script>

</body>
</html>
