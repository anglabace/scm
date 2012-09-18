<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp" %>
<head>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" /> 
<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}util/json_util.js"></script>
  
<link href="${global_css_url}tab-view.css?v=1" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css?v=1" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css?v=1" rel="stylesheet" />
<link href="${global_js_url}thickbox/thickbox.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="https://dev.genscriptcorp.com/scm/js/jquery/themes/base/ui.all.css" rel="stylesheet" />
<link href="${global_css_url}stylesheet/scm.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_css_url}themes/base/ui.all.css" rel="stylesheet" />
<link href="stylesheet/tab-view.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
    <script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.form.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}scm/customerPubGrant.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}thickbox/thickbox-compressed.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}newwindow.js"></script>
</head>
<body>
<table width="976" height="340" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="713" height="62"  valign="top"><table width="712" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="712"><table width="690" border="0" cellspacing="0" cellpadding="0"  class="list_table">
                  <tr>
                    <th width="62" align="right">Invoice No</th>

                    <th width="72">Invoice Date </th>
                    <th width="60">Due Date </th>
                    <th width="80">Payment Date </th>
                    <th width="92">Invoice Amount </th>
                    <th width="60">Paid Amount</th>
                    <th width="55">Balance</th> 
                    <th width="70">Status</th>
                    <th >Late Payment Days</th>
                  </tr>
              </table></td>
            </tr>
            <tr>
              <td colspan="8"><div class="frame_box2" >
                <table width="690" border="0" cellspacing="0" cellpadding="0"  class="list_table">
                 
                </table>
              </div></td>
            </tr>
            <tr>
              <td colspan="8"><br/>
                  <div align="center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">

                      <tr>
                        <td width="3%">&nbsp;</td>
                        <td width="29%"></td>
                        <td width="16%" align="right">Total Balance </td>
                        <td width="21%"><input name="textfield1033" type="text" class="NFText2" /></td>
                        <td width="31%"><input type="submit" name="Submit10" class="style_botton2" value="Create Statement" />
                        </td>
                      </tr>

                    </table>
                  </div></td>
            </tr>
        </table></td>
        <td width="253" class="order_search" valign="top"><form>
            <table width="207" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td colspan="2" class="order_date"> View Invoices with</td>

              </tr>
              <tr>
                <td width="63" align="right">Status</td>
                <td><select name="select4">
                    <option>Status of</option>
                    <option>Status of</option>
                    <option>Status of</option>

                </select></td>
              </tr>
            </table>
          <table width="207"  border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="52" align="right">Inv Amt </td>
                <td width="155" ><input name="textfield32" type="text" class="NFText" size="6" />
                  ~
                  <input name="textfield324" type="text" class="NFText" size="6" /></td>

              </tr>
              <tr>
                <td align="right">Balance</td>
                <td><input name="textfield322" type="text" class="NFText" size="6" />
                  ~
                  <input name="textfield3242" type="text" class="NFText" size="6" /></td>
              </tr>
              <tr>
                <td colspan="2" align="right">Past Due Days
                  <input name="textfield323" type="text" class="NFText" size="17" /></td>

              </tr>
            </table>
          <table width="207"  border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td colspan="3" class="order_date">Invoice  Date </td>
              </tr>
              <tr>
                <td align="right">Frodddm</td>

                <td width="5"><input name="textfield1423" type="text" class="NFText ui-datepicker" value="" size="5  id="btntxt4"/></td>
                <!-- <td width="72"><img src="images/dashboard-icon2.gif" width="16" height="13" align="absmiddle" onclick="ca.showMoreDay = true;ca.show(getObjById('btntxt4'),'',this)" /></td> -->
              </tr>
              <tr>
                <td align="right">To</td>
                <td><span class="frame_box2"><input name="textfield1423" type="text" class="NFText ui-datepicker" value="" size="18"  id="btntxt5"/></span></td>

                <!-- <td><img src="images/dashboard-icon2.gif" width="16" height="13" align="absmiddle" onclick="ca.showMoreDay = true;ca.show(getObjById('btntxt5'),'',this)" /></td> -->
              </tr>
              <tr>
                <td height="30" colspan="3"><div align="center">
                    <input type="reset" name="Submit223" class="style_botton" value="Reset" />
                    <input type="submit" class="style_botton"  value="Process" />
                </div></td>
              </tr>
            </table>

        </form></td>
      </tr>
    </table>
   <br />
</body>
</html>