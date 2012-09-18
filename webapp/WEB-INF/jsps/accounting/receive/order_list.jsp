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
function selectedOrder () { 
	var item = $(":radio:checked"); 
	var len=item.length; 
	if(len>0){ 
		//window.parent.$('#orderNo').val($(':radio:checked').val());
		window.parent.setOrderValue($(':radio:checked').val());
		doCancel();
	} 
}

function doCancel(){
	//window.parent.$("#new_resource_dlg3").dialog('close');
	//alert(window.parent.document.getElementById('orderIframe').parentNode.id);
	window.parent.$('#'+window.parent.$('#orderIframe').parent().attr('id')).dialog('close');
}

</script>
</head>
<body>
<table width="500" border="0" cellspacing="3" cellpadding="0" id="table11">
  <tr>
    <td bgcolor="#FFFFFF">
    <table width="449" border="0" cellspacing="0" cellpadding="0" height="339">
 		
      <tr>
        <td style="padding-left:60px;"><br />
          <table width="367" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td>
            <form method="get" target="_self" id="searchForm" action="genscript!orderlist.action">
            <input type="hidden" name="filter_EQS_shipmentNo" value="${filter_EQS_shipmentNo}"/>
            <table border="0" cellpadding="0" cellspacing="0" class="Customer_table">
              <tr>
                <th>Order No</th>
                <td><input name="filter_EQI_orderNo"  id="filter_EQI_orderNo" type="text" value="${filter_EQI_orderNo }" class="NFText" size="20"/></td>
                <td><input name="Submit3" type="submit" id="Search" class="style_botton" value="Search" /></td>
              </tr>
            </table>
            </form>
            </td>
            </tr>
            <tr>
            <td>
              <table width="373" border="0" cellspacing="0" cellpadding="0" height="244">
                <tr>
                  <td style="padding-top:10px;"><table width="350" border="0" cellpadding="0" cellspacing="0" class="list_table">
                    <tr>
                      <th width="30">&nbsp;</th>
                      <th>Order No</th>
                      </tr>
                  </table></td>
                </tr>
                <tr>
                  <td><div  style="width:367px; height:130px; overflow:scroll;">
                    <table width="350" border="0" cellpadding="0" cellspacing="0" class="list_table">
                    <s:iterator value="shipmentLinesPage.result" var="orderNo">
                      <tr>
                        <td  width="30"><div align="center">
                          <input type="radio" name="radio" id="radio" value="${orderNo}" />
                        </div></td>
                        <td>&nbsp;${orderNo}</td>
                      </tr>
                    </s:iterator>
                    </table>
                  </div></td>
                </tr>
                  <tr>
      <td>
		<div class="grayr">
			<jsp:include page="/common/db_pager.jsp">
			  <jsp:param value="${ctx}/genscript!orderlist.action" name="moduleURL"/>
			</jsp:include>	
		</div>
	  </td>
    </tr>
                <tr>
                  <td height="60"><div align="center">
                    <input id="sub1" name="Submit1" type="submit" class="style_botton"  value="Select" onclick="selectedOrder()"/>
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
