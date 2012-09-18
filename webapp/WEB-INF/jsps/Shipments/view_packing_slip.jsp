<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!-- 以上这行设定本网页为excel格式的网页 -->
<%
   response.setHeader("Content-disposition","attachment; filename=packingSlip.doc");
   response.setCharacterEncoding("UTF-8");
   response.setContentType("application/msword");
%>
<html>
  <head>
   <%@ include file="/common/taglib.jsp"%>
    <base href="${global_url}" />
    <title>scm</title>
	
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="stylesheet/scm.css" rel="stylesheet" type="text/css" />
	<link href="stylesheet/table.css" rel="stylesheet" type="text/css" />
	<link href="stylesheet/print.css" rel="stylesheet" type="text/css" />
	<link href="stylesheet/openwin.css" rel="stylesheet" type="text/css" />
  </head>

  <body>
  <center>
    <c:set var="count" value="0" scope="page" ></c:set>
  <c:if test="${empty viewPackingSlip || viewPackingSlip ==null}">
  	<table width="730" border="0">
  	<tr>
  		<td align="center">No Data!</td>
  	</tr>
  	</table>
  </c:if>
   <c:if test="${not empty viewPackingSlip && viewPackingSlip !=null}">
   <c:if test="${count==2 }">
		<br clear=all style='mso-special-character:line-break;page-break-before:always'>
	</c:if>
	<c:set var="count" value="2" scope="page"  ></c:set>
  <div align="center"><font size="4">Packing List</font><br/></div>
    <table width="730" border="0" cellspacing="3" cellpadding="0" bgcolor="#96BDEA">
  <tr>
    <td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">

      <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" style="padding-left:10px;"><table width="0%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td class="print_ge"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="31%"><img src="images/genscript_logo1.gif" width="144" /></td>
                    <td width="69%" align="left" valign="bottom">
                   
                    <table width="80%" border="0" align="right" cellpadding="0" cellspacing="0">

                      <tr>
                        <td height="24" align="center"><span class="print_txt_big_b">GenScript USA Inc.</span></td>
                        </tr>
                      <tr>
                        <td align="center">120 Centennial Ave, Piscataway, NJ08854, USA</td>
                        </tr>
                      <tr>
                        <td align="center">Tel:732-885-9188 Fax:732-210-0262 E-mail: <a href="mailto:order@genscript.com" style="text-decoration: underline;">order@genscript.com</a></td>

                        </tr>
                      </table></td>
                    </tr>
                  </table></td>
                </tr>
              <tr>
                <td class="print_line">
               
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="67%"><table width="100%" border="0" cellspacing="0" cellpadding="0">

                      <tr>
                        <td width="14%" rowspan="3" valign="top" align="right"><span class="print_txt_mid_b">Bill To:</span></td>
                        <td width="86%" align="left">${viewPackingSlip.billTo }</td>
                        </tr>
                      </table></td>
                    <td width="33%"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="34%" rowspan="3" valign="top" align="right"><span class="print_txt_mid_b">Ship To:</span></td>
                        <td width="66%" align="left">${viewPackingSlip.shipTO }</td>

                        </tr>
                      </table></td>

                    </tr>
                  </table></td>
                </tr>
              <tr>
                <td><table width="700" border="0" cellspacing="0" cellpadding="0" class="list_table">
                  <tr>
                    <th width="108">Customer No.</th>
                    <th width="50">
						PO#
					</th>
                    <th width="64">Order#</th>
                    <th width="85">Order Date</th>
                    <th width="103">Ship Via</th>
                    <th width="98">Shipping Date</th>
                    <th width="101">Total Weight</th>
                    <th width="100"># of Packages</th>
                    </tr>

                  <tr>
                    <td class="print_b">${viewPackingSlip.customerNo }</td>
                    <td>
							${viewPackingSlip.po }&nbsp;
					</td>
                    <td>${viewPackingSlip.order }</td>
                    <td>${viewPackingSlip.orderDate }</td>
                    <td>${viewPackingSlip.shipVia }</td>
                    <td>${viewPackingSlip.shippingDate }</td>
                    <td class="print_r">${viewPackingSlip.totalWeightDouble }</td>
                    <td class="print_r">${viewPackingSlip.ofPacakge }</td>
                    </tr>

                  </table></td>
                </tr>
              <tr>
                <td class="print_thank"></td>
                </tr>
              <tr>
                <td><table width="700" border="0" cellspacing="0" cellpadding="0" class="list_table">
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
																	size unit
																</th>
																<th width = "200">
																	Description
																</th>
                    </tr>
										<c:forEach items="${viewPackingSlip.shipPackageLineDTO }" var="sl">
											<tr>
																
																<td class="print_b">
																	
																	<font><span <c:if test="${sl.giftFlag!=null}">style="color: green;"</c:if>>${sl.itemNo }</span></font>&nbsp;
																</td>
																
																<td class="print_b">
																	<font><span <c:if test="${sl.giftFlag!=null}">style="color: green;"</c:if>>${sl.catalogNO }</span></font>&nbsp;
																	     
																</td>
																<td>
																	<font><span <c:if test="${sl.giftFlag!=null}">style="color: green;"</c:if>>${sl.qtyShipped }${sl.qtyUom }</span></font>&nbsp;
																</td>
																<td>
																	<font><span <c:if test="${sl.giftFlag!=null}">style="color: green;"</c:if>>${sl.size }${sl.sizeUom }</span></font>&nbsp;
																</td>
																<td>
																	<font><span <c:if test="${sl.giftFlag!=null}">style="color: green;"</c:if>>${sl.name }</span></font>&nbsp;
																</td>
															</tr>
										</c:forEach>
															<tr>
																<td colspan="5">ALL REAGENTS ARE FOR RESEARCH USE ONLY AND ARE NOT INTENDED FOR DIAGNOSTIC OR THERAPEUTIC USE. GENSCRIPT MAKES NO CLAIMS TO THE PRODUCT'S ABILITY TO FUNCTION IN THE SPECIFIC APPLICATION OF THE CUSTOMER. </td>
															</tr>
                  </table>
                  </td>
                </tr>
                <tr>
                <td><br />
                  <br />
                  <br /></td>
                </tr>
                <tr>
              

                </tr>
              
              </table></td>
            </tr>
          </table></td>
      </tr>
    </table></td>
  </tr>
</table>

		<c:if test="${viewPackingSlip.type==\"11\" || viewPackingSlip.type ==\"12\" || viewPackingSlip.type == \"28\" || viewPackingSlip.type == \"36\"}">				
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
																					${viewPackingSlip.billTo }
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
																					${viewPackingSlip.shipTO }
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
							<c:forEach items="${viewPackingSlip.workOrderList }" var="wol">
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
</c:if>
</center>
</body>

</html>
