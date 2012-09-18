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
		<script type="text/javascript">
		
		 function checkForm() {
		       $("#order_no_txt").val($.trim($("#order_no_txt").val()));
		       $("#so_no_txt").val($.trim($("#so_no_txt").val()));
		       if(isNaN( $("#so_no_txt").val())) {
		    	   alert("Please enter a valid number for NanJing SO NO .");
		    	   return false;
		       }
		       $("#filter_EQI_srcSoNo").val($.trim($("#filter_EQI_srcSoNo").val()));
		       if(isNaN( $("#filter_EQI_srcSoNo").val())) {
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
            	$('#new_route_dlg').dialog({
					autoOpen: false,
					height: 360,
					width: 660,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            	
            	$('#assign_group_dlg').dialog({
					autoOpen: false,
					height: 360,
					width: 660,
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
					height: 420,
					width: 740,
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
            
               //add by zhanghuibin
              if($("#warehouse_sel").html().indexOf("Gene Department")>=0){
                  $("#excelDown").show();
              }
            });
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
            			  var option2 = "<option value=''>All</option>";
            			  var option1 = "<option value='0'></option>";
            	          $("#workGroup_sel").append(option1);
            	          $("#workGroup_sel").append(option2);
            			  for(var i=0;i<data.workGroupList.length;i++) {
            				  var option = "<option value='"+data.workGroupList[i].id+"'>"+data.workGroupList[i].name+"</option>";
            				  $("#workGroup_sel").append(option);
            			  }                             
            		},
            		error: function(xhr, textStatus){
            		}
            	});          
            }
            
           function BatchAssignGroup() {
        	   var orderNoStrs = $("#choiceOption").val();
        	   if(orderNoStrs=="") {
        		   alert("Please select one work order at least.");
        		   return;
        	   }
        	  $.ajax({
        			type: "POST",
        			url:  "workorder_proc!checkWorkOrder.action",
        			data: "orderNoStrs="+orderNoStrs,
        			dataType: 'json',
        			success: function(data){
        				if(data.message!='fail') {
        					$('#assign_group_dlg').dialog("option", "open", function() {
        					 var url = "workorder_proc!showAssignGroupDlg.action?centerId="+data.message;
       	              		 var htmlStr = '<iframe src="'+url+'" height="100%" width="100%" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
       				         $('#assign_group_dlg').html(htmlStr);
       					});
       					$('#assign_group_dlg').dialog('open');
        				} else {
        					alert("Please select the same work center for these work orders.");
        				}
        			},
        			error: function(msg){
        			   alert("failure");
        			   return;
        			}
        	  });
           }

           //add by zhanghuibin
          function taskListForExcel(){
              var actionStr = document.forms[0].action;
              document.forms[0].action = "workorder_proc!taskListForExcel.action";
              document.forms[0].submit();
              document.forms[0].action = actionStr;
          }
         function viewOrder(id) {
             if (id != null && id != "") {
                 $('#ShowDetailDialog').dialog("option", "open", function() {
                     var htmlStr = '<iframe src="${global_url}/order/order!edit.action?orderNo=' + id + '&operation_method=view&lookCust=N&defaultTab=1&lookFromWoFlag=1" id="selectUserFrame"  height="100%" width="1000" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
                     $('#ShowDetailDialog').html(htmlStr);
                 });
                 $('#ShowDetailDialog').dialog('open');
                 return true;
             } else {
                 alert("Please select an sales order no!");
                 return false;
             }
         }
        </script>
	</head>
	<body class="content" style="background-image: none;">
	<div id="frame12" style="display:none;" title="Generate QC Batch">
	</div>
	<div id="batchOrder" style="display:none;" title="Batch Order Processing">
	</div>
		<form action="workorder_proc!taskList.action" method="get"
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
								<td>
									<input name="filter_EQI_srcSoNo" id="filter_EQI_srcSoNo" type="text" class="NFText" size="20" />
								</td>
								<td>
									Product/Service
								</td>
								<td width="120">
									<s:select id="class_sel" name="production" headerKey=""
										headerValue="All" list="classList"
										listKey="value" listValue="type"/>
								</td>
							</tr>
							<tr>
								
								<td align="right">
									Status
								</td>
								<td width="120">
								<select id="status_sel" name="filter_EQS_status">
									<option value="New">New</option>
									<option value="In Production">In Production</option>
									<option value="Canceled">Canceled</option>
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
										list="workGroupList" headerKey="0"
										headerValue="" listKey="id" listValue="name"></s:select>
								</s:if>
								<s:else>
									<select id="workGroup_sel" name="workGroupId">
										<option value="0"></option>
									</select>
								</s:else>
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
				<div class="input_box" style="height: 320px;">
					<div class="content_box">
						<iframe id="srch_route_iframe" name="result_iframe"
							src="workorder_proc!taskList.action" width="100%" height="630"
							frameborder="0" scrolling="no"></iframe>
					</div>
				</div>
				<div class="button_box" style="margin-top: 30px">
					<input type="button" name="Submit52" value="Batch Assignment Group"
						class="search_input3"
						onclick="BatchAssignGroup()" />
                    <input type="button" id="excelDown" name="excelDown" value="Excel" style="display:none;"
						class="search_input3" onclick="taskListForExcel()" />
				</div>
			</div>
		</form>
		<div id="assign_group_dlg" title="Assignment Work Group"/>
		<div id="instruction_dlg" title="Send supply email"> </div> 
		<div id="ShowDetailDialog" title=" Show Detail Dialog"></div>
		<div id="new_route_dlg" title=" New Routing " style="visible: hidden">
		</div>
	</body>
</html>