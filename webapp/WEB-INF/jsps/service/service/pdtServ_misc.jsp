<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
    <base href="${global_url}"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>scm</title>
    <link href="${global_css_url}scm.css" rel="stylesheet" type="text/css"/>
    <link href="${global_css_url}table.css" rel="stylesheet" type="text/css"/>
    <script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
    <script src="${global_js_url}jquery/jquery.validate.js?v=1" type="text/javascript"></script>
    <script>
        var type = "Service";
        $(function() {
            $('#miscForm').validate({
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
                        $("#" + key).focus();
                        return false;
                    });
                },
                rules: {
                    "serviceDTO.sellingPriceCmsn": { number:true },
                    "serviceDTO.grossProfitCmsn": { number:true },
                    "serviceDTO.unitRateCmsn": { number:true },
                    "serviceDTO.royaltyService.sellingPriceCmsn": { number:true },
                    "serviceDTO.royaltyService.grossProfitCmsn": { number:true },
                    "serviceDTO.royaltyService.unitRateCmsn": { number:true },
                    "serviceDTO.returnPoints": { number:true },
                    "serviceDTO.priceByPoints": { number:true }
                },
                messages: {
                    "serviceDTO.sellingPriceCmsn": { number: "This 'Selling Price' must be a digit!" },
                    "serviceDTO.grossProfitCmsn": { number:"This 'Gross Profit' must be a digit!" },
                    "serviceDTO.unitRateCmsn": { number:"This 'Per Unit Rate' must be a digit!" },
                    "serviceDTO.royaltyService.sellingPriceCmsn": { number:"This 'Selling Price' must be a digit!" },
                    "serviceDTO.royaltyService.grossProfitCmsn": { number:"This 'Gross Profit' must be a digit!" },
                    "serviceDTO.royaltyService.unitRateCmsn": { number:"This 'Per Unit Rate' must be a digit!" },
                    "serviceDTO.returnPoints" :{ number:"This 'Points Received When Purchased' must be a digit!" },
                    "serviceDTO.priceByPoints": { number:"This 'User Points To Buy This Product ' must be a digit!" }
                },
                errorPlacement: function(error, element) {
                }
            });
        });

    </script>
    <script type="text/javascript" language="javascript">
        $(function() {
                   $('#sellingPrice').click(function() {
                       var std1 = $("#sellingPriceCmsn");
                       var std2 = $("#grossProfitCmsn");
                       var std3 = $("#unitRateCmsn");
                       $("input[name='radie'][value='0'").attr("checked", true);
                       $("input[name='radie'][value='1'").attr("checked", false);
                       $("input[name='radie'][value='2'").attr("checked", false);
                       std2.val("");
                       std3.val("");
                       std2.attr("disabled", "disabled");
                       std3.attr("disabled", "disabled");
                       std1.attr("disabled", false);
                   });
                   $('#grossProfit').click(function() {
                       var std1 = $("#sellingPriceCmsn");
                       var std2 = $("#grossProfitCmsn");
                       var std3 = $("#unitRateCmsn");
                       $("input[name='radie'][value='0'").attr("checked", false);
                       $("input[name='radie'][value='1'").attr("checked", true);
                       $("input[name='radie'][value='2'").attr("checked", false);
                       std2.attr("disabled", false);
                       std1.val("");
                       std3.val("");
                       std3.attr("disabled", "disabled");
                       std1.attr("disabled", "disabled");
                   });
                   $('#unitRate').click(function() {
                       var std1 = $("#sellingPriceCmsn");
                       var std2 = $("#grossProfitCmsn");
                       var std3 = $("#unitRateCmsn");
                       $("input[name='radie'][value='0'").attr("checked", false);
                       $("input[name='radie'][value='1'").attr("checked", false);
                       $("input[name='radie'][value='2'").attr("checked", true);
                       std1.val("");
                       std2.val("");
                       std2.attr("disabled", "disabled");
                       std3.attr("disabled", false);
                       std1.attr("disabled", "disabled");
                   });

               });


    </script>
    <script src="${global_js_url}/scm/serv_misc.js"></script>
</head>
<body class="content" style="background:#FFFFFF;">
<div class="scm" style="background:#FFFFFF;">
    <form id="miscForm">
        <table width="970" border="0" cellpadding="0" cellspacing="0" class="General_table">
            <tr>
                <td valign="top">
                    <fieldset style="height:100px;">
                        <legend>Sales Commissions Calculations</legend>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
                            <tr>
                                <th width="45%">  <input type="radio" value="0" id="sellingPrice" name="radie"/>Selling Price</th>
                                <td><input name="serviceDTO.sellingPriceCmsn" type="text" class="NFText2" size="10" id="sellingPriceCmsn" />%
                                </td>
                            </tr>
                            <tr>
                                <th><input type="radio" value="1" id="grossProfit" name="radie"/>Gross Profit</th>
                                <td><input name="serviceDTO.grossProfitCmsn" type="text" class="NFText2" size="10" id="grossProfitCmsn"/>%
                                </td>
                            </tr>
                            <tr>
                                <th><input type="radio" value="2" id="unitRate" name="radie"/>Per Unit Rate $</th>
                                <td><input name="serviceDTO.unitRateCmsn" type="text" class="NFText2" size="10" id="unitRateCmsn"/></td>
                            </tr>
                        </table>
                    </fieldset>
                </td>
                <td valign="top">
                    <fieldset>
                        <legend>Royalties Calculations</legend>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
                            <tr>
                                <th>Supplier for Royalty Payment</th>
                                <td colspan="3"><input name="serviceDTO.royaltyService.royaltyName" id="royaltyName"
                                                       type="text" class="NFText" value="${royolty.royaltyName}"
                                                       size="35" readonly="readonly"/>
                                    <input name="serviceDTO.royaltyService.royaltyId" type="hidden" id="royaltyId"
                                           value="${royolty.royaltyId}"/>
                                    <img src="images/search.jpg" id="miscPickerTrigger" width="16" height="16"/></td>
                            </tr>
                            <tr>
                                <th> Selling Price</th>
                                <td><input name="serviceDTO.royaltyService.sellingPriceCmsn" type="text" class="NFText2"
                                           value="${royolty.sellingPriceCmsn}" size="10"/> %
                                </td>
                                <th>Gross Profit</th>
                                <td><input name="serviceDTO.royaltyService.grossProfitCmsn" type="text" class="NFText2"
                                           value="${royolty.grossProfitCmsn}" size="10"/>%
                                </td>
                            </tr>
                            <tr>
                                <th>Per Unit Rate $</th>
                                <td><input name="serviceDTO.royaltyService.unitRateCmsn" type="text" class="NFText2"
                                           value="${royolty.unitRateCmsn}" size="10"/>
                                    <input name="serviceDTO.royaltyService.id" type="hidden" value="${royolty.id}"/>
                                    <input name="serviceDTO.royaltyService.licenseId" type="hidden"
                                           value="${royolty.licenseId}"/>
                                </td>
                                <td colspan="2">&nbsp;</td>
                            </tr>
                        </table>
                    </fieldset>
                </td>
            </tr>
            <tr>
                <td valign="top">
                    <fieldset>
                        <legend>Points/Rewards</legend>
                        <table border="0" cellpadding="0" cellspacing="0" class="General_table">
                            <tr>
                                <th>Points Received When Purchased</th>
                                <td><input name="serviceDTO.returnPoints" type="text" class="NFText2" value=""
                                           size="10"/></td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input name="priceByPointsCk" type="checkbox"/> Use
                                    <input name="serviceDTO.priceByPoints" type="text" class="NFText2" size="10"/>
                                    Points To Buy This Product
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </td>
                <td valign="top">&nbsp;</td>
            </tr>
            <tr>
                <td valign="top">
                    <fieldset>
                        <legend>Customer Notice</legend>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
                            <tr>
                                <th width="45%">Send When Product is Ordered</th>
                                <td>
                                    <s:select name="serviceDTO.noticeSendType" list="sendOrderedOption" listValue="text"
                                              listKey="value" style="width:250px;"/>
                                </td>
                            </tr>
                            <tr>
                                <th>When to Generate Notice</th>
                                <td>
                                    <s:select name="serviceDTO.noticeGenerateTime" list="generateNoticeOption"
                                              listValue="text" listKey="value" style="width:250px;"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </td>
                <td valign="top">
                    <fieldset>
                        <legend>Customer Information</legend>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
                            <tr>
                                <td><input type="checkbox" name="customerInfoCk" value="checkbox"/></td>
                                <td>Customer Information for Product</td>
                            </tr>
                            <tr>
                                <td width="5%">&nbsp;</td>
                                <td width="95%"><input name="serviceDTO.customerInfo" type="text" class="NFText"
                                                       value="" size="70"/></td>
                            </tr>
                        </table>
                    </fieldset>
                </td>
            </tr>
            <tr>
                <td valign="top">
                    <fieldset>
                        <legend>Service URL</legend>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
                            <tr>
                                <td></td>
                                <td>Service URL:</td>
                            </tr>
                            <tr>
                                <td width="5%">&nbsp;</td>
                                <td width="95%"><input name="serviceDTO.url" id="url" type="text" class="NFText"
                                                       value="" size="70"/></td>
                            </tr>
                        </table>
                    </fieldset>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
<script>
    $("#url").val(parent.$("#url").val());
</script>
</html>