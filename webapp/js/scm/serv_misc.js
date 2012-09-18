//misc iframe checkbox control -->
function miscCkText(textName,textValue) {
	if($("[name='"+textName+"Ck']:checkbox").attr("checked")){
		$("[name='"+textName+"']").val(textValue);
	}else{
		$("[name='"+textName+"']").val("");
	}
}
var textValue_priceByPoints = $("[name='serviceDTO.priceByPoints']").val();
var textValue_customerInfo = $("[name='serviceDTO.customerInfo']").val();
$(function(){
	$("[name='priceByPointsCk']").click(function(){
		value1 = $("[name='serviceDTO.priceByPoints']").val();
		textValue_priceByPoints = value1?value1:textValue_priceByPoints;
		miscCkText("serviceDTO.priceByPoints",textValue_priceByPoints);
	});
	$("[name='customerInfoCk']").bind("click",function(){
		value2 = $("[name='serviceDTO.customerInfo']").val();
		textValue_customerInfo = value2?value2:textValue_customerInfo;
		miscCkText("serviceDTO.customerInfo",textValue_customerInfo);
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
	parent.$('#miscPickerDialog').dialog("option", "open",function(){
		var url = "product/product!showRoyaltiesList.action?type="+type;//这里与product用同一张表，所以使用相同的地址；
		var htmlStr = '<iframe src="'+url+'" height="380" width="650" scrolling="no" style="border:0px" frameborder="0"></iframe>';
		parent.$('#miscPickerDialog').html(htmlStr);
	});
	$("#miscPickerTrigger").click(function(){
		parent.$('#miscPickerDialog').dialog("open");
	});
	$("[name='serviceDTO.sellingPriceCmsn']").val(parent.$("[name='sellingPriceCmsn']").val());
	$("[name='serviceDTO.grossProfitCmsn']").val(parent.$("[name='grossProfitCmsn']").val());
	$("[name='serviceDTO.unitRateCmsn']").val(parent.$("[name='unitRateCmsn']").val());
	$("[name='serviceDTO.returnPoints']").val(parent.$("[name='returnPoints']").val());
	$("[name='serviceDTO.priceByPoints']").val(parent.$("[name='priceByPoints']").val());
	$("[name='serviceDTO.customerInfo']").val(parent.$("[name='customerInfo']").val());
	
	var noticeSendTypeValue = parent.$("[name='noticeSendType']").val();
	$("[name='serviceDTO.noticeSendType']").find("option[value='"+noticeSendTypeValue+"']").attr("selected",true);

	var noticeGenerateTime = parent.$("[name='noticeGenerateTime']").val();
	$("[name='serviceDTO.noticeGenerateTime']").find("option[value='"+noticeGenerateTime+"']").attr("selected",true);
	
	if($("[name='serviceDTO.priceByPoints']").val()) {
		$("[name='priceByPointsCk']").attr("checked",true);
	}
	
	$("[name='serviceDTO.priceByPoints']").bind("focus",function(){
		$("[name='priceByPointsCk']").attr("checked",true);
	});
	
	$("[name='serviceDTO.priceByPoints']").bind("blur",function(){
		if(!$("[name='serviceDTO.priceByPoints']").val()){
			
			$("[name='priceByPointsCk']").attr("checked","");
		}
	});
	
	if($("[name='serviceDTO.customerInfo']").val()){
		$("[name='customerInfoCk']").attr("checked",true);
	}
	$("[name='serviceDTO.customerInfo']").bind("focus",function(){
		$("[name='customerInfoCk']").attr("checked",true);
	});
	$("[name='serviceDTO.customerInfo']").bind("blur",function(){
		if(!$("[name='serviceDTO.customerInfo']").val()){
			$("[name='customerInfoCk']").attr("checked","");
		}
	});
     if ($("[name='serviceDTO.sellingPriceCmsn']").val() != "" && $("[name='serviceDTO.sellingPriceCmsn']").val() != "0.000") {
        $("#sellingPrice").attr("checked", true);
            $("[name='serviceDTO.sellingPriceCmsn']").attr("disabled", false);
        $("[name='serviceDTO.grossProfitCmsn']").attr("disabled", "disabled");
        $("[name='serviceDTO.unitRateCmsn']").attr("disabled", "disabled");
    }
    if ($("[name='serviceDTO.grossProfitCmsn']").val() != "" && $("[name='serviceDTO.grossProfitCmsn']").val() != "0.000") {
        $("#grossProfit").attr("checked", true);
            $("[name='serviceDTO.grossProfitCmsn']").attr("disabled", false);
        $("[name='serviceDTO.sellingPriceCmsn']").attr("disabled", "disabled");
        $("[name='serviceDTO.unitRateCmsn']").attr("disabled", "disabled");
    }
   // alert($("[name='serviceDTO.unitRateCmsn']").val());
    if ($("[name='serviceDTO.unitRateCmsn']").val() != "" && $("[name='serviceDTO.unitRateCmsn']").val() != "0.000") {
        $("#unitRate").attr("checked", true);
            $("[name='serviceDTO.unitRateCmsn']").attr("disabled", false);
        $("[name='serviceDTO.sellingPriceCmsn']").attr("disabled", "disabled");
        $("[name='serviceDTO.grossProfitCmsn']").attr("disabled", "disabled");
    }
});