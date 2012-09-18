<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/taglib.jsp"%>
	<base href="${global_url}" />
	<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
	<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
	<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
	<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
	<link href="${global_css_url}SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
	<link href="${global_css_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css" />
	<link href="${global_css_url}greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css"  />
	<script language="javascript" type="text/javascript" src="${global_js_url}ajax.js"></script>
	<script language="javascript" type="text/javascript" src="${global_js_url}tab-view.js"></script>
	<script language="javascript" type="text/javascript" src="${global_js_url}TabbedPanels.js"></script>
	<script language="javascript" type="text/javascript" src="js/newwindow.js"></script>
	<script language="javascript" type="text/javascript"> var GB_ROOT_DIR = "./greybox/";</script>
	<script language="javascript" type="text/javascript" src="${global_js_url}greybox/AJS.js"></script>
	<script language="javascript" type="text/javascript" src="${global_js_url}greybox/AJS_fx.js"></script>
	<script language="javascript" type="text/javascript" src="${global_js_url}greybox/gb_scripts.js"></script>
	<script language="javascript" type="text/javascript" src="${global_js_url}jquery/jquery.js"></script>
	<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
	<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
	<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
	<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
	<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
	<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript">
$(function() {
		    $('.ui-datepicker').each(function(){
					$(this).datepicker(
						{
							dateFormat: 'yy-mm-dd',
							changeMonth: true,
							changeYear: true,
							beforeShow: function () {
				                setTimeout(
				                    function () {
				                        $('#ui-datepicker-div').css("top", '50px');
				                        $('#ui-datepicker-div').css("left", '350px');
				                    }, 100
				                );
				            }
						});
				}); 
				$('#fromDate').datepicker();
				$('#toDate').datepicker();
});
function selectBranch(){
	var type=document.getElementById('type').value;
	document.forms[0].action = "shipments!shipmentListSearch.action?type='"+type+"'";
  	document.forms[0].submit();
}
function check(){
    var type=window.parent.window.frames["searchBody"].document.getElementById("type").value;
    document.getElementById("type").value=type;
    var clerk=window.parent.window.frames["searchBody"].document.getElementById("select").value;
    //alert(clerk)
    document.getElementById("clerk").value=clerk;
    //alert(type);
	var fromDate = document.getElementById('fromDate').value;
	var toDate = document.getElementById('toDate').value;
	var dt = new Date();
	var now = dt.getFullYear()+"-"; //读英文就行了
	var month = (dt.getMonth()+1)+"";
	if(month.length<2){
		month = "0"+month;
	}
	now = now + month+"-";//取月的时候取的是当前月-1如果想取当前月+1就可以了
	var day = dt.getDate()+"";
	if(day.length<2){
		day = "0"+day;
	}
	now = now + day;
	y=now;//��ȡ��ǰ�꣺2010
	var fromDates = fromDate.substring(0,4);//��ȡʱ�䣬��ȡ��2010
	var toDates = toDate.substring(0,4);

	if(fromDate == null || fromDate == ""){
		alert("Search Condition can't is null .");
		return false;
	}else if(toDate == null || toDate == ""){
		alert("Search Condition can't is null .");
		return false;
	}else if(fromDate > toDate){
		alert("To date must be latter then From date.");
		return false;
	}else if(fromDate>y){
		alert("From Date not greater than the current years .");
		return false;
	}else if(toDate>y){
		alert("To Date not greater than the current years .");
		return false;
	}else{
		//document.printForm.action = "work_order!viewprintReceivingReport.action?fromDate='"+fromDate+"'&toDate='"+toDate+"'";
		document.printForm.action = "work_order!print.action?fromDate="+fromDate+"&toDate="+toDate+"&type="+type+" ";
		//document.printForm.action = "work_order!viewprintReceivingReport.action?fromDate='"+fromDate+"'&toDate='"+toDate+"'&type='"+type+"' ";
		document.printForm.submit();
	}
}
</script>
</head>
<body>
<form name="printForm" action="">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>

							</tr>
							<tr>
								<td style="padding-left: 130px;">
									<br />
									<table border="0" cellspacing="0" cellpadding="0"
										class="General_table" width="100%">
										<tr>
											<td>
												<table border="0" cellpadding="0" cellspacing="0">
													<tr>
														<th width="150">
															Receiving Date From
														</th>
														<td width="116">
															<input name="fromDate" id ="fromDate" type="text" class="ui-datepicker" value="<s:date name="fromDate" format="yyyy-MM-dd"/>" size="20" style="width: 124px;"  readonly="readonly"/>
														</td>
													</tr>
												
													<tr>
														<th width="150">
															To
														</th>
														<td width="116">
															<input name="toDate" id ="toDate"  type="text" class="ui-datepicker" value="<s:date name="toDate" format="yyyy-MM-dd"/>" size="20" style="width: 124px;"  readonly="readonly"/>
														</td>
													</tr>
												
													<tr>
														<td height="30" colspan="2">
															<div align="center">
																<br />
																<br />
																<input id="sub1" name="Submit1" type="button"
																	class="style_botton" value="Create" onClick="check();"/>
																<input id="sub2" type="button" name="Submit2"
																	value="Cancel" class="style_botton"
																	onclick="parent.$('#vendor_search_dlg3').dialog('close');" />
																<br />
																<br />
																<br />
																<br />
																<br />
															</div>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									<br />
								</td>
							</tr>
						</table>
						
			<input type="hidden" name="type" id="type" value=""/>
			<input type="hidden" name="clerk" id="clerk" value=""/>
		</form>
		<div id="viewprint" style="visible: hidden;"></div>
 </body>
 </html>