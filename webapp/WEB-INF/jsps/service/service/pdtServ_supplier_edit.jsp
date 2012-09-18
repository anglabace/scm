<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url }SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url }tab-view.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.form.js"></script>
<script src="${global_js_url}jquery/jquery.validate.js" type="text/javascript"></script>
<script type="text/javascript" src="${global_js_url}util/util.js"></script>
<script>
$(document).ready(function(){
	// validate signup form on keyup and submit
	$("#editSupplierForm").validate({
		invalidHandler: function(form, validator) {
	        $.each(validator.invalid, function(key,value){
	            alert(value);
	            $("#"+key).focus();
	            return false;
	        });
  		},
		rules: {
  			vendorName: { required:true },
  			"vendorService.vendorCatalogNo": { required:true },
  			"vendorService.name": { required:true }
		},
		messages: {
			vendorName: "Please select supplier",
			"vendorService.vendorCatalogNo": "Please enter 'Supplier Item Catalog No' ",
			"vendorService.name": "Please enter item name "
		},
		errorPlacement: function(error, element) {
		}
				
	});

	if($("#vendorName").val()){
		$("#saveSupplier").attr("value","Save");
	}
	
	$("#saveSupplier").click(function(){
		if($('#editSupplierForm').valid() == false){
			return false;
		}
		var errorClass="validate_error";
		var formStr = $('#editSupplierForm').serialize();
		//ajax form post
		vendorName = $("#vendorName").val();
		vendorCatalogNo = $("#vendorCatalogNo").val();
		name = $("#name").val();
		sellQuantity = $("#sellQuantity").val();
		if(isNaN(sellQuantity)){
			   alert('Please enter the correct "Sell Quantity".');
			   $("#sellQuantity").addClass(errorClass);
			   return false; 
		}
		$("#sellQuantity").removeClass(errorClass); 
		byQuantity = $("#byQuantity").val();
		if(isNaN(byQuantity)){ 
			   alert('Please enter the correct "Buy Quantity".');
			   $("#byQuantity").addClass(errorClass);
			   return false; 
		} 
		$("#byQuantity").removeClass(errorClass); 
		standardPrice = $("#standardPrice").val();
		if(isNaN(standardPrice)){ 
			   alert('Please enter the correct "Unit Price".');
			   $("#standardPrice").addClass(errorClass);
			   return false; 
		}
		$("#standardPrice").removeClass(errorClass); 
		discount = $("#discount").val();
		leadTime = $("#leadTime").val();
		if(isNaN(leadTime)){ 
			   alert('Please enter the correct "lead time".');
			   $("#leadTime").addClass(errorClass);
			   return false; 
		}
		$("#leadTime").removeClass(errorClass); 
		comment = $("#comment").val();
	    var size = $("#size").val();
	    if(isNaN(size)){ 
			   alert('Please enter the correct "size".');
			   $("#size").addClass(errorClass);
			   return false; 
		}
	    $("#size").removeClass(errorClass); 
	    var altSize = $("#altSize").val();
	    if(isNaN(altSize)){ 
			   alert('Please enter the correct "Alt Size".');
			   $("#altSize").addClass(errorClass);
			   return false; 
		}
	    $("#altSize").removeClass(errorClass); 
		$.ajax({
		type: "POST",
		url: "serv/serv!saveSupplierSession.action",
		data: formStr,
		dataType: 'json',
		success: function(data, textStatus){
			if(data.message == 'success'){
			    var htmlStr = '<tr id="del_'+data.id+'"><td width="46">&nbsp;<input type="checkbox" name="supplierId" value="'+data.id+'" /></td>';
				htmlStr +='<td  width="140">&nbsp;'
				htmlStr +='<a href="javascript:void(0);" onclick="editSupplier(\''+data.id+'\', \''+vendorName+'\');" title="'+vendorName+'">'
				htmlStr +=vendorName+"</a>";    
              	htmlStr +='<input type="hidden" value="" id="'+data.id+'_supplierInfo" /></td>';           
                htmlStr +='<td width="120">&nbsp;'+vendorCatalogNo+'</td>';
                htmlStr+='<td width="100">&nbsp;'+name+'</td>';
                htmlStr+='<td width="50">&nbsp;'+sellQuantity+'</td>';
                htmlStr+='<td width="50">&nbsp;'+byQuantity+'</td>';
                htmlStr+=' <td width="80"><div align="right">&nbsp;'+standardPrice+'</div></td>';
                htmlStr+='<td width="80">&nbsp;'+discount+'</td>';
                htmlStr+='<td width="80">&nbsp;'+leadTime+'</td>';
                htmlStr+='<td>'+comment+'&nbsp;</td></tr>';
                if(data.isRemove=='true'){
					parent.$("#supplierIframe").contents().find("#del_"+data.id).remove();
                }
			    parent.$("#supplierIframe").contents().find('#supplierTable').prepend(htmlStr);
			    parent.$('#supplierEditDialog').dialog('close');
				parent.$('#supplierEditDialog').dialog('destory');
				//alert("The "+pdtServType+" is saved successfully!");
				//isSaved = true;
				//location.href = "product/product!input.action?type="+data.type+"&id="+data.id+"&defaultTab="+defaultTab;
			}
		},
		error: function(xhr, textStatus){
			alert("Failed to access the  web server. Please contact system administrator for help.");
			if(textStatus == 'timeout')
			{
	//			alert("Timeout!");
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

	$("#supplierPickerTrigger").click(function(){
		parent.$('#supplierPickerDialog').attr("title", "Supplier Pikcer");
		parent.$('#supplierPickerDialog').dialog("option", "open",function(){
			var psId = $("#id").val();
			var url = "${ctx}/serv/serv!showSupplierPikcerAct.action?id="+psId;
			var htmlStr = '<iframe src="'+url+'" height="350" width="650" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			parent.$('#supplierPickerDialog').html(htmlStr);
		});
		parent.$('#supplierPickerDialog').dialog("option", "title", "Supplier Lookup");
		parent.$('#supplierPickerDialog').dialog("open");
	});
});

</script>

</head>

<body>
<form id="editSupplierForm">
<table border="0" cellpadding="0" cellspacing="0" class="General_table" style="margin:10px auto;">
  <tr>
    <th><span class="important">*</span>Supplier</th>
    <td>
    	<input id="vendorName" name="vendorName" type="text" class="NFText" size="20" value="${vendorName}" readonly="readonly" />
   		<img id="supplierPickerTrigger" src="${global_image_url}search.jpg" width="16" height="16" border="0" align="absmiddle" />
    </td>
    <th>Supplier ID </th>
    <td>
    	<input id="vendorNo" name="vendorService.vendorNo" type="text" class="NFText" size="20" value="${vendorService.vendorNo}" readonly="readonly" />
    </td>
  </tr>
  <tr>
    <th style="padding:2px 0px 0"><span class="important">*</span>Supplier Item Catalog No</th>
    <td><input id="vendorCatalogNo" name="vendorService.vendorCatalogNo" type="text" class="NFText" value="${vendorService.vendorCatalogNo}" size="20" /></td>
    <th><span class="important">*</span>Item Name </th>
    <td><input id="name" name="vendorService.name" type="text" class="NFText" size="20" value="${vendorService.name}" /></td>
  </tr>
  <tr>
    <th>Buy Quantity </th>
    <td><input name="vendorService.buyQuantity" id="byQuantity" type="text" class="NFText" value="${vendorService.buyQuantity}" size="20" /></td>
    <th>Sell Quantity </th>
    <td><input name="vendorService.sellQuantity" id="sellQuantity" type="text" class="NFText" size="20" value="${vendorService.sellQuantity}" /></td>
  </tr>
  <tr>
    <th>Size</th>
    <td>
        <input name="vendorService.size" id="size" type="text" class="NFText" value="${vendorService.size}" size="3" />
        <strong>&nbsp;UOM</strong>
      	<input name="vendorService.uom" type="text" class="NFText" size="3" value="${vendorService.uom}" />    
    </td>
    <th>Alternative Size</th>
    <td>
      <input name="vendorService.altSize" id="altSize" type="text" class="NFText" value="${vendorService.altSize}" size="3" /> 
      <strong>&nbsp;UOM </strong>
      <input name="vendorService.altUom" type="text" class="NFText" size="3" value="${vendorService.altUom}" />    
    </td>
  </tr>
  
  <tr>
    <th>Unit Price</th>
    <td><input name="vendorService.standardPrice" id="standardPrice" type="text" class="NFText2" value="${vendorService.standardPrice}" size="20" /></td>
    <th>Discount</th>
    <td>
    	<input name="vendorService.discount" id="discount" type="text" class="NFText" size="20" value="${vendorService.discount}" />
    </td>
  </tr>
  <tr>
    <th>&nbsp;</th>
    <td>&nbsp;</td>
    <th>&nbsp;</th>
    <td>&nbsp;</td>
  </tr>
  
  <tr>
    <th valign="bottom">Lead Time For Delivery </th>
    <td valign="bottom">
    	<input name="vendorService.leadTime" id="leadTime" type="text" class="NFText" value="${vendorService.leadTime}" size="20" />
    </td>
    <th>&nbsp;</th>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th>Comments</th>
    <td colspan="3">
      <textarea name="vendorService.comment" id="comment" class="content_textarea2" style="width:410px;">${vendorService.comment}</textarea>    
     </td>
  </tr>
  <tr>
    <td colspan="4">
      <div class="botton_box">
        <input type="hidden" value="${vendorService.id}" name="vendorService.id" id="id" />
      	<input type="hidden" value="${id}" name="id" id="id" />
      	<input name="supplierId" type="hidden" value="${supplierId}" />
      	<input type="hidden" name="type" value="${type}" />
      	<input type="hidden" name="sessionServiceId" value="${sessionServiceId}" />
      	<input id="saveSupplier" type="button" class="style_botton" value="Add" />
      	<input name="" type="button" class="style_botton" value="Cancel" onclick="parent.$('#supplierEditDialog').dialog('close');" />
      </div>
    </td>
  </tr>
</table>
</form>
</body>
</html>
