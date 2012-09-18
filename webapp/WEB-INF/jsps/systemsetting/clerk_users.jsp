<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Order Management</title>
    <link href="${global_css_url}scm.css" rel="stylesheet" type="text/css"/>
    <link href="${global_css_url}table.css" rel="stylesheet" type="text/css"/>
    <script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
    <script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
    <link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css"/>
    <script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
    <link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css"/>
    <script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
    <link media="all" type="text/css" rel="stylesheet" href="${global_css_url}openwin.css">
    <style type="text/css">
        <!--
        .content .scm #dhtmlgoodies_tabView1 .dhtmlgoodies_aTab .General_table fieldset {
            margin: 4px;
        }

        -->
    </style>
    <script>
        $(document).ready(function() {
            $('tr:odd >td').addClass('list_td2');
        });
        var userid,username,employeeid;
        function select(id, name, employeeId) {
            userid = id;
            username = name;
            employeeid = employeeId;
            return false;
        }
        function addUser() {
            if (!userid || !username) {
                alert("please select the employee.");
                return;
            }
            //alert(username+""+employeeid+""+userid);
            parent.$("#username").val(username);
            // alert(parent.$("#shipClerk.clerkId").value=userid);
            parent.$("#employeeId").val(employeeid);
            parent.$("#clerkId").val(userid);
            window.parent.closeiframe();
        }
        function check() {
            var filter_LIKES_clerkName = $("#filter_LIKES_clerkName").val();
            var filter_EQI_userId = $("#filter_EQI_userId").val();
            var filter_LIKES_department = $("#filter_LIKES_department").val();
            $("#filter_LIKES_clerkName").attr("value", $.trim(filter_LIKES_clerkName));
            $("#filter_EQI_userId").attr("value", $.trim(filter_EQI_userId));
            $("#filter_LIKES_department").attr("value", $.trim(filter_LIKES_department));
            return true;
        }
    </script>
</head>
<body>

<table width="650" border="0" cellspacing="3" cellpadding="0" id="table11" bgcolor="#96BDEA">
    <tr>
        <td bgcolor="#FFFFFF">
            <table width="650" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td height="39" align="left" valign="top" background="${global_image_url}header_bg.gif">
                        <div class="line_l_new">Select Employee</div>
                        <div class="line_r_new" onclick="window.parent.closeiframe()"><img
                                src="${global_image_url}close.gif" width="11" height="11"/>Close
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <s:form action="emarketing_group_srch!getClerkUser" name="docTempSearch" method="get"  onsubmit="return check();">
                            <table border="0" cellpadding="0" cellspacing="0" class="General_table" align="center">
                                <tr>
                                    <td>User ID</td>
                                    <td>
                                        <s:textfield id="filter_EQI_userId" name="filter_EQI_userId"
                                                     type="text" class="NFText" size="10" onkeypress="if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false;">
                                            <s:param name="value">
                                                <s:property value="filter_EQI_userId"/>
                                            </s:param>
                                        </s:textfield>
                                    </td>
                                    <td>Employee Name</td>
                                    <td>
                                        <s:textfield id="filter_LIKES_clerkName" name="filter_LIKES_employeeName"
                                                     type="text" class="NFText" size="20">
                                            <s:param name="value">
                                                <s:property value="filter_LIKES_employeeName"/>
                                            </s:param>
                                        </s:textfield>
                                    </td>
                                    <td>Depart</td>
                                    <td>
                                        <s:textfield id="filter_LIKES_department" name="filter_LIKES_department"
                                                     type="text" class="NFText" size="20">
                                            <s:param name="value">
                                                <s:property value="filter_LIKES_department"/>
                                            </s:param>
                                        </s:textfield>
                                    </td>
                                    <td><input name="Submit3" type="submit" class="style_botton" value="Search"/> </td>
                                </tr>
                            </table>
                        </s:form>
                    </td>
                </tr>
                <tr>
                    <td style="padding-left:40px;padding-top:10px;">
                        <table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td>
                                    <table border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td>
                                                <table width="580px" border="0" cellpadding="0" cellspacing="0" class="list_table">
                                                    <tr>
                                                        <th width="120">Employee ID</th>
                                                        <th>Employee Name</th>
                                                    </tr>
                                                </table>
                                                <div class="list_box" style="height:180px;width:580px;">
                                                    <table width="560" border="0" cellpadding="0" cellspacing="0"
                                                           class="list_table">
                                                        <s:iterator status="users" value="users.result">
                                                            <tr>
                                                                <td width="120">
                                                                    <a href="#" onclick="select('<s:property value="userId"/>','<s:property value="employee.employeeName"/>','<s:property value="employee.employeeId"/>')"
                                                                    style="padding-left: 20px;" id="<s:property value="userId"/>"> <s:property  value="employee.employeeId"/></a></td>
                                                                <td><span style="padding-left: 10px;"><s:property
                                                                        value="employee.employeeName"/> </span></td>
                                                            </tr>
                                                        </s:iterator>
                                                    </table>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="grayr"> &nbsp;
                                                    <jsp:include page="/common/db_pager.jsp">
                                                        <jsp:param
                                                                value="${ctx}/emarketing_group_srch!getClerkUser.action"
                                                                name="moduleURL"/>
                                                    </jsp:include>
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td height="60" align="center">
                                    <input type="button" name="Submit62" class="style_botton" value="Select"
                                           onclick="addUser()"/>
                                    <input type="submit" name="Submit622" value="Cancel" class="style_botton"
                                           onclick="window.parent.closeiframe()"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

</body>
</html>
