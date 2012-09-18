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
<script src="${global_js_url}jquery/jquery.js" type="text/javascript" language="javascript"></script>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
</head>
<body>
<table>
    <tr>
        <td width="534" valign="top">
            <div id="treeboxbox_tree" style="width:600px;padding:10px;height:300px;background-color:#f5f5f5;border :1px solid Silver;" class="list_box"></div>
            </td>
        <td valign="top"  style="padding-left:25">
	        <div id="ActionTreeboxbox_tree" style="width:600px;padding:10px;height:300px;background-color:#f5f5f5;border :1px solid Silver;" class="list_box">
	    	<table width="50%">	
	    	<tr><td width="10%">Action Name</td><td>namespace-menthod,method,...</td></tr>
	    	<s:if test="actionNameAndMethodMap != null">
	    	<form id="actionNameForm">
	   		<s:iterator  value="actionNameAndMethodMap.keySet()" id="keyactionName">
	   		<tr><td width="10%"><input type="checkbox" name="actionName" id="actionName" value="${keyactionName}"/><s:property value="keyactionName"/></td>
				<td>
					<s:iterator value="actionNameAndMethodMap.get(#keyactionName)">
						<s:property/>
					</s:iterator>
				</td>
			</tr>	
			</s:iterator>
			</form>
			</s:if>
	    	</table>
	    	</div>
    		<input name="privilegeNameH" type="hidden" class="NFText" id="privilegeNameH" value="" />
    		<input name="privilegeActionH" type="hidden" class="NFText" id="privilegeActionH" value="" />
    		<input name="privilegeAttrH" type="hidden" class="NFText" id="privilegeAttrH" value="" />
        </td>
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
tree.loadXML("${ctx}/xml/privilege.xml");
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

var global_url = "${global_url}" ;

	function savePrivilege () {
		var actionNames = "";
		var temp = document.getElementById("actionNameForm").actionName; 
		for(var i=0;i<temp.length;i++) {
			if(temp[i].checked) {
				actionNames += temp[i].value+"-"; 
			}
		}
		if (actionNames == "") {
			alert("At less than one to selected");
			return;
		}
		//module Code
		itemId = tree.getSelectedItemId();

		$.ajax({
			type: "POST",
			url: global_url + "privilege!checkModuleCode.action",
			data: "moduleCode="+itemId,
			dataType: 'json',
			success: function(data, textStatus){
				if(data.rtnMessge){
					alert("Please select smallest MENU!");	
				}else{
					$.ajax({
						type: "POST",
						url: global_url + "privilege!saveMappingUI.action",
						data: "moduleCode="+itemId+"&actionNames="+actionNames,
						dataType: 'json',
						success: function(data, textStatus){
							if(data.hasException){
								if (data.rtnMessage != undefined) {
					 	           	alert("Save failure!"+data.rtnMessage);			
							   	} else {
							   		alert("Save failure!");
								}	
							}else{
								if (data.rtnMessage != undefined) {
					 	           	alert("Save failure!"+data.rtnMessage);			
							   	} else {
							   		alert("Save success!");
									location.href = global_url+"privilege/privilege!mappingUI.action";
								}	
							}
						},
						error: function(xhr, textStatus){
						   alert("failure");
						   return;
						}
					});
				}
			},
			error: function(xhr, textStatus){
			   alert("Save failure");
			   return;
			}
		});
		
	} 
</script>

</body>
</html>
