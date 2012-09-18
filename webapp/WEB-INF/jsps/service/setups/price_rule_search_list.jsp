<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include  file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Price rules</title>
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
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
			url:"serv/price_rule!delete.action?delStr="+del_nos,
			dataType:"json",
			success: function(msg){
				if(msg.message=="success"){
					window.location.reload();
				}else{
					alert("Failed to remove the item.Please contact system administrator for help.");	
				}
			},
			error: function(msg){
				alert("Failed to remove the item. Please contact system administrator for help.");
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
<div class="title_content">
  <div class="title">Price Rule List</div>
</div>
<div class="search_box" >
  <div class="search_box_two">
  <form id="mainForm" action="" method="post">
    <table border="0" cellpadding="0" cellspacing="0" class="General_table">
      <tr>
        <td>Rule ID</td>
        <td width="120"><input name="filter_EQI_id" type="text" class="NFText" size="20" value="${param['filter_EQI_id']}"/></td>
        <td>Service Type</td>
        <td width="120">
			<s:select id="type" name="clsId" list="dropDownDTO" listKey="id" listValue="name" value="clsId" cssStyle="width:125px;" headerKey="" headerValue=""></s:select>
		</td>
        <td>Rule Type</td>
        <td width="120">
        <s:if test="ruleType==\"Cost\"">
        	<select name="ruleType">
				<option></option>
				<option value="Price">Price</option>
				<option value="Cost" selected="selected">Cost</option>
			</select>
        </s:if>
        <s:elseif test="ruleType==\"Price\"">
        	<select name="ruleType">
        		<option></option>
				<option value="Price" selected="selected">Price</option>
				<option value="Cost">Cost</option>
			</select>
        </s:elseif>
        <s:else>
	        <select name="ruleType">
	        	<option></option>
	        	<option value="Price">Price</option>
				<option value="Cost">Cost</option>
			</select>
        </s:else>
		</td>
      </tr>
      <tr>
        <td height="40" colspan="6" align="center"><input type="submit" name="Submit5" value="Search" class="search_input" /></td>
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
        <th width="60">Rule  Id</th>
        <th width="258">Rule Name</th>
        <th width="139">Service Type</th>
        <th width="129">Rule Type</th>
        <th>Description</th>
        </tr>
 
    </table>
    </div></td>
  </tr>
  <tr>
    <td> <div class="list_box" style="height:340px; "><table width="993" border="0" cellspacing="0" cellpadding="0" class="list_table2">
     <s:iterator  value="page.result">
	      <tr>
	        <td width="46"><input type="checkbox" value="${id }" name="ids"/></td>
	        <td width="60" align="center"><a href="serv/price_rule!input.action?id=${id}&operation_method=edit">${id}</a></td>
	        <td width="258" style="word-break:break-all;word-wrap:break-word;width:258">${name}</td>
	        <td width="139">
	         <s:iterator value="dropDownDTO">
       			 <s:if test="id==clsId">
         			${name}&nbsp;
        		</s:if>
       		 </s:iterator>
	        </td>
	        <td width="129">${ruleType}</td>
	        <td style="word-break:break-all;word-wrap:break-word;width:361">${description}</td>
          </tr>
      </s:iterator>
    </table>
        </div></td>
  </tr>
 
		 <tr><td>  
			<div class="grayr">
				<jsp:include page="/common/db_pager.jsp">
				  <jsp:param value="${ctx }/serv/price_rule.action" name="moduleURL"/>
				</jsp:include>
			</div>
		</td></tr>
	  
         </table>
		</div>
  </div>
  <div  class="button_box">
<input type="button" name="Submit193" value="New Rule" class="search_input" onclick="window.location.href='${ctx}/serv/price_rule!input.action'"  style="background-image:url(images/input_searchbg2.jpg) ; width:120px;"/>
</div>	
</div>	
</body>
</html>

