<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url }SpryTabbedPanels.css" rel="stylesheet"
	type="text/css" />
<link href="${global_css_url }tab-view.css" rel="stylesheet"
	type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link type="text/css"
	href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1"
	type="text/javascript"></script>
</head>
<body>
<table width="563" border="0" align="center" cellpadding="0"
	cellspacing="0" class="General_table">
	<tr>
		<td colspan="4">
		<table class="list_table" border="0" cellpadding="0" cellspacing="0"
			width="563">
			<tbody>
				<tr>
					<th width="33">&nbsp;</th>
					<th width="96">Catalog ID</th>
					<th width="107">Update From</th>
					<th width="107">Update To</th>

					<th width="99">Update Date</th>
					<th>Updated By</th>
				</tr>
			</tbody>
		</table>
		<div style="width: 580px; height: 100px; overflow: scroll;">
		<table class="list_table" border="0" cellpadding="0" cellspacing="0"
			width="563">
			<tbody>
				<s:iterator value="servicePriceListBeanDTOList" status="st">
					<tr>
						<td align="center" width="33">${st.index+1}</td>

						<td width="96">${catalogId}</td>
						<td align="right" width="107">${priceOldVal}</td>
						<td align="right" width="107">${priceNewVal}</td>
						<td width="99"><s:date name="requestDate" format="yyyy-MM-dd"></s:date></td>
						<td>${requestedBy}</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		</div>
		</td>
	</tr>
	<tr>
		<td>
		<div align="center"><br>
		<input name="Submit2" value="Cancel" class="style_botton"
			onclick="parent.$('#priceChangeHistDialogd').dialog('close');"
			type="reset" /></div>
		</td>

	</tr>

</table>
</body>
</html>