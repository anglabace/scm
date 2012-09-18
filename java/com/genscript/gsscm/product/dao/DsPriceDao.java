package com.genscript.gsscm.product.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.DsPrice;

/**
 * DsPriceDao
 * @author Zhang Yong
 *
 */
@Repository
public class DsPriceDao extends HibernateDao<DsPrice, Integer> {

	/**
	 * 查询DsPrice
	 * @author Zhang Yong
	 * add date 2011-11-08
	 * @param sampleType
	 * @param primerType
	 * @param itemNum
	 * @return
	 */
	public DsPrice getDsPrice (String sampleType, String primerType, Integer itemNum) {
		String hql = "FROM DsPrice dp WHERE dp.sampleType=:sampleType AND dp.primerType=:primerType AND (:itemNum BETWEEN dp.start AND dp.end)";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sampleType", sampleType);
		map.put("primerType", primerType);
		map.put("itemNum", itemNum);
		List<DsPrice> dsPriceList = this.find(hql, map);
		if (dsPriceList == null || dsPriceList.isEmpty()) {
			return null;
		}
		return dsPriceList.get(0);
	}
}
