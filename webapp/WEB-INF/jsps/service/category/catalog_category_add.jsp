<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>

<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>

<script><!--
$(function(){
	parent.$('#chooseCategoryDialog').dialog({
		autoOpen: false,
		height: 480,
		width: 360,
		modal: true,
		bgiframe: true,
		buttons: {}
	}); 
	$("#chooseCategoryDialogTrigger").click(function(){
		parent.$('#chooseCategoryDialog').dialog("option", "open",function(){
			var htmlStr = '<iframe id="chooseCategoryDialog" src="${ctx}/serv/service_category!ctlgChooseCatList.action?categoryLevel=1&cataId=${cataId}&sessionCatalogId=${sessionCatalogId}" height="400" width="320" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			parent.$('#chooseCategoryDialog').html(htmlStr);
		});	
		parent.$('#chooseCategoryDialog').dialog('open');										 	
	});
});

function saveCatCategory(){
	ctgNo = $("#ctgNo").val();
	if(!ctgNo){
		alert("Please enter the Category No.");return;
	}
	var ctgId = $("#ctgId").val();
	$.ajax({
		type:"POST",
		url:"serv/service_category!addServiceCategoryToCatalog.action?serviceCategoryKey="+ctgId,
		data:$("#formCatPdtCategory").serialize(),
		success: function(msg){
			if(msg=="success"){
				if(ctgNo.length>10){
					ctgNo=ctgNo.substring(0,10);
				}
				var ctgName = $("#ctgName").val();
				if(ctgName.length>15){
					ctgName=ctgName.substring(0,15)+"...";
				}
				var ctgDescription = $("#ctgDescription").val();
				if(ctgDescription.length>15){
					ctgDescription=ctgDescription.substring(0,15)+"...";
				}
				var ctgStatus = $("#ctgStatus").val();
				var ctgAstGrp = $("#ctgAstGrp").val();
				var ctgMby = $("#ctgMdate").val();
				var ctgCby = $("#ctgCdate").val();
				var ctgBuss = $("#ctgBuss").val();
				var appendStr = "<tr id=del_"+ctgId+"><td width='46'>&nbsp;<input type='checkbox' value="+ctgId+" name='ctgIds'/></td>";
				appendStr += "<td width='85' style='word-break:break-all;word-wrap:break-word;width:85'>&nbsp;"+ctgNo+"</td>";
				appendStr += "<td width='146' style='word-break:break-all;word-wrap:break-word;width:146'>&nbsp;"+ctgName+"</td>";
				appendStr += "<td width='148' style='word-break:break-all;word-wrap:break-word;width:148'>&nbsp;"+ctgDescription+"</td><td width='81'>&nbsp;"+ctgStatus+"</td><td width='119'>&nbsp;"+ctgAstGrp+"</td><td width='113'>"+ctgBuss+"&nbsp;</td><td width='89'>&nbsp;"+ctgMby+"</td><td>&nbsp;"+ctgCby+"</td></tr>";
				parent.$("#categoryServiceList_iframe").contents().find("#servCategoryTb").append(appendStr);
				parent.$('#addCategoryDialog').dialog('close');
				parent.$('#addCategoryDialog').dialog('destory');
			}
		},
		error: function(msg){
			alert("System error! Please contact system administrator for help.");
		}
	});
}
--></script>

<style type="text/css">
<!--
body {margin:10px 0px 0px 20px;width:620px;}
-->
</style>
</head>

<body>
<form method="post" id="formCatPdtCategory">
<table border="0" cellpadding="0" cellspacing="0" class="General_table">
  <tr>
    <th>Business Unit No </th>
    <td>
    <input name="ctgNo" id="ctgNo" type="text" class="NFText" size="25" readonly="readonly" />
    <input type="hidden" name="ctgId" id="ctgId" />
    <input type="hidden" name="sessionCatalogId" id="sessionCatalogId" value="${sessionCatalogId}" />
    &nbsp;&nbsp;<img src="${global_image_url}search.jpg" style="cursor:pointer;" id="chooseCategoryDialogTrigger" />
    </td>
    <th>Name</th>
    <td><input name="ctgName" id="ctgName" type="text" class="NFText" value="" size="25" readonly="readonly" /></td>
  </tr>
  <tr>
    <th>Asset Group </th>
    <td>
   		<s:select name="ctgAstGrp" id="ctgAstGrp" list="categoryDropdownList"
			listKey="value" listValue="value"/>
    </td>
    <th valign="top">Status</th>
    <td><input name="ctgStatus" id="ctgStatus" type="text" class="NFText" value="" size="25" readonly="readonly" /></td>
  </tr>
  <tr>
    <th valign="top">Description</th>
    <td colspan="3"><textarea name="ctgDescription" id="ctgDescription" class="content_textarea2"></textarea></td>
  </tr>
  <tr>
    <th>Parent Category </th>
    <td><input name="ctgParentCtgName" id="ctgParentCtgName" type="text" class="NFText" readonly="readonly" size="25" /></td>
    <th>Previous Category </th>
    <td><input name="ctgPreCtgName" id="ctgPreCtgName" type="text" class="NFText" readonly="readonly" size="25" /></td>
  </tr>
  <tr>
    <th>Date Modified </th>
    <td><input name="ctgMdate" id="ctgMdate" type="text" class="NFText" size="25" readonly="readonly" /></td>
    <th>Modified By </th>
    <td><input name="ctgMby" id="ctgMby" type="text" class="NFText" size="25" disabled="disabled" /></td>
  </tr>
  <tr>
    <th>Date Created </th>
    <td><input name="ctgCdate" id="ctgCdate" type="text" class="NFText" size="25" readonly="readonly" /></td>
    <th>Created By </th>
    <td><input name="ctgCby" id="ctgCby" type="text" class="NFText" size="25" disabled="disabled" /></td>
  </tr>
</table>
<div  style="margin-top:20px; text-align:center">
  <input type="hidden" name="ctgBuss" id="ctgBuss" value="" />
  <input type="button" class="style_botton" value="Save" onclick="saveCatCategory()" />
  <input type="button" value="Cancel"  class="style_botton" onclick="parent.$('#addCategoryDialog').dialog('close');parent.$('#addCategoryDialog').dialog('destory');" />
</div>
</form>
</body>
</html>