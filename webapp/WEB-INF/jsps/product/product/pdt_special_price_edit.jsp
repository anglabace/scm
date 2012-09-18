<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Return Order</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />    
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.form.js"></script>
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1" type="text/javascript"></script>
<script language="javascript" type="text/javascript"> 
function calculateUnitPrice(){
	var discount = $("#discount").val();
	var standardPrice = $("#standardPrice").val();
	var patrn = /^\d+(\.\d+)?%?$/;
	if (!discount.match(patrn)){
		alert("Please enter the correct discount amount.");
		return;
	}
	var patrn = /^\d+(\.\d+)?$/;
	if (!discount.match(patrn)){
		var tmpDiscount = 1- parseFloat(discount)/100;
		var unitPrice = Math.round(standardPrice*tmpDiscount*1000)/1000;
	}else{
		var unitPrice = Math.round((standardPrice - discount)*1000)/1000;
	}
	$("#unitPrice").val(unitPrice);	
}

	$(document).ready(function(){
		$('.datepicker').each(function(){
			$(this).datepicker(
					{
						dateFormat: 'yy-mm-dd',
						changeMonth: true,
						changeYear: true
					});
		});
		// validate signup form on keyup and submit
		$("#editSpecialPriceForm").validate({
			errorClass:"validate_error",
			highlight: function(element, errorClass) {
			$(element).addClass(errorClass);
			},
			unhighlight: function(element, errorClass, validClass) {
			$(element).removeClass(errorClass);
			},
			invalidHandler: function(form, validator) {
		        $.each(validator.invalid, function(key,value){
		            alert(value);
		            $("[name='"+key+"']").focus();
		            return false;
		        });
			},
			rules: {
				"specialPriceDTO.rfmRating": { required:true ,number:true},
				"specialPriceDTO.minQty": { required:true ,number:true},
				"specialPriceDTO.orderTotal": { required:true ,number:true }
			},
			messages: {
				"specialPriceDTO.rfmRating": { required: "This 'rfmRating' must be a digit! ",number: "This 'rfmRating' must be a digit!"},
				"specialPriceDTO.minQty": { required: "Please enter 'minQty' ",number: "This 'minQty' must be a digit!"},
				"specialPriceDTO.orderTotal": { required: "Please enter 'orderTotal' ",number: "This 'orderTotal' must be a digit!"}
	  		
			},
			errorPlacement: function(error, element) {}		
		});
	});
	$(document).ready(function(){
		$("#saveSpecialPrice").click(function(){
			if($('#editSpecialPriceForm').valid() == false){
				return false;
			}
			var errorClass="validate_error";
			var formStr = $('#editSpecialPriceForm').serialize();
			//ajax form post
			standardPrice = $("#standardPrice").val();
			discount = $("#discount").val();
			unitPrice = $("#unitPrice").val();
			rfmRating = $("#rfmRating").val();
			sourceId = $("#sourceId").val();
			minQty = $("#minQty").val();
			orderTotal = $("#orderTotal").val();
			effFrom = $("#effFrom").val();
			effTo = $("#effTo").val();
			catalogId = $("#catalogId").val();
			sourceKey = $("#sourceId option:selected").text();
			var $specialPriceTable = parent.$("#priceIframe").contents().find("#specialPriceTable tr:last td:eq(1)");
			if(isNaN(parseInt($specialPriceTable.text()))){
				var seqNumber = 1;
			}else{
			var seqNumber = parseInt($specialPriceTable.text())+1;
			}
			$.ajax({
			type: "POST",
			url: "product/product_pricing!saveSpecialPriceSession.action",
			data: formStr,
			dataType: 'json',
			success: function(data, textStatus){
				if(data.message == 'success'){
					if(data.isRemove=='true'){
						parent.$("#priceIframe").contents().find("#delSpc_"+data.id).remove();
						seqNumber-=1;
	                }
					var htmlStr = '<tr id="delSpc_'+data.id+'"><td width="46"><input type="checkbox" name="priceIdChk" value="'+data.id+'" /></td>';
	                htmlStr +='<td  width="48">&nbsp;'+seqNumber+'</td>';
	                htmlStr +='<td  width="70"><div align="right">';
	                htmlStr +='<a href="javascript:void(0);" onclick="editSpecialPrice(\''+data.id+'\');" title="Special Price">&nbsp;';
	                htmlStr +=unitPrice+'</a></div></td>';                 
	                htmlStr +='<td width="70"><div align="right">&nbsp;'+discount+'</td>';
	                htmlStr +='<td width="70"><div align="center">&nbsp;'+rfmRating+'</td>';
	                htmlStr +='<td width="180">&nbsp;'+sourceKey+'</td>';
	                htmlStr +='<td width="56">&nbsp;'+minQty+'</td>';
	                htmlStr +=' <td width="100">&nbsp;'+catalogId+'</td>';
	                htmlStr +='<td width="80"><div align="center">&nbsp;'+effFrom+'</td>';
	                htmlStr +='<td width="80"><div align="center">&nbsp;'+effTo+'</td>';
	                htmlStr +='<td><div align="right">&nbsp;'+orderTotal+'</div></td></tr>';
	                
				    parent.$("#priceIframe").contents().find('#specialPriceTable').append(htmlStr);
				    parent.$('#specialPriceAddDialog').dialog('close');
					parent.$('#specialPriceAddDialog').dialog('destory');
					parent.$('#specialPriceEditDialog').dialog('close');
					parent.$('#specialPriceEditDialog').dialog('destory');
					//alert("The "+pdtServType+" is saved successfully!");
					//isSaved = true;
					//location.href = "product/product!input.action?type="+data.type+"&id="+data.id+"&defaultTab="+defaultTab;
				}
			},
			error: function(xhr, textStatus){
				alert("Failed to access the  web server. Please contact system administrator for help.!");
				if(textStatus == 'timeout')
				{
					
				}
				
				if(textStatus == 'parsererror')
				{
					tmp = xhr.responseText.split('{', 2);
					alert(tmp[0]);
					//document.write(tmp[0]);
				}
			}
			});
		});
	});
</script>

<style type="text/css">
<!--
body{width:460px; margin:0px auto}
-->
</style></head>

<body>
<form id="editSpecialPriceForm">
<table border="0"  cellpadding="0" cellspacing="0" class="General_table">
 <tr>
    <td height="32" colspan="4" valign="bottom"  class="blue_price"><strong> Method Of Pricing</strong></td>
  </tr>
  <tr>
    <th width="109">Standard Price</th>

    <td width="139"><input id="standardPrice" name="specialPriceDTO.standardPrice" value="${specialPriceDTO.standardPrice}" type="text" class="NFText2" size="15" readonly="readonly"/></td>
    <th width="96">With Discount</th>
    <td width="138"><input id="discount" name="specialPriceDTO.discount" type="text" class="NFText2" size="15" value="${specialPriceDTO.discount}" onchange="calculateUnitPrice();" <c:if test="${empty specialPriceDTO.standardPrice}">readonly="readonly"</c:if>/></td>
  </tr>
    <tr>
    <th width="109">Unit Price</th>
    <td width="139"><input id="unitPrice" name="specialPriceDTO.unitPrice" type="text" class="NFText2" size="15" value="${specialPriceDTO.unitPrice}" readonly="readonly"/>    </td>

    <th width="96">&nbsp;</th>
    <td width="138">&nbsp;</td>
  </tr>
  <tr>
    <td height="32" colspan="4" valign="bottom"  class="blue_price"><strong> Qualifiers For This Price </strong></td>
  </tr>
  <tr>
    <td colspan="4"><fieldset>

      <legend>Order</legend>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
        <tr>
          <th>RFM Rating </th>
          <td><input id="rfmRating" name="specialPriceDTO.rfmRating" type="text" class="NFText" size="12" value="${specialPriceDTO.rfmRating}"/></td>
          <th>Source Key </th>
          <td><s:select cssStyle="width:92px" id="sourceId" name="specialPriceDTO.sourceId" list="specDropDownList['ORIGINAL_SOURCE'].dropDownDTOs" listKey="id" listValue="name" headerKey="" headerValue="Select Source" value="specialPriceDTO.sourceId"></s:select></td>

        </tr>
        <tr>
          <th>Min. Qty. </th>
          <td><input id="minQty" name="specialPriceDTO.minQty" type="text" class="NFText" size="12" value="${specialPriceDTO.minQty}"/></td>
          <th>Order Total</th>
          <td><input id="orderTotal" name="specialPriceDTO.orderTotal" type="text" class="NFText2" size="12" value="${specialPriceDTO.orderTotal}"/></td>
        </tr>
        <tr>

          <th>Start Date </th>
          <td><input name="specialPriceDTO.effFrom" type="text" class="NFText datepicker" value="<s:date name="specialPriceDTO.effFrom" format="yyyy-MM-dd"/>" size="12" id="effFrom"  readonly="readonly"/></td>
          <th>End Date </th>
          <td><input name="specialPriceDTO.effTo" type="text" class="NFText datepicker" value="<s:date name="specialPriceDTO.effTo" format="yyyy-MM-dd"/>" size="12" id="effTo" readonly="readonly"/> </td>
        </tr>
      </table>
    </fieldset></td>

  </tr>
  <tr>
    <td colspan="4"><div class="botton_box">
    <input type="hidden" name="sessionProductId" value="${sessionProductId}" />
    <input type="hidden" name="specialPriceDTO.id" value="${specialPriceDTO.id}" />
    <input type="hidden" id="catalogId" name="catalogId" value="${baseCatalogId}" />
      <input  type="button" id="saveSpecialPrice" name="saveSpecialPrice" value="Add" class="style_botton"  />
      <input  type="button" name="close" value="Cancel" class="style_botton" onclick="parent.$('#specialPriceEditDialog').dialog('close');parent.$('#specialPriceAddDialog').dialog('close');" />
    </div></td>
  </tr>
</table>
</form>
</body>
</html>
