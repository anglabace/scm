<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base id="myBaseId" href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<style type="text/css">   

.tab td { margin: 0px;
	padding: 0px;
	color:#333333;
	font:11px/18px Arial, Helvetica, sans-serif; }

</style>
</head>

<body>


<form id="more_prsnl_info" method="post" action="customer/customer/viewCustMoreInfoPersonalAct">
<input type="hidden" name="custNo" value="${custNo}" id="custNo"/>
<input type="hidden" name="sessCustNo" value="${sessCustNo}"  id="sessCustNo"/>
<table width="566" border="0"  cellpadding="0" cellspacing="0" class="tab" style="margin-left:20px">
  <tr>
    <td colspan="4"><strong class="blue_price">Personal Information </strong></td>

  </tr>
  <tr>
    <td width="134" align="right">Gender ${custPersonalInfo.gender}</td>
    <td width="201" >
      <select name="custPersonalInfo.gender">
          <option value="Male" <s:if test='custPersonalInfo.gender=="Male"'>selected</s:if>>Male</option>
           <option value="Female" <s:if test='custPersonalInfo.gender=="Female"'>selected</s:if>>Female</option>
      </select>
	</td>
    <td width="80" align="right">Date of Birth </td>
    <td width="150">
        <input name="birthDate" id="more_prsnl_info_birth" type="text" class="pickdate NFText" value="<s:date format="yyyy-MM-dd" name="custPersonalInfo.birthDate"/>" size="20" />
    </td>
  </tr>
  <tr> <td valign="top" align="right">Ethnics/Race</td>

    <td colspan="3" valign="top">
         <s:select id="ethnics" name="custPersonalInfo.race" list="ethnicsList" listKey="value" listValue="value" value="custPersonalInfo.race"></s:select>
    </td>
  </tr>

  <tr>
    <td valign="top" align="right">Customer Hobby</td>
    <td>
        
         <input name="hobby" type="checkbox" value="bskt"  <c:if test='${bskt!=-1}'>checked</c:if>/>Basketball<br />
	<input name="hobby" type="checkbox" value="fish"  <c:if test='${fish!=-1}'>checked</c:if> />Fishing <br />
	<input name="hobby" type="checkbox" value="golf" <c:if test='${golf!=-1}'>checked</c:if>/>Golf<br />
        <input name="hobby" type="checkbox" value="tnns" <c:if test='${tnns!=-1}'>checked</c:if> />Tennis
	</td>

    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>

  <tr>
    <td colspan="4"><br />
      <div align="center">
          <input type="button" name="smt_more_prsn_info" value="Save" onclick="return saveMorePersonalInfo();" class="style_botton"/>
          <input  type="button" name="close" value="Cancel" onclick="javascript: parent.$('#personalInfoDialog').dialog('close');" class="style_botton" />
      </div>
      </td>
  </tr>
</table>
</form>
<script language="javascript" type="text/javascript">
function saveMorePersonalInfo()
{
	$.ajax({
		type: 'POST',
		url: "customer/customer_info!save.action",
		data: $("#more_prsnl_info").serialize(),
		success: function(msg){
			parent.$('#personalInfoDialog').dialog("close");
		},
		error: function(msg){
			alert("Warning: add personal info failed!!!");
		}
	});
}

$(document).ready(function(){
	$('.pickdate').each(function(){
		$(this).datepicker(
			{
				dateFormat: 'yy-mm-dd',
				changeMonth: true,
				changeYear: true,
				yearRange: '-100:+0'
			});
	})
});
</script>
</body>
</html>
