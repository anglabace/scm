<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/common/taglib.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scm</title>
<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<script src="${global_js_url}jquery/jquery.js" type="text/javascript"
			language="javascript"></script>
<script type="text/javascript" language="javascript"
			src="${global_js_url}util/util.js"></script>
<style type="text/css">
	.row_odd{background-color:#FFFFFF;}
	.row_even{background-color:#EEF2FD;}
	.tr_hover{background-color:#D1F1FA;}  
	.tr_click{background-color:#84E5FF;}  
	.tr_del{background-color:#E6E6E6;}  
	.tr_{background-color:#E6E6E6;}  
</style>
<script language="JavaScript" type="text/javascript">
var preTRObj = null;
$(function(){
	$("#resultTable tr:odd").find("td").addClass("list_td2");
	// tr点击事件
	$( '#resultTable tr' ).live( 'click' ,function(){
		itemTrClick( $(this) ) ;
	});
    //复选框绑定: 全选|全不选
    $("#check_all").click( function() {
       $(":checkbox").each(function() {
          this.checked = $("#check_all").get(0).checked;   
    });
	});
});

//item切换
function itemTrClick( trObj ){
	var itemId = trObj.attr("id");
	selectOneDsPlateItems(itemId);
	if(preTRObj != undefined && preTRObj != null){
	    preTRObj.removeClass("tr_click");
    }
    preTRObj = trObj;
	trObj.addClass("tr_click");
	parent.$("#dsPlateItemId").attr("value",itemId);
}

function selectOneDsPlateItems(itemId) {
	var url = "ds_plate!plateItemEdit.action";
	var data = "id="+parent.$("#id").val()+"&plateItemId="+itemId;
	$.ajax({
		url:url,
		data:data,
		dataType:'json',
		success:function(data) {
			var html = parent.getFileListHtml(data);
			parent.$("#save_form").find("#fileListTable").html("");
			parent.$("#save_form").find("#fileListTable").append(html);
		},
		error:function(data){
			alert("failure");
		}
	
	});
}

</script>
</head>
<body class="content" style="background-image: none;">
			<div class="input_box">
				<div class="content_box">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<div>
									<table width="98.5%" border="0" cellspacing="0" cellpadding="0"
										class="list_table">
										<tr>
											<th width="4%">
												<div align="left">
													<input name="checkbox2" type="checkbox" id="check_all" />
												</div>
											</th>
											<th width="5%">
												Work Order No
											</th>
											<th width="5%">
											    Sales Order No
											</th>
											<th width="5%">
												Item No
											</th>
											<th width="10%">
												Sample Name
											</th>
											<th width="10%">
												Primer Name
											</th>
											<th width="12%">
												Sequence File
											</th>
											<th width="12%">
												Trace File
											</th>
											<th width="12%">
												QV Data
											</th>
											<th>
												QV Comment
											</th>
											<th width="12%">
												Alignment File
											</th>
										</tr>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="list_box" style="width:100%;height: 240px;">
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										class="list_table2" id="resultTable">
										<s:iterator value="dsPlateItemsMap">
											<tr id="${key}">
												<td width="4%">
				       								<input type="checkbox" value="${key}" id="${key}" name="plateItemId"/>	
												</td>
												<td width="5%">
				       								<a href="workorder_entry!edit.action?id=${value.workOrderNo}&operation_method=edit" target="_parent">${value.workOrderNo}</a>
												</td>
												<td width="5%" align="center">
													${value.orderNo}
												</td>
												<td width="5%">
													${value.itemNo}
												</td>
												<td width="10%">
													${value.orderDnaSequencing.sampleName}
												</td>
												<td width="10%">
													${value.orderDnaSequencing.primerName}
												</td>
												<td width="12%">
													<s:iterator value="value.sequenceFileMap">
														${value.docName}<br>
													</s:iterator>													
												</td>
												<td width="12%">
													<s:iterator value="value.traceFileMap">
														${value.docName}<br>
													</s:iterator>	
												</td>
												<td width="12%">
													<s:iterator value="value.qvDataMap">
														${value.docName}<br>
													</s:iterator>
												</td>
												<td>
													
												</td>
										
												<td width="12%">
													<s:iterator value="value.alignmentFileMap">
														${value.docName}<br>
													</s:iterator>
												</td>
											</tr>
										</s:iterator>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
	</body>
</html>