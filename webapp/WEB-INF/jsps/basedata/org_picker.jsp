<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript"
	src="${global_js_url}scm/config.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>

<script type="text/javascript">
	function hideen() {
		document.all.div1.style.display = "";
	}
	function hideene() {
		document.getElementById("subenter").style.display = "none";
		document.getElementById("subcancel").style.display = "block";
		document.getElementById("txt2").style.display = "block";
	}
	function hideenc() {
		document.getElementById("subenter").style.display = "block";
		document.getElementById("subcancel").style.display = "none";
		document.getElementById("txt2").style.display = "none";
	}
</script>
<style type="text/css">
<!--
.General_table {
	margin: 15px auto;
}
-->
</style>
<title>Insert title here</title>
</head>
<body>

	<input type="hidden" name="disableNew" value="${disableNew}" />
	<table width="600" border="0" cellpadding="0" cellspacing="0"
		class="General_table">
		<tr>
			<form method="post" action="basedata/org_picker.action">
				<td width="37">Name</td>
				<td width="253"><label> <input name="filter_LIKES_name"
						value="${param.filter_LIKES_name}" type="text" class="NFText"
						size="40" /> <input type="hidden" name="shippingInput"
						id="shippingInput" value="${shippingInput}" /> </label>
				</td>
				<td width="311" height="40"><input type="submit" name="Submit"
					class="style_botton" value="Search" />
				</td>

			</form>
		</tr>
		<tr>
			<td colspan="3">
				<table width="580" border="0" cellpadding="0" cellspacing="0"
					class="list_table">

					<tr>
						<th width="25">&nbsp;</th>
						<th width="143">Name</th>
						<th width="56">Status</th>
						<th width="168">Description</th>
						<th style="border-right: 1px solid #ccc;">Address</th>
					</tr>

				</table>
				<div style="width: 597px; height: 170px; overflow: scroll;">
					<table width="580" border="0" cellpadding="0" cellspacing="0"
						class="list_table">
						<s:iterator value="orgList" id="oneorg">
							<tr>
								<td width="25"><input name="cust_org_match" type="radio"
									value="${oneorg.orgId}:${oneorg.name}" />
								</td>
								<td width="143"><a
									href="organization!edit.action?id=${oneorg.orgId}&operation_method=view"
									target="_blank">${oneorg.name}&nbsp;</a>
								</td>

								<td width="56"><s:if test='activeFlag == "Y"'>ACTIVE</s:if>
									<s:else>INACTIVE</s:else></td>
								<td width="168">${oneorg.description}&nbsp;</td>
								<td>${oneorg.addrLine1}&nbsp; ${oneorg.state}&nbsp;
									${oneorg.country}&nbsp;</td>
								<input type="hidden" name="addrLine1" id="${oneorg.orgId}_addr1"
									value="${oneorg.addrLine1}" />
								<input type="hidden" name="country" id="${oneorg.orgId}_country"
									value="${oneorg.country}" />
								<input type="hidden" name="state" id="${oneorg.orgId}_state"
									value="${oneorg.state}" />
								<input type="hidden" name="addrLine2" id="${oneorg.orgId}_addr2"
									value="${oneorg.addrLine2}" />
								<input type="hidden" name="addrLine3" id="${oneorg.orgId}_addr3"
									value="${oneorg.addrLine3}" />
								<input type="hidden" name="description"
									id="${oneorg.orgId}_description" value="${oneorg.description}" />
								<input type="hidden" name="categoryId"
									id="${oneorg.orgId}_categoryId" value="${oneorg.categoryId}" />
								<input type="hidden" name="typeId" id="${oneorg.orgId}_typeId"
									value="${oneorg.typeId}" />
								<input type="hidden" name="langCode"
									id="${oneorg.orgId}_langCode" value="${oneorg.langCode}" />
								<input type="hidden" name="web" id="${oneorg.orgId}_web"
									value="${oneorg.web}" />
								<input type="hidden" name="activeFlag"
									id="${oneorg.orgId}_activeFlag" value="${oneorg.activeFlag}" />
								<input type="hidden" name="shippingInput" id="shippingInput"
									value="${shippingInput}" />
							</tr>
						</s:iterator>
					</table>
				</div></td>

		</tr>
		<tr>
			<td colspan="3">
				<div class="grayr">
					<jsp:include page="/common/db_pager.jsp">
						<jsp:param value="${ctx}/org_picker.action" name="moduleURL" />
					</jsp:include>
				</div> <br /> <s:if
					test="shippingInput!=null&&shippingInput==\"shippingInput\"">
					<input type="button" value="Select" class="style_botton"
						onclick="getPickOrgShipping();" style="margin-left: 240px;" />
                &nbsp;&nbsp;<input type="button" class="style_botton"
						value="Cancel" onclick="closePickOrgShipping();" />
				</s:if> <s:else>
					<input type="button" value="Select" class="style_botton"
						onclick="getPickOrg('cust_org_match', 'cust_org_id_other', 'cust_org_new');"
						style="margin-left: 240px;" />
                &nbsp;&nbsp;<input type="button" class="style_botton"
						value="Cancel" onclick="closePickOrg();" />
				</s:else></td>
		</tr>
	</table>

	<script language="javascript" type="text/javascript">
		function toggleDisplay() {
			var toggleButton = arguments[0];
			var divId = arguments[1];
			if ($('#' + divId).css('display') == 'none') {
				$('#' + divId).css('display', '');
				$('#cust_org_id_other').get(0).checked = true;

				// set all radio button not checked
				$("input:radio").each(function() {
					this.checked = false;
				});

				toggleButton.value = "Cancel";
			} else {
				$('#' + divId).css('display', 'none');
				$('#cust_org_id_other').get(0).checked = false;
				toggleButton.value = "Enter New";
			}
		}

		function switchCheckBox() {
			var other_flag = arguments[0];
			var new_value = arguments[1];
			if ($('#' + other_flag).attr('checked') == true) {
				$('#' + new_value).attr('disabled', false);

			} else {
				$('#' + new_value).attr('disabled', true);
			}
		}

		function getPickOrg() {
			var myid = arguments[0]; // radio name
			var other_flag = arguments[1]; // other flag if no matched
			var new_org = arguments[2]; // new organization typed
			var orgIdReturn = '';
			var orgNameReturn = '';
			var orgAdd1 = '';
			var orgAdd2 = '';
			var orgAdd3 = '';
			var description = '';
			var categoryId = '';
			var typeId = '';
			var langCode = '';
			var web = '';
			var activeFlag = '';
			var hasWarning = false;
			var orgCountry = '';
			var orgState = '';
			if ($("#" + other_flag).attr("checked") === true) {

				if ($("#" + new_org).attr('value') == '') {
					alert("Please type a new organization in the textbox!!!");
					hasWarning = true;
				} else {
					orgIdReturn = '';
					orgNameReturn = $("#" + new_org).attr('value');
				}
			} else {
				if ($("input:radio").length == 0) {
					alert("No matched!! please click the checkbox and add new organization");
					hasWarning = true;
				} else {
					// set parent element
					if ($("input:radio[checked=true]").length == 0) {
						alert("Please select one matched organization!!!");
						hasWarning = true;
					} else {
						var orgArray = $("input:radio[checked=true]").attr(
								'value').split(':', 2);
						var orgid = orgArray[0];
						var orgname = orgArray[1];
						// var countrycode = orgArray[2];
						orgIdReturn = orgid;
						orgNameReturn = orgname;
						orgAdd1 = $("#" + orgid + "_addr1").val();
						orgAdd2 = $("#" + orgid + "_addr2").val();
						orgAdd3 = $("#" + orgid + "_addr3").val();
						description = $("#" + orgid + "_description").val();
						categoryId = $("#" + orgid + "_categoryId").val();
						typeId = $("#" + orgid + "_typeId").val();
						langCode = $("#" + orgid + "_langCode").val();
						web = $("#" + orgid + "_web").val();
						activeFlag = $("#" + orgid + "_activeFlag").val();
						orgCountry = $("#" + orgid + "_country").val();
						orgState = $("#" + orgid + "_state").val();
					}
				}
			}

			if (hasWarning === true)
				return false;

			if ((typeof dataHolderWin) != 'undefined') {
				// transmit the data from iframe to top window
				dataHolderWin.jQuery.data(dataHolderWin.document.body,
						'orgPickerData', orgIdReturn + "::" + orgNameReturn
								+ "::" + orgAdd1 + "::" + orgAdd2 + "::"
								+ orgAdd3 + "::" + description + "::"
								+ categoryId + "::" + typeId + "::" + langCode
								+ "::" + web + "::" + activeFlag + "::"
								+ orgCountry + "::" + orgState);

				// alert(jQuery.data(top, 'orgPickerData'));
				//  alert("11");//触发这个事件！改变country 的值。

				dataHolderWin.$("#" + self.name + "Window").dialog("close");
			} else {
				alert("Warning: organization Picker container is not loaded!\n(modules/util/templates/organization_picker.tpl)!");
			}
		}

		function getPickOrgShipping() {
			if ($("input:radio").length == 0) {
				alert("No matched!! please click the checkbox and add new organization");
				hasWarning = true;
			} else {
				// set parent element
				if ($("input:radio[checked=true]").length == 0) {
					alert("Please select one matched organization!!!");
					hasWarning = true;
				} else {
					var orgArray = $("input:radio[checked=true]").attr('value')
							.split(':', 2);
					//var name = orgArray.split(",");
					parent.$("#organizationInput").val(orgArray[1]);
					parent.$("#organization").dialog("close");
				}
			}

		}

		function closePickOrg() {
			if ((typeof dataHolderWin) != 'undefined') {
				dataHolderWin.$("#" + self.name + "Window").dialog("close");

			}
		}

		function closePickOrgShipping() {

			parent.$("#organization").dialog("close");

		}
	</script>
</body>
</html>