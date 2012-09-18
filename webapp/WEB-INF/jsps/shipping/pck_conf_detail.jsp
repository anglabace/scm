<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Package Detail</title>
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
  <div class="title">Package Information - # ${pDto.packageId} </div>
</div>
<div class="input_box">
		  <div class="content_box">

		    <form class="niceform">
		      <table border="0" cellpadding="0" cellspacing="0" class="General_table">
                <tr>
                  <th width="160">Package No</th>
                  <td><input name="textfield" type="text" class="NFText" value="${pDto.packageId}" size="25" readonly="readonly" /></td>
                  <th width="150">Status</th>
                  <td><select name="select" style="width:157px;" id="check_1">
                    <option>${pDto.status}</option>
                  </select></td>
                </tr>
                <tr>
                  <th rowspan="2" valign="top">Ship to</th>
                  <td rowspan="2"><textarea name="textarea2" readonly="readonly" class="content_textarea2" >${pDto.status}</textarea></td>
                  <th>Shipment Method</th>
                  <td><input id="packageId" name="packageId" type="text" class="NFText" value="${pDto.packageId}" size="25" readonly="readonly" /></td>
                </tr>
                <tr>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <th valign="top">Shipment Date</th>
                  <td><input name="textfield8" type="text" class="NFText" value="${pDto.packageId}" size="25" /></td>
                  <th>Tracking No</th>
                  <td><input name="textfield6" type="text" class="NFText" value="${pDto.packageId}" size="25" /></td>
                </tr>
                <tr>
                  <th>Shipping Clerk</th>
                  <td><input name="textfield3" type="text" class="NFText" value="${pDto.status}" size="25" readonly="readonly" /></td>
                  <th>Created Date</th>
                  <td><input name="textfield5" type="text" class="NFText" value="${pDto.shipmentDate}" size="25" readonly="readonly" /></td>
                </tr>
                  <tr>
      <th>Modified By </th>
      <td><input name="textfield2" type="text" class="NFText" value="${pDto.packageId}" size="25" readonly="readonly" /></td>
      <th>Modified Date</th>
      <td><input name="textfield7" type="text" class="NFText" value="${pDto.packageId}" size="25" readonly="readonly" /></td>
    </tr>
                  <tr>
                    <td colspan="4" align="center">
                      <input type="button" name="Submit193" value="View/Print Packing Slip" class="search_input2"  onclick="window.openiframe('view_packing_slip.html','734','630','1%')"/>
                    <input type="button" name="Submit" value="Cancel Shipment" class="search_input2" id="cancel_ship"/></td>
                  </tr>
              </table>
            </form>
		</div>
  </div>	

<div id="dhtmlgoodies_tabView1">
	
    <div class="dhtmlgoodies_aTab">
      <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table">
        <tr>
          <th width="50">Line No</th>
          <th width="100">Order No</th>
          <th width="102">Item No</th>
          <th width="261">Name</th>
          <th width="84">Qty</th>
          <th width="84">Size</th>
          <th width="104">Qty Missing</th>
          <th>Size Missing</th>
        </tr>
      </table>
	  <div class="frame_box" style="height:240px; ">
      <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table">

        <tr>
          <td width="50" align="center"><a href="shipments!getPackageline.action?packageId=${pDto.packageId}" target="mainFrame">${pDto.packageId}</td>
          <td width="100">${pDto.status}</td>
          <td width="102">${pDto.packageId}</td>
          <td width="261">${pDto.packageId}</td>
          <td width="84">${pDto.packageId}</td>
          <td width="84">${pDto.packageId}</td>
          <td width="104">${pDto.packageId}</td>
          
          <td>&nbsp;</td>
        </tr>
      </table>
    </div>
    <div class="grayr"><span class="disabled"> < </span><span class="current">1</span><span class="disabled"> > </span></div>
    </div>
  </div>
<script type="text/javascript">
initTabs('dhtmlgoodies_tabView1',Array('Package Lines'),0,998,320);
</script>
<div class="button_box">
      <input type="submit" name="Submit123"  value="Save" class="search_input" />
      <input type="submit" name="Submit124" value="Cancel" class="search_input" onclick="javascript:history.go(-1);" />
</div>
</div>	
</body>
</html>
