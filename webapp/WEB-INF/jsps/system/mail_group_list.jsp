<%@page contentType="text/html" pageEncoding="GBK"%>
<%@include  file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Mail Group</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />

<link type="text/css"
	href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}ajax.js"></script>
<script src="${global_js_url}table.js" type="text/javascript"></script>
<script
	src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js"
	type="text/javascript"></script>
<link type="text/css"
	href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script src="${global_js_url}jquery/ui/ui.core.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js"
	type="text/javascript"></script>
	
<script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>
<script language="JavaScript" type="text/javascript">
$(document).ready(function(){  
    $('tr:even >td').addClass('list_td2'); 
});
function   cc(e)  
{  
    var   a   =   document.getElementsByName("groupSeq");  
    for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;  
}  

function del(name){
	var del_nos = get_checked_str(name);
	if(del_nos == '')
	{
		alert('Please select one item to continue your operation.');
		return;
	}
	if(!confirm('Are you sure to delete?'))
	{
		return;
	}
	$.ajax({
		type:"POST",
		url:"system/mail_group!delete.action?delStr="+del_nos,
		dataType:"json",
		success: function(msg){
			if(msg.message=="success"){
				window.location.reload();
			}else{
				alert("Failed to delete the mail group. Please remove mail address to continue your operation.");	
			}
		},
		error: function(msg){
			alert("System error! Please contact system administrator for help.");
		}
	});
}
function get_checked_str(name)
{
	var a = document.getElementsByName(name);
	var str = '';
	for   (var   i=0;   i<a.length;   i++)
	{
		if(a[i].checked)
		{
			str += a[i].value+',';
		}
	}
	return str.substring(0,str.length-1);
}
</script>
</head>
 
<body class="content" style="background-image:none;">
<div class="scm">
<div class="title_content">
<div class="title_new"><a href="javascript:void(0);" onclick="toggleShowMorea('total_title');" id="total_titleItem"><img src="${global_url}images/arrow1.jpg" /></a>Email Groups</div>
</div>
<div class="search_box" id="total_title">
 
 
 <div class="search_box_two">
 <form action="" method="get">
 <table  border="0" cellpadding="0" cellspacing="0" class="General_table">
  <tr>
    <th>Email Group Code</th>
    <td><input name="filter_LIKES_groupCode" type="text" class="NFText" size="20" value="${param['filter_LIKES_groupCode']}"/></td>
    <th>Name</th>
    <td><input name="filter_LIKES_groupName" type="text" class="NFText" size="20" value="${param['filter_LIKES_groupName']}"/></td>
    <th>Description</th>
    <td><input name="filter_LIKES_description" type="text" class="NFText" size="20" value="${param['filter_LIKES_description']}"/></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    </tr>
  <tr>
    <td height="40" colspan="8" align="center"><input type="submit" name="Submit5" value="Search" class="search_input" /></td>
  </tr>
 </table>
 </form>
</div>
</div>		
<div class="input_box" style="height:340px;">
 
<table width="1010" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td style="padding-right:17px;">
 
  <table width="993" border="0" cellspacing="0" cellpadding="0" class="list_table">
  <tr>
  <th width="46"><div align="left">
					    <input type="checkbox" name="checkbox11"  onclick="cc(this)"/>
		       
		         <a href="javascript:void(0);" onclick="del('groupSeq')"><img src="${global_image_url}file_delete.gif" alt="Delete" width="16" height="16" border="0" /></a></div></th>
    <th width="176">Email Group Code</th>
    
    <th width="212">Name</th>
    <th width="124">Status</th>
    <th >Description</th>
    </tr>
</table></td></tr>
<tr><td><table width="993" border="0" cellspacing="0" cellpadding="0" class="list_table2">
<s:iterator value="page.result">
  <tr>
    <td width="46"><input type="checkbox" value="${groupId }" name="groupSeq" id="groupSeq"/></td>
   <td width="176"><a href="javascript:void(0)" onclick="window.location.href='${ctx}/system/mail_group!input.action?id=${groupId }'">${groupCode }</a></td>
    <td width="212">${groupName }</td>
    <td width="124" align="center">${status}</td>
    <td>${description}</td>
    </tr>
    </s:iterator>
</table>
</td></tr>
<tr>
	<td align="right"><div class="grayr">
			<jsp:include page="/common/db_pager.jsp">
			  <jsp:param value="${ctx }/product/catalog.action" name="moduleURL"/>
			</jsp:include>
		</div></td>
</tr>
</table>
 
 
</div>	
 

 
<div  class="button_box"><input type="button" name="Submit16" value="New" class="search_input" onclick="window.location.href='${ctx}/system/mail_group!input.action'"/></div>
</div>
	
</body>
</html>
