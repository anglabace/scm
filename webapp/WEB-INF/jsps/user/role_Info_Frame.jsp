<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Role list</title>
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
</head>
<body class="content">

<div class="scm">
<div class="title_content">
<div class="title">New Role</div>
</div>
<div class="search_box">
<div class="search_order">&nbsp;</div>
<iframe src="user!roleSearch.action" id="searchBody" name="searchBody" allowTransparency="true" width="100%" height="72" frameborder="0" scrolling="no"></iframe> <br /></div>
<div id="dhtmlgoodies_tabView1">
<div class="dhtmlgoodies_aTab"><iframe id="roleInfo" name="roleInfo" src="user!showRoleInf.action" width="100%" height="100%" frameborder="0" scrolling="no"></iframe></div>
<div class="dhtmlgoodies_aTab"><iframe id="roleListId" name="roleListId" src="user!getSearchUserList.action" width="100%" height="100%" frameborder="0" scrolling="no"></iframe></div>
<div class="dhtmlgoodies_aTab"><iframe id="privilegeId" name="privilegeId" src="user!roleRedirect_acl.action" width="985" height="431"  frameborder="0" scrolling="yes"></iframe></div>
</div>
<div   class="button_box">
<input type="button" name="saveButton" id="saveButton" value="Save" class="search_input" style="cursor: pointer" mce_style="cursor: pointer" /> 
<input type="button" name="closeButton" value="Cancel" class="search_input" style="cursor: pointer" mce_style="cursor: pointer" /> 
<input type="button" name="deleteButton" value="Delete" class="search_input"style="cursor: pointer" mce_style="cursor: pointer" />
</div>
</div>
<script type="text/javascript">
	initTabs('dhtmlgoodies_tabView1', Array('Role Information',
			'&nbsp;&nbsp;Users&nbsp;&nbsp;', '&nbsp;&nbsp;ACL&nbsp;&nbsp;'), 0,
			998, 420);

	var global_url = "<!-- {$global_url} -->";

	$('#saveButton').click(function() {
		var roleData, rolePrivilege;
		var postParam;

		roleData = getRoleData();
		rolePrivilege = getRoleOfPrivilege();
		postParam = roleData + "&" + rolePrivilege;
		$.ajax( {
			type : "POST",
			url : "user!saveRole.action",
			data : postParam,
			dataType : "xml",
			error : function() {
				alert("The request send fail!");
			},
			success : function(xml) {
				$(xml).find('root').each(function() {
					result = $(this).children("result").text();
					if (result == 1) {
						alert("The operation succeed!");
						window.location.reload();
					} else {
						alert("The operation fail!");
					}
				});
			}
		});
	});

	$('#closeButton').click(function() {
		if (windown.confirm('Do you want to cancel the operation ?')) {

		}
	});

	$('#deleteButton').click(function() {

	});

	function getRoleData() {

		return $('#roleInfo').contents().find('#roleDataForm').serialize();

	}

	function getRoleOfPrivilege() {
		privilegeStr = document.getElementById("privilegeId").contentWindow
				.getPrivilegeValue();
		/*
		$('#privilegeId').ready(function()
		{
			//Get privilege of tree value
			$('#privilegeId').contents().find('#button1').click() ;
			privilegeStr = $('#privilegeId').contents().find('#returnValue').attr( 'value' ) ;
			//alert( privilegeStr ) ;		
		});
		 */
		return privilegeStr;
	}
</script>

</body>
</html>