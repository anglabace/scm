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

    <script>
     
    $(function() {
		$('#dlg').dialog({
			autoOpen: false,
			height: 300,
			width: 650,
			modal: true,
			bgiframe: true,
			buttons: {}
		});	     
     });
	$(function() {
		$('#new_resource_dlg').dialog({
			autoOpen: false,
			height: 420,
			width: 850,
			modal: true,
			bgiframe: true,
			buttons: {}
		});

		$('#new_customer_dlg').dialog({
			autoOpen: false,
			height: 420,
			width: 620,
			modal: true,
			bgiframe: true,
			buttons: {}
		});	

		$('#new_allocation_dlg').dialog({
			autoOpen: false,
			height: 420,
			width: 850,
			modal: true,
			bgiframe: true,
			buttons: {}
		});	

		$('#select_order_dlg').dialog({
			autoOpen: false,
			height: 420,
			width: 620,
			modal: true,
			bgiframe: true,
			buttons: {}
		});

		$('#select_invoice_dlg').dialog({
			autoOpen: false,
			height: 420,
			width: 620,
			modal: true,
			bgiframe: true,
			buttons: {}
		});

		$('#view_order_dlg').dialog({
			autoOpen: false,
			height: 420,
			width: 620,
			modal: true,
			bgiframe: true,
			buttons: {}
		});	

		$('#view_invoice_dlg').dialog({
			autoOpen: false,
			height: 420,
			width: 620,
			modal: true,
			bgiframe: true,
			buttons: {}
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

	function apply(ele) {
		//计算余额
		$.ajax({
		      type:'get',
		      dataType:'json',
		      url :'ar_invoice_payment!calculateApplyBalance.action',
		      data:{
		      	currency: $('#currency').val(),
		      	balance: $('#balance').val(),
		      	overpaymentAmount:$('#overpaymentAmount').val()
		      },
		      success:function(obj){
				 if(obj.success == 'true'){
					  $('#balance').val(obj.balance);
			    	  $('#overpaymentAmount').val(obj.overpaymentAmount);
					  //
			  		  document.getElementById('overpaymentAmount').readOnly = true;
			  		  document.getElementById('applyBtn').disabled = true;
				  }else{
					  alert('Overpayment amount cannot more than balance!');
				  }
			  },
		      error:function(e){alert('Apply failure!');}
	    });
	}

	function doSubmit() {
		if ($('#custNo').val() == '' || $('#custNo').val() == 'undefined' || $('#custNo').val() == '0') {
			alert(lang.transaction3);
			return;
		}else if ($('#transactionDate').val() == '' || $('#transactionDate').val() == 'undefined') {
			alert(lang.transaction4);
			return;
		}else if ($('#description').val() == '' || $('#description').val() == 'undefined' || $('#custNo').val() == '0') {
			alert(lang.transaction5);
			return;
		}else if ($('#amount').val() == '' || $('#amount').val() == 'undefined') {
			alert(lang.transaction6);
			return;
		}else if ($('#transactionFee').val() == '' || $('#transactionFee').val() == 'undefined' || $('#custNo').val() == '0') {
			alert(transaction7);
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

		document.getElementById('form1').action = 'ar_invoice_payment!saveOrUpdate.action';
		$('#form1').ajaxSubmit(function(){
			$(this).ajaxSubmit({
				　　success:function(p){
						alert('Record Transaction Success!');
						document.getElementById('form1').action = 'ar_invoice_payment!input.action';	
						document.getElementById('form1').submit();
					} 
				});
			return false;
		});
	}
	
	function viewAllocation(){
	
		var paidAmt = parseFloat($("#applyAmount").val());
		if(paidAmt == 0){
			alert("No Allocation History");
		    return;
		}
		
		$('#new_allocation_dlg').dialog("option", 'open', function() { 
        	var htmlStr = '<iframe src="ar_invoice_payment!viewAllocationHistory.action?arInvoicePaymentDto.transactionId=${arInvoicePaymentDto.transactionId}" height="350" width="820" scrolling="no" style="border:0px" frameborder="0"></iframe>';
         	$('#new_allocation_dlg').html(htmlStr);
		});
		$('#new_allocation_dlg').dialog('open');
	}

	function viewOrder_bck(){
		document.getElementById('form1').action = 'ar_invoice_payment!viewOrder.action';	
		document.getElementById('form1').submit();	
	}

	function selectOrder(disabled) {
		if(disabled)return;
		if($('#custNo').val() == '0' || $('#custNo').val() == ''){
			alert('Please input the Customer first');
			return;
		}
	    $('#select_order_dlg').dialog("option", "open", function() { 
        	var htmlStr = '<iframe id="orderIframe" src="genscript!orderlist.action?filter_EQS_custNo='+$('#custNo').val()+'" height=350" width="600" scrolling="no" style="border:0px" frameborder="0"></iframe>';
         	$('#select_order_dlg').html(htmlStr);
		});
		$('#select_order_dlg').dialog('open');
	}

	function setOrderValue(orderNo){
		$('#orderNo').html('<option value="'+orderNo+'">'+orderNo+'</option>');
	}
	
	function viewInvoice_bck(){
		document.getElementById('form1').action = 'ar_invoice_payment!viewInvoice.action';	
		document.getElementById('form1').submit();	
	}
    
	function selectInvoice(disabled){
		if(disabled)return;
		var orderNo = $('#orderNo').val();
		if(!orderNo || orderNo == ''){
			alert(lang.transaction15);
			return;
		}
		$('#select_invoice_dlg').dialog("option", "open", function() { 
       	var htmlStr = '<iframe id="invoiceIframe" src="genscript!invoicelist.action?filter_EQI_orderNo='+orderNo+'&filter_EQS_status=In Progress&filter_EQS_currency=${arInvoicePaymentDto.currency}" height=350" width="620" scrolling="no" style="border:0px" frameborder="0"></iframe>';
        	$('#select_invoice_dlg').html(htmlStr);
		});
		$('#select_invoice_dlg').dialog('open');
		//setInvoiceValue();
	}

	function setInvoiceValue(invoiceId,invoiceNo,currency,applyAmount){
		if(!invoiceId)return;
		if($('#currency').val() != currency){
			alert(lang.transaction16);
			return;
		}
		
		$('#invoiceId').html('<option value="'+invoiceId+'">'+invoiceNo+'</option>');	
		if(parseInt($('#amount').val()) < parseInt(applyAmount)){
			$('#applyAmount').val($('#amount').val());
		}else{
			$('#applyAmount').val(applyAmount);
		}
		$('#currency').val(currency);

		doFeeChange();
	}

	function selectCustomer(disabled){
		if(disabled)return;
		$('#new_customer_dlg').dialog("option", "open", function() { 
        	var htmlStr = '<iframe id="iframe1" src="genscript!customerlist.action" height=350" width="600" scrolling="no" style="border:0px" frameborder="0"></iframe>';
         	$('#new_customer_dlg').html(htmlStr);
		});
		$('#new_customer_dlg').dialog('open');
	}

	function setCustomerValue(custNo,custName){
		$('#custNo').val(custNo);
		$('#custName').val(custName);
	}

	function doFeeChange(ele){
		//交验数值格式
		$.ajax({
		      type:'get',
		      dataType:'json',
		      url :'ar_invoice_payment!calculateBalance.action',
		      data:{
		      	currency: $('#currency').val(),
		      	amount: $('#amount').val(),
		      	applyAmount:$('#applyAmount').val(),
		      	transactionFee:$('#transactionFee').val(),
		      	overpaymentAmount:$('#overpaymentAmount').val(),
		      	isApplied:(document.getElementById('applyBtn').disabled)?'true':'false'
		      },
		      success:function(obj){
		    	  $('#amount').val(obj.amount);
		    	  $('#applyAmount').val(obj.applyAmount);
		    	  $('#transactionFee').val(obj.transactionFee);
		    	  $('#balance').val(obj.balance);
		    	  $('#overpaymentAmount').val(obj.overpaymentAmount);
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
	
	$(document).ready(function(){
	var name = "${arInvoicePaymentDto.firstName} ${arInvoicePaymentDto.lastName}";
	name = name.replace(/&nbsp/g," ");
	  $("#custName").val(name);
	});

</script>
<!--    <link href="greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />-->
</head>

<body class="content" style="background-image:none;">
<form name="form1" id="form1" action="ar_invoice_payment!saveOrUpdate.action" method="post">
<input name="transactionId" id="transactionId" type="hidden" value="${arInvoicePaymentDto.transactionId}"/>
<input name="forwardUrl" id="forwardUrl" type="hidden" value="allocation_list"/>
<div class="scm">
<div class="title_content">
  <div class="title">${arInvoicePaymentDto.transactionTitle}</div>
</div>
        <div class="input_box">
		  <div class="content_box">
	        <table  border="0" cellpadding="0" cellspacing="0" class="Customer_table">
      <tr>
        <th width="150">Transaction No </th>
        <td width="400">
        	<s:if test="arInvoicePaymentDto.transactionNo == null">
        		<input id="transactionNo" name="transactionNo" type="text"  class="NFText" value="" size="40" readonly />
	        </s:if>
	        <s:else>
	        	<input id="transactionNo" name="transactionNo" type="text"  class="NFText" value="${arInvoicePaymentDto.transactionNo}" size="40" ${arInvoicePaymentDto.transactionNo==null?'':'readonly'}/>
	        </s:else>
        </td>
        <th width="130">Status</th>
        <td width="212">
	        <s:if test="arInvoicePaymentDto.transactionNo == null">
	        	<select name="status" style="width:220px;*width:232px;">
				<option selected="selected">Draft</option>
			</select>
	        </s:if>
	        <s:else>
	        	<select name="status" style="width:220px;*width:232px;">
	        	<s:iterator value="arInvoicePaymentDto.statusList" >
					<s:if test="value == arInvoicePaymentDto.status">
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
        <th valign="top">Customer</th>
        <td>
        	<input name="custNo" id="custNo" type="hidden" value="${arInvoicePaymentDto.custNo}"/>
        	<input name="custName" id="custName" type="text" class="NFText" value="" size="40" readonly/>
        	<img src="images/search.gif" alt="" width="16" height="16" align="absmiddle" onclick="selectCustomer(${arInvoicePaymentDto.transactionNo==null || arInvoicePaymentDto.status=='Draft'?false:true})" />
        	<input type="button" name="Submit6" class="style_botton2" value="View Customer" onclick="viewCustomer()" />
        </td>
        <th>Transaction Type</th>
        <td>
	        <select name="transactionType" style="width:220px;*width:232px;" id="select_st" >
			<s:iterator value="arInvoicePaymentDto.transactionTypeList" >
				<s:if test="value == arInvoicePaymentDto.transactionType">
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
        <s:if test="arInvoicePaymentDto.transactionNo == null">
        	<input name="transactionDate" type="text" class="NFText" id="transactionDate" readonly value="${arInvoicePaymentDto.sysDate}" size="40"/>
        </s:if>
        <s:else>
        	<input name="transactionDate" type="text" class="NFText" id="transactionDate" readonly value="<s:date name="arInvoicePaymentDto.transactionDate" format="yyyy-MM-dd" />" size="40"/>
        </s:else>  
        </td>
        <th>Transaction Entered By</th>
        <td>
        <s:if test="arInvoicePaymentDto.transactionNo == null">
        	<input name="createdBy" type="hidden" id="createdBy" value="${arInvoicePaymentDto.userId}" />
        	<input name="createdByName" type="text"  class="NFText" id="createdByName" size="40" value="${arInvoicePaymentDto.userName}" readonly />
        </s:if>
        <s:else>
        	<input name="createdBy" type="hidden" id="createdBy" value="${arInvoicePaymentDto.createdBy}" />
        	<input name="createdByName" type="text"  class="NFText" id="createdByName" size="40" value="${arInvoicePaymentDto.createdByName}" readonly />
        </s:else>
        </td>
        </tr>
		<tr><th>Description</th><td colspan="5"><textarea id="description" name="description" rows="10" cols="30" class="content_textarea2" style="width:350px;">${arInvoicePaymentDto.description}</textarea></td></tr>
    </table>
    <div class="invoice_title">Amounts</div>
		
	          <table  border="0" cellpadding="0" cellspacing="0" class="Customer_table">
                <tr>
                  <th width="150">Amount</th>
                  <td width="400">
	                  <s:if test="arInvoicePaymentDto.transactionNo == null">
			        	  <input name="amount" id="amount" type="text"  class="NFText2" value="0.000" size="40" onchange="doFeeChange(this)"/>
			          </s:if>
			          <s:elseif test="arInvoicePaymentDto.status== 'Draft'">
			        	  <input name="amount" id="amount" type="text"  class="NFText2" value="${arInvoicePaymentDto.amount}" size="40" onchange="doFeeChange(this)" />
			          </s:elseif>	
			          <s:else>
			          	<input name="amount" id="amount" type="text"  class="NFText2" value="${arInvoicePaymentDto.amount}" size="40" onchange="doFeeChange(this)" readonly />
			          </s:else>
			          <span class="symbol">$</span>
                  </td>
                  <th width="130">Transaction Fee</th>
                  <td>
                  	<s:if test="arInvoicePaymentDto.transactionNo == null">
			        	  <input name="transactionFee" type="text"  class="NFText2" value="0.000" size="40" id="transactionFee" onchange="doFeeChange(this)" />
			          </s:if>
			          <s:elseif test="arInvoicePaymentDto.status== 'Draft'">
			          	<input name="transactionFee" type="text"  class="NFText2" value="${arInvoicePaymentDto.transactionFee}" size="40" id="transactionFee" onchange="doFeeChange(this)" />
			          </s:elseif>
			          <s:else>
			        	  <input name="transactionFee" type="text"  class="NFText2" value="${arInvoicePaymentDto.transactionFee}" size="40" id="transactionFee" onchange="doFeeChange(this)" readonly />
			        	  <span class="symbol">$</span>
			          </s:else>		
                  </td>
                </tr>
                <tr>
                  <th valign="top">Applied Amount</th>
                  <td width="400">
                  <s:if test="arInvoicePaymentDto.transactionNo == null">
		        	  <input name="applyAmount" type="text"  class="NFText2" value="0.000" size="40" readonly id="applyAmount"/>
		          </s:if>
		          <s:else>
		        	  <input name="applyAmount" type="text"  class="NFText2" value="${arInvoicePaymentDto.applyAmount==null?'0.000':arInvoicePaymentDto.applyAmount}" size="40" readonly id="applyAmount"/>
		          </s:else>	
		          <span class="symbol">$</span>
		          <input name="viewAllocationBtn" id="viewAllocationBtn" type="button" class="style_botton3" value="View Allocation History" onclick="viewAllocation()" ${arInvoicePaymentDto.transactionNo==null?'disabled':''} />
                  </td>
                  <th>Balance</th>
                  <td>
                  	<input name="balance" type="text"  class="NFText2" value="${arInvoicePaymentDto.balance==null?'0.000':arInvoicePaymentDto.balance}" size="40" id="balance" readonly />
                  	<span class="symbol">$</span>
                  </td>
                </tr>
                <tr>
                  <th valign="top">Currency</th>
                  <td>
                  	<select name="currency" id="currency" style="width:220px;*width:232px;" onchange="getCurrencyInfo()">
					<s:iterator value="arInvoicePaymentDto.currencyList" >
						<s:if test="(arInvoicePaymentDto.currency==null && value=='USD') || (arInvoicePaymentDto.currency == value)">
							<option value="${value}" selected>${key}</option>
						</s:if>
						<s:else>
							<option value="${value}">${key}</option>
						</s:else>
					</s:iterator>	
                    </select>
                  </td>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <th valign="top">&nbsp;</th>
                  <td >&nbsp;</td>
                  <th>Overpayment Amount</th>
                  <td>
                  	<input type="text" size="40" class="NFText2" id="overpaymentAmount" name="overpaymentAmount" value="0.000"  onchange="return;doFeeChange(this)" ${arInvoicePaymentDto.status=='In Progress'?'':'readonly'} />
                  	<span class="symbol">$</span>
                  	<input type="button" name="applyBtn" id="applyBtn" class="style_botton" value="Apply" onclick="apply(this)" ${arInvoicePaymentDto.status=='In Progress'?'':'disabled'} />
                  </td>
                </tr>
              </table>
	  	    <div class="invoice_title">Order Reference</div>
		
<table  border="0" cellpadding="0" cellspacing="0" class="Customer_table">
                <tr>
                  <th width="150">Order No</th>
                  <td width="400">
                  <select name="orderNo" id="orderNo" style="width:220px;*width:232px;" ${arInvoicePaymentDto.status=='In Progress' && arInvoicePaymentDto.invoiceNo==null?'':'disabled'}>
                  <s:iterator value="arInvoicePaymentDto.invoiceList" >
                  	<option value="${orderNo}">${orderNo}</option>
                  </s:iterator>	
                  </select>
                  <img src="images/search.gif" alt="" width="16" height="16" align="absmiddle" onclick="selectOrder(${arInvoicePaymentDto.status=='In Progress' && arInvoicePaymentDto.invoiceNo==null?false:true})" />
                  <input type="button" name="Submit" class="style_botton2" value="View Order"  onclick="viewOrder()" />
                  </td>
                  <th width="130">Invoice No</th>
                  <td width="212">
                  <select name="invoiceId" id="invoiceId" style="width:220px;*width:232px;" ${arInvoicePaymentDto.status=='In Progress' && arInvoicePaymentDto.invoiceNo==null?'':'disabled'}>
                  <s:iterator value="arInvoicePaymentDto.invoiceList" >
                  	<option value="${invoiceId}">${invoiceNo}</option>
                  </s:iterator>	  
                  </select></td>
                  <td>
                  	<img src="images/search.gif" alt="" width="16" height="16" align="absmiddle" onclick="selectInvoice(${arInvoicePaymentDto.status=='In Progress' && arInvoicePaymentDto.invoiceNo==null?false:true})" />
                  	<input type="button" name="Submit5" class="style_botton2" value="View Invoice" onclick="viewInvoice()" />
                  </td>
                </tr>
              </table>
			
			<div class="invoice_title">Payment Account</div>
			<s:if test="arInvoicePaymentDto.transactionNo==null || arInvoicePaymentDto.tenderType=='Check'">
				<table  border="0" cellpadding="0" cellspacing="0" class="Customer_table" id="table_1">	
			</s:if>
			<s:else>
				<table  border="0" cellpadding="0" cellspacing="0" class="Customer_table" id="table_1" style="display:none;">	
			</s:else>
			    	<tr>
			          <th width="150">Tender Type</th>
			          <td width="400">
			          <select name="tenderType" style="width:220px;*width:232px;" id="select_a">
			          <s:iterator value="arInvoicePaymentDto.tenderTypeList" >
						  <s:if test="value == arInvoicePaymentDto.tenderType">
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
			          <s:iterator value="arInvoicePaymentDto.paymentTypeList" >
						  <s:if test="value == arInvoicePaymentDto.paymentType">
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
			          <td><input name="accountName" type="text" id="accountName_1" size="40"  class="NFText" value="${arInvoicePaymentDto.accountName}" ${arInvoicePaymentDto.readonly} /></td>
			          <th>Account No</th>
			          <td><input name="accountNo" type="text"  class="NFText" id="accountNo_1" size="40" value="${arInvoicePaymentDto.accountNo}" ${arInvoicePaymentDto.readonly} /></td>
			        </tr>
			        <tr>
			          <th valign="top">Routing No</th>
			          <td><input name="routingNo" type="text" id="routingNo_1" size="40"  class="NFText" value="${arInvoicePaymentDto.routingNo}" ${arInvoicePaymentDto.readonly} /></td>
			          <th>Check No</th>
			          <td><input name="chkNo" type="text"  class="NFText" id="chkNo" size="40" value="${arInvoicePaymentDto.chkNo}" ${arInvoicePaymentDto.readonly} /></td>
			        </tr>
				</table>
			
			<s:if test="arInvoicePaymentDto.transactionNo!=null && arInvoicePaymentDto.tenderType=='Credit Card'">
				<table  border="0" cellpadding="0" cellspacing="0" class="Customer_table" id="table_2">	
			</s:if>
			<s:else>
				<table  border="0" cellpadding="0" cellspacing="0" class="Customer_table" id="table_2" style="display:none;">	
			</s:else>              
			      <tr>
			        <th width="150">Tender type </th>
			        <td width="400">
			        <select name="tenderType" style="width:220px;*width:232px;" id="select_b">
			        <s:iterator value="arInvoicePaymentDto.tenderTypeList" >
						  <s:if test="value == arInvoicePaymentDto.tenderType">
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
			            <s:iterator value="arInvoicePaymentDto.paymentTypeList" >
						  <s:if test="value == arInvoicePaymentDto.paymentType">
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
			        <td><input name="accountName" id="accountName_2" type="text" size="40" class="NFText" value="${arInvoicePaymentDto.accountName}" ${arInvoicePaymentDto.readonly} /></td>
			        <th>&nbsp;</th>
			        <td>&nbsp;</td>
			      </tr>
			      <tr>
			        <th valign="top">Credit Card Type</th>
			        <td>
			        <select name="ccType" style="width:220px;*width:232px;" id="ccType">
			        <s:iterator value="arInvoicePaymentDto.cardList" >
					  <s:if test="value == arInvoicePaymentDto.ccType">
					  	<option value="${value}" selected>${key}</option>
					  </s:if>
					  <s:else>
					  	<option value="${value}">${key}</option>
					  </s:else>
				    </s:iterator>
			        </select>
			        </td>
			        <th>Name on the Card</th>
			        <td><input name="ccCardHolder" type="text"  class="NFText" id="ccCardHolder" size="40" value="${arInvoicePaymentDto.ccCardHolder}" ${arInvoicePaymentDto.readonly} /></td>
			      </tr>
			      <tr>
			        <th valign="top">Credit Card Numeber</th>
			        <td><input name="accountNo" type="text" id="accountNo_2" size="40" class="NFText" value="${arInvoicePaymentDto.accountNo}" ${arInvoicePaymentDto.readonly} /></td>
			        <th>CVC</th>
			        <td><input name="ccCvc" type="text"  class="NFText" id="ccCvc" size="40" value="${arInvoicePaymentDto.ccCvc}" ${arInvoicePaymentDto.readonly} /></td>
			      </tr>
			      <tr>
			        <th valign="top">Expiration Date</th>
			        <td>
			          <select name="ccExpirationMonth" id="ccExpirationMonth" style="width:106px;*width:112px;">
			          <s:iterator value="arInvoicePaymentDto.monthList" >
						  <s:if test="value == arInvoicePaymentDto.month">
						  	<option value="${value}" selected>${key}</option>
						  </s:if>
						  <s:else>
						  	<option value="${value}">${key}</option>
						  </s:else>
					    </s:iterator>  
			          </select>
			          <select name="ccExpirationYear" id="ccExpirationYear" style="width:106px;*width:113px;">
			          <s:iterator value="arInvoicePaymentDto.yearList" >
						  <s:if test="value == arInvoicePaymentDto.year">
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
			              
			<s:if test="arInvoicePaymentDto.transactionNo!=null && arInvoicePaymentDto.tenderType=='Direct Deposit'">
				<table  border="0" cellpadding="0" cellspacing="0" class="Customer_table" id="table_3">
			</s:if>
			<s:else>
				<table  border="0" cellpadding="0" cellspacing="0" class="Customer_table" id="table_3" style="display:none;">	
			</s:else>               
			     <tr>
			       <th width="150">Tender type </th>
			       <td width="400">
			       <select name="tenderType" style="width:220px;*width:232px;" id="select_c">
			       <s:iterator value="arInvoicePaymentDto.tenderTypeList" >
					  <s:if test="value == arInvoicePaymentDto.tenderType">
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
			            <s:iterator value="arInvoicePaymentDto.paymentTypeList" >
						  <s:if test="value == arInvoicePaymentDto.paymentType">
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
			       <td><input name="accountName" type="text" id="accountName_3" size="40"  class="NFText" value="${arInvoicePaymentDto.accountName}" ${arInvoicePaymentDto.readonly} /></td>
			       <th>&nbsp;</th>
			       <td>&nbsp;</td>
			     </tr>
			     <tr>
			       <th valign="top">Account No</th>
			       <td><input name="accountNo" type="text" id="accountNo_3" size="40"  class="NFText" value="${arInvoicePaymentDto.accountNo}" ${arInvoicePaymentDto.readonly} /></td>
			       <th>Routing No</th>
			       <td><input name="routingNo" type="text"  class="NFText" id="routingNo_3" size="40" value="${arInvoicePaymentDto.routingNo}" ${arInvoicePaymentDto.readonly} /></td>
			     </tr>
			   </table>
   
		 <br/>
	     <br/>
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
           <tr>
             <td>
             <div class="button_box" id="button_box1">
               <input type="button" name="Submit" value="Authorize" class="search_input" onclick="Authorize()" /> 
               <input type="button" name="Submit16" value="Unauthorize" class="search_input" onclick="openUnauthorize()" />
             </div>
            
             </td>
           </tr>
         </table>
		</div>
  </div>	
</div>
<div id="dlg" title="Unauthorize Transaction"></div>	
<div id="new_resource_dlg" name="new_resource_dlg"></div>
<div id="new_customer_dlg" name="new_customer_dlg" title="Search Customer"></div>
<div id="new_allocation_dlg" name="new_allocation_dlg" title="View Allocation History"></div>
<div id="select_order_dlg" name="select_order_dlg" title="Search Order"></div>
<div id="select_invoice_dlg" name="select_invoice_dlg" title="Search Invoice"></div>
<div id="view_order_dlg" name="view_order_dlg" title="View Order"></div>
<div id="view_invoice_dlg" name="view_invoice_dlg" title="View Invoice"></div>
</form>
</body>
</html>
<script>

function Authorize()
{
   var paymentId="${arInvoicePaymentDto.transactionId}";
   
    
   
   var paramter="paymentIds="+paymentId;
   paramter=paramter+"&from=list";
    $.ajax({
	      type:'post',
	      url :'ar_transaction!Authorize.action',
	      data:paramter,
	      success:function(data){ alert("success!");window.location.href='ar_transaction!GetList.action'; },
	      error:function(data){ alert("error!");window.location.href='ar_transaction!GetList.action';}
        }); 
   
   
}	


function Unauthorize()
{
	var paymentId="${arInvoicePaymentDto.transactionId}";	
	var reason=$("#reason").val();
   
    
   
   
   
   var paramter="paymentIds="+paymentId;
   paramter=paramter+"&reason="+reason;
   paramter=paramter+"&from=list";
     
    $.ajax({
	      type:'post',
	      url :'ar_transaction!Unauthorize.action',
	      data:paramter,
	      success:function(data){ alert("success!");window.location.href='ar_transaction!GetList.action';},
	      error:function(data){ alert("error!");window.location.href='ar_transaction!GetList.action';}
        }); 
   
}

function openUnauthorize(){
	   $('#dlg')
				.dialog(
						"option",
						"open",
						function() {
							var htmlStr = '<iframe  src="ar_transaction!unauthorizeTransaction.action" height="200" width="600" scrolling="no" style="border:0px" frameborder="0"></iframe>';
							$('#dlg').html(htmlStr);
						});
		$('#dlg').dialog('open');
	}

function closeDlg(){
   $("#dlg").dialog("close");
}



</script>
