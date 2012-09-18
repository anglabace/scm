<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Customer Activities</title>
    <link href="${global_css_url}scm.css" rel="stylesheet" type="text/css"/>
    <link href="${global_css_url}table.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<table width="600" border="0" cellpadding="0" cellspacing="0" class="General_table" style="margin:16px auto 0px auto;">
    <tr>
        <td class="invoice_title" height="28"><strong>Last Ordering Date (excluding cancelled orders) :</strong></td>
    </tr>
    <tr>
        <td class="Indent">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
                <tr>
                    <td width="100" align="right">Order No</td>

                    <td>
                        <div align="left"><input disabled=1 name="OrderNo" type="text" class="NFText"
                                                 value="${customerActivityDTO.orderNo}" size="1"/>
                            Date <input disabled=1 name="Date" type="text" class="NFText" value="${customerActivityDTO.orderDate}"
                                        size="6" id="btntxt"/></div>
                    </td>
                    <td width="100" align="right">Status</td>
                    <td width="220"><input disabled=1 name="Status" type="text" class="NFText" value="${customerActivityDTO.status}"
                                           size="40"/></td>
                </tr>
                <tr>
                    <td align="right">Sold by</td>
                    <td><input disabled=1 name="Soldby" type="text" class="NFText" value="${customerActivityDTO.soldby}" size="20"/>
                    </td>
                    <td width="100" align="right">Entered by</td>
                    <td width="220"><input disabled=1 name="Enteredby" type="text" class="NFText"
                                           value="${customerActivityDTO.enteredby}" size="40"/></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td class="invoice_title" height="28"><strong>Last Phone Contact :</strong></td>

    </tr>
    <tr>
        <td class="Indent">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
                <tr>
                    <td width="100" align="right">Date/Time</td>
                    <td><input disabled=1 name="DateTime" type="text" class="NFText" value="${customerActivityDTO.dateTime}"
                               size="20" id="btntxt10"/></td>
                    <td width="100" align="right">Contact Person</td>
                    <td width="220"><input disabled=1 name="ContactPerson" type="text" class="NFText"
                                           value="${customerActivityDTO.contactPerson}" size="40"/></td>

                </tr>
                <tr>
                    <td align="right">By</td>
                    <td colspan="3"><input disabled=1 name="ContactBy" type="text" class="NFText"
                                           value="${customerActivityDTO.contactBy}" size="20"/></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td class="invoice_title" height="28"><strong>Last Correspondence Sent :</strong></td>
    </tr>
    <tr>
        <td class="Indent">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
                <tr>
                    <td width="100" align="right">Last E-mail Sent</td>
                    <td><input disabled=1 name="LastEmailSent" type="text" class="NFText" size="20"
                               value="${customerActivityDTO.lastEmailSent}"/></td>
                    <td width="100" align="right">Subject</td>
                    <td width="220"><input disabled=1 name="LastEmailSubject" type="text" class="NFText" size="40"
                                           value="${customerActivityDTO.lastEmailSubject}"/></td>
                </tr>
                <tr>
                    <td align="right">Last Fax Sent</td>
                    <td><input disabled=1 name="LastFaxSent" type="text" class="NFText" size="20"
                               value="${customerActivityDTO.lastFaxSent}"/></td>
                      <td width="100" align="right">Subject</td>
                    <td><input disabled=1 name="LastFaxSubject" type="text"  class="NFText" size="40"/></td>
                </tr>

                <tr>
                    <td align="right">Last Mailing Sent</td>
                    <td><input disabled=1 name="LastMailingSent" type="text" class="NFText"
                               value="${customerActivityDTO.lastMailingSent}" size="20"/></td>
                    <td align="right">Subject</td>
                    <td><input disabled=1 name="LastMailingSubject" type="text" class="NFText"
                               value="${customerActivityDTO.lastMailingSubject}" size="40"/></td>
                </tr>
            </table>
        </td>
    </tr>
    <%--
    <tr>
      <td class="invoice_title" height="28"><strong>Last Catalog Sent :</strong></td>
    </tr>
    <tr>
      <td class="Indent"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
        <tr>
          <td width="100" align="right">Last Catalog Sent </td>
          <td><input disabled=1 name="textfield64" type="text" class="NFText" size="20" /></td>

          <td width="100" align="right">Catalog Name </td>
          <td width="220"><input disabled=1 name="textfield5324" type="text" class="NFText" size="40" /></td>
        </tr>

      </table></td>
    </tr>--%>
    <tr>
        <td class="invoice_title" height="28"><strong>Last Modified :</strong></td>
    </tr>
    <tr>
        <td class="Indent">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
                <tr>
                    <td width="100" align="right">Date</td>
                    <td><input disabled=1 name="LastModifiedDate" type="text" class="NFText"
                               value="${customerActivityDTO.lastModifiedDate}" size="20" id="btntxt2"/></td>
                    <td width="100" align="right">By</td>
                    <td width="220"><input disabled=1 name="LastModifiedBy" type="text" class="NFText" size="20"
                                           value="${customerActivityDTO.lastModifiedBy}"/></td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>