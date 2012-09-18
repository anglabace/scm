<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Order Management</title>
		<script type="text/javascript" src="${global_js_url}AJS.js"></script>
		<script type="text/javascript" src="${global_js_url}AJS_fx.js"></script>
		<script language="javascript" type="text/javascript"
			src="${global_js_url}newwindow.js"></script>

		<script language="javascript" type="text/javascript"
			src="${global_js_url}ajax.js"></script>
		<script language="javascript" type="text/javascript"
			src="${global_js_url}tab-view.js"></script>


		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}tab-view.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}gb_styles.css" rel="stylesheet"
			type="text/css" media="all" />
		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
		<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" language="javascript"
			src="${global_js_url}jquery/jquery.js"></script>
		<script
			src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js"
			type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.core.js"
			type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.draggable.js"
			type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.resizable.js"
			type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.dialog.js"
			type="text/javascript"></script>
		<script type="text/javascript">
			var GB_ROOT_DIR = "./greybox/";
			function MM_jumpMenu(targ,selObj,restore){ //v3.0
			  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
			  if (restore) selObj.selectedIndex=0;
			}
		</script>

		<script type="text/javascript"> 
			String.prototype.Trim = function() { 
				return this.replace(/(^\s*)|(\s*$)/g, ""); 
			}  
			 
			String.prototype.LTrim = function() { 
				return this.replace(/(^\s*)/g, ""); 
			}  
			 
			String.prototype.RTrim = function() { 
				return this.replace(/(\s*$)/g, ""); 
			}
			
			var isChecked1=false;
			var isChecked2=false;
			function checkAll(str,frm)
			{
				var prefix = "chk"+str;
				var a = document.getElementsByTagName("input");
				if(str=='1'){
				  if(isChecked1==true){
				     isChecked1=false;
					  for (var i=0; i<a.length; i++)
					      if (a[i].type == "checkbox" && a[i].id.substring(0,4) == prefix) a[i].checked = false;
					   }
					  else{
					   isChecked1=true;
					   for (var i=0; i<a.length; i++)
					      if (a[i].type == "checkbox" && a[i].id.substring(0,4) == prefix) a[i].checked = true;
					  }
				 
					frm.size.readOnly = true;
					frm.quantity.readOnly = true;
					frm.size.value = 'All';
					frm.quantity.value = 'All';
				}
				else if(str=='2'){
				  if(isChecked2==true){
				     isChecked2=false;
				  for (var i=0; i<a.length; i++)
				      if (a[i].type == "checkbox" && a[i].id.substring(0,4) == prefix) a[i].checked = false;
				   }
				  else
				  {
				   isChecked2=true;
				   for (var i=0; i<a.length; i++)
				      if (a[i].type == "checkbox" && a[i].id.substring(0,4) == prefix) a[i].checked = true;
				  }		
				}
			}
			
			function checkListOrderItemsCount(){
				var listOrderItemsCount=document.getElementById("listCount").value;
				//if(listOrderItemsCount==0){
				//	alert("No orderNos");
				//	return false;
				//}
				window.openiframel('shipping!toViewShipment.action?shipmentId=${shipmentId}','840','420','10%');
			}
			
			function toDo(frm,str,url){
				var chks = frm.elements;				
				var ids = new Array();
				var tempQty = new Array();
				var tempSize = new Array();
				var packageIds = "";
				var orderNo="";
				var itemNo="";
				if(str == 'Remove'){
					var lineValues=new Array();
					for(var i=0;i<chks.length;i++){
						var index = chks[i].id.lastIndexOf('_');
						if(chks[i].type=="checkbox" && index > 4 && chks[i].checked){
							var values=chks[i].value.split(",");
							if(values[3]!="Drafted"){
								alert("Include lines are not Drafted");
								return false;
							}
							orderNo+=values[0]+",";
							itemNo+=values[1]+",";
							packageIds+=values[2]+",";
							lineValues[i]=chks[i].value;
						}
					}
					if(lineValues==null||lineValues.length==0){
						alert("Choose one line at least")
						return false;
					}
				}
				if(str == 'Pack'){
					var quantity = frm.quantity;
					var size = frm.size;
					var i = 0;
					var flag = false;
					var a = /^(\d*|\-?[1-9]{1}\d*)$/;
					for(i=0;i<chks.length;i++){
						if(chks[i].type == 'checkbox' && chks[i].checked && chks[i].id.substring(0,4) == 'chk1'){
							if(quantity.value != 'All' && isNaN(quantity.value)){
								alert('Quantity must be a number!');
								quantity.select();
								return;
							}
							if(size.value != 'All' && isNaN(size.value)){
								alert('Size must be a number!');
								size.select();
								return;
							}
							if(quantity.value != 'All' && quantity.value <= 0){
								alert('Quantity must be greater than zero!');
								quantity.select();
								return;
							}
							if(size.value != 'All' && size.value <= 0){
								alert('Size must be greater than zero!');
								size.select();
								return;
							}
							if(quantity.value != 'All' && !quantity.value.match(a)){
								alert("Quantity must be Integer!");
								quantity.select();
								return false;
							}
							var str = chks[i].value.split(',');
							if(parseInt(quantity.value) != 'All' && parseInt(str[2]) < parseInt(quantity.value)){
								alert('Quantity too big!');
								quantity.focus();
								return;
							}
							if(size.value != 'All' && parseInt(str[3]) < parseInt(size.value)){
								alert('Size too big!');
								size.focus();
								return;
							}
							ids.push(str[0]);
							tempQty.push(str[2]);
							tempSize.push(str[3]);
							flag = true;
						}
					}
					if(flag == false){
						alert('Please choose a record!');
						return;
					}
					var count = 0;
					for(var i=0;i<chks.length;i++){
						if(chks[i].type == 'checkbox' && chks[i].id.lastIndexOf('_') == 4 
							&& chks[i].id.substring(0,4) == 'chk2' && chks[i].checked){
							var packageIdTemp = chks[i].value.split(',');
							packageIds = packageIdTemp[0];
							if(packageIdTemp[1] != 'Drafted'){
								alert('Please choose the Drafted package');
								return;
							}
							count++;
						}
					}
					if(count != 1){
						alert('Please choose a package');
						return;
					}
				}
				var packAll = '0';
				if(str == 'PackAll'){
					if(${warehouseType == 'SALES'}){
						for(var i=0;i<chks.length;i++){
							if(chks[i].type == 'checkbox' && chks[i].id.lastIndexOf('_') == 4 
								&& chks[i].id.substring(0,4) == 'chk2'){
								var packageIdTemp = chks[i].value.split(',');
								packageIds = packageIdTemp[0];
								if(packageIdTemp[1] != 'Drafted'){
									alert('Can not packAll the '+packageIdTemp[1]+' records');
									return;
								}
							}
						}
						packAll = '1';
					}else{
						var countPackage = 0;
						for(var i=0;i<chks.length;i++){
							if(chks[i].type == 'checkbox' && chks[i].id.lastIndexOf('_') == 4 && chks[i].checked
								&& chks[i].id.substring(0,4) == 'chk2'){
								var packageIdTemp = chks[i].value.split(',');
								packageIds = packageIdTemp[0];
								if(packageIdTemp[1] != 'Drafted'){
									alert('Can not packAll the '+packageIdTemp[1]+' records');
									return;
								}
								countPackage++;
							}
						}
						if(countPackage == 0){
							alert('please choose a package');
							return;
						}
					}
					for(var i=0;i<chks.length;i++){
						if(chks[i].type == 'checkbox' && chks[i].id.substring(0,4) == 'chk1'){
							//chks[i].checked = true;
							var str = chks[i].value.split(',');
							ids.push(str[0]);
							tempQty.push(str[2]);
							tempSize.push(str[3]);
						}
					}
				}
				if(str == 'UndoAll'){
					for(var i=0;i<chks.length;i++){
						if(chks[i].type == 'checkbox' && chks[i].id.lastIndexOf('_') == 4
							&& chks[i].id.substring(0,4) == 'chk2'){
							var packageIdTemp = chks[i].value.split(',');
							packageIds = packageIdTemp[0];
							if(packageIdTemp[1] != 'Drafted'){
								alert('Can not UndoAll the '+packageIdTemp[1]+' records');
								return;
							}
						}
					}
				}
				frm.action = url+'?shipmentId=${shipmentId}&chkRid='+ids+'&qtys='+tempQty+'&sizes='+tempSize+'&packageIds='+packageIds
					+'&itemNo='+itemNo+'&orderNo='+orderNo+'&packAll='+packAll;
				frm.submit();
			}
			
			function checkPackage(frm,chkItem,str){
				var chks = frm.elements;
				for(var i=0;i<chks.length;i++){
					if(chks[i].type == 'checkbox'){
						var index = chks[i].id.lastIndexOf('_');
						if(chks[i].id.substring(0,index) == str)
							chks[i].checked = chkItem.checked;
					}
				}
			}
			function checkSize(frm,chk,type){
				var chks = frm.elements;
				var flag = true;
				var j=0;
				for(i=0;i<chks.length;i++){
					if(chks[i].type == 'checkbox' && chks[i].checked && chks[i].id.substring(0,4) == 'chk1'){
						j++;
					}
				}
				if(j>1){
					flag = false;
				}
				if(j==0){
					flag=false;
				}
				if(flag == true){
					
					if(type=="SERVICE"){
						frm.size.readOnly = false;
						frm.quantity.readOnly = true;
					}
					if(type=="PRODUCT"){
						frm.size.readOnly = true;
						frm.quantity.readOnly = false;
					}
					
				}
				else{
					frm.size.readOnly = true;
					frm.quantity.readOnly = true;
				}
				frm.size.value = 'All';
				frm.quantity.value = 'All';
			}
			
			function delPackage(frm){
				var chks = frm.elements;
				var flag = false;
				var packageIds = new Array();
				for(var i=0;i<chks.length;i++){
					var index = chks[i].id.lastIndexOf('_');
					if(chks[i].type == 'checkbox' && chks[i].checked && index == 4 && chks[i].id.substring(0,4) == 'chk2'){
						var str = chks[i].value.split(',');
						if(str[1] != 'Drafted'){
							alert('Can not delete the '+str[1]+' package');
							return;
						}
						flag = true;
						packageIds.push(str[0]);
					}
				}
				if(flag == false){
					alert('please choose a package');
					return;
				}
				var count = 0;
				for(var i=0;i<chks.length;i++){
					var index = chks[i].id.lastIndexOf('_');
					if(chks[i].type == 'checkbox' && chks[i].checked && index > 4 && chks[i].id.substring(0,4) == 'chk2'){
						count++;
					}
				}
				if(count > 0){
					alert('The package(s) you choose contains line(s)!');
					return;
				}
				frm.action = 'shipping!deletePackage.action?shipmentId=${shipmentId}&packageIds='+packageIds;
				frm.submit();
			}
			$(function() {            
            	$('#print_shipping_label').dialog({
					autoOpen: false,
					height: 400,
					width: 700,
					modal: true,
					bgiframe: true,
					closeText:false,
					buttons: {
					}
				});
            	$('a.ui-dialog-titlebar-close').hide();   
				
            });

			function openPrintShippingLabelDlgs() {
				$('#print_shipping_label').dialog("option", "open", function() {
			         var htmlStr = '<iframe src="shipping!printShippingLabel.action?shipmentId=${shipmentId}" height="350" width="680" style="border:0px" frameborder="0"></iframe>';
			         $('#print_shipping_label').html(htmlStr);
				});
				$('#print_shipping_label').dialog('open');
			}
			
		 	$(function() {            
            	$('#package_information').dialog({
					autoOpen: false,
					height: 850,
					width: 950,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            });
            
			function openPackage_information(packageId,count) {
				var prefix = "chkItem";
				var a = document.getElementsByTagName("input");
				var counts = 0;
				for(var i=0; i<a.length; i++){
					if(a[i].type == "checkbox" && a[i].name.substring(0,7) == prefix){
						counts++;
					}
				}
				$('#package_information').dialog("option", "open", function() {	
				var htmlStr = '<iframe src="shipping!packageInformation.action?counts='+counts+'&count='+count+'&shipmentId=${shipmentId}&packageId='+packageId+'" height="800" width="900" scrolling="no" style="border:0px" frameborder="0"></iframe>';
				$('#package_information').html(htmlStr);
				});
				$('#package_information').dialog('open');
			}

			$(function() {            
            	$('#shipping_document').dialog({
					autoOpen: false,
					height: 350,
					width: 500,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            });
            
			function view_shipping_doc(frm) {
				var chks = frm.elements;
				var orderNos = "";
				var itemNos = "";
				for(var i=0;i<chks.length;i++){
					var index = chks[i].id.lastIndexOf('_');
					if(chks[i].type=="checkbox" && index > 4 && chks[i].checked){
						var values=chks[i].value.split(",");
						var orderNo = values[0];
						if(orderNos==""){
							orderNos+=orderNo;
						}else{
							orderNos+=","+orderNo;
						}
						var itemNo = values[1];
						if(itemNos==""){
							itemNos+=itemNo;
						}else{
							itemNos+=","+itemNo;
						}
					}
				}
				if(orderNos==""){
					alert("Please choose a Order Item.");
					return ;
				}
				$('#shipping_document').dialog("option", "open", function() {	
				var htmlStr = '<iframe src="shipping!shippingDocument.action?orderNo='+orderNos+'&itemNo='+itemNos+'" height="300" width="450" scrolling="no" style="border:0px" frameborder="0"></iframe>';
				$('#shipping_document').html(htmlStr);
				});
				$('#shipping_document').dialog('open');
			}

			$(function() {            
            	$('#picks_items_label').dialog({
					autoOpen: false,
					height: 450,
					width: 880,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            });
            
			function pick_item(frm) {
				var chks = frm.elements;
				var packages = ""
				for(var i=0;i<chks.length;i++){
					var index = chks[i].id.lastIndexOf('_');
					if(chks[i].type=="checkbox" && index==4 && chks[i].checked){
						
						var values=chks[i].value.split(",");
						
						if(packages==""){
							packages = values[0];
						}else{
							packages+=","+values[0];
						}
						
					}
				}	
				
				$('#picks_items_label').dialog("option", "open", function() {	
				var htmlStr = '<iframe src="shipping!picksItems.action?shipmentId=${shipmentId}&packageIds='+packages+'" height="400" width="834" scrolling="no" style="border:0px" frameborder="0"></iframe>';
				$('#picks_items_label').html(htmlStr);
				});
				$('#picks_items_label').dialog('open');
			}
			
			$(function() {            
            	$('#vendor_search_dlg').dialog({
					autoOpen: false,
					height: 690,
					width: 784,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            });
            
			function openSearchDlg() {
				$('#vendor_search_dlg').dialog("option", "open", function() {	
					var htmlStr = '<iframe src="shipping!viewPackingSlip.action?shipmentId=${shipmentId}" height="630" width="734" scrolling="yes" style="border:0px" frameborder="0"></iframe>';
					$('#vendor_search_dlg').html(htmlStr);
				});
				$('#vendor_search_dlg').dialog('open');
			}
			
			$(function() {            
				$('#vendor_search_dlgs').dialog({
					autoOpen: false,
					height: 460,
					width: 774,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
				
			});
			
			function openSearchDlgs() {
				$('#vendor_search_dlgs').dialog("option", "open", function() {	
					var htmlStr = '<iframe src="shipping!printPickList.action?shipmentId=${shipmentId}" height="410" width="734" scrolling="yes" style="border:0px" frameborder="0"></iframe>';
					$('#vendor_search_dlgs').html(htmlStr);
				});
				$('#vendor_search_dlgs').dialog('open');
			}
			
			function message(msg){
				if(msg != null && msg != ''){
					alert(msg);
				}
			}	

			function oncleckPrint(url,type){
				
				//window.location.href=url;
				$("#print2Form").attr("action",url);
				$("#print2Form").submit();
				
				if(type=='Picked'){
					$("#Submit194").removeAttr("disabled"); 
				}
				if(type=='Packed'){
					$("#Submit194").removeAttr("disabled"); 
					$("#Submit195").removeAttr("disabled"); 
					$('#Submit195').attr("class", "search_input2");
				}
				
				$("#subTable").find("td[name='statustd']").each( function(i){
					  var status = $(this).text();
					  status = $.trim(status);
					  if((status=='Drafted'&&type=='Picked')||(status=='Picked'&&type=='Packed')){
						  $(this).text(type);
					  }
			          
			    })
			}

			function onclerkPrintNew(){
					
			}

			function oncleckPrintService(url,type){
				//window.location.href=url;
				$("#print2Form").attr("action",url);
				$("#print2Form").submit();
				if(type=='Picked'){
					$("#Submit194").removeAttr("disabled"); 
				}
				if(type=='Packed'){
					$("#Submit194").removeAttr("disabled"); 
					$("#Submit195").removeAttr("disabled"); 
					$('#Submit195').attr("class", "search_input2");
				}
				$("#subTable").find("td[name='statustd']").each( function(i){
					  var status = $(this).text();
					  status = $.trim(status);
					  if((status=='Drafted'&&type=='Picked')||(status=='Picked'&&type=='Packed')){
						  $(this).text(type);
					  }
			          
			    })
			}	

			function printPickList(url1,type){
				var url = url1;
				$.ajax({
					type:"POST",
					url:url,
					dataType:"json",
					success: function(msg){
						$("#subTable").find("td[name='statustd']").each( function(i){
			            	$(this).text(type);
			      		})
						//oncleckPrint('shipping!printPick.action?shipmentId=${shipmentId}','Picked');
						//window.location.href='shipping!printPick.action?shipmentId=${shipmentId}';
						//oncleckPrint('shipping!printPickListPdf.action?shipmentId=${shipmentId}','Picked');
					},
					error: function(msg){
						alert("System error! Please contact system administrator for help.");
					}
				});
				
			}	

            var noteUrl = "";

			function show_edit(noteKey, searchNoteType){
				var params = '?noteKey='+noteKey + '&searchNoteType=' + searchNoteType;
                noteUrl = "order_note/order_note!viewOrderNote.action";
				$('#instruction_view_dlg').dialog("option","params", params);
				$('#instruction_view_dlg').dialog('open'); 
			}

            function show_note_other(noteKey, searchNoteType){
				var params = '?noteKey='+noteKey + '&searchNoteType=' + searchNoteType;
                noteUrl = "shipping!viewOrderNote.action";
				$('#instruction_view_dlg').dialog("option","params", params);
				$('#instruction_view_dlg').dialog('open');
			}

			$(document).ready(function(){
				$('#instruction_view_dlg').dialog({
					autoOpen: false,
					height: 400,
					width: 600,
					modal: true,
					bgiframe: true,
					buttons: {
					},
					open:function(){
						var params = $('#instruction_view_dlg').dialog("option", "params");
						var url = noteUrl + params;
				        var htmlStr = '<iframe src="'+url+'" height="350" width="570" scrolling="no" style="border:0px;" frameborder="0"></iframe>';
						$('#instruction_view_dlg').html(htmlStr);
					}
				});
			});
        //显示更新后的weight
        function showNewWeight(packNo, weight){
            $("#weight" + packNo).text(weight);
        }
		</script>
		<style>
.button_style {
	font: 12px/ 18px "Lucida Grande", Arial, sans-serif;
	color: #FFFFFF;
	margin: 1px;
	padding: 0px;
	height: 25px;
	width: 120px;
	border-style: none;
	cursor: pointer;
	background: url(${global_image_url}input_searchbg.jpg) no-repeat 0px 3px
		;
}
</style>
	</head>
	<body class="content" style="background-image: none;" onload="message('${message}')">
		 <c:if test="${ (empty listOrderItem || listOrderItem ==null)&&(empty listPackage || listPackage ==null)}">
		 	<script type="text/javascript"> 
		 	
		 	window.location.href="shipping!appFrame.action";
		 	</script>
		 </c:if>
		<form action="" name="shippingForm" method="post">
			<input type="hidden" name="packageCount" id="packageCount"
				value="${packageCount }" />
			<input type="hidden" name="listCount" id="listCount"
				value="${fn:length(listOrderItems)}" />
			<div id="frame12" style="display: none;" class="hidlayer1">
				<iframe id="hidkuan" name="hidkuan" src="#" frameborder="0"
					height="425" width="668"></iframe>
			</div>
			<div class="scm">
				<div class="title_content">
					<div class="title">
						Shipping Workstation
					</div>
				</div>
				<div class="input_box">

					<table border="0" cellpadding="0" cellspacing="0" width="1010">
						<tbody>
							<tr>
								<td style="padding-left: 10px;" background="images/shipbg1.jpg"
									valign="top" width="430">
									<div class="ship_title">
										Items to Pack
									</div>
									<div class="list_box"
										style="height: 282px; background-color: rgb(255, 255, 255); width: 405px;">
										<table class="list_table" id="table1" border="0"
											cellpadding="0" cellspacing="0" width="450">
											<tbody>
												<tr>
													<th width="30">
														<input id="packing1" name="packing1" value="checkbox"
															type="checkbox" onclick="javascript:checkAll('1',shippingForm);" <c:if test="${fn:length(listOrderItem)<2}">disabled="disabled"</c:if>/>
															
													</th>
													<th width="50">
														Order No
													</th>

													<th width="54">
														Item No
													</th>
													<th width="62">
														Catalog No
													</th>
													<th width="80">
														Name
													</th>
													<th width="44">
														Status
													</th>
													<th width="40">
														Qty
													</th>
													<th>
														Size
													</th>
												</tr>
												<c:forEach var="item" items="${listOrderItem}">
													<c:if test="${rowcount mod 2 == 0}">
														<c:set var="tdclass" value=" class='list_td2'"></c:set>
													</c:if>
													<c:if test="${rowcount mod 2 == 1}">
														<c:set var="tdclass" value=""></c:set>
													</c:if>
													<tr>
														<td align="center"${tdclass}>
															<input id="chk1_${item.sort }"
																name="packing${item.sort }" type="checkbox"
																onclick="checkSize(shippingForm,this,'${item.type}')"
																value="${item.reservationId },${item.orderItemQty },${item.quantity },${item.size }" />
														</td>
														<td align="center"${tdclass}>
														
															<a href="order/order!edit.action?orderNo=${item.orderNo }&operation_method=view" target="_blank">${item.orderNo }</a>
														</td>
														<td align="center"${tdclass}>
															${item.itemNo }
														</td>
														<td align="center"${tdclass}>
															${item.catalogNo }
														</td>
														<td align="center"${tdclass}>
														<c:if test="${fn:length(item.itemName)>10}">
															${fn:substring(item.itemName,0,10)}
														</c:if>
														<c:if test="${fn:length(item.itemName)<=10}">
															${item.itemName}
														</c:if>
														...
														</td>
														<td align="center"${tdclass}>
															${item.itemStatus }
														</td>
														<td align="center"${tdclass}>
															${item.quantity }
														</td>
														<td align="center"${tdclass}>
															${item.size }
														</td>
													</tr>
													<c:set var="rowcount" value="${rowcount+1}"></c:set>
												</c:forEach>
											</tbody>
										</table>
									</div>
									<c:if test="${warehouseType eq 'MANUFACTURING'}">
										<div style="padding-top: 10px;" align="center">
											<input name="Submit142" class="style_botton2"
												value="New Item"
												onclick="window.openiframe('shipping!toAddNewItems.action?shipmentId=${shipmentId}','600','314')"
												type="button" />
										</div>
									</c:if>
								</td>
								<td class="ship_all" valign="top" width="100">
									&nbsp;Quantity to Pack
									<br />
									<input name="quantity" id="quantity" value="All" size="7"
										maxlength="5" type="text" readonly="readonly" />
									<br />
									&nbsp;Size to Pack
									<br />
									<input name="size" id="size" value="All" size="7" maxlength="5"
										readonly="readonly" type="text" />
									<input name=""
											class="style_botton2" value="&gt; Pack" type="button"
											onclick="toDo(shippingForm,'Pack','shipping!pack.action')" />
										<input name="" class="style_botton2" value="&lt; Remove"
											type="button"
											onclick="toDo(shippingForm,'Remove','shipping!remove.action')" />
										<input name="" class="style_botton2" value="&gt;&gt; Pack All"
											type="button"
											onclick="toDo(shippingForm,'PackAll','shipping!pack.action')" />
										<input name="" class="style_botton2" value="&lt;&lt; Undo All"
											type="button"
											onclick="toDo(shippingForm,'UndoAll','shipping!unDoAll.action')" />
										<c:if test="${greenAccFlag=='Y'||greenAccFlag=='y'}">
											<div style="padding-top:50px; padding-left:0px;"><img src="images/green_icon_ship.jpg"  /></div>
										</c:if>
										
										<!--<div style="padding-top: 40px; padding-left: 0px;">
											 search_input_ship 
											<input name="Submit2" type="button" class="search_input_ship_a"
												value="View Shipment
Notes"
												onclick="checkListOrderItemsCount();" />
										</div> -->
								</td>

								<td style="padding-left: 15px;" background="images/shipbg2.jpg"
									height="350" valign="top" width="480">


									<div class="ship_title">
										Packed Boxes
									</div>

									<table class="list_table" border="0" cellpadding="0"
										cellspacing="0" width="438">
										<tbody>
											<tr>
												<th width="50">
													<div align="left">
														<input name="checkbox2"
															onclick="javascript:checkAll('2',shippingForm);" type="checkbox" />
														<a onclick="delPackage(shippingForm)"
															title="Delete Invoice Line"
															rel="gb_page_center[600,  180]"> <img
																src="images/file_delete.gif" style="cursor: hand"
																alt="Delete" border="0" height="16" width="16" /> </a>
													</div>
												</th>
												<th width="60">
													Pkg No
												</th>
												<th width="70">
													Ship Via
												</th>
												<th width="40">
													Status
												</th>
												<th width="50">
													Weight
												</th>
												<th>
													Ship To
												</th>
											</tr>
										</tbody>
									</table>
									<div class="list_box"
										style="width: 455px; height: 254px; background-color: rgb(255, 255, 255);">
										<table class="list_table2" name="subTable" id="subTable" border="0"
											cellpadding="0" cellspacing="0" width="438">
											<tbody>
												<c:set var="packageRow" value="0"></c:set>
												<c:set var="isCheck" value="0"></c:set>
												<c:forEach items="${listPackage}" var="package" varStatus="status">
													<c:if test="${rowcount mod 2 == 0}">
														<c:set var="tdclass" value=" class='list_td2'"></c:set>
													</c:if>
													<c:if test="${package.packageStatus=='Drafted'&&isCheck ==0}">
														<c:set var="isCheck" value="1"></c:set>
													</c:if>
													<c:if test="${rowcount mod 2 == 1}">
														<c:set var="tdclass" value=""></c:set>
													</c:if>
													<tr>
														<td width="50" align="center"${tdclass}>
															<!-- <input id="chk2_${packageRow }" name="chkItem" value="${package.packageNo }" type="checkbox" /> -->
															<input id="chk2_${packageRow }"
																name="chkItem_${packageRow }"
																value="${package.packageNo },${package.packageStatus }"
																type="checkbox"  
																onclick="checkPackage(shippingForm,this,'chk2_${packageRow }')" 
																<c:if test="${isCheck==1}">checked="checked"<c:set var="isCheck" value="2"></c:set></c:if>/>
														</td>
														<td width="60" ${tdclass}>
															
															<a href="javascript:void(0);"
																onclick="openPackage_information(${package.packageNo },${status.count});"><img src="images/box.gif" />${package.packageNo}</a>
														</td>
														<td width="70" align="center" ${tdclass}>
															<c:if test="${fn:substring(package.shipVia,0,5)=='FedEx'}">${package.shipVia }</c:if>
															<c:if test="${fn:substring(package.shipVia,0,5)!='FedEx'}"><font color="blue" style="font-weight: bold">${package.shipVia }</font></c:if>
														</td>
														<td width="40" align="center" ${tdclass}  name="statustd">
															${package.packageStatus }
														</td>
														<td width="50" align="center" ${tdclass}>
															<div align="center" id="weight${package.packageNo}">
																${package.actualWeight }
															</div>
														</td>
														<td ${tdclass}>
															${package.rcpFirstName }&nbsp;${package.rcpLastName }&nbsp;,${package.rcpCountry }
														</td>
														<c:set var="lineRow" value="0"></c:set>
													</tr>
													<c:forEach items="${package.listPackageLine}" var="line">
														<tr>
															<td width="50" align="right">
																<input id="chk2_${packageRow }_${lineRow }"
																	name="chkSubItem_${lineRow }"
																	value="${line.orderNo },${line.itemNo },${line.packageId },${line.status }"
																	type="checkbox" />
															</td>
															<td colspan="5" width="200">
																<img src="images/box1.gif" />
																${line.lineStr }
																<c:if test="${not empty line.temperature}">,${line.temperature }<img src="images/sheshi.jpg"/>
																</c:if>
															</td>
														</tr>
														<c:set var="lineRow" value="${lineRow+1}"></c:set>
													</c:forEach>
													<c:set var="packageRow" value="${packageRow+1}"></c:set>
												</c:forEach>
											</tbody>
										</table>
									</div>

									<div style="padding-top: 10px;" align="center">
										<input name="Submit142" class="style_botton2"
											value="New Package" type="button"
											onclick="shippingForm.action='shipping!addPackage.action?shipmentId=${shipmentId}';shippingForm.submit();" />
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</form>
         		<p></p>
			 		<strong>Shipment Note:</strong>
			    <p></p>

         <div class="list_box" style="height:100px;background-color:#FFF;width:425px;font-size:12px;font-weight:bold;color:red;">
             Order:
             <s:iterator value="notesMap['orderNotes']" id="note">
                 <a href="javascript:void(0)" onclick="show_edit('${note.noteIds}', 'SHIPMENT')"><font color="red">${note.orderNote}</font></a>;
             </s:iterator>
             <br/>
             Customer:
             <s:iterator value="notesMap['custNote']" id="note">
                 <a href="javascript:void(0)" onclick="show_note_other('${note.id}', 'CUSTOMER')"><font color="red">${note.description}</font></a>;
             </s:iterator>
             <br/>
             Department:
             <s:if test="notesMap['deptNote'] != null">
             <s:iterator value="notesMap['deptNote']" id="note">
                 <a href="javascript:void(0)" onclick="show_note_other('${note[0]}', 'DEPT')"><font color="red">${note[1]}</font></a>;
             </s:iterator>
             </s:if>
             <br/>
             Division:
             <s:if test="notesMap['diviNote'] != null">
             <s:iterator value="notesMap['diviNote']" id="note">
                 <a href="javascript:void(0)" onclick="show_note_other('${note[0]}', 'DIVISION')"><font color="red">${note[1]}</font></a>;
             </s:iterator>
             </s:if>
             <br/>
             Organization:
             <s:if test="notesMap['orgNote'] != null">
             <s:iterator value="notesMap['orgNote']" id="note">
                 <a href="javascript:void(0)" onclick="show_note_other('${note[0]}', 'ORG')"><font color="red">${note[1]}</font></a>;
             </s:iterator>
             </s:if>
             <br/>
         </div>
		
		<div class="button_box">
			<br />
			
			<input type="button"  id="Submit193" name="Submit193" value="Print Pick List"    class="search_input2" onclick="pick_item(shippingForm);" />
			 
			 <c:if test="${statusPicked==null }">
			 	<input type="button" id="Submit194" name="Submit194" value="Print Packing Slip" class="search_input2_dis" disabled="disabled"/>
			 </c:if>
			 <c:if test="${statusPicked!=null }">
			 	<input type="button" id="Submit194" name="Submit194" value="Print Packing Slip" class="search_input2" onclick="oncleckPrint('shipping!viewPackingSlip.action?shipmentId=${shipmentId}','Packed');" />
			 </c:if>
			  <!--<input type="button" id="Submit194" name="Submit194" value="Service Packing Slip" class="search_input4" onclick="oncleckPrintService('shipping!viewPackingSlip.action?shipmentId=${shipmentId}','Packed');" />
			  --><input id="Submit3" name="Submit3" type="submit" class="search_input4" value="View/Print Product Document" onclick="view_shipping_doc(shippingForm)"/>
			 <c:if test="${statusPacked==null }">
			 	<input type="button" id="Submit195" name="Submit195" value="Print Shipping Label" class="search_input2_dis" onclick="openPrintShippingLabelDlgs();"  disabled="disabled"/>
			 </c:if>
			 <c:if test="${statusPacked!=null }">
			 	<input type="button" id="Submit195" name="Submit195" value="Print Shipping Label" class="search_input2" onclick="openPrintShippingLabelDlgs();"  />
			 </c:if>
			
			<br />
			<input class="search_input" value="Save" type="button"
				onclick="javascript:window.location.href='shipping!saveShipping.action?shipmentId=${shipmentId}';" />
			<input class="search_input"
				onclick="shippingForm.action='shipping!reset.action?shipmentId=${shipmentId}';shippingForm.submit();"
				value="Cancel" type="button" />
				<form id="print2Form" method="post" action="">

				</form>
		</div>
		<div id="print_shipping_label" title=" Print Shipping Label "
			style="visible: hidden" />
		<div id="package_information" title=" Package information "
			style="visible: hidden" />
		<div id="shipping_document" title=" Shipping Document "
			style="visible: hidden" />
		<div id="picks_items_label" title=" Pick items "
			style="visible: hidden" />
		<div align="center" id="vendor_search_dlg" style="visible: hidden;"></div>
		<div align="center" id="vendor_search_dlgs" style="visible: hidden;"></div>
		<div id="instruction_view_dlg" title="Instruction/Note"> </div> 
		
		
	</body>
</html>
