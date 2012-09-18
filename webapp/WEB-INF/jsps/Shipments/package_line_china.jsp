<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Shipments</title>
	<base id="myBaseId" href="${global_url}" />
	<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
	<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
	<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
	<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
	<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
	<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
	<link href="greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
	<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
	<link href="stylesheet/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
</head>
<body class="content">
<div class="scm" style="overflow:auto; width: 1100PX">
<div class="title_content">
  <div class="title">Package Line # ${splDto.pkgLineId } </div>
</div>
<div class="input_box">
		  <div class="content_box">
 
		    <form name="fr2" enctype="multipart/form-data" class="niceform" action="shipments!saveShipPackagelines.action">
		      <table width="100%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		          <td><table border="0" cellpadding="0" cellspacing="0" class="General_table">
		            <tr>
		              <th width="134">Package Lines  No</th>
		              <td width="380"><input name="pkgLineId" type="text" class="NFText" value="${splDto.pkgLineId }" size="25" readonly="readonly"/></td>
		              <th width="116">Package No</th>
		              <td width="191"><input name="splDto.shipPackages.packageId" type="text" class="NFText" value="${splDto.shipPackages.packageId }" size="25" readonly="readonly"/></td>
	                </tr>
                          <tr>
		              <th valign="top">Shipment No</th>
		              <td><input name="textfield2" type="text" class="NFText" value="${splDto.shipPackages.shipments.shipmentId }" size="25" readonly="readonly"/></td>
		              <th>Shipment Line No</th>
		              <td><input name="splDto.shipmentLines.lineId" type="text" class="NFText" value="${splDto.shipmentLines.lineId }" size="25" readonly="readonly"/></td>
	                </tr>
		            <tr>
		              <th valign="top">Order No</th>
		              <td><input name="splDto.orderNo" id="orderNo" type="text" class="NFText" value="${splDto.orderNo }" size="25" />
	                  <input name="Submit142" class="style_botton2" value="View Order"
							onclick="javascript:window.open('order/order!edit.action?orderNo=${splDto.orderNo }&operation_method=view');"
							type="button" /></td>
		              <th>Item No</th>
		              <td><input name="splDto.itemNo" id="itemNo" type="text" class="NFText" value="${splDto.itemNo }" size="25" /></td>
	                </tr>
		            <tr>
		              <th valign="top">Product/Service</th>
		              <td><input name="textfield9"  type="text" class="NFText" value="Product" size="60" /></td>
		              <th>&nbsp;</th>
		              <td>&nbsp;</td>
	                </tr>
		            <tr>
		              <th>Quantity</th><!-- orderItems.quantity -->
		              <td><input name="splDto.quantity" id="quantity" type="text" class="NFText"  value="${splDto.quantity }" size="25" /></td>
		              <th>Quantity UOM</th><!-- orderItems.qtyUom  -->
		              <td><input name="splDto.qtyUom" id="qtyUom" type="text" class="NFText"  value="${splDto.qtyUom }" size="25" /></td>
	                </tr>
                            <tr>
		              <th>Size</th><!-- orderItems.size -->
		              <td><input name="splDto.size"  id="size" type="text" class="NFText" value='${fn:endsWith(splDto.size,".0")||fn:endsWith(splDto.size,".00")||fn:endsWith(splDto.size,".000")?fn:substring(splDto.size,0,fn:indexOf(splDto.size,".")):splDto.size}' size="25" /></td>
		              <th>Size UOM</th><!-- orderItems.sizeUom -->
		              <td><input name="splDto.sizeUom" id="sizeUom" type="text" class="NFText" value="${splDto.sizeUom }" size="25" /></td>
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
    <c:forEach items="${splDto.shipPackageErrorList }" var="error">
    <c:set value="${error}" var="er" scope="page" target="com.genscript.gsscm.warehoseshipments.entity"></c:set>
    </c:forEach>
    <td width="380"><input type="hidden" name="errorid" value="${er.id }" /><input name="missingQty" id="missingQty" type="text" class="NFText" value="${er.missingQty }" size="25" /></td>
    <th width="118">Size Missing</th>
    <td width="198"><input name="missingSize" id="missingSize" type="text" class="NFText" value="${er.missingSize }" size="25" /> </td>
  </tr>
 
  <tr>
    <th><div align="center"></div>
      Comment</th>
    <td colspan="3">
        
    	<textarea name="reason" id="reason" value="" cols="" rows="" class="content_textarea" style="margin-top:5px; height:50px;width:323px;" id='con_text2' >${er.reason }</textarea></td>
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
      <input type="submit" name="sbu"  value="Save" class="search_input" id="save" onclick="checkpackagelinechina();"/>
      <input type="submit" name="cal" value="Cancel" class="search_input" onclick='window.history.go(-1)' id="cancel1"/>
</div>
</div>
<script type="text/javascript">
function checkpackagelinechina()
{
  var orderNo=document.getElementById("orderNo");
  if(orderNo.value!="")
  {
	  	var boo=isNum(orderNo.value);
	    if(!boo)
	   {
		    alert("OrderNo is not number!");
		    return false;
	    }
  }
  var itemNo=document.getElementById("itemNo");
  if(itemNo.value=="")
  {
	  var boo=isNum(itemNo.value);
	    if(!boo)
	    {
		    alert("ItemNo is not number!");
		    return false;
	    }
  }
  var quantity=document.getElementById("quantity");
  if(quantity.value!="")
  {
  	var boo=isNum(quantity.value);
	if(!boo)
	{
	   alert("Quantity is not number!");
	   return false;
	}
  }
  var size=document.getElementById("size");
  if(size.value!="")
  {
  	var boo=isNum(size.value);
	    if(!boo)
	   {
		    alert("Size is not number!");
		    return false;
	    }
  }
  var missingQty=document.getElementById("missingQty");
  if(missingQty.value != "")
  {
  		var boo=isNum(missingQty.value);
	    if(!boo)
	    {
		    alert("MissingQty is not number!");
		    return false;
	    }
  } 
    var missingSize=document.getElementById("missingSize");
  if(missingSize.value!="")
  {
  	var boo=isNum(missingSize.value);
		if(!boo)
		{
			alert("MissingSize is not number!");
			return false;
		}
  }
  	document.fr2.submit();
  }
function isNum(pck)
{
  for(var k=0;k < pck.length;k++){
  if(k!=0 && pck.charAt(k)==".")
  {
  return true;
  }
  if(pck.charAt(k)< "0"){
  return false;
  }
  if(pck.charAt(k) >"9")
  {
  return false;
  }
  }
    return true;
  }
</script>
</body>
</html>