package com.genscript.gsscm.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.quoteorder.dto.ShippingTotalDTO;
import com.genscript.gsscm.quoteorder.dto.TotalDTO;

public class OrderSessionUtil {

	/**
	 * 删除所有package
	 */
	public static void removeAllPackages(OrderService orderService, String sessOrderNo) {
		SessionUtil.deleteRow(SessionConstant.OrderPackage.value(), sessOrderNo);
//		if (StringUtils.isNumeric(sessOrderNo)) {
//			Map<String, OrderPackageDTO> newSessPackageMap = new HashMap<String, OrderPackageDTO>();
//			List<OrderPackageDTO> packageList = orderService
//					.getBaseOrderOrderPackageList(Integer.parseInt(sessOrderNo));
//			if (packageList != null && packageList.size() > 0) {
//				for (int i = 0; i < packageList.size(); i++) {
//					Integer tmpId = packageList.get(i).getPackageId();
//					newSessPackageMap.put(tmpId.toString(), null);
//				}
//			}
//			SessionUtil.insertRow(SessionConstant.OrderPackage.value(), sessOrderNo, newSessPackageMap);
//		}
	}
	
	/**
	 * 清空total统计信息，但不删除
	 * @param sessOrderNo
	 */
	public static void emptyTotal(String sessOrderNo){
		ShippingTotalDTO shippingTotalDTO = new ShippingTotalDTO();
		SessionUtil.insertRow(SessionConstant.OrderShippingTotal.value(), sessOrderNo, shippingTotalDTO);//清空total
		TotalDTO totalDTO = new TotalDTO();
		SessionUtil.insertRow(SessionConstant.OrderBillingTotal.value(), sessOrderNo, totalDTO);//清空total
	}
	
	/**
	 * 删除total统计信息，但不删除
	 * @param sessOrderNo
	 */
	public static void deleteTotal(String sessOrderNo){
		SessionUtil.deleteRow(SessionConstant.OrderShippingTotal.value(), sessOrderNo);
		SessionUtil.deleteRow(SessionConstant.OrderBillingTotal.value(), sessOrderNo);
	}
	
	/**
	 * 获得父子相关联的所有item
	 * 
	 * @param itemId
	 * @return
	 */
	public static Map<String, OrderItemDTO> getChildItemMap(String itemId, Map<String, OrderItemDTO> itemMap) {
		Map<String, OrderItemDTO> newItemMap = new HashMap<String, OrderItemDTO>();
		Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
				.iterator();
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			OrderItemDTO orderItemDTO = entry.getValue();
			String tmpId = entry.getKey();
			String tmpParentId = orderItemDTO.getParentId();
			if (tmpId.equals(itemId)) {
				newItemMap.put(tmpId, orderItemDTO);
			}
			if (!StringUtils.isEmpty(tmpParentId)
					&& !tmpParentId.equalsIgnoreCase("0")
					&& itemId.equals(tmpParentId)) {
				newItemMap.put(tmpId, orderItemDTO);
			}
		}
		return newItemMap;
	}

}
