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
		function check(){
			var filter_LIKES_userDeptName = $("#filter_LIKES_userDeptName").val();
			$("#filter_LIKES_userDeptName").val($.trim(filter_LIKES_userDeptName));
			var filter_LIKES_userDeptName = $("#filter_LIKES_userDeptName").val();
			$("#filter_LIKES_userDeptName").val($.trim(filter_LIKES_userDeptName));
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
    $("#select_btn").click( function() {    
       if ($(":checkbox:gt(0):checked").length < 1) {
           alert('Please select one at least!');
		   return;
       }
       var trCount = parent.frames["resource_list_frame"].$("#resultTable tr").length;
       var param = "sessOperationId=" + $("#sessOperationId").val() + "&trCount=" + trCount;
       var msg = "" ;
       $(":checkbox:gt(0):checked").each(function() {
          var tdObj = $(this).parent();
          var trObj = tdObj.parent();    
          param += "&resourceIdList=" + $(this).val();
          var temp = trObj.children(":eq(2)").children(":input").val();
          param += "&quantityList=" + temp;   
		  if(isNaN(temp)) {
			  msg += "Please enter a valid the quantity of '" + trObj.children(":eq(1)").html() + "' !\r\n" ;
		  }
		  if( temp == "" ) {
			msg += "Please enter the quantity of '" + trObj.children(":eq(1)").html() + "' !\r\n" ;
		  }
           
       });     
       if( msg != "" ) {
       		alert(msg) ;
       		return ;
       }
       $('#select_btn').attr("disabled", true);
       $.ajax({
			type: "POST",
			url: "operation!saveResource.action",
			dataType: 'json',
			data: param,
			success: function(data){
			   // ranscan(data);
			  //  return;
			  parent.document.getElementById("resource_list_frame").src = parent.$("#resource_list_frame").attr("src");
			    parent.$('#sel_res_dlg').dialog('close');
			},
			error: function(msg){
				alert("Select failed !");
                $('#select_btn').attr("disabled", false);		
			}
		});  	       
    });//end of 执行Select;
    
        //动态改变父窗口表格
    function ranscan(jsonObj) {
       var trCount = parent.frames["resource_list_frame"].$("#resultTable tr").length;
       var exLoop = 1;
       $(":checkbox:gt(0):checked").each(function() {
          var tdObj = $(this).parent();
          var trObj = tdObj.parent();    
          var md5Str = ""; 
          var resId = $(this).val();
		  $.each(jsonObj, function(name, value) {
	             if (resId == name) {
	                md5Str = value;
	             } 
	      });
          var trTemp = $("<tr></tr>");
          var td1 = '<input type="checkbox" value="' + md5Str + '"	name="groupResId" />';
	      trTemp.append("<td width='65' align='center'>" + td1 + "</td>");
	      trTemp.append("<td width='80' align='center'><a href='javascript:void(0)' onclick='edit(\""+resId+"\")' target='_parent'>" + (trCount + exLoop) + "</a></td>");	      
	      trTemp.append("<td width='200'>" + trObj.children(":eq(1)").html() + "</td>");//copy resource name
	      trTemp.append("<td width='50' align='right'>" +  trObj.children(":eq(2)").children(":input").val() + "</td>");//copy quantity
	      trTemp.append("<td width='463'>" + trObj.children(":eq(3)").html() + "</td>");//copy description
	      trTemp.append("<td>" + trObj.children(":eq(4)").html() + "</td>");//copy status
exLoop ++;
	      //以下是调该行包含的各个td的宽度
	      var loop = 0;
	      parent.$("#title_tr th").each( function() {
	          trTemp.children(":eq(" + loop + ")").width($(this).width());
	          loop ++;
		  }); 
	      parent.frames["resource_list_frame"].$("#resultTable").append(trTemp);
	      parent.frames["resource_list_frame"].$("#resultTable tr:odd").find("td").addClass("list_td2");//隔行变色
       });   
      parent.$('#sel_res_dlg').dialog('close');
    }
    
    $("#resultTable [name=quantityList]").click ( function() {    
         var tdObj = $(this).parent();
         var trObj = tdObj.parent();   
         trObj.children(":eq(0)").children(":eq(0)").get(0).checked = true;
    });
    
});
</script>
	</head>
	<body>
		<form action="resource!selectForGroup.action" method="post" onsubmit="check()">
		<input type="hidden" name="pageName" value="operation_resource_select"/>
		    <input type="hidden" name="sessOperationId" id="sessOperationId" value="${param.sessOperationId}" />
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
						User
					</td>
					<td>
						<input id="filter_LIKES_userDeptName" name="filter_LIKES_userDeptName" value="${param.filter_LIKES_userDeptName}" type="text" class="NFText"
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
								User
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
											size="1"/>
											
									</td>
									<td width="100">
										${userDeptName}
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
