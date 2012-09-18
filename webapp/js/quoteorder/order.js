var globalCfgObj = [];
globalCfgObj.itemDetailFormId = "itemForm";
globalCfgObj.itemTableId = "itemTable";
globalCfgObj.noValue = noValue ;
globalCfgObj.custNo = custNo ;
globalCfgObj.global_url = globalUrl ;
globalCfgObj.defaultTabIndex = defaultTabIndex ;
globalCfgObj.page = 'order' ;
globalCfgObj.ajaxUrls = {"saveItemDetailAct":"order/orderItem/saveItemDetailAct", 
						 "saveOrderAct":"order/order/saveOrderAct", 
						 "savePaymentAct":"order/orderTotal/savePaymentAct"};
globalCfgObj.getUrl = function (urlName){
	return globalCfgObj.ajaxUrls[urlName];
};