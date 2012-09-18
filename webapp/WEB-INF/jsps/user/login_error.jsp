<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglib.jsp"%>
<html>

	<head>
    <title>Error</title>
	</head>

	<body>
		<h4>Error page!</h4> 	
		 
		<s:property value="userId"/>
		<br>
		<s:property value="result.wsException.code"/>
		<s:property value="result.wsException.message"/>
		<s:property value="result.wsException.messageDetail"/>
	</body>
	
</html>