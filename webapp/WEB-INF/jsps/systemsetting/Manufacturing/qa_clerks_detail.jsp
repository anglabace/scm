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
<link href="${global_js_url}greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.content .scm #dhtmlgoodies_tabView1 .dhtmlgoodies_aTab .General_table fieldset {
	margin: 4px;
}
-->
</style>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1"
			type="text/javascript"></script>
<script
	src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js"
	type="text/javascript"></script>
<script>
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
	$('#clerkChoiceDialog').dialog({
		autoOpen: false,
		height: 440,
		width: 700,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
	$('#save_form').validate({
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
			 "qaClerk.userId": { required:true}
		 },
		 messages: {
			 "qaClerk.userId": { required : "Please select a clerk !" }
		 },
		 errorPlacement: function(error, element) {
		 }
	});
	$("#save_btn").click( function() {
		if ($('#save_form').valid()  === false ) {
            return false;
         }
	    var formStr = $("#save_form").serialize();
        $('#save_btn').attr("disabled", true);
        $.ajax({
			type: "POST",
			url: "q_clerks!saveClerk.action",
			data: formStr,
			dataType: 'json',
			success: function(data, textStatus){
				if(hasException(data)){//有错误信息.
			           $('#save_btn').attr("disabled", false);				
				}else{                              
				  alert(data.message);
				  isSaved = true;
				  var sessionGroupId = $("#sessionGroupId").val();
				  window.location = "q_clerks!load.action?referURL=select&sessionGroupId="+sessionGroupId;//parent.location = parent.location;
				}
			},
			error: function(xhr, textStatus){
			   alert("failure");
			       $('#save_btn').attr("disabled", false);
			}
	   });    
    });
    
	 window.onbeforeunload = function() {
			if(isSaved === false){
				return 'Do you want to leave without saving data?';
			}
	}    
});

//choice user
function userSelect() {
	$('#userChoiceDialog').dialog("option", "open", function() {	
 		 var htmlStr = '<iframe src="q_clerks!selectUser.action" height="100%" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
        $('#userChoiceDialog').html(htmlStr);
	});
	$('#userChoiceDialog').dialog('open');
}

//choice user
function clerkSelect() {
	$('#clerkChoiceDialog').dialog("option", "open", function() {
 		 var htmlStr = '<iframe src="manufacturing_clerks!selectUser.action" height="100%" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
        $('#clerkChoiceDialog').html(htmlStr);
	});
	$('#clerkChoiceDialog').dialog('open');
}
</script>
</head>

<body class="content">
<form class="niceform" id="save_form" method="post">
<div class="scm">
<div class="title_content">
  <div class="title">Clerk ${qaClerk.clerkName}</div>
</div>
<s:hidden name="sessionGroupId" id="sessionGroupId"/>
<s:hidden name="id" id="id"></s:hidden>
<div class="input_box">
		  <div class="content_box">
		      <table border="0" cellpadding="0" cellspacing="0" class="General_table">
                <tr>
                  <th>Clerk ID</th>
                  <td colspan="2"><s:textfield name="qaClerk.userId" id="clerkId" Class="NFText" size="25" readonly="true" /></td>
                  <th>Status</th>
                  <td width="259">
                  	<s:select list="#{'ACTIVE':'ACTIVE','INACTIVE':'INACTIVE'}" name="qaClerk.status" listKey="key" listValue="value" headerKey="" headerValue="please select..."></s:select>
                  </td>
                </tr>
                <tr>
                  <th width="166">Name</th>
                  <td colspan="2"><s:textfield name="qaClerk.clerkName" id="clerkName" Class="NFText"  size="25" readonly="true" />
                  <a href="#" onclick="clerkSelect()"><img id="org_1Trigger" src="images/search.gif" width="16" height="16" align="absmiddle" /></a>
                  </td>
                  <th width="171">Supervisor</th>
                  <td>
                  	<s:textfield name="qaClerk.superName" id="superName" Class="NFText" size="25" readonly="true"/>
                  	<a href="#" onclick="userSelect()"><img id="org_1Trigger" src="images/search.gif" width="16" height="16" align="absmiddle" /></a>
                  	<s:hidden name="qaClerk.supervisor" id="supervisor"></s:hidden>
                  </td>
                </tr>
                <tr>
                  <th rowspan="2" valign="top">Comment</th>
                  <td colspan="2" rowspan="2"><s:textarea name="qaClerk.comment"  Class="content_textarea2"></s:textarea></td>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
                      <tr>
                  <th>Modified Date</th>
                  <td colspan="2">
                  <s:textfield name="qaClerk.modifyDate"  Class="NFText" size="25" readonly="true">
                  	<s:param name="value">
                  		<s:date name="qaClerk.modifyDate" format="yyyy-MM-dd"/>
                  	</s:param>
                  </s:textfield>
                  </td>
                  <th>Modified By</th>
                  <td>
                  <s:textfield name="qaClerk.modifyUser" Class="NFText"  size="25" readonly="true" >
                  </s:textfield>
                  <s:hidden name="qaClerk.modifiedBy"/>
                  </td>
                </tr>
                  <tr>
                  <th valign="top">&nbsp;</th>
                  <td colspan="2">&nbsp;</td>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
              </table>
       
		</div>
  </div>
</div>

<div class="button_box">
      <input type="button" name="Submit123" id="save_btn"  value="Save" class="search_input" />
      <input type="button" name="Submit124" value="Cancel" class="search_input" onclick="window.history.go(-1)"/>
</div>
<s:hidden name="qaClerk.id"></s:hidden>
<s:hidden name="qaClerk.creationDate"/>
<s:hidden name="qaClerk.createdBy"></s:hidden>
<div id="userChoiceDialog" title="Select Employee" style="display:none"></div>
<div id="clerkChoiceDialog" title="clerk choice" style="display:none"></div>
</form>
</body>
</html>
