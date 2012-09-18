<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Documents</title>
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />

<link type="text/css"
	href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}ajax.js"></script>
<script src="${global_js_url}table.js" type="text/javascript"></script>
<script
	src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js"
	type="text/javascript"></script>
<link type="text/css"
	href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script src="${global_js_url}jquery/ui/ui.core.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js"
	type="text/javascript"></script>

<script language="javascript" type="text/javascript"
	src="${global_js_url}util/util.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}newwindow.js"></script>
<script language="JavaScript" type="text/javascript">
	$(document).ready(function() {
		$('tr:even >td').addClass('list_td2');

	});
	function cc(e) {
		var a = document.getElementsByName("ids");
		for ( var i = 0; i < a.length; i++)
			a[i].checked = e.checked;
	}

	function get_checked_str(name) {
		var a = document.getElementsByName(name);
		var str = '';
		for ( var i = 0; i < a.length; i++) {
			if (a[i].checked) {
				str += a[i].value + ',';
			}
		}
		return str.substring(0, str.length - 1);
	} 

	 
</script>
</head>

<body class="content" style="background-image: none;">
	<div class="scm">
		<div id="frame12" style="display: none;" class="hidlayer1">
			<iframe id="hidkuan" name="hidkuan" src="" width="668" height="425"
				frameborder="0" allowtransparency="true"></iframe>
		</div>
		<div class="scm">
			<div class="title_content">
				<div class="title">Documents</div>
			</div>
			<div class="search_box">
				<div class="search_box_two">
					<form id="mainForm" action="" method="get">
						<table border="0" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<td>Cat.No.</td>
								<td width="120"><input name="catalogNo" type="text"
									id="catalogNo" class="NFText" size="20" value="${catalogNo}" />
								</td>
								<td>Name</td>
								<td width="120"><input name="name" type="text" id="name"
									class="NFText" size="20" value="${name}" />
								</td>
								<td>Type</td>
								<td width="120"><s:select name="type" id="type"
										list="pbOptionItemList" listValue="text" listKey="value"
										headerKey="" headerValue="" value="type" /></td>
								<td>Description</td>
								<td width="120"><input name="dec" type="text" id="dec"
									class="NFText" size="20" value="${dec}" /></td>
							</tr>
							<tr>
								<td height="40" colspan="6" align="center"><input
									type="submit" name="Submit5" value="Search"
									class="search_input" /></td>
							</tr>
						</table>
					</form>
				</div>
			</div>

			<div class="input_box">
				<div class="content_box">
					<table width="1010" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><div style="margin-right: 17px;">
									<table width="993" border="0" cellspacing="0" cellpadding="0"
										class="list_table">
										<tr>
											<th width="232">Catalog No</th>
											<th width="179">File Name</th>
											<th width="206">File Type</th>
											<th width="165">Description</th>
											<th>Version</th>

										</tr>

									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="list_box" style="height: 340px;">
									<table width="993" border="0" cellspacing="0" cellpadding="0"
										class="list_table2">
										<s:iterator value="page.result">
											<tr>
												<td width="233"><s:iterator value="productList"
														status="index">
														<s:if
															test="#index.index<\"4\"">${catalogNo}</s:if>
				  <s:elseif test="#index.index==\"4\"">					...
				</s:elseif>
			</s:iterator>
		&nbsp;</td>
        <td width="178"><a href="${ctx }/product/document!input.action?docId=${docId}">${docName}&nbsp;</a></td>
        <td width="207">
        	<s:iterator value="pbOptionItemList">
			  <s:if test="docType==value">
	         	${text}&nbsp;
	          </s:if>
	        </s:iterator>
	        &nbsp;
		</td>
        <td width="168">${description}&nbsp;</td>
        <td>${version}</td> 
        </tr>
 </s:iterator>
    </table>
        </div></td>
  </tr>
  <tr><td>   
 	<div class="grayr">

			<jsp:include page="/common/db_pager.jsp">
				  <jsp:param value="${ctx }/product/document.action"
															name="moduleURL" />
			     <jsp:param value="${catalogNo}" name="catalogNo"/>
			     <jsp:param value="${name}" name="name"/>
			     <jsp:param value="${type}" name="type"/>
			     <jsp:param value="${dec}" name="dec"/>
														</jsp:include>
														</div>
												</td>
											</tr>
											<tr>
												<td align="center"></td>
											</tr>
									</table>
								</div>
								</div>
								</div>
								<div class="button_box">
									<input type="button" name="Submit6" value="New"
										class="search_input"
										onclick="location.href='${ctx }/product/document!input.action';" />
								</div>
								</div>
</body>
</html>

