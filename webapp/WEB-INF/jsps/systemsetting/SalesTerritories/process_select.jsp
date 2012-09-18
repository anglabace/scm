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
function addProcess(){
	 if ($("#resultTable :radio:checked").length < 1) {
         alert('Please select one to continue your operation!');
         return;
     }
     
     var value = $("#resultTable").find("[name='sequence']:checked").val();
     var array = value.split("::");
     parent.$("#sequence").val(array[0]);
 	 parent.$("#processName").val(array[1]);
 	 parent.$("#status_sel").val(array[2]);
 	 parent.$("#description").val(array[3]);
 	 parent.$("#modifyDate").val(array[4].substring(0,array[4].indexOf(" ")));
 	 parent.$("#modifiedName").val(array[5]);   
     parent.$('#processChoiceDialog').dialog('close');
}

function check() {
	  var filter_LIKES_processName = $("#filter_LIKES_processName").val();
	  $("#filter_LIKES_processName").attr("value",$.trim(filter_LIKES_processName));
	  return true;
}
</script>
</head>
<body>
<s:form action="assignment_rules!selectProcess"  name="docTempSearch" method="get" onsubmit="return check();">
<table border="0" cellpadding="0" cellspacing="0"
				class="General_table" align="center">
        <tr>
          <td>Process Name</td>
          <td>
          	<input  id="filter_LIKES_processName" name="filter_LIKES_processName" type="text" class="NFText" size="20" >
          	<input name="Submit3" type="submit" class="style_botton" value="Search" />
          </td>
        </tr>
</table>
</s:form>

<table width="600" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="4">
					<table width="580" border="0" cellpadding="0" cellspacing="0"
						class="list_table">
						<tr>
				        <th width="46"></th>
				        <th width="181">sequence</th>
				        <th width="240"> Process Name </th>
				        <th>Status</th>
				        </tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div style="width: 600px; height: 210px; overflow: scroll;">
						<table width="580" border="0" cellpadding="0" cellspacing="0"
							class="list_table" id="resultTable">
							<s:iterator value="processPage.result" status="st">
					      	<s:if test="#st.odd">
					      	<tr>
					          <td width="46"><input type="radio" value="${sequence}::${processName}::${status}::${description}::${modifyDate}::${modifiedName}" id="${sequence}" name="sequence"/></td>
					          <td width="181">${sequence}</td>
					          <td width="240">${processName}</td>
					          <td>${status}</td>
					        </tr>
					      	</s:if>
					      	<s:else>
					      	<tr>
					          <td class="list_td2"><input type="radio" value="${sequence}::${processName}::${status}::${description}::${modifyDate}::${modifiedName}" id="${sequence}" name="sequence"/></td>
					          <td class="list_td2">${sequence}</td>
					          <td class="list_td2">${processName}</td>
					          <td class="list_td2">${status}</td>
					        </tr>
					      	</s:else>
					      </s:iterator>
						</table>
					</div>
					<div class="grayr">
						<jsp:include page="/common/db_pager.jsp">
							<jsp:param value="${ctx}/assignment_rules!selectProcess.action" name="moduleURL" />
						</jsp:include>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="4" class="botton_box">
					<input name="Submit" type="button" id="select_btn"
						class="style_botton" value="Select" onclick="addProcess()"/>
					<input type="submit" name="Submit2" value="Cancel"
						class="style_botton"
						onclick="parent.$('#processChoiceDialog').dialog('close');" />
				</td>
			</tr>
		</table>
</body>
</html>
