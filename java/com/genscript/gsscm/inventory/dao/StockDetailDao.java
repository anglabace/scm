package com.genscript.gsscm.inventory.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.util.SoapUtil;
import com.genscript.gsscm.inventory.entity.StockDetail;

@Repository
public class StockDetailDao extends HibernateDao<StockDetail, Integer> {
	public StockDetail getProductStockDetail(Integer storageId,
			Integer warehouseId, String catalogNo) {
		StockDetail stockDetail = null;
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		// 如果warehouseId没传为null
		if (SoapUtil.getIntegerFromSOAP(storageId) != null) {
			PropertyFilter storageFilter = new PropertyFilter("EQI_storageId",
					storageId);
			PropertyFilter warehouseFilter = new PropertyFilter(
					"EQI_warehouseId", warehouseId);
			filterList.add(storageFilter);
			filterList.add(warehouseFilter);
		}
		PropertyFilter catalogNoFilter = new PropertyFilter("EQS_catalogNo",
				catalogNo);
		filterList.add(catalogNoFilter);
		List<StockDetail> stockList = this.find(filterList);
		if (stockList != null && !stockList.isEmpty()) {
			stockDetail = stockList.get(0);
		}
		return stockDetail;
	}
	
	/**
	 * 
	 * @param storageId
	 * @param warehouseId
	 * @param catalogNo
	 * @return
	 */
	public List<StockDetail> getStockDetailList(Integer storageId, Integer warehouseId, String catalogNo){
		Criteria criteria = this.createCriteria();
		criteria.add(Restrictions.eq("catalogNo", catalogNo));
		criteria.add(Restrictions.eq("storageId", storageId));
		criteria.add(Restrictions.eq("warehouseId", warehouseId));
		return criteria.list();
	}
	
	/**
	 * 计算一个产品或服务在当前仓库中的库存
	 * @param storageId
	 * @param warehouseId
	 * @param catalogNo
	 * @return
	 */
	public Integer getCurrentStock(Integer storageId,
			Integer warehouseId, String catalogNo) {
		Integer count = null;
		String hql = "select sum(onhandQty-reservedQty) from StockDetail where catalogNo=? and warehouseId=? and storageId=?";
		Object obj = this.findUnique(hql, catalogNo, warehouseId, storageId);		
		if (obj != null) {
		 count = ((Long)obj).intValue(); 
		}
		return count;
	}
}
