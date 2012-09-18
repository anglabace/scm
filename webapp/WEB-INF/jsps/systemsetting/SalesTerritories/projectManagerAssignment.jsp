<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/common/taglib.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}expland.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>

<script   language="JavaScript" type="text/javascript"> 
var GB_ROOT_DIR = "./greybox/";
function check() {
	  var filter_LIKES_resourceName = $("#filter_LIKES_resourceName").val();
	  $("#filter_LIKES_resourceName").attr("value",$.trim(filter_LIKES_resourceName));
	  return true;
}
</script>
<script type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/gb_scripts.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>

</head>
<body class="content" style="background-image:none;">
<div class="scm">
<div class="title_content">
  <div class="title_new"><a href="javascript:void(0);" onclick="toggleShowMorea('total_title');" id="total_titleItem"><img src="${global_url}images/arrow1.jpg" /></a>&nbsp;Project Manager Assignment</div>
</div>
<div class="search_box" id="total_title">
  <div class="search_box_two">
  <s:form action="project_manager_assignment!list" target="result_iframe" name="projectManagerAssignmentSearch" method="get" onsubmit="return check();">
  	  <table border="0" cellpadding="0" cellspacing="0" class="General_table">
      <tr>
        <th>Service Type Name</th>
        <td>
        <s:if test="serviceClassificationList!=null">
        	<s:select list="serviceClassificationList" 
        			  id="filter_EQI_serviceType"
        			  name="filter_EQI_serviceType"
        			  listKey="clsId"
        			  listValue="name"
        			  headerKey=""
        			  headerValue=""></s:select>
        </s:if>
        <s:else>
        	<select id="filter_EQI_serviceType" name="filter_EQI_serviceType">
        		<option value=""></option>
        	</select>
        </s:else>
        </td>
        <th>Project Manager</th>
        <td width="120">
        	<input name="filter_LIKES_resourceName" id="filter_LIKES_resourceName" type="text" class="NFText" size="20">
        </td>
        </tr>
      <tr>
        <td height="40" colspan="4" align="center"><input type="submit" name="Submit5" value="Search" class="search_input" /></td>
      </tr>
    </table>
  </s:form>

</div>
</div>
<s:hidden id="choiceOption"></s:hidden>
  <div class="input_box">
		  <div class="content_box">
         <iframe id="srch_route_iframe" name="result_iframe"
		src="project_manager_assignment!list.action" width="100%" height="400"
		frameborder="0" scrolling="no"></iframe>
		</div>
  </div>
  <div  class="button_box">
  <a href="javascript:void(0)" onclick="window.location.href='project_manager_assignment!load.action'"><input type="button" name="Submit52" value="New" class="search_input"/></a>
</div>	
</div>	
</body>
</html>