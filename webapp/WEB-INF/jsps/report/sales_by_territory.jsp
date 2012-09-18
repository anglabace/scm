<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Sales By Territory</title>
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
					Sales By Territory
				</div>
			</div>
            <div id="ShowDetailDialog" title=" Show Detail Dialog"></div>
			<div class="input_box">
				<div class="content_box">
					<form id="save_form" method="post" class="niceform" enctype="multipart/form-data">
                        <input type="hidden" name="serviceMethod" id="serviceMethod" value="getSalesOrderByReportDate"/>
                        <input type="hidden" name="actionPage" id="actionPage" value="sales_by_territory"/>
                        <input type="hidden" name="reportDataDto.reportType" id="reportType" value="territory"/>
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
                                                <td>
                                                    <input type="radio" name="reportType" id="quotationType" value="quotation" checked="true" onclick="showType()"/>Quotation
                                                </td>
                                                <td>
                                                    <input type="radio" name="reportType" id="orderType" value="order" onclick="showType()"/>Order
                                                    <input type="hidden" name="reportDataDto.type"  id="reportDataDto.type" value="quotation"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th width="204">Territory</th>
												<td colspan="1" width="180px">
                                                    <s:select list="salesTerritories" listKey="id"
														listValue="name" name="reportDataDto.territory" id="reportDataDto.territory"
														value="reportDataDto.territory" cssStyle="width:100%"/>
												</td>
                                                <th width="204">Customer</th>
												<td colspan="1" width="160px">
													<input name="reportDataDto.customer" readonly="readonly" value="${reportDataDto.customer}" id="reportDataDto.customer" type="text" class="NFText"/>
													<img src="${global_image_url}search.gif" style="cursor: pointer" width="16" height="16" id="sel_product_btn"  onclick="clickCustomer()"/>
                                                </td>
                                            </tr>
											<tr>
												<th>Status</th>
												<td colspan="1"><s:checkbox name="reportDataDto.status" id="reportDataDto.status.all" fieldValue=""/>All
                                                <input type="hidden" name="reportDataDto.column" value="fromDate:From Date"/>
                                                <input type="hidden" name="reportDataDto.column" value="toDate:To Date"/>
                                                </td>
											</tr>
                                            <tr id="quotationShow" style="display:''">
                                                <th width="204"></th>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.quotationStatus.new" fieldValue="NW"/>New</td>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.quotationStatus.open" fieldValue="OP"/>Open</td>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.quotationStatus.cc" fieldValue="CW"/>Closed w/ Order</td>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.quotationStatus.vc" fieldValue="CO"/>Closed w/o Order</td>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.quotationStatus.fs" fieldValue="SH"/>Fully Shipped</td>
											</tr>
                                            <tr id="quotationShow1" style="display:''">
                                                <th width="204"></th>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.quotationStatus.invoiced" fieldValue="CN"/>Cancelled</td>
                                            </tr>
                                            <tr id="orderShow1" style="display:none">
                                                <th width="204"></th>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.orderStatus.open" fieldValue="NW"/>Open</td>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.orderStatus.rv" fieldValue="RV"/>Order on Review</td>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.orderStatus.cc" fieldValue="CC"/>Customer Confirmed</td>
                                                <%--<td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.orderStatus.vc" fieldValue="VC"/>Vendor Confirmed</td>--%>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.orderStatus.fs" fieldValue="SH"/>Fully Shipped</td>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.orderStatus.invoiced" fieldValue="Closed"/>Invoiced</td>

											</tr>
                                            <tr id="orderShow2" style="display:none">
                                                <th width="204"></th>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.orderStatus.paid" fieldValue="PC"/>Paid</td>
                                                <td colspan="1" width="160px"><s:checkbox name="reportDataDto.status" id="reportDataDto.orderStatus.cancelled" fieldValue="CN"/>Cancelled</td>
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

   $(function(){
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
                document.forms[0].action = "report!getCommonMethodPicAnalysis.action?serviceMethod=getSalesOrderByReportDate&statisticsColumn=amount";
                document.forms[0].target = "operation_list_frame";
                document.forms[0].submit();
                $('#report').attr("disabled", false);
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
            if($("#reportDataDto\\.type").val()=="quotation"){
            $("input[id^='reportDataDto\\.quotationStatus']").each(
                function(){
                    $(this).attr("checked", true);
                }
                )
            }else{
                $("input[id^='reportDataDto\\.orderStatus']").each(
                function(){
                    $(this).attr("checked", true);
                }
                )
            }
    }else{
         $("input[name='reportDataDto\\.status']").each(
                function(){
                    $(this).attr("checked", false);
                }
                )
        }
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

    $("input[name='reportDataDto\\.column']").each(function(){
        $(this).click(
                function(){
            if($("#reportDataDto\\.column\\.all").attr("checked") && !$(this).attr("checked")){
                $("#reportDataDto\\.column\\.all").attr("checked", false);
            }
            })}
            );
  });

/*设置customer的值*/
function setCustomerValue(val,name){
  $("#reportDataDto\\.customer").val(val);
}

// Quotation or Order 表现页面
function showType(){
    var type = $("input:checked[name='reportType']").val();
    if(type == "quotation"){
        $("#quotationShow").css("display", "");
        $("#quotationShow1").css("display", "");
        $("#orderShow1").css("display", "none");
        $("#orderShow2").css("display", "none");
        $("#reportDataDto\\.type").attr("value", "quotation");
    }else if(type=="order"){
        $("#quotationShow").css("display", "none");
        $("#quotationShow1").css("display", "none");
        $("#orderShow1").css("display", "");
        $("#orderShow2").css("display", "");
        $("#reportDataDto\\.type").attr("value", "order");
    }
    $("input[name^='reportDataDto\\.status']").each(function(){
        $(this).attr("checked",false)
    });
}
</script>
	</body>
</html>