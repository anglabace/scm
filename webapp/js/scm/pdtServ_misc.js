//misc iframe checkbox control -->
function miscCkText(textName, textValue) {
    if ($("[name='" + textName + "Ck']:checkbox").attr("checked")) {
        $("[name='" + textName + "']").val(textValue);
    } else {
        $("[name='" + textName + "']").val("");
    }
}
var textValue_priceByPoints = $("[name='productDTO.priceByPoints']").val();
var textValue_customerInfo = $("[name='productDTO.customerInfo']").val();
$(function() {
    $("[name='priceByPointsCk']").click(function() {
        value1 = $("[name='productDTO.priceByPoints']").val();
        textValue_priceByPoints = value1 ? value1 : textValue_priceByPoints;
        miscCkText("productDTO.priceByPoints", textValue_priceByPoints);
    });
    $("[name='customerInfoCk']").bind("click", function() {
        value2 = $("[name='productDTO.customerInfo']").val();
        textValue_customerInfo = value2 ? value2 : textValue_customerInfo;
        miscCkText("productDTO.customerInfo", textValue_customerInfo);
    });
    var psId = parent.$("#psId").val();

    parent.$('#miscPickerDialog').dialog({
        autoOpen: false,
        height: 450,
        width: 680,
        modal: true,
        bgiframe: true,
        buttons: {}
    });
    parent.$('#miscPickerDialog').dialog("option", "open", function() {
        var url = "product/product!showRoyaltiesList.action?type=" + type;
        var htmlStr = '<iframe src="' + url + '" height="380" width="650" scrolling="no" style="border:0px" frameborder="0"></iframe>';
        parent.$('#miscPickerDialog').html(htmlStr);
    });
    $("#miscPickerTrigger").click(function() {
        parent.$('#miscPickerDialog').dialog("open");
    });
    $("[name='productDTO.sellingPriceCmsn']").val(parent.$("[name='sellingPriceCmsn']").val());
    $("[name='productDTO.grossProfitCmsn']").val(parent.$("[name='grossProfitCmsn']").val());
    $("[name='productDTO.unitRateCmsn']").val(parent.$("[name='unitRateCmsn']").val());
    $("[name='productDTO.returnPoints']").val(parent.$("[name='returnPoints']").val());
    $("[name='productDTO.priceByPoints']").val(parent.$("[name='priceByPoints']").val());
    $("[name='productDTO.customerInfo']").val(parent.$("[name='customerInfo']").val());


    var noticeSendTypeValue = parent.$("[name='noticeSendType']").val();
    $("[name='productDTO.noticeSendType']").find("option[value='" + noticeSendTypeValue + "']").attr("selected", true);

    var noticeGenerateTime = parent.$("[name='noticeGenerateTime']").val();
    $("[name='productDTO.noticeGenerateTime']").find("option[value='" + noticeGenerateTime + "']").attr("selected", true);

       if ($("[name='productDTO.sellingPriceCmsn']").val() != "" && $("[name='productDTO.sellingPriceCmsn']").val() != "0.000") {
        $("#sellingPrice").attr("checked", true);
            $("[name='productDTO.sellingPriceCmsn']").attr("disabled", false);
        $("[name='productDTO.grossProfitCmsn']").attr("disabled", "disabled");
        $("[name='productDTO.unitRateCmsn']").attr("disabled", "disabled");
    }
    if ($("[name='productDTO.grossProfitCmsn']").val() != "" && $("[name='productDTO.grossProfitCmsn']").val() != "0.000") {
        $("#grossProfit").attr("checked", true);
            $("[name='productDTO.grossProfitCmsn']").attr("disabled", false);
        $("[name='productDTO.sellingPriceCmsn']").attr("disabled", "disabled");
        $("[name='productDTO.unitRateCmsn']").attr("disabled", "disabled");
    }
    if ($("[name='productDTO.unitRateCmsn']").val() != "" && $("[name='productDTO.unitRateCmsn']").val() != "0.000") {
        $("#unitRate").attr("checked", true);
            $("[name='productDTO.unitRateCmsn']").attr("disabled", false);
        $("[name='productDTO.sellingPriceCmsn']").attr("disabled", "disabled");
        $("[name='productDTO.grossProfitCmsn']").attr("disabled", "disabled");
    }

    if ($("[name='productDTO.priceByPoints']").val()) {
        $("[name='priceByPointsCk']").attr("checked", true);
    }

    $("[name='productDTO.priceByPoints']").bind("focus", function() {
        $("[name='priceByPointsCk']").attr("checked", true);
    });

    $("[name='productDTO.priceByPoints']").bind("blur", function() {
        if (!$("[name='productDTO.priceByPoints']").val()) {

            $("[name='priceByPointsCk']").attr("checked", "");
        }
    });

    if ($("[name='productDTO.customerInfo']").val()) {
        $("[name='customerInfoCk']").attr("checked", true);
    }
    $("[name='productDTO.customerInfo']").bind("focus", function() {
        $("[name='customerInfoCk']").attr("checked", true);
    });
    $("[name='productDTO.customerInfo']").bind("blur", function() {
        if (!$("[name='productDTO.customerInfo']").val()) {
            $("[name='customerInfoCk']").attr("checked", "");
        }
    });
});