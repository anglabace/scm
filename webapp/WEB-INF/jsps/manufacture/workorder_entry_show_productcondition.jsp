<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Quality Control</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="${global_js_url}jquery/jquery.js"></script>
	</head>
	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td valign="top">
					<table width="710" border="0" cellpadding="0" cellspacing="0"
						class="General_table" style="margin: 10px auto 0px auto;">
						<tr>
							<td>
								<div class="invoice_title">
									Storage Information
								</div>
							</td>
						</tr>
						<tr>
							<td valign="top">
								<div class="botton_box">
									<table border="0" cellpadding="0" cellspacing="0"
										class="General_table">
										<tr>
											<th width="131">
												Product Form
											</th>
											<td width="191">
												<input name="textfield11" type="text" class="NFText"
													size="35" value="${storageCondition.form}" />
											</td>
											<th width="127">
												Light Protected
											</th>
											<td colspan="2" align="left">
												<select name="select20" style="width: 207px;" id="store_light_sel">
													<option value="Y">
														Yes
													</option>
													<option value="N">
														No
													</option>
												</select>
											</td>
										</tr>
										<tr>
											<th>
												Temperature
											</th>
											<td>
												<input name="textfield11" type="text" class="NFText"
													size="35"  value="${storageCondition.temperature}" />
											</td>
											<th>
												Humidity
											</th>
											<td width="194">
												<input name="textfield26" type="text" class="NFText"
													size="35"  value="${storageCondition.humidity}" />
											</td>
											<td width="63">
												&nbsp;
											</td>
										</tr>
										<tr>
											<th>
												Container
											</th>
											<td>
												<input name="textfield11" type="text" class="NFText"
													size="35"  value="${storageCondition.container}" />
											</td>
											<th>
												&nbsp;
											</th>
											<td>
												&nbsp;
											</td>
											<td>
												&nbsp;
											</td>
										</tr>
										<tr>
											<th>
												Other Requirement
											</th>
											<td colspan="4" align="left">
												<input name="textfield11" type="text" class="NFText"
													size="70"  value="${storageCondition.comment}" />
											</td>
										</tr>
									</table>
									<div class="invoice_title">
										<div align="left">
											Shipping Information
										</div>
									</div>
									<div id="Shipping" class="disp" style="display: block;">
										<table border="0" cellpadding="0" cellspacing="0"
											class="General_table">
											<tr>
												<th width="119">
													Product Form
												</th>
												<td width="214">
													<input name="textfield11" type="text" class="NFText"
														size="35"  value="${shipCondition.form}" />
												</td>
												<th width="109">
													Temperature
												</th>
												<td colspan="2" align="left">
													<input name="textfield27" type="text" class="NFText"
														size="35"  value="${shipCondition.temperature}" />
												</td>
											</tr>
											<tr>
												<th>
													Container
												</th>
												<td>
													<input name="textfield11" type="text" class="NFText"
														size="35"  value="${shipCondition.container}" />
												</td>
												<th>
													Package Type
												</th>
												<td width="223" align="left">
													<select name="select21" style="width: 207px;" id="ship_pkg_sel">
													</select>
												</td>
												<td width="41">
													&nbsp;
												</td>
											</tr>
											<tr>
												<th>
													Other Requirement
												</th>
												<td colspan="4" align="left">
													<input name="textfield11" type="text" class="NFText"
														size="70"  value="${shipCondition.comment}" />
												</td>
											</tr>
										</table>
									</div>
									<input type="button" name="close2" value="Cancel"
										class="style_botton"
										onclick="window.parent.$('#show_product_dlg').dialog('close');" />
								</div>
								<br />
								<br />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<script type="text/javascript">	
		  $("#store_light_sel").val('${storageCondition.lightProtection}');
		  $("#store_light_sel").attr("disabled", true);
		  
		
		  var opt = new Option('${shipCondition.shipPkgType}', '${shipCondition.shipPkgType}');
		  $("#ship_pkg_sel").get(0).options.add(opt);
		  $("#ship_pkg_sel").attr("disabled", true);
		  $("input").attr("readonly", true);
		
		</script>
	</body>
</html>