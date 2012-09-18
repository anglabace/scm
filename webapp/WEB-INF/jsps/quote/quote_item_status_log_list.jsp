<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
</head>
<body>
<table width="500" border="0" cellpadding="0" cellspacing="0" class="list_table" style="margin: 10px auto;">
	<tr>
		<th width="27">&nbsp;</th>
		<th width="113">Status</th>
		<th width="157">Reason</th>   
		<th width="180">Update Date</th>
		<th width="122">Update By</th>
	</tr>
	<s:iterator value="itemStatusLoglist" status="st">
		<tr>
			<td>
				&nbsp;${st.index+1}
			</td>
			<td>&nbsp;${currentStat}</td>
			<td>&nbsp;${reason}</td>
			<td>
				&nbsp;<s:date name="processDate" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
			<td>&nbsp;${updateBy}</td>
		</tr>
	</s:iterator>
</table>
<table width="100%" border="0" height="50" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="center">
					<input type="button" name="closeHistoryButton"
						 value="Close" class="style_botton" onclick="parent.$('#itemStatusHistoryDialog').dialog('close');"/>
				</td>
			</tr>
</table>
</body>
</html>