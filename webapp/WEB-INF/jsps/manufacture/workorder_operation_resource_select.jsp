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

		
$(document).ready(function(){

    $("#resultTable tr:odd").find("td").addClass("list_td2");

    //复选框绑定: 全选|全不选
    $("#check_all").click( function() {
       $(":checkbox").each(function() {
          this.checked = $("#check_all").get(0).checked;
       });      
    });
    
    //执行Select
    $("#select_btn").click( function() {    
       if ($(":checkbox:gt(0):checked").length < 1) {
           alert('Please select one at least!');
		   return;
       }
       var param = "sessWOPKey=" + $("#sessWOPKey").val() + "&idList=";
       var id = "";
       $("#resultTable :checkbox:checked").each(function() {
              id = id + "," + $.trim(this.value);
       }); 
       id = id.substring(1);
       param = param + id;
       $('#select_btn').attr("disabled", true);
       $.ajax({
			type: "POST",
			url: "workorder_operation!selectResourceForWO.action",
			data: param,
			success: function(data) {
			    parent.$('#sel_res_dlg').dialog('close');
			    var url = parent.$("#resource_list_frame").attr("src");
				parent.frames["resource_list_frame"].location = url;
			    //刷新frame
			    //parent.$("#dhtmlgoodies_tabView1 .dhtmlgoodies_aTab").html(parent.$("#dhtmlgoodies_tabView1 .dhtmlgoodies_aTab").html());
			},
			error: function(msg){
				alert("Select failed !");
                $('#select_btn').attr("disabled", false);		
			}
		});  	       
    });//end of 执行Select;
           
    $("#resultTable [name=quantityList]").click ( function() {    
         var tdObj = $(this).parent();
         var trObj = tdObj.parent();   
         trObj.children(":eq(0)").children(":eq(0)").get(0).checked = true;
    });
    
});
</script>
	</head>
	<body>
		<form action="resource!selectForGroup.action" method="get">
		    <input type="hidden" name="pageName" value="${param.pageName}" />
		    <input type="hidden" name="sessWOPKey" id="sessWOPKey" value="${param.sessWOPKey}" />
			<table border="0" cellpadding="0" cellspacing="0"
				class="General_table">
				<tr>
					<td>
						Resource Name
					</td>
					<td>
						<input name="filter_LIKES_name" value="${param.filter_LIKES_name}" type="text" class="NFText"
							size="20" />
					</td>
					<td>
						Description
					</td>
					<td>
						<input name="filter_LIKES_description" value="${param.filter_LIKES_description}" type="text" class="NFText"
							size="40" />
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
								<input name="checkbox2" type="checkbox" id="check_all" />
							</th>
							<th width="100">
								Resource Name
							</th>
							<th width="45">
								Qty
							</th>
							<th width="100">
								Description
							</th>
							<th width="60">
								Status
							</th>
							<th width="80">
								Resource Group
							</th>
							<th width="65">
								Cost Basis
							</th>
							<th width="55">
								Cost
							</th>
							<th>
								Currency
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
							<s:iterator value="resourcePage.result">
								<tr>
									<td width="28" align="center">										
											<input type="checkbox" value="${resourceId}"
												name="resourceIdList" />	
											<input type="hidden" value="${resourceNo}"
												name="resourceNo" />										
									</td>
									<td width="100">
										${name}
									</td>
									<td width="45">
										<input name="quantityList" type="text" class="NFText" value="1"
											size="1" onkeypress="if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false;" />
											
									</td>
									<td width="100">
										${description}
									</td>
									<td width="60">										
										${status}										
									</td>
									<td width="80">
										${group}
									</td>
									<td width="65">
										${costBasis}
									</td>
									<td width="55">
										<div align="right">
											${currencySymbol}${cost}
										</div>
									</td>
									<td>
										${currency}
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
					<div class="grayr">
						<jsp:include page="/common/db_pager.jsp">
							<jsp:param value="${ctx}/resource!selectForGroup.action"
								name="moduleURL" />
						</jsp:include>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="4" class="botton_box">
					<input name="Submit" type="button" id="select_btn" class="style_botton"
						value="Select" />				   
					<input type="submit" name="Submit2" value="Cancel"
						class="style_botton"
						onclick="parent.$('#sel_res_dlg').dialog('close');" />
				</td>
			</tr>
		</table>
	</body>
</html>
