<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script src="${global_js_url}jquery/jquery.js" type="text/javascript"></script>
<style type="text/css">
<!--　　　
body {
	width:570px; margin:5px auto;
}
-->
</style>
<script language="javascript">
function changeSearchType(selId){
	var _selectedvalue = $("#"+selId).val();
	$("[name='propertyName']").attr("disabled", true);
	$("[id*='_pdiv']").hide();
	$("#"+_selectedvalue+"_pdiv").show();
	$("#"+_selectedvalue+"_propertyName").attr("disabled", false);
}

function selectPdtList(){
	catalogNo = $('[id="item_add"]:radio:checked').attr( 'value' );
	objPdtSvc = parent.document.getElementById("TB_iframeContent").contentWindow;
	objPdtSvc.$("#case_catalogNo").attr("value",catalogNo);
	parent.$('#srchPdtSvcDialog').dialog('destory');
	parent.$('#srchPdtSvcDialog').dialog('close');
}

function beforeSubmit(){
	var searchOperator = $("#searchOperator").val();
	var searchPropertyType = $("#searchPropertyType").val();
	var searchType = $("#searchType").val();
	var propertyName = $("#"+searchType+"_propertyName").val();
	var propertyValue1 = $("#propertyValue1").val();
	var tmpName = "";
	$("#fillterHidden").attr("name", "filter_"+searchOperator+searchPropertyType+"_"+propertyName);
	$("#fillterHidden").attr("value", propertyValue1);
	return true;
}

$(document).ready(function(){
	changeSearchType("searchType");
});
</script>
</head>
<body>
<form method="get" action="" onsubmit="return beforeSubmit();">
	<table width="570" border="0" cellpadding="0" cellspacing="0">
	  <tr>
	    <td>&nbsp;</td>
	    <td>Lookup On </td>
	    <td>Condition</td>
	    <td>Value</td>
	    <td>&nbsp;</td>
	  </tr>
	  <tr>
	    <td>
	    	<s:select id="searchType" name="searchType" onchange="changeSearchType('searchType');" cssStyle="width:100px" list="#{'PRODUCT':'Product Item','SERVICE':'Service Item'}" value="searchType"></s:select>
	    </td>
	    <td>
			<div id="PRODUCT_pdiv" style='display:block'>
				<s:select id="PRODUCT_propertyName" name="propertyName" cssStyle="width:100px" list="#{'name':'Product Name','catalogNo':'Catalog No'}" value="propertyName"></s:select>
			</div>
			<div id="SERVICE_pdiv" style='display:none;'>
				<s:select id="SERVICE_propertyName" name="propertyName" cssStyle="width:100px" list="#{'name':'Service Name'}" value="propertyName" disabled="disabled"></s:select>
			</div>
		</td>
		<td>
		    <s:select id="searchOperator" name="searchOperator" cssStyle="width:100px" list="#{'EQ':'=','GT':'>','GE':'>=','LT':'<','LE':'<=','NE':'!=','LIKE':'LIKE'}" value="searchOperator"></s:select>
	    </td>
	    <td>
	    	<input type="text" id="propertyValue1" name="propertyValue1" class="NFText" size="14" value="${propertyValue1}" />
	    </td>
	    <td>
	    	<input type="hidden" name="" id="fillterHidden" />
	    	<input type="hidden" value="S" id="searchPropertyType" />
	    	<input type="submit" class="style_botton" value="Search" />
	    </td>
	  </tr>
	</table>
</form>

<table width="570" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td colspan="4" style="padding-top:10px;"><table width="550" border="0" cellpadding="0" cellspacing="0" class="list_table" style="border-bottom: 3px solid #b6d2ea">
      <tr>
        <td width="28" class='list_td2'></td>
        <td width="101" align="center" class='list_td2'>Ctlg  No</td>
        <td width="350" align="center" class='list_td2'> Name </td>
        <td align="center" class='list_td2'>Size</td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="4" style="padding-bottom:20px; "><div  style="width:567px; height:150px; overflow:scroll;">
      <table width="550" border="0" cellpadding="0" cellspacing="0" class="list_table">
       <c:set var="rowcount" value="1"></c:set>
      	<s:if test='searchType == "PRODUCT"'>
	      	<s:iterator value="productList" id="item" status="st">
	      	 <c:if test="${rowcount mod 2 == 0}">
                <c:set var="tdclass" value=" class='list_td2'"></c:set>
              </c:if>	
             <c:if test="${rowcount mod 2 == 1}">
                <c:set var="tdclass" value=""></c:set>
              </c:if>
	        <tr>
	          <td width="28" ${tdclass}>
	          	<div align="center" ${tdclass}>
	              <input type="radio"  value="${item.catalogNo}" name="item_add" id="item_add" />
	          	</div>
	          </td>
	          <td width="70" ${tdclass}><div align="center">${item.catalogNo}</div></td>
	          <td width="350" ${tdclass}>${item.name}</td>
	          <td ${tdclass}>${item.size}</td>
	        </tr><c:set var="rowcount" value="${rowcount+1}"></c:set>
	        </s:iterator>
        </s:if>
        <s:else>
        	<s:iterator value="serviceList" id="item" status="st">
	        <tr>
	          <td width="28">
	          	<div align="center">
	              <input type="radio" value="${item.catalogNo}" name="item_add" id="item_add" />
	          	</div>
	          </td>
	          <td width="70"><div align="center">${item.catalogNo}</div></td>
	          <td width="350">${item.name}</td>
	          <td>${item.size}</td>
	        </tr>
	        </s:iterator>
        </s:else>
        
      </table>
    </div>
    </td>
  </tr>
  <tr>
    <td colspan="4">
      <div align="center">
        <input name="Submit" type="submit" class="style_botton"  value="Select" onclick="selectPdtList();" />
        <input type="submit" name="Submit2" value="Close" class="style_botton" onclick="parent.$('#srchPdtSvcDialog').dialog('destory');parent.$('#srchPdtSvcDialog').dialog('close');" />
      </div>
	</td>
  </tr>
</table>  
</body>
</html>