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
  <div class="title_new"><a href="javascript:void(0);" onclick="toggleShowMorea('total_title');" id="total_titleItem"><img src="${global_url}images/arrow1.jpg" /></a>Shipping Method Setup</div>
</div>
<div class="search_box" style="font-weight:normal;" id="total_title">
<div class="single_search" style="font-weight:normal;">
<form action="shipping_method_srch.action" method="get" target="method_iframe">
<table width="900" border="0" cellpadding="0" cellspacing="0" class="General_table">
     <tr>
       <th width="128"><span class="single_search">Shipping Method Code</span></th>
       <td width="116"> <input name="filter_LIKES_methodCode" type="text" class="NFText" value="" size="15" /></td>
       <th width="82"><span class="single_search">
 Name</span></th>
       <td width="116"><span class="single_search">
         <input name="filter_LIKES_name" type="text" class="NFText" size="15" />
       </span></td>
       <th width="114"><span class="single_search">Shipping Carrier</span></th>
       <td width="344"><span class="single_search">
         <input name="filter_LIKES_carrier" type="text" class="NFText" size="25" />
       </span></td>
       </tr>
     <tr>
       <td height="40" colspan="6" align="center"><input type="submit" name="Submit5" value="Search" class="search_input" /></td>
     </tr>
   </table>
     
</form>
</div>
</div>	
</div>
<div class="input_box">
<table width="1010" border="0" cellspacing="0" cellpadding="0">
    <tr>
    <td>
    <div style="margin-right:17px;">
    <iframe id="method_iframe" name="method_iframe" src="shipping_method_srch.action" width="100%" height="450" frameborder="0" scrolling="no"></iframe>
    </div>
    </td>
    </tr>
</table>

  </div>
  <div class="button_box"><input type="submit" name="Submit52" value="New" class="search_input" onclick="window.location.href='shipping_method!createForm.action'" /></div>
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
</script>

</body>
</html>
