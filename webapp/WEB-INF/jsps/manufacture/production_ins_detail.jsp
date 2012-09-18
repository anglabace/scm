<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
<script src="${global_js_url}jquery/jquery.js" type="text/javascript"></script>
</head>
<body>
<table width="640" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td style="padding-left:20px;"><table border="0" cellpadding="0" cellspacing="0" class="General_table" >
          <tr>
            <th width="129">Type</th>
            <td colspan="2"><input name="type" type="text" class="NFText" value="${orderNote.type}" disabled="disabled" size="30" /></td>
          </tr>
          <tr>
            <th valign="top">Description</th>
            <td colspan="2"><textarea name="description" cols="70" rows="2" disabled="disabled" class="content_textarea">${orderNote.description}</textarea></td>
          </tr>
          <tr>
            <th valign="top">Attachment</th>
            <td width="64" align="center">
            <div>
            	<ul>
            	<s:iterator value="orderNote.documentList">
            	<li>
            	<a href="download.action?fileName=<s:property value='docName'/>&filePath=<s:property value='filePath'/>">
            		<s:property value='docName'/>
            	</a>
            	</li>
            	</s:iterator>
            	</ul>
            </div>
            
            </td>
            <td width="420"></td>
          </tr>
          <tr>
            <th colspan="3"><div class="botton_box">
              <input type="submit" name="Submit622" value="Cancel"  class="style_botton" onclick="parent.$('#instruction_update_dlg').dialog('close');"/>
            </div></th>
          </tr>
        </table>
          <br />
          <br /></td>
      </tr>
    </table>
</body>
</html>