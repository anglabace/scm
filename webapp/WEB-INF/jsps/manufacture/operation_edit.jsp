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
		<script type="text/javascript" src="${global_js_url}show_tag.js"></script>
		<script type="text/javascript">
		var baseUrl = "${global_url}";
			function edit(resourceId) {
			   window.location = "resource!edit.action?resourceId=" + resourceId;
			}			
				
            $(function() {
            	var type = "${operation.type}";
            	$("#selecta option[value='"+type+"']").attr("selected","selected");
            	$("#selecta").trigger("change");
            	$("select").each(function(){
         			var changeWidth=false;
         	   		var len = this.offsetWidth;
         	   		if(len!=0) {
        	 	   		this.style.width = 'auto';
        	 	   		if(len<this.offsetWidth) {
        	 	   			changeWidth = true;
        	 	   		}
        	 	   		this.style.width=len+"px";
        	 	   		$(this).mousedown(function(){
        	 	   			if(changeWidth) {
        	 	   				this.style.width = 'auto';
        	 	   			}
        	 	   			});
        	 	   		$(this).blur(function() {this.style.width = len+"px";});
        	 	   		$(this).change(function(){this.style.width = len+"px";});
         	   		}
         	   		
         	   	});
 
			    $("#resultTable tr:odd").find("td").addClass("list_td2");
			
			    //bind checkboxlist: all|all not
			    $("#check_all").click( function() {
			       $(":checkbox").each(function() {
			          this.checked = $("#check_all").get(0).checked;
			       });      
			    });
			    
			    //delete selected checkbox.
			    $("#check_del").click( function() {   
			        var param = "id=${param.id}"; 
			        //alert($("#resultTable :checkbox:checked").length);
			        if ($("#resultTable :checkbox:checked").length < 1) {
			           alert('Please select one at least!');
					   return;
			        }
			  		if (!confirm('Are you sure to delete?')) {
						return;
					}
					$("#resultTable :checkbox:checked").each(function() {
					   param += "&centerResId=" + $(this).val();
					   var tdObj = $(this).parent();
                       var trObj = tdObj.parent();  
                       if (tdObj.children(":hidden").length >0) {
					     $('<input type="hidden" name="dettachGroupIdList" value="' + $(this).val() + '" />').appendTo($("#operation_form"));
					   }
                       trObj.remove();
					});			
					$("#resultTable tr:even").find("td").removeClass("list_td2");
					$("#resultTable tr:odd").find("td").addClass("list_td2");			
			    });//end of $("#check_del").click.

               //valide form content
			   $('#operation_form').validate({
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
						 "operation.name": { required:true},
						 "operation.runTime":{number:true}
					 },
					 messages: {
						 "operation.name": { required : "Please enter the name !" },
					 "operation.runTime":{number:"Please enter a valid runTime."}
					 },
					 errorPlacement: function(error, element) {
					 }
				});

                //bind save button event
                $("#save_btn").click (function() {
                   if (! $('#operation_form').valid()) {
                      return false;
                   }
                   var formStr = $('#operation_form').serialize();
                   $("#resultTable :checkbox").each(function() {
                       formStr += "&groupIdList=" + $(this).val();
                   });
                   $('#save_btn').attr("disabled", true);
                   $.ajax({
						type: "POST",
						url: "operation!save.action",
						data: formStr,
						dataType: 'json',
						success: function(data, textStatus){
							if(hasException(data)){//有错误信息.
				 	           $('#save_btn').attr("disabled", false);				
							}else{                              
							  alert(data.message);
							  $('#save_btn').attr("disabled", false);
							  isSaved = true;
							  window.location.reload();
							 // window.location = "operation!search.action";
							//  window.history.go(-1);
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
           function userSelect() {
            	$('#userChoiceDialog').dialog("option", "open", function() {
             		 var htmlStr = '<iframe src="work_group!selectUser.action" height="100%" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
                    $('#userChoiceDialog').html(htmlStr);
            	});
            	$('#userChoiceDialog').dialog('open');
            }
           
           function typeChange() {
        	   var typeValue = $("#selecta").val();
        	   $(".disp").hide();
        	   $("#"+typeValue).show();
           }
           
          
        </script>
	</head>
	<body class="content">
		<div class="scm">
			<div class="title_content">
				<div class="title">
					${operation.name}
				</div>
			</div>
			<div class="input_box">
				<div class="content_box">
					<form method="get" id="operation_form" class="niceform">
						<input type="hidden" name="operation.id" value="${operation.id}" />
						<input type="hidden" name="sessOperationId" value="${sessOperationId}" />
						<table border="0" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<td>
								<table border="0" cellpadding="0" cellspacing="0" class="General_table">
								<tr>
								<th width="160">
									Operation Name
								</th>
								<td>
									<input name="operation.name" value="${operation.name}"
										type="text" class="NFText" size="76" />
								</td>
								<th width="150">
									Status
								</th>
								<td>
									<s:select list="{'ACTIVE', 'INACTIVE'}"
										value="operation.status" name="operation.status"></s:select>
								</td>
							</tr>
							<tr>
								<th valign="top">
									Description
								</th>
								<td>
									<input name="operation.description" type="text"
										value="${operation.description}" class="NFText" size="76" />
								</td>
								<th>
									Operation Supervisor
								</th>
								<td>    ${operation.supervisor}
								<%--	<s:select id="super_sel" name="operation.supervisor"
										list="superList" listKey="userId" listValue="loginName"
										value="operation.supervisor"></s:select>--%>
                                    <input name="operation.superName" value="${operation.superName}" id="superName" type="text" class="NFText" size="25" readonly="readonly"/>
								<s:hidden name="operation.supervisor" id="supervisor"></s:hidden>
								<a href="#" onclick="userSelect()"><img id="org_1Trigger" src="images/search.gif" width="16" height="16" align="absmiddle" /></a>
								</td>
							</tr>
							<tr>
								<th>
									Work Center
								</th>
								<td>
									<s:select id="workCenterId_sel" name="operation.workCenterId"
										list="centerList" listKey="id" listValue="name"
										value="operation.workCenterId"></s:select>
								</td>
								<th>

								</th>
								<td>

								</td>
							</tr>
							<tr>
								<th>
									Comment
								</th>
								<td>
									<textarea name="operation.comment" class="content_textarea2">${operation.comment}</textarea>
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
									Setup Time
								</th>
								<td>
									<input name="operation.setupTime" type="text" class="NFText"
										size="20" value="${operation.setupTime}" />
								</td>
								<th>
									Runtime Per Unit
								</th>
								<td>
									<input name="operation.runTime" type="text" class="NFText"
										size="20" value="${operation.runTime}" />
								</td>
							</tr>
							<tr>
								<th>
									UOM
								</th>
								<td>
									<s:select list="{'Hour','Day', 'Month', 'Year'}" value="operation.uom"
										name="operation.uom"></s:select>
								</td>
								<th>

								</th>
								<td>

								</td>
							</tr>
							<tr>
								<th>
									Modified Date
								</th>
								<td>
									<input name="textfield3224" type="text" class="NFText"
										size="20" readonly="readonly"
										value="<s:date name="operation.modifyDate" format="yyyy-MM-dd"/>" />
								</td>
								<th>
									Modified By
								</th>
								<td>
									<input name="textfield3322" type="text" class="NFText"
										readonly="readonly" size="20" value="${operation.modifyUser}" />
								</td>
							</tr>
							</table>
							</td>
							</tr>
						<tr>
						<td>
							<div class="invoice_title">
								<span style="cursor: pointer"
									onclick="toggle_showmore('Goods_InfoItem', 'Goods_Info');"><img
										src="${global_image_url}ad.gif" width="11" height="11"
										id="Goods_InfoItem" /> &nbsp;Experimental Data</span>
							</div>
							<div id="Goods_Info"  style="display: block;">
							 <div>
								<table border="0" cellpadding="0" cellspacing="0"
											class="General_table">
											<tr>
								              <th width="202">Experimental Data Type</th>
								              <td width="180">
								              <select name="operation.type" style="width:207px;" id="selecta" onchange="typeChange();">
								                <option value="antibody_elisa">Antibody-Elisa Result</option>
								                <option value="antibody_bleed">Antibody-Bleed Result</option>
								                <option value="antibody_purification">Antibody-Purification Result</option>
						                      </select>
						                      </td>
							                </tr>
											
								</table>
							 </div>
							<div id="antibody_elisa" class="disp" style="display: block;">
								<table border="0" cellpadding="0" cellspacing="0"
												class="General_table">
									  <tr>
						                <th width="202">Host Name</th>
						                <td width="360">
						                <select name="select6" style="width:207px;" disabled="disabled">
						                	  
					                    </select>
					                    </td>
						                <th width="143">Host Amount</th>
						                <td><input name="textfield6" type="text" class="NFText" size="35" disabled="disabled"/></td>
					                  </tr>
						              <tr>
						                <th>Host No</th>
						                <td>
						                <select name="select8" style="width:207px;" disabled="disabled">
						                 
					                    </select>
					                    </td>
						                <th>Experimental Result</th>
						                <td>
						                <select name="select6" style="width:207px;" disabled="disabled">
						                  <option>Normal</option>
						                  <option>Abnormal</option>
						                </select>
						                </td>
	                  				</tr>
								</table>
							</div>
							<div id="antibody_bleed" class="disp" style="display: none;">
								<table border="0" cellpadding="0" cellspacing="0"
												class="General_table">
										   <tr>
								              <th width="202">Host No</th>
								              <td width="360">
								              	<select name="select4" style="width:207px;" disabled="disabled">
												</select>
											  </td>
								              <th width="143">&nbsp;</th>
								              <td>&nbsp;</td>
							                </tr>
								            <tr>
								              <th>Volume</th>
								              <td><input name="textfield7" type="text" class="NFText" size="35" disabled="disabled"/></td>
								              <th>Location</th>
								              <td><input name="textfield7" type="text" class="NFText" size="35" disabled="disabled"/></td>
							                </tr>
								            <tr>
								              <th>Remains</th>
								              <td><input name="textfield4" type="text" class="NFText" size="35" disabled="disabled"/></td>
								              <th>Remains Location</th>
								              <td><input name="textfield7" type="text" class="NFText" size="35" disabled="disabled"/></td>
							                </tr>
								            <tr>
								              <th>Comment</th>
								              <td><textarea name="textarea" class="content_textarea2" style="width:340px;" disabled="disabled"></textarea></td>
								              <th>&nbsp;</th>
								              <td>&nbsp;</td>
				                			</tr>
												
								</table>
							</div>
							<div id="antibody_purification" class="disp" style="display: none;">
								<table border="0" cellpadding="0" cellspacing="0"
												class="General_table">
												
												<tr>
							                      <th>Purification Results</th>
							                      <td width="360">
							                      <select name="select7" style="width:207px;" id="purification_result" disabled="disabled">
							                        <option>Add Purification Result</option>
							                      </select>
							                      <input type="button" name="Submit2" class="style_botton" value="Add" disabled="disabled"/>
							                      <input type="button" name="Submit" class="style_botton" value="Remove" disabled="disabled"/></td>
							                      <th>&nbsp;</th>
							                      <td>&nbsp;</td>
							                    </tr>
									            <tr>
									              <th width="202">Date</th>
									              <td width="324"><input name="textfield8" type="text" class="NFText" size="35" disabled="disabled"/></td>
									              <th width="143">Host No</th>
									              <td><select name="select4" style="width:207px;" disabled="disabled">
												    </select></td>
								                </tr>
									            <tr>
									              <th>Loading</th>
									              <td><input name="textfield9" type="text" class="NFText" size="35" disabled="disabled"/></td>
									              <th>Remains Gel</th>
									              <td>
									              	<select name="textfield9" style="width:207px;" disabled="disabled">
									              	</select>
									              </td>
								                </tr>
									            <tr>
									              <th>Concentration</th>
									              <td><input name="textfield9" type="text" class="NFText" size="35" disabled="disabled"/></td>
									              <th>Volume</th>
									              <td><input name="textfield9" type="text" class="NFText" size="35" disabled="disabled"/></td>
								                </tr>
									            <tr>
									              <th>Quality</th>
									              <td><input name="textfield4" type="text" class="NFText" size="35" disabled="disabled"/></td>
									              <th>&nbsp;</th>
									              <td>&nbsp;</td>
								                </tr>
									            <tr>
									              <th>Comment</th>
									              <td><textarea name="textarea" class="content_textarea2" style="width:340px;" disabled="disabled"></textarea></td>
									              <th>&nbsp;</th>
									              <td>&nbsp;</td>
								                </tr>
								</table>
							</div>
							</div>
						</td>
						</tr>
						</table>
					</form>
				</div>
			</div>
			<div id="dhtmlgoodies_tabView1">
				<div class="dhtmlgoodies_aTab">
					<iframe width="100%" height="310px" id="resource_list_frame" name="resource_list_frame" scrolling="no" frameborder="0" src="operation!getResourceList.action?sessOperationId=${sessOperationId}"></iframe>
				</div>
			</div>
			<div class="button_box">
			<saveButton:saveBtn parameter="${operation_method}"
				disabledBtn='<input type="button" name="Submit123" value="Save" class="search_input" disabled="disabled" />'
				saveBtn='<input type="button" name="Submit123" value="Save" class="search_input" id="save_btn" />'
			/> 	
				<input type="button" name="Submit124" value="Cancel"
					class="search_input" <s:if test="operation_method=='edit'">
								  onclick=" window.location = 'operation!search.action';";
					  		 </s:if>
							   <s:else> 
								  onclick="window.history.go(-1);"
							  </s:else>/>
			</div>
		</div>
		<div id="sel_res_dlg" title="Select Resource"></div>
        <div id="userChoiceDialog" title="Select Supervisor"></div>
		<script type="text/javascript"> 
          initTabs('dhtmlgoodies_tabView1',Array('Resource'),0,998,315);
          var isSaved = false;
		  window.onbeforeunload = function() {
			 if(isSaved === false){
					return 'Do you want to leave without saving data?';
			 }
		  }
        </script>
	</body>
</html>