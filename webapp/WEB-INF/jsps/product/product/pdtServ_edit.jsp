<%-- 
    Document   : pdtServ_edit
    Created on : 2010-9-15, 14:05:22
    Author     : jinsite
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css?v=2" rel="stylesheet"
	type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css"
	rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/ui/ui.datepicker.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}scm/pdtServ_inventory_edit.js"></script>

</head>

<body>
	<div style="padding-top: 20px; padding-left: 10px;">
		<table width="500" border="0" cellspacing="0" cellpadding="0"
			class="General_tabler">
			<tr>
				<th width="105" height="20">Catalog No&nbsp;</th>
				<td><input type="hidden" value="${psId}" id="psId" /> <input
					type="hidden" value="${sessionPSID}" id="sessionPSID" /> <input
					type="hidden" value="${idStr}" id="shipAreaId" /> <input
					type="hidden" value="${idStr}" id="shipInfoOld" /> <input
					type="text" readonly="readonly" class="NFText"
					value="${product.catalogNo}" size="18" />
				</td>
			</tr>

			<tr>
				<th height="20">Country</th>
				<td><input type="text" value="${restrictShip.countryName}"
					readonly="readonly" class="NFText" size="18" /></td>
			</tr>

			<tr>
				<th valign="top">State</th>
				<td valign="top">
					<div align="left">
						<input type="text" readonly="readonly"
							value="${restrictShip.stateName}" class="NFText" size="18" />
						Zip <input type="text" readonly="readonly"
							value="${restrictShip.zipCode}" class="NFText" size="10" />
					</div>
				</td>
			</tr>

			<tr>
				<th valign="top">Date Restriction</th>
				<td valign="top"><input type="radio" name="dateRestriction"
					value="1" onclick="initDateTr(this.value);"
					<s:if test="restrictShip.effFrom==null">checked="checked"</s:if> />
					No Date Restriction</td>
			</tr>

			<tr>
				<th valign="top">&nbsp;</th>
				<td valign="top"><input type="radio" checked="checked"
					name="dateRestriction" value="2" onclick="initDateTr(this.value);"
					<s:if test="restrictShip.effFrom!=null">checked="checked"</s:if> />
					Restricted from <span
					style="<s:if test="restrictShip.effFrom==null">display:none;</s:if>"
					id="dateSpan"> <input name="effFrom" id="effFrom"
						type="text" class="pickdate NFText" style="width: 75px;"
						value="<s:date name="restrictShip.effFrom" format="yyyy-MM-dd"/>"
						size="18" /> &nbsp;&nbsp;to <input name="effTo" id="effTo"
						type="text" class="pickdate NFText" style="width: 75px;"
						value="<s:date name="restrictShip.effTo" format="yyyy-MM-dd"/>"
						size="18" /> </span>
				</td>
			</tr>
			<tr>
				<th height="47" valign="top">&nbsp;</th>
				<td valign="top">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="3">
					<div class="botton_box">
						<input type="button" id="shipAreaEdit" class="style_botton"
							value="Modify" /> <input type="button" id="shipAreaCancel"
							value="Cancel" class="style_botton" />
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>