<%-- 
    Document   : breakdown_add
    Created on : 2010-9-24, 11:37:58
    Author     : jinsite
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="${global_url}"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="${global_css_url}scm.css" rel="stylesheet" type="text/css"/>
    <link href="${global_css_url}table.css" rel="stylesheet" type="text/css"/>
    <script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
    <script language="javascript">
        function cc(e) {
            var a = document.getElementsByName("catNos");
            for (var i = 0; i < a.length; i++)a[i].checked = e.checked;
        }

        function showhid() {
            var agree = document.getElementById("agree");
            var hid = document.getElementById("hid");
            if (agree.checked == false) {
                hid.style.display = 'none';
            } else {
                hid.style.display = '';
            }
        }

        function a(obj) {
            var _selectedvalue = obj.value;
            if (_selectedvalue == '') {
                _selectedvalue = '-1';
            }

            for (i = 1; i <= 8; i++) {
                var divobj = document.getElementById('tb' + i);
                if (parseInt(_selectedvalue) == i) {
                    divobj.style.display = 'block';
                } else {
                    divobj.style.display = 'none';
                }
            }
        }

        function b(obj) {
            var _selectedvalue = obj.value;
            if (_selectedvalue == '') {
                _selectedvalue = '-1';
            }

            for (i = 1; i <= 8; i++) {
                var divobj = document.getElementById('pr' + i);
                if (parseInt(_selectedvalue) == i) {
                    divobj.style.display = 'block';
                } else {
                    divobj.style.display = 'none';
                }
            }
        }
        function Dostr(str) {
            var strs = str.split("<;>");
            //得到字符串
            var str0 = strs[0];
            var str1 = strs[1];
            var str2 = strs[2];
            var str3 = strs[3];
            var str4 = strs[4];
            var str5 = strs[5];
            // alert("strs.length=="+strs.length);
            // alert("str0="+str0);
            //  alert("str1="+str1);
            //  alert("str2="+str2);
            //  alert("str3="+str3);
            //  alert("str4="+str4);
            //alert("str5="+str5);
            var news = '';
          //  var flag = 0;
            var allstr = "";
            if (strs[1].match("&")) {
               // flag = 1;
                var srt1 = str1.split("&");
                for (var t = 0; t < srt1.length; t++) {
                  //  alert(srt1[t]);
                    news += srt1[t] + "<@>";
                }
                allstr = str0 + "<;>" + news.substring(0, news.length - 3) + "<;>" + str2 + "<;>" + str3 + "<;>" + str4 + "<;>" + str5;
            } else {
                news = str1;
                allstr = str0 + "<;>" + news + "<;>" + str2 + "<;>" + str3 + "<;>" + str4 + "<;>" + str5;
            }
            // alert(allstr);
            return allstr;
        }
        function get_checked_str(name) {
            var a = document.getElementsByName(name);
            var str = '';
            for (var i = 0; i < a.length; i++) {
                if (a[i].checked) {
                    var strall = $("#compoItemInfo_" + a[i].value).val();
                    strall = Dostr(strall);
                    str += strall + '<==>';
                }
            }
            if (str) {
                return str.substring(0, str.length - 4);
            }
            return null;
        }
        function quantityToCheck(catNo) {
            $("#catNos_" + catNo).attr("checked", "checked");
        }
        function quantityToStr(catNo) {
            str = $("#compoItemInfo_" + catNo).attr("value");
            arrStr = str.split("<;>");
            arrStr.pop();
            arrStr.push($("#quantity_" + catNo).attr("value"));
            newStr = arrStr.join("<;>");
            $("#compoItemInfo_" + catNo).attr("value", newStr);
        }
        function get_checked_idArr(name) {
            var a = document.getElementsByName(name);
            var arrR = new Array();
            j = 0;
            for (var i = 0; i < a.length; i++) {
                if (a[i].checked) {
                    arrR[j] = a[i].value;
                    j++;
                }
            }
            return arrR;
        }
        function saveCompoItem() {
            var str_item_str = get_checked_str("catNos");
            var arrCatNo = get_checked_idArr("catNos");

            trStr = "";
            len = arrCatNo.length;

            objComposite = parent.document.getElementById("compositeList_iframe").contentWindow;
            var trlen = objComposite.$("#compositeListTable" + " tr").length;
            $.ajax({
                type:"POST",
                url:"product/component!save.action",
                data:"catStr=" + str_item_str + "&psId=${psId}&sessionPSID=${sessionPSID}",
                success: function(msg) {
                    var arrIdR = msg.split(",");
                    for (i = 0; i < len; i++) {
                        lineInfoStr = $("#compoItemInfo_" + arrCatNo[i]).val();
                        arrLineInfoStr = lineInfoStr.split("<;>");
                        catalogNo = arrLineInfoStr[0];
                        name = arrLineInfoStr[1];
                        leadTime = arrLineInfoStr[2];
                        symbol = arrLineInfoStr[3];
                        standardPrice = arrLineInfoStr[4];
                        quantity = arrLineInfoStr[5];
                        seqBd = trlen + i + 1;

                        trStr += "<tr>";
                        trStr += "<td width=66><input type=\"checkbox\" name=\"cmpsId\" value='" + arrIdR[i] + "' /><input type=\"hidden\" name=\"cmpsCatNo\" value=\"" + catalogNo + "\" />&nbsp;</td>";
                        trStr += "<td width=60><span id=\"seqNo_" + arrIdR[i] + "\">" + seqBd + "</span>&nbsp;</td>";
                        trStr += "<td width=70><span id=\"catalogNo_" + arrIdR[i] + "\">" + catalogNo + "</span>&nbsp;<input type=\"hidden\" name=\"bdCatNo\" value='" + catalogNo + "' /></td>";
                        trStr += "<td><span id=\"item_" + arrIdR[i] + "\">" + name + "</span>&nbsp;</td>";
                        trStr += "<td width=70><span id=\"quantity_" + arrIdR[i] + "\">" + quantity + "</span>";
                        trStr += "<td width=120><span id=\"leadTime_" + arrIdR[i] + "\">" + leadTime + "</span>&nbsp;</td>";
                        trStr += "<td width=70><div align=\"right\"><span id=\"symbol_" + arrIdR[i] + "\">" + symbol + "</span><span id=\"price_" + arrIdR[i] + "\">" + standardPrice + "</span>&nbsp;</div></td>";
                        trStr += "</tr>";
                    }
                    //parent.
                    objComposite.$("#compositeListTable").append(trStr);
                    objComposite.location.reload();

                    parent.$('#compositeDialog').dialog('close');
                    parent.$('#compositeDialog').dialog('destroy');
                },
                error: function(msg) {
                    alert("System error! Please contact system administrator for help.");
                }
            });
            return false;
        }
    </script>
    <style type="text/css">
        <!--
        body {
            margin: 10px auto;
            width: 600px;
        }

        -->
    </style>
</head>

<body>
<form action="" method="get">
    <table width="558" border="0" cellpadding="0" cellspacing="0" class="General_table">
        <tr>
            <td width="15%" align="right">Catalog No:</td>
            <td width="30%"><input name="filter_LIKES_catalogNo" type="text" class="NFText" size="20" value="${param['filter_LIKES_catalogNo']}"/></td>
            <td width="10%" align="right">Name:</td>
            <td width="30%"><input name="filter_LIKES_name" type="text" class="NFText" size="20" value="${param['filter_LIKES_name']}"/></td>
            <td width="20%"><input name="noList" type="hidden" class="NFText" value="${noListStr}"/><input type="hidden"
                                                                                                           name="sessionPSID"
                                                                                                           value="${sessionPSID}"/><input
                    type="hidden" name="psId" value="${psId}"/><input type="submit" class="style_botton"
                                                                      value="Search"/></td>
        </tr>
    </table>
</form>
<table width="583" border="0" cellpadding="0" cellspacing="0" class="list_table2">
    <tr>
        <th width="46">
            <div align="left">
                <input type="checkbox" onclick="cc(this)"/>
                <img src="images/file_delete.gif" alt="Delete" width="16" height="16" border="0"/></div>
        </th>
        <th width="70">Catalog No</th>
        <th>Item</th>
        <th width="70">Quantity</th>
        <th width="120">Lead Time( Days)</th>
        <th width="70">Price</th>
    </tr>
</table>
<div style="width:600px; height:120px; overflow:scroll;">
    <table width="583" border="0" cellpadding="0" cellspacing="0" class="list_table2">
        <s:iterator value="ppStdPrice.result" status="pStdPrice">
            <tr>
                <td width="46"><input type="checkbox" name="catNos" id="catNos_<s:property value=" catalogNo"/>" value="<s:property
                        value="catalogNo"/>" />
                </td>
                <td width="70"><s:property value="catalogNo"/>&nbsp;</td>
                <td><s:property value="name"/>&nbsp;</td>
                <td width="70"><input type="text" name="quantity_<s:property value=" catalogNo"/>"
                    id="quantity_<s:property value="catalogNo"/>" class="NFText" size="5" value="1"
                    onfocus="quantityToCheck('<s:property value="catalogNo"/>');" onkeyup="quantityToStr('<s:property
                            value="catalogNo"/>');" />&nbsp;</td>
                <td width="120"><s:property value="leadTime"/>&nbsp;</td>
                <td width="70"><s:property value="symbol"/><s:property value="unitPrice"/>&nbsp;</td>
            </tr>
            <input type="hidden" name="compoItemInfo_<s:property value=" catalogNo"/>" id="compoItemInfo_<s:property
                value="catalogNo"/>" value="<s:property value="catalogNo"/><;><s:property value="name"/><;><s:property
                value="leadTime"/><;><s:property value="symbol"/><;><s:property value="unitPrice"/><;>1" />
        </s:iterator>
    </table>
</div>
<div class="grayr">
			<jsp:include page="/common/db_pager.jsp">
			  <jsp:param value="${ctx }/product/component!input.action" name="moduleURL"/>
			</jsp:include>
</div>
<div style="margin:10px 0px; text-align:center">
    <input type="submit" class="style_botton" value="Add" onclick="saveCompoItem();"/>
    <input type="button" value="Cancel" class="style_botton"
           onclick="javascript:parent.$('#compositeDialog').dialog('destory');parent.$('#compositeDialog').dialog('close');"/>
</div>
</body>
</html>