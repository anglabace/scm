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

<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>

<script language="javascript">
$(document).ready(function(){
    $('tr:even >td').addClass('list_td2');  
});
function selectCatalog(){
	var ctgNo = $("[name='catNos']:radio:checked").val();
	var ctgInfo = $("[name='ctgInfo_"+ctgNo+"']").val();
	if(ctgInfo==null||ctgInfo==""){
		alert("Please select one item to continue your operation.");
		return;
	}
	var arrCtgInfo = ctgInfo.split("<;>");
	var name = arrCtgInfo["0"];
	var standardPrice = arrCtgInfo["1"];
	var rltServiceId = arrCtgInfo["2"];
	parent.$("#crossDialog").contents().find("[name='crossDetail.rltCatalogNo']").val(ctgNo);
	parent.$("#crossDialog").contents().find("[name='crossDetail.rltName']").val(name);
	if(standardPrice==""){
		standardPrice = 0;
	}
	parent.$("#crossDialog").contents().find("[name='crossDetail.standardPrice']").val(standardPrice);
	parent.$("#crossDialog").contents().find("[name='crossDetail.rltServiceId']").val(rltServiceId);
	parent.$('#crossCtgNoSrchDialog').dialog('close');
	parent.$('#crossCtgNoSrchDialog').dialog('destory');
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
    <td width="90">Catalog No</td>
    <td width="135"><input name="catalogNo" type="text" class="NFText" size="20" value="${catalogNo}" /></td>
    <td width="57">Name</td>
    <td width="146"><input name="name" type="text" class="NFText" size="20" value="${name}" /></td>
    <td width="130"><input type="hidden" name="noList" value="${noList}" />
    <input type="submit" class="style_botton" value="Search" /></td>
  </tr>
</table>
</form>
<table width="583" border="0" cellpadding="0" cellspacing="0" class="list_table2">
  <tr>
    <th width="46">
      <div align="left">
      </div>
    </th>
    <th width="70">Catalog No</th>
    <th>Item</th>
    <th width="120">Lead Time(  Days)</th>
    <th width="70">Price</th>
  </tr>
</table>
<form method="post" id="formCatalogNo">
<div  style="width:600px; height:120px; overflow:scroll;">
  <table width="583" border="0" cellpadding="0" cellspacing="0" class="list_table2">
  	<s:iterator value="stdPriceList">
	  	<tr>
	      <td width="46"><input type="radio" name="catNos" value="${catalogNo}" /></td>
	      <td width="70">${catalogNo}</td>
	      <td>${name}</td>
	      <td width="120">${leadTime}</td>
	      <td width="70"><div align="right">${symbol}${standardPrice}</div></td>
	    </tr>
	     <input type="hidden" name="ctgInfo_${catalogNo}" value="${name}<;>${standardPrice}<;>${serviceId}" />
  	</s:iterator>
 </table>
</div>
<div style="margin:10px 0px; text-align:center;">
  <input type="button" class="style_botton"  value="Save" onclick="selectCatalog();" />
  <input type="button" value="Cancel"  class="style_botton" onclick="parent.$('#crossCtgNoSrchDialog').dialog('close');parent.$('#crossCtgNoSrchDialog').dialog('destory');" />
</div>
</form>

</body>
</html>