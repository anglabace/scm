<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base id="myBaseId" href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href=" ${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href=" ${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src=" ${global_js_url}scm/config.js"></script>
<script language="javascript" type="text/javascript" src=" ${global_js_url}jquery/jquery.js"></script>
<script type="text/javascript">
$(function (){
	$('#orgAddSelectAddrDialogTrigger')
		.click(function(){
			dataHolderWin.jQuery.data(dataHolderWin.document.body, 'disableNew', 1);
			dataHolderWin.$('#orgDialogWindow').dialog('open');
			dataHolderWin.jQuery.data(dataHolderWin.document.body, 'orgLoc', self);
			dataHolderWin.jQuery.data(dataHolderWin.document.body, 'orgIdStr', 'orgId');
			dataHolderWin.jQuery.data(dataHolderWin.document.body, 'orgNameStr', 'orgName');
		});
});

function select_addr(from_addrId, custNo)
{
	var a = document.getElementsByName("select_address");
	var select_id = 0;
	for (var i=0; i<a.length; i++){
		if(a[i].checked)
		{
			var select_id = a[i].value;
			break;
		}
	}
	if(select_id == 0)
	{
		alert('Please select one!');
		return;
	}
	var addr_info = document.getElementById(select_id+'_addr_info').value;
	var sessCustNo = $("#sessCustNo").val();
	if(from_addrId == '')
	{
		parent.$('#addCustomerAddrDialog').dialog('open');
		var url = 'cust_address!showAddrCreateForm.action?copy_addrId='+select_id+'&custNo='+custNo+'&addr_info='+addr_info+'&sessCustNo='+sessCustNo;
		var htmlStr = '<iframe src="'+url+'" height="410" width="720" scrolling="no" style="border:0px" frameborder="0"></iframe>';
		parent.$('#addCustomerAddrDialog').html(htmlStr);
	}
	else
	{
		parent.$('#editCustomerAddrDialog').dialog('open');
		var url = 'cust_address/edit.action?addrId='+from_addrId+'&copy_addrId='+select_id+'&custNo='+custNo+'&addr_info='+addr_info+'&sessCustNo='+sessCustNo;
		var htmlStr = '<iframe src="'+url+'" height="410" width="720" scrolling="no" style="border:0px" frameborder="0"></iframe>';
		parent.$('#editCustomerAddrDialog').html(htmlStr);
	}
	parent.$('#selectAddrDialog').dialog('close');
}
</script>
</head>

<body>
<table width="690" border="0"  cellpadding="0" cellspacing="0"  style="margin-left:15px">

<tr><td>
<form method="get" action="cust_address!custAddrPickerAct.action">
<input type="hidden" id="sessCustNo" name="sessCustNo" value="${sessCustNo}" />
<input type="hidden" name="custNo" value="${custNo}" />
<input type="hidden" name="addrId" value="${addrId}" />
<input type="hidden" name="defaultFlags" value=" ${defaultFlags}" id="defaultFlags" />
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
	   <tr>
	        <td align="right">Customer No</td>
	        <td>
	          <input name="c_custNo" type="text" class="NFText" size="20" value="${c_custNo }"/>
	        </td>
	        <td align="right">First Name </td>
	        <td><input name="firstName" type="text" class="NFText" size="20" value="${firstName }"/></td>
	        <td align="right">Last Name</td>
	        <td><input name="lastName" type="text" class="NFText" size="20" value="${lastName }"/></td>
	    </tr>
      <tr>
        <td align="right">Email</td>
        <td><input name="email" type="text" class="NFText" size="20" value="${email }"/></td>
        <td align="right">Organization</td>
        <td colspan="3">
	    	<input name="orgId" value="${orgId}" type="hidden" id="orgId" />
	    	<input name="orgName" value="${orgName}" type="text" id="orgName" size="40"  class="NFText" readonly="readonly" />
	    	<input id="hid_remove" type="hidden" onclick="tb_remove();"/>
	    	<img src=" ${global_images_url}images/search.gif" width="16" height="16" id="orgAddSelectAddrDialogTrigger" />
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td colspan="5">
        	<input type="submit" value="Search" class="search_input" />
        </td>
        </tr>
    </table>
    </form>
    </td></tr>

  <tr>
    <td class="tab_title">Select Address:</td>
  </tr>

  <tr><td>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table" >
	<tr>
   	<td>
	    	
	  </td>
  </tr>
  
  <tr>
    <td>
	<div style="width:690px;height:188px;overflow:scroll;">
	<table width="670" border="0" cellpadding="0" cellspacing="0" class="list_table" style="border-bottom: 3px solid #b6d2ea;border-top: 1px solid #cccbc6">
		       <tr >
		        <td width="30" align="center" class='list_td2'>&nbsp;</td>
		        <td width="100" align="center" class='list_td2'>Address Type</td>
		        <td width="100" align="center" class='list_td2'>Name</td>
		        <td width="200" align="center" class='list_td2'>Organization</td>
		        <td width="" align="center" class='list_td2'>Address</td>
		      </tr>
	    	</table>
	  <table width="670" border="0" cellpadding="0" cellspacing="0" class="list_table" >
	 <c:set var="rowcount" value="1"></c:set>
            <s:iterator value="page.result" status="addrList">
             <c:if test="${rowcount mod 2 == 0}">
                <c:set var="tdclass" value=" class='list_td2'"></c:set>
              </c:if>	
             <c:if test="${rowcount mod 2 == 1}">
                <c:set var="tdclass" value=""></c:set>
              </c:if>
        <tr>
          <td width="30" ${tdclass}>
              <input name="select_address" type="radio" value="<s:property value="addrId"/>"  />
          </td>
            <td width="100" ${tdclass}><div align="center" ${tdclass}> <s:property value="addrType"/>&nbsp;</div></td>
            <td width="100" ${tdclass}> <s:property value="firstName"/> <s:property value="lastName"/>&nbsp;</td>
            <td width="200" ${tdclass}> <s:property value="organization.name"/>&nbsp;</td>
            <td width="" ${tdclass}><s:property value="addrLine1"/> <s:property value="addrLine2"/> <s:property value="addrLine3"/>,<s:property value="city"/> ,<s:property value="state"/>
	           &nbsp;
                   <input type="hidden" value="<s:property value="addr_info"/>" id="<s:property value="addrId"/>_addr_info" />
          </td>
        </tr><c:set var="rowcount" value="${rowcount+1}"></c:set>
        </s:iterator>
      </table>
	</div>
	<div class="grayr">
		<jsp:include page="/common/db_pager.jsp">
		  <jsp:param value="${ctx}/cust_address!custAddrPickerAct.action" name="moduleURL"/>
		</jsp:include>	
	</div>
</td></tr>

  <tr>
    <td style="text-align:center; padding:6px;">
          <input type="button" value="Select" onclick="select_addr('${addrId}', '${custNo}');" />
          <input  type="button" value="Cancel"  onclick="parent.$('#selectAddrDialog').dialog('close');"/>
      </td>
  </tr>
</table>
</td></tr>

</table>

</body>
</html>
