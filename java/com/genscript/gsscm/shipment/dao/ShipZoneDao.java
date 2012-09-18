package com.genscript.gsscm.shipment.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.shipment.entity.ShipZone;

@Repository
public class ShipZoneDao extends HibernateDao<ShipZone, Integer> {
	public ShipZone getShipZone(Integer shipMethodId, Integer warehouseId,
			String zipCode, String country) {
		String hql = "from ShipZone where shipMethodId=:shipMethodId and warehouseId=:warehouseId";
		hql += " and country=:country and :zipCode BETWEEN IFNULL(zipFrom,:zipCode) AND IFNULL(zipTo,:zipCode)";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shipMethodId", shipMethodId);
		map.put("warehouseId", warehouseId);
		map.put("country", country);
		map.put("zipCode", zipCode);
		List<ShipZone> list = find(hql, map);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}

	private static final String DEL_ZONE_LIST = "delete from ShipZone s where s.id in (:ids)";
	private static final String GET_ZONE_ID_LIST = "select s.id from ShipZone s where s.shipMethodId=?";

	public Integer delShipZoneList(Object ids) {
		Map<String, Object> map = Collections.singletonMap("ids", ids);
		return batchExecute(DEL_ZONE_LIST, map);
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getZoneIdList(final Integer methodId) {
		return createQuery(GET_ZONE_ID_LIST, methodId).list();
	}
}
