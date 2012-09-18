<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Workstation</title>
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />

<link type="text/css"
	href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}ajax.js"></script>
<script src="${global_js_url}table.js" type="text/javascript"></script>
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
	
<script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>
<script type="text/javascript" language="javascript">
function searchMana(){
	var orderNo = $("#orderNo").val();
	$("#orderNo").attr("value",$.trim(orderNo));
	var purchaseOrderNo = $("#purchaseOrderNo").val();
	$("#purchaseOrderNo").attr("value",$.trim(purchaseOrderNo));
var SalesOrderNo= document.getElementById('orderNo').value;
var PurchaseOrderNo = document.getElementById('purchaseOrderNo').value;
			if(isNaN(SalesOrderNo)){
				alert("SalesOrderNo must is number !");
				return false;
			}else if(isNaN(PurchaseOrderNo)){
				alert("PurchaseOrderNo must is number !");
				return false;
			}else {
					document.manaSearchForm.submit();
				//return true;
			}

}
function refreshList(){
	//document.forms[0].action="workstation/work_station.action";
	document.forms[0].submit();
}
</script>
</head>
<body>
<form name="manaSearchForm" id="manaSearchForm" action="work_station!mwSearch.action" target="orderItem" >
<table  border="0" cellpadding="0" cellspacing="0" class="General_table">    
    <tr>
          <td><div id="warehouseName" >Warehouse</div></td>
          <td>
            <select name="orderItemSrchDTO.warehouseName" id="warehouseName"  style="width: 150px">
				<c:forEach items="${wList}" var="o">
					<option value="${o.warehouseId}" selected="selected">
						${o.name}
					</option>
				</c:forEach>
			</select>
          </td>   
      
    <td align="right"><div >Assignment Status</div></td>
         <td>
         <select name="orderItemSrchDTO.yesOrNo" id="orderItemSrchDTO.yesOrNo" style="width:128px">
         	<option value="">ALL</option>
             <option value="1" >YES</option>
             <option value="0">NO</option>
          </select></td> 
             
          <td><div  id="paramOrderNo">Sales Order No</div></td>
          <td><input name="orderItemSrchDTO.orderNo" id="orderNo" type="text" class="NFText" size="20"/></td>       
      </tr>
 <tr>
          <td align="right"><div id="relationType">Item Type</div></td>
          <td><select id="typeName" name="typeName"
								style="width: 157px;">
								<option value="">ALL</option>
									<s:iterator status="allcls" value="#request.allcls">
										<option value="<s:property value="key"/>">
											<s:property value="value" />
										</option>
									</s:iterator>
							</select> 
       <!--<select name="orderItemSrchDTO.type" id="type"  style="width: 150px">
       			<option value="">ALL</option>
       			<option value="PRODUCT">PRODUCT</option>
       			<option value="SERVICE">SERVICE</option>
							<c:forEach var="wh" items="${pList}">
								<option value="${wh.name}">
									product_${wh.name}
								</option>
							</c:forEach>						
							<c:forEach var="wh" items="${sList}">
								<option value="${wh.name}">
									service_${wh.name}
								</option>
							</c:forEach>
							</select>	  	   
          --></td>            
          <td align="right"><div id="poNo" >US PO NO</div> </td>
          <td><input name="orderItemSrchDTO.purchaseOrderNo" id="purchaseOrderNo" type="text" class="NFText" size="20" /></td>                               
        </tr>
        <tr>
           <td></td>
          <td><input type="button" name="searchButton" value="Search" onclick="searchMana();" class="search_input"/></td>
          <td><input type="button" name="refreshButton" id="refreshButton"value="Refresh List" class="search_input" onclick="refreshList();" style="cursor: pointer" mce_style="cursor: pointer" target="searchBody"/> 
          </td>
        </tr>   
    </table>
</form>
</body>
</html>