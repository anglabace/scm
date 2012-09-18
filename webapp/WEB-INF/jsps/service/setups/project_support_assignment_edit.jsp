<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base id="myBaseId" href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
   
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}show_tag.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}select.js"></script>
<script src="${global_js_url}jquery/jquery.js" type="text/javascript"></script>

<script type="text/javascript" src="${global_js_url}scm/config.js"></script>
<script type="text/javascript" src="${global_js_url}util/util.js"></script>
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.form.js" language="javascript" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js" language="javascript" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}scm/gs.util.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	// validate signup form on keyup and submit
	$("#redeem_Card_Form").validate({ 
		invalidHandler: function(form, validator) { 
			$.each(validator.invalid, function(key,value){ 
				alert(value); 
				$("#"+key).focus(); 
				return false; 
			}); 
		}, 
		rules: { 
			serviceTypeName: { required:true}, 
			projectSupport: { required:true}
		}, 
		messages: { 
			serviceTypeName: "Please select Service Type Name", 
			projectSupport: "Please select Project Support"
		}, 
		errorClass:"validate_error",
		highlight: function(element, errorClass) {
		$(element).addClass(errorClass);
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).removeClass(errorClass);
		},
		errorPlacement: function(error, element) {

		}
	});	
	
	$("#psaSave").click(function () {
		if($("#psaEditForm").valid() == false) {
			return false;
		}
		var serviceTypeName = $("#serviceTypeName").val();
		var projectSupport = $("#projectSupport").val();
		var comment = $("#comment").val();
		var psaDtoId = $("#psaDtoId").val();
		$.ajax({
			type: "POST",
			url: "project_support_assignment!save.action",
			data: 'projectSupportAssignmentDto.id='+psaDtoId+"&projectSupportAssignmentDto.serviceTypeName="+serviceTypeName+"&projectSupportAssignmentDto.projectSupport="+projectSupport+"&projectSupportAssignmentDto.comment="+comment,
			success: function(msg){
				if(msg == "success"){
				}else if(msg == "error"){
					alert('System error! Please contact system administrator for help.');	
				} else {
					alert("System error! Please contact system administrator for help.");
				}
				$(":checkbox").attr("checked", false);
				parent.$('#editPsaDialog').dialog('close');
				parent.window.location.reload();
			},
			error: function(msg){
				alert("System error! Please contact system administrator for help.");
			}
		});
	});

	$("#searchProjectSupport").click(function () {
		var href = 'project_support_assignment!searchProjectSupport.action';
		$('#searchProjectSupportDialogTrigger').val(href);
		$('#searchProjectSupportDialogTrigger').click();	
	});



	$("#searchProjectSupportDialogTrigger")
		.click(function(){
			$('#searchProjectSupportDialog').dialog('open');
	});
	
	$('#searchProjectSupportDialog').dialog({
		autoOpen: false,
		height: 300,
		width: 600,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
			var url = $("#searchProjectSupportDialogTrigger").val();
			var htmlStr = '<iframe src="'+url+'" allowTransparency="true" width="100%" height="100%" frameborder="0" scrolling="No"></iframe>';
			$('#searchProjectSupportDialog').html(htmlStr);
		}
	});
});

</script>
</head>
<body>
<div>
<input type="hidden" id="searchProjectSupportDialogTrigger" />
<div id="searchProjectSupportDialog"  title="Search Project Support"> </div>
</div>
<form id="psaEditForm" name="psaEditForm">
<input type="hidden" id="psaDtoId" value="${projectSupportAssignmentDto.id}"/>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="General_table">
  <tr>
    <th width="30%" valign="top">Service Type Name</th>
    <td><select id="serviceTypeName" name="projectSupportAssignmentDto.serviceTypeName">
    	<s:iterator value="serviceClassificationList" var="serviceClassification">
    		<c:if test="${projectSupportAssignmentDto.serviceTypeName == serviceClassification.name}">
    			<option value="${serviceClassification.clsId}" selected="selected">${serviceClassification.name}</option>
    		</c:if>
    		<c:if test="${projectSupportAssignmentDto.serviceTypeName != serviceClassification.name}">
    			<option value="${serviceClassification.clsId}">${serviceClassification.name}</option>
    		</c:if>
    	</s:iterator>
	    </select>
	</td>
  </tr>
  <tr>
    <th width="30%" valign="top">Project Manager</th>
    <td><input id="projectSupport" name="projectSupportAssignmentDto.projectSupport" value="${projectSupportAssignmentDto.projectSupport}" readonly="readonly" disabled="disabled" type="text" class="NFText" size="20" />
      <img src="images/search.gif" id="searchProjectSupport" width="16" height="16" /></td>
    </tr>
  <tr>
    <th width="30%" valign="top">Comment</th>
    <td><span style="font-weight: normal;">
      <textarea id="comment" name="projectSupportAssignmentDto.comment" class="content_textarea2">
      	${projectSupportAssignmentDto.comment}
      </textarea>
    </span></td>
    </tr>
  <tr>
    <td colspan="2"><div align="center"><br />
      <input type="button" name="Submit2" id="psaSave"  class="style_botton" value="Save" />
      <input  type="button" name="Cancel" value="Cancel" class="style_botton" onclick="javascript:parent.$('#editPsaDialog').dialog('close');"/>
    </div></td>
  </tr>
</table>
</form>
</body>
</html>
