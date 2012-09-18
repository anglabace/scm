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
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
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
	$('#assginRuleForm').validate({
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
				 "salesTerritoryAssignRulesDTO.ruleName": { required:true}
			 },
			 messages: {
				 "salesTerritoryAssignRulesDTO.ruleName": { required : "Please enter the ruleName !" }
			 },
			 errorPlacement: function(error, element) {
			 }
		});
		            
	$("#save_btn").click (function() {
	   if ($('#assginRuleForm').valid()  == false ) {
	      return false;
	   }
	   var formStr = $('#assginRuleForm').serialize();
	   $('#save_btn').attr("disabled", true);
	   $.ajax({
				type: "POST",
				url: "assignment_rules!save.action",
				data: formStr,
				dataType: 'json',
				success: function(data, textStatus){
					if(hasException(data)){
		 	           $('#save_btn').attr("disabled", false);				
					}else{                              
					  alert(data.message);
					  isSaved = true;
					  location.href="assignment_rules!input.action";
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


</script>
<script type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/gb_scripts.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>
</head>

<body class="content" style="background-image:none;">

<div class="scm">
<div class="title_content">
  <div class="title"><span class="title_new">Assignment Rule <s:if test="salesTerritoryAssignRulesDTO.ruleId!=null">-<s:property value="salesTerritoryAssignRulesDTO.ruleName"/></s:if></span></div>
</div><div class="input_box">
<s:form id="assginRuleForm" Class="niceform">
 <table border="0" cellpadding="0" cellspacing="0" class="General_table">
    <tr>
      <th width="113">Rule ID</th>
      <td width="426"><s:textfield name="ruleId"  Class="NFText" size="20" readonly="true"/></td>
      <th width="100">Status</th>
      <td width="144"><s:select  
	          			list="#{'ACTIVE':'ACTIVE','INACTIVE':'INACTIVE'}" 
	          			name="salesTerritoryAssignRulesDTO.status" 
	          			listKey="key" 
	          			listValue="value">
	          </s:select></td>
	  </tr>
	  <tr>
      <th width="113">Rule Name</th>
      <td width="426"><s:textfield name="salesTerritoryAssignRulesDTO.ruleName"  Class="NFText" size="20"/></td>
      <td>&nbsp;</td>
      <td valign="top">&nbsp;</td>
	</tr>
	<tr>
      <th valign="top">Description</th>
      <td><s:textarea name="salesTerritoryAssignRulesDTO.description" Class="content_textarea2" style="width:368px;"></s:textarea></td>
      <td>&nbsp;</td>
      <td valign="top">&nbsp;</td>
	</tr>
	<tr>

	  <th valign="top">Modified Date</th>
	  <td>
	  	<s:textfield name="salesTerritoryAssignRulesDTO.modifyDate" Class="NFText" size="20"  readonly="true">
         <s:param name='value'>
			<s:date name="salesTerritoryAssignRulesDTO.modifyDate" format="yyyy-MM-dd"/>
         </s:param>
        </s:textfield>
	  </td>
	  <th>Modified By</th>
	  <td valign="top">
	  <s:textfield name="salesTerritoryAssignRulesDTO.modifiedName" Class="NFText" size="25"  readonly="true"></s:textfield>
      <s:hidden name="salesTerritoryAssignRulesDTO.modifiedBy"></s:hidden>
	  </td>
	  </tr>
  </table>
  <s:hidden name="salesTerritoryAssignRulesDTO.ruleId"></s:hidden>
  <s:hidden name="sessionId" id="sessionId"></s:hidden>
  </s:form>
</div>
  <div id="dhtmlgoodies_tabView1">
    <div class="dhtmlgoodies_aTab">
      <iframe width="100%" height="310px" id="process_list_frame"
					name="process_list_frame" scrolling="no" frameborder="0"
					src="assignment_rules!listProcess.action?sessionId=${sessionId}"></iframe>
    </div>
  </div>
<div class="button_box">
      <saveButton:saveBtn parameter="${operation_method}"
disabledBtn='<input type="button" value="Save" class="search_input" style="background-image: url(images/input_searchbg2.jpg); width: 120px;" disabled="disabled" />'
saveBtn='<input type="button" value="Save" class="search_input" style="background-image: url(images/input_searchbg2.jpg); width: 120px;" id="save_btn" />'
/> 
      <input type="button" name="Submit124" value="Cancel" class="search_input" onclick="window.history.go(-1)"/>
</div>

</div>
</body>
</html>
<script type="text/javascript">
initTabs('dhtmlgoodies_tabView1',Array('Processes'),0,998,320);
</script>