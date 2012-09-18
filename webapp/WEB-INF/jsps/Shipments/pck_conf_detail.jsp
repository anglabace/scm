<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<%@ include file="/common/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Shipment Processing</title>
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}jquery/datePicker.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<script type="text/javascript">var GB_ROOT_DIR = "./greybox/";</script>
<script type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/gb_scripts.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/ui/ui.datepicker.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.datePicker.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery.js"></script>
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
$(function() {
		    $('.ui-datepicker').each(function(){
					$(this).datepicker(
						{
							dateFormat: 'yy-mm-dd',
							changeMonth: true,
							changeYear: true
						});
				}); 
		});       
          $(function() {            
            	$('#cancel_ship').dialog({
					autoOpen: false,
					height: 310,
					width: 670,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
            });
</script>
<script type="text/javascript"><!--
function cancelShipment(){
	var packageId = document.getElementById("textfield").value;
	$('#cancel_ship').dialog("option", "open", function() {
	        var htmlStr = '<iframe src="shipments!cancelCHShipment.action?packageId='+packageId+'&shipmentId=${pDto.shipments.shipmentId}" height="320" width="670" scrolling="no" style="border:0px" frameborder="0"></iframe>';
	        $('#cancel_ship').html(htmlStr);
	});
	$('#cancel_ship').dialog('open');
}
function isNum(pck)
	{
	  for(var k=0;k<pck.length;k++){
		  if(pck.charAt(k)< "0"){
		  return false;
	  }
		  if(pck.charAt(k) >"9")
		  {
		  	return false;
		  }
	  }
	}
function Len(str)
{
     var i,sum;
     sum=0;
     for(i=0;i<str.length;i++)
     {
         if ((str.charCodeAt(i)>=0) && (str.charCodeAt(i)<=255))
             sum=sum+1;
         else
             sum=sum+2;
     }
     return sum;
}
function checkpackageInfo()
{
   
    /**var shippingClerk=document.getElementById("shippingClerk");
    if(shippingClerk.value!="")
    {
	    var boo=isNum(shippingClerk.value);
	    if(boo || Len(shippingClerk)>2)
	    {
		    alert("ShippingClerk length is too long!");
		    return false;
	    }
    }*/

    trackingNo = document.getElementById('trackingNo').value;
    if(Len(trackingNo)>24)
    {
    	alert("trackingNo length is too long!");
    	return false;
    }
    window.location.reload();
    //document.fr.submit();
}
	
function check(){
	var packageId = document.getElementById("textfield").value;
	var url = "shipments!viewPackingSlip.action";
	$("#print2Form").attr("action",url);
	$("#print2Form").submit();
	document.printShippinglabelForm.action=url;
	document.printShippinglabelForm.submit();
	//window.location.href = "shipments!viewPackingSlip.action?packageId="+packageId;
	//document.fr.submit();
	
}
function toggleShowMore_img(obj,divID){
		var oId = document.getElementById(divID);
		if (obj.src.indexOf('arrow.jpg') > 0){
			obj.src="${global_url}images/title_bgewe.jpg";
			oId.style.display = "none"; 
		}else{
			obj.src="${global_url}images/arrow.jpg";
			oId.style.display = "block"; 
		}
}
function printCustomsInvoicefunct(){
	document.printShippinglabelForm.action="shipping!searchPrintShipPackages.action";
	document.printShippinglabelForm.submit();
}
function printImg(shippingLable){
	document.printShippinglabelForm.action="shipping!printShippingLabelTrackingNoUpload.action?shippingLable="+shippingLable;
	document.printShippinglabelForm.submit();
}

$(function() {            
	$('#shipping_document').dialog({
		autoOpen: false,
		height: 350,
		width: 500,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
});

function view_shipping_doc() {
	
	$('#shipping_document').dialog("option", "open", function() {	
	var htmlStr = '<iframe src="shipping!shippingDocument.action?packageId=${pDto.packageId }" height="300" width="450" scrolling="no" style="border:0px" frameborder="0"></iframe>';
	$('#shipping_document').html(htmlStr);
	});
	$('#shipping_document').dialog('open');
}
--></script>
</head>

<body class="content" style="background-image:none;">
<div class="scm" style="width: 1100PX">
<div class="title_content">
  <div style="padding-left: 20px;color: #5579A6;vertical-align:middle;"><img src="${global_url}images/arrow.jpg" style="width:16px;height:17px;vertical-align:middle;" onclick="toggleShowMore_img(this,'search_box1');"/>&nbsp;&nbsp;Package - # ${pDto.packageId} </div>
</div>
<form name="printShippinglabelForm" id="printShippinglabelForm" action="" method="post">
	 <input type="hidden" value="${pDto.packageId }" name="packageId"/>
</form>
<div  style="display:block;" class="content_box" id="search_box1">&nbsp;
  <form name="fr" action="shipments!saveShipPackager.action" method="post" >
    <table border="0" cellpadding="0" cellspacing="0" class="General_table">
            <tr>
              <th width="160">Package No</th>
              <td><input name="pDto.packageId" id="textfield" type="text" class="NFText" value="${pDto.packageId}" size="25" readonly="readonly" /></td>
              <th width="150">Status</th>
              <td>
              
              <select name="pDto.status" id="status" >
			
			<option value="${pDto.status }">
				${pDto.status}
			</option>

		</select>
              </td>
            </tr>
            <tr>
              <th width="160">Shipment No</th>
              <td><input name="pDto.shipments.shipmentNo" type="text" class="NFText" value="${pDto.shipments.shipmentNo}" size="25" readonly="readonly"/></td>
              <th valign="top">Shipment Date</th>
              <td>
                <input name="pDto.shipmentDate" id="shipDate" type="text" value="<s:date name="pDto.shipmentDate" format="yyyy-MM-dd"/>" size="25" style="width: 124px;"  readonly="readonly"/>
              </td>
             
              
            </tr>
            <tr>
              <th>Ship to</th>
              <td><textarea id="shiptoAddress" class="content_textarea2" style="height:100px;" readonly="readonly">${pDto.shiptoAddress}</textarea></td>
              <th>
              </th>
                 <td>
              </td>
            </tr>
            <tr>
              <th>Tracking No</th>
              <td><input name="pDto.trackingNo" id="trackingNo" type="text" class="NFText" value="${pDto.trackingNo}" size="25" readonly="readonly"/></td>
              <th>Shipment Method</th>
              <td>
              <input  name="" id="sm" type="hidden" class="NFText" value="${pDto.shipMethod}" size="25" onKeyUp="this.value=this.value.replace(/[^0-9|.]/g,'');"
				onfocus="this.select()"/>
              <input   id="sm" type="text" class="NFText" value="${pDto.shipVia}" size="25" readonly="readonly"/></td>
            </tr>
            <tr>
              <th>Shipping Clerk</th>
              <td><input name="pDto.shippingClerk" id="shippingClerk" type="text" class="NFText" value="${pDto.shippingClerk}" size="25" readonly="readonly"/></td>
              <th>Created Date</th>
              <td><input name="pDto.creationDate" id="creationDate" type="text" class="NFText" value="<s:date name="pDto.creationDate" format="yyyy-MM-dd"/>" size="25" readonly="readonly"  /></td>
            </tr>
              <tr>
      <th>Modified By </th>
      <td><input name="pDto.modifiedBy" id="modifiedBy" type="text" class="NFText" value="admin" size="25" readonly="readonly" /></td>
		      <th>Modified Date</th>
		      <td><input name="pDto.modifyDate" id="modifyDate" type="text" class="NFText" value="<s:date name="pDto.modifyDate" format="yyyy-MM-dd"/>" size="25" readonly="readonly"  /></td>
		    </tr>
                  <tr>
                
                  <td colspan="4" align="center">
                  <div class="button_box">
                  
                  	  <c:if test="${pDto.trackingNo!=null&&pDto.trackingNo!=''}">
                  	  	<input type="button" name="Submit2" value="View/Print Commercial Invoice" class="search_input4"  onclick="printCustomsInvoicefunct()" ${pDto.rcpCountry=='US' ? 'disabled':'' }/>
                      	<c:if test="${pDto.isPrintShippingLabel==\"0\"}">
                      		<input type="button" name="Submit3" value="View/Print Shipping Label" class="search_input3" disabled="disabled"/>
                      	</c:if>
                      	<c:if test="${pDto.isPrintShippingLabel==\"1\"}">
                      		<input type="button" name="Submit3" value="View/Print Shipping Label" class="search_input3"  onclick="printImg('${pDto.trackingNo}');"/>
                      	</c:if>
                      	
                      </c:if>
                      <input type="button" name="Submit4" value="View/Print Production Document" class="search_input4" onclick="view_shipping_doc();"/>
                    <c:if test="${pDto.status!='Drafted'}">
                    	<input type="button" name="Submit193" value="View/Print Packing Slip" class="search_input2"  onclick="check();"/>
                    </c:if>
                    <input type="button" name="cancel_ship" value="Cancel Shipment" class="search_input2" onclick="cancelShipment()"/>
             
                  </div>
                  <div id="cancel_ship" title="Cancel Shipment" style="visible: hidden;"></div>
                  </td>
                  
                </tr>
            </table>
            
          </form>
</div>
		

<div id="dhtmlgoodies_tabView1">
	
    <div class="dhtmlgoodies_aTab">
      <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table">
        <tr>
          <th width="50">Line No</th>
          <th width="100">Order No</th>
          <th width="102">Item No</th>
          <th width="230">Name</th>
          <th width="80">Qty</th>
          <th width="80">Size</th>
          <th width="80">Lot No</th>
          <th width="90">Qty Missing</th>
          <th width="90">Size Missing</th>
        </tr>
      </table>
	  <div class="frame_box">
      <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table">
      <c:set var="rowcount" value="1"></c:set>
     
      <c:forEach items="${pagerInfo.result }" var="list" >
      
      <c:if test="${rowcount mod 2 == 0}">
		<c:set var="tdclass" value=" class='list_td2'"></c:set>
	</c:if>
	<c:if test="${rowcount mod 2 == 1}">
		<c:set var="tdclass" value=""></c:set>
	</c:if>
        <tr>
          <td width="50" align="center" ${tdclass}><a href="shipments!getPackageline.action?pkgLineId=${list.pkgLineId}" target="mainFrame">${list.pkgLineId}</td>
          <td width="100" ${tdclass} align="center">&nbsp;${list.orderNo}</td>
          <td width="102" ${tdclass} align="center">&nbsp;${list.itemNo}</td>
          <td width="230" ${tdclass} align="center">&nbsp;${list.itemName}</td>
          <td width="80" ${tdclass} align="center">&nbsp;${list.quantity }&nbsp;${list.qtyUom }</td>
          <td width="80" ${tdclass} align="center">&nbsp;
          ${fn:endsWith(list.size,'.0')||fn:endsWith(list.size,'.00')||fn:endsWith(list.size,'.000')?fn:substring(list.size,0,fn:indexOf(list.size,".")):list.size}&nbsp;${list.sizeUom }</td>
          <td width="80" ${tdclass} align="center">&nbsp;${list.lotNo }</td>
          <td width="90" ${tdclass} align="center">&nbsp;
          <c:forEach items="${list.shipPackageErrorList }" var="error">
          ${error.missingQty }
          </c:forEach>
          </td>
          <td width="90" align="center" ${tdclass}>&nbsp;
		  <c:forEach items="${list.shipPackageErrorList }" var="error">
          ${error.missingSize }
          </c:forEach>
        </td>
        </tr>
        <c:set var="rowcount" value="${rowcount+1}"></c:set>
        </c:forEach>
      </table>
    </div>
   <div class="grayr">
	<jsp:include page="/common/db_pager.jsp">
		<jsp:param value="shipments!getPkgInfo.action" name="moduleURL" />
	</jsp:include>
   </div>
 
<script type="text/javascript">
initTabs('dhtmlgoodies_tabView1',Array('Package Lines'),0,998,320);
</script>
<div class="button_box">
      <input type="button" name="Submit123"  value="Save" class="search_input" onclick="checkpackageInfo();"/>
      <input type="submit" name="Submit124" value="Cancel" class="search_input" onclick="javascript:history.go(-1);" />
</div>

</div>
</div>
</div>
<div id="shipping_document" title=" Shipping Document "
			style="visible: hidden" />
<div id="cancel1" title="Cancel Shipment " style="visible: hidden;">
</div>	
		<form id="print2Form" method="post" action="">

		</form>
</body>
</html>