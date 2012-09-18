<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<base id="myBaseId" href="${global_url}"/>
<title>Production Operations</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css"/>
<link href="${global_css_url}table.css" rel="stylesheet"
      type="text/css"/>
<link href="${global_css_url}tab-view.css" rel="stylesheet"
      type="text/css"/>
<link href="${global_js_url}jquery/themes/base/ui.all.css"
      rel="stylesheet" type="text/css"/>
<script type="text/javascript" language="javascript"
        src="${global_js_url}jquery/jquery.js"></script>
<script type="text/javascript" language="javascript"
        src="${global_js_url}util/util.js"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1"
        type="text/javascript"></script>
<script type="text/javascript" src="${global_js_url}tab-view.js"></script>
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
<!-- 以下为country和state联动部分的JS. -->
<script type="text/javascript"
        src="${global_js_url}scm/gsCountryState.js?v=1"></script>
<s:if test='organization.country!=""'>
    <script>
        var countryIdNames = ['country'];
        var countryDefaults = ['${organization.country}'];
        var countryChangeHandlers = [''];

        var stateIdNames = ['state'];
        var stateDefaults = [''];
        var stateChangeHandlers = [''];
        $(document).ready(function() {
            initCountry();
            document.getElementById("state").value = '${organization.state}';//stateDefaults;
        });
    </script>
</s:if>
<s:else>
    <script>
        var countryIdNames = ['country'];
        var countryDefaults = ['US'];
        var countryChangeHandlers = [''];

        var stateIdNames = ['state'];
        var stateDefaults = [''];
        var stateChangeHandlers = [''];
        $(document).ready(function() {
            initCountry();
            document.getElementById("state").value = stateDefaults;
        });
    </script>

</s:else>

<script type="text/javascript">
$(function() {
    //1. 以下为页面初始化时执行的代码
    var flagVal = '${organization.taxExemptFlag}';
    if (flagVal == 'Y') {
        $("#tax_check").get(0).checked = true;
    } else {
        $("#tax_check").get(0).checked = false;
    }

    if ($("#status_hid").val() == '') {
        $("#status_hid").val('ACTIVE');
    }
    if ($("#notes_sel").val() == '0') {
        $("#note_btn").val("Add");
    } else {
        $("#note_btn").val("View/Edit");
    }
    var addrType = "${organization.addrType}";
    for (var i = 0; i < 4; i++) {
        if (addrType[i * 2] == 'Y') {
            $("#addrType-" + (i + 1)).get(0).checked = true;
        }
    }
    //end of 初始化

    //valide form content
    $('#org_form').validate({
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
        rules: {
            "organization.name": { required:true},
            "organization.balance":{decimal:true},
            "organization.creditLimit":{decimal:true},
            "organization.annualActualRevenue":{decimal:true},
            "organization.percentageOfSales":{decimal:true},
            "organization.saleVolume":{decimal:true},
            "organization.annualRevenueExpected":{decimal:true}
        },
        messages: {
            "organization.name": { required : "Please enter the name !" } ,
            "organization.balance":{decimal:"Please enter decimal for the balance!"},
            "organization.annualActualRevenue":{decimal:"Please enter decimal for the annual actual revenue!"},
            "organization.creditLimit":{decimal:"Please enter decimal for the credit limit!"},
            "organization.percentageOfSales":{decimal:"Please enter decimal for the percentage of sales!"},
            "organization.saleVolume":{decimal:"Please enter decimal for the saleVolume!"},
            "organization.annualRevenueExpected":{decimal:"Please enter decimal for annualRevenueExpected!"}
        },
        errorPlacement: function(error, element) {
        }
    });

    //bind save button event
    $("#save_btn").click(function() {
        var formStr = $('#org_form').serialize();
        var formExtStr = $('#org_ext_form').serialize();
        if (! $('#org_form').valid()) {
            return false;
        }
        if (! $("#tax_check").get(0).checked) {
            formStr += "&organization.taxExemptFlag=N";
        }
        var addrType = "&organization.addrType=";
        for (var i = 1; i <= 4; i++) {
            if ($("#addrType-" + i).get(0).checked) {
                addrType += "Y;";
            } else {
                addrType += "N;";
            }
        }
        formStr += addrType;

        var mystate = "organization.state=" + $("#state").val();
        formStr += "&" + mystate;
        if ($('#billingFrame').attr('src') == undefined || $('#billingFrame').attr('src') == '') {
            //页面没有加载不作任何处理
        } else {
            //页面加载且发生了修改
            if (window.frames["billingFrame"].$("#changed_hid").val() == 'Y') {
                var billInfo = window.frames["billingFrame"].$("#billing_form").serialize();
                formStr += "&" + billInfo;
            }
        }

        //Order preferenceFrame
        if ($('#preferenceFrame').attr('src') == undefined || $('#preferenceFrame').attr('src') == '') {
            //页面没有加载不作任何处理
        } else {
            //页面加载且发生了修改
            if (window.frames["preferenceFrame"].$("#changed_hid").val() == 'Y') {
                var preferenceInfo = window.frames["preferenceFrame"].$("#preference_form").serialize();
                formStr += "&" + preferenceInfo;
            }
        }
        $('#save_btn').attr("disabled", true);
        $.ajax({
            type: "POST",
            url: "organization!save.action",
            data: formStr + "&" + formExtStr,
            dataType: 'json',
            success: function(data, textStatus) {
                if (hasException(data)) {//有错误信息.
                    $('#save_btn').attr("disabled", false);
                } else {
                    alert(data.message);
                    window.location = "organization!search.action";
                    //window.history.go(-1);
                }
            },
            error: function(xhr, textStatus) {
                alert("failure");
                $('#save_btn').attr("disabled", false);
                return;
            }
        });

    });//end of {$("#save_btn").click};

    $("#notes_sel").change(function() {
        if ($("#notes_sel").val() == '0') {
            $("#note_btn").val("Add");
        } else {
            $("#note_btn").val("View/Edit");
        }
    });

    $("#status_sel").change(function() {
        $("#modify_btn").show();
        $('#modify_btn').attr("disabled", false);
    });

    $("#modify_btn").click(function() {
        $('#modify_btn').attr("disabled", true);
        $("#status_hid").val($("#status_sel").val());
    });

    $('#note_dlg').dialog({
        autoOpen: false,
        height: 260,
        width: 680,
        modal: true,
        bgiframe: true,
        buttons: {
        }
    });

    $("#note_btn").click(function() {
        var noteId = $("#notes_sel").val();
        var url = "organization!showInstruction.action?id=${organization.orgId}&sessNoteId=" + noteId + "&sessCustNo=" + $("#sessCustNo").val();
        $('#note_dlg').dialog("option", "open", function() {
            var htmlStr = '<iframe src="' + url + '" height="100%" width="100%" scrolling="yes" style="border:0px" frameborder="0"></iframe>';
            $('#note_dlg').html(htmlStr);
        });
        $('#note_dlg').dialog("option", "height", 260);
        $('#note_dlg').dialog("option", "width", 680);
        $('#note_dlg').dialog('open');
    });

    //修改status
    $("#status_btn").click(function() {
        var noteId = $("#notes_sel").val();
        var url = "organization!showUpdateStatus.action?srcStatus=" + $("#status_hid").val();
        $('#note_dlg').dialog("option", "open", function() {
            var htmlStr = '<iframe src="' + url + '" height="100%" width="100%" scrolling="no" style="border:0px" frameborder="0"></iframe>';
            $('#note_dlg').html(htmlStr);
        });
        $('#note_dlg').dialog('option', 'title', "Update Status");
        $('#note_dlg').dialog("option", "height", 260);
        $('#note_dlg').dialog("option", "width", 680);
        $('#note_dlg').dialog('open');

    });

    $("#new_div_btn").click(function() {
        if ('${organization.orgId}' == '') {
            alert("Please save the organization first !");
            return;
        }
        var url = "division!add.action?orgId=" + '${organization.orgId}';
        $('#note_dlg').dialog("option", "title", "Add New Division");
        $('#note_dlg').dialog("option", "open", function() {
            var htmlStr = '<iframe src="' + url + '" height="100%" width="100%" scrolling="no" style="border:0px" frameborder="0"></iframe>';
            $('#note_dlg').html(htmlStr);
        });
        $('#note_dlg').dialog("option", "height", 260);
        $('#note_dlg').dialog("option", "width", 680);
        $('#note_dlg').dialog('open');
    });

    //弹出New sub organization新建子的Organization
    $("#new_sub_btn").click(function() {
        if ('${organization.orgId}' == '') {
            alert("Please save the organization first !");
            return;
        }
        var url = "organization!showListForAddSub.action?orgId=" + '${organization.orgId}';
        $('#note_dlg').dialog("option", "title", "Add Sub Organizations");
        $('#note_dlg').dialog("option", "open", function() {
            var htmlStr = '<iframe src="' + url + '" height="100%" width="100%" scrolling="no" style="border:0px" frameborder="0"></iframe>';
            $('#note_dlg').html(htmlStr);
        });
        $('#note_dlg').dialog("option", "height", 400);
        $('#note_dlg').dialog("option", "width", 700);
        $('#note_dlg').dialog('open');
    });

    //点击'Subsidiaries'时才加载子页面
    $('#tabTabdhtmlgoodies_tabView1_1').click(function () {
        if ($('#subListFrame').attr('src') == undefined || $('#subListFrame').attr('src') == '') {
            $('#subListFrame').attr('src', "organization!showSubList.action?id=${organization.orgId}");
        }
    });
    //点击'Order Preferences'时才加载子页面
    $('#tabTabdhtmlgoodies_tabView1_2').click(function () {
        if ($('#preferenceFrame').attr('src') == undefined || $('#preferenceFrame').attr('src') == '') {
            $('#preferenceFrame').attr('src', "organization!showPreference.action?id=${organization.orgId}");
        }
    });
    //点击'Billing Info' 时才加载子页面.
    $('#tabTabdhtmlgoodies_tabView1_4').click(function () {
        if ($('#billingFrame').attr('src') == undefined || $('#billingFrame').attr('src') == '') {
            $('#billingFrame').attr('src', "organization!showBilling.action?id=${organization.orgId}");
        }
    });

//    $('#balance').validator({
//			format: 'decimal',
//			invalidEmpty: true,
//			correct: function() {
//				$('#validation_result').text('VALID');
//			},
//			error: function() {
//				$('#validation_result').text('INVALID');
//			}
//		});
//
//		$('#button_validate').click(function(e) {
//			$('#field').validator('validate');
//		});

});//end of jquery init.
</script>
<script type="text/javascript" language="javascript">
    $(document).ready(function() {
        // 验证值小数位数不能超过两位
        jQuery.validator.addMethod("decimal", function(value, element) {
            var decimal = /^-?\d+(\.\d{1,2})?$/;
            return this.optional(element) || (decimal.test(value));
        }, $.validator.format("小数位数不能超过两位!"));
    });
</script>
</head>
<body class="content">
<div class="scm">
<div class="title_content">
    <div class="title">
        Organization Information
    </div>
</div>
<div class="input_box">
<div class="content_box">
<form method="get" id="org_form" class="niceform">
<input type="hidden" name="organization.parentOrgId"
       value="${organization.parentOrgId}"/>
<input type="hidden" name="organization.orgId"
       value="${organization.orgId}"/>
<input type="hidden" name="sessCustNo" id="sessCustNo"
       value="${sessCustNo}"/>
<table border="0" cellpadding="0" cellspacing="0"
       class="General_table">
<tr>
    <th width="146">
        Organization Name
    </th>
    <td colspan="2">
        <input name="organization.name" value="${organization.name}"
               type="text" class="NFText" size="35"/>
    </td>
    <th width="130">
        Description
    </th>
    <td colspan="2">
        <input name="organization.description"
               value="${organization.description}" type="text" class="NFText"
               size="41"/>
    </td>
</tr>
<tr>
    <th valign="top">
        Organization Group
    </th>
    <%--zhougang xiugai 2011 05 07  --%>
    <td colspan="2">
        <s:select id="type_sel" name="organization.orgGroupId" label="选择"
                  list="#request.orgGroupList" listKey="id" listValue="name" headerKey=""
                  headerValue="please select...."
                  value="organization.orgGroupId" cssStyle="width: 207px;"></s:select>
    </td>
    <th>
        Category
    </th>
    <td colspan="2">
        <s:select id="category_sel" name="organization.categoryId"
                  list="#request.orgCategoryList" listKey="categoryId"
                  listValue="name" value="organization.categoryId"
                  cssStyle="width: 238px;"></s:select>
    </td>
</tr>
<tr>
    <th valign="top">
        <span class="important">*</span>Type
    </th>
    <td colspan="2">
        <s:select id="type_sel" name="organization.typeId"
                  list="#request.orgTypeList" listKey="orgTypeId"
                  listValue="name" value="organization.typeId"
                  cssStyle="width: 207px;"></s:select>
    </td>
    <th>
        Tax ID
    </th>
    <td colspan="2">
        <input name="organization.taxID" value="${organization.taxID}"
               type="text" class="NFText" size="18"/>
        <input type="checkbox" name="organization.taxExemptFlag"
               id="tax_check" value="Y"/>
        Tax exempt
    </td>
</tr>
<tr>
    <th valign="top">
        D-U-N-S
    </th>
    <td colspan="2">
        <input name="organization.duns" value="${organization.duns}"
               type="text" class="NFText" size="35"/>
    </td>
    <th>
        Reference No
    </th>
    <td colspan="2">
        <input name="organization.referenceNo"
               value="${organization.referenceNo}" type="text" class="NFText"
               size="41"/>
    </td>
</tr>
<tr>
    <th valign="top">
        Language
    </th>
    <td colspan="2">
        <s:select list="#request.languageList" listKey="langCode"
                  listValue="name" value="organization.langCode"
                  name="organization.langCode" cssStyle="width:207px;"></s:select>
    </td>
    <th>
        Web
    </th>
    <td colspan="2">
        <input name="organization.web" value="${organization.web}"
               type="text" class="NFText" size="41"/>
    </td>
</tr>
<tr>
    <th valign="top">
        No of Employees
    </th>
    <td colspan="2">
        <input name="organization.noOfEmployees"
               value="${organization.noOfEmployees}" type="text"
               class="NFText2" size="35"/>
    </td>
    <th>
        Status
    </th>
    <td width="246">
        <c:set var="flagStatus" value="ACTIVE"></c:set>
        <c:set var="flagStatusHid" value="Y"></c:set>
        <c:if test="${organization.activeFlag == 'N'}">
            <c:set value="INACTIVE" var="flagStatus"></c:set>
            <c:set var="flagStatusHid" value="N"></c:set>
        </c:if>
        <input type="text" readonly="readonly" value="${flagStatus}" class="NFText" size="41" id="status_txt"/>
        <input type="hidden" name="organization.activeFlag" value="${flagStatusHid}" id="status_hid"/>
        <input type="hidden" name="organization.updateStatusReason" value="${organization.updateStatusReason}"
               id="reason_hid"/>
    </td>
    <td>
        <input name="Submit3"
               type="button" class="style_botton" value="Modify" id="status_btn"/>
    </td>
</tr>
<tr>
    <th valign="top">
        Credit Status
    </th>
    <td colspan="2">
        <s:select id="credit_sel" name="organization.creditStatus"
                  list="dropDownMap['CREDIT_STATUS']" listKey="value"
                  listValue="text" value="organization.creditStatus"
                  cssStyle="width:207px;"></s:select>
    </td>
    <th>
        Credit Limit
    </th>
    <td colspan="2">
    <%--    <input name="organization.creditLimit" type="text"
               class="NFText2" value="${organization.creditLimit}" size="41"/> --%>
        <input name="organization.creditLimit" type="text"
               class="NFText2" value="${Creditlimit1}" size="41"/>
    </td>
</tr>
<tr>
    <th valign="top">
        Balance
    </th>
    <td colspan="2">
      <%--  <input name="organization.balance" id="balance"
               value="${organization.balance}" type="text" class="NFText2"
               size="35"/>  --%>
        <input name="organization.balance" id="balance"
               value="${Balance1}" type="text" class="NFText2"
               size="35"/>
    </td>
    <th>
        Rating
    </th>
    <td colspan="2">
        <s:select id="rating_sel" name="organization.rating"
                  list="dropDownMap['ORGANIZATION_RATING']" listKey="value"
                  listValue="text" value="organization.rating"
                  cssStyle="width:238px;"></s:select>
    </td>
</tr>
<tr>
    <th valign="top">
        Annual Revenue Expected
    </th>
    <td colspan="2">
       <%-- <input name="organization.annualRevenueExpected"
               value="${organization.annualRevenueExpected}" type="text"
               class="NFText2" size="35"/>  --%>
        <input name="organization.annualRevenueExpected"
               value="${AnnualExpected1}" type="text"
               class="NFText2" size="35"/>
    </td>
    <th>
        Annual Actual Revenue
    </th>
    <td colspan="2">
  <%--      <input name="organization.annualActualRevenue"
               value="${organization.annualActualRevenue}" type="text"
               class="NFText2" size="41"/>  --%>
        <input name="organization.annualActualRevenue"
               value="${Anr1}" type="text"
               class="NFText2" size="41"/>
    </td>
</tr>
<tr>
    <th valign="top">
        Sales Volume
    </th>
    <td colspan="2">
<%--        <input name="organization.saleVolume"
               value="${organization.saleVolume}" type="text" class="NFText2"
               size="35"/> --%>
        <input name="organization.saleVolume"
               value="${saleVolume1}" type="text" class="NFText2"
               size="35"/>
    </td>
    <th>
        Percentage of Sales
    </th>
    <td colspan="2">
     <%--   <input name="organization.percentageOfSales"
               value="${organization.percentageOfSales}" type="text"
               class="NFText2" size="41"/> --%>
        <input name="organization.percentageOfSales"
               value="${Percentageofsales1}" type="text"
               class="NFText2" size="41"/>
    </td>
</tr>
<tr>
    <th valign="top">
        Date of First Sale
    </th>
    <td colspan="2">

        <input name="organization.firstSalesDate" readonly="readonly"
               value='<s:date name="organization.firstSalesDate"
										format="yyyy-MM-dd" />'
               type="text" class="NFText" size="35"/>
    </td>
    <th>
        Parent Organization
    </th>
    <td colspan="2">
        <input readonly="readonly" name=""
               value="${organization.parentOrganization.name}" type="text"
               class="NFText" size="41"/>
    </td>
</tr>
<tr>
    <th valign="top">
        Instructions &amp; Notes
    </th>
    <td width="200">
        <select name="select40" id="notes_sel" style="width: 207px;">
            <option value="0">
                Add Instructions or Notes
            </option>
            <s:if test="noteList != null && noteList.size > 0">
                <s:iterator value="noteList" id="oneNote">
                    <option selected="selected" value="${oneNote.id}">
                          ${oneNote.type}-instr-org
                    </option>
                </s:iterator>
            </s:if>
        </select>
    </td>
    <td width="124">
        <input type="button" id="note_btn" class="style_botton"
               value="Add"/>
    </td>
    <th>
        &nbsp;
    </th>
    <td colspan="2">
        &nbsp;
    </td>
</tr>


</table>
</form>
</div>
</div>
<div id="note_dlg" title="Instructions & Notes"></div>
<div id="dhtmlgoodies_tabView1">
<div class="dhtmlgoodies_aTab">
    <iframe id="divIframe"
            src="division!list.action?orgId=${organization.orgId}"
            width="100%" height="220" frameborder="0" scrolling="no"></iframe>
    <div style="text-align: center;">
        <input name="Submit4" type="button" class="style_botton"
               value="New" id="new_div_btn"/>
    </div>
</div>
<div class="dhtmlgoodies_aTab">
    <iframe id="subListFrame" name="subListFrame"
            src="" width="100%" height="220" frameborder="0" scrolling="auto"></iframe>
    <div style="text-align: center;">
        <input name="Submit4" type="button" class="style_botton"
               value="New" id="new_sub_btn"/>
    </div>
</div>
<div class="dhtmlgoodies_aTab">
    <iframe id="preferenceFrame" name="preferenceFrame"
            src="" width="100%" height="245" frameborder="0" scrolling="auto"></iframe>
</div>
<div class="dhtmlgoodies_aTab">
    444
</div>
<div class="dhtmlgoodies_aTab">
    <iframe id="billingFrame" name="billingFrame"
            src="" width="100%" height="220" frameborder="0" scrolling="auto"></iframe>
</div>
<div class="dhtmlgoodies_aTab">
    <form method="get" id="org_ext_form" class="niceform">
        <table border="0" cellpadding="0" cellspacing="0"
               class="General_table">
            <tr>
                <th width="138">
                    Phone
                </th>
                <td style="margin: 0px; padding: 0px;">
                    <table border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td>
                                <input name="organization.phone"
                                       value="${organization.phone}" type="text" class="NFText"
                                       size="16"/>
                            </td>
                            <th width="26">
                                Ext
                            </th>
                            <td>
                                <input name="organization.phoneExt"
                                       value="${organization.phoneExt}" type="text" class="NFText"
                                       size="5"/>
                            </td>
                        </tr>
                    </table>
                </td>
                <th width="138">
                    Alternative Phone
                </th>
                <td style="margin: 0px; padding: 0px;">
                    <table border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td>
                                <input name="organization.altPhone"
                                       value="${organization.altPhone}" type="text" class="NFText"
                                       size="16"/>
                            </td>
                            <th width="26">
                                Ext
                            </th>
                            <td>
                                <input name="organization.altPhoneExt"
                                       value="${organization.altPhoneExt}" type="text"
                                       class="NFText" size="5"/>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <th valign="top">
                    Fax
                </th>
                <td style="margin: 0px; padding: 0px;">
                    <table border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td>
                                <input name="organization.fax" value="${organization.fax}"
                                       type="text" class="NFText" size="16"/>
                            </td>
                            <th width="26">
                                Ext
                            </th>
                            <td>
                                <input name="organization.faxExt"
                                       value="${organization.faxExt}" type="text" class="NFText"
                                       size="5"/>
                            </td>
                        </tr>
                    </table>
                </td>
                <th>
                    &nbsp;
                </th>
                <td>
                    &nbsp;
                </td>
            </tr>
            <tr>
                <th valign="top">
                    Address
                </th>
                <td width="385">
                    <input name="organization.addrLine1"
                           value="${organization.addrLine1}" type="text" class="NFText"
                           size="35"/>
                </td>
                <th>
                    &nbsp;
                </th>
                <td>
                    &nbsp;
                </td>
            </tr>
            <tr>
                <th valign="top">
                    &nbsp;
                </th>
                <td>
                    <input name="organization.addrLine2"
                           value="${organization.addrLine2}" type="text" class="NFText"
                           size="35"/>
                </td>
                <th>
                    &nbsp;
                </th>
                <td>
                    &nbsp;
                </td>
            </tr>
            <tr>
                <th valign="top">
                    &nbsp;
                </th>
                <td>
                    <input name="organization.addrLine3"
                           value="${organization.addrLine3}" type="text" class="NFText"
                           size="35"/>
                </td>
                <th>
                    &nbsp;
                </th>
                <td>
                    &nbsp;
                </td>
            </tr>
            <tr>
                <th valign="top">
                    City
                </th>
                <td>
                    <input name="organization.city" value="${organization.city}"
                           type="text" class="NFText" size="35"/>
                </td>
                <th>
                    Zip Code
                </th>
                <td>
                    <input name="organization.zipCode"
                           value="${organization.zipCode}" type="text" class="NFText"
                           size="35"/>
                </td>
            </tr>
            <tr>
                <th valign="top">
                    State
                </th>
                <td>
                    <select name="state" id="state"></select>
                </td>
                <th>
                    Country
                </th>
                <td>
                    <select name="organization.country" id="country">
                    </select>
                </td>
            </tr>
            <tr>
                <th valign="top">
                    &nbsp;
                </th>
                <td>
                    <input name="checkbox5" type="checkbox" id="addrType-1"/>
                    Ship Address
                </td>
                <th>
                    &nbsp;
                </th>
                <td>
                    <input name="checkbox3" type="checkbox" id="addrType-2"/>
                    Invoice Address
                </td>
            </tr>
            <tr>
                <th valign="top">
                    &nbsp;
                </th>
                <td>
                    <input name="checkbox6" type="checkbox" id="addrType-3"/>
                    Pay-Form Address
                </td>
                <th>
                    &nbsp;
                </th>
                <td>
                    <input name="checkbox7" type="checkbox" id="addrType-4"/>
                    Remit-To Address
                </td>
            </tr>
        </table>
    </form>
</div>
</div>
<div class="button_box">
    <saveButton:saveBtn parameter="${operation_method}"
                        disabledBtn='<input type="button" name="Submit123" value="Save" class="search_input" disabled="disabled" />'
                        saveBtn='<input type="button" name="Submit123" value="Save" class="search_input" id="save_btn" />'/>
    <input type="button" name="Submit124" value="Cancel"
           class="search_input"
           onclick="javascript:history.go(-1);"/>
</div>
</div>
<div id="sel_res_dlg" title="Select Resource"></div>
<script type="text/javascript">
    initTabs('dhtmlgoodies_tabView1', Array('Divisions', 'Subsidiaries', 'Order Preferences', 'Purchase Preferences', 'Billing Info', 'Location'), 0, 1125, 240);
    disableTabByTitle('Purchase Preferences');
    var isSaved = false;
    window.onbeforeunload = function() {
        if (isSaved === false) {
            return 'Do you want to leave without saving data?';
        }
    }
</script>
</body>
</html>