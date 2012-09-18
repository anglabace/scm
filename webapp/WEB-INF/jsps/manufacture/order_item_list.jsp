<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>
<script type="text/javascript">
	function confirm () {
		parent.$("#confirm").attr("disabled",true);
		var itemIds = $("#itemIds").val();
		var id = $("#id").val();
		$.ajax({
			type: "POST",
			url: "workorder_entry!genernateBatchWorkOrders.action",
			data: "itemIds="+itemIds+"&id="+id,
			dataType: 'json',
			success: function(data, textStatus){
		 		if(hasException(data)){	
					parent.$("#confirm").attr("disabled",true);	
				}else{
				  alert(data.message);
				  parent.$('#GenerateBatchWorkOrdersSearchDialog').dialog('close'); 
				  parent.$('#GenerateBatchWorkOrdersDialog').dialog('close'); 
				}
			},
			error: function(xhr, textStatus){
			   alert("System error! Please contact system administrator for help.");
			   parent.$("#confirm").attr("disabled",true);	
			}
		});              
	}

	function check() {
		  var filter_EQI_orderNo = $("#filter_EQI_orderNo").val();
		  $("#filter_EQI_orderNo").attr("value",$.trim(filter_EQI_orderNo));
		  if(isNaN($.trim(filter_EQI_orderNo))) {
			  alert("Please enter a valid Order No.");
			  return false;
		  }
		  var filter_EQI_srcSoNo = $("#filter_EQI_srcSoNo").val();
		  $("#filter_EQI_srcSoNo").attr("value",$.trim(filter_EQI_srcSoNo));
		  if(isNaN($.trim(filter_EQI_srcSoNo))) {
			  alert("Please enter a valid US Order No.");
			  return false;
		  }
		  var filter_EQI_itemNo = $("#filter_EQI_itemNo").val();
		  $("#filter_EQI_itemNo").attr("value",$.trim(filter_EQI_itemNo));
		  if(isNaN($.trim(filter_EQI_itemNo))) {
			  alert("Please enter a valid itemNo.");
			  return false;
		  }
		  return true;
	}
</script>
</head>

<body>
<s:form action="workorder_entry!showOrderItemList"  name="docTempSearch" method="get" onsubmit="return check();">
<table border="0" cellpadding="0" cellspacing="0"
				class="General_table" align="center">
        <tr>
        
          <td>Order No</td>
          <td>
          	<input  id="filter_EQI_orderNo" name="orderNo" value="${orderNo}" type="text" class="NFText" size="20" >
          </td>
           <td>US Order No</td>
          <td>
          	<input  id="filter_EQI_srcSoNo" name="srcSoNo" value="${srcSoNo}" type="text" class="NFText" size="20" >
          </td>
          <td>Item No</td>
          <td>
          	<input  id="filter_EQI_itemNo" name="itemNo" value="${itemNo}" type="text" class="NFText" size="20" >
          	 	
          </td>
        </tr>
        <tr>
        	<td colspan="6" align="center"><input name="Submit3" type="submit" class="style_botton" value="Search" /></td>
        </tr>
        <s:hidden name="today"></s:hidden>
        <s:hidden name="fromDate"></s:hidden>
         <s:hidden name="toDate"></s:hidden>
         <s:hidden name="id"></s:hidden>
</table>
</s:form>
<table border="0" width="100%" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><table width="560" border="0" cellspacing="0" cellpadding="0" class="list_table" >
          <tr>
            <th width="100">Order No</th>
            <th width="100">US Order no</th>
            <th width="123">Item No</th>
            <th width="101">Catalog No</th>
            <th>Create Date </th>
          </tr>
		</table>
		</td>
	</tr>
	<tr>
	<td>
<div style="overflow:scroll; width:577px;height:180px;">
  <table width="560" border="0" cellspacing="0" cellpadding="0" class="list_table" >
  <s:iterator value="itemList" status="index">
  <s:if test="#index.odd">
  	 <tr>
      <td width="100" align="center">${orderNo}</td>
      <td width="100" align="center">${srcSoNo}</td>
      <td  width="123">${itemNo}</td>
      <td  width="101">${catalogNo}</td>
      <td><div align="center">${creationDate}</div></td>
    </tr>
  </s:if>
  <s:else>
  	<tr>
      <td align="center" class="list_td2">${orderNo}</td>
      <td align="center" class="list_td2">${srcSoNo}</td>
      <td class="list_td2">${itemNo}</td>
      <td class="list_td2">${catalogNo}</td>
      <td class="list_td2"><div align="center">${creationDate}</div></td>
    </tr>
  </s:else>
  </s:iterator>
  </table>
</div>
</td>
</tr>
<tr>
	<td>
		<div class="grayr">
			Total:${size}
		</div>
	</td>
</tr>
<s:hidden name="itemIds" id="itemIds"></s:hidden>
<s:hidden name="id" id="id"></s:hidden>
<tr>
<td>
<div  align="center" style="padding:10px;">
  <input type="button" id="confirm" value=" Confirm" class="style_botton" onclick="confirm();"/>
  <input type="reset" value=" Cancel " class="style_botton" onclick="parent.$('#GenerateBatchWorkOrdersSearchDialog').dialog('close');" />
</div></td>
  </tr>
</table>
</body>
</html>
