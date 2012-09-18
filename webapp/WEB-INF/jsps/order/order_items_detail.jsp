<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css"
	rel="stylesheet" type="text/css" />
<script src="${global_js_url}jquery/jquery.js" type="text/javascript"
	language="javascript"></script>
<script src="${global_js_url}jquery/jquery.form.js"
	type="text/javascript" language="javascript"></script>
<script src="${global_js_url}jquery/jquery.dialog.all.js"
	type="text/javascript"></script>
<script src="${global_js_url}util/util.js" type="text/javascript"></script>
<title>scm item detail</title> 
<script type="text/javascript">
	function downLoadFile(url) {
		var sessOrderNo2=$("#sessOrderNo").val();
		$("#fileDownLoad").attr('action', url);
		$("#fileDownLoad").submit();
	}
</script>
</head>
<body>
	<span style="font-size: medium;"><strong>All Item
			Details:</strong> </span>
	<table border="0" cellpadding="0" cellspacing="0" class="General_table">
		<tr>
			<td>${allitemsDetail }</td>
		</tr>
		<tr>
			<td></td>
		</tr>
		<tr>
			<td><a href="javascript:void(0);"
				onclick="downLoadFile('order!createTxt4OrderItems.action')"
				title="download it..."> <input type="button" value="Create TXT"
					name="buttom1" /> </a>
			</td>
			<td><a href="javascript:void(0);"
				onclick="downLoadFile('order!createDocument4OrderItems.action')"
				title="download it..."> <input type="button"
					value="Create Document" name="buttom2" /> </a>
			</td>
		</tr> 
	</table>
		<input type="hidden" value="${sessOrderNo}" id="sessOrderNo" />
	<form name="" id="fileDownLoad" method="post">
		<input type="hidden" id="sessOrderNo2" name="sessOrderNo" value="${sessOrderNo}"/>
	</form>
</body>
</html>