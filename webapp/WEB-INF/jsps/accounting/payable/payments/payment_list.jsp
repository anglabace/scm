<%@ page language="java" import="java.util.List,java.util.Map,java.util.HashMap,java.util.ArrayList" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Invoice Payment Transaction List</title>
    <script type="text/javascript">

var GB_ROOT_DIR = "./greybox/";

//-->
</script>

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
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<script type="text/javascript" src="${global_js_url}tools.js"></script>

<style type="text/css">
<!--
.hidlayer {
	font-size: 12px;
	height: 370px;
	width: 666px;
	position: absolute;
	z-index: 9999;
	left: 20%;
	top: 20%;
	display:none;
}
.hidlayer1 {
	font-size: 12px;
	position: absolute;
	z-index: 9999;
	left: 20%;
	top: 20%;
	display:none;
	height: 200px;
	width: 200px;
}
-->
</style>
<!--  
<script language="javascript" type="text/javascript" src="../js/newwindow.js"></script>
-->
<style>
table{
	border-collapse:collapse;
}
.new_table{
	border-top-width: 1px;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-left-width: 1px;
	border-top-style: solid;
	border-right-style: none;
	border-bottom-style: none;
	border-left-style: solid;
	border-top-color: #CCC;
	border-left-color: #CCC;
}
.new_table th{
	font-weight: bold;
	text-align: right;
	padding: 2px;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-top-color: #999;
	border-right-color: #999;
	border-bottom-color: #999;
	border-left-color: #999;
}
.new_table td{
	font-weight: bold;
	text-align: left;
	padding: 2px;
	border-top-width: 0px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 0px;
	border-top-style: none;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: none;
	border-right-color: #CCC;
	border-bottom-color: #CCC;
}
</style>
<script   language="JavaScript" type="text/javascript">  
  
	function cc(e) {
		var a = document.getElementsByName("mm33");
		for ( var i = 0; i < a.length; i++)
			a[i].checked = e.checked;
	}

	$(document).ready(function() {
		$("#filter_GTD_transactionDate").attr("readonly","readonly");
	    $("#filter_LTD_transactionDate").attr("readonly","readonly");
		$('#filter_GTD_transactionDate').each(function() {
			$(this).datepicker( {
				dateFormat : 'yy-mm-dd',
				changeMonth : true,
				changeYear : true
			});
		});

		$('#filter_LTD_transactionDate').each(function() {
			$(this).datepicker( {
				dateFormat : 'yy-mm-dd',
				changeMonth : true,
				changeYear : true
			});
		});

		$('#new_resource_dlg').dialog({
			autoOpen: false,
			height: 450,
			width:900,
			modal: true,
			bgiframe: true,
			buttons: {},
			title:'Record Batch Transactions'
		});	

		 initSelect2();
	});

	function openDialog() {
		var transactionIds = '';
		var a = document.getElementsByName("mm33");
		for (i = 0; i < a.length; i++) {
			if (a[i].checked) {
				if (a[i].parentNode.parentNode.children[3].innerHTML != 'Draft') {
					alert(lang.deleteTransaction1);
					return;
				}
				transactionIds += "," + a[i].value;
			}
		}
		if (transactionIds.length > 0)
			transactionIds = transactionIds.substring(1);
		else {
			alert(lang.deleteInvoice2);
			return;
		}
		window.openiframe('ap_invoice_payment!forwardDelete.action?apInvoicePaymentDto.transactionIds=' + transactionIds,'600', '300');
	}

	//转到新增、修改页面
	function forwardRecordPage(){
		$('body').load('ar_invoice_payment!forwardRecord.action');
		window.openiframe('ar_invoice_payment!forwardDelete.action?arInvoicePaymentDto.transactionIds=' + transactionIds,'600', '300');
	}

	//批量 导入
	function recordBatch(){
		$('#new_resource_dlg').dialog("option", "open", function() { 
        	var htmlStr = '<iframe id="paymentIframe" src="ap_invoice_payment!batchRecord.action" height="400" width="860" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
         	$('#new_resource_dlg').html(htmlStr);
		});
		$('#new_resource_dlg').dialog('open');
	}
</script>


</head>

<body class="content" style="background-image:none;">
<div id="frame12" style="display:none;" class="hidlayer1">
<iframe id="hidkuan" name="hidkuan" src="" width="668" height="425" frameborder="0" allowTransparency="true"></iframe>
</div>
<div class="scm">
<div class="title_content">
  <div class="title"> Process Payment </div>
</div>
<div class="search_box" >
  <div class="search_box_three">
  <form name="form1" id="form1" method="get" action="ap_invoice_payment!list.action">
    <table  border="0" cellpadding="0" cellspacing="0" class="General_table">
      <tr>
    <th>Transaction No</th>
    <td><input name="filter_LIKES_transactionNo" type="text" class="NFText" size="20" value="${params.filter_LIKES_transactionNo}" /></td>
    <th>Supplier</th>
    <td><input name="filter_EQI_wendorNo" type="text" class="NFText" size="20" value="${params.filter_EQI_wendorNo}" /></td>
    <th>Purchase Order No</th>
    <td><input name="filter_EQI_orderNo" type="text" class="NFText" size="20" value="${params.filter_EQI_orderNo}" /></td>
    </tr>
  <tr>
    <th valign="top">Transaction Date From </th>
    <td><input name="filter_GTD_transactionDate" id="filter_GTD_transactionDate" type="text" class="NFText" size="20" value="${params.filter_GTD_transactionDate}" /></td>
    <th valign="top">To</th>
    <td><input name="filter_LTD_transactionDate" id="filter_LTD_transactionDate" type="text" class="NFText" size="20" value="${params.filter_LTD_transactionDate}" /></td>
    <th>Status</th>
    <td>
    
    <select name="filter_EQS_status" id="status" val="${param.filter_EQS_status}" style="width:120px;*width:132px;" blank="true" field="AR_TRANSACTION_STATUS">
		<option value=""></option>
	</select>
    </td>
    </tr>
  <tr>
    <td colspan="6" align="center">
    	<input type="submit" name="Submit1" value="Search" class="search_input" />
     	<input type="button" name="Submit2" value="Refresh List" onclick="location.href='ap_invoice_payment!input.action'" class="search_input" />
    </td>
    </tr>
 </table>
 </form>

</div>
</div>
        <div class="input_box">
		  <div class="content_box">
          <div >
            <table width="1010" border="0" cellspacing="0" cellpadding="0">
            
              <tr>
                <td><div class="list_box" style="width:993px;height:340px; overflow:scroll;">
                  <table width="1299" border="0" cellspacing="0" cellpadding="0" class="list_table">
                     <tr>
                      <th width="46"><div align="left">
                        <input name="checkbox2" type="checkbox"  onclick="cc(this)" />
                        <a onclick="openDialog()"  title="Delete Transaction" rel="gb_page_center[600,  180]"><img src="images/file_delete.gif" alt="Delete" width="16" height="16" border="0" /></a></div></th>
                      <th width="123">Transaction No</th>
                      <th width="88">Supplier</th>
                      <th width="77">Status</th>
                      <th width="147">Transaction Type</th>
                      <th width="99">Amount</th>
                      <th width="113">Applied Amount</th>
                      <th width="62">Currency</th>
                      <th width="107">Payment Type</th>
                      <th width="114">Description</th>
                      <th width="76">PO No</th>
                      <th width="121">Invoice No</th>
                      <th width="126">Transaction Date</th>
                    </tr>
                    <s:iterator value="apInvoicePaymentPage.result" status="stuts">
						<s:if test="#stuts.odd == true">
						<tr>
					    	<td><input type="checkbox" value="${transactionId}" name="mm33"/></td>
					        <td><a href="ap_invoice_payment!forwardRecord.action?apInvoicePaymentDto.transactionId=${transactionId}">${transactionId}</a></td>
					        <td>${vendorNo}</td>
					        <td>${status}</td>
					        <td>${transactionType}</td>
					        <td>${symbol}${amount}</td>
					        <td>${symbol}${applyAmount}</td>
					        <td>${currency}</td>
					        <td>${paymentType}</td>
					        <td>${description}</td>
					        <td>${orderNo}</td>
					        <td>${invoiceNo}</td>
					        <td><s:date name="transactionDate" format="yyyy-MM-dd" /></td>
						</tr>
						</s:if>
						<s:else>
						<tr>
					    	<td class="list_td2"><input type="checkbox" value="${transactionId}" name="mm33"/></td>
					        <td class="list_td2"><a href="ap_invoice_payment!forwardRecord.action?apInvoicePaymentDto.transactionId=${transactionId}">${transactionId}</a></td>
					        <td class="list_td2">${vendorNo}</td>
					        <td class="list_td2">${status}</td>
					        <td class="list_td2">${transactionType}</td>
					        <td class="list_td2">${symbol}${amount}</td>
					        <td class="list_td2">${symbol}${applyAmount}</td>
					        <td class="list_td2">${currency}</td>
					        <td class="list_td2">${paymentType}</td>
					        <td class="list_td2">${description}</td>
					        <td class="list_td2">${orderNo}</td>
					        <td class="list_td2">${invoiceNo}</td>
					        <td class="list_td2"><s:date name="transactionDate" format="yyyy-MM-dd" /></td>
						</tr>
						</s:else>
					</s:iterator>
                  </table>
                </div></td>
              </tr>
              <tr>
                <td>
                	<div class="grayr">
						<jsp:include page="/common/db_pager.jsp">
							<jsp:param value="${ctx}/ap_invoice_payment!list.action"
								name="moduleURL" />
						</jsp:include>
					</div>
                </td>
              </tr>
            </table>
          </div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
		    <td>
		    <form id="form2" name="form2" action="ap_invoice_payment!forwardRecord.action" method="post">
		    <div class="button_box">
		      <input name="Submit22" type="button" class="search_input2" onclick="window.location.href='ap_invoice_payment!forwardRecord.action?transactionId=-1'" value="Record Transaction" />
		      <input name="Submit2" type="button" class="search_input3" value="Record Batch Transactions" onclick="recordBatch()"/>
		    </div>
		    </form>
		    </td>
		  </tr>
		</table>
		</div>
  </div>	
</div>
<div id="new_resource_dlg" name="new_resource_dlg"></div>	
</body>
</html>

