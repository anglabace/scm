<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>Department Search Result</title>
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<script src="${global_js_url}jquery/jquery.js" type="text/javascript"
			language="javascript"></script>
		<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
		<script language="JavaScript" type="text/javascript">
function edit(id) {
   window.parent.location = "organization!edit.action?id=" + id + "&operation_method=edit";
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
	<body style="background-image: none;">
		<form action="orgGroupPage!delete.action" id="del_form">
			<div class="input_box">
				<div class="content_box">
					<table width="942" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<div style="margin-right: 12px;">
									<table width="922" border="0" cellspacing="0" cellpadding="0"
										class="list_table2">
										<tr>
											<th width="145">
												Contact No
											</th>
											<th width="97">
												Status
											</th>
											<th width="187">
												Name
											</th>
											<th width="153">
												Email
											</th>
											<th width="159">
												Office/Lab
											</th>
											<th>
												Phone
											</th>
										</tr>
									</table>
									<div style="width: 940px; height: 260px; overflow: scroll;">
										<table width="922" border="0" cellpadding="0" cellspacing="0"
											class="list_table">
											<s:iterator value="contactPage.result">
												<tr>
													<td width="145">
														${contactNo}
													</td>
													<td width="97">
														${status}
													</td>
													<td width="187">
														&nbsp;${firstName}
														<c:if test="${! empty lastName}">
														</c:if>
														${lastName}
														<c:if test="${! empty midName}">, </c:if>
														${midName}
													</td>
													<td width="153">
														&nbsp;${busEmail}
													</td>
													<td width="159">
														
													</td>
													<td>
														&nbsp;${busPhone}
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
										<jsp:param value="${ctx}/department!showContact.action"
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
