<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>Route Search Result</title>
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<script src="${global_js_url}jquery/jquery.js" type="text/javascript"
			language="javascript"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
		<script language="JavaScript" type="text/javascript">
function edit(id) {
   window.parent.location = "ds_plate!plateEdit.action?id=" + id + "&operation_method=edit";
}		
		
$(document).ready(function(){
    $("#resultTable tr:odd").find("td").addClass("list_td2");

    //复选框绑定: 全选|全不选
    $("#check_all").click( function() {
       $(":checkbox").each(function() {
          this.checked = $("#check_all").get(0).checked;
       });      
    });
    
    
});
</script>
	</head>
	<body class="content" style="background-image: none;">
		<form action="route!delete.action" id="del_form">
			<div class="input_box">
				<div class="content_box">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<div>
									<table width="98.5%" border="0" cellspacing="0" cellpadding="0"
										class="list_table">
										<tr>
											<th width="10%">
												<div align="left">
													<input name="checkbox2" type="checkbox" id="check_all" />
												</div>
											</th>
											<th width="22%">
												Plate No
											</th>
											<th width="22%">
												Plate Name
											</th>
											<th width="21%">
												Nums
											</th>
											<th width="25%">
												Status
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
										<s:iterator value="dsPlatesPage.result">
											<tr>
												<td width="10%">
													<input type="checkbox" value="${id}" name="delRouteId" />
												</td>
												<td width="22%">
													<a href="ds_plate!plateEdit.action?id=${id}&operation_method=edit" target="_parent">${plateNo}</a>
												</td>
												<td width="22%">
													${name}
												</td>
												<td width="21%">
													${nums}
												</td>
												<td width="25%">
													<div align="center">
														${status}
													</div>
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
										<jsp:param value="${ctx}/ds_plate!plateList.action" name="moduleURL" />
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
