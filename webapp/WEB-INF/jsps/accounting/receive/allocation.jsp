<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
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
<script   language="JavaScript" type="text/javascript">  
  function   cc(e)  
  {  
      var   a   =   document.getElementsByName("pa");  
      for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;  
  }  
                 </script>
</head>

<body class="content" style="background-image:none;">

<div class="scm">
<div class="title_content">
  <div class="title">Payment Allocation</div>
</div>
<div class="search_box" >
  <div class="search_box_two">
 <table  border="0" cellpadding="0" cellspacing="0" class="General_table">
  <tr>
    <th>Customer No</th>
    <td><input name="Input2" type="text" class="NFText" size="20" /></td>
    <th>Transaction  No</th>
    <td><input name="Input" type="text" class="NFText" size="20" /></td>
    <th>Transaction Date From</th>
    <td><input name="Input34" type="text" class="NFText" size="20" /></td>
    <th>To</th>
    <td><input name="Input32" type="text" class="NFText" size="20" /></td>
    </tr>
  <tr>
    <td height="40" colspan="8" align="center"><input type="submit" name="Submit5" value="Search" class="search_input" /></td>
  </tr>
 </table>

</div>
</div>
        <div class="input_box">
		  <div class="content_box">
          <div >
            <table width="1010" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><div style="margin-right:17px;">
                  <table width="993" border="0" cellspacing="0" cellpadding="0" class="list_table">
                    <tr>
                      <th width="30"><div align="center">
                        <input type="radio" name="radio" id="radio" value="radio" />
                      </div></th>
                      <th width="130">Transaction No </th>
                      <th width="76">Customer</th>
                      <th width="182">Transaction Type</th>
                      <th width="84">Amount</th>
                      <th width="113">Applied Amount</th>
                      <th width="69">Currency</th>
                      <th width="106">Payment Type</th>
                      <th>Transaction Date</th>
                    </tr>
                  </table>
                </div></td>
              </tr>
              <tr>
                <td><div class="list_box" style="height:340px; overflow:scroll;">
                  <table width="993" border="0" cellspacing="0" cellpadding="0" class="list_table2">
                    <tr>
                      <td width="30" align="center"><input type="radio" name="radio" id="radio" value="radio" /></td>
                      <td width="130"><a href="receipt_modify.html">200404</a></td>
                      <td width="76">&nbsp;</td>
                      <td width="182">Check Payment</td>
                      <td width="84" align="right">288.75</td>
                      <td width="113">Standard</td>
                      <td width="69">&nbsp;</td>
                      <td width="106">&nbsp;</td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td align="center" class="list_td2"><input type="radio" name="radio" id="radio" value="radio" /></td>
                      <td class="list_td2">524654</td>
                      <td class="list_td2">&nbsp;</td>
                      <td class="list_td2">Adjustment</td>
                      <td align="right" class="list_td2">256.58</td>
                      <td class="list_td2">Prepayment</td>
                      <td class="list_td2">&nbsp;</td>
                      <td class="list_td2">&nbsp;</td>
                      <td class="list_td2">&nbsp;</td>
                    </tr>
                    <tr>
                      <td align="center"><input type="radio" name="radio" id="radio" value="radio" /></td>
                      <td>456574</td>
                      <td>&nbsp;</td>
                      <td>Bad Debt</td>
                      <td align="right">5464.52</td>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td align="center" class="list_td2"><input type="radio" name="radio" id="radio" value="radio" /></td>
                      <td class="list_td2">456544</td>
                      <td class="list_td2">&nbsp;</td>
                      <td class="list_td2">Check Refund</td>
                      <td align="right" class="list_td2">44.25</td>
                      <td class="list_td2">&nbsp;</td>
                      <td class="list_td2">&nbsp;</td>
                      <td class="list_td2">&nbsp;</td>
                      <td class="list_td2">&nbsp;</td>
                    </tr>
                    <tr>
                      <td align="center"><input type="radio" name="radio" id="radio" value="radio" /></td>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                      <td>Credit Card Payment</td>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td align="center" class="list_td2"><input type="radio" name="radio" id="radio" value="radio" /></td>
                      <td class="list_td2">&nbsp;</td>
                      <td class="list_td2">&nbsp;</td>
                      <td class="list_td2">Direct Deposit</td>
                      <td class="list_td2">&nbsp;</td>
                      <td class="list_td2">&nbsp;</td>
                      <td class="list_td2">&nbsp;</td>
                      <td class="list_td2">&nbsp;</td>
                      <td class="list_td2">&nbsp;</td>
                    </tr>
                  </table>
                </div></td>
              </tr>
              <tr>
                <td><div class="grayr"><span class="disabled">&lt; </span><span class="current">1</span><span class="disabled"> &gt; </span></div></td>
              </tr>
            </table>
          </div>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
     <tr>
             <td><div class="button_box">
               <input name="Submit22" type="submit" class="search_input"  value="Select" onclick="window.location.href='payment_allcation.html'"/>
          </div></td>
           </tr>
         </table>
		</div>
  </div>	
</div>	
</body>
</html>
