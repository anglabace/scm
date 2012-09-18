<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp"%>
<%@ page import="java.util.List,java.util.Map" %>
<%@page import="com.opensymphony.xwork2.util.ValueStack;"%>
<% String currentDate = com.genscript.gsscm.accounting.util.Tools.getCurrentTime("yyyy-MM-dd"); 
   request.setAttribute("currentDate",currentDate); 
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<link href="${global_css_url}greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<script type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script type="text/javascript" src="${global_js_url}jquery/ui/ui.datepicker.js"></script>
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<script src="${global_js_url}tools.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>
<script type="text/javascript" src="${global_js_url}AJS.js"></script>
<script type="text/javascript" src="${global_js_url}AJS_fx.js"></script>
<script type="text/javascript" src="${global_js_url}gb_scripts.js"></script>

<style type="text/css">
.symbol{}
</style>

   <script type="text/javascript">
        var GB_ROOT_DIR = "./greybox/";
    </script>

      <script>
  var oldCurrency = "";//通过order带过来的汇率
  $(document).ready(function(){
		   $('#dueDate').each(function(){
			$(this).datepicker(
			{
			dateFormat: 'yy-mm-dd',
			changeMonth: true,
			changeYear: true
			});
			});

           $("#invoiceDate").val(getCurrentTime());
			initSelect();
			initSelect2();
  });

 
 
$(function() {
 $('#new_customer_dlg').dialog({
		autoOpen: false,
		height: 400,
		width: 670,
		modal: true,
		bgiframe: true,
		buttons: {
		}
});

  $('#view_supplier').dialog({
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

 $('#new_resource_dlg3').dialog({
		autoOpen: false,
		height:400,
		width: 660,
		modal: true,
		bgiframe: true,
		buttons: {
		}
});

$('#new_resource_dlg4').dialog({
		autoOpen: false,
		height: 320,
		width: 600,
		modal: true,
		bgiframe: true,
		buttons: {
		}
});

});




function openc(str1,str2)
{
	  if (document.getElementById(str1).style.display=="none")
	  {
	    document.getElementById(str2).src="${ctx}/images/ad.gif";
	    document.getElementById(str1).style.display="block";
	  }
	  else
	  {
		  document.getElementById(str2).src="${ctx}/images/ar.gif";
	    document.getElementById(str1).style.display="none";
	  }
  
}

function clickSuppler(){
$("#orderNo").val("");
 $('#new_customer_dlg').dialog("option", "open", function() { 
        	var htmlStr = '<iframe id="iframe1" src="genscript!vendorList.action" height=400" width="630" scrolling="no" style="border:0px" frameborder="0"></iframe>';
         	$('#new_customer_dlg').html(htmlStr);
		});
		$('#new_customer_dlg').dialog('open');
}

function setCustomerValue(val,name){
  $("#vendorNo").val(val);
}
 
 
 
 function getCustNo () {
	return $('#vendorNo').val();
}

function clickorder () {
	var vendorNo = $('#vendorNo').val();
	if (vendorNo != null && vendorNo != 0) {
    $('#new_resource_dlg3').dialog("option", "open", function() { 
        	var htmlStr = '<iframe  src="genscript!purchaseOrderNoSearch.action?filter_EQI_vendorNo='+vendorNo+'" height=400" width="630" scrolling="no" style="border:0px" frameborder="0"></iframe>';
         	$('#new_resource_dlg3').html(htmlStr);
		});
		$('#new_resource_dlg3').dialog('open');
	} else {
		alert("Vendor no can not be null");
	}
}

function setOrderValue(val){
  $("#orderNo").val(val);

  getSalerName(val);
  $("#saler").val(77);
  $("#salerName").val("report");
  $("#currency").val('USD');
}


//选择order之后，带出invoiceline信息
function fillTable(){
var orderNo = $("#orderNo").val();
   $.ajax({
   url :'ap_invoice_line!list.action',
   data:{orderNo:parseInt(orderNo),currency:$("#currency").val()},
   type:'post',
   success:function(result){
    $("#list_table").html(result);
    computeMoney();
   },
   error:function(){
    alert("query invoice_line data error");
   }
   });
}
 
 //修改invoicelIne信息
function editInvoiceLine(LineNo)
{
	$('#invoice_insert').attr('action','ap_invoice!edit_invoiceline.action?dispatch=create_invoice&LineNo='+LineNo);
	$('#invoice_insert').submit();
}

//添加invoiceLine
function addInvoiceLine()
{
		var vendorNo = $("#vendorNo").val();
		if (vendorNo == '' ) {
			alert("Please Select Vendor No First");
			return ;
		}
	
        var orderNo = $("#orderNo").val();
		if (orderNo == '' ) {
			alert("Please Select Order No First");
			return ;
		}

	$('#invoice_insert').attr('action','ap_invoice!add_invoiceline.action?dispatch=create_invoice');
	$('#invoice_insert').submit();
}

//保存按钮
function submitAction()
{
    var flag = checkFormByReq('form_table');
    if(!flag)return;
    var t1 = $("#invoiceDate").val();
    var t2 = $("#dueDate").val();
    if(t1 > t2){
     alert("Invoice Date Can not be bigger than Due Date");
     return;
    }
    var arr = $("#list_table  tr");
    if(arr.length==0){
      alert("No Invoice Lines !");
      return;
    }
    $('#invoice_insert').attr('action','ap_invoice!save.action');
	$('#invoice_insert').submit();
}

//salerName没有存在表中，通过ajax带出
function getSalerName(orderNo){
   $.ajax({
     url : 'ap_invoice!getSalerName.action?orderNo='+orderNo,
     type : 'post',
     dataType : 'json',
     success : function(res){
        if(res.state == 'success'){
           $("#saleId").val(res.saleId);
           $("#saleName").val(res.name);
           $("#currency").val(res.currency); oldCurrency=res.currency;
           $("#shipping").val(res.amt == null ? '0.0' : res.amt);
           var b1 = parseFloat($("balance").val());
           $("#balance").val(res.amt+b1);
          var b2 =  parseFloat($("#grandTotal").val());
           $("#grandTotal").val(b2 +res.amt );
           $(".symbol").html(res.symbol);
             fillTable();
             
        }else{
           alert("Query order Info error!");
        }
     }
     ,error:function(){
       alert("error");
     }
   });
}

function setSupplierValue(vendorNo,vendorName){
    $("#vendorNo").val(vendorNo);
    //$("#vendorName").val(vendorName);
}

/*
//弹出modify Dialog
function modifyDialog(){
if( $("#orderNo").val() == '' ){
  alert("Please select order first");
  return ;
} 
     $('#new_resource_dlg4').dialog("option", "open", function() { 
        	var htmlStr = '<iframe  src="ap_invoice!modify.action" height="260" width="550" scrolling="no" style="border:0px" frameborder="0"></iframe>';
         	$('#new_resource_dlg4').html(htmlStr);
		});
		$('#new_resource_dlg4').dialog('open');
}
*/


//关闭modify窗口
function closeModifyDlg(){
  $("#new_resource_dlg4").dialog('close');
}
function deleteRow(){
  // var arr = document.getElementsByName('checkbox');
$("#list_table tr td :checked").each(function(){
$(this).parent().parent().remove();
});
computeMoney();
}

//四舍五入 两位精度
function fouroutfivein(value)
{
   var a = value;
   a = value.toFixed(2);
   return a;
}

//汇率相乘，修改隐藏表单域里面的值和表格中的值
function mulCurrency(f,symbol){
   var price1= document.getElementsByName("param.unitPrice");
   var amount1 = document.getElementsByName("param.amount");
   var tax = document.getElementsByName("param.tax");
   var discounts = document.getElementsByName("param.discount");
   var p = new Array();
   var a = new Array();
   var t = new Array();
   var d = new Array();
   for(var i=0;i<price1.length;i++){
     p[i] = parseFloat(price1[i].value) *f ;   p[i] = fouroutfivein(p[i]);
     a[i] = parseFloat(amount1[i].value)*f;    a[i] = fouroutfivein(a[i]);
     t[i] = parseFloat(tax[i].value)*f;        t[i] = fouroutfivein(t[i]);
     d[i] = parseFloat(discounts[i].value)*f;  d[i] = fouroutfivein(d[i]);
   }
   var price2= document.getElementsByName("param.unitPrice");
   var amount2 = document.getElementsByName("param.amount");
   var tax2 = document.getElementsByName("param.tax");
   var discount2 = document.getElementsByName("param.discount");
     for(var i=0;i<price1.length;i++){
       price2[i].value=p[i];  
       amount2[i].value=a[i]; 
	   tax2[i].value=t[i];    
	   discount2[i].value=d[i]; 
   }
   //修改innerhtml
   $("tr [name='p_price']").each(function(i){
     var html = $(this).html();
	 html = html.substring(html.indexOf("<"));
     $(this).html(symbol+p[i]+html);
   });
   $("tr [name='p_amount']").each(function(i){
     var html = $(this).html();
	 html = html.substring(html.indexOf("<"));
	 //alert("amount="+a[i]);
     $(this).html(symbol+a[i]+html);
   });
   $("tr [name='p_tax']").each(function(i){
     var html = $(this).html();
	 html = html.substring(html.indexOf("<"));
	// alert("tax="+t[i]);
     $(this).html(symbol+t[i]+html);
   });
   //shipment的值也要变
   var shipmentValue = $("#shipping").val();
   var value = parseFloat(shipmentValue) * f;
   value = fouroutfivein(value);
   $("#shipping").val(value);
   computeMoney();
}
//改变汇率
function changeCurrency(){
    var val1 = $("#currency").val();
	if(oldCurrency == ""){
	   alert("Currency can not be null!");
	   return;
	}
    var fromCurrency = oldCurrency;
    $.ajax({
     url : 'ar_invoice!getCurrencyRate.action',
     data:{fromCurrency:fromCurrency,toCurrency:val1},
     dataType:'json',
     success:function(res){
         mulCurrency(res.rate,res.symbol);
         $(".symbol").html(res.symbol);
         if(res.rate == 0){
         alert("Rate can not be transformed!");
         }
     },
     error:function(){
      alert("Query Currency Rate Error!");
     }
   });
}

</script> 
    
<style type="text/css">
<!--
.content .scm #dhtmlgoodies_tabView1 .dhtmlgoodies_aTab .General_table fieldset {
	margin: 4px;
}

.disp{
   display:none;
   margin-left:40px;
}
-->

</style>
<script language="javascript" type="text/javascript" src="js/newwindow.js"></script>
</head>
<body class="content" >

<form  class="niceform" method="post" id="invoice_insert" action="ap_invoice!saveEdit.action"  onsubmit="return checkFormByReq('invoice_insert');" >

<input name="apInvoice.creationDate" type="hidden"    value='<s:date name="apInvoice.creationDate" format="yyyy-MM-dd" />'   />
<input name="apInvoice.createdBy" type="hidden"    value='${apInvoice.createdBy}'   />
<input name="apInvoice.invoiceId" type="hidden" id="invoiceId"    value='${apInvoice.invoiceId}'   />
<div class="scm">
<div class="title_content">
  <div class="title">Invoice Information - ${apInvoice.invoiceNo}</div>
</div>
<div class="input_box">
		  <div class="content_box">

		    
		      <table width="100%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		          <td><table  border="0" cellpadding="0" cellspacing="0" class="Customer_table" id="form_table">
		            <tr>
		              <th width="150">Supplier Invoice No</th>
		              <td colspan="3"><input name="apInvoice.invoiceNo" id="invoiceNo" type="text"  class="NFText" size="35"  value='${apInvoice.invoiceId}' readonly   /></td>
		              <th width="160">Status</th>
		              <td width="355"><select name="apInvoice.status" id="status" val="${apInvoice.status }" field="AR_INVOICE_STATUS" style="width:207px;" onchange="changeStatus(this)">		                
	
	                  </select>
	                  </td>
	                </tr>
		            <tr>
<th >Supplier  No </th>
		              <td width="212">
		                <input  type="text" id="vendorNo" readonly="readonly" size="35" name="apInvoice.vendorNo" value="${apInvoice.vendorNo }" class="NFText" desc='Vendor No' checkType="integer"/>
		             </td>
		              <td width="16"><img src="images/search.gif" width="16" height="16" align="absmiddle" onclick="clickSuppler()" style=" cursor:pointer"  /></td>
		              <td width="100">
		                <input type="button" name="Submit6" class="style_botton2" value="View Supplier" onclick="viewSupplierInfo()"/>
		             </td>
		              <th>&nbsp;</th>
		              <td>&nbsp;</td>
	
	                </tr>
		            <tr>
		              <th>Purchase Order No</th>
		              <td><input name="apInvoice.orderNo"  type="text" value='${apInvoice.orderNo}'   class="NFText" id="orderNo" size="35"  value='0'  desc='Order No' checkType="integer" readonly    /></td>
		              <td>
		             
		              <img src="${ctx}/images/search.gif" style=" cursor:pointer" alt="" width="16" height="16" id="order_btn" align="absmiddle" onclick="clickorder()" />
		             
		              </td>
		              <td><input type="button" name="Submit3" class="style_botton2" value="View Order" onclick="viewPurchaseOrder()" /></td>
		              <th>Purchasing Manager</th>
		              <td><input name="salername" type="text" value="${apInvoice.salesContact}" readonly="readonly"  class="NFText" id="salerName" size="35"/></td>
	                <input type="hidden" id="saler" value="${apInvoice.salesContact}" name="apInvoice.salesContact" />
	                </tr>
		            <tr>
		              <th valign="top">Description</th>
		              <td colspan="5"><textarea   name="apInvoice.description" rows="10" cols="30" class="content_textarea2">${apInvoice.description}</textarea></td>
	                </tr>
		            <tr>
		              <th>Invoice Type </th>
		              <td colspan="3"><select name="apInvoice.invoiceType" style="width:207px;"  field="AR_INVOICE_TYPE" val="${ apInvoice.invoiceType}">
                      </select></td>
		              <th>Invoice Date</th>
		              <td><input name="apInvoice.invoiceDate" id="invoiceDate" type="text" value='${currentDate }' readonly class="NFText"   size="35"  desc=" Invoice Date " required="true" class="ui-datepicker" /></td>
	                </tr>
		            <tr>
		              <th>Payment Method</th>		            
		              <td colspan="3"><select name="apInvoice.paymentMethod" style="width:207px;" val="${ apInvoice.paymentMethod}" field="PAYMENT_METHOD" onchange="changPaymentMethod(this.val)">
	                  </select></td>
		              <th>Payment Term </th>
		              <td><select name="apInvoice.paymentTerm" style="width:207px;" val="${apInvoice.paymentTerm }" id="paymentTerm" sqlField="PAYMENTTERM">
	                  </select></td>
	                </tr>
		            <tr>
		              <th>Currency</th>
		              <td width="212">
		              <!--  
	                      <input type="text" id="currency" class="NFText"  readonly="readonly" value="${apInvoice.currency}" name="apInvoice.currency"/>
                         -->
                         <select id="currency" style="width: 207px;" val="${apInvoice.currency}" sqlField="CURRENCY" name="apInvoice.currency" onchange="changeCurrency()">
                         </select>
                          <input type="hidden" id="reason" value="${apInvoice.statusUpdReason }" name="apInvoice.statusUpdReason" />
                      </td>
		              <td colspan="2">
		              </td>
		              <th>Due Date</th>
		              <td><input name="apInvoice.exprDate" id="dueDate" type="text" value='<s:date name="apInvoice.exprDate" format="yyyy-MM-dd"  />' desc=" Due Date " required="true"   size="35"  class="NFText" id="expr_date" /></td>
	                </tr>
                    <tr>
                      
		              <th>&nbsp;</th>
		              <td colspan="3">
<input name="apInvoice.printedFlag" type="checkbox" id="printFlag"  value="${apInvoice.printedFlag }" disabled  />
Printed</td>		              <th>&nbsp;</th>
		              <td>&nbsp;</td>

	              </table></td>
	            </tr>
		        <tr>
		          <td>
                
              <div class="invoice_title" ><a href="javascript:void(0);" onclick="openc('Contact_Info','image1')"><img src="${ctx}/images/ad.gif" width="11" height="11" id="image1"/></a>&nbsp;Amounts</div>
<div id="Contact_Info" class="disp" style="display:block;">
<table  border="0" cellpadding="0" cellspacing="0" class="Customer_table">
  <tr>
    <th width="110">SubTotal</th>
    <td colspan="2"><input name="apInvoice.subTotal"  id="subtotal" value='${apInvoice.subTotal}'  type="text"  class="NFText2" value="" size="35"   />
    <span class="symbol"></span>
    </td>
    <th width="180">Grand Total</th>
    <td width="303"><input name="apInvoice.totalAmount" id="grandTotal" value='${apInvoice.totalAmount}'   type="text"   class="NFText2" value="" size="35"    />
    <span class="symbol"></span>
    </td>
  </tr>
 
  <tr>
    <th>Tax</th>
    <td colspan="2"><input name="apInvoice.tax" id="tax" value='${apInvoice.tax}'  type="text"   class="NFText2" size="35"    />
    <span class="symbol"></span>
    </td>
    <th>Discount</th>
    <td><input name="apInvoice.discount"  type="text"  id="discount" value='${apInvoice.discount}' class="NFText2" size="35"      />
    <span class="symbol"></span>
    </td>
    </tr>
      <tr>
    <th>Shipping &amp; Handling</th>
    <td colspan="2"><input name="apInvoice.shipping"  id="shipping" value='${apInvoice.shipping}'  onblur="changeMoney(this.value)"  type="text" class="NFText2" size="35"   checkType='float' desc='Shipping & Handling' required='true'   />
    <span class="symbol"></span>
    </td>
    <th>&nbsp;</th>
    <td>&nbsp;</td>
    </tr>
      <tr>
    <th>Paid Amount</th>
    <td width="225"><input name="PaidAmount" id="paidAmount" value="<%=request.getParameter("paidAmt") %>" type="text" class="NFText2" size="35"        readonly />
    <span class="symbol"></span>
    </td>
    <td width="100">
    <!-- 
    <input type="button" name="Submit5" class="style_botton2" value="View History" onclick="viewHistory()" />
    -->
    </td>
    <th>Balance</th>
    <td><input name="apInvoice.balance" id="balance" type="text" class="NFText2" size="35"  value='0'       />
    <span class="symbol"></span>
    </td>
    </tr>
</table>

</div>
                  
                  </td>
	            </tr>
	          </table>
		   
		</div>
  </div>	

<div id="dhtmlgoodies_tabView1">
  <div class="dhtmlgoodies_aTab">
    <table width="955" border="0" cellpadding="0" cellspacing="0"  class="list_table">
      <tr>
      <th width="46"><div align="left"><input name="checkbox2" type="checkbox"  onclick="cc(this)" /><a onclick="deleteRow()" title="Delete Invoice Line" rel="gb_page_center[600,  180]"><img src="${ctx}/images/file_delete.gif" alt="Delete" width="16" height="16" border="0" /></a></div></th>
        <th width="65">Line No</th>
        <th width="88">Item No</th>
        <th width="121">Supplier Catalog No</th>
        <th width="131">Name</th>
        <th width="75">Qty</th>
        <th width="74">UOM</th>
        <th width="64">Size</th>
        <th width="81">Price</th>
        <th width="81">Amount</th>
        <th>Tax</th>
      </tr>
    </table>
    
<div id="frame12" style="display:none;" class="hidlayer1">
<iframe id="hidkuan" name="hidkuan" src="" width="668" height="425" frameborder="0"  allowtransparency="true"></iframe>
</div>

    <div class="frame_box" style="height:80px; ">
      <table width="955" border="0" cellpadding="0" cellspacing="0" id="list_table" class="list_table">
   
       <c:set var="isEmpty" value="${paramEL.itemNo == null}"></c:set>
     <c:if test="${isEmpty == false}">
     <c:set var="invoiceLineIds" value="${paramEL.invoiceLineIds}"></c:set>
     <c:set var="invoiceIds" value="${paramEL.invoiceIds}"></c:set>
      <c:set var="itemNo" value="${paramEL.itemNo}"></c:set>
      <c:set var="catalogNo" value="${paramEL.catalogNo}"></c:set>
      <c:set var="name" value="${paramEL.name}"></c:set>
      <c:set var="qty" value="${paramEL.qty}"></c:set>
      <c:set var="qtyUom" value="${paramEL.qtyUom}"></c:set>
      <c:set var="unitPrice" value="${paramEL.unitPrice}"></c:set>
      <c:set var="amount" value="${paramEL.amount}"></c:set>
      <c:set var="tax" value="${paramEL.tax}"></c:set>
      <c:set var="size" value="${paramEL.size}"></c:set>
      <c:set var="lineNo" value="${paramEL.lineNo}"></c:set>
      <c:set var="discount" value="${paramEL.discount}"></c:set>
      <c:set var="creationDate" value="${paramEL.creationDate}"></c:set>
      <c:set var="createdBy" value="${paramEL.createdBy}"></c:set>
         <c:set var="i" value="0"></c:set>
        <c:forEach items="${paramEL.itemNo}">  
        <tr>
	         <td width="46"><input type="checkbox" value="checkbox"  /></td>
	         <td width="65" ><a style="cursor:pointer" onclick="editInvoiceLine(${lineNo[i] })">${lineNo[i] }</a><input type='hidden' name='param.lineNo' value='${lineNo[i] }'></td>
	         <td width="88">${itemNo[i] }<input type='hidden' name='param.itemNo' value='${itemNo[i] }' /></td>
	         <td width="121">${catalogNo[i] }<input type='hidden' name='param.catalogNo' value='${catalogNo[i] }' /> </td>
	         <td width="131">  ${name[i] }<input type='hidden' name='param.name' value='${name[i] }' /></td>
	         <td width="75">${qty[i] }<input type='hidden' name='param.qty' value='${qty[i] }' /></td>
	         <td width="74">${ qtyUom[i]}<input type='hidden' name='param.qtyUom' value='${ qtyUom[i]}' /></td>
	         <td width="64">${size[i] }<input type='hidden' name='param.size' value='${size[i] }' /></td>
	         <td width="81" name="p_price">${unitPrice[i] }<input type='hidden' name='param.unitPrice' value='${unitPrice[i] }' /></td>
	         <td width="81" name="p_amount">${amount[i] }<input type='hidden' name='param.amount' value='${amount[i] }' /></td>
	         <td name="p_tax">${tax[i] }   <input type='hidden' name='param.tax' value='${ tax[i]}' />  
	            <input type="hidden" name="param.discount" value="${discount[i] }" />
				<input type="hidden" name="param.creationDate" value="${creationDate[i] }" />
				<input type="hidden" name="param.createdBy" value="${createdBy[i] }" />
				<input type="hidden" name="param.modifyDate" value="" />
                <input type="hidden" name="param.modifiedBy" value="" />
			</td>
        </tr>
         <c:set var="i" value="${i+1}"></c:set>
        </c:forEach>
        </c:if>
      </table>
    </div>
    <div align="center" style="padding:10px;">
      <input name="button_add" type="button" class="style_botton" value="New" onclick="addInvoiceLine();"/>
    </div>
  </div>
</div>
<script type="text/javascript">
initTabs('dhtmlgoodies_tabView1',Array('Invoice Line'),0,998,160);

</script>
<div class="button_box">
      <input type="button" name="Submit123"  value="Save" class="search_input" id="save1" onclick="submitAction()"  />
      <input type="button" name="Submit124" value="Cancel" class="search_input" onclick="window.location.href='ap_invoice!input.action'" id="cancel1"/>
</div>
<div/>
</form>	

<div id="new_customer_dlg" title=" Vendor List "></div>
<div id="new_resource_dlg3" title=" Order List "></div>
<div id="new_resource_dlg4" title=" Modify Invoice Currency "></div>
<div id="view_order" title=" View Order "></div>
<div id="view_supplier" title=" View Supplier "></div>
<script type="text/javascript">
$(document).ready(function(){
computeMoney();
});

function computeMoney(){
  var amounts = document.getElementsByName("param.amount");
  var taxs = document.getElementsByName("param.tax");
  var discounts = document.getElementsByName("param.discount");
  var amount = sum(amounts);
  var tax = sum(taxs);
  var discount = sum(discounts); 
  if($("#shipping").val() == ''){
  $("#shipping").val('0.0');
  }
  var shipping = parseFloat($("#shipping").val());
  var grandTotal = amount + tax - discount + shipping ;
  grandTotal = grandTotal.toFixed(2);
  amount = fouroutfivein(amount);
   tax = fouroutfivein(tax);
  var paidAmount =0;
  var balance = grandTotal - paidAmount;
  balance = balance.toFixed(2);
  $("#discount").attr("readonly","readonly");
  $("#subtotal").attr("readonly","readonly");
  $("#grandTotal").attr("readonly","readonly");
  $("#paidAmount").attr("readonly","readonly");
  $("#balance").attr("readonly","readonly");
  $("#tax").attr("readonly","readonly");
  $("#tax").val(tax);
  $("#discount").val(discount);
  $("#subtotal").val(amount);
  $("#grandTotal").val(grandTotal);
  $("#paidAmount").val(paidAmount);
  $("#balance").val(balance);
  }

function changeMoney(val){
if(!isFloat(val)){
alert("The Shipping & Handling money is invalid !");
return ;
}
computeMoney();
}

//add array
function sum(arr){
  var result = 0;
  for(var i=0;i<arr.length;i++){
    var a = parseFloat(arr[i].value);
    result += a;
  }
  return result;
}

</script>
</body>
</html>
