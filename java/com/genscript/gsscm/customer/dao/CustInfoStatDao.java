package com.genscript.gsscm.customer.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.CustInfoStat;

@Repository
public class CustInfoStatDao extends HibernateDao<CustInfoStat, Integer>{
   public CustInfoStat getInfoByCustNo(Integer custNo) {
	   return this.findUniqueBy("custNo", custNo);
   }
   
   public Double getCurrentBalance(Integer custNo){
	   CustInfoStat custInfoStat = this.getInfoByCustNo(custNo);
	   if(custInfoStat != null && custInfoStat.getCurrentBalance() != null){
		   return custInfoStat.getCurrentBalance();
	   }
	   return 0.0;
   }
}
