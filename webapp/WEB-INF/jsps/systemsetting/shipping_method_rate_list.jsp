<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>

<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}scm/gs.util.js"></script>

<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>

<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"/>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<script>
$(function(){
	$('tr:even >td').addClass('list_td2');  

	$('#rate_add_dialog').dialog({
		autoOpen: false,
		height: 320,
		width: 530,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
			var shipMethodId = document.getElementById('id').value;
			var warehouseId = document.getElementById('warehouseId').value;
			var htmlStr = '<iframe src="shipping_method!showCreateRateForm.action?id='+shipMethodId+ '&warehouseId='+ warehouseId +'" height="270" width="500" style="border:0px" frameborder="0"></iframe>'; 
			$('#rate_add_dialog').html(htmlStr);
		}
	});
	

	$("#btn_add")
		.click(function(){
			var warehouseId = document.getElementById('warehouseId').value;
			if (!warehouseId){
				alert ("Please select warehouse first in zone tab!");
				return;
			}else{
				$('#rate_add_dialog').dialog('open');
			}
	});

	$('#rate_edit_dialog').dialog({
		autoOpen: false,
		height: 320,
		width: 530,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
			var url = $("#editRateDialogTrigger").val();
			var htmlStr = '<iframe src="'+url+'" height="270" width="500" style="border:0px" frameborder="0"></iframe>'; 
			$('#rate_edit_dialog').html(htmlStr);
		}
	});

	$("#editRateDialogTrigger")
		.click(function(){
			$('#rate_edit_dialog').dialog('open');
	});

});

function show_edit_rate(id)
{
	var shipMethodId = document.getElementById('id').value;
	var warehouseId = document.getElementById('warehouseId').value;
	var href = 'shipping_method!showEditRateForm.action?rateId='+id+'&id='+shipMethodId+'&warehouseId='+warehouseId;
	$('#editRateDialogTrigger').val(href);
	$('#editRateDialogTrigger').click();	
}
function search_rate(pageNo){
	if (!pageNo){
		pageNo = 1;
	}
	var id = document.getElementById('id').value;
	var warehouseId = document.getElementById('warehouseId').value;
	rateForm = document.getElementById('list_rate_form');
	document.getElementById('pageNo').value = pageNo;
	rateForm.submit();
}
function del_rate(){
	if(!confirm("Are you sure to delete?")){
		return;
	}
	var id = document.getElementById('id').value;
	var to_del = getDelIds('chkRate');
	var reqUrl = "shipping_method!delInSession.action?type=Rate&toDel="+to_del+"&id="+id;
	var pageNo = document.getElementById('pageNo').value;
	//alert (reqUrl);
	$.ajax({
	    type: "POST",
	    url: reqUrl,
		success:function(data)
		{
			//alert (data);
			if (data == 'SUCCESS'){
				search_rate(pageNo);	
			}else{
				alert(data);
				return;
			}
		},
		async: false
	}); 
	
}
</script>
</head>
<body>
<form id="list_rate_form" method="post" action="shipping_method!listRate.action">
<input type="hidden" id="pageNo" name="pageNo" />
<input type="hidden" id="warehouseId" name="warehouseId" value="${warehouseId}" />
<input type="hidden" id="id" name="id" value="${id}" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th width="5%" align="right" valign="top">Rate</th>
      <td width="31%">
        <input id="standardRateValue" type="text" class="NFText" value="${sessionScope.standardRateValue[id]}" size="35" readonly="readonly" />
     </td>
      <th width="14%">&nbsp;</th>
      <td width="50%">&nbsp;</td>
    </tr>
  </table>
</form>
<br />
<div id="tb3" style='display:block;'>
  <table width="960" border="0" cellpadding="0" cellspacing="0" class="list_table">

    <tr>
      <th width="46"><div align="left">
        <input name="checkbox4" type="checkbox" onclick="setAll(this, 'chkRate')" />
		  <a href="javascript:void(0)" onclick="del_rate()" title="Delete" rel="gb_page_center[600,  180]"><img src="images/file_delete.gif" alt="Delete" width="16" height="16" border="0" /></a></div></th>
      <th width="151">Zone</th>
      <th width="236">From Weight</th>
      <th width="254">To Weight</th>

      <th>Charge</th>
    </tr>
  </table>
  <div class="frame_box3" style="height:250px; width:975px;">
    <table width="960" border="0" cellpadding="0" cellspacing="0"  class="list_table">
		<s:iterator value="retRateList">
      <tr>
        <td width="46"><input type="checkbox" value="${idStr}" name="chkRate" /></td>
        <td width="151">&nbsp;<a href="javascript:void(0)" onclick="javascript:show_edit_rate('${idStr}')" title="${zoneCode}" rel="gb_page_center[550,  300]">${zoneCode}</a></td> 
        <td width="236">&nbsp;${weightFrom}</td>
        <td width="254">&nbsp;${weightTo}</td>
        <td>&nbsp;${charge}</td>
      </tr>
		</s:iterator>
    </table>
  </div>
		<div class="grayr"><jsp:include page="/common/db_pager.jsp">
					<jsp:param value="${ctx}/shipping_method!listRate.action?warehouseId=${warehouseId}&id=${id}"
						name="moduleURL" />
				</jsp:include></div>
		<div class="botton_box">
		<input id="btn_add" type="button" class="style_botton" value="New" />
		</div>
</div>
<input type="hidden" id="editRateDialogTrigger" />
<div id="rate_add_dialog"  title="Rate Information"> </div>
<div id="rate_edit_dialog"  title="Rate Information"> </div> 
</body>
</html>
