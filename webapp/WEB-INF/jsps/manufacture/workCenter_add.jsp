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
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
		<script src="${global_js_url}jquery/jquery.validate.js?v=1"
			type="text/javascript"></script>
		<script type="text/javascript">
	
            $(function() {
            	$("select").each(function(){
         			var changeWidth=false;
         	   		var len = this.offsetWidth;
         	   		if(len!=0) {
        	 	   		this.style.width = 'auto';
        	 	   		if(len<this.offsetWidth) {
        	 	   			changeWidth = true;
        	 	   		}
        	 	   		this.style.width=len+"px";
        	 	   		$(this).mousedown(function(){
        	 	   			if(changeWidth) {
        	 	   				this.style.width = 'auto';
        	 	   			}
        	 	   			});
        	 	   		$(this).blur(function() {this.style.width = len+"px";});
        	 	   		$(this).change(function(){this.style.width = len+"px";});
         	   		}
         	   		
         	   	});
            	
               //验证form的逻辑
			   $('#workCenter_form').validate({
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
						 "workCenter.name": { required:true}
					 },
					 messages: {
						 "workCenter.name": { required : "Please enter the name !" }
					 },
					 errorPlacement: function(error, element) {
					 }
				});
				
				//绑定保存按钮事件.            
                $("#save_btn").click (function() {
                   if ($('#workCenter_form').valid()  === false ) {
                      return false;
                   }
                   var formStr = $('#workCenter_form').serialize();
                   if (! $("#flag").get(0).checked) {
                       formStr += "&workCenter.defaultFlag=N";
                   }
                   $('#save_btn').attr("disabled", true);
                   $.ajax({
						type: "POST",
						url: "work_center!save.action",
						data: formStr,
						dataType: 'json',
						success: function(data, textStatus){
							if(hasException(data)){//有错误信息.
				 	           $('#save_btn').attr("disabled", false);				
							}else{                              
							  alert(data.message);
							  parent.$('#new_workCenter_dlg').dialog('close');
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
		<form method="get" id="workCenter_form">
			<table border="0" cellpadding="0" cellspacing="0"
				class="General_table">
				<tr>
					<th>
						Name
					</th>
					<td>
						<input name="workCenter.name" type="text" class="NFText" size="40" />
					</td>
					<th>
						Status
					</th>
					<td>
						<s:select list="{'ACTIVE', 'INACTIVE'}" value="workCenter.status"
							name="workCenter.status"></s:select>
					</td>
				</tr>
				<tr>
					<th valign="top">
						Description
					</th>
					<td>
						<input name="workCenter.description" type="text" class="NFText"
							size="40" />
					</td>
					<th>
						
					</th>
					<td>
					   <input type="checkbox" id="flag" name="workCenter.defaultFlag" value="Y" /><label for="flag">Set as Default</label> 
					</td>
				</tr>
				<tr>
					<th>
						Supervisor
					</th>
					<td>
					<input name="workCenter.superName" id="superName" type="text" class="NFText" size="25" readonly="readonly"/>
					<s:hidden name="workCenter.supervisor" id="supervisor"></s:hidden>
					<a href="#" onclick="userSelect()"><img id="org_1Trigger" src="images/search.gif" width="16" height="16" align="absmiddle" /></a>
					</td>
					<th>
						Storage Warehouse
					</th>
					<td>
						<s:select id="warehouse_sel" name="workCenter.warehouseId"
							list="warehouseList" listKey="warehouseId" listValue="name"
							value="workCenter.warehouseId"></s:select>
					</td>
				</tr>
				<tr>
					<th>
						Comment
					</th>
					<td colspan="3">
						<textarea name="workCenter.comment" class="content_textarea"></textarea>
					</td>
				</tr>
				<tr>

				  <th valign="top">Modified Date</th>
				  <td>
				  	<s:textfield name="workCenter.modifyDate" Class="NFText" size="20"  readonly="true">
			         <s:param name='value'>
						<s:date name="workCenter.modifyDate" format="yyyy-MM-dd"/>
			         </s:param>
			        </s:textfield>
				  </td>
				  <th>Modified By</th>
				  <td valign="top">
				  <s:textfield name="workCenter.modifyUser" Class="NFText" size="25"  readonly="true"></s:textfield>
			      <s:hidden name="workCenter.modifiedBy"></s:hidden>
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
								onclick="parent.$('#new_workCenter_dlg').dialog('close');" />
						</div>
					</td>
				</tr>
			</table>
			<s:hidden name="sessWorkCenterId"></s:hidden>
		</form>
	</body>
</html>