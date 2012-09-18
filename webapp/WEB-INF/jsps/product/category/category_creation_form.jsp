<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}greybox/gb_styles.css" rel="stylesheet"
	type="text/css" media="all" />
<link href="${global_css_url}tab-view.css" rel="stylesheet"
	type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
	type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}TabbedPanels.js"></script>

<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>

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
<script src="${global_js_url}jquery/jquery.validate.js?v=1"
	type="text/javascript"></script>
<script src="${global_js_url}util/util.js" type="text/javascript"></script>
<script src="${global_js_url}/recordTime.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}scm/product/manager_task.js?v=2"></script>
<script language="JavaScript" type="text/javascript">
var GB_ROOT_DIR = "${global_js_url}greybox/";

var isSaved = false;
window.onbeforeunload = function() {
	if('${approvedMethod}'!="approvedEdit"){
		if(isSaved === false){
			return 'Do you want to leave without saving data?';
		}
	}
}

function changeMarket(){
	var marketingGroup = $("#marketingGroup").val();
	$.ajax({
		type: "POST",
		url: "product/product_category!searchMarketingStaff.action?marketGroup="+marketingGroup,
		dataType:"json",
		success: function(msg){
			if(msg.message == 'success'){
				for(var j=0;j<2;j++){
					var id="marketingSpvs";
					if(j==1){
						id = "marketingSplst";
					}
					var html = "<select id='"+id+"' name='productCategoryDTO."+id+"'>"
					for(var i=0;i<msg.marketingStaff.length;i++){
							html+="<option value='"+msg.marketingStaff[i].marketingStaff.staffId+"'>"+msg.marketingStaff[i].userName+"</option>";
					}
					html+="</select>";
					$("#"+id+"Div").empty();
					$("#"+id+"Div").append(html);
				}
			}else if(msg){
				alert('Fail to remove the customer. Please contact system administrator for help.');
			}else {
				alert('System error! Please contact system administrator for help.');
			}
		},
		error: function(msg){
			alert("Failed to remove the customer. Please contact system administrator for help.");
		}
	});
}
</script>

<script type="text/javascript"
	src="${global_js_url}scm/productCategory.js"></script>

<script type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
<script type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
<script type="text/javascript"
	src="${global_js_url}greybox/gb_scripts.js"></script>

</head>

<body class="content" style="background-image: none;"
	onload="recordTime()">

	<div class="scm">
		<div class="title_content">
			<div class="title">
				<s:if test="categoryNo==null">
					<s:if test="categoryLevel==1">
			New Business Unit No
		</s:if>
					<s:if test="categoryLevel==2">
			New Product Category No
		</s:if>
					<s:if test="categoryLevel==3">
			New Product Line No
		</s:if>
				</s:if>
				<s:else>
					<s:if test="categoryLevel==1">
			Business Unit - # ${name}
		</s:if>
					<s:if test="categoryLevel==2">
			Category - # ${name}
		</s:if>
					<s:if test="categoryLevel==3">
			Porduct Line - # ${name}
		</s:if>

				</s:else>
			</div>
		</div>
		<div class="input_box">
			<input type="hidden" id="categoryType" value="product" />
			<form id="categoryTopForm">
				<table border="0" cellpadding="0" cellspacing="0"
					class="General_table">
					<tr>
						<th width="160"><span class="important">*</span> <s:if
								test="categoryLevel==1">
				Business Unit No
			</s:if> <s:if test="categoryLevel==2">
				Product Category No 
			</s:if> <s:if test="categoryLevel==3">
				Product Line No
			</s:if></th>
						<td><input name="productCategoryDTO.categoryNo" type="text"
							class="NFText" value="${categoryNo}" size="25" maxlength="20" />
						</td>
						<th width="150"><span class="important">*</span> Name</th>
						<td><s:if test="name==null">
								<input name="productCategoryDTO.name" id="ctgName" type="text"
									class="NFText" value="${name}" size="25" maxlength="50" />
							</s:if> <s:else>
								<s:if
									test="approvedName!=\"\"&&approvedName!=null&&approvedMethod==\"approvedEdit\"">
									<input name="productCategoryDTO.name" id="ctgName" type="text"
										class="NFText" value="${approvedName}" readonly="readonly"
										size="25" />
								</s:if>
								<s:else>
									<input name="productCategoryDTO.name" id="ctgName" type="text"
										class="NFText" value="${name}" readonly="readonly" size="25" />
								</s:else>
							</s:else> <s:if test="categoryNo!=null&&approvedMethod!=\"approvedEdit\"">
      	&nbsp;<input type="button" value="Modify" class="style_button"
									onclick="modifyCategoryNameClick()" />
							</s:if></td>
					</tr>
					<tr>
						<th>Asset Group</th>
						<td><s:if test="categoryNo==null">
								<s:select name="productCategoryDTO.assetGroup" id="assetGroup"
									list="categoryDropdownList" listKey="value" listValue="text"
									value="\"REGULAR\"" />
							</s:if> <s:else>
								<s:select name="productCategoryDTO.assetGroup" id="assetGroup"
									list="categoryDropdownList" listKey="value" listValue="text"
									value="assetGroup" />
							</s:else></td>
						<th valign="top"><span class="important">*</span> Status</th>
						<td><s:if test="status==null">
								<input name="productCategoryDTO.status" type="text"
									class="NFText" value="INACTIVE" readonly="readonly" size="25" />
							</s:if> <s:else>
								<s:if
									test="approvedStatus!=\"\"&&approvedStatus!=null&&approvedMethod==\"approvedEdit\"">
									<input name="productCategoryDTO.status" type="text"
										class="NFText" value="${approvedStatus}" readonly="readonly"
										size="25" />
								</s:if>
								<s:else>
									<input name="productCategoryDTO.status" type="text"
										class="NFText" value="${status}" readonly="readonly" size="25" />
								</s:else>
							</s:else> <s:if test="categoryNo!=null&&approvedMethod!=\"approvedEdit\"">&nbsp;<input
									type="button" value="Modify" class="style_button"
									onclick="modifyCategoryStatusClick()" />
							</s:if></td>

					</tr>
					<tr>
						<th>Business Division</th>
						<td><s:select name="productCategoryDTO.businessDivision"
								list="businessDivisionDropdownList" listKey="value"
								listValue="text" value="businessDivision" /></td>
						<th valign="top">Marketing Group</th>
						<td><s:select name="productCategoryDTO.marketingGroup"
								id="marketingGroup" list="marketingGroupList" listKey="groupId"
								listValue="groupName" value="marketingGroup"
								onchange="changeMarket()" /></td>
					</tr>
					<tr>
						<th>Supervisor</th>
						<td>
							<div id="marketingSpvsDiv">
								<s:select name="productCategoryDTO.marketingSpvs"
									id="marketingSpvs" list="marketingStaffDTOList"
									listKey="marketingStaff.staffId" listValue="userName"
									value="marketingSpvs" />
							</div></td>
						<th>Marketing Specialist</th>
						<td>
							<div id="marketingSplstDiv">
								<s:select name="productCategoryDTO.marketingSplst"
									id="marketingSplst" list="marketingStaffDTOList"
									listKey="marketingStaff.staffId" listValue="userName"
									value="marketingSplst" />
							</div></td>
					</tr>

					<tr>
						<th valign="top">Description</th>
						<td><textarea name="productCategoryDTO.description"
								class="content_textarea2">${description}</textarea></td>
						<th valign="top">&nbsp;</th>
						<td valign="top"><input type="hidden"
							name="productCategoryDTO.catalogId" id="cataId" class="NFText"
							value="${catalogId}" readonly="readonly" size="25" />
						</td>
					</tr>

					<input name="productCategoryDTO.parentCatNo" id="ctgParentCtgNo"
						type="hidden" class="NFText" value="${parentCatNo}" size="25"
						readonly="readonly" />
					<input type="hidden" name="productCategoryDTO.parentCatId"
						value="${parentCatId}" />

					<input name="productCategoryDTO.previousCatName" type="hidden"
						class="NFText" value="${previousCatName}" size="25"
						readonly="readonly" />
					<input type="hidden" name="productCategoryDTO.previousCatId"
						value="${previousCatId}" />

					<tr>
						<th>Date Modified</th>
						<td><input name="ctgMdate" type="text" class="NFText"
							value="<s:date name='modifyDate' format='yyyy-MM-dd' />"
							size="25" readonly="readonly" />
						</td>
						<th>Modified By</th>
						<td><input name="ctgMby" type="text" class="NFText"
							value="${modifyUser}" size="25" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<th>Date Created</th>
						<td><input name="productCategoryDTO.creationDate" type="text"
							class="NFText"
							value="<s:date name='creationDate' format='yyyy-MM-dd' />"
							size="25" readonly="readonly" />
						</td>
						<th>Created By</th>
						<td><input name="productCategoryDTO.createUser" type="text"
							class="NFText" value="${createUser}" size="25"
							readonly="readonly" /> <input
							name="productCategoryDTO.categoryLevel" type="hidden"
							class="NFText" value="${categoryLevel}" /> <input type="hidden"
							name="productCategoryDTO.categoryId" id="ctgId"
							value="${categoryId}" /> <input name="nameAppr" type="hidden"
							value="${nameAppr}" /> <input name="statusAppr" type="hidden"
							value="${statusAppr}" /> <input name="nameApprove" type="hidden" />
							<input name="nameReason" type="hidden" /> <input
							name="statusApprove" type="hidden" /> <input name="statusReason"
							type="hidden" /> <input type="hidden" name="categoryIds"
							id="categoryIds" value="" /> <input type="hidden"
							name="ctgAstGrp" id="ctgAstGrp" value="" /> <input type="hidden"
							name="ctgDescription" id="ctgDescription" value="" /> <input
							type="hidden" name="productIds" id="productIds" value="" /> <input
							type="hidden" name="standrdPrices" id="standrdPrices" value="" />
							<input type="hidden" name="limitPrices" id="limitPrices" value="" />
							<div id="delSubCategoryList" title="del sub category list"></div>
							<div id="delPdtPriceList" title="del product list"></div> <input
							type="hidden" name="sessionCategoryId" id="sessionCategoryId"
							value="${sessionCategoryId}" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="dhtmlgoodies_tabView1">

			<s:if test="categoryLevel==1">
				<div class="dhtmlgoodies_aTab">
					<iframe name="subCategory_iframe" id="subCategory_iframe"
						src="${ctx}/product/product_category!subCategoryList.action?categoryLevel=2&sessionCategoryId=${sessionCategoryId}&parentCatId=${categoryId}&categoryId=${categoryId}&parentCategoryNo=${categoryNo}"
						width="100%" height="280" frameborder="0" scrolling="no"></iframe>
				</div>
			</s:if>
			<s:if test="categoryLevel==2">
				<div class="dhtmlgoodies_aTab">
					<iframe name="subCategory_iframe" id="subCategory_iframe"
						src="${ctx}/product/product_category!subCategoryList.action?categoryLevel=3&sessionCategoryId=${sessionCategoryId}&parentCatId=${categoryId}&categoryId=${categoryId}&parentCategoryNo=${categoryNo}"
						width="100%" height="280" frameborder="0" scrolling="no"></iframe>
				</div>
			</s:if>
			<s:if test="categoryLevel==3">
				<div class="dhtmlgoodies_aTab">
					<s:if test="catalogId!=null">
						<iframe name="catPdtServList_iframe" id="catPdtServList_iframe"
							src="${ctx }/product/product!productOfPdtCategoryList.action?sessionCategoryId=${sessionCategoryId}&categoryId=${categoryId }&catalogId=${catalogId}"
							width="100%" height="320" frameborder="0" scrolling="no"></iframe>
					</s:if>
					<s:else>
						<div style="margin-right: 17px;">
							<table width="967" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td><div style="margin-right: 17px;">
											<table width="950" border="0" cellspacing="0" cellpadding="0"
												class="list_table">
												<tr>
													<th width="46"><div align="left">
															<input name="chk1" type="checkbox" onclick="cc(this)" />
															<a href="javascript:void(0)" onclick="del()"><img
																src="images/file_delete.gif" alt="Delete" width="16"
																height="16" border="0" />
															</a>
														</div>
													</th>
													<th width="125">Product Catelog No</th>
													<th width="106">Name</th>
													<th width="118">Description</th>
													<th width="81">Status</th>
													<th width="110">Asset Group</th>
													<th width="123">Business Division</th>
													<th width="109">Modified Date</th>
													<th>Date Created</th>
												</tr>
											</table>
										</div>
									</td>
								</tr>
								<tr>
									<td><div style="height: 180px;">
											<table width="950" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="45">&nbsp;</td>
													<td width="124">&nbsp;</td>
													<td width="124">&nbsp;</td>
													<td colspan="2">&nbsp;</td>
													<td width="9">&nbsp;</td>
													<td width="122">&nbsp;</td>
													<td width="108"><div align="center"></div>
													</td>
													<td width="64"><div align="center"></div>
													</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td width="139">&nbsp;</td>
													<td width="215">&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td colspan="3">&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td colspan="2" class="zt">This product line does not
														belong to any catalog.</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
											</table>

										</div>
									</td>
								</tr>
							</table>
						</div>
					</s:else>
				</div>
			</s:if>


		</div>
	</div>
	<div class="button_box">
		<s:if test="approvedMethod==\"approvedEdit\"">
			<div align="center">
				<input type="hidden" name="requestIdApproved" id="requestIdApproved"
					value="${requestId}" /> <input type="hidden" name="approvedMethod"
					id="approvedMethod" value="${approvedMethod}" /> <input
					id="approveBtn" type="button" class="search_input" value="Approve" />
				<input id="rejectBtn" type="button" class="search_input"
					value="Reject" /> <input id="host" type="button"
					class="search_input" value="Cancel"
					onclick="javascript:history.back(-1);" />
			</div>
		</s:if>
		<s:else>
			<saveButton:saveBtn parameter="${operation_method}"
				disabledBtn='<input type="submit" name="save" id="saveAllTrigger" value="Save" class="search_input" disabled="disabled"/>'
				saveBtn='<input type="submit" name="save" id="saveAllTrigger" value="Save" class="search_input" />' />
			<input type="button" name="cancel" id="cancelAllTrigger"
				value="Cancel" class="search_input" />
		</s:else>
	</div>
	<s:if test="categoryLevel==1">
		<div id="addPrCategoryDialog" title="Add Product Category"></div>
	</s:if>
	<s:else>
		<div id="addPrCategoryDialog" title="Add Product Line"></div>
	</s:else>

	<div id="modifyCatNameDialog" title="Modify Category Name"
		style="display: none;">
		<table id="whole_table" width="492" border="0" cellpadding="0"
			cellspacing="0" class="General_table" style="margin-left: 40px;">
			<tr>
				<th width="100" align="left">Category Name</th>
				<td width="414"><input type="text" size='35' class="NFText"
					name="approved" id="approved" value="${name }" />
				</td>
			</tr>
			<tr>
				<th height="24" colspan="2"><div align="left">Choose the
						reason to modify the category name:</div>
				</th>
			</tr>
			<tr>
				<th colspan="2"><div align="left">
						<textarea name="approvedReason" id="approvedReason" cols="70"
							rows="2" class="content_textarea"></textarea>
					</div>
				</th>
			</tr>
			<tr>
				<th align="right" colspan="2">
					<div align="center" style="margin: 10px;">
						<div id="cat_name" style='display: block;'>
							<input type="hidden" name="approvedType" id="approvedType"
								value="ProductCategoryApprovedName" /> <input type="hidden"
								name="oldApproved" id="oldApproved" value="${name }" /> <input
								type="button" class="style_botton" value="Modify"
								id="saveApprovedTrigger" /> <input type="button" value="Cancel"
								class="style_botton"
								onclick="$('#modifyCatNameDialog').dialog('close');" />
						</div>
					</div></th>
			</tr>
		</table>
	</div>
	<div id="modifyCatStatusDialog" title="Modify Category Status"
		style="display: none;">
		<table border="0" cellpadding="0" cellspacing="0"
			class="General_table" style="margin-left: 40px;">
			<tr>
				<th height="26"><div align="left">
						Status <select name="statusApprove" id="statusApprove">
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
					</div>
				</th>
			</tr>
			<tr>
				<th height="24" valign="top"><div align="left">Choose the
						reason to update the category status:</div>
				</th>
			</tr>
			<tr>
				<th valign="top"><textarea name="statusReason"
						id="statusReason" cols="70" rows="2" class="content_textarea"></textarea>
				</th>
			</tr>
			<tr>
				<th valign="top">
					<div align="center" style="margin: 10px;">
						<div id="cat_name" style='display: block;'>
							<input type="hidden" name="approvedStatusType"
								id="approvedStatusType" value="ProductCategoryApprovedStatus" />
							<input type="hidden" name="oldStatusApproved"
								id="oldStatusApproved" value="${status }" /> <input
								type="button" class="style_botton" value="Modify"
								id="saveApprovedStatusTrigger" /> <input type="button"
								value="Cancel" class="style_botton"
								onclick="$('#modifyCatStatusDialog').dialog('close');" />

						</div>
					</div></th>
			</tr>
		</table>
	</div>
	<div id="chooseSubCategoryDialog" title="Please Choose Category"></div>
	<div id="catPdtServAddDialog" title="Add product"></div>
</body>
<s:if test="categoryLevel==1">
	<script type="text/javascript">
				
					initTabs('dhtmlgoodies_tabView1',Array('Product Categories'),0,998,280);
				

				</script>
</s:if>
<s:if test="categoryLevel==2">
	<script type="text/javascript">
				
					initTabs('dhtmlgoodies_tabView1',Array('Product Lines'),0,998,280);
				

				</script>
</s:if>
<s:if test="categoryLevel==3">
	<script type="text/javascript">
				
					initTabs('dhtmlgoodies_tabView1',Array('Products'),0,998,320);
				

				</script>
</s:if>

<div style="display: none;" id="rejectDialog">
	<table id="whole_table" width="408" border="0" cellpadding="0"
		cellspacing="0" class="General_table" style="margin-left: 50px;">
		<tr>
			<td width="364" height="18" style="font-size: 12px">Enter
				comments to reject the change request:</td>
		</tr>
		<tr>
			<td height="63"><textarea id="rejectReason" name="rejectReason"
					class="content_textarea2"></textarea></td>
		</tr>
		<tr>
			<td>
				<div align="center">
					<input id="rejectDialogConfirm" type="button" class="style_botton"
						value="Confirm" /> <input id="rejectDialogCancel" type="button"
						class="style_botton" value="Cancel" />
				</div></td>
		</tr>
	</table>
</div>
</html>