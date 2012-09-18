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
			function check() {
				alert($("#flag").attr("checked"))
				if($("#flag").attr("checked")==true) {
			  		if (!confirm('Are you sure to set the route as default?')) {
			  			$("#flag").attr("checked",false)
					}
				}
			}
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
			   $('#route_form').validate({
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
						 "route.name": { required:true}
					 },
					 messages: {
						 "route.name": { required : "Please enter the name !" }
					 },
					 errorPlacement: function(error, element) {
					 }
				});
				
				//绑定保存按钮事件.            
                $("#save_btn").click (function() {
                   if ($('#route_form').valid()  === false ) {
                      return false;
                   }
                   var formStr = $('#route_form').serialize();
                   if (! $("#flag").get(0).checked) {
                       formStr += "&route.defaultFlag=N";
                   }
                   if (! $("#rollup").get(0).checked) {
                       formStr += "&route.costRollupFlag=N";
                   }
                   $('#save_btn').attr("disabled", true);
                   $.ajax({
						type: "POST",
						url: "route!save.action",
						data: formStr,
						dataType: 'json',
						success: function(data, textStatus){
							if(hasException(data)){//有错误信息.
				 	           $('#save_btn').attr("disabled", false);				
							}else{                              
							  alert(data.message);
							  parent.$('#new_route_dlg').dialog('close');
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
        <style type="text/css">
          #route_form th, #route_form td {
              height:30px;
              line-height: 30px;
          }
        </style>
	</head>
	<body style="overflow: hidden" id="input_body">
		<form method="get" id="route_form">
			<table border="0" cellpadding="0" cellspacing="0"
				class="General_table">
				<tr>
					<th>
						Routing Name
					</th>
					<td>
						<input name="route.name" type="text" class="NFText" size="40" />
					</td>
					<th>
						Status
					</th>
					<td>
						<s:select list="{'ACTIVE', 'INACTIVE'}" value="route.status"
							name="route.status"></s:select>
					</td>
				</tr>
				<tr>
					<th valign="top">
						Description
					</th>
					<td>
						<input name="route.description" type="text" class="NFText"
							size="40" />
					</td>
					<th>
						Storage Warehouse
					</th>
					<td>
						<s:select id="warehouse_sel" name="route.warehouseId"
							list="warehouseList" listKey="warehouseId" listValue="name"
							value="route.warehouseId"></s:select>
					</td>
				</tr>
				<tr>
					<th>
						Product/Service
					</th>
					<td>
									<s:select id="class_sel" name="route.production"
										list="classList" listKey="value" listValue="type"
										value="route.production" cssStyle="width:222px;"></s:select>
					</td>
					<th>
						
					</th>
					<td>
				      <input type="checkbox" id="flag" name="route.defaultFlag" value="Y" <s:if test='route.defaultFlag!=null&&route.defaultFlag.equals("Y")'>checked</s:if> onclick="check();"/><label for="flag">Set as Default</label>
					</td>
				</tr>
				<tr>
					<th>
						Comment
					</th>
					<td>
						<textarea name="route.comment" class="content_textarea2" style="width:215px;" cols="50" rows="4"></textarea>
					</td>
					<th>
						
					</th>
					<td>
                         <input type="checkbox" id="rollup" name="route.costRollupFlag" <s:if test="route.costRollupFlag!=null&&route.costRollupFlag.equals('Y')">checked</s:if> value="Y" disabled="disabled"/><label for="rollup">Cost Rollup</label>
					</td>
				</tr>
					<tr>

				  <th valign="top">Modified Date</th>
				  <td>
				  	<s:textfield name="route.modifyDate" Class="NFText" size="20"  readonly="true">
			         <s:param name='value'>
						<s:date name="route.modifyDate" format="yyyy-MM-dd"/>
			         </s:param>
			        </s:textfield>
				  </td>
				  <th>Modified By</th>
				  <td valign="top">
				  <s:textfield name="route.modifyUser" Class="NFText" size="25"  readonly="true"></s:textfield>
			      <s:hidden name="route.modifiedBy"></s:hidden>
				  </td>
				  </tr>
			</table>


			<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<div class="botton_box">
							<input name="Submit" id="save_btn" type="button"
								class="style_botton" value="Save" />
							<input type="button" name="Submit2" value="Cancel"
								class="style_botton"
								onclick="parent.$('#new_route_dlg').dialog('close');" />
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>