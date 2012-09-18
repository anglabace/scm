<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="stylesheet/scm.css" rel="stylesheet" type="text/css" />
<link href="stylesheet/table.css" rel="stylesheet" type="text/css" />
 
<script language="javascript" type="text/javascript" src="js/tab-view.js"></script>
<link href="stylesheet/tab-view.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="js/TabbedPanels.js"></script>
<link href="stylesheet/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
  
    <link href="greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
<style type="text/css"> 
<!--
.content .scm #dhtmlgoodies_tabView1 .dhtmlgoodies_aTab .General_table fieldset {
	margin: 4px;
}
-->
</style>
<script language="javascript" type="text/javascript" src="js/newwindow.js"></script>
<script language="javascript" type="text/javascript" src="js/jquery.js"></script>
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
<div id="frame12" style="display:none;" class="hidlayer1">
<iframe id="hidkuan" name="hidkuan" src="kuang.html" width="668" height="425" frameborder="0"  allowtransparency="true"></iframe>
</div>
<div class="scm">
<div class="title_content">
  <div class="title">Shipment  #${ShipmentNo }</div>
</div>
<div class="input_box">
		  <div class="content_box">
 
		    <form class="niceform">
		      <table border="0" cellpadding="0" cellspacing="0" class="General_table">
                <tr>
                  <th width="160">Shipment No</th>
                  <td><input name="textfield" type="text" class="NFText" value="${ShipmentNo }" size="25" readonly="readonly" /></td>
                  <th width="150">Status</th>
                  <td><input name="textfield9" type="text" disabled="disabled" class="NFText" value="${ShipmentNo }" size="25" /></td>
                </tr>
                <tr>
                  <th rowspan="2" valign="top">Ship to</th>
                  <td rowspan="2"><textarea name="textarea2" readonly="readonly" class="content_textarea2">${ShipmentNo }</textarea></td>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
                      <tr>
                  <th>Priority</th>
                  <td><select name="select3" style="width:157px;" id="select3">
                    <option>Medium</option>
                    <option>Medium</option>
                    <option>Medium</option>
                  </select></td>
                  <th>Order Reference</th>
                  <td><input name="textfield4" type="text" class="NFText" value="${ShipmentNo }" size="25" readonly="readonly" />
                    <img src="images/search.gif" alt="" width="16" height="16" /></td>
                </tr>
                  <tr>
                  <th valign="top">Shipping Amount</th>
                  <td><input name="textfield3" type="text" class="NFText" value="${ShipmentNo }" size="25" readonly="readonly" /></td>
                  <th>Currency</th>
                  <td><select name="select5" style="width:157px;" id="select5">
                    <option>USD</option>
                    <option>JPY</option>
                  </select></td>
                </tr>
                  <tr>
                  <th valign="top">Shipping Type</th>
                  <td><select name="select2" style="width:157px;" id="select">
                    <option selected="selected">Carrirer</option>
                    <option>Delivery</option>
                    <option>Pickup</option>
                  </select></td>
                  <th>Shipping Rule</th>
                  <td><select name="select4" style="width:157px;" id="select4">
                    <option>Availability</option>
                    <option>Item Completed</option>
                    <option>Order Completed</option>
                    <option>Overdue</option>
                    <option>Payment Completed</option>
                  </select></td>
                </tr>
                <tr>
                  <th valign="top">Description</th>
                  <td><textarea name="textarea3" readonly="readonly" class="content_textarea2">${ShipmentNo }</textarea></td>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <th>Shipping Clerk</th>
                  <td><select name="select" style="width:157px;" id="select2">
</select></td>
                  <th>Created Date</th>
                  <td><input name="textfield5" type="text" class="NFText" value="${ShipmentNo }" size="25" readonly="readonly" /></td>
                </tr>
                  <tr>
      <th>Modified By </th>
      <td><input name="textfield2" type="text" class="NFText" value="${ShipmentNo }" size="25" readonly="readonly" /></td>
      <th>Modified Date</th>
      <td><input name="textfield7" type="text" class="NFText" value="${ShipmentNo }" size="25" readonly="readonly" /></td>
    </tr>
            
              </table>
            </form>
		</div>
  </div>
 </div>
</body>
</html>