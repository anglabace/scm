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
	  var filter_LIKES_ruleName = $("#filter_LIKES_ruleName").val();
	  $("#filter_LIKES_ruleName").attr("value",$.trim(filter_LIKES_ruleName));
	  var filter_EQI_ruleId = $("#filter_EQI_ruleId").val();
	  if($.trim(filter_LIKES_ruleName)!=''&&isNaN($.trim(filter_LIKES_ruleName))) {
		  alert("Please enter correct ruleId!");
		  return false;
	  }
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
  <div class="title_new"><a href="javascript:void(0);" onclick="toggleShowMorea('total_title');" id="total_titleItem"><img src="${global_url}images/arrow1.jpg" /></a>&nbsp;Assignment Rules</div>
</div>
<div class="search_box" id="total_title">
  <div class="search_box_two">
  <s:form action="assignment_rules!list" target="result_iframe" name="assignmentRulesSearch" method="get" onsubmit="return check();">
  	  <table border="0" cellpadding="0" cellspacing="0" class="General_table">
      <tr>
        <th>Rule ID</th>
        <td><input name="filter_EQI_ruleId" id="filter_EQI_ruleId" type="text" class="NFText" size="20" /></td>
        <th>Rule Name</th>
        <td><input name="filter_LIKES_ruleName" id="filter_LIKES_ruleName" type="text" class="NFText" size="20" /></td>
        <th>Status</th>
        <td width="387"><s:select id="status_sel"  
	          			list="#{'ACTIVE':'ACTIVE','INACTIVE':'INACTIVE'}" 
	          			name="filter_EQS_status" 
	          			listKey="key" 
	          			listValue="value"
	          			headerKey="" 
						headerValue="Please select...">
	          </s:select></td>
      </tr>
      <tr>
        <td height="40" colspan="6" align="center"><input type="submit" name="Submit5" value="Search" class="search_input" /></td>
      </tr>
    </table>
  </s:form>

</div>
</div>
<s:hidden id="choiceOption"></s:hidden>
        <div class="input_box">
		  <div class="content_box">
         <iframe id="srch_route_iframe" name="result_iframe"
		src="assignment_rules!list.action" width="100%" height="400"
		frameborder="0" scrolling="no"></iframe>
		</div>
  </div>
  <div  class="button_box">
  <a href="javascript:void(0)" onclick="window.location.href='assignment_rules!load.action'"><input type="button" name="Submit193" value="New Rule" class="search_input"  style="background-image:url(${global_url}images/input_searchbg2.jpg) ; width:120px;" /></a>
</div>	
</div>	
</body>
</html>