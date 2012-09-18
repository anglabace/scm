<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script language="javascript">
function closeCross()
{	
	var prefix = $("#prefix option:selected").text();
	var seq = $("#crrentSeq").val();
	var categoryId = $("#categoryId option:selected").val();
	var catalogNo = prefix+seq;
	if(catalogNo==null||catalogNo==''||catalogNo=='null'){
		alert("Please enter the Catalog No.");return ;
	}
	parent.$('#oldCatalogNo').val(catalogNo);
	var size =  $.trim(parent.$("#size").val());
	var uom =  $.trim(parent.$("#uom").val());
	if(size!=''){
		catalogNo += "-"+size; 
		if(uom!=''){
			catalogNo += "-"+uom; 
		}
	}
	parent.$('#categoryId').val(categoryId);
	parent.$('#catalogNo').val(catalogNo);
	parent.$('#catalogNoRuleId').val($('#ruleId').val());
	parent.$('#generateCatalogNoDialog').dialog('close');
}
function getPrefix(){
	var categoryId = $('#categoryId option:selected').val();
	if(categoryId==null||categoryId==""){
		return ;
	}
	var type = '${type}';
	$.ajax ({
		type:"POST",
		url:"product/product!searchCatalogNoRulesPrefix.action",
		dataType:"json",
		data:"categoryId="+categoryId+"&type="+type,
		success: function(msg){
				var html = "<select id='prefix' onchange='setCurrentSeq()'>";
				html += "<option value=''></option>";
				for(var i =0;i<msg.catalogNoRulesList.length;i++){
					var name= msg.catalogNoRulesList[i].prefix;
					var value = msg.catalogNoRulesList[i].currentSeq;
					var ruleId = msg.catalogNoRulesList[i].ruleId;
					html +="<option value='"+value+","+ruleId+"'>";
					html +=name;
					html +="</option>";
				}
				html += "</select>";
				$("#categoryIdRule").html(html);
		},
		error: function(msg){
			alert("Failed to selected the prefix.Please contact system administrator for help.");
		}
	});
}
function setCurrentSeq(){
	var value = $('#prefix option:selected').val();
	var values = value.split(",");
	var seq = values[0];
	var ruleId = values[1];
	$.ajax ({
		type:"POST",
		url:"product/product!searchCatalogNoRulesCurrentSeq.action",
		dataType:"json",
		data:"ruleId="+ruleId,
		success: function(msg){
			seq	=msg.currentSeq;
			$("#crrentSeq").val(seq);
			$("#ruleId").val(ruleId);
		},
		error: function(msg){
			alert("Failed to selected the Current Seq.Please contact system administrator for help.");
		}
	});
	
}
$(document).ready(function(){ 
	var id= $("#ruleId option:selected").val();
	$("#examples").val($("#ruleId_"+id).val());
	$("#catalogNo").val($("#ruleId_"+id).val());
});
</script>

<style type="text/css">
<!--
fieldset fieldset{
	margin:2px;
}
-->
</style>
</head>

<body>
<form id="formCross" name="formCross">
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
       
   <tr>
    <th width="121">&nbsp;</th>
    <td colspan="2">&nbsp;</td>
    </tr>
  <tr>
    <th width="121">
    	<s:if test="type==\"PRODUCT\"">Product Lines</s:if>
    	<s:else>Service Lines</s:else>
    </th>
    <td width="166">
    <s:select name="categoryId" id="categoryId" list="catalogNoRulesDTOList" listValue="categoryNo" listKey="categoryId" headerKey="" headerValue="" onchange="getPrefix()"/>
    <input type="hidden" name="ruleId" id="ruleId"/>
    </td>
    <td >
    	<div id="categoryIdRule">
    		<select id='prefix'></select>
    	</div>
    </td>
    </tr>
      <tr>
      
    <th width="121">&nbsp;</th>
    <td colspan="2" ><input name="crrentSeq" id="crrentSeq" type="text" class="NFText" value="" size="25"/>
     </td>
    </tr>
  <tr>
      
    <th width="121">&nbsp;</th>
    <td colspan="2" >&nbsp;
     </td>
    </tr>
  <tr>
    <td class="botton_box" colspan="2" >
      <input type="submit" name="Submit62"class="style_botton"  value="Confirm" onclick="closeCross()"/>
      
    </td>
  </tr>
</table>
<input type="hidden" name="type" value="Product" />
</form>
</body>
</html>