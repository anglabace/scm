<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp" %>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script src="${global_js_url}jquery/jquery.js" type="text/javascript" language="javascript"></script>
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>

<script  language="JavaScript" type="text/javascript">
function cc(e, name){
	var  a =   document.getElementsByName(name);
	for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;
}

function del_order(name){
	var del_order_nos = get_checked_str(name);
	var orderNos = del_order_nos.split(",");
	var noData = "orderNos="+orderNos.join("&orderNos=");
	var statusReason = $("#statusReason").val();
	var comment = $("#comment").val();
	if(comment == ""){
		alert("Note is required");
		$("#comment").focus();
		return;
	}
	$.ajax({
		type: "POST",
		url: "order/order!delete.action",
		data: noData+'&statusReason='+statusReason+'&comment='+comment,
		success: function(msg){
			if(msg == 'success'){
				$('#delOrderDialog').dialog('close');
				alert('Delete order successfully')
			}else if(msg == 'error'){
				alert('Fail to delete order');	
			}else {
				alert('Unknown error');
			}
			$(":checkbox").attr("checked", false);
			window.location.reload();
		},
		error: function(msg){
			alert("Error: Delete order failed");
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
	$('#delOrderDialog').dialog({
		autoOpen: false,
		height: 250,
		width: 620,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
    
	$('#delOrderDialogTrigger').click(function(){
		var del_order_nos = get_checked_str("orderNo");
		//alert(del_order_nos);
		if(del_order_nos == ''){
			alert('Please select one at least!');
			return;
		}
		$('#delOrderDialog').dialog('open');
	});
	$("#genPurchaseOrderTrigger").click(function(){
		var order_nos = get_checked_str("orderNo");
		//alert(order_nos);
		if(order_nos == ''){
			alert('Please select one at least!');
			return;
		}
		parent.$("#purchaseOrderDialog").dialog({
			autoOpen: false,
			height: 350,
			width: 580,
			modal: true,
			bgiframe: true,
			buttons: {
		},
			open: function(){
				var url = "cust_purchase_order!input.action?orderNoStr="+order_nos;
				var htmlStr = '<iframe id="newPurcharseOrderIframe" src="'+url+'" height="300" width="540" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
				parent.$('#purchaseOrderDialog').html(htmlStr);
			}
		});
		parent.$("#purchaseOrderDialog").dialog("open");
	});
});
</script>
</head>
<body>
<div style="margin-right:17px;">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
	    <tr>
            <th width="46">
           		<div align="left">
             		<input type="checkbox"  onclick="cc(this, 'orderNo')" />
             		<!-- 
             		<img id="delOrderDialogTrigger" src="${global_image_url}file_delete.gif" alt="Delete" width="16" height="16" border="0" /> -->
            	</div>
		    </th>
		    <th width="94">US SO No</th>
		    <th width="80">ERP Nanjing No</th>
		    <th width="60">Status</th>
		    <th width="100">Type</th>
		    <th width="70">Priority</th>
		    <th width="73">Customer</th>
		    <th width="80">Order Date</th>
		    <th width="80">Due Date</th>
		    <th width="90">Total Cost</th>
		    <th width="100">Sales Contact</th>		    
		    <th>Sales Center</th>
	    </tr>
	</table>
</div>
<div class="list_box" style="height:340px;">
	<table id="resultTable" width="100%" border="0" align="center" cellpadding="0" cellspacing="0"  class="list_table">
		<s:iterator value="orderList">
			 <tr>
		         <td  width="46">
	   						<div align="left">
	       							<input type="checkbox" value="${srcSoNo}" name="orderNo" />
	   						</div>
	   					</td>
			    <td width="94">
			    	<div align="center">&nbsp;
			    		<a href="${global_url}order/order!edit.action?orderNo=${srcSoNo}&purchaseOrderFlag=0&salesOrderFlag=1&billAccCode=${billAccCode}&operation_method=edit" target="mainFrame">${srcSoNo}</a>
			    	</div>
			    </td>
			    <td width="80">&nbsp;${erpNjSo}</td>
			   <td width="60" >&nbsp;${status}</td>
			     <td width="100">&nbsp;${type }</td>
			     <td width="70">&nbsp;${priority }</td>
			   <!-- 
			   <td width="173">&nbsp;
			    	${firstName}
				    <c:if test="${! empty lastName}"> </c:if>
				    ${lastName}<c:if test="${! empty midName}">, </c:if>
				    ${midName}
			    </td>
			     -->
			     <td width="73">&nbsp;${billAccCode}</td>
			    <td width="80">&nbsp;<s:date name="orderDate" format="yyyy-MM-dd"/></td>
			    <td width="80">&nbsp;<s:date name="exprDate" format="yyyy-MM-dd"/></td>
			    <td width="90">&nbsp;${symbol}
			    <c:if test="${symbol != '¥'}">
			    	<fmt:formatNumber value="${subTotal}" pattern="#,###,###,##0.00" />
			    </c:if>
			    <c:if test="${symbol == '¥'}">
			    	<fmt:formatNumber value="${subTotal}" pattern="#,###,###,###" />
			    </c:if>
			    </td>
			    <td width="100">&nbsp;${salesContact}</td>
			    
			    <td>&nbsp;${companyName}</td>
		  </tr>
		</s:iterator>
	</table>
</div>
<div class="grayr">
	<jsp:include page="/common/db_pager.jsp">
	  <jsp:param value="${ctx}/purchase/cust_purchase_order_srch.action" name="moduleURL"/>
	</jsp:include>	
</div>
<!--
<div align="center">
<input class="search_input3" type="button" name="genPurchaseOrderTrigger" id="genPurchaseOrderTrigger" value="Generate Purchase Order"/>
</div>
-->
</body>
</html>
