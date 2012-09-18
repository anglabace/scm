<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Customer Credit Monitor</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css"
	rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
	type="text/css" />

<script type="text/javascript" language="javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/jquery.dialog.all.js" type="text/javascript"></script>
<script src="${global_js_url}initlefttop.js" type="text/javascript"></script>
<script
	src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js"
	type="text/javascript"></script>
<script type="text/javascript">
	
	
	var custmoerNo="";
	$(function() {
		$("#resultTable tr:odd").find("td").addClass("list_td2");
	});

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
	
	$(document).ready(function(){
		$("#resultTable tr:odd").find("td").addClass("list_td2");
		$('#updateStatusDialog').dialog({
			autoOpen: false,
			height: 450,
			width: 600,
			modal: true,
			bgiframe: true,
			buttons: {
			},
			open: function(){
				var custmoerNo= $("input[name='i_custNo']:checked").val();
				var url = "tools/customer_invoice!edit.action?custmoerNo="+custmoerNo;
				var htmlStr = '<iframe id="newStatusIframe" src="'+url+'" height="450" width="600" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
				$('#updateStatusDialog').html(htmlStr);
			}
		});
		$('#updateStatusTrigger').click(function(){
			var radioVals = document.getElementsByName("i_custNo");
			var flage = false;
			for ( var i = 0; i < radioVals.length; i++) {
				if (radioVals[i].checked == true) {
					flage = true;
					$('#updateStatusDialog').dialog('open');				
				}
			}
			if (!flage) {
				alert("Please select one!");
				return;
			}
		});
	});
</script>
</head>
<body class="content" style="background-image: none;">
	<form action="customer_invoice!list.action" method="get">
		<div class="scm">
			<div class="title_content">
				<div
					style="padding-left: 20px; color: #5579A6; vertical-align: middle;">
					<img src="${global_url}images/arrow1.jpg"
						style="width: 16px; height:17px; vertical-align: middle;"
						onclick="toggleShowMore_img(this,'search_box1');" />&nbsp;&nbsp;Customer
					Credit Monitor
				</div>
			</div>
			<div class="search_box" id="search_box1">
				<div class="single_search">
					<table border="0" cellspacing="0" cellpadding="0"
						class="General_table">
						<tr>
							<th width="132">Payment Overdue Days</th>
							<td colspan="2">
							<s:select list="{'All','<120','>=120'}"  value="dutDays" style="width: 132px;" name="dutDays"></s:select>
							</td>

							<th>Customer No</th>
							<td align="left"><input name="custNo" type="text"
								class="NFText" size="20" value="${custNo}"/></td>
							<th>Status</th>
							<td width="200">
								<s:select list="{'All','ACTIVE','INACTIVE','SUSPENDED'}" value="status" style="width: 132px;" name="status"></s:select>
							</td>
						</tr>

						<tr>
							<th>Account Balance</th>
							<td width="71"><input name="startBalance" type="text"
								class="NFText2" size="5"  value="${startBalance}"/> ~</td>

							<td align="left"><input name="endBalance" type="text"
								class="NFText" size="5" value="${endBalance}"/></td>
							<th width="70">&nbsp;</th>
							<td width="119" align="left">&nbsp;</td>
							<th width="49">&nbsp;</th>

						</tr>
						<tr>
							<td></td>
							<td></td>
							<td width="100" colspan="2" align="center"><input
								type="submit" name="Submit5" value="Search" class="search_input" />
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="input_box" style="height: 350px;">
				<div class="content_box">
					<table width="1010" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<div style="margin-right: 17px;">
									<table width="1200" border="0" cellspacing="0" cellpadding="0"
										class="list_table">
										<tr>
											<th width="30" height="22">&nbsp;</th>
											<th width="87">Customer No</th>
											<th width="69">Status</th>
											<th width="107">Account Balance</th>
											<th width="108">Name</th>
											<th width="108">Email</th>
											<th width="150">Organization</th>
											<th width="198">Address</th>
											<th width="107">Phone</th>
											<th>Create Date</th>
										</tr>
									</table>
								</div></td>
						</tr>
						<tr>
							<td>
								<div class="list_box" style="height: 340px;">
									<table width="1200" border="0" cellspacing="0" cellpadding="0"
										class="list_table2" id="resultTable">
										<s:iterator value="page.result">
											<tr>
												<td width="30" align="center"><input type="radio"
													name="i_custNo" id="i_custNo" value="${custNo}" /></td>
												<td width="87"><a
													href="customer/customer!edit.action?custNo=${custNo}&operation_method=edit">
														${custNo} </a></td>
												<td width="69">${status}</td>
												<td width="107">$  ${balance}</td>
												<td width="108">${firstName}<c:if test="${! empty lastName}">,</c:if>${lastName}</td>
												<td width="108">
												<s:if test="%{busEmail != null && busEmail.length()>15}">
														<s:property value="busEmail.substring(0,15)+'...'" />
													</s:if> <s:else>
														<s:property value="busEmail" />
													</s:else></td>
												<td width="150">${organizationName}</td>
												<td width="198">
												${addrLine1}<c:if test="${! empty addrLine2}">,
												</c:if>${addrLine2}<c:if test="${! empty addrLine3}">,</c:if>${addrLine3}
												</td>
												<td width="107">
													<div align="center">
														<s:date format="yyyy-MM-dd" name="creationDate" />
													</div></td>
												<td>${creationDate}</td>
											</tr>
										</s:iterator>
									</table>
								</div></td>
						</tr>
						<tr>
							<td>
								<div class="grayr">
									<jsp:include page="/common/db_pager.jsp">
										<jsp:param value="${ctx}/customer_invoice!list.action"
											name="moduleURL" />
									</jsp:include>
								</div></td>
						</tr>
					</table>
				</div>
				<div class="button_box" style="margin-top: 30px">
					<input type="button" name="Submit8" id="updateStatusTrigger" value="Modify Status" class="search_input2" />
				</div>
			</div>
		</div>
	</form>
	<div id="updateStatusDialog" title="Update Customer Status"></div>
</body>
</html>