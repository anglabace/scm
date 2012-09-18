<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}scm/config.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
　　　
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script type="text/javascript">
function formCommit(){
	var oldPs = $("#oldPs").val();
	var confirmPs = $("#confirmPs").val();
	if(oldPs!=confirmPs){
		return ;
	}
	if(oldPs.length<6){
	alert("The password must be at least six-character long.");
	return ;
	}
	if(!isNaN(oldPs)){
		alert("The password can not be numeric.");
		return ; 
	}
	var custNo=$("#custNo").val();
	var dataAttr="custNo="+custNo+"&newPassword="+oldPs+"&confirmPassW="+confirmPs;
	$.ajax({
		type:"get",
		url:"customer!modifyPassWord.action",
		data:dataAttr,
		dataType:"",
		success:function(data){
			if(data=="true"){
			alert("Your password is changed.");
			}
			//parent.window.frames["itemListIframe"].updateStatus(itemId, data["itemStatus"]);
		},
		error:function(){
			alert("Failed to change the password. Please contact system administrator for help.");
		},
		async:false
	});

}
</script>
  <body>
    <form action="" method="get" id="passwordManage" name="passwordManage">
    <center>
    <table style="margin-top:10px;cellspacing:20px;" >
    <tr > 
    <td valign="top">
    	&nbsp;&nbsp;&nbsp;<span class="important">*</span>New&nbsp;&nbsp;&nbsp;Password &nbsp;<input type="password" size="25" id="oldPs" name="oldPs" class="NFText" />
    </td>
    </tr>
     <tr style="height:5px;"></tr>
    <tr>
    <td valign="top">
    	<span class="important">*</span>Password Confirm &nbsp;<input type="password" size="25" id="confirmPs" name="confirmPs"  class="NFText" />
    </td>
    </tr>
    <tr style="height:10px;"></tr>
    <tr>
    <td align="center">
    <input type="hidden" name="custNo" id="custNo" value="${custNo}" />
	<input type="hidden" name="sessCustNo" value="${sessCustNo}" />
    <input type="button" class="style_botton" value="Confirm" onclick="formCommit();"/>
    <input type="reset" class="style_botton" value="Cancel" />
    </td>
    </tr>
    </table>
    </center>
    </form>
  </body>
</html>
