<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>

<script src="${global_css_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>

<script><!--
$(function(){
	parent.$('#chooseUserDialog').dialog({
		autoOpen: false,
		height: 480,
		width: 360,
		modal: true,
		bgiframe: true,
		buttons: {}
	}); 
	$("#chooseUserDialogTrigger").click(function(){
		parent.$('#chooseUserDialog').dialog("option", "open",function(){
			var htmlStr = '<iframe id="chooseUserDialog" src="${ctx}/privilege/user!searchUserList.action?id=${id}" height="400" width="320" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			parent.$('#chooseUserDialog').html(htmlStr);
		});	
		parent.$('#chooseUserDialog').dialog('open');										 	
	});
});

function addMailAdress(){
	var userId = $('#userId').val();
	var mailId = $('#mailId').val();
	if(userId!=null&&userId!=""){
		if(mailId!=null&&mailId!=""){
			parent.$('#delMailAddress').val(parent.$('#delMailAddress').val()+"<;>"+mailId);
		}
		parent.$('#addMailAddress').val(parent.$('#addMailAddress').val()+"<;>"+userId);
		var emailAddress = $("#emailAddress").val();
		var userName = $("#userName").val();
		var departMent = $("#departMent option:selected").text();
		var status = $("#status").val();
		var appendStr ="";
		appendStr+='<tr id="userId_'+userId+'">';
		appendStr+='<td width="46"><input type="checkbox" value="userId_'+userId+'" name="mailId" id="mailId"/></td>';
		appendStr+='<td width="202" align="center">';
		appendStr+=emailAddress+'&nbsp;</td>';
		appendStr+='<td width="273">'+userName+'&nbsp;</td>';
		appendStr+='<td width="278">'+departMent+'&nbsp;</td>';
	    appendStr+='<td align="center">'+status+'&nbsp;</td>';
		appendStr+='</tr>';
		parent.$("#mailAddressList_iframe").contents().find("#addressTable").append(appendStr);
		
	}
	parent.$('#newMailAddressDialog').dialog('close');
	parent.$('#newMailAddressDialog').dialog('destory');
	
}
--></script>

</head>

<body>
<div class="input_box">
		  <div class="content_box">
 			<br/>
		    <form class="niceform">
		      <table border="0" cellpadding="0" cellspacing="0" class="General_table">
		      
		        <tr>
		          <th width="172">User Name</th>
		          <td width="212"><input name="userName" id="userName" value="${mailAddress.mailAddress.user.loginName}" type="text" class="NFText" size="20" readonly="readonly" />
	              <input type="hidden" name="userId" id="userId" value="${mailAddress.mailAddress.user.userId }"/>
	              <input type="hidden" name="mailId" id="mailId" value="${mailAddress.mailAddress.mailId }"/>
	              <img src="${global_image_url}search.gif"  style="cursor:pointer;" width="16" height="16" id="chooseUserDialogTrigger"/></td>
		          <th width="200">Email Address</th>
		          <td width="255"><input name="emailAddress" id="emailAddress" value="${mailAddress.mailAddress.user.email }" type="text" class="NFText" size="20" readonly="readonly"/></td>
	            </tr>
		        <tr>
		          <th valign="top">Department</th>
		          <td>
		              <s:select name="departMent" id="departMent" list="department" listKey="deptId" listValue="name" cssStyle="width:125px;" headerKey="" headerValue=""></s:select>		
		             
		          </td>
		          <th>Status</th>
		          <td>
		          	<input name="status" id="status" value="${mailAddress.mailAddress.user.status }" type="text" class="NFText" size="20" readonly="readonly"/>
		          </td>
	            </tr>
		       
	          </table>
		    </form>
		    
 			<br/>
		    <div  style="margin-top:20px; text-align:center">
		    	  <input name="sessionMailGroupId" id="sessionMailGroupId" type="hidden" value="${sessionMailGroupId }"/>
			      <input type="submit" name="Submit123"  value="Add" class="search_input" onclick="addMailAdress()"/>
			      <input type="submit" name="Submit124" value="Cancel" class="search_input" onclick="parent.$('#newMailAddressDialog').dialog('close');parent.$('#newMailAddressDialog').dialog('destory');"/>
			</div>
		</div>
  </div>	
 
 


</body>
</html>