<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Work Order Processing</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="${global_js_url}jquery/jquery.js"></script>
		<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
			type="text/css" />
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
			<script src="${global_js_url}newwindow.js"
			type="text/javascript"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
		<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
		<script type="text/javascript">
		
		 function checkForm() {
		       $("#order_no_txt").val($.trim($("#order_no_txt").val()));
		       $("#so_no_txt").val($.trim($("#so_no_txt").val()));
		       if(isNaN($("#so_no_txt").val())) {
		    	   alert("Please enter a valid number for NanJing SO NO .");
		    	   return false;
		       }
		       $("#filter_EQI_srcSoNo").val($.trim($("#filter_EQI_srcSoNo").val()));
		       if(isNaN($("#filter_EQI_srcSoNo").val())) {
		    	   alert("Please enter a valid number for SO NO .");
		    	   return false;
		       }
		       return true;
		    
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
            	
            	$('.pickdate').each(function(){
        			$(this).datepicker(
        				{
        					dateFormat: 'yy-mm-dd',
        					changeMonth: true,
        					changeYear: true,
        					yearRange: '-100:+0'
        				});
        		});
            	$('#new_route_dlg').dialog({
					autoOpen: false,
					height: 360,
					width: 660,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            	
            	$('#print_labels_dlg').dialog({
					autoOpen: false,
					height: 350,
					width: 750,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
				$('#retrieve_Info_for_Peptide_QC').dialog({
					autoOpen: false,
					height: 350,
					width: 750,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});

            	$('#protein_labels_dlg').dialog({
					autoOpen: false,
					height: 320,
					width: 560,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            	$('#oligo_labels_dlg').dialog({
            		autoOpen: false,
            		height: 320,
            		width: 560,
            		modal: true,
            		bgiframe: true,
            		buttons: {
            		}
            	});
            	
            	$('#polyclonal_antibody_labels_dlg').dialog({
					autoOpen: false,
					height: 370,
					width: 700,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            	
            	$('#monoclonal_antibody_labels_dlg').dialog({
					autoOpen: false,
					height: 300,
					width: 560,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            	$('#frame12').dialog({
					autoOpen: false,
					height: 480,
					width: 650,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});

            	$('#batchOrder').dialog({
					autoOpen: false,
					height: 250,
					width: 740,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            	
            	$('#update_operation_status_dlg').dialog({
					autoOpen: false,
					height: 650,
					width: 830,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            	
            	$('#custom_update_operation_status_dlg').dialog({
					autoOpen: false,
					height: 600,
					width: 830,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            	
            	$('#instruction_dlg').dialog({
            		autoOpen: false,
            		height: 400,
            		width: 600,
            		modal: true,
            		bgiframe: true,
            		buttons: {
            		}
            	});
            	 $('#ShowDetailDialog').dialog({
                  	autoOpen: false,
 					height: 700,
 					width: 1000,
 					modal: true,
 					bgiframe: true,
                     resize:'auto',
                    buttons: {
                 	   "Close": function() {
                            $(this).dialog('close');
                   		}
                    }
                });
            
				
                $("#new_route_dlg_btn").click( function() {
					$('#new_route_dlg').dialog("option", "open", function() {	
	              		 var htmlStr = '<iframe src="route!add.action" height="260" width="600" scrolling="no" style="border:0px" frameborder="0"></iframe>';
				         $('#new_route_dlg').html(htmlStr);
					});
					$('#new_route_dlg').dialog('open');
                });
                
                $("#operation_view").click( function() {
					$('#update_operation_status_dlg').dialog("option", "open", function() {	
	              		 var htmlStr = '<iframe src="workorder_proc!showWorkOrderOperationMain.action" height="100%" width="800" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
				         $('#update_operation_status_dlg').html(htmlStr);
					});
					$('#update_operation_status_dlg').dialog('open');
                });
                $("#operation_update").click( function() {
					$('#custom_update_operation_status_dlg').dialog('open');
                });

                $('#batchUploadDoc').click(function() {
                    $('#uploadHelp').dialog('open');
                    $('#uploadHelp').attr('innerHTML', '<iframe  src="workorder_proc!showBatchUpload.action" allowTransparency="true" width="100%" height="100%" frameborder="0" scrolling="no" ></iframe>');
                });

                $('#uploadHelp').dialog({
                    autoOpen: false ,
                    height: 240 ,
                    width: 550 ,
                    modal: true ,
                    //Div top display
                    bgiframe: true
                });
            
            });

            function Peptide_download(){
                window.open("https://192.168.1.22/ssl-bin/supply/peptide_excel");
            }

            //generate_qc_batch button click event
            function generate_qc_batch() {
                var orderNoStrs = $("#choiceOption").val();
                if(orderNoStrs=="") {
                    alert("Please select one at least!");
                    return;
                }
                var url = "workorder_proc!showOperBatch.action?orderNoStrs="+orderNoStrs;
                $('#frame12').dialog("option", "open", function() {	
             		 var htmlStr = '<iframe src="'+url+'" height="420" width="650" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			         $('#frame12').html(htmlStr);
				});
				$('#frame12').dialog('open');
            }

			var global_url = "${global_url}" ;
            function BatchOrderProcessing () {
            	var orderNoStrs = $("#choiceOption").val();
                if(orderNoStrs=="") {
                    alert("Please select one at least!");
                    return;
                } else {
	                $.ajax({
						type: "POST",
						url: global_url+"workorder_proc!checkWorkOrderNo.action",
						data: "orderNoStrs="+orderNoStrs,
						dataType: 'json',
						success: function(data, textStatus){
							if (data.hasException) {//有错误信息.
				 	            alert("program has exception");				
							} else {
								if (data.status!=undefined&&data.status!=null&&data.status.toLowerCase() == 'success') {
									var openurl = "workorder_proc!showBatchOrder.action?orderNoStrs="+orderNoStrs;
					                $('#batchOrder').dialog("option", "open", function() {	
					             		 var htmlStr = '<iframe src="'+openurl+'" height="200" width="710" scrolling="no" style="border:0px" frameborder="0"></iframe>';
								         $('#batchOrder').html(htmlStr);
									});
									$('#batchOrder').dialog('open');
								} else {
									alert("Could not batch process WorkOrders");
								}                           
						    }
						},
						error: function(xhr, textStatus){
						   alert("error,could not batch process WorkOrders");
						   return;
						}
					});
                }
            }
            function toggleShowMore_img(obj,divID){
        		var oId = document.getElementById(divID);
        		if (obj.src.indexOf('arrow1.jpg') > 0){
        			obj.src="${global_url}images/arrow.jpg";
        			oId.style.display = "none"; 
        		}else{
        			obj.src="${global_url}images/arrow1.jpg";
        			oId.style.display = "block"; 
        		}
        }
            function centerSelect () {
            	var centerId = $("#warehouse_sel").val();
            	if(centerId=="") {
            		if($("#warehouse_sel").get(0).options.length>1) {
            			centerId = $("#warehouse_sel").get(0).options[1].value;
            		} else {
            			$("#workGroup_sel").get(0).options.length = 0;
       	 	          	return;
            		}
            	}
            	var formStr ="centerId="+centerId+"&roleName=Work Order Processing Manager";
            	$.ajax({
            		type: "POST",
            		url: "workorder_proc!workCenterSelect.action",
            		data: formStr,
            		dataType: 'json',
            		success: function(data, textStatus){
            			  $("#workGroup_sel").empty(); 
            			  var option = "<option value=''>All</option>";
            	          $("#workGroup_sel").append(option);
            			  for(var i=0;i<data.workGroupList.length;i++) {
            				  var option = "<option value='"+data.workGroupList[i].id+"'>"+data.workGroupList[i].name+"</option>";
            				  $("#workGroup_sel").append(option);
            			  }                             
            		},
            		error: function(xhr, textStatus){
            		}
            	});          
            }
            function printLabels() {
            	var allChoiceVal = document.getElementById("choiceOption").value;
            	if(allChoiceVal=="") {
                	alert("Please select one at least!");
                	return;
            	}
            	var url = "workorder_proc!printLabels.action?allChoiceVal="+allChoiceVal;
            	$("#print_labels_dlg").dialog("option","open",function(){
            		 var htmlStr = '<iframe src="'+url+'" id="printLabelFrame" height="300" width="700" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			         $('#print_labels_dlg').html(htmlStr);
                });
                $("#print_labels_dlg").dialog("open");
            	
            }
            
            function Retrieve_Info_for_Peptide() {
            	var allChoiceVal = document.getElementById("choiceOption").value;
            	if(allChoiceVal=="") {
                	alert("Please select one at least!");
                	return;
            	}
            	var url = "workorder_proc!peptideInfoShow.action?allChoiceVal="+allChoiceVal;
            	$("#retrieve_Info_for_Peptide_QC").dialog("option","open",function(){
            		 var htmlStr = '<iframe src="'+url+'" id="retrieveInfoFrame" height="100%" width="700" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
			         $('#retrieve_Info_for_Peptide_QC').html(htmlStr);
                });
         
                $("#retrieve_Info_for_Peptide_QC").dialog("open");
            	
            }
            
            function sendEmail() {
            	var allChoiceVal = document.getElementById("choiceOption").value;
            	if(allChoiceVal=="") {
                	alert("Please select one!");
                	return;
            	}
            	if(allChoiceVal.indexOf(",")!=-1) {
                	alert("Please select one only!");
                	return;
            	}
            	var url = "workorder_proc!sendMail.action?sessWorkOrderNo="+allChoiceVal;
            	$("#instruction_dlg").dialog("option","open",function(){
           		 var htmlStr = '<iframe src="'+url+'"  height="100%" width="100%" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
			         $('#instruction_dlg').html(htmlStr);
               });
               $("#instruction_dlg").dialog("open");
            }
            
            
            function viewOrder(id) {
                    if(id!=null && id !=""){
                      $('#ShowDetailDialog').dialog("option", "open", function() {
                  		 var htmlStr = '<iframe src="${global_url}/order/order!edit.action?orderNo='+id+'&operation_method=view&lookCust=N&defaultTab=1&lookFromWoFlag=1" id="selectUserFrame"  height="100%" width="1000" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
                  		 $('#ShowDetailDialog').html(htmlStr);
                 	});
                 	 $('#ShowDetailDialog').dialog('open');
                        return true;
                    }else{
                        alert("Please select an sales order no!");
                        return false;
                    }
                }
            
            function customUpdateOperation() {
            	var customConfirmInfo = $("#customConfirmInfo").val();
            	var formStr = "customConfirmInfo="+customConfirmInfo;
            	$.ajax({
            		type: "POST",
            		url: "workorder_operation!batchUpdateWoOperationCustom.action",
            		data: formStr,
            		dataType: 'json',
            		success: function(data, textStatus){
            			if (data.hasException) {//有错误信息.
			 	            alert("program has exception");				
						} else if(data.message=='Success'){
							 alert(data.message);
							 $("#custom_update_operation_status_dlg").dialog("close");
							 window.location.reload();
						} else {
							alert(data.message);
						}
            		},
            		error: function(xhr, textStatus){
            			alert("Failed");
            		}
            	});          
            }
            
            function selectOperation() {
            	$('#update_operation_status_dlg').dialog("option", "open", function() {	
             		 var htmlStr = '<iframe src="workorder_proc!showWorkOrderOperationMain.action?customFlag=1" height="100%" width="800" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
			         $('#update_operation_status_dlg').html(htmlStr);
				});
				$('#update_operation_status_dlg').dialog('open');
            }

        /*
* add by zhanghuibin
* */
         function listTable_downLoad() {
             var ids = $("#choiceOption").attr("value");
             var jIframeObj = $(window.frames["result_iframe"].document);
             var delivery_date = jIframeObj.find("#scheduleEnd_dates");
             var order_no = jIframeObj.find("#so_nos");
             var so_item_no = jIframeObj.find("#so_item_nos");
             delivery_date.attr("value", "");
             order_no.attr("value", "");
             so_item_no.attr("value", "");
             if (ids != "") {
                 for (var ij in ids.split(",")) {
                     var id = ids.split(",")[ij];
                     var delivery_date_temp = jIframeObj.find("#" + id + "_scheduleEnd_date").val();
                     var order_no_temp = jIframeObj.find("#" + id + "_so_no").val();
                     var so_item_no_temp = jIframeObj.find("#" + id + "_so_item_no").val();
                     var cls_id_temp = jIframeObj.find("#" + id + "_cls_id").val();
                     if (cls_id_temp == 1) {
                         if(delivery_date_temp == "") delivery_date_temp = -1;
                         delivery_date.attr("value", delivery_date.attr("value") + "," + delivery_date_temp);
                         order_no.attr("value", order_no.attr("value") + "," + order_no_temp);
                         so_item_no.attr("value", so_item_no.attr("value") + "," + so_item_no_temp);
                     } else {
                         alert("Work Order No " + id + " is not a 'Peptide Service'.");
                         return false;
                     }
                 }
                 delivery_date.attr("value", delivery_date.attr("value").substring(1, delivery_date.attr("value").length));
                 order_no.attr("value", order_no.attr("value").substring(1, order_no.attr("value").length));
                 so_item_no.attr("value", so_item_no.attr("value").substring(1, so_item_no.attr("value").length));
                 jIframeObj.find("#work_orders").attr("value", ids);
                 //var param = "delivery_date=" + delivery_date.attr("value") + "&work_order=" + ids + "&order_no=" + order_no.attr("value") + "&so_item_no=" + so_item_no.attr("value");
                 var url = "workorder_entry!batchDownOperationTable.action?work_order=" + ids;
                 var oAction = window.frames["result_iframe"].document.forms[0].action;
                 window.frames["result_iframe"].document.forms[0].action =  url ;
                 window.frames["result_iframe"].document.forms[0].target = "result_iframe";
                 window.frames["result_iframe"].document.forms[0].submit();
                 window.frames["result_iframe"].document.forms[0].action = oAction;
             } else {
                 alert("Please select one record at least.");
             }
         }
        </script>
	</head>
	<body class="content" style="background-image: none;">
	<div id="frame12" style="display:none;" title="Generate QC Batch">
	</div>
	<div id="batchOrder" style="display:none;" title="Batch Order Processing">
	</div>
		<form action="workorder_proc!list.action" method="get"
			target="result_iframe" onsubmit="return checkForm();">
			<div class="scm">
			<div class="title_content">
  				<div style="padding-left: 20px;color: #5579A6;vertical-align:middle;"><img src="${global_url}images/arrow1.jpg" style="width:16px;height:17px;vertical-align:middle;" onclick="toggleShowMore_img(this,'search_box1');"/>&nbsp;&nbsp;Work Order Processing</div>
			</div>
				<div class="search_box" id="search_box1">
					<div class="single_search">
						<table border="0" cellspacing="0" cellpadding="0"
							class="General_table">
							<tr>
								<td>
									Work Order No
								</td>
								<td width="120">
									<input name="filter_EQS_altOrderNo" id="order_no_txt" type="text" class="NFText"
										size="20" />
								</td>
								<td>
									NanJing SO NO
								</td>
								<td width="120">
									<input name="filter_EQI_soNo" id="so_no_txt" type="text" class="NFText"
										size="20" />
								</td>
								<td align="right">
									US SO NO
								</td>
								<td width="120">
									<input name="filter_EQI_srcSoNo" id="filter_EQI_srcSoNo" type="text" class="NFText" size="20" />
								</td>
								<td>
									Product/Service
								</td>
								<td width="120">
									<s:select id="class_sel" name="production" headerKey=""
										headerValue="All" list="classList"
										listKey="value" listValue="type"></s:select>
								</td>
							</tr>
							<tr>
								<td align="right">
									Status
								</td>
								<td width="120">
								<select id="status_sel" name="filter_EQS_status">
									<option value="">All</option>
									<option value="New">New</option>
									<option value="In Production">In Production</option>
									<option value="Canceled">Canceled</option>
									<option value="Closed">Closed</option>
								</select>
								</td>
								<td align="right">
									Work Center
								</td>
								<td width="120">
								<s:if test="workCenterList!=null&&workCenterList.size()>0">
									<s:select id="warehouse_sel" name="filter_EQI_workCenterId"
										list="workCenterList" headerKey=""
										headerValue="All" listKey="id" listValue="name" onchange="centerSelect();"></s:select>
								</s:if>
								<s:else>
									<select id="warehouse_sel" name="filter_EQI_workCenterId">
										<option value="">All</option>
									</select>
								</s:else>
								</td>
								<td align="right">
									Work Group
								</td>
								<td width="120">
								<s:if test="workGroupList!=null&&workGroupList.size()>0">
									<s:select id="workGroup_sel" name="workGroupId"
										list="workGroupList" headerKey=""
										headerValue="All" listKey="id" listValue="name"></s:select>
								</s:if>
								<s:else>
									<select id="workGroup_sel" name="workGroupId">
										<option value="">All</option>
									</select>
								</s:else>
								</td>
								<td align="right">Order Date</td>
								<td colspan="2">
								<input name="start_orderDate" id="start_orderDate" type="text" class="pickdate NFText" style="width: 100px;"/>~
								<input name="end_orderDate" id="end_orderDate" type="text" class="pickdate NFText" style="width: 100px;"/>
								</td>
							</tr>
							<tr>
								<td colspan="6" align="center">
									<input type="submit" name="Submit5" value="Search"
										class="search_input" />
									
								</td>
							</tr>
						</table>
					</div>
				</div>
				<input type="hidden" id="choiceOption"/> 
				<div class="input_box" style="height: 320px;margin-top: -20px">
					<div class="content_box">
						<iframe id="srch_route_iframe" name="result_iframe"
							src="workorder_proc!list.action" width="100%" height="630"
							frameborder="0" scrolling="auto"></iframe>
					</div>
				</div>
				<div class="button_box" style="margin-top: 30px">
						<input type="button" name="Submit5" id="operation_view" value="Batch Update Work Order"
										class="search_input2" onclick=""/>
					<!--  <input type="button" name="Submit52" value="Batch Order Processing"
						class="search_input3"
						onclick="BatchOrderProcessing()" />-->
						<input type="button" name="Submit5" id="operation_update" value="Custom Update Work Order"
										class="search_input3"/>
                    <input type="button" name="Submit" id="batchUploadDoc" value="Batch Upload Production Doc"
						class="search_input3"/>
					<input type="button" name="Submit52" value="Create Labels"
						class="search_input2"
					 onclick="printLabels();"/>
					 <s:if test="wo_sendMail_flag!=null&&wo_sendMail_flag==1">
					 	<input type="button" name="Submit52" value="Send Email"
						class="search_input2"
					 onclick="sendEmail();"/>
					 </s:if>
					 
					 <s:if test="wo_peptide_info_look!=null&&wo_peptide_info_look==1">
					 <input type="button" name="Submit5" value="Retrieve Info for Peptide QC"
						class="search_input3"
					 onclick="Retrieve_Info_for_Peptide();"/>
					 </s:if>
					 <input type="button" name="Submit6" value="Peptide Download" class="search_input3" onclick="Peptide_download();"/>
					 <input type="button" name="Submit7" value="View operation tab" class="search_input3" onclick="listTable_downLoad();"/>
				</div>
			</div>
		</form>
		<div id="update_operation_status_dlg" title="Update Operation Status"/>
		<div id="custom_update_operation_status_dlg" title="Custom Update Operation Status" style="dispaly:none;">
			<s:include value="custom_update_operation_status.jsp"></s:include>
		</div>
		<div id="print_labels_dlg" title="Create Labels"/>
		<div id="new_route_dlg" title=" New Routing " style="visible: hidden"></div>
		<div id="protein_labels_dlg" title="Create Labels"/>
		<div id="monoclonal_antibody_labels_dlg" title="Create Labels"/>
		<div id="polyclonal_antibody_labels_dlg" title="Create Labels"/>
		<div id="oligo_labels_dlg" title="Create Labels"/>
		<div id="uploadHelp" title="Help"/>
		<div id="instruction_dlg" title="Order Status Report Email"> </div> 
		<div id="ShowDetailDialog" title=" Show Detail Dialog"></div>
		<div id="retrieve_Info_for_Peptide_QC" title="Retrieve Info for Peptide QC"></div>
	</body>
</html>