<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_js_url}jquery/jquery.autocomplete.css"
	rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.autocomplete.js"></script>
<script>
	$(document).ready(
			function() {
				$("#sequence").focus();
				var arrTest;
				$.ajax({
					type : "get",
					url : "qu_order_more!searchEnzymeList.action",
					dataType : "json",
					async : false,
					success : function(data) {
						if (data != undefined) {
							arrTest = data;
						}
					},
					error : function(data) {
					}
				});

				$("#sequence").autocomplete(arrTest, {
					width : 175,
					highlight : false,
					autoFill : true,
					scroll : true,
					scrollHeight : 350,
					formatItem : function(row, i, max) {
						return row.name;
					},
					formatResult : function(row) {
						return row.sequence;
					}
				});
				$("#sequence")
						.result(
								function(event, data, formatted) {
									if (data) {
										//$(this).parent().next().find("input").val(data[1]);
										parent.$("#searchEnzymeDialog").dialog(
												"option", "sequence",
												$(this).val());
										parent.$("#searchEnzymeDialog").dialog(
												"close");
									}
								});
			});
</script>
</head>

<body>
	<table border="0" cellpadding="0" cellspacing="0" class="General_table">
		<tr>
			<td><input id="sequence" name="sequence" type="text"
				class="NFText" size="24" /></td>
		</tr>
		<tr>
			<td><div style=""></div> 
			</td>
		</tr>
	</table>
</body>
</html>
