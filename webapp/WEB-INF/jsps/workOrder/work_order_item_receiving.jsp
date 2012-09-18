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
				alert("Quantity must be positive integer");
				addForm.quantity.focus();
				return false;
			}
			if(isNaN(quantity)){
				alert("quantity must be valid number");
				addForm.quantity.focus();
				return false;
			}
			if(parseInt(quantity)>parseInt(quantity2)){
				alert("Sorry, quantity too big!");
				addForm.quantity.focus();
				return false;
			}
			if(parseInt(quantity)<=0){
				alert("Sorry, quantity must greater zero!");
				addForm.quantity.focus();
				return false;
			}
			if(isNaN(size)){
				alert("size must be valid number");
				addForm.size.focus();
				return false;
			}
			if(parseInt(size)>parseInt(size2)){
				alert("Sorry, size too big!");
				addForm.size.focus();
				return false;
			}
			if(size<=0){
				alert("Sorry, size must greater zero!");
				addForm.size.focus();
				return false;
			}
			return true;
		}
</script>

	</head>

		
	<body>
		<form action="work_order!getWorkOrderItemReceiveAdd.action" onsubmit="return shows();" name="addForm">
		<input type="hidden" value="${wo.quantity }" name="quantity2" id="quantity2">
		<input type="hidden" value="${wo.size }" name="size2" id="size2">
		<input type="hidden" value="${wo.orderNo }" name="orderNo" id="orderNo">
		<input type="hidden" name="chk" value="${chk }">
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
						Package Batch No
					</th>

					<td width="217">
					<c:if test="${chkLength == 1}">
						<input type="text" name="batchNo" id="batchNo"
							value="${batchNo }" readonly="readonly">
					</c:if>
					<c:if test="${chkLength > 1}">
						<input type="text" name="batchNos" id="batchNos"
							value="" readonly="readonly">
							<input type="hidden" name="batchNo" id="batchNo"
							value="${batchNo }" readonly="readonly">
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
							<c:if test="${wo.orderNo!=wo2.orderNo}">
								<input name="quantity" id="quantity" type="text" class="NFText"
									value="${wo.quantity }" size="20"  onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"  onfocus="this.select()"/>
							</c:if>
							<c:if test="${wo.orderNo==wo2.orderNo}">
								<input name="quantity" id="quantity" type="text" class="NFText"
									value="${wo2.quantity }" size="20"  onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"  onfocus="this.select()"/>
							</c:if>
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
							<c:if test="${wo.orderNo!=wo2.orderNo}">
								<input name="size" id="size" type="text" class="NFText" value="${wo.size }" size="20"  onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"  onfocus="this.select()" <c:if test="${wo.quantityWoTemp>1}">readonly</c:if>/>
							</c:if>
							<c:if test="${wo.orderNo==wo2.orderNo}">
								<input name="size" id="size" type="text" class="NFText" value="${wo2.size }" size="20"  onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"  onfocus="this.select()" <c:if test="${wo.quantityWoTemp>1}">readonly</c:if>/>
							</c:if>
						</c:if>
						<c:if test="${chkLength > 1}">
							<input name="size" id="size" type="text" class="NFText" value="" size="20" disabled="disabled" />
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
									<input type="radio" name="Station" id="radio2" value="1" checked="checked"/>
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
									<select name="sfr">
										<option value="Freezer">
											Freezer
										</option>
										<option value="Rack">
											Rack
										</option>
									</select>
								</td>

								<td width="50">
									<select name="sName" style="width: 40px;">
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
								</td>
								<th width="30">
									Layer
								</th>
								<td>
									<select name="ly" style="width: 40px;">
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
								</td>
								<th width="30">
									Box
								</th>
								<td>
									<select name="bx" style="width: 40px;">

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
								</td>
								<th width="31">
									Well
								</th>
								<td>
									<select name="wl" style="width: 40px;">
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
								</td>
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
							value="<%=new Date().toLocaleString()%>" size="20"
							readonly="readonly" />
					</td>
					<th>
						Received By
					</th>
					<td>
						<input name="loginName" type="text" class="NFText" value="${loginName }"
							size="20" readonly="readonly" />
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
								onclick="parent.$('#workOrderItemReceiving').dialog('close');" />
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
