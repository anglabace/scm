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
function checkForm() {
      $("#orderNo_txt").val($.trim($("#orderNo_txt").val()));
}

function selectOrder() {
     if ($("#resultTable :radio:checked").length < 1) {
         alert('Please select one at least!');
         return;
     }
     var sel_orderNo = $("#resultTable").find("[name='orderNo']:checked").val();
     var orderNoArray = sel_orderNo.split(",");
     parent.$("#order_orderNo_text").val(orderNoArray[1]);
     parent.$("#order_chinaOrderNo_text").val(orderNoArray[0]);
     parent.changeOrder();  
     parent.$('#sel_order_dlg').dialog('close');
}
		
$(document).ready(function(){

    $("#resultTable tr:odd").find("td").addClass("list_td2");
    
    //双击事件
    $("#resultTable [name='orderNo']").dblclick( function() {
        selectOrder();   
    });
    
    //执行Select
    $("#select_btn").click( function() {    
	    selectOrder();
    });
    
    
    
});
</script>
	</head>
	<body>
		<form action="workorder_entry!showOrderForSelect.action" method="get" onsubmit="checkForm();">
			<table border="0" cellpadding="0" cellspacing="0"
				class="General_table" align="center">
				<tr>
					<td>
						Order No
					</td>
					<td>
						<input name="filter_EQI_orderNo" id="orderNo_txt" value="${param.filter_EQI_orderNo}" class="NFText" size="15"
							type="text" />
							<input name="Submit3" type="submit" class="style_botton" value="Search" />
					</td>
				</tr>
			</table>
		</form>
		<table width="660" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="4">
					<table width="640" border="0" cellpadding="0" cellspacing="0"
						class="list_table">
						<tr>
							<th width="46" height="30px">
								<div align="left">
									
								</div>
							</th>
							<th>
								Order No
							</th>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div style="width: 657px; height: 210px; overflow: scroll;">
						<table width="640" border="0" cellpadding="0" cellspacing="0"
							class="list_table" id="resultTable">
							<s:iterator value="#request.pagerInfo.result">
								<tr>
									<td width="46">
										<div align="left">
											<s:if test="status == 'CN'">
												<input type="radio" value="${orderNo},${srcSoNo}" name="orderNo"
													disabled="disabled" />
											</s:if>
											<s:else>
												<input type="radio" value="${orderNo},${srcSoNo}" name="orderNo" />
											</s:else>
										</div>
									</td>
									<td>
										<div align="center">
											&nbsp;
											<a
												href="${global_url}order/order!edit.action?orderNo=${srcSoNo}&operation_method=edit"
												target="_blank">${orderNo}</a>
										</div>
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
					<div class="grayr">
						<jsp:include page="/common/db_pager.jsp">
							<jsp:param value="${ctx}/workorder_entry!showOrderForSelect.action"
								name="moduleURL" />
						</jsp:include>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="4" class="botton_box">
					<input name="Submit" type="button" id="select_btn"
						class="style_botton" value="Select" />
					<input type="submit" name="Submit2" value="Cancel"
						class="style_botton"
						onclick="parent.$('#sel_order_dlg').dialog('close');" />
				</td>
			</tr>
		</table>
	</body>
</html>
