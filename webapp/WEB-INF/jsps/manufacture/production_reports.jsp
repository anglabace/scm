<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}openwin.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_css_url}ui.all.css" rel="stylesheet" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/jquery.form.js"
			type="text/javascript"></script>
<script language="javascript" type="text/javascript"
	        src="${global_js_url}newwindow.js"></script>
<script type="text/javascript">
$(function() { 
	$("#upload_btn").click( function(){
		if(! $("#upload_file").val()){
			alert("Please select one file !");
			return;
		}
		$('#upload_btn').attr("disabled", true);
		var dsPlateItemsIds = "";
		$("#dsPlateItems_iframe").contents().find('[name="plateItemId"]').each(function(){
			if(this.checked) {
				if(dsPlateItemsIds=="") {
					dsPlateItemsIds = dsPlateItemsIds + this.value;
				} else {
					dsPlateItemsIds = dsPlateItemsIds+","+ this.value;
				}
			}
			
		});
		if(dsPlateItemsIds=="") {
			alert("Please select one item at least.");
			$('#upload_btn').attr("disabled", false);
			return;
		}
		var upURL = "ds_plate!uploadFile.action?id="+ $("#id").val() +"&dsPlateItemsIds="+dsPlateItemsIds+ "&docType=" + $('#pdt_docType_sel').val(); 
		//ajax form post
		var options = {
			success:function(data){
				var documentList = data;
				var tmpStr = getFileListHtml(documentList);
				$("#save_form").find("#fileListTable").append(tmpStr);
				var file = $("#upload_file");
				file.after(file.clone().val(""));
				file.remove();
		        $('#upload_btn').attr("disabled", false);
		        $("#dsPlateItems_iframe").attr("src", $("#dsPlateItems_iframe").attr("src"));
			},
			error: function(data){
				if(data.responseText){
					alert(data.responseText);
				}else{
					alert("Error");
				}
				$('#upload_btn').attr("disabled", false);
			},
			dataType:"json",
			resetForm:false,
			url: upURL,
			type: "post"
		};
		$("#save_form").ajaxForm(options);
		$("#save_form").submit();
	});
	$("#save_btn").click(function(){
		$.ajax({
			url:"ds_plate!saveUploadData.action",
			data:"id="+$("#id").val(),
			dataType:"json",
			success:function(data) {
				alert(data.message);
				window.location.href="ds_plate!plateEdit.action?id="+$("#id").val();
			},
			error:function(data) {
				alert("failure");
			}
		});
	});
	
	fileinput("upload_file","textfield");
});


function toggleShowMore_img(obj,divID){
		var oId = document.getElementById(divID);
		if (obj.src.indexOf('arrow1.jpg') > 0){
			obj.src="${global_url}images/arrow.jpg";
			oId.style.display = "none"; 
		}else{
			obj.src="${global_url}images/arrow1.jpg";
			oId.style.display = "block"; 
		}
}

function downLoadFile(filePath,fileName) {
	$("#fileName").val(fileName);
	$("#filePath").val(filePath);
	var url = "download.action";
	$("#fileDownLoad").attr('action',url);
    $("#fileDownLoad").submit();
}

function deleteFile() {
	var docIds = "";
	var docTypes = "";
	var indexArray = new Array();
	$("#fileListTable input[type='checkbox']").each(function(){
		if(this.checked) {
			if(docIds=="") {
				docIds = docIds +this.value;
				docTypes = docTypes+this.name;
			} else {
				docIds = docIds +","+this.value;
				docTypes = docTypes+","+this.name;
			}
			var trObj = $(this).parent().parent();
			indexArray.push(trObj.get(0).rowIndex);
		}
	});
   if(docIds=="") {
	   alert("Please select one file at least.");
	   return;
   }
   if (confirm("Are you confirm delete these files ?")) {   
        $.ajax({
			type: "POST",
			url: "ds_plate!deleteFile.action",
			data: "id=" + $("#id").val()+"&plateItemId="+$("#dsPlateItemId").val()+ "&docIds=" + docIds+"&docTypes="+docTypes,
			success: function(data, textStatus) {
				alert("Success");
				for(var i =0;i<indexArray.length;i++) {
					$("#save_form").find("#fileListTable").get(0).deleteRow(indexArray[0]); 
				}
				 $("#dsPlateItems_iframe").attr("src", $("#dsPlateItems_iframe").attr("src"));
			},
			error: function(xhr, textStatus){
			   alert("failure");
			}
	   });   

   }
	
}

//获取附件列表html字符串
function getFileListHtml(documentList){
	  var tmpStr = "";
 	  $.each(documentList, function(key, value) {
		    tmpStr += '<tr>';
		    tmpStr += '<td>';
			tmpStr += '<input type="checkbox" value="'+key+'" name="'+value["docType"]+'"/>';
			tmpStr += '</td>';
			tmpStr += '<td>';
			tmpStr += '<a  href="javascript:void(0);" onclick="downLoadFile(\''+value["filePath"]+'\',\''+value["docName"]+'\')">'+value.docName+'</a>';
			tmpStr += '</td>';
			tmpStr += '<td>Type<input name="textfield32255" value="'+value.docType+'" type="text" class="NFText" size="20" readonly="readonly"/>';
			tmpStr += '</td>';
			var now = new Date().getFullYear()+"-"; 
			now = now + (new Date().getMonth()+1)+"-";
			now = now + new Date().getDate()+" ";
			tmpStr += '<td>Uploaded Date<input name="textfield3224" type="text" class="NFText" size="20" readonly="readonly" value="'+now+'" />';
			tmpStr += '</td>';
			tmpStr += '</tr>';   
      });
	  return tmpStr;
}
</script>
</head>
<body class="content">
	<div class="scm">
		<div class="title_content">
  		<div style="padding-left: 20px;color: #5579A6;vertical-align:middle;"><img src="${global_url}images/arrow1.jpg" style="width:16px;height:17px;vertical-align:middle;" onclick="toggleShowMore_img(this,'search_box1');"/>&nbsp;&nbsp;View or Edit Production Reports</div>
	    </div>
	    <div class="input_box">
	    	<div class="content_box">
				<iframe id="dsPlateItems_iframe" name="result_iframe"
					src="ds_plate!dataAnalyisList.action?id=${id}" width="100%"
					height="300" frameborder="0" scrolling="no"></iframe>
			</div>
	    </div>
	    <form id="save_form" method="post" class="niceform"
						enctype="multipart/form-data">
	    <table border="0" cellspacing="0" cellpadding="0"  class="General_table" style="margin:0px 20px;">
        <tr>
		    <th>Type</th>
		    <td colspan="2">
		    	<select name="manuDoc" id="pdt_docType_sel" style="width:160px">
		    		<option value="Sequencing File">Sequencing File</option>
		    		<option value="Trace File">Trace File</option>
		    		<option value="QV Data">QV Data</option>
		    		<option value="Alignment File">Alignment File</option>
		    	</select>
		    </td>
		    <th>&nbsp;</th>
		    </tr>
		 	
		  <tr>
		    <th>File</th>
		    <td colspan="2">
      		<input name="upload" type="file" id="upload_file" class="type-file-file"/>
      								
			<input name="tmplUploadBtn" type="button" class="style_botton" value="Upload" id="upload_btn" />
			<input type="button" name="Submit4" class="style_botton" value="Delete" onclick="deleteFile()"/></td>
		    <td>&nbsp;</td>
		    </tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td colspan="3">
					<table id="fileListTable">
					</table>
				</td>
			</tr>
      </table>
      </form>
	    <div class="button_box">
			<input type="button" name="Submit52" id="save_btn"
				value="Save" class="search_input"/>
			<input type="button" name="Submit52" id="cancel_btn"
				value="Cancel" class="search_input" onclick="window.location.href='ds_plate!plateEdit.action?id=${id}'"/>
					
		</div>
    </div>

<form name="" id="fileDownLoad" method="post">
	<s:hidden name="filePath" id="filePath"></s:hidden>
	<s:hidden name="fileName" id="fileName"></s:hidden>
</form>
<input type="hidden" id="id" value="${id}">
<input type="hidden"  id="dsPlateItemId"/>
</body>
</html>