<%-- 
    Document   : shipping_method_charge Show Shipping method charge Form
    Created on : 2010-10-12, 10:30:58
    Author     : Lichun Cui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!-- {get_com_selects value="CUSTOM_CHARGE_APPLY_TYPE"}-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <base id="myBaseId" href="${global_url}" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Order Management</title>
        <link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
        <link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
        <link href="greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
        <link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
        <link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
        <script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
        <script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
        <script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>

        <script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
        <script language="javascript" type="text/javascript" src="${global_js_url}scm/gs.util.js"></script>

        <script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
        <link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
        <script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
        <script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
        <script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
        <script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
        <script src="${global_js_url}jquery/jquery.validate.js?v=1" type="text/javascript"></script>
        <script>
            $(function(){
                var perPoundCharge = '${result.wtChargeType}';
                if (perPoundCharge == 'per')
                    changeTabClassWithPrefix(2, 'document', 'tabDisable', 'tabTabdhtmlgoodies_tabView4_');
            });
            function toggle_actual(obj){
                if (obj.checked){
                    $("#actualChrgPct").removeAttr('disabled');
                    $("#actualChrgTp").removeAttr('disabled');
                }else{
                    $("#actualChrgPct").attr('disabled', 'disabled');
                    $("#actualChrgPct").val('');
                    $("#actualChrgTp").attr('disabled', 'disabled');
                }
            }
            function toogle_weight(){                
                var chkObj = document.getElementsByName('wtChargeType');
                if (chkObj[0].checked){
                    //disableTabByTitle('Weight Range Charges');
                    changeTabClassWithPrefix(2, 'document', 'tabDisable', 'tabTabdhtmlgoodies_tabView4_');
                    
                }else{
                    //enableTabByTitle('Weight Range Charges');
                    changeTabClassWithPrefix(2, 'document', 'tabInactive', 'tabTabdhtmlgoodies_tabView4_');
                    
                }
                if(chkObj[1].checked){
                     $("#wtChrgAppTp").removeAttr('disabled');
                }
                else{
                     $("#wtChrgAppTp").attr('disabled', 'disabled');
                }
            }
            function set_selected(id, val)
            {
                var obj = document.getElementById(id);
                for(var i=0;i<obj.options.length;i++)
                    if(obj.options[i].value == val)
                        obj.options[i].selected = true;
            }

        </script>
    </head>
    <body>
        <div id="dhtmlgoodies_tabView4" >
            <div class="dhtmlgoodies_aTab_new900">
                <fieldset>
                    <legend>Box / Item  Charges</legend>
                    <form id="basic_charge_form">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
                            <tr>
                                <th width="18%" height="24">Per Box Charge</th>
                                <td width="32%"><input name="perBoxChrg" id="perBoxChrg" type="text" class="NFText" value="${shipRateCustomerBasic.perBoxChrg}" size="10" onblur="check_number(this)" /></td>
                                <th width="15%">Apply Charge to</th>
                                <td width="35%"><span style="display:block;">
                                        <select style="width: 150px;" name="perBoxAppTp" id="perBoxAppTp">
                                            <option value="FIRST" label="First shipment only">First shipment only</option>
                                            <option  value="SUBSEQUENT" label="Subsequent shipments only">Subsequent shipments only</option>
                                            <option value="ALL" label="All shipments">All shipments</option>
                                        </select>
                                    </span></td>
                            </tr>
                            <tr>
                                <th height="24">Per Item Charge</th>
                                <td><input id="perItemChrg" name="perItemChrg" type="text" class="NFText" value="${shipRateCustomerBasic.perItemChrg}" size="10" onblur="check_number(this)" /></td>
                                <th>Apply Charge to</th>
                                <td><span style="display:block;">
                                        <select style="width: 150px;" name="perItemAppTp" id="perItemAppTp">
                                            <option value="FIRST" label="First shipment only">First shipment only</option>
                                            <option value="SUBSEQUENT" label="Subsequent shipments only">Subsequent shipments only</option>
                                            <option value="ALL" label="All shipments">All shipments</option>
                                        </select>
                                    </span></td>
                            </tr>
                        </table>
                </fieldset>

                <fieldset>
                    <legend>Weight Charges</legend>
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
                        <tr>
                            <th width="5%" height="24">
                                <input id="wtChargeType" name="wtChargeType" type="radio" value="per" <s:if test="shipRateCustomerBasic.wtChargeType==\"per\"">checked</s:if> onclick="toogle_weight()" /></th>
                            <th width="13%">Per Pound Charge</th>
                            <td colspan="2"><input id="perPndChrg" name="perPndChrg" type="text" class="NFText" value="${shipRateCustomerBasic.perPndChrg}" size="10" onblur="check_number(this)" /></td>
                            <th width="15%">&nbsp;</th>
                            <td width="35%">&nbsp;</td>
                        </tr>
                        <tr>
                            <th><input name="wtChargeType" id="wtChargeType" type="radio" value="range" <s:if test="shipRateCustomerBasic.wtChargeType==\"range\"">checked</s:if> onclick="toogle_weight()" /></th>
                            <th colspan="3"><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;Weight Range Charge</div></th>
                            <th>Apply Charge to</th>
                            <td><span style="display:block;">
                                    <select style="width: 150px;" name="wtChrgAppTp" id="wtChrgAppTp">
                                        <option value="FIRST" label="First shipment only">First shipment only</option>
                                        <option  value="SUBSEQUENT" label="Subsequent shipments only">Subsequent shipments only</option>
                                        <option value="ALL" label="All shipments">All shipments</option>
                                    </select>
                                </span></td>
                        </tr>
                    </table>
                </fieldset>
                <div class="tablenew">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
                        <tr>
                            <th width="7%" style="margin-right:5px;">
                                <input id="actualChecked" type="checkbox" onclick="toggle_actual(this)" <s:if test="shipRateCustomerBasic.actualChrgTp!=null">checked</s:if>/></th>
                            <th width="13%">Actual Charges</th>
                            <td><input id="actualChrgPct" name="actualChrgPct" type="text" class="NFText" value="${shipRateCustomerBasic.actualChrgPct}" size="10" <s:if test="shipRateCustomerBasic==null||shipRateCustomerBasic.actualChrgTp==\"\"">disabled</s:if> onblur="check_number(this)" />
                                %</td>
                            <th width="15%">Apply Charge to</th>
                            <td width="36%"><span style="display:block;">
                                    <select style="width: 150px;" name="actualChrgTp" id="actualChrgTp" <s:if test="#request.shipRateCustomerBasic==null||#request.shipRateCustomerBasic.actualChrgTp==\"\"">disabled</s:if>>
                                        <option value="FIRST" label="First shipment only">First shipment only</option>
                                        <option value="SUBSEQUENT" label="Subsequent shipments only">Subsequent shipments only</option>
                                        <option value="ALL" label="All shipments">All shipments</option>
                                    </select>

                                </span></td>
                        </tr>
                    </table>
                    </form>
                </div>
            </div>

            <div class="dhtmlgoodies_aTab_new900">
                <iframe id="total_range_frame" name="total_range_frame" src="system/shipping_method!listTotalRange.action?id=${id}&idStr=${idStr}" height="320" width="940" frameborder="0"  scrolling="no"></iframe>
            </div>
            <div class="dhtmlgoodies_aTab_new900">
                <iframe id="weight_range_frame" name="weight_range_frame" src="system/shipping_method!listWeightRange.action?id=${id}&idStr=${idStr}" height="320" width="940" frameborder="0"  scrolling="no"></iframe>
            </div>

        </div>

        <script type="text/javascript">
            initTabs_new('900','dhtmlgoodies_tabView4',Array('Basic Charges','Merchandise Total Range Charges','Weight Range Charges'),0,900,320);

            set_selected("perBoxAppTp", "${shipRateCustomerBasic.perBoxAppTp}");
            set_selected("perItemAppTp", "${shipRateCustomerBasic.perItemAppTp}");
            set_selected("wtChrgAppTp", "${shipRateCustomerBasic.wtChrgAppTp}");
            set_selected("actualChrgTp", "${shipRateCustomerBasic.actualChrgTp}");
            $().ready(
            function(){
               
                toogle_weight();
               // alert(document.getElementById("actualChecked").checked);
                toggle_actual(document.getElementById("actualChecked"));
             }


        );

        </script>
    </body>
</html>
