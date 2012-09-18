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
    <script src="${global_js_url}scm/serv_inventory.js?v=2" type="text/javascript"></script>
    <style>
        .General_table {
            margin: 4px 0px;
        }

        .General_table fieldset {
            margin: 4px;
        }
    </style>
</head>
<script>
    function gochange() {
        var tmpVal = $("#prefWarehouse").val();
        changePrefWarehouse2(tmpVal, '');
    }

	function changePrefWarehouse2(prefWarehouse, prefStorage){
		$("#prefWarehouse option").each(function(i){
			if($(this).val() == prefWarehouse){
				$(this).attr("selected", true);
				var jsonStorageList = $(this).attr("_h");
				var storageList = eval(jsonStorageList);
				$("#prefStorage").html("");
				for(var o in storageList){
					var selectedStr = '';
					if(prefStorage == storageList[o].storageId){
						selectedStr = 'selected';
					}
					var tmpStr = "<option value='"+storageList[o].storageId+"' "+selectedStr+">"+storageList[o].name+"</option>";
					$("#prefStorage").append(tmpStr);
				}
			}
		});
	}
</script>

<body class="content" style="background:#FFFFFF;" onload="gochange()">

<div class="scm" style="background:#FFFFFF;">
    <input type="hidden" value="${psId}" id="psId" name="psId"/>
    <input type="hidden" value="${sessionServiceId}" id="sessionServiceId"/>
    <input type="hidden" value="${psId}" id="pdtId" name="pdtId"/>
    <input type="hidden" value="${service.catalogNo}" id="catalogNo" name="catalogNo"/>
    <input type="hidden" id="init_prefWarehouse" value="${service.prefWarehouse}"/>
    <input type="hidden" id="init_prefStorage" value="${service.prefStorage}"/>
    <table width="970" border="0" cellpadding="0" cellspacing="0" class="General_table" style="margin-top:-10px;">
        <tr>
            <td width="50%">
                <fieldset style="height:139px;">
                    <legend>Current Stock Information</legend>
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
                        <tr>
                            <th>Units In Stock</th>
                            <td>
                                <input type="text" class="NFText" value="${stockStatDTO.stockTotal}" size="20"
                                       readonly="readonly"/></td>
                        </tr>
                        <tr>
                            <th>Units Committed</th>
                            <td><input type="text" class="NFText" value="${stockStatDTO.commitedTotal}" size="20"
                                       readonly="readonly"/></td>
                        </tr>
                        <tr>
                            <th>Net on Shelf</th>
                            <td><input type="text" class="NFText"
                                       value="${stockStatDTO.commitedTotal + stockStatDTO.stockTotal}" size="20"
                                       readonly="readonly"/></td>
                        </tr>
                        <tr>
                            <th>Unprocessed Returns</th>
                            <td><input type="text" class="NFText" value="${stockStatDTO.unProcessedTotal}" size="20"
                                       readonly="readonly"/></td>
                        </tr>
                        <tr>
                            <th>Units On Back Order</th>
                            <td><input type="text" class="NFText" value="${stockStatDTO.backOrderTotal}" size="20"
                                       readonly="readonly"/></td>
                        </tr>
                    </table>
                </fieldset>
            </td>
            <td valign="top">
                <fieldset>
                    <legend>Warehouse Preference</legend>
                    <table border="0" cellpadding="0" cellspacing="0" class="General_table">
                        <tr>
                            <td>

                                <input name="radiobuttonasd" type="radio" value="radiobutton" checked="checked"/>
                            </td>
                            <td><span class="important">*</span> Always use warehouse
                                <select name="prefWarehouse" id="prefWarehouse" onchange="gochange()">
                                    <s:iterator value="warehouseList" status="warehouseList">
                                        <option value="<s:property value="warehouseId"/>" <s:if test="service">checked</s:if> _h='[<s:iterator value="storageList"
                                                                                                  status="storageList">{"name":"<s:property
                                            value="name"/>","storageId":<s:property value="storageId"/>}<s:if
                                            test="!#storageList.last">,</s:if></s:iterator>]'><s:property
                                            value="name"/></option>
                                    </s:iterator>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div align="center">
                                    <s:if test='service.altWarehouseFlag== "Y"'>
                                        <input name="service.altWarehouseFlag" id="altWarehouseFlag" type="checkbox"
                                               checked="checked"/>
                                    </s:if>
                                    <s:else>
                                        <input name="service.altWarehouseFlag" id="altWarehouseFlag" type="checkbox"/>
                                    </s:else>
                                </div>
                            </td>
                            <td>Use an alternate warehouse if ${type} is available elsewhere</td>
                        </tr>
                    </table>
                </fieldset>
                <fieldset>
                    <legend>Storage Location Preference</legend>
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
                        <tr>
                            <th><span class="important">*</span> Storage Location</th>
                            <td>
                                <select name="prefStorage" id="prefStorage" style="width:250px"></select>
                            </td>
                        </tr>
                    </table>
                </fieldset>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <fieldset>
                    <legend>Restricted Shipping Area</legend>
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td>
                                <table width="913" border="0" cellpadding="0" cellspacing="0" class="list_table"
                                       style="margin-top:5px;">
                                    <tr>
                                        <th width="46">
                                            <div align="left">
                                                <input type="checkbox" onclick="checkAllRS(this, 'rsId');"/>
                                                <img id="deleteShipAreaImg" src="images/file_delete.gif" alt="Delete"
                                                     width="16" height="16" border="0"/>
                                            </div>
                                        </th>
                                        <th width="250">Country</th>
                                        <th width="200">State</th>
                                        <th width="100">Zip</th>
                                        <th width="150">Start Date</th>
                                        <th>End Date</th>
                                    </tr>
                                </table>
                                <div class="frame_box12">
                                    <table width="913" border="0" cellpadding="0" cellspacing="0" class="list_table">
                                        <s:iterator value="restrictShipList" status="item">
                                            <s:if test="key!=\" delIdStr\"&&key!=\"\"">
                                            <tr>
                                                <td width="46">&nbsp;
                                                    <input type="checkbox" name="rsId" value="<s:property value="key"/>"
                                                    />
                                                </td>
                                                <td width="250" align="center">
                                                    <a href='javascript:void(0);'
                                                       onclick="shipAreaEdit('<s:property value="key"/>');">
                                                    &nbsp;<s:property value="value.countryName"/></a>
                                                </td>
                                                <td width="200" align="center">&nbsp;<s:property
                                                        value="value.stateName"/>&nbsp;</td>
                                                <td width="100" align="center">&nbsp;<s:property value="value.zipCode"/>&nbsp;
                                                </td>
                                                <td width="150" align="center">&nbsp;<s:property value="value.effFrom"/>&nbsp;
                                                </td>
                                                <td align="center">&nbsp;<s:property value="value.effTo"/></td>
                                            </tr>
                                            </s:if>
                                        </s:iterator>

                                    </table>
                                </div>
                            </td>
                        </tr>
                    </table>
                </fieldset>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div align="center">
                    <input id="shipAreaAddTrigger" type="button" class="style_botton" value="Add"/>
                    <input id="shipAreaEditTrigger" type="hidden" class="style_botton" value=""/>
                </div>
            </td>
        </tr>
    </table>
</div>

</body>
</html>