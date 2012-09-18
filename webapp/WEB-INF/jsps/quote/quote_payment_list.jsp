<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"  />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<s:include value="quote_config.jsp"></s:include>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script src="${global_js_url}jquery/jquery.js" type="text/javascript" language="javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.form.js"></script>
<script src="${global_js_url}jquery/jquery.validate.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script src="${global_js_url}/quoteorder/order_quotation_payment.js?v=2" type="text/javascript"></script>

</head>
  
<body class="content" style="background-image:none;background-color:white;">
<input type="hidden" id="quorderStatus" value="${quorderStatus}"/>
    <div id="dhtmlgoodies_tabView2">
	    <!-------------------------------Credit Card-------------------------------------->
	      <div class="dhtmlgoodies_aTab">
		      <div id="ccDiv">
		         <form id="ccForm" name="ccForm">
		         	
		         </form>
		        </div>
	      </div>
	      
	    <!----------------------PO---------------------->
	      <div class="dhtmlgoodies_aTab">	
		      <div id="poDiv">
		      	<form id="poForm" name="poForm" enctype="multipart/form-data" method="post">
		      		
		    	</form>
		        <br />
		      </div>
	      </div>
	      <!--------------------------------- payment list  --------------------->
	      <div class="dhtmlgoodies_aTab">
	       
	        <!-- 没有结果则显示以下  -->
	        <table width="100%" height="95%" border="0" cellpadding="0" cellspacing="0" id="noPlaymentListBody" style="display:none" class="pay_table">
	      		<tr>
	      			
	      		</tr>
	      	</table>
	      </div>
	    </div>
    <script>
    	initTabs('dhtmlgoodies_tabView2',Array('Credit Card','PO','All Payments'), 2, 450, 155);	
    </script>
</body>
</html>