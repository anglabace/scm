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

<script language="javascript" type="text/javascript">
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
$(function(){
	parent.$('#addPrCategoryDialog').dialog({
		autoOpen: false,
		height: 320,
		width: 720,
		modal: true,
		bgiframe: true,
		buttons: {}
	});
	$("#addPrCategoryDialogTrigger").click(function(){
		parent.$('#addPrCategoryDialog').dialog("option", "open",function(){
			var htmlStr = '<iframe id="catSubCatAdd_iframe" src="${ctx}/product/product_category!catSubCatAdd.action?categoryLevel=${categoryLevel}&sessionCategoryId=${sessionCategoryId}&categoryId=${categoryId}&parentCategoryNo=${parentCategoryNo}" height="260" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			parent.$('#addPrCategoryDialog').html(htmlStr);
		});							  
		parent.$('#addPrCategoryDialog').dialog('open');
	});
});
<!-- {if $sessionHave==1 } -->
var sessionHave = 1;
<!-- {else} -->
var sessionHave = 0;
<!-- {/if} -->
function categoryHrefClick(url){
	if(sessionHave){
		
	}else{
		parent.window.location.href = url;
	}
}
var delSubCategoryId=0;
function delPdtSubCategory(name){
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
		url:"product/product_category!delSubProductCategory.action?sessionCategoryId=${sessionCategoryId}&delSubProductCategory="+del_category_nos,
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
<form method="post" id="mainForm" action="">
<table width="967" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td>
      <div style="margin-right:17px;">
      <table width="950" border="0" cellspacing="0" cellpadding="0" class="list_table2">
          <tr>
          <th width="46">
            <div align="left">
                <input type="checkbox"  onclick="cc(this)" />
                
                <img src="images/file_delete.gif" alt="Delete" width="16" height="16" border="0" onclick="delPdtSubCategory('ctgIds');" />
            </div>
          </th>
          <th width="100">
				<s:if test="categoryLevel==2">
					Category No
				</s:if>
				<s:if test="categoryLevel==3">
					Product Line No
				</s:if>
		  </th>
          <th width="180">Name</th>
          <th width="200">Description</th>
          <th width="70">Status</th>
          <th width="150">Asset Group </th>
          <th width="80">Modify Date </th>
          <th>Creation Date </th>
          </tr>
      </table>
      </div>
      </td>
    </tr>
    <tr>
      <td>
      	<div class="list_box" style="height:180px;">
        <table width="950" border="0" cellspacing="0" cellpadding="0" class="list_table2" id="pdCategoryTb" style="table-layout:fixed;word-break:break-all">
        	<s:iterator value="page.result">
        	<tr id='del_${categoryId}'>
            <td width="46"><input type="checkbox" value="${categoryId}" name="ctgIds"/></td>
            <td width="100" style='word-break:break-all;word-wrap:break-word;width:100'>
            	<a href="javascript://" onclick="categoryHrefClick('${ctx }/product/product_category!input.action?categoryId=${categoryId}&callBackName=categoryCreationForm&operation_method=edit&dodo=first');">
            	${categoryNo}
            	</a>
            	<input type="hidden" name="categoryNo_${categoryId}" value="${categoryNo}" /></td>
            <td width="180" style='word-break:break-all;word-wrap:break-word;width:180'>
           ${name}
            </td>
            <td width="200" style='word-break:break-all;word-wrap:break-word;width:200'>${description}&nbsp;</td>
            <td width="70"><div align="center">${status}</div></td>
            <td width="150">${assetGroup}</td>
            <td width="80"><div align="center"><s:date name="modifyDate" format="yyyy-MM-dd" />&nbsp;</div></td>
            <td><div align="center"><s:date name="creationDate" format="yyyy-MM-dd" />&nbsp;</div></td>
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
			  <jsp:param value="${ctx}/product/product_category!subCategoryList.action" name="moduleURL"/>
			</jsp:include>
		</div>
	  </td>
    </tr>
</table>
</form>
<div align="center">
    <input name="addPrCategoryDialogTrigger" id="addPrCategoryDialogTrigger" type="button" class="style_botton" value="Add" />
</div>
</body>
</html>