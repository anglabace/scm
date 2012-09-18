<%@ page import="org.apache.commons.beanutils.BeanUtils"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/common/taglib.jsp"%>
<c:set var="method" value="${requestScope.method}" scope="request" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript">
	var GB_ROOT_DIR = "${global_js_url}greybox/";
</script>
<link href="${global_js_url}jquery/themes/base/ui.all.css"
	rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}util/util.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}tab-view.js"></script>
<script src="${global_js_url}jquery/jquery.js" type="text/javascript"
	language="javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js"
	type="text/javascript"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.form.js"></script>
<script src="${global_js_url}jquery/jquery.validate.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.dialog.all.js"
	type="text/javascript"></script>
<script src="${global_js_url}scm/config.js" type="text/javascript"></script>
<script src="${global_js_url}/recordTime.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}newwindow.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}expland.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
<script type="text/javascript"
	src="${global_js_url}greybox/gb_scripts.js"></script>
<script type="text/javascript" language="javascript">
	function get_checked_str(name) {
		var a = document.getElementsByName(name);
		var str = '';
		for ( var i = 0; i < a.length; i++) {
			if (a[i].checked) {
				str += a[i].value + '@';
			}
		}
		return str.substring(0, str.length - 1);
	}
	$(function() {

		$("#FollowupAddTrriger")
				.click(
						function() {
							var all_order_nos = get_checked_str("orderNo");
							var str = all_order_nos.split("@");
							//alert(str.length);
							if (str.length >= 2) {
								alert("Sorry,Now can't supply For Batch Add Message!");
								return;
							}
							if (all_order_nos == '') {
								alert('Please select one orderNo!');
								return;
							}
							/* if (!confirm('Are you sure to Add Message?')) {
								return;
							}  */
							$('#AddFollowupMessageWindow')
									.dialog(
											"option",
											"open",
											function() {
												var htmlStr = '<iframe id="FollowupMessage_iframe" src="follow_up!input.action?orderNo='
														+ all_order_nos
														+ '" height="350" width="510"  scrolling="no" style="border:0px" frameborder="0"></iframe>';
												$('#AddFollowupMessageWindow')
														.html(htmlStr);
											});
							$('#AddFollowupMessageWindow').dialog('open');

						});

		$('#AddFollowupMessageWindow').dialog({
			autoOpen : false,
			height : 415,
			width : 526,
			modal : true,
			bgiframe : true,
			buttons : {}
		});
	});
	function cc(e, name) {
		var a = document.getElementsByName(name);
		for ( var i = 0; i < a.length; i++)
			a[i].checked = e.checked;
	}
</script>
</head>
<body class="content" style="background-image: none;">
	<div class="input_box">
		<div class="content_box">
			<form enctype="multipart/form-data" class="niceform" method="post"
				action="tools!Searchout.action">
				<%
					String orderNo = request.getParameter("orderNo");
					String custNo = request.getParameter("custNo");
					String orderStatus = request.getParameter("orderStatus");
					String custName = request.getParameter("custName");
					String dataFrom = request.getParameter("dataFrom");
					String dataTo = request.getParameter("dataTo");
					String organization = request.getParameter("organization");
					String priority = request.getParameter("priority");
					String productServiceType = request
							.getParameter("productServiceType");
					String confirmationDate = request.getParameter("confirmationDate");
					String orderTotal = request.getParameter("orderTotal");
					String location = request.getParameter("location");
					String productionTargetDate = request
							.getParameter("productionTargetDate");
					String guaranteedDeliveryDate = request
							.getParameter("guaranteedDeliveryDate");
					String deliveryDate = request.getParameter("deliveryDate");
					String numofItems = request.getParameter("numofItems");
					String overdueNumOfitems = request
							.getParameter("overdueNumOfitems");
					String salesManager = request.getParameter("salesManager");
					String tam = request.getParameter("tam");
					String projectManager = request.getParameter("projectManager");
					String po = request.getParameter("po");
					String followupStauts = request.getParameter("followupStauts");
					String followupDate = request.getParameter("followupDate");
					String followupMessage = request.getParameter("followupMessage");
					String sortBy = request.getParameter("sortBy");
				%>
				<%
					if (orderNo != null && !"".equals(orderNo)) {
				%>
				<input type="hidden" name="orderNo" value="<%=orderNo%>" />
				<%
					}
					if (custNo != null && !"".equals(custNo)) {
				%>
				<input type="hidden" name="custNo" value="<%=custNo%>" />
				<%
					}

					if (orderStatus != null && !"".equals(orderStatus)) {
				%>
				<input type="hidden" name="orderStatus" value="<%=orderStatus%>" />
				<%
					}
					if (custName != null && !"".equals(custName)) {
				%>
				<input type="hidden" name="custName" value="<%=custName%>" />
				<%
					}
					if (dataFrom != null && !"".equals(dataFrom)) {
				%>
				<input type="hidden" name="dataFrom" value="<%=dataFrom%>" />
				<%
					}
					if (dataTo != null && !"".equals(dataTo)) {
				%>
				<input type="hidden" name="dataTo" value="<%=dataTo%>" />
				<%
					}
					if (organization != null && !"".equals(organization)) {
				%>
				<input type="hidden" name="organization" value="<%=organization%>" />
				<%
					}
					if (productServiceType != null && !"".equals(productServiceType)) {
				%>
				<input type="hidden" name="productServiceType"
					value="<%=productServiceType%>" />
				<%
					}
					if (confirmationDate != null && !"".equals(confirmationDate)) {
				%>
				<input type="hidden" name="confirmationDate"
					value="<%=confirmationDate%>" />
				<%
					}
					if (orderTotal != null && !"".equals(orderTotal)) {
				%>
				<input type="hidden" name="orderTotal" value="<%=orderTotal%>" />
				<%
					}
					if (location != null && !"".equals(location)) {
				%>

				<input type="hidden" name="location" value="<%=location%>" />
				<%
					}
					if (productionTargetDate != null
							&& !"".equals(productionTargetDate)) {
				%>
				<input type="hidden" name="productionTargetDate"
					value="<%=productionTargetDate%>" />
				<%
					}
					if (guaranteedDeliveryDate != null
							&& !"".equals(guaranteedDeliveryDate)) {
				%>
				<input type="hidden" name="guaranteedDeliveryDate"
					value="<%=guaranteedDeliveryDate%>" />
				<%
					}
					if (deliveryDate != null && !"".equals(deliveryDate)) {
				%>
				<input type="hidden" name="deliveryDate" value="<%=deliveryDate%>" />
				<%
					}
					if (numofItems != null && !"".equals(numofItems)) {
				%>
				<input type="hidden" name="numofItems" value="<%=numofItems%>" />
				<%
					}
					if (overdueNumOfitems != null && !"".equals(overdueNumOfitems)) {
				%>
				<input type="hidden" name="overdueNumOfitems"
					value="<%=overdueNumOfitems%>">
				<%
					}
					if (salesManager != null && !"".equals(salesManager)) {
				%><input type="hidden" name="salesManager" value="<%=salesManager%>" />
				<%
					}
					if (tam != null && !"".equals(tam)) {
				%><input type="hidden" name="tam" value="<%=tam%>" />
				<%
					}
					if (projectManager != null && !"".equals(projectManager)) {
				%><input type="hidden" name="projectManager"
					value="<%=projectManager%>" />
				<%
					}
					if (po != null && !"".equals(po)) {
				%>
				<input type="hidden" name="po" value="<%=po%>" />
				<%
					}
					if (followupStauts != null && !"".equals(followupStauts)) {
				%><input type="hidden" name="followupStauts"
					value="<%=followupStauts%>" />
				<%
					}
					if (followupDate != null && !"".equals(followupDate)) {
				%>
				<input type="hidden" name="followupDate" value="<%=followupDate%>" />
				<%
					}
					if (followupMessage != null && !"".equals(followupMessage)) {
				%>
				<input type="hidden" name="followupMessage"
					value="<%=followupMessage%>" />
				<%
					}
					if (sortBy != null && !"".equals(sortBy)) {
				%>
				<input type="hidden" name="sortBy" value="<%=sortBy%>" />
				<%
					}
				%>

				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<div style="margin-right: 17px; height: 340px;" class="list_box">
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									class="list_table">
									<tr>
										<th width="100px">
											<!-- <input type="checkbox"
											onclick="cc(this, 'orderNo')" /> -->
										</th>
										<input value="${orderFollowupDTO.columnName }" name="ddd"
											type="hidden" />
										<s:iterator value="orderFollowupDTO.columnName" id="colName">
											<th width="200px"><s:property value="colName" />
											</th>
										</s:iterator>
									</tr>
									<%
										ArrayList dataList = (ArrayList) request.getAttribute("reportData");
										int i = 0;
										if (dataList != null) {
											for (Object data : dataList) {
												i++;
									%>
									<tr>
										<td class="<%=(i % 2) == 0 ? "list_td2" : ""%>">
											<%
												if (BeanUtils.getProperty(data, "followupStatus").equals(
																"closed")) {
											%> <%=BeanUtils.getProperty(data, "followupStatus")%> <input
											type="checkbox" id="orderNo" name="orderNo"
											value="<%=BeanUtils.getProperty(data, "orderNo")%>"
											checked="disabled" /> <%
 	} else {
 %> <input type="checkbox" id="orderNo" name="orderNo"
											value="<%=BeanUtils.getProperty(data, "orderNo")%>" /> <%
 	}
 %>
										</td>
										<%
											ArrayList<String> columns = (ArrayList<String>) request
															.getAttribute("columns");
													for (String col : columns) {
														if ("".equals(col))
															continue;
														String coltd = BeanUtils.getProperty(data, col);
										%>
										<td class="<%=(i % 2) == 0 ? "list_td2" : ""%>" width="200px"><%=coltd == null ? "" : coltd%>
										</td>
										<%
											}
										%>
									</tr>
									<%
										}
										}
									%>
								</table>
							</div></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="grayr">
			<jsp:include page="reportPager.jsp">
				<jsp:param value="${ctx}/tools/tools!Searchout.action"
					name="moduleURL" />
			</jsp:include>
		</div>

	</div>



	<div id="AddFollowupMessageWindow" title="Add Followup Message"
		style="visible: hidden"></div>
	<div class="button_box">
		<input type="button" name="addFollowUp" value="Add Message"
			class="search_input" align="middle" id="FollowupAddTrriger" /> <a
			href="Javascript:window.history.go(-1);"><input type="button"
			name="BackButton" value="BackUp" class="search_input" /> </a>

	</div>

</body>
</html>