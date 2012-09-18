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
   
  
 


	$(function() {
		$('#dlg').dialog( {
			autoOpen : false,
			height : 320,
			width : 660,
			modal : true,
			bgiframe : true,
			buttons : {}
		});
		
		$("#transcationType").val("${param.transcationType }");
		
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
      alert("please choose one Allocation to operation !");
      return;
   } 
   
   var paramter="allctions="+paymentId;
   paramter=paramter+"&from=list";
    $.ajax({
	      type:'post',
	      url :'ar_approve_allocation!approveBatch.action',
	      data:paramter,
	      success:function(data){ 
	      eval("res="+data);
	      if(res.msg!=''){
	        alert("Allocation id "+res.msg +" Approve unsuccess!")
	      }
	      $("form:first").submit(); 
	      },
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
	      url :'ar_approve_allocation!unapproveBatch.action',
	      data:paramter,
	      success:function(data){
	       alert("success!");
	       $("form:first").submit();  
	       },
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
	     
	    var transactionNo = $("#transactionNo").val();
	    if (transactionNo != '' && !isInt(transactionNo)) {
			alert("TransactionNo No must be integer");
			return false;
		}
		var custNo = $("#custNo").val();
		if (custNo != '' && !isInt(custNo)) {
			alert("Customer No must be integer");
			return false;
		}
		var invoiceNo = $("#invoiceNo").val();
			if (invoiceNo != '' && !isInt(invoiceNo)) {
			alert("Invoice No must be integer");
			return false;
		}
		return true;
	}
	
//隔行变色	 
$(function(){
 $("#list_table tr:nth-child(even) td").each(function(){
    $(this).addClass("list_td2");
 })
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
							var htmlStr = '<iframe  src="ar_approve_allocation!unAuthPage.action" height="240" width="610" scrolling="no" style="border:0px" frameborder="0"></iframe>';
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
 
 
<div class="scm">
<div class="title_content">
  <div class="title_new"><a   id="total_titleItem"><img src="${ctx }/images/arrow1.jpg" /></a>&nbsp;Authorize AR Allocation</div></div>
<div class="search_box" id="total_title">
 <div  class="search_box_three">
 <form name="form1" method="get" action="ar_approve_allocation!getList.action">
   <table  border="0" cellpadding="0" cellspacing="0" class="General_table">
     <tr>
       <th>Transaction No</th>
       <td width="110"><input name="transactionNo" id="transactionNo" type="text" class="NFText" size="20" value="${param.transactionNo }" /></td>
       <th>Customer No</th>
       <td width="110"><input name="custNo" id="custNo" type="text" class="NFText" size="20" value="${param.custNo }" /></td>
       <th>Transaction Type</th>
       <td><select name="transcationType" id="transcationType" val="${param.transcationType }" style="width:132px;">
          <option value=""></option>
         ${select }
       </select></td>
       </tr>
     <tr>
       <th>Invoice No</th>
       <td><input name="invoiceNo" id="invoiceNo" type="text" value="${param.invoiceNo }" class="NFText" size="20" /></td>
       <th>&nbsp;</th>
       <td>&nbsp;</td>
       <th>&nbsp;</th>
       <td>&nbsp;</td>
       </tr>
     <tr>
       <td colspan="6" align="center"> <input type="submit" name="Submit3" value="Search" class="search_input" onclick="return checkQueryCondition();"/>
     <input type="submit" name="Submit4" value="Refresh List" class="search_input" onclick="window.location.href = 'ar_approve_allocation!getList.action'" /></td>
       </tr>
   </table>
 </form>
 </div>
</div>
        <div class="input_box">
		 <div class="content_box">
         <div >
		  <table width="1010" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><div style="margin-right:17px;"><table width="993" border="0" cellspacing="0" cellpadding="0" class="list_table">
      <tr>
        <th width="46"><div align="left"><input name="checkbox2" type="checkbox"  onclick="allOption(this)" />
        </div>
        </th>
        <th width="80">Customer No</th>
        <th width="100">Transaction No</th>
        <th width="60">Status </th>
        <th width="112">Transaction Type</th>
         <th width="93">Invoice No</th>
        <th width="93">Invoice Type</th>
        <th width="115">Allocation Amount</th>
        <th width="87">Creation Date</th>
        <th>Created By</th>
      </tr>
 
    </table></div></td>
  </tr>
  <tr>
    <td> <div class="list_box" style="height:340px; overflow:scroll;"><table width="993" border="0" cellspacing="0" cellpadding="0" id="list_table" class="list_table2">
  <s:iterator value="allocationPage.result">
      <tr>
        <td width="46">
          <input type="checkbox" value="${id }" name="mm33"/>
        </td>
        <td width="80">${custNo }</td>
        <td width="100"><a href="ar_invoice_payment!forwardRecord.action?arInvoicePaymentDto.transactionId=${ transactionId}&method=view">${transactionId }</a></td>
        <td width="60">${status }</td>
        <td width="112">${ transcationType}</td>
        <td width="93"><a href="ar_invoice!edit.action?invoiceId=${invoiceId }&invoiceNo=${invoiceId }&method=view">${invoiceId }</a></td>
        <td width="93">${invoiceType }</td>
        <td width="115">${symbol}${applyAmount}</td>
        <td width="87"><s:date format="yyyy-MM-dd" name="creationDate"/>  </td>
        <td >${loginName}</td>
      </tr>
      </s:iterator>
    </table>
   </td>
  </tr>
  <tr>
  <td>
  
		<div class="grayr">
		<jsp:include page="/common/db_pager.jsp">
		<jsp:param value="${ctx}/ar_approve_allocation!getList.action"
					name="moduleURL" />
		</jsp:include>
		</div>
  
  </td>
  </tr>
</table>
		
		<input type="hidden" value="" id="status_upd_reason" name="StatusUpdReason" >   
 
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
