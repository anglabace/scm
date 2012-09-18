<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}ajax.js"></script>

<link href="${global_css_url}tab-view.css" rel="stylesheet"
	type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}TabbedPanels.js"></script>
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
	type="text/css" />

<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script
	src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js"
	type="text/javascript"></script>
<link type="text/css"
	href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script src="${global_js_url}jquery/ui/ui.core.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1"
	type="text/javascript"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}scm/product/manager_task.js?v=2"></script>
<script src="${global_js_url}util/util.js" type="text/javascript"></script>
<script src="${global_js_url}/recordTime.js" type="text/javascript"></script>
		<script>
function jopen()
{
  if ($("#check1").attr("checked"))
						{
						   	window.parent.closeiframe();
					    }
  	
}

function printCustomsInvoicefunct(){
	document.printShippinglabelForm.action="shipping!searchPrintShipPackages.action";
	document.printShippinglabelForm.submit();
}

function show(){
		var trackingNo = document.getElementsByName('trackingNo');
		document.printShippinglabelForm.action="shipping!printShippingLabelEnterTrackingNoUpdate.action?shipmentId=${shipmentId}";
	 	var d1;
		for(var i=0;i<trackingNo.length;i++){
			 d1=trackingNo[i].value;
			 if(d1 == null || d1.length == 0){
				alert('Tracking No must be not empty.');
				return false;
			}	
		}
		
		return true;
}

function shipMethodOnChange(count){
	var methodId = $("#shipMethod"+count+" option:selected").val();
	if(methodId==23){
		$("#trackingNo"+count).val(" ");
		$("#trackingNo"+count).attr("readonly",true);
	}else{
		$("#trackingNo"+count).val("");
		$("#trackingNo"+count).attr("readonly",false);
	}
}
</script>
		<link href="stylesheet/openwin.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<form
			action="shipping!printShippingLabelEnterTrackingNoUpdate.action?shipmentId=${shipmentId}"
			onsubmit="return show();" id="printShippinglabelForm" name ="printShippinglabelForm">
			<table width="600" border="0" cellspacing="3" cellpadding="0"
				bgcolor="#96BDEA">
				<tr>
					<td bgcolor="#FFFFFF">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td valign="top">
											<br />
											<table border="0" align="center" cellpadding="0"
												cellspacing="0" class="General_table" width="600">
												<tr>
													<th height="30" colspan="2">
														<div align="left">
															Enter Tracking Number For:
														</div>
													</th>
                                                    <td></td>
												</tr>
												<c:if test="${count<=0}">
													<tr>
														<th width="202" colspan="2">
															Sorry, no corresponding bag information!
														</th>
                                                        <td></td>
													</tr>
												</c:if>
												<c:set var="count" value="1"></c:set>
												<c:forEach items="${list}" var="pk">
                                                    <tr>
                                                        <th width="102px" colspan="1">
															Package #${pk[0]}
														</th>
                                                        <td colspan="3" width="200px"><input type="hidden" name="packageId" value="${pk[0]}" /></td>
                                                    </tr>
													<tr>
                                                        <th width="102px" style="width:102px">Shipping carrier:</th>
                                                        <td width="200px">
                                                            <select name="shipMethod" id="shipMethod${count}" style="width:200px" onchange="shipMethodOnChange('${count}')">
                                                                <c:forEach items="${shipMenthodList}" var="methods">"
                                                                <option value="${methods.methodId}" <c:if test="${methods.methodId == pk[1]}">selected="selected"</c:if>>${methods.name}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </td>
														<th width="60px">Tracking No:</th>
                                                        <td width="100px">
                                                        	<c:if test="${pk[1]==24}">
                                                        		<input name="trackingNo" id="trackingNo${count}" type="text" class="NFText" size="29" value=" " readonly="readonly"/>
                                                        	</c:if>
															<c:if test="${pk[1]!=24}">
                                                        		<input name="trackingNo" id="trackingNo${count}" type="text" class="NFText" size="29" value="${pk[2]}"/>
                                                        	</c:if>
														</td>
													</tr>
													<c:set var="count" value="${count+1}"></c:set>
												</c:forEach>
												<tr>
                                                    <th></th>
													<td height="80" colspan="2">
														<div align="center">
															<br />
															<c:if test="${count<=0}">
																<input type="submit" name="Submit2" class="style_botton"
																	value="OK" disabled="disabled" />
															</c:if>
															<c:if test="${count>0}">
																<input type="submit" name="Submit2" class="style_botton"
																	value="OK" />
															</c:if>
															<input  type="button" name="printCustomsInvoiceName" value="Print Commercial Invoice" class="style_botton3" onclick="printCustomsInvoicefunct();" ${createCommercialInvoice!='y' ? 'disabled':'' }/>
															<input type="button" name="Cancel" value="Cancel"
																	class="style_botton"
																	onclick="parent.$('#print_shipping_label').dialog('close');" />
														</div>
													</td>
												</tr>
											</table>
											<br />
											<br />
										</td>
									</tr>
								</table>
							</td>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>

