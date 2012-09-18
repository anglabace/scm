package com.genscript.gsscm.customer.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.CustomerContactHistory;
import com.genscript.gsscm.customer.entity.CustomerContactSchedule;

@Repository
public class CustContactSchdDao extends HibernateDao<CustomerContactSchedule, Integer> {
	   public List<CustomerContactSchedule> getListByCust(Integer custNo) {
		   String hql = "From CustomerContactSchedule where custNo=?";
		   return this.find(hql, custNo);
	   }
	   
	   public int getMethodCount(String contactMethod) {
		   int count = 0;
		   String hql = "From CustomerContactSchedule where contactMethod=?";
		   List<CustomerContactHistory> list = this.find(hql, contactMethod);
		   if (list != null) {
			   count = list.size();
		   }
		   return count;
	   }
}
