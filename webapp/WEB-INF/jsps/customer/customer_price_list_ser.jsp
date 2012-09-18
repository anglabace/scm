<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${global_css_url}scm.css" rel="stylesheet" type="text/css"/>
    <link href="${global_css_url}table.css" rel="stylesheet" type="text/css"/>

    <script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
    <script>
        $(document).ready(function() {
            //document.ready  begin //add/edit trigger
            $("#addPriceDialogTrigger").click(function() {
                parent.parent.$('#addCustomerSpclPrcDialog').dialog('option', "priceCatalogId", parent.$("#priceCatalogId").val());
                parent.parent.$('#addCustomerSpclPrcDialog').dialog('option', "listType", "SERVICE");
                parent.parent.$('#addCustomerSpclPrcDialog').dialog('open');
            });

            $("#SeeCustomerSpclDialogTrigger").click(function() {
                //  alert($("#catalogNO").val());
                parent.parent.$('#SeeCustomerSpclDialog').dialog('option', "priceCatalogId", $("#priceCatalogId").val());
                parent.parent.$('#SeeCustomerSpclDialog').dialog('option', "listType", "SERVICE");
                parent.parent.$('#SeeCustomerSpclDialog').dialog('option', "catalogNO", $("#catalogNO").val());
                parent.parent.$('#SeeCustomerSpclDialog').dialog('open');
            });

//document.ready  end
        });
    </script>
</head>
<body>
<form method="post" action="cust_spcl_prc!delete.action" id="ser_form">
    <input type="hidden" name="custNo" value="${custNo}"/>
    <input type="hidden" name="sessCustNo" value="${sessCustNo}"/>
    <input type="hidden" name="listType" value="${listType}"/>
    <table width="923" border="0" cellpadding="0" cellspacing="0" class="list_table">
        <tr>
            <th width="46">
                <div align="left">
                    <input type="checkbox" onclick="cc(this, 'delPriceIdList')"/>
                    <img src="${global_image_url}file_delete.gif" width="16" height="16" onclick="remove('ser_form');"/>
                </div>
            </th>
            <th width="38">Seq No</th>
            <th width="61">Catalog No</th>
            <th width="118">Name</th>
            <th width="60">Unit Price</th>
            <th width="60">Discount</th>
            <th width="55">Min Qty</th>
            <th width="75">Source Key</th>
            <th width="64">Catalog ID</th>
            <th width="69">Start Date</th>
            <th width="69">End Date</th>
            <th width="69">Order Total</th>
        </tr>
    </table>
    <div style="width:940px; height:150px; overflow:scroll;">
        <table width="923px" border="0" cellpadding="0" cellspacing="0" class="list_table">
            <s:iterator value="priceMap" status="st">
                <tr>
                    <td width="46"><input type="checkbox" name="delPriceIdList" value="${key}"/></td>
                    <td width="38">&nbsp;${st.index+1}</td>
                    <td width="61">&nbsp;
                        <a href="javascript:void(0)" id="SeeCustomerSpclDialogTrigger">${value.catalogNo}</a>
                        <input id="catalogNO" value="${value.catalogNo}" type="hidden"/>
                    </td>
                    <td width="118" title="${value.name}">&nbsp;${value.name}</td>
                    <td width="60">&nbsp;${value.unitPrice}</td>
                    <td width="60">&nbsp;${value.discount}</td>
                    <td width="55">&nbsp;${value.minQty}</td>
                    <td width="75">&nbsp;<s:property value="sourceMap[value.source].name"/></td>
                    <td width="64">&nbsp;${value.catalogId}
                       <input type="hidden" id="priceCatalogId" value="${value.catalogId}"/>
                    </td>
                    <td width="69">&nbsp;<s:date name="value.effFrom" format="yyyy-MM-dd"/></td>
                    <td width="69">&nbsp;<s:date name="value.effTo" format="yyyy-MM-dd"/></td>
                    <td width="69">&nbsp;${value.orderTotal}</td>
                </tr>
            </s:iterator>
        </table>
    </div>
    <div class="grayr">
        <jsp:include page="/common/db_pager.jsp">
            <jsp:param value="${ctx}/cust_spcl_prc!list.action" name="moduleURL"/>
        </jsp:include>
    </div>
    <div style="">
        <p align="center">
            <input type="button" value=" New  " id="addPriceDialogTrigger" class="style_botton"/>
        </p>

        <div id="SeeCustomerSpclDialog" title="SeeCustomerSpcl"></div>
    </div>
</form>
</body>
<script src="${global_js_url}customer/customer_price.js" type="text/javascript"></script>
</html>