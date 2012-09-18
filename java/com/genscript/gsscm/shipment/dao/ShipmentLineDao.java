package com.genscript.gsscm.shipment.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.shipment.entity.ShipmentLine;

@Repository
public class ShipmentLineDao extends HibernateDao<ShipmentLine, Integer> {
	public ShipmentLine getShipmentLine(Integer orderNo, Integer itemNo) {
		ShipmentLine shipmentLine = (ShipmentLine) this.getSession()
				.createCriteria(ShipmentLine.class).add(
						Restrictions.eq("order.orderNo", orderNo)).add(
						Restrictions.eq("itemNo", itemNo)).setMaxResults(1)
				.uniqueResult();
		return shipmentLine;
	}
	
	/**
	 * 通过orderNo查询
	 * @author Zhang Yong
	 * @param orderNo
	 * @return
	 */
	public List<Object[]> getShipDateByOrderNo (Integer orderNo) {
		String hql = "select concat(a.order.orderNo,'_',a.itemNo), a.shipDate from ShipmentLine a where a.order.orderNo=?";
		Query query = this.getSession().createQuery(hql);
		query.setInteger(0, orderNo);
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		return list;
	}
}
