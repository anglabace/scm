<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include  file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css" />
<script src="${global_js_url}jquery/jquery.js" type="text/javascript" language="javascript"></script>
<script  language="JavaScript" type="text/javascript">
function selectedShipment () {
	var item = $(":radio:checked"); 
	var len=item.length; 
	if(len>0){ 
		//window.parent.$('#shipmentNo').val($(':radio:checked').val());
		window.parent.setShipmentValue($(':radio:checked').val());
		doCancel();
	} 
	window.parent.closeiframe();
}

function doCancel(){
	window.parent.$("#new_resource_dlg2").dialog('close');
}
</script>
</head>
<body>
<table width="500" border="0" cellspacing="3" cellpadding="0" id="table11" >
  <tr>
    <td bgcolor="#FFFFFF"><table width="500" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td style="padding-left:60px;"><br />
          <table width="367" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td>
            <form method="get" target="_self" id="searchForm" action="genscript!shipmentlist.action">
			<input type="hidden" name="filter_EQI_custNo" value="${filter_EQI_custNo}"/>
			<table border="0" cellpadding="0" cellspacing="0" class="Customer_table">
              <tr>
                <th>Shipment No</th>
                <td><input name="filter_EQS_shipmentNo" id="filter_EQS_shipmentNo" value="${filter_EQS_shipmentNo }" type="text"  class="NFText" size="20"/></td>
                <td><input name="Submit3" type="submit" id="Search" class="style_botton" value="Search" /></td>
              </tr>
            </table>
            </form>
            </td>
            </tr>
            <tr>
            <td>
              <table width="414" border="0" cellspacing="0" cellpadding="0" >
                <tr>
                  <td style="padding-top:10px;"><table width="350" border="0" cellpadding="0" cellspacing="0" class="list_table">
                    <tr>
                      <th width="30">&nbsp;</th>
                      <th>Shipment No</th>
                      </tr>
                  </table></td>
                </tr>
                <tr>
                  <td><div  style="width:367px; height:130px; overflow:scroll;">
                    <table width="350" border="0" cellpadding="0" cellspacing="0" class="list_table">
                    <s:iterator value="shipmentPage.result">
                      <tr>
                        <td  width="30"><div align="center">
                          <input type="radio" name="radio" id="radio" value="${shipmentNo}" />
                        </div></td>
                        <td>&nbsp;${shipmentNo}</td>
                      </tr>
                    </s:iterator>
                    </table>
                  </div></td>
                </tr>
                  <tr>
      <td>
	  	<div class="grayr">
			<jsp:include page="/common/db_pager.jsp">
			  <jsp:param value="${ctx}/genscript!shipmentlist.action" name="moduleURL"/>
			</jsp:include>	
		</div>
	  </td>
    </tr>
                <tr>
                  <td height="60"><div align="center">
                    <input id="sub1" name="Submit1" type="submit" class="style_botton"  value="Select" onclick="selectedShipment()"/>
                    <input id="sub2" type="submit" name="Submit2" value="Cancel" class="style_botton" onclick="doCancel()"/>
                    </div></td>
                </tr>
              </table></td>
          </tr>
        </table>          <br /></td>
      </tr>
    </table></td>
  </tr>
</table>

</body>
</html>
