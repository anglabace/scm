<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="${global_css_url}scm.css" rel="stylesheet" type="text/css"/>
    <link href="${global_css_url}table.css" rel="stylesheet" type="text/css"/>
    <link href="${global_css_url}tab-view.css" rel="stylesheet"
          type="text/css"/>
    <link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
          type="text/css"/>
    <link type="text/css"
          href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet"/>
    <script language="javascript" type="text/javascript"
            src="${global_js_url}jquery/jquery.js"></script>
    <script type="text/javascript" src="${global_js_url}util/util.js"></script>
    <script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
    <script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
    <script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
    <script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
    <script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
    <script src="${global_js_url}jquery/jquery.validate.js?v=1" type="text/javascript"></script>
    <script src="${global_js_url}/recordTime.js" type="text/javascript"></script>
    <script language="javascript" type="text/javascript" src="${global_js_url}scm/product/manager_task.js?v=2"></script>

    <style type="text/css">
        <!--
        body {
            margin-left: 10px;
            margin-top: 10px;
        }

        -->
    </style>

    <script type="text/javascript">
        function editIt(sessCaseId) {
            var url = "cust_case!input.action?custNo=${custNo}&sessCustNo=${sessCustNo}&sessCaseId=" + sessCaseId;
             parent.parent.$('#editCaseDialog')
                    .dialog(
                    "option",
                    "open",
                    function() {
                        var htmlStr = '<iframe src="' + url + '" height="480" width="650" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
                        parent.parent.$('#editCaseDialog').html(htmlStr);
                    });
             parent.parent.$('#editCaseDialog').dialog('open');
        }
        $(function() {
        
        });
        $(document).ready(function() {
            $('tr:even >td').addClass('list_td2');
        });
    </script>
</head>

<body>
<div style="width: 650px; height: 180px; overflow-y: scroll;">
    <table width="630" border="0" cellpadding="0" cellspacing="0"
           class="list_table">
        <tr>
        
            <th width="50">Case Id</th>
            <th width="60">Catalog No</th>
            <th width="100">Subject</th>
            <th width="80">Open Date</th>
            <th width="80">Type</th>
            <th width="60">Priority</th>
            <th width="50">Status</th>
            <th width="60">Order No</th>
            <th width="150">Potential Liability</th>
        </tr>
        <s:iterator value="custCaseMap">
            <tr>
                <td width="50">
                    <div style="width: 40px; overflow: hidden; text-align: center;">
                        <s:if test="value.caseId > 0">${value.caseId}</s:if>
                    </div>
                </td>
                <td width="60">${value.catalogNo }</td>
                <td width="100">&nbsp;<a href="#" onclick="editIt('${key}')">${value.subject}</a>
                </td>
                <td width="80">&nbsp;<s:date name="value.creationDate"
                                             format="yyyy-MM-dd"/>
                </td>
                <td width="80">&nbsp;${value.type}</td>
                <td width="60">&nbsp;${value.priority}</td>
                <td width="50">&nbsp;${value.status}</td>
                <td width="60">&nbsp;${value.orderNo}</td>
                <td width="150">&nbsp;${value.potLiability}</td>
            </tr>

        </s:iterator>
    </table>
</div>


</body>
</html>