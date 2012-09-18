<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript"
	src="${global_js_url}SpryTabbedPanels.js"></script>
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" language="javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/jquery.dialog.all.js"
	type="text/javascript"></script>
<script src="${global_js_url}initlefttop.js" type="text/javascript"></script>
<script language="javascript">
	function checkStr() {
		var quoteNo = document.getElementById("filterOrderNo").value.replace(
				/(^\s*)|(\s*$)/g, "");
		var custNo = document.getElementById("filterCustNo").value.replace(
				/(^\s*)|(\s*$)/g, "");
		var form = document.getElementById("searchForm");
		document.getElementById("filterOrderNo").value = quoteNo;
		document.getElementById("filterCustNo").value = custNo;
		form.submit();
	}

	$(document)
			.ready(
					function() {
						$('#ChargeRefundID')
								.click(
										function() { 
											/*  check that has one select.. */
											var orderNO = ""; 
											$("#srchCustAct_iframe")
													.contents()
													.find("input:radio")
													.each(
															function() {
																if (this.checked) {
																	orderNO = this.value;
																}

															});
											if (orderNO == "" || orderNO ==null) {
												alert("Please select one Order first!");
												return;
											}
											var hrefurl = "${ctx}/customer/cust_credit_card!input.action?orderNo="
													+ orderNO;
											window.location.href = hrefurl;
										});

					});
</script>
<script language="javascript">
	function toggleShowMore_img(obj, divID) {
		var oId = document.getElementById(divID);
		if (obj.src.indexOf('arrow1.jpg') > 0) {
			obj.src = "${global_url}images/arrow.jpg";
			oId.style.display = "none";
		} else {
			obj.src = "${global_url}images/arrow1.jpg";
			oId.style.display = "block";
		}
	}
</script>
</head>
<body class="content" style="background-image: none;">
	<div class="scm">
		<div class="title_content">
			<div
				style="padding-left: 20px; color: #5579A6; vertical-align: middle;">
				<img src="${global_url}images/arrow1.jpg"
					style="width: 16px; height: 17px; vertical-align: middle;"
					onclick="toggleShowMore_img(this,'search_box1');" />&nbsp;Credit
				Card Charger
			</div>
		</div>
		<div class="search_box" id="search_box1" style="display: block;">
			<div id="TabbedPanels1" class="TabbedPanels">
				<div class="TabbedPanelsContentGroup">
					<div style="display: block;"
						class="TabbedPanelsContent TabbedPanelsContentVisible">
						<form method="get" action="customer/cust_credit_card.action"
							target="srchCustAct_iframe" id="searchForm">
							<table border="0">
								<tr>
									<td align="right">Order No</td>
									<td width="150"><input name="filter_EQI_orderNo"
										id="filterOrderNo" class="NFText" size="20" type="text" /></td>
									<td align="right">Customer No</td>
									<td width="150"><input name="filter_EQI_custNo"
										id="filterCustNo" class="NFText" size="20" type="text" /></td>
								</tr>
							</table>
							<table width="725">
								<tr>
									<td height="26">&nbsp;</td>
								</tr>
							</table>

							<table width="725">
								<tr>
									<th><input type="button" value="Search"
										class="search_input" onclick="checkStr();" /></th>
								</tr>
							</table>

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="input_box">
		<div class="content_box">
			<iframe id="srchCustAct_iframe" name="srchCustAct_iframe"
				src="customer/cust_credit_card.action" width="100%" height="480"
				frameborder="0" scrolling="no"></iframe>
		</div>
	</div>
	<div class="button_box">
		<input name="Submit52" value="Charge or Refund" class="search_input2"
			type="button" id="ChargeRefundID" />
	</div>
	<script type="text/javascript">
		var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
	</script>
</body>
</html>