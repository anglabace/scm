package com.genscript.gsscm.shipment.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.shipment.entity.ShipRate;

@Repository
public class ShipRateDao extends HibernateDao<ShipRate, Integer> {
	public ShipRate getShipRate(Integer shipMethodId, Integer warehouseId,
			Double weight, String zoneCode) {
		String hql = " from ShipRate where shipMethodId=:shipMethodId and warehouseId=:warehouseId " +
				" and zoneCode=:zoneCode ";
	    Map<String , Object> map = new HashMap<String , Object>();
	    map.put("shipMethodId", shipMethodId);
	    map.put("warehouseId", warehouseId);
	    map.put("zoneCode", zoneCode);
	    if (weight == 0) {
		    hql += " and :weight>=weightFrom and :weight<=weightTo ";
	    } else {
		    hql += " and :weight>weightFrom and :weight<=weightTo ";
	    }
	    map.put("weight", weight);
	    return findUnique(hql, map);
	}
	
	private static final String DEL_RATE_LIST = "delete from ShipRate s where s.id in (:ids)";
	private static final String GET_RATE_ID_LIST = "select s.id from ShipRate s where s.shipMethodId=?";
	public Integer delShipRateList(Object ids){
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		return batchExecute(DEL_RATE_LIST, map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getRateIdList(final Integer methodId) {
		return createQuery(GET_RATE_ID_LIST, methodId).list();
	}
}
