package com.genscript.gsscm.accounting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.accounting.dao.ArShipmentDao;
import com.genscript.gsscm.shipment.entity.Shipment;
@Service
@Transactional
public class ArShipmentService {
	@Autowired
	private ArShipmentDao arShipmentDao;
	
	/**
	 * 根据customerID查询相关联的shipment
	 * @param page
	 * @param customerNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<Shipment> searchshipmentsByCustmomerNo(Page<Shipment> page,
			List<PropertyFilter> filters) {
		return arShipmentDao.findPage(page, filters);
	}
	
}
