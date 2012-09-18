package com.genscript.gsscm.shipment.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.shipment.entity.ShipCarriers;

@Repository
public class ShipCarriersDao extends HibernateDao<ShipCarriers, Integer> {

	public void logicDeleteOne(Integer carrierId) {
		String sqls = "update shipping.ship_carriers set status='INACTIVE' where carrier_id="
				+ carrierId;
		Query qs = this.getSession().createSQLQuery(sqls);
		qs.executeUpdate();
	}
}
