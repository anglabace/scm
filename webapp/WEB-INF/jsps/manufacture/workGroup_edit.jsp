<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Production WorkGroups</title>
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
			   window.location = "resource!edit.action?resourceId=" + resourceId + "&operation_method=view";
			}			
				
            $(function() {
			    $("#resultTable tr:odd").find("td").addClass("list_td2");
			    $("#check_all").click( function() {
			       $(":checkbox").each(function() {
			          this.checked = $("#check_all").get(0).checked;
			       });      
			    });
			    
			   
			    $("#check_del").click( function() {   
			        var param = "id=${param.id}"; 
			        if ($(":checkbox:gt(0):checked").length < 1) {
			           alert('Please select one at least!');
					   return;
			        }
			  		if (!confirm('Are you sure to delete?')) {
						return;
					}
					$(":checkbox:gt(0):checked").each(function() {
					   param += "&groupResId=" + $(this).val();
					   var tdObj = $(this).parent();
                       var trObj = tdObj.parent();  
                       trObj.remove();
					});					
					$("#resultTable tr:even").find("td").removeClass("list_td2");
					$("#resultTable tr:odd").find("td").addClass("list_td2");
			        $.ajax({
						type: "POST",
						url: "work_group!deleteSelect.action",
						data: param,
						success: function(data){
							//parent.location = parent.location;
						},
						error: function(msg){
							alert("Delete Group Resource failed !");
			                $('#select_btn').attr("disabled", false);		
						}
					}); 					
			    });//end of $("#check_del").click.

               //éªè¯formçé»è¾
			   $('#workGroup_form').validate({
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
						 "workGroup.name": { required:true}
					 },
					 messages: {
						 "workGroup.name": { required : "Please enter the name !" }
					 },
					 errorPlacement: function(error, element) {
					 }
				});

               
                $("#save_btn").click (function() {
                   if (! $('#workGroup_form').valid()) {
                      return false;
                   }
                   var formStr = $('#workGroup_form').serialize();
                    $('#save_btn').attr("disabled", true);
                   $.ajax({
						type: "POST",
						url: "work_group!save.action",
						data: formStr,
						dataType: 'json',
						success: function(data, textStatus){
							if(hasException(data)){
				 	           $('#save_btn').attr("disabled", false);				
							}else{                              
							  alert(data.message);
							  $('#save_btn').attr("disabled", false);
							  window.location.reload();
							 // window.location = "work_group!search.action";
							 // window.history.go(-1);
							}
						},
						error: function(xhr, textStatus){
						   alert("failure");
				 	       $('#save_btn').attr("disabled", false);
						   return;
						}
					});
                
                });//end of {$("#save_btn").click};            
                
            	$('#sel_res_dlg').dialog({
					autoOpen: false,
					height: 440,
					width: 800,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
				
                $("#new_res_btn").click( function() {
					$('#sel_res_dlg').dialog("option", "open", function() {	
	              		 var htmlStr = '<iframe src="resource!selectForGroup.action?groupId=${workGroup.id}" height="100%" width="100%" scrolling="no" style="border:0px" frameborder="0"></iframe>';
				         $('#sel_res_dlg').html(htmlStr);
					});
					$('#sel_res_dlg').dialog('open');
                });

            	$('#userChoiceDialog').dialog({
            		autoOpen: false,
            		height: 440,
            		width: 700,
            		modal: true,
            		bgiframe: true,
            		buttons: {
            		}
            	});             
            
            });
          //choice user
            function userSelect() {
            	$('#userChoiceDialog').dialog("option", "open", function() {
             		 var htmlStr = '<iframe src="work_group!selectUser.action" height="100%" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
                    $('#userChoiceDialog').html(htmlStr);
            	});
            	$('#userChoiceDialog').dialog('open');
            }
        </script>
	</head>
	<body class="content">
		<div class="scm">
			<div class="title_content">
				<div class="title">
					${workGroup.name}
				</div>
			</div>
			<div class="input_box">
				<div class="content_box">
					<form method="get" id="workGroup_form" class="niceform">
						<input type="hidden" name="workGroup.id" value="${workGroup.id}" />
						<input type="hidden" name="workGroup.workCenterId" value="${workGroup.workCenterId}" />
						<table border="0" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<th width="160">
									Work Group Name
								</th>
								<td>
									<input name="workGroup.name" value="${workGroup.name}"
										type="text" class="NFText" size="76" />
								</td>
								<th width="150">
									Status
								</th>
								<td>
									<s:select list="{'ACTIVE', 'INACTIVE'}"
										value="workGroup.status" name="workGroup.status"></s:select>
								</td>
							</tr>
							<tr>
								<th valign="top">
									Description
								</th>
								<td>
									<input name="workGroup.description" type="text"
										value="${workGroup.description}" class="NFText" size="76" />
								</td>
								<th>
									Supervisor
								</th>
								<td>
								<input name="workGroup.superName" value="${workGroup.superName}" id="superName" type="text" class="NFText" size="25" readonly="readonly"/>
								<s:hidden name="workGroup.supervisor" id="supervisor"></s:hidden>
								<a href="#" onclick="userSelect()"><img id="org_1Trigger" src="images/search.gif" width="16" height="16" align="absmiddle" /></a>
								</td>
							</tr>
							<tr>
								<th>
									Comment
								</th>
								<td>
									<textarea name="workGroup.comment" class="content_textarea2">${workGroup.comment}</textarea>
								</td>
								<td>
									&nbsp;
								</td>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<th>
									Modified Date
								</th>
								<td>
									<input name="textfield3224" type="text" class="NFText"
										size="20" readonly="readonly"
										value="<s:date name="workGroup.modifyDate" format="yyyy-MM-dd"/>" />
								</td>
								<th>
									Modified By
								</th>
								<td>
									<input name="textfield3322" type="text" class="NFText"
										readonly="readonly" size="20" value="${workGroup.modifyUser}" />
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div id="dhtmlgoodies_tabView1">
				<div class="dhtmlgoodies_aTab">
				<iframe width="100%" height="310px" id="resource_list_frame" name="resource_list_frame" scrolling="no" frameborder="0" src="work_group!getResourceList.action?id=${id}&operation_method=${operation_method}"></iframe>
				</div>
			</div>
			<div class="button_box">
			<saveButton:saveBtn parameter="${operation_method}"
				disabledBtn='<input type="button" name="Submit123" value="Save" class="search_input" disabled="disabled" />'
				saveBtn='<input type="button" name="Submit123" value="Save" class="search_input" id="save_btn" />'
			/> 	
				<input type="button" name="Submit124" value="Cancel"
					class="search_input" <s:if test="operation_method=='edit'">
								  onclick="window.location = 'work_group!search.action';";
					  		 </s:if>
							   <s:else> 
								  onclick="window.history.go(-1);"
							  </s:else>/>
			</div>
		</div>
		<div id="sel_res_dlg" title="Select Production Resource"></div>
		<div id="userChoiceDialog" title="Select Supervisor"></div>
		<script type="text/javascript"> 
          initTabs('dhtmlgoodies_tabView1',Array('Resources'),0,998,320);
        </script>
	</body>
</html>