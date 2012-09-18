<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
</head>
<script>   
function select_price_picker()
{
	var select_info = $(":radio[checked]").val();
	if(!select_info){
		alert("Please select one item at least");
		return;
	}
	var catalogInfo = select_info.split("::", 3);
	
	if(parent.$('#priceCreateFormIframe').get(0)
			&& parent.$('#priceCreateFormIframe').get(0).contentWindow.document.getElementById('name'))
	{
		//Set the catalogName and catalogNo in special price create form
		parent.$('#priceCreateFormIframe').get(0).contentWindow.document.getElementById('name').value = catalogInfo[1];				
		parent.$('#priceCreateFormIframe').get(0).contentWindow.document.getElementById('catalogNo').value = catalogInfo[0];
		parent.$('#priceCreateFormIframe').get(0).contentWindow.document.getElementById('standardPrice').value = catalogInfo[2];
		parent.$('#priceCreateFormIframe').get(0).contentWindow.document.getElementById('discount').value = "";
		parent.$('#priceCreateFormIframe').get(0).contentWindow.document.getElementById('unitPrice').value = "";
		//close the product search dialog window
		parent.$('#catalogSearchDialog').dialog('close');
	}else{
		alert("Please select one item to continue your operation.");
	}
	
}

function close_price_picker()
{
	parent.$('#catalogSearchDialog').dialog('close');
}

function set_selected(id, val)
{
	var obj = document.getElementById(id);
	for(var i=0;i<obj.options.length;i++)
	if(obj.options[i].value == val)
	obj.options[i].selected = true;
}

function beforeSubmit(){
	var searchOperator = $("#searchOperator").val();
	var searchPropertyType = $("#searchPropertyType").val();
	var propertyName = $("#propertyName").val();
	var propertyValue1 = $("#propertyValue1").val();
	var tmpName = "";
	$("#fillterHidden").attr("name", "filter_"+searchOperator+searchPropertyType+"_"+propertyName);
	$("#fillterHidden").attr("value", propertyValue1);
	return true;
}
</script>
<body>
<form id="price_picker_form" action="cust_spcl_prc!pickCatalog.action" method="get" onsubmit="return beforeSubmit();">
<input type="hidden" value="${custNo}" name="custNo" />
<input type="hidden" value="${sessCustNo}" name="sessCustNo" />
<input type="hidden" value="${catalogId}" name="catalogId" />
<input type="hidden" value="${listType}" name="listType" />
<input type="hidden" value="" id="fillterHidden" />

<input type="hidden" value="S" id="searchPropertyType" />
<table width="507" border="0" cellspacing="0" cellpadding="0" style="margin:15px 5px 0px 10px;">
  <tr>
    <td width="183">Lookup On </td>
    <td width="182">Condition</td>
    <td width="133">Value</td>
  </tr>
  <tr>
    <td>
   <div id="tb4" style='display:block'>
		<select id="propertyName" name="propertyName">
      		<option value="catalogNo">catalogNo</option>
        </select>
   </div>
   </td>
   <td>
   		<s:select name="searchOperator" id="searchOperator" list="#{'EQ':'=','GT':'>','GE':'>=','LT':'<','LE':'<=','NE':'!=','LIKE':'LIKE','LIKE':'LIKE'}" ></s:select>
   </td>
   <td>
      <input name="propertyValue1" id="propertyValue1" type="text" class="NFText" size="20" value="${propertyValue1}"/>
   </td>
  </tr>
  <tr>
    <td>Sort By</td>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td>
    <select name="orderBy" id="orderBy">
      <option value="catalogNo">Catalog No</option>
      <option value="itemName">Item Name</option>
    </select>
    </td>
    <td colspan="2">
    	<input type="submit" value="Search" />
    </td>
  </tr>
  <tr>
    <td colspan="3" style="padding-top:10px;">
    <table width="490" border="0" cellpadding="0" cellspacing="0" class="list_table">
      <tr>
        <td width="31"  style="border-top:1px solid #CCCCCC" class='list_td2'> &nbsp;</td>
        <td width="60"  style="border-top:1px solid #CCCCCC" class='list_td2'>Catalog No</td>
        <td width="300"  style="border-top:1px solid #CCCCCC" class='list_td2' align="center">Item Name </td>
       	<td width="50"  style="border-top:1px solid #CCCCCC" class='list_td2'>Size</td>
        <td width="50"  style="border-top:1px solid #CCCCCC" class='list_td2'>Price</td>
      </tr>
    </table>
   </td>
  </tr>
  <tr>
    <td colspan="3" style="padding-bottom:10px; ">
    <div  style="width:505px; height:175px; overflow:scroll;border:1px solid #cccccc;">
      <table width="490" border="0" cellpadding="0" cellspacing="0" class="list_table" >
    	<c:set var="rowcount" value="1"></c:set>
      <s:if test="productList != null">
      <s:iterator value="productList" id="item">
      <c:if test="${rowcount mod 2 == 0}">
                <c:set var="tdclass" value=" class='list_td2'"></c:set>
              </c:if>	
             <c:if test="${rowcount mod 2 == 1}">
                <c:set var="tdclass" value=""></c:set>
              </c:if>
        <tr>
          <td width="30" ${tdclass}>
          	<div align="center">
            	<input type="radio" value="${item.catalogNo}::${item.itemName}::${item.unitPrice}" name="productId" id="${item.productId}" />
          	 </div>
          </td>
          <td  width="60" ${tdclass}><div align="center">&nbsp;${item.catalogNo}</div></td>
          <td width="300" title="${item.itemName}" ${tdclass}>&nbsp;
          <s:if test="%{itemName.length()>40}"><s:property value="itemName.substring(0,40)+'...'"/></s:if><s:else><s:property value="itemName"/></s:else>
          </td>
          <td width="50" ${tdclass}>&nbsp;${item.size}&nbsp;${item.uom}</td>
          <td width="50" ${tdclass}><div align="right">&nbsp;${item.symbol}${item.unitPrice}</div>
          </td>
        </tr><c:set var="rowcount" value="${rowcount+1}"></c:set>
        </s:iterator>
        </s:if>	
        <s:else>
        <s:iterator value="serviceOfServcategoryBeanList" id="item">
      	<c:if test="${rowcount mod 2 == 0}">
                <c:set var="tdclass" value=" class='list_td2'"></c:set>
              </c:if>	
             <c:if test="${rowcount mod 2 == 1}">
                <c:set var="tdclass" value=""></c:set>
              </c:if>
        <tr>
          <td width="30" ${tdclass}>
          	<div align="center">
            	<input type="radio" value="${item.catalogNo}::${item.itemName}::${item.unitPrice}" name="serviceId" id="${item.serviceId}" />
          	 </div>
          </td>
          <td  width="60" ${tdclass}><div align="center">&nbsp;${item.catalogNo}</div></td>
          <td width="300" title="${item.itemName}" ${tdclass}>&nbsp;
          <s:if test="%{itemName.length()>40}"><s:property value="itemName.substring(0,40)+'...'"/></s:if><s:else><s:property value="itemName"/></s:else>
          </td>
          <td width="50" ${tdclass}>&nbsp;${item.size}&nbsp;${item.uom}</td>
          <td width="50" ${tdclass}><div align="right">&nbsp;${item.symbol}${item.unitPrice}</div>
          </td>
        </tr><c:set var="rowcount" value="${rowcount+1}"></c:set>
        </s:iterator>
        </s:else>
      </table>
    </div>
    <div class="grayr">
		<jsp:include page="/common/db_pager.jsp">
		  <jsp:param value="${ctx}/cust_spcl_prc!pickCatalog.action" name="moduleURL"/>
		</jsp:include>	
	</div>
    </td>
  </tr>
  <tr>
    <td colspan="3">
      <div align="center">
        <input type="button" value="Select" onclick="select_price_picker();" />
        <input type="button" value="Close" onclick="close_price_picker();" />
      </div>
    </td>
  </tr>
</table>
</form>
<script type="text/javascript">
set_selected('orderBy', '${orderBy}');
set_selected('searchOperator', '${searchOperator}');
</script>
</body>
</html>