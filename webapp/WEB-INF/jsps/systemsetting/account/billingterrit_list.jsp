
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
	src="${global_js_url}jquery/jquery.js"></script>
<script language="JavaScript" type="text/javascript">
	$(document).ready(function() {
		$('tr:even >td').addClass('list_td2');
	});
	function cc(e, name) {
		var a = document.getElementsByName(name);
		for ( var i = 0; i < a.length; i++)
			a[i].checked = e.checked;
	}
	//remove checked rows and return to_del id list
	function getDelIds(boxs) {
		var a = document.getElementsByName(boxs);
		var to_del = "";
		var total = a.length;
		for ( var i = (total - 1); i >= 0; i--) {
			if (a[i].checked) {
				to_del += a[i].value + ",";
			}
		}
		return to_del;
	}

	function del_bill_territs() {
		var to_del = getDelIds('territoryId');
		if (to_del == "" || to_del == null) {
			alert("Please select at least one record.");
			return;
		}
		if (!confirm("Are you sure to delete?")) {
			return;
		}

		var reqUrl = "billing_territ!delete.action?territoryIds=" + to_del;
		$.ajax({
			type : "POST",
			url : reqUrl,
			success : function(data) {
				if (data.indexOf("SUCCESS") == 0) {
					if (data.indexOf("##") > 0) {
						var str = data.substr(data.indexOf("##") + 2);
						alert(str + " can not be deleted!");
					} else {
						alert("Deleted successfully.");
					}
					window.location.reload();
				} else {
					alert(data);
					return;
				}
			},
			async : false
		});
	}
</script>
</head>

<body class="content" style="background-image: none;">
	<div class="scm">
		<div class="input_box">
			<div style="margin-right: 17px;">
				<table width="966" border="0" cellspacing="0" cellpadding="0"
					class="list_table2">
					<tr>
						<th width="46">
							<div align="left">
								<input type="checkbox" onclick="cc(this, 'territoryId')" /> <a
									href="javascript:void(0)" onclick="del_bill_territs()"
									title="Delete" rel="gb_page_center[600,  180]"><img
									src="${global_image_url}file_delete.gif" alt="Delete"
									width="16" height="16" border="0" /> </a>
							</div>
						</th>
						<th width="80">Billing Territory Code</th>
						<th width="100">Billing Territory Name</th>
						<th width="100">Description</th>
						<th width="100">Billing Account Code</th>
						<th width="100">Status</th>
					</tr>
				</table>
			</div>
			<div class="list_box" style="height: 340px; width: 983px;">
				<table width="966" border="0" cellspacing="0" cellpadding="0"
					class="list_table2">
					<s:iterator value="page.result">
						<tr>
							<td width="46"><input type="checkbox" value="${territoryId}"
								name="territoryId" />
							</td>
							<td width="80">${territoryCode}&nbsp;</td>
							<td width="100"><a target="_parent"
								href="billing_territ!input.action?sessTerritoryId=${territoryId}&operation_method=edit">${territoryName}&nbsp;</a>
							</td>
							<td width="100">${description}</td>
							<td width="100">${accountCode}</td>
							<td align="center" width="100">${status}&nbsp;</td>
						</tr>
					</s:iterator>
				</table>
			</div>

			<div class="grayr">
				&nbsp;<jsp:include page="/common/db_pager.jsp">
					<jsp:param
						value="${ctx}/systemsetting/account/billing_territ!list.action"
						name="moduleURL" />
				</jsp:include></div>
		</div>
	</div>
	<div class="button_box">
		<input type="submit"
			onclick="parent.location.href='billing_territ!input.action'"
			value="New BillingTerrits" class="search_input" name="Submit" />
	</div>
	<script type="text/javascript">
		var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
	</script>
</body>
</html>
