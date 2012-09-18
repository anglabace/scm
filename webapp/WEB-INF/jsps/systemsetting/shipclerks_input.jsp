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
<script language="javascript" type="text/javascript"
	src="${global_js_url}newwindow.js"></script>
<style type="text/css">
<!--
.content .scm #dhtmlgoodies_tabView1 .dhtmlgoodies_aTab .General_table fieldset
	{
	margin: 4px;
}
-->
</style>
<script language="JavaScript" type="text/javascript">
	function assign(obj) {
		var clerkId = $("#sessionClerkId").val();
		if (!clerkId) {
			clerkId = $("#clerkId").val();
		}
		if (!obj.val()) {
			alert("Please select Product/Service!");
			obj.focus();
			return;
		}
		var reqUrl = "shipping_clerks_srch!assign.action?assignValue="
				+ obj.val() + "&sessionClerkId=" + clerkId;
		//alert(reqUrl);
		$.ajax({
			type : "POST",
			url : reqUrl,
			success : function(data) {
				// obj.remove($(obj).find(":selected"));
				$("#listShip").html(data);
				$(obj).find("option:selected").each(function() {
					$(this).remove();
				});
			}

		});
	}
	function unassign() {
		var clerkId = $("#sessionClerkId").val();
		if (!clerkId) {
			clerkId = $("#clerkId").val();
		}
		var checkedObj = $("input[name='assigned']:checked");
		var checkedIds = "";
		for (i = 0; i < checkedObj.length; i++) {
			checkedIds += $(checkedObj[i]).val() + ",";
		}
		if (checkedIds == "") {
			alert("Please select the Unassign Product/Service.");
			return;
		}
		var reqUrl = "shipping_clerks_srch!unassign.action?unassignValue="
				+ checkedIds.substr(0, checkedIds.length - 1)
				+ "&sessionClerkId=" + clerkId;
		$.ajax({
			type : "POST",
			url : reqUrl,
			success : function(data) {
				//location.reload();
				$("#listShip").html(data);
			}

		});

	}
   
</script>
</head> 
<body class="content">

	<div id="frame12" style="display: none;" class="hidlayer1">
		<iframe id="hidkuan" name="hidkuan"
			src="emarketing_group_srch!getClerkUser.action" width="668"
			height="425" frameborder="0" allowtransparency="true"></iframe>
	</div>
	<div class="scm">
		<div class="title_content">
			<div class="title">Shipping Clerk Management</div>
		</div>
		<form id="shipingClerkForm" action="shipping_clerks_srch!save.action" method="post">
			<div class="input_box">
				<div class="content_box">
					<input type="hidden" name="sessionClerkId" id="sessionClerkId"
						value="${sessionClerkId}" />
					<table border="0" cellpadding="0" cellspacing="0"
						class="General_table">
						<tr>
							<th width="160">Name</th>
							<td><input name="username" id="username" type="text"
								class="NFText" value="${shipClerk.name} " size="25"
								readonly="readonly" /><a href="javascript:void(0)"
								onclick="window.openiframe('emarketing_group_srch!getClerkUser.action',680,400)"><img
									src=" ${global_image_url}search.gif" width="16" height="16"
									align="absmiddle" /> </a></td>
							<th width="150">Employee ID</th>
							<td><input id="employeeId" name="shipClerk.employeeId"
								value="${shipClerk.employeeId}" type="text" class="NFText"
								size="25" readonly="readonly" /></td>
							<input id="clerkId" name="shipClerk.clerkId"
								value="${shipClerk.clerkId}" type="hidden" class="NFText"
								size="25" readonly="readonly" />
						</tr>
						<tr>
							<th width="160">Clerk Function</th>
							<td><select name="shipClerk.clerkFunction"
								style="width: 157px;">
									<s:if test="shipClerk.clerkFunction==\"All\"">
										<option value="All" selected="selected">All</option>
									</s:if>
									<s:else>
										<option value="All">All</option>
									</s:else>
									<s:if test="shipClerk.clerkFunction==\"Receiver\"">
										<option value="Receiver" selected="selected">Receiver</option>
									</s:if>
									<s:else>
										<option value="Receiver">Receiver</option>
									</s:else>
									<s:if
										test="shipClerk.clerkFunction==\"Picker/Packer/Shipping\"">
										<option value="Picker/Packer/Shipping" selected="selected">Picker/Packer/Shipping</option>
									</s:if>
									<s:else>
										<option value="Picker/Packer/Shipping">Picker/Packer/Shipping</option>
									</s:else>

							</select></td>
							<th width="150">Supervisor</th>
							<td><select name="shipClerk.supervisor"
								style="width: 157px;">
									<s:iterator status="supervisor" value="supervisor">

										<s:if test="shipClerk.supervisor==userId">
											<option value="<s:property value="userId"/>"
												selected="selected">
												<s:property value="employee.employeeName" />
											</option>
										</s:if>
										<s:else>
											<option value="<s:property value="userId"/>">
												<s:property value="employee.employeeName" />
											</option>
										</s:else>
									</s:iterator>
							</select></td>
						</tr>
						<tr>
							<th rowspan="2" valign="top">Comment</th>
							<td rowspan="2"><textarea name="shipClerk.comment"
									class="content_textarea2">${shipClerk.comment }</textarea></td>
							<th>Status</th>

							<td><select name="shipClerk.status" style="width: 157px;">
									<s:if test="shipClerk.status==\"ACTIVE\"">
										<option value="ACTIVE" selected="selected">ACTIVE</option>
									</s:if>
									<s:else>
										<option value="ACTIVE">ACTIVE</option>
									</s:else>
									<s:if test="shipClerk.status==\"INACTIVE\"">
										<option value="INACTIVE" selected="selected">INACTIVE</option>
									</s:if>
									<s:else>
										<option value="INACTIVE">INACTIVE</option>
									</s:else>

							</select></td>
						</tr>
						<tr>
							<th>&nbsp;</th>
							<td>&nbsp;</td>
						</tr>

						<tr>
							<th>Modified Date</th>
							<td><input name="modifyDate" type="text" class="NFText"
								value="<s:date name='shipClerk.modifyDate' format='yyyy-MM-dd' />"
								size="25" readonly="readonly" /></td>
							<th>Modified By</th>
							<td><input name="userName" type="text" class="NFText"
								value="${userName}" size="25" readonly="readonly" /></td>
						</tr>
						<tr>
							<th>&nbsp;</th>

							<td>&nbsp;</td>
							<th>&nbsp;</th>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<th>&nbsp;</th>
							<td>Product/Service Type to be Processed</td>
							<th>&nbsp;</th>

							<td>&nbsp;</td>
						</tr>
						<tr>
							<th>&nbsp;</th>
							<td> <select id="typeName" name="typeName"
								style="width: 157px;">
									<s:iterator status="allcls" value="#request.allcls">
										<option value="<s:property value="key"/>">
											<s:property value="value" />
										</option>
									</s:iterator>
							</select> <input type="button" name="Submit3" class="style_botton"
								value="Assign" onclick="assign($('#typeName'))" /> <input
								type="button" name="Submit" class="style_botton"
								value="Unassign" onclick="unassign()" /></td>
							<th>&nbsp;</th>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<th>&nbsp;</th>
							<td><table width="100%" border="0" cellspacing="0"
									cellpadding="0" id="listShip">${listStr}
								</table>
							</td>
							<th>&nbsp;</th>
							<td>&nbsp;</td>
						</tr>

					</table>
				</div>
			</div>
        <input type="hidden" name="operation_method" value="${operation_method}" />
			<script type="text/javascript">
				//initTabs('dhtmlgoodies_tabView1',Array('Package Lines'),0,998,320);
			</script>
			<div class="button_box">
				<input type="submit" name="Submit123" id="ShipingADDTrriger"
					value="Save"
					class="search_input" /> <input type="submit" name="Submit124"
					value="Cancel" class="search_input"
					onclick="window.location.href='shipping_clerk.html'" />
			</div>
		</form>
	</div>
</body>
</html>

