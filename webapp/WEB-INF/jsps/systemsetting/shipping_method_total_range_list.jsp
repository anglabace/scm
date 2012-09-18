<%-- 
    Document   : shipping_method_total_range_list
    Created on : 2010-10-12, 11:24:07
    Author     : Lichun Cui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include  file="/common/taglib.jsp" %>
<!-- {get_spec_selects value="WAREHOUSE"} -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base id="myBaseId" href="${global_url}" />
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
var baseUrl = "${global_url}";
$(function(){
	$('tr:even >td').addClass('list_td2');

	$('#total_range_add_dialog').dialog({
		autoOpen: false,
		height: 270,
		width: 530,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
			var htmlStr = '<iframe src="system/shipping_method!createTotalRange.action?id=${id}&idStr=${idStr}" height="220" width="500" style="border:0px" frameborder="0"></iframe>';
			$('#total_range_add_dialog').html(htmlStr);
		}
	});

	$("#btn_add")
		.click(function(){
			$('#total_range_add_dialog').dialog('open');
	});

	$('#total_range_edit_dialog').dialog({
		autoOpen: false,
		height: 270,
		width: 530,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
			var url = $("#editTotalRangeDialogTrigger").val();
			var htmlStr = '<iframe src="'+url+'" height="220" width="500" style="border:0px" frameborder="0"></iframe>';
			$('#total_range_edit_dialog').html(htmlStr);
		}
	});

	$("#editTotalRangeDialogTrigger")
		.click(function(){
			$('#total_range_edit_dialog').dialog('open');
	});
});

function show_edit_total_range(id)
{
	var href = 'system/shipping_method!createTotalRange.action?id=${id}&idStr=${idStr}&range_id='+id;
	$('#editTotalRangeDialogTrigger').val(href);
	$('#editTotalRangeDialogTrigger').click();
}

function del_total_range(){
	if(!confirm("Are you sure to delete?")){
		return;
	}
	var to_del = getDelIds('chkTotalRange');
	var reqUrl = baseUrl + "system/shipping_method!delInSession4Charge.action?id=${id}&idStr=${idStr}&type=RateTotalRange&to_del="+to_del;
	//alert (reqUrl);
	$.ajax({
	    type: "POST",
	    url: reqUrl,
		success:function(data)
		{
			//alert (data);
			//if (data == 'SUCCESS'){
				window.location.reload();
			//}else{
			//	alert(data);
			//	return;
			//}
		},
		async: false
	});

}
</script>
</head>
<body>

      <div id="tb1" style='display:block;'>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td>Note: Customer Charges Based on Order Total (First Shipment Only)</td>
			</tr>
			<tr>
			<td><input type="checkbox" name="sepTotForAddr" id="sepTotForAddr" <s:if test="sepTotForAddr==\"Y\"">checked</s:if>/>
			Calculate Merchandise Total Separately for Each Ship-to Address</td>
			</tr>
			<tr>
			<td>&nbsp;</td>
			</tr>
		</table>

         <table width="920" border="0" cellpadding="0" cellspacing="0" class="list_table">
           <tr>
             <th width="46"><div align="left">
				<input name="checkbox3" type="checkbox" onclick="setAll(this, 'chkTotalRange')" />
				  <a href="javascript:void(0)" onclick="del_total_range()" title="Delete" rel="gb_page_center[600,  180]"><img src="images/file_delete.gif" alt="Delete" width="16" height="16" border="0" /></a></div></th>
             <th width="219">From Total</th>
             <th width="219">To Total</th>
             <th width="219">Charge</th>
             <th>Charge Percentage (of Total)</th>
           </tr>
       </table>
         <div  class="frame_box6" style="height:200px;width:930px;">
             <table width="920" border="0" cellpadding="0" cellspacing="0" class="list_table">			
               <s:iterator value="shipRateTotalRangeMap" status="shipRateTotalRangeMap">
              <tr>
                  <td width="46"><input type="checkbox" value="${key}" name="chkTotalRange" <s:if test="key==null||key==\"\"">disabled</s:if>/></td>
                  <td width="219">&nbsp;<a href="javascript:void(0)" onclick="javascript:show_edit_total_range('${key}')" title="<s:property value="value.totalFrom"/>" rel="gb_page_center[550,  300]"><s:property value="value.totalFrom"/></a></td>
                  <td width="219">&nbsp;<s:property value="value.totalTo"/></td>
                  <td width="219">&nbsp;<s:property value="value.charge"/></td>
                  <td>&nbsp;<s:property value="value.chargePct"/></td>
              </tr>
              </s:iterator>			
            </table>
        </div>
		<div class="botton_box">
		<input id="btn_add" type="button" class="style_botton" value="New" />
		</div>
		
      </div>

<input type="hidden" id="editTotalRangeDialogTrigger" />
<div id="total_range_add_dialog"  title="Total Range Information"> </div>
<div id="total_range_edit_dialog"  title="Total Range Information"> </div>
</body>
</html>

