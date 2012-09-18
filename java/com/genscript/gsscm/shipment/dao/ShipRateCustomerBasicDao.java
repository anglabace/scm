package com.genscript.gsscm.shipment.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.shipment.entity.ShipRateCustomerBasic;

@Repository
public class ShipRateCustomerBasicDao extends HibernateDao<ShipRateCustomerBasic, Integer> {
	private static final String GET_METHOD_ID_LIST = "select s.shipMethodId from ShipRateCustomerBasic s where s.shipMethodId=?";
	private static final String DEL_METHOD_LIST = "delete from ShipRateCustomerBasic s where s.shipMethodId in (:ids)";
	@SuppressWarnings("unchecked")
	public List<Integer> getMethodIdList(final Integer methodId) {
		return createQuery(GET_METHOD_ID_LIST, methodId).list();
	}
	public Integer delShipMethodList(Object ids){
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		return batchExecute(DEL_METHOD_LIST, map);
	}
}
