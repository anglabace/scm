<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script   language="JavaScript" type="text/javascript">
$(document).ready(function(){
    $('tr:even >td').addClass('list_td2');
});
function cc(e, name){
	var  a =   document.getElementsByName(name);
	for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;
}
//remove checked rows and return to_del id list
function getDelIds(boxs){
	var a = document.getElementsByName(boxs);
	var to_del = "";
	var total = a.length;
	for (var i=(total-1); i>=0; i--){
		if (a[i].checked ){
			to_del += a[i].value+",";
		}
	}
	return to_del;
}

function del_ship_clerk(){
	if(!confirm("Are you sure to delete?")){
		return;
	}
	var to_del = getDelIds('clerkId');
	var reqUrl = "shipping_clerks_srch!delete.action?clerkIds="+to_del;
	$.ajax({
	    type: "POST",
	    url: reqUrl,
		success:function(data){
			if (data.indexOf("SUCCESS") == 0){
				if (data.indexOf("##") > 0){
					var str = data.substr(data.indexOf("##")+2);
					alert (str + " can not be deleted!");
				}else{
					alert ("Deleted successfully.");
				}
				window.location.reload();
			}else{
				alert (data);
				return;
			}
		},
		async: false
	});
}
</script>
</head>

<body class="content" style="background-image:none;">

<div class="scm">

<div class="input_box">

    <div style="margin-right:17px;">
    <table width="966" border="0" cellspacing="0" cellpadding="0" class="list_table2">
		<tr>
			<th width="46">
			<div align="left">
			  <input type="checkbox" onclick="cc(this, 'clerkId')">
              <a href="javascript:void(0)" onclick="del_ship_clerk()" title="Delete" rel="gb_page_center[600,  180]"><img src="${global_image_url}file_delete.gif" alt="Delete" width="16" height="16" border="0" /></a></div></th>
			<th width="48">Clerk ID</th>
			<th width="144">Name</th>
			<th width="177">Function</th>
			<th width="348">Assigned Product/Service</th>
                        <th>Status</th>
		  </tr>
	</table>
    </div>
  		<div class="list_box" style="height:340px; width:983px;">
    	<table width="966" border="0" cellspacing="0" cellpadding="0" class="list_table2">
        	<s:iterator value="page.result">
      		<tr>
            <td width="46"><input type="checkbox" value="${clerkId}" name="clerkId"/></td>
        	<td width="48"><a target="_parent" href="shipping_clerks_srch!input.action?clerkId=${clerkId}&operation_method=edit">${clerkId}&nbsp;</a></td>
        	<td width="144">${name}&nbsp;</td>
        	<td width="177">${clerkFunction}&nbsp;</td>
                <td width="348">${typeNames}&nbsp;</td>
        	<td><div align="center">${status}&nbsp;</div></td>
      		</tr>
            </s:iterator>
    	</table>
		</div>

		<div class="grayr">&nbsp;<jsp:include page="/common/db_pager.jsp">
					<jsp:param value="${ctx}/shipping_clerks_srch.action"
						name="moduleURL" />
				</jsp:include></div>
</div>
</div>
 <div class="button_box">
      <input type="submit" onclick="parent.location.href='shipping_clerks_srch!input.action'" value="New Clerk" class="search_input" name="Submit"/>
  </div>
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
</script>
</body>
</html>
