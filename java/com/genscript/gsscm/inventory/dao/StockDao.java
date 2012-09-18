package com.genscript.gsscm.inventory.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.inventory.entity.Stock;

@Repository
public class StockDao extends HibernateDao<Stock, Integer> {
	public Long getProductStockTotal(String catalogNo) {
		String hql = "select sum(realAmount) from Stock where catalogNo=:catalogNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("catalogNo", catalogNo);
		return this.findUnique(hql, map);
	}
	
	public Long getServiceStockTotal(String catalogNo){
		String hql = "select sum(realAmount) from Stock where catalogNo=:catalogNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("catalogNo", catalogNo);
		return this.findUnique(hql, map);
	}
	/**
	 * 获得某个Product在某个warehouse里的库存量.
	 * @param catalogNo
	 * @param warehouseId
	 * @return
	 */
	public Integer getStockTotal(String catalogNo, Integer warehouseId) {
		String hql = "select realAmount from Stock where catalogNo=:catalogNo and warehouseId=:warehouseId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("catalogNo", catalogNo);
		map.put("warehouseId", warehouseId);
		return this.findUnique(hql, map);		
	}
	
	/**
	 * 查询Product的虚拟库存量。
	 * @param catalogNo
	 * @param warehouseId
	 * @return
	 */
	public Stock getStockVirtualAmount (String catalogNo, Integer warehouseId) {
		String hql = "from Stock where catalogNo=:catalogNo and warehouseId=:warehouseId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("catalogNo", catalogNo);
		map.put("warehouseId", warehouseId);
		List<Stock> stockList = this.find(hql, map);	
		if (stockList != null && !stockList.isEmpty()) {
			return stockList.get(0);
		}
		return null;
	}
}
