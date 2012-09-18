<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Work Order Processing</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
	type="text/css" />
<script
	src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.core.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js"
	type="text/javascript"></script>
<script src="${global_js_url}initlefttop.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript"
	src="${global_js_url}util/util.js"></script>
<script type="text/javascript">
	function toggleShowMore_img(obj, divID) {
		var oId = document.getElementById(divID);
		if (obj.src.indexOf('arrow1.jpg') > 0) {
			obj.src = "${global_url}images/arrow.jpg";
			oId.style.display = "none";
		} else {
			obj.src = "${global_url}images/arrow1.jpg";
			oId.style.display = "block";
		}
	}
	function cc(e, name){
		var  a =   document.getElementsByName(name);
		for   (var   i=0;   i<a.length;   i++)   
			if(a[i].disabled == false) 
				a[i].checked   =   e.checked;
	}  

	function del_quote(name){
		
		var del_quote_nos = get_checked_str(name);
		var quoteNos = del_quote_nos.split(",");
		var noData = "quoteNos="+quoteNos.join("&quoteNos=");	
		$.ajax({
			type: "POST",
			url: "manufacture/production_status_mapping!deleteProductionId.action",
			data: noData,
			success: function(msg){
				if(msg == 'success'){
					$('#delQuoteDialog').dialog('close');
					alert('Delete successfully');
				}else if(msg == 'error'){
					alert("System error! Please contact system administrator for help.");	
				}else {
					alert('Unknown error');
				}
				$(":checkbox").attr("checked", false);
				window.location.reload();
			},
			error: function(msg){
				alert("Failed to cancel the production.");
			}
		});
		return false;
	}

	function get_checked_str(name){
		var a = document.getElementsByName(name);
		var str = '';
		for(var i=0;i<a.length;i++){
			if(a[i].checked){
				str += a[i].value+',';
			}
		}
		return str.substring(0,str.length-1);
	}

	$(document).ready(function(){
		$("#resultTable tr:odd").find("td").addClass("list_td2");
		// more phones set dialog and trigger    
		$('#delQuoteDialogTrigger').click(function(){
			var del_quote_nos = get_checked_str("quoteNo");
			if(del_quote_nos == ''){
				alert('Please select one at least!');
				return;
			}
			if (!confirm('Are you sure to delete?')) {
				return;
			}
			del_quote('quoteNo');
		});
		
	});
	function add(){
		window.location.href("${global_url}manufacture/production_status_mapping!result.action");
	}
	 function centerSelect () {
     	var centerId = $("#warehouse_sel").val();
     	if(centerId=="") {
     		if($("#warehouse_sel").get(0).options.length>1) {
     			centerId = $("#warehouse_sel").get(0).options[1].value;
     		} else {
     			$("#workGroup_sel").get(0).options.length = 0;
	 	          	return;
     		}
     	}
	 }
</script>
</head>
<body class="content" style="background-image: none;">
	<div id="frame12" style="display: none;" title="Generate QC Batch">
	</div>
	<div id="batchOrder" style="display: none;"
		title="Batch Order Processing"></div>
	<form action="production_status_mapping!list.action" method="get">
		<div class="scm">
			<div class="title_content">
				<div
					style="padding-left: 20px; color: #5579A6; vertical-align: middle;">
					<img src="${global_url}images/arrow1.jpg"
						style="width: 16px; height: 17px; vertical-align: middle;"
						onclick="toggleShowMore_img(this,'search_box1');" />&nbsp;&nbsp;Production
					Status Mapping
				</div>
			</div>
			<div class="search_box" id="search_box1">
				<div class="single_search">
					<table border="0" cellspacing="0" cellpadding="0"
						class="General_table">
						<tr>
							<td align="right">Work Center</td>
							<td width="120"><s:if
									test="workCenterList!=null&&workCenterList.size()>0">
									<s:select id="warehouse_sel" name="workId"
										list="workCenterList" headerKey="" headerValue="All" 
										value="workId"
										listKey="id" listValue="name"></s:select>
								</s:if> <s:else>
									<select id="warehouse_sel" name="filter_EQI_workCenterId">
										<option value="">All</option>
									</select>
								</s:else></td>
							<td>Production Status</td>
							<td width="120"><input name="filter_LIKES_productionStatus"
								type="text" class="NFText" size="20"
								value="${param.filter_LIKES_productionStatus}" /></td>
							<td>Customized Production Status</td>
							<td width="120"><input
								name="filter_LIKES_customProductionStatus" type="text"
								class="NFText" size="20"
								value="${param.filter_LIKES_customProductionStatus}" /></td>
						</tr>
						<tr>
							<td>Status</td>
							<td>
							<s:select list="{'All','ACTIVE','Inactive'}" value="status"  name="status"></s:select>
							</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td colspan="6" align="center"><input type="submit"
								name="Submit5" value="Search" class="search_input" /></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="input_box" style="height: 350px;">
				<div class="content_box">
					<table width="1010" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<div style="margin-right: 17px;">
									<table width="1200" border="0" cellspacing="0" cellpadding="0"
										class="list_table">
										<tr>
											<th width="46">
												<div align="left">
													<input type="checkbox"  onclick="cc(this, 'quoteNo')" />
             										<img id="delQuoteDialogTrigger" src="${global_image_url}file_delete.gif" alt="Delete" width="16" height="16" border="0" />
												</div>
											</th>
											<th width="151">Production Status</th>
											<th width="203">Customized Production Status</th>
											<th width="173">Work Center</th>
											<th width="275">Description</th>
											<th>Status</th>
										</tr>

									</table>
								</div></td>
						</tr>
						<tr>
							<td>
								<div class="list_box" style="height: 340px;">
									<table width="1200" border="0" cellspacing="0" cellpadding="0"
										class="list_table2" id="resultTable">
										<s:iterator value="productionStatusMappingPage.result">
											<tr>
												<td width="46" align="center"><input type="checkbox" value="${id}" name="quoteNo" /></td>
												<td width="151"><a href="${global_url}manufacture/production_status_mapping!result.action?id=${id}" target="mainFrame">${productionStatus}</a></td>
												<td width="203">${customProductionStatus}</td>
												<td width="173">${workCenterName}</td>
												<td width="275">${description}</td>
												<td>${status}</td>
											</tr>
										</s:iterator>
									</table>
								</div></td>
						</tr>
						<tr>
							<td>
								<div class="grayr">
									<jsp:include page="/common/db_pager.jsp">
										<jsp:param
											value="${ctx}/production_status_mapping!list.action"
											name="moduleURL" />
									</jsp:include>
								</div></td>
						</tr>
						
					</table>
				</div>
			</div>
			<div class="button_box">
					<input type="button" name="Submit52" onclick="add()"
						value="New" class="search_input" />
			</div>
			
		</div>
	</form>
	
	
</body>
</html>