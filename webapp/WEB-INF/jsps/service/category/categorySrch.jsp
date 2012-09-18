<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
<script src="${global_js_url}util/util.js"
	type="text/javascript"></script>
	<script   language="JavaScript" type="text/javascript">
$(document).ready(function(){  
    $('tr:even >td').addClass('list_td2');

    $("#saveApprovedStatusTrigger").click(function(){
    	var objSeq=$('[name="categorySeq"]:checkbox:checked');
		var lenSeq = objSeq.length;
		if(!lenSeq){
			alert("Please select one item to continue your operation.");return;
		}
        var del_category_nos = get_checked_str("categorySeq");
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
			url:"serv/service_category!delCategoryListApproved.action",
			type:"get",
			data:"approved="+approved+"&approvedReason="+approvedReason+"&approvedStatusList="+del_category_nos,
			dataType:"json",
			success:function(data){
				if(hasException(data)){
					$('#deleteCategoryTrigger').attr("disabled", false);	
				}else{
					if(data.message == "success"){
						alert("The modification is ready to be submitted for the evaluation and will be applied only after it’s approved.");
					}else{
						alert("System error! Please contact system administrator for help.");
					}
					window.location.href="serv/service_category.action";
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
	var   a   =   document.getElementsByName("categorySeq");  
	for   (var   i=0;   i<a.length;   i++) {
		if(!a[i].disabled){
			a[i].checked = e.checked;
		}
	}
} 
function del_category(name){
	var del_category_nos = get_checked_str(name);
	if(del_category_nos == '')
	{
		alert('Please select one item to continue your operation.');
		return;
	}
	if(!confirm('Are you sure to delete?'))
	{
		return;
	}
	
	$.ajax({
		type: "POST",
		url: "category/categorySrch/delCategoryAct",
		data: 'id='+del_category_nos+"&type=<!-- {$categoryType} -->",
		success: function(msg){
			if(msg == 'success'){
			}else if(msg){
				alert('Failed to remove customer.Please contact system administrator for help.');
			}else {
				alert('System error! Please contact system administrator for help.');
			}
			window.location.reload();
		},
		error: function(msg){
			
			alert("Failed to remove the customer.Please contact system administrator for help.");
		}
	});
	return false;
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

function modifyStatusListClick() {
	var objSeq=$('[name="categorySeq"]:checkbox:checked');
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
//搜索前去除文本框输入字符串两端的空格
function search() {
	var filter_LIKES_categoryNo = $("#filter_LIKES_categoryNo").val();
	$("#filter_LIKES_categoryNo").attr("value",$.trim(filter_LIKES_categoryNo));
	var filter_LIKES_name = $("#filter_LIKES_name").val();
	$("#filter_LIKES_name").attr("value",$.trim(filter_LIKES_name));
	var filter_LIKES_description = $("#filter_LIKES_description").val();
	$("#filter_LIKES_description").attr("value",$.trim(filter_LIKES_description));
	$("#mainForm").submit();
}
</script>
</head>
<body class="content" style="background-image:none;">
<div class="scm">
<div class="title_content">
  <div class="title">
	<s:if test="categoryLevel==1">
	Service Business Units
	</s:if>
	<s:if test="categoryLevel==2">
	Services Categories
	</s:if>
	<s:if test="categoryLevel==3">
	Service Lines
	</s:if>
  </div>
</div>
<div class="search_box" style="font-weight:normal;">
<div class="single_search" style="font-weight:normal;">
<form id="mainForm" action="${ctx }/serv/service_category.action?categoryLevel=${categoryLevel}" method="get">
	<s:if test="categoryLevel==1">
	Business Unit No
	</s:if>
	<s:if test="categoryLevel==2">
	Service Category No 
	</s:if>
	<s:if test="categoryLevel==3">
	Service Line No
	</s:if>
      <input name="filter_LIKES_categoryNo" id="filter_LIKES_categoryNo" type="text" class="NFText" value="${param['filter_LIKES_categoryNo']}" size="15" />
      Name
      <input name="filter_LIKES_name" id="filter_LIKES_name" type="text" class="NFText" size="15" value="${param['filter_LIKES_name']}"/> 
      Description
      <input name="filter_LIKES_description" id="filter_LIKES_description" type="text" class="NFText" size="25" value="${param['filter_LIKES_description']}"/>
	  <input name="categoryLevel" id="categoryLevel" type="hidden" class="NFText" size="25" value="${categoryLevel}"/>     
      <input type="button" name="Submit5" value="Search" class="search_input" onclick="search();"/>
      
</form>
</div>
</div>	
<div class="input_box">
<table width="1010" border="0" cellspacing="0" cellpadding="0">
    <tr>
    <td>
    <div style="margin-right:17px;">
    <div class="scm">
	
<div class="input_box">
	
    <div style="margin-right:17px;">
    <table width="973" border="0" cellspacing="0" cellpadding="0" class="list_table2">
        <tr>
        <th width="46">
        <div align="left">
          <input name="checkbox" type="checkbox"  onclick="cc(this)" />
          <img src="${global_image_url}file_delete.gif" alt="Delete" id="deleteCategoryTrigger" width="16" height="16" border="0" onclick="modifyStatusListClick()"/></div></th>
        <th width="125">
				<s:if test="categoryLevel==1">
				Business Unit No
				</s:if>
				<s:if test="categoryLevel==2">
				Service Category No 
				</s:if>
				<s:if test="categoryLevel==3">
				Service Line No
				</s:if>
				
		</th>
        <th width="154">Name</th>
        <th width="166">Description</th>
        <th width="83">Status</th>
        <th width="106">Catalog Id</th>
        <th width="94">Asset Group </th>
        <th width="105">Business Division </th>
        <th>Modify Date </th>
        <!-- <th width="80">Creation Date</th> -->
        </tr>
        
	</table>
    </div>
  		<div class="list_box" style="height:340px; width:983px;">
    	<table width="966" border="0" cellspacing="0" cellpadding="0" class="list_table2" style="table-layout:fixed;word-break:break-all">
        	<s:iterator value="page.result">
      		<tr>
            <td width="46">
             <s:if test="status==\"ACTIVE\"">
        		
					<s:iterator value="statusOfapproved" id='number'>
        				<s:if test="#number==categoryId">
        				    <s:set name="approvedCategoryId" value="#number"></s:set>
        					<input type="checkbox" value="${categoryId }" name="categorySeq" disabled="disabled"/>
        				</s:if>
        			</s:iterator>
        		    <s:if test="#approvedCategoryId!=categoryId">
        		    	 <input type="checkbox" value="${categoryId }" name="categorySeq"/>
        		    	 <s:set name="approvedCategoryId" value="null"></s:set>
	        		    </s:if>
	        </s:if>
	        <s:else>
	        	<input type="checkbox" value="${categoryId }" name="categorySeq" disabled="disabled"/>
	        </s:else>
            </td>
        	<td width="125" style='word-break:break-all;word-wrap:break-word;width:90'>
        		<a href="${ctx }/serv/service_category!input.action?categoryId=${categoryId}&callBackName=categoryCreationForm&operation_method=edit&dodo=first">
        		 ${categoryNo}&nbsp;
         		</a>
        	</td>
        	<td width="154" style='word-break:break-all;word-wrap:break-word;width:154'>
			${name}
			      </td>
			      
        	<td width="166" style='word-break:break-all;word-wrap:break-word;width:166'>
	  		 ${description}    
     		 </td>
        	<td width="83"><div align="center">${status}&nbsp;</div></td>
        	<td width="106"><div align="left">
        	<s:if test="%{catalogId != null && catalogId.length()>12}">
					<s:property value="catalogId.substring(0,12)+'..'"/></s:if><s:else>${catalogId}</s:else>
        	&nbsp;</div></td>
        	<td width="94">${assetGroup}&nbsp;</td>
        	<td width="105">${businessDivision}&nbsp;</td>
        	<td><div align="center"><s:date name="modifyDate" format="yyyy-MM-dd" />&nbsp;</div></td>
       		<!-- <td width="80"><div align="center"><s:date name="creationDate" format="yyyy-MM-dd" />&nbsp;</div></td> -->
      		</tr>
           </s:iterator>
    	</table>
		</div>
	
		<div class="grayr">
			<jsp:include page="/common/db_pager.jsp">
					<jsp:param value="${ctx }/serv/service_category.action" name="moduleURL"/>
			</jsp:include>
		</div>
		
</div>
 <div  class="button_box">
<input type="button" value="New" class="search_input" onclick="window.location.href='${ctx}/serv/service_category!input.action?categoryLevel=${categoryLevel}&callBackName=categoryCreationForm'" />
  </div>

</div>
    </div>
    </td>
    </tr>
</table>
 
 
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
</body>
</html>