package com.genscript.gsscm.accounting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.shipment.entity.Shipment;
@Repository
public class ArShipmentDao extends HibernateDao<Shipment, Integer>  {
	
	/**
	 * 分页查询Shipment列表
	 */
	public Page<Shipment> findPage(Page<Shipment> page,
			List<PropertyFilter> filters) {
		return super.findPage(page, filters);
	}
}
