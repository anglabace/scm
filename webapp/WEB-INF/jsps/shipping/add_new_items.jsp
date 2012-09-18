<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Return Order</title>
		<link href="stylesheet/scm.css" rel="stylesheet" type="text/css" />
		<link href="stylesheet/table.css" rel="stylesheet" type="text/css" />

		<link href="stylesheet/openwin.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			function toSearchItems(){
				var orderNo=document.getElementById("orderNo").value;
				if(orderNo==null||orderNo==""){
					alert("Order Number cannot null");
					return false;
				}
				location.href='shipping!searchItems.action?orderNo='+orderNo;
			}
		</script>
	</head>

	<body>
		<table width="100%" border="0" cellspacing="3" cellpadding="0"
			bgcolor="#96BDEA">
			<tr>
				<td bgcolor="#FFFFFF">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="39" align="left" valign="top" background="images/header_bg.gif"><div class="line_l_new">Add New Items To Pack</div>
          <div class="line_r_new" onclick="javascript: window.parent.closeiframe();"><img src="images/w_close.gif"  />Close</div></td>
							
						</tr>
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td valign="top">
											<br />
											<br />
											<br />
											<table width="100%" border="0" cellpadding="0"
												cellspacing="0" class="General_table">
												<tr>
													<th>
														Enter Order Number
													</th>
													<td>
														<input name="orderNo" type="text" class="NFText"
															id="orderNo" size="38" />
													</td>
												</tr>
												<tr>
													<td colspan="4" valign="top">
														&nbsp;
													</td>
												</tr>
												<tr>
													<td height="149" colspan="4" valign="top">
														<div class="botton_box">
															<input name="Submit2" type="submit" class="style_botton" value="Select" onclick="toSearchItems()"/>
                  											<input  type="button" name="close" value="Cancel" class="style_botton" onclick="javascript: window.parent.closeiframe();"   />
														</div>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>

