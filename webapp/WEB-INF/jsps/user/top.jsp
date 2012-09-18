<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>11</title>
<link href="${global_css_url}top.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="JavaScript"><!--
	var $=function(id) {
		return document.getElementById(id);
	}

	function setTopLink(position_str){
		var str = '<span class="positon_font">You Position:</span><a href="${ctx}/privilege!main.action?out_lock=true" target="mainFrame">Home</a>';
		str = str + '<span id="navigation" class="positon_font">'+position_str+'</span>';
		$('position').innerHTML =str;
	}
--></script>
</head>

<body>
<div class="top_message" id="banner">
	<div  class="logo"><img src="${global_image_url}genscript_logo3777.jpg" width="174" height="58" /></div>
	<div class="account">
		<div class="message"><a href="#">My Account</a><a href="#">Help</a>
		<a href="javascript:void(0);" onclick="return openWin();">About</a> </div>
	
		<div class="welcome">Welcome, <span class="name">${user.loginName} </span><a href="<s:url action='logout'/>" target="_parent">Log Out</a></div>
	</div>
</div>

<div class="search_bar" id="search_bar">		
	<div class="Search">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	    <tr>	
	      <td valign="top"><input name="" type="text"  id="search_scm" disabled="disabled"/></td>
	      <td><input type="image"  src="${global_image_url}Search_scm.jpg" /></td>	
	    </tr>
	  </table>
	</div>
	<div class="position" id="position">
		<span class="positon_font">You Position:</span><a href="privilege!main.action?out_lock=true" target="mainFrame">Home</a> 
		<span id="navigation" class="positon_font"></span>
	</div>
</div>
<script language="javascript">
function openWin()
{
	var popstr='toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,width=600,height=300,top='+(window.screen.availHeight-300)/2+',left='+(window.screen.availWidth-600)/2;
	if(jQuery.browser.msie && jQuery.browser.version>=7)
	{
		var wFeature = "dialogHeight:300px;dialogWidth:600px;status:no;scroll:no;resizable:no;center:yes;";
		window.showModalDialog("${ctx}/util/about.jsp","",wFeature);
	}else {
		window.open('${ctx}/util/about.jsp','blueidea',popstr);
	}
	return false;
}
</script>
</body>
</html>
