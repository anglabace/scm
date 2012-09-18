<%@ page language="java" import="java.util.List" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
   <title>Order Management</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	 <script type="text/javascript">

var GB_ROOT_DIR = "./greybox/";

//-->
</script>

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
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<script src="${global_js_url}tools.js" type="text/javascript"></script>
<style type="text/css">
<!--
.hidlayer {
	font-size: 12px;
	height: 370px;
	width: 666px;
	position: absolute;
	z-index: 9999;
	left: 20%;
	top: 20%;
	display:none;
}
.hidlayer1 {
	font-size: 12px;
	position: absolute;
	z-index: 9999;
	left: 20%;
	top: 20%;
	display:none;
	height: 200px;
	width: 200px;
}
-->
</style>
<!--  
<script language="javascript" type="text/javascript" src="../js/newwindow.js"></script>
-->
<style>
table{
	border-collapse:collapse;
}
.new_table{
	border-top-width: 1px;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-left-width: 1px;
	border-top-style: solid;
	border-right-style: none;
	border-bottom-style: none;
	border-left-style: solid;
	border-top-color: #CCC;
	border-left-color: #CCC;
}
.new_table th{
	font-weight: bold;
	text-align: right;
	padding: 2px;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-top-color: #999;
	border-right-color: #999;
	border-bottom-color: #999;
	border-left-color: #999;
}
.new_table td{
	font-weight: bold;
	text-align: left;
	padding: 2px;
	border-top-width: 0px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 0px;
	border-top-style: none;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: none;
	border-right-color: #CCC;
	border-bottom-color: #CCC;
}
</style>
<script   language="JavaScript" type="text/javascript">  
   
  
  $(document).ready(function() {
       	$("#dateFrom").attr("readonly","readonly");
	    $("#dateTo").attr("readonly","readonly");
	     $("#dateFrom").datepicker( {
				dateFormat : 'yy-mm-dd',
				changeMonth : true,
				changeYear : true
		});
		$("#dateTo").datepicker( {
				dateFormat : 'yy-mm-dd',
				changeMonth : true,
				changeYear : true
		});
	});

//自动选中select的值
$(document).ready(function(){
   $("select").each(function(){
      var value= $(this).attr("val");
      $(this).val(value);
   });
});

	$(function() {
		$('#dlg').dialog( {
			autoOpen : false,
			height : 320,
			width : 660,
			modal : true,
			bgiframe : true,
			buttons : {}
		});
	});
	
	
	
function Authorize()
{
   var paymentId="0";
   
   $(":checkbox").each(
	   function()
	   {
	     if(this.checked)
	     paymentId=paymentId+","+this.value;
	   }
   
   );  
   
   if("0"==paymentId)
   {
      alert("please choose one Transaction to operation !");
      return;
   } 
   
   var paramter="paymentIds="+paymentId;
   paramter=paramter+"&from=list";
    $.ajax({
	      type:'post',
	      url :'ar_transaction!Authorize.action',
	      data:paramter,
	      success:function(data){ alert("success!");$("form:first").submit(); },
	      error:function(data){ alert("error!");$("form:first").submit();}
        }); 
   
   
}	


function Unauthorize()
{
	var paymentId="0";	
	var reason=$("#reason").val();
   
   $(":checkbox").each(
	   function()
	   {
	     if(this.checked)
	     paymentId=paymentId+","+this.value;
	   }
   
   );  
   
   if("0"==paymentId)
   {
      alert("please choose one Transaction to operation !");
      return;
   } 
   
   var paramter="paymentIds="+paymentId;
   paramter=paramter+"&reason="+reason;
   paramter=paramter+"&from=list";
     
    $.ajax({
	      type:'post',
	      url :'ar_transaction!Unauthorize.action',
	      data:paramter,
	      success:function(data){ alert("success!");$("form:first").submit();  },
	      error:function(data){ alert("error!");$("form:first").submit(); }
        }); 
   
}


function allOption(obj)
{
   var is_checked=false;
   if(obj.checked)
   is_checked=true;
       
    $(":checkbox").each(
	           function(){
	           this.checked=is_checked;	      
	           }  
       );  
}	
	
	function checkQueryCondition() {
	     
	    var invoiceNo = $("#filter_LIKES_transactionNo").val();
	    if (invoiceNo != '' && !isInt(invoiceNo)) {
			alert("Transcation No must be integer");
			return false;
		}
		var orderNo = $("#filter_EQI_custNo").val();
		if (orderNo != '' && !isInt(orderNo)) {
			alert("Customer No must be integer");
			return false;
		}
		return true;
	}
	
//隔行变色	 
$(function(){
 $("#list_table tr:nth-child(even)").addClass("list_td2");
});

function unUnauthorized(){
var arr = $("#list_table :checked");
if(arr.length ==0){
  alert("Please select at least one transcation item");
  return;
}
$('#dlg')
				.dialog(
						"option",
						"open",
						function() {
							var htmlStr = '<iframe  src="ar_transaction!unauthorizeTransaction.action" height="240" width="610" scrolling="no" style="border:0px" frameborder="0"></iframe>';
							$('#dlg').html(htmlStr);
						});
		$('#dlg').dialog('open');
}

function closeDlg(){
   $("#dlg").dialog("close");
}

</script>


</head>
  
  <body class="content" style="background-image:none;">

<div id="frame12" style="display:none;" class="hidlayer1">
<iframe id="hidkuan" name="hidkuan" src="kuang.html" width="668" height="425" frameborder="0" allowTransparency="true"></iframe>
</div>



<div class="scm">
<div class="title_content">
  <div class="title"> Authorize AR Transactions</div></div>
<div class="search_box">
 <div  class="search_box_three">
    <div class="search_box_two">             
  <form name="searchform" method="post" id="searchform" action="ar_transaction!GetList.action">
 <table  border="0" cellpadding="0" cellspacing="0" class="General_table">
  <tr>
  
     <th>Transaction  No</th>
    <td width="110"><input name="filter_LIKES_transactionNo"  id="filter_LIKES_transactionNo" type="text"  class="NFText"   value="${params.filter_LIKES_transactionNo}" /></td>
    <th>Customer No</th>
    <td width="110"><input name="filter_EQI_custNo" id="filter_EQI_custNo" type="text"    class="NFText"   value="${params.filter_EQI_custNo}" /></td>
   
     <th>Transaction Type</th>
    <td><select name="filter_EQS_transactionType"  style="width:234px;" id="select_st" val="${param.filter_EQS_transactionType }">
               <option value="">please choose</option>
											<c:forEach items="${transactionTypeList}" var="SelectBean">
												<option value="${SelectBean.value }">
													${SelectBean.key }
												</option>
											</c:forEach>
										</select>
    </td>
  </tr>
   <tr> 
    <th>Transaction Date From</th>
    <td><input name="filter_GTD_transactionDate" type="text"  class="NFText"  size="20"  class="ui-datepicker"  id="dateFrom"  value="${params.filter_GTD_transactionDate}" /></td>
    <th>To</th>
    <td><input name="filter_LTD_transactionDate" type="text"  class="NFText"   size="20"  class="ui-datepicker" id="dateTo"    value="${params.filter_LTD_transactionDate}" /></td>
    
     <th>Status</th>
       <td><select name="select2" style="width:132px;"  >
         <option>Draft</option>
       </select></td>
   </tr>
    
  <tr>
    <td colspan="6" align="center">
    	<input type="submit" name="Submit1" value="Search" class="search_input" onclick="return checkQueryCondition();" />
     	<input type="button" name="Submit2" value="Refresh List" onclick="location.href='ar_transaction!GetList.action'" class="search_input" />
    </td>
    </tr>
 </table>
</form>
</div>
 </div>
</div>
        <div class="input_box">
		 <div class="content_box">
         <div >
		  <table width="1010" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><div style="margin-right:17px;"><table width="993" border="0" cellspacing="0" cellpadding="0" class="list_table">
      <tr>
        <th width="46"><div align="left"><input name="choose"  id="choose"  type="checkbox" value="0"   onclick="allOption(this)" /></div></th>
        <th width="95">Transaction No</th>
        <th width="64">Customer </th>
        <th width="55">Status </th>
        <th width="107">Transaction Type</th>
        <th width="62">Amount</th>
        <th width="114">Transaction Fee</th>
        <th width="70">Currency</th>
        <th width="88">Payment Type</th>
        <th width="66">Order No</th>
        <th width="73"> Invoice No</th>
        <th>Transaction Date</th>
      </tr>

    </table></div></td>
  </tr>
  
  <tr>
    <td><div class="list_box" style="height:340px; overflow:scroll;">
    <table width="993" border="0" cellspacing="0" cellpadding="0" id="list_table" class="list_table2">
      <s:iterator value="arInvoicePaymentPage.result">
      <tr>
        <td width="46">
          <input type="checkbox" value="${transactionId}" name="choose" id="choose" />
        </td>
        <td width="95"><a href="ar_transaction!forwardRecord.action?arInvoicePaymentDto.transactionId=${transactionId}">${transactionId}</a></td>
        <td width="64">${custNo}</td>
        <td width="55">${status}</td>
        <td width="107">${transactionType}</td>
        <td width="62">${symbol}${amount}</td>
        <td width="114">${symbol}${transactionFee}</td>
        <td width="70">${currency}</td>
        <td width="88">${paymentType}</td>
        <td width="66">${orderNo}</td>
        <td width="73">${invoiceNo}</td>
        <td><s:date name="transactionDate" format="yyyy-MM-dd"  /></td>
      </tr>
      </s:iterator>
    </table>
    </div></td>
  </tr>

  <tr>
	<td   colspan="12" align="center">

		<div class="grayr">
		<jsp:include page="/common/db_pager.jsp">
		<jsp:param value="${ctx}/ar_transaction!GetList.action"
					name="moduleURL" />
		</jsp:include>
		</div>

</td>
  </tr>
</table>
<input id="reason"	type="hidden"  />	   

</div>

<div class="new_item">

 <input type="button" name="Submit" value="Authorize" class="search_input" onclick="Authorize()" /> 
 <input type="button" name="Submit16" value="Unauthorize" class="search_input" onclick="unUnauthorized()" />
</div>
</div>
</div>	
</div>

<div id="dlg" title=" Unauthorize Transaction "></div>
</body>
</html>
  
   

