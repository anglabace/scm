<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="org.apache.commons.beanutils.BeanUtils"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.genscript.gsscm.tools.entity.FollowUp"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Accounting</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js"
	type="text/javascript"></script>
<link href="${global_css_url}stylesheet/openwin.css" rel="stylesheet"
	type="text/css" />
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
	src="${global_js_url}jquery/jquery.form.js"></script>

<script language="javascript" type="text/javascript">
	$(document).ready(function() {
		/* var nextFollowupDate = $("#nextFollowupDate").val();
		if (nextFollowupDate == "") {
			alert("Please select a date!");
			return;
		}
		var todaytime = Date.parse(new Date());
		var nextFollowupDateTime = Date
				.parse(nextFollowupDate);
		if (todaytime < nextFollowupDateTime) {
			 $("#taskId").attr("disabled",true);
			 $("#result").attr("disabled",true);
		} */
		$('.ui-datepicker').each(function() {
			$(this).datepicker({
				dateFormat : 'yy-mm-dd',
				changeMonth : true,
				changeYear : true
			});
		});

	});

	$(function() {
		$("#SaveID")
				.click(
						function() {
							var nextFollowupDate = $("#nextFollowupDate").val();
							if (nextFollowupDate == "") {
								alert("Please select a date!");
								return;
							}
							var todaytime = Date.parse(new Date());
							var nextFollowupDateTime = Date
									.parse(nextFollowupDate);
							if (todaytime > nextFollowupDateTime) {
								alert("Please select the next followup date that should older than today! ");
							}
							var dates = '';
							dates += $("#FollowUpForm").serialize();
							alert(dates);
							$
									.ajax({
										type : "POST",
										url : "follow_up!save.action",
										data : dates,
										dataType : 'json',
										success : function(msg) {
											if (msg.message == 'success') {
												alert("Save success!");
												parent
														.$(
																"#AddFollowupMessageWindow")
														.dialog("close");
												parent
														.$(
																'#AddFollowupMessageWindow')
														.dialog('destory');
												//	parent.window.location.reload();
											}
										},
										error : function(msg) {
											alert("Failed to view the order. Please contact system administrator for help.");
										}
									});
						});
		$("#subclose").click(function() {
			parent.$("#AddFollowupMessageWindow").dialog("close");

		});
	});

	function selectAll(form, chkAll) {
		var chk = form.elements;
		for ( var i = 0; i < chk.length; i++) {
			chk[i].checked = chkAll.checked;
		}
	}

	function listAll(obj, chk) {
		if (chk == null) {
			chk = 'checkboxes';
		}

		var elems = obj.form.getElementsByTagName("INPUT");
		for ( var i = 0; i < elems.length; i++) {
			if (elems[i].name == chk || elems[i].name == chk + "[]") {
				elems[i].checked = obj.checked;
			}
		}
	}
</script>

</head>

<body class="content">
	<table width="510" border="0" cellspacing="3" cellpadding="0"
		id="table11" bgcolor="#96BDEA">
		<tr>
			<td bgcolor="#FFFFFF">
				<form id="FollowUpForm">
					<input type="hidden" name="followUp.id" value="${followUp.id}" />
					<input type="hidden" id="today" value="${date}" name="today" /> <input
						type="hidden" id="OpenFlag" value="${OpenFlag}" name="OpenFlag" />
					<input name="followUp.orderNo" value="${orderNo}" type="hidden" />
					<table border="0" cellpadding="0" cellspacing="0"
						class="General_table" style="margin: 0px;">
						<tr>
							<th></th>
							<td height="20"></td>
						</tr>
						<tr>
							<th height="20">Followup Status</th>
							<td height="30"><s:if test="followUp.status=='closed'">
									<select name="followUp.status" style="width: 182px;"
										disabled="disabled">
										<option value="open">Open</option>
										<option value="closed">Closed</option>
									</select>
								</s:if> <s:else>
									<select name="followUp.status" style="width: 182px;">
										<option value="open">Open</option>
										<option value="closed">Closed</option>
									</select>
								</s:else>
							</td>
						</tr>
						<tr>
							<th height="20">Production Target Date</th>
							<td height="30"><input type="text" size="30" class="NFText"
								readonly="readonly" value="${productiontargetDate}" />
							</td>
						</tr>
						<tr>
							<th>Next Followup Date</th>
							<td height="30"><s:if test="%{followUp.status=='closed'}">
									<input name="followUp.nextFollowupDate" id="nextFollowupDate"
										type="text" class="NFText ui-datepicker" size="30"
										readonly="readonly" value="${followUp.nextFollowupDate}"
										disabled="disabled" />
								</s:if> <s:else>
									<input name="followUp.nextFollowupDate" id="nextFollowupDate"
										type="text" class="NFText ui-datepicker" size="30"
										readonly="readonly" value="${followUp.nextFollowupDate}" />
								</s:else>
							</td>
						</tr>
						<tr>
							<th>Next Followup Task</th>
							<td height="30"><s:if test="%{followUp.status=='closed'}">
									<input name="followUp.nextFollowupTask" type="text" id="taskId"
										class="NFText" size="30" value="${followUp.nextFollowupTask}"
										disabled="disabled" />
								</s:if> <s:else>
									<input name="followUp.nextFollowupTask" type="text" id="taskId"
										class="NFText" size="30" value="${followUp.nextFollowupTask}" />
								</s:else></td>
						</tr>
						<tr>
							<th>Followup Result</th>
							<td height="70"><s:if test="%{followUp.status=='closed'}">
									<textarea name="followUp.message" class="content_textarea2"
										id="result" disabled="disabled" style="width: 325px;">${followUp.message} </textarea>
								</s:if> <s:else>
									<textarea name="followUp.message" class="content_textarea2"
										id="result" style="width: 325px;">${followUp.message} </textarea>
								</s:else></td>
						</tr>
						<tr>
							<td height="40" colspan="2"><div align="center">
									<s:if test="%{followUp.status=='closed'}">
										<input type="button" name="Submit2" value="Save"
											disabled="disabled" class="style_botton" />
									</s:if>
									<s:else>
										<input id="SaveID" type="button" name="Submit2" value="Save"
											class="style_botton" />
									</s:else>
									<input id="subclose" type="submit" name="sub" value="Cancel"
										class="style_botton" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="invoice_title">Followup History:</div></td>
						</tr>
					</table>
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="4">
								<table width="400" border="0" cellpadding="0" cellspacing="0"
									style="margin: 0px;">
									<tr>
										<td colspan="4">
											<table width="460" border="0" cellpadding="0" cellspacing="0"
												style="margin: 0px;">
												<c:if test="fluplist!=null">
													<s:iterator value="fluplist" id="followup1">
														<tr>
															<td width="50">${followup1.followupDate }</td>
															<td width="50">${followup1.followupBy }</td>
															<td width="300">${followup1.Message }</td>
														</tr>
													</s:iterator>
												</c:if>
											</table></td>
									</tr>
									<tr>
										<td height="40" colspan="4" valign="top">&nbsp;</td>
									</tr>
								</table></td>
						</tr>
					</table>
				</form></td>
		</tr>
	</table>
</body>
</html>