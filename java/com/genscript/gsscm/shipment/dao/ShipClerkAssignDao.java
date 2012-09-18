/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.genscript.gsscm.shipment.dao;

import java.util.List;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.shipment.entity.ShipClerkAssigned;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jinsite
 */
@Repository
public class ShipClerkAssignDao extends HibernateDao<ShipClerkAssigned,Integer> {
	public List<ShipClerkAssigned> getShipClerkAssingByItemTypeAndClsId(String itemType,Integer clsId){
		String hql = "from ShipClerkAssigned where itemType='"+itemType+"' and clsId ="+clsId;
		System.out.println(hql);
		return this.find(hql);
	}
	
	public List<ShipClerkAssigned> getShipClerkAssing(){
		String hql = "from ShipClerkAssigned";
		System.out.println(hql);
		return this.find(hql);
	}
	
}
