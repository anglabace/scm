<!-- {get_spec_selects value="ORIGINAL_SOURCE,TIME_ZONE"} -->
<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/taglib.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base id="myBaseId" href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />　　　
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.form.js"></script>
<script language="JavaScript" src="${global_js_url}scm/contactAddr.js?v=1" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function(){
    $('tr:even >td').addClass('list_td2');
    parent.initTerritory();
});
</script>
</head>
<body>
	  <div style="height:200px;width:955px;">
	  <form action="contact_address/delete.action" id='address_del_form' method="post" name="address_del_form" >
	  <input type="hidden" id="sessContactNo" name="sessContactNo" value="${sessContactNo}" />
	  <input type="hidden" name="contactNo" value="${contactNo}" />
              <table width="955" border="0" cellpadding="0" cellspacing="0" class="list_table">
                <tr>
                  <th width="46">
                  	<div align="left">
					   <input type="checkbox"  onclick="choose_all(this, 'addrIds')" />
			      	  	 <img style="cursor:pointer" src="${global_images_url}images/file_delete.gif" id="del_img" onclick="del_address('addrIds', '${sessContactNo}');" width="16" height="16" />
			      	 </div>
			      </th>
                  <th width="100">Address Type</th>
                  <th width="150">Name</th>
                  <th width="60">Default</th>
                  <th  width="60">Status</th>
                  <th>Address Information</th>
                </tr>
              </table>
	   	    <div class="frame_box">
                <table border="0" cellpadding="0" cellspacing="0" class="list_table" width="955">
                  <s:iterator value="addressMap">
                  <s:if test="value!=null">
                  	<tr>
	                    <td width="46">
                            <input type="checkbox" name="addrIds" value="${key}" />
	                     </td>
	                    <td width="100" >
	                        <div align="center">${value.addrType}&nbsp;</div>
	                    </td>
	                    <td width="150">&nbsp;
	                    	<a href="javascript:void(0);" onclick="show_edit_addrform('${key}','${sessContactNo}');"
							title="Edit Address">
	                            <s:property value="value.lastName"/> , <s:property value="value.firstName"/> 
	                   	 	</a>
	                    </td>
	                    <td width="60" >
	                    	<div align="center">
	                                <input type="checkbox" <s:if test='value.defaultFlag=="Y"'> checked</s:if> disabled="disabled" onclick="return false;" />
	                    		&nbsp;
	                    	</div>
	                    </td>
	                    <td width="60" ><s:property value="value.status"/>&nbsp;</td>
	                    <td><s:property value="value.addrLine1"/> <s:property value="value.addrLine2"/>  <s:property value="value.addrLine3"/>, <s:property value="value.city"/>, <s:property value="value.state"/> <s:property value="value.zipCode"/>, <s:property value="value.country"/>   &nbsp;</td>
	                  </tr>
                  </s:if>
	                 
                  </s:iterator>
                </table>
               </div>
   	        </form>
   	        </div>

</body>
</html>