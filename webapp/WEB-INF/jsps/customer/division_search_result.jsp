<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>Division Search Result</title>
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
			url: "organization!delete.action",
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
				alert("Delete organization failed !");
                $('#check_del').attr("disabled", false);		
			}
		});    
    });
    
});
</script>
	</head>
	<body style="background-image: none;">
		<form action="orgGroupPage!delete.action" id="del_form">
			<div class="input_box">
				<div class="content_box">
					<table width="970" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<div style="margin-right: 17px;">
									<table width="950" border="0" cellspacing="0" cellpadding="0"
										class="list_table2">
										<tr>
											<th width="170">
												Divlsion Name
											</th>
											<th width="122">
												Status
											</th>
											<th width="184">
												Supervisor
											</th>
											<th width="107">
												Language
											</th>
											<th width="231">
												Address
											</th>
											<th>
												Phone
											</th>
										</tr>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="list_box" style="height: 160px;">
									<table width="950" border="0" cellspacing="0" cellpadding="0"
										class="list_table2" id="resultTable">
										<s:iterator value="divisionPage.result">
											<tr>
												<td width="170">
													<a href="division!edit.action?id=${divisionId}" target="mainFrame">${name}</a>
												</td>
												<td width="122">
													<c:if test="${activeFlag == 'Y'}">ACTIVE</c:if>
							                        <c:if test="${activeFlag == 'N'}">INACTIVE</c:if>
												</td>
												<td width="184">
													${supervisor}
												</td>
												<td width="107">
													${langCode}
												</td>
												<td width="231">
													${addrLine1}<c:if test="${! empty addrLine2}"> </c:if>${addrLine2}<c:if test="${! empty addrLine3 }"> </c:if>${addrLine3}<c:if test="${! empty state && ! empty addrLine1 || ! empty addrLine2 || ! empty addrLine3 }">, </c:if>${state }<c:if test="${! empty zipCode }"> </c:if>${zipCode }<c:if test="${! empty country && ! empty state}">, </c:if>${country }
												</td>
												<td>
													${phone}
												</td>
											</tr>
										</s:iterator>
									</table>
								</div>
						</tr>
						<tr>
							<td>
								<div class="grayr">
									<jsp:include page="/common/db_pager.jsp">
										<jsp:param value="${ctx}/division!list.action"
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
