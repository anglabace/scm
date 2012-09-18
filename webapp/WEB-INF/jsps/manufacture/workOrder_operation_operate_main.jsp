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
<link href="${global_js_url}greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.dialog.all.js"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script>
 $(function(){
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
	 $("#update_status_dlg").dialog({
			autoOpen: false,
			height: 440,
			width: 700,
			modal: true,
			bgiframe: true,
			buttons: {
			}});
 });
 function updateStatus() {
	 if($("#choiceOption").val()=="") {
		 alert("Please select one at least.");
		 return;
	 }
	 $('#update_status_dlg').dialog("option", "open", function() {	
  		 var htmlStr = '<iframe id="updateStatusFrame" src="workorder_operation!batchOperationDlg.action" height="100%" width="100%" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
         $('#update_status_dlg').html(htmlStr);
	});
	 $("#update_status_dlg").dialog("open");
 }
  function modifyStatus() {
	  $("#customStartDate_hidden").attr("value",$("#updateStatusFrame").contents().find("#customStartDate").val());
	  $("#status").attr("value",$("#updateStatusFrame").contents().find("#status_sel_dlg").val());
	  $("#comments_hidden").attr("value",$("#updateStatusFrame").contents().find("#comments").val());
	  $('#srch_route_iframe').contents().find("input:checkbox").each(function(){
		  if(this.checked) {
			  $(this).parent().parent().find("td[name='status']").html($("#status").val());
			  if($("#customStartDate_hidden").val()!="") {
				  $(this).parent().parent().find("td[name='customStartDate']").html($("#customStartDate_hidden").val());
			  }
		  }
	  });
	  $("#update_status_dlg").dialog("close");
  }
  function saveUpdate() {
	    var comments = $("#comments_hidden").val();
		var status = $("#status").val();
		var customStartDate = $("#customStartDate_hidden").val();
		if(customStartDate!=null&&customStartDate!="") {
			customStartDate = customStartDate+" 00:00";
		}
		if(status!=""||customStartDate!="") {
			var param = "comments="+comments+"&woStatus="+status+"&customStartDate="+customStartDate;
			var woOperationIds =$("#choiceOption").val();
			param = param+"&woOperationIds="+woOperationIds;
		     $.ajax ({
		 			type: "POST",
		 			url: "workorder_operation!batchUpdateWoOperation.action",
		 			dataType: 'json',
		 			data: param,
		 			success: function(data){
		 			    if(data.message!='error') {
		 			    	alert("Update workOrderOperations successful.");
		 			    } else {
		 			    	alert("Update workOrderOperations failing.");
		 			    }
		 			   parent.$('#update_operation_status_dlg').dialog('close');
		 			   	return;		   
		 			},
		 			error: function(msg){
		 				parent.$('#update_operation_status_dlg').dialog('close');
		 				alert("Update workOrderOperations failing,please contact with administrator for help.");		
		 			}
		 	 }); 
		} else {
			parent.$('#update_operation_status_dlg').dialog('close');
		}
  }
  function check() {
	  var altOrderNo = $("#altOrderNo").val();
	  $("#altOrderNo").attr("value",$.trim(altOrderNo));
	  var soNo = $("#soNo").val();
	  $("#soNo").attr("value",$.trim(soNo));
	  var srcSoNo = $("#srcSoNo").val();
	  $("#srcSoNo").attr("value",$.trim(srcSoNo));
	  return true;
  }
  
  function confirm() {
	  var str = parent.$("#customConfirmInfo").val();
	  $('#srch_route_iframe').contents().find("input:checkbox").each(function(){
		  if(this.checked) {
			  if(this.id!='check_all') {
				  str = str + $(this).parent().parent().find("td[name='workOrderNo']").html()
		  			+","+$(this).parent().parent().find("td[name='srcSoNo']").html()+"_"
		  			+$(this).parent().parent().find("td[name='soitemNo']").html()
		  			+","+$(this).parent().parent().find("td[name='operationName']").html()
		  			+","+$(this).parent().parent().find("td[name='status']").html()+"\n";
			  }
			
		  }
	  });
	  parent.$("#customConfirmInfo").val(str);
	  parent.$('#update_operation_status_dlg').dialog('close');
	  
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
  
</script>
</head>
<body>
<s:form action="workorder_operation!workOrderOperationList" target="result_iframe" name="docTempSearch" method="get" onsubmit="return check();">
<table border="0" cellpadding="0" cellspacing="0" class="General_table">
        <tr>
          <th height="22">Work Order No</th>
          <td>
          	  <input type="text" name="workOrderOperationDTO.altOrderNo"  id="altOrderNo" class="NFText" size="20"/>
          </td>
          <th>China Sales Order No</th>
          <td>
          	  <input type="text" name="workOrderOperationDTO.soNo"  id="soNo" class="NFText" size="20"/>
          </td>
          <th height="22">US Sales Order No</th>
        	<td><input type="text" name="workOrderOperationDTO.srcSoNo"  id="srcSoNo" class="NFText" size="20"/></td>
         </tr>
         <tr>
         <th height="22">Scheduled Start Date</th>
          <td>
          	  <input name="workOrderOperationDTO.exptdStartDate" id="exptdStartDate" type="text" class="pickdate NFText" style="width: 124px;"/>
          </td>
          <th>Scheduled Complete Date</th>
          <td>
          	   <input name="workOrderOperationDTO.exptdEndDate" id="exptdEndDate" type="text" class="pickdate NFText" style="width: 124px;"/>
          </td>
          <th>Status</th>
          <td>
          <s:if test="customFlag!=null&&customFlag==1">
          	<select name="workOrderOperationDTO.status">
          	<option value="0">All</option>
          	<option value="New">New</option>
          	<option value="In Production">In Production</option>
          	<option value="Hold">Hold</option>
          </select>
          </s:if>
          <s:else>
          <select name="workOrderOperationDTO.status">
          	<option value="0">All</option>
          	<option value="New">New</option>
          	<option value="In Production">In Production</option>
          </select>
          </s:else>
          </td>
        </tr>
         <tr>
         <th height="22">Customized Start Date</th>
          <td>
          	  <input name="workOrderOperationDTO.customStartDate" id="customStartDate" type="text" class="pickdate NFText" style="width: 124px;"/>
          </td>
          <th>Customized Complete Date</th>
          <td>
          	   <input name="workOrderOperationDTO.customEndDate" id="customEndDate" type="text" class="pickdate NFText" style="width: 124px;"/>
          </td>
          <th>Operation Name</th>
          <td>
          	<input type="text" name="workOrderOperationDTO.operationName"  id="soNo" class="NFText" size="20"/>
          </td>
        </tr>
        <tr>
        	<th>Work Center</th>
        	<td>
        	<s:if test="workCenterList!=null&&workCenterList.size()>0">
									<s:select id="warehouse_sel" name="workCenterId"
										list="workCenterList" headerKey=""
										headerValue="All" listKey="id" listValue="name" onchange="centerSelect();"></s:select>
								</s:if>
								<s:else>
									<select id="warehouse_sel" name="workCenterId">
										<option value="">All</option>
									</select>
								</s:else>
			</td>
	        <th>Work Group</th>
	          <td>
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
        	<td>&nbsp;</td>
        	<td>&nbsp;</td>
        </tr>
        <tr>
          <td colspan="6" align="center">
          	<input type="submit" name="Submit5" value="Search" class="style_botton"/>
          </td>
        </tr>
</table>
<s:hidden name="customFlag" id="customFlag"/>
</s:form>
<s:hidden name="status" id="status"/>
<s:hidden name="comments" id="comments_hidden"/>
<s:hidden name="customStartDate_hidden" id="customStartDate_hidden"/>
<input type="hidden" id="choiceOption"/> 	 
<div>
<iframe id="srch_route_iframe" name="result_iframe"
		src="workorder_operation!workOrderOperationList.action?customFlag=${customFlag}" width="100%" height="400"
		frameborder="0" scrolling="no"></iframe>
</div>
<div align="center">
<s:if test="customFlag!=null&&customFlag==1">
	 <input type="button" name="Submit5" value="Confirm" class="style_botton3" onclick="confirm();"/>
</s:if>
<s:else>
    <input type="button" name="Submit5" value="Update Status & Date" class="style_botton3" onclick="updateStatus();"/>
	<input type="button" name="Submit16" value="Save" class="style_botton" onclick="saveUpdate();"/>
</s:else>
<input type="button" name="Submit16" value="Cancel" class="style_botton" onclick="parent.$('#update_operation_status_dlg').dialog('close');"/>
</div>
<div id="update_status_dlg" title="Update Status">
</div>

     	
</body>
</html>