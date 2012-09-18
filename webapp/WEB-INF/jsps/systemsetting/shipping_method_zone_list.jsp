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

	$('#zone_add_dialog').dialog({
		autoOpen: false,
		height: 320,
		width: 530,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
			var shipMethodId = document.getElementById('id').value;
			var warehouseSlt = document.getElementById('warehouseSlt');
			var warehouseId = warehouseSlt.options[warehouseSlt.selectedIndex].value;
			var htmlStr = '<iframe src="shipping_method!showCreateZoneForm.action?id='+shipMethodId+ '&warehouseId='+ warehouseId +'" height="270" width="500" style="border:0px" frameborder="0"></iframe>'; 
			$('#zone_add_dialog').html(htmlStr);
		}
	});
	

	$("#btn_add")
		.click(function(){
			var slt = document.getElementById('warehouseSlt');
			if (!slt.options[slt.selectedIndex].value){
				alert ("Please select warehouse first!");
				return;
			}else{
				$('#zone_add_dialog').dialog('open');
			}
	});


	$('#zone_edit_dialog').dialog({
		autoOpen: false,
		height: 320,
		width: 530,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
			var url = $("#editZoneDialogTrigger").val();
			var htmlStr = '<iframe src="'+url+'" height="270" width="500" style="border:0px" frameborder="0"></iframe>'; 
			$('#zone_edit_dialog').html(htmlStr);
		}
	});

	$("#editZoneDialogTrigger")
		.click(function(){
			$('#zone_edit_dialog').dialog('open');
	});
	

});

function show_edit_zone(id)
{
	var shipMethodId = document.getElementById('id').value;
	var warehouseSlt = document.getElementById('warehouseSlt');
	var warehouseId = warehouseSlt.options[warehouseSlt.selectedIndex].value;
	var href = 'shipping_method!showEditZoneForm.action?zoneId='+id+'&id='+shipMethodId+'&warehouseId='+warehouseId;
	$('#editZoneDialogTrigger').val(href);
	$('#editZoneDialogTrigger').click();	
}

function search_zone(pageNo){
	if (!pageNo){
		pageNo = 1;
	}
	var id = document.getElementById('id').value;
	var warehouseSlt = document.getElementById('warehouseSlt');
	var warehouseId = warehouseSlt.options[warehouseSlt.selectedIndex].value;
	//refresh iframe rate_frame
	var rateFrame = parent.document.getElementById('rate_frame');
	rateFrame.src = "shipping_method!listRate.action" + "?warehouseId=" + warehouseId + "&id=" + id;

	zoneForm = document.getElementById('list_zone_form');
	document.getElementById('pageNo').value = pageNo;
	zoneForm.submit();

}

function del_zone(){
	if(!confirm("Are you sure to delete?")){
		return;
	}
	var id = document.getElementById('id').value;
	var to_del = getDelIds('chkZone');
	var reqUrl = "shipping_method!delInSession.action?type=Zone&toDel="+to_del+"&id="+id;
	var pageNo = document.getElementById('pageNo').value;
	//alert (reqUrl);
	$.ajax({
	    type: "POST",
	    url: reqUrl,
		success:function(data)
		{
			//alert (data);
			if (data == 'SUCCESS'){
				search_zone(pageNo);	
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
<form id="list_zone_form" method="post" action="shipping_method!listZone.action">
<input type="hidden" id="pageNo" name="pageNo" value="${pageNo}" />
<input type="hidden" id="id" name="id" value="${id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <th width="9%" align="right" valign="top">Warehouse</th>
          <td width="20%">
			<s:select cssStyle="width:207px" name="warehouseSlt" list="specDropDownList['WAREHOUSE'].dropDownDTOs" listKey="id" listValue="name" headerKey="" headerValue="" value="warehouseId" onchange="search_zone()"></s:select>
          </td>

          <th width="6%" align="right">Zone</th>
          <td width="65%">
            <input id="standardZoneValue" type="text" class="NFText" value="${sessionScope.standardZoneValue[id]}" size="35" readonly="readonly" />
          </td>
        </tr>
      </table>
</form>
      <br />
      <div id="tb1" style='display:block;'>

        <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table"> 
            <tr>
              <th width="46"><div align="left">
                <input name="checkbox3" type="checkbox" onclick="setAll(this, 'chkZone')" />
              <a href="javascript:void(0)" onclick="del_zone()" title="Delete" rel="gb_page_center[600,  180]"><img src="images/file_delete.gif" alt="Delete" width="16" height="16" border="0" /></a></div></th>
              <th width="146">Zone</th>
              <th width="75">Country</th>
              <th width="248">From Zip</th> 
              <th width="227">To Zip</th>
              <th>Note</th>
            </tr>
          </table>

		<div class="frame_box3" style="height:250px; width:970px;">
            <table width="955" border="0" cellpadding="0" cellspacing="0"  class="list_table">
			<s:iterator value="retZoneList">
              <tr>
                <td width="46"><input type="checkbox" value="${idStr}" name="chkZone"  /></td> 
                <td width="146">&nbsp;<a href="javascript:void(0)" onclick="javascript:show_edit_zone('${idStr}')" title="${zoneCode}" rel="gb_page_center[550,  300]">${zoneCode}</a></td>
                <td width="75">&nbsp;${country}</td>
                <td width="248">&nbsp;${zipFrom}</td>
                <td width="227">&nbsp;${zipTo}</td>
                <td>&nbsp;${note}</td>
              </tr>
			</s:iterator>
            </table>
        </div>
		<div class="grayr"><jsp:include page="/common/db_pager.jsp">
					<jsp:param value="${ctx}/shipping_method!listZone.action?id=${id}&warehouseSlt=${warehouseId}"
						name="moduleURL" />
				</jsp:include></div>
		<div class="botton_box">
		<input id="btn_add" type="button" class="style_botton" value="New" />
		</div>
      </div>

<input type="hidden" id="editZoneDialogTrigger" />
<div id="zone_add_dialog"  title="Zone Information"> </div>
<div id="zone_edit_dialog"  title="Zone Information"> </div> 
</body>
</html>
