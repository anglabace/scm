<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Work Order Detail</title>
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
					Work Order Detail
				</div>
			</div>
            <div id="ShowDetailDialog" title=" Show Detail Dialog"></div>
			<div class="input_box">
				<div class="content_box">
					<form id="save_form" method="post" class="niceform" enctype="multipart/form-data">
                        <input type="hidden" name="serviceMethod" id="serviceMethod" value="getWorlOrderDetailReportDate"/>
                        <input type="hidden" name="actionPage" id="actionPage" value="work_order_detail"/>
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
												<td colspan="1" width="180px">
                                                    <s:select list="#{'today':'Today', 'week':'This Week', 'month' : 'This Month', 'quarter' : 'This Quarter', 'year':'This Year', '':'--------------------', 'yesterday':'Yesterday', 'lWeek':'Last Week', 'lMonth':'Last Month', 'lQuarter':'Last Quarter', 'lYear':'Last Year', 'custom':'Custom Date Range'}"
                                                              headerKey="" headerValue="All" id="reportDataDto.dataRange" name="reportDataDto.dataRange" value="reportDataDto.dataRange" cssStyle="width:100%"/>
												</td>
                                                    <td id="dateFromTD" style="display:none;width:190px">From <input name="reportDataDto.dataFrom" id="reportDataDto.dataFrom" type="text" class="ui-datepicker" style="width: 124px;" value="<s:date name="reportDataDto.dataFrom" format="yyyy-MM-dd"/>" readonly="readonly"/>
                                                    </td>
                                                    <td id="dateToTD" style="display:none;">To <input name="reportDataDto.dataTo" id="reportDataDto.dataTo" type="text" class="ui-datepicker" style="width: 124px;" value="<s:date name="reportDataDto.dataTo" format="yyyy-MM-dd"/>" readonly="readonly"/>
                                                    </td>
											</tr>
                                            <tr>
                                                <th width="204">Production Department</th>
												<td colspan="1" width="180px">
                                                    <s:select list="workCenters" listKey="id"
														listValue="name" name="reportDataDto.workCenterId" id="workCenterId"
														value="reportDataDto.workCenterId"  cssStyle="width:100%" onchange="getWorkGroupsByWorkCenter();"/>
												</td>
                                                <th width="204">Work Group</th>
												<td colspan="1" width="180px">
                                                    <s:select list="workGroups" listKey="id"
														listValue="name" name="reportDataDto.workGroupId" id="workGroupId"
														value="reportDataDto.workGroupId"  cssStyle="width:100%"/>
												</td>
                                            </tr>
                                            <tr>
                                                <th width="204">Order Source</th>
												<td colspan="1" width="180px">
                                                    <s:select list="#{'INTERNAL ORDER':'Internal Order', 'REMANUFACTURE':'Remanufacture', 'REPAIR':'Repair'}" headerKey="SALES ORDER"
														 headerValue="Sales Order" name="reportDataDto.source" id="source"
														value="reportDataDto.source"  cssStyle="width:100%"/>
												</td>
                                                <th width="204"></th>
                                                <td>
                                                    <input type = "checkbox" id="stepFlag" name="reportDataDto.stepFlag" value="N"  onchange="stepChange(this);"/>Includes step items
												</td>
                                            </tr>
											<tr>
												<th>Status</th>
												<td colspan="1"><s:checkbox name="reportDataDto.status" id="reportDataDto.status.all" fieldValue=""/>All
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
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.status" fieldValue="orderNo:Work Order #"/>Work Order #</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.subtotal" fieldValue="workCenterName:Production Department"/>Production Department</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.freight" fieldValue="workGroupName:Work Group"/>Work Group</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.tax" fieldValue="itemType:Product/Service"/>Product/Service</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.total" fieldValue="status:Status"/>Status</td>
											</tr>
                                            <tr>
                                                <th width="210"></th>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.status" fieldValue="operationName:WO Operation"/>WO Operation</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.status" fieldValue="scheduleStart:Scheduled Start Date"/>Scheduled Start Date</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.subtotal" fieldValue="scheduleEnd:Scheduled Complete Date"/>Scheduled Complete Date</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.modCD" fieldValue="modComDate:Mod Sch Complete Date"/>Mod Sch Complete Date</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.scmMR" fieldValue="scdReason:SCD Modified Reason"/>SCD Modified Reason</td>

											</tr>
                                            <tr>
                                                <th width="210"></th>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.scdMD" fieldValue="scdDate:SCD Modified Date"/>SCD Modified Date</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.actuStaDate" fieldValue="actualStart:Actual Start Date"/>Actual Start Date</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.actualComDate" fieldValue="actualEnd:Actual Complete Date"/>Actual Complete Date</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.dayDelay" fieldValue="delayDays:Days Delayed"/>Days Delayed</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.calDate" fieldValue="cancelledDate:Cancellation Date"/>Cancellation Date</td>

											</tr>
                                            <tr>
                                                <th width="210"></th>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.calReas" fieldValue="cancelledReason:Cancellation Reason"/>Cancellation Reason</td>
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

    function validData(){
        var flag = 0;
        $("input[name='reportDataDto\\.column']").each(function(){
            if($(this).attr("checked")){
                flag++;
            }
        });
        if(flag < 1){
            alert("Please select Output Parameters !");
            return false;
        }
        //验证fromDate ToDate

        return true;
    }

    $(function() {

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

   $("#reportDataDto\\.status\\.all").click(function(){
        if($(this).attr("checked")){
        $("input[name='reportDataDto\\.status']").each(
                function(){
                    $(this).attr("checked", true);
                }
                )
    }else{
         $("input[name='reportDataDto\\.status']").each(
                function(){
                    $(this).attr("checked", false);
                }
                )
        }
    });

    $("input[name='reportDataDto\\.status']").each(function(){
        $(this).click(
                function(){
            if($("#reportDataDto\\.status\\.all").attr("checked") && !$(this).attr("checked")){
                $("#reportDataDto\\.status\\.all").attr("checked", false);
            }
            })}
            );
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

    $("input[name='reportDataDto\\.column']").each(function(){
        $(this).click(
                function(){
            if($("#reportDataDto\\.column\\.all").attr("checked") && !$(this).attr("checked")){
                $("#reportDataDto\\.column\\.all").attr("checked", false);
            }
            })}
            );

 });

</script>
	</body>
</html>