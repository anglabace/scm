<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>

<html>
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>scm</title>
		<link href="stylesheet/scm.css" rel="stylesheet" type="text/css" />
		<link href="stylesheet/table.css" rel="stylesheet" type="text/css" />


		<style type="text/css">
<!--
body {
	margin-left: 10px;
}
-->
</style>
		<style type="text/css" media="all">
@import "Upimg/thickbox.css";
</style>

		<script src="Upimg/jquery-1.1.3.1.pack.js" type="text/javascript"></script>
		<script src="Upimg/thickbox-compressed.js" type="text/javascript"></script>
		<link type="text/css" href="stylesheet/ui.all.css" rel="stylesheet" />
		<!--<script src="js/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="js/ui.core.js" type="text/javascript"></script>-->

		<script language="javascript" type="text/javascript"
			src="js/jquery.js"></script>
		<script src="js/ui.datepicker.js" type="text/javascript"></script>

	</head>
	<body>
		<form action="work_order!getPurchaseOrderItemReceiveAllAdd.action">
		<input type="hidden" value="${chksss }" name="chks" id="chks">
			<table width="700" height="250" border="0" cellpadding="0"
				cellspacing="0" class="General_table" style="margin: 5px auto;">
				<tr>
					<th width="212">
						&nbsp;
					</th>
					<td width="171">
						&nbsp;
					</td>
					<th width="149">
						&nbsp;
					</th>
					<td width="168">
						&nbsp;
					</td>
				</tr>
				<tr>
					<th>
						Storage Location
					</th>
					<td colspan="3">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="20" style="padding: 0px;">
									<input type="radio" name="Station" id="radio2" value="1"
										checked="checked" />
								</td>
								<td>
									Work Station
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<th>
						&nbsp;
					</th>
					<td colspan="3">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="20" style="padding: 0px;">
									<input type="radio" name="Station" id="radio" value="2" />
								</td>
								<td>
									<s:select  name="sfr"  list="storageList" listValue="name" listKey="name"/>
								</td>
								<td width="50">
									<!--<select name="sName" style="width: 40px;">
										<option value="1">
											1
										</option>
										<option value="2">
											2
										</option>
										<option value="3">
											3
										</option>
										<option value="4">
											4
										</option>
										<option value="5">
											5
										</option>
									</select>
								--></td>
								<th width="30"><!--
									Layer
								--></th>
								<td>
									<!--<select name="ly" style="width: 40px;">
										<option value="1">
											1
										</option>
										<option value="2">
											2
										</option>
										<option value="3">
											3
										</option>
										<option value="4">
											4
										</option>
										<option value="5">
											5
										</option>
									</select>
								--></td>
								<th width="30"><!--
									Box
								--></th>
								<td>
									<!--<select name="bx" style="width: 40px;">

										<option value="1">
											1
										</option>
										<option value="2">
											2
										</option>
										<option value="3">
											3
										</option>
										<option value="4">
											4
										</option>
										<option value="5">
											5
										</option>
									</select>
								--></td>
								<th width="31"><!--
									Well
								--></th>
								<td>
									<!--<select name="wl" style="width: 40px;">
										<option value="1">
											1
										</option>
										<option value="2">
											2
										</option>
										<option value="3">
											3
										</option>
										<option value="4">
											4
										</option>
										<option value="5">
											5
										</option>
									</select>
								--></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<th>
						Receiving Note
					</th>
					<td colspan="3">
						<input name="note" type="text" class="NFText" value="" size="85" />
					</td>
				</tr>
				<tr>
					<th>
						Date to Receive
					</th>
					<td width="171">
						<input name="reveivingDate" type="text" class="NFText"
							value='<fmt:formatDate value="<%=new java.util.Date() %>" pattern="yyyy-MM-dd hh:mm:ss"/>' size="20"
							readonly="readonly" />
					</td>
					<th>
						Received By
					</th>
					<td>
						<input name="loginName" type="text" class="NFText"
							value="${loginName }" size="20" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td height="100" colspan="4">
						<div align="center">
							<input type="submit" name="Submit" class="style_botton"
								value="Confirm" />
							&nbsp;&nbsp;
							<input type="button" name="Submit622" value="Cancel"
								class="style_botton"
								onclick="parent.$('#purchaseOrderReceiving').dialog('close');" />
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
