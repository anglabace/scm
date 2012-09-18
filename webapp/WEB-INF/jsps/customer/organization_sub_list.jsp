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
			function edit(id) {
			   window.parent.location = "organization!edit.action?id=" + id + "&operation_method=edit";
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
                    var param = "";			   
			        if ($("#resultTable :checkbox:checked").length < 1) {
			           alert('Please select one at least!');
					   return;
			        }
			  		if (!confirm('Are you sure to delete?')) {
						return;
					}
					$("#resultTable :checkbox:checked").each(function() {
					   param += "&keyList=" + $(this).val();
					});		
					param = "parentOrgId=${param.id}" + param;
					$('#check_del').attr("disabled", true);
                    $.ajax({
						type: "POST",
						url: "organization!deleteSubList.action",
						data: param,
						dataType: 'json',
						success: function(data, textStatus){
							if(hasException(data)){//有错误信息.
				 	           $('#check_del').attr("disabled", false);				
							}else{        
							  window.location = window.location;                      
							  parent.$('#note_dlg').dialog('close');
							  parent.frames["subListFrame"].location = parent.frames["subListFrame"].location;
							}
						},
						error: function(xhr, textStatus){
						   alert("failure");
				 	       $('#check_del').attr("disabled", false);
						}
					}); 
				   	$("#resultTable :checkbox:checked").each(function() {
			           //var trObj = $(this).parent().parent();
			           //$("#resultTable").get(0).deleteRow(trObj.get(0).rowIndex); 
			        });	
			        
			    });//end of $("#check_del").click.                                           
            });
               
        </script>
	</head>
	<body>
		<input type="hidden" name="delIdList" id="delIdList_hid" />
		<table border="0" width="1084" cellpadding="0" cellspacing="0" class="list_table">
			<tr id="title_tr">
				<th width="46">
					<div class="quote_dele">
						<input name="checkbox2" type="checkbox" id="check_all" />
						<img style="cursor: pointer"
							src="${global_image_url}file_delete.gif" id="check_del"
							alt="Delete" width="16" height="16" border="0" />
					</div>

				</th>
				<th width="138">
					Organization Name
				</th>
				<th width="130">
					Organization Group
				</th>
				<th width="200">
					Address
				</th>
				<th width="50">
					Type
				</th>
				<th width="50">
					Status
				</th>
				<th width="60">
					Language
				</th>
				<th width="130">
					Parent Organization
				</th>
				<th>
					Supervisor
				</th>

			</tr>
		</table>
		<div class="list_box" style="height: 160px">
			<table border="0" width="1084" cellpadding="0" cellspacing="0" class="list_table"
				id="resultTable">
				<s:iterator value="#request.orgMap">
					<tr>
						<td width="46" align="center">
							<input type="checkbox" value="${key}" name="keyList" />
						</td>
						<td width="138">
							<a href="javascript:void(0)" onclick="edit('${value.orgId}')"
														target="_parent">${value.name}</a>
						</td>
						<td width="130">
						    ${value.organizationGroup.name}
						</td>
						<td width="200">
							${value.addrLine1}
							<c:if test="${! empty value.addrLine2}">
							</c:if>
							${value.addrLine2}
							<c:if test="${! empty value.addrLine3 }">
							</c:if>
							${value.addrLine3}
							<c:if
								test="${! empty value.state && ! empty value.addrLine1 || ! empty value.addrLine2 || ! empty value.addrLine3 }">, </c:if>
							${value.state}
							<c:if test="${! empty value.zipCode }">
							</c:if>
							${value.zipCode}
							<c:if test="${! empty value.country && ! empty value.state}">, </c:if>
							${value.country}
						</td>
						<td width="50">
							${value.organizationType.name}
						</td>
						<td width="50">
							<c:if test="${value.activeFlag == 'Y'}">ACTIVE</c:if>
							<c:if test="${value.activeFlag == 'N'}">INACTIVE</c:if>
						</td>
						<td width="60">
							${value.langName}
						</td>
						<td width="130">
							${value.parentOrganization.name}
						</td>
						<td>
							${value.supervisor}
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div class="grayr">
			<jsp:include page="/common/db_pager.jsp">
				<jsp:param value="${ctx}/organization!showSubList.action" name="moduleURL" />
			</jsp:include>
		</div>

	</body>
</html>