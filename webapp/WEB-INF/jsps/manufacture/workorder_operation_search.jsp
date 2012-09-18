<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>Operation Search Result</title>
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<script src="${global_js_url}jquery/jquery.js" type="text/javascript"
			language="javascript"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
		<script language="JavaScript" type="text/javascript">
	
$(document).ready(function(){
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
    $("#status_sel").val('${param.filter_EQS_status}');
    $("#workCenterId_sel").val('${param.filter_EQI_workCenterId}');
    //复选框绑定: 全选|全不选
    $("#check_all").click( function() {
       $(":checkbox").each(function() {
          this.checked = $("#check_all").get(0).checked;
       });      
    });  

    $("#select_btn").click( function() {
       if ($("#resultTable :checkbox:checked").length < 1) {
           alert('Please select one at least!');
		   return;
       }
       var name = "";
       var id = "";
       $("#resultTable :checkbox:checked").each(function() {
	          var tdObj = $(this).parent();
	          var trObj = tdObj.parent();    
	          var temp = trObj.children(":eq(1)").html();
              name = name + "," + $.trim(temp);
              id = id + "," + $.trim(this.value);
       });     
       name = name.substring(1);
       id = id.substring(1);
       parent.$("#operationNameList_txt").val(name);
       parent.$("#operationIdList_hidden").val(id);
       parent.$('#sel_operation_dlg').dialog('close');
    });
    
});
</script>
	</head>
	<body>
		<form action="operation!list.action" method="get">
			<input type="hidden" name="toPage" id="toPage"
				value="${param.toPage}" />
			<table border="0" cellpadding="0" cellspacing="0"
				class="General_table">
				<tr>
					<th>
						Operation Name
					</th>
					<td width="120">
						<input name="filter_LIKES_name" type="text" class="NFText"
							size="20" value="${param.filter_LIKES_name}" />
					</td>
					<th>
						Description
					</th>
					<td width="120">
						<input name="filter_LIKES_description" type="text" class="NFText"
							size="20"  value="${param.filter_LIKES_description}"/>
					</td>
					<th>
						Work Center
					</th>
					<td width="120">
						<s:select id="workCenterId_sel" name="filter_EQI_workCenterId"
							list="centerList" listKey="id" listValue="name" headerKey=""
							headerValue="All"></s:select>
					</td>
				</tr>
				<tr>
					<td>
						Status
					</td>
					<td width="120">
					   <s:select id="status_sel" name="filter_EQS_status"
							list="{'ACTIVE', 'INACTIVE'}" headerKey=""
							headerValue="All" ></s:select>
					</td>
					<td>
						<input name="Submit3" type="submit" class="style_botton"
							value="Search" />
					</td>
				</tr>
			</table>
		</form>
		<table width="678" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<table width="655" border="0" cellpadding="0" cellspacing="0"
						class="list_table">
						<tr>
							<th width="48" align="center">
								<input name="checkbox2" type="checkbox" id="check_all" />
							</th>
							<th width="120">
								Operation Name
							</th>
							<th width="200">
								Description
							</th>
							<th width="80">
								Status
							</th>
							<th width="100">
								Supervisor
							</th>
							<th>
								Work Center
							</th>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div style="width: 675px; height: 240px; overflow: scroll;">
						<table width="655" border="0" cellpadding="0" cellspacing="0"
							class="list_table" id="resultTable">
							<s:iterator value="operationPage.result">
								<tr>
									<td width="48" align="center">
										<input type="checkbox" value="${id}" name="delOperationId" />
									</td>
									<td width="120">
										${name}
									</td>
									<td width="200">
										${description}
									</td>
									<td width="80" align="center">
										${status}
									</td>
									<td width="100">
										${superName}
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
							<jsp:param value="${ctx}/operation!list.action" name="moduleURL" />
						</jsp:include>
					</div>
				</td>
			</tr>
			<tr>
				<td class="botton_box">
					<input name="Submit" type="button" id="select_btn"
						class="style_botton" value="Select" />
					<input type="submit" name="Submit2" value="Cancel"
						class="style_botton"
						onclick="parent.$('#sel_operation_dlg').dialog('close');" />
				</td>
			</tr>
		</table>
	</body>
</html>
