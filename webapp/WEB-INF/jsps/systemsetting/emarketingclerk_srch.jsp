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
<script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>
</head>

<body class="content" style="background-image:none;">

<div class="scm">
<div class="title_content">
  <div class="title_new"><a href="javascript:void(0);" onclick="toggleShowMorea('total_title');" id="total_titleItem"><img src="${global_url}images/arrow1.jpg" /></a>Marketing Group</div>
</div>
<div class="search_box" id="total_title">
<div class="single_search"><form action="emarketing_group_srch!list.action" method="get" target="method_iframe">
<table cellspacing="0" cellpadding="0" border="0" class="General_table">
        <tbody><tr>
          <th>Marketing Group</th>
          <td width="120"><span class="single_search_css">
            <input type="text" class="NFText" name="filter_LIKES_groupName"/>
             
          </span></td>
          <th width="100">Marketing Clerk</th>
          <td width="120"><input type="text" class="NFText" name="filter_LIKES_clerkFunction"/>
            </td>
          <th width="60">Status</th>
          <td><select name="filter_LIKES_status">
            <option value="Active">Active</option>
            <option value="Inactive">Inactive</option>
          </select></td>
        </tr>
        <tr>
          <th align="center">Group Function</th>
          <td><select name="assign">
                  <s:iterator status="allcls" value="#request.allcls">
                      <option value="<s:property value="key"/>:<s:property value="value"/>"><s:property value="key"/></option>
                  </s:iterator>
          </select></td>
          <th>&nbsp;</th>
          <td>&nbsp;</td>
          <th>&nbsp;</th>
          <td align="center">&nbsp;</td>
        </tr>
        <tr>
          <td align="center" colspan="6"><input type="submit" class="search_input" value="Search" name="Search"/></td>
        </tr>
    </tbody></table></form>
</div>
</div>
 <div class="input_box" id="table_usa">
<table width="1010" border="0" cellspacing="0" cellpadding="0">
    <tr>
    <td>
    <div style="margin-right:17px;">
    <iframe id="method_iframe" name="method_iframe" src="emarketing_group_srch!list.action" width="100%" height="580" frameborder="0" scrolling="no"></iframe>
    </div>
    </td>
    </tr>
</table>

  </div>
</div>

<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
</script>

</body>
</html>
