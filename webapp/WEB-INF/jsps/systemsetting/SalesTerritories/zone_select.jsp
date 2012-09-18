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
function addZone(){
	 if ($("#resultTable :radio:checked").length < 1) {
         alert('Please select one to continue your operation!');
         return;
     }
     
     var value = $("#resultTable").find("[name='assignId']:checked").val();
     var array = value.split("::");
     parent.$("#assignId").val(array[0]);
 	 parent.$("#countryCode").val(array[1]);
 	 parent.$("#stateCode").val(array[2]);
 	 parent.$("#fromZip").val(array[3]);
 	 parent.$("#toZip").val(array[4]);
 	 parent.$("#modifyDate").val(array[5].substring(0,array[5].indexOf(" ")));
 	 parent.$("#modifiedName").val(array[6]);   
     parent.$('#zoneChoiceDialog').dialog('close');
}

function check() {
	  var filter_LIKES_countryCode = $("#filter_LIKES_countryCode").val();
	  $("#filter_LIKES_countryCode").attr("value",$.trim(filter_LIKES_countryCode));
	  return true;
}
</script>
</head>
<body>
<s:form action="sales_territories!selectZone"  name="docTempSearch" method="get" onsubmit="return check();">
<table border="0" cellpadding="0" cellspacing="0"
				class="General_table" align="center">
        <tr>
          <td>Country Name</td>
          <td>
          	<input  id="filter_LIKES_countryCode" name="filter_LIKES_countryCode" type="text" class="NFText" size="20" >
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
				        <th width="181">assignId</th>
				        <th width="240"> Country Name </th>
				        <th>State/Province</th>
				        </tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div style="width: 600px; height: 210px; overflow: scroll;">
						<table width="580" border="0" cellpadding="0" cellspacing="0"
							class="list_table" id="resultTable">
							<s:iterator value="zonePage.result" status="st">
					      	<s:if test="#st.odd">
					      	<tr>
					          <td width="46"><input type="radio" value="${assignId}::${countryCode}::${stateCode}::${fromZip}::${toZip}::${modifyDate}::${modifiedName}" id="${assignId}" name="assignId"/></td>
					          <td width="181">${assignId}</td>
					          <td width="240">${countryCode}</td>
					          <td>${stateCode}</td>
					        </tr>
					      	</s:if>
					      	<s:else>
					      	<tr>
					          <td class="list_td2"><input type="radio" value="${assignId}::${countryCode}::${stateCode}::${fromZip}::${toZip}::${modifyDate}::${modifiedName}" id="${assignId}" name="assignId"/></td>
					          <td class="list_td2">${assignId}</td>
					          <td class="list_td2">${countryCode}</td>
					          <td class="list_td2">${stateCode}</td>
					        </tr>
					      	</s:else>
					      </s:iterator>
						</table>
					</div>
					<div class="grayr">
						<jsp:include page="/common/db_pager.jsp">
							<jsp:param value="${ctx}/sales_territories!selectZone.action" name="moduleURL" />
						</jsp:include>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="4" class="botton_box">
					<input name="Submit" type="button" id="select_btn"
						class="style_botton" value="Select" onclick="addZone()"/>
					<input type="submit" name="Submit2" value="Cancel"
						class="style_botton"
						onclick="parent.$('#zoneChoiceDialog').dialog('close');" />
				</td>
			</tr>
		</table>
</body>
</html>
