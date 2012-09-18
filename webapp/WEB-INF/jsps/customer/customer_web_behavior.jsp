<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/taglib.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="${global_url}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>scm</title>
<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" />
<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}scm/customer_web_behavior.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}scm/gs.util.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}scm/config.js"></script>
<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
   
   
<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.dialog.all.js" type="text/javascript"></script>
<script src="${global_js_url}jquery/jquery.validate.js" type="text/javascript"></script>

<link href="stylesheet/tab-view.css" rel="stylesheet" type="text/css" />
<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="dhtmlgoodies_tabView1">
	<div class="dhtmlgoodies_aTab">
		  <table border="0" cellpadding="0" cellspacing="0" class="General_table">
			<tr>
			  <td align="right">Number of Visits </td>
			  <td><input id="visitTotal" readonly type="text" class="NFText" size="15" /></td>
			  <td align="right">First Visit </td>
			  <td><input id="firstVisit" type="text" readonly class="NFText" size="15" /></td>
			  <td align="right"><strong>Last  Visit</strong></td>
			  <td >   
				<input id="lastVisit" type="text" readonly class="NFText" size="20" /> </td>
			</tr>
			<tr>
			  <td>Number of Pages Viewed </td>
			  <td><input id="visitPagesTotal" type="text" readonly class="NFText" size="15" /></td>
			  <td>Average Visit Duration </td>
			  <td><input id="avgStaySecd" type="text" class="NFText" readonly size="15" /></td>
			  <td>File Downloaded </td>
			  <td ><input name="textfield32232" type="text" class="NFText" readonly size="20" />                 
			  </td>
			</tr>
		  </table>
		  
		<div id="dhtmlgoodies_tabView2">
		
		<div class="dhtmlgoodies_aTab_new740">
			<table  style="TABLE-LAYOUT:fixed;word-break:break-all" border="0" cellpadding="0" cellspacing="0" class="list_table">
			   <tr>
				 <th width="45">Seq No </th>
				 <th width="370">URL</th>
				 <th width="80">View Times </th>
				 <th width="65">First View </th>
				 <th width="65">Last View </th>
				 <th width="110">Avg Visibility Time </th>
			   </tr>
			</table>	  
	<div style="height:100%">
    	<iframe id="pageViewId" name="pageViewId" src="customer_web_behavior!getPageViewList.action?cust_no=${request.cust_no}" width="100%" height="100%" frameborder="0" scrolling="no" ></iframe>
   	</div>
</div>
     <div class="dhtmlgoodies_aTab_new740" style="display:none">
			    <table  style="TABLE-LAYOUT:fixed;word-break:break-all" border="0"  cellpadding="0" cellspacing="0" class="list_table">
				 <tr>
				   <th width="60">Seq No </th>
				   <th width="305">Product Name </th>
				   <th width="120">View Times </th>
				   <th width="120">First View </th>
				   <th width="120">Last View </th>
				   </tr>
				</table>
	<div style="height:100%">
    	<iframe id="productId" name="productViewId" src="customer_web_behavior!getProductViewList.action?cust_no=${request.cust_no}" width="100%" height="100%" frameborder="0" scrolling="no" target="pageViewId"></iframe>
   	</div>	
   	</div>
		 <div class="dhtmlgoodies_aTab_new740" style="display:none">
		   <table  border="0" style="TABLE-LAYOUT:fixed;word-break:break-all" cellpadding="0" cellspacing="0" class="list_table">
			 <tr>
			   <th width="60">Seq No </th>
			   <th width="305">Service  Name </th>
			   <th width="120">View Times </th>
			   <th width="120">First VIew </th>
			   <th width="120">Last View </th>
			   </tr>
			</table>
			<div style="height:100%">
    <iframe id="serviceView" name="serviceView" src="customer_web_behavior!getServiceViewList.action?cust_no=${request.cust_no}" width="100%" height="100%" frameborder="0" scrolling="no" target="pageViewId"></iframe>
   			</div>	
		</div>
            <!--  Sample  Request -->
		 <div class="dhtmlgoodies_aTab_new740" style="display:none">
		   <table  border="0" cellpadding="0" cellspacing="0" class="list_table" style="TABLE-LAYOUT:fixed;word-break:break-all">
			 <tr>
			   <th width="60">Seq No</th>
			   <th width="420">Sample  Name </th>
			   <th width="95">Request Date  </th>
			   <th width="95">Delivery Date </th>
			   <th width="100">Note</th>
			 </tr>
		   </table>
			<div style="height:100%">
             <iframe id="SampleRequestView" name="SampleRequestView" src="customer_web_behavior!getSampleRequestViewList.action?cust_no=${request.cust_no}" width="100%" height="100%" frameborder="0" scrolling="no" target="pageViewId"></iframe>
		   </div>
			<%--//<div class="grayr"><!-- {$pager} --></div>--%>
		</div>

	</div>
            </div>
			<div class="dhtmlgoodies_aTab"> 
			 
			   <div align="center" class="blue_price">Web Analysis Report </div>
			   <table width="80%" border="0" cellpadding="0" cellspacing="0" class="General_table">
                   <tr>
                     <td align="right">Period Type&nbsp;&nbsp;</td>
                     <td colspan="4" width="30%">
                       <select id="period_1">
						<option value="1">Day</option>
                         <option value="7">Week</option> <option value="30">Month</option>
						<option value="90">Quarter</option> <option value="365">Year</option>
                       </select>
					</td>
                     <td>&nbsp;</td>
                     <td>&nbsp;</td>
                     <td>&nbsp;</td>
					</tr>
                   <tr>
                     <td width="20%" align="right">Web Analysis&nbsp;&nbsp;</td>
                     <td width="5%"> <input type="radio" name="r_analyse_time_type" />   </td>
                     <td width="8%" >From</td>
                     <td width="25%">
					    <input id="analyseFrom" type="text" class="NFText ui-datepicker" style="width:80px" size="20" /> 
					</td>
                     <td width="8%" align="right">To</td>
                     <td>
					    <input id="analyseTo" type="text" class="NFText ui-datepicker" style="width:80px" size="20" /> 
                    </td>
                     <td width="8%">&nbsp;</td>
                     <td>&nbsp;</td>
                   </tr>
                   <tr>
                     <td>&nbsp;</td>
                     <td><input name="r_analyse_time_type" type="radio" checked="checked" /></td>
                     <td >For</td>
                     <td>
				<select id="last_period_type" style="width: 120px; visibility: visible; "> 
                        <option value="${WebBehaviorPeriodDTO.firstDate}">Since First Date</option>
                        <option value="${WebBehaviorPeriodDTO.lastYear}">Last Year</option> 
						<option value="${WebBehaviorPeriodDTO.thisYear}">This Year</option>
                        <option value="${WebBehaviorPeriodDTO.lastWeek}" selected="selected">Last Week</option> 
						<option value="${WebBehaviorPeriodDTO.thisWeek}">This Week</option>
                        <option value="${WebBehaviorPeriodDTO.lastMonth}">Last Month</option> 
						<option value="${WebBehaviorPeriodDTO.thisMonth}">This Month</option>
                        <option value="${WebBehaviorPeriodDTO.lastQuarter}">Last Quarter</option> 
						<option value="${WebBehaviorPeriodDTO.thisQuarter}">This Quarter</option>
                        <option value="${WebBehaviorPeriodDTO.lastSixMonths}">Last 6 Months</option> 
			   </select> </td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
                     <td>
						<input type="button" class="style_botton" value="Process" onclick="searchAnalyse()" />
						<input type="hidden" id="beginDate" /> <input type="hidden" id="endDate" />
						<input type="hidden" id="period" />
					</td>
                   </tr>
                 </table>
				<div id="report_img"><img src="images/visit_chart_tpl.png" /></div>

				<!-- <div align="center"><strong>Week</strong></div> --> 
			<table width="730" border="0" cellpadding="0" cellspacing="0" class="list_table">
				<tr>
				<th width="25%">Period [mm/dd]</th>
				<th width="10%" >Visits </th>
				<th width="10%">Referred  </th>
				<th width="15%">Searching</th>
				<th width="20%"># of Page Viewed  </th>
				<th width="20%">Avg View Duration </th>
				</tr>
			</table>
			<div style="height:100px; width:750px; overflow:auto">
            <table width="730" border="0" cellpadding="0" cellspacing="0" class="list_table" style="TABLE-LAYOUT:fixed;word-break:break-all">
				<tbody id="analyse_result">
		   </table> 
			<div style="margin-left:300px;" id="analyse_indicator"></div>
			</div>
	    </div>

		<div class="dhtmlgoodies_aTab">
<form action="customer_web_behavior!getClickStream.action" name="clickStreamForm" id="clickStreamForm" target="clickStream" >
	  <table width="571" border="0" cellpadding="0" cellspacing="0" class="General_table">
          <tr>
            <td width="35" align="right">From</td>
		<td> 
		<input id="clickFrom" name="clickFrom" type="text" class="NFText ui-datepicker" style="width:80px" size="20" /> 
		</td>
		<td width="20" align="right">To</td>
		<td>
		<input id="clickTo" name="clickTo" type="text" class="NFText ui-datepicker" style="width:80px" size="20" /> 
		</td>
		<td width="102"> 
		<input type="button" name="Submit5" class="style_botton" onclick="searchClickStream()" value="Process" /> 
		 </td>
		 <input type="hidden" id="cust_no" name="cust_no" value="${request.cust_no}" />
          </tr>
        </table>
</form>
            <table width="740" border="0" cellpadding="0" cellspacing="0" class="list_table" style="TABLE-LAYOUT:fixed;word-break:break-all">
              <tr>
                <th width="60">Date</th>
                <th width="70">Duration(s)</th>
                <th width="100">Page/URL</th>
                <th width="100">Page Referrer</th>
                <th width="80">Search Term</th>
                <th width="60">Country</th>
                <th width="50">Zip</th>
                <th width="50">ISP</th>
                <th width="50">Platform</th>
                <th width="50">Browser</th>
                <th width="70">Institution</th>
              </tr>
			</table>
          <div style="height:345px; width:810px; overflow:scroll">
<iframe id="clickStream" name="clickStream" src="customer_web_behavior!getClickStream.action?cust_no=${request.cust_no}" width="100%" height="100%" frameborder="0" scrolling="no" ></iframe>
            
	    </div></div>
<!--
			 <div style="display: block;" class="TabbedPanelsContent TabbedPanelsContentVisible">Email Campaign</div>
-->
	<input type="hidden" id="tab_stream_clicked" /> <input type="hidden" id="tab_analyse_clicked" />
	<input type="hidden" id="custNo" value="${custNo}" />  

<div align="center" style="margin-top:6px;"> 
<input type="button" value="Close" class="style_botton" onclick="javascript:parent.$('#webBehaviorDialog').dialog('close');" />
</div>

<script type="text/javascript">
//dispaly first statics tab and pane page view
getStat();
</script>
<script type="text/javascript">
initTabs('dhtmlgoodies_tabView1',Array('Statistics','Analysis','Clickstream'),0,827,440);
initTabs_new('740','dhtmlgoodies_tabView2',Array('Page view','Product View','Service View','Sample Request'),0,762,330);
$("#tabTabdhtmlgoodies_tabView2_3").one("click", function(){
	//getProductViewPane(1);
});
</script>
</body>
<script type="text/javascript" >
function searchClickStream(){
	document.clickStreamForm.submit();
}
</script>
</html>
