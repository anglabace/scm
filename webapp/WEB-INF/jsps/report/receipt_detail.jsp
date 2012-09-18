<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Receipt Detail</title>
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
					Receipt Detail
				</div>
			</div>
            <div id="ShowDetailDialog" title=" Show Detail Dialog"></div>
			<div class="input_box">
				<div class="content_box">
					<form id="save_form" method="post" class="niceform" enctype="multipart/form-data">
                        <input type="hidden" name="serviceMethod" id="serviceMethod" value="getReceiptDetailReportDate"/>
                        <input type="hidden" name="actionPage" id="actionPage" value="receipt_detail"/>
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
												<th width="210">Receiving Date</th>
												<td colspan="1" width="180px">
                                                    <s:select list="#{'today':'Today', 'week':'This Week', 'month' : 'This Month', 'quarter' : 'This Quarter', 'year':'This Year', '':'--------------------', 'yesterday':'Yesterday', 'lWeek':'Last Week', 'lMonth':'Last Month', 'lQuarter':'Last Quarter', 'lYear':'Last Year', 'custom':'Custom Date Range'}"
                                                              headerKey="" headerValue="All" id="reportDataDto.dataRange" name="reportDataDto.dataRange" value="reportDataDto.dataRange" cssStyle="width:130px"/>
												</td>
                                                <td id="dateFromTD" style="display:none;width:190px">From <input
                                                        name="reportDataDto.dataFrom" id="reportDataDto.dataFrom"
                                                        type="text" class="ui-datepicker" style="width: 124px;"
                                                        value="<s:date name="reportDataDto.dataFrom" format="yyyy-MM-dd"/>"
                                                        readonly="readonly"/>
                                                </td>
                                                <td id="dateToTD" style="display:none;">To <input
                                                        name="reportDataDto.dataTo" id="reportDataDto.dataTo"
                                                        type="text" class="ui-datepicker" style="width: 124px;"
                                                        value="<s:date name="reportDataDto.dataTo" format="yyyy-MM-dd"/>"
                                                        readonly="readonly"/>
                                                </td>
											</tr>
											<tr>
												<th width="210">Vendor</th>
												<td width="180px">
                                                    <s:select list="vendorList" listKey="id" id="vendorId"
														listValue="name" name="reportDataDto.vendorId"
														value="reportDataDto.vendorId" cssStyle="width:130px"/></td>
												<th width="210">Receiving Clerk</th>
                                                <td width="180px">
                                                    <s:select list="receivingClerks" listKey="id" id="receivingClerk"
														listValue="name" name="reportDataDto.receivingClerk"
														value="reportDataDto.receivingClerk" cssStyle="width:130px"/>
                                                </td>
											</tr>
                                            <tr>
												<th width="210">Purchase Order #</th>
												<td width="180px">
                                                    <input type="text" id="purchase_order_no" class="NFText" name="reportDataDto.purchaseOrderNo" value=""/>
												</td>
												<th width="210">Sales Order Ref</th>
												<td width="180px">
                                                    <input type="text" id="sales_order_no" class="NFText" name="reportDataDto.salesOrderNo" value=""/>
                                                </td>
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
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.segment" fieldValue="purchaseOrderNo:Purchase Order #"/>Purchase Order #</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.business_unit" fieldValue="purchaseOrderDate:Purchase Order Date"/>Purchase Order Date</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.category" fieldValue="vendorName:Vendor"/>Vendor</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.product_line" fieldValue="salesOrderNo:Sale Order Ref"/>Sale Order Ref</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.catalog_no" fieldValue="salesOrderDate:Sales Order Date"/>Sales Order Date</td>
											</tr>
                                            <tr><th width="210px"></th><td><strong>Item Info:</strong></td></tr>
                                            <tr>
                                                <th width="210"></th>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.name" fieldValue="itemNo:Item #"/>Item #</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.quantity" fieldValue="qtyReceived:Qty Received"/>Qty Received</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.qty_uom" fieldValue="sizeReceived:Size Received"/>Size Received</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.amount" fieldValue="storageName:Storage Location"/>Storage Location</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.amount" fieldValue="trackingNo:Tracking #"/>Tracking #</td>
											</tr>
                                            <tr>
                                                <th width="210px"></th>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.name" fieldValue="receivingDate:Receiving Date"/>Receiving Date</td>
                                                <td colspan="1" width="170px"><s:checkbox name="reportDataDto.column" id="reportDataDto.column.name" fieldValue="receivingClerk:Receiving Clerk"/>Receiving Clerk</td>
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
     $("#segmentType").change(function() {
        $.ajax({
            type:"post",
            url:"report/report!getCategoryData.action?segmentType=" + $(this).val(),
            dataType:"json",
            success:function(data) {
                    if (data){
                        $("#psLineId").empty();
                        for (var ps in data.psLines) {
                            $("#psLineId").append("<option value='"+ data.psLines[ps].id +"'>"+ data.psLines[ps].name +"</option>> ")
                        }
                        $("#categoryId").empty();
                        for (var ci in data.categories) {
                            $("#categoryId").append("<option value='"+ data.categories[ci].id +"'>"+ data.categories[ci].name +"</option>> ")
                        }
                        $("#busUnitId").empty();
                        for (var bs in data.busUnits) {
                            $("#busUnitId").append("<option value='"+ data.busUnits[bs].id +"'>"+ data.busUnits[bs].name +"</option>> ")
                        }
                    }
                    else
                        alert("System error! Please contact system administrator for help.");
            },
            error:function() {
                alert("System error! Please contact system administrator for help.");
            },
            async:false
        });

    });
</script>
	</body>
</html>