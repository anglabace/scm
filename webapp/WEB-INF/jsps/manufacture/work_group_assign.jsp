<%@ include file="/common/taglib.jsp"%>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_css_url}ui.all.css" rel="stylesheet" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script>
$(function(){
	$("#work_group_sel").attr("size",$("#work_group_sel option").length);
});
function add() {
	$("#work_group_sel :selected").each(function(){
		var workGroupId = this.value;
		var workGroupName = this.text;
		var str = workGroupId+"::"+workGroupName;
		var flg = false;
		$("#add_a input:checkbox").each(function(i){
			if($(this).val()==str) {
				flg = true;
				return true;
			}
		});
		if(!flg) {
			str = "<li><input type='checkbox' value='"+workGroupId+"::"+workGroupName+"' checked/>"+workGroupName+"</li>";
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
	var workGroupNames = "";
	var workGroupIds ="";
	$("#add_a input:checkbox").each(function(i){
		var array = $(this).val().split("::");
		if(workGroupIds=="") {
			workGroupIds = array[0];
		} else {
			workGroupIds = workGroupIds+","+array[0];
		}
		if(workGroupNames=="") {
			workGroupNames = array[1];
		} else {
			workGroupNames = workGroupNames+","+array[1];
		}
	});
	var processFlag = "0";
	if($("#processFlag").attr("checked")) {
		processFlag = "1";
	}
	parent.$("#processFlag").attr("value",processFlag);
	parent.$("#workGroupIds").attr("value",workGroupIds);
	parent.$("#workGroupNames").attr("value",workGroupNames);;
	parent.$("#work_group_assign_dlg").dialog("close");
}
</script>
</head>

<body>
<table  border="0" cellpadding="0" cellspacing="0" class="General_table">
         <tr>
         <th>&nbsp;</th>
	  	<td>
	    	<input type="checkbox" value="1" name="processFlag"/>Skip Productive Process
	    </td>
	  </tr>
          <tr>
            <th width="154">Work Group</th>
            <td>
            <select id="work_group_sel" multiple="multiple" style="height:auto;width: 207px;">
		            	<s:iterator value="workGroupList">
		            		<option value="<s:property value='id'/>"><s:property value='name'/></option>
		            	</s:iterator>
            	</select>
            
            <input  type="button"  value="Add" class="style_botton" onclick="add()" />
              <input  type="button" value="Remove" class="style_botton" onclick="remove();" /></td>
            </tr>
          <tr>
            <th>&nbsp;</th>
            <td height="80" colspan="2" valign="top">
            <div id="add_a" style="float:left;list-style:none;">
            	${workGroupAssignStr}
              </div></td>
          </tr>
          <tr>
            <td height="40" colspan="3" align="center"><input  type="button" name="close4" value="Confirm" class="style_botton" onclick="confirm();" />              
            <input  type="button" name="close2" value="Cancel" class="style_botton" onclick="javascript:parent.$('#work_group_assign_dlg').dialog('close');" /></td>
          </tr>
</table>
</body>
</html>
