<%@ include file="/common/taglib.jsp"%>
<%@ page contentType="text/html; charset=utf-8" %>
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
      $("#catalogNo_txt").val($.trim($("#catalogNo_txt").val()));
      $("#name_txt").val($.trim($("#name_txt").val())); 
}

//响应select事件.
function selectProductBtn() {
     if ($("#resultTable :radio:checked").length < 1) {
         alert('Please select one at least!');
         return;
     }
     var sel_id = $("#resultTable").find("[name='productId']:checked").val();
     parent.$("#catalogNo_txt").val(sel_id);
     parent.selectProduct('${param.type}', sel_id);
     parent.$('#sel_product_dlg').dialog('close');
}		
$(document).ready(function(){
    $("#type_sel").val('${param.type}');
    $("#resultTable tr:odd").find("td").addClass("list_td2");
    //双击事件
    $("#resultTable [name='productId']").dblclick( function() {
        selectProductBtn();   
    });
    
    //执行Select
    $("#select_btn").click( function() {    
	    selectProductBtn();
    });
    
});
</script>
	</head>
	<body>
		<form action="workorder_entry!showProductForSelect.action" method="get" onsubmit="checkForm();">
				<table border="0" cellpadding="0" cellspacing="0"
				class="General_table" align="center">
				<tr>
					<td>
                        <c:set var="pdtType" value="${param.type}"></c:set>
						<s:select name="type" id="type_sel" cssStyle="width:110px;" list="{'Product', 'Service'}" value="#pdtType" />
					</td>				   
					<td>
						Catalog No
					</td>
					<td>
						<input name="filter_LIKES_catalogNo" id="catalogNo_txt" value="${param.filter_LIKES_catalogNo}"
							type="text" class="NFText" size="20" />
					</td>

					<td>
						Catalog Name
					</td>
					<td>
						<input name="filter_LIKES_name" id="name_txt" value="${param.filter_LIKES_name}"
							type="text" class="NFText" size="20" />
					</td>
					<td>
						<input name="Submit3" type="submit" class="style_botton"
							value="Search" />
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
							<th width="38" height="30px">

							</th>
							<th width="130">
								Catalog No
							</th>
							<th width="125">
								Type
							</th>
							<th width="220">
								Name
							</th>
							<th>
								Status
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
									<td width="38" align="center">
										<input type="radio" value="${serviceId}${productId}" name="productId" />
									</td>
									<td width="130">
										${catalogNo}&nbsp;
									</td>
									<td width="125">
										${type}&nbsp;
									</td>
									<td width="220">
										${name}&nbsp;
									</td>
									<td>
										${status}&nbsp;
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
					<div class="grayr">
						<jsp:include page="/common/db_pager.jsp">
							<jsp:param value="${ctx}/workorder_entry!showProductForSelect.action"
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
						onclick="parent.$('#sel_product_dlg').dialog('close');" />
				</td>
			</tr>
		</table>
	</body>
</html>
