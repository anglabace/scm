<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url }SpryTabbedPanels.css" rel="stylesheet"
	type="text/css" />
<link href="${global_css_url }tab-view.css" rel="stylesheet"
	type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link type="text/css"
	href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
   <script src="${global_js_url}jquery/jquery.validate.js?v=1"
	type="text/javascript"></script>   
<script>
	var type = "Product";
</script>

<script>
	$(function() {
		$("#priceChangeHistDialogTrigger")
				.click(
						function() {
							var url = "${ctx}/product/product_pricing!getPriceChangeHist.action?sessionProductId=${sessionProductId}";
							parent
									.$('#priceChangeHistDialog')
									.dialog(
											"option",
											"open",
											function() {
												var htmlStr = '<iframe src="' + url + '" height="180" width="560" scrolling="no" style="border:0px" frameborder="0"></iframe>';
												parent
														.$(
																'#priceChangeHistDialog')
														.html(htmlStr);
											});
							parent.$('#priceChangeHistDialog').dialog("open");
						});
		
		 $("#priceForm").validate({
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
	                $("[name='" + key + "']").focus();
	                return false;
	            });
	        },
	        rules : {
	            "productDTO.vtRatio" : {
	                required : true,
	                number : true
	            },
	            "productDTO.btRatio" : {
	                required : true,
	                number : true
	            }
	        },
	        messages : {
	            "productDTO.vtRatio" : {
	                required : "Please enter the vtRatio" ,
	                number : "This 'vtRatio' must be a digit!"
	            },
	            "productDTO.btRatio" : {
	                required : "Please enter the btRatio",
	                number : "This 'btRatio' must be a digit!"
	            }
	        },
	        errorPlacement : function(error, element) {
	        }
	    });  
	   
	});
	function editSpecialPrice(id) {
		$("#editPriceDialogTrigger").attr("specialPriceId", id);
		$("#editPriceDialogTrigger").trigger("click");
	}
	function editProductPrice(catalogId, categoryId, unitPrice, limitPrice,
			currency, sessionProductId) {
		$("#priceEditTrigger").attr("catalogId", catalogId);
		$("#priceEditTrigger").attr("categoryId", categoryId);
		$("#priceEditTrigger").attr("unitPrice", unitPrice);
		$("#priceEditTrigger").attr("limitPrice", limitPrice);
		$("#priceEditTrigger").attr("currency", currency);
		$("#priceEditTrigger").attr("sessionProductId", sessionProductId);
		$("#priceEditTrigger").trigger("click");
	}

	function addProductPrice() {

		$("#priceAddTrigger").trigger("click");
	}
	$(document)
			.ready(

					function() {
						$('#productPriceTable tr:odd >td').addClass('list_td2');
						$('#orderTable tr:odd >td').addClass('list_td2');
						$("#delPriceTrigger")
								.click(
										function() {
											var tmpArr = [];
											$(
													"#productPriceTable :checkbox[checked]")
													.each(
															function(i, n) {
																tmpArr
																		.push($(
																				n)
																				.val());
															});
											var priceIdStr = tmpArr.toString();
											if (priceIdStr == "") {
												alert("Please select one item to continue your operation.");
												return;
											}
											if (!confirm("Are you sure to delete?")) {
												return;
											}

											var psId = $("#psId").val();
											$
													.ajax({
														url : "${ctx}/product/product_pricing!delPriceSession.action",
														type : "post",
														dataType : "text",
														data : "id="
																+ psId
																+ "&type=product&priceIdStr="
																+ priceIdStr
																+ "&sessionProductId=${sessionProductId}",
														success : function(data) {
															if (data == "success") {

																var del = priceIdStr
																		.split(",");
																for ( var i = 0; i < del.length; i++) {
																	$(
																			"#del_"
																					+ del[i])
																			.remove();
																}
															} else {
																alert("System error! Please contact system administrator for help.");
															}

														},
														error : function(data) {
															alert("System error! Please contact system administrator for help.");
														}
													});
										});

						$("#priceListCheckAll").click(
								function() {
									if ($(this).attr("checked") == true) {
										$("[name='catalogIdChk']").attr(
												"checked", true);
									} else {
										$("[name='catalogIdChk']").attr(
												"checked", false);
									}
								});

						var psId = $("#psId").val();
						//ship area add trigger
						$("#priceAddTrigger")
								.click(
										function() {
											var tmpArr = [];
											$("#productPriceTable :checkbox")
													.each(
															function(i, n) {
																tmpArr
																		.push($(
																				n)
																				.val());
															});
											var catlogIdIdStr = tmpArr
													.toString();
											//alert(catlogIdIdStr);
											parent
													.$('#productPriceAddDialog')
													.dialog(
															"option",
															"open",
															function() {
																var url = "${ctx}/product/product_pricing!showAddProductPrice.action?sessionProductId=${sessionProductId}&catalogNo=${catalogNo}&catalogIdStr="
																		+ catlogIdIdStr;
																var htmlStr = '<iframe src="' + url + '" height="220" width="520" scrolling="no" style="border:0px" frameborder="0"></iframe>';
																parent
																		.$(
																				'#productPriceAddDialog')
																		.html(
																				htmlStr);
															});
											parent.$('#productPriceAddDialog')
													.attr("title",
															"Assign Product");
											parent.$('#productPriceAddDialog')
													.dialog("open");
										});
						//ship area edit trigger
						$("#priceEditTrigger")
								.click(
										function() {

											var sessionProductId = $(this)
													.attr("sessionProductId");
											var catalogId = $(this).attr(
													"catalogId");
											var categoryId = $(this).attr(
													"categoryId");
											var unitPrice = $(this).attr(
													"unitPrice");
											var limitPrice = $(this).attr(
													"limitPrice");
											var currency = $(this).attr(
													"currency");
											//parent.$('#productPriceEditDialog').find("#priceEditIframe").contents().find("#catalogId").val(catalogId);
											parent
													.$(
															'#productPriceEditDialog')
													.dialog(
															"option",
															"open",
															function() {
																//var supplierInfo = $("#"+supplierId+"_supplierInfo").val();
																var url = "${ctx}/product/product_pricing!showEditProductPrice.action?catalogId="
																		+ catalogId
																		+ "&categoryId="
																		+ categoryId
																		+ "&unitPrice="
																		+ unitPrice
																		+ "&limitPrice="
																		+ limitPrice
																		+ "&currency="
																		+ currency
																		+ "&sessionProductId="
																		+ sessionProductId
																		+ "&operation_method=edit";
																var htmlStr = '<iframe id="priceEditIframe" src="' + url + '" height="220" width="520" scrolling="no" style="border:0px" frameborder="0"></iframe>';
																parent
																		.$(
																				'#productPriceEditDialog')
																		.html(
																				htmlStr);
																//alert(parent.$('#productPriceEditDialog').find("#priceEditIframe").contents().html());
															});
											parent
													.$(
															'#productPriceEditDialog')
													.dialog("option", "title",
															"Edit Product Price");
											parent.$('#productPriceEditDialog')
													.dialog("open");
										});

						//Special Price
						$('#specialPriceTable tr:odd >td').addClass('list_td2');
						$('#specialPriceTable tr:odd >td').addClass('list_td2');
						$("#delSpecailPriceTrigger")
								.click(
										function() {
											var tmpArr = [];
											$(
													"#specialPriceTable :checkbox[checked]")
													.each(
															function(i, n) {
																tmpArr
																		.push($(
																				n)
																				.val());
															});
											var priceIdStr = tmpArr.toString();
											if (priceIdStr == "") {
												alert("Please select one item to continue your operation.");
												return;
											}
											if (!confirm("Are you sure to delete?")) {
												return;
											}

											var psId = $("#psSpecId").val();
											$
													.ajax({
														url : "${ctx}/product/product_pricing!delSpecialPriceSession.action",
														type : "post",
														dataType : "text",
														data : "id="
																+ psId
																+ "&type=product&specialPriceIdStr="
																+ priceIdStr
																+ "&sessionProductId=${sessionProductId}",
														success : function(data) {
															if (data == "success") {

																var del = priceIdStr
																		.split(",");
																for ( var i = 0; i < del.length; i++) {
																	$(
																			"#delSpc_"
																					+ del[i])
																			.remove();
																}
															} else {
																alert("System error! Please contact system administrator for help.");
															}

														},
														error : function(data) {
															alert("System error! Please contact system administrator for help.");
														}
													});
										});

						$("#specialPriceListCheckAll").click(
								function() {
									if ($(this).attr("checked") == true) {
										$("[name='priceIdChk']").attr(
												"checked", true);
									} else {
										$("[name='priceIdChk']").attr(
												"checked", false);
									}
								});

						$("#editPriceDialogTrigger")
								.click(
										function() {

											var sessionProductId = $(this)
													.attr("sessionProductId");
											var id = $(this).attr(
													"specialPriceId");
											//parent.$('#productPriceEditDialog').find("#priceEditIframe").contents().find("#catalogId").val(catalogId);
											parent
													.$(
															'#specialPriceEditDialog')
													.dialog(
															"option",
															"open",
															function() {
																//var supplierInfo = $("#"+supplierId+"_supplierInfo").val();
																var url = "${ctx}/product/product_pricing!editSpecialPrice.action?specialPriceId="
																		+ id
																		+ "&sessionProductId="
																		+ sessionProductId;
																var htmlStr = '<iframe id="specialPriceEditIframe" src="' + url + '" height="380" width="540" scrolling="no" style="border:0px" frameborder="0"></iframe>';
																parent
																		.$(
																				'#specialPriceEditDialog')
																		.html(
																				htmlStr);
																//alert(parent.$('#productPriceEditDialog').find("#priceEditIframe").contents().html());
															});
											//parent.$('#specialPriceEditDialog').dialog("option", "title", "Edit Product Price");
											parent.$('#specialPriceEditDialog')
													.dialog("open");
										});

						$("#addPriceDialogTrigger")
								.click(
										function() {

											var sessionProductId = $(
													"#sessionProductId").val();
											if (sessionProductId.length == 32) {
												alert("Please save product first!");
												return;
											}
											var id = $(this).attr("id");
											//parent.$('#productPriceEditDialog').find("#priceEditIframe").contents().find("#catalogId").val(catalogId);
											parent
													.$('#specialPriceAddDialog')
													.dialog(
															"option",
															"open",
															function() {
																//var supplierInfo = $("#"+supplierId+"_supplierInfo").val();
																var url = "${ctx}/product/product_pricing!createSpecialPrice.action?sessionProductId=${sessionProductId}";
																var htmlStr = '<iframe id="specialPriceAddIframe" src="' + url + '" height="380" width="540" scrolling="no" style="border:0px" frameborder="0"></iframe>';
																parent
																		.$(
																				'#specialPriceAddDialog')
																		.html(
																				htmlStr);
																//alert(parent.$('#productPriceEditDialog').find("#priceEditIframe").contents().html());
															});
											//parent.$('#specialPriceEditDialog').dialog("option", "title", "Edit Product Price");
											parent.$('#specialPriceAddDialog')
													.dialog("open");
										});
					});
</script>
</head>

<body>
	<div style="overflow: auto; height: 420px; width: 980px;">
		<form id="priceForm">
			<table width="940" border="0" cellpadding="0" cellspacing="0"
				class="General_table">
				<tr>
					<th width="58" style="padding-bottom: 2PX;"><span
						class="important">*</span>VT Ratio</th>
					<td width="141" style="padding-bottom: 2px;"><input
						type="text" size="25" class="NFText2" id="vtRatio"
						value="${vtRatio}" name="productDTO.vtRatio"/></td>
					<th width="183" style="padding-bottom: 2PX;"><span
						class="important">*</span>BT Ratio</th>
					<td width="579" style="padding-bottom: 2PX;"><input
						type="text" size="25" class="NFText2" id="btRatio"
						value="${btRatio}"  name="productDTO.btRatio" /></td>
				</tr>
			</table>
		</form>
		<table width="940" border="0" cellpadding="0" cellspacing="0"
			class="General_table">
			<tr>
				<td>
					<table width="940" border="0" cellpadding="0" cellspacing="0"
						class="list_table">
						<tr>
							<th width="46">
								<div align="left">
									<input id="priceListCheckAll" name="priceListCheckAll"
										type="checkbox" />
								</div>
							</th>
							<%--<img id="delPriceTrigger" src="${global_image_url}file_delete.gif" alt="Delete" width="16" height="16" border="0" />--%>
							<th width="141">Catalog ID</th>
							<th width="210">Category</th>
							<th width="104">Retail Price</th>
							<th width="102">Price Limit</th>
							<th width="96">Currency</th>
							<th width="101">Update Date</th>
							<th>Updated By</th>
						</tr>

					</table>
					<div class="frame_pr" style="height: 80px;">
						<table width="940" border="0" cellpadding="0" cellspacing="0"
							class="list_table" id="productPriceTable">
							<s:iterator value="resultMap">
								<tr id="del_${key}">
									<td width="46">&nbsp;<input type="checkbox"
										name="catalogIdChk" value="${key}" /></td>
									<td width="141">&nbsp;${value.catalogId}</td>
									<td width="210">&nbsp;${value.categoryName }</td>
									<td width="104" align="right">&nbsp; <a
										href="javascript:void(0);"
										onclick="editProductPrice('${value.catalogId}', '${value.categoryId}', '${value.unitPrice}', '${value.limitPrice}', '${value.currencyCode}', '${sessionProductId}');"
										title="Edit Product Price" rel="gb_page_center[580,200]">
											${value.symbol}${value.unitPrice}</a></td>

									<td width="102" align="right">&nbsp;<c:if
											test="${! empty  value.limitPrice}">${value.symbol}${value.limitPrice}</c:if>
									</td>
									<td width="96">&nbsp;${value.currencyCode}</td>
									<td width="101">&nbsp;<s:date name="value.modifyDate"
											format="yyyy-MM-dd" /></td>
									<td>&nbsp;${value.modifiedBy}</td>
								</tr>
							</s:iterator>
						</table>

					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="botton_box">
						<a href="javascript:void(0);" title="Assign Product"
							rel="gb_page_center[580,200]"> <input id="priceEditTrigger"
							type="hidden" class="style_botton" value="" /> <input
							id="priceAddTrigger" type="hidden" class="style_botton" value="" />
							<input id="psId" type="hidden" value="${id}" /> <input
							id="sessionProductId" type="hidden" value="${sessionProductId}" />
							<input name="Submit7222" type="Submit" class="style_botton3"
							value="Assign To Catalog" onclick="addProductPrice()" /> </a> <input
							type="button" value="Price Change History"
							id="priceChangeHistDialogTrigger" class="style_botton3" />
					</div>
				</td>
			</tr>
		</table>

		<table width="960" border="0" cellspacing="0" cellpadding="0"
			class="General_table">

			<tr>
				<td height="30" valign="top">
					<div align="center">
						<span class="blue_price">Special Price List </span>
					</div>
				</td>
			</tr>
			<tr>
				<td>

					<table width="940" border="0" cellpadding="0" cellspacing="0"
						class="list_table">


						<tr>
							<th width="46">
								<div align="left">
									<input id="specialPriceListCheckAll"
										name="specialPriceListCheckAll" type="checkbox" /> <img
										id="delSpecailPriceTrigger"
										src="${global_image_url}file_delete.gif" alt="Delete"
										width="16" height="16" border="0" />
								</div>
							</th>
							<th width="48">Seq No</th>
							<th width="70">Unit Price</th>
							<th width="70">Discount</th>

							<th width="70">RFM Rating</th>
							<th width="180">Source Key</th>
							<th width="56">Min Qty</th>
							<th width="100">Catalog ID</th>
							<th width="80">Start Date</th>
							<th width="80">End Date</th>

							<th>Order Total</th>
						</tr>
					</table>

					<div class="frame_pr" style="height: 160px;">
						<table width="940" border="0" cellpadding="0" cellspacing="0"
							class="list_table" id="specialPriceTable">
							<s:iterator value="specialResultMap" status="st">
								<tr id="delSpc_${key}">
									<td width="46"><input type="checkbox" name="priceIdChk"
										value="${key}" /></td>
									<td width="48">&nbsp;${st.index+1}</td>

									<td width="70">
										<div align="right">
											<a href="javascript:void(0);"
												onclick="editSpecialPrice('${key}');" title="Special Price"
												rel="gb_page_center[550,  350]">&nbsp;${value.unitPrice}</a>
										</div>
									</td>
									<td width="70">
										<div align="right">&nbsp;${value.discount}</div>
									</td>
									<td width="70">
										<div align="center">&nbsp;${value.rfmRating}</div>
									</td>
									<td width="180">&nbsp;${value.sourceKey}</td>
									<td width="56">&nbsp;${value.minQty}</td>
									<td width="100">&nbsp;${value.catalogId}</td>

									<td width="80">
										<div align="center">
											&nbsp;
											<s:date name="value.effFrom" format="yyyy-MM-dd" />
										</div>
									</td>
									<td width="80">
										<div align="center">
											&nbsp;
											<s:date name="value.effTo" format="yyyy-MM-dd" />
										</div>
									</td>
									<td>
										<div align="right">&nbsp;${value.orderTotal}</div>
									</td>
								</tr>
							</s:iterator>

						</table>
					</div>
				</td>
			</tr>

			<tr>
				<td>

					<div class="botton_box">
						<input id="editPriceDialogTrigger" type="hidden"
							class="style_botton" value="" /> <input type="button"
							value=" New " id="addPriceDialogTrigger" class="style_botton" />
					</div>
				</td>
			</tr>
		</table>
	</div>
	<br>

</body>
</html>