<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/common/taglib.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}expland.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script type="text/javascript" src="${global_js_url}jquery.ajaxSelect.js"></script>
<script>
  $(function(){
	  $("#workCenterSel").ajaxSelect({
		  	 select1:"workCenterSel",
			 select2:"workGroupSel",
			 url:"manufacturing_clerks!changeWorkCenter.action",
			 param:"workCenterId",
			 listName:"workGroupList",
			 selectName:"name",
			 selectValue:"id"
	  });

  });

  function check() {
	  var filter_LIKES_groupName = $("#filter_LIKES_groupName").val();
	  $("#filter_LIKES_groupName").attr("value",$.trim(filter_LIKES_groupName));
	  return true;
  }

</script>
<script type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
<script type="text/javascript">var GB_ROOT_DIR = "./greybox/";</script>
<script type="text/javascript" src="${global_js_url}greybox/gb_scripts.js"></script>
</head>
<body class="content" style="background-image:none;">
<div class="scm">
<div class="title_content">
  <div class="title_new"><a href="javascript:void(0);" onclick="toggleShowMorea('total_title');" id="total_titleItem"><img src="${global_url}images/arrow1.jpg" /></a>Email Group</div>
</div>
<div class="search_box" id="total_title">
<div class="search_box_three">
<s:form action="email_group!list" target="result_iframe" name="docTempSearch" method="get" onsubmit="return check();">
<table border="0" cellpadding="0" cellspacing="0" class="General_table">
        <tr>
          <td width="120" align="right">Email Group Name</td>
          <td width="60">
          	<s:textfield id="filter_LIKES_groupName" name="filter_LIKES_groupName"  Class="NFText" size="20" />
          </td>
          <td width="120" align="right">Email Group Function</td>
          <td width="140">
          	<s:select id="groupFunSel"
          			  list="mailGroupDropdownList"
          			  name="filter_EQS_groupFunction"
          			  listKey="id"
          			  listValue="name"
          			  headerKey=""
          			  headerValue="please select...">
          	</s:select>
          </td>
          <td align="right">Status</td>
            <label id="txt_china" style="display:none;">Work Order No</label>
          <td>
          <s:select id="statusSel"
          			list="#{'ACTIVE':'ACTIVE','INACTIVE':'INACTIVE'}"
          			name="filter_EQS_status"
          			listKey="key"
          			listValue="value"
          			headerKey=""
          			headerValue="please select...">
          </s:select>
          </td>
          <th>&nbsp;</th>
          <td>&nbsp;</td>
          <td colspan="2" >&nbsp;</td>
      </tr>
        <tr>
          <td colspan="6" align="center"><input type="submit" name="Submit5" value="Search" class="search_input" /></td>
        </tr>
    </table>
    </s:form>

</div>
</div>
<input type="hidden" id="choiceOption"/>
<div class="input_box" style="height: 320px;">
<div class="content_box">
<iframe id="srch_route_iframe" name="result_iframe"
		src="email_group!list.action" width="100%" height="630"
		frameborder="0" scrolling="no"></iframe>
</div>
</div>



<div  class="button_box"><a href="javascript:void(0)" onclick="window.location.href='email_group!input.action'"><input type="button" name="Submit16" value="New" class="search_input"/></a></div>
</div>
</body>
</html>