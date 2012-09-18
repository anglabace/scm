<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<s:include value="order_config.jsp"></s:include>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.form.js"></script>

<script>
function init(shipMethod){
	$("#shipMethod").find("option[value='"+shipMethod+"']").attr("selected", true);
	changeShipMethod();
	if($("#shippingAccount").val() != ""){
		$("[name='customerCharge']").attr("readonly", true);
	}
}

function changeShipMethod(){
	var shipMethod = $("#shipMethod").find("option[selected]").val();
	var carrier = $("#shipMethodCarrier").find("option[value='"+shipMethod+"']").html();
	$("#carrierLegend").html(carrier);
}

function getFloat(str){
	if(!str){
		return 0;	
	}
	var newStrs = [];
	var regularStr = "0123456789.";
	for(var i=0; i<str.length; i++){
		if(regularStr.indexOf(str[i]) > -1){
			newStrs.push(str[i]);
		}
	}
	return newStrs.join("");
}

function modifyPackage(){
//	if($('#packageModifyForm').valid() == false)
//	{
//		return false;
//	}
	var form = $("#packageModifyForm");
	var customerCharge = getFloat(form.find("[name='customerCharge']").val());//浮点型数据处理
	form.find("[name='customerCharge']").val(customerCharge);
	var options = {
		success:function(data){
			if(data == "SUCCESS")
				alert("The package is modified.");
			else{
				if(data){
					alert(data);
				}else{
					alert("System error! Please contact system administrator for help.");
				}
			}
			parent.document.getElementById("totalIframe").src = orderquoteObj.url("showTotal");
			parent.document.getElementById("packagingIframe").src = parent.$("#packagingIframe").attr("_src");
			parent.$('#packageModifyDialog').dialog('close');
		},
		error: function(){
			alert("Other Error");
		},
		resetForm:false,
		url:orderquoteObj.url("packageSave"),
		type:"POST"
	};
	form.ajaxForm(options);
	form.submit();
}

$(document).ready(function(){
	$("#modifyPackageBtn").click(function(){
		modifyPackage();
	});
	init($("#shipMethod").attr("_h"));
	$("#shipMethod").change(function(){
		changeShipMethod();
	});
});
</script>

</head>

<body>
<input type="hidden" id="quorderStatus" value="${quorderStatus}"/>
<form id="packageModifyForm" >
<table width="750" border="0" cellpadding="0" cellspacing="0" class="General_table">
  <tr>
    <th>Ship to </th>
    <td colspan="3">
    	<input type="text" class="NFText" size="80" value="${shiptoAddress}" readonly="readonly" />
    </td>
  </tr>
  <tr>
    <th>Delivery Type </th>
    <td>
    	<input name="deliveryType" type="text" class="NFText" value="${deliveryType}" size="20" readonly="readonly" />
    </td>
    <th>Status</th>
    <td><input name="status" type="text" class="NFText" value="${status}" size="20" readonly="readonly" /></td>
  </tr>
  <tr>
    <th>Shipment Date </th>
    <td>
    	<input name="shipmentDate" type="text" class="NFText" value='<s:date name="shipmentDate" format="yyyy-MM-dd"/>' size="20" readonly="readonly" />
    </td>
    <th>Tracking Number </th>
    <td><input name="trackingNo" type="text" class="NFText" value="${trackingNo}" size="20" readonly="readonly" /></td>
  </tr>
  <tr>
    <th>Actual Weight</th>
    <td><input name="actualWeight" type="text" class="NFText" value="${actualWeight}" size="20" /></td>
    <th>Billable Weight </th>
    <td><input name="billableWeight" type="text" class="NFText" value="${billableWeight}" size="20" />
    (lb)</td>
  </tr>
  <tr>
    <th>Zone</th>
    <td><input name="zone" type="text" class="NFText" value="${zone}" size="20" readonly="readonly" /></td>
    <th>Package</th>
    <td><input name="pkgBatchSeq" type="text" class="NFText" value="${pkgBatchSeq}" size="4" />
      &nbsp;&nbsp;Of 
      <input name="pkgBatchCount" type="text" class="NFText" value="${pkgBatchCount}" size="4" /></td>
  </tr>
  <tr>
    <th>Carrier Charges </th>
    <td><input name="" type="text" class="NFText" value="${carrierCharge}" size="20" readonly="readonly" />(${symbol})</td>
    <th>Customer Charges </th>
    <td>
    	<input id="shippingAccount" value="${shippingAccount}" type="hidden" /> 
    	<input name="customerCharge" type="text" class="NFText" value="${customerCharge}" size="20" />(${symbol})
    </td>
  </tr>
  <tr>
    <th>Insurance Value </th>
    <td><input name="insuranceValue" type="text" class="NFText" value="${insuranceValue}" size="20" />(${symbol})</td>
    <th>Packer</th>
    <td><input name="packer" type="text" class="NFText" size="20" value="${packer}" /></td>
  </tr>
  <tr>
    <th>Shipment Method</th>
    <td>
    	<s:select id="shipMethod" name="shipMethod" list="specDropDownList['SHIP_METHOD'].dropDownDTOs" listKey="id" listValue="name" value="shipMethod" ></s:select>
    	<s:select id="shipMethodCarrier" cssStyle="display:none;" name="" list="specDropDownList['SHIP_METHOD'].dropDownDTOs" listKey="id" listValue="value"></s:select>
    </td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4">
	<fieldset>
		<legend id="carrierLegend">UPS</legend>
				<fieldset>
					<legend>Package Options</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td>
						  	<s:checkbox name="additionalHandle" value='additionalHandle == "Y"' fieldValue="Y"></s:checkbox>
						  	Additional Handling
						</td>
						<td>
							<s:checkbox name="deliveryConfirm" value='deliveryConfirm == "Y"' fieldValue="Y"></s:checkbox>
						  	Delivery Confirmation
						</td>
						<td>
						 	<s:checkbox name="hazardousMtl" value='hazardousMtl == "Y"' fieldValue="Y"></s:checkbox>
						 	 Hazardous Material 
						</td>
						<td>
						  	<s:checkbox name="saturdayPickup" value='saturdayPickup == "Y"' fieldValue="Y"></s:checkbox>
						  	Staturday Pickup 
						  	</td>
					  </tr>
					  <tr>
						<td>Send Email Notification at</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					  </tr>
					  <tr>
					    <td>
							<s:checkbox name="noteOnShip" value='noteOnShip == "Y"' fieldValue="Y"></s:checkbox>
							Ship
						</td>
				        <td>
								<s:checkbox name="noteOnDelivery" value='noteOnDelivery == "Y"' fieldValue="Y"></s:checkbox>
								Delivery
							</td>
					    <td>
					    	<s:checkbox name="noteOnExcp" value='noteOnExcp == "Y"' fieldValue="Y"></s:checkbox>
							Exception
						</td>
					    <td>&nbsp;</td>
					  </tr>
					</table>
				</fieldset>
				<fieldset>
					<legend>Package Size</legend>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
					  <tr>
						<td>&nbsp;</td>
						<td>
							<select name="select2">
								<option value="packageType">${packageType}</option>  
							</select>						
						</td>
						<th>Length</th>
						<td><input name="length1" type="text" class="NFText" value="${length}" size="5" />(inches)</td>
						<th>Width</th>
						<td><input name="width" type="text" class="NFText" value="${width}" size="5" />(inches)</td>
						<th>Height</th>
						<td><input name="height" type="text" class="NFText" value="${height}" size="5" />(inches)</td>
					  </tr>
				</table>
			    </fieldset>
				<fieldset>
					<legend>Package Charges </legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <th width="18%">Base Charge </th>
    <td width="39%">
    	<input name="baseCharge" type="text" class="NFText" size="10" value="${baseCharge}" readonly="readonly" />(${symbol})
    </td>
	<th width="25%">Delivery Confirmation Fee </th>
    <td width="18%">
    	<input name="deliveryConfirmFee" type="text" class="NFText" size="10" value="${deliveryConfirmFee}" />(${symbol})
    </td>
  </tr>
  <tr>
    <th>Other Packaging Fees </th>
    <td>
    	<input name="packagingFee" type="text" class="NFText" size="10" value="${packagingFee}" />(${symbol})
      (Add'I Handling,Surcharge etc) <br /></td>
	 <th>Carrier Actual Charge </th>
    <td>
    	<input name="actlCarrCharge" type="text" class="NFText" size="10"  value="${actlCarrCharge}" readonly="readonly" />(${symbol})
    </td>
  </tr>
  <tr><td colspan="4"></td></tr>
  <tr>
    <th>Insurance</th>
    <td>
    	<input name="insuranceCharge" type="text" class="NFText" size="10" value="${insuranceCharge}" />(${symbol})
    </td>
	<th>Additional Customer Charges </th>
    <td>
    	<input name="adtlCustomerCharge" type="text" class="NFText" size="10" value="${adtlCustomerCharge}" />(${symbol})
    </td>
  </tr>
  <tr><td colspan="4"></td></tr>
  <tr>
    <th>&nbsp;</th>
	<td></td>
	<th>Total Shipping Charge </th>
    <td>
    	<input name="" type="text" class="NFText" size="10" value="${carrierCharge}" readonly="readonly" />(${symbol})
   	</td>    
  </tr>
</table>
			    </fieldset>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
  <tr>
    <th width="30%">Instructions on the Box Label</th>
    <td width="70%">
    	<textarea name="note" class="content_textarea2">${note}</textarea>
    </td>
  </tr>
</table>
</fieldset></td>
</tr>
	<tr>
		<td colspan="4">
			<div class="botton_box">
					  <input type="hidden" value="${sessPackageId}" id="packageId" name="sessPackageId" />
					<s:if test = 'quorderStatus != "CN" && quorderStatus != "CC"'>
		              <input id="modifyPackageBtn" type="button" class="style_botton" value="Save" />
					</s:if>	
		              <input type="button" class="style_botton" value="Cancel" onclick="parent.$('#packageModifyDialog').dialog('close');" />
		    </div>
	    </td>
    </tr>
</table>
</form>
</body>
</html>
