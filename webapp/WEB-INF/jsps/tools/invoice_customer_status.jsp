<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base id="myBaseId" href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Instruction Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css" />
<script src="${global_js_url}scm/config.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.form.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js" type="text/javascript"></script>
<script src="${global_js_url}scm/gs.util.js" type="text/javascript"></script>
<script src="${global_js_url}quoteorder/quoteorder_note_edit.js" type="text/javascript"></script>
<script src="${global_js_url}nicEdit.js" type="text/javascript"></script>
<script type="text/javascript">
    function showhid1(chk) {
        if (chk.checked) {
            $('#schedule_div').show();
            $('#status_div').show();
        } else {
            $('#schedule_div').hide();
            $('#status_div').hide();
        }
    }
</script>

		<style type="text/css">
<!--
body {
	margin: 0px auto;
	width: 570px;
	overflow-x: hidden;

}

.General_table {
	margin: 0px;
}
#instruction_form {
	width: 570px;
	overflow-x: hidden;
}
  
-->
</style>
	</head>
	<body>
	<input type="hidden" value="${customerNote.subject}" id="custConfirmMailTemplateSubject"/>
	<input type="hidden" value="${customerNote.content}" id="custConfirmMailTemplateContent"/>
	<input type="hidden" value="${customerNote.status}" id="oldStatus"/>
	<input type="hidden" value="${customerNote.custNo}" id="oldCustNo"/>
		<form id="instruction_form" method="post"
			enctype="multipart/form-data" id="myform">
			<div>
				<table width="600" border="0" cellpadding="0" cellspacing="0"
					class="General_table">	
					<tr>
						<th width="99" valign="top">
							Status
						</th >
						<td colspan="2" valign="top">
							<s:select list="{'ACTIVE','INACTIVE','SUSPENDED'}" value="customerNote.status" style="width: 132px;" name="customerNote.status"></s:select>		
						</td>
					</tr>	
		             <tr>
		                <td></td>
		                <td>Choose the reason to update the Customer Status:</td>
		              </tr>
					 <tr>
			            <td>&nbsp;</td>
			            <td width="99" height="60" valign="top"><textarea id="massage" name="customerNote.massage"  class="content_textarea"></textarea></td>
		            </tr>
					 <tr>
			            <th ></th>
			            <td colspan="2" valign="top"><input name="send_check" type="checkbox" id="send_check" checked="checked" />
			              	Send an Email on Confirm</td>
          			</tr>
				</table>
			</div>
			<div id="mail_div">
				<table width="600" border="0" cellpadding="0" cellspacing="0"
					class="General_table">
					<tr><th>
							Email Template
						</th>
						<td colspan="3" valign="top">
							<select name="content_select" id='content_select'
								onchange="change_content_tmpl(this)" style="width:225px">
								  <option value="">Please select</option>
							</select>
						</td>
					</tr>
					<tr>
						<th valign="top" width="99">
							To
						</th>
						<td colspan="3" valign="top" >
							<s:select id="email_to" name="email_to" list="mailGroupList"
								listKey="groupAddress" listValue="groupName"  headerKey=""
								headerValue="Please select" onchange="change_mailto('email_to');" style="width:225px"></s:select>
						</td>
					</tr>
					<tr>
						<th valign="top">
							&nbsp;
						</th>
						<td colspan="3">
							<textarea name="customerNote.sender" id="receipt"
								class="content_textarea2">${customerNote.sender}</textarea><input type="hidden" name="receiptValue" id="receiptValue" value=""/>
						</td>
					</tr>
					<tr>
						<th>
							<span class="important">*</span>Subject
						</th>
						<td colspan="3">
							<input name="customerNote.subject" value="${customerNote.subject}"
								id="email_subject" type="text" class="NFText" size="76" />
						</td>
					</tr>
				</table>
			</div>
			<div id="content_div">
				<table width="600" border="0" cellpadding="0" cellspacing="0"
					class="General_table">
					<tr>
						<th width="99" height="60" valign="top">
							<span class="important">*</span>Content
						</th>
						<td valign="top">
                              <textarea name="customerNote.content" id="content" class="content_textarea2" style="height: 200px;">${customerNote.content}</textarea>
						</td>
					</tr>
				</table>
			</div>

			<div id="file_div">
				<table width="600" border="0" cellpadding="0" cellspacing="0"
					class="General_table">
					<tr>
						<th valign="top" width="99">
							Attachment
						</th>
						<td>
							<div id="doc_list">
								<s:iterator value="documentList" id="item" status="st">
									<div id="${st.index}_docDiv">
										<a target="_blank" onclick="downloadFile('${item.filePath}','${item.docName}')" style="cursor:hand">${item.docName}</a>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" onclick="delete_file('${st.index}')" value="Delete" />
									</div>
								</s:iterator>
								<s:iterator value="custNoteDocumentList" id="item" status="st">
									<div id="${st.index}_docDiv">
										<a target="_blank" onclick="downloadFile('${item.filePath}','${item.docName}')" style="cursor:hand">${item.docName}</a>
										&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
								</s:iterator>
							</div>
							<div id="uploaded_files"></div>
							<input type="button" id="btn_add_file"
								value="Add Attachments" onclick="add_file()" />
						</td>
					</tr>
				</table>
			</div>

			<div>
				<table width="600" border="0" cellpadding="0" cellspacing="0"
					class="General_table">
					<tr>
						<td colspan="2">
							<div align="center">
									<input type="button" class="style_botton" value=" Confirm "
										onclick="saveStatus()" />					
								&nbsp;&nbsp;
								<input type="button" name="cancel" value="Cancel"
									class="style_botton"
									onclick="parent.$('#updateStatusDialog').dialog('close');" />
							</div>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<form action="" method="post" id="downloadForm">
			<input name="fileName" id="myFileName" type="hidden"/>
			<input name="filePath" id="myFilePath" type="hidden"/>
		</form>
		<script type="text/javascript">
		        //生成content_template相关数据的全局变量.
				var g_contentTmpl = new Array();			
				//一级content_template发生变化时触发执行的方法
				function change_content_tmpl(tmplObj) {
				    var tmplType = tmplObj.options[tmplObj.selectedIndex].text;//获得模板的类型'type';
				    for (var i=0; i<g_contentTmpl.length; i++) {
				         if (tmplObj.options[tmplObj.selectedIndex].value == "") {//第一项空值选中.
                            contentChange("");
		                    if (tmplType == 'Template') {
			                    var custConfirmMailTemplateSubject = $("#custConfirmMailTemplateSubject").val();
			                    var custConfirmMailTemplateContent = $("#custConfirmMailTemplateContent").val();
			                    $("#email_subject").val(custConfirmMailTemplateSubject);
                                $('#content').attr("value", custConfirmMailTemplateContent);
							}
						    return;
				         }		         
                         if (tmplType == g_contentTmpl[i][0]) {
			                 if (g_contentTmpl[i][1] == '') {//没有子级
			                    //获得模板的具体内容'content';
//			                    $('#content').attr("value", g_contentTmpl[i][2]);
                                 contentChange(g_contentTmpl[i][2]);
			                 } else {	                 	               
			                    //清除子级下拉.     
			                    $("#sub_content_select").get(0).length = 0;
			                    //循环生成子级下拉.
			                    for (var k=0; k<g_contentTmpl.length; k++) {					         
			                         if (tmplType == g_contentTmpl[k][0]) {
					                    $("#sub_content_select").append("<option value='" + g_contentTmpl[k][2] + "'> " + g_contentTmpl[k][1] + "</option>");  //为Select追加一个Option(下拉项)		                 
							         } 	         
							    }							    
							    $("#sub_slt_div").show();
//                                $('#content').attr("value", $("#sub_content_select").get(0).options[0].value);//用子级的第一项作默认值.
                                 contentChange($("#sub_content_select").get(0).options[0].value);
			                 }
		                    break;
				         }		         
				    }
				}
				
				//子级content_template发生变化时触发执行的方法
				function change_subcontent_tmpl(tmplObj) { 
//				     $('#content').attr("value", tmplObj.options[tmplObj.selectedIndex].value);
                    contentChange(tmplObj.options[tmplObj.selectedIndex].value);
				}
				
				$(document).ready(function(){			
					//生成content_template所有相关数据.
					<s:iterator value="mailTmplList" status="st">
					  g_contentTmpl[${st.index}] = Array('${type}', '${name}', '${content}');   
		            </s:iterator>
		            
		            //生成一级下拉框
		            var firstLevel = new Array();
				    for (var i=0; i<g_contentTmpl.length; i++) {					         
				         var bContainer = false;
				          for (var k=0; k<firstLevel.length; k++) {
				              if (g_contentTmpl[i][0] == firstLevel[k]) {
				                   bContainer = true;
				                   break;
				               }
				          }
				          //不在第一级下拉框中: 为了一级下拉框中不产生重复的项.
				          if (! bContainer) {
				             firstLevel[i] = g_contentTmpl[i][0];
                              var sel = "";
                              if(g_contentTmpl[i][0] == "Template"){
                                   sel = "selected='selected'";
                              }
        					     $("#content_select").append("<option value='" + g_contentTmpl[i][2] + "' " + sel + "> " + g_contentTmpl[i][0] + "</option>");  //为Select追加一个Option(下拉项)
				          }			         
				    }
                    if($('#content').attr("value") == ""){
                        change_content_tmpl(document.getElementById("content_select"));
                    }
                    $('#send_check').click(function(){
                    	var send_check = document.getElementById("send_check");
                    	if(send_check.checked){
                    		$('#doc_list').show();
							$('#file_div').show();
							$('#content_div').show();
							$('#mail_div').show();
                    	}else{
                    		$('#doc_list').hide();
							$('#file_div').hide();
							$('#content_div').hide();
							$('#mail_div').hide();
                    	}
            		});
				});

            function contentChange(value){
                $('#email_subject').val(value);
                $('#content').attr("value", value);
            }
            function saveStatus(){
            		if (! $('#email_subject').val()){
            			alert("Please enter the Subject."); 
            			$("#email_subject").focus(); 
            			return;
            		}
            		if (! $('#content').val()){
            			alert("Please enter the Content."); 
            			$("#content").focus(); 
            			return;
            		} 
            	var send_check = document.getElementById("send_check");
            	var sendBool=send_check.checked;
            	var action = "customer_invoice!updateStatus.action?sendBool="+sendBool+"&oldStatus="+$("#oldStatus").val()+"&oldCustNo="+$("#oldCustNo").val();
            	var form = $("#instruction_form");
            	var options = {
            		success:function(msg) {
            			if(msg =='success'){				
        					alert('Update status successfully');
        					parent.$('#updateStatusDialog').dialog('close');
        				}else if(msg == 'error'){
        					alert("System error! Please contact system administrator for help.");	
        				}else {
        					alert('Unknown error');
        				}
        				$(":checkbox").attr("checked", false);
        				window.parent.location="customer_invoice!list.action";
        			},
        			error: function(msg){
        				alert("Failed to cancel the status.");
        			},
            		resetForm:false, 
            		url:action,
            		type:"POST",
            		async:false
            	};
            	form.ajaxSubmit(options);
            }
			</script>
	</body>
</html>

