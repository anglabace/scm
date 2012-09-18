<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base id="myBaseId" href="${global_url}"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Order Management</title>
    <link href="${global_css_url}scm.css" rel="stylesheet" type="text/css"/>
    <link href="${global_css_url}table.css" rel="stylesheet" type="text/css"/>
    <link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet"/>

    <script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
    <script language="javascript" type="text/javascript" src="${global_js_url}show_tag.js"></script>
    <script language="javascript" type="text/javascript" src="${global_js_url}select.js"></script>
    <script src="${global_js_url}jquery/jquery.js" type="text/javascript"></script>

    <script type="text/javascript" src="${global_js_url}scm/config.js"></script>
    <script type="text/javascript" src="${global_js_url}util/util.js"></script>
    <script language="javascript" type="text/javascript" src="${global_js_url}scm/gs.util.js"></script>
    <script src="${global_js_url}recordTime.js" type="text/javascript"></script>
    <script src="${global_js_url}initlefttop.js" type="text/javascript"></script>

    <script language="javascript" type="text/javascript">
        var baseUrl = "${global_url}";
        function add_new(oper_type) {
            try {
                var custNo = "${custNo}";
            	if (checkCustomer (custNo) == true) {
            		var salesContactName = $("#salesContact option:selected").text();
                    var techSupportName = $("#techSupport option:selected").text();
                    var fullName = "${customerDetail.firstName} " + "${customerDetail.lastName}";
                    var prefPaymentTerm = "${customerDetail.prefPaymentTerm}";
                    var salesContact = "${customerDetail.salesContact}";
                    var techSupport = "${customerDetail.techSupport}";
                    var prefShipMthd = "${customerDetail.prefShipMthd}";
                    var paymentCurrency = "${customerDetail.paymentCurrency}";
                    var projectSupport = "${customerDetail.projectSupport}";
                    var projectSupportUser = $("#projectSupport option:selected").html();
                    var url = oper_type + '!edit.action?custNo=' + custNo + '&fullName=' + fullName + '&paymentTerm=' + prefPaymentTerm + '&salesContact=' + salesContact + '&techSupport=' + techSupport
                            + '&salesContactUser=' + salesContactName + '&techSupportUser=' + techSupportName + "&prefShipMthd=" + prefShipMthd + "&orderCurrency=" + paymentCurrency + "&quoteCurrency=" + paymentCurrency
                            + "&projectSupport=" + projectSupport + "&projectSupportUser=" + projectSupportUser + "&operation_method=edit";
                    parent.window.location.href = url;
            	}
            } catch (error) {
                alert("System error! Please contact system administrator for help.");
            }
        }
        
        function checkCustomer (custNo) {
        	var rtn = false;
        	$.ajax({
        		url:"customer!checkCustomer.action?custNo="+custNo,
        		success:function(data){
        			if (hasException(data)){
        			} else if (data.message == "SUCCESS"){
        				rtn = true;
        			} else if (data.message == "failure"){
        				alert("System error! check customer information failure, Please contact system administrator for help.");
        			} else{
        				alert(data.message);
        			}
        		},
        		error: function(){
        			alert("System error! Please contact system administrator for help.");
        		},
        		type:"POST",
        		async:false,
        		dataType:"json"
        	});
        	return rtn;
        }
    </script>
    <style>
        #ui-datepicker-div {
            z-index: 1003;
        }
    </style>
</head>

<body class="content"
      onload="parent.document.getElementById('srchCustAct_iframe').height=900;parent.document.getElementById('srchCustAct_iframe').width=1050;recordTime();$('#customer_PM').hide();">

<div class="scm">
<div class="input_box">
    <div class="content_box">
        <s:include value="customer_edit_top.jsp"></s:include>
    </div>

    <table width="865" cellspacing="0" cellpadding="0" border="0" class="Customer_table">
        <tbody>
        <tr>
            <td valign="top">
                <div align="center">
                    <s:if test="(operation_method == null || operation_method == 'edit')&&custNo!=null&&custNo!=''">
                    	<s:if test='customerDetail.custType == "Internal"'>
                    		<input type="button" class="search_input" value="New Quotation" disabled="disabled"/>&nbsp;&nbsp;
                    	</s:if><s:else>
                    		<input type="button" onclick="add_new('quote')" class="search_input" value="New Quotation"/>&nbsp;&nbsp;
                        </s:else>
                        <input type="button" onclick="add_new('order')" class="search_input" value="New Order"/>&nbsp;&nbsp;
                    </s:if>
                    <s:else>
                        <input type="button" class="search_input" value="New Quotation" disabled="disabled"/>&nbsp;&nbsp;
                        <input type="button" class="search_input" value="New Order" disabled="disabled"/>&nbsp;&nbsp;
                    </s:else>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<div id="dhtmlgoodies_tabView1">
<!-- tabs start -->
<input type="hidden" name="sessCustNo" id="sessCustNo" value="${sessCustNo}"/>
<!-- general start -->
<div class="dhtmlgoodies_aTab">
    <s:include value="customer_edit_general.jsp"></s:include>
</div>
<!-- general end -->

<!-- organization start -->
<div class="dhtmlgoodies_aTab">
    <s:include value="customer_edit_organization.jsp"></s:include>
</div>
<!-- organization end -->
<input type="hidden" name="custNo" id="custNo" value="${custNo }"/>

<div class="dhtmlgoodies_aTab">
    <!-- Customer Quotation tab here -->
    <iframe id="custQuoteIframe" src="cust_qu_order!showQuorderListByCustNo.action?custNo=${custNo}&codeType=quote"
            scrolling="no" width="100%" height="350px" frameborder="0"></iframe>
</div>
<div class="dhtmlgoodies_aTab">
    <!-- Customer Order tab here -->
    <iframe id="custOrderIframe" src="cust_qu_order!showQuorderListByCustNo.action?custNo=${custNo}&codeType=order"
            scrolling="no" width="100%" height="350px" frameborder="0"></iframe>
</div>

<div class="dhtmlgoodies_aTab">
    <table width="976" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td width="713" valign="top">
                <table width="712" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="712">
                            <table width="690" border="0" cellspacing="0" cellpadding="0" class="list_table">
                                <tr>
                                    <th width="62">Invoice No</th>
                                    <th width="72">Invoice Date</th>
                                    <th width="60">Due Date</th>
                                    <th width="80">Payment Date</th>
                                    <th width="92">Invoice Amount</th>
                                    <th width="60">Paid Amount</th>
                                    <th width="55">Balance</th>
                                    <th width="70">Status</th>
                                    <th>Late Payment Days</th>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="8">
                            <div class="frame_box2">
                                <table width="690" border="0" cellspacing="0" cellpadding="0" class="list_table">
                                    <tr>
                                        <td width="62">105</td>
                                        <td width="72">2009-09-18</td>
                                        <td width="60">2009-09-18</td>
                                        <td width="80">2009-09-18</td>
                                        <td width="92">1.00</td>
                                        <td width="60">4.00</td>
                                        <td width="55">0.25</td>
                                        <td width="70">Pre-Payment</td>
                                        <td>2.00</td>
                                    </tr>
                                    <tr>
                                        <td class="list_td2">135</td>
                                        <td class="list_td2">2009-08-05</td>
                                        <td class="list_td2">2009-08-05</td>
                                        <td class="list_td2">2009-09-18</td>

                                        <td class="list_td2">2.00</td>
                                        <td class="list_td2">4.00</td>
                                        <td class="list_td2">53.5</td>
                                        <td class="list_td2">Pre-Payment</td>
                                        <td class="list_td2">30.00</td>
                                    </tr>
                                    <tr>
                                        <td>153A</td>
                                        <td>2009-08-02</td>
                                        <td>2009-09-12</td>
                                        <td>2009-09-18</td>
                                        <td>12.00</td>
                                        <td>101.00</td>
                                        <td>58.3</td>
                                        <td>Current</td>
                                        <td>12.00</td>
                                    </tr>
                                    <tr>
                                        <td class="list_td2">156A</td>
                                        <td class="list_td2">2009-07-25</td>
                                        <td class="list_td2">2009-08-02</td>
                                        <td class="list_td2">2009-09-18</td>
                                        <td class="list_td2">38.00</td>
                                        <td class="list_td2">45.00</td>
                                        <td class="list_td2">4545.5</td>
                                        <td class="list_td2">Pre-Payment</td>
                                        <td class="list_td2">2.00</td>
                                    </tr>
                                </table>
                            </div>
                        </td>
                    </tr>
                    
                    <tr>
                        <td colspan="8"><br/><br/><br/><br/><br/><br/><br/><br/><br/>
                            <div align="center">
                                <table border="0" cellpadding="0" cellspacing="0" class="General_table">
                                    <tr>
                                        <th>Total Balance</th>
                                        <td><input name="textfield103" type="text" class="NFText2" value="$-3798.61"/>
                                        </td>
                                        <td>
                                            <input type="submit" name="Submit10" class="style_botton2"
                                                   value="Create Statement"/>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
            </td>
            <td width="253"  valign="top" class="order_search">
                <form>
                    <table border="0" cellspacing="0" cellpadding="0" >
                        <tr>
                            <td colspan="2" class="order_date"> View Invoices with</td>
                        </tr>
                        <tr>
                            <td width="75">
                                <div align="right">Status</div>
                            </td>
                            <td><select name="select39">
                                <option>Status of</option>
                                <option>Status of</option>
                                <option>Status of</option>
                            </select></td>
                        </tr>
                        <tr>
                            <td>
                                <div align="right">Inv Amt</div>
                            </td>
                            <td><input name="textfield32" type="text" class="NFText" size="5"/>
                                ~
                                <input name="textfield324" type="text" class="NFText" size="5"/></td>
                        </tr>
                        <tr>

                            <td>
                                <div align="right">Balance</div>
                            </td>
                            <td><input name="textfield322" type="text" class="NFText" size="5"/>
                                ~
                                <input name="textfield3242" type="text" class="NFText" size="5"/></td>
                        </tr>
                        <tr>
                            <td>
                                <div align="right">Past Due Days</div>
                            </td>
                            <td><input name="textfield323" type="text" class="NFText" size="20"/></td>

                        </tr>
                        <tr>
                            <td colspan="2" class="order_date">Invoice Date</td>
                        </tr>
                        <tr>
                            <td width="75">
                                <div align="right">From</div>
                            </td>
                            <td><input name="textfield142" type="text" class="pickdate NFText" style="width:125px;"
                                       value="     -   -  " size="18"/></td>
                        </tr>

                        <tr>
                            <td>
                                <div align="right">To</div>
                            </td>
                            <td><span class="frame_box2">
                  <input name="textfield1423" type="text" class="pickdate NFText" style="width:125px;"
                         value="     -   -  " size="18"/>
                </span></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div class="botton_box">

                                    <input type="submit" name="Submit5" class="style_botton" value="Reset"/><input
                                        type="submit" name="Submit6" class="style_botton" value="Process"/>
                                </div>
                            </td>
                        </tr>
                    </table>
                </form>
            </td>
        </tr>
    </table>
</div>

<div class="dhtmlgoodies_aTab">
    <!-- Customer Accounting Here -->
    <s:include value="customer_edit_accounting.jsp"></s:include>
</div>

<!-- address start -->
<div class="dhtmlgoodies_aTab">
    <iframe id="addrIframe" width="100%" height="300" frameborder="0" scrolling="yes"></iframe>
    <p align="center">
        <input type="button" value=" Add" class="style_botton" id="addCustomerAddrDialogTrigger"/>
        <input type="hidden" id="editCustomerAddrDialogTrigger"/>
        <input type="hidden" id="selectAddrDialogTrigger"/>
    </p>

    <div id="addCustomerAddrDialog" title="Add Address"></div>
    <div id="editCustomerAddrDialog" title="Edit Address"></div>
    <div id="selectAddrDialog" title="Select Address"></div>
</div>
<!-- address end -->

<!-- Contacts start -->
<div class="dhtmlgoodies_aTab">
    <iframe id="contactIframe" width="100%" height="340" frameborder="0" scrolling="yes"></iframe>
    <input type="hidden" id="addCustomerCntctDialogTrigger"/>
    <input type="hidden" id="editCustomerCntctDialogTrigger"/>

    <div id="addCustomerCntctDialog" title="Add Contact"></div>
    <div id="editCustomerCntctDialog" title="Edit Contact"></div>
</div>
<!-- Contacts end -->

<div class="dhtmlgoodies_aTab">
    <!-- CUSTOMER SPECIAL PRICE HERE -->
    <iframe id="priceIframe" width="100%" height="335" frameborder="0"
            src="cust_spcl_prc!list.action?custNo=${custNo}&sessCustNo=${sessCustNo}"></iframe>
    <div id="addCustomerSpclPrcDialog" title="Add Special Price"></div>
    <%--//-----------------------add by zhou gang --------------------%>
    <div id="SeeCustomerSpclDialog" title="See Special Price"></div>
    <%-------------------------------------%>
    <div id="catalogSearchDialog" title="Catalog No. Lookup"></div>
    <!-- CUSTOMER SPECIAL PRICE END -->
</div>


<div class="dhtmlgoodies_aTab">
    <!-- Sales Statistics Tab Here -->
    <%@ include file="customer_sales_statistics.jsp" %>
</div>

<div class="dhtmlgoodies_aTab">
    <!-- Customer More Info Here -->
    <%@ include file="customer_more_info_list.jsp" %>
</div>

</div>
<!-- tabs end -->

<div class="button_box">
    <saveButton:saveBtn parameter="${operation_method}"
                        disabledBtn='<input type="submit" id="saveAllTrigger"  value="Save" class="search_input" disabled="disabled"/>'
                        saveBtn='<input type="submit" id="saveAllTrigger"  value="Save" class="search_input" onclick="isSaved=true;"/>'
            />
    <s:if test='operation_method == "edit"'>
        <input type="button" id="cancelAllTrigger" value="Cancel" class="search_input" onclick="history.go(-1);"/>
    </s:if>
    <s:else>
        <input type="button" value="Close" onclick="javascript:window.close();" class="search_input"/>
    </s:else>
</div>

</div>
<!-- end of input_box class -->
<!-- end of scm class -->

<!-- org/div/dept picker container start -->
<div id="orgDialogWindow" title="Organization Lookup" style="visible:hidden"></div>
<div id="divDialogWindow" title="Division Lookup" style="visible:hidden"></div>
<div id="deptDialogWindow" title="Department Lookup" style="visible:hidden"></div>
<!-- org/div/dept picker container end -->

<!-- more phones start -->
<div id="morePhoneDialog" title="More Phones" style="display: none;">
    <form id="morePhoneDialogForm">
        <table border="0" cellpadding="0" cellspacing="0" class="General_table" style="margin:auto;">
            <tr>
                <td align="right">Business Alternative</td>
                <td><input type="text" name="altPhone" id="bussAltPhone" value="${customerDetail.altPhone}" size="30"
                           class="NFText"/></td>
                <td width="20" align="right">Ext</td>
                <td><input type="text" name="altPhoneExt" id="bussAltPhoneExt" value="${customerDetail.altPhoneExt}"
                           class="NFText" size="10"/></td>
            </tr>
            <tr>
                <td align="right">Mobile</td>
                <td colspan="3"><input type="text" name="mobile" id="bussMobile" value="${customerDetail.mobile}"
                                       size="30" class="NFText"/></td>
            </tr>
            <tr>
                <td align="right">Mobile Alternate</td>
                <td colspan="3"><input type="text" name="altMobile" id="bussMobileAlt"
                                       value="${customerDetail.altMobile}" size="30" class="NFText"/></td>
            </tr>
            <tr>
                <td align="right">Home</td>
                <td colspan="3"><input type="text" name="homePhone" id="bussHomePhone"
                                       value="${customerDetail.homePhone}" size="30" class="NFText"/></td>
            </tr>
        </table>
    </form>
</div>
<!-- more phones end -->

<!-- more email settings start -->
<div id="moreEmailDialog" title="More Emails" style="display: none;">
    <form id="moreEmailDialogForm">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table" style="margin:auto;">
            <tr>
                <td align="right">Business Email Alternative</td>
                <td><input type="text" class="NFText" name="altBusEmail" id="bussAltEmail"
                           value="${customerDetail.altBusEmail}" size="40"/></td>
            </tr>
            <tr>
                <td align="right">Personal Email</td>
                <td><input type="text" class="NFText" name="personalEmail" id="bussPersonalEmail"
                           value="${customerDetail.personalEmail}" size="40"/></td>
            </tr>
            <tr>
                <td align="right">Personal Email Alternative</td>
                <td><input type="text" class="NFText" name="altPersonalEmail" id="bussPersonalAltEmail"
                           value="${customerDetail.altPersonalEmail}" size="40"/></td>
            </tr>
        </table>
    </form>
</div>
<!-- more email settings end -->
<!-- update status dialog start -->
<div id="updateStatusDialog" title="Update Customer Status" style="display: none;">
    <table border="0" cellpadding="0" cellspacing="0" class="General_table" style="margin-left:40px;">
        <tr>
            <td height="26">
                <div align="left">Status
                    <s:select id="customerStatus2" list="contactStatusList" listKey="value" listValue="value"
                              value="customerDetail.status"></s:select>
                </div>
            </td>
        </tr>
        <tr>
            <td height="24" valign="top">
                <div align="left">Choose the reason to update the Customer Status:</div>
            </td>
        </tr>
        <tr>
            <td valign="top"><textarea name="statusReason" cols="70" rows="2"
                                       class="content_textarea">${customerDetail.statusReason}</textarea></td>

        </tr>
    </table>
</div>
<!-- update status dialog end -->
<!-- customer activities dialog container -->
<div id="customerActvDialog" title="Customer Activities" style="visible:hidden"></div>

<div id="customerCreditDialog" title="Edit Credit Information" style="visible:hidden;">
    <s:include value="customer_credit_edit.jsp"></s:include>
</div>

<!-- view source dialog -->
<div id="viewSourceDialog" title="View All Original Sources" style="visible:hidden"></div>


<div id="srchPdtSvcDialog" title="Product/Service List"></div>
<div id="viewOrderDialog" title="View Order"></div>

<div id="newGrant" title="Add New Grants"></div>
<div id="editGrant" title="Edit Grants"></div>

<div id="editPublicationDialog" title="Edit Publication" style="visible:hidden"></div>
<div id="orderStatDialog" title="Order Statistics For: Dept. Of Parks & Recreation "></div>
<script language="javascript" type="text/javascript">
	$(document).ready(function() {
		disableTabByTitle("Invoices");
	});
</script>
<script type="text/javascript">
    initTabs('dhtmlgoodies_tabView1', Array('General', 'Organization', 'Quotations', 'Orders', 'Invoices', 'Accounting', 'Addresses', 'Contacts', 'Special Pricing', 'Sales Statistics', 'More Info'), '${defaultTab>0?defaultTab:0}', 998, 350);

    // load iframe for address
    $('#tabTabdhtmlgoodies_tabView1_6')
            .click(function () {
                if ($('#addrIframe').attr('src') == undefined || $('#addrIframe').attr('src') == '') {
                    $('#addrIframe').attr('src', "cust_address!list.action?custNo=${custNo}&sessCustNo=${sessCustNo}");
                }
            });

    // load iframe for contact
    $('#tabTabdhtmlgoodies_tabView1_7')
            .click(function () {
                if ($('#contactIframe').attr('src') == undefined || $('#contactIframe').attr('src') == '') {
                    $('#contactIframe').attr('src', "cust_contact!list.action?custNo=${custNo}&sessCustNo=${sessCustNo}");
                }
            });

    // load iframe for price
    $('#tabTabdhtmlgoodies_tabView1_${defaultTab}').trigger('click');
</script>

<script language="javascript">
    // inititial values
    var orgNameBeforeChange = "${customerDetail.organization.name}";
    var divNameBeforeChange = "${customerDetail.division.name}";
    var deptNameBeforeChange = "${customerDetail.department.name}";
    var orgIdBeforeChange = "${customerDetail.organization.orgId}";
    var divIdBeforeChange = "${customerDetail.division.divisionId}";
    var deptIdBeforeChange = "${customerDetail.department.deptId}";

    // country state city default value initialization
    var countryIdNames = ['country', 'orgLocCountry', 'divLocCountry', 'deptLocCountry'];
    var countryDefaults = ['${customerDetail.country != null?customerDetail.country:"US"}','${customerDetail.organization.country !=null?customerDetail.organization.country:"US"}','${customerDetail.division.country != null?customerDetail.division.country:"US"}','${customerDetail.department.country != null?customerDetail.department.country:"US"}'];
    var countryChangeHandlers = ['','','',''];//去掉了initTerritory事件

    var stateIdNames = ['state', 'orgLocState', 'divLocState', 'deptLocState'];
    var stateDefaults = ['${customerDetail.state}','${customerDetail.organization.state}','${customerDetail.division.state}','${customerDetail.department.state}'];
    var stateChangeHandlers = ['','','',''];//去掉了initTerritory事件

    // territory initialization
    var defaultSalesTerritory = "${customerDetail.salesTerritory}";
    var defaultSalesGroup = "${customerDetail.salesGroup}";
    var defaultSalesContact = "${customerDetail.salesContact}";
    var defaultTechSupport = "${customerDetail.techSupport}";
    var custNo = "${custNo}";
    var sessCustNo = "${sessCustNo}";
</script>

<script type="text/javascript" src="${global_js_url}scm/gsCountryState.js"></script>
<script src="${global_js_url}jquery/jquery.dialog.all.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js" type="text/javascript"></script>

<script type="text/javascript" src="${global_js_url}customer/customer_fun.js"></script>
<script type="text/javascript" src="${global_js_url}scm/customer.js"></script>
<script type="text/javascript" src="${global_js_url}customer/customer_validate.js"></script>
<script type="text/javascript" src="${global_js_url}customer/customer_trigger.js"></script>
<script type="text/javascript" src="${global_js_url}scm/orgPicker.js"></script>
<script type="text/javascript" src="${global_js_url}scm/divPicker.js"></script>
<script type="text/javascript" src="${global_js_url}scm/deptPicker.js"></script>
<script>
    var isSaved = false;
    window.onbeforeunload = function() {
        if (isSaved === false) {
            return 'Do you want to leave without saving data?';
        }
    }
</script>
</body>
</html>
