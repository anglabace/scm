<%@ page contentType="application/msexcel; charset=UTF-8" %>
<%@ include file="/common/taglib.jsp"%>
<%
   response.setHeader("Content-disposition","attachment; filename=packingSlip.doc");
   response.setCharacterEncoding("UTF-8");
   response.setContentType("application/msword");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
   <%@ include file="/common/taglib.jsp"%>
    <base href="${global_url}" />
    <title>scm</title>
	
	<link href="stylesheet/scm.css" rel="stylesheet" type="text/css" />
	<link href="stylesheet/table.css" rel="stylesheet" type="text/css" />
	<link href="stylesheet/print.css" rel="stylesheet" type="text/css" />
	<link href="stylesheet/openwin.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
  	<center>
	<c:set var="count" value="0" scope="page" ></c:set>
	<c:set var="k" value="0" scope="page" ></c:set>
	<c:forEach items="${viewlist }" var="l">
	<c:if test="${l.order!=k && k!=0}" >
	<c:set var="bool" value="true"  scope="page"  ></c:set>
	</c:if>
	
	<c:if test="${l.order==k || k==0 }">
	<!-- pageView.result -->
	<c:if test="${count==2 }">
		<br clear=all style='mso-special-character:line-break;page-break-before:always'/>
	</c:if>
	<c:set var="count" value="2" scope="page"  ></c:set>
	<div style="width:730px;" >
	<div align="center"><font size="4">Packing List</font><br/></div>
		<table width="730" border="0" cellspacing="3" cellpadding="0"
			bgcolor="#96BDEA">
			<tr>
				<td bgcolor="#FFFFFF">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">

						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td valign="top" style="padding-left: 10px;">
											<table width="0%" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td class="print_ge">
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td width="144" height="52">
																	<img src="images/genscript_logo1.gif" width="144" height="52"/>
																</td>
																<td width="69%" align="left" valign="bottom">

																	<table width="80%" border="0" align="right"
																		cellpadding="0" cellspacing="0">

																		<tr>
																			<td height="24" align="center">
																				<span class="print_txt_big_b">GenScript USA
																					Inc.</span>
																			</td>
																		</tr>
																		<tr>
																			<td align="center">
																				860 Centennial Ave, Piscataway, NJ 08854, USA
																			</td>
																		</tr>
																		<tr>
																			<td align="center">
																				Tel:732-885-9188 Fax:732-210-0262 
																			</td>

																		</tr>
																		<tr>
																			<td align="center">
																				E-mail:
																				<a href="mailto:order@genscript.com"
																					style="text-decoration: underline;">order@genscript.com</a>
																			
																			</td>

																		</tr>
																		
																	</table>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td class="print_line">
														
															<table width="100%" border="0" cellspacing="0"
																cellpadding="0">
																<tr>
																	<td width="67%">
																		<table width="100%" border="0" cellspacing="0"
																			cellpadding="0">

																			<tr>
																				<td width="14%" rowspan="3" valign="top" align="right">
																					<span class="print_txt_mid_b" style="font-weight: 800">Bill To:</span>
																				</td>
																				<td width="86%" align="left">
																					${l.billTo }
																				</td>
																			</tr>
																		</table>
																	</td>
																	<td width="33%">
																		<table width="100%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td width="34%" rowspan="3" valign="top" align="right">
																					<span class="print_txt_mid_b" style="font-weight: 800">Ship To:</span>
																				</td>
																				<td width="66%" align="left">
																					${l.shipTO }
																				</td>

																			</tr>
																		</table>
																	</td>

																</tr>
															</table>
													</td>
												</tr>
												<tr>
													<td>
														<table width="700" border="0" cellspacing="0"
															cellpadding="0" class="list_table">
															<tr>
																<th width="118">
																	Customer No.
																</th>
																<th width="50">
																	PO#
																</th>
																<th width="60">
																	Order#
																</th>

																<th width="80">
																	Order Date
																</th>
																<th width="100">
																	Ship Via
																</th>
																<th width="90">
																	Shipping Date
																</th>
																<th width="60">
																	Terms
																</th>
																<th width="100">
																	Total Weight
																</th>
																<th width="100">
																	# Of Packages
																</th>
															</tr>

															<tr>
																<td class="print_b">
																	${l.customerNo }&nbsp;
																</td>
																<td>
																	${l.po }&nbsp;
																</td>
																<td>
																	${l.order }&nbsp;
																</td>
																<td>
																	${l.orderDate }&nbsp;
																</td>
																<td>
																	${l.shipVia }&nbsp;
																</td>
																<td>
																	${l.shippingDate }&nbsp;
																</td>
																<td>
																	${l.terms }&nbsp;
																</td>
																<td class="print_r">
																	${l.totalWeightDouble }&nbsp;(lb)
																</td>
																<td class="print_r">
																	${l.ofPacakge }&nbsp;
																</td>
															</tr>

														</table>
													</td>
												</tr>
												<tr>
													<td class="print_thank">
														<div class="print_txt_line" style="text-align:center">
															
														</div>
													</td>
												</tr>
												<br></br>
												<tr>
													<td>
														<table width="700" border="0" cellspacing="0"
															cellpadding="0" class="list_table">
															<tr>

																<th width = "50">
																	Item
																</th>
																<th width = "100">
																	Catalog number
																</th>
																<th width = "50">
																	Quantity
																</th>
																<th width = "50">
																	size
																</th>
																<th width = "200">
																	Description
																</th>
															</tr>
															<c:forEach items="${l.shipPackageLineDTO }" var="sl">
															<tr>
																
																<td class="print_b">
																	
																	<font><span <c:if test="${sl.giftFlag!=null}">style="color: green;"</c:if>>${sl.itemNo }</span></font>&nbsp;
																</td>
																
																<td>
																	<font><span <c:if test="${sl.giftFlag!=null}">style="color: green;"</c:if>>${sl.catalogNO }</span></font>&nbsp;
																	     
																</td>
																<td>
																	<font><span <c:if test="${sl.giftFlag!=null}">style="color: green;"</c:if>>${sl.qtyShipped }&nbsp;${sl.qtyUom }</span></font>&nbsp;
																</td>
																<td>
																	<font><span <c:if test="${sl.giftFlag!=null}">style="color: green;"</c:if>>
																	${fn:endsWith(sl.size,'.0')||fn:endsWith(sl.size,'.00')||fn:endsWith(sl.size,'.000')?fn:substring(sl.size,0,fn:indexOf(sl.size,".")):sl.size}
																	&nbsp;${sl.sizeUom }</span></font>&nbsp;
																</td>
																<td>
																	<font><span <c:if test="${sl.giftFlag!=null}">style="color: green;"</c:if>>${sl.name }</span></font>&nbsp;
																</td>
															</tr>
															</c:forEach>
															<tr>
																<td colspan="6">ALL REAGENTS ARE FOR RESEARCH USE ONLY AND ARE NOT INTENDED FOR DIAGNOSTIC OR THERAPEUTIC USE. GENSCRIPT MAKES NO CLAIMS TO THE PRODUCT'S ABILITY TO FUNCTION IN THE SPECIFIC APPLICATION OF THE CUSTOMER. </td>
															</tr>
														</table>													
													</td>
												</tr>
												<tr>
													<td>												
													</td>
												</tr>
												<tr>
													<td class="print_footline">													
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>

			</tr>
			
		</table>
		</div>
		</c:if>
		<c:if test="${bool}">
		<c:set var="height" value="${(2-count)*523 }" scope="page" ></c:set>
		<c:set var="count" value="0" scope="page"  ></c:set>
		<c:set var="bool" value="false" scope="page"></c:set>
		
		</c:if>
		<c:set var="k" value="${l.order }" scope="page"  ></c:set>
		<c:if test="${l.type==\"11\" || l.type ==\"12\" || l.type == \"28\" || l.type == \"36\"}">				
	<br/>
	<br/>	
	<br clear="all" style='mso-special-character:line-break;page-break-before:always'/>		
	<table width="730" border="0" cellspacing="3" cellpadding="0" bgcolor="#96BDEA">
			<tr>
			<td bgcolor="#FFFFFF">
					
					<div style="width:730px;" >
					<div align="center">
					<table width="730" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td width="144" height="52">
																	<img src="images/genscript_logo1.gif" width="144" height="52"/>
																</td>
																<td width="69%" align="left" valign="bottom">

																	<table width="80%" border="0" align="right"
																		cellpadding="0" cellspacing="0">

																		<tr>
																			<td height="24" align="center">
																				<span class="print_txt_big_b">GenScript USA
																					Inc.</span>
																			</td>
																		</tr>
																		<tr>
																			<td align="center">
																				860 Centennial Ave, Piscataway, NJ 08854, USA
																			</td>
																		</tr>
																		<tr>
																			<td align="center">
																				Tel:732-885-9188 Fax:732-210-0262 
																			</td>

																		</tr>
																		<tr>
																			<td align="center">
																				E-mail:
																				<a href="mailto:order@genscript.com"
																					style="text-decoration: underline;">order@genscript.com</a>
																			
																			</td>

																		</tr>
																		
																	</table>
																</td>
															</tr>
														</table>
					<table width="730" border="0" cellspacing="0"
																cellpadding="0">
																<tr>
																	<td width="67%">
																		<table width="100%" border="0" cellspacing="0"
																			cellpadding="0">

																			<tr>
																				<td width="14%" rowspan="3" valign="top" align="right">
																					<span class="print_txt_mid_b" style="font-weight: 800">Bill To:</span>
																				</td>
																				<td width="86%" align="left">
																					${l.billTo }
																				</td>
																			</tr>
																		</table>
																	</td>
																	<td width="33%">
																		<table width="100%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td width="34%" rowspan="3" valign="top" align="right">
																					<span class="print_txt_mid_b" style="font-weight: 800">Ship To:</span>
																				</td>
																				<td width="66%" align="left">
																					${l.shipTO }
																				</td>

																			</tr>
																		</table>
																	</td>

																</tr>
															</table>	
															
					<table width="660"><tr><td></td></tr></table>
					<table  border=0 cellspacing=0 cellpadding=0 width=620>
					<tr><td width="160"></td><td width="300" align="center"><font size="4">Antibody Packing List</font></td>
					<td width='160' align='right'><font size='-1'></font></td></tr>
					</table>
					<table width='660' ><tr><td><table class="list_table"  border=0 cellspacing=0 cellpadding=0>
							<tr>
								<th>Item Number</th>
								<th>Lot Number</th>
								<th>Quantity</th>
								<th>Description</th>
								<th>Shipping Temperature</th>
								<th>Storage Temperature</th>
							</tr>
							<c:forEach items="${l.workOrderList }" var="wol">
							<tr><td rowspan="${wol.lotLength }" align='center' class="print_b">${wol.soNo }_${wol.soItemNo }</td>
											<c:if test="${wol.workOrderLotList==null}">
												<td align=center></td>
												<td align=center></td>
												<td align=center></td>
												<td align=center></td>
												<td align=center></td>
												
											</c:if>	
											<c:if test="${wol.workOrderLotList!=null}">
												<c:set var="wolltr" value="0" scope="page" ></c:set>	
												<c:forEach items = "${wol.workOrderLotList}" var="woll">
													<c:if test="${wolltr == 1}">
														</tr>
														<tr>
													</c:if>
													<td align=center>${woll.lotNo }</td>
													<td align=center>${woll.quantity }</td>
													<td align=center>${woll.lotDesc }</td>
													<td align=center>${woll.shipTemperature }<img src="images/sheshi.jpg"/></td>
													<td align=center>${woll.storageTemperature }<img src="images/sheshi.jpg"/></td>
													<c:set var="wolltr" value="1" scope="page" ></c:set>
												</c:forEach>
													
												
											</c:if>																		
										</tr>			
							</c:forEach>
					<tr><td colspan='6'>			
					ALL REAGENTS ARE FOR RESEARCH USE ONLY AND ARE NOT INTENDED FOR DIAGNOSTIC OR THERAPEUTIC USE. GENSCRIPT MAKES NO CLAIMS TO THE PRODUCT'S ABILITY TO FUNCTION IN THE SPECIFIC APPLICATION OF THE CUSTOMER.
					</td></tr>
					</table>
					</td></tr>			
					</table>
					
					
					</div>
					          
					</div>
					</td>
					</tr>
					</table>
					</c:if>
		</c:forEach>
		</center>
	</body>

</html>
