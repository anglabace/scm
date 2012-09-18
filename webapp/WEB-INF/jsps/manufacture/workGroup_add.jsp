<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Work Group</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" language="javascript"
			src="${global_js_url}jquery/jquery.js"></script>
	    <script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.dialog.all.js"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
		<script src="${global_js_url}jquery/jquery.validate.js?v=1"
			type="text/javascript"></script>
		<script type="text/javascript">
	
            $(function() {

               //验证form的逻辑
			   $('#workGroup_form').validate({
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
						 "workGroup.name": { required:true}
					 },
					 messages: {
						 "workGroup.name": { required : "Please enter the name !" }
					 },
					 errorPlacement: function(error, element) {
					 }
				});
				
				//绑定保存按钮事件.            
                $("#save_btn").click (function() {
                   if ($('#workGroup_form').valid()  === false ) {
                      return false;
                   }
                   var formStr = $('#workGroup_form').serialize();
                   $('#save_btn').attr("disabled", true);
                   $.ajax({
						type: "POST",
						url: "work_group!save.action",
						data: formStr,
						dataType: 'json',
						success: function(data, textStatus){
							if(hasException(data)){//有错误信息.
				 	           $('#save_btn').attr("disabled", false);				
							}else{                              
							  alert(data.message);
							  parent.$('#new_workGroup_dlg').dialog('close');
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
          //choice user
            function userSelect() {
            	parent.$('#userChoiceDialog').dialog("option", "open", function() {	
             		 var htmlStr = '<iframe src="work_group!selectUser.action" id="selectUserFrame"  height="100%" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
             		parent.$('#userChoiceDialog').html(htmlStr);
            	});
            	parent.$('#userChoiceDialog').dialog('open');
            }
        </script>
	</head>
	<body style="overflow: hidden" id="input_body">
		<form method="get" id="workGroup_form">
			<table border="0" cellpadding="0" cellspacing="0"
				class="General_table">
				<tr>
					<th>
						Work Group Name
					</th>
					<td>
						<input name="workGroup.name" type="text" class="NFText" size="40" />
					</td>
					<th>
						Status
					</th>
					<td>
						<s:select list="{'ACTIVE', 'INACTIVE'}" value="workGroup.status"
							name="workGroup.status"></s:select>
					</td>
				</tr>
				<tr>
					<th valign="top">
						Description
					</th>
					<td>
						<input name="workGroup.description" type="text" class="NFText" size="40" />
					</td>
					<th>
						Supervisor
					</th>
					<td>
					<input name="workGroup.superName" id="superName" type="text" class="NFText" size="25" readonly="readonly"/>
					<s:hidden name="workGroup.supervisor" id="supervisor"></s:hidden>
					<a href="#" onclick="userSelect()"><img id="org_1Trigger" src="images/search.gif" width="16" height="16" align="absmiddle" /></a>
					</td>
				</tr>
				<tr>
					<th>
						Comment
					</th>
					<td colspan="3">
						<textarea name="workGroup.comment" class="content_textarea"></textarea>
					</td>
				</tr>
				<tr>
					<th>
						Modified Date
					</th>
					<td>
						<input name="textfield3224" type="text" class="NFText" size="20" readonly="readonly" />
					</td>
					<th>
						Modified By
					</th>
					<td>
						<input name="textfield3322" type="text" class="NFText"
							readonly="readonly" size="20" />
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
								onclick="parent.$('#new_workGroup_dlg').dialog('close');" />
						</div>
					</td>
				</tr>
			</table>
		</form>
              	<div id="userChoiceDialog" title="Select Supervisor"></div>
	</body>
</html>