<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Quality Control </title>
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
		<script src="${global_js_url}newwindow.js" type="text/javascript"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
		<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
		<script type="text/javascript">
			var global_url = "${global_url}" ;
		    function checkForm() {
		       $("#filter_EQS_altOrderNo").val($.trim($("#filter_EQS_altOrderNo").val()));
		       $("#filter_EQI_soNo").val($.trim($("#filter_EQI_soNo").val()));
		       if(isNaN( $("#filter_EQI_soNo").val())) {
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
	 
	 $('.pickdate').each(function(){
			$(this).datepicker(
				{
					dateFormat: 'yy-mm-dd',
					changeMonth: true,
					changeYear: true,
					yearRange: '-100:+0'
				});
		});
     //清除Status为'Received'的选项 
     
      /**页面触发选择QC Group**/
      $("#qc_group_sel").change(function() {
    	  var qc_group = $(this).val();
	      if (qc_group == "") {
	    	  if($("#qc_group_sel").get(0).options.length>1) {
	    		  qc_group = $("#qc_group_sel").get(0).options[1].value;
	    	  } else {
	    		  $("#qc_clerk_sel").get(0).options.length = 0;
	 	          return;
	    	  }
	      }
	      $.ajax({
			type: "POST",
			url: "workorder_entry!selectQaQcGroup.action",
			data: "id=" + qc_group,
			dataType: 'json',
			success: function(data, textStatus){
			    var jsonObj = data.qaClerkList;
			    $("#qc_clerk_sel").get(0).options.length = 0;
			    if (jsonObj != null && jsonObj.length >= 1) {
				    $("#qc_clerk_sel").get(0).options.add(new Option("All", ""));
				    for (var i=0; i<jsonObj.length; i++) {
				         $("#qc_clerk_sel").get(0).options.add(new Option(jsonObj[i].superName, jsonObj[i].userId));
				    }
			    }						
			},
			error: function(xhr, textStatus){
			   alert("failure");
			   return;
			},
			async:false
		 });       
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
			height: 320,
			width: 630,
			modal: true,
			bgiframe: true,
			buttons: {
			}
		});
  	
  	$('#monoclonal_antibody_labels_dlg').dialog({
			autoOpen: false,
			height: 320,
			width: 630,
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

    //Batch Order Processing Dialog
    $('#batch_order_dlg').dialog({
		autoOpen: false,
		height: 380,
		width: 650,
		modal: true,
		bgiframe: true,
		buttons: {
		}
    });

    $("#batch_order_btn").click( function() { 
       if (window.frames['result_iframe'].$(":checkbox:gt(0):checked").length < 1) {
           alert('Please select one at least!');
		   return;
       }    
    
	   $('#batch_order_dlg').dialog("option", "open", function() {	
         	var htmlStr = '<iframe src="workorder_qc!showProcessOrder.action" height="320" width="100%" scrolling="no" style="border:0px" frameborder="0"></iframe>';
            $('#batch_order_dlg').html(htmlStr);
	   });
    
       $('#batch_order_dlg').dialog('open');
    });
 });//end of $(function() {}...

          //generate_qc_batch button click event
          function generate_qa_batch() {
              var orderNoStrs = $("#choiceOption").val();
              if(orderNoStrs=="") {
                  alert("Please select one at least!");
                  return;
              }
              var url = "workorder_qc!showOperBatch.action?orderNoStrs="+orderNoStrs;
              $('#frame12').dialog("option", "open", function() {	
           		 var htmlStr = '<iframe src="'+url+'" height="420" width="650" scrolling="no" style="border:0px" frameborder="0"></iframe>';
	         $('#frame12').html(htmlStr);
		});
		$('#frame12').dialog('open');
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
        </script>
	</head>
	<body class="content" style="background-image: none;">
		<div id="frame12" style="display: none;" title="Generate QA Batch">
		</div>
		<div id="batch_order_dlg" style="display: none;"
			title="Batch Order Processing">
		</div>
		<form action="workorder_qc!list.action" method="get"
			target="result_iframe" onsubmit="return checkForm();">
			<div class="scm">
			<div class="title_content">
  				<div style="padding-left: 20px;color: #5579A6;vertical-align:middle;"><img src="${global_url}images/arrow1.jpg" style="width:16px;height:17px;vertical-align:middle;" onclick="toggleShowMore_img(this,'search_box1');"/>&nbsp;&nbsp;Quality Control</div>
			</div>
				<div class="search_box" id="search_box1">
					<div class="search_box_three single_search" style="padding: 0px; padding-bottom: 5px">
						<table border="0" cellspacing="0" cellpadding="0"
							class="General_table">
							<tr>
								<td>
									Work Order No
								</td>
								<td>
									<input name="filter_EQS_altOrderNo" id="filter_EQS_altOrderNo" type="text" class="NFText" size="20" />
								</td>
								<td>
									NanJing SO NO 
								</td>
								<td>
									<input name="filter_EQI_soNo" id="filter_EQI_soNo" type="text" class="NFText" size="20" />
								</td>
								<td align="right">
									Status
								</td>
								<td>
								<select id="status_sel" name="filter_EQS_status">
									<option value="">All</option>
									<option value="Production Complete">Production Complete</option>
									<option value="Product QC Partial">Product QC Partial</option>
									<option value="Document QC Partial">Document QC Partial</option>
									<option value="Product QC Failed">Product QC Failed</option>
									<option value="Document QC Failed">Document QC Failed</option>
									<option value="Product QC Passed">Product QC Passed</option>
									<option value="Document QC Passed">Document QC Passed</option>
									<option value="QC Partial">QC Partial</option>
									<option value="QC Failed">QC Failed</option>
									<option value="Closed">Closed</option>
								</select>
								</td>
								<td align="right">
								US SO NO
								</td>
								<td>
									<input name="filter_EQI_srcSoNo" id="filter_EQI_srcSoNo" type="text" class="NFText" size="20" />
								</td>

							</tr>
							<tr>
								
								<td align="right">
									QC Group
								</td>
								<td>
								<s:if test="qcGroupList!=null&&qcGroupList.size()>0">
									<s:select id="qc_group_sel" name="filter_EQI_qcGroup"
										list="qcGroupList" listKey="id" listValue="groupName" headerKey=""
										headerValue="All"></s:select>
								</s:if>
								<s:else>
									<select id="qc_group_sel" name="filter_EQI_qcGroup">
										<option value="">All</option>
									</select>
								</s:else>
								</td>
								<td align="right">
									QC Clerk
								</td>
								<td>
								<s:if test="qcClerkList!=null&&qcClerkList.size()>0">
									<s:select id="qc_clerk_sel" name="filter_EQI_qcClerk"
										list="qcClerkList" listKey="userId" listValue="superName" headerKey=""
										headerValue="All"></s:select>
								</s:if>
								<s:else>
									<select name="filter_EQI_qcClerk" id="qc_clerk_sel" />
								</s:else>
								</td>
								<td align="right">Order Date</td>
								<td colspan=2>
								<input name="start_orderDate" id="start_orderDate" type="text" class="pickdate NFText" style="width: 100px;"/>~
								<input name="end_orderDate" id="end_orderDate" type="text" class="pickdate NFText" style="width: 100px;"/>
								</td>
								<td></td>
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
							src="workorder_qc!list.action" width="100%" height="630"
							frameborder="0" scrolling="no"></iframe>
					</div>
				</div>
				<div class="button_box" style="margin-top: 30px">
					<input type="button" name="Submit52" value="Batch Order Processing"
						class="search_input3" id="batch_order_btn" />
					<input type="button" name="Submit52" value="Create Labels"
						class="search_input2"
					 onclick="printLabels();"/>
				</div>
			</div>
		</form>
		<div id="print_labels_dlg" title="Create Labels"/>
		<div id="protein_labels_dlg" title="Create Labels"/>
		<div id="monoclonal_antibody_labels_dlg" title="Create Labels"/>
		<div id="polyclonal_antibody_labels_dlg" title="Create Labels"/>
		<div id="oligo_labels_dlg" title="Create Labels"/>
		<div id="new_route_dlg" title=" New Routing " style="visible: hidden">
		</div>
	</body>
</html>