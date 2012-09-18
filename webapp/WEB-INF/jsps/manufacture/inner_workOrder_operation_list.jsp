<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/common/taglib.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scm</title>
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
    <td>
     <table width="100%" border="0" cellspacing="0" cellpadding="0" class="list_table2" id="operationListTable">
      <tr>
      	 <th width="5%">
			<div align="left">
				<input name="checkbox2" type="checkbox" id="check_all" />
			</div>
		 </th>
         <th width="8%">Work Order No</th>
         <th width="8%">US Sales Order No</th>
         <th width="8%">China Sales Order No</th>
         <th width="5%">Item No</th>
         <th>Work Group</th>
         <th width="10%">Operation</th>
         <th width="10%">Status</th>
         <th width="8%">Sch Start Date</th>
         <th width="8%">Sch Complete Date</th>
         <th width="8%">Cust Start Date</th>
         <th width="8%">Cust Complete Date</th>
         </tr>
      <s:if test="workOrderOperationList!=null">
      <s:iterator value="workOrderOperationList" status="st">
      <s:if test="#st.odd">
	      <tr>
	      	 <td width="5%">
				<input type="checkbox" value="${id}" id="${id}"
					name="delRouteId" onclick="choice(this);"/>
			 </td>
	      	 <td width="8%">${altOrderNo}</td>
	      	  <td width="8%">${srcSoNo}</td>
	         <td width="8%">${soNo}</td>
	         <td width="5%">${soitemNo}</td>
	         <td>${workGroupNames}</td>
	         <td width="10%">${operationName}</td>
	          <td width="10%" name="status">${status}</td>
	         <td width="8%">${exptdStartDate}</td>
	         <td width="8%">${exptdEndDate}</td>
	         <td width="8%" name="customStartDate">${customStartDate}</td>
	         <td width="8%" name="customEndDate">${customEndDate}</td>
	      </tr>
      </s:if>
      <s:else>
	      <tr>
	      		<td class="list_td2">
				<input type="checkbox" value="${id}" id="${id}"
					name="delRouteId" onclick="choice(this);"/>
				 </td>
	      	  <td class="list_td2">${altOrderNo}</td>
	      	    <td class="list_td2">${srcSoNo}</td>
	          <td class="list_td2">${soNo}</td>
	          <td class="list_td2">${soitemNo}</td>
	          <td class="list_td2">${workGroupNames}</td>
	          <td class="list_td2">${operationName}</td>
	          <td class="list_td2" name="status">${status}</td>
	          <td class="list_td2">${exptdStartDate}</td>
	          <td class="list_td2">${exptdEndDate}</td>
	          <td class="list_td2" name="customStartDate">${customStartDate}</td>
	         <td class="list_td2" name="customEndDate">${customEndDate}</td>
	      </tr>
      </s:else>
      </s:iterator>
      </s:if>
      </table>
      <td>
      </tr>
  <tr>
	  <td>
	  <div class="grayr">
			Total:<s:property value="workOrderOperationList.size()"/>
	  </div>
	  </td>
  </tr>
</table>    
</body>
</html>