package com.genscript.gsscm.shipment.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.shipment.entity.ShipRateTotalRange;

@Repository
public class ShipRateTotalRangeDao extends
		HibernateDao<ShipRateTotalRange, Integer> {
	private static final String GET_RATE_LIST = "from ShipRateTotalRange s where s.shipMethodId=? ORDER BY s.totalFrom ASC";
	private static final String GET_RATE_ID_LIST = "select s.id from ShipRateTotalRange s where s.shipMethodId=?";
	private static final String DEL_TOTAL_RANGE_LIST = "delete from ShipRateTotalRange s where s.id in (:ids)";

	@SuppressWarnings("unchecked")
	public List<ShipRateTotalRange> getShipRateTotalRangeList(
			final Integer methodId) {
		List<ShipRateTotalRange> list = createQuery(GET_RATE_LIST, methodId)
				.list();
		return list;
	}

	public Integer delTotalRangeList(Object ids) {
		Map<String, Object> map = Collections.singletonMap("ids", ids);
		return batchExecute(DEL_TOTAL_RANGE_LIST, map);
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getRateTotalRangeIdList(final Integer methodId) {
		return createQuery(GET_RATE_ID_LIST, methodId).list();
	}
}
