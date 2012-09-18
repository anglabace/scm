<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}ajax.js"></script>

<link href="${global_css_url}tab-view.css" rel="stylesheet"
	type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}TabbedPanels.js"></script>
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
	type="text/css" />

<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script
	src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js"
	type="text/javascript"></script>
<link type="text/css"
	href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script src="${global_js_url}jquery/ui/ui.core.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1"
	type="text/javascript"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}scm/product/manager_task.js?v=2"></script>
<script src="${global_js_url}util/util.js" type="text/javascript"></script>
<script src="${global_js_url}/recordTime.js" type="text/javascript"></script>
		<script>
			function selectAll(form,chkAll){
				var chk = form.elements;
				for(var i=0;i<chk.length;i++){
					chk[i].checked = chkAll.checked;
				}				
			}
			
			function listAll(obj, chk){
				if (chk == null){
					chk = 'checkboxes';
				}
				
				var elems = obj.form.getElementsByTagName("INPUT");
				for (var i=0; i < elems.length; i++){
					if (elems[i].name == chk || elems[i].name == chk + "[]"){
						elems[i].checked = obj.checked;
					}
				}
			}

			$(function() {            
            	$('#detailsFram').dialog({
					autoOpen: false,
					height: 300,
					width: 600,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            });
            
			function details(orderNo,overDuo,receivingFlag){
				
				if(orderNo==null){
					return ;
				}
				var notRecItem = "0";
				var expItem = "0";
				if(overDuo>0){
					expItem = "1";
				}
				if(receivingFlag=="0"){
					notRecItem = "1";
				}
				$('#detailsFram').dialog("option", "open", function() {	
				var htmlStr = '<iframe src="shipping!searchShippingRuleDetail.action?orderNo='+orderNo+'&notRecItem='+notRecItem+'&expItem='+expItem+'" height="250" width="550" scrolling="no" style="border:0px" frameborder="0"></iframe>';
				$('#detailsFram').html(htmlStr);
				});
				$('#detailsFram').dialog('open');
			}
		</script>
	</head>

	<body class="content">
		<form action="" name="shippingListForm" method="post">
			<script type="text/javascript">
				<c:if test="${request.sign == 1}">
					alert(" You search's Shipments not exist !");
				</c:if>
			</script>
			<c:if test="${warehouseType=='SALES'}">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td style="padding-right: 17px;"></td>
					</tr>
					<tr>
						<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0" class="list_table" style="TABLE-LAYOUT: fixed">
								<tr>
									<th width="50">
										<div align="center">
											<input name="checkc_all" type="checkbox" onclick="selectAll(shippingListForm,this)" />
										</div>
									</th>
									<th width="60">
										Order No
									</th>
									
									<th width="50">
										US PO NO
									</th>
									<th width="50">
										Status
									</th>
									<th width="100">
										Product/Service Type
									</th>
									<th width="60">
										Priority
									</th>
									<th width="80">
										Shipment No
									</th>
									<th width="120">
										Ship Via
									</th>
									<th width="120">
										Ship To
									</th>
									<th width ="100">
										Ship Rule
									</th>
									<th width ="50">
										Over due?
									</th> 
									<th width ="70">
										Receive Time
									</th> 
									<th>
										Green Account
									</th>
									
								</tr>
							</table>
							<div class="list_box" style="height: 290px;">
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="0" class="list_table" style="TABLE-LAYOUT: fixed">
									<c:set var="rowcount" value="1"></c:set>
									<s:iterator value="pageShipmentsDTO.result">
										<c:if test="${rowcount mod 2 == 0}">
											<c:set var="tdclass" value=" class='list_td2'"></c:set>
										</c:if>
										<c:if test="${rowcount mod 2 == 1}">
											<c:set var="tdclass" value=""></c:set>
										</c:if>
										<tr>
											<td align="center" width="50"${tdclass}>
												<div align="center">
													<input type="checkbox" id="chk" name="chk" value="${orderNo },${shipmentNo },${warehouseType },${shipmentId }" />
												</div>
											</td>
											<td align="center" width="60" ${tdclass} align="center">
												<!-- <a href="shipments!getShipInfo.action?shipmentNo=${shipmentNo }" target="mainFrame">${orderNo }</a> -->
												<a href="order/order!edit.action?orderNo=${orderNo }&operation_method=view" target="_blank">${orderNo }</a>
											</td>
											
											<td align="center" width="50" ${tdclass} align="center">
												
												${usPoNo }
											</td>
											<td align="center" width="50"${tdclass}>
												${status }
											</td>
											<td align="center" width="100"${tdclass}>
												${serviceType }
											</td>
											<td align="center" width="60"${tdclass}>
												${priority }
											</td>
											<td align="center" width="80"${tdclass}>
												<a href="shipments!getShipInfo.action?shipmentNo=${shipmentId }" target="mainFrame">${shipmentNo }</a>
											</td>
											<td align="center" width="120"${tdclass}>
												${shipVia }
											</td>
											<td align="center" width="120"${tdclass}>
												${shipTo }
											</td>
											<td align="center"${tdclass} width ="100">
												<c:if test="${receivingFlag==null }"><img src="images/complete_received.jpg"  width="16" height="16"/></c:if>
												<c:if test="${receivingFlag=='1' }"><img src="images/complete_received.jpg"  width="16" height="16"/></c:if>
												<c:if test="${receivingFlag=='0' }"><img src="images/immediate_delivery.jpg"  width="16" height="16"/></c:if>
												<a href ="javascript:void(0)" onclick="details('${orderNo }','${overDuo}','${receivingFlag }')">&nbsp;
												<span onMouseOver="this.style.textDecoration='underline';" onMouseout="this.style.textDecoration='none';">Details</span></a>
											</td>
											<td align="center" width="50" ${tdclass}>
												<c:if test="${OverDuo>0 }"><font color="red">${overDuo }</font></c:if>
											</td>
											<td align="center" width="70" ${tdclass}>
												${receiveTime }
											</td>
											<td align="center"${tdclass}>
												<c:if test="${greenAccFlag=='Y'||greenAccFlag=='y'}">
													<img src="images/green_icon.gif"  width="27" height="23"/>
												</c:if>
											</td>
										</tr>
										<c:set var="rowcount" value="${rowcount+1}"></c:set>
									</s:iterator>
								</table>
							</div>
							<div class="grayr">
								<jsp:include page="/common/db_pager.jsp">
									<jsp:param value="${ctx}/shipping!shippingList.action"
										name="moduleURL" />
								</jsp:include>
							</div>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${warehouseType=='MANUFACTURING'}">
				<table width="1010" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td style="padding-right: 17px;"></td>
					</tr>
					<tr>
						<td>
							<table width="1010" border="0" align="center" cellpadding="0"
								cellspacing="0" class="list_table">
								<tr>
									<th width="50">
										<div align="center">
											<input name="checku_All" type="checkbox" onclick="selectAll(shippingListForm,this)" />
										</div>
									</th>
									<th width="60">
										Order No
									</th>
									<th width="80">
										Status
									</th>
									<th width="80">
										Order Type
									</th>
									<th width="60">
										Priority
									</th>
									<th width="80">
										package No
									</th>
									<th width="80">
										Ship Via
									</th>
									<th width="330">
										Ship To
									</th>
									<th>
										Warehouse
									</th>
								</tr>
							</table>
							<div class="list_box" style="height: 290px;">
								<table width="993" border="0" align="center" cellpadding="0"
									cellspacing="0" class="list_table">
									<c:set var="rowcount" value="1"></c:set>
									<s:iterator value="pagePackagesDTO.result">
										<c:if test="${rowcount mod 2 == 0}">
											<c:set var="tdclass" value=" class='list_td2'"></c:set>
										</c:if>
										<c:if test="${rowcount mod 2 == 1}">
											<c:set var="tdclass" value=""></c:set>
										</c:if>
										<tr>
											<td width="50" align="center"${tdclass}>
												<input type="checkbox" name="chk" id="chk" value="${orderNo },${packageNo },${warehouseType }" />
											</td>
											<td width="60" align="center"${tdclass}>
												${orderNo}
											</td>
											<td align="center" width="80"${tdclass}>
												${status }
											</td>
											<td align="center" width="80"${tdclass}>
												${orderType }
											</td>
											<td align="center" width="60"${tdclass}>
												${priority }
											</td>
											<td align="center" width="80"${tdclass}>
												<a href="shipments!getPkgInfo.action?packageId=${packageNo }" target="mainFrame" >${packageNo }</a>
											</td>
											<td align="center" width="80"${tdclass}>
												${shipVia }
											</td>
											<td align="center" width="330"${tdclass}>
												${shipTo }
											</td>
											<td align="center"${tdclass}>
												GenScript NJ Warehouse
											</td>
										</tr>
										<c:set var="rowcount" value="${rowcount+1}"></c:set>
									</s:iterator>
								</table>
							</div>
							<div class="grayr">
								<jsp:include page="/common/db_pager.jsp">
									<jsp:param value="${ctx}/shipping!shippingList.action" name="moduleURL" />
								</jsp:include>
							</div>
						</td>
					</tr>
				</table>
			</c:if>
		</form>
	</body>
			<div id="detailsFram" title=" Details "
			style="visible: hidden" />
</html>