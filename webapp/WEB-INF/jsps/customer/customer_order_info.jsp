<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Order Management</title>
    <link href="${global_css_url}scm.css" rel="stylesheet" type="text/css"/>
    <link href="${global_css_url}table.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet"/>
    <script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
    <link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css"/>
    <script language="javascript" type="text/javascript" src="${global_js_url}show_tag.js"></script>
    <script language="javascript" type="text/javascript" src="${global_js_url}select.js"></script>
    <script language="javascript" type="text/javascript" src="${global_js_url}SpryTabbedPanels.js"></script>
    <link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css"/>
    <script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
    <script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.form.js"></script>
    <script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>

    <script src="${global_js_url}jquery/jquery.validate.js" type="text/javascript"></script>
    <script src="${global_js_url}jquery/jquery.dialog.all.js" type="text/javascript"></script>
    <script type="text/javascript" src="${global_js_url}scm/orgPicker.js"></script>

    <script language="javascript" type="text/javascript">
        $(document).ready(function() {
            $('tr:odd >td').addClass('list_td2');
            $('.ui-datepicker').each(function() {
                $(this).datepicker(
                {
                    dateFormat: 'yy-mm-dd',
                    changeMonth: true,
                    changeYear: true
                });
            })
        });
    </script>
</head>
<body class="content">
<div class="scm">
    <div class="whiteBackground" style="background-color:white">
        <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td width="693" height="62">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="100%" style="padding-top: 7px;">
                                <table width="695" border="0" cellspacing="0" cellpadding="0" class="list_table">
                                    <tr>
                                        <th width="60">Order No</th>
                                        <th width="70">Order Date</th>
                                        <th width="70">Ship Date</th>
                                        <th width="74">Amount</th>
                                        <th width="74">Status</th>
                                        <th width="90">Sales Manager</th>
                                        <th width="80">Ref Order No</th>
                                        <th>Quotation No</th>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="8">
                                <div class="frame_box2">
                                    <table width="695" border="0" cellspacing="0" cellpadding="0" class="list_table">
                                        <s:iterator value="pageOrderMainDto.result">
                                            <tr>
                                                <%--<td width="60"><a href="order/order!edit.action?orderNo=${orderNo}&custNo=${custNo}&fullName=${firstName} ${midName} ${lastName}&operation_method=view" target="mainFrame">${orderNo} </a></td>--%>
                                                <td width="60"><a
                                                        href="order/order!edit.action?orderNo=${orderNo}&custNo=${custNo}&fullName=${firstName} ${midName} ${lastName}&operation_method=edit"
                                                        target="mainFrame">${orderNo} </a></td>
                                                <td width="70">&nbsp;<s:date name="orderDate" format="yyyy-MM-dd"/></td>
                                                <td width="70">&nbsp; <s:date name="shipDate" format="yyyy-MM-dd"/></td>
                                                <td width="74">&nbsp;${amount}</td>
                                                <td width="74">&nbsp;${status}</td>
                                                <td width="90">&nbsp;${salesContact}</td>
                                                <td width="80"><s:if test="refOrderNo==0">
                                                    &nbsp;
                                                </s:if> <s:else>
                                                    &nbsp;${refOrderNo}
                                                </s:else>
                                                </td>
                                                <td>
                                                    <s:if test="quoteNo==0">

                                                    </s:if> <s:else>
                                                    <a href="quote/quote!edit.action?quoteNo=${quoteNo}&custNo=${custNo}&fullName=${firstName} ${midName} ${lastName}&operation_method=edit"
                                                        target="mainFrame">${quoteNo}</a>
                                                </s:else>
                                                </td>
                                                <%--<td width="80">&nbsp;${refOrderNo}</td>
                                                <td>&nbsp;${quoteNo}</td>--%>
                                            </tr>
                                        </s:iterator>
                                    </table>
                                </div>
                                <div class="grayr">
                                    <jsp:include page="/common/db_pager.jsp">
                                        <jsp:param value="${ctx}/cust_qu_order!showQuorderListByCustNo.action"
                                                   name="moduleURL"/>
                                         <jsp:param value="order" name="codeType"/>   
                                         <jsp:param value="${custNo}" name="custNo"/>            
                                    </jsp:include>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="8">
                                <div align="center"><br/>
                                    <%@ include file="totalProductService.jsp" %>
                                </div>
                            </td>
                        </tr>
                    </table>
                </td>
                <td width="230" class="order_search" valign="top">
                    <%@ include file="quorderSearch.jsp" %>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
