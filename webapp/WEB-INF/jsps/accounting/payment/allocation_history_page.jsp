<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>

<% 
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="stylesheet/scm.css" rel="stylesheet" type="text/css" />
<link href="stylesheet/table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="js/ajax.js"></script>
<script language="javascript" type="text/javascript" src="js/tab-view.js"></script>
<link href="stylesheet/tab-view.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="js/TabbedPanels.js"></script>
<link href="stylesheet/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
        var GB_ROOT_DIR = "./greybox/";
    </script>

    <script type="text/javascript" src="greybox/AJS.js"></script>
    <script type="text/javascript" src="greybox/AJS_fx.js"></script>
    <script type="text/javascript" src="greybox/gb_scripts.js"></script>
    <link href="greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
<style type="text/css">
<!--
.content .scm #dhtmlgoodies_tabView1 .dhtmlgoodies_aTab .General_table fieldset {
	margin: 4px;
}
-->
</style>

<script>
function doClose(){
	window.parent.$('#new_allocation_dlg').dialog('close');
}
</script>

</head>

<body class="content">

<div class="scm">
<%--<div class="title_content">--%>
<%--  <div class="title">View Allocation History</div>--%>
<%--</div>--%>
<div class="input_box">
		  <div class="content_box">

		    <form id="form1" name="form1" class="niceform" method="post">
		      <div style="margin-right:17px;">
		        <table width="" height="42" border="0" cellpadding="0" cellspacing="0" class="list_table">
		          <tr>
		            <th width="81"><div align="left">
		              <input name="checkbox2" type="checkbox"  onclick="cc(this)" />
		            </div></th>
		            <th width="120">Date</th>
		            <th width="150">Amount</th>
		            <th width="150">Order No</th>
		            <th width="150">Invoice No</th>
		            <th width="100">Apply By</th>
	              </tr>
	            </table>
		        <table width="" border="0" cellspacing="0" cellpadding="0" class="list_table2">
	              <s:iterator value="arInvoiceAllocationPage.result">
				      <tr>
				        <td width="81"><input type="checkbox" value="checkbox" name="mm33"/></td>
			            <td width="120"><s:date name="creationDate" format="yyyy-MM-dd" /></td>
			            <td width="150">${applyAmount}</td>
			            <td width="150">${orderNo}</td>
			            <td width="150">${invoiceNo}</td>
			            <td width="100">${createdBy}</td>
				      </tr>
					</s:iterator>
	            </table>
	          </div>
		    </form>
		</div>
  </div>	



<div class="button_box" style="width:800px;">
<%--      <input type="submit" name="Submit123"  value="Save" class="search_input" style="display:none;" />--%>
      <input name="Submit2" type="button" class="search_input" value="Cancel" onclick="doClose()"/>
</div>
</div>	
</body>
</html>
