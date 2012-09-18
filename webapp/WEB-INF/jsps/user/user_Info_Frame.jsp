<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}gb_style.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css?v=1" rel="stylesheet" type="text/css" />
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
  <div class="title">User Information</div>
</div>		
<div class="search_box">
 <div class="search_order">&nbsp;
 </div><iframe src="user!userSearch.action" id="searchBody" allowTransparency="true" name="searchBody"  width="100%" height="72" frameborder="0" scrolling="no"></iframe>
 <br />
 </div>
 <s:hidden name="choiceVal" id="choiceRoleVal"></s:hidden>
 <div id="dhtmlgoodies_tabView1">	
    <div class="dhtmlgoodies_aTab" style="width:">
    	<iframe name="userInfo" style="margin:0px" id="userInfo" src="user!showUserInf.action?userId=${userId}" width="98%" height="100%" frameborder="0" scrolling="no" ></iframe>
    </div>
    <div class="dhtmlgoodies_aTab">
    	<iframe id="roleListId" src="user!RoleList.action?userId=${userId}" width="100%" height="100%"  scrolling="yes" frameborder="0"  ></iframe>
    </div>
    <div class="dhtmlgoodies_aTab" >
    	<iframe id="privilegeId" name="privilegeId" src="user!userRedirect_acl.action?userId=${userId}" width="886" height="432"  scrolling="yes"  frameborder="0"></iframe>
    </div>
   
 </div>
 <div class="button_box">
 <input type="button" name="saveButton" id="saveButton" value="Save" class="search_input" style="cursor:pointer" mce_style="cursor: pointer" />
 <input  type="button" name="closeButton" value="Cancel"  class="search_input" style="cursor:pointer" mce_style="cursor: pointer" />
<input type="button" name="deleteButton"  value="Delete" class="search_input" style="cursor:pointer" mce_style="cursor: pointer"/>
</div></div>
<script type="text/javascript"><!--
initTabs('dhtmlgoodies_tabView1',Array('User Information','&nbsp;&nbsp;Role&nbsp;&nbsp;','&nbsp;&nbsp;ACL&nbsp;&nbsp;'),0,998,420);

var global_url = "${global_url} " ;

$('#saveButton').click(
	function()
	{
		var userData , userRole , userPrivilege ;
		var postParam ;
		
		userData = getUserData() ;
		userRole = getUserOfRole() ;
		userPrivilege = getUserOfPrivilege() ;
		postParam = userData+"&"+userRole+"&"+userPrivilege ;
		//return ;
		$.ajax
		({
			type: "POST",
			url: "user!saveUser.action",
			data: postParam ,
			dataType: "xml" ,
			error:function(xml)
			{
				alert( "The request send fail!" ) ;
			},
			success: function( xml )
			{
				$(xml).find( 'root' ).each(
				function()
				{
					result = $(this).children( "result" ).text() ;
					if( result == 1 )
					{
						alert( "The operation succeed!" ) ;
						window.location.reload();
					}
					else if( result == 2 )
					{
						alert( "EmployeeId must be numberic !" ) ;
					}
					else if(result == 3){
						alert("Login name and employee name can't repeat !");
					}
					else
					{
						alert( "The operation fail!" ) ;
					}
				});
			} 
		});
	}
) ;

/*$('#deleteButton').click(
	function()
	{
		var userData , userRole , userPrivilege ;
		var postParam ;
		
		userData = getUserData() ;
		userRole = getUserOfRole() ;
		userPrivilege = getUserOfPrivilege() ;
		
		postParam = userData+"&"+userRole+"&"+userPrivilege ;
		
		$.ajax
		({
			type: "POST",
			url: global_url+"user/user_managerment/ajaxDelUserInfo",
			data: postParam ,
			dataType: "xml" ,
			error:function()
			{
					alert( "The request send fail!" ) ;
			},
			success: function( xml )
			{
				$(xml).find( 'root' ).each(
				function()
				{
					result = $(this).children( "result" ).text() ;
					if( result == 1 )
					{
						alert( "The operation succeed!" ) ;
					}
					else{
							alert( "The operation fail!" ) ;
						}
				});
			}
		});
	}
) ;
*/
function getUserData()
{
	//Get user data
	return $('#userInfo').contents().find( '#userDataForm' ).serialize() ;
	//alert( $('#userInfo').contents().find( '#userDataForm' ).serialize() ) ;
}

function getUserOfRole()
{
	//Get Role that user belong to
	//return $('#roleListId').contents().find( '#userOfRoleForm' ).serialize() ;
	roleStr = "roleIds="+$("#choiceRoleVal").val() ;
	return roleStr ;
	//alert( $('#roleListId').contents().find( '#userOfRoleForm' ).serialize() ) ;
}

function getUserOfPrivilege()
{
	privilegeStr = document.getElementById("privilegeId").contentWindow.getPrivilegeValue();
	/*
	$('#privilegeId').ready(function()
	{
		//Get privilege of tree value
		$('#privilegeId').contents().find('#button1').click() ;
		privilegeStr = $('#privilegeId').contents().find('#returnValue').attr( 'value' ) ;
		//alert( privilegeStr ) ;		
	});
	*/
	return privilegeStr ;
}

</script>

</body>
</html>