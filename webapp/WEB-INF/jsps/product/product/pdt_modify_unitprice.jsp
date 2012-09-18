<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1" type="text/javascript"></script>
<script src="${global_js_url}scm/pdtServ_dialog.js" type="text/javascript"></script>
<script src="${global_js_url}util/util.js"
	type="text/javascript"></script>
<script>
$(document).ready(function(){
	$("#savePriceApprovedTrigger").click(function(){
		var approved = $("#approvedPrice").val();
		var catalogId = $("#catalogId").val();
		var sessionProductId = $("#sessionProductId").val();
		var approvedReason = $("#approvedPriceReason").val();
		var approvedType = $("#approvedPriceType").val();
		$.ajax({
			url:"product/product_pricing!productPriceApprovedSaveSession.action",
			type:"get",
			data:"approved="+approved+"&approvedReason="+approvedReason+"&approvedType="+approvedType+"&sessionProductId="+sessionProductId+"&catalogId="+catalogId,
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
		parent.$("#modifyUnitPriceDialog").dialog("close");
	});
});

function modifyUnitPriceClick(unitPrice) {
	if($("[name='priceAppr']").val() == 'true'){
		alert("The retail price have been modified.");return;
	}
	parent.$('#modifyUnitPriceDialog').find('#approved').val(unitPrice);
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
		//var htmlStr = '<iframe src="category/category/modifyCatNameAct?name='+name+'" height="240" width="570" scrolling="no" style="border:0px" frameborder="0"></iframe>';
		//parent.$('#modifyCatNameDialog').html(htmlStr);
	});
	//alert($('#modifyUnitPriceDialog').attr('id'));
	parent.$('#modifyUnitPriceDialog').dialog('open');
}
</script>
</head>
<body>
<table id="whole_table" width="492" border="0" cellpadding="0" cellspacing="0" class="General_table" style="margin-left:40px;">
			  <tr>
			   <th width="100" align="left">Retail Price</th>
			   <td width="414"><input type="text" size='35' class="NFText" name="approvedPrice" id="approvedPrice" value="${approved}" onkeyup="this.value=this.value.replace(/[^0-9.]/,'');"/></td>
			  </tr>
			  <tr>
	            <th height="24" colspan="2"><div align="left"> Choose the reason to modify the retail price: </div></th>
	          </tr>
	          <tr>
	            <th colspan="2"><div align="left"><textarea name="approvedPriceReason" id="approvedPriceReason" cols="70" rows="2" class="content_textarea" >${approvedReason}</textarea></div></th>
	          </tr>
	          <tr>
	            <th align="right" colspan="2">
				<div align="center" style="margin:10px;">
		            <div id="cat_name" style='display:block;'>
		            	<input type="hidden" name="approvedPriceType" id="approvedPriceType" value="ProductPriceApproved" />
		            	<input type="hidden" name="catalogId" id="catalogId" value="${catalogId}"/>
		            	<input type="hidden" name="sessionProductId" id="sessionProductId"/>
						<input type="button"  class="style_botton" value="Modify" id="savePriceApprovedTrigger"/>	
					  	<input type="button" value="Cancel"  class="style_botton" onclick="parent.$('#modifyUnitPriceDialog').dialog('close');" />
		            </div>
				 </div>
				 </th>
	          </tr>
	</table>
</body>
</html>