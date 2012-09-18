<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base id="myBaseId" href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<input type="hidden" value="${custNo}" id="custNo" />
	<iframe
		src="${ctx}/customer/customer!showRedeemPointHistory.action?custNo=${custNo}"
		align="top" height="400" width="780" frameborder="0" scrolling="yes"></iframe>
	<iframe
		src="${ctx}/customer/customer!showAmazonPointHistory.action?custNo=${custNo}"
		align="top" height="300" width="780" frameborder="0" scrolling="yes"></iframe>
	<div align="center">
		<input type="submit" name="Submit3" value="Close" class="style_botton"
			onclick="javascript:parent.$('#show_Redeem_Point_History_Dialog').dialog('close');" />
	</div>
</body>
</html>
