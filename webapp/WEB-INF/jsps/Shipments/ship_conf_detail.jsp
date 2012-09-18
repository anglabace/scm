<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Shipment processing</title>
		<base href="${global_url}" />
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
		<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
		<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
		<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
		<script type="text/javascript" language="javascript" src="${global_js_url}jquery/jquery.js"></script>
		<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
		
		<script type="text/javascript">
			$(function() {            
             $('#vendor_search_dlg3').dialog({
			autoOpen: false,
			height: 500,
			width: 660,
			modal: true,
			bgiframe: true,
			buttons: {
			}
			});
			            });
			function openSearchDlgss(orderNos) {
			$('#vendor_search_dlg3').dialog("option", "open", function() { 
			         var htmlStr = '<iframe src="shipments!order_reference_list.action?orderNos='+orderNos+'"  height="430" width="620" scrolling="no"  style="border:0px" frameborder="0"></iframe>';
			         $('#vendor_search_dlg3').html(htmlStr);
			});
			$('#vendor_search_dlg3').dialog('open');
			
			}
			
			function Len(str)
			{
			     var i,sum;
			     sum=0;
			     for(i=0;i<str.length;i++)
			     {
			         if ((str.charCodeAt(i)>=0) && (str.charCodeAt(i)<=255))
			             sum=sum+1;
			         else
			             sum=sum+2;
			     }
			     return sum;
			}
			function checkLen(){
			   desc = document.getElementById('description').value;
			   if(Len(desc)>255)
			   {
			    alert("Description length is too long!");
			    return false;
			   }
			
			}
			function toggleShowMore_img(obj,divID){
			var oId = document.getElementById(divID);
			if (obj.src.indexOf('arrow.jpg') > 0){
				obj.src="${global_url}images/title_bgewe.jpg";
				oId.style.display = "none"; 
			}else{
				obj.src="${global_url}images/arrow.jpg";
				oId.style.display = "block"; 
			}
			}

			function show_edit(noteKey, searchNoteType,orderNo){
				
				if(orderNo==null||orderNo==''){
					orderNo = $("#sessOrderNo").val();
					var params = '?sessOrderNo='+orderNo+'&searchNoteType=' + searchNoteType+'&tmpStr=tt'+'&status=CC';
	                noteUrl = "order_note/order_note!edit.action";
					$('#instruction_update_dlg').dialog("option","params", params);
					$('#instruction_update_dlg').dialog('open'); 
				}else{
					var params = '?sessOrderNo='+orderNo+'&noteKey='+noteKey + '&searchNoteType=' + searchNoteType+'&tmpStr=tt';
	                noteUrl = "order_note/order_note!edit.action";
					$('#instruction_update_dlg').dialog("option","params", params);
					$('#instruction_update_dlg').dialog('open'); 
				}
				
			}

			$(document).ready(function(){
				$('#instruction_dlg').dialog({
					autoOpen: false,
					height: 400,
					width: 600,
					modal: true,
					bgiframe: true,
					buttons: {
					},
					open:function(){
						var params = $('#instruction_dlg').dialog("option", "params");
						var url = noteUrl + params;
				        var htmlStr = '<iframe src="'+url+'" height="350" width="570" scrolling="no" style="border:0px;" frameborder="0"></iframe>';
						$('#instruction_dlg').html(htmlStr);
					}
				});
			});

			$(document).ready(function(){
				$('#instruction_update_dlg').dialog({
					autoOpen: false,
					height: 400,
					width: 600,
					modal: true,
					bgiframe: true,
					buttons: {
					},
					open:function(){
						var params = $('#instruction_update_dlg').dialog("option", "params");
						var url = noteUrl + params;
				        var htmlStr = '<iframe src="'+url+'" height="350" width="570" scrolling="no" style="border:0px;" frameborder="0"></iframe>';
						$('#instruction_update_dlg').html(htmlStr);
					}
				});
			});
		</script>

	</head>
	

	<body class="content"  style="background-image:none;">
			<div class="scm" style="overflow:auto; width: 1100PX">
			<div class="title_content">
			  <div style="padding-left: 20px;color: #5579A6;vertical-align:middle;"><img src="${global_url}images/arrow.jpg" style="width:16px;height:17px;vertical-align:middle;" onclick="toggleShowMore_img(this,'search_box1');"/>&nbsp;&nbsp;Shipment Processing-# ${sDto.shipmentNo}</div>
			</div>
				<div class="input_box">
				<form action="shipments!updateShipment.action" name="shipmentForm" method="post">
				<div class="content_box"  style="display:block;" id="search_box1">
					
					<input type="hidden" name="shipmentId" value="${sDto.shipmentId }" />
						<table border="0" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<th width="160">
									Shipment No
								</th>
								<td>
									<input name="shipmentNo" type="text" class="NFText"
										value="${sDto.shipmentNo }" size="25" readonly="readonly"/>
								</td>
								<th width="150">
									Status
								</th>
								<td>
									<input name="status" type="text" class="NFText"
										value="${sDto.status }" size="25" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<th rowspan="2" valign="top">
									Ship to
								</th>
								<td rowspan="2">
									<textarea name="shipTo" class="content_textarea2" readonly="readonly" >${sDto.shipTo } </textarea>
								</td>
								<th>
									&nbsp;
								</th>
								<td>
									&nbsp;
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
									Priority
								</th>
								<td>
								<s:select name="priority" list="allShipPriority"
			listKey="value" listValue="value" value="sDto.priority" />
									
								</td>
								
								<th>Order Reference</th>
								                  <td><input name="orderReference" type="text" class="NFText" value="${sDto.orderNo }" size="25" readonly="readonly" />
								                    <a href="javascript:void(0);" onclick="openSearchDlgss('${sDto.orderNo }');"><img
								src="images/search.gif" width="16" height="16" border="0px" />
								</a>
								</td>
								
								
							
							</tr>
							<tr>
								<th valign="top">
									Shipping Amount
								</th>
								<td>
									<input name="customerCharge" type="text" class="NFText"
										value="${sDto.shipAmt }" size="25" readonly="readonly"/>
								</td>
								<th>
									Currency
								</th>
								<td>
									<s:select name="currency" list="allCurrency"
			listKey="name" listValue="name" value="sDto.currency"/>
									

								</td>
							</tr>
							<tr>
								<th valign="top">
									Shipping Type
								</th>
								<td>
								<s:select name="shippingType" list="allShipType"
			listKey="value" listValue="value" value="sDto.shippingType" />
									
									
								</td>
								<th>
									Shipping Rule
								</th>
								<td>
								<!--<s:select name="shippingRule" list="allShippingRule"
			listKey="value" listValue="value" value="sDto.shippingRule" />
									
								-->
								<input name="shippingRule" value="${sDto.shippingRule}" readonly="readonly" size="25"/>
								</td>
							</tr>
							<tr>
								<th valign="top">
									Description
								</th>
								<td>
									<textarea name="description" id="description" onblur="checkLen();" class="content_textarea2">${sDto.description }</textarea>
								</td>
								<th>
									&nbsp;
								</th>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<th>
									Shipping Clerk
								</th>
								<td>
									<input name="shippingClerk" type="text" class="NFText"
										value="${sDto.shippingClerk }" size="25" readonly="readonly"/>
								</td>
								<th>
									Created Date
								</th>
								<td>
									<input name="createionDate" type="text" class="NFText"
										value="<s:date name='sDto.creationDate' format='yyyy-MM-dd' />" size="25" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<th>
									Modified By
								</th>
								<td>
									<input name="modifiedBy" type="text" class="NFText"
										value="${sDto.modifyName }" size="25" readonly="readonly"/>
								</td>
								<th>
									Modified Date
								</th>
								<td>
								<input type="hidden" name="trackingNo" value="${sDto.trackingNo }"/>
								<input type="hidden" name="shipDate" value="${sDto.shipDate }"/>
								<input type="hidden" name="warehouseId" value="${sDto.wareHouse.warehouseId }"/>
								<input type="hidden" name="companyId" value="${sDto.companyId }"/>
									<input name="modifyDate" type="text" class="NFText"
										value="<s:date name='sDto.modifyDate' format='yyyy-MM-dd' />" size="25" readonly="readonly"/>
								</td>
							</tr>
							  <tr>
                    <th>Shipment Note:</th>
                    <td><div class="list_box" style="height:100px;background-color:#FFF;width:405px;">
                      <table width="300" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          
                          <td>
                          <div id="orderNoteShipment">
                          	 <div id="orderNoteDiv">
					             <s:iterator value="notesMap['orderNotes']" id="note">
					                 <a href="javascript:void(0)" onclick="show_edit('${note.noteIds}', 'SHIPMENT','${note.orderNo }')"><font color="red" size="2">${note.orderNote}</font>&nbsp;(${note.orderNo })</a>;&nbsp;&nbsp;&nbsp;
					             </s:iterator>
				             </div>
				             
				             <s:select id="sessOrderNo" list="orderLists"/> <input type="button" id="instructionDialogBtn" value="Add" class="style_botton" onclick="show_edit('','SHIPMENT','')"/>
				          </div>
             			 </td>
                        </tr>
                       
                      </table>
                    </div></td>
                    <th>&nbsp;</th>
                    <td>&nbsp;</td>
                  </tr>

						</table>
			</div>
			<div id="dhtmlgoodies_tabView1">
				<div class="dhtmlgoodies_aTab">
					<table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table">
						<tr>
							<th width="50">
								Line No
							</th>
							<th width="50">
								Status
							</th>
							<th width="100">
								Order No
							</th>
							<th width="100">
								Item No
							</th>
							<th width="261">
								Proudct/Service
							</th>
							<th width="84">
								Order Qty
							</th>
							<th width="84">
								Ship Qty
							</th>
							<th width="104">
								Order Size
							</th>
							<th>
								Ship Size
							</th>
						</tr>
					</table>
					<div class="frame_box" style="height: 240px;">

						<table width="955" border="0" cellpadding="0" cellspacing="0"
								class="list_table">
								<c:set var="rowcount" value="1"></c:set>
								<c:forEach var="pageslDTO" items="${list}">
									<c:if test="${rowcount mod 2 == 0}">
										<c:set var="tdclass" value=" class='list_td2'"></c:set>
									</c:if>
									<c:if test="${rowcount mod 2 == 1}">
										<c:set var="tdclass" value="" ></c:set>
									</c:if>
									<tr>
										<td width="50" align="center" ${tdclass}>
											<a
												href="shipments!getSlinedetail.action?lineId=${pageslDTO.lineId }&productService=${pageslDTO.itemName }&shippeQuantity=${pageslDTO.quantity }&orderQuantity=${pageslDTO.orderQty }&orderSize=${pageslDTO.orderSize }&shipSize=${pageslDTO.size }"
												target="mainFrame">${pageslDTO.lineId }</a>
										</td>
										<td width="50" align="center" ${tdclass}>
											${pageslDTO.status }
										</td>
										<td width="100" align="center" ${tdclass}>
											${pageslDTO.order.orderNo }
										</td>
										<td width="100" align="center" ${tdclass}>
											${pageslDTO.itemNo }
										</td>
										<td width="261" align="center" ${tdclass}>
											${pageslDTO.itemName }
										</td>
										<td width="84" align="center" ${tdclass}>
											${pageslDTO.orderQty }&nbsp;${pageslDTO.qtyUom }
										</td>
										<td width="84" align="center" ${tdclass}>
											${pageslDTO.quantity }&nbsp;${pageslDTO.qtyUom }
										</td>
										<td width="104" align="center" ${tdclass}>
											${fn:endsWith(pageslDTO.orderSize,'.0')||fn:endsWith(pageslDTO.orderSize,'.00')||fn:endsWith(pageslDTO.orderSize,'.000')?fn:substring(pageslDTO.orderSize,0,fn:indexOf(pageslDTO.orderSize,".")):pageslDTO.orderSize}
											&nbsp;${pageslDTO.sizeUom }
										</td>
										<td align="center" ${tdclass}>
										    ${fn:endsWith(pageslDTO.size,'.0')||fn:endsWith(pageslDTO.size,'.00')||fn:endsWith(pageslDTO.size,'.000')?fn:substring(pageslDTO.size,0,fn:indexOf(pageslDTO.size,".")):pageslDTO.size}
											&nbsp;${pageslDTO.sizeUom }
										</td>
									</tr>
									<c:set var="rowcount" value="${rowcount+1}"></c:set>
								</c:forEach>
							</table>
					</div>
					<div class="grayr">
						<jsp:include page="/common/db_pager.jsp">
							<jsp:param value="${ctx}/shipments!getShipInfo.action"
								name="moduleURL" />
						</jsp:include>
					</div>
				</div>
	<div class="dhtmlgoodies_aTab">
      <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table">
          <tr>
          <th width="40">Pkg No</th>
	  	  <th width="100">Tracking No</th>
          <th width="80">Description</th>
          <th width="70">Status</th>
          <th width="70">Ship Via</th>
          <th width="100">Ship To</th>
          <th width="80">Ship Date</th>
          <th width="90">Shipping Clerk</th>
          <th width="70">Deliver By</th>
          
          <th width="110">Warehouse</th>
          <th>Packaging Error</th>
        </tr>	
      </table>
	  <div class="frame_box" style="height:240px; ">
	 
      <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table">	
      <c:set var="rowcount1" value="1"></c:set>
      <c:forEach var="packagesDTO" items="${list1}">
     	 <c:if test="${rowcount1 mod 2 == 0}">
		 	<c:set var="tdclass" value=" class='list_td2'"></c:set>
		 </c:if>	
		 <c:if test="${rowcount1 mod 2 == 1}">
		    <c:set var="tdclass" value=""></c:set>
		 </c:if>              
        <tr>
          <td width="40" align="center" ${tdclass} color="#FFF">
         <a href="shipments!getPkgInfo.action?packageId=${packagesDTO.packageId }" target="mainFrame" >${packagesDTO.packageId }</a>
         </td>
          <td width="100" align="center" ${tdclass}>
          	<c:if test="${packagesDTO.carrier == 'Fedex'}"><a href="http://www.fedex.com/Tracking?cntry_code=us&language=english&tracknumbers=${packagesDTO.trackingNo }" target="_blank" >${packagesDTO.trackingNo }</a></c:if>
			<c:if test="${packagesDTO.carrier == 'UPS'}"><a href="http://wwwapps.ups.com/tracking/tracking.cgi?tracknum=${packagesDTO.trackingNo }" target="_blank" >${packagesDTO.trackingNo }</a></c:if>         	
          	<c:if test="${packagesDTO.carrier == 'DHL'}"><a href="http://track.dhl-usa.com/TrackByNbr.asp?ShipmentNumber=${packagesDTO.trackingNo }" target="_blank" >${packagesDTO.trackingNo }</a></c:if>         	
          	<c:if test="${packagesDTO.carrier == 'USPS'}"><a href="https://www.usps.com/" target="_blank" >${packagesDTO.trackingNo }</a></c:if>
          	<c:if test="${packagesDTO.carrier != 'Fedex'&&packagesDTO.carrier != 'UPS'&&packagesDTO.carrier != 'DHL'&&packagesDTO.carrier != 'USPS'}"><span style="color: red;">${packagesDTO.trackingNo }</span></c:if>         	
          </td>
          
          <td width="80" align="center" ${tdclass}>
          <c:if test="${fn:length(packagesDTO.note)>10}">
															${fn:substring(packagesDTO.note,0,10)}...
														</c:if>
														<c:if test="${fn:length(packagesDTO.note)<=10}">
															${packagesDTO.note}
														</c:if>
         </td>
          <td width="70" align="center" ${tdclass}>${packagesDTO.status }</td>
          <td width="70" align="center" ${tdclass}>${packagesDTO.shipVia }</td>
          <td width="100" align="center" ${tdclass} >
          <c:if test="${fn:length(packagesDTO.shiptoAddress)>20}">
															${fn:substring(packagesDTO.shiptoAddress,0,20)}...
														</c:if>
														<c:if test="${fn:length(packagesDTO.shiptoAddress)<=20}">
															${packagesDTO.shiptoAddress}
														</c:if>
         </td>
          <td width="80" align="center"${tdclass}>
          ${packagesDTO.shipmentDateStr }
          </td>
          <td width="90" align="center" ${tdclass}>${packagesDTO.shippingClerk }</td>
          <td width="70" align="center" ${tdclass}>${packagesDTO.sendBy }</td>
          <td width="110" align="center" ${tdclass}>${packagesDTO.wareHouse.name }GenScript US Warehouse</td>
          <td align="center" ${tdclass}>${error }</td>
        </tr>
        <c:set var="rowcount1" value="${rowcount1+1}"></c:set>
        </c:forEach>
      </table>
    </div><!--
    <div class="grayr">
				<jsp:include page="/common/db_pager.jsp">
					<jsp:param value="${ctx}/shipments!getShipInfo.action" name="moduleURL" />
				</jsp:include>
			</div>
    --></div>
    
    
				</div>

			<script type="text/javascript">
				var show_tab = '${show_tab}';
				if (show_tab != undefined && show_tab != "" && show_tab != -1) {
					initTabs('dhtmlgoodies_tabView1',Array('Shipment Lines','Packages'),1,998,320);
				} else {
					initTabs('dhtmlgoodies_tabView1',Array('Shipment Lines','Packages'),0,998,320);
				}
			</script>
			<div class="button_box">
				<input type="submit" value="Save" class="search_input" />
				<input type="button" value="Cancel" class="search_input" onclick="javascript:history.go(-1);" />
			</div>
			<div id="vendor_search_dlg3" title=" Order Reference "
				style="visible: hidden;">
	</div>
	</form>
	</div>
	</div>
	<div id="instruction_dlg" title="Instruction/Note"> </div> 
	<div id="instruction_update_dlg" title="Instruction/Note"> </div>
	</body>
</html>