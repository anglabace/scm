<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
</head>  
<frameset rows="97,*,0,0" frameborder="NO" border="0" framespacing="0"
	id="rootFrame">
	<frame src="privilege!top.action" name="topFrame" scrolling="NO"
		noresize id="topFrame">
	<frameset cols="220,8,*" frameborder="NO" border="0" framespacing="0"
		id="midFrame" name="midFrame">
		<frame src="privilege!menuListForUser.action" name="leftFrame"
			scrolling="auto" noresize id="leftFrame" title="leftFrame"/>
		<frame src="privilege!middle.action" name="adjust" scrolling="no"
			noresize marginwidth="6"/>
		<frame src="privilege!main.action" name="mainFrame" scrolling="yes"
			id="mainFrame" title="mainFrame"/>
	</frameset>
</frameset>

<body leftmargin="0" topmargin="0">
</body>
</html>