<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
   <%@ include file="/common/taglib.jsp"%>
    <base href="${global_url}" />
    <title>scm</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="stylesheet/scm.css" rel="stylesheet" type="text/css" />
	<link href="stylesheet/table.css" rel="stylesheet" type="text/css" />
	<link href="stylesheet/print.css" rel="stylesheet" type="text/css" />
	<link href="stylesheet/openwin.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
   <c:if test="${empty Picklist || Picklist ==null}">
  	<table width="730" border="0">
  	<tr>
  		<td align="center">No Data!</td>
  	</tr>
  	</table>
  </c:if>
   <c:if test="${not empty Picklist && Picklist !=null}">

    <table width="730" border="0" cellspacing="3" cellpadding="0" bgcolor="#96BDEA">
  <tr>
    <td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" style="padding-left:10px;"><table width="0%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="31%"><img src="images/genscript_logo1.gif" width="144" /></td>
                <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td>&nbsp;</td>

                    </tr>
                  <tr>
                    <td height="24" align="center"><span class="print_txt_big_b">Pick List</span></td>
                    </tr>
                  <tr>
                    <td align="center"><span class="print_txt_mid_b">GenScript Inc.</span></td>
                    </tr>
                  </table>

                </tr>
              <tr>
                <td style="padding-top: 20px;padding-bottom: 20px;">
                 <c:forEach items="${Picklist }" var="l">
                 <c:if test="${ l.printPickListDTOList !=null&&not empty l.printPickListDTOList }">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="50%"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="24%" rowspan="3" valign="top" align="right"><span class="print_txt_b" style="font-weight: 800">Bill To:&nbsp;&nbsp;</span></td>
                        <td width="76%" align="left">${l.billToAddress }</td>

                        </tr>
                      
                      </table></td>

                    <td width="50%"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="20%" rowspan="3" valign="top" align="right"><span class="print_txt_b" style="font-weight: 800">Ship To:&nbsp;&nbsp;</span></td>
                        <td width="80%" align="left">${l.shipToAddress }</td>
                        </tr>
                    
                      </table></td>
                    </tr>
                  </table>
			 <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td style="padding-bottom: 20px;"><table width="100%" border="0" cellspacing="0" cellpadding="0" >
                  <tr>
                    <td width="12%" height="30" align="right"><span class="print_txt_b" style="font-weight: 800">Ship Via:&nbsp;&nbsp;</span></td>
                    <td width="38%" align="left">${l.shipVia }</td>
                    <td width="10%" align="right"><span class="print_txt_b" style="font-weight: 800">Weight:&nbsp;&nbsp;</span></td>
                    <td width="40%" align="left">${l.shipPackage.actualWeight }&nbsp; (lb)</td>

                    </tr>
                  <tr>
                    <td align="right"><span class="print_txt_b" style="font-weight: 800">Picker Name:&nbsp;&nbsp;</span></td>
                    <td align="left">${l.shipPackage.packer }</td>
                    <td align="right"><span class="print_txt_b" style="font-weight: 800">Warehouse:&nbsp;&nbsp;</span></td>
                    <td align="left">${l.warehouseName }</td>
                    </tr>

                  <tr>
                    <td colspan="4"><span class="print_txt_b" style="font-weight: 800">Fulfillment Information:</span></td>
                    </tr>
                     <tr>
                    <td colspan="4"><span class="print_txt_b" style="font-weight: 800">Sales order no:</span> ${orderNos}</td>
                    </tr>
                  </table></td>
                </tr>
              <tr>
                <td><table width="700" border="0" cellspacing="0" cellpadding="0" class="list_table">
                  <tr>

                    <th width="100">Storage Location</th>
                    <th width="50">Lot No</th>
                    <th width="190">Name</th>
                    <th width="85">Cat No</th>
                    <th width="85">Qty To Pick</th>
                    <th width="85">Qty Picked</th>
                    <th width="100">Comment</th>

                    </tr>
                   <c:forEach items="${l.printPickListDTOList }" var="sl">
                  <tr>
                    <td class="print_b">
                    ${sl.locationCode }
					&nbsp;</td>
					<td>${sl.lotNo }</td>
                    <td>${sl.name }</td>
                    <td>${sl.catNo }&nbsp;</td>
                    <td>${sl.qtyToPick }&nbsp;${sl.qtyUom }&nbsp;</td>
                    <td>${sl.qtypicked }&nbsp;${sl.qtyUom }&nbsp;</td>
                    <td class="print_r">${sl.comment }&nbsp;</td>
                    </tr>
					</c:forEach>
                 
                  </table>
                  </td>
                  </tr>
                  </table>
                   </c:if>
                  </c:forEach>
                  </td>
                </tr>
              </table></td>
            </tr>
          </table></td>
      </tr>
    </table></td>
  </tr>

</table>
 </c:if>
  </body>
</html>
