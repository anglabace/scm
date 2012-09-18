<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp" %>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>scm</title>
<base  href="${global_url}" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script src="${global_js_url}jquery/jquery.js" type="text/javascript" language="javascript"></script>
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>	
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<script src="${global_js_url}initlefttop.js" type="text/javascript"></script>
<script  language="JavaScript" type="text/javascript">
function cc(e, name){
	var  a =   document.getElementsByName(name);
	for   (var   i=0;   i<a.length;   i++)   
		if(a[i].disabled == false) 
			a[i].checked   =   e.checked;
}  

function del_quote(name){
	var del_quote_nos = get_checked_str(name);
	var quoteNos = del_quote_nos.split(",");
	var noData = "quoteNos="+quoteNos.join("&quoteNos=");
	var statusReason = $("#statusReason").find("option:selected").text();
	var status = $("#statusReason").val();
	var comment = $("#comment").val();
	if(comment == ""){
		alert("Please enter the Note.");
		$("#comment").focus();
		return;
	}
	$.ajax({
		type: "POST",
		url: "quote/quote!delete.action",
		data: noData+'&status='+status+'&statusReason='+statusReason+'&comment='+comment,
		success: function(msg){
			if(msg == 'success'){
				$('#delQuoteDialog').dialog('close');
				alert('Delete quote successfully');
			}else if(msg == 'error'){
				alert("You don't have permission to cancel quote, please contact your supervisor.");	
			}else {
				alert('Unknown error');
			}
			$(":checkbox").attr("checked", false);
			window.location.reload();
		},
		error: function(msg){
			alert("Failed to cancel the quotation.");
		}
	});
	return false;
}

function get_checked_str(name){
	var a = document.getElementsByName(name);
	var str = '';
	for   (var   i=0;   i<a.length;   i++){
		if(a[i].checked){
			str += a[i].value+',';
		}
	}
	return str.substring(0,str.length-1);
}

$(document).ready(function(){
	$("#resultTable tr:odd").find("td").addClass("list_td2");
	parent.$('#srchCustAct_iframe').attr("height",430);
	// more phones set dialog and trigger
	$('#delQuoteDialog').dialog({
		autoOpen: false,
		height: 250,
		width: 620,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
    
	$('#delQuoteDialogTrigger').click(function(){
		var del_quote_nos = get_checked_str("quoteNo");
		if(del_quote_nos == ''){
			alert('Please select one at least!');
			return;
		}
		$('#delQuoteDialog').dialog('open');
	});
	
});
</script>
</head>
<body onload="rtnPreLeftTop('Quotation Processing');">

<div style="margin-right:17px">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                <tr>
                  <th width="50">
                  	<div align="left">
                  		<input type="checkbox"  onclick="cc(this, 'quoteNo')" />
             		<img id="delQuoteDialogTrigger" src="${global_image_url}file_delete.gif" alt="Delete" width="16" height="16" border="0" />
                    </div>
                   </th>
				    <th width="80">Quote No</th>
				    <th width="60">Status</th>
				    <th width="90">Customer No</th>
				    <th width="90">Customer</th>
				    <th width="200">Address</th>
				    <th width="85">Quotation Date</th>
				    <th width="85">Expiration Date</th>
				    <th width="60">Amount </th>
				    <th width="85">Sales Manager</th>
				    <th>Order No</th>
				    <th  width="85">FollowUp Date</th>
				    <th width="80">Created By</th>
                </tr>
				</table>
				</div>
				<div class="list_box" style="height:340px;">
				<table id="resultTable" width="100%" border="0" align="center" cellpadding="0" cellspacing="0"  class="list_table">
					<s:iterator value="quoteList">
					<tr>
			        <td width="50">
	      				<div align="left">
	      					<div align="left">
	   							<s:if test="status == 'CO' || status == 'VD' || status == 'CW'">
	   								<input type="checkbox" value="${quoteNo}" name="quoteNo" disabled="disabled"/>
	   						 	</s:if>
	       						<s:else>
	       							<input type="checkbox" value="${quoteNo}" name="quoteNo" />
	       						</s:else>
	   						</div>
	      				</div>
	      			</td>
				    <td width="80"><div align="center">&nbsp;<a href="quote/quote!edit.action?quoteNo=${quoteNo}&custNo=${custNo}&tempStatus=${status}&operation_method=edit" target="mainFrame">${quoteNo} </a></div></td>
				    <td width="60" >&nbsp;${fulStatus}</td>
				    <td width="90" >&nbsp;${custNo}</td>
				    <td width="90">&nbsp;
				    ${firstName}
				    <c:if test="${! empty lastName}"> </c:if>
				    ${lastName}<c:if test="${! empty midName}">, </c:if>
				    ${midName}
				    </td>
				    <td width="200" >&nbsp;
				    <s:if test="%{addressMap[quoteNo].length()>50}">
						<s:property value="addressMap[quoteNo].substring(0,50)+'...'"/>
					</s:if>
					<s:else><s:property value="addressMap[quoteNo]"/></s:else></td>
				    <td width="85">&nbsp;<s:date name="quoteDate" format="yyyy-MM-dd"></s:date></td>
				    <td width="85">&nbsp;<s:date name="exprDate" format="yyyy-MM-dd"></s:date></td>
				    <td width="60" align="right">
						&nbsp;${symbol}
			    		<c:if test="${symbol != '¥'}">
			    			<fmt:formatNumber value="${amount}" pattern="#,###,###,##0.00" />
			    		</c:if>
			    		<c:if test="${symbol == '¥'}">
			    			<fmt:formatNumber value="${amount}" pattern="#,###,###,###" />
			    		</c:if>
					</td>
				    <td width="85">&nbsp;${salesContact}</td>
				    <c:if test="${orderNo==0}"> <td>&nbsp;</td></c:if>
				    <c:if test="${orderNo!=0}"> <td>&nbsp;<a href="${global_url}order/order!edit.action?orderNo=${orderNo}&operation_method=edit" target="mainFrame">${orderNo}</a></td></c:if>
				   <td width="85"><s:property value="followUpDateMap[quoteNo]"/></td>
				    <td width="80">${createdBy}</td>
				  	</tr>
				</s:iterator>
  				</table>
</div>
<div class="grayr">
	<jsp:include page="/common/db_pager.jsp">
	 	 <jsp:param value="quote_search!quoteList.action" name="moduleURL"/>
	</jsp:include>
</div>
<div id="delQuoteDialog" title="Cancel Quote" style="display: none;">
	<s:include value="quote_update_status.jsp"></s:include>
</div>
</body>
</html>
