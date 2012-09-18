<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Documents File</title>
<base href="${global_url}" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}tab-view.js"></script>
<link href="${global_css_url}tab-view.css" rel="stylesheet"
	type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}TabbedPanels.js"></script>
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
	type="text/css" />

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
<script src="${global_js_url}jquery/ui/ui.datepicker.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1"
	type="text/javascript"></script>
<script src="${global_js_url}util/util.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}newwindow.js"></script>

<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.form.js"></script>

<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
	type="text/css" />
<script>
	function update_note(op_type) {
		var name = $("#name").val();
		if (name == "") {
			alert("Please enter the Name.");
			$("#name").focus();
			return;
		}
		var delProductId = get_checked_str("ids");

		var action = '${ctx}/product/document!saveProductFilesSession.action?delProductId='
				+ delProductId;
		var form = $("#productFilesForm");
		//ajax form post
		var options = {
			success : function(data) {
				alert("Save success!");
				var datas = data.split(","); 
				if (datas[0] == "success") {
					location.href = "${ctx}/product/document!input.action?docId="
							+ datas[1];
				}
			},
			error : function() {
				alert('System error! Please contact system administrator for help.');
			},
			resetForm : false,
			url : action,
			type : "POST"
		};
		form.ajaxForm(options);
		form.submit();
	}

	function get_checked_str(name) {
		var a = document.getElementsByName(name);
		var str = '';
		for ( var i = 0; i < a.length; i++) {
			if (!a[i].checked) {
				str += a[i].value + ',';
			}
		}
		return str.substring(0, str.length - 1);
	}

	function addCatalogNoClick() {
		var docId = $("#docId").val();
		// alert(docId);
		$('#addProductDialog').dialog({
			autoOpen : false,
			height : 450,
			width : 720,
			modal : true,
			bgiframe : true,
			buttons : {}
		});
		var url = "product/product!searchProductNotInDocument.action?docId="
				+ docId;
		$('#addProductDialog')
				.dialog(
						"option",
						"open",
						function() {
							var htmlStr = '<iframe name="productDialog" id="productDialog" src="'+url+'" height="400" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
							$('#addProductDialog').html(htmlStr);
						});
		$('#addProductDialog').dialog('open');
	}

	function viewOldVersionClick() {
		var docId = $("#docId").val();
		$('#viewOldVersionDialog').dialog({
			autoOpen : false,
			height : 450,
			width : 720,
			modal : true,
			bgiframe : true,
			buttons : {}
		});
		var url = "product/product!searchProductVersionList.action?docId="
				+ docId;
		$('#viewOldVersionDialog')
				.dialog(
						"option",
						"open",
						function() {
							var htmlStr = '<iframe name="oldVersionDialog" id="oldVersionDialog" src="'+url+'" height="400" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
							$('#viewOldVersionDialog').html(htmlStr);
						});
		$('#viewOldVersionDialog').dialog('open');
	}

	function delFile(name, shisname) {
		$("#" + name).val('');
		$("#" + shisname).html('');
	}
	function ff(url, fileName, oldFlag, wwwstr) {
		$("#fileName").val(fileName);
		$("#oldFlag").val(oldFlag);
		$("#wwwstr").val(wwwstr);
		
		$("#fileForm").attr('action', url);
		$("#fileForm").submit();
	}
</script>
</head>
<body class="content" style="background-image: none;">
	<div id="frame12" style="display: none;" class="hidlayer1">
		<iframe id="hidkuan" name="hidkuan" src="" width="668" height="425"
			frameborder="0" allowtransparency="true"></iframe>
	</div>
	<div class="scm">
		<div class="title_content">
			<div class="title">
				<s:if test="documentDTO.documents.docId==null">
			New Document
		</s:if>
				<s:else>
			${documentDTO.documents.docName }
		</s:else>
			</div>
		</div>
		<div class="input_box">
			<form id="productFilesForm" encType="multipart/form-data">

				<table border="0" cellpadding="0" cellspacing="0"
					class="General_table">
					<tr>
						<th width="116">File Name</th>
						<td width="389"><input name="document.docName" id="name"
							type="text" class="NFText" size="20"
							value="${documentDTO.documents.docName}" /> <input
							name="document.docId" id="docId" type="hidden"
							value="${documentDTO.documents.docId}" /> <input
							name="document.creationDate" type="hidden"
							value="${documentDTO.documents.creationDate}" /> <input
							name="document.createdBy" type="hidden"
							value="${documentDTO.documents.createdBy}" /> <input
							name="document.modifyDate" type="hidden"
							value="${documentDTO.documents.modifyDate}" /> <input
							name="document.modifiedBy" type="hidden"
							value="${documentDTO.documents.modifiedBy}" /> <input
							name="document.docFilePath" id="docFilePath" type="hidden"
							value="${documentDTO.documents.docFilePath}" /> <input
							name="document.imageFilePath" id="imageFilePath" type="hidden"
							value="${documentDTO.documents.imageFilePath}" /> <input
							name="document.docFileName" id="docFileName" type="hidden"
							value="${documentDTO.documents.docFileName}" /> <input
							name="document.docFileType" type="hidden"
							value="${documentDTO.documents.docFileType}" /> <input
							name="document.imageFileType" type="hidden"
							value="${documentDTO.documents.imageFileType}" /> <input
							name="document.imageFileName" id="imageFileName" type="hidden"
							value="${documentDTO.documents.imageFileName}" /> <input
							type="hidden" name="productId" id="productIdList" value="" />
						</td>
						<th width="86">File Type</th>
						<td width="335"><s:select name="document.docType" id="type"
								list="pbOptionItemList" listValue="text" listKey="value"
								value="documentDTO.documents.docType" />
						</td>
					</tr>
					<tr>
						<th width="116">Version</th>
						<td width="389"><input name="document.version" type="text"
							class="NFText" size="20" value="${documentDTO.documents.version}"
							readonly="readonly" /> <input type="button"
							class="style_botton4" value="View Old Versions"
							onclick="viewOldVersionClick()" /></td>
						<th width="86">&nbsp;</th>
						<td width="335"><s:if
								test="documentDTO.documents.internalFlag==\"Y\"">
								<input type="checkbox" name="document.internalFlag"
									id="internalFlag" checked="checked" value="Y" />
							</s:if> <s:else>
								<input type="checkbox" name="document.internalFlag"
									id="internalFlag" value="Y" />
							</s:else> Internal Use Only</td>
					</tr>
					<tr>
						<th valign="top">Description</th>
						<td><textarea name="document.description"
								class="content_textarea2" style="width: 268px;">${documentDTO.documents.description}</textarea>
						</td>
						<th>Note</th>
						<td valign="top"><textarea name="document.note"
								class="content_textarea2" style="width: 268px;">${documentDTO.documents.note}</textarea>
						</td>
					</tr>
					<tr>
						<th>Large File</th>
						<td><input type="file" name="upload" id="updataProductFiles"
							size="30" class="type-file-file" /></td>
						<th>Small File(300 DPI)</th>
						<td colspan="2"><input type="file" name="imageFile"
							id="updataImageFile" size="30" class="type-file-file" />
						</td>
					</tr>
					<tr>
						<th></th>

						<td>
							<div id="docFilePathDiv">
								<s:if test="documentDTO.documents.docFilePath!=null">
									<a href="javaScript:void(0);"
										onclick="ff('download.action?filePath=${documentDTO.documents.docFilePath}','${documentDTO.documents.docFileName}','${documentDTO.documents.oldFlag}','doc')"
										target="_self">${documentDTO.documents.docFileName}</a>
        	&nbsp;&nbsp;&nbsp;
         <input type="button" class="style_botton" value="Delete"
										onclick="delFile('docFilePath','docFilePathDiv')" />
								</s:if>
							</div>
						</td>
						<th></th>
						<td colspan="2">
							<div id="imageFilePathDiv">
								<s:if test="documentDTO.documents.imageFilePath!=null">
									<a href="javaScript:void(0);"
										onclick="ff('download.action?filePath=${documentDTO.documents.imageFilePath}','${documentDTO.documents.imageFileName}','${documentDTO.documents.oldFlag}','img')"
										target="_self">${documentDTO.documents.imageFileName}</a>
        	 		&nbsp;&nbsp;&nbsp;	
        	 <input type="button" class="style_botton" value="Delete"
										onclick="delFile('imageFilePath','imageFilePathDiv')" />
								</s:if>
							</div>
						</td>
					</tr>
					<tr>
						<th>Modified Date</th>
						<td><input name="textfield4" type="text" class="NFText"
							value="<s:date name="documentDTO.documents.modifyDate" format="yyyy-MM-dd"/>"
							size="20" readonly="readonly" /></td>
						<th>Modified By</th>
						<td colspan="2"><input name="textfield4" type="text"
							class="NFText" value="${documentDTO.modifiedByName }" size="20"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td colspan="5"
							style="border-bottom: 2px solid #C2D5FC; color: #5579A6;">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="5">&nbsp;</td>
					</tr>


					<tr>
						<td height="22" colspan="5" style="padding: 0px;"><table
								border="0" cellspacing="0" cellpadding="0">
								<tr>
									<th>Associated Products or Services Catalog No:</th>
									<td><input type="button" class="style_botton" value="Add"
										onclick="addCatalogNoClick()" /></td>
								</tr>
							</table></td>
					</tr>
					<tr>
						<th height="22">&nbsp;</th>
						<td colspan="4"><table width="503" border="0" cellpadding="0"
								cellspacing="0" id="documentProductTable">
								<s:iterator value="documentDTO.productId" status="status">

									<s:if test="#status.index % 6 == 0">
										<tr>
									</s:if>
									<td><input type="checkbox" name="ids" id="delProductId"
										value="${productId}" checked="checked" /> ${catalogNo }</td>
									<s:if test="#status.index % 6 == 5 || #status.last">
										</tr>
									</s:if>

								</s:iterator>
							</table></td>
					</tr>
					<tr>
						<th height="22">&nbsp;</th>
						<td colspan="4">&nbsp;</td>
					</tr>

				</table>
			</form>
		</div>
		<form action="" id="fileForm" method="post">
			<input name="fileName" id="fileName" type="hidden" /><input
				name="wwwstr" id="wwwstr" type="hidden" /> <input name="oldFlag"
				id="oldFlag" type="hidden" />
		</form>
		<div class="button_box">
			<input type="button" name="saveProductFiles" value="Save"
				class="search_input" onclick="update_note()" /> <input
				type="submit" name="cancelButton" value="Cancel"
				class="search_input" onclick="window.location.reload();" /> <a
				href="Javascript:window.history.go(-1);"><input type="button"
				name="BackButton" value="Back" class="search_input" /> </a>


		</div>

	</div>
	<div id="addProductDialog" title="Item Lookup"></div>
	<div id="viewOldVersionDialog" title="View Old Versions"></div>
</body>
</html>
<script type="text/javascript">
	fileinput("updataProductFiles", "upload")
	fileinput("updataImageFile", "imageFile")
</script>
