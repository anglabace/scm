<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<script src="${global_js_url}jquery/jquery.js" type="text/javascript"
	language="javascript"></script>
<script language="JavaScript" type="text/javascript">
	function cc(e, name) {
		var a = document.getElementsByName(name);
		for ( var i = 0; i < a.length; i++)
			a[i].checked = e.checked;
	}
	//delete checked contact
	function del_contact(name) {
		var del_cust_nos = get_checked_str(name);

		if (del_cust_nos == '') {
			alert('Please select one at least!');
			return;
		}
		if (!confirm('Are you sure to delete?')) {
			return;
		}
		var formStr = '';
		var commitURL = '${global_url}' + "contact/contact!delete.action";
		$
				.ajax({
					type : "POST",
					url : commitURL,
					data : 'contactNoList=' + del_cust_nos,
					success : function(msg) {
						if (msg == 'transfer_error') {
							alert(msg);
						} else if (msg == 'success') {
							alert('The contact is removed.')
						} else if (msg == 'error') {
							alert('Fail to delete the contact customer. Please contact system administrator for help.');
						} else {
							alert('System error! Please contact system administrator for help.');
						}
						window.location.reload();
					},
					error : function(msg) {
						alert("Failed to delete the contact. Please contact system administrator for help.");
					}
				});
		//	return false;
		//	document.forms[0].submit();
		return;
	}
	//Judge checked radio.
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
			del_contact('contactNo');
		});
	});
</script>
</head>
<body>
	<s:form action="contact/contact!delete.action" id="contactDelForm">
		<div style="margin-right: 17px;">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="list_table">
				<tr>
					<th width="46">
						<div align="center">
							<input type="checkbox" onclick="cc(this, 'contactNo')" /> <img
								id="delImg" src="${ctx}/images/file_delete.gif"
								style="cursor: pointer" alt="Delete" width="16" height="16"
								border="0" />
						</div>
					</th>
					<th width="80">Contact No</th>
					<th width="120">Contact Name</th>
					<th width="220">Orangization</th>
					<th width="80">Country</th>
					<th width="80">State</th>
					<th width="250">E-mail</th>
					<th width="150">Phone</th>
					<th width="80">Create Date</th>

				</tr>
			</table>
		</div>
		<div class="list_box" style="height: 340px;">
			<table id="resultTable" width="100%" border="0" align="center"
				cellpadding="0" cellspacing="0" class="list_table">
				<s:iterator value="contactPage.result">
					<tr>
						<td width="46">
							<div align="left">
								<c:if test='${status =="ACTIVE" || status =="Active"}'>
									<input type="checkbox" value="${contactNo}" name="contactNo" />
								</c:if>
								<c:if test='${status =="INACTIVE"}'>
									<input type="checkbox" value="${contactNo}" name="contactNo"
										disabled="disabled" />
								</c:if>
							</div>
						</td>
						<td width="80">
							<div align="center">
								&nbsp;<a
									href="contact!edit.action?contactNo=${contactNo}&operation_method=edit"
									target="_self">${contactNo}</a>
							</div>
						</td>
						<td width="120"><div align="center">
								<a
									href="contact!edit.action?contactNo=${contactNo}&operation_method=edit"
									target="_self">&nbsp;${firstName}<c:if
										test="${! empty lastName}">
									</c:if>${lastName}<c:if test="${! empty midName}">, </c:if>${midName}</a>
							</div></td>
						<td width="220" title="${organizationName}"><div
								align="center">
								&nbsp;
								<s:if
									test="%{organizationName != null && organizationName.length()>40}">
                        &nbsp; <s:property
										value="organizationName.substring(0,40)" escape="false" />... 
                        </s:if>
								<s:else>
                            ${organizationName} 
                        </s:else>
							</div>
						</td>
						<td width="80"><div align="center">&nbsp;${country}</div></td>
						<td width="80"><div align="center">&nbsp;${state}</div></td>
						<td width="220">&nbsp;${busEmail} </td>
						<td width="150"><div align="center">&nbsp;${busPhone}</div></td>
						<td width="80"><div align="center">&nbsp;  <s:date name="creationDate" format="yyyy-MM-dd" /> </div></td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div class="grayr">
			<jsp:include page="/common/db_pager.jsp">
				<jsp:param value="${ctx}/contact!list.action" name="moduleURL" />
			</jsp:include>
		</div>
	</s:form>
</body>
</html>
