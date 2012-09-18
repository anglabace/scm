package com.genscript.gsscm.purchase.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.purchase.entity.PurchaseClerks;

@Repository
public class PurchaseClerksDao extends HibernateDao<PurchaseClerks, Integer> {
	
	public List<PurchaseClerks> searchPurchaseClerkList(String status){
		
		String hql = "from PurchaseClerks where status = ?";
		System.out.println(hql+status);
		return this.find(hql,status);
	}
}
