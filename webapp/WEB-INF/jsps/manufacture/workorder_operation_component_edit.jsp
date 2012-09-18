<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Production Components</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="${global_js_url}jquery/jquery.js"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
		<script src="${global_js_url}jquery/jquery.validate.js?v=1"
			type="text/javascript"></script>
		<script type="text/javascript" src="${global_js_url}show_tag.js"></script>
		<script src="${global_js_url}jquery/ui/ui.datepicker.js"
			type="text/javascript"></script>
		<script type="text/javascript">
	        var baseUrl="${global_url}";	
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
                $('.ui-datepicker').each(function(){
					$(this).datepicker(
					{
					dateFormat: 'yy-mm-dd',
					changeMonth: true,
					changeYear: true
					});
				});
				
                 //绑定保存按钮事件.
                $("#save_btn").click (function() {
                    var formStr = $('#component_form').serialize();
                    $('#save_btn').attr("disabled", true);
                   $.ajax({
						type: "POST",
						url: "workorder_operation!saveComponent.action",
						data: formStr,
						dataType: 'json',
						success: function(data, textStatus){
							if(hasException(data)){//有错误信息.
				 	           $('#save_btn').attr("disabled", false);				
							}else{                              
							  //alert(data.message);
							  parent.$('#edit_com_dlg').dialog('close');
							  var url = parent.$("#component_list_frame").attr("src");
							  parent.frames["component_list_frame"].location = url;
							}
						},
						error: function(xhr, textStatus){
						   alert("failure");
				 	       $('#save_btn').attr("disabled", false);
						   return;
						}
					});
                
                });//end of {$("#save_btn").click};             
            
            });
        </script>
	</head>
	<body>
		<div>
			<form method="get" id="component_form" class="niceform">
				<div class="input_box">
					<div class="content_box">
						<input type="hidden" name="woComponent.id"
							value="${woComponent.id}" />
						<input type="hidden" name="sessWOPKey" id="sessWOPKey"
							value="${param.sessWOPKey}" />
						<input type="hidden" name="sessComKey" id="sessComKey"
							value="${param.sessComKey}" />




						<table border="0" cellpadding="5" cellspacing="0"
							class="General_table" id="edit_tbl">
							<tr>
								<th width="160">
									Sequence No
								</th>
								<td>
									<input readonly="readonly" id="res_componentNo" type="text"
										class="NFText" value="${woComponent.seqNo}"
										size="20" name="woComponent.seqNo" />
								</td>
								<th width="150">
									Storage Location
								</th>
								<td>
									<input id="loc_name" type="text" class="NFText"
										name="woComponent.storageLoc"
										value="${woComponent.storageLoc}" size="20" />
								</td>
							</tr>
							<tr>
								<th>
									Component Name
								</th>
								<td>
									<input id="res_name" type="text" class="NFText"
										name="woComponent.componentName"
										value="${woComponent.componentName}" size="20" />
								</td>
								<th>
									UOM
								</th>
								<td>
									<s:select id="component_uom" list="dropDownMap['RESOURCE_UOM']"
										listKey="value" listValue="text"
										value="woComponent.uom" name="woComponent.uom"></s:select>
								</td>
							</tr>
							<tr>
								<th valign="top">
									Description
								</th>
								<td>
									<input type="text" class="NFText" size="20"
										value="${woComponent.description}" name="woComponent.description" />
								</td>
								<th>
									Quantity Used
								</th>
								<td>
									<input name="woComponent.quantityUsed" type="text" class="NFText2"
										value="${woComponent.quantityUsed}" size="20" />									
								</td>
							</tr>
							<tr>
								<th valign="top">
									Per Item Quantity
								</th>
								<td>
									<input name="woComponent.perItemQuantity" type="text" class="NFText2"
										value="${woComponent.perItemQuantity}" size="20" />
								</td>
								<th>
									Currency
								</th>
								<td>
									<s:select list="#request.currencyList" listKey="currencyCode"
										listValue="currencyCode" name="woComponent.currencyCode" value="woComponent.currencyCode"></s:select>
								</td>
							</tr>

							<tr>
								<th valign="top">
									Cost
								</th>
								<td>
									<input type="text" class="NFText2" name="woComponent.cost"
										value="${woComponent.cost}" size="20" />
								</td>
								<th>
									Cost Basis
								</th>
								<td>
									<s:select id="component_costBasis"
										list="dropDownMap['RESOURCE_COST_BASIS']" listKey="value"
										listValue="text" name="woComponent.costBasis" value="woComponent.costBasis"></s:select>
								</td>
							</tr>
							<tr>
								<th>
									Modified Date
								</th>
								<td>
									<input name="textfield322" type="text" class="NFText"
										value="<s:date name="woComponent.modifyDate" format="yyyy-MM-dd"/>"
										size="20" readonly="readonly" />
								</td>
								<th>
									Modified By
								</th>
								<td>
									<input name="textfield332" type="text" class="NFText"
										value="${woComponent.modifyUser}" readonly="readonly"
										size="20" />
								</td>
							</tr>
						</table>
					</div>
				</div>
			</form>
			<div style="text-align: center; margin-top: 30px;">
				<input type="button" name="Submit123" value="Save"
					class="search_input" id="save_btn" />
				<input type="button" name="Submit124" value="Cancel"
					class="search_input"
					onclick="parent.$('#edit_com_dlg').dialog('close');" />
			</div>

		</div>
	</body>
</html>