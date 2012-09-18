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

//////////////////////wo///////////////////////////////
  $(function() {            
            	$('#workOrderItemReceiving').dialog({
					autoOpen: false,
					height: 350,
					width: 800,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            });
            
            function woopenSearchDlgss() {
            var cks = document.getElementsByName('cks');
            var d1 = new Array();
            var batchNo = '';
			for(var i=0;i<cks.length;i++){
				if(cks[i].checked){
					var ck = cks[i].value.split(',');
					 d1.push(ck[0]);
					 batchNo = ck[1];
				}
			}
			var chksShow2 = document.getElementById('chksShow2').value;
			if(d1 == null || d1.length == 0){
				alert('Please choose a record!');
				return;
}
	$('#workOrderItemReceiving').dialog("option", "open", function() {	
         var htmlStr = '<iframe src="work_order!getWorkOrderItemReceiving.action?batchNo='+batchNo+'&cks='+d1+'&chksShow2='+chksShow2+'" height="280" width="700" scrolling="no" style="border:0px" frameborder="0"></iframe>';
         $('#workOrderItemReceiving').html(htmlStr);
	});
	$('#workOrderItemReceiving').dialog('open');

}
		    $(function() {            
            	$('#workOrderReceiving').dialog({
					autoOpen: false,
					height: 350,
					width: 800,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            });
            
            function woopenSearchDlgs() {
              var cks = document.getElementsByName('ckss');
             var d1 = new Array();
			   var batchNo = '';
			for(var i=0;i<cks.length;i++){
					var ck = cks[i].value.split(',');
					d1.push(ck[0]);
					 batchNo = ck[1];
			}
	$('#workOrderReceiving').dialog("option", "open", function() {	
         var htmlStr = '<iframe src="work_order!getWorkOrderReceiving.action?batchNo='+batchNo+'&cks='+d1+'" height="280" width="700" scrolling="no" style="border:0px" frameborder="0"></iframe>';
         $('#workOrderReceiving').html(htmlStr);
	});
	$('#workOrderReceiving').dialog('open');

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
			var MANUFACTURING = 'MANUFACTURING';
			document.forms[0].action = "work_order!workOrderManageFrame.action?type="+MANUFACTURING;
			document.forms[0].submit();
		}
			
		function woSave() { 
          var cks = document.getElementsByName('cks');
	            var d1 = new Array();
				 for(var i=0;i<cks.length;i++){
					 if(cks[i].checked){
						 d1.push(cks[i].value);
						}
				}
				window.openiframe('work_order!unmatchedPackageWo.action?cks="'+d1+'"','840','440');
			}
		function woSave2() { 
				document.forms[0].action = "work_order!workOrderAllSave.action";
  				document.forms[0].submit();
			}
		function doReset2(){
			var chks = document.getElementById('chks').value;
			document.forms[0].action="work_order!workOrderReset.action?wo=wo2&chks="+chks;
			document.forms[0].submit();
		}
		function checkShow2(chk){
		  var d1 = document.getElementById('chksShow2').value;
			  if(chk.checked==false){
				 d1 +=chk.value+",";
			   }
			   document.getElementById('chksShow2').value=d1;
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
				<input type="hidden" value="${receivewo2 }" name="receivewo2"
				id="receivewo2" />
				<input type="hidden" value="${receivewo1 }" name="receivewo1"
				id="receivewo1" />
			<input type="hidden" value="${batchNo }" name="batchNo" id="batchNo" />
			<input type="hidden" value="${chks }" name="chks" id="chks" />
			<input type="hidden" value="" name="chksShow2" id="chksShow2"/>
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
										
										<c:if test="${whManu=='ok'}">
											<table width="961" border="0" cellspacing="0" cellpadding="0"
												class="list_table">
												<tr>
													<th width="30">
														<div align="center">
															<input onclick='listAll(this, "cks")' type="checkbox" />
														</div>
													</th>													
													<th width="60">
														Batch No
													</th>
													<th width="90">
														Work Order No
													</th>
													<th width="100">
														Order Reference
													</th>
													<th width="60">
														Item No
													</th>
													<th width="60">
														Catalog No
													</th>
													<th width="30">
														Name
													</th>
													<th width="80">
														Qty Expected
													</th>
													<th width="80">
														Qty Received
													</th>
													<th width="30">
														UOM
													</th>
													<th width="80">
														Size Expected
													</th>
													<th width="80">
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
										
										<c:if test="${whManu=='ok'}">
												<table width="961" border="0" cellspacing="0"
													cellpadding="0" class="list_table2">
													<c:forEach var="list1" items="${list2}">
											<input name="ckss" type="hidden" value="${list1.orderNo},${list1.batchNo }"/>
										</c:forEach>
													<s:iterator value="pageWorkOrderDTO.result">
													<c:if test="${rowcount mod 2 == 0}">
														<c:set var="tdclass" value=" class='list_td2'"></c:set>
													</c:if>
													<c:if test="${rowcount mod 2 == 1}">
														<c:set var="tdclass" value=""></c:set>
													</c:if>
													<tr>
														<td width="30" align="center" ${tdclass}>
															<input name="cks" id="cks" onclick="checkShow2(this);" type="checkbox" value="${orderNo},${batchNo }"
														<c:forEach var="temp" items="${listTemp2}">
															<c:if test="${temp[0] == orderNo}">
																checked="checked" 
															</c:if>
														</c:forEach>
													/>
															
														</td>														
														<td width="60" align="center" ${tdclass}>
															${batchNo }
														</td>
														<td width="90" align="center" ${tdclass}>
															${orderNo }
														</td>
														<td width="100" align="center" ${tdclass}>
															<s:date format="yyyy-MM-dd" name="orderDate"/>
														</td>
														<td width="60" align="center" ${tdclass}>
															${soItemNo}
														</td>
														<td width="60" align="center" ${tdclass}>
															${catalogNo}
														</td>
														<td width="30" align="center" ${tdclass}>
															${itemName }
														</td>
														<td width="80" align="center" ${tdclass}>
															${quantity }
														</td>
														<td width="80" align="center" ${tdclass}>
															<c:forEach items="${str}" var="str">
																<c:if test="${str==orderNo}">
																	${quantity}
																</c:if>
															</c:forEach>
															<c:forEach var="temp" items="${listTemp2}">
																<c:if test="${temp[0] == orderNo}">
																	${temp[1]}
																</c:if>
															</c:forEach>

														</td>
														<td width="30" align="center" ${tdclass}>
															${qtyUom }
														</td>
														<td width="80" align="center" ${tdclass}>
															${size }
														</td>
														<td width="80" align="center" ${tdclass}>
															<c:forEach items="${str}" var="str">
																<c:if test="${str==orderNo}">
																${size}
																</c:if>
															</c:forEach>
															<c:forEach var="temp" items="${listTemp2}">
																<c:if test="${temp[0] == orderNo}">
																	${temp[2]}
																</c:if>
															</c:forEach>
														</td>
														<td ${tdclass}>
															${locationCode }
														</td>
													</tr>
													<c:set var="rowcount" value="${rowcount+1}"></c:set>
													</s:iterator>
												</table>
												</c:if>
								</td>
							</tr>
							<tr>
								<td>
									<div class="grayr">
										<jsp:include page="/common/db_pager.jsp">
											<jsp:param
												value="${ctx}/work_order!getWorkOrderReceivingDetail.action"
												name="moduleURL" />
										</jsp:include>
									</div>
								</td>

							</tr>
							<tr>
								<td align="center">
									<c:if test="${whManu=='ok'}">
										<c:if test="${list2Size==null}">
											<input type="button" name="Submit2" with="100px"
												value="Receive" class="style_botton2"
												onclick="woopenSearchDlgss();" disabled="disabled" />
											<input type="button" name="Submit2" value="Receive All"
												class="style_botton2" onclick="woopenSearchDlgs();"
												disabled="disabled" />
												<input name="" type="reset" value="Reset" class="style_botton2" disabled="disabled"/>
										</c:if>
										<c:if test="${list2Size!=null}">
											<input type="button" name="Submit2" with="100px"
												value="Receive" class="style_botton2"
												onclick="woopenSearchDlgss();" />
											<input type="button" name="Submit2" value="Receive All"
												class="style_botton2" onclick="woopenSearchDlgs();" />
											<input name="" type="reset" value="Reset" class="style_botton2" onclick="doReset2();"/>
										</c:if>
									</c:if>
									<input type="submit" name="Submit2" value="Scan & Receive"
										class="style_botton2" />
								</td>
							</tr>
						</table>
					
					</div>
				</div>
				<div class="button_box">
					<c:if test="${whManu=='ok'}">
						<c:if test="${receivewo2=='woReceive2'}">
							<input type="button" class="search_input" value="Save"
								onclick="woSave2();" />
						</c:if>
						<c:if test="${receivewo1=='woReceive1'}">
							<input type="button" class="search_input" value="Save"
								onclick="woSave();" />
						</c:if>
					</c:if>
					<input type="button" name="" value="Cancel" class="search_input"
						onclick="doCancel();" />
				</div>
			</div>
		</form>
			<div id="workOrderItemReceiving" title="Work Order Item Receiving "
				style="visible: hidden;"></div>
			<div id="workOrderReceiving" title="Work Order Receiving "
				style="visible: hidden;">
			</div>
	</body>
</html>