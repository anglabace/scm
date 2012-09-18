<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>catalog detail</title>
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script>
$(document).ready(function(){
	$("#moreinfoTable").find(":text, :textarea").bind("focus", function(){ $(this).trigger("blur"); });
});
</script>

</head>

<body>
<table id="moreinfoTable" border="0" cellpadding="0" cellspacing="0" class="General_table" width="100%">
  <tr>
    <th>Catalog ID </th>
    <td>
    	<input name="textfield" type="text" class="NFText" value="${catalog.catalogId}" size="20" readonly="readonly"/>    
   	</td>
    <th width="100">Name</th>
    <td>
    	<input name="textfield2" type="text" class="NFText" value="${catalog.catalogName}" size="20" />
    </td>
  </tr>
  <tr>
    <th valign="top">Type</th>
    <td valign="top">
		<input name="textfield2" type="text" class="NFText" value="${catalog.type}" size="20" />	
    </td>
    <th>Status</th>
    <td>
    	<input name="textfield22" type="text" class="NFText" value="${catalog.status}" size="20" />
       <br />
       <s:checkbox name="" value='catalog.defaultFlag == "Y"'></s:checkbox>
	   Default
	</td>
  </tr>
  <tr>
    <th valign="top">Description</th>
    <td colspan="3">
    	<textarea name="textarea" class="content_textarea2">${catalog.description}</textarea>    
    </td>
  </tr>
  <tr>
    <th valign="top">Currency</th>
    <td>
    	<input name="textfield22" type="text" class="NFText" value="${catalog.currencyCode}" size="20" />
	</td>
    <th>&nbsp;</th>
    <td valign="top">
      <s:checkbox name="" value='catalog.priceLimit == "Y"'></s:checkbox>
      Enforce price Limit
    </td>
  </tr>
  <tr>
    <th>Publisher</th>
    <td><input name="textfield3323" type="text" class="NFText" value="${catalog.publisher}" size="20" readonly="readonly"/></td>
    <th>Zone Published </th>
    <td>
    	<input name="" type="text" class="NFText" value="${catalog.publishZone}" size="20" readonly="readonly"/>  
    </td>
  </tr>
  <tr>
    <th>Date Published </th>
    <td><input name="" type="text" class="NFText" value='<s:date name="publishDate" format="yyyy-MM-dd"/>' size="20" readonly="readonly" /></td>
    <th>&nbsp;</th>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th>Date Modified </th>
    <td><input name="textfield322" type="text" class="NFText" value='<s:date name="catalog.modifyDate" format="yyyy-MM-dd"/>' size="20" readonly="readonly" /></td>
    <th>Modified By </th>
    <td><input name="textfield332" type="text" class="NFText" value="${catalog.modifyUser}" size="20" readonly="readonly"/></td>
  </tr>
  <tr>
    <th>Date Created </th>
    <td><input name="textfield3222" type="text" class="NFText" value='<s:date name="catalog.creationDate" format="yyyy-MM-dd"/>' size="20" readonly="readonly" /></td>
    <th>Created By </th>
    <td><input name="textfield3322" type="text" class="NFText" value="${catalog.createUser}" size="20" readonly="readonly"/></td>
  </tr>
</table>
<div  class="botton_box">
  <input type="button" name="close" value="Close" class="style_botton" onclick="parent.$('#itemMoreInfoDialog').dialog('close');"  />
</div>
</body>
</html>
