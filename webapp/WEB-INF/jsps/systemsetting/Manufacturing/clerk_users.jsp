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
function addUser(){
	 if ($("#resultTable :radio:checked").length < 1) {
         alert('Please select one at least!');
         return;
     }
     parent.$("#clerkName").val($("#resultTable").find("[name='userId']:checked").val());
 	 parent.$("#clerkId").val($("#resultTable").find("[name='userId']:checked").attr("id"));  
     parent.$('#clerkChoiceDialog').dialog('close');
}

function check() {
	  var filter_LIKES_loginName = $("#filter_LIKES_loginName").val();
	  var filter_EQI_userId = $("#filter_EQI_userId").val();
	  var filter_LIKES_deptName = $("#filter_LIKES_deptName").val();
	  $("#filter_LIKES_loginName").attr("value",$.trim(filter_LIKES_loginName));
	  $("#filter_EQI_userId").attr("value",$.trim(filter_EQI_userId));
	  $("#filter_LIKES_deptName").attr("value",$.trim(filter_LIKES_deptName));
	  return true;
}
</script>
</head>
<body>
<s:form action="manufacturing_clerks!selectUser"  name="docTempSearch" method="get" onsubmit="return check();">
<table border="0" cellpadding="0" cellspacing="0"
				class="General_table" align="center">
        <tr>
          <td>Employee ID</td>
          <td>
          	<s:textfield id="filter_EQI_userId" name="filter_EQI_userId" type="text" class="NFText" size="20" onkeypress="if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false;">
          		<s:param name="value">
          			<s:property value="param.filter_EQI_userId"/>
          		</s:param>
          	</s:textfield>
          </td>
            <td>Employee Name</td>
          <td>
          	<s:textfield id="filter_LIKES_loginName" name="filter_LIKES_loginName" type="text" class="NFText" size="20" >
          		<s:param name="value">
          			<s:property value="param.filter_LIKES_loginName"/>
          		</s:param>
          	</s:textfield>
          </td>
            <td>Depart</td>
          <td>
          	<s:textfield id="filter_LIKES_deptName" name="filter_LIKES_deptName" type="text" class="NFText" size="20" >
          		<s:param name="value">
          			<s:property value="param.filter_LIKES_deptName"/>
          		</s:param>
          	</s:textfield>
          </td>
            <td><input name="Submit3" type="submit" class="style_botton" value="Search" /></td>
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
				        <th width="114">Employee ID</th>
				        <th width="240">Employee Name</th>
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
					          <td width="46"><input type="radio" value="${loginName}" id="${userId}" name="userId"/></td>
					          <td width="114">${userId}</td>
					          <td width="240">${loginName}</td>
					        </tr>
					      	</s:if>
					      	<s:else>
					      	<tr>
					          <td class="list_td2"><input type="radio" value="${loginName}" id="${userId}" name="userId"/></td>
					          <td class="list_td2">${userId}</td>
					          <td class="list_td2">${loginName}</td>
					        </tr>
					      	</s:else>
					      </s:iterator>
						</table>
					</div>
					<div class="grayr">
						<jsp:include page="/common/db_pager.jsp">
							<jsp:param value="${ctx}/manufacturing_clerks!selectUser.action" name="moduleURL" />
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
						onclick="parent.$('#clerkChoiceDialog').dialog('close');" />
				</td>
			</tr>
		</table>
</body>
</html>
