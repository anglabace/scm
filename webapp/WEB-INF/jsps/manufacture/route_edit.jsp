<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Production Routes</title>
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
			function check() {
				if($("#flag").attr("checked")==true) {
			  		if (!confirm('Are you sure to set the route as default?')) {
			  			$("#flag").attr("checked",false)
					}
				}
			}			
				
            $(function() {
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
                var flagVal = '${route.defaultFlag}';
                 if (flagVal == 'Y') {
                  $("#flag").get(0).checked = true;
                } else {
                  $("#flag").get(0).checked = false;
                }
                var rollupVal = '${route.costRollupFlag}';
                 if (rollupVal == 'Y') {
                  $("#rollup").get(0).checked = true;
                } else {
                  $("#rollup").get(0).checked = false;
                }                
                
			    $("#resultTable tr:odd").find("td").addClass("list_td2");
			
			    //复选框绑定: 全选|全不选
			    $("#check_all").click( function() {
			       $(":checkbox").each(function() {
			          this.checked = $("#check_all").get(0).checked;
			       });      
			    });
			    
			    //删除选中的checkbox.
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
					     $('<input type="hidden" name="dettachGroupIdList" value="' + $(this).val() + '" />').appendTo($("#route_form"));
					   }
                       trObj.remove();
					});			
					$("#resultTable tr:even").find("td").removeClass("list_td2");
					$("#resultTable tr:odd").find("td").addClass("list_td2");			
			    });//end of $("#check_del").click.

               //验证form的逻辑
			   $('#route_form').validate({
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
						 "route.name": { required:true}
					 },
					 messages: {
						 "route.name": { required : "Please enter the name !" }
					 },
					 errorPlacement: function(error, element) {
					 }
				});

                //绑定保存按钮事件.
                $("#save_btn").click (function() {
                   if (! $('#route_form').valid()) {
                      return false;
                   }
                   var formStr = $('#route_form').serialize();
                   if (! $("#flag").get(0).checked) {
                       formStr += "&route.defaultFlag=N";
                   }
                   if (! $("#rollup").get(0).checked) {
                       formStr += "&route.costRollupFlag=N";
                   }
                   $("#resultTable :checkbox").each(function() {
                       formStr += "&groupIdList=" + $(this).val();
                   });
                   $('#save_btn').attr("disabled", true);
                   $.ajax({
						type: "POST",
						url: "route!save.action",
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
							 // window.location = "route!search.action";
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
                
            	$('#sel_res_dlg').dialog({
					autoOpen: false,
					height: 440,
					width: 820,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
				
                $("#new_res_btn").click( function() {
					$('#sel_res_dlg').dialog("option", "open", function() {	
	              		 var htmlStr = '<iframe src="work_group!selectForCenter.action?centerId=${route.id}" height="100%" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
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
					Routing Information
				</div>
			</div>
			<div class="input_box">
				<div class="content_box">
					<form method="get" id="route_form" class="niceform">
						<input type="hidden" name="route.id" value="${route.id}" />
						<input type="hidden" name="sessRouteId" value="${sessRouteId}" />
						<table border="0" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<th width="160">
									Routing Name
								</th>
								<td>
									<input name="route.name" value="${route.name}" type="text"
										class="NFText" size="76" />
								</td>
								<th width="150">
									Status
								</th>
								<td>
									<s:select list="{'ACTIVE', 'INACTIVE'}" value="route.status"
										name="route.status"></s:select>
								</td>
							</tr>
							<tr>
								<th valign="top">
									Description
								</th>
								<td>
									<input name="route.description" type="text"
										value="${route.description}" class="NFText" size="76" />
								</td>
								<th>
									Storage Warehouse
								</th>
								<td>
									<s:select id="warehouse_sel" name="route.warehouseId"
										list="warehouseList" listKey="warehouseId" listValue="name"
										value="route.warehouseId"></s:select>
								</td>
							</tr>
							<tr>
								<th>
									Product/Service
								</th>
								<td>
									<s:select id="class_sel" name="route.production"
										list="classList" listKey="value" listValue="type"
										value="route.production" cssStyle="width:222px;"></s:select>
								</td>
								<th>

								</th>
								<td>
									<input type="checkbox" id="flag" name="route.defaultFlag"
										value="Y" <s:if test='route.defaultFlag!=null&&route.defaultFlag.equals("Y")'>checked</s:if> onclick="check();"/>
									<label for="flag">
										Set as Default
									</label>
								</td>
							</tr>
							<tr>
								<th>
									Comment
								</th>
								<td>
									<textarea name="route.comment" class="content_textarea2">${route.comment}</textarea>
								</td>
								<td>
									&nbsp;
								</td>
								<td>
									<input type="checkbox" id="rollup" name="route.costRollupFlag" <s:if test='route.costRollupFlag!=null&&route.costRollupFlag.equals("Y")'>checked</s:if> value="Y" disabled="disabled"/><label for="rollup">Cost Rollup</label>
								</td>
							</tr>
							<tr>
								<th>
									Modified Date
								</th>
								<td>
									<input name="textfield3224" type="text" class="NFText"
										size="20" readonly="readonly"
										value="<s:date name="route.modifyDate" format="yyyy-MM-dd"/>" />
								</td>
								<th>
									Modified By
								</th>
								<td>
									<input name="textfield3322" type="text" class="NFText"
										readonly="readonly" size="20" value="${route.modifyUser}" />
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div id="dhtmlgoodies_tabView1">
				<div class="dhtmlgoodies_aTab">
					<iframe width="100%" height="310px" id="operation_list_frame" name="operation_list_frame" scrolling="no" frameborder="0" src="route!getOperationList.action?sessRouteId=${sessRouteId}"></iframe>
				</div>
			</div>
			<div class="button_box">
			<saveButton:saveBtn parameter="${operation_method}"
				disabledBtn='<input type="button" name="Submit123" value="Save" class="search_input" disabled="disabled" />'
				saveBtn='<input type="button" name="Submit123" value="Save" class="search_input" id="save_btn" />'
			/> 	
				<input type="button" name="Submit124" value="Cancel"
					class="search_input" <s:if test="operation_method=='edit'">
								  onclick=" window.location = 'route!search.action';";
					  		 </s:if>
							   <s:else> 
								  onclick="window.history.go(-1);"
							  </s:else>/>
			</div>
		</div>
		<div id="sel_res_dlg" title="Select Operations "></div>
		<script type="text/javascript"> 
          initTabs('dhtmlgoodies_tabView1',Array('Operations'),0,998,320);
          var isSaved = false;
		  window.onbeforeunload = function() {
			 if(isSaved === false){
					return 'Do you want to leave without saving data?';
			 }
		  }
        </script>
	</body>
</html>