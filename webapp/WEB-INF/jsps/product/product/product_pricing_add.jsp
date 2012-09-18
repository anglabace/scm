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
<script src="${global_js_url}jquery/jquery.validate.js?v=1" type="text/javascript"></script>
<script>
$(document).ready(function(){
	// validate signup form on keyup and submit
	$("#addProductPriceForm").validate({
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
  			"productPrice.unitPrice":{required:true,number:true}
  			
		},
		messages: {
			"productPrice.catalogId": { required: "Please select one catalog to continue your operation."},
			"productPrice.currencyCode": { required: "Please enter the currency code to continue your operation."},
			"productPrice.categoryId": { required: "Please select one category to continue your operation."},
			"productPrice.unitPrice":{ required:"Please enter unit price ",number: "This 'Retail Price' must be a digit!"}
		},
		errorPlacement: function(error, element) {}	
	});
	
	$("#savePriceApprovedTrigger").click(function(){
		var approved = $("#approved").val();
		var approvedReason = $("#approvedReason").val();
		var approvedType = $("#approvedType").val();
		$.ajax({
			url:"product/product_pricing!productPriceApprovedSaveSession.action",
			type:"get",
			data:"approved="+approved+"&approvedReason="+approvedReason+"&approvedType="+approvedType+"&sessionProductId="+sessionProductId,
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

function change_category(categoryLevel){
	var catalogId = $("#catalogId option:selected").val();
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

function change_catalog(obj){
	var catalogId = obj.options[obj.selectedIndex].value;
	var categorySlt = document.getElementById('categoryId');
	var reqUrl = "${ctx}/product/product_pricing!getCategoryByCatalog.action?catalogId="+catalogId;
	//alert (reqUrl);
	$.ajax({
		type: "POST",
		url: reqUrl,
		success: function(data, textStatus){
			if(data){
				categorySlt.options.length = 0;
				categorySlt.options.add(new Option('',''));
				var str = data.split("$");
				var currencyCode = str[1];
				//alert(currencyCode);
				$('#currencyCode').val(currencyCode);
				var segment = str[0].split("#");
				if (segment.length > 1){
					for(i = 0; i < segment.length-1; i++){
						var part = segment[i].split("|");
						var uId = part[0];
						var uName = part[1];
						categorySlt.options.add(new Option(uName,uId));
					}

				}

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

function addPriceClick(){
	if($('#addProductPriceForm').valid() == false){
		return false;
	}
	if($("#categoryId option:selected").val() == ""){
		alert("Please select one item to continue your operation.");
		return false;
	}
	
	var errorClass="validate_error";
	var formStr = $('#addProductPriceForm').serialize();
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
            //htmlStr +='<td width="141">&nbsp;<a href="javascript:void(0);" onclick="editProductPrice(\''+catalogId+'\', \''+categoryId+'\', \''+unitPrice+'\', \''+limitPrice+'\', \''+currencyCode+'\');" title="Edit Product Price" rel="gb_page_center[580,200]">'+catalogId+'</a>';              
          	htmlStr +='<td width="141">&nbsp;'+catalogId;              
          	htmlStr +='<input type="hidden" value="" id="'+data.id+'_supplierInfo" /></td>';           
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
		    parent.$('#productPriceAddDialog').dialog('close');
			parent.$('#productPriceAddDialog').dialog('destory');
			//alert("The "+pdtServType+" is saved successfully!");
			//isSaved = true;
			//location.href = "product/product!input.action?type="+data.type+"&id="+data.id+"&defaultTab="+defaultTab;
		}
	},
	error: function(xhr, textStatus){
		alert("Failed to access the  web server. Please contact system administrator for help.!");
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
</script>
</head>

<body style="padding-top:20px;">
<form id="addProductPriceForm">
<table border="0" align="center" cellpadding="0" cellspacing="0" class="General_table">
  <tr>
    <th width="100">Price Catalog</th>

    <td>
    	<s:select id="catalogId" name="productPrice.catalogId" list="retCatalogList" listKey="catalogId" listValue="catalogId" headerKey="" headerValue="Select Catalog" onchange="change_catalog(this)"></s:select>
    </td>
    <th width="100">Currency</th>
    <td><input id="currencyCode" name="productPrice.currencyCode" type="text" class="NFText" size="20" readonly="readonly" value="${productPrice.currencyCode}"/></td>
  </tr>
  <tr>
    <th>Business Unit</th>
    <td>
    <select id="categoryId" onchange="change_category(2)" style="width:200px"></select>
    </td>

    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
   <tr>
    <th>Category</th>
    <td>
    <select id="ccategoryId" onchange="change_category(3)" style="width:200px"></select>
    </td>

    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
   <tr>
    <th>Production Line</th>
    <td>
    <select id="pcategoryId" name="productPrice.categoryId" style="width:200px"></select>
    </td>

    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th valign="top">Retail Price</th>
    <td><input id ="unitPrice" name="productPrice.unitPrice" type="text" class="NFText2" size="20" /></td>
    
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th>Price Limit</th>
    <td><input id="limitPrice" name="productPrice.limitPrice" type="text" class="NFText2" size="20" /></td>
    <td colspan="2">&nbsp;</td>
  </tr>

  <tr>
    <td colspan="4"><div align="center">
      <br />
      <input name="saveType" type="hidden" value="add" />
      <input id="userName" name="userName" type="hidden" value="${sessionScope.username}" />
      <input name="sessionProductId" type="hidden" value="${sessionProductId }" />
      <input type="button" id="addPrice" name="addPrice"  class="style_botton" value="Confirm" onclick="addPriceClick()"/>
      <input type="reset" name="Submit2" value="Cancel" class="style_botton" onclick="parent.$('#productPriceAddDialog').dialog('close');" />
      
      <br/>
    </div></td>
  </tr>
</table>
</form>
</body>
</html>
