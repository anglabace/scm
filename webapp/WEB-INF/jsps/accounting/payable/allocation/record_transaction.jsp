<%@ page language="java" import="java.util.List,java.util.Map,java.util.HashMap,java.util.ArrayList,java.util.Date,java.text.SimpleDateFormat" pageEncoding="UTF-8"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.genscript.gsscm.accounting.service.ApInvoicePaymentService"%>
<%@page import="com.genscript.gsscm.accounting.service.CollectionService"%>
<%@page import="com.genscript.gsscm.accounting.entity.SelectBean"%>
<%@page import="com.opensymphony.xwork2.util.ValueStack"%>
<%@page import="com.genscript.gsscm.accounting.dto.ApInvoicePaymentDTO"%>

<jsp:useBean id="SessionUtil" class="com.genscript.gsscm.common.util.SessionUtil"></jsp:useBean>

<% 
	ValueStack vs = (ValueStack)request.getAttribute("struts.valueStack");
	ApInvoicePaymentDTO apInvoicePaymentDto = (ApInvoicePaymentDTO)vs.findValue("apInvoicePaymentDto");

	ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
	CollectionService collectionService = (CollectionService) ctx.getBean("collectionService");
	ApInvoicePaymentService apInvoicePaymentService = (ApInvoicePaymentService)ctx.getBean("apInvoicePaymentService");
	
	List<SelectBean> statusList = collectionService.getCollection("Status");
	List<SelectBean> transactionTypeList = collectionService.getCollection("Transaction_Type");
	List<SelectBean> tenderTypeList = collectionService.getCollection("Tender_Type");
	List<SelectBean> paymentTypeList = collectionService.getCollection("Payment_Type");
	List<SelectBean> cardList = collectionService.getCollection("Card");
	List<SelectBean> monthList = collectionService.getCollection("Month");
	List<SelectBean> yearList = collectionService.getCollection("Year");
	List<SelectBean> currencyList = collectionService.getCollection("Currency");
	
	List<Map> invoiceList = new ArrayList<Map>();
	if(apInvoicePaymentDto.getTransactionNo() != null)	
		invoiceList = apInvoicePaymentService.getInvoiceList(apInvoicePaymentDto.getTransactionId());
	
	int userId = SessionUtil.getUserId();
	String userName = SessionUtil.getUserName();
	
	String transactionNo = apInvoicePaymentService.makeTransactionNo();
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
	String sysDate = simpleDateFormat.format(new Date());
%>

<%@ include file="/common/taglib.jsp"%>


<% 
	Object isExist = request.getAttribute("isExist");
	if(isExist != null && isExist.toString().equals("true")){
%>
<script>
	alert(lang.transaction1);
</script>	
<%	
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script type="text/javascript" src="${global_js_url}jquery/ui/ui.datepicker.js"></script>
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}lang/lang.js"></script>

    <script>
     
	$(function() {
		$('#new_resource_dlg').dialog({
			autoOpen: false,
			height: 420,
			width: 660,
			modal: true,
			bgiframe: true,
			buttons: {}
		});	
		
		$("#select_a").change(function() {
			$("#select_b").val($("#select_a").val());
			$("#select_c").val($("#select_a").val());
			if ($("#select_a").val() == 'Creadit Card') {
				$("#table_1").hide();
				$("#table_2").show();
				$("#table_3").hide();
			} else if ($("#select_a").val() == 'Direct Deposit') {
				$("#table_1").hide();
				$("#table_2").hide();
				$("#table_3").show();
			} else {
				$("#table_1").show();
				$("#table_2").hide();
				$("#table_3").hide();
			}
		})

		$("#select_b").change(function() {
			$("#select_a").val($("#select_b").val());
			$("#select_c").val($("#select_b").val());
			if ($("#select_b").val() == 'Creadit Card') {
				$("#table_1").hide();
				$("#table_2").show();
				$("#table_3").hide();
			} else if ($("#select_b").val() == 'Direct Deposit') {
				$("#table_1").hide();
				$("#table_2").hide();
				$("#table_3").show();
			} else {
				$("#table_1").show();
				$("#table_2").hide();
				$("#table_3").hide();
			}
		})

		$("#select_c").change(function() {
			$("#select_a").val($("#select_c").val());
			$("#select_b").val($("#select_c").val());
			if ($("#select_c").val() == 'Creadit Card') {
				$("#table_1").hide();
				$("#table_2").show();
				$("#table_3").hide();
			} else if ($("#select_c").val() == 'Direct Deposit') {
				$("#table_1").hide();
				$("#table_2").hide();
				$("#table_3").show();
			} else {
				$("#table_1").show();
				$("#table_2").hide();
				$("#table_3").hide();
			}
		})

		$("#select_st").change(function() {

			//alert($("[id^='cai']").css("width"));

				if (($("#select_st").val() == 'Check Payment')
						|| ($("#select_st").val() == 'Bad Debt')
						|| ($("#select_st").val() == 'Adjustment')) {
					$("[id^='txt']").attr("disabled", "disabled");
					//$("#txt2").attr("disabled","disabled");
					//$("#txt3").attr("disabled","disabled");
					//$("#txt4").attr("disabled","disabled");
					//$("#txt5").attr("disabled","disabled");
					//$("#txt6").attr("disabled","disabled");
					//$("#txt7").attr("disabled","disabled");
				} else {
					$("[id^='txt']").attr("disabled", false);
					//$("#txt2").attr("disabled",false);
					// $("#txt3").attr("disabled",false);
					//$("#txt4").attr("disabled",false);
					//$("#txt5").attr("disabled",false);
					// $("#txt6").attr("disabled",false);
					// $("#txt7").attr("disabled",false);
				}
			})
	})
	
	$(document).ready(function() {
		$('#transactionDate').each(function() {
			$(this).datepicker( {
				dateFormat : 'yy-mm-dd',
				changeMonth : true,
				changeYear : true
			});
		});
	});

	function apply() {
		return;
	}

	function doSubmit() {
		if ($('#transactionNo').val() == '' || $('#transactionNo').val() == 'undefined') {
			alert(lang.transaction2);
			return;
		}else if ($('#vendorNo').val() == '' || $('#vendorNo').val() == 'undefined' || $('#vendorNo').val() == '0') {
			alert(lang.transaction3);
			return;
		}else if ($('#transactionDate').val() == '' || $('#transactionDate').val() == 'undefined') {
			alert(lang.transaction4);
			return;
		}else if ($('#description').val() == '' || $('#description').val() == 'undefined' || $('#vendorNo').val() == '0') {
			alert(lang.transaction5);
			return;
		}else if ($('#amount').val() == '' || $('#amount').val() == 'undefined') {
			alert(lang.transaction6);
			return;
		}

		var tendType = $('#select_a').val();
		if(tendType == 'Check'){
			if($('#table_1 #accountName_1').val() == '' || $('#table_1 #accountName_1').val() == 'undefined'){
				alert(lang.transaction9);
				return;
			}else if($('#table_1 #accountNo_1').val() == '' || $('#table_1 #accountNo_1').val() == 'undefined'){
				alert(lang.transaction10);
				return;
			}else if($('#table_1 #routingNo_1').val() == '' || $('#table_1 #routingNo_1').val() == 'undefined'){
				alert(lang.transaction11);
				return;
			}
		}else if(tendType == 'Creadit Card'){
			if($('#table_2 #accountName_2').val() == '' || $('#table_2 #accountName_2').val() == 'undefined'){
				alert(lang.transaction9);
				return;
			}else if($('#table_2 #ccCardHolder').val() == '' || $('#table_2 #ccCardHolder').val() == 'undefined'){
				alert(lang.transaction12);
				return;
			}else if($('#table_2 #accountNo_2').val() == '' || $('#table_2 #accountNo_2').val() == 'undefined'){
				alert(lang.transaction13);
				return;
			}else if($('#table_2 #ccCvc').val() == '' || $('#table_2 #ccCvc').val() == 'undefined'){
				alert(lang.transaction14);
				return;
			}	
		}else if(tendType == 'Direct Deposit'){
			if($('#table_3 #accountName_3').val() == '' || $('#table_3 #accountName_3').val() == 'undefined'){
				alert(lang.transaction9);
				return;
			}else if($('#table_3 #accountNo_3').val() == '' || $('#table_3 #accountNo_3').val() == 'undefined'){
				alert(lang.transaction10);
				return;
			}else if($('#table_3 #routingNo_3').val() == '' || $('#table_3 #routingNo_3').val() == 'undefined'){
				alert(lang.transaction11);
				return;
			}	
		}
		
		if($('#transactionId').val() == '0'){ //新增
			//交验编号是否存在
			var isExist = 1;
			var options = {
					async:false,
					success:function(data, textStatus){
							isExist = data.result;
							if(data.result == 1){
								alert('Transaction No#'+$('#transactionNo').val()+' has existed');
								return;
							}
						},
					type:'get',
					data: {transactionNo:$('#transactionNo').val()},
					dataType:'json',
					url:'ap_invoice_payment!checkTransactionNoExist.action',
					error:function(XMLHttpRequest, textStatus, errorThrown){
					       alert("error:"+textStatus);
					     }	
					};
			$.ajax(options);
			if(isExist == 1){return;}
		}	

		//提交之前移除 隐藏的Payment Account表单域
		if(document.getElementById('table_1').style.display == 'none')$('#table_1').remove();
		if(document.getElementById('table_2').style.display == 'none')$('#table_2').remove();
		if(document.getElementById('table_3').style.display == 'none')$('#table_3').remove();
		
		document.getElementById('form1').action = 'ap_invoice_payment!saveOrUpdate.action';	
		document.getElementById('form1').submit();
	}
	
	function viewAllocation(){
		document.getElementById('form1').action = 'ar_invoice_payment!viewAllocationHistory.action';	
		document.getElementById('form1').submit();	
		
		return;
		window.location.href = "ar_invoice_payment!viewAllocationHistory.action?apInvoicePaymentDto.transactionId=${apInvoicePaymentDto.transactionId}";
		
		
		$('#new_resource_dlg').dialog("option", "open", function() { 
        	var htmlStr = '<iframe src="ar_invoice_payment!viewAllocationHistory.action?apInvoicePaymentDto.transactionId=${apInvoicePaymentDto.transactionId}" height="320" width="650" scrolling="no" style="border:0px" frameborder="0"></iframe>';
         	$('#new_resource_dlg').html(htmlStr);
		});
		$('#new_resource_dlg').dialog('open');
	}

	function viewOrder() {
		if($('#vendorNo').val() == '0' || $('#vendorNo').val() == ''){
			alert('Please input the Supplier first');
			return;
		}
	    $('#new_resource_dlg').dialog("option", "open", function() { 
        	var htmlStr = '<iframe id="orderIframe" src="genscript!orderlist.action?filter_EQS_vendorNo='+$('#vendorNo').val()+'" height=350" width="600" scrolling="no" style="border:0px" frameborder="0"></iframe>';
         	$('#new_resource_dlg').html(htmlStr);
		});
		$('#new_resource_dlg').dialog('open');
	}

	function setOrderValue(orderNo){
		$('#orderNo').html('<option value="'+orderNo+'">'+orderNo+'</option>');
	}
	
	function viewInvoice_bck(){
		document.getElementById('form1').action = 'ar_invoice_payment!viewInvoice.action';	
		document.getElementById('form1').submit();	
	}

	function viewInvoice(){
		var orderNo = $('#orderNo').val();
		if(!orderNo || orderNo == ''){
			alert(lang.transaction15);
			return;
		}
		$('#new_resource_dlg').dialog("option", "open", function() { 
       	var htmlStr = '<iframe id="invoiceIframe" src="genscript!invoicelist.action?filter_EQI_orderNo='+orderNo+'" height=350" width="620" scrolling="no" style="border:0px" frameborder="0"></iframe>';
        	$('#new_resource_dlg').html(htmlStr);
		});
		$('#new_resource_dlg').dialog('open');
		//setInvoiceValue();
	}

	function setInvoiceValue(invoiceId,invoiceNo,currency,applyAmount){
		if(!invoiceId)return;
		if($('#currency').val() != currency){
			alert(lang.transaction16);
			return;
		}
		
		$('#invoiceId').html('<option value="'+invoiceId+'">'+invoiceNo+'</option>');	
		$('#applyAmount').val(applyAmount);
		$('#currency').val(currency);
		var balance = parseInt($('#amount').val()) - parseInt($('#applyAmount').val());
		$('#balance').val((balance == 'NaN' || balance < 0)?'0.000':balance);
	}

	function viewCustomer(){
		$('#new_resource_dlg').dialog("option", "open", function() { 
        	var htmlStr = '<iframe id="iframe1" src="genscript!vendorList.action" height=350" width="600" scrolling="no" style="border:0px" frameborder="0"></iframe>';
         	$('#new_resource_dlg').html(htmlStr);
		});
		$('#new_resource_dlg').dialog('open');
	}

	function setCustomerValue(vendorNo,vendorName){
		$('#vendorNo').val(vendorNo);
		$('#vendorName').val(vendorName);
	}

	function doFeeChange(ele){
		//交验数值格式
		var balance = parseInt($('#amount').val()) - parseInt($('#applyAmount').val());
		$('#balance').val((balance == 'NaN' || balance < 0)?'0.000':balance);
	}

</script>
<!--    <link href="greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />-->
</head>

<body class="content" style="background-image:none;">
<form name="form1" id="form1" action="ap_invoice_payment!saveOrUpdate.action" method="post">
<input name="transactionId" id="transactionId" type="hidden" value="${apInvoicePaymentDto.transactionId}"/>
<div class="scm">
<div class="title_content">
  <div class="title">${apInvoicePaymentDto.transactionTitle}</div>
</div>


        <div class="input_box">
		  <div class="content_box">
	        <table  border="0" cellpadding="0" cellspacing="0" class="Customer_table">
      <tr>
        <th width="150">Transaction No </th>
        <td>
        	<s:if test="apInvoicePaymentDto.transactionNo == null">
        		<input id="transactionNo" name="transactionNo" type="text"  class="NFText" value="<%= transactionNo%>" size="40" readonly />
	        </s:if>
	        <s:else>
	        	<input id="transactionNo" name="transactionNo" type="text"  class="NFText" value="${apInvoicePaymentDto.transactionId}" size="40" ${apInvoicePaymentDto.transactionNo==null?'':'readonly'}/>
	        </s:else>
        </td>
        <th width="121">Status</th>
        <td width="212">
	        <s:if test="apInvoicePaymentDto.transactionNo == null">
	        	<select name="status" style="width:220px;*width:232px;">
				<option selected="selected">Draft</option>
			</select>
	        </s:if>
	        <s:else>
	        	<select name="status" style="width:220px;*width:232px;">
				<% 
					out.print(collectionService.outCollection(statusList,apInvoicePaymentDto.getStatus()));
				%>
				</select>
	        </s:else>
        </td>
        </tr>
      <tr>
        <th valign="top">Supplier</th>
        <td width="350">
        	<input name="vendorNo" id="vendorNo" type="hidden" value="${apInvoicePaymentDto.vendorNo}"/>
        	<input name="vendorName" id="vendorName" type="text" class="NFText" value="${apInvoicePaymentDto.firstName} ${apInvoicePaymentDto.lastName}" size="40" readonly/>
        	<img src="images/search.gif" width="16" height="16" align="absmiddle" onclick="viewCustomer()" style=" cursor:pointer"  />
        	<input type="button" name="Submit6" class="style_botton2" value="View Supplier" onclick="viewSupplierInfo()" />
        </td>
        <th>Transaction Type</th>
        <td>
	        <select name="transactionType" style="width:220px;*width:232px;" id="select_st">
				<% 
					out.print(collectionService.outCollection(transactionTypeList,apInvoicePaymentDto.getTransactionType()));
				%>
			</select>
        </td>
        </tr>
      <tr>
        <th>Transaction Entry Date</th>
        <td>
        <s:if test="apInvoicePaymentDto.transactionNo == null">
        	<input name="transactionDate" type="text" class="NFText" id="transactionDate" readonly value="<%= sysDate%>" size="40"/>
        </s:if>
        <s:else>
        	<input name="transactionDate" type="text" class="NFText" id="transactionDate" readonly value="<s:date name="apInvoicePaymentDto.transactionDate" format="yyyy-MM-dd" />" size="40"/>
        </s:else>
        </td>
        <th>Transaction Entered By</th>
        <td>
        <s:if test="apInvoicePaymentDto.transactionNo == null">
        	<input name="createdBy" type="hidden" id="createdBy" value="<%= userId%>" />
        	<input name="createdByName" type="text"  class="NFText" id="createdByName" size="40" value="<%= userName%>" readonly />
        </s:if>
        <s:else>
        	<input name="createdBy" type="hidden" id="createdBy" value="${apInvoicePaymentDto.createdBy}" />
        	<input name="createdByName" type="text"  class="NFText" id="createdByName" size="40" value="${apInvoicePaymentDto.createdByName}" readonly />
        </s:else>
        </td>
        </tr>
		<tr><th>Description</th><td colspan="5"><textarea id="description" name="description" rows="10" cols="30" class="content_textarea2" style="width:350px;">${apInvoicePaymentDto.description}</textarea></td></tr>
    </table>
    <div class="invoice_title">Amounts</div>
		
	          <table  border="0" cellpadding="0" cellspacing="0" class="Customer_table">
                <tr>
                  <th width="150">Amount</th>
                  <td width="350">
                  	  <input type="hidden" name="transactionFee" value="0.000" />
	                  <s:if test="apInvoicePaymentDto.transactionNo == null">
			        	  <input name="amount" id="amount" type="text"  class="NFText2" value="0.000" size="40" onchange="doFeeChange(this)"/>
			          </s:if>
			          <s:else>
			        	  <input name="amount" id="amount" type="text"  class="NFText2" value="${apInvoicePaymentDto.amount}" size="40" onchange="doFeeChange(this)"/>
			          </s:else>	
                  </td>
                  <th width="121">Applied Amount</th>
                  <td width="220">
                  	<s:if test="apInvoicePaymentDto.transactionNo == null">
		        	  <input name="applyAmount" type="text"  class="NFText2" value="0.000" size="40" readonly id="applyAmount"/>
		            </s:if>
		            <s:else>
		        	  <input name="applyAmount" type="text"  class="NFText2" value="${apInvoicePaymentDto.applyAmount==null?'0.000':apInvoicePaymentDto.applyAmount}" size="40" readonly id="applyAmount"/>
		            </s:else>	
                  </td>
                </tr>
                <tr>
                  <th valign="top">Balance</th>
                  <td width="350">
                  	<input name="balance" type="text"  class="NFText2" value="${apInvoicePaymentDto.balance==null?'0.000':apInvoicePaymentDto.balance}" size="40" id="balance" readonly />
                  </td>
                  <th>Currency</th>
                  <td>
                  	<select name="currency" id="currency" style="width:220px;*width:232px;">
						<% 
							out.print(collectionService.outCollection(currencyList,apInvoicePaymentDto.getCurrency()));
						%>
                    </select>
                  </td>
                </tr>
              </table>
	  	    <div class="invoice_title">Order Reference</div>
		
<table  border="0" cellpadding="0" cellspacing="0" class="Customer_table">
                <tr>
                  <th width="150">Order No</th>
                  <td width="350">
                  <select name="orderNo" id="orderNo" style="width:220px;*width:232px;" ${apInvoicePaymentDto.transactionNo!=null?'disabled':''}>
                  	<% 
						for(int i=0; i<invoiceList.size(); i++){
							Map invoiceMap = invoiceList.get(i);
					%>
						<option value="<%= invoiceMap.get("orderNo")%>"><%= invoiceMap.get("orderNo")%></option>					
					<%		
						}
					%>
                  </select>
                  
                  <img src="images/search.gif" width="16" height="16" align="absmiddle" onclick="viewOrder()" style=" cursor:pointer"  />
                  <input type="button" name="Submit" class="style_botton2" value="View Order"  onclick="viewPurchaseOrder()" ${apInvoicePaymentDto.transactionNo!=null?'disabled':''} />
                  </td>
                  <th width="121">Invoice No</th>
                  <td width="">
                  <select name="invoiceId" id="invoiceId" style="width:220px;*width:232px;" ${apInvoicePaymentDto.transactionNo!=null?'disabled':''}>
                    <% 
						for(int i=0; i<invoiceList.size(); i++){
							Map invoiceMap = invoiceList.get(i);
					%>
						<option value="<%= invoiceMap.get("invoiceId")%>"><%= invoiceMap.get("invoiceNo")%></option>					
					<%		
						}
					%>
                  </select>
                   <img src="images/search.gif" width="16" height="16" align="absmiddle" onclick="viewInvoice()" style=" cursor:pointer"  />
                  <input type="button" name="Submit5" class="style_botton2" value="View Invoice" onclick="viewApInvoice()" ${apInvoicePaymentDto.transactionNo!=null?'disabled':''} />
                  </td>
                </tr>
              </table>
	
			<div class="invoice_title">Payment Account</div>
			<s:if test="apInvoicePaymentDto.transactionNo==null || apInvoicePaymentDto.tendType=='Check'">
				<table  border="0" cellpadding="0" cellspacing="0" class="Customer_table" id="table_1">	
			</s:if>
			<s:else>
				<table  border="0" cellpadding="0" cellspacing="0" class="Customer_table" id="table_1" style="display:none;">	
			</s:else>
			    	<tr>
			          <th width="150">Tender Type</th>
			          <td width="350">
			          <select name="tendType" style="width:220px;*width:232px;" id="select_a">
			          	<% 
							out.print(collectionService.outCollection(tenderTypeList,apInvoicePaymentDto.getTenderType()));
						%>
			          </select>
			          </td>
			          <th width="121">Payment Type</th>
			          <td width="212">
			          <select name="paymentType" style="width:220px;*width:232px;">
			            <% 
							out.print(collectionService.outCollection(paymentTypeList,apInvoicePaymentDto.getPaymentType()));
						%>
			          </select>
			          </td>
			        </tr>
			        <tr>
			          <th valign="top">Account Name</th>
			          <td><input name="accountName" type="text" id="accountName_1" size="40"  class="NFText" value="${apInvoicePaymentDto.accountName}" /></td>
			          <th>Account No</th>
			          <td><input name="accountNo" type="text"  class="NFText" id="accountNo_1" size="40" value="${apInvoicePaymentDto.accountNo}" /></td>
			        </tr>
			        <tr>
			          <th valign="top">Routing No</th>
			          <td><input name="routingNo" type="text" id="routingNo_1" size="40"  class="NFText" value="${apInvoicePaymentDto.routingNo}" /></td>
			          <th>Check No</th>
			          <td><input name="chkNo" type="text"  class="NFText" id="chkNo" size="40" value="${apInvoicePaymentDto.chkNo}" /></td>
			        </tr>
				</table>
			
			<s:if test="apInvoicePaymentDto.transactionNo!=null && apInvoicePaymentDto.tendType=='Creadit Card'">
				<table  border="0" cellpadding="0" cellspacing="0" class="Customer_table" id="table_2">	
			</s:if>
			<s:else>
				<table  border="0" cellpadding="0" cellspacing="0" class="Customer_table" id="table_2" style="display:none;">	
			</s:else>              
			      <tr>
			        <th width="150">Tender type</th>
			        <td width="350"><select name="tendType" style="width:220px;*width:232px;" id="select_b">
			          <option>Check</option>
			          <option selected="selected">Creadit Card</option>
			          <option>Direct Deposit</option>
			        </select></td>
			        <th width="121">Payment Type</th>
			        <td width="212">
			        	<select name="paymentType" style="width:220px;*width:232px;">
			            <% 
							out.print(collectionService.outCollection(paymentTypeList,apInvoicePaymentDto.getPaymentType()));
						%>
			          </select>
			        </td>
			      </tr>
			      <tr>
			        <th valign="top">Account Name</th>
			        <td><input name="accountName" id="accountName_2" type="text" size="40" class="NFText" value="${apInvoicePaymentDto.accountName}" /></td>
			        <th>&nbsp;</th>
			        <td>&nbsp;</td>
			      </tr>
			      <tr>
			        <th valign="top">Credit Card Type</th>
			        <td>
			        <select name="ccType" style="width:220px;*width:232px;" id="ccType">
			          <% 
						out.print(collectionService.outCollection(cardList,apInvoicePaymentDto.getCcType()));
					  %>
			        </select>
			        </td>
			        <th>Name on the Card</th>
			        <td><input name="ccCardHolder" type="text"  class="NFText" id="ccCardHolder" size="40" value="${apInvoicePaymentDto.ccCardHolder}" /></td>
			      </tr>
			      <tr>
			        <th valign="top">Credit Card Numeber</th>
			        <td><input name="accountNo" type="text" id="accountNo_2" size="40" class="NFText" value="${apInvoicePaymentDto.accountNo}" /></td>
			        <th>CVC</th>
			        <td><input name="ccCvc" type="text"  class="NFText" id="ccCvc" size="40" value="${apInvoicePaymentDto.ccCvc}" /></td>
			      </tr>
			      <tr>
			        <th valign="top">Expiration Date</th>
			        <td>
			          <select name="ccExpirationMonth" id="ccExpirationMonth" style="width:106px;*width:112px;">
			            <% 
			              String month = "";
			              String year = "";
			              String ccExpiration = apInvoicePaymentDto.getCcExpiration();
			            	if(ccExpiration != null && !ccExpiration.equals("")){
			            		year = ccExpiration.substring(0,4);
			            		month = ccExpiration.substring(5,7);
			            	}
			            	out.print(collectionService.outCollection(monthList,month));
						  %>
			          </select>
			          <select name="ccExpirationYear" id="ccExpirationYear" style="width:106px;*width:113px;">
			            <% 
							out.print(collectionService.outCollection(yearList,year));
						%>
			          </select>
			        <input type="button" name="Submit3" class="style_botton2" value="Charge" /></td>
			        <th>&nbsp;</th>
			        <td>&nbsp;</td>
			      </tr>
			    </table>
			              
			<s:if test="apInvoicePaymentDto.transactionNo!=null && apInvoicePaymentDto.tendType=='Direct Deposit'">
				<table  border="0" cellpadding="0" cellspacing="0" class="Customer_table" id="table_3">
			</s:if>
			<s:else>
				<table  border="0" cellpadding="0" cellspacing="0" class="Customer_table" id="table_3" style="display:none;">	
			</s:else>               
			     <tr>
			       <th width="150">Tender type </th>
			       <td width="350"><select name="tendType" style="width:220px;*width:232px;" id="select_c">
			         <option value="Check">Check</option>
			         <option value="Creadit Card">Creadit Card</option>
			         <option value="Direct Deposit" selected="selected">Direct Deposit</option>
			       </select></td>
			       <th width="121">Payment Type</th>
			       <td width="212">
			       		<select name="paymentType" style="width:220px;*width:232px;">
			            <% 
							out.print(collectionService.outCollection(paymentTypeList,apInvoicePaymentDto.getPaymentType()));
						%>
			           </select>
			       </td>
			     </tr>
			     <tr>
			       <th valign="top">Account Name</th>
			       <td><input name="accountName" type="text" id="accountName_3" size="40"  class="NFText" value="${apInvoicePaymentDto.accountName}" /></td>
			       <th>&nbsp;</th>
			       <td>&nbsp;</td>
			     </tr>
			     <tr>
			       <th valign="top">Account No</th>
			       <td><input name="accountNo" type="text" id="accountNo_3" size="40"  class="NFText" value="${apInvoicePaymentDto.accountNo}"/></td>
			       <th>Routing No</th>
			       <td><input name="routingNo" type="text"  class="NFText" id="routingNo_3" size="40" value="${apInvoicePaymentDto.routingNo}"/></td>
			     </tr>
			   </table>
   
		 <br/>
	     <br/>
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
           <tr>
             <td><div class="button_box">
<%--             	 <input name="Submit4" type="button" class="search_input" value="Save" onclick="doSubmit()" />--%>
				 <input name="Submit2" type="button" class="search_input" value="Cancel" onclick="window.history.back()"/>
             </div></td>
           </tr>
         </table>
		</div>
  </div>	
</div>	
<div id="new_resource_dlg" name="new_resource_dlg"></div>
</form>
</body>
</html>
