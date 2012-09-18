<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scm</title>
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {
	margin-left: 10px;
	margin-top: 10px;
	width: 730px;
}
</style>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}util/util.js"></script>
<script type="text/javascript" language="javascript">
	$(document)
			.ready(
					function() {
						$("#combineConfirmBtn")
								.click(
										function() {
											var toCustNo = document
													.getElementById("toCustNos").contentWindow.document
													.getElementById("toCustNod").innerHTML;
											var tmpArr = [];
											var fromCustNo = document
													.getElementById("fromCustNos").contentWindow.document
													.getElementById("fromCustNod").innerHTML;
											if (!toCustNo) {
												alert("Please select the customer’s master account.");
												return;
											}
											if (!fromCustNo) {
												alert("Please select the customer’s duplicate account.");
												return;
											}
											$
													.ajax({
														type : "get",
														url : "customer/combine_accounts!combineCustomer.action",
														data : "fromCustNo="
																+ fromCustNo
																+ "&toCustNo="
																+ toCustNo,
														dataType : 'json',
														success : function(data) {
															if (hasException(data)) { //服务端返回有异常时

															} else { //成功时调用
																alert("        "
																		+ data.message);
																parent
																		.$(
																				"#combineDialog")
																		.dialog(
																				"close");
																window.location
																		.reload();
															}
														},
														error : function(data) {
															if (data)
																alert(data);
															else
																alert("System error! Please contact system administrator for help.");
														},
														async : false
													});
										});
						$("#combineCancelBtn").click(function() {
							parent.$("#combineDialog").dialog("close");
						});
					});
</script>
</head>
<body>
	<iframe id="listall"
		src="${ctx}/customer/combine_accounts!listCustNos.action?filter_EQS_status=ACTIVE"
		height="400" width="1000" scrolling="no" frameborder="0"></iframe>
	<div align="center">
		<input type="button" id="combineConfirmBtn" class="style_botton"
			value="Confirm" /> <input type="button" id="combineCancelBtn"
			value="Cancel" class="style_botton" />
	</div>
	<iframe id="fromCustNos"
		src="${ctx}/customer/combine_accounts!fromCustNo.action" height="30"
		width="1000" scrolling="no" frameborder="0"></iframe>
	<iframe id="toCustNos"
		src="${ctx}/customer/combine_accounts!toCustNos.action" height="30"
		width="1000" scrolling="no" frameborder="0"></iframe>

</body>
</html>