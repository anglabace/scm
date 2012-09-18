package com.genscript.gsscm.shipment.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.shipment.entity.ShipRateWeightRange;

@Repository
public class ShipRateWeightRangeDao extends
		HibernateDao<ShipRateWeightRange, Integer> {
	private static final String GET_RATE_LIST = "from ShipRateWeightRange s where s.shipMethodId=? ORDER BY s.weightFrom ASC";
	private static final String GET_RATE_ID_LIST = "select s.id from ShipRateWeightRange s where s.shipMethodId=?";
	private static final String DEL_WEIGHT_RANGE_LIST = "delete from ShipRateWeightRange s where s.id in (:ids)";

	@SuppressWarnings("unchecked")
	public List<ShipRateWeightRange> getShipRateWeightRangeList(
			final Integer methodId) {
		List<ShipRateWeightRange> list = createQuery(GET_RATE_LIST, methodId)
				.list();
		return list;
	}

	public Integer delWeightRangeList(Object ids) {
		Map<String, Object> map = Collections.singletonMap("ids", ids);
		return batchExecute(DEL_WEIGHT_RANGE_LIST, map);
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getWeightTotalRangeIdList(final Integer methodId) {
		return createQuery(GET_RATE_ID_LIST, methodId).list();
	}
}
