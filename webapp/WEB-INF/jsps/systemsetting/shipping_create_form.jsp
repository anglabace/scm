<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglib.jsp" %>
<head>
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

<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1" type="text/javascript"></script>
<script language="JavaScript" type="text/javascript">
function checkSave(){
	if ($('#savedFlag').val() != 'Y'){
		return 'Unsaved data will be losted, are you sure to continue?';
	}
}
window.onbeforeunload = checkSave;
</script>
<script type="text/javascript" src="${global_js_url}scm/setting_shipping_methods.js"></script> 
</head>
<body class="content" >

<div class="scm">
<div class="title_content">
<div class="title"><s:if test="opType == 'add'">New Shipping Method</s:if><s:else>${carrier} - ${name}(${methodCode})</s:else></div>
</div>
<div class="input_box">
<form id="method_main">
<input type="hidden" id="methodId" name="methodId" value="<s:if test="opType == 'add'"></s:if><s:else>${id}</s:else>" />
<input type="hidden" id="id" name="id" value="<s:if test="opType == 'add'">${id}</s:if><s:else>${methodId}</s:else>" />
<input type="hidden" id="opType" name="opType" value="${opType}" />
<input type="hidden" id="savedFlag" />
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="General_table">
    <tr>
      <th width="140">Shipping Method Code </th>
      <td width="424">
      <input id="methodCode" name="methodCode" type="text" class="NFText" value="${methodCode}" size="20" <s:if test="opType == 'edit'">readonly</s:if> /></td>
      <th width="92">Name</th>
      <td width="346">
        <input id="name" name="name" type="text" class="NFText" value="${name}" size="20" /> 
      </td>
    </tr> 
	<tr>
	  <th valign="top">Description</th>
	  <td>
	    <textarea name="description" class="content_textarea2">${description}</textarea>
	    </td>
	  <th>Status</th>
	  <td valign="top">
		 <s:select cssStyle="width:131px" name="status" list="statusMap" listKey="key" listValue="value" value="status"></s:select>
        </td>
	  </tr>
    <tr>
      <th> Modified Date</th>
      <td><input name="modifyDate" type="text" class="NFText" value="<s:date name="modifyDate" format="yyyy-MM-dd"/>" size="20" readonly="yes" /></td>

      <th>Modified By </th>
      <td><input name="modifiedByUser" type="text" class="NFText" value="${modifiedByUser}" size="20" readonly="yes"/></td>
    </tr>
  </table>
</form>
</div>


  <div id="dhtmlgoodies_tabView1">
    <div class="dhtmlgoodies_aTab">
	<div class="general_box" style="height:370px;">

	<form id="method_general">
    <table border="0" cellpadding="0" cellspacing="0" class="General_table">
		<tr>
          <th colspan="2">&nbsp;</th>
          <td colspan="2">&nbsp;</td>
          <th width="219">&nbsp;</th>
          <td width="219">&nbsp;</td>
        </tr>
        <tr>
          <th colspan="2">Package Carrier</th>

          <td width="192" valign="top">
			<s:select cssStyle="width:182px" name="carrier" list="specDropDownList['SHIP_CARRIERS'].dropDownDTOs" listKey="id" listValue="name" headerKey="" headerValue="" value="carrier" onchange="get_track_url(this)"></s:select>
          </td>

          <td width="139" valign="top">&nbsp;</td>
          <th>Zone</th>
          <td valign="top">
			<s:select cssStyle="width:182px" name="standardZone" list="specDropDownList['SHIP_ZONE'].dropDownDTOs" listKey="id" listValue="name" headerKey="" headerValue="" value="standardZone" onchange="get_rate(this)"></s:select>
         </td>
        </tr>

        <tr>
          <th colspan="2" valign="top">Rate</th>
          <td colspan="2" valign="top">
          <s:if test="opType == 'edit'">
			<s:select cssStyle="width:182px" name="standardRate" list="specDropDownList['RATE_LIST'].dropDownDTOs" listKey="id" listValue="name" headerKey="" headerValue="" value="standardRate" onchange="change_rate(this)"></s:select>
			</s:if>
			<s:else>
				<select name="standardRate" id="standardRate" style="width:182px;" onchange="change_rate(this)">
				<option></option></select>
			</s:else>
          </td>
          <th>Shipping Account</th>
          <td valign="top"><input name="shippingAcount" type="text" class="NFText" size="30" value="${shippingAcount}" /></td>
        </tr>
        <tr>
          <th colspan="2">Shipment Tracking URL</th>
          <td colspan="2" valign="top"><input name="trackUrl" type="text" class="NFText" size="32" id="trackUrl" value="${trackUrl}"/></td>
          <th>Declared Insurance Value Base On</th>
          <td valign="top"> 
			<s:select cssStyle="width:182px" name="insuranceBase" list="dropDownList['SHIP_METHOD_INSURANCE_BASE']" listKey="value" listValue="value" headerKey="" headerValue="" value="insuranceBase"></s:select>
          </td>
        </tr>
        <tr>
          <th width="196">Comparative Shipping Methods</th>
          <th width="15" align="right">1.</th>
          <td colspan="2">
			<s:select cssStyle="width:322px" name="cmprMethod1" list="method1Map" listKey="key" listValue="value" headerKey="" headerValue="" value="cmprMethod1"></s:select>
          </td>
          <th rowspan="2">&nbsp;</th>
          <td rowspan="2"><table border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td>
                <input type="checkbox" name="printFlag" id="printFlag" <c:if test="${printFlag == 'Y'}">checked</c:if>/>
             </td>
              <td>Print Shipping Manifest/Report</td>

            </tr>
            <tr>
              <td><input type="checkbox" name="externalFlag" id="externalFlag" <c:if test="${externalFlag == 'Y'}">checked</c:if>/></td>
              <td>Shipping Method is Available Externally</td>
            </tr>
          </table></td>
        </tr>
        <tr>

          <th>&nbsp;</th>
          <th align="right">2.</th>
          <td colspan="2">
			<s:select cssStyle="width:322px" name="cmprMethod2" list="method2Map" listKey="key" listValue="value" headerKey="" headerValue="" value="cmprMethod2"></s:select>
          </td>
        </tr>
      </table>
		</form>
      
      </div>

    </div>
    <div class="dhtmlgoodies_aTab">
<iframe id="zone_frame" name="zone_frame" src="shipping_method!listZone.action?id=${id}&warehouseId=${warehouseId}" height="390" width="980" frameborder="0"  scrolling="no"></iframe>
    </div>
    
<div class="dhtmlgoodies_aTab">
<iframe id="rate_frame" name="rate_frame" src="shipping_method!listRate.action?id=${id}&sessionId=${idStr}" height="390" width="980" frameborder="0"  scrolling="no"></iframe>
</div>
<div class="dhtmlgoodies_aTab">
<iframe id="charge_frame" name="charge_frame" src="system/shipping_method!showCharge.action?id=${id}" height="390" width="980" frameborder="0"  scrolling="no"></iframe>
</div> 
    
  </div>

</div>
<div class="button_box">
<saveButton:saveBtn parameter="${operation_method}"
	disabledBtn='<input type="submit" id="saveAllTrigger" value="Save" class="search_input" disabled="disabled" />'
	saveBtn='<input type="submit" id="saveAllTrigger" value="Save" class="search_input" />'
/> 
  <input type="button" name="Submit124" value="Cancel" class="search_input" onclick="cancelAll()" />
</div>
<script type="text/javascript">
initTabs('dhtmlgoodies_tabView1',Array('General','Zone','Rate','Shipping Charge Customization'), ${activeTabIndex}, 998, 380);
</script>
</body>

</html>
