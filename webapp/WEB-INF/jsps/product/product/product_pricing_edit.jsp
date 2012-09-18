<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url }tab-view.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.form.js"></script>
<script>
$(document).ready(function(){
	// validate signup form on keyup and submit
	$("#editProductPriceForm").validate({
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
			"productPrice.catalogId": { required:true },
			"productPrice.currencyCode": { required:true },
  			"productPrice.categoryId": { required:true },
  			"productPrice.unitPrice":{required:true,number:true},

  			
		},
		messages: {
			"productPrice.catalogId": { required: "Please select one catalog to continue your operation."},
			"productPrice.currencyCode": { required: "Please enter the currency code to continue your operation."},
			"productPrice.categoryId": { required: "Please select one category to continue your operation."},
			"productPrice.unitPrice":{ required:"Please enter unit price ", number: "This 'Retail Price' must be a digit!"}
		},
		errorPlacement: function(error, element) {}		
	});

	$("#savePriceApprovedTrigger").click(function(){
		var approved = $("#approvedPrice").val();
		var catalogId = $("#catalogId").val();
		//alert(approved);
		return;
		var approvedReason = $("#approvedPriceReason").val();
		var approvedType = $("#approvedPriceType").val();
		$.ajax({
			url:"product/product_pricing!productPriceApprovedSaveSession.action",
			type:"get",
			data:"approvedd="+approved+"&approvedReason="+approvedReason+"&approvedType="+approvedType+"&sessionProductId="+sessionProductId+"&catalogId="+catalogId,
			dataType:"json",
			success:function(data){
				if(hasException(data)){
					$('#savePriceApprovedTrigger').attr("disabled", false);	
				}else{
					if(data.message == "success"){
						alert("The modification is ready to be submitted for the evaluation and will be applied only after it’s approved.");
					}else{
						 alert("System error!Please contact system administrator for help.");
					}
				}
			},
			error:function(data){
					alert("System error! Please contact system administrator for help.");
			},
			async:false
		});
		$("#modifyUnitPriceDialog").dialog("close");
	});
	
});
function modifyUnitPriceClick(unitPrice, catalogId, sessionProductId) {
	if($("[name='priceAppr']").val() == 'true'){
		alert("The retail price have been modified");return;
	}
	parent.$('#modifyUnitPriceDialog').find('#approved').val(unitPrice);
	parent.$('#modifyUnitPriceDialog').find('#catalogId').val(catalogId);
	parent.$('#modifyUnitPriceDialog').find('#sessionProductId').val(sessionProductId);
	//alert($(window.parent.document).find('#modifyUnitPriceDialog').attr('id'));
	parent.$('#modifyUnitPriceDialog').dialog({
		autoOpen: false,
		height: 300,
		width: 600,
		modal: true,
		bgiframe: true,
		buttons: {}
	});	
	parent.$('#modifyUnitPriceDialog').dialog("option", "open",function(){
		//alert($('#modifyUnitPriceDialog').attr('id'));
		//var name = $("[name='unitPriceName']").val();
		var htmlStr = '<iframe src="product_pricing!getProductPriceApprovedSession.action?catalogId='+catalogId+'" height="240" width="570" scrolling="no" style="border:0px" frameborder="0"></iframe>';
		parent.$('#modifyUnitPriceDialog').html(htmlStr);
	});
	//alert($('#modifyUnitPriceDialog').attr('id'));
	parent.$('#modifyUnitPriceDialog').dialog('open');
}
function editPriceClick(){
		if($('#editProductPriceForm').valid() == false){
			return false;
		}
		if($("#categoryId option:selected").val() == ""){
			alert("Please select one item to continue your operation.");
			return false;
		}
		
		var errorClass="validate_error";
		var formStr = $('#editProductPriceForm').serialize();
		//ajax form post
		catalogId = $("#catalogId").val();
		categoryId = $("#categoryId").val();
		categoryName = $("#categoryId option:selected").text();
		currencyCode = $("#currencyCode").val();
		unitPrice = $("#unitPrice").val();
		limitPrice = $("#limitPrice").val();
		userName = $("#userName").val();
		var now = new Date() ;
		var today = "" ;
		today = now.getFullYear() + "-" + ( now.getMonth() + 1 ) + "-" + now.getDate() ;
		$.ajax({
		type: "POST",
		url: "product/product_pricing!savePriceSession.action",
		data: formStr,
		dataType: 'json',
		success: function(data, textStatus){
			if(data.message == 'success'){
				var htmlStr = '<tr id="del_'+data.id+'"><td width="46">&nbsp;<input type="checkbox" name="catalogIdChk" value="'+data.id+'" /></td>';
                htmlStr +='<td width="141">&nbsp;<a href="javascript:void(0);" onclick="editProductPrice(\''+catalogId+'\', \''+categoryId+'\', \''+unitPrice+'\', \''+limitPrice+'\', \''+currencyCode+'\');" title="Edit Product Price" rel="gb_page_center[580,200]">'+catalogId+'</a>';                         
                htmlStr +='<td width="210">&nbsp;'+categoryName+'</td>';
                htmlStr+='<td width="104" align="right">&nbsp;'+unitPrice+'</td>';
                htmlStr+='<td width="102" align="right">&nbsp;'+limitPrice+'</td>';
                htmlStr+='<td width="96">&nbsp;'+currencyCode+'</td>';
                htmlStr+='<td width="101">&nbsp;'+today+'</td>';
                htmlStr+='<td>&nbsp;'+userName+'</td></tr>';
                if(data.isRemove=='true'){
					parent.$("#priceIframe").contents().find("#del_"+data.id).remove();
                }
			    parent.$("#priceIframe").contents().find('#productPriceTable').prepend(htmlStr);
			    parent.$('#productPriceEditDialog').dialog('close');
				parent.$('#productPriceEditDialog').dialog('destory');
				//alert("The "+pdtServType+" is saved successfully!");
				//isSaved = true;
				//location.href = "product/product!input.action?type="+data.type+"&id="+data.id+"&defaultTab="+defaultTab;
			}
		},
		error: function(xhr, textStatus){
			alert("Failed to access the  web server. Please contact system administrator for help.");
			if(textStatus == 'timeout')
			{
				//alert("Timeout!");
			}
			
			if(textStatus == 'parsererror')
			{
				tmp = xhr.responseText.split('{', 2);
				alert(tmp[0]);
				//document.write(tmp[0]);
			}
		}
		});
}
		
function change_category(categoryLevel){
	var catalogId = $("#catalogId").val();
	var categoryId;
	if(categoryLevel==2){
		categoryId = $("#categoryId option:selected").val();
	}
	if(categoryLevel ==3){
		categoryId = $("#ccategoryId option:selected").val();
	}
	if(catalogId ==null||catalogId == ''){
		alert("The catalog id is not null.");
		return false;
	}
	if(categoryId ==null||categoryId == ''){
		alert("The category id is not null.");
		return false;
	}
	var reqUrl = "${ctx}/product/product_pricing!getCategoryByCatalogAndCategory.action?catalogId="+catalogId+"&categoryId="+categoryId+"&categoryLevel="+categoryLevel;
	//alert (reqUrl);
	$.ajax({
		type: "POST",
		dataType :"json",
		url: reqUrl,
		success: function(data){
			var html="";
			if(data.length>0){
				html ="<option></option>"
			}
			for(var i =0;i<data.length;i++){
				html += "<option value="+data[i].categoryId+">"+data[i].categoryNo+"</option>";
			}
			if(categoryLevel==2){
				$("#ccategoryId").html(html);
				$("#pcategoryId").html("");
			}
			if(categoryLevel ==3){
				$("#pcategoryId").html(html);
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
			}
		}
	}); 
}
</script>
</head>

<body style="padding-top:20px;">
<form id="editProductPriceForm">
<table border="0" align="center" cellpadding="0" cellspacing="0" class="General_table">
  <tr>
    <th width="100">Price Catalog</th>

    <td><input id="catalogId" name="productPrice.catalogId" type="text" class="NFText" size="20" readonly="readonly" value="${productPrice.catalogId}"/></td>
    <th width="100">Currency</th>
    <td><input id="currencyCode" name="productPrice.currencyCode" type="text" class="NFText" size="20" readonly="readonly" value="${productPrice.currencyCode}"/></td>
  </tr>
  <tr>
    <th>Business Unit</th>
    <td>
    <s:select id="categoryId"  list="productBCategoryList" listKey="categoryId" listValue="name" headerKey="" headerValue="Select Category" value="bcategoryId" onchange="change_category(2)" cssStyle="width:200px"></s:select>
    </td>

    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th>Category</th>
    <td>
    <s:select id="ccategoryId" list="productCCategoryList" listKey="categoryId" listValue="name" headerKey="" headerValue="Select Category" value="ccategoryId" onchange="change_category(3)" cssStyle="width:200px"></s:select>
    </td>

    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th>Production Line</th>
    <td>
    <s:select id="pcategoryId" name="productPrice.categoryId" list="productCategoryList" listKey="categoryId" listValue="name" headerKey="" headerValue="Select Category" value="productPrice.categoryId" cssStyle="width:200px"></s:select>
    </td>

    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th valign="top">Retail Price</th>
    <td><input id ="unitPrice" name="productPrice.unitPrice" type="text" class="NFText2" readonly="readonly" value="${productPrice.unitPrice}" size="20" /></td>
    <td>
      <input name="Submit8" type="button" id='Submit3' class="style_botton" value="Modify" onclick="modifyUnitPriceClick('${productPrice.unitPrice}','${productPrice.catalogId}', '${sessionProductId}')"/>

    </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th>Price Limit</th>
    <td><input id="limitPrice" name="productPrice.limitPrice" type="text" class="NFText2" value="${productPrice.limitPrice}" size="20" /></td>
    <td colspan="2">&nbsp;</td>
  </tr>

  <tr>
    <td colspan="4"><div align="center">
      <br />
      <input name="saveType" type="hidden" value="edit" />
      <input name="priceAppr" type="hidden" value="${priceAppr}" />
      <input name="catalogId" type="hidden" value="${productPrice.catalogId}" />
      <input id="userName" name="userName" type="hidden" value="${sessionScope.username}" />
      <input name="sessionProductId" type="hidden" value="${sessionProductId }" />
      <saveButton:saveBtn parameter="${operation_method}"
		disabledBtn='<input type="button" class="style_botton" value="Confirm" disabled="disabled"/>'
		saveBtn='<input type="button" name="editPrice" id="editPrice" class="style_botton" value="Confirm" onclick="editPriceClick()"/>'
	  /> 
      <input type="reset" name="Submit2" value="Cancel" class="style_botton" onclick="parent.$('#productPriceEditDialog').dialog('close');" />
      
      <br/>
    </div></td>
  </tr>
</table>
</form>
</body>
</html>
