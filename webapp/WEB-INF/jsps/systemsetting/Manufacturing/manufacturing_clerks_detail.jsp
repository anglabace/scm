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
	$('#clerkChoiceDialog').dialog({
		autoOpen: false,
		height: 440,
		width: 700,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});


	  $('#manClerkForm').validate({
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
       if ($('#manClerkForm').valid()  === false ) {
          return false;
       }
       var formStr = $('#manClerkForm').serialize();
       $('#save_btn').attr("disabled", true);
       $.ajax({
				type: "POST",
				url: "manufacturing_clerks!save.action",
				data: formStr,
				dataType: 'json',
				success: function(data, textStatus){
					if(hasException(data)){
		 	           $('#save_btn').attr("disabled", false);				
					}else{                              
					  alert(data.message);
					  isSaved = true;
					  location.href="manufacturing_clerks!search.action";
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
	};           
});
//choice user
function userSelect() {
	$('#clerkChoiceDialog').dialog("option", "open", function() {
 		 var htmlStr = '<iframe src="manufacturing_clerks!selectUser.action" height="100%" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
        $('#clerkChoiceDialog').html(htmlStr);
	});
	$('#clerkChoiceDialog').dialog('open');
}
//workcenter select event
function centerChange() {
	var dataStr = "workCenterId="+$("#workCenterSel").val();
	//alert(dataStr);
	$.ajax({   
		    url:'manufacturing_clerks!workCenterSel.action',   
		    type:'POST',   
		    dataType: 'json',   
		    data:dataStr,   
			beforeSend:function(xmlhttprequest){
		 	},
			success:function(data){ 
		 		$("#workGroupSel").empty();
	        	var option = "<option value=''>please select...</option>";
	        	$("#workGroupSel").append(option);
	        	for(var i=0;i<data.workGroupList.length;i++) {
	        		var option2 = "<option value='"+data.workGroupList[i].id+"'>"+data.workGroupList[i].name+"</option>";
	        		$("#workGroupSel").append(option2);
	        	}
	        	$("#centerSupervisor").attr("value",data.workCenterSupervisor);
			},
			error:function(data){
				alert("Error!");
			}
		});  
}
//workgroup select event
function groupChange() {
	var dataStr = "workGroupId="+$("#workGroupSel").val();
	$.ajax({   
		    url:'manufacturing_clerks!workGroupSel.action',   
		    type:'POST',   
		    dataType: 'json',   
		    data:dataStr,   
			beforeSend:function(xmlhttprequest){
		 	},
			success:function(data){
	        	$("#groupSupervisor").attr("value",data.workGroupSupervisor);
			},
			error:function(data){
				alert("Error!");
			}
		});  
}
</script>
</head>
<body class="content">
<s:form Class="niceform" id="manClerkForm">
<s:hidden name="manufacturingClerks.id" id="id"></s:hidden>
<div class="scm">
<div class="title_content">
  <div class="title">Clerk #${manufacturingClerks.clerkName}</div>
</div>
<div class="input_box">
		  <div class="content_box">


		      <table border="0" cellpadding="0" cellspacing="0" class="General_table">
                <tr>
                  <th>Clerk ID</th>
                  <td width="410"><s:textfield name="manufacturingClerks.clerkId" id="clerkId"  Class="NFText" size="25" readonly="true" /></td>
                  <th>Status</th>
                  <td width="259"><s:select name="manufacturingClerks.status" list="#{'INACTIVE':'INACTIVE'}" listKey="key" listValue="value" headerKey="ACTIVE"
		          			  headerValue="ACTIVE">
                  </s:select></td>
                </tr>
                <tr>
                  <th width="166">Clerk Name</th>
                  <td><s:textfield name="manufacturingClerks.clerkName" id="clerkName"   Class="NFText" size="25" readonly="true" /><a href="#" onclick="userSelect()"><img id="org_1Trigger" src="images/search.gif" width="16" height="16" align="absmiddle" /></a></td>
                  <th width="171">&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <th width="166">Work Center</th>
                  <td>
                  <s:if test="workCenterList!=null&&workCenterList.size>0">
		          	<s:select id="workCenterSel"
		          			  list="workCenterList"
		          			  name="manufacturingClerks.workCenterId"
		          			  listKey="id"
		          			  listValue="name"
		          			  headerKey=""
		          			  headerValue="please select..."
		          			  onchange="centerChange()">
		          	</s:select>
		          </s:if>
		          <s:else>
		          	<select id="workCenterSel" name="manufacturingClerks.workCenterId" onchange="centerChange()">
		          		<option value="">please select...</option>
		          	</select>
		          </s:else>
                  </td>
                  <th width="171">Center's Supervisor</th>
                  <td><s:textfield name="manufacturingClerks.workCenterSupervisor" id="centerSupervisor" Class="NFText" readonly="true"/></td>
                </tr>
                  <tr>
                  <th width="166">Work Group</th>
                  <td>
                  <s:if test="workGroupList!=null&&workGroupList.size()>0">
		          	<s:select id="workGroupSel" 
		          			  list="workGroupList"
		          			  name="manufacturingClerks.workGroupId"
		          			  listKey="id"
		          			  listValue="name"
		          			  headerKey=""
		          			  headerValue="please select..."
		          			  onchange="groupChange()">
		          	</s:select>
		          </s:if>
		          <s:else>
		          	<select id="workGroupSel" name="manufacturingClerks.workGroupId" onchange="groupChange()">
		          		<option value="">please select...</option>
		          	</select>
		          </s:else>
				  </td>
                  <th width="171">Group's Supervisor</th>
                  <td><s:textfield name="manufacturingClerks.workGroupSupervisor" id="groupSupervisor" Class="NFText"  readonly="true"/></td>
                </tr>
                    
                <tr>
                  <th rowspan="2" valign="top">Comment</th>
                  <td rowspan="2"><s:textarea name="manufacturingClerks.comment"  Class="content_textarea2"></s:textarea></td>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
                      <tr>
                  <th>Modified Date</th>
                  <td>
                   <s:textfield name="manufacturingClerks.modifyDate" Class="NFText" size="25"  readonly="true">
                  	<s:param name='value'>
						<s:date name="manufacturingClerks.modifyDate" format="yyyy-MM-dd"/>
			         </s:param>
                  	</s:textfield>
                  </td>
                  <th>Modified By</th>
                  <td>
                  	<s:textfield name="manufacturingClerks.modifyName" Class="NFText" size="25"  readonly="true"></s:textfield>
                  	<s:hidden name="manufacturingClerks.modifiedBy"></s:hidden>
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
<s:hidden name="manufacturingClerks.creationDate"></s:hidden>
<s:hidden name="manufacturingClerks.createdBy"></s:hidden>
<div id="clerkChoiceDialog" title="Select Employee" style="display:none"></div>
</s:form>	
</body>
</html>