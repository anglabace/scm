package com.genscript.gsscm.shipment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.inventory.entity.PickingLogs;

@Repository
public class PickingLogDao extends HibernateDao<PickingLogs,Integer> {
	public String savePickLog(PickingLogs log){
		String flag = "0";
		try {
			this.save(log);
			flag = "1";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return flag;
	}
	
	public List<PickingLogs> searchPickingLogsList(String lineIds){
		String hql = "from PickingLogs where pkgLineId in("+lineIds+")";
		return this.find(hql);
	}
}
