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
			function edit(resourceId) {
			   window.location = "resource!edit.action?resourceId=" + resourceId;
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
                    var param = $("#delIdList_hid").val();			   
			        if ($("#resultTable :checkbox:checked").length < 1) {
			           alert('Please select one at least!');
					   return;
			        }
			  		if (!confirm('Are you sure to delete?')) {
						return;
					}
					$("#resultTable :checkbox:checked").each(function() {
					   param += "&groupIdList=" + $(this).val();
					});		
				   	$("#resultTable :checkbox:checked").each(function() {
			           var trObj = $(this).parent().parent();
			           $("#resultTable").get(0).deleteRow(trObj.get(0).rowIndex); 
			        });	
			        $("#delIdList_hid").val(param);
					$("#resultTable tr:even").find("td").removeClass("list_td2");
					$("#resultTable tr:odd").find("td").addClass("list_td2");
			    });//end of $("#check_del").click.

                      
                
            	$('#sel_res_dlg').dialog({
					autoOpen: false,
					height: 440,
					width: 700,
					modal: true,
					bgiframe: true,
					buttons: {
					}
				});
				
                $("#new_res_btn").click( function() {
					parent.$('#sel_res_dlg').dialog("option", "open", function() {	
	              		 var htmlStr = '<iframe src="resource!selectForGroup.action?pageName=operation_resource_select&sessOperationId=${param.sessOperationId}" height="100%" width="680" scrolling="no" style="border:0px" frameborder="0"></iframe>';
				         parent.$('#sel_res_dlg').html(htmlStr);
					});
					parent.$('#sel_res_dlg').dialog('open');
                });
                
  
                             
            });
               
        </script>
	</head>
	<body>
		<input type="hidden" name="sessOperationId" id="sessOperationId"
			value="${param.sessOperationId}" />
		<input type="hidden" name="delIdList" id="delIdList_hid" />
		<table width="955" border="0" cellpadding="0" cellspacing="0"
			class="list_table">
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
				<th width="138">
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
				<th width="134">
					Parent Organization
				</th>
				<th>
					Supervisor
				</th>

			</tr>
		</table>
		<div class="frame_box3" style="height: 240px">
			<table width="955" border="0" cellpadding="0" cellspacing="0"
				class="list_table" id="resultTable">
				<s:iterator value="#request.pagerInfo.result">
					<tr>
						<td width="46" align="center">
							<input type="checkbox" value="${orgId}" name="groupResId" />
						</td>
						<td width="138">
							<!--<a href="organization!edit.action?id=${orgId}&operation_method=view" target="_parent">${name}</a>-->

                             <c:if test="${!empty name}">
                        <s:if test="%{name != null && name.length()>40}">
                            <div align="center">&nbsp;<a href="organization!edit.action?id=${orgId}&operation_method=view" target="_parent"><s:property value="name.substring(0,20)+'......'"/></a></div>
                        </s:if>
                        <s:else>
                            <div align="center">&nbsp;<a href="organization!edit.action?id=${orgId}&operation_method=view" target="_parent">${name}</a></div>
                        </s:else>
                       </c:if>
						</td>
						<td width="138">

                            ${organizationGroup.name}
						</td>
						<td width="200">
							${addrLine1}<c:if test="${! empty addrLine2}"> </c:if>${addrLine2}<c:if test="${! empty addrLine3 }"> </c:if>${addrLine3}<c:if test="${! empty state && ! empty addrLine1 || ! empty addrLine2 || ! empty addrLine3 }">, </c:if>${state }<c:if test="${! empty zipCode }"> </c:if>${zipCode }<c:if test="${! empty country && ! empty state}">, </c:if>${country }
						</td>
						<td width="50">
							${organizationType.name}
						</td>
						<td width="50">
							<c:if test="${activeFlag == 'Y'}">ACTIVE</c:if>
							<c:if test="${activeFlag == 'N'}">INACTIVE</c:if>
						</td>
						<td width="60">
							${langName}
						</td>
						<td width="134">
							${parentOrganization.name}
						</td>
						<td>
							${supervisor}
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div class="grayr">
			<jsp:include page="/common/db_pager.jsp">
				<jsp:param value="${ctx}/organization!list.action" name="moduleURL" />
			</jsp:include>
		</div>

	</body>
</html>