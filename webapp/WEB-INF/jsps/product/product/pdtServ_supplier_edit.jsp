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
			vendorName: { required:true },
  			"vendorProduct.vendorCatalogNo": { required:true },
  			"vendorProduct.name": { required:true },
  			"vendorProduct.buyQuantity":{number:true},
  			"vendorProduct.sellQuantity":{number:true},
  			"vendorProduct.standardPrice":{number:true},
  			"vendorProduct.leadTime":{number:true},
  			"vendorProduct.size":{number:true},
  			"vendorProduct.altSize":{number:true}
		},
		messages: {
			vendorName: { require: "Please enter 'Supplier' "},
			"vendorProduct.vendorCatalogNo": { require: "Please enter 'Supplier Item Catalog No' "},
  			"vendorProduct.name": { require: "Please enter 'Item Name' "},
  			"vendorProduct.buyQuantity":{ number: "This 'Buy Quantity' must be a digit!"},
  			"vendorProduct.sellQuantity":{ number: "This 'Sell Quantity' must be a digit!"},
  			"vendorProduct.standardPrice":{ number: "This 'Unit Price' must be a digit!"},
  			"vendorProduct.leadTime":{ number: "This 'Lead Time For Delivery' must be a digit!"},
  			"vendorProduct.size":{ number: "This 'Size' must be a digit!"},
  			"vendorProduct.altSize":{ number: "This 'altSize' must be a digit!"}
		},
		errorPlacement: function(error, element) {}		
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
		byQuantity = $("#byQuantity").val();
		standardPrice = $("#standardPrice").val();
		discount = $("#discount").val();
		leadTime = $("#leadTime").val();
		comment = $("#comment").val();
	    var size = $("#size").val();
	    var altSize = $("#altSize").val();
		$.ajax({
		type: "POST",
		url: "product/product!saveSupplierSession.action",
		data: formStr,
		dataType: 'json',
		success: function(data, textStatus){
			if(data.message == 'success'){
				var htmlStr = '<tr id="del_'+data.id+'"><td width="46">&nbsp;&nbsp;<input type="checkbox" name="supplierId" value="'+data.id+'" /></td>';
                htmlStr +='<td  width="140">&nbsp;'
                htmlStr +='<a href="javascript:void(0);" onclick="editSupplier(\''+data.id+'\', \''+vendorName+'\');" title="'+vendorName+'">&nbsp;'
                htmlStr +=vendorName+"</a>";              
              	htmlStr +='<input type="hidden" value="" id="'+data.id+'_supplierInfo" /></td>';           
                htmlStr +='<td width="120">&nbsp;'+vendorCatalogNo+'</td>';
                htmlStr+='<td width="100">&nbsp;'+name+'</td>';
                htmlStr+='<td width="50">&nbsp;'+sellQuantity+'</td>';
                htmlStr+='<td width="50">&nbsp;'+byQuantity+'</td>';
                htmlStr+=' <td width="80"><div align="right">&nbsp;'+standardPrice+'</div></td>';
                htmlStr+='<td width="80">&nbsp;'+discount+'</td>';
                htmlStr+='<td width="80">&nbsp;'+leadTime+'</td>';
                htmlStr+='<td>&nbsp;'+comment+'</td></tr>';
                if(data.isRemove=='true'){
					parent.$("#supplierIframe").contents().find("#del_"+data.id).remove();
                }
			    parent.$("#supplierIframe").contents().find('#supplierTable').append(htmlStr);
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
			var url = "${ctx}/product/product!showSupplierPikcerAct.action?id="+psId;
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
    	<input id="vendorNo" name="vendorProduct.vendorNo" type="text" class="NFText" size="20" value="${vendorProduct.vendorNo}" readonly="readonly" />
    </td>
  </tr>
  <tr>
    <th style="padding:2px 0px 0"><span class="important">*</span>Supplier Item Catalog No</th>
    <td><input id="vendorCatalogNo" name="vendorProduct.vendorCatalogNo" type="text" class="NFText" value="${vendorProduct.vendorCatalogNo}" size="20" /></td>
    <th><span class="important">*</span>Item Name </th>
    <td><input id="name" name="vendorProduct.name" type="text" class="NFText" size="20" value="${vendorProduct.name}" /></td>
  </tr>
  <tr>
    <th>Buy Quantity </th>
    <td><input name="vendorProduct.buyQuantity" id="byQuantity" type="text" class="NFText" value="${vendorProduct.buyQuantity}" size="20" /></td>
    <th>Sell Quantity </th>
    <td><input name="vendorProduct.sellQuantity" id="sellQuantity" type="text" class="NFText" size="20" value="${vendorProduct.sellQuantity}" /></td>
  </tr>
  <tr>
    <th>Size</th>
    <td>
        <input name="vendorProduct.size" id="size" type="text" class="NFText" value="${vendorProduct.size}" size="3" />
        <strong>&nbsp;UOM</strong>
      	<input name="vendorProduct.uom" type="text" class="NFText" size="3" value="${vendorProduct.uom}" />    
    </td>
    <th>Alternative Size</th>
    <td>
      <input name="vendorProduct.altSize" id="altSize" type="text" class="NFText" value="${vendorProduct.altSize}" size="3" /> 
      <strong>&nbsp;UOM </strong>
      <input name="vendorProduct.altUom" type="text" class="NFText" size="3" value="${vendorProduct.altUom}" />    
    </td>
  </tr>
  
  <tr>
    <th>Unit Price</th>
    <td><input name="vendorProduct.standardPrice" id="standardPrice" type="text" class="NFText2" value="${vendorProduct.standardPrice}" size="20" /></td>
    <th>Discount</th>
    <td>
    	<input name="vendorProduct.discount" id="discount" type="text" class="NFText" size="20" value="${vendorProduct.discount}" />
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
    	<input name="vendorProduct.leadTime" id="leadTime" type="text" class="NFText" value="${vendorProduct.leadTime}" size="20" />
    </td>
    <th>&nbsp;</th>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th>Comments</th>
    <td colspan="3">
      <textarea name="vendorProduct.comment" id="comment" class="content_textarea2" style="width:410px;">${vendorProduct.comment}</textarea>    
     </td>
  </tr>
  <tr>
    <td colspan="4">
      <div class="botton_box">
      	<input type="hidden" value="${vendorProduct.id}" name="vendorProduct.id" id="id" />
      	<input name="id" type="hidden" value="${id}" />
      	<input name="supplierId" type="hidden" value="${supplierId}" />
      	<input type="hidden" name="type" value="${type}" />
      	<input type="hidden" name="sessionProductId" value="${sessionProductId}" />
      	<input id="saveSupplier" type="button" class="style_botton" value="Add" />
      	<input name="" type="button" class="style_botton" value="Cancel" onclick="parent.$('#supplierEditDialog').dialog('close');" />
      </div>
    </td>
  </tr>
</table>
</form>
</body>
</html>
