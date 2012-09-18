<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<META HTTP-EQUIV="Pragma" CONTENT="no-cache"/>
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
<META HTTP-EQUIV="Expires" CONTENT="0" />

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
<script>

$(document).ready(function() {
           $("#filter_GTD_invoiceDate").attr("readonly","readonly");
           $("#filter_LTD_invoiceDate").attr("readonly","readonly");
			$("#filter_GTD_invoiceDate").datepicker( {
				dateFormat : 'yy-mm-dd',
				changeMonth : true,
				changeYear : true
			});
			$("#filter_LTD_invoiceDate").datepicker( {
				dateFormat : 'yy-mm-dd',
				changeMonth : true,
				changeYear : true
			});	
		getInvoices();
				
	});


$(function() {
 $('#new_resource_dlg').dialog({
		autoOpen: false,
		height: 400,
		width: 660,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
});

function resetValue()
{
   var PaymentApply='${arInvoicePayment.balance}';
   var applyAmount='${arInvoicePayment.applyAmount}';
   $("#PaymentApply").val(PaymentApply);
   $("#applyAmount").val(applyAmount);
   $("#invoice_id").val("");  
   $("#BadDebt").val("");
   $("#Overpayment").val("");
   $("#apply_amount").val(""); 
   $("#Balance").val("");
   $("#apply").removeAttr("disabled");
}

function responseHandle( res )
{
	$("#invoicePanel").html(res);	
}

function errorHandle()
{
    alert("response error!");
}

function getInvoices()
{
  var orderNo=$("#orderNo").val();
  var dateFrom=$("#filter_GTD_invoiceDate").val();
  var dateTo=$("#filter_LTD_invoiceDate").val();
  var invoiceNo=$("#invoiceNo").val();
  
   
  if((orderNo!="")&&isNaN(orderNo))
  {
     alert("Order No must be a number!");
     return;
  }
 
  
  if((invoiceNo!="")&&isNaN(invoiceNo))
  {
     alert("Invoice No must be a number!");
     return;
  }
  
  
  paramter="orderNo="+$.trim(orderNo)+"&"
          +"dateFrom="+$.trim(dateFrom)+"&"
          +"dateTo="+$.trim(dateTo)+"&"
          +"invoiceNo="+$.trim(invoiceNo)+"&"
          +"customerNo=${arInvoicePayment.custNo}"+"&"
          +"status=<%=com.genscript.gsscm.accounting.util.Constant.STATUS_INPROCESS%>"+"&"
          +"currency=${arInvoicePayment.currency}"+"&"
          +"transaction_id=${arInvoicePayment.transactionId}";
           
  $.ajax({
	      type:'get',
	      url :'ar_invoice_allocation!getInvoices.action',
	      data:paramter,
	      success:responseHandle,
	      error:errorHandle
        }); 
}

function applyPaymentAmount()
{
   var invoice=0;
   
   $(":radio").each(
	   function()
	   {
	     if(this.checked)
	     invoice=this.value;
	   }
   
   );
  
  if(0==invoice)
  {
  	alert("please choose one invoice to operation !");
  	return;
  } 
  
   $('#new_resource_dlg').dialog("option", "open", function() { 
        	var htmlStr = '<iframe id="iframe1" src="ar_invoice_allocation!applyPaymentAmount.action?invoiceNo='+invoice+'" height=340" width="560" scrolling="no" style="border:0px" frameborder="0"></iframe>';
         	$('#new_resource_dlg').html(htmlStr);
		});
		$('#new_resource_dlg').dialog('open');
 // window.openiframe('ar_invoice_allocation!applyPaymentAmount.action?invoiceNo='+invoice,'632','464')
  
}


function check()
{
    var applyAmount=$.trim( $("#apply_amount").val() );
	if( (""==applyAmount)||("0"==applyAmount) )
	{
	   alert("Please reconciliation before saving!");
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

<body class="content">

<div class="scm">
<form   class="niceform" action="ar_invoice_allocation!afterReconcile.action"   onsubmit="return check()"   >
<div class="title_content">
  <div class="title">Payment Allocation</div>
</div>
<div class="input_box">
		  <div class="content_box">

		    
		      <table width="100%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		          <td>
                
              <div class="invoice_title" >&nbsp;Payment</div>

              <table width="993" border="0" cellpadding="0" cellspacing="0"  class="list_table">
                <tr>
                  <th width="105">Transaction No</th>
                  <th width="98">Customer</th>
                  <th width="163">Transaction Type</th>
                  <th width="86">Amount</th>
                  <th width="145">Transaction Fee</th>
                  <th width="96">Applied Amount</th>
                  <th width="84">Currency</th>
                  <th width="105">Payment Type</th>
                  <th width="110">Transaction Date</th>
                </tr>
                <tr>
                  <td>${arInvoicePayment.transactionNo}</td>
                  <td>${arInvoicePayment.custNo}</td>
                  <td>${arInvoicePayment.transactionType}</td>
                  <td align="right">${arInvoicePayment.symbol}${arInvoicePayment.amount}</td>
                  <td align="right">${arInvoicePayment.transactionFee}</td>
                  <td align="right">${arInvoicePayment.symbol}${arInvoicePayment.applyAmount == null ?0.0: arInvoicePayment.applyAmount}</td>
                  <td>${arInvoicePayment.currency}</td>
                  <td>${arInvoicePayment.paymentType}</td>
                  <td><s:date name="arInvoicePayment.transactionDate" format="yyyy-MM-dd" /></td>
                </tr>
              </table></td>
	            </tr>
                <tr>
                <td>&nbsp;
                
                </td>
                </tr>
                <tr>
		          <td>
                
              <div class="invoice_title" >&nbsp;Invoice</div>
                  <table  border="0" cellpadding="0" cellspacing="0" class="General_table">
                    <tr>
                      <th>Order No</th>
                      <td><input name="orderNo" id="orderNo" type="text" value=""  class="NFText" size="20" /></td>
                      <td>Invoice Date From</td>
                      <td><input name="filter_GTD_invoiceDate" id="filter_GTD_invoiceDate" type="text" value=""   class="NFText"/></td>
                      <td>To</td>
                      <td><input name="filter_LTD_invoiceDate" id="filter_LTD_invoiceDate" type="text" value="" class="NFText" /></td>
                      <th>Invoice No</th>
                      <td><input name="invoiceNo" id="invoiceNo" type="text" value=""   class="NFText" size="20" /></td>
                      <td><input type="button" name="Submit6" class="style_botton" value="Search" onclick="getInvoices()" /></td>
                    </tr>
                    
                  </table>
             <div id="frame12" style="display:none;position:absolute;z-index:10;" class="hidlayer1">
               <iframe id="hidkuan" name="hidkuan" src="" width="668" height="425" frameborder="0"  allowtransparency="true"></iframe>
             </div>
             
                  <div style="width:993px;height:150px;overflow:auto;">
                 <table width="1293" id="invoicePanel"   border="0" cellpadding="0" cellspacing="0" class="list_table">
                <tr>
                  <th  align="center" width="30"><input type="radio"  name="Invoice_NO"   id="Invoice_NO" value="0" /></th>
                  <th width="71">Invoice No</th>
                  <th width="79">Customer</th>
                  <th width="70">Order No</th>
                  <th width="101">Invoice Amount</th>
                  <th width="92">Paid Amount</th>
                  <th width="102">Discount Amount</th>
                  
                  <th width="107">Bad Debt</th>
                  <th width="56">Balance</th>
                  <th width="70">Currency</th>
                </tr>
                    
              </table>
              
                <tr>
                  <td>
            
                  <table  border="0" cellpadding="0" cellspacing="0" class="General_table">
                    <tr>
                      <th>Amount Left Apply</th>
                      <td><input name="PaymentApply" id="PaymentApply" type="text" class="NFText2" value="${arInvoicePayment.balance}" size="20" readonly />${arInvoicePayment.symbol}</td>
                      <th>Total Amount Applied</th>
                      <td><input name="applyAmount" type="text" id="applyAmount" class="NFText2" value="${arInvoicePayment.applyAmount}" size="20"  readonly  />${arInvoicePayment.symbol}</td>
                      <th><input type="button" name="Submit"  id="apply"  onclick="applyPaymentAmount()"  class="style_botton2" value="Apply Amount" /></th>
                    </tr>
                  </table></td>
                </tr>
	          </table>
		  
		</div>
  </div>	
<input type="hidden" name="arTransactionAllocation.transaction_id"    value="${arInvoicePayment.transactionId}"  />
<input type="hidden" name="arTransactionAllocation.invoice_id"  id="invoice_id"  value=""  />
<input type="hidden" name="arTransactionAllocation.apply_amount"  id="apply_amount"  value=""  />
<input type="hidden" name="InvoiceBalance"  id="Balance"  value=""  />
<input type="hidden" name="BadDebt"  id="BadDebt"  value=""  />
<input type="hidden" name="Overpayment"  id="Overpayment"  value=""  />

<div class="button_box">
      <input type="submit" name="Submit123"  value="Save" class="search_input" id="save1"/>
      <input type="button" name="Submit124" value="Cancel" class="search_input" onclick="window.location.href='ar_invoice_allocation!GetList.action'" id="cancel1"/>
</div>
</form>
</div>	


<div id="new_resource_dlg" title=" Apply Payment Amount "></div>

</body>
</html>
