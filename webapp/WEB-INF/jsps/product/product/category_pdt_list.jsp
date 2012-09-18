<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />

<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>

<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script src="${global_js_url}table.js" type="text/javascript"></script>
<script language="JavaScript" type="text/javascript">
function   cc(e)  
{  
	var   a   =   document.getElementsByName("ids");  
	for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;  
}  
function   dd(e)  
{  
	var   a   =   document.getElementsByName("as");  
	for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;  
}
$(function(){
	parent.$('#catPdtServAddDialog').dialog({
		autoOpen: false,
    	height: 400,
    	width: 720,
    	modal: true,
    	bgiframe: true,
    	buttons: {}
	});
	$("#catPdtServAddDialogTrigger").click(function(){
		var categoryId = parent.$("#ctgId").val();
    	var type = parent.$("#categoryType").val();
		parent.$('#catPdtServAddDialog').dialog("option", "open",function(){
			var htmlStr = '<iframe src="product/product!catPdtAddAct.action?sessionCategoryId=${sessionCategoryId}&categoryId='+categoryId+'&type='+type+'&catalogId='+parent.$("#cataId").val()+'" height="350" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			parent.$('#catPdtServAddDialog').html(htmlStr);
		});						  
		parent.$('#catPdtServAddDialog').dialog('open');
	});
});
var delpdtPriceId=0;
function delPdtPrice(name){
	var del_price_nos = get_checked_str(name);
	if(del_price_nos == '')
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
		url:"product/product!delProductToCategory.action?sessionCategoryId=${sessionCategoryId}&delProduct="+del_price_nos,
		success: function(msg){
			if(msg=="success"){
				var del_price = del_price_nos.split(",");
				for(var i=0;i<del_price.length;i++){
					$("#del_"+del_price[i]).remove();
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
<form id="mainForm" action="" method="get">
<table width="967" border="0" cellspacing="0" cellpadding="0">
<tr>
<td><div style="margin-right:17px;">
<form action="${ctx }/product/product!productOfPdtCategoryList.action" method="post">
	 <table width="967" border="0" cellpadding="0" cellspacing="0" height="10" class="General_table">
    	
    	Product Catalog No<input name="catalogNo" id="catalogNo" type="text" class="NFText" value="${catalogNo }" size="25" />
    	Name<input name="name" type="text" id="name" class="NFText" value="${name}" size="25" />  
    	<input name="categoryId" id="categoryId" value="${categoryId }" type="hidden"/>
    	<input name="catalogId" id="catalogId" value="${catalogId }" type="hidden"/>
    	<input name="sessionCategoryId" id="sessionCategoryId" value="${sessionCategoryId }" type="hidden"/>
    	
     	<input name="search" type="submit" id='search' class="style_botton" value="Search" />
    
     </table>
</form>	
	<table width="950" border="0" cellspacing="0" cellpadding="0" class="list_table2">
    <tr>
    <th width="46">
    	<div align="left">
    		<input name="checkbox" type="checkbox" onclick="cc(this)" />
            <img src="images/file_delete.gif" id="pdtServDelete" alt="Delete" width="16" height="16" border="0" onclick="delPdtPrice('ids');"/>
      	</div>
    </th>
    <th width="76">Catalog No</th>
    <th width="52">Type</th>
    <th width="124">Name</th>
    <th width="240">Description</th>
    <th width="71">Status</th>
    <th width="62">Size</th>
    <th width="73">Unit Price</th>
    <th width="81">Modify Date</th>
    <th>Creation Date</th>
    </tr>
    </table>
</div></td>
</tr>
<tr>
<td><div class="list_box" style="height:180px;">
	<table width="950" border="0" cellspacing="0" cellpadding="0" class="list_table2" id="catPdtServList">
		<s:iterator value="pageOfCategory.result">
    	<tr id="del_${productId}">
        <td width="46"><input type="checkbox" name="ids" value="${productId}" />&nbsp;</td>
        <td width="76" style='word-break:break-all;word-wrap:break-word;width:76'>
	        <a href="${ctx}/product/product!input.action?id=${productId}&operation_method=edit" target="_parent">
	        	${catalogNo}
	        </a>&nbsp;
        </td>
        <td width="52">${type}&nbsp;</td>
        <td width="124" style='word-break:break-all;word-wrap:break-word;width:124'>
			${name}&nbsp;
		</td>
        <td width="240" style='word-break:break-all;word-wrap:break-word;width:240'>${description}&nbsp;</td>
        <td width="71">${status}&nbsp;</td>
        <td width="62">${size}&nbsp;</td>
        <td width="73">${symbolPrice}${unitPrice}&nbsp;</td>
        <td width="81"><s:date name="modifyDate" format="yyyy-MM-dd" />&nbsp;</td>
        <td> <s:date name="creationDate" format="yyyy-MM-dd" />&nbsp;</td>
        </tr>
       </s:iterator>
     </table>
</div></td>
</tr>
<tr>
<td><div class="grayr">
			<jsp:include page="/common/db_pager.jsp">
			  <jsp:param value="${ctx }/product/product!productOfPdtCategoryList.action" name="moduleURL"/>
			</jsp:include>
	</div></td>
</tr>
</table>
</form>
<div align="center">
<input name="catPdtServAddDialogTrigger" id="catPdtServAddDialogTrigger" type="button" class="style_botton" value="Add" />
</div>
</body>
</html>
