<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base id="myBaseId" href="${global_url}" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Contact Management</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<script language="javascript" type="text/javascript">
			var baseUrl="${global_url}";
		</script>
		<style type="text/css">   　
<!--
body {
	width: 600px;
}

.invoice_title {
	padding: 6px 0px;
	margin: 0px;
}

.General_table .Indent {
	padding-left: 15px;
}

.General_table  .General_table {
	margin: 6px 0px;
}
-->
</style>
	</head>
	<body>
		<s:iterator value="#request.contactActivity">
			<table width="600" border="0" cellpadding="0" cellspacing="0"
				class="General_table" style="margin: 6px 0px 0px 10px;">
				<tr>
					<td class="invoice_title" height="28" >
						<strong>Last Phone Contact :</strong>
					</td>
				</tr>
				<tr>
					<td class="Indent">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<td width="100" align="right">
									Date/Time
								</td>
								<td>
									<input name="textfield232" type="text" class="NFText"
										value="${lastPhoneDate}" size="20" id="btntxt10" />
								</td>
								<td width="100" align="right">
									Contact Person
								</td>
								<td width="220">
									<input name="textfield54" type="text" class="NFText"
										value="${lastPhoneContactUser}" size="40" />
								</td>

							</tr>
							<tr>
								<td align="right">
									By
								</td>
								<td colspan="3">
									<input name="textfield53" type="text" class="NFText"
										value="${lastPhoneContactBy}" size="20" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>

					<td class="invoice_title" height="28" >
						<strong>Last Correspondence Sent :</strong>
					</td>
				</tr>
				<tr>
					<td class="Indent">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<td width="100" align="right">
									Last E-mail Sent
								</td>
								<td>
									<input name="textfield6" type="text" class="NFText" size="20"
										value="${lastEmailSent}" />
								</td>
								<td width="100" align="right">
									Subject
								</td>

								<td width="220">
									<input name="textfield532" type="text" class="NFText" size="40"
										value="${lastEmailSubject}" />
								</td>
							</tr>
							<tr>
								<td align="right">
									Last Fax Sent
								</td>
								<td>
									<input name="textfield62" type="text" class="NFText" size="20"
										value="${lastFaxSent}" />
								</td>
								<td>
									&nbsp;
								</td>
								<td>
									<input name="textfield5322" type="text" class="NFText"
										size="40" value="${lastFaxSubject}" />
								</td>
							</tr>

							<tr>
								<td align="right">
									Last Mailing Sent
								</td>
								<td>
									<input name="textfield63" type="text" class="NFText"
										value="${lastMailSent}" size="20" />
								</td>
								<td align="right">
									Subject
								</td>
								<td>
									<input name="textfield5323" type="text" class="NFText"
										value="${lastMailSubject}" size="40" />
								</td>
							</tr>
						</table>
					</td>
				</tr>

				<tr>
					<td class="invoice_title" height="28">
						<strong>Last Catalog Sent :</strong>
					</td>
				</tr>
				<tr>
					<td class="Indent">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<td width="100" align="right">
									Last Catalog Sent
								</td>
								<td>
									<input name="textfield64" type="text" class="NFText" size="20"
										value="${lastCatalogSent}" />
								</td>

								<td width="100" align="right">
									Catalog Name
								</td>
								<td width="220">
									<input name="textfield5324" type="text" class="NFText"
										size="40" value="${lastCatalogName}" />
								</td>
							</tr>

						</table>
					</td>
				</tr>
				<tr>
					<td class="invoice_title" height="28" >
						<strong>Last Modified :</strong>
					</td>

				</tr>
				<tr>
					<td class="Indent">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<td width="100" align="right">
									Date
								</td>
								<td>
									<input name="textfield232" type="text" class="NFText"
										value="<s:date name="lastModifyDate" format="yyyy-MM-dd"/>"
										size="20" id="btntxt2" />
								</td>
								<td width="100" align="right">
									By
								</td>
								<td width="220">
									<input name="textfield643" type="text" class="NFText" size="20"
										value="${lastModifyUser}" />
								</td>

							</tr>
						</table>
					</td>
				</tr>
			</table>
		</s:iterator>
	</body>
</html>

