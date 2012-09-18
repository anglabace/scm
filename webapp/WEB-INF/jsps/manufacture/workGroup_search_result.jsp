<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>WorkGroup Search Result</title>
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<script src="${global_js_url}jquery/jquery.js" type="text/javascript"
			language="javascript"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
		<script language="JavaScript" type="text/javascript">
function edit(workGroupId) {
   window.parent.location = "work_group!edit.action?id=" + workGroupId + "&operation_method=edit";
}		
		
$(document).ready(function(){
    $("#resultTable tr:odd").find("td").addClass("list_td2");

    //复选框绑定: 全选|全不选
    $("#check_all").click( function() {
       $(":checkbox").each(function() {
          this.checked = $("#check_all").get(0).checked;
       });      
    });
    
    //删除选中的checkbox.
    $("#check_del").click( function() {    
        if ($(":checkbox:gt(0):checked").length < 1) {
           alert('Please select one at least!');
		   return;
        }
  		if (!confirm('Are you sure to delete?')) {
			return;
		}
        $('#check_del').attr("disabled", true);	
		$.ajax({
			type: "POST",
			url: "work_group!delete.action",
			data: $("#del_form").serialize(),
			dataType: 'json',
			success: function(data){
				if(hasException(data)){//有错误信息.
	 	            $('#check_del').attr("disabled", false);			
				}else {                        
					alert(data.message);
				    window.location.reload();
				}
			},
			error: function(msg){
				alert("Delete Work Group failed !");
                $('#check_del').attr("disabled", false);		
			}
		});    
    });
    
});
</script>
	</head>
	<body class="content" style="background-image: none;">
		<form action="work_group!delete.action" id="del_form">
			<div class="input_box">
				<div class="content_box">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<div>
									<table width="98.7%" border="0" cellspacing="0" cellpadding="0"
										class="list_table">
										<tr>
											<th width="5%">
												<div align="left">
													<input name="checkbox2" type="checkbox" id="check_all" />
													<img style="cursor: pointer"
														src="${global_image_url}file_delete.gif" id="check_del"
														alt="Delete" width="16" height="16" border="0" />
												</div>
											</th>
											<th width="21%">
												Work Group Name
											</th>
											<th width="21%">
												Description
											</th>
											<th width="10.5%">
												Status
											</th>
											<th width="10.5%">
												Supervisor
											</th>
											<th>
												Comment
											</th>
										</tr>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="list_box" style="width:100%;height: 340px;">
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										class="list_table2" id="resultTable">
										<s:iterator value="workGroupPage.result">
											<tr>
												<td width="5%">
													<input type="checkbox" value="${id}"
														name="delWorkGroupId" />
												</td>
												<td width="21%">
													<a href="javascript:void(0)" onclick="edit('${id}')" target="_parent">${name}</a>
												</td>
												<td width="21%">
													${description}
												</td>
												<td width="10.5%">
													<div align="center">
														${status}
													</div>
												</td>
												<td width="10.5%">
													${superName}
												</td>
												<td>
													${comment}
												</td>
											</tr>
										</s:iterator>
									</table>
								</div>
								</td>
						</tr>

						<tr>
							<td>
								<div class="grayr">
									<jsp:include page="/common/db_pager.jsp">
										<jsp:param value="${ctx}/work_group!list.action"
											name="moduleURL" />
									</jsp:include>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</body>
</html>
