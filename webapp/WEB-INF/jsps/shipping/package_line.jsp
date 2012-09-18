<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="stylesheet/scm.css" rel="stylesheet" type="text/css" />
<link href="stylesheet/table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="js/ajax.js"></script>
<script language="javascript" type="text/javascript" src="js/tab-view.js"></script>
<link href="stylesheet/tab-view.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="js/TabbedPanels.js"></script>
<link href="stylesheet/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
        var GB_ROOT_DIR = "./greybox/";
    </script>
 
    <script type="text/javascript" src="greybox/AJS.js"></script>
    <script type="text/javascript" src="greybox/AJS_fx.js"></script>
    <script type="text/javascript" src="greybox/gb_scripts.js"></script>
    <link href="greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
    
    
      <script> 
  
function openc(str1,str2)
{
  if (document.getElementById(str1).style.display=="none")
  {
    document.getElementById(str2).src="images/ad.gif";
    document.getElementById(str1).style.display="block";
  }
  else
  {
	  document.getElementById(str2).src="images/ar.gif";
    document.getElementById(str1).style.display="none";
  }
  
}
function onclick1()
{
  document.cookie="work_o_Antibody";
  window.location.href="work_order_trans.html";
}
 
</script> 
    
<style type="text/css"> 
<!--
.content .scm #dhtmlgoodies_tabView1 .dhtmlgoodies_aTab .General_table fieldset {
	margin: 4px;
}
 
.disp{
   display:none;
   margin-left:40px;
}
-->
</style>
 
</head>
 
<body class="content">
 
<div class="scm">
<div class="title_content">
  <div class="title">Package Line #2</div>
</div>
<div class="input_box">
		  <div class="content_box">
 
		    <form enctype="multipart/form-data" class="niceform">
		      <table width="100%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		          <td><table border="0" cellpadding="0" cellspacing="0" class="General_table">
		            <tr>
		              <th width="134">Package Lines  No</th>
		              <td width="380"><input name="textfield5" type="text" class="NFText" value="${eDto.sid }" size="25" /></td>
		              <th width="116">Package No</th>
		              <td width="191"><input name="textfield3" type="text" class="NFText" value="${eDto.sid }" size="25" /></td>
	                </tr>
                          <tr>
		              <th valign="top">Shipment No</th>
		              <td><input name="textfield2" type="text" class="NFText" value="${eDto.sid }" size="25" /></td>
		              <th>Shipment Line No</th>
		              <td><input name="textfield21" type="text" class="NFText" value="${eDto.sid }" size="25" /></td>
	                </tr>
		            <tr>
		              <th valign="top">Order No</th>
		              <td><input name="textfield2" type="text" class="NFText" value="${eDto.sid }" size="25" />
	                  <input type="button" name="Submit6" class="style_botton2" value="View Order" /></td>
		              <th>Item No</th>
		              <td><input name="textfield21" type="text" class="NFText" value="12" size="25" /></td>
	                </tr>
		            <tr>
		              <th valign="top">Product/Service</th>
		              <td><input name="textfield9" type="text" class="NFText" size="60" /></td>
		              <th>&nbsp;</th>
		              <td>&nbsp;</td>
	                </tr>
		            <tr>
		              <th>Quantity</th>
		              <td><input name="textfield" type="text" class="NFText" value="12" size="25" /></td>
		              <th>Quantity UOM</th>
		              <td><input name="textfield28" type="text" class="NFText" size="25" /></td>
	                </tr>
                            <tr>
		              <th>Size</th>
		              <td><input name="textfield26" type="text" class="NFText" value="12" size="25" /></td>
		              <th>Size UOM</th>
		              <td><input name="textfield6" type="text" class="NFText" size="25" /></td>
	                </tr>
		            <tr>
		              <th>&nbsp;</th>
		              <td>&nbsp;</td>
		              <th>&nbsp;</th>
		              <td>&nbsp;</td>
	                </tr>
	              </table></td>
	            </tr>
		        <tr>
		          <td>
                
              <div class="invoice_title" >Packing Error</div>
<div id="Contact_Info" class="disp" style="display:block;">
<table  border="0" cellpadding="0" cellspacing="0" class="General_table">
  <tr>
    <th width="93">Qty Missing</th>
    <td width="380"><input name="textfield4" type="text" class="NFText" value="12" size="25" maxlength="10" onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
								onfocus="this.select()"/></td>
    <th width="118">Size Missing</th>
    <td width="198"><input name="textfield22" type="text" class="NFText" size="25" maxlength="10" onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
								onfocus="this.select()"/></td>
  </tr>
 
  <tr>
    <th><div align="center"></div>
      Comment</th>
    <td colspan="3"><textarea name="textarea6" cols="" rows=""    class="content_textarea" style="margin-top:5px; height:50px;width:323px;" id='con_text2'></textarea></td>
    </tr>
</table>
</div>
<div id="product_docu" class="disp" style="display:none;">
  <table  border="0" cellpadding="0" cellspacing="0" class="General_table">
  <tr>
    <th width="130">Type</th>
    <td width="490"><select name="select9" style="width:207px;">
      <option>COA</option>
    </select></td>
    <th width="193">&nbsp;</th>
    </tr>
 
  <tr>
    <th>File</th>
    <td><input type="file" name="fileField" id="fileField" />
      <input type="button" name="Submit4" class="style_botton" value="Upload" /><input type="button" name="Submit4" class="style_botton" value="Delete" /></td>
    <td><input type="checkbox" name="checkbox3" id="disable2" />
      QC 
      
      Passed</td>
    </tr>
      <tr>
    <th>&nbsp;</th>
    <td colspan="2" style="padding-left:0px;"><table border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><input type="checkbox" name="checkbox4" id="checkbox3" /></td>
        <td>MSDS:Product1.jpg</td>
        <td width="80"><div align="right">Uploaded By</div></td>
        <td><input name="textfield14" type="text" class="NFText" /></td>
        <td width="100"><div align="right">Uploaded Date</div></td>
        <td><input name="textfield15" type="text" class="NFText" /></td>
        </tr>
      <tr>
        <td><input type="checkbox" name="checkbox5" id="checkbox4" /></td>
        <td>COA:Product2.jpg </td>
        <td><div align="right">Uploaded By</div></td>
        <td><input name="textfield16" type="text" class="NFText" /></td>
        <td><div align="right">Uploaded Date</div></td>
        <td><input name="textfield17" type="text" class="NFText" /></td>
        </tr>
      <tr>
        <td><input type="checkbox" name="checkbox6" id="checkbox5" /></td>
        <td>HPLC:Product3.jpg</td>
        <td><div align="right">Uploaded By</div></td>
        <td><input name="textfield24" type="text" class="NFText" /></td>
        <td><div align="right">Uploaded Date</div></td>
        <td><input name="textfield18" type="text" class="NFText" /></td>
        </tr>
    </table></td>
    </tr>
</table>
</div>
                  </td>
	            </tr>
	          </table>
		    </form>
		</div>
  </div>
 
<div class="button_box">
      <input type="submit" name="Submit123"  value="Save" class="search_input" id="save1"/>
      <input type="submit" name="Submit124" value="Cancel" class="search_input" onclick='window.history.go(-1)' id="cancel1"/>
</div>
</div>	
</body>
</html>