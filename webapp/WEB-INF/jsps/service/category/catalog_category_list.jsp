<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="${global_url}" />
<link href="${global_css_url }scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url }table.css" rel="stylesheet" type="text/css" />

<link href="${global_css_url }SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url }tab-view.css" rel="stylesheet" type="text/css" />
<script src="${global_js_url}table.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url }jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url }ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url }tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url }TabbedPanels.js"></script>

<link type="text/css" href="${global_js_url }jquery/themes/base/ui.all.css" rel="stylesheet" />

<script src="${global_js_url }jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script   language="JavaScript" type="text/javascript">
var baseUrl = "${ctx}";
function   cc(e)  
{  
	var   a   =   document.getElementsByName("ctgIds");  
	for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;  
}  
function   dd(e)  
{  
	var   a   =   document.getElementsByName("as");  
	for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;  
}
$(document).ready(function(){  
    $('tr:even >td').addClass('list_td2');  
});
$(function(){
	parent.$('#addCategoryDialog').dialog({
		autoOpen: false,
		height: 320,
		width: 720,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
		}
	});	
	$("#addCategoryDialogTrigger").click(function(){
		parent.$('#addCategoryDialog').dialog("option", "open",function(){
			var htmlStr = '<iframe id="catalog_service_Category_iframe" src="${ctx}/serv/service_category!ctlgCatAdd.action?sessionCatalogId=${sessionCatalogId}&cataId=${param['filter_EQS_catalogId']}" height="260" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			parent.$('#addCategoryDialog').html(htmlStr);
		});									  
		parent.$('#addCategoryDialog').dialog('open');
	});
});
<!-- {if $sessionHave==1 } -->
var sessionHave = 1;
<!-- {else} -->
var sessionHave = 0;
<!-- {/if} -->
function categoryHrefClick(url){
	if(sessionHave){
		//alert("Please save category first.");
	}else{
		parent.window.location.href = url;
	}
}
function delPdtCategory(name){
	var del_category_nos = get_checked_str(name);
	if(del_category_nos == '')
	{
		alert('Please select one item to continue your operation.');
		return;
	}
	if(!confirm('Are you sure to delete?'))
	{
		return;
	}
	$.ajax({
		type:"POST",
		url:"serv/service_category!delServiceCategoryToCatalog.action?sessionCatalogId=${sessionCatalogId}&delServiceCategory="+del_category_nos,
		success: function(msg){
			if(msg=="success"){
				var del_category = del_category_nos.split(",");
				for(var i=0;i<del_category.length;i++){
					$("#del_"+del_category[i]).remove();
				}
			}else{
				alert("Failed to remove the item. Please contact system administrator for help.");	
			}
		},
		error: function(msg){
			alert("Failed to remove the item. Please contact system administrator for help.");
		}
	});
}
function get_checked_str(name)
{
	var a = document.getElementsByName(name);
	var str = '';
	for   (var   i=0;   i<a.length;   i++)
	{
		if(a[i].checked)
		{
			str += a[i].value+',';
		}
	}
	return str.substring(0,str.length-1);
}
</script>
</head>

<body>
<form method="post" id="listForm" action="">
<table width="967" border="0" cellspacing="0" cellpadding="0">
<tr>
<td>
<div style="margin-right:17px;">
 <table width="950" border="0" cellspacing="0" cellpadding="0" class="list_table2">
    <tr>
     <th width="46"><div align="left">
          <input name="checkbox" type="checkbox"  onclick="cc(this)" />
          <img src="${global_image_url}file_delete.gif" alt="Delete" width="16" height="16" border="0" onclick="delPdtCategory('ctgIds');" /></div></th>
      <th width="125">Business Unit No</th>
      <th width="106">Name</th>
      <th width="148">Description</th>
      <th width="81">Status</th>
      <th width="119">Asset Group </th>
      <th width="113">Business Division</th>
      <th width="89">Modify Date </th>
      <th>Creation Date
      </th>
    </tr>
  </table>
</div>
</td>
</tr>
<tr>
<td>
<div class="list_box" style="height:180px;">
  <table width="950" border="0" cellspacing="0" cellpadding="0" class="list_table2" id="servCategoryTb" style="table-layout:fixed;word-break:break-all">
   <s:iterator value="page.result">
    <tr id="del_${categoryId }">
      <td width="46">&nbsp;<input type="checkbox" value="${categoryId}" name="ctgIds"/></td>
      <td width="125" style='word-break:break-all;word-wrap:break-word;width:125'>&nbsp;
        <a href="javascript://" onclick="categoryHrefClick('${ctx }/serv/service_category!input.action?categoryId=${categoryId}&callBackName=categoryCreationForm&operation_method=edit&dodo=first');">
      	 ${categoryNo}&nbsp;
         </a>
      	<input type="hidden" name="categoryNo_${categoryId}" value="${categoryNo}" />
      </td>
      <td width="106" style='word-break:break-all;word-wrap:break-word;width:106'>&nbsp;${name}
      </td>
      <td width="148" style='word-break:break-all;word-wrap:break-word;width:148'>&nbsp;
	   ${description}      
      </td>
      <td width="81">&nbsp;${status}</td>
      <td width="119">&nbsp;${assetGroup}</td>
      <td width="113">${businessDivision}&nbsp;</td>
      <td width="89">&nbsp;<s:date name="modifyDate" format="yyyy-MM-dd" /></td>
      <td>&nbsp;<s:date name="creationDate" format="yyyy-MM-dd" /></td>
    </tr>
   </s:iterator>
  </table>
</div>
</td>
</tr>
<tr>
<td>
<div class="grayr">
	<jsp:include page="/common/db_pager.jsp">
		<jsp:param value="${ctx}/serv/service_category.action" name="moduleURL"/>
	</jsp:include>
</div>
</td>
</tr>
</table>
 </form> 
<div align="center">
	<input name="addCategoryDialogTrigger" id="addCategoryDialogTrigger" type="button" class="style_botton" value="Add" />
</div>
</body>
</html>
