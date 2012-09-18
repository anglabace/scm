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
	$('#resourceChoiceDialog').dialog({
		autoOpen: false,
		height: 440,
		width: 850,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
	$('#resourceForm').validate({
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
		if($('#salesId').val()=="") {
			alert("Please select a sales resource to save!");
			return false;
		}
	   var formStr = "sessionId="+$("#sessionId").val()+"&salesId="+$('#salesId').val();
	   $('#save_btn').attr("disabled", true);
	   var sessionId = $("#sessionId").val();
	   $.ajax({
				type: "POST",
				url: "sales_group!saveResource.action",
				data: formStr,
				dataType: 'json',
				success: function(data, textStatus){
					if(hasException(data)){
		 	           $('#save_btn').attr("disabled", false);				
					}else{                              
					  alert(data.message);
					  isSaved = true;
					  location.href="sales_group!load.action?sessionId="+sessionId+"&referURL=select";
					}
				},
				error: function(xhr, textStatus){
				   alert("System error! Please contact system administrator for help.");
		 	       $('#save_btn').attr("disabled", false);
				}
			});                
	});//end of {$("#save_btn").click};  
});
function resourceSelect() {
	$('#resourceChoiceDialog').dialog("option", "open", function() {	
		 var htmlStr = '<iframe src="sales_group!selectResource.action" height="100%" width="850" scrolling="no" style="border:0px" frameborder="0"></iframe>';
       $('#resourceChoiceDialog').html(htmlStr);
	});
	$('#resourceChoiceDialog').dialog('open');
}
</script>
</head>

<body class="content" style="background-image:none;">
<div class="scm">
<div class="title_content">
  <div class="title">Sales Resource <s:if test="salesRep.resourceName!=null">-<s:property value="salesRep.resourceName"/></s:if></div>
</div>
<s:form id="resourceForm" Class="niceform">
<div class="input_box">
 <table border="0" cellpadding="0" cellspacing="0" class="General_table">
                <tr>
                <th  width="188">Sales Resource No </th>
                  <td width="221">
                   <s:textfield name="salesRep.salesId" id="salesId"  Class="NFText"  size="20" readonly="true" /></td>
                  <th width="250">Status</th>
                  <td width="278">
	                  <s:select id="status_sel"  
		          			list="#{'INACTIVE':'INACTIVE','ACTIVE':'ACTIVE'}" 
		          			name="salesRep.status" 
		          			listKey="key" 
		          			listValue="value" 
		          			headerKey="" 
							headerValue="Please select..." 
							disabled="true">
		              </s:select>
	              </td>
                </tr>
                <tr>
                <th> Sales Resource Name </th>
                  <td>
                  <s:textfield name="salesRep.resourceName" id="resourceName" Class="NFText"  size="20" readonly="true"/>
                  <s:if test="salesRep.salesId==null">
                  	<a href="#" onclick="resourceSelect()"><img  src="images/search.gif" width="16" height="16" align="absmiddle" /></a>
                  </s:if>
                  
                  </td>
                	<th valign="top">Department</th>
                  <td><s:if test="depList!=null">
			          <s:select name="salesRep.deptId"
			          	id="deptId"
			          	list="depList"
			          	listKey="deptId"
			          	listValue="name"
			          	headerKey=""
			          	headerValue="Please select..." disabled="true">
			          </s:select>
			          </s:if>
			          <s:else>
			          	<select name="salesRep.deptId" id="deptId" disabled="true">
			          		<option value="">Please select...</option>
						</select>
			          </s:else>
			     </td>
                 
                </tr>
                <tr>
                <th valign="top">Description </th>
                  <td><s:textarea name="salesRep.description" id="description" cssClass="content_textarea2" disabled="true"></s:textarea></td>
			     <th>&nbsp;</th>
                  <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <th valign="top">Modified Date</th>
					  <td>
					  	<s:textfield name="salesRep.modifyDate" id="modifyDate"  Class="NFText" size="20"  readonly="true">
				         <s:param name='value'>
							<s:date name="salesRep.modifyDate"  format="yyyy-MM-dd"/>
				         </s:param>
				        </s:textfield>
					  </td>
					  <th>Modified By</th>
					  <td valign="top">
					  <s:textfield name="salesRep.modifiedName" id="modifiedName" Class="NFText" size="25"  readonly="true"></s:textfield>
					  </td>
                </tr>
              </table>
</div>
<div class="button_box">
    <input type="button" id="save_btn" name="Submit123"  value="Save" class="search_input" />
      <input type="button" name="Submit124" value="Cancel" class="search_input" onclick="javascript:history.go(-1)"/>
</div>
 <s:hidden name="sessionId" id="sessionId"></s:hidden>
</s:form>
<div id="resourceChoiceDialog" title="Sales Resource choice" style="display:none"></div>
</div>
</body>
</html>