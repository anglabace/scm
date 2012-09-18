package com.genscript.gsscm.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.system.dao.CompanyDao;

@Service
@Transactional
public class CompanyService {
	@Autowired
	private CompanyDao companyDao;
	
	public String getCompanyName(Integer companyId){
		return companyDao.getCompanyName(companyId);
	}
}
