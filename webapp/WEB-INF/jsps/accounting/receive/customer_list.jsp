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
//function mysearch () {
	//window.location.href = "ar_invoice!customerlist.action";
//}

function selectedCust () {

	var item = $(":radio:checked"); 
	var len=item.length; 
	if(len>0){ 
		//window.parent.$('#customerNo').val($(':radio:checked').val());
		var a = $(':radio:checked').parent().siblings();
		window.parent.setCustomerValue($(':radio:checked').val(),a[1].innerHTML);//将表格的customer的名称和编号传递
		doCancel();
	} 
}

function doCancel(){
	window.parent.$("#new_customer_dlg").dialog('close');
}

</script>
</head>
<body><br /><table width="560" border="0" cellspacing="3" cellpadding="0" id="table11" >
<tr><td bgcolor="#FFFFFF">
<table width="570" border="0" cellspacing="0" cellpadding="0">

      <tr>
        <td style="padding-left:20px;"><br />
          <table width="537" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td>
            <form method="get" target="_self" id="searchForm" action="genscript!customerlist.action">
            <input type="hidden" name="page.pageNo" value="${page.pageNo}" id="pageNo" />
            <input type="hidden" name="page.orderBy" value="${page.orderBy}" id="orderBy" />
            <table border="0" cellpadding="0" cellspacing="0" class="Customer_table">
              <tr>
                <th>Customer No</th>
                <td><input name="filter_EQI_custNo" id="filter_custNo" type="text" value="${param.filter_EQI_custNo }" class="NFText" size="20"/></td>
                <th>Customer Name</th>
                <td><input name="filter_LIKES_concat(firstName,' ',lastName)" id="filter_custName" type="text" value="${param.filter_LIKES_firstName }" class="NFText" size="20"/></td>
                <td><input name="Submit3" type="submit" class="style_botton" value="Search"/></td>
              </tr>
            </table>
            </form>
              <table width="517" border="0" cellspacing="0" cellpadding="0">
              <tr>
                  <td style="padding-top:10px;"><table width="520" border="0" cellpadding="0" cellspacing="0" class="list_table">
                    <tr>
                      <th width="30">&nbsp;</th>
                      <th width="60">Customer No</th>
                      <th width="120">Customer Name</th>
                      <th>Email</th>
                      <th width="60">Organization</th>
                      </tr>
                  </table></td>
                </tr>
                <tr>
                  <td>
                    <table width="517" border="0" cellpadding="0" cellspacing="0" class="list_table">
                    <s:iterator value="page.result">
                	 <tr>
                        <td  width="30" align="center">
                          <input type="radio" name="radio" id="custNo" value="${custNo}" />
                        </td>
                        <td width="60">&nbsp;${custNo}</td>
                        <td width="120">&nbsp;${firstName}<c:if test="${! empty lastName}"> </c:if>${lastName}<c:if test="${! empty midName}">, </c:if>${midName}</td>
                     	<td>${busEmail}</td>
                     	<td width="60">${organizationName}</td>
                     </tr>
                    </s:iterator>
                    </table>
                    </td>
                </tr>
                  <tr>
      <td><div class="grayr">
			<jsp:include page="/common/db_pager.jsp">
			  <jsp:param value="${ctx}/genscript!customerlist.action" name="moduleURL"/>
			</jsp:include>	
		  </div>
	  </td>
    </tr>
                <tr>
                  <td height="40"><div align="center">
                    <input id="sub1" name="Submit1" type="submit" class="style_botton"  value="Select" onclick="selectedCust()"/>
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
