<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/common/taglib.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>scm</title>
    <link href="${global_css_url}scm.css" rel="stylesheet" type="text/css"/>
    <link href="${global_css_url}table.css" rel="stylesheet" type="text/css"/>
    <link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"/>
    <script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>
    <script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
    <script src="${global_js_url}jquery/jquery.validate.js?v=1"
            type="text/javascript"></script>
    <script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.dialog.all.js"></script>
    <script language="javascript" type="text/javascript" src="${global_js_url}util/util.js"></script>
    <script type="text/javascript">
        $(function() {
            var isSaved = false;
            $('#terrChoiceDialog').dialog({
                autoOpen: false,
                height: 440,
                width: 610,
                modal: true,
                bgiframe: true,
                buttons: {
                }
            });
            $('#terrForm').validate({
                errorClass:"validate_error",
                highlight: function(element, errorClass) {
                    $(element).addClass(errorClass);
                },
                unhighlight: function(element, errorClass, validClass) {
                    $(element).removeClass(errorClass);
                },
                invalidHandler: function(form, validator) {
                    $.each(validator.invalid, function(key, value) {
                        alert(value);
                        $(this).find("name=[" + key + "]").focus();
                        return false;
                    });
                },
                errorPlacement: function(error, element) {
                }
            });

            $("#save_btn").click(function() {
                if ($('#territoryId').val() == "") {
                    alert("Please select a sales territory.");
                    return false;
                }
                var formStr = $("#terrForm").serialize();
                $('#save_btn').attr("disabled", true);
                var sessionId = $("#sessionId").val();
                $.ajax({
                    type: "POST",
                    url: "territory_assign!saveTerr.action",
                    data: formStr,
                    dataType: 'json',
                    success: function(data, textStatus) {
                        if (hasException(data)) {
                            $('#save_btn').attr("disabled", false);
                        } else {
                            isSaved = true;
                            alert(data.message);
                            location.href = "territory_assign!load.action?sessionId=" + sessionId + "&referURL=select" + "&defaultTab=${defaultTab}";
                        }
                    },
                    error: function(xhr, textStatus) {
                        alert("System error! Please contact system administrator for help.");
                        $('#save_btn').attr("disabled", false);
                    }
                });
            });//end of {$("#save_btn").click};
        });
        function terrSelect() {
            $('#terrChoiceDialog').dialog("option", "open", function() {
                var htmlStr = '<iframe src="territory_assign!selectTerr.action" height="100%" width="610" scrolling="no" style="border:0px" frameborder="0"></iframe>';
                $('#terrChoiceDialog').html(htmlStr);
            });
            $('#terrChoiceDialog').dialog('open');
        }
    </script>
</head>

<body class="content" style="background-image:none;">
<div class="scm">
    <div class="title_content">
        <div class="title">Territory <s:if
                test="salesResourceAssignTerritory.salesTerritory.territoryName!=null">-<s:property
                value="salesResourceAssignTerritory.salesTerritory.territoryName"/></s:if></div>
    </div>

    <div class="input_box">
        <table border="0" cellpadding="0" cellspacing="0" class="General_table">
            <tr>
                <th width="131">Territory Code</th>
                <td width="410">
                    <s:textfield name="salesResourceAssignTerritory.salesTerritory.territoryCode" id="territoryCode"
                                 Class="NFText" size="20" readonly="true"/></td>
                <th>Status</th>
                <td valign="top">

                    <s:select id="status_sel"
                              list="#{'INACTIVE':'INACTIVE','ACTIVE':'ACTIVE'}"
                              name="salesResourceAssignTerritory.salesTerritory.status"
                              listKey="key"
                              listValue="value" disabled="true">
                    </s:select>
                </td>

            </tr>
            <tr>
                <th width="131">Territory Name</th>
                <td width="293">
                    <s:textfield name="salesResourceAssignTerritory.salesTerritory.territoryName" id="territoryName"
                                 Class="NFText" size="20" readonly="true"/>
                    <a href="#" onclick="terrSelect()"><img src="images/search.gif" width="16" height="16"
                                                            align="absmiddle"/></a>
                </td>
                <th></th>
                <td valign="top">
                </td>
            </tr>
            <tr>
                <th valign="top">Modified Date</th>
                <td>
                    <s:textfield name="salesResourceAssignTerritory.salesTerritory.modifyDate" id="modifyDate"
                                 Class="NFText" size="20" readonly="true">
                        <s:param name='value'>
                            <s:date name="salesResourceAssignTerritory.salesTerritory.modifyDate" format="yyyy-MM-dd"/>
                        </s:param>
                    </s:textfield>
                </td>
                <th>Modified By</th>
                <td valign="top">
                    <s:textfield name="salesResourceAssignTerritory.salesTerritory.modifiedName" id="modifiedName"
                                 Class="NFText" size="25" readonly="true"></s:textfield>
                </td>
            </tr>
        </table>
    </div>
    <s:form id="terrForm" Class="niceform">
        <div class="button_box">
            <input type="button" id="save_btn" name="Submit123" value="Save" class="search_input"/>
            <input type="button" name="Submit124" value="Cancel" class="search_input"
                   onclick="javascript:history.go(-1)"/>
        </div>
        <s:hidden name="salesResourceAssignTerritory.salesTerritory.territoryId" id="territoryId"></s:hidden>
        <s:hidden name="salesResourceAssignTerritory.salesTerritory.territoryCode" id="territoryCode"></s:hidden>
        <s:hidden name="sessionId" id="sessionId"></s:hidden>
        <s:hidden name="terrSessionId"></s:hidden>
        <s:hidden name="salesResourceAssignTerritory.assignId"></s:hidden>
        <s:hidden name="salesResourceAssignTerritory.createdBy"></s:hidden>
        <s:hidden name="salesResourceAssignTerritory.creationDate"></s:hidden>
    </s:form>

    <div id="terrChoiceDialog" title="Sales Territory Choice" style="display:none"></div>
</div>
</body>
</html>