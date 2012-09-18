<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<link media="all" type="text/css" rel="stylesheet" href="${global_css_url}openwin.css">
<style type="text/css">
<!--
.content .scm #dhtmlgoodies_tabView1 .dhtmlgoodies_aTab .General_table fieldset {
	margin: 4px;
}
-->
</style>
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
});
function addUser(){
	 if ($("#resultTable :radio:checked").length < 1) {
         alert('Please select one to continue your operation!');
         return;
     }
     if(parent.$("#superName").val()==null) {
    	 parent.$("#workGroupAddFrame").contents().find("#superName").val($("#resultTable").find("[name='userId']:checked").val());
         parent.$("#workGroupAddFrame").contents().find("#supervisor").val($("#resultTable").find("[name='userId']:checked").attr("id"));

    	  
     } else {
    	 parent.$("#superName").val($("#resultTable").find("[name='userId']:checked").val());
    	 parent.$("#supervisor").val($("#resultTable").find("[name='userId']:checked").attr("id"));
     }
     parent.$('#userChoiceDialog').dialog('close');
}

function check() {
	  var filter_EQI_employeeId = $("#filter_EQI_employeeId").val();
	  $("#filter_EQI_employeeId").attr("value",$.trim(filter_EQI_employeeId));
	  if(isNaN($.trim(filter_EQI_employeeId))) {
		  alert("Please enter a valid employeeId.");
		  return;
	  }
	  var filter_LIKES_deptName = $("#filter_LIKES_deptName").val();
	  $("#filter_LIKES_deptName").attr("value",$.trim(filter_LIKES_deptName));
	  var filter_LIKES_employeeName = $("#filter_LIKES_employeeName").val();
	  $("#filter_LIKES_employeeName").attr("value",$.trim(filter_LIKES_employeeName));
	  return true;
}
</script>
</head>
<body>
<s:form action="work_group!selectUser"  name="docTempSearch" method="get" onsubmit="return check();">
<table border="0" cellpadding="0" cellspacing="0"
				class="General_table" align="center">
        <tr>
          <td>Employee Id</td>
          <td>
          	<input  id="filter_EQI_employeeId" name="srch.employeeId" value="${srch.employeeId}" type="text" class="NFText" size="20" >
          </td>
          <td>Employee name</td>
          <td>
          	<input  id="filter_LIKES_employeeName" name="srch.employeeName" value="${srch.employeeName}" type="text" class="NFText" size="20" >
          </td>
           <td>Department</td>
          <td>
          	<s:if test="workCenterList!=null&&workCenterList.size()>0">
									<s:select id="filter_EQI_deptId" name="srch.deptId"
										list="workCenterList" headerKey=""
										headerValue="All" listKey="id" listValue="name"></s:select>
								</s:if>
								<s:else>
									<select id="filter_LIKES_deptName" name="filter_EQI_workCenterId">
										<option value="">All</option>
									</select>
								</s:else>
          	 	
          </td>
        </tr>
        <tr>
        <td colspan="6" align="center"><input name="Submit3" type="submit" class="style_botton" value="Search" /></td>
        </tr>
</table>
</s:form>

<table width="660" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="4">
					<table width="640" border="0" cellpadding="0" cellspacing="0"
						class="list_table">
						<tr>
				        <th width="46"></th>
				        <th width="114">Employee Id</th>
				        <th width="240">Employee name</th>
				        </tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div style="width: 657px; height: 210px; overflow: scroll;">
						<table width="640" border="0" cellpadding="0" cellspacing="0"
							class="list_table" id="resultTable">
							<s:iterator value="usersPage.result" status="st">
					      	<s:if test="#st.odd">
					      	<tr>
					          <td width="46"><input type="radio" value="${firstName} ${lastName}" id="${userId}" name="userId"/></td>
					          <td width="114">${employee.employeeId}</td>
					          <td width="240">${firstName}
				   					<c:if test="${! empty lastName}"> </c:if>
				    				${lastName}
				   			</td>
					        </tr>
					      	</s:if>
					      	<s:else>
					      	<tr>
					          <td class="list_td2"><input type="radio" value="${firstName} ${lastName}" id="${userId}" name="userId"/></td>
					          <td class="list_td2">${employee.employeeId}</td>
					          <td class="list_td2">${firstName}
				   					<c:if test="${! empty lastName}"> </c:if>
				    				${lastName}</td>
					        </tr>
					      	</s:else>
					      </s:iterator>
						</table>
					</div>
					<div class="grayr">
						<jsp:include page="/common/db_pager.jsp">
							<jsp:param value="${ctx}/work_group!selectUser.action" name="moduleURL" />
						</jsp:include>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="4" class="botton_box">
					<input name="Submit" type="button" id="select_btn"
						class="style_botton" value="Select" onclick="addUser()"/>
					<input type="submit" name="Submit2" value="Cancel"
						class="style_botton"
						onclick="parent.$('#userChoiceDialog').dialog('close');" />
				</td>
			</tr>
		</table>
</body>
</html>
