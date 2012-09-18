<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include  file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>Attributes</title>
<base href="${global_url}" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript"> 
function save(){
	var attributeCode = $("#attributeCode").val();
	if(!attributeCode){
		alert("Please enter the Attribute to continue your operation.");return;
	}
	var id = $("#id").val();
	$.ajax({
		type:"POST",
		url:"serv/attributes!save.action",
		data:$("#form1").serialize(),
		dataType:"json",
		success: function(msg){
			if(msg.message=="success"){
				window.parent.closeiframe();
				parent.window.location.reload();
			}
		},
		error: function(msg){
			alert("System error! Please contact system administrator for help.");
		}
	});
	
}
</script>
</head>
 
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="3" bgcolor="#96BDEA" id="table11">
  <tr>
    <td bgcolor="#FFFFFF" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="39" align="left" valign="top" background="js/greybox/header_bg.gif">
        <div class="line_l_new">
          <s:if test="id==null">New</s:if><s:else>Edit</s:else>  Service Attribute</div>
          <div class="line_r_new" onclick="window.parent.closeiframe()"><img src="js/greybox/w_close.gif" width="11" height="11" />Close</div></td>
      </tr>
      <tr>
        <td><br />
        <form id="form1">
		<table width="600"  border="0" cellpadding="0" cellspacing="0" class="General_table" style="margin:10px auto 0px auto;">
          
          <tr>
            <th width="135">Service Type</th>
            <td width="465">
			<s:select id="type" name="clsId" list="dropDownDTO" listKey="id" listValue="name" cssStyle="width:125px;" value="clsId"></s:select>		
			</td>
            </tr>
          <tr>
            <th>Attribute Code</th>
            <td><input name="entity.attributeCode" type="text"  class="NFText" id="attributeCode" value="${attributeCode}"/></td>
            </tr>
          <tr>
            <th>Attribute Name</th>
            <td><input name="entity.attributeName" type="text" id="attributeName" class="NFText" value="${attributeName}"/></td>
            </tr>
          <tr>
            <th>Description</th>
            <td><textarea name="entity.description" class="content_textarea2">${description}</textarea></td>
            </tr>
          <tr>
            <td colspan="2" valign="top"><div  class="botton_box">
           	  <input type="hidden" name="entity.id" id="id" value="${id }" />
           	  <s:if test="id==null">
           	  	<saveButton:saveBtn parameter="${operation_method}"
				disabledBtn='<input type="button" class="style_botton" value="New"  disabled="disabled"/>'
				saveBtn='<input name="process_a" type="button" class="style_botton" value="New"  id="process_a" onclick="save()"/>'
			  /> 
           	  </s:if>
           	  <s:else>
           	  <saveButton:saveBtn parameter="${operation_method}"
				disabledBtn='<input type="button" class="style_botton" value="Modify"  disabled="disabled"/>'
				saveBtn='<input name="process_a" type="button" class="style_botton" value="Modify"  id="process_a" onclick="save()"/>'
			  /> 
           	  </s:else>
              <input  type="button" name="close2" value="Cancel" class="style_botton" onclick="javascript: window.parent.closeiframe();" />
              </div><br />
              <br />
              <br />
              <br />
<br />
              </td>
          </tr>
        </table>
        </form>
        </td>
      </tr>
    </table></td>
  </tr>
</table></body>
</html>

