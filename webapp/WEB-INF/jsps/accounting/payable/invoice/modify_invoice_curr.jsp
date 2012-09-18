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
<script type="text/javascript" src="${global_js_url}tools.js"></script>
<script type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script type="text/javascript" src="${global_js_url}tools.js"></script>
<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
   
   function doConfirm(){
   var val1 = $("#currency").val();
   var val2 = $("#reason").val();
   var fromCurrency = window.parent.$("#currency").val();
     if(""==$.trim(val2))
     {
        alert("reason required !");
        return;
     }
   if(fromCurrency == val1){
     alert("Currency did't changed");
     return;
   }
   
   $.ajax({
     url : 'ar_invoice!getCurrencyRate.action',
     data:{fromCurrency:fromCurrency,toCurrency:val1},
     dataType:'json',
     success:function(res){
           window.parent.setCurrencyValue(val1,val2,res.rate,res.symbol); 
           window.parent.closeModifyDlg();
     },
     error:function(){
      alert("Query Currency Rate Error!");
     }
   });
   }
   
   $(document).ready(function(){
     initSelect();
   })
   
</script>
</head>

<body>
<table width="600" border="0" cellspacing="3" cellpadding="0" id="table11" >
  <tr>
    <td bgcolor="#FFFFFF"><table width="600" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td style="padding-left:30px;"><br />
          <table width="550" border="0" cellpadding="0" cellspacing="0" class="General_table">
            <tr>
              <td height="22" colspan="2"><span class="important">Note:</span> A new invoice showing the amounts with modified currency is created and the original invoice will be void.</td>
              </tr>
									<tr>
										<th width="50">
											Currency
										</th>
										<td width="500">
											<select name="currency" id="currency" style="width: 220px;" sqlField="CURRENCY" >
												<option value="USD">
													USD
												</option>
												<option selected="selected" value="JPY">
													JPY
												</option>
											</select>
										</td>
									</tr>
									<tr>
              <th colspan="2"><div align="left">Choose the reason to modify the invoice currency:</div></th>
             </tr>
            <tr>
              <td colspan="2" ><span style="font-weight: normal;">
                <textarea name="reason" id="reason" class="content_textarea1" style="width:400px;"></textarea>
              </span></td>
              </tr>
            <tr>
              <td height="100" colspan="2"><div align="center">
                <input type="submit" name="Submit3" class="style_botton" value="Modify" onclick="doConfirm()"/>
                <input type="submit" name="Submit" class="style_botton" value="Cancel" onclick="window.parent.closeModifyDlg()"/>
              </div></td>
            </tr>
          </table>          <br /></td>
      </tr>
    </table></td>
  </tr>
</table>

</body>
</html>
