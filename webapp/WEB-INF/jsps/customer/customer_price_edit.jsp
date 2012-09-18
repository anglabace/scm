<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base id="myBaseId" href="${global_url}"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>scm</title>
    <link href="${global_css_url}scm.css" rel="stylesheet" type="text/css"/>
    <link href="${global_css_url}table.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet"/>

    <script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
    <script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.form.js"></script>
    <script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.validate.js"></script>
    <script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
    <script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
    <script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>

    <script>
        function add_price(bt_obj, form_name, action_name) {
            if ($("#create_price_form").valid() == false) {
                return false;
            }
            var standardPrice = $("#standardPrice").val();
            if (checkNumberContent(standardPrice) == false) {
                alert("Please enter the correct 'Standard Price'.");
                return false;
            }
            var discount = $("#discount").val();
            if (Number(discount) >= 100) {
                alert("'With Discount' must be less than 100,please enter again.");
                return false;
            }

            var tmpDiscount = 1 - parseFloat(discount) / 100;
            var unitPrice = Math.round(standardPrice * tmpDiscount * 1000) / 1000;
            $("#unitPrice").val(unitPrice);

            var bt_val = bt_obj.value;
            bt_obj.value = bt_val + '...';
            bt_obj.disabled = true;
            var form = $("#" + form_name);
            var listType = $("#listType").val();
            //ajax form post
            var options = {
                success:function(data) {
                    if (data == "SUCCESS") {
                        //
                    } else if (data) {
                        alert(data);
                    }
                    bt_obj.value = bt_val + ":success";
                    if (listType == "PRODUCT") {
                        parent.$("#priceIframe").contents().find("#pdtPriceIframe").each(function() {
                            this.contentWindow.location.reload(true);
                        });
                    } else {
                        parent.$("#priceIframe").contents().find("#serPriceIframe").each(function() {
                            this.contentWindow.location.reload(true);
                        });
                    }
                    parent.$("#addCustomerSpclPrcDialog").dialog('close');
                },
                error: function() {
                    alert('error...');
                    bt_obj.value = bt_val;
                    bt_obj.disabled = true;
                },
                resetForm:true,
                url:"customer/cust_spcl_prc!save.action",
                type:"POST"
            };
            form.ajaxForm(options);
            form.submit();
        }

        function catalog_picker() {
            var catalogId = $("#catalogId").val();
            if (catalogId == "") {
                alert("A catalogId is needed before search.");
                return;
            }
            var listType = $("#listType").val();
            var sessCustNo = $("#sessCustNo").val();
            var custNo = $("#custNo").val();
            var catalogId = $("#catalogId").val();
            var url = 'customer/cust_spcl_prc!pickCatalog.action?custNo=' + custNo + '&sessCustNo=' + sessCustNo + '&listType=' + listType + '&catalogId=' + catalogId;
            var htmlStr = '<iframe id="searchProductIframe" src="' + url + '" height="350" width="520" scrolling="no" style="border:0px" frameborder="0"></iframe>';
            ;
            parent.$('#catalogSearchDialog').html(htmlStr);
            parent.$('#catalogSearchDialog').dialog('open');
        }

        function checkNumberContent(prm) {
            var patrn = /^\d+(\.\d+)?%?$/;
            if (!prm.match(patrn)) {
                return false;
            }
            return true;
        }

        function calculateUnitPrice() {
            var standardPrice = $("#standardPrice").val();
            if (checkNumberContent(standardPrice) == false) {
                alert("Please enter the correct 'Standard Price'.");
                return;
            }
            var discount = $("#discount").val();
            if (checkNumberContent(discount) == false) {
                alert("Please enter the correct 'With Discount'.");
                return;
            }
            if (Number(discount) >= 100) {
                alert("'With Discount' must be less than 100,please enter again.");
                return;
            }
            var tmpDiscount = 1 - parseFloat(discount) / 100;
            var unitPrice = Math.round(standardPrice * tmpDiscount * 1000) / 1000;
            $("#unitPrice").val(unitPrice);
        }

        $(document).ready(function() {
            $('.datepicker').each(function() {
                $(this).datepicker(
                {
                    dateFormat: 'yy-mm-dd',
                    changeMonth: true,
                    changeYear: true
                });
            });

            $("#discount").blur(function () {
                calculateUnitPrice();
            });

            $("#create_price_form").validate({
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
                        return false;
                    });
                },
                rules: {
                    "custSpecialPrice.catalogNo":{required:true},
                    "custSpecialPrice.standardPrice":{required:true,number:true},
                    "custSpecialPrice.discount":{required:true,number:true},
                    "custSpecialPrice.unitPrice":{required:true,number:true},
                    "custSpecialPrice.minQty":{number:true},
                    "custSpecialPrice.orderTotal":{required:true,number:true}
                },
                messages: {
                    "custSpecialPrice.catalogNo":{required:"Please select a Catalog No."},
                    "custSpecialPrice.standardPrice":{required:"Please enter the 'Standard Price'.",number:"Please enter the valid 'Standard Price'"},
                    "custSpecialPrice.discount":{required:"Please enter the 'With Discount'.",number:"Please enter the valid 'With Discount'."},
                    "custSpecialPrice.unitPrice":{required:"Please enter the 'Unit Price'.",number:"Please enter the valid 'Unit Price'."},
                    "custSpecialPrice.minQty": {number:"Please enter the valid 'Min. Qty'."},
                    "custSpecialPrice.orderTotal":{required:"Please enter the 'Order Total'.",number:"Please enter the valid 'Order Total'."}
                },
                errorPlacement: function(error, element) {
                }
            });
        });
    </script>
</head>

<body>
<form id="create_price_form" action="customer/custSpclPrc/saveCustSpclPrcAct">
    <input type="hidden" name="custNo" id="custNo" value="${custNo}"/>
    <input type="hidden" name="sessCustNo" id="sessCustNo" value="${sessCustNo }"/>
    <input type="hidden" name="listType" id="listType" value="${listType }"/>
    <input type="hidden" name="custSpecialPrice.type" value="${listType }"/>
    <table width="500px" border="0" style="margin:15px auto;" cellpadding="0" cellspacing="0" class="General_table">
        <tr>
            <td width="100" align="right">Catalog No</td>
            <td width="150">
                <input name="custSpecialPrice.catalogNo" type="text" id="catalogNo" class="NFText" size="15"
                       readonly="readonly"/>
                <input name="custSpecialPrice.name" id="name" class="NFText" size="15" type="hidden"/>
                <a href="javascript:void(0);" onclick="catalog_picker();" id="catalog_picker_href"
                   title="Produce Code Search">
                    <img src="${global_image_url}search.jpg" width="16" height="16"/>
                </a>
            </td>
            <td width="100">&nbsp;</td>
            <td>&nbsp;
                <input type="hidden" id="catalogId" name="custSpecialPrice.catalogId" value="${priceCatalogId}"/>
            </td>
        </tr>
        <tr>
            <td height="35" colspan="4" valign="bottom" class="blue_price">
                <strong>Method Of Pricing </strong>
            </td>
        </tr>
        <tr>
            <td align="right">Standard Price</td>
            <td width="108">
                <input id="standardPrice" name="custSpecialPrice.standardPrice" type="text" class="NFText2"
                       value="${result.standardPrice}" size="15" readonly="readonly"/>
            </td>
            <td width="104" align="right">With Discount</td>
            <td width="170">
                <input id="discount" name="custSpecialPrice.discount" type="text" class="NFText2"
                       value="${result.discount}" size="15" onchange="calculateUnitPrice();"/>
            </td>
        </tr>
        <tr>
            <td align="right">Unit Price</td>
            <td width="108"><input id="unitPrice" name="custSpecialPrice.unitPrice" type="text" class="NFText2"
                                   value="${result.unitPrice}" size="15" readonly="readonly"/>
            </td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td height="35" colspan="4" valign="bottom" class="blue_price"><strong> Qualifiers For This Price </strong>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend>Order</legend>
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
                        <tr>
                            <td align="right">Min. Qty.</td>
                            <td><input name="custSpecialPrice.minQty" type="text" class="NFText"
                                       value="${result.minQty}" size="15"/></td>
                            <td align="right">Source Key</td>
                            <td>
                                <s:select id="source" name="custSpecialPrice.source" cssStyle="width:110px;"
                                          list="sourceList" listKey="sourceId" listValue="name"></s:select>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">Order Total</td>
                            <td><input name="custSpecialPrice.orderTotal" type="text" class="NFText2" value="0"
                                       size="15"/></td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td align="right">Start Date</td>
                            <td>
                                <input name="custSpecialPrice.effFrom" type="text" class="NFText datepicker" value=""
                                       size="15" id="effFrom" readonly="readonly"/>
                            </td>
                            <td align="right">End Date</td>
                            <td>
                                <input name="custSpecialPrice.effTo" type="text" class="NFText datepicker" value=""
                                       size="15" id="effTo" readonly="readonly"/>
                            </td>
                        </tr>
                    </table>
                </fieldset>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <div align="center">
                    <input type="button" value="Add" onclick="add_price(this, 'create_price_form');"/>
                    <input type="button" value="Cancel"
                           onclick="javascript:parent.$('#addCustomerSpclPrcDialog').dialog('close');"/>
                </div>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
