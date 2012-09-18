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
	function send() {
		var CustNo = $("#CustNo").val();
		var Sendid = $("#Sendid").val();
		//alert(CustNo);
		//alert(Sendid);
		if ($("input:radio[checked=true]").length == 0) {
			alert("Please select a Card first!");
			return;
		} 
		var cardid = $("input:radio[checked=true]").attr('value');
		//alert(cardid);//被选中card id   
		var strform = "&CardId=" + cardid + "&CustNo=" + CustNo + "&Sendid=" + Sendid;
		//alert(strform);
		$.ajax({
			type : "POST",
			url : "customer/gift_card!SaveAndSend.action",
			data : strform,
			dataType : "json",
			success : function(msg) {
				alert(msg.message); 
					window.parent.closeiframe();
					parent.window.location.reload(); 
			},
			error : function(msg) {
				alert("Save new case failed!!!");
			}
		});

	}

	$(document).ready(function() {
		$('tr:even >td').addClass('list_td2');
	});
</script>
</head>


<body>
	<table width="660" border="0" cellspacing="3" cellpadding="0"
		bgcolor="#96BDEA">
		<tr>
			<td bgcolor="#FFFFFF"><table width="100%" border="0"
					cellspacing="0" cellpadding="0">
					<tr>
						<td height="39" align="left" valign="top"
							background="js/greybox/header_bg.gif">
							<div class="line_l">$ ${CardValue}---Available Gift Cards</div>
							<div class="line_r_new" onclick="window.parent.closeiframe()">
								<img src="js/greybox/w_close.gif" width="11" height="11" />Close
							</div></td>
					</tr>
					<tr>
						<td align="center">
							<table width="660" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
										<div style="margin-right: 17px;">
											<table width="660" border="0" cellspacing="0" cellpadding="0"
												class="list_table">
												<tr>
													<th width="80">Select</th>
													<th width="209">Card No</th>
													<th width="187">Card Value</th>
													<th>Card Type</th>
												</tr>
											</table>
										</div>
									</td>
								</tr>
							</table>
							<form id="form1">
								<input type="hidden" id="CustNo" value="${custNo}" /> <input
									type="hidden" id="Sendid" value="${Sendid}" />
								<table width="660" border="0" cellpadding="0" cellspacing="0"
									class="General_table">
									<tr>
										<td>
											<div class="list_box">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0" class="list_table2">
													<s:iterator value="giftCardPage.result">
														<tr>
															<td width="80" align="center"><input name="Cardid"
																type="radio" value="${id}" /></td>
															<td width="209" align="center">${cardNo }</td>
															<td width="187" align="center">${cardValue }</td>
															<td align="center">${cardType}</td>
														</tr>
													</s:iterator>
												</table>
											</div>
										</td>
									</tr>
								</table>
							</form></td>
					</tr>
					<tr>
						<td>
							<div class="grayr">
								<jsp:include page="/common/db_pager.jsp">
									<jsp:param
										value="${ctx }/customer/gift_card!SelectCardsByValue.action"
										name="moduleURL" />
								</jsp:include>
							</div></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr align="center">
			<td align="center"><input name="Submit" type="button"
				value="Save and Send" onclick="send()" /> <input type="button"
				name="Submit2" value="Cancel"
				onclick="javascript: window.parent.closeiframe();" /></td>
		</tr>
	</table>
</body>
</html>

