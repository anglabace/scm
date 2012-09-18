<%-- 
    Document   : shipping_method_weight_range_list list shipping method weight
    Created on : 2010-10-12, 13:40:46
    Author     : Lichun Cui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include  file="/common/taglib.jsp" %>
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

	$('#weight_range_add_dialog').dialog({
		autoOpen: false,
		height: 270,
		width: 530,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
			var htmlStr = '<iframe src="system/shipping_method!createWeightRange.action?id=${id}&idStr=${idStr}" height="220" width="500" style="border:0px" frameborder="0"></iframe>';
			$('#weight_range_add_dialog').html(htmlStr);
		}
	});


	$("#btn_add")
		.click(function(){
			$('#weight_range_add_dialog').dialog('open');
	});


	$('#weight_range_edit_dialog').dialog({
		autoOpen: false,
		height: 270,
		width: 530,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
			var url = $("#editWeightRangeDialogTrigger").val();
			var htmlStr = '<iframe src="'+url+'" height="220" width="500" style="border:0px" frameborder="0"></iframe>';
			$('#weight_range_edit_dialog').html(htmlStr);
		}
	});

	$("#editWeightRangeDialogTrigger")
		.click(function(){
			$('#weight_range_edit_dialog').dialog('open');
	});


});

function show_edit_weight_range(id)
{
	var href = 'system/shipping_method!createWeightRange.action?id=${id}&idStr=${idStr}&weight_id='+id;
	$('#editWeightRangeDialogTrigger').val(href);
	$('#editWeightRangeDialogTrigger').click();
}

function del_weight_range(){
	if(!confirm("Are you sure to delete?")){
		return;
	}

	var to_del = getDelIds('chkWeightRange');
	var reqUrl = baseUrl + "system/shipping_method!delInSession4Charge.action?id=${id}&idStr=${idStr}&type=RateWeightRange&to_del="+to_del;
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
				//alert(data);
				//return;
			//}
		},
		async: false
	});

}
</script>
</head>
<body>
	   <table width="920" border="0" cellpadding="0" cellspacing="0" class="list_table">
	     <tr>
	       <th width="46"><div align="left">
			<input name="checkbox3" type="checkbox" onclick="setAll(this, 'chkWeightRange')" />
              <a href="javascript:void(0)" onclick="del_weight_range()" title="Delete" rel="gb_page_center[600,  180]"><img src="images/file_delete.gif" alt="Delete" width="16" height="16" border="0" /></a></div></th>
	       <th width="280">From Weight</th>
	       <th width="280">To Weight</th>
	       <th>Charge</th>
		  </tr>
       </table>

	   <div  class="frame_box6" style="height:250px;width:930px;">
            <table width="920" border="0" cellpadding="0" cellspacing="0"  class="list_table">
			
                        <s:iterator value="shipRateWeightRangeMap" status="shipRateWeightRangeMap">
              <tr>
                  <td width="46"><input type="checkbox" value="${key}" name="chkWeightRange" <s:if test="key==null||key==\"\"">disabled</s:if>/></td>
                  <td width="280">&nbsp;<a href="javascript:void(0)" onclick="javascript:show_edit_weight_range('${key}')" title="<s:property value="value.weightFrom"/>" rel="gb_page_center[550,  300]"><s:property value="value.weightFrom"/></a></td>
                  <td width="280">&nbsp;<s:property value="value.weightTo"/></td>
                  <td>&nbsp;<s:property value="value.charge"/></td>
              </tr>
              </s:iterator>
			
            </table>
        </div>
		<div class="botton_box">
		<input id="btn_add" type="button" class="style_botton" value="New" />
		</div>

<input type="hidden" id="editWeightRangeDialogTrigger" />
<div id="weight_range_add_dialog"  title="Weight Range Information"> </div>
<div id="weight_range_edit_dialog"  title="Weight Range Information"> </div>
</body>
</html>
