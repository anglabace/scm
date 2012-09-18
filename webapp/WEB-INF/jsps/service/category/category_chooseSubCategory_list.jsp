<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
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
<script src="${global_js_url}table.js" type="text/javascript"></script>
<script language="JavaScript" type="text/javascript">
<!--
function fzCategoryNo(uId){
	var ctgId = $("#ctgId"+uId).val();
	var ctgNoS = $("#ctgNo" + uId).val();
	var pdtCategoryList = parent.$("#categoryIds").val();
	var pdtCategory =  pdtCategoryList.split("<;;>");
	for(var i = 0;i<pdtCategory.length;i++){
		if(ctgId==pdtCategory[i]){
			alert(ctgNoS+" has been added.");return;
		}
	}
	var obj_subCategory = parent.$("#catSubCatAdd_iframe").contents();
	var ctgCby = $("#ctgCby"+uId).val();
	var ctgMby = $("#ctgMby"+uId).val();
	if(ctgCby!=null||ctgCby!=""){
		ctgCby = getUserName(ctgCby,obj_subCategory,'cby');
	}
	if(ctgMby!=null||ctgMby!=""){
		ctgMby = getUserName(ctgMby,obj_subCategory,'mby');
	}
	obj_subCategory.find("#ctgId").val($("#ctgId"+uId).val());	
	obj_subCategory.find("#ctgNo").val($("#ctgNo" + uId).val());
	obj_subCategory.find("#ctgName").val($("#ctgName"+uId).val());
	obj_subCategory.find("#ctgAstGrp").val($("ctgAstGrp" + uId).val());
	obj_subCategory.find("#ctgStatus").val($("#ctgStatus"+uId).val());
	obj_subCategory.find("#ctgDescription").val($("#ctgDescription"+uId).val());
	//obj_subCategory.find("#ctgParentCtgName").val($("#ctgParentCtgName"+uId).val());
	obj_subCategory.find("#ctgPreCtgName").val($("#ctgPreCtgName"+uId).val());
	obj_subCategory.find("#ctgMdate").val($("#ctgMdate"+uId).val());
	obj_subCategory.find("#ctgCdate").val($("#ctgCdate"+uId).val());
	
	parent.$('#chooseSubCategoryDialog').dialog('close');
	parent.$('#chooseSubCategoryDialog').dialog('destory');
}
function getUserName(userId,obj_subCategory,type){
	$.ajax({
		type:"POST",
		url:"product/product_category!searchUserName.action?userId="+userId,
		dataType:"json",
		success: function(msg){
			if(msg.message=="success"){
				if(type = 'cby'){
					obj_subCategory.find("#ctgCby").val(msg.name);
				}
				if(type = 'mby'){
					obj_subCategory.find("#ctgMby").val(msg.name);
				}
				
			}else{
			}
			
		},
		error: function(msg){
		}
	});
}
function chooseCategoryNo(uId) {
	
			fzCategoryNo(uId);
}
$(document).ready(function(){  
    $('tr:even >td').addClass('list_td2');
	
});
-->
 </script>
</head>

<body>
	<b>Choose Category: Category List</b><br/>
	<form id="mainForm" action="" method="get">
	<table width="300" border="0" cellpadding="0" cellspacing="0" class="list_table">
          <tr>
            <th>Service Category No</th>
            </tr>
        </table>
    <table width="300" border="0" cellspacing="0" cellpadding="0" class="list_table2">
    <s:iterator value="page.result">
    <tr>
    <td width="300">
    	<input type="hidden" id="ctgId${categoryId}" value="${categoryId}" />
    	<input type="hidden" id="ctgNo${categoryId}" value="${categoryNo}" />
        <input type="hidden" id="ctgName${categoryId}" value="${name}" />
        <input type="hidden" id="ctgAstGrp${categoryId}" value="${assetGroup}" />
        <input type="hidden" id="ctgStatus${categoryId}" value="${status}" />
        <input type="hidden" id="ctgDescription${categoryId}" value="${description}" />
        <input type="hidden" id="ctgParentCtgName${categoryId}" value="${parentCatName}" />
        
        <input type="hidden" id="ctgPreCtgName${categoryId}" value="${previousCatName}" />
        
        <input type="hidden" id="ctgMdate${categoryId}" value="<s:date name="modifyDate" format="yyyy-MM-dd" />" />
        <input type="hidden" id="ctgMby${categoryId}" value="${modifiedBy}" />
        
        <input type="hidden" id="ctgCdate${categoryId}" value="<s:date name="creationDate" format="yyyy-MM-dd" />" />
        <input type="hidden" id="ctgCby${categoryId}" value="${createdBy}" />
        <a href="javascript:chooseCategoryNo('${categoryId}')">${categoryNo}&nbsp;</a>
    </td>   
    </tr>
   </s:iterator>
    </table>
    </form>
    <div class="grayr">
		<jsp:include page="/common/db_pager.jsp">
			<jsp:param value="${ctx}/serv/service_category!cateChooseSubCatList.action" name="moduleURL"/>
		</jsp:include>
	</div>
	<script type="text/javascript">
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
	</script>
</body>
</html>