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
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"  />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.dialog.all.js"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1"
			type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}expland.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>

<script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>
<script   language="JavaScript" type="text/javascript">
var GB_ROOT_DIR = "./greybox/";
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
		            
	$("#save_btn").click (function() {
		if($('#serviceType').val()=="") {
			alert("Please select service type name to save!");
			return false;
		}
		if($('#salesId').val()=="") {
			alert("Please select Project Manager to save!");
			return false;
		}
		  var formStr = $('#salesProjectManagerAssignment').serialize();
	   $('#save_btn').attr("disabled", true);
	   $.ajax({
				type: "POST",
				url: "project_manager_assignment!save.action",
				data: formStr,
				dataType: 'json',
				success: function(data, textStatus){
					if(hasException(data)){
		 	           $('#save_btn').attr("disabled", false);				
					}else{
						alert(data.message);                              
					  isSaved = true;
					  location.href="project_manager_assignment!input.action";
					}
				},
				error: function(xhr, textStatus){
				   alert("System error! Please contact system administrator for help.");
		 	       $('#save_btn').attr("disabled", false);
				}
			});                
	});//end of {$("#save_btn").click};  
	 
	
	
	    
	window.onbeforeunload = function() {
		if(isSaved === false){
			return 'Do you want to leave without saving data?';
		}
	}

});
function resourceSelect() {
	$('#resourceChoiceDialog').dialog("option", "open", function() {	
		 var htmlStr = '<iframe src="project_manager_assignment!selectProjectManager.action" height="100%" width="850" scrolling="no" style="border:0px" frameborder="0"></iframe>';
       $('#resourceChoiceDialog').html(htmlStr);
	});
	$('#resourceChoiceDialog').dialog('open');
}

</script>
<script type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/gb_scripts.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>
</head>

<body class="content" style="background-image:none;">

<div class="scm">
<div class="title_content">
  <div class="title"><span class="title_new">Project Manager Assignment   <s:if test="salesProjectManagerAssignment.assignId!=null">-<s:property value="salesProjectManagerAssignment.assignId"/></s:if></span></div>
</div><div class="input_box">
<s:form id="salesProjectManagerAssignment" Class="niceform">
  <table border="0" cellpadding="0" cellspacing="0" class="General_table">
                <tr>
                  <th  width="188">Service Type Name</th>
                  <td width="221">
                    <s:if test="serviceClassificationList!=null">
		        	<s:select id="serviceType"
		        			  list="serviceClassificationList" 
		        			  name="salesProjectManagerAssignment.serviceType"
		        			  listKey="clsId"
		        			  listValue="name"
		        			  headerKey=""
		        			  headerValue="Please select..."></s:select>
			        </s:if>
			        <s:else>
			        	<select  id="serviceType" name="salesProjectManagerAssignment.serviceType">
			        		<option value="">Please select...</option>
			        	</select>
			        </s:else>
                  </td>
                  <th width="250">Project 	Manager</th>
                  <td width="278"><s:textfield name="salesProjectManagerAssignment.resourceName" id="resourceName" readonly="readonly" cssClass="NFText" size="20" />
                  <s:hidden name="salesProjectManagerAssignment.salesId" id="salesId"></s:hidden>
                  <a href="#" onclick="resourceSelect();"><img src="images/search.gif" width="16" height="16" /></a></td>
                </tr>
                <tr>
                  <th valign="top">Comment</th>
                  <td><span style="font-weight: normal;">
                    <s:textarea name="salesProjectManagerAssignment.comments" cssClass="content_textarea2" cssStyle="width:368px;height:30px;"></s:textarea>
                  </span></td>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
                  <tr>
                   <th valign="top">Modified Date</th>
					  <td>
					  	<s:textfield name="salesProjectManagerAssignment.modifyDate" id="modifyDate"  Class="NFText" size="20"  readonly="true">
				         <s:param name='value'>
							<s:date name="salesProjectManagerAssignment.modifyDate"  format="yyyy-MM-dd"/>
				         </s:param>
				        </s:textfield>
					  </td>
					  <th>Modified By</th>
					  <td valign="top">
					  <s:textfield name="salesProjectManagerAssignment.modifiedName" id="modifiedName" Class="NFText" size="25"  readonly="true"></s:textfield>
					  </td>
                </tr>
    </table>
    <s:hidden name="salesProjectManagerAssignment.creationDate"></s:hidden>
    <s:hidden name="salesProjectManagerAssignment.createdBy"></s:hidden>
    <s:hidden name="salesProjectManagerAssignment.modifiedBy"></s:hidden>
  <s:hidden name="salesProjectManagerAssignment.assignId" id="assignId"></s:hidden>
  </s:form>
</div>
<div class="button_box">
      <saveButton:saveBtn parameter="${operation_method}"
disabledBtn='<input type="button" value="Save" class="search_input" style="background-image: url(images/input_searchbg2.jpg); width: 120px;" disabled="disabled" />'
saveBtn='<input type="button" value="Save" class="search_input" style="background-image: url(images/input_searchbg2.jpg); width: 120px;" id="save_btn" />'
/> 
      <input type="button" name="Submit124" value="Cancel" class="search_input" onclick="window.history.go(-1)"/>
</div>

</div>
<div id="resourceChoiceDialog" title="Sales Project Manager" style="display:none"></div>
</body>
</html>