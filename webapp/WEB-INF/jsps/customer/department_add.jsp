<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Production Resources</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" language="javascript"
			src="${global_js_url}jquery/jquery.js"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
		<script src="${global_js_url}jquery/jquery.validate.js?v=1"
			type="text/javascript"></script>
		<script type="text/javascript">
	
            $(function() {

               //验证form的逻辑
			   $('#div_form').validate({
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
						 "department.name": { required:true}
					 },
					 messages: {
						 "department.name": { required : "Please enter the department name !" }
					 },
					 errorPlacement: function(error, element) {
					 }
				});
				
				//绑定保存按钮事件.            
                $("#save_btn").click (function() {
                   if ($('#div_form').valid()  === false ) {
                      return false;
                   }
                   var formStr = $('#div_form').serialize();
                   $('#save_btn').attr("disabled", true);
                   $.ajax({
						type: "POST",
						url: "department!save.action",
						data: formStr,
						dataType: 'json',
						success: function(data, textStatus){
							if(hasException(data)){//有错误信息.
				 	           $('#save_btn').attr("disabled", false);				
							}else{                              
							  alert(data.message);
							  parent.$('#new_div_dlg').dialog('close');
							  parent.location = parent.location;
							}
						},
						error: function(xhr, textStatus){
						   alert("failure");
				 	       $('#save_btn').attr("disabled", false);
						}
					});                
                });//end of {$("#save_btn").click};               
            
            });
        </script>
	</head>
	<body style="overflow: hidden" id="input_body">
		<form method="get" id="div_form">
		    <input type="hidden" name="department.orgId" value="${param.orgId}" />
		    <input type="hidden" name="department.divisionId" value="${param.divisionId}" />
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="General_table" id="edit_tbl">
				<tr>
					<th width="142">
						Department Name
					</th>
					<td width="389">
						<input name="department.name" value="${department.name}" type="text" class="NFText" size="35" />
					</td>
				</tr>
				<tr>
					<th valign="top">
						Supervisor
					</th>
					<td>
						<input name="department.supervisor" value="${department.supervisor}" type="text" class="NFText" size="35" />
					</td>
				</tr>
				<tr>
					<th valign="top">
						Department Function
					</th>
					<td>
						<s:select list="#request.functionList" listKey="functionId"
							listValue="name" name="department.deptFuncId" cssStyle="width:207px;"></s:select>
					</td>
				</tr>
				<tr>
					<th valign="top">
						Status
					</th>
					<td>
						<select name="department.activeFlag" style="width: 207px;">
							<option value="Y">
								ACTIVE
							</option>
							<option value="N">
								INACTIVE
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<th valign="top">
						Description
					</th>
					<td>
						<span style="display: block"> <textarea name="department.description"
								class="content_textarea"></textarea> </span>
					</td>
				</tr>
				<tr>
					<th valign="top">
						&nbsp;
					</th>
					<td>
						&nbsp;
					</td>
				</tr>
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<div class="botton_box">
							<input name="Submit" id="save_btn" type="button"
								class="style_botton" value="Save" />
							<input type="button" name="Submit2" value="Cancel"
								class="style_botton"
								onclick="parent.$('#note_dlg').dialog('close');" />
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>