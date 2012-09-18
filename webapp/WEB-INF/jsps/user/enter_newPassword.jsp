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
	<form action="${ctx }/login!resetPassword.action" method="post" name="loginForm" id="loginForm" target="_top">
		<div class="login_content" id="change"><div class="login_title">Change Password</div>
		  <table width="94%" border="0" align="center" cellpadding="0" cellspacing="0">
	        <tr>
	          <td width="29%" height="30" class="login_font">User ID:</td>
	          <td width="71%">
	          <div align="right">
	            <input name="userId" class="input1" id="userId" onFocus="this.className='input2-bor'" onBlur="this.className='input1'" value="${userId}"  readonly="readonly"/>
	          </div>
	          </td>
	        </tr>
	        <tr>
	          <td height="30"  class="login_font" valign="top">New Password: </td>
	          <td><div align="right">
	            <input name="password" id="newPassword" class="input1" type="password" onFocus="this.className='input2-bor'" onBlur="this.className='input1'"/>
	            <span class="suggest">Password must be at least 6 characters which contain at least one uppercase letter(A-Z), one lowercase letter(a-z) and one number(0-9). And do not include three or more consecutive characters from the user ID.</span>
	          </div></td>
	        </tr>
	        <tr>
	          <td height="33"  class="login_font">Retype Password: </td>
	          <td><div align="right">
	            <input name="retypePassword" id="rePassword" class="input1" type="password" onFocus="this.className='input2-bor'" onBlur="this.className='input1'"/>
	          </div></td>
	        </tr>
	        <tr>
          <td height="20" colspan="2">&nbsp;</td>
        </tr>
	        
	        <s:hidden name="randomStr"></s:hidden>
	        <tr>
	          <td height="28" colspan="2"><div class="login_button">
			  <span class="login_ok" onclick="checkForm()">Confirm</span>&nbsp;&nbsp;
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
	$("#userId").focus();
	
	function checkForm()
	{
		if($("#newPassword").val().length == 0){
			alert( 'New Password is required!' );
			$("newPassword").focus();
			return false;
		}
		if($("#rePassword").val().length == 0){
			alert( 'Confirm Password is required!' );
			$("#rePassword").focus();
			return false;
		}
		if($("#newPassword").val() != $("#rePassword").val()){
			alert( 'Please enter the same password as above!');
			$("#rePassword").focus(); 
			return false;
		}if(!validPassword($("#newPassword").val())){
			alert('The new password is invalid. Please retry it.');
			$("#newPassword").focus();
			return false;
		}else{
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
	
	function validPassword(s){
		var pattern = /^(?=\S{6,}$)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])/;
		if (!pattern.exec(s)) return false
		return true;
	}

</script>
<div class="login_copyright">&copy; 2008-2011 GenScript Corporation. All rights reserved.</div>
</body>
</html>