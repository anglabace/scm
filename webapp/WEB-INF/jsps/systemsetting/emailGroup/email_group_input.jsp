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
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
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
<script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1"
			type="text/javascript"></script>
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<script>
$(function(){
	var isSaved = false;


	  $('#emailGroupForm').validate({
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
				 "manufacturingClerks.clerkId": { required:true}
			 },
			 messages: {
				 "manufacturingClerks.clerkId": { required : "Please select a user !" }
			 },
			 errorPlacement: function(error, element) {
			 }
		});

    $("#save_btn").click (function() {
       if ($('#emailGroupForm').valid()  === false ) {
          return false;
       }
       var formStr = $('#emailGroupForm').serialize();
       $('#save_btn').attr("disabled", true);
       $.ajax({
				type: "POST",
				url: "email_group!save.action",
				data: formStr,
				dataType: 'json',
				success: function(data, textStatus){
					if(hasException(data)){
		 	           $('#save_btn').attr("disabled", false);
					}else{
					  alert(data.message);
					  isSaved = true;
					  location.href="email_group!search.action";
					}
				},
				error: function(xhr, textStatus){
				   alert("failure");
		 	       $('#save_btn').attr("disabled", false);
				}
			});
    });//end of {$("#save_btn").click};
    window.onbeforeunload = function() {
		if(isSaved === false){
			return 'Do you want to leave without saving data?';
		}
	}
})
//choice user
function userSelect() {
	$('#clerkChoiceDialog').dialog("option", "open", function() {
 		 var htmlStr = '<iframe src="manufacturing_clerks!selectUser.action" height="100%" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
        $('#clerkChoiceDialog').html(htmlStr);
	});
	$('#clerkChoiceDialog').dialog('open');
}

</script>
</head>
<body class="content">
<s:form Class="niceform" id="emailGroupForm">
<div class="scm">
<div class="title_content">
  <div class="title">Email Group - #${emailGroupDTO.groupFunction}</div>
</div>
<div class="input_box">
		  <div class="content_box">

		      <table border="0" cellpadding="0" cellspacing="0" class="General_table">
                <tr>
                  <th>Email Group Name</th>
                  <td width="410"><s:textfield name="emailGroupDTO.groupName" id="groupName"  Class="NFText" size="25" /><s:hidden name="emailGroupDTO.groupId" id="groupId"/></td>
                  <th>Status</th>
                  <td width="259"><s:select name="emailGroupDTO.status" list="#{'INACTIVE':'INACTIVE'}" listKey="key" listValue="value" headerKey="ACTIVE"
		          			  headerValue="ACTIVE">
                  </s:select></td>
                </tr>
                <tr>
                  <th width="166">Email Group Function</th>
                  <td>
                  <s:if test="mailGroupDropdownList!=null&&mailGroupDropdownList.size>0">
		          	<s:select id="workCenterSel"
		          			  list="mailGroupDropdownList"
		          			  name="emailGroupDTO.groupFunction"
		          			  listKey="id"
		          			  listValue="name"
		          			  headerKey=""
		          			  headerValue="please select...">
		          	</s:select>
		          </s:if>
		          <s:else>
		          	<select id="workCenterSel" name="emailGroupDTO.groupFunction">
		          		<option value="">please select...</option>
		          	</select>
		          </s:else>
                  </td>
                  <th width="171">&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <th width="166">Description</th>
                    <td>
                   <s:textarea name="emailGroupDTO.description"  Class="content_textarea2"></s:textarea>
                   </td>
                </tr>
                  <tr>
                  <th width="166">Email Addresses</th>
                  <td>
                     <s:textarea name="emailGroupDTO.groupAddress"  Class="content_textarea2"></s:textarea>
				  </td>
                </tr>
                      <tr>
                  <th>Modified Date</th>
                  <td>
                   <s:textfield name="emailGroupDTO.modifyDate" Class="NFText" size="25"  readonly="true">
                  	<s:param name='value'>
						<s:date name="emailGroupDTO.modifyDate" format="yyyy-MM-dd"/>
			         </s:param>
                  	</s:textfield>
                  </td>
                  <th>Modified By</th>
                  <td>
                  	<s:textfield name="emailGroupDTO.modifiedByName" Class="NFText" size="25"  readonly="true"></s:textfield>
                  	<s:hidden name="emailGroupDTO.modifiedBy"></s:hidden>
                  </td>
                </tr>
                  <tr>
                  <th valign="top">&nbsp;</th>
                  <td>&nbsp;</td>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>

              </table>
		</div>
  </div>
</div>

<div class="button_box">
      <input type="button" id="save_btn" name="Submit123"  value="Save" class="search_input" />
      <input type="button" name="Submit124" value="Cancel" class="search_input" onclick="window.history.go(-1)"/>
</div>
<s:hidden name="emailGroupDTO.creationDate"></s:hidden>
<s:hidden name="emailGroupDTO.createdBy"></s:hidden>
<div id="clerkChoiceDialog" title="Select Employee" style="display:none"></div>
</s:form>
</body>
</html>