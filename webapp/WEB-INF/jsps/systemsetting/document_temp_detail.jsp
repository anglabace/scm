<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/common/taglib.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>document templates detail</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />

<style type="text/css">
<!--
.content .scm #dhtmlgoodies_tabView1 .dhtmlgoodies_aTab .General_table fieldset {
	margin: 4px;
}
-->
</style>
<script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1"
			type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}ajaxupload.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>
<script>
$(function(){
	var isSaved = false;
	  $('#documentTemplateForm').validate({
			errorClass:"validate_error",
			highlight: function(element, errorClass) {
				$(element).addClass(errorClass);
		    },
		    unhighlight: function(element, errorClass, validClass) {
		        $(element).removeClass(errorClass);
		    },
			invalidHandler: function(form, validator) {
				 $.each(validator.invalid, function(key,value){
		            alert(value);
		            $(this).find("name=[" + key + "]").focus();
		            return false;
		        });
			 },
			 rules: {
				 "serviceDocTemplate.name": { required:true}
			 },
			 messages: {
				 "serviceDocTemplate.name": { required : "Please enter the name !" }
			 },
			 errorPlacement: function(error, element) {
			 }
		});
		            
      $("#save_btn").click (function() {
         if ($('#documentTemplateForm').valid()  === false ) {
            return false;
         }
         var formStr = $('#documentTemplateForm').serialize();
         $('#save_btn').attr("disabled", true);
         $.ajax({
				type: "POST",
				url: "document_templates!save.action",
				data: formStr,
				dataType: 'json',
				success: function(data, textStatus){
					if(hasException(data)){//æéè¯¯ä¿¡æ¯.
		 	           $('#save_btn').attr("disabled", false);				
					}else{                              
					  alert(data.message);
					  isSaved = true;
					  location.href="document_templates!list.action";
					}
				},
				error: function(xhr, textStatus){
				   alert("failure");
		 	       $('#save_btn').attr("disabled", false);
				}
			});                
      });//end of {$("#save_btn").click};               

	new AjaxUpload('upload_btn',{
		action: 'document_templates!upload.action',
		name: 'doc',
		textField:'upload_file',
		single:true,
		onSubmit : function(file, ext){
			this.disable();
		},
		onCheck:function() {
			if($("#fileNameShow").html()=='') {
				if($("#dispDelLbl").html()==null||$("#dispDelLbl").html()=='') {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		},
		onComplete: function(file, response){
			if(response!=null) {
				docFileUrl = response.split("@");
			}
			var docId = docFileUrl[0];
			var fileName = docFileUrl[1];
			this.enable();
			if(fileName!=null) {
				var newName= fileName+"&nbsp;&nbsp;<input type='button' id='delButton' value='delete' onclick='deleteFile()'/>";
				$("#fileNameShow").html(newName);
				$("#docId").val(docId);			
			}
		}
	});

	window.onbeforeunload = function() {
		if(isSaved === false){
			return 'Do you want to leave without saving data?';
		}
	}

});
function deleteFile() {
	$.ajax({   
		url: 'document_templates!deleteFile.action',   
		type:'POST',   
		dataType: 'json',   
		data:"docId="+$("#docId").attr("value"),   
		beforeSend:function(xmlhttprequest){
		},
		success:function(data){
			if(hasException(data)){//有错误信息.
		           $('#delButton').attr("disabled", false);				
			} 
			alert(data.message);
			$("#fileNameShow").html("");
			$("#dispDelLbl").html("");
			$("#docId").val("");	
		},
		error:function(){
			alert("delete file failed!");
		}
		}); 
}

function cancel() {
	if($("#fileNameShow")!=null&&$("#fileNameShow").html()!=null&&$("#fileNameShow").html()!='') {
		alert("Please delete files after upload cancel！");
		return;
	}
	window.history.go(-1);
}
</script>
</head>

<body class="content">
<s:hidden name="message" id="message"></s:hidden>
<s:form id="documentTemplateForm" class="niceform">
<div class="scm">
<div class="title_content">
  <div class="title">Document Template - ${serviceDocTemplate.name}</div>
</div>
<div class="input_box">
		  <div class="content_box">
		      <table border="0" cellpadding="0" cellspacing="0" class="General_table">
		      	<tr>
                  <th valign="top"><span class="important">*</span>Template Name</th>
                  <td colspan="3"><s:textfield name="serviceDocTemplate.name" Class="NFText" size="25" id="sDTname"/></td>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
		      	
                <tr>
                  <th width="164">Document Template Name</th>
                  <td width="280"><div id="upload_div"><input type='text' id="upload_file" name='upload_file' class='NFText'/>  <input type='button' name='button' id='upload_btn' value='Browse...' class='style_botton' /></div></td>
                  <td colspan="2">
	                  
                  </td>
                  <th width="148">Status</th>
                  <td width="165">
                  <s:select name="serviceDocTemplate.status"
                  			list="#{'ACTIVE':'ACTIVE','INACTIVE':'INACTIVE'}" 
                  			listKey="key" 
                  			listValue="value">
                  </s:select>
                  </td>
                </tr>
                <tr>
                	<td colspan="4" align="center">
	                  <div id="fileNameShow"></div>
	                  <s:hidden name="serviceDocTemplate.docId" id="docId"/>
				      <s:if test="serviceDocTemplate.docName!=null&&serviceDocTemplate.docName!=''">
					   <div id="dispDelLbl"><s:property value="serviceDocTemplate.docName"/>
					   <input type="button" id="delButton" value="delete" onclick="deleteFile()"/></div>
				      </s:if>
                  </td>
                  <th>
                  </th>
                  <td></td>
                </tr>
                      <tr>
                  <th>Work Function</th>
                  <td colspan="3">
                  <s:if test="dropDownMap['TEMPLATE_WORK_FUNCTION']!=null&&dropDownMap['TEMPLATE_WORK_FUNCTION'].size>0">
                  	<s:select name="serviceDocTemplate.function"
                  			list="dropDownMap['TEMPLATE_WORK_FUNCTION']"  
                  			listKey="value" 
                  			listValue="text" 
                  			style="width:157px;">
                  	</s:select>
                  </s:if>
                  <s:else>
                  	<select name="serviceDocTemplate.function">
                  		<option value="">Please select...</option>
                  	</select>
                  </s:else>
                  </td>
                  <th>Service Category</th>
                  <td>
                  <s:if test="serviceClassificationList!=null&&serviceClassificationList.size>0">
                  <s:select name="serviceDocTemplate.serviceClsId"
                  			list="serviceClassificationList"  
                  			listKey="clsId" 
                  			listValue="name" 
                  			style="width:157px;">
                  </s:select>
                  </s:if>
                  <s:else>
                  	<select name="serviceDocTemplate.serviceClsId">
                  		<option value="">Please select...</option>
                  	</select>	
                  </s:else>
                  <s:else>
                  </s:else>
                  </td>
                </tr>
                  <tr>
                  <th valign="top">Description</th>
                  <td colspan="3">
                  	<s:textfield name="serviceDocTemplate.description" Class="NFText" size="50"></s:textfield>
                  </td>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
                  <tr>
                  <th valign="top">Modified Date</th>
                  <td colspan="3">
                  	<s:textfield name="serviceDocTemplate.modifyDate" Class="NFText" size="25"  readonly="true">
                  	<s:param name='value'>
						<s:date name="serviceDocTemplate.modifyDate" format="yyyy-MM-dd"/>
			         </s:param>
                  	</s:textfield>
                  </td>
                  <th>Modified By</th>
                  <td>
                  	<s:textfield name="serviceDocTemplate.modifyName" Class="NFText" size="25"  readonly="true"></s:textfield>
                  	<s:hidden name="serviceDocTemplate.modifiedBy"></s:hidden>
                  </td>
                </tr>
                <tr>
                  <th valign="top">&nbsp;</th>
                  <td colspan="3">&nbsp;</td>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <th>&nbsp;</th>
                  <td colspan="3">&nbsp;</td>
                  <th>&nbsp;</th>
                  <td>&nbsp;</td>
                </tr>
            
              </table>
		</div>
  </div>
</div>

<div class="button_box">
      <input type="button" id="save_btn" name="Submit123"  value="Save" class="search_input" />
      <input type="button" name="Submit124" value="Cancel" class="search_input" onclick="cancel()"/>
</div>
<s:hidden name="serviceDocTemplate.creationDate"></s:hidden>
<s:hidden name="serviceDocTemplate.createdBy"></s:hidden>
<s:hidden name="serviceDocTemplate.templateId"></s:hidden>
</s:form>
</body>
</html>