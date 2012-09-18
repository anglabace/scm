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
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
<link href="${global_js_url}jquery/themes/base/ui.all.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.content .scm #dhtmlgoodies_tabView1 .dhtmlgoodies_aTab .General_table fieldset {
	margin: 4px;
}
-->
</style>
<script type="text/javascript">
    var GB_ROOT_DIR = "./greybox/";
</script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>  
<script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}expland.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/gb_scripts.js"></script>
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
			 "qaGroup.groupName":{ required:true}
		 },
		 messages: {
			 "qaGroup.groupName":{ required:"please input a groupName!"}
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
			url: "q_clerks!save.action",
			data: formStr,
			dataType: 'json',
			success: function(data, textStatus){
				if(hasException(data)){//有错误信息.
			           $('#save_btn').attr("disabled", false);				
				}else{                              
				  alert(data.message);
				  isSaved = true;
				  window.location = "q_clerks!search.action";//parent.location = parent.location;
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

function assign(obj){
	 var groupId=$("#groupId").val();
	 if(!groupId||groupId=="") {
		 groupId = $("#sessionGroupId").val();
	 }
     if(!obj.val()){
         alert("Please select Product/Service!");
         obj.focus();
         return;
     }
     var reqUrl = "q_clerks!assign.action?assignValue="+obj.val()+"&sessionGroupId="+groupId;
     //alert(reqUrl);
     $.ajax({
         type:"POST",
         url: reqUrl,
         dataType: 'json',
         success:function(data){
            // obj.remove($(obj).find(":selected"));
              $("#listShip").html(data.qaGroupAssignedStr);
         }
     });
}
function unassign(){
	var groupId=$("#groupId").val();
	 if(!groupId||groupId=="") {
		 groupId = $("#sessionGroupId").val();
	 }
    var checkedObj=$("input[name='assigned']:checked");
    var checkedIds="";
    for(i=0;i<checkedObj.length;i++){
        checkedIds+=$(checkedObj[i]).val()+",";
    }
    if(checkedIds==""){
        alert("Please select the Unassign Product/Service.");
        return;
    }
    var reqUrl = "q_clerks!unassign.action?assignValue="+checkedIds.substr(0, checkedIds.length-1)+"&sessionGroupId="+groupId;
    $.ajax({
        type:"POST",
        url: reqUrl,
        dataType: 'json',
        success: function(data){
            $("#listShip").html(data.qaGroupAssignedStr);
        }

    });
    
}

//choice user
function userSelect() {
	$('#userChoiceDialog').dialog("option", "open", function() {	
 		 var htmlStr = '<iframe src="q_clerks!selectUser.action" height="100%" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
        $('#userChoiceDialog').html(htmlStr);
	});
	$('#userChoiceDialog').dialog('open');
}

//type change
function typeChange() {
	  var type=$("#type").val();
	  $.ajax({
			type: "POST",
			url: "q_clerks!typeChange.action",
			data: "type="+type,
			dataType: 'json',
			success: function(data, textStatus){
				$("#ps_sel").empty();
				 $.each(data.producatServiceMap,function(name,value) {
					 var option2 = "<option value='"+name+"'>"+value+"</option>";
		        		$("#ps_sel").append(option2);
			        });
			},
			error: function(xhr, textStatus){
			   alert("failure");
			   return;
			}
		});
}
</script>
</head>
<body class="content">
<form class="niceform" id="save_form" method="post">
<div class="scm">
<div class="title_content">
  <div class="title">Group ${qaGroup.groupName}</div>
</div>
<div class="input_box">
		  <div class="content_box">

	
		      <table border="0" cellpadding="0" cellspacing="0" class="General_table">
                <tr>
                  <th width="164">Group Name</th>
                  <td width="416" colspan="2"><s:textfield name="qaGroup.groupName"  Class="NFText" size="25"/></td>
                  <th width="148">Status</th>
                  <td width="165">
      				<s:select list="#{'INACTIVE':'INACTIVE'}" name="qaGroup.status" listKey="key" listValue="value" headerKey="ACTIVE" headerValue="ACTIVE"></s:select>
                  </td>
                </tr>
                <tr>
                  <th>Description</th>
                  <td colspan="2"><s:textfield name="qaGroup.description" Class="NFText" size="25"/></td>
                  <th>Supervisor</th>
                  <td>
                  	<s:textfield name="qaGroup.superName" id="superName"  Class="NFText" size="20" readonly="true"/><a href="#" onclick="userSelect()"><img id="org_1Trigger" src="images/search.gif" width="16" height="16" align="absmiddle" /></a>
                  	<s:hidden name="qaGroup.supervisor" id="supervisor"></s:hidden>
                  </td>
                </tr>
                  <tr>
                  <th valign="top">Group Function</th>
                  <td colspan="2">
		          	<s:select id="function_sel"
		          	  			name="qaGroup.groupFunction"
		          	  			list="#{ 'QC':'QC'}"
								headerKey="QA"
								headerValue="QA">
					 </s:select>
                  </td>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
                  <tr>
                  <th valign="top">Modified Date</th>
                  <td colspan="2">
                  <s:textfield name="qaGroup.modifyDate"  Class="NFText"  size="25" readonly="readonly" >
                  	<s:param name="value">
                  		<s:date name="qaGroup.modifyDate" format="yyyy-MM-dd"/>
                  	</s:param>
                  </s:textfield>
                  </td>
                  <th>Modified By</th>
                  <td>
                  <s:textfield name="qaGroup.modifyUser" Class="NFText" size="25" readonly="readonly" >
                  </s:textfield>
                  <s:hidden name="qaGroup.modifiedBy"></s:hidden>
                  </td>
                </tr>
                <tr>
                  <th valign="top">&nbsp;</th>
                  <td colspan="2">&nbsp;</td>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <th valign="top">&nbsp;</th>
                  <td colspan="2">Product/Service Type to be Processed</td>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
                
               <tr>
                  <th valign="top">&nbsp;</th>
                  <td colspan="2">
                   Type
      					<select id="type" onchange="typeChange();">
      						<option value="product">Product</option>
      						<option value="service">Service</option>
      					</select>
                  <s:if test="producatServiceMap!=null&&producatServiceMap.size>0">
			          	<s:select id="ps_sel"
			          	  			list="producatServiceMap" 
			          	  			listKey="key"
									listValue="value">
						 </s:select>
	          	 </s:if>
	          	 <s:else>
	          		<select id="ps_sel">
	          			<option value="">Please select...</option>
	          		</select>
	          	 </s:else>
                    
                    <input type="button" name="Submit33"  class="style_botton" value="Assign" onclick="assign($('#ps_sel'))"/>
                    <input type="button" name="Submit"  class="style_botton" value="Unassign" onclick="unassign()"/>
                  </td>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
                
                <tr>
                  <th>&nbsp;</th>
                  <td colspan="2">
                  	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="listShip">
                      		${qaGroupAssignedStr}
                    </table>
                  </td>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>

            
              </table>
		</div>
  </div>	

<div id="dhtmlgoodies_tabView1">
	<div class="dhtmlgoodies_aTab">
		<iframe width="100%" height="310px" id="operation_list_frame" name="operation_list_frame" scrolling="no" frameborder="0"
			    src="q_clerks!qaClerksList.action?sessionGroupId=${sessionGroupId}"></iframe>
    </div>
</div>
  
<script type="text/javascript">
	initTabs('dhtmlgoodies_tabView1',Array('Clerks'),0,998,310);
</script>
<div class="button_box">
      <input type="button" name="Submit123" id="save_btn"  value="Save" class="search_input" />
      <input type="button" name="Submit124" value="Cancel" class="search_input" onclick="window.history.go(-1)"/>
</div>
<s:hidden name="qaGroup.createdBy"></s:hidden>
<s:hidden name="qaGroup.creationDate"></s:hidden>
<s:hidden name="qaGroup.id" id="groupId"></s:hidden>
<s:hidden name="sessionGroupId" id="sessionGroupId"></s:hidden>
</div>
<div id="userChoiceDialog" title="Select Employee" style="display:none"></div>
</form>
</body>
</html>