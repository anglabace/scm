package com.genscript.gsscm.shipment.dao;
 

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.shipment.entity.ShipCarrierBilling;

@Repository
public class ShipCarriersBillingDao extends
		HibernateDao<ShipCarrierBilling, Integer> {

	private static final String sql = " from ShipCarrierBilling where carrierId=?";

	public List<ShipCarrierBilling> getBycarrierId(Integer carrierId) {
		return this.find(sql,carrierId);
	}
	
	
	public void deleteBycarrierId(Integer carrierId) {
		String hql = "delete from ShipCarrierBilling where carrierId=:carrierId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("carrierId", carrierId);
		Query q = createQuery(hql, map);
		q.executeUpdate();
	}

}
