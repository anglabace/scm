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
<s:include value="workOrder_config.jsp"></s:include>
<script>
$(function(){
	$("#work_group_sel").attr("size",$("#work_group_sel option").length);
	//document.all.mysel.size=document.all.mysel.options.length;
});

function add() {
	$("#work_group_sel :selected").each(function(){
		var workGroupId = this.value;
		var workGroupName = this.text;
		var str = workGroupId;
		var flg = false;
		$("#add_a input:checkbox").each(function(i){
			if($(this).val()==str) {
				flg = true;
				return true;
			}
		});
		if(!flg) {
			str = "<li><input type='checkbox' value='"+workGroupId+"' checked/>"+workGroupName+"</li>";
			$("#add_a").append(str);
		}
	});
	
}
function remove() {
	$("#add_a input:checked").each(function(i){
		$(this).parent().remove();
	});
}

function confirm() {
	var workGroupIds ="";
	$("#add_a input:checkbox").each(function(i){
		var workGroupId = $(this).val();
		if(workGroupIds=="") {
			workGroupIds = workGroupId;
		} else {
			workGroupIds = workGroupIds+","+workGroupId;
		}
	});
	if(workGroupIds=="") {
		alert("Please add one group at least.");
		return;
	}
	var processFlag = "0";
	if($("#processFlag").attr("checked")) {
		processFlag = "1";
	}
	$("#confirm").attr("disabled","disabled");
	$.ajax({
		type: "POST",
		url:  "workorder_entry!assignGroup.action",
		data: "workGroupIds="+workGroupIds+"&orderNoStrs="+parent.$("#choiceOption").val()+"&processFlag="+processFlag,
		dataType:"json",
		success: function(data){
			if(data.message=='Success') {
				alert("Success");
				parent.$("#assign_group_dlg").dialog("close");
				sendEmailForAssignGroup(workGroupIds);
				window.location.reload();
			} else {
				alert(data.message);
			}
			$("#confirm").attr("disabled","");
			parent.$("#choiceOption").attr("value","");
		},
		error: function(msg){
		   alert("failure");
		   $("#confirm").attr("disabled","");
		   parent.$("#choiceOption").attr("value","");
		   return;
		}
	});

}

//发送邮件
function sendEmailForAssignGroup(workGroupIds) {
	var searchNoteType = "VENDOR_CONFIRM_EMAIL";
	parent.$('#instruction_dlg').dialog('option', "open", function() {
		var url = orderquoteObj.url("editNote") + "&searchNoteType="+searchNoteType+"&workGroupIds="+workGroupIds+"&orderNoStrs="+parent.$("#choiceOption").val();
		var htmlStr = '<iframe src="'+url+'" height="350" width="579" scrolling="auto" style="border:0px;" frameborder="0"></iframe>';
		parent.$('#instruction_dlg').html(htmlStr);
	});
	parent.$('#instruction_dlg').dialog('open');
}
</script>
</head>
<body>
<table id="updateStatusTable"  border="0" align="center" cellpadding="0" cellspacing="0" class="General_table"  style="margin:10px auto 0px auto;">
	  <tr>
	    <th>&nbsp;</th>
	  	<td>
	    	<input type="checkbox" id="processFlag" value="1" name="processFlag"/>Skip Productive Process
	    </td>
	  </tr>
	  <tr>
	    <th>
	    	<div align="left">Work Center:</div>
	    </th>
	    <td>
	   <s:select id="work_center_sel" name="centerId"
				 disabled="true"
				 list="workCenterList" listKey="id" listValue="name"
				 value="centerId" cssStyle="width: 207px;"></s:select>
	    </td>
	  </tr>
	  <tr>
	    <th>
	    	<div align="left">Work Group</div>
	    </th>
	    <td>

            	<select id="work_group_sel" name="mysel" multiple="multiple" style="width: 207px;height:auto">
		            	<s:iterator value="workGroupList">
		            		<option value="<s:property value='id'/>"><s:property value='name'/></option>
		            	</s:iterator>
            	</select>
            <input  type="button"  value="Add" class="style_botton" onclick="add()" />
            <input  type="button" value="Remove" class="style_botton" onclick="remove();" />
	    </td>
	  </tr>
	  <tr>
	  	<td colspan="2">
	  		<div id="add_a" style="float:left;list-style:none;">
            	${workGroupAssignStr}
              </div>
        </td>
	  </tr>
	  <tr>
	    <td colspan="2">
	    	<div align="center"><br />
		       <input type="button" id="confirm"   class="style_botton" value="Confirm" onclick="confirm();" />
		       <input  type="button" name="Cancel" value="Cancel" class="style_botton" onclick="parent.$('#assign_group_dlg').dialog('close');" />
	    	</div>
	    </td>
	  </tr>
</table>
</body>
</html>