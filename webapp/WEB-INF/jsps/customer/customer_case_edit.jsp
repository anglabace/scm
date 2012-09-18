<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}scm/config.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<link type="text/css"
	href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />

<script src="${global_js_url}jquery/ui/ui.datepicker.js"
	type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}ajaxupload.js"></script>
<script>
	$().ready(function() {
		$('.ui-datepicker').each(function() {
			$(this).datepicker({
				dateFormat : 'yy-mm-dd',
				changeMonth : true,
				changeYear : true
			});
		});
		$('#receiveDate').datepicker();
	});
	var custNo = parent.$("#custNo").val();
	$(function() {
		parent.$('#srchPdtSvcDialog').dialog({
			autoOpen : false,
			height : 350,
			width : 640,
			modal : true,
			bgiframe : true,
			buttons : {}
		});
		//search button
		$("#srchPdtSvcDialogTrigger")
				.click(
						function() {
							parent
									.$('#srchPdtSvcDialog')
									.dialog(
											"option",
											"open",
											function() {
												var htmlStr = '<iframe id="srchPdtSvc_iframe" src="cust_case!searchPdtList.action" height="300" width="600" scrolling="no" style="border:0px" frameborder="0"></iframe>';
												parent.$(
														'#srchPdtSvcDialog')
														.html(htmlStr);
											});
							parent.$('#srchPdtSvcDialog').dialog('open');

						});

		parent.$("#viewOrderDialog").dialog({
			autoOpen : false,
			height : 700,
			width : 1000,
			modal : true,
			bgiframe : true,
			buttons : {}
		});
		//view button
		$("#viewOrderDialogTrigger")
				.click(
						function() {
							var orderNo = $("#case_orderNo").val();
							if (!orderNo) {
								return;
							}
							$.ajax({
								type : "POST",
								url : "order/order!isExistOrder.action",
								data : "orderNo="+ orderNo,
								dataType:'json',
								success : function(msg) {
									if(msg.message=='success') {
										parent.$('#viewOrderDialog')
										.dialog(
												"option",
												"open",
												function() {
													var htmlStr = '<iframe id="viewOrder_iframe" src="order/order!edit.action?orderNo='
															+ orderNo
															+ '&custNo=${custNo}&operation_method=view" height="700" width="1000" scrolling="no" style="border:0px" frameborder="0"></iframe>';
													parent.$(
															'#viewOrderDialog')
															.html(htmlStr);
												});
								parent.$('#viewOrderDialog').dialog('open');
									} else {
										alert("The order#"+orderNo+" is not exist.");
									}
									
								},
								error : function(msg) {
									alert("Failed to view the order. Please contact system administrator for help.");
								}
							});


						});
		new AjaxUpload('upload_btn',{
			action:"cust_case!uploadFile.action" +"?sessCaseId="+ $("#sessCaseId").val()+"&sessCustNo="+$("#sessCustNo").val(),
			name: 'upload',
			textField:'upload_file',
			single:false,
			onSubmit : function(file, ext){
				this.disable();
			},
			onComplete: function(file, response){
				var docFileUrl = null;
				if(response!=null) {
					docFileUrl = response.split("@");
					var tmpPath = "download.action?filePath="+docFileUrl[2]+"&fileName="+docFileUrl[1];
				    var tmpStr = '<tr>';
					tmpStr += '<td>';
					tmpStr += '<a target="_blank" href="'+tmpPath+'">'+docFileUrl[1]+'</a>';
					tmpStr += '</td>';
					tmpStr += '<td><input type="button" onclick="deleteFile(\'' + docFileUrl[0] + '\')"  value="Delete" class="style_botton" /></td>';
					tmpStr += '</tr>';
					$("#formCase").find("#fileListTable").append(tmpStr);
					var file = $("#upload_file");
					file.after(file.clone().val(""));
					file.remove();
					this.enable();
			        var createBy = $("#createdBy").val();
			        if(createBy=="") {
			        	var height = parent.$('#newCase').dialog('option','height');
				    	parent.$('#newCase').dialog('option','height',height+10);
			        } else {
			        	var height = parent.$('#editCaseDialog').dialog('option','height');
				    	parent.$('#editCaseDialog').dialog('option','height',height+10);
			        }
				}
				
			}
		});
	});
	//save button   
	function saveCase() {
		if (isNaN($("#case_orderNo").attr("value"))) {
			alert("The order No must be a number");
			document.getElementById("case_orderNo").focus();
			return false;
		}
		if (!jQuery.trim($("#case_subject").attr("value"))) {
			alert("Please enter the subject.");
			document.getElementById("case_subject").focus();
			return false;
		}
		var myDate = new Date();
		var receiveD = $('[name="customerCase.receiveDate"]').val();
		var receiveArr = receiveD.split("-");
		var receiveDate = new Date(receiveArr[0], receiveArr[1] - 1,
				receiveArr[2]);
		if (myDate.getTime() < receiveDate.getTime()) {
			alert("Please enter the correct Receive Date.");
			return false;
		}
		$.ajax({
					type : "POST",
					url : "cust_case!save.action",
					data : $("#formCase").serialize(),
					success : function(msg) {
						closeDialog();
						self.parent.document.getElementById("customer_case_iframe").contentWindow.location.reload();
					},
					error : function(msg) {
						alert("Failed to save new case. Please contact system administrator for help.");
					}
				});
	}
	//获取附件列表html字符串
	function getFileListHtml(documentList){
		  var tmpStr = "";
	 	  $.each(documentList, function(key, value) {
	            var tmpPath = "download.action?filePath="+value["filePath"]+"&fileName="+value["docName"];
			    tmpStr += '<tr>';
				tmpStr += '<td>';
				tmpStr += '<a target="_blank" href="'+tmpPath+'">'+value.docName+'</a>';
				tmpStr += '</td>';
				tmpStr += '<td><input type="button" onclick="deleteFile(\'' + key + '\')"  value="Delete" class="style_botton" /></td>';
				tmpStr += '</tr>';   
	      });
		  return tmpStr;
	}
	
	function deleteFile(fileKey, event) {
		   var event=event||window.event;
		   var e = event.srcElement||event.target;
		   var trObj = $(e).parent().parent();
		   var createBy = $("#createdBy").val();
		   if (confirm("Are you confirm delete the file ?")) {   
		        $.ajax({
					type: "POST",
					url: "cust_case!deleteFile.action",
					data: "sessCaseId="+ $("#sessCaseId").val() + "&fileKey=" + fileKey+"&sessCustNo="+$("#sessCustNo").val(),
					success: function(data, textStatus) {
			           $("#formCase").find("#fileListTable").get(0).deleteRow(trObj.get(0).rowIndex); 
			           if(createBy=="") {
				        	var height = parent.$('#newCase').dialog('option','height');
					    	parent.$('#newCase').dialog('option','height',height-10);
				        } else {
				        	var height = parent.$('#editCaseDialog').dialog('option','height');
					    	parent.$('#editCaseDialog').dialog('option','height',height-10);
				        }
					},
					error: function(xhr, textStatus){
					   alert("failure");
					}
			   });   

		   }
		}
	function closeDialog() {
		 var createBy = $("#createdBy").attr("value");
		 if(createBy=="") {
		    	parent.$('#newCase').dialog('close');
	        } else {
		    	parent.$('#editCaseDialog').dialog('close');
	        }
	}
</script>

<style type="text/css">
<!--
body {
	margin-left: 10px;
	margin-top: 10px;
}
-->
</style>
</head>
<body>
	<form id="formCase"  method="post"
						enctype="multipart/form-data">
		<input type="hidden"
			name="sessCustNo" id="sessCustNo" value="${sessCustNo}" />
		<input type="hidden"
			name="sessCaseId" id="sessCaseId" value="${sessCaseId}" />
		<input type="hidden" name="customerCase.creationDate" id="creationDate"/>
		<s:hidden name="customerCase.createdBy" id="createdBy"/>
		<table width="700" border="0" cellpadding="0" cellspacing="0"
			class="General_table">
			<tr>
				<td align="right">Case ID</td>
				<td><input name="customerCase.caseId"
					id="case_id" type="text" class="NFText" size="20"
					value="${customerCase.caseId}" readonly="readonly"/></td>
				<td align="right">Case Owner</td>
				<td><input name="customerCase.ownerName"  type="text" class="NFText" size="20"
					value="${customerCase.ownerName}" readonly="readonly"/>
					<s:hidden name="customerCase.owner"></s:hidden>
				</td>
			</tr>
			<tr>
				<td align="right">Contact Name</td>
				<td><input name="customerCase.contactName"
					id="case_contactName" type="text" class="NFText" size="20"
					maxlength="50" value="${customerCase.contactName}" /></td>
				<td align="right">Status</td>
				<td><label>
				<s:if test="dropDownList['CASE_STATUS']!=null&&dropDownList['CASE_STATUS'].size()>0">
					<s:select id="case_status"
							name="customerCase.status" list="dropDownList['CASE_STATUS']"
							listKey="value" listValue="value" value="customerCase.status"></s:select>
				</s:if> 
				<s:else>
					<select name="customerCase.status" id="case_status">
						<option value=""></option>
					</select>
				</s:else>
				</label>
				</td>
			</tr>


			<tr>
				<td align="right">Order No</td>
				<td><input name="customerCase.orderNo" id="case_orderNo"
					type="text" class="NFText" size="20" maxlength="20"
					value="${customerCase.orderNo}" /> <input type="button"
					id="viewOrderDialogTrigger" class="style_botton" value="View" /></td>
				<td align="right">Priority</td>
				<td>
				<s:if test="dropDownList['CASE_PRIORITY']!=null&&dropDownList['CASE_PRIORITY'].size()>0">
				<s:select id="case_priority" name="customerCase.priority"
						list="dropDownList['CASE_PRIORITY']" listKey="value"
						listValue="value" value="customerCase.priority">
				</s:select>
				</s:if>
				<s:else>
					<select id="case_priority" name="customerCase.priority">
						<option value=""></option>
					</select>
				</s:else>
				
				</td>
			</tr>
			<tr>
				<td align="right">Case Type</td>
				<td>
				<s:if test="dropDownList['CASE_TYPE']!=null&&dropDownList['CASE_TYPE'].size()>0">
				<s:select id="case_type" name="customerCase.type"
						list="dropDownList['CASE_TYPE']" listKey="value" listValue="value"
						value="customerCase.type">
				</s:select>
				</s:if>
				<s:else>
				<select id="case_type" name="customerCase.type">
					<option value=""></option>
				</select>
				</s:else>
				</td>
				<td align="right">Case Origin</td>
				<td>
				<s:if test="dropDownList['CASE_ORIGIN']">
					<s:select id="case_origin" name="customerCase.origin"
						list="dropDownList['CASE_ORIGIN']" listKey="value"
						listValue="value" value="customerCase.origin">
				</s:select>
				</s:if>
				<s:else>
					<select id="case_origin" name="customerCase.origin">
					<option value=""></option>
					</select>
				</s:else>
				</td>
			</tr>
			<tr>
				<td align="right">Case Reason</td>
				<td>
				<s:if test="dropDownList['CASE_REASON']!=null&&dropDownList['CASE_REASON'].size()>0">
					<s:select id="case_reason" name="customerCase.reason"
						list="dropDownList['CASE_REASON']" listKey="value"
						listValue="value" value="customerCase.reason">
				</s:select>
				</s:if>
				<s:else>
					<select id="case_reason" name="customerCase.reason">
						<option value=""></option>
					</select>
				</s:else>
				
				</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td align="right">Product/Service</td>
				<td><input type="text" name="customerCase.catalogNo"
					id="case_catalogNo" readonly="readonly"
					value="${customerCase.catalogNo}" class="NFText" /> <img
					width="16" height="16" align="middle" src="images/search.jpg"
					id="srchPdtSvcDialogTrigger" style="cursor: pointer;" />
				</td>
				<td align="right">Potential Liability</td>
				<td>
		
				<s:select name="customerCase.potLiability" id="case_potLiability"
									cssStyle="width:171px;"
									list="specDropDownList['ALL_DEPARTMENTS'].dropDownDTOs"
									listKey="name" listValue="name" headerKey="Shipping carrier"
									headerValue="Shipping carrier" value="customerCase.potLiability"></s:select>
		
				</td>
			</tr>

			<tr>

				<td align="right">Case Receive Date</td>
				<td><input name="customerCase.receiveDate"
					id="case_receiveDate" type="text" class="NFText ui-datepicker"
					value='<s:date name="customerCase.receiveDate" format="yyyy-MM-dd" />'
					size="20" style="width: 120px;" />
				</td>

				<td align="right">Case Open Date</td>
				<td><input name="customerCase.openDate"
					id="case_openDate" type="text" class="NFText ui-datepicker"
					value='<s:date name="customerCase.openDate" format="yyyy-MM-dd" />'
					size="20"   style="width: 120px;" />
				</td>
			</tr>

		<tr>

				<td align="right">Modify Date</td>
				<td><input name="customerCase.modifyDate"
					id="case_updateDate" type="text" class="NFText"
					value='<s:date name="customerCase.modifyDate" format="yyyy-MM-dd" />'
					size="20"   style="width: 120px;" readonly="readonly"/>
				</td>

				<td align="right">Modifie By</td>
				<td><input name="customerCase.modifiedUser"
					id="case_modifiedBy" type="text" class="NFText"
					value='${customerCase.modifiedUser}'
					size="20"   style="width: 120px;" readonly="readonly"/>
					<s:hidden name="customerCase.modifiedBy"></s:hidden>
				</td>
			</tr>
			<tr>
				<td align="right"> Subject</td>
				<td colspan="3"><input name="customerCase.subject"
					id="case_subject" type="text" class="NFText" size="76"
					maxlength="255" value="${customerCase.subject}" />
				</td>
			</tr>
			<tr>
				<td valign="top" align="right">Description</td>
				<td colspan="3"><textarea name="customerCase.description"
						id="case_description" class="content_textarea2">${customerCase.description}</textarea>
				</td>
			</tr>
			<tr>
				<td valign="top" align="right">Investigation</td>
				<td colspan="3"><textarea name="customerCase.investigation"
						id="case_investigation" class="content_textarea2">${customerCase.investigation}</textarea>
				</td>
			</tr>
			<tr>
				<td valign="top" align="right">Solution</td>
				<td colspan="3"><textarea name="customerCase.solution"
						id="case_solution" class="content_textarea2">${customerCase.solution}</textarea>
				</td>
			</tr>

           <tr>
               <td valign="top" align="right">Send Email To
                 </td>
               <td colspan="3">
               	<s:select name="customerCase.sendEmailTo" id="case_sendEmailTo"
									cssStyle="width:171px;"
									list="specDropDownList['CASE_SEND_EMAILTO'].dropDownDTOs"
									listKey="id" listValue="name" headerKey=""
									headerValue="Please select ..." value="customerCase.sendEmailTo"></s:select> 
        
			   </td>
           </tr>
            <tr>
            <td valign="top" align="right">Attachment</td>
            <td colspan="3">
            <div id="upload_div"><input type='text' id="upload_file" name='upload_file' class='NFText'/>  <input type='button' name='button' id='upload_btn' value='Browse...' class='style_botton' /></div>
			</td>
            </tr>
     		<tr>
				<th>
					&nbsp;
				</th>
				<td colspan="3">
					<table id="fileListTable">
						<s:iterator value="customerCase.documentMap">
							<tr>
								<td>
									<a target="_blank"
										href="download.action?filePath=${value.filePath}&fileName=${value.docName}">${value.docName}</a>
								</td>
								<td>
									<input type="button" onclick="deleteFile('${key}')"
										value="Delete" class="style_botton" />
								</td>
							</tr>
						</s:iterator>
					</table>
				</td>
			</tr>
			<tr>
				<td height="40" colspan="4">
					<div align="center">
						<input type="hidden" name="custNo" value="${custNo}" />  <input
							type="button" name="Submit" class="style_botton" value="Save and Send"
							onclick="saveCase();" />&nbsp;&nbsp; <input name="Submit2"
							type="button" class="style_botton"
							onclick="closeDialog();" value="Cancel" />
					</div>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>
