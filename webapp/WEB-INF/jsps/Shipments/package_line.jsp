<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp"%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Order Management</title>
		<link href="stylesheet/scm.css" rel="stylesheet" type="text/css" />
		<link href="stylesheet/table.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
		<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
		<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
		<link href="stylesheet/tab-view.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
		<link href="stylesheet/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
        	var GB_ROOT_DIR = "./greybox/";
    	</script>
		<script type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
		<script type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
		<script type="text/javascript" src="${global_js_url}greybox/gb_scripts.js"></script>
		<link href="${global_js_url}greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
		
		<script type="text/javascript">
		function Len(str)
		{
		     var i,sum;
		     sum=0;
		     for(i=0;i<str.length;i++)
		     {
		         if ((str.charCodeAt(i)>=0) && (str.charCodeAt(i)<=255))
		             sum=sum+1;
		         else
		             sum=sum+2;
		     }
		     return sum;
		}
		function checkLen(){
		   desc = document.getElementById('description').value;
		   if(Len(desc)>255)
		   {
		    alert("Description length too long!");
		    return false;
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
		function onclick1()
		{
		  document.cookie="work_o_Antibody";
		  window.location.href="work_order_trans.html";
		}
			
			
		</script>

		<style type="text/css">
			<!--
			.content .scm #dhtmlgoodies_tabView1 .dhtmlgoodies_aTab .General_table fieldset
				{
				margin: 4px;
			}
			
			.disp {
				display: none;
				margin-left: 40px;
			}
			-->
		</style>
	</head>

	<body class="content">
		<div class="scm" style="overflow:auto; width: 1100PX">
			<div class="title_content">
				<div class="title">
					Shipment Line #${slDto.lineId }
				</div>
			</div>
			<div class="input_box">
				<div class="content_box">
					<form enctype="multipart/form-data" class="niceform" name="shipmentForm" action="shipments!updateShipmentLine.action" method="post">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									<table border="0" cellpadding="0" cellspacing="0"
										class="General_table">
										<tr>
											<th>
												Shipment Line No
											</th>
											<td colspan="2">
												<input name="lineId" type="text" class="NFText" readonly="readonly"
													value="${slDto.lineId }" size="25" />
											</td>
											<th>
												Status
											</th>

											<td>
												<select name="select" style="width: 157px;" id="select2" disabled="disabled" >
													<option>
														${slDto.status }
													</option>
												</select>
											</td>
										</tr>
										<tr>
											<th width="138">
												Shipment No
											</th>
											<td colspan="2">
												<input name="shipmentNo" type="text" class="NFText" readonly="readonly"
													value="${slDto.shipments.shipmentNo }" size="25" />
											</td>
											<th width="120">
												&nbsp;
											</th>

											<td width="195">
												&nbsp;
											</td>
										</tr>
										<tr>
											<th valign="top">
												Order No
											</th>
											<td width="148">
												<input name="orderNo" type="text" class="NFText" readonly="readonly"
													value="${slDto.order.orderNo }" size="25" />
											</td>
											<td width="264">
												<input name="Submit142" class="style_botton2"
												value="View Order"
												onclick="javascript:window.open('order/order!edit.action?orderNo=${slDto.order.orderNo }&operation_method=view');"
												type="button" />	
											</td>
											<th>
												Item No
											</th>
											<td>
												<input name="itemNo" type="text" class="NFText" readonly="readonly"
													value="${slDto.itemNo }" size="25" />
											</td>
										</tr>
										<tr>
											<th valign="top">
												Product/Service
											</th>
											<td colspan="2">
												<input name="itemName" type="text" class="NFText" size="60" readonly="readonly"
													value="${productService}" />
											</td>
											<th>
												Shipment Method
											</th>
											<td>
												<input name="shipmentMethod" type="text" class="NFText" readonly="readonly"
													size="25" value="${slDto.shipMethod }" />
											</td>
										</tr>
										<tr>
											<th>
												Shipped Quantity
											</th>
											<td colspan="2">
												<input name="shippedQty" type="text" class="NFText" readonly="readonly"
													value="${shippeQuantity}" size="25" />
											</td>
											<th>
												Order Quantity
											</th>
											<td>
												<input name="orderQty" type="text" class="NFText" readonly="readonly"
													value="${orderQuantity}" size="25" />
											</td>
										</tr>
										<tr>
											<th>
												Quantity UOM
											</th>
											<td colspan="2">
												<input name="qtyUom" type="text" class="NFText" readonly="readonly"
													size="25" value="${slDto.qtyUom }" />
											</td>
											<th>
												&nbsp;
											</th>
											<td>
												&nbsp;
											</td>
										</tr>
										<tr>
											<th>
												Shipped Size
											</th>
											<td colspan="2">
												<input name="shippedSize" type="text" class="NFText" readonly="readonly"
													value='${fn:endsWith(shipSize,".0")||fn:endsWith(shipSize,".00")||fn:endsWith(shipSize,".000")?fn:substring(shipSize,0,fn:indexOf(shipSize,".")):shipSize}' size="25" />
											</td>
											<th>
												Order Size
											</th>
											<td>
												<input name="orderSize" type="text" class="NFText" readonly="readonly"
													value='${fn:endsWith(orderSize,".0")||fn:endsWith(orderSize,".00")||fn:endsWith(orderSize,".000")?fn:substring(orderSize,0,fn:indexOf(orderSize,".")):orderSize}' size="25" />
											</td>
										</tr>
										<tr>
											<th>
												Size UOM
											</th>
											<td colspan="2">
												<input name="sizeUom" type="text" class="NFText" size="25" readonly="readonly"
													value="${slDto.sizeUom }" />
											</td>
											<th>
												&nbsp;
											</th>
											<td>
												&nbsp;
											</td>
										</tr>
										<tr>
											<th>
												Description
											</th>
											<td colspan="2">
												<textarea name="description" id="description"  onblur="checkLen();" style="width: 322px;"
													class="content_textarea2">${slDto.description }</textarea>
											</td>
											<th>
												&nbsp;
											</th>
											<td>
												&nbsp;
											</td>
										</tr>
										<tr>
											<th>
												Shipping Clerk
											</th>
											<td colspan="2">
												<select name="shippingClerk" style="width: 157px;" id="select" disabled="disabled" >
													<option>${slDto.shipClerk}</option>
												</select>
											</td>
											<th>
												Created Date
											</th>
											<td>
												<input name="creationDate" type="text" class="NFText" value="<s:date name='slDto.creationDate' format='yyyy-MM-dd' />"
													readonly="readonly" size="25" />
											</td>
										</tr>
										<tr>
											<th>
												Modified By
											</th>
											<td colspan="2">
												<input name="modifiedName" type="text" class="NFText" value="${slDto.modifiedName }"
													readonly="readonly" size="25" />
											</td>

											<th>
												Modified Date
											</th>
											<td>
												<input name="modifyDate" type="text" class="NFText" value="<s:date name='slDto.modifyDate' format='yyyy-MM-dd' />"
													readonly="readonly" size="25" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<div class="button_box">
							<input type="submit" value="Save" class="search_input"/>
							<input type="button" value="Cancel" class="search_input" onclick='window.history.go(-1)' id="cancel1" />
						</div>
					</form>
				</div>

			</div>

		</div>
	</body>
</html>