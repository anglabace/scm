package com.genscript.gsscm.shipment.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.shipment.entity.ShipMethod;

@Repository
public class ShipMethodDao extends HibernateDao<ShipMethod, Integer> {

	/**
	 * 获得一个Order相关的Packaging的ship method的carrier.
	 * @param orderNo
	 * @param shipMethodList
	 * @return
	 */
	public Long getShipMethodCarrierCount(List<Integer> shipMethodList) {
		String hql = "select count(distinct carrier) from ShipMethod where methodId in (:shipMethodList)";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shipMethodList", shipMethodList);
		return this.findUnique(hql, map);
	}
	
	/**
	 * 根据methodId查询ShipMethod
	 * @param methodId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  List getShipMethod(Integer methodId){
		List<ShipMethod> list =this.getSession().createQuery("from ShipMethod where methodId =:methodId")
				.setInteger("methodId", methodId).list();
		return list;
	}
}
