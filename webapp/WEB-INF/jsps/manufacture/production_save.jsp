<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Quotation Detail</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}tab-view.css" rel="stylesheet"
	type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript">
	var baseUrl = "${global_url}";
</script>
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
<script src="${global_js_url}jquery/jquery.form.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js"
	type="text/javascript"></script>
<script type="text/javascript" src="${global_js_url}show_tag.js"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js"
	type="text/javascript"></script>
<script src="${global_js_url}scm/gs.util.js" type="text/javascript"></script>
<script type="text/javascript"
	src="${global_js_url}report/reportCommon.js"></script>
<style type="text/css">
<!--
.content .scm #dhtmlgoodies_tabView1 .dhtmlgoodies_aTab .General_table fieldset
	{
	margin: 4px;
}

.disp {
	display: none;
	margin-left: 40px;
}
-->
</style>
<script type="text/javascript">
	
			$(function() {
				$("select").each(function(){
					var changeWidth=false;
			   		var len = this.offsetWidth;
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
			   	});		
			    //绑定保存按钮事件.
			    $("#save_btn").click(function() {
			        var formStr = $('#div_form').serialize();
			        $('#save_btn').attr("disabled", true);
			        $.ajax({
			            type: "POST",
			            url: "manufacture/production_status_mapping!save.action",
			            data: formStr,
			            dataType: 'json',
			            success: function(data, textStatus) {
			                if (hasException(data)) {//有错误信息.
			                    $('#save_btn').attr("disabled", false);
			                } else {
			                    alert(data.message);
			                    $('#save_btn').attr("disabled", false);
			                }
			            },
			            error: function(xhr, textStatus) {
			                alert("failure");
			                $('#save_btn').attr("disabled", false);
			            }
			        });
			    });//end of {$("#save_btn").click};
			});
            function getProduction(){
        		var warehouse_sel = document.getElementById('warehouse_sel').value;
        		$ ("#production_sel option").remove();
        		 $.ajax({
						type: "POST",
						url: "manufacture/production_status_mapping!selProduction.action?workCenterId="+warehouse_sel,
						dataType: 'json',
						success: function(msg){
								for(var i=0;i<msg.list.length;i++){
										var line = msg.list[i];
										$ ("#production_sel").append ("<option value='"+line+"'>" + line + "</option>");
								}
						},
						error: function(msg){							
							alert("Please contact system administrator for help.");							
						}
					});
            	
         }
        </script>
</head>
<body class="content">
	<div class="scm">
		<div class="title_content">
			<div class="title">Map Production Status</div>
		</div>
	</div>
	<form id="div_form" method="get">

		<table border="0" cellpadding="0" cellspacing="0"
			class="General_table">
			<s:hidden name="productionStatusMapping.id"></s:hidden>
			<tr>
				<th width="186" valign="top">Work Center</th>
				<td>	
				<s:if test="workCenterList!=null&&workCenterList.size()>0">
						<s:select id="warehouse_sel" name="productionStatusMapping.workCenterId"
							list="workCenterList" headerKey="" headerValue="All" listKey="id"
							listValue="name" style="width: 257px;" onChange="getProduction()"></s:select>
						</s:if> <s:else>		
						<select id="warehouse_sel" name="productionStatusMapping.workCenterId"  style="width: 257px;">
							<option>All</option>
						</select>
						</s:else>
				</td>
				<th>&nbsp;</th>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>Production Status</th>
				<td>
						<select id='production_sel' name='production_status' style='width: 257px'>
							<option value="${productionStatus}">${productionStatus}</option>
						</select>
								
				</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>Customized Production Status</th>
				<td>
					<s:textfield name="productionStatusMapping.customProductionStatus" Class="NFText" size="45" style="width: 257px;"></s:textfield>
				</td>
				<th>&nbsp;</th>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>Status</th>
				<td>
					<s:select list="{'ACTIVE','Inactive'}" value="productionStatusMapping.status" style="width: 257px;" name="productionStatusMapping.status"></s:select>
				</td>
				<th width="99">&nbsp;</th>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>Description</th>
				<td><s:textarea name="productionStatusMapping.description" Class="content_textarea2" style="width: 400px;"></s:textarea>
				</td>
				<td>&nbsp;</td>
				<td align="left">&nbsp;</td>
			</tr>

			<tr>
				<th>Modified Date</th>
				<td width="347">
					<s:textfield name="productionStatusMapping.modifyDate" Class="NFText" size="20" style="width: 257px;" readonly="true">
                           <s:param name='value'>
                                    <s:date name="productionStatusMapping.modifyDate" format="yyyy-MM-dd" />
                           </s:param>
                    </s:textfield>
				</td>
				<th>Modified By</th>
				<td>
					<s:textfield name="productionStatusMapping.modifiedBy" Class="NFText" size="25" readonly="true"></s:textfield>
				</td>
			</tr>
			
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td>
                            <div class="botton_box">
                                <input name="Submit" id="save_btn" type="button"
                                       class="search_input" value="Save"/>
                                <input type="button" name="Submit2"
									   value="Cancel" class="search_input" onclick="window.history.go(-1);" />                   
                            </div>
                        </td>
                    </tr>
                </table>
	</form>

</body>
</html>