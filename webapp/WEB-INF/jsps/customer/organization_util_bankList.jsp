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
      $("#name_txt").val($.trim($("#name_txt").val()));
      return true;
}

$(document).ready(function(){

    $("#resultTable tr:odd").find("td").addClass("list_td2");

    //复选框绑定: 全选|全不选
    $("#check_all").click( function() {
       $(":checkbox").each(function() {
          this.checked = $("#check_all").get(0).checked;
       });      
    });
    
    //执行Select
    $("#save_btn").click( function() {    
       if ($(":radio:checked").length < 1) {
           alert('Please select one at least!');
		   return;
       } 
       $('#save_btn').attr("disabled", true);
       var bankName = $(':radio:checked').val();       
       parent.frames["billingFrame"].$("#bank_txt").val(bankName);
       parent.frames["billingFrame"].$("#changed_hid").val("Y");
       parent.$('#note_dlg').dialog('close');              
    });//end of 执行Select;
    
 
    
    
});
</script>
	</head>
	<body>
		<form action="organization!showSeclectBank.action" method="get" onsubmit="checkForm();">
			<table border="0" cellpadding="0" cellspacing="0"
				class="General_table" align="center">
				<tr>
					<td>
						Bank Name
					</td>
					<td>
						<input name="filter_LIKES_bankName" value="${param.filter_LIKES_bankName}"
							type="text" id="name_txt" class="NFText" size="20" />
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
							<th width="28">
								
							</th>
							<th width="100">
								Bank Name
							</th>
							<th width="100">
								Description
							</th>
							<th width="60">
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
									<td width="28" align="center">
										<input type="radio" name="name_radio" value="${bankName}" />
									</td>
									<td width="100">
										${bankName}
									</td>
									<td width="100">
										${description}
									</td>
									<td width="60">
										${status}
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
					<div class="grayr">
						<jsp:include page="/common/db_pager.jsp">
							<jsp:param value="${ctx}/organization!showSeclectBank.action"
								name="moduleURL" />
						</jsp:include>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="4" class="botton_box">
					<input name="Submit" type="button" id="save_btn"
						class="style_botton" value="Select" />
					<input type="submit" name="Submit2" value="Cancel"
						class="style_botton"
						onclick="parent.$('#note_dlg').dialog('close');" />
				</td>
			</tr>
		</table>
	</body>
</html>
