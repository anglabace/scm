package com.genscript.gsscm.shipment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.shipment.dao.ShipMethodDao;
import com.genscript.gsscm.shipment.entity.ShipMethod;

@Service
@Transactional
public class ShipMethodService {
	@Autowired
	private ShipMethodDao shipMethodDao;

	/**
	 * 根据shipMethodId查询ShipMethod
	 * 
	 * @param methodId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getShipMethod(Integer shipMethodId) {
		List shipMethod = this.shipMethodDao.getShipMethod(shipMethodId);
		return shipMethod;
	}
	
	public List<ShipMethod> searchShipMethodAll(){
		return this.shipMethodDao.getAll();
	}
	
	public ShipMethod getShipM(Integer shipMethodId){
		return this.shipMethodDao.getById(shipMethodId);
	} 
	
	public List<ShipMethod> getShipMethodList(){
		return this.shipMethodDao.getAll();
	} 

	public ShipMethodDao getShipMethodDao() {
		return shipMethodDao;
	}

	public void setShipMethodDao(ShipMethodDao shipMethodDao) {
		this.shipMethodDao = shipMethodDao;
	}
	
	public ShipMethod getShipMethodById(Integer id){
		return this.shipMethodDao.getById(id);
	}

}