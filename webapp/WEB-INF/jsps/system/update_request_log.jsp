<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_css_url}ui.all.css" rel="stylesheet" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script>
function confirmggg() {
	if($("#reason").val()=="") {
		alert("Please enter the reason.");
		return;
	}
	$("#confirm").attr("disabled","disabled");
	var param = $("#from1").serialize();
	$.ajax({
		type: "POST",
		url:  "update_request_log!save.action",
		data: param,
		dataType:"json",
		success: function(data){
			if(data.message=='Success') {
				alert("Success");
				parent.$("#update_request_log_dlg").dialog("close");
			} else {
				alert(data.message);
			}
			$("#confirm").attr("disabled","");
			parent.$("#"+$("#changeType").val()).val($("#newValue").val());
		},
		error: function(msg){
		   alert("failure");
		   $("#confirm").attr("disabled","");
		   return;
		}
	});

}
</script>
</head>
<body>
<form id="from1" method="post">
<table id="updateRequestLogTable"  border="0" align="center" cellpadding="0" cellspacing="0" class="General_table">
	   <tr>
          <th valign="top">Log Date</th>
          <td><input name="updateRequestLog.requestDate" id="requestDate" type="text" class="NFText" size="15" readonly="readonly" value="<s:date name="updateRequestLog.requestDate" format="yyyy-MM-dd"/>"/></td>
          <th>Requested By</th>
          <td>
          	<input name="textfield2" id="requestedByName" type="text" class="NFText" size="15" readonly="readonly" value="${updateRequestLog.requestedByName}" />
          	<s:hidden name="updateRequestLog.requestedBy"></s:hidden>
          </td>
       </tr>
	  <tr>
	    <th>
	    	<font color="red">*</font>Reason
	    </th>
	    <td colspan=3>
	    	<s:textarea name="updateRequestLog.reason" cssClass="content_textarea2" id="reason">
			</s:textarea>
	    </td>
	  </tr>
	  <tr>
	    <td colspan="4">
	    	<div align="center"><br />
		       <input type="button" id="confirm"   class="style_botton" value="Confirm" onclick="confirmggg()" />
		       <input  type="button" name="Cancel" value="Cancel" class="style_botton" onclick="parent.$('#update_request_log_dlg').dialog('close');" />
	    	</div>
	    </td>
	  </tr>
	  <s:hidden name="updateRequestLog.objectEntity"></s:hidden>
	  <s:hidden name="updateRequestLog.field"></s:hidden>
	  <s:hidden name="updateRequestLog.objectId"></s:hidden>
	  <s:hidden name="updateRequestLog.originalValue"></s:hidden>
	  <s:hidden name="updateRequestLog.newValue" id="newValue"></s:hidden>
	  <s:hidden name="updateRequestLog.parentId"></s:hidden>
	  <s:hidden name="changeType" id="changeType"></s:hidden>
</table>
</form>
</body>
</html>