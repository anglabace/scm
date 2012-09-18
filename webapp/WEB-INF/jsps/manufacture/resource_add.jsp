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
       <script src="${global_js_url}jquery/jquery.validate.js?v=1" type="text/javascript"></script>
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
               //新增时默认为USD.
               $("#resource_currency").val("CNY");

               //验证form的逻辑
			   $('#resource_form').validate({
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
						 "resource.resourceNo": { required:true},
						 "resource.name": { required:true}
					 },
					 messages: {
						 "resource.resourceNo": { required : "Please enter the resource no !" },
						 "resource.name": { required : "Please enter the resource name !" }
					 },
					 errorPlacement: function(error, element) {
					 }
				});
				
				//绑定保存按钮事件.            
                $("#save_btn").click (function() {
                   if ($('#resource_form').valid()  === false ) {
                      return false;
                   }
                   var formStr = $('#resource_form').serialize();
                   $('#save_btn').attr("disabled", true);
                   $.ajax({
						type: "POST",
						url: "resource!save.action",
						data: formStr,
						dataType: 'json',
						success: function(data, textStatus){
							if(hasException(data)){//有错误信息.
				 	           $('#save_btn').attr("disabled", false);				
							}else{                              
							  alert(data.message);
							  parent.$('#new_resource_dlg').dialog('close');
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
		<form method="get" id="resource_form">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="General_table" id="edit_tbl">
				<tr>
					<th>
						Resource No
					</th>
					<td>
						<input name="resource.resourceNo" id="res_resourceNo" type="text" class="NFText"
							value="${resource.resourceNo}" size="20" />
					</td>
					<th>
						Status
					</th>
					<td>
						<s:select list="{'ACTIVE', 'INACTIVE'}" value="resource.status"
							name="resource.status"></s:select>
					</td>
				</tr>
				<tr>
					<th>
						Resource Name
					</th>
					<td>
						<input name="resource.name" id="res_name" type="text" class="NFText"
							value="${resource.name}" size="40" />
					</td>
					<th>
						Resource Group
					</th>
					<td>
						<s:select id="resource_group" name="resource.group"
							list="dropDownMap['RESOURCE_GROUP']" listKey="value"
							listValue="text" value="resource.group"></s:select>
					</td>
				</tr>
				<tr>
					<th valign="top">
						Description
					</th>
					<td>
						<input name="resource.description" type="text" class="NFText"
							size="40" value="${resource.description}" />
					</td>
					<th>
					<s:if test="workGroupShow==true">
						Work Group
					</s:if>
					<s:else>
					User
					</s:else>	
					</th>
					<td>
					<s:if test="workGroupShow==true">
						<s:hidden name="operationId" id="operationId"/>
						<s:if test="workGroupList!=null&&workGroupList.size>0">
							<s:select name="resource.workGroupId" id="workGroupId" list="workGroupList" listKey="id" listValue="name">
							</s:select>
						</s:if>
						<s:else>
							<select name="resource.workGroupId" id="workGroupId">
							</select>
						</s:else>
					
					</s:if>
					<s:else>
					<s:select list="userDeptList" name="resource.userDept" listKey="id" listValue="name" headerKey="0" headerValue="All"></s:select>
					</s:else>
					</td>
				</tr>
				<tr>
					<th valign="top">
						Cost
					</th>
					<td>
						<input name="resource.cost" type="text" class="NFText2"
							value="${resource.cost}" size="20" />
					</td>
					<th>
						Cost Basis
					</th>
					<td>
						<s:select id="resource_costBasis" name="resource.costBasis"
							list="dropDownMap['RESOURCE_COST_BASIS']" listKey="value"
							listValue="text" value="resource.costBasis"></s:select>
					</td>
				</tr>
				<tr>
					<th>
						Currency
					</th>
					<td>
						<s:select id="resource_currency" list="currencyList" listKey="currencyCode"
							listValue="currencyCode" value="resource.currency"
							name="resource.currency"></s:select>
					</td>
					<th>
						UOM
					</th>
					<td>
						<s:select id="resource_uom" name="resource.uom"
							list="dropDownMap['RESOURCE_UOM']" listKey="value"
							listValue="text" value="resource.uom"></s:select>
					</td>
				</tr>
				<tr>
					<th>
						Modified Date
					</th>
					<td>
						<input name="textfield322" type="text" class="NFText"
							value="<s:date name="resource.modifyDate" format="yyyy-MM-dd"/>"
							size="20" readonly="readonly" />
					</td>
					<th>
						Modified By
					</th>
					<td>
						<input name="textfield332" type="text" class="NFText"
							value="${resource.modifyUser}" readonly="readonly" size="20" />
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
								onclick="parent.$('#new_resource_dlg').dialog('close');" />
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>