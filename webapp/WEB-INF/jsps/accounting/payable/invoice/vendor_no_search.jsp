


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
  function   cc(e)  
  {  
      var   a   =   document.getElementsByName("mm33");  
      for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;  
  }  

  function selectVendorName () {
	var vendorNo = $(":radio:checked").val();
	var vendorName = $(":radio:checked").attr("vendorName"); 
	window.parent.setSupplierValue(vendorNo,vendorName);
	doCancel();
  }

function doCancel(){
	//window.parent.$("#new_resource_dlg3").dialog('close');
	//alert(window.parent.document.getElementById('orderIframe').parentNode.id);
	window.parent.$("#new_customer_dlg").dialog('close');
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
          <table width="380" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td>
            <form method="get" target="_self" id="searchForm" action="genscript!vendorList.action">
            <table border="0" cellpadding="0" cellspacing="0" class="Customer_table">
              <tr>
                <th width="200px">Vendor No</th>
                <td><input name="filter_EQI_vendorNo" value="${param.filter_EQI_vendorNo }" type="text" class="NFText" size="20" value="${params.filter_EQI_vendorNo }" /></td>
                 <th width="250px">Vendor Name</th>
               <td><input name="filter_LIKES_vendorName" value="${param.filter_LIKES_vendorName }" type="text" class="NFText" size="20" /></td>
              <td colspan="8" align="center"> <input type="submit" name="Submit3" value="Search" class="style_botton" />
              </tr>
            </table>
            </form>
            </td>
            </tr>
            <tr>
            <td>
<table width="517" border="0" cellspacing="0" cellpadding="0">
              <tr>
                  <td style="padding-top:10px;"><table width="520" border="0" cellpadding="0" cellspacing="0" class="list_table">
                    <tr>
                      <th width="30">&nbsp;</th>
                      <th width="209">Vendor No</th>
                      <th>Vendor Name</th>
                      </tr>
                  </table></td>
                </tr>
                <tr>
                  <td><div  style="width:537px; height:180px; overflow:scroll;">
                    <table width="520" border="0" cellpadding="0" cellspacing="0" class="list_table">
                    <s:iterator value="vendorPage.result">
                	 <tr>
                        <td  width="30" align="center">
                          <input type="radio" vendorName="${vendorName}" value="${vendorNo }" name="vendorName" id="vendorName"/>
                        </td>
                        <td width="209">${vendorNo}</td>
                        <td>${vendorName}</td>
                     </tr>
                    </s:iterator>
                    </table>
                  </div></td>
                </tr>
                  <tr>
      <td>
														<div class="grayr">
															<jsp:include page="/common/db_pager.jsp">
																<jsp:param
																	value="${ctx}/genscript!vendorList.action"
																	name="moduleURL" />
															</jsp:include>
														</div>
													</td>
    </tr>
                <tr>
                  <td height="60"><div align="center">
                    <input id="sub1" name="Submit1" type="submit" class="style_botton"  value="Select" onclick="selectVendorName()"/>
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
