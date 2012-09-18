<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css" />
</head>
<body>
<table width="600" border="0" cellspacing="3" cellpadding="0" id="table11" bgcolor="#96BDEA">
   <tr>
    <td bgcolor="#FFFFFF"><table width="600" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="384" style="padding-left:30px;"><br />
          <table border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
           
              <tr>
                <td height="22" colspan="3">
                Packing error
                <br/>
                <ul>
                	<s:iterator value="workOrderErrorList">
                		<li><s:property value="orderNo"/> is not closed!</li>
                	</s:iterator>
                </ul>
               </td>
                </tr>
            </table>
             </td>
          </tr>
        </table><br /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>