<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Order Management</title>
		<link href="stylesheet/scm.css" rel="stylesheet" type="text/css" />
		<link href="stylesheet/table.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript" src="js/ajax.js"></script>
		<script language="javascript" type="text/javascript"
			src="js/tab-view.js"></script>
		<link href="stylesheet/tab-view.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript"
			src="js/TabbedPanels.js"></script>
		<link href="stylesheet/SpryTabbedPanels.css" rel="stylesheet"
			type="text/css" />
		<script language="JavaScript" type="text/javascript">  
 </script>

		<script type="text/javascript">
        var GB_ROOT_DIR = "./greybox/";
    </script>
		<script type="text/javascript" src="greybox/AJS.js"></script>
		<script type="text/javascript" src="greybox/AJS_fx.js"></script>
		<script type="text/javascript" src="greybox/gb_scripts.js"></script>
		<link href="greybox/gb_styles.css" rel="stylesheet" type="text/css"
			media="all" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}greybox/gb_styles.css" rel="stylesheet"
			type="text/css" media="all" />
		<link href="${global_css_url}tab-view.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="${global_js_url}jquery/jquery.js"></script>
		<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
			type="text/css" />
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

		<style type="text/css">
<!--
.hidlayer {
	font-size: 12px;
	height: 370px;
	width: 666px;
	position: absolute;
	z-index: 9999;
	left: 20%;
	top: 20%;
	display: none;
}
-->
</style>

		<script>
 /////////////////////po/////////////////////////////
		    $(function() {            
            	$('#purchaseOrderItemReceiving').dialog({
					autoOpen: false,
					height: 350,
					width: 800,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            });
            
            function openSearchDlgss() {
            var cks = document.getElementsByName('cks');
            var d1 = new Array();
            var rece1 = new Array();
            var trackingNo = '';
			 for(var i=0;i<cks.length;i++){
				 if(cks[i].checked){
					// d1.push(cks[i].value);
					 var ck = cks[i].value.split(',');
					d1.push(ck[0]);
					 rece1.push(ck[2]);
					trackingNo = ck[1];
					}
			}
			var chksShow = document.getElementById('chksShow').value;
			if(d1 == null || d1.length == 0){
				alert('Please choose one record!');
				return;
			}
	$('#purchaseOrderItemReceiving').dialog("option", "open", function() {	
         var htmlStr = '<iframe src="work_order!getPurchaseOrderItemReceiving.action?receIds='+rece1+'&cks='+d1+'&trackingNo='+trackingNo+'&chksShow='+chksShow+'" height="280" width="700" scrolling="no" style="border:0px" frameborder="0"></iframe>';
         $('#purchaseOrderItemReceiving').html(htmlStr);
	});
	$('#purchaseOrderItemReceiving').dialog('open');

}
		    $(function() {            
            	$('#purchaseOrderReceiving').dialog({
					autoOpen: false,
					height: 350,
					width: 800,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            });
            
            function openSearchDlgs() {
             var cks = document.getElementsByName('ckss');
             var d1 = new Array();
             var rece1 = new Array();
			 var trackingNo = '';
			 for(var i=0;i<cks.length;i++){
			 		var ck = cks[i].value.split(',');
					 d1.push(ck[0]);
					 rece1.push(ck[2]);
					 trackingNo = ck[1];
			}
	$('#purchaseOrderReceiving').dialog("option", "open", function() {	
         var htmlStr = '<iframe src="work_order!getPurchaseOrderReceiving.action?receIds='+rece1+'&cks='+d1+'&trackingNo='+trackingNo+'" height="280" width="700" scrolling="no" style="border:0px" frameborder="0"></iframe>';
         $('#purchaseOrderReceiving').html(htmlStr);
	});
	$('#purchaseOrderReceiving').dialog('open');

}
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
          function doCancel(){
			document.forms[0].action = "work_order!workOrderManageFrame.action";
			document.forms[0].submit();
		}
         function poSave() { 
          var cks = document.getElementsByName('cks');
	            var d1 = new Array();
				 for(var i=0;i<cks.length;i++){
					 if(cks[i].checked){
						 d1.push(cks[i].value);
						}
				}
				window.openiframel("work_order!unmatchedPackagePo.action?cks='"+d1+"'","800","440","10%");
			}
		function poSave2() { 
				document.forms[0].action = "work_order!purchaseOrderAllSave.action";
  				document.forms[0].submit();
			}
		function doReset(){
			var trackingNo = document.getElementById('trackingNo').value;
			document.forms[0].action="work_order!purchaseOrderReset.action?po=po3&trackingNo="+trackingNo;
			document.forms[0].submit();
		}
		function checkShow(chk){
		 	 var d1 = document.getElementById('chksShow').value;
			  if(chk.checked==false){
				 d1 +=chk.value+",";
			   }
			   document.getElementById('chksShow').value=d1;
		}
</script>
		<script language="javascript" type="text/javascript"
			src="js/newwindow.js"></script>
	</head>

	<body class="content" style="background-image: none;">
	<div id="frame12" style="display: none;" class="hidlayer1">
				<iframe id="hidkuan" name="hidkuan" src="#" width="668"
					height="425" frameborder="0" allowtransparency="true"></iframe>
			</div>
		<form action="" name="receivingForm" method="post">
			<input type="hidden" value="${trackingNo }" name="trackingNo"
				id="trackingNo" />
			<input type="hidden" name="warehouse" id="warehouse"
				value="${warehouse }" />
				<input type="hidden" value="" name="chksShow" id="chksShow"/>
			<div class="scm">
				<div class="title_content">
					<div class="title">
						Batch Order Receiving
					</div>
				</div>
				<div id="dhtmlgoodies_tabView1">

					<div class="dhtmlgoodies_aTab" style="width: 978px;">

						<table width="898" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									<div style="margin-right: 17px;">
										<c:if test="${whSales=='ok'}">
											<table width="961" border="0" cellspacing="0" cellpadding="0"
												class="list_table">
												<tr>
													<th width="30">
														<div align="center">
															<input onclick='listAll(this, "cks")' type="checkbox" />
														</div>
													</th>
													<th width="100">
														Tracking No
													</th>
													<th width="90">
														Sales Order No
													</th>
													<th width="40">
														PO NO
													</th>
													<th width="30">
														Item No
													</th>
													<th width="60">
														Catalog No
													</th>
													<th width="140">
														Name
													</th>
													<th width="50">
														Status
													</th>
													<th width="50">
														Qty Expected
													</th>
													<th width="50">
														Qty Received
													</th>
													<th width="30">
														UOM
													</th>
													<th width="50">
														Size Expected
													</th>
													<th width="50">
														Size Received
													</th>
													<th>
														Receiving Location
													</th>
												</tr>
											</table>
										</c:if>
										
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="list_box" style="width: 978px; height: 300px;">
										<c:if test="${whSales=='ok'}">
											
												<table width="961" border="0" cellspacing="0"
													cellpadding="0" class="list_table2">
													<c:forEach var="list1" items="${list1}">
													<input name="ckss"  type="hidden" value="${list1.orderItemId},${list1.trackingNo }"/>
													</c:forEach>
													<s:iterator value="pagePurchaseOrderItemDTO.result">
														<c:if test="${rowcount mod 2 == 0}">
															<c:set var="tdclass" value=" class='list_td2'"></c:set>
														</c:if>
														<c:if test="${rowcount mod 2 == 1}">
															<c:set var="tdclass" value=""></c:set>
														</c:if>
													<tr>
														<td width="30" align="center" ${tdclass}>
																<input name="cks" id="cks" onclick="checkShow(this);" type="checkbox" value="${orderItemId},${trackingNo },${receId }"
														<c:forEach var="temp" items="${listTemp}">
															<c:if test="${temp[0] == orderItemId}">
																checked="checked" 
															</c:if>
														</c:forEach>
													/>
														</td>
														<td width="100" align="center" ${tdclass}>
															${trackingNo }
														</td>
														<td width="90" align="center" ${tdclass}>
															${purchaseOrder.srcSoNo }
														</td>
														<td width="40" align="center" ${tdclass}>
															${purchaseOrder.orderNo }
														</td>
														<td width="30" align="center" ${tdclass} >
															${itemNo }
														</td>
														<td width="60" align="center" ${tdclass}>
															${catalogNo }
														</td>
														<td width="140" align="center" ${tdclass}>
															${name }
														</td>
														<td width="50" align="center" ${tdclass}>
															${status }
														</td>
														<td width="50" align="center" ${tdclass}>
															${quantity }
														</td>
														<td width="50" align="center" ${tdclass}>
															<c:forEach items="${str}" var="str">
																<c:if test="${str==orderItemId}">
																	${quantity}&nbsp;
																</c:if>
															</c:forEach>
															<c:forEach var="temp" items="${listTemp}">
																<c:if test="${temp[0] == orderItemId}">
																	${temp[1]}
																</c:if>
															</c:forEach>
														</td>
														<td width="30" align="center" ${tdclass}>
															${qtyUom }
														</td>
														<td width="50" align="center" ${tdclass}>
															${fn:endsWith(size,'.0')||fn:endsWith(size,'.00')||fn:endsWith(size,'.000')?fn:substring(size,0,fn:indexOf(size,".")):size}&nbsp;
															&nbsp;${sizeUom }
														</td>
														<td width="50" align="center" ${tdclass}>
															<c:forEach items="${str}" var="str">
																<c:if test="${str==orderItemId}">
																	${fn:endsWith(size,'.0')||fn:endsWith(size,'.00')||fn:endsWith(size,'.000')?fn:substring(size,0,fn:indexOf(size,".")):size}
																	&nbsp;${sizeUom }
																</c:if>
															</c:forEach>
															<c:forEach var="temp" items="${listTemp}">
																<c:if test="${temp[0] == orderItemId}">
																	${fn:endsWith(temp[2],'.0')||fn:endsWith(temp[2],'.00')||fn:endsWith(temp[2],'.000')?fn:substring(temp[2],0,fn:indexOf(temp[2],".")):temp[2]}&nbsp;
																${sizeUom }
																</c:if>
															</c:forEach>
															&nbsp;
														</td>
														<td ${tdclass}>
															<c:forEach items="${str}" var="str">
																<c:if test="${str==orderItemId}">
																	${lbw}
																</c:if>
															</c:forEach>
															<c:forEach var="temp" items="${listTemp}">
																<c:if test="${temp[0] == orderItemId}">
																	${temp[5]}
																</c:if>
															</c:forEach>
														</td>
													</tr>
														<c:set var="rowcount" value="${rowcount+1}"></c:set>
													</s:iterator>
												</table>
											
										</c:if>
										</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="grayr">
										<jsp:include page="/common/db_pager.jsp">
											<jsp:param
												value="${ctx}/work_order!getPurchaseReceivingTrackingNoDetail.action"
												name="moduleURL" />
										</jsp:include>
									</div>
								</td>

							</tr>
							<tr>
								<td align="center">
									<c:if test="${whSales=='ok'}">
										<c:if test="${list1Size==0}">
											<input type="button" name="Submit2" with="100px"
												value="Receive" class="style_botton2"
												onclick="openSearchDlgss();" disabled="disabled" />
											<input type="button" name="Submit2" value="Receive All"
												class="style_botton2" onclick="openSearchDlgs();"
												disabled="disabled" />
													<input name="" type="reset" value="Reset" class="style_botton2" disabled="disabled"/>
										</c:if>
										<c:if test="${list1Size!=0}">
											<input type="button" name="Submit2" with="100px"
												value="Receive" class="style_botton2"
												onclick="openSearchDlgss();" />
											<input type="button" name="Submit2" value="Receive All"
												class="style_botton2" onclick="openSearchDlgs();" />
											<input name="" type="reset" value="Reset" class="style_botton2" onclick="doReset();"/>
										</c:if>
									</c:if>
									<input type="button" name="Submit2" value="Scan & Receive"
										class="style_botton2" />
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="button_box">
					
					<c:if test="${whSales=='ok'}">
						<c:if test="${receive1=='ReceiveAll'}">
							<input type="button" class="search_input" value="Save"
								onclick="poSave2();" />
						</c:if>
						<c:if test="${receive2=='Receive'}">
							<input type="button" class="search_input" value="Save"
								onclick="poSave();" />
						</c:if>
					</c:if>
					<input type="button" name="" value="Cancel" class="search_input"
						onclick="doCancel()" />
				</div>
			</div>
		</form>
			<div id="purchaseOrderItemReceiving" title="Purchase Order Item Receiving " 
				style="visible: hidden;">
			</div>
			<div id="purchaseOrderReceiving" title="Purchase Order Receiving "
				style="visible: hidden;">
			</div>
			
	</body>
</html>