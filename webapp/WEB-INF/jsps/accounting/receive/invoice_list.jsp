<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Invoice List</title>
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
<script language="javascript" type="text/javascript" src="${global_js_url}tools.js"></script>
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
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
  function   cc(e)  
  {  
      var   a   =   document.getElementsByName("mm33");  
      for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;  
  }  
  
  
	$(document).ready(function() {
	  $("#filter_GTD_invoiceDate").attr("readonly","readonly");
	  $("#filter_LTD_invoiceDate").attr("readonly","readonly");
		$('#filter_GTD_invoiceDate').each(function() {
			$(this).datepicker( {
				dateFormat : 'yy-mm-dd',
				changeMonth : true,
				changeYear : true
			});
		});

		$('#filter_LTD_invoiceDate').each(function() {
			$(this).datepicker( {
				dateFormat : 'yy-mm-dd',
				changeMonth : true,
				changeYear : true
			});
		});
	});

	$(function() {
		$('#printDialog').dialog( {
			autoOpen : false,
			height : 550,
			width : 900,
			modal : true,
			bgiframe : true,
			buttons : {}
		});

		initSelect2();//初始化下拉选框 在tools.js中
	});

	function printDlg(invoiceIds) {
		window.open("ar_invoice!printInvoice.action?arInvoiceDto.invoiceIds="+invoiceIds,null,'height=550,width=900,status=yes,toolbar=no,menubar=no,location=no');
		return;
		$('#printDialog')
				.dialog(
						"option",
						"open",
						function() {
							var htmlStr = '<iframe src="ar_invoice!printInvoice.action?arInvoiceDto.invoiceIds='+invoiceIds+'" height="550" width="900" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
							$('#printDialog').html(htmlStr);
						});
		$('#printDialog').dialog('open');
	}

	function openDialog() {
		var invoiceIds = '';
		var a = document.getElementsByName("mm33");
		for (i = 0; i < a.length; i++) {
			if (a[i].checked) {
				var status = a[i].parentNode.parentNode.children[2].innerHTML;
				if (status != 'New' && status != 'In Progress') {
					alert(lang.deleteInvoice1);
					return;
				}
				invoiceIds += "," + a[i].value;
			}
		}
		if (invoiceIds.length > 0)
			invoiceIds = invoiceIds.substring(1);
		else {
			alert(lang.deleteInvoice2);
			return;
		}
		window
				.openiframe(
						'ar_invoice!delete.action?arInvoiceDto.invoiceIds=' + invoiceIds,
						'600', '300');
	}

	var invoice = new Object();
	function printInvoice() {
		var invoiceIds = '';
		var length = $("#list_table tr td :checked").length;
		if (length < 1) {
			alert("You did not have select one Invoice");
			return;
		} else {
			var arr = [ "invoiceId", "invoiceNo", "status", "customerNo",
					"orderNo", "shipmentNo", "InvoiceType", "invoiceDate",
					"amt", "paidAAmt", "balance", "currency" ];
			var a = getTableData("list_table", arr);
			var c = document.getElementsByName("mm33");
			for ( var i = 0; i < c.length; i++) {
				if (c[i].checked) {
					invoice = a[i];
					invoiceIds += "," + c[i].value;
				}
			}
			if(invoiceIds != '')invoiceIds = invoiceIds.substring(1);
			printDlg(invoiceIds);
			//window.openiframe('ar_invoice!print.action','760','310');
		}
	}

//检查查询条件，有些查询条件只能为数字
	function checkQueryCondition() {
	     
	    var invoiceNo = $("#invoiceNo").val();
	    if (invoiceNo != '' && !isInt(invoiceNo)) {
			alert("Invoice No must be integer");
			return false;
		}
		var orderNo = $("#orderNo").val();
		if (orderNo != '' && !isInt(orderNo)) {
			alert("Order No must be integer");
			return false;
		}
		var shipmentNo = $("#shipmentNo").val();
		if (shipmentNo != '' && !isInt(shipmentNo)) {
			alert("Shipment No must be integer");
			return false;
		}
		var customerNo = $("#customerNo").val();
		if (customerNo != '' && !isInt(customerNo)) {
			alert("Customer No must be integer");
			return false;
		}
	}
	
//表格隔行变色	
$(function(){
 $("#list_table tr:nth-child(even) td").each(function(){
    $(this).addClass("list_td2");
 })
});

	
	
</script>


</head>

<body class="content" style="background-image:none;">
<input name="Submit10" type="submit" class="style_botton" value="Update" onclick="newiframe('acl_new.html','800','600')" style="display:none;"/>
<div  id="frame12" title=" Delete Invoice" style="display:none;" class="hidlayer1">


<iframe id="hidkuan" name="hidkuan" src="" width="668" height="425" frameborder="0" allowTransparency="true"></iframe>

</div>


<div class="scm">
<div class="title_content">
  <div class="title"> Process Invoice</div></div>
<div class="search_box">
 <div  class="search_box_three">
 <form name="form1" method="get" action="ar_invoice!list.action">
   <table  border="0" cellpadding="0" cellspacing="0" class="General_table" id="queryForm">
     <tr>
       <th>Invoice No</th>
       <td width="110"><input name="filter_LIKES_invoiceNo" id="invoiceNo" type="text" class="NFText" size="20" value="${params.filter_LIKES_invoiceNo }"/></td>
       <th>Order No</th>
       <td width="110"><input name="filter_EQI_orderNo" type="text" id="orderNo" class="NFText" size="20" value="${params.filter_EQI_orderNo }" /></td>
       <th>Customer No</th>
       <td width="110"><input name="filter_EQI_custNo"  type="text" id="customerNo" class="NFText" size="20" value="${params.filter_EQI_custNo }"/></td>
       <th>Shipment No</th>
       <td width="110"><input name="filter_EQI_shipmentId" id="shipmentNo" type="text" class="NFText" size="20" value="${params.filter_EQI_shipmentId }" /></td>
     </tr>
     <tr>
       <th>Invoice Date From</th>
       <td><input name="filter_GTD_invoiceDate" id="filter_GTD_invoiceDate" type="text" value="${params.filter_GTD_invoiceDate }"  class="NFText" /></td>
       <th>To</th>
       <td><input name="filter_LTD_invoiceDate" id="filter_LTD_invoiceDate" type="text" value="${params.filter_LTD_invoiceDate }" class="NFText" /></td>
       <th>Status</th>
       <td><select name="filter_EQS_status" style="width:120px;*width:132px;" val="${ params.filter_EQS_status}" field="AR_INVOICE_STATUS" blank="true">
                   
       </select></td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
     </tr>
     <tr>
       <td colspan="8" align="center"> <input type="submit" name="Submit3" value="Search" onclick="return checkQueryCondition()" class="search_input" />
     <input type="button" name="Submit4" value="Refresh List" onclick="location.href='ar_invoice!input.action'" class="search_input" /></td>
       </tr>
   </table>
</form>
 </div>
</div>
        <div class="input_box">
		 <div class="content_box">
         <div >
		  <table width="1010" border="0" cellspacing="0" cellpadding="0">
		    <s:iterator value="#request.names" status="invoice">  </s:iterator>
  <tr>
    <td><div style="margin-right:17px;"><table width="993" border="0" cellspacing="0" cellpadding="0" class="list_table">
      <tr>
        <th width="47"><div align="left"><input name="checkbox2" type="checkbox"  onclick="cc(this)" />
        <a onclick="openDialog()" title="Delete Invoice" rel="gb_page_center[600,  180]"><img src="${ctx }/images/file_delete.gif" alt="Delete" width="14" height="14" border="0" /></a></div></th>
        <th width="94">Invoice No </th>
        <th width="94">Status </th>
        <th width="112">Customer No</th>
        <th width="82">Order No</th>
         <th width="97">Shipment No</th>
        <th width="97">Invoice Type </th>
        <th width="97">Invoice Date </th>
        <th width="97">Invoice Amt</th>
        <th width="112">Paid  Amt </th>
        <th width="112"> Balance</th>
        <th>Currency</th>
      </tr>


    </table></div></td>
  </tr>
  <tr>
    <td> <div class="list_box" style="height:340px; overflow:scroll;"><table width="993" border="0" cellspacing="0" cellpadding="0" id="list_table" class="list_table2">

<s:iterator value="arInvoicePage.result">
       <tr>
        <td width="41"><input type="checkbox" value="${invoiceId }" name="mm33"/></td>
        <td width="84"><a href="ar_invoice!edit.action?invoiceId=${invoiceId }&invoiceNo=${invoiceNo }&PaidAmount=${ paidAmt}&status=${status }">${invoiceId}</a></td>
        <td width="85">${status }</td>
        <td width="105">${custNo }</td>
        <td width="74">${orderNo }</td>
        <td width="94">${shipmentId }</td>
        <td width="84">${invoiceType }</td>
        <td width="87"><s:date name="invoiceDate" format="yyyy-MM-dd" /></td>
        <td width="88" align="right">${symbol}${totalAmount }</td>
        <td width="95" align="right">${symbol}${paidAmt }</td>
        <td width="101" align="right">${symbol}${balance }</td>
        <td width="54">${currency }</td>
      </tr>
</s:iterator>

    </table></div></td>
  </tr>
							<tr>
								<td>

									<div class="grayr">
										<jsp:include page="/common/db_pager.jsp">
											<jsp:param value="${ctx}/ar_invoice!list.action"
												name="moduleURL" />
										</jsp:include>
									</div>

								</td>
							</tr>
						</table>
		   

</div>

<div class="new_item">
<input type="button" name="Submit16" value="Create Invoice" class="search_input"  onclick="window.location.href='ar_invoice!add.action'"/><input type="button" name="Submit16" value="Print Invoice" class="search_input"  onclick="printInvoice()"/>
<input type="button" name="Submit" value="View Overdue Invoice" class="search_input3"  onclick="window.location.href='ar_invoice!viewOverDueInvoice.action'"/>

<input type="button" name="Submit193" value="View Uninvoiced Shipments" class="search_input3"  onclick="window.openiframe('search_uninvoiced.html','664','380')" disabled="disabled" style="display:none;"/>
</div>
		   </div>
  </div>	

<div id="printDialog" title=" Print Invoices "></div>


</body>
</html>
