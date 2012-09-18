<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base id="myBaseId" href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
</head>
<body>
   <table width="580" border="0" cellpadding="0" cellspacing="0" class="Customer_table">
    <tr>
      <td>&nbsp;</td>
    </tr>
      <tr>
      <td><span class="important">Thank you</span><br />
        <br />
	 <center>	
        　　Dear ${firstName} ${lastName}.<br />
          　　　　　　You have successfully redeemed:<br />
          　　　　　　${successMsg}
          　　　　　　An email confirmation with your Amazon Gift Card information will arrive at ${email}.<br />
          </center>
        </td>
    </tr>
     <tr>
       <td height="50" style="padding-left:250px;">
         <input type="submit" name="Submit3" value="Close" class="style_botton" onclick="javascript:parent.closeDialogAndRefeash();"  />
       </td>
     </tr>
    </table>
</body>
</html>
