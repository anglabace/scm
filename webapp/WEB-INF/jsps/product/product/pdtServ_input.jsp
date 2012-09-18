<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"  />

<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script src="${global_js_url}scm/country_state.js?v=5" type="text/javascript"></script>
<script src="${global_js_url}scm/pdtServ_inventory_add.js" type="text/javascript"></script>
</head>

<body>
<table width="500" border="0" cellspacing="0" cellpadding="0">
<tr>
  <td colspan="2">
      <input type="hidden" value="${psId}" id="psId" name="psId" />
       <input type="hidden" value="${sessionPSID}" id="sessionPSID"/>
  <input type="hidden" value='${countryStates}' id="init_countryStates"  name="init_countryStates"/>
  <div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab TabbedPanelsTabSelected" tabindex="0">Country</li>
    <li class="TabbedPanelsTab" tabindex="1">State</li>
	<li class="TabbedPanelsTab" tabindex="2">Zip Code</li>
  </ul>
   <div class="TabbedPanelsContentGroup">
        <div style="display:block;" class="TabbedPanelsContent TabbedPanelsContentVisible">
           <table width="500" border="0" cellpadding="0" cellspacing="0" class="list_table" style="margin-top:5px;" id="countryTHTable" >
             <tr>
               <th width="30">
               	<input type="checkbox"  onclick="checkAll(this, 'country');"  />
               </th>
               <th width="80">Seq No </th>
               <th>Country</th>
             </tr>
           </table>
           <div style="width:517px;height:100PX;overflow:scroll;">
	           <table width="500" border="0" cellpadding="0" cellspacing="0" class="list_table" id="countryTable">
	           </table>
       		</div>
	   </div>

	   <div style="display: block;" class="TabbedPanelsContent TabbedPanelsContentVisible">
		  	<table width="500" border="0" cellpadding="0" cellspacing="0" class="list_table" style="margin-top:5px;" id="stateTHTable" >
             <tr>
               <th width="30">
               	<input type="checkbox"  onclick="checkAll(this, 'state');"/>
               </th>
               <th width="80">Seq No </th>
               <th width="150">State</th>
	       <th width="240">Country</th>
             </tr>
           </table>
		    <div style="width:517PX;height:100PX;overflow:scroll">
				<table width="500" border="0" cellpadding="0" cellspacing="0" class="list_table" id="stateTable">
	            </table>
		 	</div>
		 </div>

		  <div style="display:block;" class="TabbedPanelsContent TabbedPanelsContentVisible" >
			 <table width="500" border="0" cellpadding="0" cellspacing="0" class="list_table" style="margin-top:5px;">
	             <tr>
	               <th width="30">
	               		<input type="checkbox" onclick="checkAll(this, 'zipCode');" />
	               </th>
	               <th width="80">Seq No </th>
	               <th width="80">Zip Code </th>
	               <th width="150">City</th>
	               <th width="50">State</th>
				   <th>Country</th>
	             </tr>
	         </table>
		      <div style="width:517px;height:100px;overflow:scroll;">
			        <table width="500" border="0" cellpadding="0" cellspacing="0" class="list_table" id="zipTable" >
		            </table>
		        </div>
		        <div class="grayr" id="pager"></div>
		  </div>

  	</div>

    </div>

	</td>
  </tr>

  <tr>
    <th width="130">Date Restriction : </th>
    <td width="470">&nbsp;</td>
  </tr>
  <tr id="">
    <td>&nbsp;</td>
    <td>
      <input type="radio" name="dateRestriction" value="1" onclick="initDateTr(this.value);" checked="checked" />
    	No Date Restriction
    </td>
  </tr>
  <tr id="">
    <td>&nbsp;</td>
    <td colspan="2">
    	<input type="radio" name="dateRestriction" value="2" onclick="initDateTr(this.value);" />
	    Restricted from
	    <span style="display:none;" id="dateSpan">
        	<input name="effFrom" id="effFrom" type="text" class="pickdate" style="width:75px;" value="     -   -  " size="18"/>
        	&nbsp;&nbsp;to
	    	<input name="effTo" id="effTo" type="text" class="pickdate" style="width:75px;" value="     -   -  " size="18"/>
	    </span>
	</td>
  </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3">
	    <div class="botton_box">
	      <input type="submit" id="shipAreaSave" class="style_botton"  value="Add" />
	      <input type="submit" name="Submit622" value="Cancel"  class="style_botton" onclick="parent.$('#shipAreaAddDialog').dialog('close');" />
		</div>
  </td>
  </tr>
</table>
</body>
</html>