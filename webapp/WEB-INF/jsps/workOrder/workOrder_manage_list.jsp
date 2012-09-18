<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Order Management</title>
		<script type="text/javascript" src="${global_js_url}AJS.js"></script>
		<script type="text/javascript" src="${global_js_url}AJS_fx.js"></script>
		<script language="javascript" type="text/javascript"
			src="${global_js_url}newwindow.js"></script>

		<script language="javascript" type="text/javascript"
			src="${global_js_url}ajax.js"></script>
		<script language="javascript" type="text/javascript"
			src="${global_js_url}tab-view.js"></script>


		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}tab-view.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}gb_styles.css" rel="stylesheet"
			type="text/css" media="all" />
		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
		<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" language="javascript"
			src="${global_js_url}jquery/jquery.js"></script>
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
		<script type="text/javascript">
			var GB_ROOT_DIR = "./greybox/";
			function MM_jumpMenu(targ,selObj,restore){ //v3.0
			  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
			  if (restore) selObj.selectedIndex=0;
			}
		</script>

		<script type="text/javascript" language="javascript">
			 function listAll(obj, chk){
              if (chk == null)
			  {
			    chk = 'checkboxes';
			  }
			
			  var elems = obj.form.getElementsByTagName("INPUT");
			
			  for (var i=0; i < elems.length; i++)
			  {
			    if (elems[i].name == chk || elems[i].name == chk + "[]")
			    {
			      elems[i].checked = obj.checked;
			    }
			  }
          }
           function subAllDel(){
             document.forms[0].submit();
          }
          $(function() {            
           	$('#shipping_document').dialog({
					autoOpen: false,
					height: 350,
					width: 770,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
           });

			function view_shipping_doc(orderNo) {
				
				if(orderNo==""||orderNo == null){
					alert("Please choose a Order Item.");
					return ;
				}
				$('#shipping_document').dialog("option", "open", function() {	
				var htmlStr = '<iframe src="work_order!searchOrderItem.action?orderNo='+orderNo+'" height="300" width="720" scrolling="no" style="border:0px" frameborder="0"></iframe>';
				$('#shipping_document').html(htmlStr);
				});
				$('#shipping_document').dialog('open');
			}
			</script>
	</head>

	<body>
		<form action="work_order!batchOrderReceiving.action">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td style="padding-right: 17px;"></td>
				</tr>
				<tr>
					<td>
						<c:if test="${china=='ok'}">
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0" class="list_table" style="TABLE-LAYOUT: fixed">
								<tr>
									<th width="30">
										<div align="center">
											<input onclick='listAll(this, "cks")' type="checkbox" />
										</div>
									</th>
									<th style="width: 110px">
										Work Order No
									</th>
									<th style="width: 50px">
										Status
									</th>
									<th style="width: 50px">
										Vendor
									</th>
									<th style="width: 80px">
										Order Type
									</th>
									<th style="width: 100px">
										Order Reference
									</th>
									<th style="width: 80px">
										Order Date
									</th>
									<th style="width: 80px">
										Due Date
									</th>
									<th style="width: 100px">
										Product/Service
									</th>
									<th style="width: 100px">
										Work Group
									</th>
									<th>
										Warehouse
									</th>
								</tr>
							</table>
							<div class="list_box" style="height: 340px;">
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="0" class="list_table">
									<c:set var="rowcount" value="1"></c:set>

									<s:iterator value="pageWorkOrderDTO.result">

										<c:if test="${rowcount mod 2 == 0}">
											<c:set var="tdclass" value=" class='list_td2'"></c:set>
										</c:if>
										<c:if test="${rowcount mod 2 == 1}">
											<c:set var="tdclass" value=""></c:set>
										</c:if>
										<tr align="center">
											<td width="30"${tdclass}>
												<input name="cks" id="cks" type="checkbox" value="${orderNo},${workGroupName}" />
											</td>
											<td style="width: 110px" align="center"${tdclass}>
												&nbsp;
												<a
													href="work_order!getReceiveOrderWo.action?orderNo=${orderNo }"
													target="mainFrame">${orderNo}</a>
											</td>
											<td style="width: 50px"${tdclass}>
												&nbsp;${status}
											</td>
											<td style="width: 50px"${tdclass}>
												&nbsp;
												<c:if test="${fn:length(workGroupName)>10}">
															${fn:substring(workGroupName,0,10)}...
														</c:if>
														<c:if test="${fn:length(workGroupName)<=10}">
															${workGroupName}
														</c:if>
											</td>
											<td style="width: 80px"${tdclass}>
												&nbsp;
												<c:if test="${fn:length(type)>8}">
															${fn:substring(type,0,8)}...
														</c:if>
														<c:if test="${fn:length(type)<=8}">
															${type}
														</c:if>
											</td>
											<td style="width: 100px"${tdclass}>
												&nbsp;${soNo}
											</td>
											<td style="width: 80px"${tdclass}>
												&nbsp;
												<s:date format="yyyy-MM-dd" name="orderDate"/>
											</td>
											<td style="width: 80px"${tdclass}>
												&nbsp;
												<s:date format="yyyy-MM-dd" name="exprDate"/>
											</td>
											<td style="width: 100px"${tdclass}>
												&nbsp;${catalogNo}
											</td>
											<td style="width: 100px"${tdclass}>
												&nbsp;
												<c:if test="${fn:length(workGroupName)>10}">
															${fn:substring(workGroupName,0,10)}...
														</c:if>
														<c:if test="${fn:length(workGroupName)<=10}">
															${workGroupId}
														</c:if>
											</td>
											<td ${tdclass}>
												&nbsp;${name }
											</td>
										</tr>
										<c:set var="rowcount" value="${rowcount+1}"></c:set>
									</s:iterator>
								</table>
							</div>
							<div class="grayr">
								<jsp:include page="/common/db_pager.jsp">
									<jsp:param
										value="${ctx}/work_order!getWorkOrderManageList.action"
										name="moduleURL" />
								</jsp:include>
							</div>
						</c:if>
						<c:if test="${usa=='ok'}">
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0" class="list_table2" style="TABLE-LAYOUT: fixed">
								<tr>
									<th width="30">
										<div align="center">
											<input onclick='listAll(this, "cks")' type="checkbox" />
										</div>
									</th>
									<th style="width: 100px">
										Order Reference
									</th>
									<th style="width: 110px">
										Purchase Order No
									</th>
									<th style="width: 50px">
										 US PO NO
									</th>
									<th style="width: 50px">
										Status
									</th>
									<th style="width: 150px">
										Vendor
									</th>
									<th style="width: 80px">
										Service Type
									</th>
									<th style="width:70px">
										TAM
									</th>
									<th style="width: 80px">
										Order Date
									</th>
									<th style="width: 70px">
										Due Date
									</th>
									<th style="width: 80px">
										Grand Total
									</th>
									
									<th>
										Green Account
									</th>
									
								</tr>
							</table>
							<div class="list_box" style="height: 340px;">
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="0" class="list_table2" style="TABLE-LAYOUT: fixed">
									<c:set var="rowcount" value="1"></c:set>
									<s:iterator value="pagePurchaseOrderDTO.result">

										<c:if test="${rowcount mod 2 == 0}">
											<c:set var="tdclass" value=" class='list_td2'"></c:set>
										</c:if>
										<c:if test="${rowcount mod 2 == 1}">
											<c:set var="tdclass" value=""></c:set>
										</c:if>
										<tr align="center">
											<td width="30"${tdclass}>
												<input name="cks" id="cks" type="checkbox" value="${orderNo},${vendors.vendorName }" />
											</td>
											<td style="width: 100px"${tdclass}>
												&nbsp;${srcSoNo}
											</td>
											<td style="width: 110px"${tdclass}>
												&nbsp;
												<a
													href="work_order!getReceiveOrderPo.action?orderNo=${orderNo }"
													target="mainFrame">${orderNo}</a>
											</td>
											<td style="width: 50px"${tdclass}>
												&nbsp;
												${usPOrderNo}
											</td>
											<td style="width: 50px"${tdclass}>
												&nbsp;${status}
											</td>
											<td style="width: 150px"${tdclass}>
												&nbsp;${vendors.vendorName }
											</td>
											<td style="width: 80px"${tdclass}>
												${serviceType}
											</td>
											<td style="width: 70px"${tdclass}>
												${tam}
											</td>
											<td style="width: 80px"${tdclass}>
												&nbsp;
												<s:date format="yyyy-MM-dd" name="orderDate"/>
											</td>
											<td style="width: 70px"${tdclass}>
												&nbsp;<s:date format="yyyy-MM-dd" name="exprDate"/>
											</td>
											<td style="width: 80px"${tdclass}>
												&nbsp;${subTotal }
											</td>
											
											<td ${tdclass}>
												 <c:if test="${greenAccFlag=='Y'||greenAccFlag=='y'}">
												 	<div align="center"><img src="images/green_icon.gif" width="27" height="23" /></div>
												 </c:if>
											</td>
											
										</tr>
										<c:set var="rowcount" value="${rowcount+1}"></c:set>
									</s:iterator>
								</table>
							</div>
							<div class="grayr">
								<jsp:include page="/common/db_pager.jsp">
									<jsp:param
										value="${ctx}/work_order!getWorkOrderManageList.action"
										name="moduleURL" />
								</jsp:include>
							</div>
						</c:if>
					</td>
				</tr>
			</table>
		</form>
		<div id="shipping_document" title=" Files Document "
			style="visible: hidden" />
	</body>
</html>