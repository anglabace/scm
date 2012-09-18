<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp"%>
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


<script   language="JavaScript" type="text/javascript">  
  function   cc(e)  
  {  
      var   a   =   document.getElementsByName("pa");  
      for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;  
  } 
  
  $(document).ready(function() {
			$("#dateFrom").attr("readonly","readonly");
	        $("#dateTo").attr("readonly","readonly");
			$("#dateFrom").datepicker( {
				dateFormat : 'yy-mm-dd',
				changeMonth : true,
				changeYear : true
			});
			
			$("#dateTo").datepicker( {
				dateFormat : 'yy-mm-dd',
				changeMonth : true,
				changeYear : true
			});
	
	});
	
function allocation()
{
   var payment=0;
   
   $(":radio").each(
	   function()
	   {
	     if(this.checked)
	     payment=this.value;
	   }
   
   );
  
    if(0==payment)
    {
    	alert("please choose one Transaction to operation !");
    	return;
    }
    window.location.href='ap_invoice_allocation!Reconcile.action?id='+payment;
}	
	$(function(){
 $("#list_table tr:nth-child(even) td").each(function(){
    $(this).addClass("list_td2");
 })
});

</script>
</head>

<body class="content" style="background-image:none;">

<div class="scm">
<div class="title_content">
  <div class="title">Payment Allocation</div>
</div>

<div class="search_box" >
 <div class="search_box_two">             
  <form name="form1" method="get" action="ap_invoice_allocation!GetList.action">
 <table  border="0" cellpadding="0" cellspacing="0" class="General_table">
  <tr>
    <th>Supplier No</th>
    <td><input name="filter_EQI_vendorNo" type="text" class="NFText" size="20" value="${params.filter_EQI_vendorNo}" /></td>
    <th>Transaction No</th>
    <td><input name="filter_LIKES_transactionNo" type="text" class="NFText" size="20" value="${params.filter_LIKES_transactionNo}" /></td>
    <th>Transaction Date From</th>
    <td><input name="filter_GTD_transactionDate"  id="dateFrom" type="text"   size="20"  class="NFText"   value="${params.filter_GTD_transactionDate}" /></td>
    <th>To</th>
    <td><input name="filter_LTD_transactionDate" id="dateTo" type="text"   size="20"  class="NFText"    value="${params.filter_LTD_transactionDate}" /></td>
    </tr>
    <input type='hidden' name='filter_EQS_status' value='In Progress' />
  <tr>
    <td colspan="6" align="center">
    	<input type="submit" name="Submit1" value="Search" class="search_input" />
     	<input type="button" name="Submit2" value="Refresh List" onclick="location.href='ap_invoice_allocation!GetList.action'" class="search_input" />
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
                <td><div style="margin-right:17px;">
                  <table width="993" border="0" cellspacing="0" cellpadding="0" class="list_table">
                    <tr>
                      <th width="30">&nbsp;</th>
                      <th width="130">Transaction No </th>
                      <th width="76">Supplier</th>
                      <th width="182">Transaction Type</th>
                      <th width="84">Amount</th>
                      <th width="113">Applied Amount</th>
                      <th width="69">Currency</th>
                      <th width="106">Payment Type</th>
                      <th>Transaction Date</th>
                    </tr>
                  </table>
                </div></td>
              </tr>
              <tr>
                <td><div class="list_box" style="height:340px; overflow:scroll;">
                  <table width="993" border="0" cellspacing="0" cellpadding="0" id="list_table" class="list_table2">
                     <s:iterator value="apInvoicePaymentPage.result">
					      <tr>
					        <td  width="30" ><div align="center">
					        <input type="radio" name="selectId" id="selectId" value="${transactionId}" name="transaction"/>
					        </div></td>
					        <td  width="130"><a href="ap_invoice_allocation!forwardRecord.action?apInvoicePaymentDto.transactionId=${transactionId}">${transactionId}</a></td>
					        <td  width="76" >${vendorNo}</td>					        
					        <td  width="182">${transactionType}</td>
					        <td  width="84" >${symbol}${amount}</td>
					        <td  width="113">${symbol}${applyAmount}</td>
					        <td  width="69" >${currency}</td>
					        <td  width="106">${paymentType}</td>
					        <td><s:date name="transactionDate" format="yyyy-MM-dd" /></td>
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
											<jsp:param value="${ctx}/ap_invoice_allocation!GetList.action"
												name="moduleURL" />
										</jsp:include>
									</div>

								</td>
          </tr>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
     <tr>
             <td><div class="button_box">
               <input name="Submit22" type="submit" class="search_input"  value="Select" onclick="allocation()"/>
          </div></td>
           </tr>
         </table>
		 
</body>
</html> 