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
<!-- genscript_logo.jpg -->
	<a href="http://www.genscript.com">
		<img src="${global_image_url}genscript_logo4.jpg"  border="0" />
	</a>
</div>

<div class="login_contain">
	<form action="${ctx }/login.action" method="post" name="loginForm" id="loginForm" target="_top">
		<div class="login_content"><div class="login_title">Sign into GenScript</div>
		  <table width="269" border="0" align="center" cellpadding="0" cellspacing="0">
	        <tr>
	          <td width="31%" height="28" class="login_font">User ID:</td>
	          <td width="69%">
	          <div align="right">
	            <input name="userId" class="input1" id="userId" onFocus="this.className='input1-bor'" onBlur="this.className='input1'" value="${cookieUserId}" />
	          </div>
	          </td>
	        </tr>
	        <tr>
	          <td height="28"  class="login_font">Password: </td>
	          <td><div align="right">
	            <input name="password" id="password" class="input1" type="password" onFocus="this.className='input1-bor'" onBlur="this.className='input1'"/>
	          </div></td>
	        </tr>
	        <tr>
	          <td colspan="2"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	                <td width="9%" height="24"><input type="checkbox" name="remeberId" id="remeberId" value="1" /></td>
	                <td width="91%" class="remember">Remember my ID </td>
	              </tr>
	            </table>          </td>
	        </tr>
	        
			<tr>
	          <th height="26" colspan="2"><a href="${ctx }/login!forgetPassword.action">Forgot password?</a></th>
	        </tr>
	        <tr>
	          <td height="28" colspan="2"><div class="login_button">
			  <span class="login_ok" onclick="checkForm()">Login</span>&nbsp;&nbsp;
			   <span class="login_ok" onclick="loginForm.reset()">Cancel</span>
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
	$("#userId").val(getCookie("userId"));
	$("#userId").focus();
	
	function checkForm()
	{
		if($("#userId").attr( "value" ) == ""){
			alert('userId is required!');
			$("#userId").focus();
			return false;
		}
		if($("#password").attr( "value" ) == 0){
			alert('Password is required!');
			$("#password").focus();
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
	$("#password").keydown(function (event){
		if(event.keyCode==13){
			checkForm();
		}
	});
	$("#remeberId").keydown(function (event){
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