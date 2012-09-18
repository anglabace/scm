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
	<form action="${ctx }/login!judge.action" method="post" name="loginForm" id="loginForm" target="_top">
		<div class="login_content"><div class="login_title">Forget Password</div>
		  <table width="269" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		  	<td colspan="2">
		  		Please enter your user ID. We will email you a link to page where you can easily create a new password.
		  	</td>
		  </tr>
	        <tr>
	          <td width="31%" height="28" class="login_font">User ID:</td>
	          <td width="69%">
	          <div align="right">
	            <input name="userId" class="input1" id="userId" onFocus="this.className='input1-bor'" onBlur="this.className='input1'" value="${userId}" />
	          </div>
	          </td>
	        </tr>
	        <tr>
	          <td height="28" colspan="2"><div class="login_button">
			  <span class="login_ok" onclick="checkForm()">Continue</span>&nbsp;&nbsp;
	           </div></td>
	        </tr>
	      </table>
		</div>
	</form>	
</div>


<script>
	
	if (self.location != top.location) { 
		top.location.href = self.location.href; 
	}
	$("#userId").focus();
	
	function checkForm()
	{
		if($("#userId").attr( "value" ) == ""){
			alert('userId is required!');
			$("#userId").focus();
			return false;
		}
		else{
			$("#loginForm").submit();
		}
	}
	
	$("#userId").keydown(function (event)
	{
		if(event.keyCode==13){
			checkForm();
		}
	});
	function getCookie(c_name)
	{
	if (document.cookie.length>0)
	  {
	  c_start=document.cookie.indexOf(c_name + "=");
	  if (c_start!=-1)
	    { 
	    c_start=c_start + c_name.length+1 ;
	    c_end=document.cookie.indexOf(";",c_start);
	    if (c_end==-1) c_end=document.cookie.length;
	    return unescape(document.cookie.substring(c_start,c_end));
	    } 
	  }
	return "";
	}
</script>
<div class="login_copyright">&copy; 2008-2011 GenScript Corporation. All rights reserved.</div>
</body>
</html>