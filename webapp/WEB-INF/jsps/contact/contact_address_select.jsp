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

function checkForm() {
   $("#orgIdTag").attr("value", $("#orgId").val());  
   $("#orgNameTag").attr("value", $("#orgName").val());
   return true;
}


function select_addr(addressKey, sessContactNo)
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
	if(addressKey == '')
	{
		parent.$('#addContactAddrDialog').dialog('open');
		var url = 'contact_address!selectCallBack.action?fromAddrId=' + select_id + '&sessContactNo='+sessContactNo  + '&addressKey=' + addressKey;
		var htmlStr = '<iframe src="'+url+'" height="410" width="720" scrolling="no" style="border:0px" frameborder="0"></iframe>';
		parent.$('#addContactAddrDialog').html(htmlStr);
	}
	else
	{
		parent.$('#editContactAddrDialog').dialog('open');
		var url = 'contact_address!selectCallBack.action?fromAddrId=' + select_id + '&sessContactNo='+sessContactNo  + '&addressKey=' + addressKey;
		var htmlStr = '<iframe src="'+url+'" height="410" width="720" scrolling="no" style="border:0px" frameborder="0"></iframe>';
		parent.$('#editContactAddrDialog').html(htmlStr);
	}
	parent.$('#selectAddrDialog').dialog('close');
}
</script>
</head>

<body>
<table width="690" border="0"  cellpadding="0" cellspacing="0"  style="margin-left:15px">

<tr><td>
<form method="get" action="contact_address!select.action" onsubmit="return checkForm();">
<input type="hidden" id="sessContactNo" name="sessContactNo" value="${sessContactNo}" />
<input type="hidden" name="contactNo" value="${contactNo}" />
<input type="hidden" name="addrId" value="${addrId}" />
<input type="hidden" name="defaultFlags" value=" ${defaultFlags}" id="defaultFlags" />
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
	   <tr>
	        <td align="right">Contact No</td>
	        <td>
	          <input name="addrSrch.custNo" class="NFText" size="20" value="${addrSrch.custNo}"
												type="text" />
	        </td>
	        <td align="right">First Name </td>
	        <td>
	        <input name="addrSrch.firstName" class="NFText" size="20" value="${addrSrch.firstName}"
												type="text" />
	        </td>
	        <td align="right">Last Name</td>
	        <td><input name="addrSrch.lastName" class="NFText" size="20" value="${addrSrch.lastName}"
												type="text" /></td>
	    </tr>
      <tr>
        <td align="right">Email</td>
        <td><input name="addrSrch.email" class="NFText" size="15" value="${addrSrch.email}"
												type="text" /></td>
        <td align="right">Organization</td>
        <td colspan="3">
	    	<input name="orgId" value="${addrSrch.orgId}" type="hidden" id="orgId" />
	    	<input name="addrSrch.orgId" value="${addrSrch.orgId}" type="hidden" id="orgIdTag" />
	    	<input name="orgName" value="${addrSrch.orgName}" type="text" id="orgName" size="40"  class="NFText" readonly="readonly" />
	    	<input name="addrSrch.orgName" value="${addrSrch.orgName}" type="hidden" id="orgNameTag" />
	    	<input id="hid_remove" type="hidden" onclick="tb_remove();"/>
	    	<img src=" ${global_images_url}images/search.gif" style="cursor:pointer" width="16" height="16" id="orgAddSelectAddrDialogTrigger" />
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td colspan="5">
        	<input type="submit" value="Search" class="search_input"/>
        </td>
        </tr>
    </table>
    </form>
    </td></tr>

  <tr>
    <td class="tab_title">Select Address:</td>
  </tr>

  <tr><td>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
	<tr>
   	<td>
	    
	  </td>
  </tr>
  <tr>
    <td>
	<div style="width:690px;height:188px;overflow:scroll;">
		<table width="670" border="0" cellpadding="0" cellspacing="0" class="list_table" style="border-bottom: 3px solid #b6d2ea;border-top: 1px solid #cccbc6">
		      <tr>
		        <td width="30" align="center" class='list_td2'>&nbsp;</td>
		        <td width="100" align="center" class='list_td2'>Address Type</td>
		        <td width="100" align="center" class='list_td2'>Name</td>
		        <td width="200" align="center" class='list_td2'>Organization</td>
		        <td width="" align="center" class='list_td2'>Address</td>
		      </tr>
	    	</table>
	  <table width="670" border="0" cellpadding="0" cellspacing="0" class="list_table">
	   <c:set var="rowcount" value="1"></c:set>
            <s:iterator value="addressPage.result">
             <c:if test="${rowcount mod 2 == 0}">
                <c:set var="tdclass" value=" class='list_td2'"></c:set>
              </c:if>	
             <c:if test="${rowcount mod 2 == 1}">
                <c:set var="tdclass" value=""></c:set>
              </c:if>
        <tr>
          <td width="30" ${tdclass}>
              <input name="select_address" type="radio" value="${addrId}" />
          </td>
            <td width="100" ${tdclass}><div align="center"> ${addrType}&nbsp;</div></td>
            <td width="100" ${tdclass}> ${firstName} ${lastName}&nbsp;</td>
            <td width="200" ${tdclass}> ${organization.name}&nbsp;</td>
            <td width="" ${tdclass}> ${addrLine1}  ${addrLine2}  ${addrLine3}, ${city} ,${state}</td>
        </tr><c:set var="rowcount" value="${rowcount+1}"></c:set>
        </s:iterator>
      </table>
	</div>
</td></tr>
<tr>
							<td>
								<div class="grayr">
									<jsp:include page="/common/db_pager.jsp">
										<jsp:param value="${ctx}/contact_address!select.action"
											name="moduleURL" />
									</jsp:include>
								</div>
							</td>
						</tr>
  <tr>
    <td style="text-align:center; padding:6px;">
          <input type="button" value="Select" onclick="select_addr('${addressKey}', '${sessContactNo}');" />
          <input  type="button" value="Cancel"  onclick="parent.$('#selectAddrDialog').dialog('close');"/>
      </td>
  </tr>
</table>
</td></tr>

</table>

</body>
</html>
