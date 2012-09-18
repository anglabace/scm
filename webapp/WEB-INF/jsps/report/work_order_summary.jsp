<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Work Order Summary</title>
		<link href="${global_css_url}scm.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}table.css" rel="stylesheet" type="text/css" />
		<link href="${global_css_url}tab-view.css" rel="stylesheet" type="text/css" />
		<link href="${global_js_url}jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript"> var baseUrl="${global_url}"; </script>
		<script type="text/javascript" language="javascript" src="${global_js_url}jquery/jquery.js"></script>
		<script type="text/javascript" language="javascript" src="${global_js_url}util/util.js"></script>
		<script src="${global_js_url}jquery/jquery.validate.js?v=1" type="text/javascript"></script>
		<script type="text/javascript" src="${global_js_url}tab-view.js"></script>
		<script src="${global_js_url}jquery/external/bgiframe/jquery.bgiframe.min.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/jquery.form.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.core.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.draggable.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.resizable.js" type="text/javascript"></script>
		<script src="${global_js_url}jquery/ui/ui.dialog.js" type="text/javascript"></script>
		<script type="text/javascript" src="${global_js_url}show_tag.js"></script>
		<script src="${global_js_url}jquery/ui/ui.datepicker.js" type="text/javascript"></script>
        <script src="${global_js_url}scm/gs.util.js" type="text/javascript"></script>
        <script type="text/javascript" src="${global_js_url}report/reportCommon.js"></script>
		<style type="text/css">
<!--
.content .scm #dhtmlgoodies_tabView1 .dhtmlgoodies_aTab .General_table fieldset
	{
	margin: 4px;
}

.disp {
	display: none;
	margin-left: 40px;
}
-->
</style>

	</head>
	<body class="content">
		<div class="scm">
			<div class="title_content">
				<div class="title">
					Work Order Summary
				</div>
			</div>
            <div id="ShowDetailDialog" title=" Show Detail Dialog"></div>
			<div class="input_box">
				<div class="content_box">
					<form id="save_form" method="post" class="niceform" enctype="multipart/form-data">
                        <input type="hidden" name="serviceMethod" id="serviceMethod" value="getWorlOrderReportDate"/>
                        <input type="hidden" name="actionPage" id="actionPage" value="work_order_summary"/>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									<div class="invoice_title">
										<span style="cursor: pointer"
											onclick="toggle_showmore('Filter_ParametersItem', 'Filter_Parameters');">
                                            <img src="${global_image_url}ad.gif" width="11" height="11"
												id="Filter_ParametersItem" /> &nbsp; Filter Parameters</span>
									</div>
									<div id="Filter_Parameters" class="disp" style="display: block;">
										<table border="0" cellpadding="0" cellspacing="0" class="General_table">
											<tr>
												<th width="210">Work Order Date</th>
												<td colspan="1" width="160px">
                                                    <s:select list="#{'today':'Today', 'week':'This Week', 'month' : 'This Month', 'quarter' : 'This Quarter', 'year':'This Year', '':'--------------------', 'yesterday':'Yesterday', 'lWeek':'Last Week', 'lMonth':'Last Month', 'lQuarter':'Last Quarter', 'lYear':'Last Year', 'custom':'Custom Date Range'}"
                                                              headerKey="" headerValue="All" id="reportDataDto.dataRange" name="reportDataDto.dataRange" value="reportDataDto.dataRange" cssStyle="width:100%"/>
												</td>
                                                    <td id="dateFromTD" style="display:none;width:190px">From <input name="reportDataDto.dataFrom" id="reportDataDto.dataFrom" type="text" class="ui-datepicker" style="width: 124px;" value="<s:date name="reportDataDto.dataFrom" format="yyyy-MM-dd"/>" readonly="readonly"/>
                                                    </td>
                                                    <td id="dateToTD" style="display:none;">To <input name="reportDataDto.dataTo" id="reportDataDto.dataTo" type="text" class="ui-datepicker" style="width: 124px;" value="<s:date name="reportDataDto.dataTo" format="yyyy-MM-dd"/>" readonly="readonly"/>
                                                    </td>
											</tr>
                                            <tr>
                                                <th width="204">Period Type</th>
												<td colspan="1" width="160px">
                                                    <s:select list="#{'week':'Weeks', 'month' : 'Months', 'quarter' : 'Quarters', 'year':'Years'}" headerKey="day"
														headerValue="Days" name="reportDataDto.periodType" id="reportDataDto.periodType"
														value="reportDataDto.periodType" cssStyle="width:100%"/>
												</td>
                                            </tr>
                                            <tr>
                                                <th width="204">Production Department</th>
												<td colspan="1" width="160px">
                                                    <s:select list="workCenters" listKey="id"
														listValue="name" name="reportDataDto.workCenterId" id="workCenterId"
														value="reportDataDto.workCenterId" cssStyle="width:100%" onchange="getWorkGroupsByWorkCenter();"/>
												</td>
                                                <th width="204">Work Group</th>
												<td colspan="1" width="180px">
                                                    <s:select list="workGroups" listKey="id"
														listValue="name" name="reportDataDto.workGroupId" id="workGroupId"
														value="reportDataDto.workGroupId"  cssStyle="width:100%"/>
												</td>
                                            <tr>
                                                <th width="204">Product/Service Type</th>
												<td colspan="1" width="160px">
                                                    <s:select list="productServiceTypes" listKey="id"
														listValue="name" name="reportDataDto.psType" id="psType"
														value="reportDataDto.psType" cssStyle="width:100%"/>
												</td>
                                                <th width="204"></th>
                                                <td>
                                                    <input type = "checkbox" id="stepFlag" name="reportDataDto.stepFlag" value="N" onchange="stepChange(this);"/>Includes step items
												</td>
                                            </tr>
                                            <tr>
                                                <th width="204">Delayed Days</th>
												<td colspan="1" width="160px">
                                                    <input class="NFText" type="text" name="reportDataDto.delayFrom" id="delayFrom" style="width:15px;" value="${reportDataDto.delayFrom}" onkeypress="numberValid();"/>-<input type="text" name="reportDataDto.delayTo" id="delayTo" style="width:15px;" class="NFText" value="${reportDataDto.delayTo}" onkeypress="numberValid();"/>
                                                    <s:select list="#{'Weeks':'week', 'Days':'day'}" headerKey="month" headerValue="Months" name="reportDataDto.delayType" id="delayType"
														value="reportDataDto.delayType" cssStyle="width:95px"/>
												</td>
                                                <th width="204">Order Source</th>
												<td colspan="1" width="160px">
                                                    <s:select list="#{'INTERNAL ORDER':'Internal Order', 'REMANUFACTURE':'Remanufacture', 'REPAIR':'Repair'}" headerKey="SALES ORDER"
														 headerValue="Sales Order" name="reportDataDto.source" id="source"
														value="reportDataDto.source" cssStyle="width:100%"/>
												</td>
                                            </tr>
                                            <tr>
                                                <th width="204">Target Date</th>
                                                <td colspan="3" width="160px">
                                                    <input name="reportDataDto.targetDateFrom" id="reportDataDto.targetDateFrom" type="text" class="ui-datepicker" style="width: 124px;" value="<s:date name="reportDataDto.targetDateFrom" format="yyyy-MM-dd"/>" readonly="readonly"/>-
                                                    <input name="reportDataDto.targetDateTo" id="reportDataDto.targetDateTo" type="text" class="ui-datepicker" style="width: 124px;" value="<s:date name="reportDataDto.targetDateTo" format="yyyy-MM-dd"/>" readonly="readonly"/>
                                                </td>
                                            </tr>
											<tr>
												<th>Status</th>
												<td colspan="1"><s:checkbox name="reportDataDto.status" id="reportDataDto.status.all" fieldValue=""/>All
                                                <input type="hidden" name="reportDataDto.column" value="fromDate:From Date"/>
                                                <input type="hidden" name="reportDataDto.column" value="toDate:To Date"/>
                                                </td>
											</tr>
                                            <tr>
                                                <th width="204"></th>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.orderStatus.new" fieldValue="New"/>New</td>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.orderStatus.ip" fieldValue="In Production"/>In Production</td>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.orderStatus.pc" fieldValue="Production Completed"/>Production Completed</td>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.orderStatus.closed" fieldValue="Closed"/>Closed</td>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.orderStatus.pt" fieldValue="Closed w/PT"/>Production Terminated</td>

											</tr>
                                            <tr id="orderShow1">
                                                <th width="204"></th>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.orderStatus.ct" fieldValue="Closed w/CR"/>Conditional Released</td>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.orderStatus.prqc" fieldValue="Product QC Failed"/>Product QC Failed</td>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.orderStatus.doqc" fieldValue="Document QC Failed"/>Document QC Failed</td>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.orderStatus.cancelled" fieldValue="Canceled"/>Cancelled</td>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.orderStatus.delayed" fieldValue="delayed"/>Delayed</td>
											</tr>
										</table>
									</div>
									<div class="invoice_title">
										<span style="cursor: pointer"
											onclick="toggle_showmore('OutPut_ParamItem', 'OutPut_Param');">
                                            <img src="${global_image_url}ad.gif" width="11" height="11"
												id="OutPut_ParamItem" /> &nbsp;Output Parameters</span>
									</div>
									<div id="OutPut_Param" class="disp" style="display:block;">
										<table border="0" cellpadding="0" cellspacing="0" class="General_table">
											<tr>
                                                <th width="210"></th>
												<td><s:checkbox name="reportDataDto.column" id="reportDataDto.column.all" fieldValue="" value=""/>All</td>
											</tr>
											<tr>
                                                <th width="210"></th>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.pd" fieldValue="workCenterName:Production Department"/>Production Department</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.status" fieldValue="status:Status"/>Status</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.workgroup" fieldValue="workGroupName:Work Group"/>Work Group</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.numWO" fieldValue="orderNum:Num of Work Orders"/>Num of Work Orders</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.numBW" fieldValue="delieryNum:Num of Deliery WOs"/>Num of Deliery WOs</td>

											</tr>
                                            <tr>
                                                <th width="210"></th>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.cost" fieldValue="delayedNum:Num of Delayed WOs"/>Num of Delayed WOs</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.cost" fieldValue="total:Total Cost"/>Total Cost</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.sr" fieldValue="successRatio:Success Ratio"/>Success Ratio</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.dr" fieldValue="delayedRatio:Delayed Ratio"/>Delayed Ratio</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.fr" fieldValue="failedRatio:Failed Ratio"/>Failed Ratio</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
        <div class="button_box">
			<input type="submit" name="report" id="report" value="Generate Report" class="search_input2"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="submit" name="chart" id="chart" value="Generate Chart" class="search_input2"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <img src="${global_image_url}excel.jpg" style="cursor: pointer" id="excelExport" width="16" height="16" align="absmiddle"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <img src="${global_image_url}pdf.gif" style="cursor: pointer" id="pdfExport" width="16" height="16" align="absmiddle"/>
		</div>

		<div id="dhtmlgoodies_tabView1">
				<iframe width="100%" height="0px" id="operation_list_frame"
					name="operation_list_frame" scrolling="no" frameborder="0"
					src=""></iframe>
		</div>
        <div id="new_customer_dlg" title=" Customer List "></div>
<script type="text/javascript">
	var global_url = "${global_url}" ;
	var isSaved = false;
//	window.onbeforeunload = function() {
//		if(isSaved === false){
//			return 'Do you want to leave without saving data?';
//		}
//	};

    $("#save_form").validate({
        errorClass:"validate_error",
        highlight: function(element, errorClass) {
            $(element).addClass(errorClass);
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).removeClass(errorClass);
        },
        invalidHandler: function(form, validator) {
            $.each(validator.invalid, function(key, value) {
                alert(value);
                $(this).find("name=[" + key + "]").focus();
                return false;
            });
        },
        rules: {
            "reportDataDto.status": { required:true},
            "country": { required:true},
            "reportDataDto.column": { required:true}
        },
        messages: {
            "reportDataDto.status": { required : "Please select Status !" },
            "country": { required : "Please select Country !" },
            "reportDataDto.column": { required : "Please select Output Parameters !" }
        },
        errorPlacement: function(error, element) {
        }
    });

    function numberValid() {
            if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false;
    }

    function statusShow(){
            if ($("#delayFrom").val() != "" && $("#delayTo").val() != "") {
                $("input[name='reportDataDto\\.status']").each(function() {
                    if ($(this).attr("id").indexOf("delayed") < 0) $(this).attr("disabled", true);
                });
                $("#reportDataDto\\.orderStatus\\.delayed").attr("disabled", false);
                $("#reportDataDto\\.status\\.all").attr("disabled", false);
            }else{
                $("input[name='reportDataDto\\.status']").each(function() {
                    if ($(this).attr("id").indexOf("delayed") < 0) $(this).attr("disabled", false);
                });
                $("#reportDataDto\\.orderStatus\\.delayed").attr("disabled", true);
            }
        }

    $(function() {
        $('#ShowDetailDialog').dialog({
                 	autoOpen: false,
					height: 700,
					width: 1000,
					modal: true,
					bgiframe: true,
                    resize:'auto',
                   buttons: {
                        "Close": function() {
                     $(this).dialog('close');
            }
                   }
               });
           $("#ShowDetailTrigger").click (function() {
               var ids=$("#order_orderNo_text").val();
               if(ids!=null && ids !=""){
                 $('#ShowDetailDialog').dialog("option", "open", function() {
             		 var htmlStr = '<iframe src="${global_url}/order/order!edit.action?orderNo='+ids+'&operation_method=view" id="selectUserFrame"  height="100%" width="1000" scrolling="auto" style="border:0px" frameborder="0"></iframe>';
             		 $('#ShowDetailDialog').html(htmlStr);
            	});
            	 $('#ShowDetailDialog').dialog('open');
                   return true;
               }else{
                   alert("Please select an sales order no!");
                   return false;
               }
           });

    showDateRange();
    $("#reportDataDto\\.dataRange").change(function showDateRange(){
        if($(this).val() == "custom"){
            $("#dateFromTD").css("display" , "");
            $("#dateToTD").css("display", "");
        }else{
            $("#dateFromTD").css("display" , "none");
            $("#dateToTD").css("display", "none");
        }
    });

    $("#chart").click(
            function(){
                if($("#save_form").valid() === false){
                    return false;
                }
                if(!validData()) return false;
                $("#operation_list_frame").height("830px");
                var formStr = $("#save_form").serialize();
                $('#report').attr("disabled", true);
                document.forms[0].action = "report!getCommonMethodPicAnalysis.action?serviceMethod=getWorlOrderReportDate&statisticsColumn=total";
                document.forms[0].target = "operation_list_frame";
                document.forms[0].submit();
                $('#report').attr("disabled", false);
       });

    $("#report").click(
            function(){
                if($("#save_form").valid() === false){
                    return false;
                }
//                alert($("#reportDataDto\\.type").val());
//                return;
                if(!validData()) return false;
                $("#operation_list_frame").height("830px");
                var formStr = $("#save_form").serialize();
                $('#report').attr("disabled", true);
                document.forms[0].action = "report!getCommonMethodReportDate.action";
                document.forms[0].target = "operation_list_frame";
                document.forms[0].submit();
                $('#report').attr("disabled", false);
       });

    $("#excelExport").click(
            function(){
                if($("#save_form").valid() === false){
                    return false;
                }
                if(!validData()) return false;
                var formStr = $("#save_form").serialize();
                $('#report').attr("disabled", true);
                document.forms[0].action = "report!getCommonMethodExcelExport.action";
                document.forms[0].target = "operation_list_frame";
                document.forms[0].submit();
                $('#report').attr("disabled", false);
       });

    $("#pdfExport").click(
            function(){
                if($("#save_form").valid() === false){
                    return false;
                }
                if(!validData()) return false;
                var formStr = $("#save_form").serialize();
                $('#report').attr("disabled", true);
                document.forms[0].action = "report!getCommonMethodPDFExport.action";
                document.forms[0].target = "operation_list_frame";
                document.forms[0].submit();
                $('#report').attr("disabled", false);
       });

        $("#reportDataDto\\.status\\.all").click(function() {
            if ($(this).attr("checked")) {
                if($("#delayFrom").val() != "" && $("#delayTo").val() != ""){
                    $("#reportDataDto\\.status\\.delayed").attr("disabled", false);
                    $("input[name='reportDataDto\\.status']").each(function() {
//                        if($(this).attr("id").indexOf("delayed") < 0) $(this).attr("disabled", true);
//                        $("#reportDataDto\\.status\\.delayed").attr("disabled", false);
                        $(this).attr("checked", true)
                    });
//                    $("#reportDataDto\\.orderStatus\\.delayed").attr("disabled", false);
//                    $("#reportDataDto\\.status\\.all").attr("disabled", false);
//                    $("#reportDataDto\\.orderStatus\\.delayed").attr("checked", true);
                } else {
                    $("input[name='reportDataDto\\.status']").each(function() {
                        $(this).attr("checked", true);
//                        if($(this).attr("id").indexOf("delayed") < 0) $(this).attr("disabled", false);
                    });
                    /*$("#reportDataDto\\.orderStatus\\.delayed").attr("disabled", true);
                    $("#reportDataDto\\.orderStatus\\.delayed").attr("checked", false);*/
                }
            } else {
                $("input[name='reportDataDto\\.status']").each(function() {
                    $(this).attr("checked", false);
                })
            }
        });

        $("input[name='reportDataDto\\.status']").each(function() {
            $(this).click(function() {
                if ($("#reportDataDto\\.status\\.all").attr("checked") && !$(this).attr("checked")) {
                    $("#reportDataDto\\.status\\.all").attr("checked", false);
                }
            })
        });

    $("#reportDataDto\\.column\\.all").click(function(){
        if($(this).attr("checked")){
            $("input[name='reportDataDto\\.column']").each(function(){
                $(this).attr("checked", true);
            })
        }else{
            $("input[name='reportDataDto\\.column']").each(function(){
                $(this).attr("checked", false);
            })
        }
    });

    $("input[name='reportDataDto\\.column']").each(function() {
        $(this).click(function() {
            if ($("#reportDataDto\\.column\\.all").attr("checked") && !$(this).attr("checked")) {
                $("#reportDataDto\\.column\\.all").attr("checked", false);
            }
        })
    });
  });

function delayStatus(){
    if($("#delayFrom").val() != "" && $("#delayTo").val() != ""){
        $("#reportDataDto\\.orderStatus\\.delayed").attr("disabled", false);
    }else{
        $("#reportDataDto\\.orderStatus\\.delayed").attr("checked", false);
        $("#reportDataDto\\.orderStatus\\.delayed").attr("disabled", true);
    }
}
</script>
	</body>
</html>