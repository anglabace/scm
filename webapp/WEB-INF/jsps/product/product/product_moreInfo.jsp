<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
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
<link type="text/css"
	href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.form.js"></script>
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript">
	var GB_ROOT_DIR = "./greybox/";
	var baseUrl = "${ctx}/";
</script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}show_tag.js"></script>
</head>
<script type="text/javascript" language="javascript">
	var referenceId = "0";
	function add_ref() {
		referenceId++;
		$("#referenceTable")
				.append(
						'<tr id="_'+referenceId+'"><th width="150" rowspan="2">Description</th><td width="330" rowspan="2" align="left"><textarea id="productReference_'+referenceId+'" class="content_textarea2" style="width:250PX;"></textarea></td><th width="130">Url</th><td width="290" align="left"><input id="productReferenceUrl_'+referenceId+'" type="text" class="NFText" size="30"/> <img src="images/file_delete.gif" id="delTrigger" alt="Delete" width="16" height="16" border="0"  onclick="delClick('
								+ "'_"
								+ referenceId
								+ "'"
								+ ','
								+ "'no'"
								+ ')"/></td></tr><tr id="tr_'+referenceId+'"><th>&nbsp;</th><td>&nbsp;</td></tr>')
	}

	function delClick(id, isSave) {
		$("#" + id).remove();
		$("#tr" + id).remove();
		if (isSave == 'yes') {
			$("#delReferenceId").val($("#delReferenceId").val() + "<;>" + id);
		}
	}

	function del_note(name) {
		var del_FileProduct_nos = get_checked_str(name);
		if (del_FileProduct_nos == '') {
			alert('Please select one item to continue your operation.');
			return;
		}
		if (!confirm('Are you sure to delete?')) {
			return;
		}
		$
				.ajax({
					type : "POST",
					url : "product/product!delFileProductSession.action?sessionProductId=${sessionProductId}&delFileProduct="
							+ del_FileProduct_nos,
					success : function(msg) {
						if (msg == "success") {
							var del_FileProduct = del_FileProduct_nos
									.split(",");
							for ( var i = 0; i < del_FileProduct.length; i++) {
								$("#del_" + del_FileProduct[i]).remove();
							}
						} else {
							alert("Failed to remove the item. Please contact system administrator for help.");
						}
					},
					error : function(msg) {
						alert("Failed to remove the item. Please contact system administrator for help.");
					}
				});
	}
	//update source in session
	function update_note(op_type) {
		if ($("#updataProductFiles").val() == "") {

			return;
		}
		var action = '${ctx}/product/product!saveProductFilesSession.action';
		var form = $("#productFilesForm");
		//ajax form post
		var options = {
			success : function(data) {
				var datas = data.split(",");
				if (datas[0] == "success") {
					var typeFile = $("#productFilesType").val();
					var name = "DOCproductDocUL";
					if (typeFile == "Image & Graph") {
						name = "FIGproductDocUL";
					}
					if (typeFile == "Reference") {
						name = "REproductDocUL";
					}
					var newFile = "<li id='del_"+datas[1]+"'><input type='checkbox' name='fileProductId' id='fileProductId' value='"+datas[1]+"'/>"
							+ datas[2]
							+ ":<span class='css_blue_b'>"
							+ datas[3] + "</span></li>";
					if (datas[4] == "Document") {
						$('#DOCproductDocUL').prepend(newFile);
					} else if (datas[4] == "Reference") {
						$('#REproductDocUL').prepend(newFile);
					} else {
						$('#FIGproductDocUL').prepend(newFile);
					}
					$("#updataProductFiles").val("");

				} else {
					alert("Failed to remove the item. Please contact system administrator for help.");
				}
			},
			error : function() {
				alert("Failed to remove the item. Please contact system administrator for help.");
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
			if (a[i].checked) {
				str += a[i].value + ',';
			}
		}
		return str.substring(0, str.length - 1);
	}
	function ff(url, fileName, oldFlag, wwwstr) {
		$("#fileName").val(fileName);
		$("#oldFlag").val(oldFlag);
		$("#wwwstr").val(wwwstr);

		$("#fileForm").attr('action', url);
		$("#fileForm").submit();
	}
</script>
<body>
	<div id="dhtmlgoodies_tabView1">
		<div class="dhtmlgoodies_aTab">
			<form id="extendedInfo">
				<table border="0" cellspacing="0" cellpadding="0"
					class="General_table" style="margin: 0px auto;">
					<tr>
						<th>&nbsp;</th>
						<td width="330">&nbsp;</td>
						<th>&nbsp;</th>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<th>Keyword</th>
						<td width="330"><input name="productExtendedInfo.keywords"
							type="text" class="NFText" size="30"
							value="${productExtendedInfo.keywords}" />
						</td>
						<th>&nbsp;</th>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<th>Key Features</th>
						<td><textarea name="productExtendedInfo.keyFeatures"
								class="content_textarea2" style="width: 250PX;">${productExtendedInfo.keyFeatures }</textarea>
						</td>
						<th>Meta Description</th>
						<td><textarea name="productExtendedInfo.metaDesc"
								class="content_textarea2" style="width: 250PX;">${productExtendedInfo.metaDesc }</textarea>
						</td>
					</tr>
					<tr>
						<th>Applications</th>
						<td><textarea name="productExtendedInfo.applications"
								class="content_textarea2" style="width: 250PX;">${productExtendedInfo.applications }</textarea>
						</td>
						<th>Application Sample</th>
						<td><textarea name="productExtendedInfo.applSample"
								class="content_textarea2" style="width: 250PX;">${productExtendedInfo.applSample }</textarea>
						</td>
					</tr>

					<tr>
						<th>URL of Product/Service</th>
						<td><span class="css_b"> <input
								name="productExtendedInfo.url" type="text" class="NFText2"
								value="${productExtendedInfo.url }" style="width: 250PX;"
								readonly="readonly" /> </span>
						</td>
						<th>&nbsp;</th>
						<td>&nbsp;</td>
					</tr>
					<input id="delReferenceId" name="delReferenceId" type="hidden"
						value="" />
				</table>
			</form>
			<div class="invoice_title"></div>
			<table width="900" border="0" cellpadding="0" cellspacing="0"
				class="General_table" style="margin: 0px auto;">
				<tr>
					<th width="151"><input type="button" id="adda2"
						class="style_botton2" value="Add Reference" onclick="add_ref()" />
					</th>
					<td width="749" colspan="3">&nbsp;</td>
				</tr>

			</table>
			<div id="addhtml">
				<table id="referenceTable" width="900" border="0" cellpadding="0"
					cellspacing="0" class="General_table" id="add_refe"
					style="margin: 0px; display: block">
					<s:iterator value="productReferenceList">

						<tr id="${id }">
							<th width="150" rowspan="2">Description</th>
							<td width="330" rowspan="2" align="left"><textarea
									id="productReference${id }" class="content_textarea2"
									style="width: 250PX;">${reference}</textarea></td>
							<th width="130">Url</th>
							<td width="290" align="left"><input
								id="productReferenceUrl${id }" type="text" class="NFText"
								size="30" value="${url}" /> <input type="hidden"
								id="productReferenceId${id }" value="${id}" /> <img
								src="images/file_delete.gif" id="delTrigger" alt="Delete"
								width="16" height="16" border="0"
								onclick="delClick('${id }','yes')" /></td>
						</tr>
						<tr id="tr${id }">
							<th>&nbsp;</th>
							<td>&nbsp;</td>
						</tr>
					</s:iterator>
				</table>
			</div>

		</div>
		<div class="dhtmlgoodies_aTab">

			<div class="invoice_title">
				<a href="javascript:void(0);"
					onclick="toggleShowMore('documentImage','Documents');"
					id="DocumentsItem"><img id="documentImage" src="images/ad.gif"
					width="11" height="11" /> </a>&nbsp;Document
			</div>
			<div id="Documents" style="display: block; margin-left: 20px;"
				class="interest">
				<ul id="DOCproductDocUL"> 

					<s:iterator value="productDoc"> 
						 
							<li id="del_${docId}"><s:property
									value="docType" />: <span class="css_blue_b"><a
									href="${ctx }/product/document!input.action?docId=${docId}"
									target="_parent">${docName}</a> </span> &nbsp;<a
								href="javaScript:void(0);" 
								onclick="ff('download.action?filePath=${docFilePath}','${docFileName}','${oldFlag}','doc')"
								target="_parent"><img src="images/down1.jpg" /> </a>
							</li>
				 
					</s:iterator>
				</ul>
			</div>
			<div class="invoice_title">
				<a href="javascript:void(0);"
					onclick="toggleShowMore('figuresImage','Figures');"
					id="FiguresItem"><img id="figuresImage" src="images/ar.gif"
					width="11" height="11" /> </a>&nbsp;Image &amp; Graph
			</div>
			<div id="Figures" style="display: none; margin-left: 20px;"
				class="interest">
				<ul id="FIGproductDocUL">
		 
				</ul>
			</div>
		</div>
	</div>
	<form action="" id="fileForm" method="post">
		<input name="fileName" id="fileName" type="hidden" /> <input
			name="oldFlag" id="oldFlag" type="hidden" /><input name="wwwstr"
			id="wwwstr" type="hidden" />
	</form>
</body>
</html>
<script type="text/javascript" language="javascript">
	initTabs('dhtmlgoodies_tabView1', Array('Extended Info', 'Product Files'),
			0, 970, 365)
</script>