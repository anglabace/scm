<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>User Management</title>
<%@ include file="/common/taglib.jsp"%>
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"	type="text/css" />
<link href="${global_css_url}greybox/gb_styles.css" rel="stylesheet"	type="text/css" media="all" />
<link href="${global_css_url}tab-view.css" rel="stylesheet"	type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<script type="text/javascript">
	var GB_ROOT_DIR = "./greybox/";
</script>
<script type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
<script type="text/javascript"
	src="${global_js_url}greybox/gb_scripts.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script language="JavaScript" type="text/javascript">
	function cc(e) {
		var a = document.getElementsByName("uu1");
		for ( var i = 0; i < a.length; i++)
			a[i].checked = e.checked;
	}
	function row(e) {
		var a = document.getElementsByName("row1");
		for ( var i = 0; i < a.length; i++)
			a[i].checked = e.checked;
	}
	
	function toggleShowMore_img(obj,divID){
		var oId = document.getElementById(divID);
		if (obj.src.indexOf('arrow.jpg') > 0){
			obj.src="${global_url}images/title_bgewe.jpg";
			oId.style.display = "none"; 
		}else{
			obj.src="${global_url}images/arrow.jpg";
			oId.style.display = "block"; 
		}
	}
</script>

</head>

<body class="content">

<div class="scm">
<div class="title_content">
  <div style="padding-left: 20px;color: #5579A6;vertical-align:middle;"><img src="${global_url}images/arrow.jpg" style="width:16px;height:17px;vertical-align:middle;" onclick="toggleShowMore_img(this,'search_box1');"/>&nbsp;&nbsp;Package Information - # ${eDto.employeeId}</div>
</div>

<div class="input_box">
	<div class="content_box">
<div class="search_order">&nbsp;<iframe src="shipments!shipSearch.action" id="searchBody" name="searchBody"  width="100%" height="180" frameborder="0" scrolling="no"></iframe></div>
</div></div>
<div id="dhtmlgoodies_tabView1">
<iframe src="shipments!shipList.action" name="shipInfo" id="appInfo" width="100%" height="300px" frameborder="0" scrolling="yes"></iframe></div>
</div>

</body>
</html>