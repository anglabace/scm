<%@ include file="/common/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>WorkCenter Search Result</title>
    <link href="${global_css_url}table.css" rel="stylesheet"
          type="text/css"/>
    <link href="${global_css_url}scm.css" rel="stylesheet" type="text/css"/>
    <script src="${global_js_url}jquery/jquery.js" type="text/javascript"
            language="javascript"></script>
    <script type="text/javascript" language="javascript"
            src="${global_js_url}util/util.js"></script>
    <script language="JavaScript" type="text/javascript">
        function edit(id) {
            window.parent.location = "organization!edit.action?id=" + id + "&operation_method=edit";
        }

        $(document).ready(function() {
            $("#resultTable tr:odd").find("td").addClass("list_td2");

            //复选框绑定: 全选|全不选
            $("#check_all").click(function() {
                $(":checkbox").each(function() {
                    this.checked = $("#check_all").get(0).checked;
                });
            });

            //删除选中的checkbox.
            $("#check_del").click(function() {
                if ($(":checkbox:gt(0):checked").length < 1) {
                    alert('Please select one at least!');
                    return;
                }
                if (!confirm('Are you sure to delete?')) {
                    return;
                }
                var url = "organization!showDelReasonDlg.action?delUrl=organization!delete.action&delObjType=Organization";
                parent.$('#delReasonDlg').dialog("option", "title", "Delete Organization");
                parent.$('#delReasonDlg').dialog("option", "open", function() {
                    htmlStr = '<iframe src="' + url + '" height="150" width="450" scrolling="no" style="border:0px" frameborder="0"></iframe>';
                    parent.$('#delReasonDlg').html(htmlStr);
                });
                parent.$('#delReasonDlg').dialog('open');
            });

        });
    </script>
</head>
<body class="content" style="background-image: none;">
<form action="orgGroupPage!delete.action" id="del_form">
    <div class="input_box">
        <div class="content_box">
            <table width="1010" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td>
                        <div style="margin-right: 17px;">
                            <table width="993" border="0" cellspacing="0" cellpadding="0"
                                   class="list_table">
                                <tr>
                                    <th width="46">
                                        <div align="left">
                                            <input name="checkbox2" type="checkbox" id="check_all"/>
                                            <img style="cursor: pointer"
                                                 src="${global_image_url}file_delete.gif" id="check_del"
                                                 alt="Delete" width="16" height="16" border="0"/>
                                        </div>
                                    </th>
                                    <th width="129">
                                        Organization ID
                                    </th>
                                    <th width="133">
                                        Organization Name
                                    </th>
                                    <th width="133">
                                        Organization Group
                                    </th>
                                    <th width="87">
                                        Address
                                    </th>
                                    <th width="85">
                                        Type
                                    </th>
                                    <th width="59">
                                        Status
                                    </th>
                                    <th width="78">
                                        Language
                                    </th>
                                    <th width="118">
                                        Parent Organization
                                    </th>
                                    <th>
                                        Supervisor
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
                                <s:iterator value="organizationPage.result">
                                    <tr>
                                        <td width="46">
                                            <input type="checkbox" value="${orgId}" name="groupIdList"/>
                                        </td>
                                        <td width="129">
                                            ${orgId}
                                        </td>
                                        <td width="133">
                                            <a href="javascript:void(0)" onclick="edit('${orgId}')"
                                               target="_parent">
                                                <c:if test="${!empty name}">
                                                    <s:if test="%{name != null && name.length()>40}">
                                                        <div align="center">&nbsp;<s:property
                                                                value="name.substring(0,20)+'......'"/></div>
                                                    </s:if>
                                                    <s:else>
                                                        <div align="center">&nbsp;${name}</div>
                                                    </s:else>
                                                </c:if>
                                            </a>


                                        </td>
                                        <td width="133">
                                                <s:if test="%{organizationGroup.status == 'ACTIVE'}">
                                            ${organizationGroup.name}</s:if>

                                        </td>
                                        <td width="87">
                                            ${addrLine1}<c:if test="${! empty addrLine2}"> </c:if>${addrLine2}<c:if
                                                test="${! empty addrLine3 }"> </c:if>${addrLine3}<c:if
                                                test="${! empty state && ! empty addrLine1 || ! empty addrLine2 || ! empty addrLine3 }">, </c:if>${state }<c:if
                                                test="${! empty zipCode }"> </c:if>${zipCode }<c:if
                                                test="${! empty country && ! empty state}">, </c:if>${country }
                                        </td>
                                        <td width="85">
                                            ${organizationType.name}
                                        </td>
                                        <td width="59">
                                            <c:if test="${activeFlag == 'Y'}">ACTIVE</c:if>
                                            <c:if test="${activeFlag == 'N'}">INACTIVE</c:if>
                                        </td>
                                        <td width="78">
                                            ${langName}
                                        </td>
                                        <td width="118">
                                            ${parentOrganization.name}
                                        </td>
                                        <td>

                                        </td>
                                    </tr>
                                </s:iterator>
                            </table>
                        </div>
                </tr>

                <tr>
                    <td>
                        <div class="grayr">
                            <jsp:include page="/common/db_pager.jsp">
                                <jsp:param value="${ctx}/organization!list.action"
                                           name="moduleURL"/>
                            </jsp:include>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</form>
</body>
</html>
