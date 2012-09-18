<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${global_css_url}scm.css" rel="stylesheet" type="text/css"/>
    <link href="${global_css_url}table.css" rel="stylesheet"
          type="text/css"/>
    <script language="javascript" type="text/javascript"
            src="${global_js_url}jquery/jquery.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('tr:even >td').addClass('list_td2');
        });
    </script>
    <style type="text/css">
        <!--
        body {
            padding: 10px 0px 0px 10px;
            width: 480px;
        }

        -->
    </style>
    <title>Source list</title>
</head>
<body>
<div style="overflow-y: scroll; width: 477px; height: 180px;">
    <table width="460" border="0" cellspacing="0" cellpadding="0"
           class="list_table">
        <tr>
            <th width="100">
                Source Code
            </th>
            <th width="100">
                Source Name
            </th>
            <th width="100">
                Source status
            </th>

            <th>
                Source Description
            </th>

        </tr>
        <s:if test="source != null">
        <tr>
            <td width="100" style="word-break:break-all">
                &nbsp; ${source.code}
            </td>
            <td width="100" style="word-break:break-all">
                &nbsp; ${source.name}
            </td>
                <td>
                &nbsp; ${source.status}
            </td>
            <td>
                &nbsp; ${source.description}
            </td>

        </tr>
        </s:if>
    </table>
</div>
</body>

</html>