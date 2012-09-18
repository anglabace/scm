<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order ManagerMent</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css"
	rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" language="javascript"
	src="${global_js_url}SpryTabbedPanels.js"></script>
<script type="text/javascript" language="javascript"
	src="${global_js_url}jquery/jquery.js"></script>

<script src="${global_js_url}jquery/jquery.dialog.all.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js"
	type="text/javascript"></script>
<script
	src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js"
	type="text/javascript"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}show_tag.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}select.js"></script>
<script type="text/javascript" src="${global_js_url}scm/config.js"></script>
<script type="text/javascript" src="${global_js_url}util/util.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}scm/gs.util.js"></script>
<script src="${global_js_url}recordTime.js" type="text/javascript"></script>
<script src="${global_js_url}initlefttop.js" type="text/javascript"></script>
<script type="text/javascript" src="${global_js_url}scm/orgPicker.js"></script>
<script>
	function openc(str1, str2) {
		if (document.getElementById(str1).style.display == "none") {
			document.getElementById(str2).src = "images/ad.gif";
			document.getElementById(str1).style.display = "block";
		} else {
			document.getElementById(str2).src = "images/ar.gif";
			document.getElementById(str1).style.display = "none";
		}
	}
	var orgNameBeforeChange = "";
	var orgIdBeforeChange = "";
	var countryIdNames = [ 'country' ];
	var countryDefaults = [ 'US' ];
	var countryChangeHandlers = [ '' ];
	var stateIdNames = [ 'state' ];
	var stateDefaults = [ '' ];
	var stateChangeHandlers = [ '' ];

	$(function() {
		$("#select_cust").change(function() {
			if ($("#select_cust").val() == 'Custom Date Range') {
				$("#cust_a").show();
			} else {
				$("#cust_a").hide();
			}
		});
		$("input[name='orderFollowupDTO\\.column']").each(
				function() {
					$(this).click(
							function() {
								if ($("#orderFollowupDTO\\.column\\.all").attr(
										"checked")
										&& !$(this).attr("checked")) {
									$("#orderFollowupDTO\\.column\\.all").attr(
											"checked", false);
								}
							});
				});
		$("#save_form").validate({
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
			rules : {
				"orderFollowupDTO.column" : {
					required : true
				}
			},
			messages : {
				"orderFollowupDTO.column" : {
					required : "Please select Output Parameters !"
				}
			},
			errorPlacement : function(error, element) {
			}
		});

		function validData() {

			var flag = 0;
			$("[id$='Check']").each(function() {
				if ($(this).attr("checked")) {
					flag++;
				}
			});
			if (flag < 1) {
				alert("Please select Output Parameters !");
				return false;
			}

			return true;
		}
		var flag = $("#chkAll").attr("checked");//判断全选按钮的状态 

		$("#all")
				.click(
						function() {
							if ($(this).attr("checked")) {
								$("[id$='Check']").each(function() {
									$(this).attr("checked", true);
								});
							} else {
								$("[id$='Check']").each(function() {
									$(this).attr("checked", false);
								});
							}

							$("[id$='Check']")
									.each(
											function() {
												$(this)
														.click(
																function() {
																	if ($("[id$='Check']:checked").length == $("[id$='Check']").length) {
																		$(
																				"#all")
																				.attr(
																						"checked",
																						"checked");
																	} else
																		$(
																				"#all")
																				.removeAttr(
																						"checked");
																});
											});
						});
		$("#FollowUpTrriger").click(function() {
			if ($("#save_form").valid() === false) {
				return false;
			}
			if (!validData())
				return false;
			var formStr = $("#save_form").serialize();
			$('#FollowUpTrriger').attr("disabled", "disabled");
			document.forms[0].action = "tools!Searchout.action";
			document.forms[0].method = "post";
			document.forms[0].submit();
			$("#msg").html("<B>Please wait for searching........</B>");
		//	$('#FollowUpTrriger').attr("disabled", false);
		});
		$("#sdasdsd")
				.click(
						function() {
							if ($("#save_form").valid() === false) {
								return false;
							}
							if (!validData())
								return false;
							var formStr = $("#save_form").serialize();
							$('#sdasdsd').attr("disabled", "disabled");
							document.forms[0].action = "tools!getOrderFollowUpSummaryExcelExport.action";
							document.forms[0].method = "post";
							document.forms[0].target = "operation_list_frame";
							document.forms[0].submit();
							$("#msg").html("<B>Please wait for Excel Export........</B>");
						//	$('#sdasdsd').attr("disabled", false);
						});

		$("#eeeee")
				.click(
						function() {
							if ($("#save_form").valid() === false) {
								return false;
							}
							if (!validData())
								return false;
							var formStr = $("#save_form").serialize();
							$('#eeeee').attr("disabled", "disabled");
							document.forms[0].action = "tools!getOrderFollowUpSummaryPdfExport.action";
							document.forms[0].method = "post";
							document.forms[0].target = "operation_list_frame";
							document.forms[0].submit();
							$("#msg").html("<B>Please wait for Pdf Export........</B>");
							//$('#eeeee').attr("disabled", false);
						});
		$("#org_1Trigger")
				.click(
						function() {
							dataHolderWin.jQuery.data(
									dataHolderWin.document.body, 'disableNew',
									1);
							$('#orgDialogWindow').dialog('open');
							dataHolderWin.jQuery.data(
									dataHolderWin.document.body, 'isGetAddr',
									"true");
							dataHolderWin.jQuery.data(
									dataHolderWin.document.body,
									'isGetOrgOtherInfo', "true");
							dataHolderWin.jQuery.data(
									dataHolderWin.document.body, 'orgAddr',
									"address_1:address_2:address_3");
							dataHolderWin.jQuery
									.data(dataHolderWin.document.body,
											'orgOtherInfo',
											'orgDescription:categoryId:orgType:orgLangCode:orgWeb:orgActive');
							dataHolderWin.jQuery
									.data(dataHolderWin.document.body,
											'orgLoc', self);
							dataHolderWin.jQuery.data(
									dataHolderWin.document.body, 'orgIdStr',
									'orgId');
							dataHolderWin.jQuery.data(
									dataHolderWin.document.body, 'orgNameStr',
									'orgName1:orgName');
						});

	});
</script>
<style type="text/css">
.invoice_title1 {
	margin: 10px 0px 5px 470px;
	padding: 5px 10px;
	border-bottom: 2px solid #C2D5FC;
	color: #5579A6;
	font: bold 14px Arial, Helvetica, sans-serif;
}

.a {
	font: bold Arial, Helvetica, sans-serif;
}

.invoice_title11 {
	margin: 10px 0px 5px 150px;
	padding: 5px 10px;
	border-bottom: 2px solid #C2D5FC;
	color: #5579A6;
	font: bold 14px Arial, Helvetica, sans-serif;
}

.quo {
	padding-top: 15px;
	padding-left: 140px;
}
</style>

</head>

<body class="content">
	<div id="orgDialogWindow" title="Organization Lookup"
		style="visible: hidden"></div>

	<div class="scm">
		<div class="title_content">
			<div class="title">Order Followup</div>
		</div>
		<div class="input_box">
			<div class="content_box">
				<form class="niceform" id="save_form">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<div class="invoice_title">
									<a href="javascript:void(0);"
										onclick="openc('Contact_Info','image1')"><img
										src="images/ad.gif" width="11" height="11" id="image1" /> </a>&nbsp;Filter
									Parameters
								</div>
								<div id="Contact_Info" class="disp" style="display: block;">
									<table border="0" cellpadding="0" cellspacing="0"
										class="General_table">
										<tr>
											<th width="200">Sales Order Confirmation Date</th>
											<td style="padding: 0px;"><s:select
													list="#{'today':'Today', 'week':'This Week', 'month' : 'This Month', 'quarter' : 'This Quarter', 'year':'This Year', '':'--------------------', 'yesterday':'Yesterday', 'lWeek':'Last Week', 'lMonth':'Last Month', 'lQuarter':'last Quarter', 'lYear':'Last Year', 'custom':'Custom Date Range'}"
													headerKey="" headerValue="All"
													id="orderFollowupDTO.confirmationDate"
													name="orderFollowupDTO.confirmationDate"
													value="orderFollowupDTO.confirmationDate"
													cssStyle="width:171px;" />
											</td>
											<td id="dateFromTD" style="display: none; width: 190px">From
												<input name="orderFollowupDTO.dataFrom"
												id="orderFollowupDTO.dataFrom" type="text"
												class="ui-datepicker" style="width: 124px;"
												value="<s:date name="orderFollowupDTO.dataFrom" format="yyyy-MM-dd"/>"
												readonly="readonly" /></td>
											<td id="dateToTD" style="display: none;">To <input
												name="orderFollowupDTO.dataTo" id="orderFollowupDTO.dataTo"
												type="text" class="ui-datepicker" style="width: 124px;"
												value="<s:date name="orderFollowupDTO.dataTo" format="yyyy-MM-dd"/>"
												readonly="readonly" /></td>
											<td style="padding: 0px;">&nbsp;</td>
											<td style="padding: 0px;">&nbsp;</td>
										</tr>
										<tr>
											<th>Order #</th>
											<td width="278"><input name="orderFollowupDTO.orderNo"
												type="text" class="NFText" id="orderFollowupDTO.orderNo"
												size="30" />
											</td>
											<th width="154">Order Status</th>
											<td width="304"><s:select
													name="orderFollowupDTO.orderStatus"
													id="orderFollowupDTO.OrderStatus" cssStyle="width:171px;"
													list="specDropDownList['GET_ALLORDER_STATUS'].dropDownDTOs"
													listKey="code" listValue="name" headerKey=""
													headerValue="ALL"></s:select>
											</td>
										</tr>
										<tr>
											<th>Priority</th>
											<td><s:if
													test="priorityList != null && priorityList.size > 0">
													<s:select cssStyle="width:171px"
														id="orderFollowupDTO.priority"
														name="orderFollowupDTO.priority" list="priorityList"
														listKey="value" listValue="text" headerKey=""
														headerValue="ALL"></s:select>
												</s:if>
											</td>
											<th>Product/Service Type</th>
											<td><select id="orderFollowupDTO.typeName"
												name="orderFollowupDTO.typeName" style="width: 171px;">
													<option value="">All</option>
													<s:iterator status="allcls" value="#request.allcls">
														<option value="<s:property value="key"/>">
															<s:property value="value" />
														</option>
													</s:iterator>
											</select>
											</td>
										</tr>
										<tr>
											<th>Customer #</th>
											<td><input name="orderFollowupDTO.custNo" type="text"
												class="NFText" id="orderFollowupDTO.custNo" size="30" />
											</td>
											<th>Email</th>
											<td><input name="orderFollowupDTO.email" type="text"
												class="NFText" id="orderFollowupDTO.email" size="30" />
											</td>
										</tr>

										<tr>
											<th>Organization</th>
											<td><input name="orderFollowupDTO.orgName" type="text"
												id="orgName1" size="30" class="NFText" readonly="readonly" />
												<input name="orgName" type="hidden" id="orgName" /> <img
												id="org_1Trigger" src="${global_image_url}search.gif"
												width="16" height="16" align="middle" />
											</td>

											<th>Country</th>
											<td><s:select cssStyle="width:171px"
													id="orderFollowupDTO.country"
													name="orderFollowupDTO.country"
													list="specDropDownList['ALL_COUNTRY'].dropDownDTOs"
													listKey="id" listValue="name" headerKey=""
													headerValue="ALL"></s:select>
											</td>
										</tr>
										<tr>
											<th>Sales Manager</th>
											<td><s:select name="orderFollowupDTO.salesManager"
													id="orderFollowupDTO.salesManager" cssStyle="width:171px;"
													list="specDropDownList['GET_ALLSALES_MANAGER'].dropDownDTOs"
													listKey="id" listValue="name" headerKey=""
													headerValue="ALL"></s:select>
											</td>
											<th>Technical Account Manager</th>
											<td><s:select name="orderFollowupDTO.accountManager"
													id="orderFollowupDTO.accountManager"
													cssStyle="width:171px;"
													list="specDropDownList['GET_ALLTECHACCOUNT_MANAGER'].dropDownDTOs"
													listKey="id" listValue="name" headerKey=""
													headerValue="ALL"></s:select></td>
										</tr>
										<tr>
											<th>Project Manager</th>
											<td><s:select name="orderFollowupDTO.projectManager"
													id="orderFollowupDTO.projectManager"
													cssStyle="width:171px;"
													list="specDropDownList['GET_ALLPROJECT_MANAGER'].dropDownDTOs"
													listKey="id" listValue="name" headerKey=""
													headerValue="ALL"></s:select>
											</td>
											<th>Follow Up Status</th>
											<td><s:select name="orderFollowupDTO.followupStatus"
													id="orderFollowupDTO.followupStatus"
													cssStyle="width:171px;"
													list="#{'open':'Open', 'closed':'Closed'}" headerKey=""
													headerValue="ALL"></s:select></td>

										</tr>
									</table>
								</div>

								<div class="invoice_title">
									<a href="javascript:void(0);"
										onclick="openc('product_docu','image2')"><img
										src="images/ad.gif" width="11" height="11" id="image2" /> </a>&nbsp;Output
									Parameters
								</div>
								<div id="product_docu" class="disp" style="display: block;">
									<table border="0" cellpadding="0" cellspacing="0"
										class="General_table">
										<tr>
											<th width="154">&nbsp;</th>
											<td><table border="0" cellspacing="0" cellpadding="0">

													<tr>
														<td><input type="checkbox" name="all" id="all" />
														</td>
														<td width="120">All</td>
														<td>&nbsp;</td>
														<td width="120">&nbsp;</td>
														<td>&nbsp;</td>
														<td width="120">&nbsp;</td>
														<td>&nbsp;</td>

														<td width="120">&nbsp;</td>
														<td>&nbsp;</td>
														<td width="120">&nbsp;</td>
													</tr>
													<tr>
														<td><input type="checkbox" id="orderNoCheck"
															name="orderNo" value="Order No" checked="checked" /></td>
														<td>Order #</td>

														<td><input type="checkbox" id="custNoCheck"
															name="custNo" value="Cust No" checked="checked" />
														</td>

														<td>Customer #</td>
														<td><input type="checkbox" id="orderStatusCheck"
															name="orderStatus" value="Status" checked="checked" />
														</td>
														<td>Order Status</td>
														<td><input type="checkbox" name="custName"
															id="custNameCheck" value="Customer" /></td>

														<td>Customer Name</td>
														<td><!-- <input type="hidden" name="dataFrom"
															id="dataFromCheck" value="From Date" />
															 <input
															type="hidden" name="dataTo" id="dataToCheck"
															value="To Date" /> --> <input type="checkbox"
															name="organization" id="organizationCheck"
															checked="checked" value="Organization" /></td>

														<td>Organization</td>

													</tr>
													<tr>
														<td><input type="checkbox" name="priority"
															id="priorityCheck" value="Priority" />
														</td>
														<td>Priority</td>
														<td><input type="checkbox" name="productServiceType" checked="checked"
															id="productServiceTypeCheck" value="Product/Service Type" />
														</td>
														<td>Product/Service Type</td>

														<td><input type="checkbox" name="confirmationDate"
															checked="checked" id="confirmationDateCheck"
															value="Confirmation Date" /></td>
														<td>Confirmation Date</td>
														<td><input type="checkbox" name="orderTotal" checked="checked"
															id="orderTotalCheck" value="Order Total" /></td>
														<td>Order Total</td>
														<td><input type="checkbox" name="location"
															checked="checked" id="locationCheck" value="Location" />
														</td>
														<td>Location</td>
													</tr>
													<tr>
														<td><input type="checkbox"
															name="productionTargetDate"
															id="productionTargetDateCheck"
															value="Product Target Date" /></td>
														<td>Production Target Date</td>
														<td><input type="checkbox"
															name="guaranteedDeliveryDate"
															id="guaranteedDeliveryDateCheck"
															value="Guaranteed Delivery Date" /></td>
														<td>Guaranteed Delivery Date</td>
														<td><input type="checkbox" name="deliveryDate"
															id="deliveryDateCheck" value="Delivery Date" />
														</td>
														<td>Delivery Date</td>
														<td><input type="checkbox" name="numofItems"
															checked="checked" id="numofItemsCheck"
															value="Num of Items" /></td>
														<td>Num of Items</td>
														<td><input type="checkbox" name="overdueNumOfitems"
															id="overdueNumOfitemsCheck" value="Overdue Num of Items" />
														</td>
														<td>Overdue Num Of items</td>
													</tr>
													<tr>
														<td><input type="checkbox" name="salesManager"
															checked="checked" id="salesManagerCheck"
															value="Sales Manager" /></td>
														<td>Sales Manager</td>
														<td><input type="checkbox" name="tam" id="tamCheck"
															value="T.A. Manager" />
														</td>
														<td>TAM</td>
														<td><input type="checkbox" name="projectManager"
															checked="checked" id="projectManagerCheck"
															value="Project Manager" /></td>
														<td>Project Manager</td>
														<td><input type="checkbox" name="po" id="poCheck"
															value="P.O" />
														</td>
														<td>P.O.#</td>
														<td><input type="checkbox" name="followupStatus"
															checked="checked" id="followupStatusCheck"
															value="Followup Status" /></td>
														<td>Followup Status</td>
													</tr>
													<tr>
														<td><input type="checkbox" name="followupDate"
															checked="checked" id="followupDateCheck"
															value="Followup Date" />
														</td>
														<td>Followup Date</td>
														<td><input type="checkbox" name="followupMessage"
															checked="checked" id="followupMessageCheck"
															value="Followup Message" />
														</td>
														<td>Followup Message</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>

														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<th>Sort By</th>

											<td><select name="sortBy">
													<option value="">All</option>
													<option value="`order`.`v_all_orders`.`order_date`">Order
														Date</option>
													<option value="`order`.`v_all_orders`.`amount`">Order
														Total</option>
													<option value="`order`.`v_all_orders`.`expr_date`">Delay
														Days</option>
											</select></td>
										</tr>
									</table>
								</div></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<div class="button_box">
		<input type="button" name="FollowUpTrriger" value="Follow Up"
			class="search_input" id="FollowUpTrriger" align="middle" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img src="${global_image_url}excel.jpg"
			style="cursor: pointer" id="sdasdsd" width="16" height="16"
			align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img
			src="${global_image_url}pdf.gif" style="cursor: pointer" id="eeeee"
			width="16" height="16" align="middle" />
	</div>
	<div id="msg"></div>
	<div id="dhtmlgoodies_tabView1" style="visibility: none">
		<iframe width="100%" height="0px" id="operation_list_frame"
			name="operation_list_frame" scrolling="no" frameborder="0" src=""></iframe>
	</div>

</body>
</html>
