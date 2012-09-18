<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>WorkGroup Search Result</title>
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<script src="${global_js_url}jquery/jquery.js" type="text/javascript"
			language="javascript"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
		<script language="JavaScript" type="text/javascript">

		
$(document).ready(function(){

    $("#resultTable tr:odd").find("td").addClass("list_td2");
    var parentVal = "";
    parent.frames["operation_list_frame"].$("#resultTable :checkbox").each(function() {
        parentVal += $(this).attr("id") + ",";
    });      
    //复选框绑定: 全选|全不选
    $("#check_all").click( function() {
       $(":checkbox").each(function() {
          this.checked = $("#check_all").get(0).checked;
       });      
    });
    
    //执行Select
    $("#select_btn").click( function() {    
       if ($(":checkbox:gt(0):checked").length < 1) {
           alert('Please select one at least!');
		   return;
       }
       var trCount = parent.frames["operation_list_frame"].$("#resultTable tr").length;
       var param = "sessRouteId=" + $("#sessRouteId").val() + "&trCount=" + trCount;
       var msg = "" ;
       $("#resultTable :checkbox:checked").each(function() {  
          
          var tdObj = $(this).parent();
          var trObj = tdObj.parent();
          if (parentVal.indexOf($(this).val()+",") != -1) {
              alert("Has the operation '"+ trObj.children(":eq(1)").html() + "' already !");
          } else {
        	  param += "&operationIdList=" + $(this).val();      
          }    
              
       });     
       $('#select_btn').attr("disabled", true);
       $.ajax({
			type: "POST",
			url: "route!saveOperation.action",
			dataType: 'json',
			data: param,
			success: function(data){
			    parent.$('#sel_res_dlg').dialog('close');
			    //刷新frame
			    parent.$("#dhtmlgoodies_tabView1 .dhtmlgoodies_aTab").html(parent.$("#dhtmlgoodies_tabView1 .dhtmlgoodies_aTab").html());
			},
			error: function(msg){
				alert("Select failed !");
                $('#select_btn').attr("disabled", false);		
			}
		});  	       
    });//end of 执行Select;
    
        //动态改变父窗口表格
    function ranscan(jsonObj) {
       var trCount = parent.frames["operation_list_frame"].$("#resultTable tr").length;
       var exLoop = 1;
       $("#resultTable :checkbox:checked").each(function() {
       
          var tdObj = $(this).parent();
          var trObj = tdObj.parent();
          if (parentVal.indexOf($(this).val()+",") == -1) {
        	  var hiddenTr = $("#hidden_tbl tr:eq(" + trObj.get(0).rowIndex + ")");

        	  var tempSeqNo = "";

        	            var md5Str = ""; 
        	            var resId = $(this).val();
        	  		  $.each(jsonObj, function(name, value) {
        	  	             if (resId == name) {
        	  	                md5Str = value;
        	  	             } 
        	  	      });
        	  	      var tagTR = hiddenTr.clone();
        	  	      tagTR.children(":eq(0)").children(":eq(0)").val(md5Str);
        	  	      tagTR.children(":eq(1)").html("<a href='javascript:void(0)' onclick='edit(\""+resId+"\")' target='_parent'>"+(trCount+exLoop)+"</a>");
        	            parent.frames["operation_list_frame"].$("#resultTable").append(tagTR);
        	  	      exLoop ++;
          }    
          

       });   
      parent.$('#sel_res_dlg').dialog('close');
    }
    
    
});
</script>
	</head>
	<body>
		<form action="operation!selectForRoute.action" method="get">
			<input type="hidden" name="sessRouteId" id="sessRouteId"
				value="${param.sessRouteId}" />
			<table border="0" cellpadding="0" cellspacing="0"
				class="General_table">
				<tr>
					<td>
						Operation Name
					</td>
					<td>
						<input name="filter_LIKES_name" value="${param.filter_LIKES_name}"
							type="text" class="NFText" size="20" />
					</td>
					<td>
						Description
					</td>
					<td>
						<input name="filter_LIKES_description"
							value="${param.filter_LIKES_description}" type="text"
							class="NFText" size="40" />
					</td>
					<td>
						<input name="Submit3" type="submit" class="style_botton"
							value="Search" />
					</td>
				</tr>
			</table>
		</form>
		<table width="800" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="4">
					<table width="780" border="0" cellpadding="0" cellspacing="0"
						class="list_table">
						<tr>
							<th width="28">
								<input name="checkbox2" type="checkbox" id="check_all" />
							</th>
							<th width="100">
								Operation Name
							</th>
							<th width="100">
								Description
							</th>
							<th width="60">
								Status
							</th>
							<th width="126">
								Operation Supervisor
							</th>
							<th width="80">
								SetupTime
							</th>
							<th width="65">
								Runtime
							</th>
							<th width="55">
								UOM
							</th>
							<th>
								WorkCenter
							</th>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div style="width: 800px; height: 210px; overflow: scroll;">
						<table width="780" border="0" cellpadding="0" cellspacing="0"
							class="list_table" id="resultTable">
							<s:iterator value="operationPage.result">
								<tr>
									<td width="28" align="center">
										<input type="checkbox" value="${id}"
											name="operationIdList" />
									</td>
									<td width="100">
										${name}
									</td>
									<td width="100">
										${description}
									</td>
									<td width="60">
										${status}
									</td>
									<td width="126">
										${superName}
									</td>
									<td width="80">
										${setupTime}
									</td>
									<td width="65">
										${runTime}
									</td>
									<td width="55">
										<div align="right">
											${uom}
										</div>
									</td>
									<td>
										${workCenterName}
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
					<div class="grayr">
						<jsp:include page="/common/db_pager.jsp">
							<jsp:param value="${ctx}/operation!selectForRoute.action"
								name="moduleURL" />
						</jsp:include>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="4" class="botton_box">
					<input name="Submit" type="button" id="select_btn"
						class="style_botton" value="Select" />
					<input type="submit" name="Submit2" value="Cancel"
						class="style_botton"
						onclick="parent.$('#sel_res_dlg').dialog('close');" />
				</td>
			</tr>
		</table>
		<table width="955" border="0" cellpadding="0" cellspacing="0"
			class="list_table" id="hidden_tbl" style="display: none">
			<s:iterator value="operationPage.result">
				<tr>
					<td width="65" align="center">
						<input type="checkbox" value="${key}" id="${id}" name="routeOperationId" />
					</td>
					<td width="60">
						${value.seqNo}
					</td>
					<td width="123">
						${name}
					</td>
					<td width="98">
						${description}
					</td>
					<td width="70">
						${status}
					</td>
					<td width="126">
						${superName}
					</td>
					<td width="89">
						${setupTime}
					</td>
					<td width="81">
						${runTime}
					</td>
					<td width="55">
						${uom}
					</td>
					<td width="102">
						${workCenterName}
					</td>
					<td width="90" align="center">
						<s:date format="yyyy-MM-dd" name="modifyDate" />
					</td>
					<td>
						${modifyUser}
					</td>
				</tr>
			</s:iterator>
		</table>
	</body>
</html>
