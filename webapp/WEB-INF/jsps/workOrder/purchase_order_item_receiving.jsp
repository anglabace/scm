<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>

<html>
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>scm</title>
		<link href="stylesheet/scm.css" rel="stylesheet" type="text/css" />
		<link href="stylesheet/table.css" rel="stylesheet" type="text/css" />


		<style type="text/css">
<!--
body {
	margin-left: 10px;
}
-->
</style>
		<style type="text/css" media="all">
@import "Upimg/thickbox.css";
</style>

		<script src="Upimg/jquery-1.1.3.1.pack.js" type="text/javascript"></script>
		<script src="Upimg/thickbox-compressed.js" type="text/javascript"></script>
		<link type="text/css" href="stylesheet/ui.all.css" rel="stylesheet" />
		<!--<script src="js/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="js/ui.core.js" type="text/javascript"></script>-->

		<script language="javascript" type="text/javascript"
			src="js/jquery.js"></script>
		<script src="js/ui.datepicker.js" type="text/javascript"></script>
		<script language="javascript" type="text/javascript"> 

	function shows(){
			var quantity =document.getElementById("quantity").value;
			var size =document.getElementById("size").value;
			var quantity2 =document.getElementById("quantity2").value;
			var size2 =document.getElementById("size2").value;
			var a = /^(\d*|\-?[1-9]{1}\d*)$/;
			if(!quantity.match(a)){
				alert("Quantity must be positive integer!");
				addForm.quantity.focus();
				return false;
			}
			if(isNaN(quantity)){
				alert("Quantity must be positive integer!");
				addForm.quantity.focus();
				return false;
			}
			if(parseInt(quantity)>parseInt(quantity2)){
				alert("Sorry, quantity is too big!");
				addForm.quantity.focus();
				return false;
			}
			if(parseInt(quantity)<=0){
				alert("Sorry, quantity must be greater than zero!");
				addForm.quantity.focus();
				return false;
			}
			if(isNaN(size)){
				alert("Size must be a valid number!");
				addForm.size.focus();
				return false;
			}
			if(parseInt(size)>parseInt(size2)){
				alert("Sorry, size is too big.");
				addForm.size.focus();
				return false;
			}
			if(${chkLength}<2){
				if(size<=0){
					alert("Sorry, size must be greater than zero.");
					addForm.size.focus();
					return false;
				}
			}
			return true;
		}
</script>

	</head>


	<body>
		<form action="work_order!getPurchaseOrderItemReceiveAdd.action"
			onsubmit="return shows();" name="addForm">
			<input type="hidden" value="${poi.quantity }" name="quantity2"
				id="quantity2">
			<input type="hidden" value="${poi.size }" name="size2" id="size2">
			<input type="hidden" value="${poi.orderItemId }" name="orderItemId"
				id="orderItemId">
			<input type="hidden" name="chk" value="${chk }">
			<input type="hidden" name="receIds" value="${receIds}">
			<table width="700" border="0" cellpadding="0" cellspacing="0"
				class="General_table" style="margin: 5px auto;">
				<tr>
					<td height="30">
						&nbsp;
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
					<th width="216">
						Package Tracking No
					</th>

					<td width="217">
						<c:if test="${chkLength == 1}">
							<input type="text" name="trackingNo" id="trackingNo"
								value="${trackingNo }" disabled="disabled" >
						</c:if>
						<c:if test="${chkLength > 1}">
							<input type="text" name="trackingNos" id="trackingNos" value=""
								disabled="disabled">
								<input type="hidden" name="trackingNo" id="trackingNo" value="${trackingNo }"
								disabled="disabled">
						</c:if>
					</td>
					<th width="102">
						&nbsp;
					</th>
					<td width="165">
						&nbsp;
					</td>
				</tr>
				<tr>
					<th>
						Quantity to Receive
					</th>

					<td width="217">
						<c:if test="${chkLength == 1}">
							<c:if test="${po.id!=poi.orderItemId}">
								<input name="quantity" id="quantity" type="text" class="NFText"
									value="${poi.quantity }" size="20" onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"  onfocus="this.select()" <c:if test="${poi.quantity==1}">readonly="readonly"</c:if>/>
							</c:if>
							<c:if test="${po.id==poi.orderItemId}">
								<input name="quantity" id="quantity" type="text" class="NFText"
									value="${po.quantity }" size="20" onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"  onfocus="this.select()" <c:if test="${poi.quantity==1}">readonly="readonly"</c:if>/>
							</c:if>
							${poi.qtyUom }
						</c:if>
						<c:if test="${chkLength > 1}">
							<input name="quantity" id="quantity" type="text" class="NFText"
								value="" size="20" disabled="disabled" />
						</c:if>
					</td>
					<th>
						Size to Receive
					</th>
					<td>
						<c:if test="${chkLength == 1}">
							<c:if test="${po.id!=poi.orderItemId}">
								<input name="size" id="size" type="text" class="NFText"
									value='${fn:endsWith(poi.size,".0")||fn:endsWith(poi.size,".00")||fn:endsWith(poi.size,".000")?fn:substring(poi.size,0,fn:indexOf(poi.size,".")):poi.size}' size="20" onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"  onfocus="this.select()" <c:if test="${poi.quantity>1}">readonly</c:if>/>
							</c:if>
							<c:if test="${po.id==poi.orderItemId}">
								<input name="size" id="size" type="text" class="NFText"
									value='${fn:endsWith(poi.size,".0")||fn:endsWith(poi.size,".00")||fn:endsWith(poi.size,".000")?fn:substring(poi.size,0,fn:indexOf(poi.size,".")):poi.size}' size="20" onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"  onfocus="this.select()" <c:if test="${poi.quantity>1}">readonly</c:if>/>
							</c:if>
							${poi.sizeUom }
						</c:if>
						<c:if test="${chkLength > 1}">
							<input name="size" id="size" type="text" class="NFText" value=""
								size="20" disabled="disabled" />
						</c:if>
					</td>
				</tr>
				<tr>
					<th>
						Storage Location
					</th>
					<td colspan="3">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>

								<td width="20" style="padding: 0px;">
									<input type="radio" name="Station" id="radio2" value="1"
										checked="checked" />
								</td>
								<td>
									Work Station
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<th>
						&nbsp;
					</th>
					<td colspan="3">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>

								<td width="22" style="padding: 0px;">
									<input type="radio" name="Station" id="radio" value="2" />
								</td>
								<td>
									<s:select
							            name="sfr"
							            list="storageList" listValue="name" listKey="name"
							            />
								</td>

								<td width="50">
									<!--<select name="sName" style="width: 40px;">
										<option value="1">
											1
										</option>
										<option value="2">
											2
										</option>
										<option value="3">
											3
										</option>
										<option value="4">
											4
										</option>
										<option value="5">
											5
										</option>
									</select>
								--></td>
								<th width="30"><!--
									Layer
								--></th>
								<td>
									<!--<select name="ly" style="width: 40px;">
										<option value="1">
											1
										</option>
										<option value="2">
											2
										</option>
										<option value="3">
											3
										</option>
										<option value="4">
											4
										</option>
										<option value="5">
											5
										</option>
									</select>
								--></td>
								<th width="30"><!--
									Box
								--></th>
								<td>
									<!--<select name="bx" style="width: 40px;">

										<option value="1">
											1
										</option>
										<option value="2">
											2
										</option>
										<option value="3">
											3
										</option>
										<option value="4">
											4
										</option>
										<option value="5">
											5
										</option>
									</select>
								--></td>
								<th width="31"><!--
									Well
								--></th>
								<td>
									<!--<select name="wl" style="width: 40px;">
										<option value="1">
											1
										</option>
										<option value="2">
											2
										</option>
										<option value="3">
											3
										</option>
										<option value="4">
											4
										</option>
										<option value="5">
											5
										</option>
									</select>
								--></td>
							</tr>

						</table>
					</td>
				</tr>
				<tr>
					<th>
						Receiving Note
					</th>
					<td colspan="3">
						<input name="note" type="text" class="NFText" value="" size="85" />
					</td>
				</tr>
				<tr>
					<th>
						Date to Receive
					</th>
					<td>
						<input name="reveivingDate" type="text" class="NFText"
							value='<fmt:formatDate value="<%=new java.util.Date() %>" pattern="yyyy-MM-dd hh:mm:ss"/>' size="20"
							readonly="readonly" />
					</td>
					<th>
						Received By
					</th>
					<td>
						<input name="loginName" type="text" class="NFText"
							value="${loginName }" size="20" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td height="100" colspan="4">
						<div align="center">
							<input type="submit" name="Submit" class="style_botton"
								value="Confirm" />
							&nbsp;&nbsp;
							<input type="button" name="Submit622" value="Cancel"
								class="style_botton"
								onclick="parent.$('#purchaseOrderItemReceiving').dialog('close');" />
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
