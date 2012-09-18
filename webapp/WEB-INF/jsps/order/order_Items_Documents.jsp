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
<script src="${global_js_url}util/util.js" type="text/javascript"></script>
<script type="text/javascript">
	function downLoadFile(filePath, fileName) {
		$("#fileName").val(fileName);
		$("#filePath").val(filePath);
		var url = "download.action";
		$("#fileDownLoad").attr('action', url);
		$("#fileDownLoad").submit();
	}
</script>
<title>scm item documents detail</title>
</head>
<body>
	<table border="0" cellpadding="0" cellspacing="0" class="General_table"
		style="">
		<s:if test="itemDocumentsMap2.size>0">
			<s:iterator value="itemDocumentsMap2" id="tmp">
				<tr>
					<td>ItemNo <s:property value="#tmp.key" /> : <s:iterator
							value="#tmp.value" id="document">
							<%-- 	<s:iterator value="#document.value" id="s"> --%>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
						 <font color="green"><a href="javascript:void(0);"
								onclick="downLoadFile('<s:property value="#document.filePath" />','<s:property value="#document.docName" />')"
								title="download it..."> <s:property
										value="#document.docName" /> </a> </font>
							<%-- 	</s:iterator> --%>
						</s:iterator> <br /> <br />
					</td>
				</tr>
			</s:iterator>
		</s:if>
		<s:else>
			<tr>
				<td><font color="red">There has no documents in this
						order items.</font></td>
			</tr>
		</s:else>
	</table>
	<form name="" id="fileDownLoad" method="post">
		<s:hidden name="filePath" id="filePath"></s:hidden>
		<s:hidden name="fileName" id="fileName"></s:hidden>
	</form>
</body>
</html>
