<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base id="myBaseId" href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>scm special price</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
   
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>

</head>
<body>
	<div class="">
	<form method="post" action="" id="spcl_form" name="spcl_form" title="xxx">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
      <tr>
        <td align="right">Customer Discount </td>
        <td>
        	<input id="specialDiscount" name="specialDiscount" type="text" value="${specialDiscount}" class="NFText" size="5" />%
        </td>
        <td align="right">Start Date </td>
        <td >
        	<input name="discEffFrom" value="<s:date name='discEffFrom' format='yyyy-MM-dd'/>" type="text" class="NFText datepicker" size="15" id="discEffFrom" readonly="readonly" />
        </td>
        <td align="right">End Date </td>
        <td><input name="discEffTo" value="<s:date name='discEffTo' format='yyyy-MM-dd'/>" type="text" class="NFText datepicker" size="15" id="discEffTo" readonly="readonly" />
            </td>
        <td align="right">
        	Unapplied Credit</td>
        	<td>
          		<input name="unappliedCredit" type="text" class="NFText" value="" size="15" value="${unappliedCredit}" readonly="readonly" disabled="disabled" />
        	</td>
        	<td>
        	<input type="submit" name="Submit" class="style_botton2" value="View History" /></td>
        	
      </tr>
        <tr>
        <td align="right">Special Price Catalog
        </td>
        <td colspan="8">
            <s:select id="priceCatalogId" name="priceCatalogId" list="catalogList" listKey="catalogId" listValue="catalogName" value="priceCatalogId"></s:select>
        </td>
      </tr>
    </table>
    </form>
    
    <div id="dhtmlgoodies_tabView2" >
      
    	<div class="dhtmlgoodies_aTab">
    		<iframe id="pdtPriceIframe" src="customer/cust_spcl_prc!list.action?listType=PRODUCT&custNo=${custNo}&sessCustNo=${sessCustNo}" style="overflow-x:hidden;overflow-y:hidden;" scrolling="no" width="958" height="235" frameborder="0"></iframe>
        </div>
		
	    <div class="dhtmlgoodies_aTab">
	     	<iframe id="serPriceIframe" src="customer/cust_spcl_prc!list.action?listType=SERVICE&custNo=${custNo}&sessCustNo=${sessCustNo}" style="overflow-x:hidden;overflow-y:hidden;" scrolling="no" width="958" height="235" frameborder="0"></iframe>	
	    </div>
    </div>
 </div>
 <script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	initTabs('dhtmlgoodies_tabView2',  Array('Products','Services'),0,940,200);
	$('.datepicker').each(function(){
		$(this).datepicker({
			dateFormat: 'yy-mm-dd',
			changeMonth: true,
			changeYear: true
		});
	})
});
</script>
</body>
</html>