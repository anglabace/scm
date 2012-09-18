<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Production Operations</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}tab-view.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
		<link href="${global_css_url}seqno.css" rel="stylesheet"
			type="text/css" />
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
		<script type="text/javascript">
		function edit(groupId) {
			   parent.window.location = "work_group!edit.action?id=" + groupId ;
			}			
         $(function() {
			    $("#resultTable tr:odd").find("td").addClass("list_td2");
			
			    //复选框绑定: 全选|全不选
			    $("#check_all").click( function() {
			       $(":checkbox").each(function() {
			          this.checked = $("#check_all").get(0).checked;
			       });      
			    });
			    
			    //删除选中的checkbox.
			    $("#check_del").click( function() {   
			        var param = "id=${param.id}"; 
			        //alert($("#resultTable :checkbox:checked").length);
			        if ($("#resultTable :checkbox:checked").length < 1) {
			           alert('Please select one at least!');
					   return;
			        }
			  		if (!confirm('Are you sure to delete?')) {
						return;
					}
					$("#resultTable :checkbox:checked").each(function() {
					   param += "&centerResId=" + $(this).val();
					   var tdObj = $(this).parent();
                    var trObj = tdObj.parent();  
                    if (tdObj.children(":hidden").length >0) {
					     $('<input type="hidden" name="dettachGroupIdList" value="' + $(this).val() + '" />').appendTo(parent.$("#workCenter_form"));
					   }
                    trObj.remove();
					});			
					$("#resultTable tr:even").find("td").removeClass("list_td2");
					$("#resultTable tr:odd").find("td").addClass("list_td2");
					  $.ajax({
							type: "POST",
							url: "work_center!deleteSelect.action",
							data: param,
							success: function(data){
								//parent.location = parent.location;
							},
							error: function(msg){
								alert("Delete Group  failed !");
				                $('#select_btn').attr("disabled", false);		
							}
						}); 			
			    });//end of $("#check_del").click. 
             $("#new_res_btn").click( function() {
					parent.$('#sel_res_dlg').dialog("option", "open", function() {	
	              		 var htmlStr = '<iframe src="work_group!selectForCenter.action?centerId=${param.id}" height="100%" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
	              		parent.$('#sel_res_dlg').html(htmlStr);
					});
					parent.$('#sel_res_dlg').dialog('open');
             });
                          
         
         });
        </script>
	</head>
	<body>
					<table width="955" border="0" cellpadding="0" cellspacing="0"
						class="list_table">
						<tr id="title_tr">
							<th width="46">
								<div align="left">
									<input name="checkbox2" type="checkbox" id="check_all" />
									<img style="cursor: pointer"
										src="${global_image_url}file_delete.gif" id="check_del"
										alt="Delete" width="16" height="16" border="0" />
								</div>
							</th>
							<th width="136">
								Work Group Name
							</th>
							<th width="197">
								Description
							</th>
							<th width="89">
								Status
							</th>
							<th width="118">
								Supervisor
							</th>
							<th>
								Comment
							</th>

						</tr>
					</table>
					<div class="list_box" style="height: 240px; overflow: scroll;">
						<table width="955" border="0" cellpadding="0" cellspacing="0"
							class="list_table" id="resultTable">
							<s:iterator value="centerGroupMap">
							<s:if test="value!=null">
							<tr>
									<td width="46" align="center">
										<input type="checkbox" value="${value.id}" name="groupIdList" />
										<input type="hidden" value="${value.id}" name="delGroupId" />
									</td>
									<td width="136">
										<a href="javascript:void(0)"
											onclick="edit('${value.id}')" target="_parent">${value.name}</a>
									</td>
									<td width="197">
										${value.description}
									</td>
									<td width="89">
										${value.status}
									</td>
									<td width="118">
										${value.superName}
									</td>
									<td>
										${value.comment}
									</td>
								</tr>
							</s:if>
								
							</s:iterator>
						</table>
					</div>
					<div align="center" style="padding: 10px;">
								<saveButton:saveBtn parameter="${operation_method}"
				disabledBtn='<input type="button" name="Submit123" value="New" class="style_botton" disabled="disabled" />'
				saveBtn='<input type="button" name="Submit123" value="New" class="style_botton" id="new_res_btn" />'
			/> 
					</div>
	</body>
</html>