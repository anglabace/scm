<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}tab-view.js"></script>
<link href="${global_css_url}tab-view.css" rel="stylesheet"
	type="text/css" />
<link href="${global_css_url}index.css" rel="stylesheet" type="text/css" />
</head>

<script type="text/javascript">
	function searchall() {
		var quoteNo = document.getElementById("filterOrderNo").value.replace(
				/(^\s*)|(\s*$)/g, "");
		var custNo = document.getElementById("filterCustNo").value.replace(
				/(^\s*)|(\s*$)/g, "");
		var form = document.getElementById("searchForm");
		document.getElementById("filterOrderNo").value = quoteNo;
		document.getElementById("filterCustNo").value = custNo;
		form.submit();
	}
</script>
<body class="content" style="background-image: none;">
	<div class="scm">
		<div class="title_content">
			<div class="title">Index of</div>
		</div>
		<div class="search_box" style="font-weight: normal;">
			<div class="search_order" style="font-weight: normal;">Order
				Search:</div>
			<form method="get" action="order/order_search.action" id="searchForm">
				<input type="hidden" value="OrderSearch" name="searchType">
			    <input type="hidden" value="" name="internalCustomer">
				Customer NO <input name="filter_EQI_custNo" id="filterCustNo"
					type="text" class="NFText" size="25" /> 
				Order NO <input
					name="filter_EQI_orderNo" id="filterOrderNo" type="text"
					class="NFText" size="25" /> <input type="button" value="Search"
					class="search_input" onclick="searchall();" />
			</form>
			<br /> <br /> <br /> <br />
		</div>
		<div class="input_box">
			<div class="border">
				<div class="border-top">
					<div class="shotcuts">
						<a href="#"><img src="${global_image_url}file_edit.gif"
							width="16" height="16" border="0" align="absmiddle" /> </a><a
							href="#"> Manage Shotcuts </a>
					</div>
					<img src="${global_image_url}favorites.jpg" width="175" height="26" />

				</div>
				<div class="box_index">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td><ul class="link_list">
									<li><a href="customer/customer_srch_list!search.action"
										target="mainFrame">Quotation/Order Entry</a></li>
									<li><a href="quote/quote_search.action" target="mainFrame">Quotation
											Processing</a></li>
									<li><a href="order/order_search!search.action"
										target="mainFrame">Order Processing</a></li>
									<li><a
										href="manufacture/work_order!workOrderManageFrame.action"
										target="mainFrame">Receiving</a></li>
									<li><a href="#" target="mainFrame">Picking</a></li>
									<li><a href="#" target="mainFrame">Packing</a></li>
									<li><a href="shipments/shipments!appFrame.action"
										target="mainFrame">Shipments</a></li>
									<li><a href="shipments/shipments!appFrame.action"
										target="mainFrame">Shipment (Single)</a></li>
									<!-- <li><a href="shipping_report.html" target="mainFrame">Shipment Reports</a></li> -->

								</ul>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="border">
				<div class="border-top">
					<img src="${global_image_url}statistics.jpg" width="152"
						height="26" align="absmiddle" />
				</div>
				<div class="box_index">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="top"><div class="sta_title">
									Sales in 2009 to complete the table<br /> <img
										src="${global_image_url}year_statistics.jpg" />
								</div>
							</td>
							<td valign="top"><div class="sta_title">
									Sales table each month in 2009 <br /> <img
										src="${global_image_url}person_statistics.jpg" width="545"
										height="204" />
								</div>
							</td>
						</tr>
					</table>

				</div>
			</div>
			<div class="border">
				<div class="border-top">
					<img src="${global_image_url}job_list.jpg" width="93" height="24"
						align="absmiddle" />
				</div>
				<div class="box_index">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="top"><div class="sta_title">
									Unapproved List <br />
									<ol>
										<li><span class="unspproved">Order Number:</span><a
											href="#">2333502</a>,<span class="unspproved">Customer
												name:</span>Smish,<span class="unspproved">Order Date:</span>11/09/2009
										</li>
										<li><span class="unspproved">Order Number:</span><a
											href="#">2333502</a>,<span class="unspproved">Customer
												name:</span>Smish,<span class="unspproved">Order Date:</span>02/09/2009
										</li>
										<li><span class="unspproved">Order Number:</span><a
											href="#">2333502</a>,<span class="unspproved">Customer
												name:</span>Smish,<span class="unspproved">Order Date:</span>25/08/2009
										</li>
										<li><span class="unspproved">Order Number:</span><a
											href="#">2333502</a>,<span class="unspproved">Customer
												name:</span>Smish,<span class="unspproved">Order Date:</span>2009-06-22
										</li>
										<li><span class="unspproved">Order Number:</span><a
											href="#">2333502</a>,<span class="unspproved">Customer
												name:</span>Smish,<span class="unspproved">Order Date:</span>24/07/2009
										</li>
										<li><span class="unspproved">Order Number:</span><a
											href="#">2333502</a>,<span class="unspproved">Customer
												name:</span>Smish,<span class="unspproved">Order Date:</span>05/07/2009
										</li>
										<li><span class="unspproved">Order Number:</span><a
											href="#">2333502</a>,<span class="unspproved">Customer
												name:</span>Smish,<span class="unspproved">Order Date:</span>05/07/2009
										</li>
									</ol>
								</div>
							</td>
							<td valign="top"><div class="sta_title">
									User-cared Job <br />
									<ol>
										<li><span class="unspproved">Has Completed payment
												of order ID </span><a href="#">2333502</a></li>
										<li><span class="unspproved">OrderID: </span><a href="#">7536532
										</a><span class="unspproved">have been shipped</span></li>
										<li><span class="unspproved">OrderID: </span><a href="#">4564564
										</a><span class="unspproved">have been shipped</span></li>
										<li><span class="unspproved">OrderID: </span><a href="#">4523454
										</a><span class="unspproved">have been shipped</span></li>
										<li><span class="unspproved">OrderID: </span><a href="#">4245757
										</a><span class="unspproved">have been shipped</span></li>
										<li><span class="unspproved">OrderID: </span><a href="#">45234521
										</a><span class="unspproved">have been shipped</span></li>
										<li><span class="unspproved">OrderID: </span><a href="#">78454354
										</a><span class="unspproved">have been shipped</span></li>
									</ol>

								</div>
							</td>
						</tr>
					</table>

				</div>
			</div>
		</div>
	</div>

</body>
</html>