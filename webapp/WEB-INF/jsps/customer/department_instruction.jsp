<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base id="myBaseId" href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Organization Instructions</title>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}util/json_util.js"></script>
  
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.form.js" language="javascript" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js" language="javascript" type="text/javascript"></script>

<script src="${global_js_url}scm/order_quotation_instruction_dialog.js" language="javascript" type="text/javascript"></script>

<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script language="javascript" type="text/javascript"> 
//js variables
var emptyDate = "${emptyDate}"; 
var globalUrl = "${global_url}";
var noteDocJsonMap = [];
</script>

<script language="javascript" type="text/javascript"> 
function add_file(){ 
	var uploaded_count = $('#uploaded_count').val();
	if (! uploaded_count){
		uploaded_count = 0;
	}
	var td = document.getElementById("uploaded_files");    
	var input = document.createElement("input");
	var button = document.createElement("input");
	var br = document.createElement("br");
	
	input.type = "file";
	input.name = "upload";
	input.size = "55";
	
	button.type = "button";
	button.value = "  Delete  ";
	
	button.onclick = function() {
		td.removeChild(br);
		td.removeChild(input);
		td.removeChild(button);
	}
	td.appendChild(input);
	td.appendChild(button); 
	td.appendChild(br);

	uploaded_count++;
	$('#uploaded_count').val(uploaded_count); 
}

function delete_file(index){
	var listDiv = document.getElementById("doc_list"); 
	var delDiv = document.getElementById(index+"_docDiv");
	listDiv.removeChild(delDiv); 
	var docDelIndexs = document.getElementById("docDelIndexs");
	if(docDelIndexs.value == ""){
		docDelIndexs.value = index;
	}else{
		docDelIndexs.value += ","+index;
	}
}

//update source in session
function update_note(op_type){
	if (! $('#description').val()){
		alert("Please enter the description."); 
		$("#description").focus(); 
		return;
	}
	if($("#uploaded_files").html() == "" || $("#file_div").find(":file").size() == 0 ){
		$("#docFlag").val("N");
	}else{
		$("#docFlag").val("Y");
	}
	
	if($("#id").val() == ""){
		$("#id").val("0");
	}
	var action = 'department!saveInstruction.action';
	var form = $("#instruction_form");
	//setHiddenFileHtml("docHidParams");
	var options = {
	    dataType:"text",
		success:function(data) {
			//<pre>&lt;pre&gt;44&lt;/pre&gt;</pre>
			data = data.replace(/<pre>/gi, "");
			data = data.replace(/<\/pre>/gi, "");
			data = data.replace(/&lt;pre&gt;/gi, "");
			data = data.replace(/&lt;\/pre&gt;/gi, "");

			var note_id = data;
			var slt = parent.document.getElementById('notes_sel'); 
			var pos = slt.selectedIndex; 
			var noteType = $('#instruction_type').val();
			
			if (pos == 0){
				//insert
		//	slt.options.add(new Option(noteType+"#"+slt.options.length, note_id, false, true)); 
				slt.options.add(new Option(noteType+" - instr - depart", note_id, false, true));
				parent.$("#note_btn").val("View/Edit");
			}else{
				//edit
				var optionText = $('#id').val();
				if(optionText == 0){
					optionText = pos;
				}
				slt.options[pos] = null; 
				slt.options.add(new Option(noteType+" - instr - depart", note_id, false, true), pos);
			}
			parent.$('#note_dlg').dialog('close'); 
		}, 
		error: function(){
			alert('error...'); 
		}, 
		resetForm:false, 
		url:action,
		type:"POST",
		async:false, 
	};
	form.ajaxForm(options);
	form.submit();
}

function getFileUrl(){
	//处理新增附件
	var tmpFileName= "";
	var tmpFilePath="";
	var noteDocObj = [];
	var hidHtml = "";
	$("#uploaded_files").find(":file").each(function(){
		tmpFileName = $(this).attr("name");
		tmpFilePath = $(this).val();
		noteDocObj = {"docId":"","refType":"","refId":"","docType":"","fileType":"","description":"","docName":""+tmpFileName, "filePath":""+tmpFilePath};
		JSON_DOC_MAP.updateOneRow(tmpFilePath, noteDocObj);
	});
	return JSON_DOC_MAP.conver2url("noteDocumentList");
}
</script>

<style type="text/css">
<!--
body{margin:0px auto; width:600px;}
.General_table{margin:0px;}
-->
</style>
</head>

<body id="oneNoteBody">
 
<form id="instruction_form"  method="post" enctype="multipart/form-data">
	<table width="600" border="0" cellpadding="0" cellspacing="0" class="General_table" style="margin-top:8px;">
	  <tr>
		<td width="95" valign="top" align="right">Type</td>
		<td width="180">
			<s:select name="noteDTO.type" id="instruction_type" cssStyle="width:170px;" value="noteDTO.type"
			list="#{'ACCOUNTING':'Accounting Notes', 'PRODUCTION':'Production Notes', 'SALES':'Sales Notes', 'SHIPMENT':'Shipment Notes'}">
			</s:select>
		</td>
		<td>
			&nbsp;
		</td>
	  </tr>
	 </table>
	
	 <div id="desc_div">
	 <table width="600"  border="0" cellpadding="0" cellspacing="0" class="General_table"> 
		<tr>
		<td valign="top" width="95" align="right"><span class="important">*</span>Description</td>
		<td>
			<textarea name="noteDTO.description" id="description" class="content_textarea2">${noteDTO.description}</textarea>
		</td>
		  </tr>
	</table>
	</div>

	<div id="file_div">
	 <table width="600"  border="0" cellpadding="0" cellspacing="0" class="General_table"> 
		<tr>
		<td valign="top" width="95">&nbsp;</td>
		<td>
			<div id="doc_list">
				<s:iterator value="noteDTO.documentList" id="item" status="st">
					<div id="${st.index}_docDiv">
						<a href="download.action?filePath=${item.filePath}&fileName=${item.docName}" target="self">${item.docName}</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" onclick="delete_file('${st.index}')" value="Delete" />
					</div>
				</s:iterator>
			</div>
			<div id="uploaded_files"></div>
			<input type="button" id="btn_add_file" value="  Add Attachments  " onclick="add_file()"/>			
		</td>
		</tr>
	</table>
	</div> 

	<div> 
	 <table width="600"  border="0" cellpadding="0" cellspacing="0" class="General_table"> 
		<tr>
		  <td colspan="2">
		  	<div align="center"> <br />
		  	<s:if test='sessNoteId != ""'>
		  		<input type="button" class="style_botton"  value="Update"  onclick="update_note()"/>	
		  	</s:if>
		  	<s:else>
		  		<input type="button" class="style_botton"  value="Add"  onclick="update_note()"/>
		  	</s:else>
			&nbsp;&nbsp;
			<input type="submit" value="Cancel"  class="style_botton" onclick="javascript:parent.$('#note_dlg').dialog('close');" />
			</div>
		 </td>
		  </tr>
	</table>
	</div>
	
	<input type="hidden" id="id" name="noteDTO.id" value="${noteDTO.id}" />
	<input type="hidden" id="docFlag" name="noteDTO.docFlag" value="${noteDTO.docFlag}" />
	<input type="hidden" name="uploaded_count"  id="uploaded_count" />
	<input type="hidden" name="docDelIndexs" id="docDelIndexs" />
	<input type="hidden" name="id" id="id" value="${param.id}" />
	<input type="hidden" name="sessNoteId" id="sessNoteId" value="${sessNoteId}" />
	<input type="hidden" name="sessCustNo" id="sessCustNo" value="${sessCustNo}" />
</form>
</body>
</html>