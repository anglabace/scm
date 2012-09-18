<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
	.row_odd{background-color:#FFFFFF;}
	.row_even{background-color:#EEF2FD;}
	.tr_hover{background-color:#D1F1FA;}  
	.tr_click{background-color:#84E5FF;}  
	.tr_del{background-color:#E6E6E6;}  
	.tr_{background-color:#E6E6E6;}  
</style>

<script language="javascript">
//全局变量用于order quote 通用模块
var orderquoteObj = [];
orderquoteObj.sessNoName = "sessOrderNo";
orderquoteObj.numberName = "orderNo";
orderquoteObj.sessNoValue = "${sessOrderNo}";
if (orderquoteObj.sessNoValue == null || orderquoteObj.sessNoValue == "") {
	orderquoteObj.sessNoValue = "${quorderNo}";
}
orderquoteObj.custNo = "${custNo}";
orderquoteObj.type = 'order';
orderquoteObj.searchClass = "${searchClass}";
orderquoteObj.customerNo = "${custNo}";
orderquoteObj.quorderNo = "${quorderNo}";
orderquoteObj.prmtCode = "${prmtCode}";
orderquoteObj.ajaxUrls = {
						  "getTemplateList":"order_extra!searchTemplate.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "saveTemplate":"order!saveTemplate.action",
						  "getItemDetail":"order_item!input.action?sessOrderNo="+orderquoteObj.sessNoValue+"&purchaseOrderFlag=${purchaseOrderFlag}",
						  "saveNote":"order_note!save.action",
						  "editNote":"order_note!edit.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "getItemList":"order_item!list.action?sessOrderNo="+orderquoteObj.sessNoValue,
		  				  "getItemOtherInfo":"order_item!getItemOtherInfo.action",
		  				  "getItemStatusHist":"order_item!getItemStatusHist.action?sessOrderNo="+orderquoteObj.sessNoValue,
		  				  "getItemDetailList":"order_item!detailList.action?sessOrderNo="+orderquoteObj.sessNoValue,
		  				  "updateItemQty":"order_item!updateItemQty.action?sessOrderNo="+orderquoteObj.sessNoValue,
		  				  "updateItemPrice":"order_item!updateItemPrice.action?sessOrderNo="+orderquoteObj.sessNoValue,
		  				  "updateItemCost":"order_item!updateItemCost.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "updateItemStatus":"order_item!updateItemStatus.action",
						  "updateTargetDate":"order_item!updateTargetDate.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "updateItemScheduleShipment":"order_item!updateItemScheduleShipment.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "getItemStatusList":"order_item!getItemStatusList.action",
						  "edit":"order!edit.action?orderNo=",
						  "showAddress":"order_address!list.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "showMultiAddress":"order_address!listMulti.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "showPackageList":"order_package!list.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "showPackageEdit":"order_package!input.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "packageSave":"order_package!save.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "packageDel":"order_package!delete.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "getShippingTotal":"order_total!getShippingTotal.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "calShipping":"order_total!calShipping.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "caltotalPaymentslist":"order_total!caltotalPaymentslist.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "changeShipAccount":"order_total!changeShipAccount.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "changeShipMethod":"order_total!changeShipMethod.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "changeCurrency":"order_total!changeCurrency.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "calAllItemPrice":"order_total!calAllItemPrice.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "calBill":"order_total!calBill.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "replaceOrder":"order_total!replace.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "changeCost":"order_total!changeCost.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "checkConfirm":"order!checkConfirm.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "confirm":"order!confirm.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "save":"order!save.action",
						  "isPromotionExist":"order!isPromotionExist.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "getPrmtListBySource":"order!getPrmtListBySource.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "copyItem":"order_item!copyItem.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "saveItem":"order_item!save.action",
						  "delItem":"order_item!delete.action",
						  "showTotal":"order_total!showTotal.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "showPayment":"order_payment!list.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "savePayment":"order_payment!save.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "delPayment":"order_payment!delete.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "showReturn":"order_return!list.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "saveReturn":"order_return!save.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "updateReturnItem":"order_return!updateReturnItem.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "calReturnRefund":"order_return!calReturnRefund.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "deletePoDoc":"order_payment!deletePoDoc.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "exchangeItem":"order_item!exchangeItem.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "print":"order!showPrintPage.action?orderNo="+orderquoteObj.sessNoValue+"&editFlg=Y",
						  "repeat":"order_extra!repeat.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "searchTemplate":"order_extra!searchTemplate.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "reopenOrder":"order_extra!reopen.action?sessOrderNo="+orderquoteObj.sessNoValue+"&operation_method=edit",
						  "showMore":"order_more!showMoreDetail.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "saveGene":"order_more!saveGeneSynthesis.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "saveRna":"order_more!saveRna.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "saveCustCloning":"order_more!saveCustCloning.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "saveOrfClone":"order_more!saveOrfClone.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "saveOligo":"order_more!saveOligo.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "saveCustomService":"order_more!saveCustomService.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "savePlasmid":"order_more!savePlasmid.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "saveMuta":"order_more!saveMuta.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "saveMutaLib":"order_more!saveMutaLib.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "savePkgService":"order_more!savePkgService.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "saveStdPeptide":"order_more!saveStdPeptide.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "saveBatchPeptide":"order_more!saveBatchPeptide.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "saveLibraryPeptide":"order_more!saveLibraryPeptide.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "saveArrayPeptide":"order_more!saveArrayPeptide.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "changeAntigenType":"order_more!changeAntigenType.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "saveAntibody":"order_more!saveAntibody.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "savePeptideForAntibody":"order_more!savePeptideForAntibody.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "getAntibodyPeptidePrice":"order_more!calAntibodyPeptidePrice.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "checkOtherPeptide":"order_more!checkOtherPeptide.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "checkRna":"order_more!checkRna.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "uploadServiceFile":"order_more!uploadFile.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "delServiceFile":"order_more!deleteFile.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "returnItemMap":"order_more!returnItemMap.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "applyCouponCode":"order_total!applyCouponCode.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "Allitemdetail":"order!allItemDetail.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "saveBatchSeesionItem":"order_item!saveBatchSessionItem.action?sessOrderNo="+orderquoteObj.sessNoValue,
						  "calculateTargetDate":"order_item!calculateTargetDate.action?sessOrderNo="+orderquoteObj.sessNoValue	  
						  };
//例子：orderquoteObj.url("getItemDetail");
orderquoteObj.url = function (urlName){
	return orderquoteObj.ajaxUrls[urlName];
};
</script>