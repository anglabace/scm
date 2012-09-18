<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
 
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
<script src="${global_js_url}js/ui.datepicker.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript"> 
$(document).ready(function(){
	$('.pickdate').each(function(){
		$(this).datepicker(
			{
				dateFormat: 'yy-mm-dd',
				changeMonth: true,
				changeYear: true,
				yearRange: '-100:+0'
			});
	});
	AmounttoApply();
});
//四舍五入 两位精度
function fouroutfivein(value)
{
   if(isNaN(value))
   value=0;
   result=new Number(value);
  
   return result.toFixed(2)
}


function AmounttoApply()
{
	var paymentApply=window.parent.$("#PaymentApply").val();
	var payment=new Number(paymentApply);
	var invoice=new Number(${apInvoice.balance});
	
	if(payment>invoice)
	{
	    $("#amounttoApply").val(invoice);
	    $("#InvoiceBalance").val("0");
	    $("#paymentBalance").val(fouroutfivein(payment-invoice));
	    $("#BadDebt").attr("disabled",true);
   
	}
	else
	{
	    $("#amounttoApply").val(payment);
	    $("#InvoiceBalance").val(fouroutfivein(invoice-payment));
	    $("#paymentBalance").val("0");
	    $("#Overpayment").attr("disabled",true);
	}
	
    if(payment.valueOf()==invoice.valueOf())
	{
		$("#BadDebt").attr("disabled",true);
		$("#Overpayment").attr("disabled",true);
	}
}

function commit()
{
    if($("#BadDebt").attr("checked"))
    window.parent.$("#BadDebt").val("Bad Debt");
    
    if($("#Overpayment").attr("checked"))
    window.parent.$("#Overpayment").val("Overpayment");
    
    window.parent.$("#invoice_id").val("${apInvoice.invoiceId}");
    window.parent.$("#apply_amount").val($("#amounttoApply").val());
    window.parent.$("#Balance").val($("#InvoiceBalance").val());
	window.parent.$("#PaymentApply").val($("#paymentBalance").val());
	var hadApply=window.parent.$("#applyAmount").val();
	if(""==hadApply)
	hadApply=0;	
	var hadPaymentApply=new Number(hadApply);
    var amounttoApply=$("#amounttoApply").val();
    var amountApply=new Number(amounttoApply);
	window.parent.$("#PaymentApply").val($("#paymentBalance").val());
	window.parent.$("#applyAmount").val(hadPaymentApply+new Number(amounttoApply));
    window.parent.$("#apply").attr("disabled","true");
    doCancel();
}

function doCancel(){
   window.parent.$("#new_resource_dlg").dialog('close');
}

</script>


<link href="stylesheet/openwin.css" rel="stylesheet" type="text/css" />

</head>
<body>
<table width="600" border="0" cellpadding="0" cellspacing="3" >
  <tr>
    <td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
    
      <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><br />
              <table width="400" border="0" cellpadding="0" cellspacing="0" class="General_table" style="margin:5px auto;">
              <tr>
                <th width="162">Invoice No</th>
                <td width="117"><input name="textfield23" type="text" class="NFText" value="${apInvoice.invoiceNo}" size="20" /></td>
                <th width="121">&nbsp;</th>
                </tr>
              <tr>
                <th>Subtotal</th>
                <td><input name="textfield2" type="text" class="NFText2" value="${apInvoice.subTotal}" size="20" /></td>
                <th>&nbsp;</th>
                </tr>
              <tr>
                <th>Shipping</th>
                <td><input name="textfield22" type="text" class="NFText2" value="${apInvoice.shipping}" size="20" /></td>
                <th>&nbsp;</th>
                </tr>
              <tr>
                <th>Tax</th>
                <td><input name="textfield" type="text" class="NFText2" value="${apInvoice.tax}" size="20" /></td>
                <th>&nbsp;</th>
                </tr>
              <tr>
                <th>Total</th>
                <td><input name="textfield3" type="text" class="NFText2" value="${apInvoice.totalAmount}" size="20" /></td>
                <th>&nbsp;</th>
                </tr>
              <tr>
                <th>Balance</th>
                <td><input name="textfield4" type="text" class="NFText2" value="${apInvoice.balance}" size="20" /></td>
                <th>&nbsp;</th>
                </tr>
              <tr>
                <th>Amount to Apply</th>
                <td><input name="amounttoApply" id="amounttoApply"   type="text" class="NFText2" value="" size="20" readonly /></td>
                <td>&nbsp;</td>
                </tr>
              <tr>
                <th valign="top">Invoice Ending Balance</th>
                <td><input name="InvoiceBalance" id="InvoiceBalance"  type="text" class="NFText2" value="0" size="20"  readonly /></td>
                <td><span class="list_td2">
                  <input name="checkbox7" type="checkbox" id="BadDebt"    />
                Bad Debt</span></td>
                </tr>
              <tr>
                <th valign="top">Amount Left to Apply</th>
                <td><input name="paymentBalance" id="paymentBalance"   type="text" class="NFText2" value="0" size="20"  readonly /></td>
                <td><span class="list_td2">
                  <input name="checkbox" type="checkbox" id="Overpayment"     />
                Overpayment</span></td>
                </tr>
              <tr>
                <td height="40" colspan="3"><div align="center">
                  <input type="submit" name="Submit"  class="style_botton" value="Confirm" onclick="commit()"/>
                  &nbsp;&nbsp;
                  <input name="Submit2" type="button" class="style_botton" value="Cancel" onclick="doCancel()"/>
                  </div></td>
              </tr>
            </table>
              <br /></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
