<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Production Operations</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}tab-view.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="${global_js_url}jquery/jquery.js"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
		<script src="${global_js_url}jquery/jquery.validate.js?v=1"
			type="text/javascript"></script>
		<script type="text/javascript" src="${global_js_url}tab-view.js"></script>
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
		<script type="text/javascript">
			function edit(resourceId) {
			   window.location = "resource!edit.action?resourceId=" + resourceId;
			}			
				
            $(function() {		   
               if ($("#status_hid").val() == '') {
                  $("#status_hid").val('ACTIVE');
               } 
               //valide form content
			   $('#org_form').validate({
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
						 "orgGroup.groupCode": { required:true}
					 },
					 messages: {
						 "orgGroup.groupCode": { required : "Please enter the groupCode !" }
					 },
					 errorPlacement: function(error, element) {
					 }
				});

                //bind save button event
                $("#save_btn").click (function() {
                   var formStr = $('#org_form').serialize();
                   var delIdList = window.frames["org_list_frame"].$("#delIdList_hid").val();
                   //alert(delIdList);
                   //alert(formStr + delIdList);
                   //return;
                   if (! $('#org_form').valid()) {
                      return false;
                   }
                   
                   $('#save_btn').attr("disabled", true);
                   $.ajax({
						type: "POST",
						url: "organization!saveOrgGroup.action",
						data: formStr + delIdList,
						dataType: 'json',
						success: function(data, textStatus){
							if(hasException(data)){//有错误信息.
				 	           $('#save_btn').attr("disabled", false);				
							}else{                              
							  alert(data.message);
							  if(data.result=="Failure") {
								  $('#save_btn').attr("disabled", false);
								  return;
							  }
							  window.location = "organization!searchOrgGroup.action";
							  //window.history.go(-1);
							}
						},
						error: function(xhr, textStatus){
						   alert("failure");
				 	       $('#save_btn').attr("disabled", false);
						   return;
						}
					});
                
                });//end of {$("#save_btn").click};            
                
                $("#status_sel").change (function() {
                   $("#modify_btn").show();          
                   $('#modify_btn').attr("disabled", false);      
                });
                
                $("#modify_btn").click (function() {
                   $('#modify_btn').attr("disabled", true);
                   $("#status_hid").val($("#status_sel").val());
                });
                
            	$('#sel_res_dlg').dialog({
					autoOpen: false,
					height: 440,
					width: 700,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
				
                $("#new_res_btn").click( function() {
					$('#sel_res_dlg').dialog("option", "open", function() {	
	              		 var htmlStr = '<iframe src="resource!selectForGroup.action?centerId=${operation.id}" height="100%" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
				         $('#sel_res_dlg').html(htmlStr);
					});
					$('#sel_res_dlg').dialog('open');
                });
                             
            
            });
        </script>
	</head>
	<body class="content">
		<div class="scm">
			<div class="title_content">
				<div class="title">
					Organization Group Information
				</div>
			</div>
			<div class="input_box">
				<div class="content_box">
					<form method="get" id="org_form" class="niceform">
						<input type="hidden" name="orgGroup.id" value="${orgGroup.id}" />
						<input type="hidden" name="sessOperationId"
							value="${sessOperationId}" />
						<table border="0" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<th width="138">
									Organization Group Code
								</th>
								<td width="385">
									<input name="orgGroup.groupCode" value="${orgGroup.groupCode}"
										type="text" class="NFText" size="20" />
								</td>
								<th width="96">
									Name
								</th>
								<td width="116">
									<input name="orgGroup.name" value="${orgGroup.name}"
										type="text" class="NFText" size="20" />
								</td>
								<td width="74">
									&nbsp;
								</td>
							</tr>
							<tr>
								<th valign="top">
									Description
								</th>
								<td>
									<input name="orgGroup.description" type="text"
										value="${orgGroup.description}" class="NFText" size="60" />
								</td>
								<th>
									Status
								</th>
								<td>
									<s:select list="{'ACTIVE', 'INACTIVE'}" value="orgGroup.status"
										name="temp_status" id="status_sel"></s:select>
								    <input type="hidden" name="orgGroup.status" id="status_hid" value="${orgGroup.status}" />
								</td>
								<td>
								    <c:if test="${!empty param.id}">
									<input name="Submit2" type="button" id='modify_btn'
										class="style_botton" value="Modify" style="display: none" /></c:if>
								</td>
							</tr>

						</table>
					</form>
				</div>
			</div>
			<div id="dhtmlgoodies_tabView1">
				<div class="dhtmlgoodies_aTab">
					<iframe width="100%" height="310px" id="org_list_frame"
						name="org_list_frame" scrolling="no" frameborder="0"
						src="organization!showOrganizationListForGroup.action?id=${orgGroup.id}"></iframe>
				</div>
			</div>
			<div class="button_box">
				<saveButton:saveBtn parameter="${operation_method}"
					disabledBtn='<input type="button" name="Submit123" value="Save" class="search_input" disabled="disabled" />'
					saveBtn='<input type="button" name="Submit123" value="Save" class="search_input" id="save_btn" />' />
				<input type="button" name="Submit124" value="Cancel"
					class="search_input"
					onclick="window.location='organization!searchOrgGroup.action';" />
			</div>
		</div>
		<div id="sel_res_dlg" title="Select Resource"></div>
		<script type="text/javascript"> 
          initTabs('dhtmlgoodies_tabView1',Array('Organizations'),0,998,315);
          var isSaved = false;
		  window.onbeforeunload = function() {
			 if(isSaved === false){
					return 'Do you want to leave without saving data?';
			 }
		  }
        </script>
	</body>
</html>