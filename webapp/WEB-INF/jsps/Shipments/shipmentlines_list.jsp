<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>User list</title>
		<base href="${global_url}" />
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}greybox/gb_styles.css" rel="stylesheet"
			type="text/css" media="all" />
		<link href="${global_css_url}tab-view.css" rel="stylesheet"
			type="text/css" />
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
	</head>

	<body class="content">
		<script type="text/javascript">
		<c:if test="${request.sign == 1}">
			alert(" You search's user not exist !");
		</c:if>
</script>
		<table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table">
        <tr>
          <th width="50">Line No</th>
		  <th width="50">Status</th>
          <th width="100">Order No</th>
          <th width="100">Item No</th>
          <th width="261">Proudct/Service</th>
          <th width="84">Order Qty</th>
          <th width="84">Ship Qty</th>
          <th width="104">Order Size</th>
          <th>Ship Size</th>
        </tr>
      </table>
	  <div class="frame_box" style="height:240px; ">
	 
      <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table">	
      <c:set var="rowcount" value="1"></c:set>
      <c:forEach var="pageslDTO" items="${list}">
     	 <c:if test="${rowcount mod 2 == 0}">
		 	<c:set var="tdclass" value=" class='list_td2'"></c:set>
		 </c:if>	
		 <c:if test="${rowcount mod 2 == 1}">
		    <c:set var="tdclass" value=""></c:set>
		 </c:if>              
        <tr>
          <td width="50" align="center"><a href="shipments!getSlinedetail.action?lineId=${pageslDTO.lineId }" target="mainFrame" >${pageslDTO.lineId }</a></td>
          <td width="50" align="center">${pageslDTO.status }</td>
          <td width="100" align="center">${pageslDTO.order.orderNo }</td>
          <td width="100" align="center">${pageslDTO.itemNo }</td>
          <td width="261" align="center">product</td>
          <td width="84" align="center">${pageslDTO.itemNo }</td>
          <td width="84" align="center">${pageslDTO.itemNo }</td >
          <td width="104" align="center">${pageslDTO.itemNo }</td>
          <td align="center">${pageslDTO.itemNo }</td>
        </tr>
        <c:set var="rowcount" value="${rowcount+1}"></c:set>
        </c:forEach>
      </table>
    </div>
    <div class="grayr">
				<jsp:include page="/common/db_pager.jsp">
					<jsp:param value="${ctx}/shipments!getShipInfo.action" name="moduleURL" />
				</jsp:include>
			</div>
	</body>
</html>