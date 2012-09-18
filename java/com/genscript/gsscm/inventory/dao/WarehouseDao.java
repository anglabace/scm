package com.genscript.gsscm.inventory.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.inventory.entity.Warehouse;

@Repository
public class WarehouseDao extends HibernateDao<Warehouse, Integer> {
	  public Warehouse getCompanyDefaultWarehouse(Integer companyId) {
		  String hql = "from Warehouse where companyId=? and defaultFlag=?";
		  return this.findUnique(hql, companyId, "Y");		  
	  }
	  
	  /**
		 * 
		 */
		public List<Warehouse> findAll(String order,String orderBy) {
			String hql = "from Warehouse order by "+orderBy+" "+order;
			return this.find(hql);
		}
}
