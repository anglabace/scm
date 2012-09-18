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
<script src="${global_js_url}scm/category_pdtServList.js" type="text/javascript"></script>
<script src="${global_js_url}table.js" type="text/javascript"></script>
</head>

<body>
<form id="mainForm" action="" method="get">
<table width="967" border="0" cellspacing="0" cellpadding="0">
<tr>
<td>
<div style="margin-right:17px;">
	<table width="950" border="0" cellspacing="0" cellpadding="0" class="list_table2">
    <tr>
    <th width="46">
    	<div align="left">
    		<input type="hidden" name="pageOfCategory.pageNo" id="pageOfCategoryNo" value="${pageOfCategory.pageNo}" />
	 		<input type="hidden" name="pageOfCategory.orderBy" id="orderBy"	value="${pageOfCategory.orderBy}" />
	 		<input type="hidden" name="pageOfCategory.order" id="order" value="${pageOfCategory.order}" />
        	<input name="checkbox" type="checkbox" onclick="cc(this)" />
        	<input type="hidden" id="categoryType" value="product>" />
            <img src="images/file_delete.gif" id="pdtServDelete" alt="Delete" width="16" height="16" border="0" />
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
    	<tr>
        <td width="46"><input type="checkbox" name="ids" value="${serviceId}" />&nbsp;</td>
        <td width="76"><a href="${ctx}/serv/serv!input.action?id=${serviceId}&operation_method=edit">${catalogNo}</a>&nbsp;</td>
        <td width="52">${type}&nbsp;</td>
       <td width="124">
		<s:if test="%{name != null && name.length()>18}">
<s:property value="name.substring(0,18)+'...'"/></s:if><s:else>${name}</s:else>&nbsp;
		&nbsp;</td>
        <td width="240"><s:if test="%{description != null && description.length()>35}">
<s:property value="description.substring(0,35)+'...'"/></s:if><s:else>${description}</s:else>&nbsp;</td>
        <td width="71">${status}&nbsp;</td>
        <td width="62">${size}&nbsp;</td>
        <td width="73">${symbol}${unitPrice}&nbsp;</td>
        <td width="81"><s:date name="modifyDate" format="yyyy-MM-dd" />&nbsp;</td>
        <td> <s:date name="creationDate" format="yyyy-MM-dd" />&nbsp;</td>
        </tr>
       </s:iterator>
     </table>
</div></td>
</tr>
<tr>
<td>
	<jsp:include page="/common/db_pager.jsp">
		<jsp:param value="${ctx}/serv/product_category!subCategoryList.action" name="moduleURL"/>
	</jsp:include>
</td>
</tr>
</table>
</form>
<div align="center">
<input name="catPdtServAddDialogTrigger" id="catPdtServAddDialogTrigger" type="button" class="style_botton" value="Add" />
</div>
</body>
</html>
