package com.genscript.gsscm.shipment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.shipment.dao.ShipCarriersBillingDao;

@Service
@Transactional
public class ShipCarriersBillingService {

	@Autowired
	private ShipCarriersBillingDao shipCarriersBillingDao;

	public void delShipCarrierBilling(int carrierId) {
		  shipCarriersBillingDao.deleteBycarrierId(carrierId);  
	}

}
