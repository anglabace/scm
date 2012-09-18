package com.genscript.gsscm.order.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.OrderLineErpMapping;

@Repository
public class OrderLineErpMappingDao extends HibernateDao<OrderLineErpMapping, Integer>{
	private static final String GET_ERP_ITEM = "select o.erpUsSoLine from OrderLineErpMapping o where o.orderNo=? and o.itemNo=?";
	private static final String QUERY_ERP_ITEM = "from OrderLineErpMapping o where o.orderNo=? and o.itemNo=?";
	public String getErpItemNo(final Integer orderNo, final Integer itemNo) {
		return findUnique(GET_ERP_ITEM, orderNo, itemNo);
	}
	public OrderLineErpMapping getErpItem(final Integer orderNo, final Integer itemNo) {
		return findUnique(QUERY_ERP_ITEM, orderNo, itemNo);
	}
}
