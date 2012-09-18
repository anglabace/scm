<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp"%>
<%
   response.setHeader("Content-disposition","attachment; filename=PrintCommercialInvoice.doc");
   response.setCharacterEncoding("UTF-8");
   response.setContentType("application/msword");
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Commercial Invoice</title>
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<script type="text/javascript">var GB_ROOT_DIR = "./greybox/";</script>
<script type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/gb_scripts.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
</head>
<c:set var="count" value="0" scope="page"/>
<body>
<center>
<s:if test="printShipPackageDTOList != null && printShipPackageList.size >0">
<s:iterator value="printShipPackageDTOList" id="printShipPackage" status="index">
<c:if test="${count==2 }">
    <br clear=all style='mso-special-character:line-break;page-break-before:always'/>
</c:if>
<c:set var="count" value="2" scope="page"/>
<h4>Commercial Invoice </h4>
<table width="650" border="1">
  <tr>
    <td colspan="4"><strong>ULTIMATE  DESTINATION</strong><br />
        <s:property value="#printShipPackage.rcpCountry" escape="false"/>
     </td>
    <td width="248"><strong>NO.  OF PKGS.</strong><br />
    <s:property value="#index.index+1"/>   
</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4"><strong>DATE  OF EXPORTATION</strong><br />
    <s:date name="#printShipPackage.shipmentDate" format="yyyy-MM-dd"/>
 </td>
    <td><strong>SHIPPING  LABEL NO.</strong><br />
    <s:property value="#printShipPackage.trackingNo"/></td>
    <td width="114"><strong>CURRENCY</strong><br />
US Dollars </td>
  </tr>
  <tr>
    <td colspan="4"><strong>SHIPPER/EXPORTER</strong><br />
Genscript Customer Services<br />
7328859188 <br />
GENSCRIPT CORP <br />
860 Centennial Ave. <br />
Piscataway, NJ 08854<br />
USA</td>
    <td valign="top"><strong>CONSIGNEE</strong><br />
      <%--<s:property value="#printShipPackage.rcpFirstName "/> <s:property value="#printShipPackage.rcpMidName " escape="false"/> <s:property value="#printShipPackage.rcpLastName " escape="false"/><br />--%>
      <%--<s:property value="#printShipPackage.rcpPhone " escape="false"/> <br />--%>
      <%--<s:property value="#printShipPackage.rcpOrgName " escape="false"/> <br />--%>
      <s:property value="#printShipPackage.shiptoAddress " escape="false"/><br />
      <%--<s:property value="#printShipPackage.rcpCity " escape="false"/>, <s:property value="#printShipPackage.rcpState " escape="false"/>, <s:property value="#printShipPackage.rcpZipCode " escape="false"/><br />--%>
       <%--<s:property value="#printShipPackage.rcpCountry" escape="false"/>--%>
         </td>
    <td valign="top"><strong>IMPORTER</strong><br />
Same as Consignee</td>
  </tr>
  <tr>
    <td><div align="center"><strong>COUNTRY<br />
    OF MFR.</strong></div></td>
    <td><div align="center"><strong>&nbsp;DESCRIPTION  OF GOODS&nbsp;</strong></div></td>
    <td><div align="center"><strong>WEIGHT<br />
    </strong>(LBS) </div></td>
    <td><div align="center"><strong>QTY&nbsp;</strong></div></td>
    <td><strong>UNIT<br />
    VALUE</strong></td>
    <td><strong>COMMODITY<br />
VALUE</strong></td>
  </tr>
  <tr>
    <td width="137" align="right">USA </td>
    <td width="298">	
    	<s:if test="#printShipPackage.ciItemDesc=='Other'">
    		<s:property value="#printShipPackage.ciItemOtherDesc "/>
    	</s:if>
    	<s:else>
    		<s:property value="#printShipPackage.ciItemDesc "/>
    	</s:else>
    </td>
    <td width="117"><div align="right"><s:property value="#printShipPackage.actualWeight "/></div></td>
    <s:set var="unitvalue" scope="page" value="#printShipPackage.unitvalue"/>
      <%
           Double unitvalue = (Double)pageContext.getAttribute("unitvalue");
           DecimalFormat tf = new DecimalFormat("#.000000");
		   String unitvalues = tf.format(unitvalue);
      %>
    <td width="32"><div align="right"><s:property value="#printShipPackage.quty "/></div></td>
    <td><div align="right"><%=unitvalues%></div></td>
    <td><div align="right">$<s:if test="#printShipPackage.insuranceValue != null"><s:property value="#printShipPackage.insuranceValue "/></s:if><s:else>0.00</s:else></div></td>
  </tr>
  <tr>
    <td colspan="5" align="right">FREIGHT</td>
    <td align="right">$0.00</td>
  </tr>
  <tr>
    <td colspan="5" align="right">INSURANCE</td>
    <td align="right"></td>
  </tr>
  <tr>
    <td colspan="5" align="right">ADDITIONAL CHARGES</td>
    <td align="right">$<s:if test="#printShipPackage.adtlCustomerCharge != null"><s:property value="#printShipPackage.adtlCustomerCharge "/></s:if><s:else>0.0</s:else></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><strong>TOTAL</strong><br />
<div align="right"><s:property value="#printShipPackage.actualWeight "/></div></td>
    <td>&nbsp;</td>
    <td><strong>TOTAL CUSTOMS VALUE </strong><br />
<div align="right">$<s:if test="#printShipPackage.insuranceValue != null"><s:property value="#printShipPackage.insuranceValue "/></s:if><s:else>0.0</s:else></div> </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4">These commodities,  technology, or software were exported from the United States in accordance with  the Export Administration regulations. Diversion contrary to U.S. Law  prohibited. </td>
    <td colspan="2"><p align="right"><strong>Terms of  Sale:</strong> <br />
      Free Carrier(FCA/FOB) </p>
      <div align="center">
        <hr size="2" width="100%" align="center" />
      </div>
      <strong>TOTAL INVOICE VALUE</strong> <br />
<div align="right"><s:property value="#printShipPackage.insuranceValue "/></div></td>
  </tr>
  <tr>
    <td colspan="6"><p>SIGNATURE OF SHIPPER/EXPORTER:<br />
      I declare that all the information  contained in this invoice is true and correct. </p>
____________________________________________________ <br />
GenScript Customer Services Date:<s:date name="#printShipPackage.shipmentDate" format="yyyy-MM-dd"/> </td>
  </tr>
</table>
<br/>
<br/>
<br/>
</s:iterator>
</s:if>
<s:else>
No data.
</s:else>
</center>
</body>
</html>