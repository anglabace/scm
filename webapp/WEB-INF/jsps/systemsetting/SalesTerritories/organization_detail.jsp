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
	$('#orgChoiceDialog').dialog({
		autoOpen: false,
		height: 440,
		width: 610,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
	$('#orgForm').validate({
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
		if($('#orgId').val()=="") {
			alert("Please select a organization.");
			return false;
		}
	   var divisionId = $("#division_sel").val(); 
	   var formStr = $("#orgForm").serialize()+"&salesResourceAssignOrg.divisionId="+divisionId;
	   $('#save_btn').attr("disabled", true);
	   var sessionId = $("#sessionId").val();
	   $.ajax({
				type: "POST",
				url: "territory_assign!saveOrg.action",
				data: formStr,
				dataType: 'json',
				success: function(data, textStatus){
					if(hasException(data)){
		 	           $('#save_btn').attr("disabled", false);				
					}else{                              
					  isSaved = true;
					  location.href="territory_assign!load.action?sessionId="+sessionId+"&referURL=select"+"&defaultTab=${defaultTab}";
					}
				},
				error: function(xhr, textStatus){
				   alert("System error! Please contact system administrator for help.");
		 	       $('#save_btn').attr("disabled", false);
				}
			});                
	});//end of {$("#save_btn").click};  
});
function orgSelect() {
	$('#orgChoiceDialog').dialog("option", "open", function() {	
		 var htmlStr = '<iframe src="territory_assign!selectOrg.action" height="100%" width="610" scrolling="no" style="border:0px" frameborder="0"></iframe>';
       $('#orgChoiceDialog').html(htmlStr);
	});
	$('#orgChoiceDialog').dialog('open');
}
</script>
</head>

<body class="content" style="background-image:none;">
<div class="scm">
<div class="title_content">
  <div class="title">Organization <s:if test="salesResourceAssignOrg.organization.name!=null">-${salesResourceAssignOrg.organization.name}</s:if></div>
</div>
<div class="input_box">
 <table border="0" cellpadding="0" cellspacing="0" class="General_table">
                 <tr>
			      <th  width="188">Organization ID</th>
			      <td width="221"><s:textfield name="salesResourceAssignOrg.organization.orgId" id="orgId" Class="NFText" size="20" readonly="true" />
			        <a href="#" onclick="orgSelect()"><img src="images/search.gif" width="16" height="16" align="absmiddle" /></a>
			      </td>
			      <th width="250">Division</th>
			      <td width="278">
			      <s:if test="divisionList!=null">
			      	<s:select id="division_sel"
			      			  list="divisionList"
			      			  name="salesResourceAssignOrg.divisionId"
			      			  listKey="divisionId"
			      			  listValue="name"
			      			  headerKey=""
			      			  headerValue="please select...">
			      	</s:select>
			      </s:if>
			      <s:else>
			      	<select id="division_sel" name="salesResourceAssignOrg.divisionId">
			      		<option value="">please select...</option>
			      	</select>
			      </s:else>
			      </td>
			    </tr>
			    <tr>
			      <th> Organization Name </th>
			      <td><input name="salesResourceAssignOrg.organization.name" value="${salesResourceAssignOrg.organization.name}" id="orgName" Class="NFText" size="20" />
			    
			      </td>
			      <th width="250">Status</th>
			      <td width="278">
			      	 <s:select id="status_sel"  
		          			list="#{'N':'INACTIVE','Y':'ACTIVE'}" 
		          			name="salesResourceAssignOrg.organization.activeFlag" 
		          			listKey="key" 
		          			listValue="value" disabled="true">
		              </s:select>
			      </td>
			    </tr>
			    </tr>
			    <tr>
			      <th valign="top">Description</th>
			      <td><s:textarea name="salesResourceAssignOrg.organization.description" id="description" Class="content_textarea2" style="width:368px;" disabled="true"></s:textarea></td>
			      <th>&nbsp;</th>
			      <td>&nbsp;</td>
			    </tr>
                  <tr>
                    <th valign="top">Modified Date</th>
					  <td>
					  	<s:textfield name="salesResourceAssignOrg.organization.modifyDate" id="modifyDate"  Class="NFText" size="20"  readonly="true">
				         <s:param name='value'>
							<s:date name="salesResourceAssignOrg.organization.modifyDate"  format="yyyy-MM-dd"/>
				         </s:param>
				        </s:textfield>
					  </td>
					  <th>Modified By</th>
					  <td valign="top">
					  <s:textfield name="salesResourceAssignOrg.organization.modifiedName" id="modifiedName" Class="NFText" size="25"  readonly="true"></s:textfield>
					  </td>
                </tr>
              </table>
</div>
<s:form id="orgForm" Class="niceform">
<div class="button_box">
    <input type="button" id="save_btn" name="Submit123"  value="Add" class="search_input" />
      <input type="button" name="Submit124" value="Cancel" class="search_input" onclick="javascript:history.go(-1)"/>
</div>
 <s:hidden name="salesResourceAssignOrg.organization.orgId" id="orgId1"></s:hidden>
 <s:hidden name="sessionId" id="sessionId"></s:hidden>
 <s:hidden name="orgSessionId"></s:hidden>
 <s:hidden name="salesResourceAssignOrg.assignId"></s:hidden>
 <s:hidden name="salesResourceAssignOrg.createdBy"></s:hidden>
 <s:hidden name="salesResourceAssignOrg.creationDate"></s:hidden>
</s:form>
<div id="orgChoiceDialog" title="Organization Choice" style="display:none"></div>
</div>
</body>
</html>