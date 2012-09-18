<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>picks_items</title>
<link href="stylesheet/scm.css" rel="stylesheet" type="text/css" />
<link href="stylesheet/table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css"
	rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/ui/ui.datepicker.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.datePicker.js"></script>

<style type="text/css">
<!--
fieldset fieldset {
	margin: 2px;
}
-->
</style>
<script language="javascript" type="text/javascript">
	function printPickList(){
		var packageIds = $("#packageIds").val();
		var oTR = $("#pickListTable").find("tr");
		var lotNos = "";
		for(var i=0;i<oTR.length;i++){
			var id = $(oTR[i]).attr("id");
			var nos = "";
			var lineId = $("#lineId"+id).val();
			var lotNo = $("#lotNo"+id).val();
			
			var locationCode = $("#locationCode"+id).val();
			if(locationCode==null||locationCode==""){
				alert("The Storage Location is required.");
				return ;
			}
			if(locationCode!="Work Station"&&(lotNo==""||lotNo ==null)){
				alert("The Lot No is required.");
				return ;
			}
			if(locationCode=="Work Station"){
				locationCode="WorkStation";
			}
			if(lotNo==""||lotNo ==null){
				lotNo = "null";
			}
			nos = lineId+","+lotNo+","+locationCode;
			if(lotNos == ""){
				lotNos = nos;
			}else{
				lotNos+=";"+nos;
			}
		}
		if(lotNos==""){
			return ;
		}	
		var url = "shipping!printPick.action?packageIds="+packageIds+"&shipmentId=${shipmentId}&lotNos="+lotNos;
		//alert(packageIds);
		window.location.href=url;
	}

	function confrimPickList(){
		var packageIds = $("#packageIds").val();
		var oTR = $("#pickListTable").find("tr");
		var lotNos = "";
		if(oTR.length<1){
			return ;
		}
		for(var i=0;i<oTR.length;i++){
			var id = $(oTR[i]).attr("id");
			var nos = "";
			var lineId = $("#lineId"+id).val();
			var lotNo = $("#lotNo"+id).val();

			var locationCode = $("#locationCode"+id).val();
			if(locationCode!="Work Station"&&(lotNo==""||lotNo ==null)){
				alert("The Lot No is required.");
				return ;
			}
			if(lotNo==""||lotNo ==null){
				lotNo = " ";
			}
			nos = lineId+","+lotNo+","+locationCode;
			if(lotNos == ""){
				lotNos = nos;
			}else{
				lotNos+=";"+nos;
			}
		}
		if(lotNos==""){
			return ;
		}
		//alert("shipping!confirmPrintPick.action?packageIds="+packageIds+"&shipmentId=${shipmentId}&lotNos="+lotNos);
		$.ajax ({
			type:"POST",
			url:"shipping!confirmPrintPick.action?packageIds="+packageIds+"&shipmentId=${shipmentId}&lotNos="+lotNos,
			dataType:"json",
			success: function(msg){
					parent.window.location.href = "shipping!packingDetail.action?shipmentId=${shipmentId}";
			},
			error: function(msg){
				alert("Failed to selected the prefix.Please contact system administrator for help.");
			}
		});	
	}	

	function onchangstorageLocationSelect(stCodeId,lineId){
		var j = $("#"+stCodeId+" option:selected").val();
		var lotNo = $("#lotNoSelect"+j+lineId +" option:selected").text();	
		$("#lotNoDIV"+lineId).empty();
		$("#lotNoDIV"+lineId).append($("#lotNoDIV"+j+lineId).html());
		var inventoryInput = $("#lotNoSelect"+j+lineId +" option:selected").val();
		$("#inventoryDIV"+lineId).empty();
		$("#inventoryDIV"+lineId).append(inventoryInput);		
		$("#lotNo"+lineId).val(lotNo);
		$("#locationCode"+lineId).val($("#"+stCodeId+" option:selected").text());
	}

	function onchanglotNoSelect(lotNoCodeId,lineId){
		var inventoryInput = $("#"+lotNoCodeId +" option:selected").val();
		$("#inventoryDIV"+lineId).empty();
		$("#inventoryDIV"+lineId).append(inventoryInput);
		
		var i  = 0;
		$("#"+lotNoCodeId +" option:selected").each(function(){
			if(i==0){
				$("#lotNo"+lineId).val($(this).text());
				i++;
			}
		});				
	}

	function getLotNo(){
		var packageIds = $("#packageIds").val();
		$("#sub4").attr("disabled","disabled");
		$.ajax ({
			type:"POST",
			url:"shipping!searchLotNo.action?packageIds="+packageIds+"&shipmentId=${shipmentId}",
			dataType:"json",
			success: function(msg){
				//alert(msg);
				for(var i=0;i<msg.list.length;i++){
					var line = msg.list[i];
					if(line.storageLocationList!=null){
						var lotNoValue = line.lotNoValue;
						var inventory = line.inventory;
						var storageLocationHtml = "<select id='storageLocationSelect"+line.lineId+"' onchange=\"onchangstorageLocationSelect('storageLocationSelect"+line.lineId+"','"+line.lineId+"');\" style='width: 100px'>";
						for(var j = 0;j<line.storageLocationList.length;j++){
							var storageLocation = line.storageLocationList[j];
							var stlselected = "";
							if(lotNoValue == storageLocation.storageLocation){
								stlselected = "selected=selected";
								$("#locationCode"+line.lineId).val(storageLocation.storageLocation);
							}
							storageLocationHtml += "<option "+stlselected+" value='"+j+"'>";
							storageLocationHtml +=  storageLocation.storageLocation;
							storageLocationHtml +=  "</option>";
							
							var lotNoHtml = "<div id='lotNoDIV"+j+line.lineId+"'>";
							lotNoHtml += "<select id='lotNoSelect"+j+line.lineId+"' style='width: 85px' onchange=\"onchanglotNoSelect('lotNoSelect"+j+line.lineId+"','"+line.lineId+"');\">";
							var lotNoSelected = "";
							var lotNoTestValue="";
							var lotNoTestinputValue="";
							for(var k=0;k<storageLocation.lotNoList.length;k++){
								var lotNo = storageLocation.lotNoList[k];
								var lotSelect="";
								if(stlselected == "selected=selected"&&lotNo.value == inventory){
									lotNoSelected = "selected=selected";
									lotSelect = "selected=selected";
									lotNoTestValue = lotNo.value;
									lotNoTestinputValue = lotNo.lotNo;
								}
								lotNoHtml +="<option "+lotSelect+" value='"+lotNo.value+"'>"
								lotNoHtml +=lotNo.lotNo;
								lotNoHtml += "</option>";
							}
							lotNoHtml += "</select>";
							lotNoHtml += "</div>";
							$("#lotNoSelectDIV").append(lotNoHtml);
							if(stlselected == "selected=selected"&&lotNoSelected=="selected=selected"){
								$("#lotNo"+line.lineId).attr("style","display:none");
								$("#lotNo"+line.lineId).val(lotNoTestinputValue);
								$("#lotNoDIV"+line.lineId).empty();
								$("#lotNoDIV"+line.lineId).append(lotNoHtml);
								var inventoryInput =lotNoTestValue;
								$("#inventoryDIV"+line.lineId).empty();
								$("#inventoryDIV"+line.lineId).append(inventoryInput);
							}
						}
						storageLocationHtml += "</select>";
						$("#locationCode"+line.lineId).attr("style","display:none");
						$("#storageLocationDIV"+line.lineId).empty();
						$("#storageLocationDIV"+line.lineId).append(storageLocationHtml);
					}
					
				}
				alert("We get the values of Lot No and Storage Location completed.");
				$("#sub4").attr("disabled","");
			},
			error: function(msg){
				
				alert("Please contact system administrator for help.");
				
			}
		});	
	}	
</script>

</head>

<body>
<table width="800" border="0" cellspacing="3" cellpadding="0"
	id="table11" bgcolor="#96BDEA">
	<tr>
		<td bgcolor="#FFFFFF">
		<table width="800" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="384" style="padding-left: 20px;"><br />
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">

							<tr>
								<td width="100%" height="22">Enter the information of the
								items to be picked:
								<input id="sub4" name="sub2" type="submit" class="style_botton6" onclick="getLotNo()"  value="Retrieve Lot No And Location" )"/></td>
							</tr>
						</table>
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td colspan="4" style="padding-top: 10px;">
								<table width="740" border="0" cellpadding="0" cellspacing="0"
									class="list_table">
									<tr>
										<th width="70">Item Name</th>
										<th width="50">Cat No</th>
										<th width="70">Qty To Pick</th>
										<th width="70">Qty Picked</th>
										<th width="70">Size To Pick</th>
										<th width="70">Size Picked</th>
										<th width="110">Storage Location</th>
										<th width="90">Lot No</th>
										<th>Inventory</th>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td colspan="4">
								<div style="width: 757px; height: 210px; overflow: scroll;">
								<table width="740" border="0" cellpadding="0" cellspacing="0"
									class="list_table" id = "pickListTable">
									<c:if test="${ (empty Picklists || Picklists ==null)}">
									 	<script type="text/javascript"> 
									 		alert("There is no item we can operate.");
									 		parent.$('#picks_items_label').dialog('close');
									 	</script>
									 </c:if>
									<input type="hidden" value="${packageIds}" id="packageIds"/>
									 <c:forEach items="${Picklists }" var="l">
									 	 <c:forEach items="${l.printPickListDTOList }" var="sl">
									 	  <tr id="${sl.lineId }">
									 	  	<input type="hidden" value="${sl.lineId}" id="lineId${sl.lineId }"/>
											<td width="70">${sl.name }</td>
											<td width="50">${sl.catNo }</td>
											<td width="70">${sl.qtyToPick }&nbsp;${sl.qtyUom }</td>
											<td width="70">${sl.qtypicked }&nbsp;${sl.qtyUom }</td>
											<td width="70">
											${fn:endsWith(sl.sizeToPick,'.0')||fn:endsWith(sl.sizeToPick,'.00')||fn:endsWith(sl.sizeToPick,'.000')?fn:substring(sl.sizeToPick,0,fn:indexOf(sl.sizeToPick,".")):sl.sizeToPick}
											&nbsp;${sl.sizeUom }</td>
											<td width="70">
											${fn:endsWith(sl.sizePick,'.0')||fn:endsWith(sl.sizePick,'.00')||fn:endsWith(sl.sizePick,'.000')?fn:substring(sl.sizePick,0,fn:indexOf(sl.sizePick,".")):sl.sizePick}
											&nbsp;${sl.sizeUom }</td>
											<td width="110">
											<div id="storageLocationDIV${sl.lineId }"></div>
												<c:if test="${sl.type=='PRODUCT'}"><input id="locationCode${sl.lineId }" value="${sl.storageLocation }" size="12" <c:if test="${sl.status!='Drafted'}">readonly="readonly" </c:if>  /></c:if>
												<c:if test="${sl.type!='PRODUCT'}">
												
													<input id="locationCode${sl.lineId }" value="Work Station" size="12" readonly="readonly" />
														
															
													
												
												
												</c:if>	
											
												<input type="hidden" id="status${sl.lineId }" value="${sl.status }"/>
											</td>
											<td width="90">
												<div id="lotNoDIV${sl.lineId }"></div>	
												<c:if test="${sl.type=='PRODUCT'}"><input id="lotNo${sl.lineId }" value="${sl.lotNo }" size="7" <c:if test="${sl.status!='Drafted'}">readonly="readonly" </c:if>  /></c:if>
												
											</td>
											<td><div id="inventoryDIV${sl.lineId }"></div> </td>
					
											</tr>
									 	 </c:forEach>
									 </c:forEach>
								</table>
								</div>
								</td>
							</tr>

							<tr>
								<td height="40" colspan="4">
								<div align="center"><input id="sub1" name="Submit1"
									type="submit" class="style_botton" value="Confirm" onclick="confrimPickList();"/> <input
									id="sub2" type="submit" name="Submit2" value="Cancel"
									class="style_botton" onclick="parent.$('#picks_items_label').dialog('close');" /> <input
									id="sub3" name="sub" type="submit" class="style_botton2"
									value="Print Pick List"
									onclick="printPickList();" />
								</div>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				<br />
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<div id="lotNoSelectDIV" style="display: none;">
</div>

</body>
</html>