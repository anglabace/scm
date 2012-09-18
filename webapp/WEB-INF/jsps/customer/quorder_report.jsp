<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <c:if test="${tag !='print'}">
        <title>scm</title>
    </c:if>
</head>
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script>
    $(function() {
        var now = new Date();
        var today = "";
        today = now.getFullYear() + "-" + ( now.getMonth() + 1 ) + "-" + now.getDate();
        time = now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
        $('#currentDate').html("Date: " + today);
        $('#currentTime').html("Time: " + time);
    });

</script>
<body style="margin: 0px;padding: 0px;color:#333333;font:11px/18px Arial, Helvetica, sans-serif;">
<c:if test="${tag !='print'}">

    <div style="margin: 0px;padding: 0px;float: left;height: auto;width: 700px;font-family: Arial, Helvetica, sans-serif;">
        <div style="margin: 0px;float: left;height: auto;width: 700px;padding-top: 0px;padding-right: 0px;padding-bottom: 10px;padding-left: 0px;">
            <div style="margin: 0px;padding: 0px;float: left;height: 68px;width: 161px;"><img
                    src="${ctx}/images/genscript_logo.gif" width="144"/></div>
            <div style="margin: 0px;padding: 0px;float: left;height: auto;width: 500px;">
                <div style="margin: 0px;float: left;height: 24px;width: 460px;font-size: 14px;padding-top: 4px;padding-right: 0px;padding-bottom: 0px;
padding-left: 40px;font-weight: bold;">Order Summary for customer ${custNo}-${username}</div>
                <div id="currentDate"
                     style="margin: 0px;float: left;height: 16px;width: 480px;font-size: 12px;font-family: Arial, Helvetica, sans-serif;padding-top: 4px;padding-right: 0px;padding-bottom: 0px;padding-left: 20px;">
                    Date:
                </div>

                <div id="currentTime"
                     style="margin: 0px;float: left;height: 16px;width: 480px;font-size: 12px;font-family: Arial, Helvetica, sans-serif;padding-top: 4px;padding-right: 0px;padding-bottom: 0px;padding-left: 20px;">
                    Time:
                </div>
                <br/>
            </div>
        </div>
    </div>
</c:if>

<div style="margin: 0px;padding: 0px;float: left;height: auto;width: 100">
    <table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#999999">

        <c:choose>
            <c:when test="${codeType =='order'}">
                <tr>
                    <td width="113" height="26" bgcolor="#DBEBF7">Date Ordered</td>
                    <td width="78" bgcolor="#DBEBF7">Order#</td>
                    <td width="132" bgcolor="#DBEBF7">Shipment Date</td>
                    <td width="154" bgcolor="#DBEBF7">Quantity</td>
                    <td width="113" bgcolor="#DBEBF7">Status</td>
                    <td width="109" bgcolor="#DBEBF7">Grand Total</td>
                </tr>
            </c:when>

            <c:otherwise>
                <tr>
                    <td width="113" height="26" bgcolor="#DBEBF7">Date Quoted</td>
                    <td width="78" bgcolor="#DBEBF7">Quoter#</td>
                    <td width="132" bgcolor="#DBEBF7">Shipment Date</td>
                    <td width="154" bgcolor="#DBEBF7">Quantity</td>
                    <td width="113" bgcolor="#DBEBF7">Status</td>
                    <td width="109" bgcolor="#DBEBF7">Grand Total</td>
                </tr>
            </c:otherwise>
        </c:choose>


        <c:choose>
            <c:when test="${codeType =='order'}">
                <s:iterator value="pageOrderMainDto.result">
                    <tr>
                        <td bgcolor="#FFFFFF" style="font-weight: bold;text-align: center;"><s:date name="orderDate"
                                                                                                    format="yyyy-MM-dd"/>
                        </td>
                        <td align="center" bgcolor="#FFFFFF">${orderNo}</td>
                        <td align="center" bgcolor="#FFFFFF"><s:date name="closeDate" format="yyyy-MM-dd"/></td>
                        <td align="center" bgcolor="#FFFFFF">${qty} </td>
                        <td align="center" bgcolor="#FFFFFF">${status}</td>
                        <td bgcolor="#FFFFFF" style="text-align: right;">${amount}</td>
                    </tr>
                </s:iterator>
            </c:when>
            <c:otherwise>
                <s:iterator value="pageQuoteMainDto.result">
                    <tr>
                        <td bgcolor="#FFFFFF" style="font-weight: bold;text-align: center;"><s:date name="quoteDate"
                                                                                                    format="yyyy-MM-dd"/>
                        </td>
                        <td align="center" bgcolor="#FFFFFF">${quoteNo}</td>
                        <td align="center" bgcolor="#FFFFFF"><s:date name="closeDate" format="yyyy-MM-dd"/></td>
                        <td align="center" bgcolor="#FFFFFF">${qty} </td>
                        <td align="center" bgcolor="#FFFFFF">${status}</td>
                        <td bgcolor="#FFFFFF" style="text-align: right;">${amount}</td>
                    </tr>
                </s:iterator>
            </c:otherwise>

        </c:choose>


    </table>
</div>
<div style="text-align: center;margin: 0px;float: left;height: auto;width: 700px;padding-top: 10px;padding-right: 0px;padding-bottom: 0px;padding-left: 0px;color: #354D71;">
    <div style="font-size: 13px;font-weight: bold;margin: 0px;padding: 0px;float: left;height: 20px;width: 700px;background-image: url(../images/print_foot.jpg);background-repeat: no-repeat;">
        120 Centernnial Ave., Piscataway, NJ 008854. USA
    </div>
    <div style="font-size: 12px;margin: 0px;padding: 0px;float: left;height: 20px;width: 700px;">Tel:1-732-865-9188 Fax
        1-732-210-0262 Email:info@genscript.com Web:www.genscript.com
    </div>
</div>
<div style="margin: 0px;float: left;height: 40px;width: 700px;text-align: center;padding-top: 20px;padding-right: 0px;padding-bottom: 0px;padding-left: 0px;">

    <c:if test="${tag !='print'}">
        <div class="print_anlu">

            <%--<a href="cust_qu_order!print.action?custNo=${custNo}&codeType=${codeType}&status=${status}&toAmount=${toAmount}&fromAmount=${fromAmount}&toDate=${toDate}&fromDate=${fromDate}">--%>
            <input type="button" class="style_botton" value="Print" onclick="DoPrint()"/> &nbsp;&nbsp;&nbsp;
            <input type="button" name="close" value="Cancel" class="style_botton"
                   onclick="window.parent.$('#orderStatDialog').dialog('close') "/>
        </div>

    </c:if>
</div>
<Script type="text/javascript" language="javascript">
    function DoPrint() {
        var url = "/scm/customer/cust_qu_order!print.action?custNo=${custNo}&codeType=${codeType}&status=${status}&toAmount=${toAmount}&fromAmount=${fromAmount}&toDate=${toDate}&fromDate=${fromDate}";
        window.open(url);
    }

</Script>
</body>
</html>
