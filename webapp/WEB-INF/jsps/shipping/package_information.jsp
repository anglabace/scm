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
		<style type="text/css">
<!--
fieldset fieldset {
	margin: 2px;
}
-->
</style>
		<script language="javascript" type="text/javascript">
		function shows(){
					var additionalHandle=document.getElementById("sips.additionalHandle");
					var deliveryConfirm=document.getElementById("sips.deliveryConfirm");
					var hazardousMtl =document.getElementById("sips.hazardousMtl");
					var saturdayPickup =document.getElementById("sips.saturdayPickup");
					var noteOnShip =document.getElementById("sips.noteOnShip");
					var noteOnDelivery =document.getElementById("sips.noteOnDelivery");
					var noteOnExcp =document.getElementById("sips.noteOnExcp");
					var ciItemDescFromorder =document.getElementById("ciItemDescFromorder");
					
					var ciInsuranceFromorder = document.getElementById("ciInsuranceFromorder");
					var creationDate = $("#creationDate").val();
					
					var shipmentDate = $("#shipmentDate").val();
					
	              		if(additionalHandle.checked){
	              			additionalHandle='Y';
	              		}else{
	              			additionalHandle='N';
	              		}
	              		if(deliveryConfirm.checked){
	              			deliveryConfirm='Y';
	              		}else{
	              			deliveryConfirm='N';
	              		}
	              		if(hazardousMtl.checked){
	              			hazardousMtl='Y';
	              		}else{
	              			hazardousMtl='N';
	              		}
	              		if(saturdayPickup.checked){
	              			saturdayPickup='Y';
	              		}else{
	              			saturdayPickup='N';
	              		}
	              		if(noteOnShip.checked){
	              			noteOnShip='Y';
	              		}else{
	              			noteOnShip='N';
	              		}
	              		if(noteOnDelivery.checked){
	              			noteOnDelivery='Y';
	              		}else{
	              			noteOnDelivery='N';
	              		}
	              		if(noteOnExcp.checked){
	              			noteOnExcp='Y';
	              		}else{
	              			noteOnExcp='N';
	              		}
	              		if(ciItemDescFromorder.checked){
	              			ciItemDescFromorder='Y';
		              	}else{
		              		ciItemDescFromorder = 'N';
			            }
	              		if(ciInsuranceFromorder.checked){
	              			ciInsuranceFromorder='Y';
		              	}else{
		              		ciInsuranceFromorder = 'N';
			            }
	              		document.forms[0].action = "shipping!packageInformationUpdate.action?ciItemDescFromorder="+ciItemDescFromorder+"&ciInsuranceFromorder="+ciInsuranceFromorder+"&additionalHandle="+additionalHandle+"&deliveryConfirm="+deliveryConfirm+"&hazardousMtl="+hazardousMtl+"&saturdayPickup="+saturdayPickup+"&noteOnShip="+noteOnShip+"&noteOnDelivery="+noteOnDelivery+"&noteOnExcp="+noteOnExcp+"&creationDate="+creationDate+"&shipmentDate="+shipmentDate;
	 					document.forms[0].submit();
 					}

		$(function() {
		    $('.ui-datepicker').each(function(){
					$(this).datepicker(
						{
							dateFormat: 'yy-mm-dd',
							changeMonth: true,
							changeYear: true,
							beforeShow: function () {
				                setTimeout(
				                    function () {
				                    	$('#ui-datepicker-div').css("left", '200px');
				                    }, 100
				                );
				            }
						});
				}); 
				$('#fromDate').datepicker();

				 $("#description").change(function(){
						
					   if ($("#description").val()=='Other')
					   {
						   document.getElementById('txt_new_1').style.display="block";
						}
						else
						{
						   	document.getElementById('txt_new_1').style.display="none";
						}
				   })
								
				
});
		function onclickCiInsuranceFrmorder(){
			
			if($("#ciInsuranceFromorder").attr('checked')==false){
				
				$("#insuranceValue").attr('readonly',false);
				$("#insuranceValue").val("10.00");
			}else{
				
				$("#insuranceValue").attr('readonly',true);
				$("#insuranceValue").val($("#itemAmt").val());
			}
		}

		function openc(str1,str2)
		{
		 
			  if (document.getElementById(str1).style.display=="none")
			  {
			    document.getElementById(str2).src="images/ad.gif";
			    document.getElementById(str1).style.display="block";
			  }
			  else
			  {
				  document.getElementById(str2).src="images/ar.gif";
			    document.getElementById(str1).style.display="none";
			  }
		  
		}
		 
		$(function(){
				   $("#select_cust").change(function(){
													 if($("#select_cust").val()=='Your package')
													 {
														$("#cust_a").show(); 
													 }
													 else
													 {
														 $("#cust_a").hide(); 
													  }
													 })
				   
				   $("#save1").click(function(){
											  $("#table_a").toggle();
											  })
				   })
				   
			$(function() {            
            	$('#edit_address').dialog({
					autoOpen: false,
					height: 500,
					width: 800,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            });
            
			function openEditAddress(packageId,count) {
				$('#edit_address').dialog("option", "open", function() {	
				var htmlStr = '<iframe src="shipping!editAddress.action" height="450" width="750" scrolling="no" style="border:0px" frameborder="0"></iframe>';
				$('#edit_address').html(htmlStr);
				});
				$('#edit_address').dialog('open');
			}
				
</script>

	</head>

	<body>
		<form action="shipping!packageInformationUpdate.action" method="post">

			<input type="hidden" value="${sps.createdBy }" name="sips.createdBy" />
			<input type="hidden" value="${sps.shipinfoSentFlag }" name="sips.shipinfoSentFlag" />
			<input type="hidden" value="${sps.packageNo }" name="sips.packageNo" />
			<input type="hidden" value="${sps.invoicedFlag }"
				name="sips.invoicedFlag" />
			<input type="hidden" value="${sps.creationDate }"
				id="creationDate" />
			<input type="hidden" value="${sps.dimUom }" name="sips.dimUom" />
			<input type="hidden" value="${sps.shippingClerk }"
				name="sips.shippingClerk" />

			<c:if test="${sps.shipments.shipmentId!=null }">
				<input type="hidden" value="${sps.shipments.shipmentId }"
					name="sips.shipments.shipmentId" />
			</c:if>
			<c:if test="${sps.companyId!=null }">
				<input type="hidden" value="${sps.companyId }" name="sips.companyId" />
			</c:if>
			<c:if test="${sps.packageId!=null }">
				<input type="hidden" value="${sps.packageId }" name="sips.packageId" />
			</c:if>
			<c:if test="${sps.warehouseId!=null }">
				<input type="hidden" value="${sps.warehouseId }"
					name="sips.warehouseId" />
			</c:if>
			<table width="900" border="0" cellpadding="0" cellspacing="0" class="General_table">
  <tr>
  
    <th width="150" rowspan="3">Item  Description:</th>
    <td width="244" valign="top"><table><tr><td>
   
   	<input type="hidden" id="rcpMidName" name="sips.rcpMidName" value="${sps.rcpMidName  }"/>
   	<input type="hidden" id="rcpLastName" name="sips.rcpLastName" value="${sps.rcpLastName  }"/>
   	<input type="hidden" id="rcpOrgName" name="sips.rcpOrgName" value="${sps.rcpOrgName  }"/>
   	<input type="hidden" id="rcpCity" name="sips.rcpCity" value="${sps.rcpCity  }"/>
   	<input type="hidden" id="rcpState" name="sips.rcpState" value="${sps.rcpState  }"/>
   	<input type="hidden" id="rcpCountry" name="sips.rcpCountry" value="${sps. rcpCountry }"/>
   	<input type="hidden" id="rcpPhone" name="sips.rcpPhone" value="${sps.rcpPhone  }"/>
   	<input type="hidden" id="rcpMobile" name="sips.rcpMobile" value="${sps.rcpMobile  }"/>
   	<input type="hidden" id="rcpZipCode" name="sips.rcpZipCode" value="${sps.rcpZipCode  }"/>
   	<input type="hidden" id="rcpFirstName" name="sips.rcpFirstName" value="${sps. rcpFirstName }"/>
   	<input type="hidden" id="rcpBusEmail" name="sips.rcpBusEmail" value="${sps. rcpBusEmail }"/>
   	<input type="hidden" id="rcpFax" name="sips.rcpFax" value="${sps. rcpFax }"/>
   	<input type="hidden" id="rcpTitle" name="sips.rcpTitle" value="${sps. rcpTitle }"/>
   	<input type="hidden" id="rcpAddrClass" name="sips.rcpAddrClass" value="${sps. rcpAddrClass }"/>
   	<input type="hidden" id="rcpAddrLine1" name="sips.rcpAddrLine1" value="${sps. rcpAddrLine1 }"/>
   	<input type="hidden" id="rcpAddrLine2" name="sips.rcpAddrLine2" value="${sps. rcpAddrLine2 }"/>
   	<input type="hidden" id="rcpAddrLine3" name="sips.rcpAddrLine3" value="${sps. rcpAddrLine3 }"/>
    <select id="description" name="sips.ciItemDesc" style="width:400px;">
    <c:if test='${fn:indexOf(fn:toUpperCase(sps.rcpBusEmail),"VWR.COM")!=-1}'>
    	<option title="Same as Item real name." value="Same as Item real name." ${sps.ciItemDesc=='Same as Item real name.'? 'selected':''}>Same as Item real name.</option>
    </c:if>	
    <c:if test='${fn:indexOf(fn:toUpperCase(sps.rcpBusEmail),"VWR.COM")==-1}'>	
    	<c:if test='${sps.rcpCountry=="AU"}'>	
    		<option title='"laboratory reagent"  HTS: 3822005090,for research use only, not for resale.' value='"laboratory reagent"  HTS: 3822005090,for research use only, not for resale.'  ${sps.ciItemDesc=='"laboratory reagent"  HTS: 3822005090,for research use only, not for resale.' ? 'selected':''}>"laboratory reagent"  HTS: 3822005090,for research use only, not for resale.</option>
			<option title='"Purified plasmid DNA sample" , HTS code is 2934991800,for research use only, not for resale.' value='"Purified plasmid DNA sample" , HTS code is 2934991800,for research use only, not for resale.'  ${sps.ciItemDesc=='"Purified plasmid DNA sample" , HTS code is 2934991800,for research use only, not for resale.' ? 'selected':''}>"Purified plasmid DNA sample" , HTS code is 2934991800,for research use only, not for resale.</option>
			<option title='"Fully synthetic  Purified Peptide sample", HTS code is 2924198000,for research use only, not for resale.' value='"Fully synthetic  Purified Peptide sample", HTS code is 2924198000,for research use only, not for resale.'  ${sps.ciItemDesc=='"Fully synthetic  Purified Peptide sample", HTS code is 2924198000,for research use only, not for resale.' ? 'selected':''}>"Fully synthetic  Purified Peptide sample", HTS code is 2924198000,for research use only, not for resale.</option>
			<option title='"protein solution" , HTS code is 3504001000,for research use only, not for resale.' value='"protein solution" , HTS code is 3504001000,for research use only, not for resale.'  ${sps.ciItemDesc=='"protein solution" , HTS code is 3504001000,for research use only, not for resale.' ? 'selected':''}>"protein solution" , HTS code is 3504001000,for research use only, not for resale.</option>				
    	
    		<option title='"Purified antibody sample" , HTS code is 3002100190,for research use only, not for resale.' value='"Purified antibody sample" , HTS code is 3002100190,for research use only, not for resale.' ${sps.ciItemDesc=='"Purified antibody sample" , HTS code is 3002100190,for research use only, not for resale.' ? 'selected':''}>"Purified antibody sample" , HTS code is 3002100190,for research use only, not for resale.</option>
    	</c:if>
    	<c:if test='${sps.rcpCountry!="AU"}'>
    		<option title="Synthesized salt samples in plastic tube, HTS code is 3822005090,for research use only, not for resale." value="Synthesized salt samples in plastic tube, HTS code is 3822005090,for research use only, not for resale."  ${sps.ciItemDesc=='Synthesized salt samples in plastic tube, HTS code is 3822005090,for research use only, not for resale.' ? 'selected':''}>Synthesized salt samples in plastic tube, HTS code is 3822005090,for research use only, not for resale.</option>
    		
    	</c:if>
    </c:if>
    	
    	<option title="" value="Other"  ${sps.ciItemDesc=='Other' ? 'selected':''}>Other</option>
    		
	  </select>		
	  </td>
	  </tr>
	  <tr>
  		<td colspan="3" valign="top" <c:if test="${sps.ciItemDesc!='Other'}">style="display:none;"</c:if> id="txt_new_1"><input type="text"  name ="sips.ciItemOtherDesc" class="NFText" size="30" value="${sps.ciItemOtherDesc }"/></td>
		</tr>
	  
	  </table>
    </td>
    <th width="230" rowspan="3">Ship to </th>
    <td width="267" rowspan="3"><textarea id="shiptoAddress" name="sips.shiptoAddress" class="content_textarea2" style="width:250PX;height:70px;cursor:pointer;" readonly="readonly" onclick="openEditAddress();">${sps.shiptoAddress }</textarea></td>
  </tr>
  
  <tr>
    <td valign="top">
    
    <input type="checkbox" id="ciItemDescFromorder"  name="ciItemDescFromorder"
												value="${sps.ciItemDescFromorder}"  ${sps.ciItemDescFromorder=='Y' ? 'checked':'' }/>
   
      Same As Item Name</td>
  </tr>
  <tr>
    <td valign="top">&nbsp;</td>
  </tr>
 <tr>
					<th>
						Delivery Type
					</th>
					<td>
						<input name="sips.deliveryType" type="text" class="NFText"
							value="${sps.deliveryType }" size="20" maxlength="20" readonly="readonly"/>
					</td>
					<th>
						Status
					</th>
					<td>
						<input name="sips.status" type="text" class="NFText"
							value="${sps.status }" size="20" maxlength="20" readonly="readonly"/>
					</td>
				</tr>
  <tr>
					<th>
						Shipment Date
					</th>
					<td>
					    <input id="shipmentDate"  type="text" class="ui-datepicker" value="<fmt:formatDate value="${sps.shipmentDate}" pattern="yyyy-MM-dd"/>" style="width: 120px;"  readonly="readonly"/>
						<span style="color:red">*Date must be within ten days.</span>
					</td>
					<th>
						Tracking Number
					</th>
					<td>
						<input name="sips.trackingNo" type="text" class="NFText"
							value="${sps.trackingNo }" size="20" maxlength="20" readonly="readonly"/>
					</td>
				</tr>
<tr>
					<th>
						Actual Weight
					</th>
					<td>
						<input name="sips.actualWeight" type="text" class="NFText"
							value="${sps.actualWeight }" size="20" maxlength="10"
							onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
							onfocus="this.select()"/>
					</td>
					<th>
						Billable Weight
					</th>
					<td>
						<input name="sips.billableWeight" type="text" class="NFText"
							value="${sps.billableWeight }" size="20" maxlength="10"
							onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
							onfocus="this.select()" readonly="readonly" readonly="readonly"/>
						(lb)
					</td>
				</tr>
				<tr>
					<th>
						Zone
					</th>
					<td>
						<input name="sips.zone" type="text" class="NFText"
							value="${sps.zone }" size="20" maxlength="10" readonly="readonly"/>
					</td>
					<th>
						Package
					</th>
					<td>
						<input name="sips.pkgBatchSeq" type="text" class="NFText"
							value="${count }" size="4" onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"/>
						&nbsp;&nbsp;Of
						<input name="sips.pkgBatchCount" type="text" class="NFText"
							value="${counts}" size="4" onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"/>
					</td>
				</tr>
				<tr>
					<th>
						Carrier Charges
					</th>
					<td>
						<input name="sips.carrierCharge" type="text" class="NFText"
							value="${sps.carrierCharge }" size="20" maxlength="10"
							onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
							onfocus="this.select()" readonly="readonly"/>
					</td>
					<th>
						Handling Fee
					</th>
					<td>
						<input name="sips.handingFee" type="text" class="NFText"
							value="${sps.handingFee }" size="20" maxlength="10"
							onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
							onfocus="this.select()" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<th>
						Insurance Value
					</th>

					<td>
						<s:if test="sps.ciInsuranceFromorder==\"Y\"">
							<input name="sips.insuranceValue" id ="insuranceValue" type="text" class="NFText"
							value="${sps.itemAmt }" size="20" maxlength="10"
							onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
							onfocus="this.select()" readonly="readonly"/>
						</s:if>
						<s:else>
							<input name="sips.insuranceValue" id ="insuranceValue" type="text" class="NFText"
							value="${sps.insuranceValue }" size="20" maxlength="10"
							onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
							onfocus="this.select()" />
						</s:else>
						<input type="hidden" id="itemAmt" value="${sps.itemAmt}"/>
					</td>
					<th>
						Customer Charges 
					</th>
					<td>
						<input name="sips.customerCharge" type="text" class="NFText"
							value="${sps.customerCharge }" size="20" maxlength="10"
							onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
							onfocus="this.select()" readonly="readonly"/>
					</td>
				</tr>
  <tr>
    <th>&nbsp;</th>
    <td>
    <input type="checkbox" id="ciInsuranceFromorder"  name="ciInsuranceFromorder"
												value="${sps.ciInsuranceFromorder}"  ${sps.ciInsuranceFromorder=='Y' ? 'checked':'' } onclick="onclickCiInsuranceFrmorder();"/>
    Same As Item Value</td>
					<th>
						Packer
					</th>
					<td>
						<input name="sips.packer" type="text" class="NFText" size="20"
							value="${sps.packer }" maxlength="50" readonly="readonly"/>
					</td>
  </tr>

<tr>
					<th>
						Shipment Method
					</th>
					<td >
						<s:select name="sips.shipMethod"
														list="shipMenthodList" listValue="name" listKey="methodId"
														value="sps.shipMethod" cssStyle="height:20px;width:200px;"/>
						<!--<select name="sips.shipMethod" style="width: 250px">
							<option value="${sps.shipMethod }">
								${sd.name_ }
							</option>
						</select>
					--></td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
  <th >Package type</th>
  <td colspan="2" style="padding:0px;"><table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td ><select name="sips.packageType" id="select_cust">
        <option value="Envlop" <c:if test="${sps.packageType=='Envelop'}">selected="selected"</c:if>>Envelop</option>
        <option value="Box" <c:if test="${sps.packageType=='Box'}">selected="selected"</c:if>>Box</option>
        <option value="Package" <c:if test="${sps.packageType=='Package'}">selected="selected"</c:if> >Package</option>
        <option value="Your package" <c:if test="${sps.packageType=='Your package'}">selected="selected"</c:if>>Your package</option>
      </select></td>
      <td style="padding:0px;"><table border="0" cellspacing="0" cellpadding="0" id="cust_a" <c:if test="${sps.packageType!='Your package'}">style="display:none;"</c:if>  width="200">
        <tr>
          <th width="60"></th>
          <td  ><input name="sips.containerWeight" type="hidden"" class="NFText" value="${sps.containerWeight}" onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');" onfocus="this.select()"  style="width:50PX;" /></td>
          <th width="60">Dimensions</th>
          <td><input name="sips.length" type="text"  style="width:20PX;" onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');" onfocus="this.select()" value="${sps.length }"/></td><Td>L</td>
          <td><input name="sips.width" type="text"   style="width:20PX;" onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');" onfocus="this.select()" value="${sps.width }"/></td><Td>W</td>
          <td><input name="sips.height" type="text"  style="width:20PX;" onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');" onfocus="this.select()" value="${sps.height }"/></td><Td>H</td>
        </tr>
      </table></td>
    </tr>
  </table></td>
</tr>
				
				<tr>
					<td colspan="4">
						<fieldset><!--
							<legend>
								${sd.carrier }
							</legend>

							--><fieldset>
								<legend>
									Package Options
								</legend>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
											<input type="checkbox" id="sips.additionalHandle"  name="sips.additionalHandle"
												value="${sps.additionalHandle}"  ${sps.additionalHandle=='Y' ? 'checked':'' } disabled="disabled"/>
											Additional Handling
										</td>
										<td>
											<input type="checkbox" id="sips.deliveryConfirm" name="sips.deliveryConfirm"
												value="${sps.deliveryConfirm }"  ${sps.deliveryConfirm=='Y' ? 'checked':'' } disabled="disabled"/>
											Delivery Confirmation
										</td>

										<td>
											<input type="checkbox" id="sips.hazardousMtl" name="sips.hazardousMtl"
												value="${sps.hazardousMtl }"  ${sps.hazardousMtl=='Y' ? 'checked':'' } disabled="disabled"/>
											Hazardous Material
										</td>
										<td>
											<input type="checkbox" id="sips.saturdayPickup" name="sips.saturdayPickup"
												value="${sps.saturdayPickup }"  ${sps.saturdayPickup=='Y' ? 'checked':'' } disabled="disabled"/>
											Staturday Pickup
										</td>
									</tr>
									<tr>
										<td>

										</td>

										<td>
											&nbsp;
										</td>
										<td>
											&nbsp;
										</td>
										<td>
											&nbsp;
										</td>
									</tr>
									<tr>
										<td>Send Email Notification at:
											<input type="checkbox" name="sips.noteOnShip"
												value="${sps.noteOnShip }" id="sips.noteOnShip" ${sps.noteOnShip=='Y' ? 'checked':'' } disabled="disabled"/>
											 Ship
										</td>
										<td>
											<input type="checkbox" id="sips.noteOnDelivery" name="sips.noteOnDelivery"
												value="${sps.noteOnDelivery}"  ${sps.noteOnDelivery=='Y' ? 'checked':'' } disabled="disabled"/>
											Delivery
										</td>

										<td>
											<input type="checkbox" id="sips.noteOnExcp" name="sips.noteOnExcp"
												value="${sps.noteOnExcp }"  ${sps.noteOnExcp=='Y' ? 'checked':'' } disabled="disabled"/>
											Exception
										</td>
										<td>
											&nbsp;
										</td>
									</tr>
								</table>
							</fieldset>
							
							<fieldset>
								<legend>
									Package Charges
								</legend>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<th width="20%">
											Base Charge
										</th>
										<td width="39%">
											<input name="sips.baseCharge" type="text" class="NFText"
												size="10" value="${sps.baseCharge }" maxlength="10"
												onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
												onfocus="this.select()" />
										</td>

										<th width="32%">
											Delivery Confirmation Fee
										</th>
										<td width="9%">
											<input name="sips.deliveryConfirmFee" type="text"
												class="NFText" size="10" value="${sps.deliveryConfirmFee }"
												maxlength="10"
												onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
												onfocus="this.select()" />
										</td>
									</tr>
									<tr>
										<th>
											Other Packaging Fees
										</th>
										<td>
											<input name="sips.packagingFee" type="text" class="NFText"
												size="10" value="${sps.packagingFee }" maxlength="10"
												onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
												onfocus="this.select()" readonly="readonly"/>
											(Add'I Handling,Surcharge etc)
											<br />
										</td>

										<th>
											Carrier Actual Charge
										</th>
										<td>
											<input name="sips.actlCarrCharge" type="text" class="NFText"
												size="10" value="${sps.actlCarrCharge }" maxlength="10"
												onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
												onfocus="this.select()" readonly="readonly"/>
										</td>
									</tr>
									<tr>
										<td colspan="4">
										</td>
									</tr>
									<tr>
										<th>
											Insurance Charge
										</th>
										<td>
											<input name="sips.insuranceCharge" type="text" class="NFText"
												size="10" value="0" readonly="readonly"/>
										</td>
										<th>
											Additional Customer Charges
										</th>

										<td>
											<input name="sips.adtlCustomerCharge" type="text"
												class="NFText" size="10" value="${sps.adtlCustomerCharge }"
												maxlength="10"
												onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
												onfocus="this.select()" />
										</td>
									</tr>
									<tr>
										<td colspan="4">
										</td>
									</tr>
									<tr>
										<th>
											&nbsp;
										</th>
										<td></td>
										<th>
											Total Shipping Charge
										</th>
										<td>
											<input name="sips.shippingAccount" type="text" class="NFText"
												size="10" value="${sps.actlCarrCharge + sps.adtlCustomerCharge }" maxlength="20" readonly="readonly"/>
										</td>
									</tr>

								</table>
							</fieldset>
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="General_table">
								<tr>
									<th width="30%">
										Instructions on the Box Label
									</th>
									<td width="70%">
										<textarea name="sips.note" class="content_textarea2">${sps.note }</textarea>
									</td>
								</tr>
							</table>
						</fieldset>
					</td>
				</tr>

				<tr>
					<td colspan="4">
						<DIV class="botton_box">
							<input type="button" name="Submit62" class="style_botton"
								value="Save" onclick="shows();"/>
							<input type="button" name="Submit622" value="Cancel"
								class="style_botton"
								onclick="parent.$('#package_information').dialog('close');" />
						</DIV>
					</td>
				</tr>
			</table>
 			
			
			<!--<table width="750" border="0" cellpadding="0" cellspacing="0"
				class="General_table">
				<tr>
					<th>
						Ship to
					</th>

					<td colspan="3">
						<textarea name="sips.shiptoAddress" class="content_textarea2"
							style="width: 250PX; height: 70px;">
						${sps.shiptoAddress }
						</textarea>
					</td>
				</tr>
				<tr>
					<th>
						Delivery Type
					</th>
					<td>
						<input name="sips.deliveryType" type="text" class="NFText"
							value="${sps.deliveryType }" size="20" maxlength="20" />
					</td>
					<th>
						Status
					</th>
					<td>
						<input name="sips.status" type="text" class="NFText"
							value="${sps.status }" size="20" maxlength="20" />
					</td>
				</tr>
				<tr>
					<th>
						Shipment Date
					</th>
					<td>
						<input name="sips.shipmentDate" type="text" class="NFText"
							value="${sps.shipmentDate }" size="20" readonly="readonly" />
					</td>
					<th>
						Tracking Number
					</th>
					<td>
						<input name="sips.trackingNo" type="text" class="NFText"
							value="${sps.trackingNo }" size="20" maxlength="20" />
					</td>
				</tr>
				<tr>
					<th>
						Actual Weight
					</th>
					<td>
						<input name="sips.actualWeight" type="text" class="NFText"
							value="${sps.actualWeight }" size="20" maxlength="10"
							onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
							onfocus="this.select()" />
					</td>
					<th>
						Billable Weight
					</th>
					<td>
						<input name="sips.billableWeight" type="text" class="NFText"
							value="${sps.billableWeight }" size="20" maxlength="10"
							onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
							onfocus="this.select()" />
						(lb)
					</td>
				</tr>
				<tr>
					<th>
						Zone
					</th>
					<td>
						<input name="sips.zone" type="text" class="NFText"
							value="${sps.zone }" size="20" maxlength="10" />
					</td>
					<th>
						Package
					</th>
					<td>
						<input name="sips.pkgBatchSeq" type="text" class="NFText"
							value="${sps.pkgBatchSeq }" size="4" />
						&nbsp;&nbsp;Of
						<input name="sips.pkgBatchCount" type="text" class="NFText"
							value="${sps.pkgBatchCount }" size="4" />
					</td>
				</tr>
				<tr>
					<th>
						Carrier Charges
					</th>
					<td>
						<input name="sips.carrierCharge" type="text" class="NFText"
							value="${sps.carrierCharge }" size="20" maxlength="10"
							onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
							onfocus="this.select()" />
					</td>
					<th>
						Customer Charges
					</th>
					<td>
						<input name="sips.customerCharge" type="text" class="NFText"
							value="${sps.customerCharge }" size="20" maxlength="10"
							onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
							onfocus="this.select()" />
					</td>
				</tr>
				<tr>
					<th>
						Insurance Value
					</th>

					<td>
						<input name="sips.insuranceValue" type="text" class="NFText"
							value="${sps.insuranceValue }" size="20" maxlength="10"
							onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
							onfocus="this.select()" />
					</td>
					<th>
						Packer
					</th>
					<td>
						<input name="sips.packer" type="text" class="NFText" size="20"
							value="${sps.packer }" maxlength="50" />
					</td>
				</tr>
				<tr>
					<th>
						Shipment Method
					</th>
					<td>
						<select name="sips.shipMethod">
							<option value="${sps.shipMethod }">
								${sd.name_ }
							</option>
						</select>
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<fieldset>
							<legend>
								UPS
							</legend>

							<fieldset>
								<legend>
									Package Options
								</legend>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
											<input type="checkbox" id="sips.additionalHandle"  name="sips.additionalHandle"
												value="${sps.additionalHandle}"  ${sps.additionalHandle=='Y' ? 'checked':'' }/>
											Additional Handling
										</td>
										<td>
											<input type="checkbox" id="sips.deliveryConfirm" name="sips.deliveryConfirm"
												value="${sps.deliveryConfirm }"  ${sps.deliveryConfirm=='Y' ? 'checked':'' }/>
											Delivery Confirmation
										</td>

										<td>
											<input type="checkbox" id="sips.hazardousMtl" name="sips.hazardousMtl"
												value="${sps.hazardousMtl }"  ${sps.hazardousMtl=='Y' ? 'checked':'' }/>
											Hazardous Material
										</td>
										<td>
											<input type="checkbox" id="sips.saturdayPickup" name="sips.saturdayPickup"
												value="${sps.saturdayPickup }"  ${sps.saturdayPickup=='Y' ? 'checked':'' }/>
											Staturday Pickup
										</td>
									</tr>
									<tr>
										<td>

										</td>

										<td>
											&nbsp;
										</td>
										<td>
											&nbsp;
										</td>
										<td>
											&nbsp;
										</td>
									</tr>
									<tr>
										<td>
											<input type="checkbox" name="sips.noteOnShip"
												value="${sps.noteOnShip }" id="sips.noteOnShip" ${sps.noteOnShip=='Y' ? 'checked':'' } />
											Send Email Notification at Ship
										</td>
										<td>
											<input type="checkbox" id="sips.noteOnDelivery" name="sips.noteOnDelivery"
												value="${sps.noteOnDelivery}"  ${sps.noteOnDelivery=='Y' ? 'checked':'' }/>
											Delivery
										</td>

										<td>
											<input type="checkbox" id="sips.noteOnExcp" name="sips.noteOnExcp"
												value="${sps.noteOnExcp }"  ${sps.noteOnExcp=='Y' ? 'checked':'' }/>
											Exception
										</td>
										<td>
											&nbsp;
										</td>
									</tr>
								</table>
							</fieldset>
							<fieldset>
								<legend>
									Package Size
								</legend>

								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									class="General_table">
									<tr>
										<td>
											&nbsp;
										</td>
										<td>
											<select name="sips.packageType">
												<option value="${sps.packageType }">
													Regular
												</option>
											</select>
										</td>
										<th>
											Length
										</th>

										<td>
											<input name="sips.length" type="text" class="NFText"
												value="${sps.length }" size="5" maxlength="10"
												onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
												onfocus="this.select()" />
											(inches)
										</td>
										<th>
											Width
										</th>
										<td>
											<input name="sips.width" type="text" class="NFText"
												value="${sps.width }" size="5" maxlength="10"
												onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
												onfocus="this.select()" />
											(inches)
										</td>
										<th>
											Height
										</th>
										<td>
											<input name="sips.height" type="text" class="NFText"
												value="${sps.height }" size="5" maxlength="10"
												onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
												onfocus="this.select()" />
											(inches)
										</td>
									</tr>

								</table>
							</fieldset>
							<fieldset>
								<legend>
									Package Charges
								</legend>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<th width="20%">
											Base Charge
										</th>
										<td width="39%">
											<input name="sips.baseCharge" type="text" class="NFText"
												size="10" value="${sps.baseCharge }" maxlength="10"
												onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
												onfocus="this.select()" />
										</td>

										<th width="32%">
											Delivery Confirmation Fee
										</th>
										<td width="9%">
											<input name="sips.deliveryConfirmFee" type="text"
												class="NFText" size="10" value="${sps.deliveryConfirmFee }"
												maxlength="10"
												onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
												onfocus="this.select()" />
										</td>
									</tr>
									<tr>
										<th>
											Other Packaging Fees
										</th>
										<td>
											<input name="sips.packagingFee" type="text" class="NFText"
												size="10" value="${sps.packagingFee }" maxlength="10"
												onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
												onfocus="this.select()" />
											(Add'I Handling,Surcharge etc)
											<br />
										</td>

										<th>
											Carrier Actual Charge
										</th>
										<td>
											<input name="sips.actlCarrCharge" type="text" class="NFText"
												size="10" value="${sps.actlCarrCharge }" maxlength="10"
												onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
												onfocus="this.select()" />
										</td>
									</tr>
									<tr>
										<td colspan="4">
										</td>
									</tr>
									<tr>
										<th>
											Insurance
										</th>
										<td>
											<input name="sips.insuranceCharge" type="text" class="NFText"
												size="10" value="${sps.insuranceCharge }" maxlength="10"
												onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
												onfocus="this.select()" />
										</td>
										<th>
											Additional Customer Charges
										</th>

										<td>
											<input name="sips.adtlCustomerCharge" type="text"
												class="NFText" size="10" value="${sps.adtlCustomerCharge }"
												maxlength="10"
												onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
												onfocus="this.select()" />
										</td>
									</tr>
									<tr>
										<td colspan="4">
										</td>
									</tr>
									<tr>
										<th>
											&nbsp;
										</th>
										<td></td>
										<th>
											Total Shipping Charge
										</th>
										<td>
											<input name="sips.shippingAccount" type="text" class="NFText"
												size="10" value="${sps.shippingAccount }" maxlength="20" />
										</td>
									</tr>

								</table>
							</fieldset>
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="General_table">
								<tr>
									<th width="30%">
										Instructions on the Box Lable
									</th>
									<td width="70%">
										<textarea name="sips.note" class="content_textarea2">${sps.note }</textarea>
									</td>
								</tr>
							</table>
						</fieldset>
					</td>
				</tr>

				<tr>
					<td colspan="4">
						<DIV class="botton_box">
							<input type="button" name="Submit62" class="style_botton"
								value="Save" onclick="shows();"/>
							<input type="button" name="Submit622" value="Cancel"
								class="style_botton"
								onclick="parent.$('#package_information').dialog('close');" />
						</DIV>
					</td>
				</tr>
			</table>
		--></form>
		<div id="edit_address" title=" Edit Address "
			style="visible: hidden" />
	</body>
</html>