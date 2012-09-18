<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Division Edit</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}tab-view.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="${global_js_url}jquery/jquery.js"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
		<script src="${global_js_url}jquery/jquery.validate.js?v=1"
			type="text/javascript"></script>
		<script type="text/javascript" src="${global_js_url}tab-view.js"></script>
		<script
			src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js"
			type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.core.js"
			type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.draggable.js"
			type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.resizable.js"
			type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.dialog.js"
			type="text/javascript"></script>
		<!-- 以下为country和state联动部分的JS. -->
<script type="text/javascript"
        src="${global_js_url}scm/gsCountryState.js?v=1"></script>
<s:if test='division.country !=""'>
	<script>
		var countryIdNames = ['country'];
		var countryDefaults = ['${division.country}'];
		var countryChangeHandlers = [''];
		
		var stateIdNames = ['state'];
		var stateDefaults = [''];
		var stateChangeHandlers = [''];
		$(document).ready(function(){
			initCountry();
		    document.getElementById("state").value = '${division.state}';//stateDefaults;
		});	
	</script>
</s:if>
<s:else>
    <script>
		var countryIdNames = ['country'];
		var countryDefaults = ['US'];
		var countryChangeHandlers = [''];
		
		var stateIdNames = ['state'];
		var stateDefaults = [''];
		var stateChangeHandlers = [''];
		$(document).ready(function(){
			initCountry();
		        document.getElementById("state").value=stateDefaults;
		});	
	</script>
</s:else>

		<script type="text/javascript">
            $(function() {		
                //以下为页面初始化时执行的代码   


               if ($("#status_hid").val() == '') {
                   $("#status_hid").val('ACTIVE');
               } 
               if ($("#notes_sel").val() == '0') {
                   $("#note_btn").val("Add");
               } else {
                   $("#note_btn").val("View/Edit");
               } 
               var addrType = "${division.addrType}";
               for (var i=0; i<4; i++) {
                 if (addrType[i*2] == 'Y') {
                   $("#addrType-" + (i+1)).get(0).checked = true;
                 }
               }  
               //end of 初始化

               //valide form content
			   $('#org_form').validate({
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
						 "division.name": { required:true}
					 },
					 messages: {
						 "division.name": { required : "Please enter the name !" }
					 },
					 errorPlacement: function(error, element) {
					 }
				});

                //bind save button event
                $("#save_btn").click (function() {
                   var formStr = $('#org_form').serialize();
                   var formExtStr = $('#org_ext_form').serialize();
                   if (! $('#org_form').valid()) {
                      return false;
                   }     
                   var addrType = "&division.addrType=";
                   for (var i=1; i<=4; i++) {
	                   if ($("#addrType-" + i).get(0).checked) {
	                       addrType += "Y;";
	                   } else {
	                       addrType += "N;";
	                   }                                          
                   }     
                   formStr += addrType;
                   var mystate = "division.state=" + $("#state").val();
                   formStr += "&" + mystate;
                   if($('#billingFrame').attr('src') == undefined || $('#billingFrame').attr('src') == '') {
                     //页面没有加载不作任何处理
                   } else {
                      //页面加载且发生了修改
                     if (window.frames["billingFrame"].$("#changed_hid").val() == 'Y') {
                        var billInfo = window.frames["billingFrame"].$("#billing_form").serialize();
                        formStr += "&" + billInfo;
                     }
                   }
                   $('#save_btn').attr("disabled", true);
                   $.ajax({
						type: "POST",
						url: "division!save.action",
						data: formStr + "&" + formExtStr,
						dataType: 'json',
						success: function(data, textStatus){
							if(hasException(data)){//有错误信息.
				 	           $('#save_btn').attr("disabled", false);				
							}else{                              
							  alert(data.message);
                             // window.location='organization!edit.action?id=${division.orgId}';
							}
						},
						error: function(xhr, textStatus){
						   alert("failure");
				 	       $('#save_btn').attr("disabled", false);
						   return;
						}
					});
                
                });//end of {$("#save_btn").click};            
                
                $("#notes_sel").change(function() {
                   if ($("#notes_sel").val() == '0') {
                      $("#note_btn").val("Add");
                   } else {
                      $("#note_btn").val("View/Edit");
                   }                
                });
               
            	$('#note_dlg').dialog({
					autoOpen: false,
					height: 260,
					width: 680,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
				
                $("#note_btn").click( function() {
                    var noteId = $("#notes_sel").val();
                    var url = "division!showInstruction.action?id=${division.divisionId}&sessNoteId=" + noteId + "&sessCustNo=" + $("#sessCustNo").val();
					$('#note_dlg').dialog("option", "open", function() {	
	              		 var htmlStr = '<iframe src="' + url + '" height="100%" width="100%" scrolling="no" style="border:0px" frameborder="0"></iframe>';
				         $('#note_dlg').html(htmlStr);
					});
					$('#note_dlg').dialog('open');
                });
                
               //修改status
               $("#status_btn").click( function() {
                    var noteId = $("#notes_sel").val();
                    var url = "organization!showUpdateStatus.action?srcStatus=" + $("#status_hid").val();
					$('#note_dlg').dialog("option", "open", function() {	
	              		 var htmlStr = '<iframe src="' + url + '" height="100%" width="100%" scrolling="no" style="border:0px" frameborder="0"></iframe>';
				         $('#note_dlg').html(htmlStr);
					});
					$('#note_dlg').dialog('option', 'title', "Update Status");      
					$('#note_dlg').dialog('open');                
                
                }); 
                
                $("#new_department_btn").click( function() {
                    if ('${division.divisionId}' == '') {
                       alert("Please save the division first !");
                       return;
                    }
                    var url = "department!add.action?divisionId=" + '${division.divisionId}&orgId=${division.orgId}';
                    $('#note_dlg').dialog("option", "title", "Add New Department");
					$('#note_dlg').dialog("option", "open", function() {	
	              		 var htmlStr = '<iframe src="' + url + '" height="100%" width="100%" scrolling="no" style="border:0px" frameborder="0"></iframe>';
				         $('#note_dlg').html(htmlStr);
					});
					$('#note_dlg').dialog('open');
                });
                             
                $('#tabTabdhtmlgoodies_tabView1_1').click( function () {
						if($('#billingFrame').attr('src') == undefined || $('#billingFrame').attr('src') == '')
						{
							$('#billingFrame').attr('src', "division!showBilling.action?id=${division.divisionId}");
						}
				});            
            });
        </script>
	</head>
	<body class="content">
		<div class="scm">
			<div class="title_content">
				<div class="title">
					Division Information
				</div>
			</div>
			<div class="input_box">

					<form method="get" id="org_form" class="niceform">
						<input type="hidden" name="division.orgId"
							value="${division.orgId}" />
						<input type="hidden" name="division.divisionId"
							value="${division.divisionId}" />
						<input type="hidden" name="sessCustNo" id="sessCustNo"
							value="${sessCustNo}" />
						<table border="0" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<th width="142">
									Division Name
								</th>
								<td colspan="2">
									<input name="division.name" value="${division.name}"
										type="text" class="NFText" size="35" />
								</td>
								<th width="200">
									Description
								</th>
								<td>
									<input name="division.description"
										value="${division.description}" type="text" class="NFText"
										size="35" />
								</td>
							</tr>
							<tr>
								<th valign="top">
									Supervisor
								</th>
								<td colspan="2">
									<input name="division.supervisor"
										value="${division.supervisor}" type="text" class="NFText"
										size="35" />
								</td>
								<th>
									Language
								</th>
								<td>
									<s:select list="#request.languageList" listKey="langCode"
										listValue="name" value="division.langCode"
										name="division.langCode" cssStyle="width:207px;"></s:select>
								</td>
							</tr>
							<tr>
								<th>
									Status
								</th>
								<td>
								  <c:set value="INACTIVE" var="flagStatus"></c:set>
								  <c:if test="${division.activeFlag == 'Y'}">
								    <c:set value="ACTIVE" var="flagStatus"></c:set>
								  </c:if>
								  <input type="text" readonly="readonly" value="${flagStatus}"  class="NFText" size="35" id="status_txt" />
								  <input type="hidden" name="division.activeFlag" value="${division.activeFlag}" id="status_hid" />
								  <input type="hidden" name="division.updateStatusReason" value="${division.updateStatusReason}" id="reason_hid" />
								</td>
								<td>
									<input name="Submit3"
											type="button" class="style_botton" value="Modify" id="status_btn" />									
								</td>
							</tr>
							<tr>
								<th valign="top">
									Instructions &amp; Notes
								</th>
								<td width="200">
									<select name="select40" id="notes_sel" style="width: 207px;">
										<option value="0">
											Add Instructions or Notes
										</option>
										<s:if test="noteList != null && noteList.size > 0">
											<s:iterator value="noteList" id="oneNote">
												<option selected="selected" value="${oneNote.id}">
											<%--${oneNote.type}#${oneNote.id}--%> ${oneNote.type}-instr-div

												</option>
											</s:iterator>
										</s:if>
									</select>
								</td>
								<td>
									<input type="button" id="note_btn" class="style_botton"
										value="Add" />
								</td>
								<th>
									&nbsp;
								</th>
								<td colspan="2">
									&nbsp;
								</td>
							</tr>


						</table>
					</form>

			</div>
			<div id="note_dlg" title="Instructions & Notes"></div>
			<div id="dhtmlgoodies_tabView1">
				<div class="dhtmlgoodies_aTab">
					<iframe id="deptIframe"
						src="department!list.action?divisionId=${division.divisionId}"
						width="100%" height="360" frameborder="0" scrolling="no"></iframe>
					<div style="text-align: center;">
						<input name="Submit4" type="button" class="style_botton"
							value="New" id="new_department_btn" />
					</div>
				</div>
				<div class="dhtmlgoodies_aTab">
					<iframe id="billingFrame" name="billingFrame"
						src="" width="100%" height="360" frameborder="0" scrolling="auto"></iframe>
				</div>
				<div class="dhtmlgoodies_aTab">
					<form method="get" id="org_ext_form" class="niceform">
						<table border="0" cellpadding="0" cellspacing="0"
							class="General_table">
							<tr>
								<th width="138">
									Phone
								</th>
								<td style="margin: 0px; padding: 0px;">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
												<input name="division.phone"
													value="${division.phone}" type="text" class="NFText"
													size="16" />
											</td>
											<th width="26">
												Ext
											</th>
											<td>
												<input name="division.phoneExt"
													value="${division.phoneExt}" type="text" class="NFText"
													size="5" />
											</td>
										</tr>
									</table>
								</td>
								<th width="138">
									Alternative Phone
								</th>
								<td style="margin: 0px; padding: 0px;">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
												<input name="division.altPhone"
													value="${division.altPhone}" type="text" class="NFText"
													size="16" />
											</td>
											<th width="26">
												Ext
											</th>
											<td>
												<input name="division.altPhoneExt"
													value="${division.altPhoneExt}" type="text"
													class="NFText" size="5" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<th valign="top">
									Fax
								</th>
								<td style="margin: 0px; padding: 0px;">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
												<input name="division.fax" value="${division.fax}"
													type="text" class="NFText" size="16" />
											</td>
											<th width="26">
												Ext
											</th>
											<td>
												<input name="division.faxExt"
													value="${division.faxExt}" type="text" class="NFText"
													size="5" />
											</td>
										</tr>
									</table>
								</td>
								<th>
									&nbsp;
								</th>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<th valign="top">
									Address
								</th>
								<td width="385">
									<input name="division.addrLine1"
										value="${division.addrLine1}" type="text" class="NFText"
										size="35" />
								</td>
								<th>
									&nbsp;
								</th>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<th valign="top">
									&nbsp;
								</th>
								<td>
									<input name="division.addrLine2"
										value="${division.addrLine2}" type="text" class="NFText"
										size="35" />
								</td>
								<th>
									&nbsp;
								</th>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<th valign="top">
									&nbsp;
								</th>
								<td>
									<input name="division.addrLine3"
										value="${division.addrLine3}" type="text" class="NFText"
										size="35" />
								</td>
								<th>
									&nbsp;
								</th>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<th valign="top">
									City
								</th>
								<td>
									<input name="division.city" value="${division.city}"
										type="text" class="NFText" size="35" />
								</td>
								<th>
									Zip Code
								</th>
								<td>
									<input name="division.zipCode"
										value="${division.zipCode}" type="text" class="NFText"
										size="35" />
								</td>
							</tr>
							<tr>
								<th valign="top">
									State
								</th>
								<td>
									<select name="state" id="state"></select>
								</td>
								<th>
									Country
								</th>
								<td>
									<select name="division.country" id="country">
									</select>
								</td>
							</tr>
							<tr>
								<th valign="top">
									&nbsp;
								</th>
								<td>
									<input name="addrType-1" type="checkbox" id="addrType-1" value="Y" />
									Ship Address
								</td>
								<th>
									&nbsp;
								</th>
								<td>
									<input name="addrType-2" type="checkbox" id="addrType-2" value="Y" />
									Invoice Address
								</td>
							</tr>
							<tr>
								<th valign="top">
									&nbsp;
								</th>
								<td>
									<input name="addrType-3" type="checkbox" id="addrType-3" value="Y" />
									Pay-Form Address
								</td>
								<th>
									&nbsp;
								</th>
								<td>
									<input name="addrType-4" type="checkbox" id="addrType-4" value="Y" />
									Remit-To Address
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="button_box">
				<saveButton:saveBtn parameter="${operation_method}"
					disabledBtn='<input type="button" name="Submit123" value="Save" class="search_input" disabled="disabled" />'
					saveBtn='<input type="button" name="Submit123" value="Save" class="search_input" id="save_btn" />' />
				<input type="button" name="Submit124" value="Cancel"
					class="search_input"
					onclick="window.location='organization!edit.action?id=${division.orgId}';" />
			</div>
		</div>
		<div id="sel_res_dlg" title="Select Resource"></div>
		<script type="text/javascript"> 
	      initTabs('dhtmlgoodies_tabView1',Array('Departments','Billing Info','Location'),0,998,380);
		  disableTabByTitle('Purchase Preferences');
		  $('#tabTabdhtmlgoodies_tabView1_1')
			.click( function () {
				if($('#divIframe').attr('src') == undefined || $('#pubIframe').attr('src') == '')
				{
					$('#divIframe').attr('src', "contact_pub!index.action?sessContactNo=${sessContactNo}");
				}
		  });
          var isSaved = false;
		  window.onbeforeunload = function() {
			 if(isSaved === false){
					 return 'Do you want to leave without saving data?';
			 }
		  }
        </script>
	</body>
</html>