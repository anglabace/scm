//customer>>accounting>>Credit Information:Credit Cards 
function initCcpayFlag(){
	if($("#cust_ccpay_flag").attr("checked") == true){
		$("#cardList").attr("disabled", false);
		$("#cust_ccpay_flag").parent().parent().find(":button").attr("disabled", false);
	}else{
		$("#cardList").attr("disabled", true);
		$("#cust_ccpay_flag").parent().parent().find(":button").attr("disabled", true);
	}
}