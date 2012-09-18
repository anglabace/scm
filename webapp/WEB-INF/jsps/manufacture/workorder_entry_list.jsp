<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Work Order Entry</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet"
			type="text/css" />
		<link href="${global_js_url}jquery/themes/base/ui.all.css"
			rel="stylesheet" type="text/css" />
		<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
			type="text/css" />
			
		<script type="text/javascript" language="javascript"
			src="${global_js_url}jquery/jquery.js"></script>
		<script
			src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js"
			type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.core.js"
			type="text/javascript"></script>
		<script type="text/javascript">
            $(function() {
               $("#resultTable tr:odd").find("td").addClass("list_td2");
            });
            
            function toggleShowMore_img(obj,divID){
        		var oId = document.getElementById(divID);
        		if (obj.src.indexOf('arrow1.jpg') > 0){
        			obj.src="${global_url}images/arrow.jpg";
        			oId.style.display = "none"; 
        		}else{
        			obj.src="${global_url}images/arrow1.jpg";
        			oId.style.display = "block"; 
        		}
        }
        </script>
	</head>
	<body class="content" style="background-image: none;">
		<form action="workorder_entry!list.action" method="get">
			<div class="scm">
			<div class="title_content">
  				<div style="padding-left: 20px;color: #5579A6;vertical-align:middle;"><img src="${global_url}images/arrow1.jpg" style="width:16px;height:17px;vertical-align:middle;" onclick="toggleShowMore_img(this,'search_box1');"/>&nbsp;&nbsp;Work Order Entry</div>
			</div>
				<div class="search_box" id="search_box1">
					<div class="single_search">
						<table border="0" cellspacing="0" cellpadding="0"
							class="General_table">
							<tr>
								<td>
									Work Center Name
								</td>
								<td width="120">
									<input name="filter_LIKES_name" type="text" class="NFText"
										size="20" value="${param.filter_LIKES_name}"/>
								</td>
								<td>
									Description
								</td>
								<td width="120">
									<input name="filter_LIKES_description" type="text"
										class="NFText" size="20" value="${param.filter_LIKES_description}"/>
								</td>
								
							</tr>
							<tr>
							<td></td>
							<td></td>
								<td width="100" colspan="2" align="center">
									<input type="submit" name="Submit5" value="Search"
										class="search_input" />
								</td>
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
										<table width="993" border="0" cellspacing="0" cellpadding="0"
											class="list_table">
											<tr>
												<th width="160">
													Work Center Name
												</th>
												<th width="140">
													Description
												</th>
												<th width="60">
													Status
												</th>
												<th width="50">
													Default
												</th>
												<th width="100">
													Supervisor
												</th>
												<th width="140">
													Storage Warehouse
												</th>
												<th width="100">
													Comment
												</th>
												<th width="80">
													Modified Date
												</th>
												<th>
													Modified By
												</th>

											</tr>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="list_box" style="height: 340px;">
										<table width="993" border="0" cellspacing="0" cellpadding="0"
											class="list_table2" id="resultTable">
											<s:iterator value="workCenterPage.result">
												<tr>
													<td width="160">
														<a href="workorder_entry!detail.action?id=${id}&operation_method=edit">
														<span title="${name}">
														<s:if test="name.length()>25">
														<s:property  value="name.substring(0,25)"/>...
														</s:if>
														<s:else>
															${name}
														</s:else>
														</span>
														
														</a>
													</td>
													<td width="140">
														<span title="${description}">
														<s:if test="description!=null&&description.length()>25">
														<s:property  value="description.substring(0,25)"/>...
														</s:if>
														<s:else>
															${description}
														</s:else>
														</span>
													</td>
													<td width="60">
														<div align="center">
															${status}
														</div>
													</td>
													<td width="50">
														<c:if test="${defaultFlag == 'Y'}">Yes</c:if>
														<c:if test="${defaultFlag != 'Y'}">No</c:if>
													</td>
													<td width="100">
														${superName}
													</td>
													<td width="140">
														${warehouseName}
													</td>
													<td width="100">
														<span title="${comment}">
														<s:if test="comment!=null&&comment.length()>15">
														<s:property  value="comment.substring(0,15)"/>...
														</s:if>
														<s:else>
															${comment}
														</s:else>
														</span>
													</td>
													<td width="80">
														<div align="center">
															<s:date format="yyyy-MM-dd" name="modifyDate" />
														</div>
													</td>
													<td>
														${modifyUser}
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
											<jsp:param value="${ctx}/workorder_entry!list.action"
												name="moduleURL" />
										</jsp:include>
									</div>
								</td>
							</tr>
						</table>
					</div>

				</div>
			</div>
		</form>
	</body>
</html>