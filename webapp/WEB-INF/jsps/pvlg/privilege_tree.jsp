<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link rel="STYLESHEET" type="text/css" href="${global_js_url}codebase/dhtmlxtree.css">
<script src="${global_js_url}codebase/dhtmlxcommon.js"></script>
<script src="${global_js_url}codebase/dhtmlxtree.js"></script>
<script src="${global_js_url}codebase/ext/dhtmlxtree_ed.js"></script>
<script src="${global_js_url}scm/user_security.js" language="javascript"></script>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
</head>
<body>
<table>
    <tr>
        <td width="534" valign="top">
            <div id="treeboxbox_tree" style="width:600px;padding:10px;height:300px;background-color:#f5f5f5;border :1px solid Silver;" class="list_box"></div>
            </td>
        <td valign="top"  style="padding-left:25">
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
        	<tr>
        		<td height="30"><span class="blue_price">Privilege name</span>
        		<input name="privilegeName" type="text" class="NFText" id="privilegeName" value="" />
        		
        		</td>
        	</tr>
        	<tr>
        		<td height="30">
        		<span class="blue_price">Privilege code</span>
        		<input name="privilegeCode" type="text" class="NFText" id="privilegeCode" value="" />
        		</td>
        	</tr>
        	<tr>
        		<td height="30">
		          <select id="privilegeType" onchange="linkDisplay( this );">
		          	<option value="">Please Choose privilege type</option>
		          	<option value="MENU">MENU</option>
		          	<option value="UI" selected>UI</option>
		          	<option value="EC">EC</option>
		          </select>
        		</td>
        	</tr>
        	<tr>
        		<td height="30">
        		<div id="privilegeAttrDiv" style="display:none;" >
        		<span class="blue_price">Privilege Link</span>
        		<input name="privilegeAttr" id="privilegeAttr" type="text" class="NFText" value="" />
        		</div>
        		<div id="privilegeActionDiv" style="display:block;" >
        		<span class="blue_price">Privilege Action</span>
        		<input name="privilegeAction" id="privilegeAction" type="text" class="NFText"  value="" />
        		</div>
        		</td>
        	</tr>
        	<tr>
        		<td height="30">
        			<input type="button" name="modNode" id="modNode" value="Modify" onclick="modPrivilege();" disabled />&nbsp;
        			<input type="button" name="delNode" id="delNode" value="Delete" onclick="delPrivilege();"/>&nbsp;
        			<input type="button" name="newNode" id="newNode" value="New" onclick="newPrivilege();"/>&nbsp;
        			<!--<input name="Input" type="button" value="Get ALl" onclick="getAllPrivilege();"/>-->
        		</td>
        	</tr>
    		<input name="privilegeNameH" type="hidden" class="NFText" id="privilegeNameH" value="" />
    		<input name="privilegeActionH" type="hidden" class="NFText" id="privilegeActionH" value="" />
    		<input name="privilegeAttrH" type="hidden" class="NFText" id="privilegeAttrH" value="" />
        </table></td>
    </tr>
</table>
<div class="button_box">
	<input type="button" value="Save" class="search_input" onclick="savePrivilege() ;" />
	<input name="button2" type="button" value="Cancel" class="search_input" onclick="cancelPrivilege() ;" style="cursor:pointer" mce_style="cursor: pointer"/>
</div>
<script>

var tree = new dhtmlXTreeObject("treeboxbox_tree", "100%", "100%", 0 ); 

tree.setSkin('dhx_skyblue');
tree.setImagePath("${global_js_url}codebase/imgs/csh_bluebooks/");
//tree.enableCheckBoxes(1);
//tree.enableThreeStateCheckboxes(true);
tree.loadXML("${ctx}/xml/privilege.xml");
//tree.enableItemEditor( 1 ) ;
//tree.editItem( 1 ) ;
tree.setOnDblClickHandler( "privilegeModForm" ) ;
tree.setOnClickHandler( "judgeIsEdit" ) ;

var privilegeNameObj = document.getElementById( "privilegeName" ) ;
var privilegeCodeObj = document.getElementById( "privilegeCode" ) ;
var privilegeTypeObj = document.getElementById( "privilegeType" ) ;
var privilegeAttrDivObj = document.getElementById( "privilegeAttrDiv" ) ;
var privilegeAttrObj = document.getElementById( "privilegeAttr" ) ;

var privilegeNameHObj = document.getElementById( "privilegeNameH" ) ;
var privilegeAttrHObj = document.getElementById( "privilegeAttrH" ) ;
var privilegeActionHObj = document.getElementById( "privilegeActionH" ) ;

var privilegeActionDivObj = document.getElementById( "privilegeActionDiv" ) ;
var privilegeActionObj = document.getElementById( "privilegeAction" ) ;

var modNodeObj = document.getElementById( "modNode" ) ;
var delNodeObj = document.getElementById( "delNode" ) ;
var newNodeObj = document.getElementById( "newNode" ) ;

var privilegeEditStatus = 0 ;

/**
 * Get All Privilege 
 * The function testing ....
 */
function getAllPrivilege()
{
	document.write( tree.getAllSubItemsProperty( 'privilege' ) ) ;
	//alert( tree.getAllSubItemsProperty( '001' ) ) ;
}

function linkDisplay( theSelect )
{
	if( theSelect.options[theSelect.selectedIndex].value == "MENU" )
	{
		privilegeAttrDivObj.style.display = "block" ;
		privilegeActionDivObj.style.display = "none" ;
	}
	else
	{		
		privilegeAttrDivObj.style.display = "none" ;
		privilegeActionDivObj.style.display = "block" ;
	}
}

function privilegeModForm()
{
	modNodeObj.disabled = false ;
	delNodeObj.disabled = true ;
	newNodeObj.disabled = true ;
	
	itemText = tree.getSelectedItemText() ;
	itemId = tree.getSelectedItemId() ;
	itemType = tree.getUserData( itemId , "privilegeType" ) ;
	itemAttr = tree.getUserData( itemId , "privilegeAttr" ) ;
	itemAction = tree.getUserData( itemId , "privilegeAction" ) ;
	
	//alert( tree.getUserData( itemId , "privilegeAttr" ) ) ;

	itemText = itemText.replace( "("+itemId+")" , "" ) ;

	privilegeNameObj.value = itemText ;
	privilegeCodeObj.value = itemId ;
	privilegeAttrObj.value = itemAttr ;
	
	
	privilegeNameHObj.value = itemText ;
	privilegeAttrHObj.value = itemAttr ;

	for( var i = 0 ; i < privilegeTypeObj.length ; i ++ )
	{
		if( privilegeTypeObj.options[i].value == itemType )
		{
			privilegeTypeObj.options[i].selected = true ;
			if( itemType == "MENU" )
			{
				privilegeAttrDivObj.style.display = "block" ;
				privilegeActionDivObj.style.display = "none" ;
			}
			else
			{
				privilegeActionObj.value = itemAction ;
				privilegeActionHObj.value = itemAction ;
				linkDisplay( privilegeTypeObj ) ;
			}
		}
	}

	privilegeCodeObj.disabled = true ;
	privilegeTypeObj.disabled = true ;
	privilegeAttrObj.disabled = false ;
}

function judgeIsEdit()
{
	if( (privilegeNameHObj.value == privilegeNameObj.value & privilegeAttrHObj.value == privilegeAttrObj.value &privilegeActionHObj.value == privilegeActionObj.value))
	{
		replaySetup() ;
	}
	else
	{
		if( window.confirm( "Do you want to edit this item ?" ) )
		{
			modPrivilege() ;
		}
	}
}

function replaySetup()
{	
	modNodeObj.disabled = true ;
	delNodeObj.disabled = false ;
	newNodeObj.disabled = false ;
	
	privilegeCodeObj.disabled = false ;
	privilegeTypeObj.disabled = false ;
	privilegeAttrObj.disabled = false ;
	
	privilegeNameObj.value = "" ;
	privilegeCodeObj.value = "" ;
	privilegeTypeObj.options[0].selected = true ;
	privilegeAttrObj.value = "" ;
	privilegeActionObj.value = "" ;
	privilegeAttrDivObj.style.display = "none" ;
	privilegeActionDivObj.style.display = "none" ;
	privilegeEditStatus = 0 ;
	
	privilegeNameHObj.value = "" ;
	privilegeAttrHObj.value = "" ;
	privilegeActionHObj.value = "" ;
}
var global_url = "${global_url}" ;
</script>

</body>
</html>
