<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>custom date change log</title>
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<script src="${global_js_url}jquery/jquery.js" type="text/javascript"
			language="javascript"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><div style="overflow:scroll; width:100%;height:300px;">
    <table width="98%" border="0" cellspacing="0" cellpadding="0" class="list_table">
      <tr>
         <th width="12%">Original Cust Start Date</th>
         <th width="12%">Original Cust Complete Date</th>
         <th width="12%">New Cust Start Date</th>
         <th width="12%">New Cust Complete Date</th>
         <th width="12%">Modifier</th>
         <th width="12%">Change Date</th>
         <th>Update Reason</th>
         </tr>
 <s:if test="updateRequestLogList!=null">
      <s:iterator value="updateRequestLogList" status="st">
      <s:if test="#st.odd">
      <tr>
      <s:if test='field.equals("customStartDate")'>
      	<td width="12%">${originalValue}</td>
      	<td width="12%"></td>
      	<td width="12%">${newValue}</td>
      	<td width="12%"></td>
      	<td width="12%">${requestedByName}</td>
      	<td width="12%"><s:date name="requestDate" format="yyyy-MM-dd HH:mm"/></td>
      	<td>${reason}</td>
      </s:if>
      <s:elseif test='field.equals("customEndDate")'>
      	<td width="12%"></td>
      	<td width="12%">${originalValue}</td>
      	<td width="12%"></td>
      	<td width="12%">${newValue}</td>
      	<td width="12%">${requestedByName}</td>
      	<td width="12%"><s:date name="requestDate" format="yyyy-MM-dd HH:mm"/></td>
      	<td>${reason}</td>
      </s:elseif>
      </tr>
      </s:if>
      <s:else>
      <tr>
      <s:if test='field.equals("customStartDate")'>
      	<td class="list_td2">${originalValue}</td>
      	<td class="list_td2"></td>
      	<td class="list_td2">${newValue}</td>
      	<td class="list_td2"></td>
      	<td class="list_td2">${requestedByName}</td>
      	<td class="list_td2"><s:date name="requestDate" format="yyyy-MM-dd HH:mm"/></td>
      	<td class="list_td2">${reason}</td>
      </s:if>
      <s:elseif test='field.equals("customEndDate")'>
      	<td class="list_td2"></td>
      	<td class="list_td2">${originalValue}</td>
      	<td class="list_td2"></td>
      	<td class="list_td2">${newValue}</td>
      	<td class="list_td2">${requestedByName}</td>
      	<td class="list_td2"><s:date name="requestDate" format="yyyy-MM-dd HH:mm"/></td>
      	<td>${reason}</td>
      </s:elseif>
      </tr>
      </s:else>
      </s:iterator>
      </s:if>
    </table></div></td>
  </tr>
</table>  
</body>
</html>