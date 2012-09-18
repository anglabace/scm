package com.genscript.gsscm.customer.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.CustomerPersonalInfo;

@Repository
public class CustPersInfoDao extends HibernateDao<CustomerPersonalInfo, Integer> {
	   public CustomerPersonalInfo getInfoByCustNo(Integer custNo) {
		   return this.findUniqueBy("custNo", custNo);
	   }
}
