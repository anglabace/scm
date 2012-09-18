<%@ page language="java" import="java.util.List,java.util.Map,java.util.HashMap,java.util.ArrayList,java.util.Date,java.text.SimpleDateFormat" pageEncoding="UTF-8"%>

<jsp:useBean id="SessionUtil" class="com.genscript.gsscm.common.util.SessionUtil"></jsp:useBean>
<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script type="text/javascript" src="${global_js_url}jquery/jquery.form.js"></script>
<script type="text/javascript" src="${global_js_url}jquery/ui/ui.datepicker.js"></script>
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}lang/lang.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tools.js"></script>
    <script>
     
     
	$(function() {
		$('#new_customer_dlg').dialog({
			autoOpen: false,
			height: 420,
			width: 660,
			modal: true,
			bgiframe: true,
			buttons: {}
		});	

		$('#new_resource_dlg3').dialog({
			autoOpen: false,
			height: 420,
			width: 660,
			modal: true,
			bgiframe: true,
			buttons: {}
		});	
		
		$('#new_invoice_dlg').dialog({
			autoOpen: false,
			height: 420,
			width: 660,
			modal: true,
			bgiframe: true,
			buttons: {}
		});	
		
	$('#view_invoice').dialog({
		autoOpen: false,
		height: 450,
		width: 800,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
	
    $('#view_supplier_info').dialog({
		autoOpen: false,
		height: 450,
		width: 800,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
	
	 $('#view_order').dialog({
		autoOpen: false,
		height: 450,
		width: 800,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
	
		$("#select_a").change(function() {
			$("#select_b").val($("#select_a").val());
			$("#select_c").val($("#select_a").val());
			if ($("#select_a").val() == 'Credit Card') {
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
			if ($("#select_b").val() == 'Credit Card') {
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
			if ($("#select_c").val() == 'Credit Card') {
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
		$('#form1').ajaxForm(function(){
			alert('Record Transaction Success!');
		});
	});


	function clickSuppler(disabled){
	if(disabled)return;
	$("#orderNo").val("");
	 $('#new_customer_dlg').dialog("option", "open", function() { 
	        	var htmlStr = '<iframe id="iframe1" src="genscript!vendorList.action" height=400" width="630" scrolling="no" style="border:0px" frameborder="0"></iframe>';
	         	$('#new_customer_dlg').html(htmlStr);
			});
			$('#new_customer_dlg').dialog('open');
	}

	function setSupplierValue(vendorNo,vendorName){
		$('#vendorNo').val(vendorNo);
		$('#vendorName').val(vendorName);
	}

	function doSubmit() {
		if ($('#vendorNo').val() == '' || $('#vendorNo').val() == 'undefined' || $('#vendorNo').val() == '0') {
			alert(lang.transaction18);
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

		var tenderType = $('#select_a').val();
		if(tenderType == 'Check'){
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
		}else if(tenderType == 'Credit Card'){
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
		}else if(tenderType == 'Direct Deposit'){
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

		//提交之前移除 隐藏的Payment Account表单域
		if(document.getElementById('table_1').style.display == 'none')$('#table_1').remove();
		if(document.getElementById('table_2').style.display == 'none')$('#table_2').remove();
		if(document.getElementById('table_3').style.display == 'none')$('#table_3').remove();
		
		document.getElementById('form1').action = 'ap_invoice_payment!saveOrUpdate.action';
		$('#form1').ajaxSubmit(function(){
			$(this).ajaxSubmit({
				　　success:function(p){
						alert('Record Transaction Success!');
						document.getElementById('form1').action = 'ap_invoice_payment!input.action';	
						document.getElementById('form1').submit();
					} 
				});
			return false;
		});
	}
	
	function viewOrder() {
		if($('#vendorNo').val() == '0' || $('#vendorNo').val() == ''){
			alert('Please input the Supplier first');
			return;
		}
	    $('#new_resource_dlg3').dialog("option", "open", function() { 
        	var htmlStr = '<iframe id="orderIframe" src="genscript!purchaseOrderNoSearch.action?filter_EQI_vendorNo='+$('#vendorNo').val()+'" height=350" width="600" scrolling="no" style="border:0px" frameborder="0"></iframe>';
         	$('#new_resource_dlg3').html(htmlStr);
		});
		$('#new_resource_dlg3').dialog('open');
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
		$('#new_invoice_dlg').dialog("option", "open", function() { 
		var vendorNo = $("#vendorNo").val();
		var currency =$('#currency').val();
		var url = 'genscript!ApInvoiceList.action?filter_EQI_orderNo='+orderNo+'&filter_EQS_currency='+currency+'&filter_EQI_vendorNo='+vendorNo+'&filter_EQS_status=In Progress';
       	var htmlStr = '<iframe id="invoiceIframe" src="'+url+'" height=350" width="620" scrolling="no" style="border:0px" frameborder="0"></iframe>';
        	$('#new_invoice_dlg').html(htmlStr);
		});
		$('#new_invoice_dlg').dialog('open');
		//setInvoiceValue();
	}

//applyAmount 为invoice的balance
	function setInvoiceValue(invoiceId,invoiceNo,currency,applyAmount){
		if(!invoiceId)return;
		if($('#currency').val() != currency){
			alert(lang.transaction16);
			return;
		}
		
		$('#invoiceId').html('<option value="'+invoiceId+'">'+invoiceNo+'</option>');	
		
		
		
		
		//$('#applyAmount').val(applyAmount);
		var balance = parseFloat($("#balance").val());
				applyAmount = applyAmount > balance ? balance : applyAmount;
		$('#applyAmount').val(applyAmount);
		$('#currency').val(currency);
		balance = parseFloat($('#amount').val()) - parseFloat($('#applyAmount').val());
		$('#balance').val((balance == 'NaN' || balance < 0)?'0.000':balance);

        if(applyAmount <= balance){//如果invoice的balance<付款单的balance说明invoice对账完毕
         $("#closeInvoice").val('Closed');
        }
	}





	function doFeeChange(ele){
		//交验数值格式
		//var balance = parseInt($('#amount').val()) - parseInt($('#applyAmount').val());
		//$('#balance').val((balance == 'NaN' || balance < 0)?'0.000':balance);
		$.ajax({
		      type:'get',
		      dataType:'json',
		      url :'ap_invoice_payment!calculateBalance.action',
		      data:{currency: $('#currency').val(),amount: $('#amount').val(),applyAmount:$('#applyAmount').val()},
		      success:function(obj){
		    	  $('#amount').val(obj.amount);
		    	  $('#applyAmount').val(obj.applyAmount);
		    	  $('#balance').val(obj.balance);
			  },
		      error:function(){}
	   }); 
	}

	//货币调整
	function getCurrencyInfo(){
		$.ajax({
		      type:'get',
		      dataType:'json',
		      url :'ap_invoice_payment!getCurrencyInfo.action',
		      data:{currency: $('#currency').val()},
		      success:function(obj){
				 $('.symbol').each(function(i){
						$(this).html(obj.symbol);
					 });
			  },
		      error:function(){}
	        }); 
	}

</script>
<!--    <link href="greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />-->
</head>

<body class="content" style="background-image:none;">
<form name="form1" id="form1" action="ap_invoice_payment!saveOrUpdate.action" method="post">
<input name="transactionId" id="transactionId" type="hidden" value="${apInvoicePaymentDto.transactionId}"/>
<input name="forwardUrl" id="forwardUrl" type="hidden" value="ar-invoice-payment-list"/>
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
        		<input id="transactionNo" name="transactionNo" type="text"  class="NFText" value="" size="40" readonly />
	        </s:if>
	        <s:else>
	        	<input id="transactionNo" name="transactionNo" type="text"  class="NFText" value="${apInvoicePaymentDto.transactionId}" size="40" ${apInvoicePaymentDto.transactionNo==null?'':'readonly'}/>
	        </s:else>
        </td>
        <th width="130">Status</th>
        <td width="212">
	        <s:if test="apInvoicePaymentDto.transactionNo == null">
	        	<select name="status" style="width:220px;*width:232px;">
				<option selected="selected">Draft</option>
			</select>
	        </s:if>
	        <s:else>
	        	<select name="status" style="width:220px;*width:232px;">
	        	<s:iterator value="apInvoicePaymentDto.statusList" >
					<s:if test="value == apInvoicePaymentDto.status">
						<option value="${value}" selected>${key}</option>
					</s:if>
					<s:else>
						<option value="${value}">${key}</option>
					</s:else>
				</s:iterator>
				</select>
	        </s:else>
        </td>
        </tr>
      <tr>
        <th valign="top">Supplier</th>
        
        
        <input name="closeInvoice" type="hidden" value=""/>
        
        <td width="350">
        	<input name="vendorNo" id="vendorNo" type="hidden" value="${apInvoicePaymentDto.vendorNo}"/>
        	<input name="vendorName" id="vendorName" type="text" class="NFText" value="${apInvoicePaymentDto.firstName} ${apInvoicePaymentDto.lastName}" size="40" readonly/>
        	<img src="images/search.gif"  height="16" align="absmiddle" onclick="clickSuppler(${apInvoicePaymentDto.transactionNo==null || apInvoicePaymentDto.status=='Draft'?false:true})" style=" cursor:pointer"  />
        	<input type="button" name="Submit6" class="style_botton2" value="View Supplier" onclick="viewSupplierInfo()" />
        </td>
        <th>Transaction Type</th>
        <td>
	        <select name="transactionType" style="width:220px;*width:232px;" id="select_st">
			<s:iterator value="apInvoicePaymentDto.transactionTypeList" >
				<s:if test="value == apInvoicePaymentDto.transactionType">
					<option value="${value}" selected>${key}</option>
				</s:if>
				<s:else>
					<option value="${value}">${key}</option>
				</s:else>
			</s:iterator>	
			</select>
        </td>
        </tr>
      <tr>
        <th>Transaction Entry Date</th>
        <td>
        <s:if test="apInvoicePaymentDto.transactionNo == null">
        	<input name="transactionDate" type="text" class="NFText" id="transactionDate" readonly value="${apInvoicePaymentDto.sysDate}" size="40"/>
        </s:if>
        <s:else>
        	<input name="transactionDate" type="text" class="NFText" id="transactionDate" readonly value="<s:date name="apInvoicePaymentDto.transactionDate" format="yyyy-MM-dd" />" size="40"/>
        </s:else>
        </td>
        <th>Transaction Entered By</th>
        <td>
        <s:if test="apInvoicePaymentDto.transactionNo == null">
        	<input name="createdBy" type="hidden" id="createdBy" value="${apInvoicePaymentDto.userId}" />
        	<input name="createdByName" type="text"  class="NFText" id="createdByName" size="40" value="${apInvoicePaymentDto.userName}" readonly />
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
			          <s:elseif test="apInvoicePaymentDto.status== 'Draft'">
			        	  <input name="amount" id="amount" type="text"  class="NFText2" value="${apInvoicePaymentDto.amount}" size="40" onchange="doFeeChange(this)" />
			          </s:elseif>	
			          <s:else>
			          	<input name="amount" id="amount" type="text"  class="NFText2" value="${apInvoicePaymentDto.amount}" size="40" onchange="doFeeChange(this)" readonly />
			          </s:else>
			          <span class="symbol">${apInvoicePaymentDto.symbol}</span>
                  </td>
                  <th width="130">Applied Amount</th>
                  <td>
                  	<s:if test="apInvoicePaymentDto.transactionNo == null">
		        	  <input name="applyAmount" type="text"  class="NFText2" value="0.000" size="40" readonly id="applyAmount"/>
		            </s:if>
		            <s:else>
		        	  <input name="applyAmount" type="text"  class="NFText2" value="${apInvoicePaymentDto.applyAmount==null?'0.000':apInvoicePaymentDto.applyAmount}" size="40" readonly id="applyAmount"/>
		            </s:else>	
		            <span class="symbol">${apInvoicePaymentDto.symbol}</span>
                  </td>
                </tr>
                <tr>
                  <th valign="top">Balance</th>
                  <td width="350">
                  	<input name="balance" type="text"  class="NFText2" value="${apInvoicePaymentDto.balance==null?'0.000':apInvoicePaymentDto.balance}" size="40" id="balance" readonly />
                  	<span class="symbol">${apInvoicePaymentDto.symbol}</span>
                  </td>
                  <th>Currency</th>
                  <td>
                  	<select name="currency" id="currency" style="width:220px;*width:232px;" onchange="getCurrencyInfo()">
					<s:iterator value="apInvoicePaymentDto.currencyList" >
						<s:if test="(apInvoicePaymentDto.currency==null && value=='USD') || (apInvoicePaymentDto.currency == value)">
							<option value="${value}" selected>${key}</option>
						</s:if>
						<s:else>
							<option value="${value}">${key}</option>
						</s:else>
					</s:iterator>	
                    </select>
                  </td>
                </tr>
              </table>
	  	    <div class="invoice_title">Order Reference</div>
		
<table  border="0" cellpadding="0" cellspacing="0" class="Customer_table">
                <tr>
                  <th width="150">Order No</th>
                  <td width="350">
                  <select name="orderNo" id="orderNo" style="width:220px;*width:232px;"  disabled="disabled"> <%-- ${apInvoicePaymentDto.status=='In Progress' && apInvoicePaymentDto.invoiceNo==null?'':'disabled'} --%>
                  <s:iterator value="apInvoicePaymentDto.invoiceList" >
                  	<option value="${orderNo}">${orderNo}</option>
                  </s:iterator>
                  </select>
                   <img src="images/search.gif" width="16" height="16" align="absmiddle" onclick="viewOrder()" style=" cursor:pointer" ${apInvoicePaymentDto.status =='In Progress' ? '':'disabled'} /> 
                   <input type="button" name="Submit" class="style_botton2" value="View Order"  onclick="viewPurchaseOrder()"  />
                  </td>
                  
                  <th width="130">Invoice No</th>
                  <td width="">
                  <select name="invoiceId" id="invoiceId" style="width:220px;*width:232px;" disabled="disabled">
                  <s:iterator value="apInvoicePaymentDto.invoiceList" >
                  	<option value="${invoiceId}">${invoiceNo}</option>
                  </s:iterator>
                  </select>
                   <img src="images/search.gif" width="16" height="16" align="absmiddle" onclick="viewInvoice()" style=" cursor:pointer" ${apInvoicePaymentDto.status =='In Progress' ? '':'disabled'} />
                  <input type="button" name="Submit5" class="style_botton2" value="View Invoice" onclick="viewApInvoice()" } />
                  </td>
                </tr>
              </table>
	
			<div class="invoice_title">Payment Account</div>
			<s:if test="apInvoicePaymentDto.transactionNo==null || apInvoicePaymentDto.tenderType=='Check'">
				<table  border="0" cellpadding="0" cellspacing="0" class="Customer_table" id="table_1">	
			</s:if>
			<s:else>
				<table  border="0" cellpadding="0" cellspacing="0" class="Customer_table" id="table_1" style="display:none;">	
			</s:else>
			    	<tr>
			          <th width="150">Tender Type</th>
			          <td width="350">
			          <select name="tenderType" style="width:220px;*width:232px;" id="select_a">
			          <s:iterator value="apInvoicePaymentDto.tenderTypeList" >
						  <s:if test="value == apInvoicePaymentDto.tenderType">
						  	<option value="${value}" selected>${key}</option>
						  </s:if>
						  <s:else>
						  	<option value="${value}">${key}</option>
						  </s:else>
					  </s:iterator>
			          </select>
			          </td>
			          <th width="130">Payment Type</th>
			          <td width="212">
			          <select name="paymentType" style="width:220px;*width:232px;">
			          <s:iterator value="apInvoicePaymentDto.paymentTypeList" >
						  <s:if test="value == apInvoicePaymentDto.paymentType">
						  	<option value="${value}" selected>${key}</option>
						  </s:if>
						  <s:else>
						  	<option value="${value}">${key}</option>
						  </s:else>
					  </s:iterator>
			          </select>
			          </td>
			        </tr>
			        <tr>
			          <th valign="top">Account Name</th>
			          <td><input name="accountName" type="text" id="accountName_1" size="40"  class="NFText" value="${apInvoicePaymentDto.accountName}" ${apInvoicePaymentDto.readonly} /></td>
			          <th>Account No</th>
			          <td><input name="accountNo" type="text"  class="NFText" id="accountNo_1" size="40" value="${apInvoicePaymentDto.accountNo}" ${apInvoicePaymentDto.readonly} /></td>
			        </tr>
			        <tr>
			          <th valign="top">Routing No</th>
			          <td><input name="routingNo" type="text" id="routingNo_1" size="40"  class="NFText" value="${apInvoicePaymentDto.routingNo}" ${apInvoicePaymentDto.readonly} /></td>
			          <th>Check No</th>
			          <td><input name="chkNo" type="text"  class="NFText" id="chkNo" size="40" value="${apInvoicePaymentDto.chkNo}" ${apInvoicePaymentDto.readonly} /></td>
			        </tr>
				</table>
			
			<s:if test="apInvoicePaymentDto.transactionNo!=null && apInvoicePaymentDto.tenderType=='Credit Card'">
				<table  border="0" cellpadding="0" cellspacing="0" class="Customer_table" id="table_2">	
			</s:if>
			<s:else>
				<table  border="0" cellpadding="0" cellspacing="0" class="Customer_table" id="table_2" style="display:none;">	
			</s:else>              
			      <tr>
			        <th width="150">Tender type </th>
			        <td width="350">
			        <select name="tenderType" style="width:220px;*width:232px;" id="select_b">
			        <s:iterator value="apInvoicePaymentDto.tenderTypeList" >
						  <s:if test="value == apInvoicePaymentDto.tenderType">
						  	<option value="${value}" selected>${key}</option>
						  </s:if>
						  <s:else>
						  	<option value="${value}">${key}</option>
						  </s:else>
					  </s:iterator>
			        </select></td>
			        <th width="130">Payment Type</th>
			        <td width="212">
			        	<select name="paymentType" style="width:220px;*width:232px;">
			            <s:iterator value="apInvoicePaymentDto.paymentTypeList" >
						  <s:if test="value == apInvoicePaymentDto.paymentType">
						  	<option value="${value}" selected>${key}</option>
						  </s:if>
						  <s:else>
						  	<option value="${value}">${key}</option>
						  </s:else>
					  </s:iterator>
			          </select>
			        </td>
			      </tr>
			      <tr>
			        <th valign="top">Account Name</th>
			        <td><input name="accountName" id="accountName_2" type="text" size="40" class="NFText" value="${apInvoicePaymentDto.accountName}" ${apInvoicePaymentDto.readonly} /></td>
			        <th>&nbsp;</th>
			        <td>&nbsp;</td>
			      </tr>
			      <tr>
			        <th valign="top">Credit Card Type</th>
			        <td>
			        <select name="ccType" style="width:220px;*width:232px;" id="ccType">
			        <s:iterator value="apInvoicePaymentDto.cardList" >
					  <s:if test="value == apInvoicePaymentDto.ccType">
					  	<option value="${value}" selected>${key}</option>
					  </s:if>
					  <s:else>
					  	<option value="${value}">${key}</option>
					  </s:else>
				    </s:iterator>
			        </select>
			        </td>
			        <th>Name on the Card</th>
			        <td><input name="ccCardHolder" type="text"  class="NFText" id="ccCardHolder" size="40" value="${apInvoicePaymentDto.ccCardHolder}" ${apInvoicePaymentDto.readonly} /></td>
			      </tr>
			      <tr>
			        <th valign="top">Credit Card Numeber</th>
			        <td><input name="accountNo" type="text" id="accountNo_2" size="40" class="NFText" value="${apInvoicePaymentDto.accountNo}" ${apInvoicePaymentDto.readonly} /></td>
			        <th>CVC</th>
			        <td><input name="ccCvc" type="text"  class="NFText" id="ccCvc" size="40" value="${apInvoicePaymentDto.ccCvc}" ${apInvoicePaymentDto.readonly} /></td>
			      </tr>
			      <tr>
			        <th valign="top">Expiration Date</th>
			        <td>
			          <select name="ccExpirationMonth" id="ccExpirationMonth" style="width:106px;*width:112px;">
			          <s:iterator value="apInvoicePaymentDto.monthList" >
						  <s:if test="value == apInvoicePaymentDto.month">
						  	<option value="${value}" selected>${key}</option>
						  </s:if>
						  <s:else>
						  	<option value="${value}">${key}</option>
						  </s:else>
					    </s:iterator> 
			          </select>
			          <select name="ccExpirationYear" id="ccExpirationYear" style="width:106px;*width:113px;">
			          <s:iterator value="apInvoicePaymentDto.yearList" >
						  <s:if test="value == apInvoicePaymentDto.year">
						  	<option value="${value}" selected>${key}</option>
						  </s:if>
						  <s:else>
						  	<option value="${value}">${key}</option>
						  </s:else>
					    </s:iterator>
			          </select>
			        <input type="button" name="Submit3" class="style_botton2" value="Charge" /></td>
			        <th>&nbsp;</th>
			        <td>&nbsp;</td>
			      </tr>
			    </table>
			              
			<s:if test="apInvoicePaymentDto.transactionNo!=null && apInvoicePaymentDto.tenderType=='Direct Deposit'">
				<table  border="0" cellpadding="0" cellspacing="0" class="Customer_table" id="table_3">
			</s:if>
			<s:else>
				<table  border="0" cellpadding="0" cellspacing="0" class="Customer_table" id="table_3" style="display:none;">	
			</s:else>               
			     <tr>
			       <th width="150">Tender type </th>
			       <td width="350">
			       <select name="tenderType" style="width:220px;*width:232px;" id="select_c">
			         <s:iterator value="apInvoicePaymentDto.tenderTypeList" >
						  <s:if test="value == apInvoicePaymentDto.tenderType">
						  	<option value="${value}" selected>${key}</option>
						  </s:if>
						  <s:else>
						  	<option value="${value}">${key}</option>
						  </s:else>
					  </s:iterator>
			       </select></td>
			       <th width="130">Payment Type</th>
			       <td width="212">
			       		<select name="paymentType" style="width:220px;*width:232px;">
			            <s:iterator value="apInvoicePaymentDto.paymentTypeList" >
						  <s:if test="value == apInvoicePaymentDto.paymentType">
						  	<option value="${value}" selected>${key}</option>
						  </s:if>
						  <s:else>
						  	<option value="${value}">${key}</option>
						  </s:else>
					  </s:iterator>
			           </select>
			       </td>
			     </tr>
			     <tr>
			       <th valign="top">Account Name</th>
			       <td><input name="accountName" type="text" id="accountName_3" size="40"  class="NFText" value="${apInvoicePaymentDto.accountName}" ${apInvoicePaymentDto.readonly} /></td>
			       <th>&nbsp;</th>
			       <td>&nbsp;</td>
			     </tr>
			     <tr>
			       <th valign="top">Account No</th>
			       <td><input name="accountNo" type="text" id="accountNo_3" size="40"  class="NFText" value="${apInvoicePaymentDto.accountNo}" ${apInvoicePaymentDto.readonly} /></td>
			       <th>Routing No</th>
			       <td><input name="routingNo" type="text"  class="NFText" id="routingNo_3" size="40" value="${apInvoicePaymentDto.routingNo}" ${apInvoicePaymentDto.readonly} /></td>
			     </tr>
			   </table>
   
		 <br/>
	     <br/>
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
           <tr>
             <td><div class="button_box">
             <input name="Submit4" type="button" class="search_input" value="Save" onclick="doSubmit()" />
				 <input name="Submit2" type="button" class="search_input" value="Cancel" onclick="window.history.back()"/>
             </div></td>
           </tr>
         </table>
		</div>
  </div>	
</div>	
<div id="new_resource_dlg3" name="new_resource_dlg3" title="View Order"></div>
<div id="new_customer_dlg" name="new_customer_dlg" title="View Supplier"></div>
<div id="new_invoice_dlg" name="new_invoice_dlg" title="View Invoice "></div>
<div id="view_invoice"  title="View Invoice Info "></div>
<div id="view_order"  title="View Order Info "></div>
<div id="view_supplier_info"  title="View Supplier Info "></div>
</form>
</body>
</html>
