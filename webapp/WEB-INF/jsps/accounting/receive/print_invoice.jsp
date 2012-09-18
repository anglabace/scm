<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script type="text/javascript" src="${global_js_url}jquery/ui/ui.datepicker.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tools.js"></script>
<script type="text/javascript">
  $(document).ready(function(){
   $('.ui-datepicker').each(function(){
$(this).datepicker(
{
dateFormat: 'yy-mm-dd',
changeMonth: true,
changeYear: true
});
});

var invoice = window.parent.invoice;
initData("table11",invoice);
$('#invoiceId').val(invoice.invoiceId);
  });

function doPrintSubmit(){
	//document.getElementById('form1').action = "ar_invoice!print.action"
}
  
  function cancel(){
    window.parent.$("#printDialog").dialog('close');
  }
</script>
</head>

<body>
<form id="form1" name="form1" method="post" action="ar_invoice!printInvoice.action">
<input name="invoiceId" id="invoiceId" type="hidden"/>
<table width="650" border="0" cellspacing="3" cellpadding="0" id="table11" >
  <tr>
    <td bgcolor="#FFFFFF"><table width="650" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td style="padding-left:20px;"><br />
          <table width="600" border="0" cellpadding="0" cellspacing="0" class="General_table">
            <tr>
              <th>Invoice No</th>
              <td><input name="invoiceNo" type="text" id="invoiceNo"    class="NFText"/></td>
              <th>Status</th>
              <td><select name="status" id="status">
              <option>Completed</option>
		                <option value="Closed">Closed</option>                       
                        <option value="In Progress">In Progress</option>
		                <option value="Invalid">Invalid</option>
                        
                        <option selected="selected" value="New">New</option>
                        <option value="Overdue">Overdue</option>
                        <option value="Voided">Voided</option>
              </select></td>
            </tr>
            <tr>
              <th width="119">Order No</th>
              <th width="136"><input name="orderNo" type="text" id="orderNo"    class="NFText"/></th>
              <th>Customer No</th>
              <td><input name="customerNo" type="text" id="customerNo"    class="NFText"/></td>
            </tr>
            <tr>
              <th>Invoice Type</th>
              <th><select name="select" id="select">
                <option value="AR Auto">AR Auto</option>
                <option value="AR Manual">AR Manual</option>
                <option value="AR Prepayment">AR Prepayment</option>
                </select>
              </th>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <th >Invoice Date From</th>
              <td ><input name="dateFrom" type="text" id="dateFrom" style="width:123px;"   class="ui-datepicker"/></td>
              <th width="173">To</th>
              <td width="172"><input name="dateTo" type="text" id="dateTo" style="width:123px;"    class="ui-datepicker"/></td>
            </tr>
            <tr>
              <td height="100" colspan="4"><div align="center">
                <input type="submit" name="Submit3" class="style_botton" value="Confirm" />
                <input type="button" name="Submit" class="style_botton" value="Cancel" onclick="cancel()"/>
              </div></td>
            </tr>
          </table>          <br /></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html>
