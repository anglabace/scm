<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/common/taglib.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"  />
<script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1"
			type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.dialog.all.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>
<script type="text/javascript">
$(function(){
	var isSaved = false;
	$('#processChoiceDialog').dialog({
		autoOpen: false,
		height: 440,
		width: 610,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
	$('#assginProcessForm').validate({
			errorClass:"validate_error",
			highlight: function(element, errorClass) {
				$(element).addClass(errorClass);
		    },
		    unhighlight: function(element, errorClass, validClass) {
		        $(element).removeClass(errorClass);
		    },
			invalidHandler: function(form, validator) {
				 $.each(validator.invalid, function(key,value){
		            alert(value);
		            $(this).find("name=[" + key + "]").focus();
		            return false;
		        });
			 },
			 errorPlacement: function(error, element) {
			 }
		});
		            
	$("#save_btn").click (function() {
		if($('#sequence').val()=="") {
			alert("Please select a process to save!");
			return false;
		}
		var formStr = "sessionId="+$("#sessionId").val()+"&sequence="+$('#sequence').val();
	   $('#save_btn').attr("disabled", true);
	   var sessionId = $("#sessionId").val();
	   $.ajax({
				type: "POST",
				url: "assignment_rules!saveProcess.action",
				data: formStr,
				dataType: 'json',
				success: function(data, textStatus){
					if(hasException(data)){
		 	           $('#save_btn').attr("disabled", false);				
					}else{                              
					  alert(data.message);
					  isSaved = true;
					  location.href="assignment_rules!load.action?sessionId="+sessionId+"&referURL=select";
					}
				},
				error: function(xhr, textStatus){
				   alert("System error! Please contact system administrator for help.");
		 	       $('#save_btn').attr("disabled", false);
				}
			});                
	});//end of {$("#save_btn").click};  
});

function processSelect() {
	$('#processChoiceDialog').dialog("option", "open", function() {	
		 var htmlStr = '<iframe src="assignment_rules!selectProcess.action" height="100%" width="610" scrolling="no" style="border:0px" frameborder="0"></iframe>';
       $('#processChoiceDialog').html(htmlStr);
	});
	$('#processChoiceDialog').dialog('open');
}
</script>
</head>

<body class="content" style="background-image:none;">
<div class="scm">
<div class="title_content">
  <div class="title">Process <s:if test="salesTerritoryAssignProcess.processName!=null">-<s:property value="salesTerritoryAssignProcess.processName"/></s:if></div>
</div>
<s:form id="assginProcessForm" Class="niceform">
<div class="input_box">
 <table border="0" cellpadding="0" cellspacing="0" class="General_table">
    <tr>
       <th  width="113">Process Sequence </th>
             <td width="426">
               <s:textfield name="salesTerritoryAssignProcess.sequence" id="sequence"  Class="NFText"  size="20" readonly="true" /></td>
      <th width="100">Status</th>
      <td width="144"><s:select
      					id="status_sel"  
	          			list="#{'ACTIVE':'ACTIVE','INACTIVE':'INACTIVE'}" 
	          			name="salesTerritoryAssignProcess.status" 
	          			listKey="key" 
	          			listValue="value"
	          			disabled="true">
	          </s:select></td>
	  </tr>
	  <tr>
	   <th width="113">Process Name</th>
      <td width="426">
      <s:textfield name="salesTerritoryAssignProcess.processName" id="processName"  Class="NFText"  size="20" readonly="true" /> 
       <a href="#" onclick="processSelect()"><img  src="images/search.gif" width="16" height="16" align="absmiddle" /></a> 
      </td>
      <td>&nbsp;</td>
      <td valign="top">&nbsp;</td>
      </tr>
	<tr>
      <th valign="top">Description</th>
      <td><s:textarea name="salesTerritoryAssignProcess.description" id="description" Class="content_textarea2" style="width:368px;" disabled="true"></s:textarea></td>
      <td>&nbsp;</td>
      <td valign="top">&nbsp;</td>
	</tr>
	<tr>

	  <th valign="top">Modified Date</th>
	  <td>
	  	<s:textfield name="salesTerritoryAssignProcess.modifyDate" id="modifyDate" Class="NFText" size="20"  readonly="true">
         <s:param name='value'>
			<s:date name="salesTerritoryAssignProcess.modifyDate"  format="yyyy-MM-dd"/>
         </s:param>
        </s:textfield>
	  </td>
	  <th>Modified By</th>
	  <td valign="top">
	  <s:textfield name="salesTerritoryAssignProcess.modifiedName" id="modifiedName" Class="NFText" size="25"  readonly="true"></s:textfield>
      <s:hidden name="salesTerritoryAssignProcess.modifiedBy"></s:hidden>
	  </td>
	  </tr>
	<tr>
	  <th valign="top">&nbsp;</th>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td valign="top">&nbsp;</td>
	  </tr>
  </table>
  
</div>
<div class="button_box">
    <input type="button" id="save_btn" name="Submit123"  value="Save" class="search_input" />
      <input type="button" name="Submit124" value="Cancel" class="search_input" onclick="javascript:history.go(-1)"/>
</div>
 <s:hidden name="sessionId" id="sessionId"></s:hidden>
</s:form>
<div id="processChoiceDialog" title="Process Choice" style="display:none"></div>
</div>
</body>
</html>