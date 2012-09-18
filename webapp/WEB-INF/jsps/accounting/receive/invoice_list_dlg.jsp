<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include  file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="stylesheet/scm.css" rel="stylesheet" type="text/css" />
<link href="stylesheet/table.css" rel="stylesheet" type="text/css" />
<link href="stylesheet/openwin.css" rel="stylesheet" type="text/css" />
<script src="${global_js_url}jquery/jquery.js" type="text/javascript" language="javascript"></script>
<script  language="JavaScript" type="text/javascript">
function selectedInvoice () { 
	var item = $(":radio:checked"); 
	var len=item.length; 
	if(len>0){ 
		var id = $(':radio:checked').val();
		var no = $(':radio:checked').attr("invoiceNo");
		var currency = $(':radio:checked').attr("currency");
		//var paidAmt = $(':radio:checked').attr("paidAmt");
		var paidAmt = $(':radio:checked').attr("balance");
		window.parent.setInvoiceValue(id,no,currency,paidAmt);
		doCancel();
	} 
}

function doCancel(){
		window.parent.$('#'+window.parent.$('#invoiceIframe').parent().attr('id')).dialog('close');
}

$(document).ready(function(){
	$('#Search').click(function(){
		var filter_EQI_InvoiceNo = $('#filter_LIKES_invoiceId').val();
		var pattern=/^\d+$/;
		var flag = pattern.test(filter_EQI_InvoiceNo);
		if(flag){
			$('#searchForm').submit();
		} else {
			alert("please fill in number.");
		}
	});
});

</script>
</head>
<body>
<table width="500" border="0" cellspacing="3" cellpadding="0" id="table11">
  <tr>
    <td bgcolor="#FFFFFF"><table width="449" border="0" cellspacing="0" cellpadding="0" height="339">
 
      <tr>
        <td style="padding-left:60px;"><br />
          <table width="367" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td>
            <form method="get" target="_self" id="searchForm" action="genscript!invoicelist.action">
            <input type="hidden" name="filter_EQI_orderNo" value="${param.filter_EQI_orderNo}"/>
            <input type="hidden" name="filter_EQS_currency" value="${param.filter_EQS_currency}"/>
            <input type="hidden" name="filter_EQS_status" value="${param.filter_EQS_status}"/>
            <table border="0" cellpadding="0" cellspacing="0" class="Customer_table">
              <tr>
                <th>Invoice No</th>
                <td><input name="filter_LIKES_invoiceId" id="filter_LIKES_invoiceId" type="text" value="${param.filter_LIKES_invoiceId }" class="NFText" size="20"/></td>
                <td><input name="Submit3" type="button" id="Search" class="style_botton" value="Search" /></td>
              </tr>
            </table>
            </form>
            </td>
            </tr>
            <tr>
            <td>
              <table width="373" border="0" cellspacing="0" cellpadding="0" height="244">
                <tr>
                  <td style="padding-top:10px;"><table width="350" border="0" cellpadding="0" cellspacing="0" class="list_table">
                    <tr>
                      <th width="30">&nbsp;</th>
                       <th>Invoice Id</th>
                      <th>Invoice No</th>
                      <th>Currency</th>
                      <th>Paid Amount</th>
                      </tr>
                  </table></td>
                </tr>
                <tr>
                  <td><div  style="width:367px; height:130px; overflow:scroll;">
                    <table width="350" border="0" cellpadding="0" cellspacing="0" class="list_table">
                    <s:iterator value="invoiceListPage.result" >
                      <tr>
                        <td  width="30"><div align="center">
                          <input type="radio" name="radio" id="radio" value="${invoiceId}" invoiceNo="${invoiceNo }" currency="${currency }" paidAmt="${paidAmt }" balance="${balance }"/>
                        </div></td>
                        <td>${invoiceId}</td>
                        <td>${balance}</td>
                        <td>${currency}</td>
                        <td>${paidAmt}</td>
                      </tr>
                    </s:iterator>
                    </table>
                  </div></td>
                </tr>
                  <tr>
      <td>
		<div class="grayr">
			<jsp:include page="/common/db_pager.jsp">
			  <jsp:param value="${ctx}/genscript!invoicelist.action" name="moduleURL"/>
			</jsp:include>	
		</div>
	  </td>
    </tr>
                <tr>
                  <td height="60"><div align="center">
                    <input id="sub1" name="Submit1" type="submit" class="style_botton"  value="Select" onclick="selectedInvoice()"/>
                    <input id="sub2" type="submit" name="Submit2" value="Cancel" class="style_botton" onclick="doCancel()"/>
                    </div></td>
                </tr>
              </table></td>
          </tr>
        </table>          <br /></td>
      </tr>
    </table></td>
  </tr>
</table>

</body>
</html>
