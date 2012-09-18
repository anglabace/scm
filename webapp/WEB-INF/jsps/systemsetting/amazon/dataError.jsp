<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Edit amazon card process</title>
<base href="${global_url}" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}openwin.css" rel="stylesheet"
	type="text/css" />

<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>

<script language="javascript" type="text/javascript">
	$(document).ready(function() {
		$('tr:even >td').addClass('list_td2');
	});
</script>
</head>


<body>
	<table width="660" border="0" cellspacing="3" cellpadding="0"
		bgcolor="#96BDEA">
		<tr>
			<td bgcolor="#FFFFFF"><table width="100%"
					border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td align="left" valign="top"
							background="js/greybox/header_bg.gif">
							<div class="line_l">NO Available Gift Cards</div>
							<div class="line_r_new" onclick="window.parent.closeiframe()">
								<img src="js/greybox/w_close.gif" width="11" height="11" />Close
							</div>
						</td>
					</tr>
					<tr>
						<td align="center" valign="middle"  height="200"><font color="red">Sorry this OrderNO has not
							normal~~~ <br /> Please contact with System Manager!</font></td>
					</tr>

				</table></td>
		</tr>
		<tr align="center">
			<td align="center"><input type="button" name="Submit2"
				value="Close Window"
				onclick="javascript: window.parent.closeiframe();" /></td>
		</tr>
	</table>
</body>
</html>

