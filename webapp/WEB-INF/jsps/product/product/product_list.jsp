<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1" type="text/javascript"></script>
<script src="${global_js_url}util/util.js"
	type="text/javascript"></script>
<script   language="JavaScript" type="text/javascript">
$(document).ready(function(){  
    $('tr:even >td').addClass('list_td2'); 

    $("#saveApprovedStatusTrigger").click(function(){
    	var objSeq=$('[name="pdtServSeq"]:checkbox:checked');
		var lenSeq = objSeq.length;
		if(!lenSeq){
			alert("Please select one item to continue your operation.");return;
		}
      
        var del_category_nos = get_checked_str("pdtServSeq");
        var approved = $("#statusApprove").val();
		var approvedReason = $("#statusReason").val();
		approvedReason = $.trim(approvedReason);
		if(approvedReason==''||approvedReason==null){
			alert("Please enter the reason.");return;
		}
		 if(!confirm("Are you sure to delete?")){
	            return;
	        }
        $.ajax({
			url:"product/product!delProductListApproved.action",
			type:"get",
			data:"approved="+approved+"&approvedReason="+approvedReason+"&approvedStatusList="+del_category_nos,
			dataType:"json",
			success:function(data){
				if(hasException(data)){
					$('#saveApprovedStatusTrigger').attr("disabled", false);	
				}else{
					if(data.message == "success"){
						alert("The modification is ready to be submitted for the evaluation and will be applied only after it’s approved.");
					}else{
						alert("System error!Please contact system administrator for help.");
					}
					parent.window.location.href="product/product!search.action";
				}
			},
			error:function(data){
				alert("System error! Please contact system administrator for help.");
			},
			async:false
		});
        $("#modifyStatusDialog").dialog("close");
    });
});
function modifyStatusListClick() {
	var objSeq=$('[name="pdtServSeq"]:checkbox:checked');
	var lenSeq = objSeq.length;
	if(!lenSeq){
		alert("Please select one item to continue your operation.");return;
	}
	$("#modifyStatusDialog").dialog({
		autoOpen: false,
		height: 300,
		width: 600,
		modal: true,
		bgiframe: true,
		buttons: {}
	});	
	$('#modifyStatusDialog').dialog("option", "open",function() {
		
	});
	$('#modifyStatusDialog').dialog('open');	
}
function   cc(e)  
{  
	var   a   =   document.getElementsByName("pdtServSeq");  
	for   (var   i=0;   i<a.length;   i++) {
		if(!a[i].disabled){
			a[i].checked = e.checked;
		}
	}
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
<div class="input_box">
<div class="content_box">
<div style="margin-right:17px;">
<form id="mainForm" method="post" action="">
<table width="976" border="0" cellspacing="0" cellpadding="0" class="list_table2">
  <tr>
    <th width="46"><div align="left">
      <input name="pdtServCh" type="checkbox" onclick="cc(this)" />
      <img src="images/file_delete.gif" id="delPdtServTrigger" alt="Delete" width="16" height="16" border="0"  onclick="modifyStatusListClick()"/></div></th>
    <th width="70">Catalog No</th>
    <th width="125">Type</th>
    <th width="150">Name</th>
    <th width="200">Description</th> 
    <th width="60">Status</th>
    <th width="55">Size</th>
    <%--<th width="65">Unit Price </th>--%>
    <th width="80">Modify Date </th>
    <th>Creation Date</th>
    </tr>
</table>
</form>
</div>
  <div class="list_box" style="height:320px; width:993px;">
    <table border="0" cellspacing="0" cellpadding="0" class="list_table2" width="976"  style="table-layout:fixed;word-break:break-all">
      <s:iterator value="pageBean.result">
      <tr>
        <td width="46">
        <s:if test="status==\"ACTIVE\"">
        		
					<s:iterator value="statusOfapproved" id='number'>
        				<s:if test="#number==productId">
        				    <s:set name="approvedProductId" value="#number"></s:set>
        					<input type="checkbox" value="${productId}" name="pdtServSeq"  disabled="disabled"/>
        				</s:if>
        			</s:iterator>
        		    <s:if test="#approvedProductId!=productId">
        		    	 <input type="checkbox" value="${productId}" name="pdtServSeq" />
        		    	 <s:set name="approvedProductId" value="null"></s:set>
        		    </s:if>
        </s:if>
        <s:else>
        	<input type="checkbox" value="${productId}" name="pdtServSeq" disabled="disabled"/>
        </s:else>
        </td>
        <td width="70" style="word-break:break-all;word-wrap:break-word;width:70">
        	<a href="${ctx}/product/product!input.action?id=${productId}&type=${type}&operation_method=edit" target="_parent">
            	${catalogNo}&nbsp;
            </a>
        </td>
        <td width="125">${type}&nbsp;</td>
        <td width="150" style="word-break:break-all;word-wrap:break-word;width:150">${name}&nbsp;</td>
        <td width="200" style="word-break:break-all;word-wrap:break-word;width:200">${description}&nbsp;</td> 
        <td width="60">${status}&nbsp;</td>
        <td width="55">${size}${uom }&nbsp;</td>
        <%--<td width="65"><div align="right">${symbol }${unitPrice}&nbsp;</div></td>--%>
        <td width="80"><div align="center"><s:date name="modifyDate" format="yyyy-MM-dd" />&nbsp;</div></td>
        <td><div align="center"><s:date name="creationDate" format="yyyy-MM-dd" />&nbsp;</div></td>
      </tr>
      </s:iterator>
    </table>
	</div>
	<div class="grayr">
		<jsp:include page="/common/db_pager.jsp">
			  <jsp:param value="${ctx }/product/product.action" name="moduleURL"/>
			</jsp:include>
	</div>
  </div>
  </div>
  <div  class="button_box">
&nbsp;&nbsp;<input type="button" value="New" class="search_input" onclick="parent.location.href='${ctx}/product/product!input.action';"/> 
		
  </div>
  
  
</div>
<div id="modifyStatusDialog" title="Modify Products Status"
	style="display: none;">
<table border="0" cellpadding="0" cellspacing="0" class="General_table"
	style="margin-left: 40px;">
	<tr>
		<th height="26">
		<div align="left">Status <select name="statusApprove"
			id="statusApprove">
				<option value="INACTIVE" selected="selected">INACTIVE</option>
			
		</select></div>
		</th>
	</tr>
	<tr>
		<th height="24" valign="top">
		<div align="left">Choose the reason to update the catalog
		status:</div>
		</th>
	</tr>
	<tr>
		<th valign="top"><textarea name="statusReason" id="statusReason"
			cols="70" rows="2" class="content_textarea"></textarea></th>
	</tr>
	<tr>
		<th valign="top">
		<div align="center" style="margin: 10px;">
		<div id="cat_name" style='display: block;'>
		<input type="button"
			class="style_botton" value="Modify" id="saveApprovedStatusTrigger" />
		<input type="button" value="Cancel" class="style_botton"
			onclick="$('#modifyStatusDialog').dialog('close');" /></div>
		</div>
		</th>
	</tr>
</table>
</div>
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
</script>
</body>
</html>