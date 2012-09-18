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
		<script src="${global_js_url}jquery/jquery.validate.js?v=1"
			type="text/javascript"></script>
		<script type="text/javascript" src="${global_js_url}tab-view.js"></script>
		<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.dialog.all.js"></script>
		<script src="${global_js_url}jquery/ui/ui.datepicker.js"
			type="text/javascript"></script>
		<script type="text/javascript" src="${global_js_url}show_tag.js"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
		<script type="text/javascript">
            var baseUrl="${global_url}";	
			function edit(resourceId) {
			   window.location = "resource!edit.action?resourceId=" + resourceId;
			}			
				
            $(function() {
            	var type = "${woOperation.operation.type}";
            	$("#selecta option[value='"+type+"']").attr("selected","selected");  	
            	$("#"+$("#selecta").val()).show();
            	$("#"+$("#selecta").val()+" input").each(function(){
            		$(this).attr("disabled","");
            	});
            	$("#"+$("#selecta").val()+" select").each(function(){
            		$(this).attr("disabled","");
            	});
            	 $("#"+$("#selecta").val()+" textarea").each(function(){
            		 $(this).attr("disabled","");
                 });
            	 $("#purification_result").trigger("change");
            	 $("#hostNo_result").trigger("change");
            	 $("#resultList input[name='remainsGel']").each(function(){
            		 $(this).parent().parent().find("select").get(0).value=$(this).val();
            		 if($(this).parent().parent().find("select").get(0).options.selectedIndex==-1) {
            			 $(this).parent().parent().find("select").get(0).options.selectedIndex=0;
            			 $(this).parent().show();
            		 }
            	 });
            	 
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
            	 
            	 
              //  var flagVal = '${woOperation.optionalFlag}';
                // if (flagVal == 'Y') {
                  //$("#flag").get(0).checked = true;
                //} else {
                  //$("#flag").get(0).checked = false;
                //}
			    $("#resultTable tr:odd").find("td").addClass("list_td2");
			    

			    
			    $(".pickdate").live("mousemove",function(e){                
	                $(this).datepicker({
						dateFormat: 'yy-mm-dd',
						changeMonth: true,
						changeYear: true,
						yearRange: '-100:+0'
					});
	            });
			
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
					     $('<input type="hidden" name="dettachGroupIdList" value="' + $(this).val() + '" />').appendTo($("#operation_form"));
					   }
                       trObj.remove();
					});			
					$("#resultTable tr:even").find("td").removeClass("list_td2");
					$("#resultTable tr:odd").find("td").addClass("list_td2");			
			    });//end of $("#check_del").click.

               //验证form的逻辑
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
						 "woOperation.operation.name": { required:true}
					 },
					 messages: {
						 "woOperation.operation.name": { required : "Please enter the name !" }
					 },
					 errorPlacement: function(error, element) {
					 }
				});
				
				//Cancel按钮返回WorkOrder的编辑页面
                $("#cancel_btn").click( function() {
				    $('#confirm_btn').attr("disabled", true);
				    $('#cancel_btn').attr("disabled", true);
			        var sessId = $("#sessId").val();
			        isSaved = true;
				    window.location.href = 'workorder_entry!edit.action?referURL=select&sessId=' + sessId;
				});

                //绑定保存按钮事件.
                $("#save_btn").click (function() {
                   if (! $('#operation_form').valid()) {
                      return false;
                   }
                   $("#cloneDiv input").each(function(){
                	   $(this).attr("disabled","disabled");
                   });
                   $("#cloneDiv textarea").each(function(){
                	   $(this).attr("disabled","disabled");
                   });
                   $("#cloneDiv select").each(function(){
                	   $(this).attr("disabled","disabled");
                   });
                   var formStr = $('#operation_form').serialize();
                 //  if (! $("#flag").get(0).checked) {
                  //     formStr += "&woOperation.optionalFlag=N";
                 //  }
                 
                   $('#save_btn').attr("disabled", true);
                   $.ajax({
						type: "POST",
						url: "workorder_operation!saveOperation.action",
						data: formStr,
						dataType: 'json',
						success: function(data, textStatus){
							if(hasException(data)){//有错误信息.
				 	            $('#save_btn').attr("disabled", false);				
							}else{                              
						        var sessId = $("#sessId").val();
						        isSaved = true;
							    window.location.href = 'workorder_entry!edit.action?referURL=select&sessId=' + sessId;
							}
						},
						error: function(xhr, textStatus){
						   alert("failure");
				 	       $('#save_btn').attr("disabled", false);
						   return;
						}
					});
                
                });//end of {$("#save_btn").click};
                
                $("#change_log").click(function(){
                	if(!isNaN($("#woId").val())) {
                		var url = "workorder_operation!customDateChangeLog.action?id="+$("#woId").val();
		          	 $('#customized_date_Log_dlg').dialog("option", "open", function() {
		           		 var htmlStr = '<iframe src="'+url+'" id="selectUserFrame"  height="100%" width="100%" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
		           		 $('#customized_date_Log_dlg').html(htmlStr);
		          	});
		          	$('#customized_date_Log_dlg').dialog('open');
                	}
                	
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
            	$('#edit_res_dlg').dialog({
					autoOpen: false,
					height: 440,
					width: 700,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});				
            	$('#edit_com_dlg').dialog({
					autoOpen: false,
					height: 440,
					width: 700,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            	
            	$('#update_request_log_dlg').dialog({
            		autoOpen: false,
            		height: 250,
            		width: 620,
            		modal: true,
            		bgiframe: true,
            		buttons: {
            		}
            	});
            	
            	$("#customized_date_Log_dlg").dialog({
            		autoOpen: false,
            		height: 350,
            		width: 620,
            		modal: true,
            		bgiframe: true,
            		buttons: {
            		}
            	});
            
            });
            
            function addResult() {
            	var now = new Date();
            	var key = now.getTime()+"abc"+parseInt(Math.random()*100);
            	var length = $("#purification_result option").length+1;
            	var text = "#"+length;
         	    var option = "<option value='"+key+"'>"+text+"</option>";
         	   $("#purification_result").append(option);
         	   var obj = $("#cloneDiv").clone(true);
         	   $(obj).attr("id",key);
         	  $("#resultList").append(obj);
         	 $("#purification_result option[value='"+key+"']").attr("selected","selected");
         	$("#resultList div").hide();
         	obj.show();
       	  
            }
            
            function removeResult() {
            	var delResultIds = $("#delResultIds").val();
            	var selectValue= $("#purification_result").val();
            	$("#purification_result option[selected='selected']").remove();
            	if(!isNaN(selectValue)) {
            		if(delResultIds=="") {
            			delResultIds = delResultIds +selectValue;
            		} else {
            			delResultIds = delResultIds +","+selectValue;
            		}
            	}
            	$("#delResultIds").attr("value",delResultIds);
            	$("#"+selectValue).remove();
            	resultChange();
            }
            
            function resultChange() {
            	$("#resultList div").hide();
            	$("#"+$("#purification_result").val()).show();
            }
            
            function hostNoChange() {
            	$("#antibody_bleed div").hide();
            	$("#"+$("#hostNo_result").val()).show();
            }
            
            function checkVolume(obj) {
            	var volumeValue = $(obj).val();
            	var concentrationValue = $(obj).parent().parent().parent().find("input[name='concentration']").get(0).value;
            	if(volumeValue!=""&&isNaN(volumeValue)) {
            		alert("please enter a valid volume");
            		return;
            	} 
            	if(volumeValue=="") {
            		$(obj).parent().parent().parent().find("input[name='quantity']").get(0).value="";
            		return;
            	}
            	if(concentrationValue!="") {
            		var quality = volumeValue*concentrationValue;
            		$(obj).parent().parent().parent().find("input[name='quantity']").get(0).value=quality;
            	}
            }
            
            function checkConcentration(obj) {
            	var concentrationValue = $(obj).val();
            	var volumeValue = $(obj).parent().parent().parent().find("input[name='volume']").get(0).value;
            	if(concentrationValue!=""&&isNaN(concentrationValue)) {
            		alert("please enter a valid concentration");
            		return;
            	} 
            	if(concentrationValue=="") {
            		$(obj).parent().parent().parent().find("input[name='quantity']").get(0).value="";
            		return;
            	}
            	if(volumeValue!="") {
            		var quality = volumeValue*concentrationValue;
            		$(obj).parent().parent().parent().find("input[name='quantity']").get(0).value=quality;
            	}
            	
            }
            
            function remainsGelChanage(obj) {
            	var value = $(obj).val();
            	$(obj).parent().find("input[name='remainsGel']").get(0).value = value;

            	if(value=="") {
            		$(obj).parent().find("span").get(0).style.display="block";
            	} else {
            		$(obj).parent().find("span").get(0).style.display="none";
            	}
            }
        </script>
	</head>
	<body class="content">
		<div class="scm">
			<div class="title_content">
				<div class="title">
					Operation Information
				</div>
			</div>
			<form method="get" id="operation_form" class="niceform">
				<div class="input_box">
					<div class="content_box">
						<input name="sessId" id="sessId" type="hidden"
							value="${param.sessId}" />
						<input type="hidden" name="woOperation.id"
							value="${woOperation.id}" id="woId"/>
						<input type="hidden" name="woOperation.operation.id"
							value="${woOperation.operation.id}" />
						<input type="hidden" name="sessWOPKey" value="${param.sessWOPKey}" />
						<table border="0" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<th width="160">
									Sequence
								</th>
								<td>
									<input name="woOperation.seqNo" value="${woOperation.seqNo}"
										type="text" class="NFText" size="35" readonly="readonly" />
								</td>
								<th width="150">
									&nbsp;
								</th>
								<td>
									
								</td>
							</tr>
							<tr>
								<th width="160">
									Operation Name
								</th>
								<td>
									<input name="woOperation.operation.name"
										value="${woOperation.operation.name}" type="text"
										class="NFText" size="35" readonly="readonly" />
								</td>
								<th width="150">
									Status
								</th>
								<td>
								<s:if test='changeStatusFlg!=null&&changeStatusFlg.equals("N")'>
								<s:select list="dropDownMap['WO_OPERATION_STATUS']" listKey="value"
										listValue="text" value="woOperation.status"
										name="woOperation.status" cssStyle="width:207px" disabled="true"/>
										<s:hidden name="woOperation.status"></s:hidden>
								</s:if>
								<s:else>
									<s:select list="dropDownMap['WO_OPERATION_STATUS']" listKey="value"
										listValue="text" value="woOperation.status"
										name="woOperation.status" cssStyle="width:207px"/>
								</s:else>
								</td>
							</tr>
							<tr>
								<th valign="top">
									Description
								</th>
								<td>
									<input name="woOperation.operation.description" type="text"
										value="${woOperation.operation.description}" class="NFText"
										size="76" readonly="readonly" />
								</td>
								<th>
									Operation Supervisor
								</th>
								<td>
									<s:select id="super_sel"
										name="woOperation.operation.supervisor" list="superList"
										listKey="userId" listValue="loginName"
										value="woOperation.operation.supervisor"
										cssStyle="width:207px" disabled="true"></s:select>
								</td>
							</tr>
							<tr>
								<th>
									Work Center
								</th>
								<td>
									<s:select id="workCenterId_sel"
										name="woOperation.operation.workCenterId" list="centerList"
										listKey="id" listValue="name"
										value="woOperation.operation.workCenterId"
										cssStyle="width:207px" disabled="true"></s:select>
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
									<s:textarea name="woOperation.comment"
										cssClass="content_textarea2"></s:textarea>
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
									<input name="woOperation.operation.setupTime" type="text"
										class="NFText" size="20"
										value="${woOperation.operation.setupTime}" readonly="readonly" />
								</td>
								<th>
									Runtime Per Unit
								</th>
								<td>
									<input name="woOperation.operation.runTime" type="text"
										class="NFText" size="20"
										value="${woOperation.operation.runTime}" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<th>
									UOM
								</th>
								<td>
									<s:select list="{'Hour','Day', 'Month', 'Year'}"
										value="woOperation.operation.uom"
										name="woOperation.operation.uom" cssStyle="width:207px"
										disabled="true"></s:select>
								</td>
								<th>

								</th>
								<td>

								</td>
							</tr>
						</table>
					</div>
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
								              <select name="woOperation.operation.type" style="width:207px;" id="selecta"  disabled="disabled">
								                <option value="antibody_elisa">Antibody-Elisa Result</option>
								                <option value="antibody_bleed">Antibody-Bleed Result</option>
								                <option value="antibody_purification">Antibody-Purification Result</option>
						                      </select>
						                      <s:hidden name="woOperation.operation.type"></s:hidden>
						                      </td>
							                </tr>
											
								</table>
							 </div>
							
							<div id="antibody_elisa" class="disp" style="display: none;">
								<table border="0" cellpadding="0" cellspacing="0"
												class="General_table">
									  <tr>
						                <th width="202">Host Name</th>
						                <td width="360">
						                <s:select list="dropDownMap['OPERATION_HOST_NAME']" listKey="value" listValue="text" name="antibodyOprExperimentDatasDTO.hostName" style="width:207px;" disabled="true">
										</s:select>
						            
					                    </td>
						                <th width="143">Host Amount</th>
						                <td><input name="antibodyOprExperimentDatasDTO.hostAmount" value="${antibodyOprExperimentDatasDTO.hostAmount}" type="text" class="NFText" size="35" disabled="disabled"/></td>
					                  </tr>
					                  <s:iterator value="woOperation.antibodyOprExperimentDatasMap">
					                  <input type="hidden" name="antibodyOprExperimentDatasDTO.id" value="${value.id}"  disabled="disabled"></input>
					                  	<tr>
						                <th>Host No</th>
						                <td>
						                	<input type="text" name="antibodyOprExperimentDatasDTO.hostNo" class="NFText" size="20" value="${key}" readonly="readonly"/>
					                    </td>
						                <th>Experimental Result</th>
						                <td>
						                <s:select list="{'Normal','Abnormal'}"  name="antibodyOprExperimentDatasDTO.experimentalResult" value="value.experimentalResult" cssStyle="width:207px;" disabled="true">
						                </s:select>
						                </td>
	                  				</tr>
					                  </s:iterator>
						              
								</table>
								</div>
							</div>
							<div id="antibody_bleed" class="disp" style="display: none;">
								<table border="0" cellpadding="0" cellspacing="0"
												class="General_table">
										   <tr>
								              <th width="202">Host No</th>
								              <td width="360">
								              <select name="select7" style="width:207px;" id="hostNo_result" onchange="hostNoChange();">
								                      <s:iterator value="woOperation.antibodyOprExperimentDatasMap" status="st">
								                        <option value="${key}">${key}</option>
								                       </s:iterator>
							                 </select>
											  </td>
								              <th width="143">&nbsp;</th>
								              <td>&nbsp;</td>
							                </tr>
							     </table>
							     <s:iterator value="woOperation.antibodyOprExperimentDatasMap">
							     <input type="hidden" name="antibodyOprExperimentDatasDTO.id" value="${value.id}"  disabled="disabled"></input>
							      	 <div id="${key}" style="display: none;">
							     <table border="0" cellpadding="0" cellspacing="0"
												class="General_table">
											
								            <tr>
								              <th width="202">Volume</th>
								              <td width="360"><input name="antibodyOprExperimentDatasDTO.volume" value="${value.volume}" type="text" class="NFText" size="35" disabled="disabled"/></td>
								              <th width="143">Location</th>
								              <td><input name="antibodyOprExperimentDatasDTO.location" value="${value.location}" type="text" class="NFText" size="35" disabled="disabled"/></td>
							                </tr>
								            <tr>
								              <th>Remains</th>
								              <td><input name="antibodyOprExperimentDatasDTO.remains" value="${value.remains}" type="text" class="NFText" size="35" disabled="disabled"/></td>
								              <th>Remains Location</th>
								              <td>
								              <input name="antibodyOprExperimentDatasDTO.remainsLocation" value="${value.remainsLocation}" type="text" class="NFText" size="35" disabled="disabled"/>
								              </td>
							                </tr>
								            <tr>
								              <th>Comment</th>
								              <td><s:textarea name="antibodyOprExperimentDatasDTO.comment" cssClass="content_textarea2"  disabled="true">
								              	<s:param name="value"><s:property value="value.comment"/></s:param>
								              </s:textarea></td>
								              <th>&nbsp;</th>
								              <td>&nbsp;</td>
				                			</tr>
												
								</table>
								</div>
								</s:iterator>
							</div>
							<div id="antibody_purification" class="disp" style="display: none;">
								<table border="0" cellpadding="0" cellspacing="0"
												class="General_table">
												
												<tr>
							                      <th width="202">Purification Results</th>
							                      <td width="360">
							                      
							                      <select name="select7" style="width:207px;" id="purification_result" onchange="resultChange();">
								                      <s:iterator value="woOperation.antibodyOprPurificationResultsMap" status="st">
								                        <option value="${key}">#<s:property value="#st.index+1"/></option>
								                       </s:iterator>
							                      </select>
							                      <input type="button" name="Submit2" class="style_botton" value="Add" onclick="addResult();"/>
							                      <input type="button" name="Submit" class="style_botton" value="Remove" onclick="removeResult();"/></td>
							                      <th>&nbsp;</th>
							                      <td>&nbsp;</td>
							                    </tr>
							      </table>
							      <div id="resultList">
							      <input type="hidden" name="delResultIds" value="${delResultIds}" id="delResultIds" disabled="disabled"></input>
							      <s:if test="woOperation.antibodyOprPurificationResultsMap!=null">
							      	<s:iterator value="woOperation.antibodyOprPurificationResultsMap">
							      	 <div id="${key}" style="display: none;">
							      	 <input type="hidden" value="${value.id}" name="ids"/>
							      	 	<table border="0" cellpadding="0" cellspacing="0"
												class="General_table">
									            <tr>
									              <th width="202">Date</th>
									              <td width="324"><input name="experimentDate" value="<s:date name="value.experimentDate" format="yyyy-MM-dd HH-mm"/>"  type="text" class="pickdate NFText" size="35" disabled="disabled"/></td>
									              <th width="143">Host No</th>
									              <td>
									              	<input type="text" class="NFText" value="${value.hostNo}" name="hostNo" size="35" disabled="disabled"/>
								                    </td>
								                </tr>
									            <tr>
									              <th>Loading</th>
									              <td><input name="loading" value="${value.loading}" type="text" class="NFText" size="35" disabled="disabled"/></td>
									              <th>Remains Gel</th>
									              <td>
									              <s:select list="dropDownMap['OPERATION_REMAINS_GEL']" listKey="value" listValue="text" headerKey="" headerValue="Other"  disabled="true" onchange="remainsGelChanage(this);">
										          </s:select>
										          <span style="display:none;"><input type="text" name="remainsGel" value="${value.remainsGel}" class="NFText" size="20"/></span>
									              </td>
								                </tr>
									            <tr>
									              <th>Concentration</th>
									              <td><input name="concentration" value="${value.concentration}" type="text" class="NFText" size="35" onblur="checkConcentration(this)" disabled="disabled"/></td>
									              <th>Volume</th>
									              <td><input name="volume" value="${value.volume}" type="text" class="NFText" size="35" onblur="checkVolume(this)" disabled="disabled"/></td>
								                </tr>
									            <tr>
									              <th>Quality</th>
									              <td><input name="quantity" value="${value.quantity}" type="text" class="NFText" size="35" readonly="readonly" disabled="disabled"/></td>
									              <th>&nbsp;</th>
									              <td>&nbsp;</td>
								                </tr>
									            <tr>
									              <th>Comment</th>
									              <td><s:textarea name="comment" class="content_textarea2" style="width:207px" disabled="disabled">
									              <s:param name="value"><s:property value="value.comment"/></s:param>
									              </s:textarea></td>
									              <th>Titer</th>
									              <td>
									              <select name="titer" style="width:207px;" disabled="disabled">
									              	<option value="Normal">Normal</option>
									              	<option value="Abnormal">Abnormal</option>
												</select>
												</td>
								                </tr>
										</table>
							      	 </div>
							      </s:iterator>
							      </s:if>
							      
							      </div>
							      <div id="cloneDiv" style="display: none;">
							      <table border="0" cellpadding="0" cellspacing="0"
												class="General_table">
												<input type="hidden"  name="ids"></input>
									            <tr>
									              <th width="202">Date</th>
									              <td width="324"><input name="experimentDate" type="text" class="pickdate NFText" size="35"/></td>
									              <th width="143">Host No</th>
									              <td>
									               <input type="text" class="NFText" value="${value.hostNo}" name="hostNo" size="35"/>
								                    </td>
								                </tr>
									            <tr>
									              <th>Loading</th>
									              <td><input name="loading" type="text" class="NFText" size="35"/></td>
									              <th>Remains Gel</th>
									              <td>
									              <s:select list="dropDownMap['OPERATION_REMAINS_GEL']"  listKey="value" listValue="text" headerKey="" headerValue="Other"  onchange="remainsGelChanage(this);">
										          </s:select>
										          <span style="display:block;"><input type="text" name="remainsGel" class="NFText" size="20"/></span>
										          </td>
								                </tr>
									            <tr>
									              <th>Concentration</th>
									              <td><input name="concentration" type="text" class="NFText" size="35" onblur="checkConcentration(this)"/></td>
									              <th>Volume</th>
									              <td><input name="volume" type="text" class="NFText" size="35" onblur="checkVolume(this)"/></td>
								                </tr>
									            <tr>
									              <th>Quality</th>
									              <td><input name="quantity" type="text" class="NFText" size="35" readonly="readonly"/></td>
									              <th>&nbsp;</th>
									              <td>&nbsp;</td>
								                </tr>
									            <tr>
									              <th>Comment</th>
									              <td><textarea name="comment" class="content_textarea2"></textarea></td>
									              <th>Titer</th>
									              <td>
									              <select name="titer" style="width:207px;">
									              	<option value="Normal">Normal</option>
									              	<option value="Abnormal">Abnormal</option>
												</select>
												</td>
								                </tr>
								</table>
								</div>
							</div>
							</div>
					<div class="invoice_title">
						<span style="cursor: pointer"
							onclick="toggle_showmore('WorkTime_InfoItem', 'WorkTime_Info');"><img
								src="${global_image_url}ar.gif" width="11" height="11"
								id="WorkTime_InfoItem" /> &nbsp;Work Time</span>
					</div>
					<div id="WorkTime_Info" class="disp" style="display: none">
						<table border="0" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<th width="210">
									Scheduled Start Date
								</th>
								<td>
									<input name="woOperation.exptdStartDate"
										id="scheduleStart_date" type="text" readonly="readonly"
										class="NFText" style="width: 200px;"
										value="<s:date name="woOperation.exptdStartDate" format="yyyy-MM-dd HH:mm"/>" />
								</td>
								<th width="276">
									Scheduled Complete Date
								</th>
								<td width="130">
									<input name="woOperation.exptdEndDate" id="scheduleEnd_date"
										type="text"  readonly="readonly"
										class="NFText" style="width: 200px;"
										value="<s:date name="woOperation.exptdEndDate" format="yyyy-MM-dd HH:mm"/>" />
								</td>
							</tr>
							<tr>
								<th>
									Actual Start Date
								</th>
								<td>
									<input name="woOperation.actualStartDate" id="actualStart_date"
										type="text"  readonly="readonly"
										class="NFText" style="width: 200px;"
										value="<s:date name="woOperation.actualStartDate" format="yyyy-MM-dd HH:mm"/>" />
								</td>
								<th>
									Actual Complete Date
								</th>
								<td>
									<input name="woOperation.actualEndDate" id="actualEnd_date"
										type="text"  readonly="readonly"
										class="NFText" style="width: 200px;"
										value="<s:date name="woOperation.actualEndDate" format="yyyy-MM-dd HH:mm"/>" />
								</td>
							</tr>
							<tr>
								<th width="210">
									Customized  Start Date
								</th>
								<td>
									<input name="woOperation.customStartDate"
										id="customStart_date" type="text" 
										class="NFText" style="width: 200px;"
										value="<s:date name="woOperation.customStartDate" format="yyyy-MM-dd HH:mm"/>" />
									<input type="hidden" id="customStartDate_hidden" value="<s:date name="woOperation.customStartDate" format="yyyy-MM-dd HH:mm"/>"/>
								</td>
								<th width="276">
									Customized  Complete Date
								</th>
								<td width="130">
									<input name="woOperation.customEndDate" id="customEnd_date"
										type="text"  
										class="NFText" style="width: 200px;"
										value="<s:date name="woOperation.customEndDate" format="yyyy-MM-dd HH:mm"/>" />
									<input type="hidden" id="customEndDate_hidden" value="<s:date name="woOperation.customEndDate" format="yyyy-MM-dd HH:mm"/>"/>
								</td>
							</tr>
							
							<tr>
			                <td height="40" colspan="4" align="center">
			                  <div class="button_box" style="padding:0px;margin:0px;"><input type="button" name="Submit3"  value="Add/View Concentration Results" class="search_input4" onclick="plateItemsEdit();"/></div>
			               </td>
			                </tr>
							
						</table>
					</div>
				</div>
			</form>
			<div id="dhtmlgoodies_tabView1">
				<div class="dhtmlgoodies_aTab">
					<iframe width="100%" height="310px" id="resource_list_frame"
						name="resource_list_frame" scrolling="no" frameborder="0"
						src="workorder_operation!getResourceList.action?sessId=${sessId}&sessWOPKey=${param.sessWOPKey}"></iframe>
				</div>
				<div class="dhtmlgoodies_aTab" id="com_tab">
					<iframe width="100%" height="310" frameborder="0" scrolling="no"
						id="component_list_frame"></iframe>
				</div>
			</div>
			<div class="button_box">
				<input type="button" name="Submit123" value="Save"
					class="search_input" id="save_btn" />
				<input type="button" name="Submit124" value="Cancel"
					class="search_input" id="cancel_btn" />
			</div>
		</div>
		<div id="sel_res_dlg" title="Select Resource"></div>
		<div id="edit_res_dlg" title="Edit Resource"></div>
		<div id="edit_com_dlg" title="Edit Component"></div>
		<div id="update_request_log_dlg" title="Records causes"></div>
		<div id="customized_date_Log_dlg" title="Customized Date Changed Log"></div>
		<script type="text/javascript"> 
          initTabs('dhtmlgoodies_tabView1',Array('Resource', 'Components'),0,998,315);
          var isSaved = false;
		  window.onbeforeunload = function() {
			 if(isSaved === false){
					return 'Do you want to leave without saving data?';
			 }
		  }
		  $(function() {
			  $('#Compenents').click( function() {
				if($('#component_list_frame').attr('src') == undefined || $('#component_list_frame').attr('src') == '') {
					$('#component_list_frame').attr('src', "workorder_operation!getComponentList.action?sessWOPKey=${param.sessWOPKey}");
				}
			  });
		  });
		  
		  function plateItemsEdit() {
			  isSaved = true;
			  window.location.href='workorder_operation!plateItemsEdit.action';
		  }
        </script>
	</body>
</html>