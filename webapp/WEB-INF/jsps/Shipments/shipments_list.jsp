<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Shipment processing</title>
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<script type="text/javascript">var GB_ROOT_DIR = "./greybox/";</script>
<script type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/gb_scripts.js"></script>

<script type="text/javascript" language="javascript">
			 function listAll(obj, cks){
              if (cks == null)
			  {
			    cks = 'checkboxes';
			    alert("null");
			  }
			
			  var elems = obj.form.getElementsByTagName("INPUT");
			  for (var i=0; i < elems.length; i++)
			  {
			    if (elems[i].name == cks || elems[i].name == cks + "[]")
			    {
			      elems[i].checked = obj.checked;
			    }
			  }
          }
           function subAllDel(){
             document.forms[0].submit();
          }
</script>

</head>

<body class="content">
<script type="text/javascript">
		<c:if test="${request.sign == 1}">
			alert(" You search's Shipments not exist !");
		</c:if>
</script>
<form action="">
<c:if test="${usa=='ok'}">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" style="TABLE-LAYOUT: fixed">
		<tr>
			<td style="padding-right: 17px;"></td>
		</tr>
		<tr>	
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="list_table">
				<tr>
				    <th width="30">
				    <div align="center">
			          <input onclick='listAll(this, "cks")' type="checkbox"/>
			        </div></th>
					  <th width="80">Shipment No</th>
					  <th width="55">Order Ref</th>
				      <th width="45">Priority</th>
				      
				      <th width="50">Status</th>
				      <th width="70">Shipping Amt</th>
				      
				      <th width="60">Ship To</th>
				      <th width="75">Ship via</th>
				      <th width="70">Tracking No</th>
				      <th width="60">Ship Date</th>
				      <th width="80">Shipping Clerk</th>
				      <th width="60">Deliver By</th>
				      <th width="55">US PO NO</th>
				      <th width="55">Cust No</th>
				      <th>Packaging Error</th>
				      
	
				</tr>
			</table>
			<div class="list_box" style="height: 270px;">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="list_table" style="TABLE-LAYOUT: fixed">
				<c:set var="rowcount" value="1"></c:set>
				<s:iterator value="pageShipmentsDTO.result">
	              <c:if test="${rowcount mod 2 == 0}">
	                <c:set var="tdclass" value=" class='list_td2'"></c:set>
	              </c:if>	
	             <c:if test="${rowcount mod 2 == 1}">
	                <c:set var="tdclass" value=""></c:set>
	              </c:if>
					<tr>
					    <td width="30" ${tdclass}><div align="center">
					    
					    <input type="checkbox" name="cks" id="cks" value="${shipmentId }" /></div></td>
					    
						<td width="80" ${tdclass} align="center"><a href="shipments!getShipInfo.action?shipmentNo=${shipmentId }" target="mainFrame">${shipmentNo}</a></td>
					     <td width="55" ${tdclass} title="${orderNo }" >
					    	<c:if test="${fn:length(orderNo)>10}">
								${fn:substring(orderNo,0,8)}...
							</c:if>
							<c:if test="${fn:length(orderNo)<=10}">
								${orderNo }
							</c:if>
					    
					    </td>
					    
					    <td width="45" ${tdclass}>${priority }</td>
					    <td width="50" ${tdclass}>${status }</td>
					    <td width="70" ${tdclass} align="center">${shipAmt }</td>
					   
					    <td width="60" ${tdclass}>
					    	
								${shipTo }
							
					    
					    </td>
					    
					    <td width="75" ${tdclass} title="${shipVia }" >
					    	${shipVia }
					    
					    </td>
					    <td width="70" ${tdclass} title="${trackingNo }" >
					    	<c:if test="${fn:length(trackingNo)>10}">
								${fn:substring(trackingNo,0,10)}...
							</c:if>
							<c:if test="${fn:length(trackingNo)<=10}">
								${trackingNo }
							</c:if>
					    
					    </td>
					    
						<td width="60" ${tdclass}>${shipDate }</td>
						
						<td width="80" ${tdclass} title="${shippingClerk }" >
					    	<c:if test="${fn:length(shippingClerk)>13}">
								${fn:substring(shippingClerk,0,13)}...
							</c:if>
							<c:if test="${fn:length(shippingClerk)<=13}">
								${shippingClerk }
							</c:if>
					    
					    </td>
					    <td width="60" ${tdclass} title="${sendBys }" >
					    	<c:if test="${fn:length(sendBys)>13}">
								${fn:substring(sendBys,0,13)}...
							</c:if>
							<c:if test="${fn:length(sendBys)<=13}">
								${sendBys }
							</c:if>
					    </td>
						
					     <td width="55" ${tdclass}  title="${poNo }">
					     	<c:if test="${fn:length(poNo)>7}">
								${fn:substring(poNo,0,7)}...
							</c:if>
							<c:if test="${fn:length(poNo)<=7}">
								${poNo }
							</c:if>
							</td>
					     <td width="55" ${tdclass}>${CustNo }</td>
					    <td ${tdclass} align="center">${error }</td>
					</tr><c:set var="rowcount" value="${rowcount+1}"></c:set>
				</s:iterator>
			</table>
			</div>
			<c:if test="${count>0}">
				<div class="grayr">
					<jsp:include page="/common/db_pager.jsp">
						<jsp:param value="${ctx}/shipments!shipmentListSearch.action" name="moduleURL" />
					</jsp:include>
				</div>
			</c:if>
			<c:if test="${count<=0}">
				<div class="grayr"><span class="small_b">Total:0</span><span class="disabled"> &lt; 
				</span><span class="current">1</span><span class="disabled"> &gt;</span></div>
			</c:if>
			</td>			
		</tr>
	</table>
</c:if>
<c:if test="${china=='ok'}">
	<table width="1010" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td style="padding-right: 17px;"></td>
		</tr>
		<tr>	
			<td>
			<table width="1010" border="0" align="center" cellpadding="0"
				cellspacing="0" class="list_table">
				<tr>
					  <th width="80">Pkg No</th>
				      <th width="100">Tracking No</th>
				      <th width="85">Description</th>
				      <th width="87">Status</th>
				      <th width="82">Ship Via</th>
				      <th width="120">Ship To</th>
				      <th width="90">Ship Date</th>
				      <th width="90">Shipping Clerk</th>
				      <th width="123">Warehouse</th>
				      <th>Packaging Error</th>
	
				</tr>
			</table>
			<div class="list_box" style="height: 270px;">
			<table width="993" border="0" align="center" cellpadding="0"
				cellspacing="0" class="list_table">
				<c:set var="rowcount" value="1"></c:set>
				<s:iterator value="packagesDTO.result">
	              <c:if test="${rowcount mod 2 == 0}">
	                <c:set var="tdclass" value=" class='list_td2'"></c:set>
	              </c:if>	
	             <c:if test="${rowcount mod 2 == 1}">
	                <c:set var="tdclass" value=""></c:set>
	              </c:if>
					<tr>
						<td width="80" align="center" ${tdclass}><a href="shipments!getPkgInfo.action?packageId=${packageId }" target="mainFrame">${packageId}</a></td>
					 
					    <td width="100" ${tdclass} title="${trackingNo }" >
					    	<c:if test="${fn:length(trackingNo)>15}">
								${fn:substring(trackingNo,0,15)}...
							</c:if>
							<c:if test="${fn:length(trackingNo)<=15}">
								${trackingNo }
							</c:if> 
					    </td>
					    
					    <td width="85" ${tdclass} title="${note }" >
					    	<c:if test="${fn:length(note)>10}">
								${fn:substring(note,0,10)}...
							</c:if>
							<c:if test="${fn:length(note)<=10}">
								${note }
							</c:if> 
					    </td>
					   
					    <td width="87" ${tdclass}>${status }</td>
					    <td width="82" ${tdclass}>${shipMethod }</td>
					    <td width="120" ${tdclass} title="${shiptoAddress }" >
					    	<c:if test="${fn:length(shiptoAddress)>10}">
								${fn:substring(shiptoAddress,0,10)}...
							</c:if>
							<c:if test="${fn:length(shiptoAddress)<=10}">
								${shiptoAddress }
							</c:if> 
					    </td>
					   
					    <td width="90" ${tdclass}>
					    	<s:date format="yyyy-MM-dd" name="shipmentDate"/>
					    </td>
					    <td width="90" ${tdclass} title="${shippingClerk }" >
					    	<c:if test="${fn:length(shippingClerk)>10}">
								${fn:substring(shippingClerk,0,10)}...
							</c:if>
							<c:if test="${fn:length(shippingClerk)<=10}">
								${shippingClerk }
							</c:if> 
					    
					    </td>
						<td width="123" ${tdclass}>${wname }</td>
						<td ${tdclass} align="center">${error }</td>
						
					</tr>
					<c:set var="rowcount" value="${rowcount+1}"></c:set>
				</s:iterator>
			</table>
			</div>
			<c:if test="${count>0}">
				<div class="grayr">
					<jsp:include page="/common/db_pager.jsp">
					<jsp:param value="${ctx}/shipments!shipmentListSearch.action" name="moduleURL" />
				</jsp:include>
				</div>
			</c:if>
			<c:if test="${count<=0}">
				<div class="grayr"><span class="small_b">Total:0</span><span class="disabled"> &lt; 
				</span><span class="current">1</span><span class="disabled"> &gt;</span></div>
			</c:if>
			</td>			
		</tr>
	</table>
</c:if>
</form>
</body>
</html>