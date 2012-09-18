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
<s:include value="order_config.jsp"></s:include>
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

<s:if test="orderNote.custNoteId == null || orderNote.custNoteId == ''">
  bkLib.onDomLoaded(function() {
//	new nicEditor().panelInstance('area1');
//	new nicEditor({fullPanel : true}).panelInstance('area2');
	new nicEditor({iconsPath : '${ctx}/images/nicEditorIcons.gif'}).panelInstance('content');
//	new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image']}).panelInstance('area4');
//	new nicEditor({maxHeight : 100}).panelInstance('area5');
});
</s:if>
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
<s:if test="custConfirmMailTemplate != null">
<input type="hidden" value="${custConfirmMailTemplate.subject}" id="custConfirmMailTemplateSubject"/>
<input type="hidden" value="${custConfirmMailTemplate.content}" id="custConfirmMailTemplateContent"/>
</s:if>
		<form id="instruction_form" method="post"
			enctype="multipart/form-data" id="myform">
			&nbsp;
			<input type="hidden" id="sessOrderNo" name="sessOrderNo"
				value="${sessOrderNo}" />
			<input type="hidden" id="noteKey" name="noteKey" value="${noteKey}" />
			<input type="hidden" id="id" name="orderNote.id" value="${orderNote.id}" />
			<input type="hidden" id="docFlag" name="orderNote.docFlag" value="${orderNote.docFlag}" />
			<input type="hidden" name="uploaded_count"  id="uploaded_count" />
			<input type="hidden" name="docDelIndexs" id="docDelIndexs" />
			<div>
				<table width="600" border="0" cellpadding="0" cellspacing="0"
					class="General_table">
					<s:if test="orderNote.orderNo!=null">
						<tr>
					   	 	<th width="99" valign="top">Order No</th>
					    	<td colspan="2"><input type="text" value="<s:property value='orderNote.orderNo'/>" disabled="disabled"/></td>
					  	</tr>
					</s:if>
					
					<tr>
						<th width="99" valign="top">
							Type
						</th>
						<td width="180">
							<select name="type" id="notetype_sel" style="width: 170px;"
								onchange="change_note_type(this)">
								
						<s:if test="tmpStr=='tt'">
								<s:if test="status=='NW'||status=='RV'">
									<option value="CUST_CONFIRM_EMAIL">
										Customer Confirmation Email
									</option>
								</s:if>
								<option value="SALES_NOTES">
									Sales Notes
								</option>
								<option value="SHIPMENT">
									Shipment Notes
								</option>
								<option value="PRODUCTION">
									Production Notes
								</option>
								<option value="ACCOUNTING">
									Accounting Notes
								</option>
                                <option value="CROSS_SELLING">
									Cross Selling Notes
								</option>
						</s:if>
                        <s:elseif test="tmpStr=='ff'">
                                <option value="ORDER_CHANGE_NOTIFICATION">
										Add Order Change Notification
								</option>
                        </s:elseif>
						<s:else>
								<s:if test="status=='NW'||status=='RV'">
									<option value="CUST_CONFIRM_EMAIL">
										Customer Confirmation Email
									</option>
								</s:if>
						</s:else>
							</select>
						</td>
						<td>
						
							<div id="send_chk_div">
								<input name="chk_send_email" id="chk_send_email" type="checkbox" value="checkbox" checked="checked" />Send an Email on Save
							</div>
						
						</td>
					</tr>
                    <tr>
						<th width="99" valign="top"></th>
                        <td colspan="2"><input name="chk_email_group" id="chk_email_group" type="checkbox" value="" onclick="changeEmailGroup();"/> Gene sequence length or peptide sequence length, purity, size changed?</td>
                    </tr>
				</table>
			</div>

			<div id="send_div">
				<table width="600" border="0" cellpadding="0" cellspacing="0"
					class="General_table" border="1">
					<tr>
						<td width="275">
							<table>
								<tr>
									<th valign="top" width="99">
										Contact Date
									</th>
									<td width="175">
										&nbsp;
										<input name="contactDate" id="contact_date" type="text"
											class="ui-datepicker" style="width: 124px;"
											value="<s:date name="orderNote.sendDate" format="yyyy-MM-dd"/>"
											onchange="change_contact_date(this);" readonly="readonly"/>
									</td>
								</tr>
							</table>
						</td>
						<td>
							<div id='schedule_div'>
								<table>
									<tr>
										<th width="90">
											Scheduled On
										</th>
										<td width="234">
											<s:if test="noteKey == null">
											<input name="scheduleDate" id="schedule_date" type="text"
												class="ui-datepicker" style="width: 124px;"
												value="<s:date name="curDate" format="yyyy-MM-dd"/>"
												onchange="change_schedule_date(this);" readonly="readonly"/>
									</s:if>
									<s:else>
										<input name="scheduleDate" id="schedule_date" type="text"
												class="ui-datepicker" style="width: 124px;"
												value="<s:date name="orderNote.scheduleDate" format="yyyy-MM-dd"/>"
												onchange="change_schedule_date(this);" readonly="readonly"/>
									</s:else>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</div>

			<div id="status_div">
				<table width="600" border="0" cellpadding="0" cellspacing="0"
					class="General_table">
					<tr>
						<th height="32" width="99" valign="top">
							Status
							<br />
						</th>
						<td colspan="3" valign="top">
							<input name="status" type="radio" value="COMPLETE" />
							COMPLETE
							<input type="radio" name="status" value="INCOMPLETE" checked="checked"/>
							INCOMPLETE
						</td>
					</tr>
				</table>
			</div>

			<div id="mail_div">
				<table width="600" border="0" cellpadding="0" cellspacing="0"
					class="General_table">
					<tr><th valign="top" width="99">
							Email Template
						</th>
						<td colspan="3" valign="top">
							<select name="content_select" id='content_select'
								onchange="change_content_tmpl(this)" style="margin-bottom: 3px;">
								  <option value="">Please select</option>
							</select>
						</td>
					</tr>
					<tr>
						<th valign="top" width="99">
							To
						</th>
						<td colspan="3" valign="top">
							<s:select id="email_to" name="email_to" list="mailGroupList"
								listKey="groupAddress" listValue="groupName"  headerKey=""
								headerValue="Please select" onchange="change_mailto('email_to');"></s:select>
						</td>
					</tr>
					<tr>
						<th valign="top">
							&nbsp;
						</th>
						<td colspan="3">
							<textarea name="receipt" id="receipt"
								class="content_textarea2">${orderNote.receipt}</textarea><input type="hidden" name="receiptValue" id="receiptValue" value=""/>
						</td>
					</tr>
					<tr>
						<th>
							<span class="important">*</span>Subject
						</th>
						<td colspan="3">
							<input name="orderNote.subject" value="${orderNote.subject}"
								id="email_subject" type="text" class="NFText" size="76" />
						</td>
					</tr>
				</table>
			</div>

			<div id="followup_div">
				<table width="600" border="0" cellpadding="0" cellspacing="0"
					class="General_table">
					<tr>
						<th valign="top" width="99">
							<span class="important">*</span>Follow Up Date
						</th>
						<td>
							<input name="followup_date" id="followup_date" type="text"
								class="ui-datepicker" style="width: 124px;"  readonly="readonly"/>
						</td>
					</tr>
				</table>
			</div>

			<div id="desc_div">
				<table width="600" border="0" cellpadding="0" cellspacing="0"
					class="General_table">
					<tr>
						<th valign="top" width="99">
							<span class="important">*</span>Description
						</th>
						<td>
							<textarea name="description" id="description"
								class="content_textarea2">${orderNote.description}</textarea>
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
                                <s:if test="orderNote.custNoteId == null || orderNote.custNoteId == ''">
                                    <textarea name="content" id="content" class="content_textarea2" style="height: 200px;">${orderNote.content}</textarea>
                                </s:if>
                                <s:else>
                                    <div class="terms" style="height:400px">${orderNote.content}</div>
                                </s:else>
							<div id="sub_slt_div" style='display: none'>
								<div style="margin: -61px 0px 5px 180px;">
									<select id="sub_content_select" style="width: 230px;"
										onchange="change_subcontent_tmpl(this)">
									</select>
								</div>
							</div>

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
								<s:iterator value="orderNote.documentList" id="item" status="st">
									<div id="${st.index}_docDiv">
										<a target="_blank" onclick="downloadFile('${item.filePath}','${item.docName}')" style="cursor:hand">${item.docName}</a>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" onclick="delete_file('${st.index}')" value="Delete" />
									</div>
								</s:iterator>
								<s:iterator value="orderNote.custNoteDocumentList" id="item" status="st">
									<div id="${st.index}_docDiv">
										<a target="_blank" onclick="downloadFile('${item.filePath}','${item.docName}')" style="cursor:hand">${item.docName}</a>
										&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
								</s:iterator>
							</div>
							<s:if test="orderNote.custNoteId == null || orderNote.custNoteId == ''">
							<div id="uploaded_files"></div>
							<input type="button" id="btn_add_file"
								value="  Add Attachments  " onclick="add_file()" />
							</s:if>	
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
								<br />
								<s:if test="noteKey == null || noteKey == ''">
									<input type="button" class="style_botton" value=" Add"
										onclick="saveNote()" />
								</s:if>
								<s:else>
								   <s:if test="orderNote.status != 'COMPLETE' && (orderNote.custNoteId == null || orderNote.custNoteId == '')">
									<input type="button" class="style_botton" value=" Update "
										onclick="saveNote()" />
								    </s:if>
								</s:else>
								&nbsp;&nbsp;
								<input type="button" name="cancel" value="Cancel"
									class="style_botton"
									onclick="parent.$('#instruction_dlg').dialog('close');parent.$('#instruction_update_dlg').dialog('close');" />
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
		                    $("#sub_slt_div").hide();	
//		                    $('#content').attr("value", "");
                            contentChange("");
		                    if (tmplType == 'Template') {
			                    var custConfirmMailTemplateSubject = $("#custConfirmMailTemplateSubject").val();
			                    var custConfirmMailTemplateContent = $("#custConfirmMailTemplateContent").val();
			                    $("#email_subject").val(custConfirmMailTemplateSubject);
                                <s:if test="tmpStr!='ff'">
//                                    $('#content').attr("value", custConfirmMailTemplateContent);
                                    contentChange(custConfirmMailTemplateContent);
                                </s:if>
							}
						    return;
				         }		         
                         if (tmplType == g_contentTmpl[i][0]) {
			                 if (g_contentTmpl[i][1] == '') {//没有子级
			                    //获得模板的具体内容'content';
//			                    $('#content').attr("value", g_contentTmpl[i][2]);
                                 contentChange(g_contentTmpl[i][2]);
			                    $("#sub_slt_div").hide();
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
					//初始化页面: 提交页面后， 回显时选中note_type.
					var searchNoteType = "${searchNoteType}";
					if(searchNoteType == "CUST_CONFIRM_EMAIL"){
						$("#notetype_sel").html('<option value="CUST_CONFIRM_EMAIL">Customer Confirmation Email</option>');
					}
					$("#notetype_sel option[value='${searchNoteType}']").attr( 'selected' , true ) ;
					
					//初始页面根据note_type, 显示与隐藏相关div.
					change_note_type($("#notetype_sel").get(0));

					var notetype_sel = $("#notetype_sel").val();
					if (notetype_sel == "CUST_CONFIRM_EMAIL" || notetype_sel == "ORDER_CHANGE_NOTIFICATION") {
						if ('${orderNote.status}' == 'COMPLETE') {	
							$("#chk_send_email").attr("checked", false);
							$("#chk_send_email").attr("disabled", true);
							$('#schedule_div').hide();
							$('#status_div').hide();
						} else {
							$("#chk_send_email").attr("checked", true);
							$("#chk_send_email").attr("disabled", "");
							$('#schedule_div').show();
							$('#status_div').show();
						}
					}
					
					//编辑则type不能修改
					if ('${noteKey}' != '') {
					  $("#notetype_sel").attr("disabled", true);
                      $(":radio[value='${orderNote.status}']").attr("checked", true);
                      if ('${orderNote.status}' == 'COMPLETE') {			
                         $(":text").attr("disabled", true);
                         $(":radio").attr("disabled", true);
                         $("#email_to").attr("disabled", true);
                         $("#content_select").attr("disabled", true);
                         $(".content_textarea2").attr("disabled", true);
                         $("input[type='button']:not(:last)").attr("disabled", true);
                      }
					}					
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
				});

            //add by zhanghuibin
            function changeEmailGroup(){
                if ($("#chk_email_group").attr("checked")) {
                    $.ajax({
                        type:"get",
                        url:"email_group!getEmailGroupList.action?groupName=Peptide Dept Dept WO Notification",
                        dataType:"json",
                        success:function(data) {
                            var recStr = $("#receipt").val();
                            $("#receiptValue").attr("value", ","+data.groupAddress);
                            $("#receipt").attr("value", recStr +","+ data.groupAddress);
                            parent.$("#keyInfoChanged").attr("value", "1")
                        },
                        error:function(data) {
                            alert("System error! Please contact system administrator for help.");
                        },
                        async:false
                    });
                }else{
                    var recStr = $("#receipt").val();
                    var receiptValue = $("#receiptValue").val();
                    recStr = recStr.replace(receiptValue, "");
                    $("#receipt").attr("value", recStr)
                }
            }

            function contentChange(value){
                $("div.nicEdit-main").html(value);
                $('#content').attr("value", value);
            }
			</script>
	</body>
</html>

