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
<style type="text/css">
body {
	margin-left: 10px;
	margin-top: 10px;
	width: 730px;
}
</style>
<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}util/util.js"></script>
<script>

        function deleteThis(custNo) {
            var num = custNo;
            var oldstr = parent.document.getElementById("fromCustNos").contentWindow.document.getElementById("fromCustNod").innerHTML;
            var anum = oldstr.split(",");
            for (var i = 0; i < anum.length; i++) {
                if (num == anum[i]) {
                    oldstr = oldstr.replace("," + num, "");
                }
            }
            parent.document.getElementById("fromCustNos").contentWindow.document.getElementById("fromCustNod").innerHTML = oldstr;
        }

        function checkCustNos(Arr, id) {
            var con = false;
            if (Arr.length > 0) {
                for (var i = 0; i < Arr.length; i++) {
                    if (Arr[i] == id) {
                        con = true;
                        return con;
                    }
                }
            }
            return con;
        }
        $(document).ready(function() {
            var fromCustNos = parent.document.getElementById("fromCustNos").contentWindow.document.getElementById("fromCustNod").innerHTML;
            var tocustNos=   parent.document.getElementById("toCustNos").contentWindow.document.getElementById("toCustNod").innerHTML;
            var dfcustNos = fromCustNos.split(",");
            $("input[name='fromCustNo']:checkbox").each(function () {
                if (checkCustNos(dfcustNos, $(this).val())) {
                    $(this).attr("checked", true);
                }
            });
                $("input[name='toCustNo']:radio").each(function () {
                if (tocustNos==$(this).val()) {
                    $(this).attr("checked", true);
                }
            });


            $('input:checkbox').click(function () {
                var custNo = $(this).val();//确定用户编号 分别将其放入div中
                if ($(this).attr("checked")) {    //判断当前复选框 是否被选中了
                    if (parent.document.getElementById("fromCustNos").contentWindow.document.getElementById("fromCustNod").innerHTML != "") {
                        parent.document.getElementById("fromCustNos").contentWindow.document.getElementById("fromCustNod").innerHTML += "," + custNo;
                    } else {
                        parent.document.getElementById("fromCustNos").contentWindow.document.getElementById("fromCustNod").innerHTML = custNo;
                    }
                } else {
                    deleteThis(custNo);
                }
            });

            $("#combineSearch").click(function() {
                document.searchForm.submit();
            });

            $("#listTable").click(function(e) {
                if (e.target.name == "fromCustNo") {
                    var tmpToChecked = $(e.target).parent().parent().parent().find("[name='toCustNo']").attr("checked");
                    if ($(e.target).attr("checked") == true && tmpToChecked == true) {
                        return false;
                    }
                } else if (e.target.name == "toCustNo") {
                    $(e.target).parent().parent().parent().find("[name='fromCustNo']").attr("checked", false);
                    parent.document.getElementById("toCustNos").contentWindow.document.getElementById("toCustNod").innerHTML = $(e.target).parent().parent().parent().find("[name='toCustNo']").val();
                    deleteThis($(e.target).parent().parent().parent().find("[name='fromCustNo']").val());

                }
            });
        });

        function check() {
            var custNo = $("#custNo").val();
            $("#custNo").attr("value", $.trim(custNo));
            var firstName = $("#firstName").val();
            $("#firstName").attr("value", $.trim(firstName));
            var lastName = $("#lastName").val();
            $("#lastName").attr("value", $.trim(lastName));
            var email = $("#email").val();
            $("#email").attr("value", $.trim(email));
            var org = $("#org").val();
            $("#org").attr("value", $.trim(org));
            return true;
        }
    </script>
</head>
<body>
	<form action="combine_accounts!listCustNos.action" id="searchForm"
		name="searchForm" onsubmit="return check();">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="General_table" id="searchTable">
			<tr>
				<td>Customer No</td>
				<td><input name="filter_EQI_custNo" id="custNo"
					value="${custNo}" type="text" class="NFText" size="20" /></td>
				<td>First Name</td>
				<td><input name="filter_LIKES_firstName" id="firstName"
					value="${firstName}" type="text" class="NFText" size="20" />
				</td>
				<td>Last Name</td>
				<td><input name="filter_LIKES_lastName" id="lastName"
					value="${lastName}" type="text" class="NFText" size="20" />
				</td>
				<input type="hidden" name="filter_EQS_status" value="ACTIVE" />
			</tr>
			<tr>
				<td>Email</td>
				<td><input name="filter_LIKES_busEmail" id="email"
					value="${busEmail}" type="text" class="NFText" size="20" />
				</td>
				<td>Organization</td>
				<td><input name="filter_LIKES_organizationName" id="org"
					value="${organizationName}" type="text" class="NFText" size="20" />
				</td>
				<td>&nbsp;</td>
				<td><input type="submit" class="style_botton" value="Search" />
				</td>
			</tr>
		</table>
	</form>
	<table width="970" border="0" cellpadding="0" cellspacing="0"
		class="list_table">
		<tr>
			<th width="72">Customer No</th> 
			<th width="158">Name</th>
			<th width="250">Email</th>
			<th width="150">Organization</th>
			<th width="200">Address</th>
			<th width="45">Master</th>
			<th>Duplicate</th>
		</tr>
	</table>

	<div style="width: 990px; height: 250px; overflow: scroll;">
		<table width="970" border="0" cellpadding="0" cellspacing="0"
			class="list_table" id="listTable">
			<s:iterator value="pageCustomerBean.result">
				<tr>
					<td width="72">
						<div align="center">
							<a
								href="${global_url}customer/customer!edit.action?custNo=${custNo}&operation_method=view"
								target="_blank">&nbsp;${custNo}</a>
						</div></td> 
					<td width="158">${firstName}&nbsp;${midName}&nbsp;${lastName}</td>
					<td width="250"><s:property value="busEmail"/></td>
					<td width="150"><s:if
							test="%{organizationName != null && organizationName.length()>40}">
							<div align="center"> 
							     <s:property value="organizationName.substring(0,40)" escape="false"/>...
							</div>
						</s:if> <s:else>
							<div align="center">${organizationName}</div>
						</s:else>
					</td> 
					<td width="200">${state}&nbsp;${zipCode}&nbsp;${country}</td>
					<td width="45">
						<div align="center">
							<input type="radio" name="toCustNo" value="${custNo}" />
						</div></td>
					<td>
						<div align="center">
							<input type="checkbox" name="fromCustNo" value="${custNo}" />
						</div></td>
				</tr>
			</s:iterator>
		</table>
	</div>

	<div class="grayr">
		<jsp:include page="/common/db_pager.jsp">
			<jsp:param
				value="${ctx}/customer/combine_accounts!listCustNos.action"
				name="moduleURL" />
		</jsp:include>
	</div>

</body>
</html>