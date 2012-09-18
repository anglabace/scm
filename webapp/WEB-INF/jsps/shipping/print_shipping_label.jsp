<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>scm</title>
<%@ include file="/common/taglib.jsp"%>
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"	type="text/css" />
<link href="${global_css_url}greybox/gb_styles.css" rel="stylesheet"	type="text/css" media="all" />
<link href="${global_css_url}tab-view.css" rel="stylesheet"	type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<script type="text/javascript">
	var GB_ROOT_DIR = "./greybox/";
</script>
<script type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
<script type="text/javascript"
	src="${global_js_url}greybox/gb_scripts.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
		<script type="text/javascript" language="javascript">
		
		function jopens()
		{ 
			    document.getElementById("btn").disabled="disabled";
				var check1 = document.forms[0].check1;
				var check2 = document.forms[0].check2;
				var check3 = document.forms[0].check3;
				var name = document.getElementById('name').value.indexOf("FEDEX");
				if(check1.checked==false){
						   	if (check2.checked==true)
							{
								if(name > -1){
									var url="";
									if(check3.checked==true){
										url = "?createCommercialInvoice=y";
									}
									location.href = "shipping!printShippingLabelTrackingNo.action"+url;
	  								//document.forms[0].submit();
									
								}else{
								 	document.getElementById("btn").disabled="true";
								 	alert("ShipMethod is not Fedex!");
									return false;
  								}
							}
							else{
								var url = "";
								if(check3.checked==true){
									url = "?createCommercialInvoice=y";
								}
								
								location.href = "shipping!printShippingLabelEnterTrackingNo.action"+url;
							}
						}
						else
						{
							var chargeAmount = document.getElementById("chargeAmount").value;
							
							$.ajax({
								type:"POST",
								dataType:"json",
								url:"shipping!credcard.action?chargeAmount="+chargeAmount,
								success: function(msg){
									alert(msg.message);
									if (check2.checked==true)
									{
										if(name > -1){
											var url="";
											if(check3.checked==true){
												url = "?createCommercialInvoice=y";
											}
											location.href = "shipping!printShippingLabelTrackingNo.action"+url;
			  								//document.forms[0].submit();
											
										}else{
										 	document.getElementById("btn").disabled="true";
										 	alert("ShipMethod is not Fedex!");
											return false;
		  								}
									}
									else{
										var url = "";
										if(check3.checked==true){
											url = "?createCommercialInvoice=y";
										}
										
										location.href = "shipping!printShippingLabelEnterTrackingNo.action"+url;
									}
								},
								error: function(msg){
									alert("Failed to save the new break-down item.Please contact system administrator for help.");
								}
							});
  						}
		}
		
			function jopenss(){ 
				var check1 = document.forms[0].check1;
				var check2 = document.forms[0].check2;
				var check3 = document.forms[0].check3;
				var name = document.getElementById('name').value.indexOf("FEDEX");
				if(check2.checked!=true){
					check3.checked = check2.ckecked;
				}
				if(check1.checked==true){
					document.getElementById("btn").disabled="";
					document.getElementById('creditCardDIV').style.display = 'block';
					
				}else if(check2.checked==true){
								if(name > -1){
									document.getElementById("btn").disabled="";
								}else{
								 	document.getElementById("btn").disabled="true";
  								}
				}else{
					document.getElementById("btn").disabled="";
				}
				if(check1.checked==false){
					document.getElementById('creditCardDIV').style.display = 'none';
				}
		}
		function check2Check(){
			
		}
		function check22Check(){
			var check3 = document.forms[0].check3;
			if(check3.checked==true){
				var check2 = document.forms[0].check2;
				check2.checked = check3.checked;
			}
		}
</script>
	</head>
	<body>
		<form  name="fr" action="" method="post">
			<table width="600" border="0" cellspacing="3" cellpadding="0"
				bgcolor="#96BDEA">
				<input type="hidden" name="name" id="name" value="${sd.name_ }" />
				<tr>
					<td bgcolor="#FFFFFF">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td valign="top">
												<br />
												<table border="0" align="center" cellpadding="0"
													cellspacing="0" class="General_table" width="500">
													<tr>
													
														 <td colspan="2">
														  <div id="creditCardDIV" style="display: none;">
														 	<table width="350" border="0" cellspacing="0" cellpadding="1" class="list_table2">
               
											                <tr>
											                <td width="151">Card Holder:</td>
											                <td width="198">${bank.cardHolder }&nbsp;</td>
											                </tr>
											                <tr>
											                <td class="list_td2">Card Number:</td>
											                <td class="list_td2">${bank.cardNo }&nbsp;</td>
											                </tr>
											                <tr>
											                <td>Bill Address:</td>
											                <td>${bank.billTo }</td>
											                </tr>
											               
											                <tr>
											                <td class="list_td2">Expiration Date:</td>
											                <td class="list_td2">${bank.exprMonth }/${bank.exprYear }</td>
											                </tr>
											                <tr>
											                <td>Balance:</td>
											                <td>${bank.customerCharge }</td>
											                </tr>
											                <tr>
											                <td class="list_td2">Charge Amount:</td>
											                <td class="list_td2">$<input id = "chargeAmount" value="${bank.customerCharge }"/></td>
											                </tr>
											                </table><br/>
											                </div>
											                </td>

													</tr>
													<tr>
														<td width="26">
														
															<input name="check1" type="checkbox"
																id="check1"  onclick="jopenss()"/>
														</td>
														<td width="474">
															Charge credit card
														</td>
													</tr>
													<tr>
														<td width="26">
														    <c:if test="${country =='US'}">
														    	<input name="check3" type="checkbox" 
																	id="check3" onclick="check2Check()" disabled="disabled"/>
														    </c:if>
															 <c:if test="${country !='US'}">
																<input name="check3" type="checkbox" checked="checked"
																	id="check3" onclick="check2Check()"/>
															</c:if>
														</td>
														<td width="474">
															Create Commercial Invoice
														</td>
													</tr>
													<tr>
														<td>
															<input name="check2" type="checkbox" checked="checked"
																id="check2"  onclick="jopenss()"/>
														</td>
														<td>
															Print shipping label through GenScript system
														</td>
													</tr>
													<tr>
														<td height="80" colspan="2">
															<div align="center">
																<br />
																<input type="button" name="OK" class="style_botton"
																	value="OK" onclick="jopens()" id="btn"/>
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
							</tr>
						</table>
					</td>
				</tr>
			</table>
			
		</form>
	</body>
</html>