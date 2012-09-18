<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}table.js" type="text/javascript"></script>
<script language="JavaScript" type="text/javascript">
function chooseCatalogNo(cId) {
	parent.$("#promotion_edit_iframe").contents().find("#orderCatalogNo").val(cId);
	parent.$("#promotion_add_iframe").contents().find("#orderCatalogNo").val(cId);
	parent.$('#categoryCatalogDialog').dialog('close');
	parent.$('#categoryCatalogDialog').dialog('destory');
}
$(document).ready(function(){  
    $('tr:even >td').addClass('list_td2');
	
});
 </script>
</head>

<body>
	<b>Choose Catalog: Catalog List</b><br />
	<form id="mainForm" action="" method="get">
	<table width="300" border="0" cellpadding="0" cellspacing="0" class="list_table">
          <tr>
            <th>Catalog No</th>
            </tr>
        </table>
	
    <table width="300" border="0" cellspacing="0" cellpadding="0" class="list_table2">
    <s:iterator value="serviceInCategoryList">
    <tr>
    <td width="300">
        <a href="javascript:chooseCatalogNo('${catalogNo}')">${catalogNo}&nbsp;</a>
    </td>   
    </tr>
   </s:iterator>
    </table>
    </form>
    <div class="grayr">
			<jsp:include page="/common/db_pager.jsp">
			  <jsp:param value="${ctx}/basedata/category_picker!getServiceCatalogNoList.action" name="moduleURL"/>
			</jsp:include>
		</div>
	<script type="text/javascript">
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
	</script>
</body>
</html>