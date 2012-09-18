<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css"> 
	.tr_hover{background-color:#D1F1FA;}  
	.tr_click{background-color:#84E5FF;}  
	.tr_del{background-color:#E6E6E6;}  
	.tr_{background-color:#E6E6E6;}  
</style>

<script language="javascript">
//全局变量用于order quote 通用模块
var orderquoteObj = [];
orderquoteObj.sessNoName = "sessQuoteNo";
orderquoteObj.numberName = "quoteNo";
orderquoteObj.sessNoValue = "${sessQuoteNo}";
if (orderquoteObj.sessNoValue == null || orderquoteObj.sessNoValue == "") {
	orderquoteObj.sessNoValue = "${quorderNo}";
}
orderquoteObj.custNo = "${quote.custNo}";
orderquoteObj.type = 'quote';
orderquoteObj.searchClass = "${searchClass}";  
orderquoteObj.customerNo = "${custNo}";
orderquoteObj.tempStatus="${quote.status}";
orderquoteObj.quorderNo = "${quorderNo}";
orderquoteObj.prmtCode = "${prmtCode}";
orderquoteObj.ajaxUrls = {
						   "getTemplateList":"quote_extra!searchTemplate.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "editNote":"quote_note!edit.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "changeAntigenType":"quote_more!changeAntigenType.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "saveAntibody":"quote_more!saveAntibody.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "savePeptideForAntibody":"quote_more!savePeptideForAntibody.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "getPrmtListBySource":"quote!getPrmtListBySource.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "savePkgService":"quote_more!savePkgService.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "saveGene":"quote_more!saveGeneSynthesis.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "saveRna":"quote_more!saveRna.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "saveCustCloning":"quote_more!saveCustCloning.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "savePlasmid":"quote_more!savePlasmid.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "saveMuta":"quote_more!saveMuta.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "saveMutaLib":"quote_more!saveMutaLib.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "saveOrfClone":"quote_more!saveOrfClone.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "saveOligo":"quote_more!saveOligo.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "saveBatchPeptide":"quote_more!saveBatchPeptide.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "uploadServiceFile":"quote_more!uploadFile.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "delServiceFile":"quote_more!deleteFile.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "showMore":"quote_more!showMoreDetail.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "saveArrayPeptide":"quote_more!saveArrayPeptide.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "checkOtherPeptide":"quote_more!checkOtherPeptide.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "checkRna":"quote_more!checkRna.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "getItemDetail":"quote_item!input.action?sessQuoteNo="+orderquoteObj.sessNoValue+"&tempStatusStr="+orderquoteObj.tempStatus,
						  "saveNote":"quote_note!save.action",
						  "reopenOrder":"quote_extra!reopen.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "getItemList":"quote_item!list.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "showAddress":"quote_address!list.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "showMultiAddress":"quote_address!listMulti.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "isPromotionExist":"quote!isPromotionExist.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "getItemOtherInfo":"quote_item!getItemOtherInfo.action",
						  "saveCustomService":"quote_more!saveCustomService.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "saveLibraryPeptide":"quote_more!saveLibraryPeptide.action?sessQuoteNo="+orderquoteObj.sessNoValue,
		  				  "getItemStatusHist":"quote_item!getItemStatusHist.action?sessQuoteNo="+orderquoteObj.sessNoValue,
		  				  "updateItemScheduleShipment":"quote_item!updateItemScheduleShipment.action?sessQuoteNo="+orderquoteObj.sessNoValue,
		  				  "getItemDetailList":"quote_item!detailList.action?sessQuoteNo="+orderquoteObj.sessNoValue,
		  				  "packageDel":"quote_package!delete.action?sessQuoteNo="+orderquoteObj.sessNoValue,
		  				  "packageSave":"quote_package!save.action?sessQuoteNo="+orderquoteObj.sessNoValue,
		  				  "showPackageList":"quote_package!list.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "showPackageEdit":"quote_package!input.action?sessQuoteNo="+orderquoteObj.sessNoValue,
		  				  "updateItemQty":"quote_item!updateItemQty.action?sessQuoteNo="+orderquoteObj.sessNoValue,
		  				  "updateItemPrice":"quote_item!updateItemPrice.action?sessQuoteNo="+orderquoteObj.sessNoValue,
		  				  "updateItemCost":"quote_item!updateItemCost.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "updateItemStatus":"quote_item!updateItemStatus.action",
						  "updateTargetDate":"quote_item!updateTargetDate.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "getItemStatusList":"quote_item!getItemStatusList.action",
						  "exchangeItem":"quote_item!exchangeItem.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						 "repeat":"quote_extra!repeat.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "save":"quote!save.action",
					 
						  "isPromotionExist":"quote!isPromotionExist.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "copyItem":"quote_item!copyItem.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "saveItem":"quote_item!save.action",
						  "delItem":"quote_item!delete.action",
						  "exchangeItem":"quote_item!exchangeItem.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						 "calAllItemPrice":"quote_total!calAllItemPrice.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						  "edit":"quote/quote!edit.action?quoteNo=",
						  "showTotal":"quote_total!showTotal.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						   "getShippingTotal":"quote_total!getShippingTotal.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						   "calShipping":"quote_total!calShipping.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						   "saveTemplate":"quote!saveTemplate.action",
						   "calBill":"quote_total!calBill.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						   "changeShipMethod":"quote_total!changeShipMethod.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						    "changeCurrency":"quote_total!changeCurrency.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						    "changeCost":"quote_total!changeCost.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						    "getAntibodyPeptidePrice":"quote_more!calAntibodyPeptidePrice.action?sessQuoteNo="+orderquoteObj.sessNoValue,
                            "searchTemplate":"quote!saveTmplForm.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						    "changeShipAccount":"quote_total!changeShipAccount.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						    "print":"quote!showPrintPage.action?quoteNo="+orderquoteObj.sessNoValue+"&editFlg=Y",
						    "saveStdPeptide":"quote_more!saveStdPeptide.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						    "returnItemMap":"quote_more!returnItemMap.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						    "run":"quote_extra!run.action",
						    "PrintDocument":"quote_extra!Printdocument.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						    "applyCouponCode":"quote_total!applyCouponCode.action?sessQuoteNo="+orderquoteObj.sessNoValue,
						    "PrintTxt":"quote_extra!PrintTxt.action?sessQuoteNo="+orderquoteObj.sessNoValue,
                            "saveBatchSeesionItem":"quote_item!saveBatchSessionItem.action?sessQuoteNo="+orderquoteObj.sessNoValue,
                            "calculateTargetDate":"quote_item!calculateTargetDate.action?sessQuoteNo="+orderquoteObj.sessNoValue
						  };
//例子：orderquoteObj.url("getItemDetail");
orderquoteObj.url = function (urlName){
	return orderquoteObj.ajaxUrls[urlName];
};
</script>