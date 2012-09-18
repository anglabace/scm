<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>User list</title>
		<base href="${global_url}" />
		<link href="stylesheet/table.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript"
			src="${global_js_url}ajax.js"></script>
		<script language="javascript" type="text/javascript"
			src="${global_js_url}tab-view.js"></script>
		<script language="javascript" type="text/javascript"
			src="${global_js_url}TabbedPanels.js"></script>
		<script type="text/javascript">var GB_ROOT_DIR = "./greybox/";</script>
		<script type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
		<script type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
		<script type="text/javascript"
			src="${global_js_url}greybox/gb_scripts.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>

</head>

<body>
  <c:if test="${empty listOrder || listOrder ==null}">
  	<table width="730" border="0">
  	<tr>
  		<td align="center">No Data!</td>
  	</tr>
  	</table>
  </c:if>
 <c:if test="${not empty listOrder && listOrder !=null}">
<table width="660" border="0" cellspacing="3" cellpadding="0" bgcolor="#96BDEA">
  <tr>
    <td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">

      <tr>
        <td height="39" align="center" valign="top" background="greybox/header_bg.gif">
        <div class="line_l">Create Receiving Report</div>
          </td>
      </tr>
      <tr>
        <td>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="459" valign="top">

<div class="print_content_all_new">
<div class="print_content_new">
<div class="print_head">

<div class="print_headtitle_new" algin="center" >Receiving Report <br />
  <span class="print_txt" >GenScript USA Inc</span></div>
<div class="print_headright">
<div class="print_headdate">Date Time:  ${now }</div>
<div class="print_headdate">${logSize}</div>
</div>
</div>
<div class="print_table">

<c:forEach items="${listOrder}" var="listOrder">

		<c:if test="${rowcount mod 2 == 0}">
			<c:set var="tdclass" value=" class='list_td2'"></c:set>
		</c:if>
		<c:if test="${rowcount mod 2 == 1}">
			<c:set var="tdclass" value=""></c:set>
		</c:if>
<table width="700" border="0" cellspacing="0" cellpadding="0" class="list_table">
	  <caption>
	    <c:if test="${type=='SALES'}">
	    Purchase Order No:</c:if>
	    <c:if test="${type=='MANUFACTURING'}">
	    Work Order No:</c:if>${listOrder}

	  </caption>
	  <tr>
	    <th width="70">Item No</th>
	    <th width="70">Catalog No</th>
	    <th width="65">Name</th>
	    <th width="88">Qty Expected</th>
	    <th width="76">Qty Received</th>
	    <th width="87">Size Expected</th>
	    <th width="84">Size Received</th>
	    <th width="84">Date Received</th>
	    <th width="75">Received By</th>
	
	  </tr>
	  <c:forEach items="${listRl}" var="listRl">
	  <c:if test="${listRl.orderNo==listOrder}">
	  <tr>
	  
	      <td class="print_b">&nbsp;${listRl.itemNo }</td>
	      <td>&nbsp;${listRl.catalogNo }</td>
	      <td>&nbsp;${listRl.name }</td>
	      <td>&nbsp;${listRl.sumQty }</td>
	      <td>&nbsp;${listRl.qtyReceived }</td>
	      <td class="print_r">&nbsp;
	      ${fn:endsWith(listRl.sumSize,'.0')||fn:endsWith(listRl.sumSize,'.00')||fn:endsWith(listRl.sumSize,'.000')?fn:substring(listRl.sumSize,0,fn:indexOf(listRl.sumSize,".")):listRl.sumSize}&nbsp;
	      &nbsp;${listRl.sizeUom }</td>
	      <td class="print_r">&nbsp;
	      ${fn:endsWith(listRl.sizeReceived,'.0')||fn:endsWith(listRl.sizeReceived,'.00')||fn:endsWith(listRl.sizeReceived,'.000')?fn:substring(listRl.sizeReceived,0,fn:indexOf(listRl.sizeReceived,".")):listRl.sizeReceived}&nbsp;
	      &nbsp;${listRl.sizeUom }</td>
	      <td class="print_r">&nbsp;${listRl.reveivingDate }</td>
	      <td class="print_r">&nbsp;${listRl.userName }</td>
	    </tr>
	    </c:if>
	    </c:forEach>
</table>
</c:forEach>
</div>
<div class="print_foot">
<div class="print_foottop"></div>
</div>
</div>
</div> 
            
            </td>
          </tr>
        </table></td>
      </tr>
    </table></td>

  </tr>
</table>
</c:if>

</body>
</html>