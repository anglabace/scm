<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${global_css_url}login.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
</head>
<body>
<div class="login_logo">
	<a href="http://www.genscript.com">
		<img src="${global_image_url}genscript_logo.jpg" width="168" height="54" border="0" />
	</a>
</div>
<div class="login_contain">
<div class="warning_box">

	<div class="left_warning"></div>
	<div class="right_warning">
	  <div class="title">${message}</div>
	  <s:if test='code=="101"'>
	   <li>We have sent an e-mail to you with the following subject line "GenScript Password Assistance." </li>
	  <li>When you click the link that appears in the body of the message, you will be taken to a page where you can create your new password.</li>
	  </s:if>
	  <div class="warning_button">
	  
	  <a class="login_ok" href="#" onclick="location='/scm/'">Continue</a>
    </div>
	</div>
</div>
</div>
</body>
</html>