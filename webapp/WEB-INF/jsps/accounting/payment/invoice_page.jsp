<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp"%>
<%@page import="com.opensymphony.xwork2.util.ValueStack"%>
 
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

   <script type="text/javascript">
        var GB_ROOT_DIR = "./greybox/";
    </script>

      <script>
  
function openc(str1,str2)
{
	  if (document.getElementById(str1).style.display=="none")
	  {
	    document.getElementById(str2).src="images/ad.gif";
	    document.getElementById(str1).style.display="block";
	  }
	  else
	  {
		  document.getElementById(str2).src="images/ar.gif";
	    document.getElementById(str1).style.display="none";
	  }
  
}
 


  $(document).ready(function(){
   $('.ui-datepicker').each(function(){
$(this).datepicker(
{
dateFormat: 'yy-mm-dd',
changeMonth: true,
changeYear: true
});
});

var status = "<%=request.getParameter("status")%>";
   	$("#status").val(status);
   	
  });



function deleteRow(){
$("#list_table tr td :checked").each(function(){

$(this).parent().parent().empty();
});
}


 function checkInvoiceNo(val){
     $.ajax({
        url : 'ar_invoice!checkInvoiceNo.action',
        data: {invoiceNo:val},
        method:'get', 
        dataType:'json',
        success: function(res){
         if(res.status=='true'){
           alert("The invoiceNo is already exists!");
         }
        },
        error:function(){
          alert("error");
        }
     });
  }


 
function editInvoiceLine(LineNo)
{
	$('#invoice_insert').attr('action','ar_invoice!edit_invoiceline.action?dispatch=edit_invoice&LineNo='+LineNo);
	$('#invoice_insert').submit();
}


function addInvoiceLine()
{
	$('#invoice_insert').attr('action','ar_invoice!add_invoiceline.action?dispatch=edit_invoice');
	$('#invoice_insert').submit();
}

function submitAction()
{
    $('#invoice_insert').attr('action','ar_invoice!saveEdit.action');
	$('#invoice_insert').submit();
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

.tabDisable{
		background-image:url(/images/tab_inactive1.gif);
		margin:0px;
		color:#999;
		z-index:1;
}
-->

</style>
<script language="javascript" type="text/javascript" src="js/newwindow.js"></script>
</head>

<body class="content">

<form  class="niceform" method="post" id="invoice_insert" action="ar_invoice!saveEdit.action"  onsubmit="return checkFormByReq('invoice_insert');" >

<input name="arInvoice.creationDate" type="hidden"    value='<s:date name="arInvoice.creationDate" format="yyyy-MM-dd" />'   />
<input name="arInvoice.createdBy" type="hidden"    value='${arInvoice.createdBy}'   />
<input name="arInvoice.invoiceId" type="hidden"    value='${arInvoice.invoiceId}'   />
<div class="scm">
<div class="title_content">
  <div class="title">Invoice Information - ${arInvoice.invoiceNo}</div>
</div>
<div class="input_box">
		  <div class="content_box">

		    
		      <table width="100%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		          <td><table  border="0" cellpadding="0" cellspacing="0" class="Customer_table">
		            <tr>
		              <th width="150">Invoice No</th>
		              <td colspan="3"><input name="arInvoice.invoiceNo" type="text"  class="NFText" size="35" value='${arInvoice.invoiceNo}'  onblur="checkInvoiceNo(this.value)"   /></td>
		              <th width="160">Status</th>
		              <td width="355"><select name="arInvoice.status" style="width:207px;">		                
		                <option selected="selected" value="New">New</option>
	                  </select></td>
	                </tr>
		            <tr>
		              <th >Customer  No </th>
		              <td width="212"><input name="arInvoice.custNo" type="text" value='${arInvoice.custNo}' id="first2" size="35"  class="NFText" value='0'  desc='Customer  No ' required='true' readonly /></td>
		              <td width="16"><img src="images/search.gif" width="16" height="16" align="absmiddle" style="display:none;" /></td>
		              <td width="100">
		                <input type="button" name="Submit6" class="style_botton2" value="View Customer" style="display:none;"  />
		          </td>
		              <th>Shipment No</th>
		              <td><input name="arInvoice.shipmentId" type="text"  value='${arInvoice.shipmentId}'   id="Last2" size="35"  class="NFText"  value='0'  desc='Shipment No' required='true' readonly /><img src="images/search.gif" width="16" height="16" align="absmiddle"  style="display:none;" />		                
		              <input type="button" name="Submit2" class="style_botton2" value="View Shipment" style="display:none;"  /></td>
	                </tr>
		            <tr>
		              <th>Order No</th>
		              <td><input name="arInvoice.orderNo"  type="text" value='${arInvoice.orderNo}'  class="NFText" id="textfield5" size="35"  value='0'  desc='Order No' required='true' readonly    /></td>
		              <td><img src="images/search.gif" alt="" width="16" height="16" align="absmiddle"  style="display:none;" /></td>
		              <td><input type="button" name="Submit3" class="style_botton2" value="View Order" style="display:none;"  /></td>
		              <th>Sales Contact</th>
		              <td><input name="arInvoice.salesContact" type="text" value='${arInvoice.salesContact}' checkType='integer'  desc='Sales Contact' required='true'  class="NFText" id="textfield" size="35"/></td>
	                </tr>
		            <tr>
		              <th valign="top">Description</th>
		              <td colspan="5"><textarea   name="arInvoice.description" rows="10" cols="30" class="content_textarea2">${arInvoice.description}</textarea></td>
	                </tr>
		            <tr>
		              <th>Invoice Type </th>
		              <td colspan="3"><select name="arInvoice.invoiceType" style="width:207px;">
		                <option ${arInvoice.invoiceType eq "AR Auto "?"selected":""} value="AR Auto " >AR Auto </option>
		                <option ${arInvoice.invoiceType eq "AR Manual "?"selected":""} value="AR Manual " >AR Manual </option>
		                <option ${arInvoice.invoiceType eq "AR Prepayment"?"selected":""} value="AR Prepayment" >AR Prepayment</option>
                      </select></td>
		              <th>Invoice Date</th>
		              <td><input name="arInvoice.invoiceDate" type="text" value='<s:date name="arInvoice.invoiceDate" format="yyyy-MM-dd"  />'   size="35"   class="ui-datepicker" style="width: 113px;height:13px"/></td>
	                </tr>
		            <tr>
		              <th>Payment Method</th>		            
		              <td colspan="3"><select name="arInvoice.paymentMethod" style="width:207px;">
		                 <option ${arInvoice.paymentMethod eq "Check"?"selected":""} value="Check" >Check</option>
	                     <option ${arInvoice.paymentMethod eq "Creadit Card"?"selected":""} value="Creadit Card" >Creadit Card</option>
	                     <option ${arInvoice.paymentMethod eq "Direct Deposit"?"selected":""} value="Direct Deposit" >Direct Deposit</option>
	                  </select></td>
		              <th>Payment Term </th>
		              <td><select name="arInvoice.paymentTerm" style="width:207px;">
		                <option value="0" >2%10 Net 30</option>
	                  </select></td>
	                </tr>
		            <tr>
		              <th>Currency</th>
		              <td width="212">
			              <select name="arInvoice.currency" style="width:207px;">
	                         <option ${arInvoice.currency eq "USD"?"selected":""} value="USD" >USD</option>
	                         <option ${arInvoice.currency eq "JPY"?"selected":""} value="JPY" >JPY</option>
	                      </select>
                      </td>
		              <td colspan="2"><input type="button" name="Submit4" class="style_botton" value="Modify" onclick="window.openiframe('modify_invoice_curr.html',630,350)" style="display:none;" /></td>
		              <th>Due Date</th>
		              <td><input name="arInvoice.exprDate" type="text" value='<s:date name="arInvoice.exprDate" format="yyyy-MM-dd"  />'    size="35"  class="ui-datepicker" style="width: 113px;height:13px"/></td>
	                </tr>
                    <tr>
		              <th>Comment</th>
		              <td colspan="3"><input name="arInvoice.comment"  value='${arInvoice.comment}'  type="text"  class="NFText" id="textfield3" size="35"/></td>
		              <th>&nbsp;</th>
		              <td>&nbsp;</td>
	                </tr>
                    <tr>
                      <th>Customer Note</th>
                      <td colspan="3"><input name="arInvoice.customerNote"  value='${arInvoice.customerNote}'    type="text"  class="NFText" id="textfield4" size="35"/></td>
                      <th>&nbsp;</th>
                      <td><input name="arInvoice.printedFlag" type="checkbox" id="checkbox" ${arInvoice.currency eq "Y"?"checked":""}  value="0" disabled  /><input name="arInvoice.printedFlag" type="hidden" value='${arInvoice.printedFlag}' />
Printed</td>
                    </tr>
	              </table></td>
	            </tr>
		        <tr>
		          <td>
                
              <div class="invoice_title" ><a href="javascript:void(0);" onclick="openc('Contact_Info','image1')"><img src="images/ad.gif" width="11" height="11" id="image1"/></a>&nbsp;Amounts</div>
<div id="Contact_Info" class="disp" style="display:block;">
<table  border="0" cellpadding="0" cellspacing="0" class="Customer_table">
  <tr>
    <th width="110">SubTotal</th>
    <td colspan="2"><input name="arInvoice.subTotal"  id="subtotal" value='${arInvoice.subTotal}'  type="text"  class="NFText2" value="" size="35"   /></td>
    <th width="180">Grand Total</th>
    <td width="303"><input name="arInvoice.totalAmount" id="grandTotal" value='${arInvoice.totalAmount}'   type="text"   class="NFText2" value="" size="35"    /></td>
  </tr>
 
  <tr>
    <th>Tax</th>
    <td colspan="2"><input name="arInvoice.tax" id="tax" value='${arInvoice.tax}'  type="text"   class="NFText" size="35"    /></td>
    <th>Discount</th>
    <td><input name="arInvoice.discount"  type="text"  id="discount" value='${arInvoice.discount}' class="NFText" size="35"      /></td>
    </tr>
      <tr>
    <th>Shipping &amp; Handling</th>
    <td colspan="2"><input name="arInvoice.shipping"  id="shipping" value='${arInvoice.shipping}'  onblur="changeMoney(this.value)"  type="text" class="NFText" size="35"   checkType='float' desc='Shipping & Handling' required='true'   /></td>
    <th>&nbsp;</th>
    <td>&nbsp;</td>
    </tr>
      <tr>
    <th>Paid Amount</th>
    <td width="212"><input name="PaidAmount" id="paidAmount" value="<%=request.getParameter("paidAmt") %>" type="text" class="NFText" size="35"        readonly /></td>
    <td width="100"><input type="button" name="Submit5" class="style_botton2" value="View History"  style="display:none;" /></td>
    <th>Balance</th>
    <td><input name="arInvoice.balance" id="balance" type="text" class="NFText" size="35"  value='0'       /></td>
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
      <th width="46"><div align="left"><input name="checkbox2" type="checkbox"  onclick="cc(this)" /><a onclick="deleteRow()" title="Delete Invoice Line" rel="gb_page_center[600,  180]"><img src="images/file_delete.gif" alt="Delete" width="16" height="16" border="0" /></a></div></th>
        <th width="65">Line No</th>
        <th width="88">Item No</th>
        <th width="81">Catalog No</th>
        <th width="151">Name</th>
        <th width="75">Qty</th>
        <th width="74">UOM</th>
        <th width="64">Size</th>
        <th width="81">Price</th>
        <th width="81">Amount</th>
        <th>Tax</th>
      </tr>
    </table>
<div id="frame12" style="display:none;" class="hidlayer1">
<iframe id="hidkuan" name="hidkuan" src="kuang.html" width="668" height="425" frameborder="0"  allowtransparency="true"></iframe>
</div>
    <div class="frame_box" style="height:80px; ">
      <table width="955" border="0" cellpadding="0" cellspacing="0" id="list_table" class="list_table">
      <%
      ValueStack vs = (ValueStack)request.getAttribute("struts.valueStack");
      if(vs!=null){
       Map param = vs.findValue("param") == null ? null :(Map)vs.findValue("param");
       if(param!=null){
		String[] itemNos = param.get("itemNo") == null ? null : (String[])param.get("itemNo");
		String[] catalogNo = param.get("catalogNo") == null ? null : (String[])param.get("catalogNo");
		String[] names = param.get("name") == null ? null : (String[])param.get("name");
		String[] qtys = param.get("qty") == null ? null : (String[])param.get("qty");
		String[] qtyUoms = param.get("qtyUom") == null ? null : (String[])param.get("qtyUom");
		String[] unitPrices = param.get("unitPrice") == null ? null : (String[])param.get("unitPrice");
		String[] amounts = param.get("amount") == null ? null : (String[])param.get("amount");
		String[] taxs = param.get("tax") == null ? null : (String[])param.get("tax");
		String[] sizes = param.get("size") == null ? null : (String[])param.get("size");
		String[] lineNo = param.get("lineNo") == null ? null : (String[])param.get("lineNo");
		String[] discounts = param.get("discount") == null ? null : (String[])param.get("discount");
		String[] creationDate = param.get("creationDate") == null ? null : (String[])param.get("creationDate");
		String[] creationBy = param.get("createdBy") == null ? null : (String[])param.get("createdBy");
		String[] modifyDate = param.get("modifyDate") == null ? null : (String[])param.get("modifyDate");
		String[] modifyBy = param.get("modifiedBy") == null ? null : (String[])param.get("modifiedBy");
		String[] invoiceLineIds = param.get("invoiceLineIds") == null ? null : (String[])param.get("invoiceLineIds");
		String[] invoiceIds = param.get("invoiceIds") == null ? null : (String[])param.get("invoiceIds");
  
		if(catalogNo!=null){
		for(int i=0;i<catalogNo.length;i++){
		%>
		
		  <tr>
         <td width="46"><input type="checkbox" value="checkbox"  /></td>
         <td width="65" ><a value="<%=lineNo[i] %>" onclick="editInvoiceLine(<%=lineNo[i] %>)" ><%=lineNo[i] %></a><input type='hidden' name='param.lineNo' value='<%=lineNo[i] %>'></td>
         <td width="88"><%=itemNos[i] %><input type='hidden' name='param.itemNo' value='<%=itemNos[i] %>'></td>
         <td width="81"><%=catalogNo[i] %><input type='hidden' name='param.catalogNo' value='<%=catalogNo[i] %>'></td>
         <td width="151"><%=names[i] %><input type='hidden' name='param.name' value='<%=names[i] %>'></td>
         <td width="75"><%=qtys[i] %><input type='hidden' name='param.qty' value='<%=qtys[i]%>'></td>
         <td width="74"><%=qtyUoms[i] %><input type='hidden' name='param.qtyUom' value='<%=qtyUoms[i] %>'></td>
         <td width="64"><%=sizes[i] %><input type='hidden' name='param.size' value='<%=sizes[i] %>'></td>
         <td width="81"><%=unitPrices[i] %><input type='hidden' name='param.unitPrice' value='<%=unitPrices[i] %>'></td>
         <td width="81"><%=amounts[i] %><input type='hidden' name='param.amount' value='<%=amounts[i] %>'></td>
         <td><%=taxs[i] %><input type='hidden' name='param.tax' value='<%=taxs[i] %>' />
         <input type="hidden" name="param.discount" value="<%=discounts[i] %>" />
<input type="hidden" name="param.creationDate" value="<%=creationDate[i] %>" />
<input type="hidden" name="param.createdBy" value="<%=creationBy[i] %>" />
<input type="hidden" name="param.modifyDate" value="<%=modifyDate[i] %>" />
<input type="hidden" name="param.modifiedBy" value="<%=modifyBy[i] %>" />
<input type="hidden" name="param.invoiceLineIds" value="<%=invoiceLineIds[i] %>" />
<input type="hidden" name="param.invoiceIds" value="<%=invoiceIds[i] %>" />
         </td>
       </tr>
		
		<%
		}
		}
       }
       }
      %>  
      </table>
    </div>
    <div align="center" style="padding:10px;">
      <input name="button_add" type="button" class="style_botton" value="New" onclick="addInvoiceLine();" style="display:none;" />
    </div>
  </div>
</div>
<script type="text/javascript">
initTabs('dhtmlgoodies_tabView1',Array('Invoice Line'),0,998,160);

</script>
<div class="button_box">
      <input type="button" name="Submit124" value="Back" class="search_input" onclick="window.history.back();" id="cancel1"/>
</div>
<div/>
</div>
</form>	

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
  var shipping = 0;
  if($("#shipping").val() == ''){
  $("#shipping").val('0.0');
  }
    var shipping = parseFloat($("#shipping").val());
  var grandTotal = amount + tax - discount + shipping ;
  var paidAmount = <%=request.getParameter("PaidAmount")%>;
  var balance = grandTotal - paidAmount;

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

$(document).ready(function(){
//fillTable();
});

function fillTable(){
var orderNo = $("orderNo").val();
   $.ajax({
   url :'ar_invoice_line!list2.action?invoiceId='+orderNo,
   type:'post',
   success:function(result){
    alert(result);
   },
   error:function(){
    alert("query data error");
   }
   });
}
</script>
</body>
</html>
