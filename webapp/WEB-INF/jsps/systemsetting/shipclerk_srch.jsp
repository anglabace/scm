<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}tab-view.js"></script>
<link href="${global_css_url}tab-view.css" rel="stylesheet"
	type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}TabbedPanels.js"></script>
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
	type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}newwindow.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
</head>
<script language="javascript" type="text/javascript">
	$(document).ready(function() { 
		var msgs = $("#msg").val();
		if (msgs != "") {
			alert(msgs);
		}
	});
</script>
<body class="content" style="background-image: none;">
	<input type="hidden" name="msg" id="msg" value="${msg}" />
	<div class="scm">
		<div class="title_content">
			<div class="title_new">
				<a href="javascript:void(0);"
					onclick="toggleShowMorea('total_title');" id="total_titleItem"><img
					src="${global_url}images/arrow1.jpg" />
				</a>Shipping Clerk Management
			</div>
		</div>
		<div class="search_box" id="total_title">
			<div class="single_search">
				<form action="shipping_clerks_srch.action" method="get"
					target="method_iframe">
					<table cellspacing="0" cellpadding="0" border="0"
						class="General_table">
						<tbody>
							<tr>
								<td align="center">Assigned Product/Service</td>
								<td><select name="assign">
										<option value="">Please select</option>
										<s:iterator status="allcls" value="#request.allcls">
											<option
												value="<s:property value="key"/>:<s:property value="value"/>">
												<s:property value="value" />
											</option>
										</s:iterator>
								</select>
								</td>
								<!--<td align="right">
          Warehouse
          </td>
          <td width="120">
          <span class="single_search_css">
            <select name="warehourse">
              <option selected="selected" value="">GenScript US</option>
              <option value="">GenScript China</option>
            </select>
          </span>
         </td>
          -->
								<td width="100" align="right">Clerk Function</td>
								<td width="120"><select name="filter_LIKES_clerkFunction">
										<option>Receiver</option>
										<option>Picker</option>
										<option>Packer</option>
										<option>Picker/Packer</option>
										<option selected="selected" value="">All</option>
								</select>
								</td>
								<td width="60" align="right">Status</td>
								<td><select name="filter_EQS_status">
										<option value="">All</option>
										<option value="ACTIVE">ACTIVE</option>
										<option value="INACTIVE">INACTIVE</option>
								</select>
								</td>
							</tr>
							<tr>
								<td align="center">
									<!--Assigned Product/Service-->
								</td>
								<td>
									<!--<select name="assign">
                  <s:iterator status="allcls" value="#request.allcls">
                      <option value="<s:property value="key"/>:<s:property value="value"/>"><s:property value="key"/></option>
                  </s:iterator>
          </select>--></td>
								<th>&nbsp;</th>
								<td>&nbsp;</td>
								<th>&nbsp;</th>
								<td align="center">&nbsp;</td>
							</tr>
							<tr>
								<td align="center" colspan="6"><input type="submit"
									class="search_input" value="Search" name="Search" />
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
	<div class="input_box">
		<table width="1010" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<div style="margin-right: 17px;">
						<iframe id="method_iframe" name="method_iframe"
							src="shipping_clerks_srch.action" width="100%" height="560"
							frameborder="0" scrolling="no"></iframe>
					</div></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
		var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
	</script>

</body>
</html>
