<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>

<% 
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<script type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script type="text/javascript" src="${global_js_url}jquery/ui/ui.datepicker.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}lang/lang.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tools.js"></script>
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<style type="text/css">
<!--
.content .scm #dhtmlgoodies_tabView1 .dhtmlgoodies_aTab .General_table fieldset {
	margin: 4px;
}
-->
</style>

<script>
function doCancel(){
  window.parent.$("#viewHistory").dialog('close');
}

function cc(obj){
    if(obj.checked){
       $("input[type=checkbox]").each(function(){
           $(this).attr("checked","checked");
       });
    }else{
       $("input[type=checkbox]").each(function(){
          $(this).removeAttr("checked");
       });
    }
}

$(function(){
 $("#list_table tr:nth-child(even)").addClass("list_td2");
});

</script>

</head>

<body class="content">

<div class="scm">
<div class="title_content">
  <div class="title">View Allocation History</div>
</div>
<div class="input_box">
		  <div class="content_box">
		    <form id="form1" name="form1" class="niceform" method="post">
		      <div style="height:350px;">
		        <table width="580" height="42" border="0" cellpadding="0" cellspacing="0" class="list_table">
		          <tr>
		            <th width="30"><div align="left">
		              <input name="checkbox2" type="checkbox"  onclick="cc(this)" />
		            </div></th>
		            <th width="110">Date</th>
		            <th width="110">Amount</th>
		            <th width="110">Transcation No</th>
		            <th width="110">Invoice No</th>
		            <th width="110">Created By</th>
	              </tr>
	            </table>
		        <table width="580" border="0" cellspacing="0" cellpadding="0" id="list_table" class="list_table2">
	              <s:iterator value="arInvoiceAllocationList">
				      <tr>
				        <td width="30"><input type="checkbox" value="${id }" name="mm33" /></td>
			            <td width="110"><s:date name="creation_date" format="yyyy-MM-dd" /></td>
			            <td width="110">${apply_amount}</td>
			            <td width="110">${transaction_id}</td>
			            <td width="110">${invoice_id}</td>
			            <td width="110">${created_by}</td>
				      </tr>
					</s:iterator>
	            </table>
	          </div>
		    </form>
		</div>
  </div>	



<div class="button_box">
      <input type="submit" name="Submit123"  value="Save" class="search_input" style="display:none;" />
      <input name="Submit2" type="submit" class="search_input" value=" Close " style="display:none;"  onclick="doCancel()"/>
</div>
</div>	
</body>
</html>
