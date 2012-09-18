<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>


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
<!--greybox -->
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
<script language="javascript" type="text/javascript" src="${global_js_url}tools.js"></script>
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>

<script type="text/javascript">

  var email="";
  var cust_no = 0;  

  $(function() {
 $('#sendMail').dialog({
autoOpen: false,
height: 350,
width: 700,
modal: true,
bgiframe: true,
buttons: {
}
});
});



  function openMailDlg(){
           $('#sendMail').dialog("option", "open", function() { 
        	var htmlStr = '<iframe  src="ar_invoice!notifyCustomer.action?cust_no='+cust_no+'" height="300" width="650" scrolling="no" style="border:0px" frameborder="0"></iframe>';
         	$('#sendMail').html(htmlStr);
		    });
		    $('#sendMail').dialog('open');
  }
  
  function openDialog(){
    var emails = new Array();
    var custs = new Array();
    $("#list_table2 tr td :checked").each(function(i){
        emails[i] = $(this).val();
         custs[i] = $(this).attr("cust_no");
    });
   
  if(emails.length<1){
  //没有选中任何发票
  alert(" You have not selected any invoice !");
  return;
  }
  if(emails.length == 1){
     email = emails[0];
     cust_no = custs[0];
     openMailDlg();
  }else{
    var oldEmail = emails[0];
    for(var i=0;i<emails.length;i++){
      if(oldEmail != emails[i]){
        alert("Recipients are different ! Please Send Mail one by one");
        return;
      }
    }
      email = emails[0];
      cust_no = custs[0];
   openMailDlg();
  }

  }

function cc(obj){
    if(obj.checked){
       $("input[type=checkbox]").each(function(){
           $(this).attr("checked","checked");
       });
    }else{
       $("input[type=checkbox]").each(function(){
          $(this).removeAttr("checked");
       });
    }
}



  //获取隐藏的email
  /*
  function getEmails(){
        var emails = new Array();
    	$("#list_table2   tr  :input").each(function(i){
    	   var s = $(this).parent().html();
    	   var start = 0;var end = 0;
    	   if(s.indexOf("checkbox")==-1){
    	     start = s.indexOf("value=");
    	     end = s.indexOf(" type");
    	     value = s.substring(start+6,end);
    	     emails.push(value);
    	   }
    	});
    	alert(emails[0]);
  } */
  

function closeDlg(){
  window.parent.$("#sendMail").dialog('close');
}


</script>

</head>

<body class="content" style="background-image:none;">
<input name="Submit10" type="submit" class="style_botton" value="Update" onclick="newiframe('acl_new.html','800','600')" style="display:none;"/>
<div id="frame12" style="display:none;" class="hidlayer1">
<iframe id="hidkuan" name="hidkuan" src="${ctx }/html/kuang.html" width="668" height="425" frameborder="0" allowTransparency="true"></iframe>
</div>



<div class="scm">
<div class="title_content">
  <div class="title"> View Orderdue Invoice</div></div>
<div class="input_box">
    <div class="content_box">
         <div >
		  <table width="1010" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table width="896" height="42" border="0" cellpadding="0" cellspacing="0" class="list_table">
      <tr>
        <th width="81"><div align="left">
          <input name="checkbox2" type="checkbox"  onclick="cc(this)" />
        </th>
        <th width="156">Invoice No</th>
        <th width="154">Order No</th>
        <th width="182">Order Date</th>
        <th width="151">Dates</th>
        <th width="171">Comment</th>
      </tr>
    </table>
      <table width="896" border="0" cellspacing="0" cellpadding="0" id="list_table2" class="list_table2">
      <c:set var="rowcount" value="1"></c:set>
      <s:iterator value="overDuePage.result">
       <c:if test="${rowcount mod 2 == 0}">
             <c:set var="tdclass" value=" class='list_td2'"></c:set>
              </c:if>	
             <c:if test="${rowcount mod 2 == 1}">
                <c:set var="tdclass" value=""></c:set>
              </c:if>
        <tr >
          <td width="81" ${tdclass }><input type="checkbox"  value="${busEmail }" cust_no="${custNo }" name="mm33"/></td>
          <td width="156" ${tdclass }>${invoiceId }</td>
          <td width="154" ${tdclass }>${orderNo }</td>
          <td width="182" ${tdclass }><s:date name="orderDate" format="yyyy-MM-dd" /></td>
          <td width="151" ${tdclass }><s:date name="exprDate" format="yyyy-MM-dd" /></td>
          <td width="171" ${tdclass }>${comment }</td>
          <input type="hidden" name="busEmail" value="${invoiceId }"/>
        </tr>
        <c:set var="rowcount" value="${rowcount+1}"></c:set>
        </s:iterator>
        
      </table></td>
  </tr>

							<tr>
								<td>
									<div class="grayr">
										<jsp:include page="/common/db_pager.jsp">
											<jsp:param value="${ctx}/ar_invoice!viewOverDueInvoice.action"
												name="moduleURL" />
										</jsp:include>
									</div>
									<!--  
									 <div class="grayr"><span class="disabled">< </span><span class="current">1</span><span class="disabled"> > </span></div>
								     -->
								</td>
							</tr>

  <tr>
  <td>&nbsp;</td>
  </tr>
</table>
		   

</div>

<div class="new_item">
<input type="button" name="Submit16" value="Notify Customer" class="search_input"  onclick="openDialog()"/>
<input type="button" name="Submit193" value="View Uninvoiced Shipments" class="search_input3"  onclick="window.openiframe('search_uninvoiced.html','664','380')" disabled="disabled" style="display:none;"/>
</div>
    </div>
  </div>	
	
  <div>

</div>

<div id="sendMail" title=" Notify Customer "></div>
</body>
</html>
