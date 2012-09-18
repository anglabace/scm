<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>More Phone</title>
<style type="text/css">
<!--
body {
	font-family: Arial, Helvetica, sans-serif;
}
-->
</style>
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
	
<script type="text/javascript" src="${global_js_url}util/util.js"></script>
<script type="text/javascript" src="${global_js_url}scm/catalog.js"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1"
	type="text/javascript"></script>
<script>
$(function(){
	$("#copyNewCtlgForm").validate({
		invalidHandler: function(form, validator) {
	        $.each(validator.invalid, function(key,value){
	            alert(value);
	            $("[name='"+key+"']").focus();
	            return false;
	        });
    	},
		rules: {
    		"catalogDTO.catalogId": {required:true, minlength:1, maxlength:20},
    		"catalogDTO.catalogName": {required:true, minlength:1, maxlength:50}
		},
		messages: {
			"catalogDTO.catalogId": {
				required: "Please specify your Catalog Id",
				maxlength: "Catalog Id - max length 20"
			},
			"catalogDTO.catalogName": {
				required: "Please specify your Name",
				maxlength: "Catalog Name - max length 50"
			}
		},
		errorPlacement: function(error, element) {}		
	});
});
</script>
</head>

<body>
<form id="copyNewCtlgForm">
<table width="620" border="0" cellpadding="0" cellspacing="0"
	class="General_table" style="margin: auto;">
	<tr>
		<th>Catalog ID</th>

		<td><input name="catalogDTO.catalogId" type="text" id="catalogDTO.catalogId" size="
			20"  class="NFText" /></td>
		<th>Name</th>
		<td><input name="catalogDTO.catalogName" type="text" id="catalogDTO.catalogName" size="20"
			class="NFText" /></td>
	</tr>
	<tr>
		<th>Type</th>
		<td><select name="catalogDTO.type">
			<s:if test="type=='ALL'">
				<option value="ALL">all</option>
			</s:if>
			<s:if test="type=='PRODUCT'">
				<option value="PRODUCT" selected="selected">product</option>
			</s:if>
			<s:if test="type=='SERVICE'">
				<option value="SERVICE" selected="selected">service</option>
			</s:if>
		</select></td>
		<th>Status</th>
		<td><input name="catalogDTO.status" type="text" id="catalogDTO.status"
			readonly="readonly" value="INACTIVE" size="20" class="NFText" /></td>
	</tr>
	<tr>

		<th>Description</th>
		<td><textarea name="catalogDTO.description" class="content_textarea2"
			style="width: 250PX;"></textarea></td>
		<td>&nbsp;</td>
		<td>
			<input type="checkbox" name="catalogDTO.defaultFlag" id="checkbox" value="Y" />
		Default</td>
	</tr>
	<tr>
		<th>Currency</th>
		<td><s:select name="catalogDTO.currencyCode" list="currencyDropdownList"
			listKey="name" listValue="name" value="currencyCode" /></td>
		<td>&nbsp;</td>
		<td><s:if test="priceLimit==\"Y\"">
			<input name="catalogDTO.priceLimit" type="checkbox" id="checkbox2" value="Y"
				checked="checked" />
		</s:if> <s:else>
			<input name="catalogDTO.priceLimit" type="checkbox" id="checkbox2" value="Y" />
		</s:else> Enforce price Limit</td>
	</tr>
	<tr>
		<th>Publisher</th>
		<td><input type="text" size="20" readonly="readonly"
			class="NFText" /></td>
		<th></th>
		<td></td>

	</tr>
	<tr>
		<th>Date Published</th>
		<td><input name="cataDatePublished" type="text"
			readonly="readonly" size="20" class="NFText" /></td>
		<th>Zone Published</th>
		<td><s:select name="catalogDTO.publishZone" list="catalogDropdownList"
			listKey="value" listValue="value" value="publishZone" /></td>
	</tr>
	<tr>
		<th>Date Modified</th>

		<td><input name="cataDateModified" type="text" class="NFText"
			readonly="readonly" value="" size="20" /></td>
		<th>Modified By</th>
		<td><input name="cataModifyUser" type="text" class="NFText"
			readonly="readonly" value="" size="20" /></td>
	</tr>
	<tr>
		<th>Date Created</th>
		<td><input name="cataDateCreated" type="text" class="NFText"
			readonly="readonly" value="" size="20" /></td>
		<th>Created By</th>

		<td><input name="cataCreatUser" type="text" class="NFText"
			readonly="readonly" value="" size="20" /></td>
	</tr>
	<tr>
		<td colspan="4">
		<div align="center"><br />
		<input type="button" id="saveCopyNewTrigger" class="style_botton"
			value="Confirm" /> 
		<input type="button" id="cancelCopyCtlgTrigger"
			value="Cancel" class="style_botton" /> <input type="hidden"
			name="catalogDTO.id" value="${id}" /></div>
		</td>
	</tr>
</table>
</form>
</body>
</html>


