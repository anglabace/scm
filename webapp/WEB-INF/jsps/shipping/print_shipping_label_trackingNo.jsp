<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="stylesheet/scm.css" rel="stylesheet" type="text/css" />
<link href="stylesheet/table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="js/jquery.js"></script>
<script>
function jopen()
{
  if ($("#check1").attr("checked")==false)
						{
						   	window.parent.closeiframe();
							window.parent.openiframe('print_ship_label_1.html','602','296');
						}
  	
}
function show(){
		var trackingNo = document.getElementsByName('trackingNo');
	 	var d1;
			 for(var i=0;i<trackingNo.length;i++){
				  d1=trackingNo[i].value;
			}
		return true;
}

function printImg(shippingLable){
		document.forms[0].action="shipping!printShippingLabelTrackingNoUpload.action?shippingLable="+shippingLable;
		document.forms[0].submit();
}

function subBtn () {
	document.printShippinglabelForm.action="shipping!printShippinglabelTrackingno.action";
	document.printShippinglabelForm.submit();
}

function printCustomsInvoicefunct(){
	document.printShippinglabelForm.action="shipping!searchPrintShipPackages.action";
	document.printShippinglabelForm.submit();
}

function cancelTraking(){
    var trackingNo = document.getElementsByName('trackingNo');
 	var d1 = "";
		 for(var i=0;i<trackingNo.length;i++){
			 if(trackingNo[i].value!=null&&trackingNo[i].value!=""){
				 if(d1==""){
					 d1=trackingNo[i].value;
				 }else{
					 d1 = d1+","+trackingNo[i].value;
				 }
			 }
			
	}
    document.cacelTrakingForm.action='shipping!cancelTraking.action';	
    document.getElementById("trackingNo1").value=d1;
    document.cacelTrakingForm.submit();
   //location.href="shipping!cancelTraking.action?trackingNo="+d1+"";
}
</script>
<link href="stylesheet/openwin.css" rel="stylesheet" type="text/css" />
</head>

<body>

<form name="printShippinglabelForm" id="printShippinglabelForm" action="shipping!printShippinglabelTrackingno.action" method="post">
<table width="600" border="0" cellspacing="3" cellpadding="0" bgcolor="#96BDEA">

  <tr>
    <td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">

          <tr>
            <td valign="top"><br />
              <table  border="0" align="center" cellpadding="0" cellspacing="0" class="General_table" width="500">
              	<c:forEach items="${m}" var="list">
              	<tr>
                <td height="30" valign="top"> ${list.trackingNo }.
                <input type="hidden" value="${list.packageId }" name="packageId"/>
                <input type="hidden" value="${list.trackingNo_ }" name="trackingNo" id="trackingNo"/>
                </td>
                </tr>
             	</c:forEach>
              <tr>
                <td height="80"><div align="center"><br />
                  <input  type="button" name="printCustomsInvoiceName" value="Print Commercial Invoice" class="style_botton3" onclick="printCustomsInvoicefunct();" ${createCommercialInvoice!='y' ? 'disabled':'' }/>
                  <input  type="button" name="Cancel2" value="Print Label" class="style_botton2" onclick="printImg('${shippingLable}');"/>
                  <input  type="button" name="Cancel3" value="Cancel Shipment" class="style_botton2" onclick="cancelTraking();"/>
                  <input  type="button" name="Cancel" value="Close" class="style_botton" onclick="subBtn();"/>
                </div></td>
              </tr>
            </table>
              <br />
              <br /></td>
          </tr>

        </table></td>
      </tr>
    </table></td>
  </tr>
</table></form>

<form name = "cacelTrakingForm" action="">
	<input type="hidden" name="trackingNo" id="trackingNo1"/>
</form>
</body>
</html>
