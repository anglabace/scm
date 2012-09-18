package com.genscript.gsscm.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.quote.dto.QuoteItemDTO;
import com.genscript.gsscm.quote.service.QuoteService;

public class QuoteSessionUtil {

	/**
	 * 删除所有package
	 */
	public static void removeAllPackages(QuoteService quoteService, String sessQuoteNo) {
		SessionUtil.deleteRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
//		if (StringUtils.isNumeric(sessQuoteNo)) {
//			Map<String, QuotePackageDTO> newSessPackageMap = new HashMap<String, QuotePackageDTO>();
//			List<QuotePackageDTO> packageList = quoteService
//					.getBaseQuoteShipPackageList(Integer.parseInt(sessQuoteNo));
//			if (packageList != null && packageList.size() > 0) {
//				for (int i = 0; i < packageList.size(); i++) {
//					Integer tmpId = packageList.get(i).getPackageId();
//					newSessPackageMap.put(tmpId.toString(), null);
//				}
//			}
//			SessionUtil.insertRow(SessionConstant.QuotePackage.value(),
//					sessQuoteNo, newSessPackageMap);
//		}
	}

	/**
	 * 删除total统计信息，但不删除
	 * @param sessQuoteNo
	 */
	public static void deleteTotal(String sessQuoteNo){
		SessionUtil.deleteRow(SessionConstant.QuoteShippingTotal.value(), sessQuoteNo);
		SessionUtil.deleteRow(SessionConstant.QuoteBillingTotal.value(), sessQuoteNo);
	}
	
	/**
	 * 获得父子相关联的所有item
	 * 
	 * @param itemId
	 * @return
	 */
	public static Map<String, QuoteItemDTO> getChildItemMap(String itemId, Map<String, QuoteItemDTO> itemMap) {
		Map<String, QuoteItemDTO> newItemMap = new HashMap<String, QuoteItemDTO>();
		Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
				.iterator();
		while (it.hasNext()) {
			Entry<String, QuoteItemDTO> entry = it.next();
			QuoteItemDTO quoteItemDTO = entry.getValue();
			String tmpId = entry.getKey();
			String tmpParentId = quoteItemDTO.getParentId();
			if (tmpId.equals(itemId)) {
				newItemMap.put(tmpId, quoteItemDTO);
			}
			if (!StringUtils.isEmpty(tmpParentId)
					&& !tmpParentId.equalsIgnoreCase("0")
					&& itemId.equals(tmpParentId)) {
				newItemMap.put(tmpId, quoteItemDTO);
			}
		}
		return newItemMap;
	}
}
