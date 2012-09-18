<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scm</title>
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<script src="${global_js_url}jquery/jquery.js" type="text/javascript"
	language="javascript"></script>
<script src="${global_js_url}initlefttop.js" type="text/javascript"></script>
<script language="JavaScript" type="text/javascript">
	function cc(e, name) {
		var a = document.getElementsByName(name);
		for ( var i = 0; i < a.length; i++)
			a[i].checked = e.checked;
	}

	function del_cust(name) {
		var del_cust_nos = get_checked_str(name);
		if (del_cust_nos == '') {
			alert('Please select one at least!');
			return;
		}
		if (!confirm('Are you sure to delete?')) {
			return;
		}

		$
				.ajax({
					type : "POST",
					url : "customer/customer!delCust.action",
					data : 'custNos=' + del_cust_nos,
					success : function(msg) {
						if (msg == 'transfer_error') {
							alert(msg);
						} else if (msg == 'success') {
							alert('Delete customer successfully');
						} else if (msg == 'error') {
							alert('Fail to delete customer');
						} else {
							alert('System error! Please contact system administrator for help.');
						}
						window.location.reload();
					},
					error : function(msg) {
						alert("Failed to delete the customer. Please contact system administrator for help.");
					}
				});
		return false;
	}

	function get_checked_str(name) {
		var a = document.getElementsByName(name);
		var str = '';
		for ( var i = 0; i < a.length; i++) {
			if (a[i].checked) {
				str += a[i].value + ',';
			}
		}
		return str.substring(0, str.length - 1);
	}

	$(document).ready(function() {
		$("#resultTable tr:odd").find("td").addClass("list_td2");
		parent.$('#srchCustAct_iframe').attr("height", 430);
		$("#delImg").bind("click", function() {
			del_cust('custNo');
		});
		$("#combineDialogTrigger").click(function() {
			parent.$("#combineDialog").dialog("open");

		});
	});
</script>
</head>
<body>
	<div style="margin-right: 17px;">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="list_table">
			<tr>
				<th width="46">
					<div align="left">
						<input type="checkbox" onclick="cc(this, 'custNo')" /> <img
							id="delImg" src="${global_image_url}file_delete.gif" alt="Delete"
							width="16" height="16" border="0" />
					</div>
				</th>
				<th width="80">Customer No</th>
				<th width="50">Alt No</th>
				<th width="70">Status</th>
				<th width="150">Name</th>
				<th width="100">Email</th>
				<th width="100">Organization</th>
				<th>Address</th>
				<th width="100">Phone</th>
				<th width="40">Points</th>
				<th width="60">Create Date</th>
			</tr>
		</table>
	</div>
	<div class="list_box" style="height: 340px;">
		<table id="resultTable" width="100%" border="0" align="center"
			cellpadding="0" cellspacing="0" class="list_table">
			<s:iterator value="page.result" id="custBean">
				<tr>
					<td width="46">
						<div align="left">
							<c:if test='${status !="INACTIVE"}'>
								<input type="checkbox" value="${custNo}" name="custNo" />
							</c:if>
							<c:if test='${status =="INACTIVE"}'>
								<input type="checkbox" value="${custNo}" name="custNo"
									disabled="disabled" />
							</c:if>
						</div>
					</td>
					<td width="80">
						<div align="center">
							&nbsp;<a
								href="${global_url}customer/customer!edit.action?custNo=${custNo}&operation_method=edit"
								target="_self">${custNo}</a>
						</div>
					</td>
					<td width="50" title="${altNo }"><c:if test="${! empty altNo}">
							<s:if test="%{altNo != null && altNo.length()>7}">
								<div align="center">
									&nbsp;
									<s:property value="altNo.substring(0,6)+'...'" />
								</div>
							</s:if>
							<s:else>
								<div align="center">&nbsp;${altNo}</div>
							</s:else>
						</c:if></td>
					<td width="70"  title="${custBean.status}">&nbsp;<s:if
							test="%{status != null && status.length()>6}">
							<s:property value="status.substring(0,6)+'...'" />
						</s:if> <s:else>
							<s:property value="status" />
						</s:else></td>
					<td width="150" title="${firstName},${midName},${lastName}">${firstName}<c:if
							test="${! empty lastName}">,
						</c:if>${lastName}<c:if test="${! empty midName}">, </c:if>${midName}
					</td>
					<td width="100" title="${busEmail}">&nbsp;<s:if
							test="%{busEmail != null && busEmail.length()>10}">
							<s:property value="busEmail.substring(0,10)+'...'" />
						</s:if> <s:else>
							<s:property value="busEmail" />
						</s:else></td>

					<td width="100" title="${custBean.organizationName}"><s:if
							test="%{organizationName != null && organizationName.length()>30}">
							<div>
								&nbsp;
								<s:property value="organizationName.substring(0,30)"
									escape="false" />
								...
							</div>
						</s:if> <s:else>
							<div>&nbsp;${custBean.organizationName}</div>
						</s:else></td>
					<td  title="${custBean.addrLine1},${custBean.addrLine2},${custBean.addrLine3},${zipCode },${state },${country }"> <s:if
							test="%{addrLine1 != null && addrLine1.length()>25}">	 
								<div><s:property value="addrLine1.substring(0,25)" />
								... </div>
						</s:if> <s:else>
							<div> ${addrLine1}</div>
						</s:else> <s:if test="%{addrLine2 != null && addrLine2.length()>15}"> 
									<div>,<s:property value="addrLine2.substring(0,15)" />
								...			</div>					 
						</s:if> <s:else>
							  <c:if test="${! empty addrLine2 }"> 	<div>,${addrLine2 }	</div></c:if>
						</s:else> <s:if test="%{addrLine3 != null && addrLine3.length()>15}">	 
									<div>,<s:property value="addrLine3.substring(0,15)" />
								... 	</div>
						</s:if> <s:else> 
									<c:if test="${! empty addrLine3 }"> <div>,${addrLine3 }</div></c:if>
						</s:else> <c:if
							test="${! empty state && ! empty addrLine1 || ! empty addrLine2 || ! empty addrLine3 }">,${state } </c:if>
						<c:if test="${! empty zipCode }"> ,${zipCode }</c:if> <c:if
							test="${! empty country && ! empty state}">, ${country }</c:if></td>
					<td width="100">&nbsp; ${busPhone}</td>
					<td width="40">&nbsp;${points}</td>
					<td width="60">&nbsp; <s:date name="creationDate"
							format="yyyy-MM-dd" /></td>
				</tr>
			</s:iterator>
		</table>
	</div>

	<div class="grayr">
		<jsp:include page="/common/db_pager.jsp">
			<jsp:param value="${ctx}/customer/customer_srch_list.action"
				name="moduleURL" />
		</jsp:include>
	</div>
</body>
</html>
