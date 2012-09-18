<%@ include file="/common/taglib.jsp"%>
<!doctype html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<base href="${global_url}" />
<link href="${global_css_url}dhtmlxtree.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}gb_style.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css?v=1" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<script  src="${global_js_url}codebase/dhtmlxcommon.js"></script>
<script  src="${global_js_url}codebase/dhtmlxtree.js"></script>
<script  src="${global_js_url}codebase/ext/dhtmlxtree_ed.js"></script>
<script  src="${global_js_url}scm/user_security.js" language="javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<link href="${global_css_url}stylesheet/scm.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
body {
	width:400px;  
}
-->
</style>
</head>
<html>
<body>
<div id="treeboxbox_tree" style="width:950px;padding:10px;height:380px;" ></div>
<script>
	tree = new dhtmlXTreeObject("treeboxbox_tree", "100%", "100%", 0);
	tree.setSkin('dhx_skyblue');
	tree.setImagePath("${global_url}js/codebase/imgs/csh_bluebooks/");
	tree.enableCheckBoxes(1);
	tree.enableThreeStateCheckboxes(true);
	tree.loadXML("${ctx}/xml/privilege.xml", false, false);
</script>
<input type="hidden" id="returnValue" value="" />
<script>
//Parent frame get privilege by check in this page

function getPrivilegeValue()
{
	var treeCode = tree.getAllChecked() ;
	var tmpVal = "" ;
	
	if( treeCode != "" )
	{
		treeCodeArr = treeCode.split( "," ) ;
		for( var i = 0 ; i < treeCodeArr.length ; i ++ )
		{
			tmpVal += tree.getUserData( treeCodeArr[i] , 'privilegeId' ) + "," ;	
		}
		tmpVal = tmpVal.substr( 0 , ( tmpVal.length - 1  )) ;
		
		$('#returnValue').attr( 'value' , 'privilegeId='+tmpVal ) ;			
	}
	else
	{
		$('#returnValue').attr( 'value' , 'privilegeId=' ) ;			
	}

	return $('#returnValue').val() ;
}
$('body').ready(
	function()
	{
		tree.openItem( '${parentCode}' ) ;
		tree.setCheck( '${privilegeCode}' , 1 ) ;
	}
);
$('body').ready(
		//userListPrivilegeAcl

		function()
		{
			<c:if test="${request.sign == 2}">
			<s:iterator value="userListPrivilegeAcl">
			tree.openItem( '${parentCode}') ;
			tree.setCheck( '${privilegeCode}' , 1 ) ;
			</s:iterator>
			</c:if>
			<c:if test="${request.sign == 3}">
			<s:iterator value="roleListPrivilegeAcl">
			tree.openItem( '${parentCode}') ;
			tree.setCheck( '${privilegeCode}' , 1 ) ;
			</s:iterator>
			</c:if>
		}
	);
</script>
</body></html>