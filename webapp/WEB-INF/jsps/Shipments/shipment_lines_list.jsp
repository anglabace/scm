<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="stylesheet/scm.css" rel="stylesheet" type="text/css" />
<link href="stylesheet/table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<link href="stylesheet/tab-view.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<link href="stylesheet/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
  
    <link href="greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
<style type="text/css"> 
<!--
.content .scm #dhtmlgoodies_tabView1 .dhtmlgoodies_aTab .General_table fieldset {
	margin: 4px;
}
-->
</style>
<script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery.js"></script>
<script> 
$(function(){
		  $("#check_1").click(function(){
							  							 
		  if ($("#check_1").val()!='Ready to Ship')
		   {
			   $("#cancel_ship").attr("disabled","disabled") 
		   }
		   else
		   {
			   $("#cancel_ship").attr("disabled",false)  
		   }
		    })
		   })
</script>
</head>
 
<body class="content">
<div class="scm">
<div id="dhtmlgoodies_tabView1">
	<div class="dhtmlgoodies_aTab">
     <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table">
    <tr>
      <th width="67"> Line No </th>
      <th width="98">Status</th>
      <th width="125">Order No</th>
      <th width="102">Item No</th>
      <th width="207">Proudct/Service</th>
      <th width="73">Order Qty</th>
      <th width="97">Ship Qty</th>
      <th width="88">Order Size</th>
      <th>Ship Size</th>
      </tr>
  </table>
	  <div class="frame_box" style="height:170px; ">
	    <table width="955" border="0" cellspacing="0" cellpadding="0" class="list_table2">
	      <tr>
	        <td width="67" align="center"><a href="shipment_linel.html">1</a></td>
	        <td width="98">Drafted</td>
	        <td width="125">2357</td>
	        <td width="102">2</td>
	        <td width="207">Beta Amyloid Petides</td>
	        <td width="73">&nbsp;</td>
	        <td width="97">&nbsp;</td>
	        <td width="88">&nbsp;</td>
	        <td>&nbsp;</td>
          </tr>
        </table>
	  </div>
    
    <div align="center" style="padding:0px;">
      <input name="Submit" type="submit" class="style_botton" value="New"/>
    </div>
    </div>
    <div class="dhtmlgoodies_aTab">
     <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table">
    <tr>
      <th width="62">Pkg No </th>
      <th width="114">Tracking No</th>
      <th width="201">Description</th>
      <th width="75">Status</th>
      <th width="68">Ship Via</th>
      <th width="68">Ship To</th>
      <th width="68">Ship Date</th>
      <th width="92">Shipping Clerk</th>
      <th>Warehouse </th>
      <th width="92">Packaging Error</th>
      </tr>
  </table>
	  <div class="frame_box" style="height:200px; ">
	    <table width="955" border="0" cellspacing="0" cellpadding="0" class="list_table2">
	      <tr>
	        <td width="62" align="center"><a href="ship_conf_detail.html">20</a></td>
	        <td width="114">1Z8V41V86696064186</td>
	        <td width="201">Order #9261888:3 items;Order #9261900:</td>
	        <td width="75">Ready to Ship</td>
	        <td width="68">&nbsp;</td>
	        <td width="68">&nbsp;</td>
	        <td width="68">&nbsp;</td>
	        <td width="92">&nbsp;</td>
	        <td>&nbsp;</td>
	        <td width="92">NO</td>
          </tr>
        </table>
	  </div>
  </div>
 </div>
<script type="text/javascript"> 
initTabs('dhtmlgoodies_tabView1',Array('Shipment Lines','Packages'),0,998,250);
</script>

</body>
</html>

