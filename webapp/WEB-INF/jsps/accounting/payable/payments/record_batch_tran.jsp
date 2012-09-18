<%@ page language="java" import="java.util.List,java.util.Map,java.util.HashMap,java.util.ArrayList" pageEncoding="UTF-8"%>

<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${global_js_url}json.js"></script>
<script type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script type="text/javascript" src="${global_js_url}jquery/jquery.form.js"></script>

<script>
$(document).ready(function(){
	var errorInfo = "${error}";
	if(errorInfo!= ''){
	alert(errorInfo);
	}
});


function doUpload(){
	if(document.getElementById('upload').value == ''){
		return;
	}
	var file = document.getElementById('upload').value;
	if(file.substring(file.length-3,file.length) != 'xls'){
		alert('Please pick a file suffixes width ".xls"');
		return;
	}
	
	document.getElementById('form1').submit();
}

function doCancel(){
	window.parent.$('#'+window.parent.$('#paymentIframe').parent().attr('id')).dialog('close');
}

function doSubmit(){
	var paymentList = [];
	var rows = $('#paymentList tr');
	for(var i=0; i<rows.length; i++){
		var cells = $(rows[i]).children('td');
		var obj = {
			vendorNo:	$(cells[0]).html(),
			paymentType: $(cells[1]).html(),
			amount: $(cells[2]).html(),
			transactionFee:  $(cells[3]).html(),
			currency:  $(cells[4]).html(),
			description:  $(cells[5]).html(),
			tenderType:  $(cells[6]).html(),
			accountName: ($(cells[6]).html() == 'Credit Card')?$(cells[11]).html():$(cells[7]).html(),
			accountNo:  ($(cells[6]).html() == 'Credit Card')?$(cells[14]).html():$(cells[8]).html(),
			routingNo:  $(cells[9]).html(),
			chkNo:  $(cells[10]).html(),
			ccType:  $(cells[12]).html(),
			ccCardHolder:  $(cells[13]).html(),
			ccCvc:  $(cells[15]).html(),
			ccExpiration:  $(cells[16]).html(),
			transactionType: $('#transactionType').val()
		};
		
		if(obj.vendorNo == ''){
			alert('Line #'+(i+1)+': Supplier No is not null');
			return;
		}else if(obj.paymentType == ''){
			alert('Line #'+(i+1)+': Payment Type is not null');
			return;
		}else if(obj.amount == ''){
			alert('Line #'+(i+1)+': Amount is not null');
			return;
		}else if(obj.transactionFee == ''){
			alert('Line #'+(i+1)+': Transaction Fee is not null');
			return;
		}else if(obj.currency == ''){
			alert('Line #'+(i+1)+': Currency is not null');
			return;
		}else if(obj.description == ''){
			alert('Line #'+(i+1)+': Description is not null');
			return;
		}else if(obj.tenderType == ''){
			alert('Line #'+(i+1)+': Tender Type is not null');
			return;
		}
		if(obj.tenderType == 'Check'){
			if(obj.accountName == ''){
				alert('Line #'+(i+1)+': Account Name is not null');
				return;
			}else if(obj.accountNo == ''){
				alert('Line #'+(i+1)+': Account No is not null');
				return;
			}else if(obj.routingNo == ''){
				alert('Line #'+(i+1)+': Routing No is not null');
				return;
			}else if(obj.chkNo == ''){
				alert('Line #'+(i+1)+': Check No is not null');
				return;
			}
		}else if(obj.tenderType == 'Credit Card'){
			if(obj.accountName == ''){
				alert('Line #'+(i+1)+': Account Name is not null');
				return;
			}else if(obj.ccType == ''){
				alert('Line #'+(i+1)+': Credit Card Type is not null');
				return;
			}else if(obj.ccCardHolder == ''){
				alert('Line #'+(i+1)+': Name on the Card is not null');
				return;
			}else if(obj.accountNo == ''){
				alert('Line #'+(i+1)+': Credit Card Numeber is not null');
				return;
			}else if(obj.ccCvc == ''){
				alert('Line #'+(i+1)+': CVC is not null');
				return;
			}else if(obj.ccExpiration == ''){
				alert('Line #'+(i+1)+':  Expiration Date is not null');
				return;
			}
		}else{
			if(obj.accountName == ''){
				alert('Line #'+(i+1)+': Account Name is not null');
				return;
			}else if(obj.accountNo == ''){
				alert('Line #'+(i+1)+': Account No is not null');
				return;
			}else if(obj.routingNo == ''){
				alert('Line #'+(i+1)+': Routing No is not null');
				return;
			}
		}
		
		paymentList.push(obj);		
	}

	/*
	var isExist = 1;
	var options = {
			async:false,
			success:function(data, textStatus){
					alert(data);
				},
			type:'post',
			data: {paymentList:'s:123'},
			dataType:'json',
			//processData: false,
			url:'ar_invoice_payment!btachSave.action',
			error:function(XMLHttpRequest, textStatus, errorThrown){
			       alert("error:"+textStatus);
			     }	
			};
	$.ajax(options);
	*/
	$('#payments').val(paymentList.toJSONString());
	document.getElementById('form2').action = "ap_invoice_payment!btachSave.action";
	//document.getElementById('form2').submit();	
	$('#form2').ajaxSubmit(function(){
		$(this).ajaxSubmit({
			　　success:function(p){
					alert('Record Batch Transactions Success!');
					doCancel();
					window.parent.$('#form1').submit();
				} 
			});
		return false;
	});
}
</script>
</head>

<link href="stylesheet/openwin.css" rel="stylesheet" type="text/css" />
<body>
<form name="form1" id="form1" action="ap_invoice_payment!btachUpload.action" method="post" enctype="multipart/form-data" style="margin:0px;" >
<table width="850" border="0" cellpadding="0" cellspacing="3" bgcolor="#96BDEA" id="table11">
  <tr>
    <td bgcolor="#FFFFFF"><table width="850" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td style="padding-left:20px;">
          <table border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td><table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
              <tr>
                <td height="30" colspan="5">&nbsp;Enter the transactions in the following form or upload the transaction document by the <strong><u>Template</u></strong></td>
                </tr>
              <tr>
                <th width="120">Transaction Type</th>
                <td width="180">
                	<select name="transactionType" id="transactionType" style="width:234px;">
					<s:iterator value="apInvoicePaymentDto.transactionTypeList" >
						<s:if test="(apInvoicePaymentDto.transactionType == null && value == 'Check Payment') || (apInvoicePaymentDto.transactionType != null && value == apInvoicePaymentDto.transactionType)">
							<option value="${value}" selected>${key}</option>
						</s:if>
						<s:else>
							<option value="${value}">${key}</option>
						</s:else>
					</s:iterator>	
					</select>
                 </td>
                <td width="200"><input type="file" name="upload" id="upload" /></td>
                <td width="100"><input name="Submit5" type="button" class="style_botton" value="Upload" onclick="doUpload()" /></td>
                <td width=""><input name="Submit3" type="submit" class="style_botton" value="Delete" style="display:none;" /></td>
              </tr>
            </table>
            </form>
            <form name="form2" id="form2" action="ap_invoice_payment!btachSave.action" method="post" style="margin:0px;" >
              <input type="hidden" id="payments" name="payments" />
              <div style="width:820px; overflow:auto; height:250px;">
              <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td colspan="4" style="padding-top:10px;">
                  <table width="1700" border="0" cellpadding="0" cellspacing="0" class="list_table">
                    <tr>
                      <th width="89">Supplier</th>
                      <th width="89">Payment Type</th>
                      <th width="52"> Amount </th>
                      <th width="0" style="display:none;">Transaction Fee</th>
                      <th width="56">Currency</th>
                      <th  width="77">Description</th>
                      <th  width="90">Tender Type</th>
                      <th width="86">Account Name</th>
                      <th width="80">Account No</th>
                      <th width="86">Routing No</th>
                      <th width="99">Check No</th>
                      <th width="86">Account Name</th>
                      <th width="120">Credit Card Type</th>
                      <th width="120">Name on the Card</th>
                      <th width="120">Credit Card Numeber</th>
                      <th width="86">CVC</th>
                      <th width="86">Expiration Date</th>
                    </tr>
                  </table></td>
                </tr>
                <tr>
                  <td colspan="4">
                  <div  style="width:1717px; height:200px; overflow:scroll;">
                    <table id="paymentList" width="1700" border="0" cellpadding="0" cellspacing="0" class="list_table" style="border-collapse:collapse;">
                      <s:iterator value="apInvoicePaymentPage.result" status="stuts">
					      <s:if test="#stuts.odd == true">
					      <tr>
					        <td width="89">${vendorNo}</td>
					        <td width="89">${paymentType}</td>
					        <td width="52">${amount}</td>
					        <td width="0" style="display:none;">${transactionFee}</td>
					        <td width="56">${currency}</td>
					        <td width="77">${description}</td>
					        <td width="90">${tenderType}</td>
					        <td width="86">${tenderType!='Credit Card'?accountName:''}</td>
					        <td width="80">${tenderType!='Credit Card'?accountNo:''}</td>
					        <td width="86">${routingNo}</td>
					        <td width="99">${chkNo}</td>
					        <td width="86">${tenderType=='Credit Card'?accountName:''}</td>
					        <td width="120">${ccType}</td>
					        <td width="120">${ccCardHolder}</td>
					        <td width="120">${tenderType=='Credit Card'?accountNo:''}</td>
					        <td width="86">${ccCvc}</td>
					        <td width="86">${ccExpiration}</td>
					      </tr>
					      </s:if>
					      <s:else>
					      <tr>
					        <td class="list_td2" width="89">${vendorNo}</td>
					        <td class="list_td2" width="89">${paymentType}</td>
					        <td class="list_td2" width="52">${amount}</td>
					        <td class="list_td2" width="0" style="display:none;">${transactionFee}</td>
					        <td class="list_td2" width="56">${currency}</td>
					        <td class="list_td2" width="77">${description}</td>
					        <td class="list_td2" width="90">${tenderType}</td>
					        <td class="list_td2" width="86">${tenderType!='Credit Card'?accountName:''}</td>
					        <td class="list_td2" width="80">${tenderType!='Credit Card'?accountNo:''}</td>
					        <td class="list_td2" width="86">${routingNo}</td>
					        <td class="list_td2" width="99">${chkNo}</td>
					        <td class="list_td2" width="86">${tenderType=='Credit Card'?accountName:''}</td>
					        <td class="list_td2" width="120">${ccType}</td>
					        <td class="list_td2" width="120">${ccCardHolder}</td>
					        <td class="list_td2" width="120">${tenderType=='Credit Card'?accountNo:''}</td>
					        <td class="list_td2" width="86">${ccCvc}</td>
					        <td class="list_td2" width="86">${ccExpiration}</td>
					      </tr>	
					      </s:else>
					</s:iterator>
                    </table>
                  </div></td>
                </tr>
              </table>
             </div>
             <div align="center" style="padding-left:40px; padding-top:20px;">
             	<input id="sub1" name="Submit1" type="button" class="style_botton"  value="Confirm" onclick="doSubmit()" />
                <input id="sub2" type="button" name="Submit2" value="Cancel" class="style_botton" onclick="doCancel()"/>
             </div>
              </td>
          </tr>
        </table>          
        <br /></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html>
