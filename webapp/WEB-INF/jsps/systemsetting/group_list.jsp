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
    <table width="966" border="0" cellspacing="0" cellpadding="0" class="list_table">
      <tr>
        <th width="46"><div align="left">
					    <input type="checkbox" name="checkbox11"  onclick="sur(this)"/>

		         <a href="delete_group.html" title="Delete Group" rel="gb_page_center[600,  180]"><img src="images/file_delete.gif" alt="Delete" width="16" height="16" border="0" /></a></div></th>
        <th width="141">Group Name</th>
        <th width="171">Description</th>

        <th width="82">Status</th>
        <th width="196">Group Function</th>
        <th width="109">Supervisor</th>

        <th width="120">Modified Date</th>
        <th>Modified By</th>
         </tr>

    </table>
    </div>
            <div class="list_box" style="height:340px; width:983px;">
    	<table width="966" border="0" cellspacing="0" cellpadding="0" class="list_table2">
        	<s:iterator value="page.result">
      		<tr>
            <td width="46"><input type="checkbox" value="${groupId}" name="clerkId"/></td>
        	<td width="141"><a target="_parent" href="emarketing_group_srch!input.action?sessionGroupId=${groupId}&groupId=${groupId}&operation_method=edit">${groupName}&nbsp;</a></td>
        	<td width="171">${description}&nbsp;</td>
        	<td width="82">${status}&nbsp;</td>
                <td width="196">${typeNames}&nbsp;</td>
                <td width="109">${supervisor}&nbsp;</td>
                <td  width="120"><div align="center"><s:date name="modifyDate" format="yyyy-MM-dd"/>&nbsp;</div></td>
                <td><div align="center">${name}&nbsp;</div></td>
      		</tr>
            </s:iterator>
    	</table>
		</div>

		<div class="grayr">&nbsp;<jsp:include page="/common/db_pager.jsp">
					<jsp:param value="${ctx}/emarketing_group_srch!list.action"
						name="moduleURL" />
				</jsp:include></div>
</div>
 <div class="button_box">
      <input type="submit" onclick="parent.location.href='emarketing_group_srch!input.action'" value=" New " class="search_input" name="Submit"/>
  </div>
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
</script>
</div>
</body>
</html>
