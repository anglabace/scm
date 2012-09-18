<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>Order Management</title>
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
<script type="text/javascript" src="${global_js_url}scm/catalog.js"></script>
<script language="JavaScript" type="text/javascript">
$(document).ready(function(){  
    $('tr:even >td').addClass('list_td2'); 

    $("#saveStatusTrigger").click(function(){
    	var objSeq=$('[name="catalogSeq"]:checkbox:checked');
		var lenSeq = objSeq.length;
		if(!lenSeq){
			alert("Please select one item to continue your operation.");return;
		}
     
        var del_category_nos = get_checked_str("catalogSeq");
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
			url:"product/catalog!delCatalogListApproved.action",
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
					window.location.href="product/catalog.action";
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
function   cc(e)  
{  
	var a = document.getElementsByName("catalogSeq");  
	for(var i=0; i<a.length; i++) 
	{
		if(!a[i].disabled){
			a[i].checked = e.checked;
		}
	}
}

//搜索前去除文本框输入字符串两端的空格
function search() {
	var filter_LIKES_catalogId = $("#filter_LIKES_catalogId").val();
	$("#filter_LIKES_catalogId").attr("value",$.trim(filter_LIKES_catalogId));
	var filter_LIKES_catalogName = $("#filter_LIKES_catalogName").val();
	$("#filter_LIKES_catalogName").attr("value",$.trim(filter_LIKES_catalogName));
	var filter_LIKES_description = $("#filter_LIKES_description").val();
	$("#filter_LIKES_description").attr("value",$.trim(filter_LIKES_description));
	$("#mainForm").submit();
}
function modifyStatusListClick() {
	var objSeq=$('[name="catalogSeq"]:checkbox:checked');
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

<body class="content" style="background-image: none;">

<div class="scm">
<div class="title_content">
<div class="title">Catalogs</div>
</div>
<div class="search_box">
<div class="single_search">
<form id="mainForm" action="" method="get">
<table border="0" cellpadding="0" cellspacing="10">
	<tr>
		<td>Catalog ID</td>
		<td width="120"><input name="filter_LIKES_catalogId" id="filter_LIKES_catalogId" type="text"
			class="NFText" value="${param['filter_LIKES_catalogId']}" size="20" /></td>
		<td>Name</td>
		<td width="120"><input name="filter_LIKES_catalogName" id="filter_LIKES_catalogName"
			type="text" class="NFText"
			value="${param['filter_LIKES_catalogName']}" size="20" /></td>
		<td>Description</td>
		<td width="120"><input name="filter_LIKES_description" id="filter_LIKES_description"
			type="text" class="NFText"
			value="${param['filter_LIKES_description']}" size="20" /></td>
	</tr>
	<tr>
		<td height="40" colspan="6" align="center"><input type="button"
			value="Search" class="search_input" onclick="search();" /> 
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
		<td>
		<div style="margin-right: 17px;">
		<div class="scm">
		<div class="input_box">
		<div class="content_box">
		<div style="margin-right: 17px;">
		<table width="966" border="0" cellspacing="0" cellpadding="0"
			class="list_table2">
			<tr>
				<th width="46">
				<div align="left"><input name="checkbox" type="checkbox"
					onclick="cc(this)" /> <img
					src="${global_image_url}file_delete.gif" alt="Delete"
					id="deleteTrigger" width="16" height="16" border="0"  onclick="modifyStatusListClick()"/></div>
				</th>
				<th width="65">Catalog ID</th>
				<th width="150">Name</th>
				<th width="60">Status</th>
				<th width="60">Type</th>
				<th width="240">Description</th>
				<th width="70">Modify Date</th>
				<th width="60">Currency</th>
				<th width="80">Price Limit Control</th>
				<th>Publish Date</th>
			</tr>
		</table>
		</div>
		<div class="list_box" style="height: 340px; width: 983px;">
		<table width="966" border="0" cellspacing="0" cellpadding="0"
			class="list_table2" style="table-layout:fixed;word-break:break-all">
			<s:iterator value="page.result">
				<tr>
					<td width="46">
					<s:if test="status==\"ACTIVE\"">
								<s:iterator value="statusOfapproved" id='number'>
			        				<s:if test="#number==id">
			        				    <s:set name="approvedCatalogId" value="#number"></s:set>
			        					<input type="checkbox" value="${id}" name="catalogSeq" disabled="disabled"/>
			        				</s:if>
			        			</s:iterator>
			        		    <s:if test="#approvedCatalogId!=id">
			        		    	 <input type="checkbox" value="${id}" name="catalogSeq" />
			        		    	 <s:set name="approvedCatalogId" value="null"></s:set>
			        		    </s:if>
			        </s:if>
			        <s:else>
			        	<input type="checkbox" value="${id}" name="catalogSeq" disabled="disabled"/>
			        </s:else>
					</td>
					<td width="65" style="word-break:break-all;word-wrap:break-word;width:65"><a href="${ctx }/product/catalog!input.action?id=${id}&callBackName=catalogCreationForm&operation_method=edit">
						${catalogId}
						&nbsp;</a></td>
					<td width="150" style="word-break:break-all;word-wrap:break-word;width:150">
					
						${catalogName}
						
					&nbsp;</td>
					<td width="60" style="overflow: hidden;">
					<div align="center">${status}&nbsp;</div>
					</td>
					<td width="60">
					<div align="center">${type}&nbsp;</div>
					</td>
					<td width="240" style="word-break:break-all;word-wrap:break-word;width:240">
						${description}
					&nbsp;</td>
					<td width="70">
					<div align="center"><s:date name="modifyDate" format="yyyy-MM-dd" />
					&nbsp;</div>
					</td>
					<td width="60">
					<div align="center">${currencyCode}&nbsp;</div>
					</td>
					<td width="80">
					<div align="center">${priceLimit}&nbsp;</div>
					</td>
					<td>
					<div align="center">
						<s:date name="publishDate" format="yyyy-MM-dd" />&nbsp;
					</div>
					</td>
				</tr>
			</s:iterator>
		</table>
		</div>
		<div class="grayr">
			<jsp:include page="/common/db_pager.jsp">
			  <jsp:param value="${ctx }/product/catalog.action" name="moduleURL"/>
			</jsp:include>
		</div>
		</div>
		</div>
		</div>
		</div>
		</td>
	</tr>
</table>
</div>
</div>

 <div align="center"><br/>
<input type="button"
			value="New" class="search_input"
			onclick="window.location.href='${ctx}/product/catalog!input.action?callBackName=catalogCreationForm'" />
		<input type="button" id="copyCtlgDialogTrigger"
			value="Copy as New Catalog" class="search_input2" />
		<div id="copyCtlgDialog" title="Copy as new catalog"></div>
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
			class="style_botton" value="Modify" id="saveStatusTrigger" />
		<input type="button" value="Cancel" class="style_botton"
			onclick="$('#modifyStatusDialog').dialog('close');" /></div>
		</div>
		</th>
	</tr>
</table>
  </div>


</body>
</html>
