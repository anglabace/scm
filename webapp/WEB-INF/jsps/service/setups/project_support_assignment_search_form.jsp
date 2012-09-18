<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"  />

<script type="text/javascript" language="javascript" src="${global_js_url}SpryTabbedPanels.js"></script>
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript" src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/jquery.dialog.all.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="${global_js_url}searchattr.js"></script>
<script src="${global_js_url}searchForm.js" type="text/javascript"></script>
</head>
<body class="content" style="background-image:none;">
<div class="scm">
<div class="title_content">
  <div style="padding-left: 20px;color: #5579A6;vertical-align:middle;" ><img src="${global_url}images/arrow.jpg" style="width:16px;height:17px;vertical-align:middle;"  onclick="toggleShowMore_img(this,'search_box1');"/>&nbsp;Order Management</div>
</div>
<div class="search_box" id="search_box1" style="display:block;">
  <div class="search_box_two">
  <form action="project_support_assignment!searchInit.action" method="post" name="projectSupportAssignmentForm" id="projectSupportAssignmentForm"> 
    <table border="0" cellpadding="0" cellspacing="0" class="General_table">
      <tr>
        <th>Service Type Name</th>
        <td width="120"><select name="projectSupportAssignmentDto.serviceTypeName" style="width:132px;">
          <option>select all</option> 
          <s:iterator value="serviceClassificationList" var="serviceClassification">
          <c:if test="${projectSupportAssignmentDto.serviceTypeName == serviceClassification.clsId}">
          	<option value="${serviceClassification.clsId}" selected="selected">${serviceClassification.name}</option>
          </c:if>
          <c:if test="${projectSupportAssignmentDto.serviceTypeName  !=  serviceClassification.clsId}">
          	<option value="${serviceClassification.clsId}">${serviceClassification.name}</option>
          </c:if>
          </s:iterator>
        </select></td>
        <th>Project Manager</th>
        <td width="120"><input name="projectSupportAssignmentDto.projectSupport" value="${projectSupportAssignmentDto.projectSupport}" type="text" class="NFText" size="20" /></td>
         <th>&nbsp;</th>
        <td width="120">&nbsp;</td>
        <th>&nbsp;</th>
        <td width="120">&nbsp;</td>
      </tr>
      <tr>
        <td height="40" colspan="7" align="center"><input type="submit" name="Submit5" value="Search" class="search_input" />
          </td>
      </tr>
    </table>
    </form>
</div>
</div>
<div class="input_box">
	<div class="content_box">
		<iframe id="srchProjectSupport_iframe" name="srchProjectSupport_iframe" src="project_support_assignment!list.action?projectSupportAssignmentDto.projectSupport=${projectSupportAssignmentDto.projectSupport}&projectSupportAssignmentDto.serviceTypeName=${projectSupportAssignmentDto.serviceTypeName}" width="100%" height="600" frameborder="0" scrolling="no" ></iframe>
	</div>
</div>	
</div>
</body>
</html>