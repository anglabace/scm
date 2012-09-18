<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Order Management</title>
		<link href="stylesheet/scm.css" rel="stylesheet" type="text/css" />
		<link href="stylesheet/table.css" rel="stylesheet" type="text/css" />

		<script language="javascript" type="text/javascript"
			src="${global_js_url}tab-view.js"></script>
		<link href="stylesheet/tab-view.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript"
			src="${global_js_url}TabbedPanels.js"></script>
		<link href="stylesheet/SpryTabbedPanels.css" rel="stylesheet"
			type="text/css" />

		<link href="greybox/gb_styles.css" rel="stylesheet" type="text/css"
			media="all" />
		<style type="text/css">
<!--
.content .scm #dhtmlgoodies_tabView1 .dhtmlgoodies_aTab .General_table fieldset
	{
	margin: 4px;
}
-->
</style>
		<script language="javascript" type="text/javascript"
			src="${global_js_url}/newwindow.js"></script>
		<script language="javascript" type="text/javascript"
			src="${global_js_url}/jquery.js"></script>
		<script>
		  
</script>
		<script language="javascript" type="text/javascript">
	function print(){
	var packageId = document.getElementById("shipPackages.packageId").value;
	document.fr.action = "shipments!print.action?packageId="+packageId;
	document.fr.submit();
	
}

</script>
	</head>

	<body class="content">
	
		<form action="work_order!updateShipPackage.action" method="post"
			name="fr">
			<div id="frame12" style="display: none;" class="hidlayer1">
				<iframe id="hidkuan" name="hidkuan" src="kuang.html" width="668"
					height="425" frameborder="0" allowtransparency="true"></iframe>
			</div>
			<div class="scm">
				<div class="title_content">
					<div class="title">
						Package Information - # ${sp.packageId }
					</div>
				</div>
				<div class="input_box">
					<div class="content_box">

						<input type="hidden" value="${sp.createdBy }"
							name="shipPackages.createdBy" />
						<c:if test="${sp.warehouseId!=null }">
							<input type="hidden" value="${sp.warehouseId }"
								name="shipPackages.warehouseId" />
						</c:if>
						<c:if test="${sp.shipments!=null}">
							<c:if test="${sp.shipments.shipmentId!=null }">
								<input type="hidden" value="${sp.shipments.shipmentId }"
									name="shipments.shipments.shipmentId" />
							</c:if>
						</c:if>
						<c:if test="${sp.companyId!=null }">
							<input type="hidden" value="${sp.companyId }"
								name="shipPackages.companyId" />
						</c:if>
						
						<input type="hidden" value="${sp.shippingClerk==null?0:sp.shippingClerk }" name="shipPackages.shippingClerk" />
						
						<input type="hidden" value="${sp.invoicedFlag }"
							name="shipPackages.invoicedFlag" />
						<input type="hidden" value="${sp.invoiceNo }"
							name="shipPackages.invoiceNo" />
						<input type="hidden" value="${sp.deliveryType }"
							name="shipPackages.deliveryType" />
						<input type="hidden" value="${sp.actualWeight }"
							name="shipPackages.actualWeight" />
						<input type="hidden" value="${sp.billableWeight }"
							name="shipPackages.billableWeight" />
						<input type="hidden" value="${sp.pkgBatchSeq }"
							name="shipPackages.pkgBatchSeq" />
						<input type="hidden" value="${sp.zone }" name="shipPackages.zone" />
						<input type="hidden" value="${sp.pkgBatchCount }"
							name="shipPackages.pkgBatchCount" />
						<input type="hidden" value="${sp.packer }"
							name="shipPackages.packer" />
						<input type="hidden" value="${sp.insuranceValue }"
							name="shipPackages.insuranceValue" />
						<input type="hidden" value="${sp.additionalHandle }"
							name="shipPackages.additionalHandle" />
						<input type="hidden" value="${sp.deliveryConfirm }"
							name="shipPackages.deliveryConfirm" />
						<input type="hidden" value="${sp.hazardousMtl }"
							name="shipPackages.hazardousMtl" />
						<input type="hidden" value="${sp.saturdayPickup }"
							name="shipPackages.saturdayPickup" />
						<input type="hidden" value="${sp.noteOnShip }"
							name="shipPackages.noteOnShip" />
						<input type="hidden" value="${sp.noteOnDelivery }"
							name="shipPackages.noteOnDelivery" />
						<input type="hidden" value="${sp.noteOnExcp }"
							name="shipPackages.noteOnExcp" />
						<input type="hidden" value="${sp.packageType }"
							name="shipPackages.packageType" />
						<input type="hidden" value="${sp.length }"
							name="shipPackages.length" />
						<input type="hidden" value="${sp.width }"
							name="shipPackages.width" />
						<input type="hidden" value="${sp.height }"
							name="shipPackages.height" />
						<input type="hidden" value="${sp.dimUom }"
							name="shipPackages.dimUom" />
						<input type="hidden" value="${sp.baseCharge }"
							name="shipPackages.baseCharge" />
						<input type="hidden" value="${sp.deliveryConfirmFee }"
							name="shipPackages.deliveryConfirmFee" />
						<input type="hidden" value="${sp.packagingFee }"
							name="shipPackages.packagingFee" />
						<input type="hidden" value="${sp.actlCarrCharge }"
							name="shipPackages.actlCarrCharge" />
						<input type="hidden" value="${sp.insuranceCharge }"
							name="shipPackages.insuranceCharge" />
						<input type="hidden" value="${sp.adtlCustomerCharge }"
							name="shipPackages.adtlCustomerCharge" />
						<input type="hidden" value="${sp.carrierCharge }"
							name="shipPackages.carrierCharge" />
						<input type="hidden" value="${sp.customerCharge }"
							name="shipPackages.customerCharge" />
						<input type="hidden" value="${sp.shippingAccount }"
							name="shipPackages.shippingAccount" />
						<input type="hidden" value="${sp.note }" name="shipPackages.note" />
						<table border="0" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<th width="160">
									Package No
								</th>
								<td>
									<input name="shipPackages.packageId"
										id="shipPackages.packageId" type="text" class="NFText"
										value="${sp.packageId }" size="25" readonly="readonly" />
								</td>
								<th width="150">
									Status
								</th>
								<td>
									<input name="shipPackages.status" type="text"
										disabled="disabled" class="NFText" value="${sp.status }"
										size="25" />
								</td>
							</tr>
							<tr>
								<th rowspan="2" valign="top">
									Ship to
								</th>
								<td rowspan="2">
									<textarea name="shipPackages.shiptoAddress" readonly="readonly"
										class="content_textarea2">${sp.shiptoAddress }</textarea>
								</td>
								<th>
									Shipment Method
								</th>
								<td>
									<input name="shipPackages.shipMethod" type="text"
										disabled="disabled" class="NFText"
										value="${sp.shipMethod }" size="25" />
								</td>
							</tr>
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<th>
									Shipment Date
								</th>
								<td>
									<input name="shipPackages.shipmentDate" type="text"
										class="NFText" value="${sp.shipmentDate }" size="25"
										readonly="readonly" />
								</td>
								<th>
									Tracking No
								</th>
								<td>
									<input name="shipPackages.trackingNo" type="text"
										class="NFText" value="${sp.trackingNo }" size="25"
										readonly="readonly" />
								</td>
							</tr>
							<tr>
								<th>
									Shipping Clerk
								</th>
								<td>
									
									<input name="" type="text"
										class="NFText" value="${userName }"
										size="25" readonly="readonly" />
								</td>
								<th>
									Created Date
								</th>
								<td>
									<input name="shipPackages.creationDate" type="text"
										class="NFText" value="<%=new Date().toLocaleString()%>"
										size="25" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<th>
									Modified By
								</th>
								<td>
									<input name="shipPackages.modifiedBy" type="hidden"
										class="NFText" value="${loginName }" size="25"
										readonly="readonly" />
									<input name="modifiedBy" type="text"
										class="NFText" value="${loginNames }" size="25"
										readonly="readonly" />
								</td>
								<th>
									Modified Date
								</th>
								<td>
									<input name="shipPackages.modifyDate" type="text"
										class="NFText" value="<%=new Date().toLocaleString()%>"
										size="25" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<td colspan="4" align="center">
									<input type="button" name="Submit193"
										value="View/Print Packing Slip" class="search_input2"
										onclick="print();" />
								</td>
							</tr>
						</table>
					</div>
				</div>

				<div id="dhtmlgoodies_tabView1">
					<div class="dhtmlgoodies_aTab">
						<table width="955" border="0" cellpadding="0" cellspacing="0"
							class="list_table">
							<tr>
								<th width="50">
									Line No
								</th>
								<th width="50">
									Order No
								</th>
								<th width="50">
									Item No
								</th>
								<th width="300">
									Name
								</th>
								<th width="50">
									Qty
								</th>
								<th width="50">
									Size
								</th>
								<th width="100">
									Qty Missing
								</th>
								<th width="100">
									Size Missing
								</th>
							</tr>
						</table>
						<div class="frame_box" style="height: 240px;">
							<table width="955" border="0" cellpadding="0" cellspacing="0"
								class="list_table">
								<s:iterator value="pageShipPackageLineDTO.result">
									<tr>
										<td width="50" align="center">
											${pkgLineId }
										</td>
										<td width="50">
											${orderNo }
										</td>
										<td width="50">
											${itemNo }
										</td>
										<td width="300">
											${name }
										</td>
										<td width="50">
											${quantity }
										</td>
										<td width="50">
											${size }
										</td>
										<td width="100">
											${missingQty }
										</td>
										<td width="100">
											${missingSize }
										</td>
									</tr>
								</s:iterator>
							</table>
						</div>
						<c:if test="${poPk=='ok'}">
							<div class="grayr">
								<jsp:include page="/common/db_pager.jsp">
									<jsp:param value="${ctx}/work_order!purchaseViewPackage.action"
										name="moduleURL" />
								</jsp:include>
							</div>
						</c:if>
						<c:if test="${poPk!='ok'}">
							<div class="grayr">
								<jsp:include page="/common/db_pager.jsp">
									<jsp:param
										value="${ctx}/work_order!getPackageInformation.action"
										name="moduleURL" />
								</jsp:include>
							</div>
						</c:if>
					</div>
				</div>

			</div>
			<div class="button_box">
				<input type="submit" name="Submit193" value=" Save"
					class="search_input" />
				<input type="submit" name="Submit124" value="Cancel"
					class="search_input" onclick="window.close();" />
			</div>
		</form>
	</body>
</html>
<script type="text/javascript">
initTabs('dhtmlgoodies_tabView1',Array('Package Lines'),0,998,320);
</script>
