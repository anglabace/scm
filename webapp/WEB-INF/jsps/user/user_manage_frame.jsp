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
</script>

</head>

<body class="content">

<div class="scm">
<div class="title_content">
<div class="title">User Management</div>
</div>

<div class="search_box">
<div class="search_order">&nbsp; <iframe src="user!userSearch.action" id="searchBody" allowTransparency="true" name="searchBody"  width="100%" height="72" frameborder="0" scrolling="no"></iframe></div>
</div>
<div id="dhtmlgoodies_tabView1">
<div class="dhtmlgoodies_aTab"><iframe src="user!manageList.action" name="userInfo"  id="userInfo" width="100%" height="100%" frameborder="0" scrolling="no"></iframe></div>
<div class="dhtmlgoodies_aTab"><iframe src="user!RoleList.action" name="roleInfo" width="100%" height="100%" frameborder="0" scrolling="no"></iframe></div>
</div>
</div>
<script type="text/javascript">
	initTabs('dhtmlgoodies_tabView1', Array('User Management',
			'Role Management'), 0, 998, 420);

	document.getElementById("User Management").onclick = function() {
		var paramIdObj = $('#userSearchBody').contents().find("#paramId");
		var paramNameObj = $('#userSearchBody').contents().find("#paramName");
		var paramSearchButtonObj = $('#userSearchBody').contents().find(
				"#searchButton");
		var paramNewButtonObj = $('#userSearchBody').contents().find(
				"#newButton");

		paramIdObj.text("User ID");
		paramNameObj.text("User Name");

		paramSearchButtonObj.click(function() {
			var formParam;
		});

		paramNewButtonObj.click(function() {
			location = 'userNew';
		});
	}
	document.getElementById('Role Management').onclick = function() {
		
		$('#searchBody').attr('src', 'user!roleSearch.action');
	}
	document.getElementById('User Management').onclick = function() {
		$('#searchBody').attr('src', 'user!userSearch.action');
	}
</script>



</body>
</html>