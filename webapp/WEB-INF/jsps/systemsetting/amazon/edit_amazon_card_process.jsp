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
	function save() {
		var name = $("#name").val();
		if (!name) {
			alert("Card Name can't not be empty");
			return;
		}
		var value = $("#value").val();
		if (!value) {
			alert("Value can't not be empty");
			return;
		}
		$.ajax({
			type : "POST",
			url : "customer/gift_card!save.action",
			data : $("#form1").serialize(),
			dataType : "json",
			success : function(msg) {
				if (msg.message == "success") {
					window.parent.closeiframe();
					parent.window.location.reload();
				}
			},
			error : function(msg) {
				alert("Save new case failed!!!");
			}
		});

	}
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
							<div class="line_l">Amazon Cards</div>
							<div class="line_r_new" onclick="window.parent.closeiframe()">
								<img src="js/greybox/w_close.gif" width="11" height="11" />Close
							</div>
						</td>
					</tr>
					<tr>
						<td align="center">

							<form id="form1">
								<table width="95%" border="0" align="center" cellpadding="0"
									cellspacing="0" class="General_table">

									<tr>
										<th width="50%">&nbsp;</th>
										<td width="50%">&nbsp;</td>
									</tr>

									<tr>
										<th>Card Number:</th>
										<td align="left"><input name="entity.cardNo" id="name"
											type="text" class="NFText" size="20" value="${cardNo}" />
										</td>
									</tr>
									<tr>
										<th>Value:</th>
										<td align="left"><input name="entity.cardValue"
											id="value" type="text" class="NFText" size="20"
											value="${cardValue }" />
										</td>
									</tr>
									<tr>
										<th>Purchase Ref</th>
										<td align="left"><input name="entity.purchaseRef"
											type="text" class="NFText" size="20" value="${purchaseRef }" />
										</td>
									</tr>
									<tr>
										<th>Purchase processer :</th>
										<td align="left"><input name="purchaseByname" type="text"
											class="NFText" size="20" readonly="readonly"
											value="${purchaseByName}" /></td>
									</tr>
									<tr>
										<th>Purchase Date:</th>
										<td><input name="purchaseDate" type="text" class="NFText"
											size="20" readonly="readonly" value="${purchaseDatee }" /></td>
									</tr>
									<tr>
										<td height="100" colspan="2" align="center"><input
											type="hidden" name="entity.id" id="id" value="${id }" /> <input
											type="hidden" name="entity.cardType" id="cardType"
											value="${cardType }" /> <input type="hidden"
											name="entity.sendDate" id="sendDate" value="${sendDate }" />
											<input type="hidden" name="entity.sentBy" id="sentBy"
											value="${sentBy }" /> <input type="hidden"
											name="entity.custNo" id="custNo" value="${custNo }" /> <input
											type="hidden" name="entity.orderNo" id="orderNo"
											value="${orderNo }" /> <input type="hidden"
											name="entity.purchaseRef" id="purchaseRef"
											value="${purchaseRef }" /> <input type="hidden"
											name="entity.purchaseBy" id="purchaseBy"
											value="${purchaseBy }" /> <input name="Submit" type="button"
											class="style_botton" value="Save" onclick="save()" /> <input
											type="button" name="Submit2" value="Cancel"
											class="style_botton"
											onclick="javascript: window.parent.closeiframe();" /></td>
									</tr>
								</table>
							</form> <br />
						</td>
					</tr>
				</table></td>
		</tr>
	</table>



</body>
</html>

