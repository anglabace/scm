<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp"%>

<script language="javascript" type="text/javascript" src="${global_js_url}lang/lang.js"></script>

<% 
	String invoiceIds = "";
	Object operateResult = request.getAttribute("operate_result");
	if(operateResult != null){
		if(operateResult.toString().equals("true")){
%>
<script>
	//window.parent.location.reload();
	window.parent.location.href="ap_invoice!input.action";
	window.parent.closeiframe();
</script>
<%			
		}else{
%>
<script>
	alert(lang.deleteInvoice3);
	window.parent.closeiframe();
</script>
<%			
		}
	}else{
		invoiceIds = request.getAttribute("invoiceIds").toString();
		//request.removeAttribute("invoiceIds");
	}
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>delete invoice page</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>

<script>
function doSubmit(){
	if($('#statusUpReason').val() == ''){
		alert(lang.deleteInvoice4);
		return;
	}
	document.getElementById('form1').submit();
}
</script>

</head>

<body>
		<table width="578" border="0" cellspacing="3" cellpadding="0"
			id="table11" bgcolor="#96BDEA">
			<tr>
				<td bgcolor="#FFFFFF">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="line" height="39" align="left" valign="top"
								background="${global_image_url }header_bg.gif">
								<div class="line_l_new"
									style="background:url(${global_image_url }header_bg2.gif) no-repeat;">
									Delete Invoice
								</div>
								<div class="line_r_new" onclick=
	window.parent.closeiframe();
>
									<img src="${global_image_url }w_close.gif" width="11"
										height="11" />
									Close
								</div>
							</td>
						</tr>
						<tr>
							<td style="padding-left: 20px;">
								<br />
								
								<form name="form1" id="form1" method="post" action="ap_invoice!doDelete.action" target="">
									<input type="hidden" name="invoiceIds" id="invoiceIds" value="<%= invoiceIds%>" />
									<table width="100%" height="" border="0" cellpadding="0"
										cellspacing="0" class="General_table">
										<tr>
											<th valign="top">
												Note:
											</th>
											<td>
												<textarea id="statusUpReason" name="statusUpReason" class="content_textarea2"></textarea>
											</td>
										</tr>

										<tr>
											<td colspan="2">
												<div align="center">
													<br />
													<input type="button" name="Submit2" class="style_botton"
														value="Confirm" onclick="doSubmit()"/>
													<input type="button" name="Cancel" value="Cancel"
														class="style_botton"
														onclick="javascript: window.parent.closeiframe();" />
												</div>
											</td>
										</tr>
									</table>
								</form>
								<br />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>

