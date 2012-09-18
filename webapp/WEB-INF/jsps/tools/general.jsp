<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Production</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />


<script language="javascript" type="text/javascript"
	src="${global_js_url}newwindow.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script>
	$(function() {
		$("#table_new tr").each(function(i) {

			this.style.backgroundColor = [ "#ffffff", "#eee" ][i % 2]
		})

	})
</script>


</head>
<body class="content" style="background-image:none;">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center"><br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />

    <br />
    <br />    <br />    <img src="${global_image_url}build.jpg" width="304" height="266" /></td>
  </tr>
</table>

</body>
</html>