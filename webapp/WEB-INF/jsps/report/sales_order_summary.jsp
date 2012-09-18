<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Sales Order Summary</title>
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
					Sales Order Summary
				</div>
			</div>
            <div id="ShowDetailDialog" title=" Show Detail Dialog"></div>
			<div class="input_box">
				<div class="content_box">
					<form id="save_form" method="post" class="niceform"
						enctype="multipart/form-data">
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
												<th width="210">Date Range</th>
												<td colspan="1" width="160px">
                                                    <s:select list="#{'today':'Today', 'week':'This Week', 'month' : 'This Month', 'quarter' : 'This Quarter', 'year':'This Year', '':'--------------------', 'yesterday':'Yesterday', 'lWeek':'Last Week', 'lMonth':'Last Month', 'lQuarter':'Last Quarter', 'lYear':'Last Year', 'custom':'Custom Date Range'}"
                                                              headerKey="" headerValue="All" id="reportDataDto.dataRange" name="reportDataDto.dataRange" value="reportDataDto.dataRange" cssStyle="width:80%"/>
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
														value="reportDataDto.periodType" cssStyle="width:80%"/>
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
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.status.open" fieldValue="NW"/>Open</td>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.status.open" fieldValue="RV"/>Order on Review</td>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.status.cc" fieldValue="CC"/>Customer Confirmed</td>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.status.vc" fieldValue="VC"/>Vendor Confirmed</td>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.status.fs" fieldValue="SH"/>Fully Shipped</td>
                                                <%--<td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.status.cancelled" fieldValue="CN"/>Cancelled</td>--%>
                                                <%--<td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.status.invoiced" fieldValue="Closed"/>Invoiced</td>--%>

											</tr>
                                            <tr>
                                                <th width="204"></th>
                                                <%--<td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.status.paid" fieldValue="PC"/>Paid</td>--%>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.status.cancelled" fieldValue="CN"/>Cancelled</td>
                                            </tr>
											<tr>
												<th width="210">Currency</th>
												<td width="160px"> <s:select list="#{'CNY':'CNY', 'EUR':'EUR', 'JPY':'JPY', 'GBP':'GBP'}" headerKey="USD"
														headerValue="USD" name="reportDataDto.currency"
														value="reportDataDto.TaxingScheme" cssStyle="width:160px"/></td>
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
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.status" fieldValue="status:Status"/>Status</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.subtotal" fieldValue="subTotal:Subtotal"/>Subtotal</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.freight" fieldValue="shipAmt:Freight"/>Freight</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.tax" fieldValue="tax:Tax"/>Tax</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.total" fieldValue="amount:Total"/>Total</td>
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
        $("#report").click(
            function(){
                if($("#save_form").valid() === false){
                    return false;
                }
                if(!validData()) return false;
                $("#operation_list_frame").height("830px");
                var formStr = $("#save_form").serialize();
                $('#report').attr("disabled", true);
                document.forms[0].action = "report!getSalesSummaryReportDate.action";
                document.forms[0].target = "operation_list_frame";
                document.forms[0].submit();
                $('#report').attr("disabled", false);
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
                document.forms[0].action = "report!getCommonMethodPicAnalysis.action?serviceMethod=getSalesSummaryReportDate&statisticsColumn=amount";
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
                document.forms[0].action = "report!getSalesSummaryExcelExport.action";
                document.forms[0].target = "operation_list_frame";
                document.forms[0].submit();
                $('#report').attr("disabled", false);
       });

        $("#pdfExport").click(function() {
            if ($("#save_form").valid() === false) {
                return false;
            }
            if (!validData()) return false;
            var formStr = $("#save_form").serialize();
            $('#report').attr("disabled", true);
            document.forms[0].action = "report!getSalesSummaryPDFExport.action";
            document.forms[0].target = "operation_list_frame";
            document.forms[0].submit();
            $('#report').attr("disabled", false);
        });

        $("#reportDataDto\\.status\\.all").click(function() {
            if ($(this).attr("checked")) {
                $("input[name='reportDataDto\\.status']").each(function() {
                    $(this).attr("checked", true);
                }
                        )
            } else {
                $("input[name='reportDataDto\\.status']").each(function() {
                    $(this).attr("checked", false);
                }
                        )
            }
        });

        $("#reportDataDto\\.column\\.all").click(function() {
            if ($(this).attr("checked")) {
                $("input[name='reportDataDto\\.column']").each(function() {
                    $(this).attr("checked", true);
                })
            } else {
                $("input[name='reportDataDto\\.column']").each(function() {
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
            "reportDataDto.column": { required:true}
        },
        messages: {
            "reportDataDto.status": { required : "Please select Status !" },
            "reportDataDto.column": { required : "Please select Output Parameters !" }
        },
        errorPlacement: function(error, element) {
        }
    });


    $("input[name='reportDataDto\\.status']").each(function(){
        $(this).click( function(){
            if($("#reportDataDto\\.status\\.all").attr("checked") && !$(this).attr("checked")){
                $("#reportDataDto\\.status\\.all").attr("checked", false);
            }
            })}
            );
</script>
	</body>
</html>