<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"  />
<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />


<script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}expland.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>

<script src="${global_js_url}jquery/jquery.js" type="text/javascript" language="javascript"></script>
<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1" type="text/javascript"></script>
<script src="${global_js_url}/recordTime.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}scm/product/manager_task.js?v=2"></script>
<script>
$(function(){
	var type = "${type}"
	if(type == "PRODUCT"){
		disableTabByTitle("Service Segment");
	}
	if(type == "SERVICE"){
		disableTabByTitle("Product Segment");
	}
	
	$("#catalogTopForm").validate({
		invalidHandler: function(form, validator) {
	        $.each(validator.invalid, function(key,value){
	            alert(value);
	            $("[name='"+key+"']").focus();
	            return false;
	        });
    	},
		rules: {
    		cataId: {required:true, minlength:1, maxlength:20},
    		cataName: {required:true, minlength:1, maxlength:50}
		},
		messages: {
			cataId: {
				required: "Please specify your Catalog Id",
				maxlength: "The length of the Catalog ID is out of maximum limit 19."
			},
			cataName: {
				required: "Please specify your Name",
				maxlength: "The length of the Catalog Name is out of maximum limit 49."
			}
		},
		errorPlacement: function(error, element) {}		
	});
});
var isSaved = false;
window.onbeforeunload = function() {
	if('${approvedMethod}'!="approvedEdit"){
		if(isSaved === false){
			return 'Do you want to leave without saving data?';
		}
	}
}

function checktab(){
	var type = $("#type option:selected").text();
	var activeTab = 0;
	var mainContainerID = "dhtmlgoodies_tabView1";
	if(type=="All"){
		enableTabByTitle('Product Categories');
		enableTabByTitle('Services Categories');
	}else if(type == "Product"){
		showTab(mainContainerID,activeTab);
		enableTabByTitle('Product Categories');
		disableTabByTitle('Services Categories');
	}else{
		activeTab = 1;
		showTab(mainContainerID,activeTab);
		disableTabByTitle('Product Categories');
		enableTabByTitle('Services Categories');
	}
}
</script>

<script type="text/javascript" src="${global_js_url}scm/catalog.js"></script>
</head>

<body class="content" style="background-image:none;" onload="recordTime()">

<div class="scm">
<div class="title_content">
  <div class="title">
  	<s:if test="catalogId==null">
  		New Catalog
  	</s:if>
  	<s:else>
  		Catalog - # ${catalogName}
  	</s:else>
  </div>
</div>
<div class="input_box">
<form id="catalogTopForm">

  <table border="0" cellpadding="0" cellspacing="0" class="General_table">
    <tr>
      <th width="120"><span class="important">*</span> Catalog ID </th>
      <td>
      	<s:if test="catalogId==null">
      		<input name="catalogDTO.catalogId" id="cataId1" type="text" class="NFText" size="20"/>      
      	</s:if>
      	<s:else>
      		<input name="catalogDTO.catalogId" id="cataId1" type="text" class="NFText" size="20" value="${catalogId}"  readonly="readonly"/>      
      	</s:else>
      </td>
      <th width="130"><span class="important">*</span> Name</th>
      <td>
      <s:if test="catalogId==null">
      	 <input name="catalogDTO.catalogName" id="catalogName" type="text" class="NFText" size="20"/>
      </s:if>
      <s:else>
      	<s:if test="approvedName!=null&&approvedMethod==\"approvedEdit\"">
      		 <input name="catalogDTO.catalogName" id="catalogName" type="text" class="NFText" size="20" value="${approvedName }"  readonly="readonly"/>
      	</s:if>
      	<s:else>
      	 	 <input name="catalogDTO.catalogName" id="catalogName" type="text" class="NFText" size="20" value="${catalogName }"  readonly="readonly"/>
      	</s:else>
      </s:else>
      	<s:if test="catalogId!=null&&approvedMethod!=\"approvedEdit\""><input type="button" value="Modify" class="style_button" onclick="modifyCatalogNameClick()" /></s:if>
      </td>
    </tr>
    <tr>
      <th valign="top">Type</th>
      <td>
      <s:if test="catalogId==null">
	       <select name="catalogDTO.type" id="type" onchange="checktab()">
				<option value="ALL">All</option>
				<option value="PRODUCT">Product</option>
				<option value="SERVICE">Service</option>
			</select>
      </s:if>
      <s:else>
      	 <select name="catalogDTO.type" id="type">
      	 	<s:if test="type=='ALL'">
      	 		<option value="ALL" selected="selected">All</option>
      	 	</s:if>
			<s:elseif test="type=='PRODUCT'">
				<option value="PRODUCT" selected="selected">Product</option>
			</s:elseif>
			<s:elseif test="type=='SERVICE'">
				<option value="SERVICE" selected="selected">Service</option>
			</s:elseif>
		</select>
      </s:else>
     </td>
      <th><span class="important">*</span> Status</th>
      <td>
      <s:if test="status==null">
      	 	<input name="catalogDTO.status" id="status" type="text" class="NFText" value="INACTIVE" size="20" readonly="readonly" />
      </s:if>
      <s:else>
      	 <s:if test="approvedStatus!=null&&approvedMethod==\"approvedEdit\"">
      		  <input name="catalogDTO.status" id="status" type="text" class="NFText" value="${approvedStatus}" size="20" readonly="readonly" />
      	</s:if>
      	<s:else>
      	 	  <input name="catalogDTO.status" id="status" type="text" class="NFText" value="${status}" size="20" readonly="readonly" />
      	</s:else>
      </s:else>
      <s:if test="catalogId!=null&&approvedMethod!=\"approvedEdit\"">
     	 <input type="button" value="Modify" class="style_button" onclick="modifyCatalogStatusClick()" />
      </s:if>
      </td>
    </tr>
    <tr>
      <th valign="top">Description</th>
      <td><textarea name="catalogDTO.description" class="content_textarea2">${description }</textarea></td>
      <td>&nbsp;</td>
      <td valign="top"><s:if test="defaultFlag==\"Y\"">
			<input type="checkbox" name="catalogDTO.defaultFlag" id="checkbox" value="Y"
				checked="checked" />
		</s:if>
		<s:else>
			<input type="checkbox" name="catalogDTO.defaultFlag" id="checkbox" value="Y" />
		</s:else>
Default</td>
    </tr>
    <tr>
      <th valign="top"><span class="important">*</span> Currency</th>
      <td><s:select name="catalogDTO.currencyCode" list="currencyDropdownList"
			listKey="name" listValue="name" value="currencyCode"/>
        </td>
      <th></th>
      <td>
      <s:if test="priceLimit==\"Y\"">
			<input name="catalogDTO.priceLimit" type="checkbox" id="checkbox2" value="Y"
				checked="checked" />
		</s:if> <s:else>
			<input name="catalogDTO.priceLimit" type="checkbox" id="checkbox2" value="Y" />
		</s:else>
        Enforce price Limit
      </td>
    </tr>
    <tr>
      <th>Publisher</th>
      <td>
      	<input type="text" class="NFText" value="${publishUser }" readonly="readonly" size="20" />
      	<input name="catalogDTO.publisher" type="hidden" class="NFText" value="${publisher}" readonly="readonly" size="20" />
      	</td>
      <th>Zone Published </th>
      <td>
        <s:select name="catalogDTO.publishZone" list="catalogDropdownList"
			listKey="value" listValue="text" value="publishZone" />
      </td>
    </tr>
    <tr>
      <th>Date Published </th>
      <td><input name="catalogDTO.publishDate" id="catalogDTO.publishDate" type="text" class="NFText" style="width:126px;" value="<s:date name='publishDate' format='yyyy-MM-dd' />" readonly="readonly" /></td>
      <th>&nbsp;</th>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <th>Date Modified </th>
      <td><input name="catalogDTO.modifyDate" type="text" class="NFText" disabled="disabled" value="<s:date name='modifyDate' format='yyyy-MM-dd' />" /></td>
      <th>Modified By </th>
      <td><input name="catalogDTO.modifyUser" type="text" class="NFText" disabled="disabled" value="${modifyUser }" size="20" /></td>
    </tr>
    <tr>
      <th>Date Created </th>
      <td><input name="catalogDTO.creationDate" type="text" class="NFText" disabled="disabled" value="<s:date name='creationDate' format='yyyy-MM-dd' />" /></td>
      <th>Created By </th>
      <td>
      <input name="catalogDTO.createUser" type="text" class="NFText" disabled="disabled" value="${createUser}" size="20" />
      <input name="catalogDTO.id" type="hidden" value="${id }" />
      <input name="nameAppr" type="hidden" value="${nameAppr}" />
      <input name="statusAppr" type="hidden" value="${statusAppr}" />
      <!--
      <input name="nameApprove" type="hidden" />
      <input name="nameReason" type="hidden" /> 
      <input name="statusApprove" type="hidden" />
      <input name="statusReason" type="hidden" />
      -->
      <input type="hidden" name="categoryId" id="categoryId" value="" />
      <input type="hidden" name="ctgAstGrp" id="ctgAstGrp" value="" />
      <input type="hidden" name="ctgDescription" id="ctgDescription" value="" />
      <input type="hidden" name="servCategoryId" id="servCategoryId" value="" />
      <input type="hidden" name="servCtgAstGrp" id="servCtgAstGrp" value="" />
      <input type="hidden" name="servCtgDescription" id="servCtgDescription" value="" />
      <input type="hidden" name="pdtCtgDel" id="pdtCtgDel" value="" />
      <input type="hidden" name="servCtgDel" id="servCtgDel" value="" />
      <input type="hidden" name="pricePrecision" id="pricePrecision" value="${pricePrecision }" />
      <input type="hidden" name="sessionCatalogId" id="sessionCatalogId" value="${sessionCatalogId}" />
      </td>
    </tr>
  </table>
</form>
</div>
  <div id="dhtmlgoodies_tabView1">
        <div class="dhtmlgoodies_aTab">
    <s:if test="catalogId==null">
    	<iframe id="categoryProductList_iframe" name="categoryProductList_iframe" src="${ctx}/product/product_category.action?sessionCatalogId=${sessionCatalogId}&filter_EQI_categoryLevel=1&filter_EQS_catalogId=null&callBackName=catalogCategoryList" width="100%" height="280" frameborder="0" scrolling="no"></iframe>
    </s:if>
    <s:else>
    	<iframe id="categoryProductList_iframe" name="categoryProductList_iframe" src="${ctx}/product/product_category.action?sessionCatalogId=${sessionCatalogId}&filter_EQI_categoryLevel=1&filter_EQS_catalogId=${catalogId}&callBackName=catalogCategoryList" width="100%" height="280" frameborder="0" scrolling="no"></iframe>
    </s:else>
    </div>
	<div class="dhtmlgoodies_aTab">
	 <s:if test="catalogId==null">
    	<iframe id="categoryServiceList_iframe" name="categoryServiceList_iframe" src="${ctx}/serv/service_category.action?sessionCatalogId=${sessionCatalogId}&filter_EQI_categoryLevel=1&filter_EQS_catalogId=null&callBackName=catalogCategoryList" width="100%" height="280" frameborder="0" scrolling="no"></iframe>
    </s:if>
    <s:else>
    	<iframe id="categoryServiceList_iframe" name="categoryServiceList_iframe" src="${ctx}/serv/service_category.action?sessionCatalogId=${sessionCatalogId}&filter_EQI_categoryLevel=1&filter_EQS_catalogId=${catalogId}&callBackName=catalogCategoryList" width="100%" height="280" frameborder="0" scrolling="no"></iframe>
    </s:else>
	
	</div>
</div>
<div class="button_box">
    <s:if test="approvedMethod==\"approvedEdit\"">
    	<div align="center">
    	    <input type="hidden" name="requestIdApproved" id="requestIdApproved" value="${requestId}" />
    	    <input type="hidden" name="approvedMethod" id="approvedMethod" value="${approvedMethod}" />
			<input id="approveBtn" type="button" class="search_input" value="Approve" />
			<input id="rejectBtn" type="button" class="search_input" value="Reject" />
			<input id="host" type="button" class="search_input" value="Cancel" onclick="javascript:history.back(-1);"/>
		</div>
    </s:if>
    <s:else>
    	<saveButton:saveBtn parameter="${operation_method}"
			disabledBtn='<input type="button" id="saveAllTrigger"  value="Save" class="search_input" disabled="disabled" />'
			saveBtn='<input type="button" id="saveAllTrigger"  value="Save" class="search_input"/>'
		/> 
	      <input type="button" id="cancelAllTrigger" value="Cancel" class="search_input" />
    </s:else>
	
</div>
<div id="addCategoryDialog" title="Add New Business Unit"></div>
<div id="editCategoryPdtDialog" title="Edit Category"></div>
<div id="chooseCategoryDialog" title="Choose Business Unit"></div>
<div id="modifyCatNameDialog" title="Modify Catalog Name" style="display:none;">
	<table id="whole_table" width="492" border="0" cellpadding="0" cellspacing="0" class="General_table" style="margin-left:40px;">
			  <tr>
			   <th width="78" align="left">Catalog Name</th>
			   <td width="414"><input type="text" size='35' class="NFText" name="approved" id="approved" value="${catalogName }" /></td>
			  </tr>
			  <tr>
	            <th height="24" colspan="2"><div align="left"> Choose the reason to modify the catalog name: </div></th>
	          </tr>
	          <tr>
	            <th colspan="2"><div align="left"><textarea name="approvedReason" id="approvedReason" cols="70" rows="2" class="content_textarea"></textarea></div></th>
	          </tr>
	          <tr>
	            <th align="right" colspan="2">
				<div align="center" style="margin:10px;">
		            <div id="cat_name" style='display:block;'>
		            	<input type="hidden" name="approvedType" id="approvedType" value="CatalogApprovedName" />
		            	<input type="hidden" name="oldApproved" id="oldApproved" value="${catalogName }" />
						<input type="button"  class="style_botton" value="Modify" id="saveApprovedTrigger"/>	
					  	<input type="button" value="Cancel"  class="style_botton" onclick="$('#modifyCatNameDialog').dialog('close');" />
		            </div>
				 </div>
				 </th>
	          </tr>
	</table>
</div>
<div id="modifyCatStatusDialog" title="Modify Catalog Status" style="display:none;">
		<table border="0" cellpadding="0" cellspacing="0" class="General_table" style="margin-left:40px;">
          <tr>
            <th height="26"><div align="left">Status
                <select name="statusApprove" id="statusApprove">
                	<s:if test="status==null">
                	 	<option value="ACTIVE" selected="selected">ACTIVE</option>
                  	    <option value="INACTIVE">INACTIVE</option>
                	</s:if>
                 	<s:if test="status==\"ACTIVE\"">
                	 	<option value="ACTIVE" selected="selected">ACTIVE</option>
                  	    <option value="INACTIVE">INACTIVE</option>
                	</s:if>
                	<s:if test="status==\"INACTIVE\"">
                	 	<option value="ACTIVE">ACTIVE</option>
                  	    <option value="INACTIVE" selected="selected">INACTIVE</option>
                	</s:if>
                </select>
            </div></th>
          </tr>
          <tr>
            <th height="24" valign="top"><div align="left">Choose the reason to update the catalog status: </div></th>
          </tr>
          <tr>
            <th valign="top"><textarea name="statusReason" id="statusReason" cols="70" rows="2" class="content_textarea"></textarea></th>
          </tr>
          <tr>
            <th valign="top">
			<div align="center" style="margin:10px;">
              <div id="cat_name" style='display:block;'>
              <input type="hidden" name="approvedStatusType" id="approvedStatusType" value="CatalogApprovedStatus" />
			  <input type="hidden" name="oldStatusApproved" id="oldStatusApproved" value="${status }" />
			  <input type="button"  class="style_botton" value="Modify" id="saveApprovedStatusTrigger" />	
			  <input type="button" value="Cancel" class="style_botton" onclick="$('#modifyCatStatusDialog').dialog('close');" /> 
            </div>
            </div>
            </th>
          </tr>
        </table>
</div>
<script type="text/javascript">
	var defaultTab = '${defaultTab}';
	if(defaultTab == null||defaultTab==''){
		defaultTab = 0;
	}
	if("${type}"=="SERVICE"){
		defaultTab = 1;
	}
	initTabs('dhtmlgoodies_tabView1',Array('Product Segment','Service Segment'),defaultTab,998,280);
</script>
</div>
<div style="display:none;" id="rejectDialog" >
	<table id="whole_table" width="408" border="0" cellpadding="0" cellspacing="0" class="General_table" style="margin-left:50px;">
		  <tr>		   
			   <td width="364" height="18" style="font-size:12px">		
			   	Enter comments to reject the change request:
				</td>   	
			</tr>
			<tr>
			   <td height="63">
		      	<textarea id="rejectReason" name="rejectReason" class="content_textarea2"></textarea>
		      </td>
			</tr>
		  <tr>
			 <td>
			  <div align="center">
				<input id="rejectDialogConfirm" type="button" class="style_botton" value="Confirm" />     
				<input id="rejectDialogCancel" type="button" class="style_botton" value="Cancel" />
				</div>
			</td>
		</tr>
</table>
</div>
</body>
</html>


