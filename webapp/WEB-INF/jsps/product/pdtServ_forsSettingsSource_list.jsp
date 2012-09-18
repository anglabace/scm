<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}thickbox/thickbox.css" rel="stylesheet" type="text/css" />
<script src="${global_js_url}jquery/jquery.js" type="text/javascript"></script>
<script src="${global_js_url}thickbox/jquery-1.1.3.1.pack.js" type="text/javascript"></script>
<script src="${global_js_url}thickbox/thickbox-compressed.js" type="text/javascript"></script>
<style type="text/css">
<!--
body {
	width:570px; margin:5px auto;
}
-->
</style>
<script language="javascript">
function  selectAll(e)
{
	var a = document.getElementsByName("Item_add");
	for (var i=0; i<a.length; i++) {
		a[i].checked  =  e.checked;
	}
}

function selectPdtList(){
	var a = document.getElementsByName("item_add");
	var exist = parent.$("#catalogNo").val();
	var codes = "";
	for (var i=0; i<a.length; i++) {
		if (a[i].checked && exist.indexOf(a[i].value+",") == -1 ){
			exist += a[i].value +",";
		}
	}
	//codes = codes.substring(0, codes.length-1);
	//alert ("get:" + codes);

	parent.$("#catalogNo").val(exist);
	parent.$('#searchPS').dialog('close');
}
</script>
</head>
<body>
<form method="get" action="">
<table width="570" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>&nbsp;</td>
    <th>Lookup On </th>
    <th>Condition</th>
    <th>Value</th>
    <th>&nbsp;</th>
  </tr>
  <tr>
    <td><select id='selecttb' onchange="a(this)" style="width:100px">
      <option  value='1' selected="selected">Product Item</option>
      <option value='2'>Service Item</option>
    </select></td>
    <td>
	<div id="tb1" style='display:block'>
	<select name="propName" style="width:100px">
	<option value="name"<s:if test="propName == 'name'"> selected="selected"</s:if>>Product Name</option>
	<option value="catalogNo"<s:if test="propName == 'catalogNo'"> selected="selected"</s:if>>Catalog No</option>
	</select>
	</div>
	<div id="tb2" style='display:none;'>
	<select style="width:100px">
	<option>Service Name</option>
	</select>
	</div>
	</td>
	<td>
    <select name="srchOperator" style="width:100px"><s:property value="searchProperty.searchPropertyType"/>
      <option value="EQ"<s:if test="srchOperator == 'EQ'"> selected="selected"</s:if>>=</option>
      <option value="GT"<s:if test="srchOperator == 'GT'"> selected="selected"</s:if>>&gt;</option>
      <option value="GE"<s:if test="srchOperator == 'GE'"> selected="selected"</s:if>>&gt;=</option>
      <option value="LT"<s:if test="srchOperator == 'LT'"> selected="selected"</s:if>>&lt;</option>
      <option value="LE"<s:if test="srchOperator == 'LE'"> selected="selected"</s:if>>&lt;=</option>
      <option value="NE"<s:if test="srchOperator == 'NE'"> selected="selected"</s:if>>!=</option>
      <option value="LIKE"<c:if test="srchOperator == 'LIKE'"> selected="selected"</c:if>>LIKE</option>
    </select>
    </td>
    <td><input type="text" name="propValue1" class="NFText" size="14" value="${propValue1}" /></td>
    <td><input type="submit" class="style_botton" value="Search" /></td>
  </tr>
</table>
</form>
  <table width="570" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td colspan="4" style="padding-top:10px;"><table width="550" border="0" cellpadding="0" cellspacing="0" class="list_table">
      <tr>
        <th width="28"></th>
        <th width="70">Ctlg  No</th>
        <th width="350"> Name </th>
        <th>Size</th>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="4" style="padding-bottom:20px; "><div  style="width:567px; height:150px; overflow:scroll;">
      <table width="550" border="0" cellpadding="0" cellspacing="0" class="list_table">
      	<s:iterator value="pageProduct.result">
        <tr>
          <td width="28"><div align="center">
              <input type="checkbox" value="${catalogNo}" name="item_add" />
          </div></td>
          <td width="70"><div align="center">${catalogNo}</div></td>
          <td width="350">${name}</td>
          <td>${size}</td>
        </tr>
        </s:iterator>
      </table>
    </div></td>
  </tr>
  <tr>
    <td colspan="4">
      <div align="center">
        <input name="Submit" type="submit" class="style_botton"  value="Select" onclick="selectPdtList();" />
        <input type="submit" name="Submit2" value="Close" class="style_botton" onclick="parent.$('#searchPS').dialog('close');" />
      </div>
	</td>
  </tr>
</table>
</body>
</html>
