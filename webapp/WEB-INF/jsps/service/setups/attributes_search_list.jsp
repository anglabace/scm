<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include  file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Attributes</title>
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />

<link type="text/css"
	href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}ajax.js"></script>
<script src="${global_js_url}table.js" type="text/javascript"></script>
<script
	src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js"
	type="text/javascript"></script>
<link type="text/css"
	href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script src="${global_js_url}jquery/ui/ui.core.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js"
	type="text/javascript"></script>
	
<script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>
<script language="JavaScript" type="text/javascript">  
$(document).ready(function(){  
    $('tr:even >td').addClass('list_td2'); 
    
});
function   cc(e)  
{
	var   a   =   document.getElementsByName("ids");
	for   (var   i=0;   i<a.length;   i++)   a[i].checked   =   e.checked;  
} 

  function del(name){
		var del_nos = get_checked_str(name);
		if(del_nos == '')
		{
			alert('Please select one item to continue your operation.');
			return;
		}
		if(!confirm('Are you sure to delete?'))
		{
			return;
		}
		$.ajax({
			type:"POST",
			url:"serv/attributes!delete.action?delStr="+del_nos,
			dataType:"json",
			success: function(msg){
				if(msg.message=="success"){
					window.location.reload();
				}else{
					alert("Failed to delete the price rule. Please remove it from the Service Rule Group to continue your operation.");	
				}
			},
			error: function(msg){
				alert("Failed to delete the price rule. Please remove it from the Service Rule Group to continue your operation.");
			}
		});
	}
	function get_checked_str(name)
	{
		var a = document.getElementsByName(name);
		var str = '';
		for   (var   i=0;   i<a.length;   i++)
		{
			if(a[i].checked)
			{
				str += a[i].value+',';
			}
		}
		return str.substring(0,str.length-1);
	}
</script>
</head>
 
<body class="content" style="background-image:none;">
 
 <div class="scm">
<div id="frame12" style="display:none;" class="hidlayer1">
<iframe id="hidkuan" name="hidkuan" src="" width="668" height="425" frameborder="0"  allowtransparency="true"></iframe>
</div>
<div class="scm">
<div class="title_content">
  <div class="title">Service Attributes</div>
</div>
<div class="search_box" >
  <div class="search_box_two">
  <form id="mainForm" action="" method="post">
    <table border="0" cellpadding="0" cellspacing="0" class="General_table">
      <tr>
        <td>Attribute Code</td>
        <td width="120"><input name="filter_EQS_attributeCode" type="text" class="NFText" size="20" value="${param['filter_EQS_attributeCode']}"/></td>
        <td>Attribute Name</td>
        <td width="120"><input name="filter_LIKES_attributeName" type="text" class="NFText" size="20" value="${param['filter_LIKES_attributeName']}"/></td>
        <td>Service Type</td>
        <td width="120">
		 	<s:select id="type" name="clsId" list="dropDownDTO" listKey="id" listValue="name" value="clsId" cssStyle="width:125px;" headerKey="" headerValue=""></s:select>		
		</td>
      </tr>
      <tr>
        <td height="40" colspan="6" align="center">
        	<input type="submit" name="Submit5" value="Search" class="search_input" />
        	
        </td>
      </tr>
    </table>
    </form>
</div>
</div>
 
        <div class="input_box">
		  <div class="content_box">
		    <table width="1010" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><div style="margin-right:17px;"><table width="993" border="0" cellspacing="0" cellpadding="0" class="list_table">
      <tr>
        <th width="46"><div align="left">
            <input name="checkbox2" type="checkbox"  onclick="cc(this)"/>
        <img src="${global_image_url}file_delete.gif" alt="Delete" width="16" height="16" border="0" onclick="del('ids');" /></div></th>
        <th width="148">Attribute Code</th>
        <th width="259">Attribute Name</th>
        <th width="229">Service Type</th>
        <th>Description</th>
        </tr>
 
    </table>
    </div></td>
  </tr>
  <tr>
    <td> <div class="list_box" style="height:340px; "><table width="993" border="0" cellspacing="0" cellpadding="0" class="list_table2">
 <s:iterator value="page.result">
      <tr>
        <td width="46"><input type="checkbox" value="${id }" name="ids"/></td>
        <td width="148"  align="center" style="word-break:break-all;word-wrap:break-word;width:148"><a href="javascript:void(0)" onclick="window.openiframe('serv/attributes!input.action?id=${id}&operation_method=edit',620,400)">${attributeCode}&nbsp;</a></td>
        <td width="259" style="word-break:break-all;word-wrap:break-word;width:259">${attributeName}&nbsp;</td>
        <td  width="229">
        <s:iterator value="dropDownDTO">
        <s:if test="id==clsId">
         ${name}&nbsp;
        </s:if>
        </s:iterator>
        </td>
        <td style="word-break:break-all;word-wrap:break-word;width:311">${description}&nbsp;</td>
        </tr>
 </s:iterator>
    </table>
        </div></td>
  </tr>
 
		 <tr><td>   
			<div class="grayr">
				<jsp:include page="/common/db_pager.jsp">
				  <jsp:param value="${ctx }/serv/attributes.action" name="moduleURL"/>
				</jsp:include>
			</div>
		</td></tr>
		 <tr>
		   <td align="center"></td>
		   </tr>
	  
            </table>
		</div>
		 <div  class="button_box" style="padding-top:20px;">
 			<a href="javascript:void(0)" onclick="window.openiframe('serv/attributes!input.action',620,400)">
        	<input type="button" name="Submit5" value="New" class="search_input" />
        	</a>
		 </div>	
		
  </div>
</div>	
</div>
</body>
</html>

