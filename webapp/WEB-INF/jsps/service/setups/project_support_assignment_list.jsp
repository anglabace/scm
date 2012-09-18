<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script src="${global_js_url}jquery/jquery.js" type="text/javascript" language="javascript"></script>
<script src="${global_js_url}jquery/jquery.dialog.all.js" type="text/javascript"></script>

<script  language="JavaScript" type="text/javascript">
function cc(e, name){
	var  a =   document.getElementsByName(name);
	for   (var   i=0;   i<a.length;   i++){
		if(a[i].disabled == false)   
		a[i].checked   =   e.checked;
	}
} 

function get_checked_str(name){
	var a = document.getElementsByName(name);
	var str = '';
	for   (var   i=0;   i<a.length;   i++){
		if(a[i].checked){
			str += a[i].value+',';
		}
	}
	return str.substring(0,str.length-1);
}

function show_edit_psa (id) {
	var href = 'project_support_assignment!edit.action?projectSupportAssignmentDto.id='+id+'&operation_method=view';
	if (id != 0) {
		href = 'project_support_assignment!edit.action?projectSupportAssignmentDto.id='+id+'&operation_method=edit';
	}
	$('#editPsaDialogTrigger').val(href);
	$('#editPsaDialogTrigger').click();	
}

$(document).ready(function(){
	$("#resultTable tr:odd").find("td").addClass("list_td2");
	
	$('#editPsaDialog').dialog({
		autoOpen: false,
		height: 400,
		width: 600,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
			var url = $("#editPsaDialogTrigger").val();
			var htmlStr = '<iframe src="'+url+'" allowTransparency="true" width="100%" height="100%" frameborder="0" scrolling="No"></iframe>';
			$('#editPsaDialog').html(htmlStr);
		}
	});

	$("#editPsaDialogTrigger")
		.click(function(){
			$('#editPsaDialog').dialog('open');
	});
    
	$('#delPsaDialogTrigger').click(function(){
		var del_psa_ids = get_checked_str("id");
		if(del_psa_ids == ''){
			alert('Please select one item to continue your operation.');
			return;
		} else {
			$.ajax({
				type: "POST",
				url: "project_support_assignment!delete.action",
				data: 'ids='+del_psa_ids,
				success: function(data){
					if(data.msg != undefined){
					}else if(data.hasException != undefined){
						alert('Failed to delete the project Support Assignment.Please contact system administrator for help.');	
					}
					$(":checkbox").attr("checked", false);
					window.location.reload();
				},
				error: function(msg){
					alert("System error! Please contact system administrator for help.");
				}
			});
		}
	});

});
</script>
</head>
<body>
<div>
<input type="hidden" id="hiddenProjectSupport"/>
<input type="hidden" id="editPsaDialogTrigger" />
<div id="editPsaDialog"  title="Service Type Assignment"> </div>
</div>
<div style="margin-right:17px;">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
	    <tr>
            <th width="50">
           		<div align="left">
             		<input type="checkbox"  onclick="cc(this, 'id')" />
             		<img id="delPsaDialogTrigger" src="${global_image_url}file_delete.gif" alt="Delete" width="16" height="16" border="0" />
            	</div>
		    </th>
		    <th width="50">ID</th>
		    <th width="250">Service Type Name</th>
		    <th width="250">Project Manager</th>
		    <th>Comment</th>
	    </tr>
	</table>
</div>
<div class="list_box" style="height:400px;margin-right: 17px;">
	<table id="resultTable" width="100%" border="0" align="center" cellpadding="0" cellspacing="0"  class="list_table">
		<s:iterator value="page.result" var="projectSupportAssignmentDTO">
			 <tr>
		        <td  width="50">
	   				<input type="checkbox" value="${projectSupportAssignmentDTO[0]}" name="id" />
	   			</td>
	   			<td width="50" >
   					<a href="javascript:void(0)" onclick="javascript:show_edit_psa('${projectSupportAssignmentDTO[0]}');">
		    			${projectSupportAssignmentDTO[0]}
		    		</a>
			   	</td>
			    <td width="250">${projectSupportAssignmentDTO[1]}</td>
			   <td width="250" >&nbsp;${projectSupportAssignmentDTO[2]}</td>
			   <td>&nbsp;${projectSupportAssignmentDTO[3]}</td>
		  </tr>
		</s:iterator>
	</table>
</div>
<div class="grayr">
	<jsp:include page="/common/db_pager.jsp">
	  <jsp:param value="${ctx}/order/project_support_assignment!list.action" name="moduleURL"/>
	</jsp:include>	
</div>
<div  class="button_box">
<input type="button" value="New" class="search_input" onclick="javascript:show_edit_psa(0);" style="background-image:url(images/input_searchbg2.jpg) ; width:120px;"/>
</div>	
</body>
</html>
