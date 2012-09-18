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
            $(function() {
 
			    $("#resultTable tr:odd").find("td").addClass("list_td2");
			
			    //复选框绑定: 全选|全不选
			    $("#check_all").click( function() {
			       $(":checkbox").each(function() {
			          this.checked = $("#check_all").get(0).checked;
			       });      
			    });
			    
			    //Select按钮.
			    $("#select_btn").click( function() {    
                    var param = "";			   
			        if ($("#resultTable :checkbox:checked").length < 1) {
			           alert('Please select one at least!');
					   return;
			        }

					$("#resultTable :checkbox:checked").each(function() {
					   param += "&idList=" + $(this).val();
					});		
					param = "parentOrgId=${param.orgId}" + param;
			        $("#idList_hid").val(param);
                    $('#select_btn').attr("disabled", true);
                    $.ajax({
						type: "POST",
						url: "organization!addSubList.action",
						data: param,
						dataType: 'json',
						success: function(data, textStatus){
							if(hasException(data)){//有错误信息.
				 	           $('#select_btn').attr("disabled", false);				
							}else{                              
							  parent.$('#note_dlg').dialog('close');
							  parent.frames["subListFrame"].location = parent.frames["subListFrame"].location;
							}
						},
						error: function(xhr, textStatus){
						   alert("failure");
				 	       $('#select_btn').attr("disabled", false);
						}
					}); 
			    });//end of $("#check_del").click.                              
            });
               
        </script>
	</head>
	<body>
		<input type="hidden" name="idList" id="idList_hid" />
		<table width="680px" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<table width="658" border="0" cellpadding="0" cellspacing="0"
						class="list_table">
						<tr id="title_tr">
							<th width="42" height="26">
								<div class="quote_dele">
									<input name="checkbox2" type="checkbox" id="check_all" />
								</div>

							</th>
							<th width="120">
								Organization Name
							</th>
							<th width="120">
								Organization Group
							</th>
							<th>
								Address
							</th>
							<th width="90">
								Type
							</th>
							<th width="120">
								Parent Organization
							</th>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div style="width: 675px; height: 230px; overflow: scroll;">
						<table width="658" border="0" cellpadding="0" cellspacing="0"
							class="list_table" id="resultTable">
							<s:iterator value="#request.pagerInfo.result">
								<tr>
									<td width="42" align="center">
										<input type="checkbox" value="${orgId}" name="orgId_chk" />
									</td>
									<td width="120">
										${name}
									</td>
									<td width="120">
										${organizationGroup.name}
									</td>
									<td>
										${addrLine1}
										<c:if test="${! empty addrLine2}">
										</c:if>
										${addrLine2}
										<c:if test="${! empty addrLine3 }">
										</c:if>
										${addrLine3}
										<c:if
											test="${! empty state && ! empty addrLine1 || ! empty addrLine2 || ! empty addrLine3 }">, </c:if>
										${state }
										<c:if test="${! empty zipCode }">
										</c:if>
										${zipCode }
										<c:if test="${! empty country && ! empty state}">, </c:if>
										${country }
									</td>
									<td width="90">
										${organizationType.name}
									</td>
									<td width="120">
										&nbsp;
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
					<div class="grayr" style="padding-right: 5px;">
						<jsp:include page="/common/db_pager.jsp">
							<jsp:param value="${ctx}/organization!showListForAddSub.action"
								name="moduleURL" />
						</jsp:include>
					</div>
				</td>
			</tr>
			<tr>
				<td class="botton_box">
					<input name="Submit" type="button" id="select_btn" class="style_botton"
						value="Select" />				   
					<input type="submit" name="Submit2" value="Cancel"
						class="style_botton"
						onclick="parent.$('#note_dlg').dialog('close');" />
				</td>
			</tr>
		</table>
	</body>
</html>