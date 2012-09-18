package com.genscript.gsscm.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.system.entity.Company;

@Repository
public class CompanyDao extends HibernateDao<Company, Integer> {
	private static final String GET_COMPANY_NAME = "select c.name from Company c";
	@SuppressWarnings("unchecked")
	public List<String> getAllCompanyName(){
		return createQuery(GET_COMPANY_NAME).list();
	}
	
	public String getCompanyName(Integer companyId){
		String hql="select c.name  from  Company c where c.companyId="+companyId+"";
		return findUnique(hql);
	}
	
	
}
