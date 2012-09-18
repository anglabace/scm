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
<script src="${global_js_url}jquery/jquery.validate.js?v=1"
			type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.dialog.all.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}expland.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>

<script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>
<script   language="JavaScript" type="text/javascript">
var GB_ROOT_DIR = "./greybox/";
$(function(){
	var isSaved = false;
	$('#userChoiceDialog').dialog({
		autoOpen: false,
		height: 440,
		width: 700,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
	$('#saleGroupForm').validate({
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
			 rules: {
				 "salesGroupDTO.groupCode": { required:true}
			 },
			 messages: {
				 "salesGroupDTO.groupCode": { required : "Please enter the group name !" }
			 },
			 errorPlacement: function(error, element) {
			 }
		});
		            
	$("#save_btn").click (function() {
	   if ($('#saleGroupForm').valid()  == false ) {
	      return false;
	   }
	   var formStr = $('#saleGroupForm').serialize();
	   $('#save_btn').attr("disabled", true);
	   $.ajax({
				type: "POST",
				url: "sales_group!save.action",
				data: formStr,
				dataType: 'json',
				success: function(data, textStatus){
					if(hasException(data)){
		 	           $('#save_btn').attr("disabled", false);				
					}else{                              
					  alert(data.message);
					  isSaved = true;
					  location.href="sales_group!input.action";
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
//choice user
function userSelect() {
	$('#userChoiceDialog').dialog("option", "open", function() {	
 		 var htmlStr = '<iframe src="sales_group!selectUser.action" height="100%" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
        $('#userChoiceDialog').html(htmlStr);
	});
	$('#userChoiceDialog').dialog('open');
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
  <div class="title"><span class="title_new">Sales Group <s:if test="salesGroupDTO.groupId!=null">-<s:property value="salesGroupDTO.groupCode"/></s:if></span></div>
</div><div class="input_box">
<s:form id="saleGroupForm" Class="niceform">
 <table border="0" cellpadding="0" cellspacing="0" class="General_table">
    <tr>
       <th width="160">Sales Group Name</th>
          <td><s:textfield name="salesGroupDTO.groupCode" Class="NFText"  size="20" id="grouptxt"/></td>
       <th width="150">Status</th>
          <td>
          <s:select id="status_sel"  
		          			list="#{'ACTIVE':'ACTIVE','INACTIVE':'INACTIVE'}" 
		          			name="salesGroupDTO.status" 
		          			listKey="key" 
		          			listValue="value">
		  </s:select>
          </td>
    </tr>
    <tr>
       <th valign="top">Department</th>
          <td>
			<s:if test="depList!=null">
			          <s:select name="salesGroupDTO.deptId"
			          	list="depList"
			          	listKey="deptId"
			          	listValue="name"
			          	headerKey=""
			          	headerValue="Please select...">
			          </s:select>
          </s:if>
          <s:else>
          	<select name="salesGroupDTO.deptId">
          		<option value="">Please select...</option>
			</select>
          </s:else>
		  </td>
          <th>Supervisor</th>
          <td>
          	<s:textfield name="salesGroupDTO.supervisorName" id="superName"  Class="NFText" size="20" readonly="true"/><a href="#" onclick="userSelect()"><img id="org_1Trigger" src="images/search.gif" width="16" height="16" align="absmiddle" /></a>
            <s:hidden name="salesGroupDTO.supervisor" id="supervisor"></s:hidden>
          </td>
    </tr>
    <tr>
         <th>Description</th>
         <td><s:textarea name="salesGroupDTO.description" Class="content_textarea2"></s:textarea></td>
         <td>&nbsp;</td>
         <td>&nbsp;</td>
    </tr>
	<tr>
	  <th valign="top">Modified Date</th>
	  <td>
	  	<s:textfield name="salesGroupDTO.modifyDate" Class="NFText" size="20"  readonly="true">
         <s:param name='value'>
			<s:date name="salesGroupDTO.modifyDate" format="yyyy-MM-dd"/>
         </s:param>
        </s:textfield>
	  </td>
	  <th>Modified By</th>
	  <td valign="top">
	  <s:textfield name="salesGroupDTO.modifiedName" Class="NFText" size="25"  readonly="true"></s:textfield>
      <s:hidden name="salesGroupDTO.modifiedBy"></s:hidden>
	  </td>
	  </tr>
  </table>
  <s:hidden name="salesGroupDTO.gsCoId"></s:hidden>
  <s:hidden name="salesGroupDTO.createdBy"></s:hidden>
  <s:hidden name="salesGroupDTO.creationDate"></s:hidden>
  <s:hidden name="salesGroupDTO.groupId"></s:hidden>
  <s:hidden name="sessionId" id="sessionId"></s:hidden>
  </s:form>
</div>
  <div id="dhtmlgoodies_tabView1">
    <div class="dhtmlgoodies_aTab">
      <iframe width="100%" height="310px" id="resource_list_frame"
					name="resource_list_frame" scrolling="no" frameborder="0"
					src="sales_group!listResource.action?sessionId=${sessionId}"></iframe>
    </div>
  </div>
<div class="button_box">
    <saveButton:saveBtn parameter="${operation_method}"
disabledBtn='<input type="button" value="Save" class="search_input" style="background-image: url(images/input_searchbg2.jpg); width: 120px;" disabled="disabled" />'
saveBtn='<input type="button" value="Save" class="search_input" style="background-image: url(images/input_searchbg2.jpg); width: 120px;" id="save_btn"/>'
/> 
      <input type="button" name="Submit124" value="Cancel" class="search_input" onclick="window.history.go(-1)"/>
</div>

</div>
<div id="userChoiceDialog" title="Select Employee" style="display:none"></div>
</body>
</html>
<script type="text/javascript">
initTabs('dhtmlgoodies_tabView1',Array('Sales Resources'),0,998,320);
</script>