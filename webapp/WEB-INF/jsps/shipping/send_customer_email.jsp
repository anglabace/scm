="text/html" pageEncoding="UTF-8"%>
<%@include  file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css" />
<script src="${global_js_url}jquery/jquery.js" type="text/javascript" language="javascript"></script>
</head>
<body>
<s:if test="sendStatus == true">
<script type="text/javascript">alert("Send e-mail success!");</script>
</s:if>
<table width="500" border="0" cellspacing="3" cellpadding="0" id="table11" bgcolor="#96BDEA">
  <tr>
    <td bgcolor="#FFFFFF"><table width="500" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="39" align="left" valign="top" background="${global_js_url}greybox/header_bg.gif"><div class="line_l_new">Notify Customer</div>
          <div class="line_r_new" onclick="window.parent.closeiframe()"><img src="${global_js_url}greybox/w_close.gif" width="11" height="11" />Close</div></td>
      </tr>
      <tr>
        <td style="padding-left:20px;"><br />
        <form action="shipping!sendEmailToCustomer.action" method="post" enctype="multipart/form-data"  class="niceform">
          <table width="350" height="217" border="0" cellpadding="0" cellspacing="0" class="General_table">
            <tr>
              <th>&nbsp;</th>
              <th>To</th>
              <td><input type="hidden" name="custNo" value="${custNo}" />
              <s:if test="customerBusEmailList !=null && customerBusEmailList.size()>0">
              <s:select list="customerBusEmailList"
              	 		name="customerBusEmail" 
              	 		id="customerBusEmail"
              	 		/></s:if>              </td>
              <td>&nbsp;</td>
            </tr>
            
            <tr>
              <th>&nbsp;</th>
              <th>Subject</th>
              <td><input name="emailSubject" type="text" id="emailSubject" value="${emailSubject}" class="NFText"/></td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <th >&nbsp;</th>
              <th >Content</th>
              <td align="left"><textarea name="emailContent" class="content_textarea2" style="width:250px;">${emailContent}</textarea></td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <th >&nbsp;</th>
              <th >Attachment</th>
              <td>
              	<input type="file" class="NFText" style="height:18px;" name="upload" id="upload_file" size="30" />              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td height="24" colspan="4"><div align="center">
                <input type="submit" name="Submit3" class="style_botton" value="Send" />
                <input type="button" name="btn" class="style_botton" value="Cancel" onclick="parent.window.location='warehoseshipments/shipping!appFrame.action';  "/>
              </div></td>
            </tr>
          </table>          
         </form> 
          <br /></td>
      </tr>
    </table></td>
  </tr>
</table>

</body>
</html>