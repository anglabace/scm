<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>

<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>

<script type="text/javascript">
<!--
$().ready(function(){
	var parentCtgValue = parent.$("#ctgName").attr("value");
	$("#ctgParentCtgName").val(parent.$('[name="ctgNo"]').val());
	parent.$('#chooseSubCategoryDialog').dialog({
		autoOpen: false,
		height: 480,
		width: 360,
		modal: true,
		bgiframe: true,
		buttons: {}
	});
	$("#catAddTrigger").click(function(){
		parent.$('#chooseSubCategoryDialog').dialog("option", "open",function(){
			var catNo = $("#ctgParentCtgName").val();
			var htmlStr = '<iframe id="chooseCategory_iframe" src="${ctx}/product/product_category!cateChooseSubCatList.action?categoryLevel=${categoryLevel}&sessionCategoryId=${sessionCategoryId}&categoryId=${categoryId}" height="400" width="320" scrolling="no" style="border:0px" frameborder="0"></iframe>';
			parent.$('#chooseSubCategoryDialog').html(htmlStr);
		});	
		parent.$('#chooseSubCategoryDialog').dialog('open');
	});
});
function saveSubCategory(){
	var ctgNo = $("input[name='ctgNo']").val();
	if(!ctgNo){
		alert("Please enter the Category Id.");
		$("input[name='ctgNo']").focus();
		return;
	}
	if(ctgNo.length>60){
		alert("The length of the Category Id is out of maximum limit 60.");
		$("input[name='ctgNo']").focus();
		return;
	}
	var ctgName = $("input[name='ctgName']").val();
	if(!ctgName){
		alert("Please enter the Name");
		$("input[name='ctgName']").focus();
		return;
	}
	if(ctgName.length>50){
		alert("The length of the Category name is out of maximum limit 50");
		$("input[name='ctgName']").focus();
		return;
	}
	var ctgId = $("#ctgId").val();
	$.ajax({
		type:"POST",
		url:"product/product_category!addSubProductCategory.action?productCategoryKey="+ctgId,
		data:$("#formSubPrdCategory").serialize(),
		success: function(msg){
			if(msg=="success"){
				var ctgNo =  $("#ctgNo").val();
				var ctgName = $("#ctgName").val();
				var ctgDescription = $("#ctgDescription").val();
				var ctgStatus = $("#ctgStatus").val();
				var ctgAstGrp = $("#ctgAstGrp").val();
				var ctgMby = $("#ctgMdate").val();
				var ctgCby = $("#ctgCdate").val();
				var https = '<a href="${ctx }/product/product_category!input.action?categoryId='+ctgId+'&callBackName=categoryCreationForm&operation_method=edit&dodo=first" target="_parent">'
				var appendStr = "<tr id=del_"+ctgId+"><td width='46'><input type='checkbox' value="+ctgId+" name='ctgIds'/></td>";
				appendStr += "<td width='100' style='word-break:break-all;word-wrap:break-word;width:100'>&nbsp;"+https+ctgNo+"</a></td>";
				appendStr += "<td width='180' style='word-break:break-all;word-wrap:break-word;width:180'>&nbsp;"+ctgName+"</td>";
				appendStr += "<td width='200' style='word-break:break-all;word-wrap:break-word;width:200'>&nbsp;"+ctgDescription+"</td><td width='70'>&nbsp;"+ctgStatus+"</td><td width='150'>&nbsp;"+ctgAstGrp+"</td><td width='80'>&nbsp;"+ctgMby+"</td><td>&nbsp;"+ctgCby+"</td></tr>";
				parent.$("#subCategory_iframe").contents().find("#pdCategoryTb").append(appendStr);
				parent.$('#addPrCategoryDialog').dialog('close');
				parent.$('#addPrCategoryDialog').dialog('destory');
			}
		},
		error: function(msg){
			alert("System error! Please contact system administrator for help.");
		}
	});
}
-->
</script>

<style type="text/css">
<!--
body {margin:10px 0px 0px 20px;width:620px;}
-->
</style>
</head>

<body>
<form method="post" id="formSubPrdCategory">
<table border="0" cellpadding="0" cellspacing="0" class="General_table">
  <tr>
    <th><span class="important">*</span> 
		<s:if test="categoryLevel==2">
		Product Category No 
		</s:if>
		<s:else>
		Product Line No
		</s:else>
	</th>
    <td>
    <input name="ctgId" id="ctgId" type="hidden" />
    <input type="hidden" name="sessionCategoryId" id="sessionCategoryId" value="${sessionCategoryId}" />
    <input name="ctgNo" id="ctgNo" type="text" class="NFText" size="25" readonly="readonly" />
    <img src="images/search.gif" id="catAddTrigger" width="16" height="16" style="cursor:pointer;" />
    </td>
    <th><span class="important">*</span> Name</th>
    <td><input name="ctgName" id="ctgName" type="text" class="NFText" size="25" readonly="readonly"/></td>
  </tr>
  <tr>
    <th>Asset Group</th>
    <td>
    	<s:select name="ctgAstGrp" id="ctgAstGrp" list="categoryDropdownList" listKey="value" listValue="value"/>
    </td>
    <th valign="top"><span class="important">*</span> Status</th>
    <td><input name="ctgStatus" id="ctgStatus" type="text" class="NFText" value="INACTIVE" size="25" readonly="readonly" /></td>
  </tr>
  <tr>
    <th valign="top">Description</th>
    <td colspan="3"><textarea name="ctgDescription" id="ctgDescription" class="content_textarea2"></textarea></td>
  </tr>
  <tr>
    <th>Parent Category</th>
    <td><input name="ctgParentCtgName" id="ctgParentCtgName"  type="text" class="NFText" size="25" readonly="readonly" value="${parentCategoryNo}"/> <input type="hidden" name="ctgParentCtg" value="${parentCategoryNo}" /></td>
    <th>Previous Category</th>
    <td><input name="ctgPreCtgName" id="ctgPreCtgName" type="text" class="NFText" size="25" readonly="readonly" /> <input type="hidden" name="ctgPreCtg" /></td>
  </tr>
  <tr>
    <th>Date Modified</th>
    <td><input name="ctgMdate" id="ctgMdate" type="text" class="NFText" size="25" readonly="readonly" /></td>
    <th>Modified By</th>
    <td><input name="ctgMby" id="ctgMby" type="text" class="NFText" size="25" readonly="readonly" /></td>
  </tr>
  <tr>
    <th>Date Created</th>
    <td><input name="ctgCdate" id="ctgCdate" type="text" class="NFText" size="25" readonly="readonly" /></td>
    <th>Created By</th>
    <td><input name="ctgCby" id="ctgCby" type="text" class="NFText" size="25" readonly="readonly" /></td>
  </tr>
</table>
<div  style="margin-top:20px; text-align:center">
  <input type="button" class="style_botton" value="Save" onclick="saveSubCategory();" />
  <input type="button" class="style_botton" value="Cancel" onclick="parent.$('#addPrCategoryDialog').dialog('close');parent.$('#addPrCategoryDialog').dialog('destory');" />
</div>
</form>
</body>
</html>
