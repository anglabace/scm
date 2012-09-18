<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/taglib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Quotation Detail</title>
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
					Shipping Rate Calculator
				</div>
			</div>
			<div class="input_box">
				<div class="content_box">
					<form id="save_form" method="post" class="niceform"
						enctype="multipart/form-data">
                        <div id="ps_data">
                            <s:if test="productServiceDto!=null">
                                <s:iterator id="psDto" value="productServiceDto" status="status">
                                    <input type='hidden' name='productServiceDto[${status.index}].price' value='${psDto.price}'/>
                                    <input type='hidden' name='productServiceDto[${status.index}].clsId' value='${psDto.clsId}'/>
                                </s:iterator>
                            </s:if>
                        </div>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
										<table border="0" cellpadding="0" cellspacing="0" class="General_table">
											<tr>
												<th width="210">Ship From Country</th>
												<td colspan="1" width="160px">
                                                    <input name="fromCountry" readonly="readonly" value="US" id="reportDataDto.customer" type="text" class="NFText"/>
												</td>
                                                    <th width="190px">Ship From Zip Code</th>
                                                    <td>
                                                        <input name="fromZipCode" id="warehousingShipmentDto.fromZipCode" type="text" class="NFText" style="width: 124px;" value="${warehousingShipmentDto.fromZipCode}" readonly="readonly"/>
                                                    </td>
											</tr>
                                            <tr>
                                                <th width="204">Ship To Country</th>
												<td colspan="1" width="160px">
                                                    <s:select list="country" headerKey=""
														headerValue="please select..." name="warehousingShipmentDto.toCountry" id="toCountry" listKey="countryCode" listValue="name"
														value="warehousingShipmentDto.toCountry" cssStyle="width:80%"/>
												</td>
                                                <th>Ship To Zip Code</th>
                                                <td>
                                                    <input name="warehousingShipmentDto.toZipCode" id="toZipCode" type="text" class="NFText" style="width: 124px;" value="${warehousingShipmentDto.toZipCode}"/>
                                                </td>
                                            </tr>
											<tr>
												<th>Product/Service</th>
												<td colspan="1">
                                              <%--没有实际用途，留用扩展--%>
                                               <input name="warehousingShipmentDto.catalogNos" readonly="readonly" value="${warehousingShipmentDto.catalogNos}" id="itemId" type="text" class="NFText"/>
											   <img src="${global_image_url}search.gif" style="cursor: pointer" width="16" height="16" id="sel_product_btn"  onclick="clickPS()"/>
                                                </td>
											</tr>
                                            <tr>
                                                <th width="204">Weight</th>
                                                <td colspan="1" width="160px">
                                                     <s:select list="#{ '3' : '3', '5' : '5', '15':'15', '25':'25'}" headerKey="1"
														headerValue="1" name="warehousingShipmentDto.weight" id="weight"
														value="warehousingShipmentDto.weight" cssStyle="width:80%"/>
                                                </td>
											</tr>
                                            <tr>
                                                <th width="204">Shipping Carrier</th>
                                                <td colspan="1" width="160px">
                                                     <%--<s:select list="#{'week':'Weeks', 'month' : 'Months', 'quarter' : 'Quarters', 'year':'Years'}" headerKey="day"
														headerValue="Days" name="shippingCarrier" id="shippingCarrier"
														value="warehousingShipmentDto.shippingCarrier" cssStyle="width:80%"/>--%>
                                                    <s:select name="warehousingShipmentDto.shippingCarrier" id="shippingCarrier"
                                                              list="ShipCarriesList"
                                                              listKey="id" listValue="name" headerKey="" headerValue="please select..."
                                                              value="warehousingShipmentDto.shippingCarrier" cssStyle="width:80%"/>Ibs
                                                </td>
											</tr>
											<tr>
												<%--<th width="210">Package Type</th>
												<td width="160px"> <s:select list="#{'CNY':'CNY', 'EUR':'EUR', 'JPY':'JPY', 'GBP':'GBP'}" headerKey="USD"
														headerValue="USD" name="reportDataDto.currency"
														value="reportDataDto.TaxingScheme" cssStyle="width:160px"/></td>--%>
                                                <th width="210">Shipping Method</th>
												<td width="160px"> <s:select list="ShipMethodList" name="warehousingShipmentDto.shippingMethod" id="shippingMethod"
                                                        listKey="id" listValue="name" headerKey="" headerValue="please select..."
														value="warehousingShipmentDto.shippingMethod" cssStyle="width:120%"/></td>
											</tr>
										</table>
									<div class="invoice_title">
										<span style="cursor: pointer"
											onclick="toggle_showmore('OutPut_ParamItem', 'OutPut_Param');">
                                            <img src="${global_image_url}ad.gif" width="11" height="11"
												id="OutPut_ParamItem" /> &nbsp;Transit Time & Rates</span>
									</div>
									<div id="OutPut_Param" class="disp" style="display:block;">
										<table border="0" cellpadding="0" cellspacing="0" class="General_table">
											<tr>
                                                <th width="210">Delivery Date/Time</th>
												<td>
                                                    <input name="warehousingShipmentDto.deliveryDate" readonly="readonly" value="${warehousingShipmentDto.deliveryDate}" id="deliveryDate" type="text" class="NFText"/>
                                                </td>
											</tr>
											<tr>
                                                <th width="210">Carrier's Rate</th>
                                                <td>
                                                    <input name="warehousingShipmentDto.carrierRate" readonly="readonly" value="${warehousingShipmentDto.carrierRate}" id="carrierRate" type="text" class="NFText"/>
                                                </td>
											</tr>
                                            <tr>
                                                <th width="210">GenScript Rate</th>
                                                <td  colspan="1">
                                                <input name="warehousingShipmentDto.genScriptRate" readonly="readonly" value="${warehousingShipmentDto.genScriptRate}" id="genScriptRate" type="text" class="NFText"/>
                                                </td>
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
			<input type="submit" name="calculator" id="calculator" value=" Calculator " class="search_input"/>
		</div>


        <div id="new_ps_dlg" title=" Product/Service "></div>
<script type="text/javascript">
	var global_url = "${global_url}" ;
	var isSaved = false;
//	window.onbeforeunload = function() {
//		if(isSaved === false){
//			return 'Do you want to leave without saving data?';
//		}
//	};

    /*弹出customer list界面*/
    function clickPS() {
        $('#new_ps_dlg').dialog("option", "open", function() {
            var htmlStr = '<iframe id="iframe1" src="warehousing_shipment!showProductItemPrice.action" height=420" width="620" scrolling="no" style="border:0px" frameborder="0"></iframe>';
            $('#new_ps_dlg').html(htmlStr);
        });
        $('#new_ps_dlg').dialog('open');
    }

    $(function() {
        $('#new_ps_dlg').dialog({
                 	autoOpen: false,
					height: 520,
					width: 640,
					modal: true,
					bgiframe: true,
                    resize:'auto',
                   buttons: {
                        "Close": function() {
                     $(this).dialog('close');
            }
                   }
               });
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
            "toCountry": { required:true},
            "toZipCode": { required:true},
            "itemId": { required:true},
            "shippingCarrier": { required:true}
        },
        messages: {
            "toCountry": { required : "Please select Ship To Country !" },
            "toZipCode": { required : "Please input Ship To Zip Code !" },
            "itemId": { required : "Please select Product/Service !" },
            "shippingCarrier": { required : "Please select Shipping Carrier !" }
        },
        errorPlacement: function(error, element) {
        }
    });

    function validData(){
        if($("#itemId").val() == ""){
            alert("Please select Product/Service !");
            return false;
        }
        if($("#shippingCarrier").val() == ""){
            alert("Please select Shipping Carrier !");
            return false;
        }

        return true;
    }

    $(function() {
        $("#calculator").click(function() {
            if ($("#save_form").valid() === false) {
                return false;
            }
            if (!validData()) return false;
            var formStr = $("#save_form").serialize();
            $('#calculator').attr("disabled", true);
            $.ajax({
                type: "POST",
                url: "warehousing_shipment!calculate.action",
                data: formStr,
                dataType: 'json',
                success: function(data, textStatus) {
                    if (data != undefined && data.hasException == 'Y') {
                        $('#calculator').attr("disabled", false);
                        alert(data.exception);
                    } else {
                        $("#deliveryDate").attr("value", data.deliveryDate)
                        $("#carrierRate").attr("value", data.carrierRate)
                        $("#genScriptRate").attr("value", data.genScriptRate)
                        $('#calculator').attr("disabled", false);
                    }
                },
                error: function(xhr, textStatus) {
                    alert("failure");
                    $('#calculator').attr("disabled", false);
                }
            });

        });
    });

//弹框返回值
function setReturnValue(obj){
    $("#itemId").attr("value", "");
    var str = "";
    for(var i = 0; i< obj.length; i++){
        var price = 0;
        if(obj[i].price!="") price = obj[i].price;
        str += "<input type='hidden' name='productServiceDto["+ i +"].price' value='" + price + "'/>";
        str += "<input type='hidden' name='productServiceDto["+ i +"].clsId' value='" + obj[i].clsId + "'/>";
        $("#itemId").attr("value", $("#itemId").val()+ obj[i].catalogNo + ",")
    }
    $("#ps_data").html(str);
}
</script>
	</body>
</html>