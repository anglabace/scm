package com.genscript.gsscm.shipment.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.shipment.entity.ShippingClerkShipmentAdjustment;

@Repository
public class ShippingClerkShipmentAdjustmentDao extends HibernateDao<ShippingClerkShipmentAdjustment,Integer>{

	//根据shipmentId删除
	public void delByShipMentId(Integer id){
		String hql = "DELETE FROM ShippingClerkShipmentAdjustment WHERE shipmentId ="+id;
		this.batchExecute(hql);
	}
}
