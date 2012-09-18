<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
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
	parent.$('#generalCrossDialog').dialog('close');
}
function saveCross(){
	if($("#effFrom").val()>$("#effTo").val()){
		alert("Please select the correct time range.");return;
	}
	$.ajax({
		type:"POST",
		url:"serv/serv!saveSessionCrossAct.action",
		data:$("#formCross").serialize(),
		dataType:"json",
		success: function(msg){    
			var str = '';	
			str += '<option value="'+msg.id+'">' + $("#relationType").val()+ '-' +$("#rltCatalogNo").val()+ '</option>';	
			parent.$("#cross option[value='"+ msg.id +"']").remove(); 
			parent.$("#cross").prepend(str);
			parent.$("#catalogNoSpan").prepend("<input type=\"hidden\" name=\"crossCatalogNo\" value='"+$("#rltCatalogNo").val()+"' />");
		    parent.$('#generalCrossDialog').dialog('close');
			parent.$('#generalCrossDialog').dialog('destroy');		    
		},
		error: function(msg){
			alert("System error! Please contact system administrator for help.");
		}
	});
}
$().ready(function(){
	$('.ui-datepicker').each(function(){
		$(this).datepicker(
		{
			dateFormat: 'yy-mm-dd',
			changeMonth: true,
			changeYear: true
		});
	});
	$('#effFrom').datepicker();
	$('#effTo').datepicker();
	var effFrom = "";
	var effTo = "";
	$('[name="displayDate"]').click(function(){
		if($('#effFrom').val()){
			effFrom = $('#effFrom').val();
		}
		if($('#effTo').val()){
			effTo = $('#effTo').val();
		}
		if($(this).attr("checked")){
			$('#effFrom').val(effFrom);
			$('#effTo').val(effTo);
			$('#effFrom').attr("disabled",false);
			$('#effTo').attr("disabled",false);
		}else{
			$('#effFrom').val("");
			$('#effTo').val("");
			$('#effFrom').attr("disabled",true);
			$('#effTo').attr("disabled",true);
		}
	});
});
function catalogNoDialog(){
	parent.$("#crossCtgNoSrchDialog").dialog({
		autoOpen: false,
		height: 400,
		width: 720,
		modal: true,
		bgiframe: true,
		buttons: {}
	});	
	parent.$('#crossCtgNoSrchDialog').dialog("option", "open",function(){
		var noList = parent.$().find("[name='crossCatalogNo']");
		var noListLen = noList.length;
		var noListStr = "";
		for(i=0;i<noListLen;i++){
			noListStr += noList[i].value + "<;>";
		}
		if(noListStr){
			noListStr = noListStr.slice(0,-3);
		}
		var htmlStr = '<iframe src="serv/serv!searchStdPriceList.action?noList='+noListStr+'&type=Service" height="340" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
		parent.$('#crossCtgNoSrchDialog').html(htmlStr);
	});
	parent.$('#crossCtgNoSrchDialog').dialog('open');	
}
function countUnitPrice()
{
	var standardPrice = $("input[name='crossDetail.standardPrice']").val();
	var discount =  $("input[name='crossDetail.discount']").val();
	var untilPrice =  Number(standardPrice - standardPrice * discount/100).toFixed(2);
	$("input[name='crossDetail.unitPrice']").attr("value",untilPrice);
}
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
<table width="650" border="0" cellpadding="0" cellspacing="0" class="General_table">
  <tr>
    <td><fieldset>
      <legend>Item Info </legend>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <th>Type of Selling Item </th>
          <td>
          <input type="hidden" name="crossDetail.serviceId" id="serviceId" value="${crossDetail.serviceId }" />
          <input type="hidden" name="sessionServiceId" id="sessionServiceId" value="${sessionServiceId }" />
          <input type="hidden" name="crossDetail.relationId" id="relationId" value="${crossDetail.relationId }" />
           <s:select name="crossDetail.relationType" id="relationType" list="pbOptionItemList" listValue="text" listKey="text" value="crossDetail.relationType"/>
          </td>
          <th>Lookup Status </th>
          <td>
          <s:if test="crossDetail.lookupFlag==\"Y\"">
          	<select name="crossDetail.lookupFlag" id="lookupFlag">
            	<option value="Y" selected="selected">Yes</option>
            	<option value="N">No</option>
          	</select>
          </s:if>
          <s:else>
          	<select name="crossDetail.lookupFlag" id="lookupFlag">
            	<option value="Y">Yes</option>
            	<option value="N" selected="selected">No</option>
          	</select>
          </s:else>
          </td>
        </tr>
        <tr>
          <th>Item Catalog No </th>
          <td><input name="crossDetail.rltCatalogNo" id="rltCatalogNo" type="text" class="NFText" size="20"  value="${crossDetail.rltCatalogNo}" readonly="readonly" />
              <img src="images/search.gif" width="16" height="16" align="absmiddle" onclick="catalogNoDialog()" style="cursor:pointer;" /> <br /></td>
          <th>Name</th>
          <td>
          	<input name="crossDetail.rltName" id="rltName"  value="${crossDetail.rltName}" type="text" class="NFText" readonly="readonly" />
          	<input name="crossDetail.rltServiceId" id="rltServiceId" value="${crossDetail.rltServiceId}" type="hidden" />
          </td>
        </tr>
      </table>
      </fieldset>
        <fieldset>
        <legend>Display / Pricing </legend>
          <div id="tb1" style='display:block;'>
          <table border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="81">&nbsp;</td>
              <td colspan="3" width="316">
              	<s:if test="crossDetail.displayOnlyBo==\"Y\"">
              		<input name="crossDetail.displayOnlyBo"  type="checkbox" value="Y" checked="checked"/>
              	</s:if>
                <s:else>
                	<input name="crossDetail.displayOnlyBo"  type="checkbox" value="Y"/>
				</s:else>
                Display Only When Item is Back Ordered </td>
              <td>
              	<s:if test="crossDetail.mandatoryFlag==\"Y\"">
              		<input type="checkbox" name="crossDetail.mandatoryFlag" value="Y" checked="checked"/>
              	</s:if>
                <s:else>
                	<input type="checkbox" name="crossDetail.mandatoryFlag" value="Y"/>
				</s:else>
                Substitute Item is Mandatory </td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td colspan="4">
              	<s:if test="crossDetail.effFrom!=null">
              		<input type="checkbox" checked="checked" name="displayDate" value="checkbox" />
              		 Display Only From
                	<input name="crossDetail.effFrom" id="effFrom" type="text" class="NFText ui-datepicker" style="width:100px;" value="<s:date name='crossDetail.effFrom' format='yyyy-MM-dd'  />" readonly="readonly" />
                	To
                	<input name="crossDetail.effTo" id="effTo" type="text" class="NFText ui-datepicker" style="width:100px;" value="<s:date name='crossDetail.effTo' format='yyyy-MM-dd' />" readonly="readonly" />
              	</s:if>
                <s:else>
                	<input type="checkbox"  name="displayDate" value="checkbox" />
                	 Display Only From
                	<input name="crossDetail.effFrom" id="effFrom" type="text" class="NFText ui-datepicker" style="width:100px;" value="<s:date name='crossDetail.effFrom' format='yyyy-MM-dd' />" readonly="readonly" disabled="disabled"/>
               		 To
                	<input name="crossDetail.effTo" id="effTo" type="text" class="NFText ui-datepicker" style="width:100px;" value="<s:date name='crossDetail.effTo' format='yyyy-MM-dd' />" readonly="readonly" disabled="disabled"/>
				</s:else>
               </td>
            </tr>
            <tr>
              <td colspan="5">
              	<b>Standard Price</b>
              	<input name="crossDetail.standardPrice" value="${crossDetail.standardPrice}" type="text" class="NFText" size="8" readonly="readonly" />
              	&nbsp;&nbsp;<b>With Discount</b>
              	<input name="crossDetail.discount" value="${crossDetail.discount}" type="text" class="NFText" size="8" onkeyup="countUnitPrice();" /> %
              	&nbsp;&nbsp;<b>Selling Price</b>
              	<input name="crossDetail.unitPrice" value="${crossDetail.unitPrice}" type="text" class="NFText" size="8" readonly="readonly" />
              </td>
            </tr>
          </table>
          </div>
      </fieldset>
	  <table width="100%" cellspacing="0" cellpadding="0" border="0" class="General_table">
          <tbody>
          <tr>
            <th width="30%">Comments</th>
            <td width="70%"><textarea name="crossDetail.relateInfo" class="content_textarea2">${crossDetail.relateInfo}</textarea></td>
          </tr>
      </tbody>
	  </table>
</td>
  </tr>
  <tr>
    <td class="botton_box"><input type="button" name="save"class="style_botton"  value="Save"  onclick="saveCross();" />
        <input type="submit" name="cancel" value="Cancel"  class="style_botton" onclick="javascript: closeCross();" />
    </td>
  </tr>
</table>
<input type="hidden" name="type" value="Service" />
</form>
</body>
</html>