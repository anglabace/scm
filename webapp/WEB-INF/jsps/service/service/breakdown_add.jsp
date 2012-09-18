<%-- 
    Document   : breakdown_add
    Created on : 2010-9-24, 11:37:58
    Author     : jinsite
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include  file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>

<script language="javascript">
$(document).ready(function(){
    $('tr:even >td').addClass('list_td2');
});
function cc(e){
	var a = document.getElementsByName("catNos");
	for(var i=0;i<a.length;i++)a[i].checked = e.checked;
}
function get_checked_str(name)
{
	var a = document.getElementsByName(name);
	var str = '';
	for   (var   i=0;   i<a.length;   i++)
	{
		if(a[i].checked)
		{
			str += $("#breakdownItemInfo_"+a[i].value).val()+'<==>';
		}
	}
	if(str) {
		return str.substring(0,str.length-4);
	}
	return null;
}
function get_checked_idArr(name){
	var a = document.getElementsByName(name);
	var arrR = new Array();
	j=0;
	for   (var   i=0;   i<a.length;   i++)
	{
		if(a[i].checked)
		{
			arrR[j] = a[i].value;
			j++;
		}
	}
	return arrR;
}
function quantityToCheck(catNo)
{
	$("#catNos_"+catNo).attr("checked","checked");
}
function quantityToStr(catNo)
{
	str = $("#breakdownItemInfo_"+catNo).attr("value");
	arrStr = str.split("<;>");
	arrStr.pop();
	arrStr.push($("#quantity_"+catNo).attr("value"));
	newStr = arrStr.join("<;>");
	$("#breakdownItemInfo_"+catNo).attr("value",newStr);
}
function saveItemToSession(){
	var str_item_str = get_checked_str("catNos");
	var arrCatNo = get_checked_idArr("catNos");
        var psId=$("#psId").val();
      	trStr = "";
	len = arrCatNo.length;
	objBreakdown = parent.document.getElementById("breakdownList_iframe").contentWindow;
	//var trlen = objBreakdown.$("#breakdownListTable" + " tr").length;
	$.ajax({
		type:"POST",
		url:"serv/breakdown!save.action",
		data:"catStr="+str_item_str+"&psId=${psId}&type=${type}&sessionServiceId=${sessionServiceId}",
		success: function(msg){
			objBreakdown.location.reload();
                       // parent.document.getElementById("inventroyIframe").src = parent.$("#inventroyIframe").attr("src");
			parent.$('#breakdownDialog').dialog('close');
			//parent.$('#breakdownDialog').dialog('destroy');
		},
		error: function(msg){
			alert("Failed to save the new break-down item.Please contact system administrator for help.");
		}
	});
	return false;
}

</script>
<style type="text/css">
<!--
body{margin:10px auto;width:600px;}
-->
</style></head>

<body>
<form action="" method="get">
<table width="558" border="0" cellpadding="0" cellspacing="0" class="General_table">
  <tr>
    <th width="90">Catalog No</th>
    <td width="135"><input name="filter_LIKES_catalogNo" type="text" class="NFText" size="20" value="${param['filter_LIKES_catalogNo']}"/></td>
    <th width="57">Name</th>
    <td width="146"><input name="filter_LIKES_name" type="text" class="NFText" size="20" value="${param['filter_LIKES_name']}" /></td>
    <td width="130"><input type="hidden" name="noList" value="${noListStr}" />
    <input type="hidden" name="sessionServiceId" id="sessionServiceId" value="${sessionServiceId}" />
    <input type="hidden" name="type" value="${type}" />
    <input type="hidden" name="psId" value="${psId}" />
    <input type="submit" class="style_botton" value="Search" /></td>
  </tr>
</table>
</form>
<table width="583" border="0" cellpadding="0" cellspacing="0" class="list_table2">
  <tr>
    <th width="46">
      <div align="left">
      <input type="checkbox" onclick="cc(this);" />
      </div>
    </th>
    <th width="70">Catalog No</th>
    <th>Item</th>
    <th width="70">Quantity</th>
    <th width="120">Lead Time(  Days) </th>
  </tr>
</table>
<form method="post" id="formCatalogNo">
<div  style="width:600px; height:120px; overflow:scroll;">
  <table width="583" border="0" cellpadding="0" cellspacing="0" class="list_table2">	
    
    <s:iterator value="ppStdPrice.result" status="pStdPrice">
    <tr>
        <td width="46"><input type="checkbox" name="catNos" id="catNos_<s:property value="catalogNo"/>" value="<s:property value="catalogNo"/>" /></td>
      <td width="70"><s:property value="catalogNo"/>&nbsp;</td>
      <td><s:property value="name"/>&nbsp;</td>
      <td width="70"><input type="text" name="quantity_<s:property value="catalogNo"/>" id="quantity_<s:property value="catalogNo"/>" class="NFText" size="5" value="1"
      	onfocus="quantityToCheck('<s:property value="catalogNo"/>');" onkeyup="quantityToStr('<s:property value="catalogNo"/>');" />&nbsp;</td>
        <td width="120"><s:property value="leadTime"/>&nbsp;</td>
    </tr>
    <input type="hidden" name="breakdownItemInfo_<s:property value="catalogNo"/>" id="breakdownItemInfo_<s:property value="catalogNo"/>" value="<s:property value="catalogNo"/><;><s:property value="name"/><;><s:property value="leadTime"/><;><s:property value="symbol"/><;><s:property value="standardPrice"/><;>1" />
   </s:iterator>
   
   
 </table>
</div>
<div class="grayr">
			<jsp:include page="/common/db_pager.jsp">
			  <jsp:param value="${ctx }/serv/breakdown!input.action" name="moduleURL"/>
			</jsp:include>
</div>
<div style="margin:10px 0px; text-align:center;">
  <input type="button" class="style_botton"  value="Save" onclick="saveItemToSession();" />
  <input type="button" value="Cancel"  class="style_botton" onclick="javascript:parent.$('#breakdownDialog').dialog('destory');parent.$('#breakdownDialog').dialog('close');" />
</div>
</form>

</body>
</html>