<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/common/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css"
	rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}newwindow.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1"
	type="text/javascript"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.dialog.all.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}util/util.js"></script>
<script type="text/javascript">
	$(function() {
		var isSaved = false;
		$('#zoneForm').validate({
			errorClass : "validate_error",
			highlight : function(element, errorClass) {
				$(element).addClass(errorClass);
			},
			unhighlight : function(element, errorClass, validClass) {
				$(element).removeClass(errorClass);
			},
			invalidHandler : function(form, validator) {
				$.each(validator.invalid, function(key, value) {
					alert(value);
					$(this).find("name=[" + key + "]").focus();
					return false;
				});
			},
			errorPlacement : function(error, element) {
			}
		});

		$("#save_btn")
				.click(
						function() {

							var formStr = $('#zoneForm').serialize();
							var countryCode_sels = $("#countryCode_sel").val();
							var stateCode_sels = $("#stateCode_sel").val();
							/* if (countryCode_sels == "") {
								alert("Please select a country!");
								return false;
							}
							if (stateCode_sels == "") {
								alert("Please select a state!");
								return false;
							} */
							$('#save_btn').attr("disabled", true);
							var sessionId2 = $("#sessionId").val();
							 
							$
									.ajax({
										type : "POST",
										url : "sales_territories!saveZone.action",
										data : formStr,
										dataType : 'json',
										success : function(data, textStatus) {
											if (hasException(data)) {
												$('#save_btn').attr("disabled",
														false);
											} else { 
												alert(data.message);
												isSaved = true;
												location.href = "sales_territories!load.action?sessionId="
														+ sessionId2
														+ "&referURL=select"; 
											}

										},
										error : function(xhr, textStatus) {
											alert("System error! Please contact system administrator for help.");
											$('#save_btn').attr("disabled",
													false);
										}
									});
						});//end of {$("#save_btn").click};  
	});

	function zoneSelect() {
		var countryId = $("#countryCode_sel").find("option[selected=true]")
				.attr("name");
		if (countryId == '') {
			return;
		}
		var formStr = "countryId=" + countryId;
		$
				.ajax({
					type : "POST",
					url : "sales_territories!selectCountry.action",
					data : formStr,
					dataType : 'json',
					success : function(data, textStatus) {
						if (hasException(data)) {
							$("#countryCode_sel").attr("disabled", true);
						} else {
							$("#stateCode_sel").empty();
							var option = "<option value=''>please select...</option>";
							// alert(data.stateList.length);
							$("#stateCode_sel").append(option);
							if (data.stateList != null
									&& data.stateList.length > 0) {
								for ( var i = 0; i < data.stateList.length; i++) {
									var option = "<option value='"+data.stateList[i].stateCode+"'>"
											+ data.stateList[i].name
											+ "</option>";
									$("#stateCode_sel").append(option);
								}
							}
						}
					},
					error : function(xhr, textStatus) {
						alert("System error! Please contact system administrator for help.");
						$("#countryCode_sel").attr("disabled", true);
					}
				});
	}
	function continentSelect() {
		var continent = $("#continent").val();
		var formStr = "continent=" + continent;
		$
				.ajax({
					type : "POST",
					url : "sales_territories!selectContinent.action",
					data : formStr,
					dataType : 'json',
					success : function(data, textStatus) {
						if (hasException(data)) {
							$("#continent").attr("disabled", true);
						} else {
							$("#countryCode_sel").empty();
							$("#stateCode_sel").empty();
							var option = "<option value='' name=''>please select...</option>";
							$("#stateCode_sel").append(option);
							$("#countryCode_sel").append(option);
							for ( var i = 0; i < data.countryList.length; i++) {
								var option = "<option value='"+data.countryList[i].countryCode+"' name='"+data.countryList[i].countryId+"'>"
										+ data.countryList[i].name
										+ "</option>";
								$("#countryCode_sel").append(option);
							}
						}
					},
					error : function(xhr, textStatus) {
						alert("System error! Please contact system administrator for help.");
						$("#continent").attr("disabled", true);
					}
				});
	}
</script>
</head>

<body class="content" style="background-image: none;">
	<div class="scm">
		<div class="title_content">
			<div class="title">
				Zone
				<s:if test="salesTerritoryAssignment.countryCode!=null">-<s:property
						value="salesTerritoryAssignment.countryCode" />
				</s:if>
			</div>
		</div>
		<s:form id="zoneForm" Class="niceform">
			<div class="input_box">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="General_table">
					<tr>
						<th width="140">Continent</th>
						<td width="424"><s:if test="dropDownMap['CONTINENT']!=null">
								<s:select name="salesTerritoryAssignment.continent"
									id="continent" list="dropDownMap['CONTINENT']" listKey="value"
									listValue="text" cssStyle="width:157px;"
									onchange="continentSelect();">
								</s:select>
							</s:if> <s:else>
								<select name="salesTerritoryAssignment.continent" id="continent"
									onchange="continentSelect();">
								</select>
							</s:else>
						</td>
						<th width="92">&nbsp;</th>
						<td width="346">&nbsp;</td>
					</tr>
					<tr>
						<th width="140">Country name</th>

						<td width="424"><s:if test="countryList!=null">
								<s:select id="countryCode_sel" list="countryList"
									name="salesTerritoryAssignment.countryCode"
									listKey="countryCode" listValue="name" headerKey=""
									headerValue="please select..." onchange="zoneSelect();">
								</s:select>
							</s:if> <s:else>
								<select id="countryCode_sel"
									name="salesTerritoryAssignment.countryCode"
									onchange="zoneSelect();">
									<option value="">please select...</option>
								</select>
							</s:else>
						</td>
						<th width="92">State/Province</th>
						<td width="346"><s:if test="stateList!=null">
								<s:select id="stateCode_sel" list="stateList"
									name="salesTerritoryAssignment.stateCode" listKey="stateCode"
									listValue="name" headerKey="" headerValue="please select...">
								</s:select>
							</s:if> <s:else>
								<select id="stateCode_sel"
									name="salesTerritoryAssignment.stateCode">
									<option value="">please select...</option>
								</select>
							</s:else>
						</td>
					</tr>
					<tr>
						<th width="140">From Zip</th>
						<td width="424"><s:textfield
								name="salesTerritoryAssignment.fromZip" id="fromZip"
								Class="NFText" size="20"></s:textfield>
						<th width="92">To Zip</th>
						<td width="346"><s:textfield
								name="salesTerritoryAssignment.toZip" id="toZip" Class="NFText"
								size="20"></s:textfield>
						</td>
					</tr>
					<tr>

						<th valign="top">Modified Date</th>
						<td><s:textfield name="salesTerritoryAssignment.modifyDate"
								id="modifyDate" Class="NFText" size="20" readonly="true">
								<s:param name='value'>
									<s:date name="salesTerritoryAssignment.modifyDate"
										format="yyyy-MM-dd" />
								</s:param>
							</s:textfield>
						</td>
						<th>Modified By</th>
						<td valign="top"><s:textfield
								name="salesTerritoryAssignment.modifiedName" id="modifiedName"
								Class="NFText" size="25" readonly="true"></s:textfield> <s:hidden
								name="salesTerritoryAssignment.modifiedBy"></s:hidden>
						</td>
					</tr>
					<tr>
						<th valign="top">&nbsp;</th>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td valign="top">&nbsp;</td>
					</tr>
				</table>

			</div>
			<div class="button_box">
				<input type="button" id="save_btn" name="Submit123" value="Save"
					class="search_input" /> <input type="button" name="Submit124"
					value="Cancel" class="search_input"
					onclick="javascript:history.go(-1)" />
			</div>
			<s:hidden name="salesTerritoryAssignment.createdBy"></s:hidden>
			<s:hidden name="salesTerritoryAssignment.creationDate"></s:hidden>
			<s:hidden name="salesTerritoryAssignment.assignId" id="assignId"></s:hidden>
			<s:hidden name="zoneSessionId" id="zoneSessionId"></s:hidden>
			<s:hidden name="sessionId" id="sessionId"></s:hidden>
		</s:form>
	</div>
</body>
</html>