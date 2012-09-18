<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Management</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}ajax.js"></script>

<link href="${global_css_url}tab-view.css" rel="stylesheet"
	type="text/css" />
<script language="javascript" type="text/javascript"
	src="${global_js_url}TabbedPanels.js"></script>
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet"
	type="text/css" />

<script language="javascript" type="text/javascript"
	src="${global_js_url}jquery/jquery.js"></script>
<script
	src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js"
	type="text/javascript"></script>
<link type="text/css"
	href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script src="${global_js_url}jquery/ui/ui.core.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.draggable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.resizable.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.dialog.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js"
	type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js?v=1"
	type="text/javascript"></script>
<script language="javascript" type="text/javascript"
	src="${global_js_url}scm/product/manager_task.js?v=2"></script>
<script src="${global_js_url}util/util.js" type="text/javascript"></script>
<script src="${global_js_url}/recordTime.js" type="text/javascript"></script>
<script>
$(function() {            
	$('#organization').dialog({
		autoOpen: false,
		height: 450,
		width: 700,
		modal: true,
		bgiframe: true,
		buttons: {
		}
	});
});

function openEditAddress(packageId,count) {
	$('#organization').dialog("option", "open", function() {	
	var htmlStr = '<iframe src="basedata/org_picker.action?disableNew=1&shippingInput=shippingInput" height="400" width="650" scrolling="no" style="border:0px" frameborder="0"></iframe>';
	$('#organization').html(htmlStr);
	});
	$('#organization').dialog('open');
}
</script>
</head>
 
<body>
<table width="650" border="0" cellpadding="0" cellspacing="0">
     
      <tr>
        <td valign="top"><table  border="0" cellpadding="0" cellspacing="0" class="General_table" style="margin:10px auto 0px auto;">
          <tr>
            <th rowspan="2" valign="top">Name</th>
            <th><div align="left">
              <select size="1" id="select3" name="select" style="width:55px;">
                <option>Dr.</option>
                
                </select>
              <span class="important"> *</span>
              First
              <input name="first"  type="text"  class="NFText" id="first" value="" size="19"/>
              </div></th>
            <th><span class="important">*</span>Last</th>
            <td><input name="last" type="text" id="last" size="20"  class="NFText"/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
          </tr>
          <tr>
            <th>&nbsp;</th>
            <th>Middle</th>
            <td><input name="middle" type="text" id="middle" size="20"  class="NFText"/></td>
          </tr>
          <tr>
            <th>Title</th>
            <td><input name="title" type="text"  class="NFText" id="title" value="" size="38"/></td>
            <th ><span class="important">*</span>Organization</th>
            <td><input name="organizationInput" id="organizationInput" type="text"  class="NFText" value="" size="38"/>
              <img src="images/search.gif" width="16" height="16" id="orgAddDialogTrigger1" onclick="openEditAddress()" style="cursor:pointer;"/></td>
          </tr>
          <tr>
            <th>Phone</th>
            <td><input name="phone" type="text"  class="NFText" id="phone" value="" size="20"/>
              &nbsp;&nbsp;&nbsp;<strong>Ext</strong>
              <input name="pext" type="text"  class="NFText" id="pext" value="" size="5"/></td>
            <th >Alt</th>
            <td><input name="alt" type="text" id="alt" size="20"  class="NFText"/>
              &nbsp;&nbsp;&nbsp;<strong>Ext</strong>
              <input name="aext" type="text"  class="NFText" id="aext" value="" size="5"/></td>
          </tr>
          <tr>
            <th>Email</th>
            <td><input name="email" type="text" id="email" size="38"  class="NFText"/></td>
            <th >Fax</th>
            <td ><input name="fax" type="text" id="fax" size="20"  class="NFText"/>
              &nbsp;&nbsp;&nbsp;<strong>Ext</strong>
              <input name="fext" type="text"  class="NFText" id="fext" value="" size="5"/></td>
          </tr>
          <tr>
            <th><span class="important">*</span>Address</th>
            <td><input name="address1" type="text" id="address1" size="38"  class="NFText" /></td>
            <th><span class="important">*</span>City</th>
            <td><input name="city" type="text"  class="NFText" id="city" value="" size="38"/></td>
          </tr>
          <tr>
            <th>&nbsp;</th>
            <td><input name="address2" type="text"   class="NFText" id="address2" size="38"/></td>
            <th><span class="important">*</span>State</th>
            <td><input name="state" type="text" id="state" size="20"  class="NFText"/>
              &nbsp;&nbsp;&nbsp;<span class="important">*</span><strong>Zip</strong>
              <input name="zip" type="text" class="NFText" id="zip" value="" size="5" /></td>
          </tr>
          <tr>
            <th>&nbsp;</th>
            <td><input name="address3" type="text"   class="NFText" id="address3" size="38"/></td>
            <th><span class="important">*</span>Country</th>
            <td><s:select list="countryList" listKey="countryCode" listValue="countryCode" id="country"></s:select></td>
          </tr>
          <tr>
            <th>&nbsp;</th>
            <td colspan="3">
            <input name="addrClass" id="addrClass1" type="radio"
					value="Commercial" /> Commercial 
					
			<input type="radio" name="addrClass" id="addrClass2" value="Residential"/>
					Residential</td>
          </tr>
          <tr>
            <td colspan="4"><div  class="botton_box">
              <br />
  <input name="Submit22" type="submit" class="style_botton" value="Save" onclick="save()"/>
              <input type="submit" name="Submit622" value="Cancel"  class="style_botton" onclick="parent.$('#edit_address').dialog('close')"/>
              <br />
  </div></td>
          </tr>
        </table>
        </td>
      </tr>
    </table>
    <div id="organization" title=" Organization Lookup "
			style="visible: hidden" />
</body>
<script>
	$("#first").val(parent.$("#rcpFirstName").val());
	$("#middle").val(parent.$("#rcpMidName").val());
	$("#last").val(parent.$("#rcpLastName").val());
	$("#title").val(parent.$("#rcpTitle").val());
	$("#organizationInput").val(parent.$("#rcpOrgName").val());
	$("#phone").val(parent.$("#rcpPhone").val());
	$("#email").val(parent.$("#rcpBusEmail").val());
	$("#address1").val(parent.$("#rcpAddrLine1").val());
	$("#address2").val(parent.$("#rcpAddrLine2").val());
	$("#address3").val(parent.$("#rcpAddrLine3").val());
	$("#state").val(parent.$("#rcpState").val());
	$("#zip").val(parent.$("#rcpZipCode").val());
	$("#city").val(parent.$("#rcpCity").val());
	
	$("#fax").val(parent.$("#rcpFax").val());
	$("#country").attr("value",parent.$("#rcpCountry").val());
	var addrClass = parent.$("#rcpAddrClass").val();
	
	if(addrClass=="Commercial") {
		$("#addrClass1").attr("checked","checked");
	}
	if(addrClass=="Residential") {
		$("#addrClass2").attr("checked","checked");
	}	
	function save(){
		parent.$("#rcpFirstName").val($("#first").val());
		parent.$("#rcpMidName").val($("#middle").val());
		parent.$("#rcpLastName").val($("#last").val());
		parent.$("#rcpTitle").val($("#title").val());
		parent.$("#rcpOrgName").val($("#organizationInput").val());
		parent.$("#rcpPhone").val($("#phone").val());
		parent.$("#rcpBusEmail").val($("#email").val());
		parent.$("#rcpAddrLine1").val($("#address1").val());
		parent.$("#rcpAddrLine2").val($("#address2").val());
		parent.$("#rcpAddrLine3").val($("#address3").val());
		parent.$("#rcpState").val($("#state").val());
		parent.$("#rcpZipCode").val($("#zip").val());
		parent.$("#rcpCity").val($("#city").val());
		parent.$("#rcpFax").val($("#fax").val());
		parent.$("#rcpCountry").val($("#country option:selected").val());
		
		var addrClass = $('input:radio[name="addrClass"]:checked').val();
		parent.$("#rcpAddrClass").val(addrClass);
		var shipToAddr=" ";
		shipToAddr +=$("#first").val();
		shipToAddr +=" "+$("#last").val()+"\n";
		shipToAddr +=" "+$("#organizationInput").val()+"\n";
		shipToAddr +=" "+$("#address1").val()+"\n";
		shipToAddr +=" "+$("#city").val()+","+$("#state").val()+ $("#zip").val()+","+$("#country option:selected").val()+"\n";
		parent.$("#shiptoAddress").val(shipToAddr);
		parent.$('#edit_address').dialog('close');
	}
</script>
</html>

