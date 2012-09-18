<!-- {get_spec_selects value="ORIGINAL_SOURCE"} -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp" %>
<head>

    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>scm</title>
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
            });
        });
    </script>
    <script language="JavaScript" type="text/javascript">
        function choose_all(e, tag_name) {
            var a = document.getElementsByName(tag_name);
            for (var i = 0; i < a.length; i++) {
                a[i].checked = e.checked;
            }
        }

        function del_contact(custNo, sessCustNo) {
            if (!check_any_selected('contactId[]')) {
                alert('Please choose at least one');
                return;
            }
            else if (!confirm("Are you sure to continue?")) {
                return;
            }
            var id_str = get_checked_str('contactId[]');
            $.ajax({
                url: 'cust_contact!delete.action?sessCustNo=' + sessCustNo,
                type: 'GET',
                data: 'contact_id_str=' + id_str + '&custNo=' + custNo,
                dataType: 'text',
                timeout: 1000,
                error: function() {

                },
                success: function(data) {
                    if (data == 'success') {
//				alert('success');
                        window.location.reload();
                        clearContactCheck();
                    } else {
                        alert(data);
                    }
                }
            });
        }

        function clearContactCheck() {
            $(":checkbox[name*='contactId']").each(
                    function() {
                        //this.checked = false ;
                        $(this).attr("checked", false);
                    }
                    );
        }

        function check_any_selected(name) {
            var a = document.getElementsByName(name);
            var flag = false;
            for (var i = 0; i < a.length; i++) {
                if (a[i].checked) {
                    flag = true;
                    break;
                }
            }
            return flag;
        }
        function get_checked_str(name) {
            var a = document.getElementsByName(name);
            var str = '';
            for (var i = 0; i < a.length; i++) {
                if (a[i].checked) {
                    str += a[i].value + ',';
                }
            }
            return str.substring(0, str.length - 1);
        }

        function show_add_cntform(custNo, sessCustNo) {
            var href = 'cust_contact!showCustCntctCreForm.action?custNo=' + custNo + '&sessCustNo=' + sessCustNo;
            parent.$('#addCustomerCntctDialogTrigger').val(href);
            parent.$('#addCustomerCntctDialogTrigger').click();
        }

        function show_edit_cntform(id, custNo, sessCustNo) {
            var href = 'cust_contact!showCustCntctEditForm.action?contactIdStr=' + id + '&custNo=' + custNo + '&sessCustNo=' + sessCustNo;
            parent.$('#editCustomerCntctDialogTrigger').val(href);
            parent.$('#editCustomerCntctDialogTrigger').click();
        }

        function set_selected(id, val) {
            var obj = document.getElementById(id);
            for (var i = 0; i < obj.options.length; i++)
                if (obj.options[i].value == val)
                    obj.options[i].selected = true;
        }
    </script>
</head>
<body>
<div class="">
    <form method="post" action="cust_contact!list.action">
        <input type="hidden" name="custNo" value="${custNo}"/>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td height="110" valign="top">
                    <div align="center" class="blue_price"></div>
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
                        <tr>
                            <td rowspan="2" valign="top"><span class="blue_price">View Contacts with </span></td>
                            <td align="right">Type</td>
                            <td>
                                <select name="contactMethod" id="contactMethod" style="width:150px">
                                    <option value="">All</option>
                                    <option value="Email">Email</option>
                                    <option value="Fax">Fax</option>
                                    <option value="Mail">Mail</option>
                                    <option value="Phone">Phone</option>
                                    <option value="Meeting">Meeting</option>
                                </select>
                            </td>
                            <td align="right">Status</td>
                            <td>
                                <select name="status" id="status" style="width:150px">
                                    <option value="">All</option>
                                    <option value="SENT">SENT</option>
                                    <option value="UNSENT">UNSENT</option>
                                    <option value="CALLED">CALLED</option>
                                    <option value="UNCALLED">UNCALLED</option>
                                </select>
                            </td>
                            <td align="right">Contacted By</td>
                            <td colspan="2">
                                <select name="contactBy" id="contactBy">
                                    <option value="">All</option>
                                    <s:iterator value="contactUserNameList" status="dex">
                                        <s:set name="index" value="#dex.getIndex()"/>
                                        <option><s:property value="contactUserNameList.get(#index)"/></option>
                                    </s:iterator>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">Contact Date From</td>
                            <td>
                                <input name="contactDateFrom" id="contactDateFrom" type="text"
                                       class="NFText ui-datepicker"
                                       value="<s:date name=" contactDateFrom" format="yyyy-MM-dd" />" size="20"
                                id="contactDateFrom" style="width:145px;"/>
                            </td>
                            <td align="right">To</td>
                            <td colspan="4">
                                <input name="contactDateTo" id="contactDateTo" type="text" class="NFText ui-datepicker"
                                       value="<s:date name=" contactDateTo" format="yyyy-MM-dd" />" size="20"
                                id="contactDateTo" style="width:145px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="8">
                                <div align="center" style="margin-top:10px;">
                                    <input type="submit" value="Search" class="style_botton"/>
                                    <input type="button" value="New"
                                           onclick="show_add_cntform('${custNo}','${sessCustNo}');"
                                           class="style_botton"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td valign="top"><span class="blue_price">Contact Summary</span></td>
                            <td colspan="7"><br/>
                                <table border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td># of Phone :</td>
                                        <td width="20"><span class="history_td">${phoneCount}</span></td>
                                        <td># of Emails :</td>
                                        <td width="20"><span class="history_td">${emailCount}</span></td>
                                        <td># of Mail :</td>
                                        <td width="20"><span class="history_td">${mailCount}</span></td>
                                        <td># of Fax :</td>
                                        <td width="20"><span class="history_td">${faxCount}</span></td>
                                        <td># of Meeting :</td>
                                        <td width="20"><span class="history_td">${meetingCount}</span></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </form>
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td>
                <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                        <td colspan="2" style="padding:0px">
                            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                                <tr>
                                    <th width="50">
                                        <div align="left">
                                            <input type="checkbox" onclick="choose_all(this, 'contactId[]')"/>
                                            <img src="${global_image_url}file_delete.gif"
                                                 onclick="del_contact('${custNo}', '${sessCustNo}');" width="16"
                                                 height="16"/>
                                        </div>
                                    </th>
                                    <th width="130">Contact/Schedule Date</th>
                                    <th width="80">Contact</th>
                                    <th width="80">Contacted By</th>
                                    <th width="70">Type</th>
                                    <th width="70">Status</th>
                                    <th width="220">Subject</th>
                                    <th width="90">Source</th>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="padding:0px;">

                            <div class="frame_box" style="height:130px;">
                                <table width="100%" border='0' cellpadding='0' cellspacing='0' class='list_table'>
                                    <s:iterator value="pageContactDTO.result">
                                        <tr>
                                            <td width="50"><input type="checkbox" name="contactId[]"
                                                                  value="${contactIdStr}"/></td>
                                            <td width="130" align="center">&nbsp;<c:choose><c:when
                                                    test="${empty contactDate}"><s:date name="scheduleDate"
                                                                                        format="yyyy-MM-dd"/></c:when><c:otherwise><s:date
                                                    name="contactDate" format="yyyy-MM-dd"/></c:otherwise></c:choose>
                                            </td>
                                            <td width="80" align="center">&nbsp;<s:if
                                                    test="%{contactName != null && contactName.length()>9}">
                                                <s:property
                                                        value="contactName.substring(0,6)+'...'"/></s:if><s:else>${contactName}</s:else>
                                            </td>
                                            <td width="80">&nbsp;${contactUserName}</td>
                                            <td width="70">&nbsp;${contactMethod}</td>
                                            <td width="70">&nbsp;${status}</td>
                                            <td width="220">&nbsp;
                                                <a href="javascript:void(0);"
                                                   onclick="show_edit_cntform('${contactIdStr}', '${custNo}', '${sessCustNo}');"
                                                   title="${subject}">
                                                    <c:if test="${empty subject}">null</c:if>
                                                    <s:if test="%{subject != null && subject.length()>30}">
                                                        <s:property
                                                                value="subject.substring(0,27)+'...'"/></s:if>${subject}
                                                </a>
                                            </td>
                                            <td width="90">&nbsp;${sourceName}</td>
                                        </tr>
                                    </s:iterator>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    set_selected('contactMethod', '${contactMethod}');
    set_selected('status', '${status}');
    set_selected('contactBy', '${contactBy}');
</script>
</body>
</html>

